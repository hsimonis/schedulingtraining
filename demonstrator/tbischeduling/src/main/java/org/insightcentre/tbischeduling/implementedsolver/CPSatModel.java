package org.insightcentre.tbischeduling.implementedsolver;

import com.google.ortools.Loader;
import com.google.ortools.sat.*;
import org.insightcentre.tbischeduling.datamodel.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static com.google.ortools.sat.CpSolverStatus.FEASIBLE;
import static com.google.ortools.sat.CpSolverStatus.OPTIMAL;
import static java.util.stream.Collectors.groupingBy;
import static org.insightcentre.tbischeduling.datamodel.SolverStatus.*;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class CPSatModel extends AbstractModel{
    int jaNr=0;
    public CPSatModel(Scenario base, SolverRun run){
        super(base,run);
    }

    public boolean solve(){
        info("Starting solver");
        long startTime = System.currentTimeMillis();
        int nrTasks = base.getListTask().size();
        int nrJobs = base.getListJob().size();
        int horizon = base.getHorizon();

        // disjunctive resources
        int nrDisjunctiveResources = base.getListDisjunctiveResource().size();
        DisjunctiveResource[] disjRes = new DisjunctiveResource[nrDisjunctiveResources];
        int i =0;
        Hashtable<DisjunctiveResource,Integer> disjHash = new Hashtable<>();
        for(DisjunctiveResource r:base.getListDisjunctiveResource()){
            disjRes[i] = r;
            disjHash.put(r,i);
            i++;
        }

        Loader.loadNativeLibraries();
        // Creates the model.
        CpModel model = new CpModel();

        IntVar[] start = new IntVar[nrTasks];
        IntVar[] end = new IntVar[nrTasks];
        IntervalVar[] tasks = new IntervalVar[nrTasks];
        IntervalVar[][] z = new IntervalVar[nrTasks][nrDisjunctiveResources];
        BoolVar[][] p = new BoolVar[nrTasks][nrDisjunctiveResources];
        IntVar[] jStart = new IntVar[nrJobs];
        IntVar[] jEnd = new IntVar[nrJobs];

        i=0;
        Hashtable<Task,Integer> taskHash = new Hashtable<>();
        for(Task t:base.getListTask()){
            taskHash.put(t,i);
//            info("task "+i+" dur "+t.getDuration());
            start[i] = model.newIntVar(0, horizon, "start" + t.getName());
            end[i] = model.newIntVar(0, horizon, "end" + t.getName());
            tasks[i] = model.newIntervalVar(start[i], LinearExpr.constant(t.getDuration()), end[i], "task" + t.getName());
            if (t.getMachines().size() == 1) {
                int m =disjHash.get(t.getMachines().get(0));
                z[i][m] = tasks[i];
            } else {
                Literal[] presences = new Literal[t.getMachines().size()];
                int k= 0;
                for (DisjunctiveResource r : t.getMachines()) {
                    int m = disjHash.get(r);
                    IntVar zStart = model.newIntVar(0, horizon, "zstart" + i+"_"+m);
                    IntVar zEnd = model.newIntVar(0, horizon, "zend" + i+"_"+m);
                    BoolVar presence = model.newBoolVar("presence" + i + "_" + m);
                    presences[k++] = presence;
                    z[i][m] = model.newOptionalIntervalVar(zStart, LinearExpr.constant(t.getDuration()), zEnd, presence, "ztask" + i + "_" + m);
                    p[i][m] = presence;
                    model.addEquality(zStart,start[i]).onlyEnforceIf(presence);
                    model.addEquality(zEnd,end[i]).onlyEnforceIf(presence);
                }
                model.addExactlyOne(presences);
            }
            i++;
        }
        i =0;
        Hashtable<Job,Integer> jobHash = new Hashtable<>();
        for(Job j:base.getListJob()){
            jobHash.put(j,i);
            jStart[i] = model.newIntVar(0, horizon, "start" + j.getName());
            jEnd[i] = model.newIntVar(0, horizon, "end" + j.getName());
            i++;
        }


        // Precedences between tasks
        //??? needs to deal with offset
        for (Task t:base.getListTask()) {
            for(Task after:t.getPrecedes()){
//                info("seq "+t+" "+after);
                ProcessSequence ps = findProcessSequence(t.getProcessStep(),after.getProcessStep());
                switch (ps.getSequenceType()) {
                    case EndBeforeStart -> model.addGreaterOrEqual(start[taskHash.get(after)], end[taskHash.get(t)]);
                    case StartBeforeStart ->
                            model.addGreaterOrEqual(start[taskHash.get(after)], start[taskHash.get(t)]);
                    default -> {
                        severe("Bad sequenceType " + ps.getSequenceType());
                        assert (false);
                    }
                }
            }
        }
        // link job start /end to tasks
        for(Job j:base.getListJob()){
            List<IntVar> starts = base.getListTask().stream().filter(x->x.getJob()==j).map(x->start[taskHash.get(x)]).toList();
            model.addMinEquality(jStart[jobHash.get(j)], starts);
            List<IntVar> ends = base.getListTask().stream().filter(x->x.getJob()==j).map(x->end[taskHash.get(x)]).toList();
            model.addMaxEquality(jEnd[jobHash.get(j)], ends);
            if (run.getEnforceReleaseDate()) {
                model.addGreaterOrEqual(jStart[jobHash.get(j)], j.getOrder().getRelease());
            }
            if (run.getEnforceDueDate()) {
                info("due date "+j.getName()+" due "+j.getOrder().getDue());
                model.addLessOrEqual(jEnd[jobHash.get(j)], j.getOrder().getDue());
            }
        }
        // disjunctive Resources
        for(DisjunctiveResource m:base.getListDisjunctiveResource()){
            int mIndex = disjHash.get(m);
            List<IntervalVar> list = new ArrayList<>();
            for(int ii=0;ii<nrTasks;ii++){
                if (z[ii][mIndex] != null){
                    list.add(z[ii][mIndex]);
                }
            }
            // add wip, downtime
            for(WiP wip:base.getListWiP().stream().filter(x->x.getDisjunctiveResource()==m).toList()){
                IntervalVar wTask = model.newFixedSizeIntervalVar(model.newConstant(0),wip.getDuration(),wip.getName());
                list.add(wTask);
                info("Machine "+m.getName()+" add wip "+wip.getEnd());
            }
            for(Downtime down:base.getListDowntime().stream().filter(x->x.getDisjunctiveResource()==m).toList()){
                IntervalVar dTask = model.newFixedSizeIntervalVar(model.newConstant(down.getStart()),down.getDuration(),down.getName());
                list.add(dTask);
                info("Machine "+m.getName()+" add down "+down.getStart()+"-"+down.getEnd());
            }
            info("Machine "+m+" tasks "+list.size());
            model.addNoOverlap(list);
        }
        // noOverlap jobs
        for(Job j:base.getListJob().stream().filter(Job::getNoOverlap).toList()){
            List<IntervalVar> list = base.getListTask().stream().filter(x->x.getJob() == j).map(x->tasks[taskHash.get(x)]).toList();
            info("Job "+j+" tasks "+list.size());
            model.addNoOverlap(list);
        }
        // cumulative resources
        if (run.getEnforceCumulative()) {
            for (CumulativeResource r : base.getListCumulativeResource()) {
                int limit = limit(r);
                CumulativeConstraint cumul = model.addCumulative(limit);

                Hashtable<ProcessStep, Integer> demandHash = new Hashtable<>();
                int needs = 0;
                for (CumulativeNeed cn : base.getListCumulativeNeed().stream().
                        filter(x -> x.getCumulativeResource() == r).
                        toList()) {
                    demandHash.put(cn.getProcessStep(), cn.getDemand());
                    needs++;
                }
                int cnt = 0;
                for (Task t : base.getListTask().stream().toList()) {
                    Integer demand = demandHash.get(t.getProcessStep());
                    if (demand != null) {
                        cumul.addDemand(tasks[taskHash.get(t)], demand);
                        cnt++;
                    }
                }
                info("Cumulative " + r + " limit " + limit + " needs " + needs + " cnt " + cnt);

            }
        }

        // Makespan objective
        IntVar objVar = model.newIntVar(0, base.getHorizon(), "makespan");
        List<IntVar> ends = new ArrayList<>();
        for (Job j:base.getListJob()) {
            ends.add(jEnd[jobHash.get(j)]);
        }
        model.addMaxEquality(objVar, ends);
        model.minimize(objVar);

        // Creates a solver and solves the model.
        CpSolver solver = new CpSolver();
        solver.getParameters().setMaxTimeInSeconds(run.getTimeout());
        solver.getParameters().setNumWorkers(run.getNrThreads());
        info("solving for "+run.getTimeout()+" seconds with "+run.getNrThreads()+" workers...");

        CpSolverStatus status = solver.solve(model);
        run.setSolverStatus(toSolverStatus(status));
        run.setTime(solver.wallTime());
        if (status == OPTIMAL || status == FEASIBLE) {
            // extract solution
            int cost = (int) Math.round(solver.objectiveValue());
            double bound = solver.bestObjectiveBound();
            double gap = (cost-bound)/cost;
            info("Solved cost "+cost+" Bound "+bound+" gapPercent "+gap*100.0+" in "+solver.wallTime()+" seconds");
            Solution solution = new Solution(base);
            solution.setName("cpsat");
            solution.setObjectiveValue(cost);
            solution.setBound(bound);
            solution.setGap(gap);
            solution.setSolverRun(run);
            solution.setSolverStatus(toSolverStatus(status));
            Hashtable<Job, JobAssignment> jobAssignmentHash = new Hashtable<>();
            int taNr=0;
            for(Task t:base.getListTask()){
                int ii = taskHash.get(t);
                int startValue = (int) Math.round(solver.value(start[ii]));
                int endValue = (int) Math.round(solver.value(end[ii]));
//                info("Start "+t+" "+startValue+" "+endValue);
                TaskAssignment ta = new TaskAssignment(base);
                ta.setName("TA"+taNr++);
                ta.setTask(t);
                ta.setStart(startValue);
                ta.setEnd(endValue);
                ta.setDuration(t.getDuration());
                ta.setJobAssignment(findJobAssignment(t.getJob(), solution, jobAssignmentHash));
                if (t.getMachines().size()==1){
                    ta.setDisjunctiveResource(t.getMachines().get(0));
                } else {
                    boolean found = false;
                    for (DisjunctiveResource m : t.getMachines()) {
                        int mm = disjHash.get(m);
                        boolean present = solver.booleanValue(p[ii][mm]);
                        if (present) {
                            assert(!found);
                            ta.setDisjunctiveResource(m);
                            found = true;
                        }
                    }
                    assert(found);
                }

            }
            updateJA(solution);
            return true;
        } else {
            info("No solution, status "+status);
            return false;
        }

    }

    private JobAssignment findJobAssignment(Job j, Solution s, Hashtable<Job,JobAssignment> jobHash){
        JobAssignment res = jobHash.get(j);
        if (res == null){
            res = new JobAssignment(base);
            res.setName("JA"+jaNr++);
            res.setJob(j);
            res.setSolution(s);
            jobHash.put(j,res);
        }
        return res;
    }

    private SolverStatus toSolverStatus(CpSolverStatus status){
        switch(status){
            case OPTIMAL:return Optimal;
            case FEASIBLE: return Solution;
            case INFEASIBLE: return Infeasible;
            case UNKNOWN: return Unknown;
            default:
                severe("Bad cp solver status "+status);
                return null;
        }
    }

    private void updateJA(Solution solution){
        Map<JobAssignment, List<TaskAssignment>> map = base.getListTaskAssignment().stream().
                filter(x->x.getJobAssignment().getSolution()==solution).
                collect(groupingBy(TaskAssignment::getJobAssignment));
        for(JobAssignment ja:map.keySet()){
            List<TaskAssignment> tasks = map.get(ja);
            ja.setStart(tasks.stream().mapToInt(TaskAssignment::getStart).min().orElse(0));
            ja.setEnd(tasks.stream().mapToInt(TaskAssignment::getEnd).max().orElse(0));
            ja.setDuration(ja.getEnd()-ja.getStart());
            ja.setEarly(Math.max(0,ja.getJob().getOrder().getDue()-ja.getEnd()));
            ja.setLate(Math.max(0,ja.getEnd()-ja.getJob().getOrder().getDue()));
        }
        List<JobAssignment> jList =base.getListJobAssignment().stream().filter(x->x.getSolution()==solution).toList();
        solution.setStart(jList.stream().mapToInt(JobAssignment::getStart).min().orElse(0));
        solution.setEnd(jList.stream().mapToInt(JobAssignment::getEnd).max().orElse(0));
        solution.setMakespan(jList.stream().mapToInt(JobAssignment::getEnd).max().orElse(0));
        solution.setFlowtime(jList.stream().mapToInt(JobAssignment::getEnd).sum());
        solution.setDuration(solution.getEnd()-solution.getStart());
        List<JobAssignment> lateJobs = base.getListJobAssignment().stream().
                filter(x->x.getSolution()==solution).
                filter(x->x.getLate() > 0).
                toList();
        solution.setNrLate(lateJobs.size());
        solution.setPercentLate(100.0*lateJobs.size()/base.getListJob().size());
        solution.setTotalLateness(lateJobs.stream().mapToInt(JobAssignment::getLate).sum());
        solution.setMaxLateness(lateJobs.stream().mapToInt(JobAssignment::getLate).max().orElse(0));
        solution.setWeightedLateness(lateJobs.stream().mapToDouble(x->x.getLate()*x.getJob().getOrder().getLatenessWeight()).sum());
        List<JobAssignment> earlyJobs = base.getListJobAssignment().stream().
                filter(x->x.getSolution()==solution).
                filter(x->x.getEarly() > 0).
                toList();
        solution.setNrEarly(earlyJobs.size());
        solution.setPercentEarly(100.0*earlyJobs.size()/base.getListJob().size());
        solution.setTotalEarliness(earlyJobs.stream().mapToInt(JobAssignment::getEarly).sum());
        solution.setMaxEarliness(earlyJobs.stream().mapToInt(JobAssignment::getEarly).max().orElse(0));
        solution.setWeightedEarliness(earlyJobs.stream().mapToDouble(x->x.getEarly()*x.getJob().getOrder().getEarlinessWeight()).sum());

    }

    private int limit(CumulativeResource m){
        return base.getListCumulativeProfile().stream().mapToInt(CumulativeProfile::getCapacity).max().orElse(0);
    }

    private ProcessSequence findProcessSequence(ProcessStep before,ProcessStep after){
        return base.getListProcessSequence().stream().
                filter(x->x.getBefore()==before && x.getAfter()==after).
                findAny().orElse(null);
    }

}

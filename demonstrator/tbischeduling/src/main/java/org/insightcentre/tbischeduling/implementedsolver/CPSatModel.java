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
        int n = base.getListTask().size();
        int horizon = base.getHorizon();
        Loader.loadNativeLibraries();
        // Creates the model.
        CpModel model = new CpModel();

        IntVar[] start = new IntVar[n];
        IntVar[] end = new IntVar[n];
        IntervalVar[] tasks = new IntervalVar[n];

        int i=0;
        Hashtable<Task,Integer> taskHash = new Hashtable<>();
        for(Task t:base.getListTask()){
            taskHash.put(t,i);
//            info("task "+i+" dur "+t.getDuration());
            start[i] = model.newIntVar(0, horizon, "start" + t.getName());
            end[i] = model.newIntVar(0, horizon, "end" + t.getName());
            tasks[i] = model.newIntervalVar(start[i], LinearExpr.constant(t.getDuration()), end[i], "task" + t.getName());
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
        // disjunctive Resources
        for(DisjunctiveResource m:base.getListDisjunctiveResource()){
            List<IntervalVar> list = base.getListTask().stream().filter(x->x.getMachines().contains(m)).map(x->tasks[taskHash.get(x)]).toList();
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
        for(CumulativeResource r:base.getListCumulativeResource()){
            int limit = limit(r);
            CumulativeConstraint cumul = model.addCumulative(limit);

            Hashtable<ProcessStep,Integer> demandHash = new Hashtable<>();
            int needs=0;
            for(CumulativeNeed cn:base.getListCumulativeNeed().stream().
                    filter(x->x.getCumulativeResource()==r).
                    toList()) {
                demandHash.put(cn.getProcessStep(),cn.getDemand());
                needs++;
            }
            int cnt = 0;
            for(Task t:base.getListTask().stream().toList()){
                Integer demand = demandHash.get(t.getProcessStep());
                if (demand != null){
                    cumul.addDemand(tasks[taskHash.get(t)],demand);
                    cnt++;
                }
            }
            info("Cumulative "+r+" limit "+limit+" needs "+needs+" cnt "+cnt);

        }

        // Makespan objective
        IntVar objVar = model.newIntVar(0, base.getHorizon(), "makespan");
        List<IntVar> ends = new ArrayList<>();
        for (Task t:base.getListTask()) {
            ends.add(end[taskHash.get(t)]);
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
            Hashtable<Job, JobAssignment> jobHash = new Hashtable<>();
            int taNr=0;
            for(Task t:base.getListTask()){
                int startValue = (int) Math.round(solver.value(start[taskHash.get(t)]));
                int endValue = (int) Math.round(solver.value(end[taskHash.get(t)]));
//                info("Start "+t+" "+startValue+" "+endValue);
                TaskAssignment ta = new TaskAssignment(base);
                ta.setName("TA"+taNr++);
                ta.setTask(t);
                ta.setStart(startValue);
                ta.setEnd(endValue);
                ta.setDuration(t.getDuration());
                ta.setJobAssignment(findJobAssignment(t.getJob(), solution, jobHash));
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
        }
        List<JobAssignment> jList =base.getListJobAssignment().stream().filter(x->x.getSolution()==solution).toList();
        solution.setStart(jList.stream().mapToInt(JobAssignment::getStart).min().orElse(0));
        solution.setEnd(jList.stream().mapToInt(JobAssignment::getEnd).max().orElse(0));
        solution.setMakespan(jList.stream().mapToInt(JobAssignment::getEnd).max().orElse(0));
        solution.setFlowtime(jList.stream().mapToInt(JobAssignment::getEnd).sum());
        solution.setDuration(solution.getEnd()-solution.getStart());

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

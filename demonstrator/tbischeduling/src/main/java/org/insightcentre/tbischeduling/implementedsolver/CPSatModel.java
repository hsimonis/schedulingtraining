package org.insightcentre.tbischeduling.implementedsolver;

import com.google.ortools.Loader;
import com.google.ortools.sat.*;
import org.insightcentre.tbischeduling.datamodel.*;

import java.util.*;

import static com.google.ortools.sat.CpSolverStatus.FEASIBLE;
import static com.google.ortools.sat.CpSolverStatus.OPTIMAL;
import static java.util.stream.Collectors.groupingBy;
import static org.insightcentre.tbischeduling.datamodel.SequenceType.Blocking;
import static org.insightcentre.tbischeduling.datamodel.SequenceType.NoWait;
import static org.insightcentre.tbischeduling.datamodel.SolverStatus.*;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;
import static org.insightcentre.tbischeduling.utilities.TypeConverters.toDateTime;

public class CPSatModel extends AbstractModel{
    int jaNr=0;
    public CPSatModel(Scenario base, SolverRun run){
        super(base,run);
    }

    static class VarArraySolutionPrinterWithObjective extends CpSolverSolutionCallback {
        Scenario base;
        SolverRun run;
        private int solutionCount;
        private final IntVar[] variableArray;
        public VarArraySolutionPrinterWithObjective(Scenario base,SolverRun run,IntVar[] variables) {
            this.base = base;
            this.run= run;
            variableArray = variables;
        }

        @Override
        public void onSolutionCallback() {
            IntermediateSolution sol = new IntermediateSolution(base);
            sol.setName("IntSol"+solutionCount);
            sol.setNr(solutionCount++);
            sol.setSolverRun(run);
            sol.setCost(objectiveValue());
            sol.setBound(bestObjectiveBound());
            sol.setGapPercent(100.0 * (sol.getCost()-bestObjectiveBound())/sol.getCost());
            sol.setTime(wallTime());
            info("Solution "+sol.getCost()+" bound "+sol.getBound()+" gap "+sol.getGapPercent()+" time "+sol.getTime());

        }

        public int getSolutionCount() {
            return solutionCount;
        }

    }
    public boolean solve(){
        info("Starting solver");
        long startTime = System.currentTimeMillis();
        base.resetListIntermediateSolution();
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

        // start and end of each task, are lined to the tasks Interval variables
        IntVar[] start = new IntVar[nrTasks];
        IntVar[] end = new IntVar[nrTasks];
        // the main variables for each task, used for temporal relations and cumulative constraints
        // all of these tasks are required
        IntervalVar[] tasks = new IntervalVar[nrTasks];
        // create matrix of potential optional tasks for each possible machine
        // copy x variable directly into z matrix if there is only one possible machine
        // check that z_{ij} is not null before every use !!
        IntervalVar[][] z = new IntervalVar[nrTasks][nrDisjunctiveResources];
        // separate boolean presence variables to see which alternative machine was taken
        //??? we probably can extract that from the z variables as well
        BoolVar[][] p = new BoolVar[nrTasks][nrDisjunctiveResources];
        // start and end variables for the jobs, used in release and due date constraints, and in the objective functions
        IntVar[] jStart = new IntVar[nrJobs];
        IntVar[] jEnd = new IntVar[nrJobs];


        // create the variables for the tasks, start, end, tasks, z and p
        i=0;
        Hashtable<Task,Integer> taskHash = new Hashtable<>();
        for(Task t:base.getListTask()){
            taskHash.put(t,i);
//            info("task "+i+" dur "+t.getDuration()+" "+horizon);
            start[i] = model.newIntVar(0, horizon, "start" + t.getName());
            end[i] = model.newIntVar(0, horizon, "end" + t.getName());
            tasks[i] = model.newIntervalVar(start[i], LinearExpr.constant(t.getDuration()), end[i], "task" + t.getName());
            if (t.getMachines().size() == 0) {
//                info("No machine "+t.getName());
            } else if (t.getMachines().size() == 1) {
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
        // create the variables for the jobs, jStart and jEnd
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
                ProcessSequence ps = findProcessSequence(t.getProcessStep(),after.getProcessStep());
//                info("seq "+t+" "+after+" "+ps.getSequenceType());
                switch (modifiedSequenceType(run,ps.getSequenceType())) {
                    case EndBeforeStart -> model.addGreaterOrEqual(start[taskHash.get(after)], end[taskHash.get(t)]);
                    case StartBeforeStart ->
                            model.addGreaterOrEqual(start[taskHash.get(after)], start[taskHash.get(t)]);
                    case NoWait -> model.addEquality(start[taskHash.get(after)], end[taskHash.get(t)]);
                    case Blocking -> model.addEquality(start[taskHash.get(after)], end[taskHash.get(t)]);
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
                info("enforce due date "+j.getName()+" due "+j.getOrder().getDue());
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
            if (run.getEnforceWip()) {
                for (WiP wip : base.getListWiP().stream().filter(x -> x.getDisjunctiveResource() == m).toList()) {
                    IntervalVar wTask = model.newFixedSizeIntervalVar(model.newConstant(0), wip.getDuration(), wip.getName());
                    list.add(wTask);
                    info("Machine " + m.getName() + " add wip " + wip.getEnd());
                }
            }
            if (run.getEnforceDowntime()) {
                for (Downtime down : base.getListDowntime().stream().filter(x -> x.getDisjunctiveResource() == m).toList()) {
                    IntervalVar dTask = model.newFixedSizeIntervalVar(model.newConstant(down.getStart()), down.getDuration(), down.getName());
                    list.add(dTask);
//                info("Machine "+m.getName()+" add down "+down.getStart()+"-"+down.getEnd());
                }
            }
            info("Machine "+m+" tasks, wip, downtime "+list.size());
            // set noOverlap constraint
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
                // create the cumulative constraint with the limit, add demands later on
                CumulativeConstraint cumul = model.addCumulative(limit);

                // create a hashtable from the process steps to the demands of the step for that cumulative resource
                Hashtable<ProcessStep, Integer> demandHash = new Hashtable<>();
                int needs = 0;
                for (CumulativeNeed cn : base.getListCumulativeNeed().stream().
                        filter(x -> x.getCumulativeResource() == r).
                        toList()) {
                    demandHash.put(cn.getProcessStep(), cn.getDemand());
                    needs++;
                }
                // create a demand for every task that has a demand on that machine
                int cnt = 0;
                for (Task t : base.getListTask().stream().toList()) {
                    Integer demand = demandHash.get(t.getProcessStep());
                    if (demand != null) {
                        cumul.addDemand(tasks[taskHash.get(t)], demand);
                        cnt++;
                    }
                }
                // add dummy demands to create the correct profile
                int dummies=createDummies(model,cumul,r,limit);

                info("Cumulative " + r + " limit " + limit + " needs " + needs + " cnt " + cnt+" dummyDemands "+dummies);

            }
        }

        /*
        setup the objective function
        only add lateness and earliness variables if we need them
         */
        switch (run.getObjectiveType()) {
            case Makespan -> {
                IntVar objVar = model.newIntVar(0, base.getHorizon(), "objective");
                model.addMaxEquality(objVar, jEnd);
                model.minimize(objVar);
            }
            case Flowtime -> {
                model.minimize(LinearExpr.sum(jEnd));
            }
            case TotalLateness -> {
                LinearExprBuilder sum = LinearExpr.newBuilder();
                for (Job j : base.getListJob()) {
                    IntVar latej = latenessVar(model,jEnd[jobHash.get(j)],j.getOrder().getDue(),j.getName());
                    sum.addTerm(latej, 1);
                }
                model.minimize(sum.build());
            }
            case WeightedLateness -> {
                LinearExprBuilder sum = LinearExpr.newBuilder();
                for (Job j : base.getListJob()) {
                    IntVar latej = latenessVar(model,jEnd[jobHash.get(j)],j.getOrder().getDue(),j.getName());
                    //??? using only integer part of weight
                    sum.addTerm(latej, (long) Math.round(j.getOrder().getLatenessWeight()));
                }
                model.minimize(sum.build());
            }
            case MaxLateness -> {
                IntVar objVar = model.newIntVar(0, base.getHorizon(), "objective");
                List<IntVar> lateList = new ArrayList<>();
                for (Job j : base.getListJob()) {
                    IntVar latej = latenessVar(model,jEnd[jobHash.get(j)],j.getOrder().getDue(),j.getName());
                    lateList.add(latej);
                }
                model.addMaxEquality(objVar, lateList);
                model.minimize(objVar);
            }
            case TotalEarliness -> {
                LinearExprBuilder sum = LinearExpr.newBuilder();
                for (Job j : base.getListJob()) {
                    IntVar earlyj = earlinessVar(model,jEnd[jobHash.get(j)],j.getOrder().getDue(),j.getName());
                    sum.addTerm(earlyj, 1);
                }
                model.minimize(sum.build());
            }
            case WeightedEarliness -> {
                LinearExprBuilder sum = LinearExpr.newBuilder();
                for (Job j : base.getListJob()) {
                    IntVar earlyj = earlinessVar(model,jEnd[jobHash.get(j)],j.getOrder().getDue(),j.getName());
                    //??? using only integer part of weight
                    sum.addTerm(earlyj, (long) Math.round(j.getOrder().getEarlinessWeight()));
                }
                model.minimize(sum.build());
            }
            case MaxEarliness -> {
                IntVar objVar = model.newIntVar(0, base.getHorizon(), "objective");
                List<IntVar> earlyList = new ArrayList<>();
                for (Job j : base.getListJob()) {
                    IntVar earlyj = earlinessVar(model,jEnd[jobHash.get(j)],j.getOrder().getDue(),j.getName());
                    earlyList.add(earlyj);
                }
                model.addMaxEquality(objVar, earlyList);
                model.minimize(objVar);
            }
            case OnTime -> {
                LinearExprBuilder sum = LinearExpr.newBuilder();
                for (Job j : base.getListJob()) {
                    IntVar earlyj = earlinessVar(model,jEnd[jobHash.get(j)],j.getOrder().getDue(),j.getName());
                    sum.addTerm(earlyj, run.getWeightEarliness());
                    IntVar latej = latenessVar(model,jEnd[jobHash.get(j)],j.getOrder().getDue(),j.getName());
                    sum.addTerm(latej, run.getWeightLateness());
                }
                model.minimize(sum.build());
            }
            case Hybrid -> {
                // need separate variable for makespan
                IntVar makespan = model.newIntVar(0, base.getHorizon(), "makespan");
                // this will be the objective
                LinearExprBuilder sum = LinearExpr.newBuilder();
                for (Job j : base.getListJob()) {
                    // add weighted lateness, earliness and flowtime for each job to objective
                    IntVar earlyj = earlinessVar(model,jEnd[jobHash.get(j)],j.getOrder().getDue(),j.getName());
                    sum.addTerm(earlyj, run.getWeightEarliness());
                    IntVar latej = latenessVar(model,jEnd[jobHash.get(j)],j.getOrder().getDue(),j.getName());
                    sum.addTerm(latej, run.getWeightLateness());
                    sum.addTerm(jEnd[jobHash.get(j)], run.getWeightFlowtime());
                }
                // add weighted makespan to objective
                model.addMaxEquality(makespan, jEnd);
                sum.addTerm(makespan,run.getWeightMakespan());
                model.minimize(sum.build());
            }
            default -> {
                severe("Objective " + run.getObjectiveType() + " not yet implemented in CPSat");
                assert (false);
            }
        }

        // Creates a solver and solves the model.
        CpSolver solver = new CpSolver();
        VarArraySolutionPrinterWithObjective cb =
                new VarArraySolutionPrinterWithObjective(base,run,null);
        solver.getParameters().setMaxTimeInSeconds(run.getTimeout());
        solver.getParameters().setNumWorkers(run.getNrThreads());
        solver.getParameters().setRandomSeed(run.getSeed());
        info("CPSat solving for "+run.getTimeout()+" seconds with "+run.getNrThreads()+" workers...");

        CpSolverStatus status = solver.solve(model,cb);
        // update SolverRun status and time regardless of result
        run.setSolverStatus(toSolverStatus(status));
        run.setTime(solver.wallTime());
        if (status == OPTIMAL || status == FEASIBLE) {
            // extract solution
            int cost = (int) Math.round(solver.objectiveValue());
            double bound = solver.bestObjectiveBound();
            double gapPercent = 100.0*(cost-bound)/cost;
            info("Solved cost "+cost+" Bound "+bound+" gapPercent "+gapPercent+" in "+solver.wallTime()+" seconds");
            Solution solution = new Solution(base);
            solution.setName("Sol"+solNr++);
            solution.setObjectiveValue(cost);
            solution.setBound(bound);
            solution.setGapPercent(gapPercent);
            solution.setSolverRun(run);
            solution.setSolverStatus(toSolverStatus(status));
            Hashtable<Job, JobAssignment> jobAssignmentHash = new Hashtable<>();
            Hashtable<Task,TaskAssignment> assignHash = new Hashtable<>();
            int taNr=0;
            for(Task t:base.getListTask()){
                int ii = taskHash.get(t);
                int startValue = (int) Math.round(solver.value(start[ii]));
                int endValue = (int) Math.round(solver.value(end[ii]));
//                info("Start "+t+" "+startValue+" "+endValue);
                TaskAssignment ta = new TaskAssignment(base);
                ta.setName("TA"+taNr++);
                ta.setTask(t);
                assignHash.put(t,ta);
                ta.setStart(startValue);
                ta.setEnd(endValue);
                ta.setStartDate(toDateTime(base,startValue));
                ta.setEndDate(toDateTime(base,endValue));
                ta.setDuration(t.getDuration());
                ta.setJobAssignment(findJobAssignment(t.getJob(), solution, jobAssignmentHash));
                if (t.getMachines().size()==0) {
                    ta.setDisjunctiveResource(null);
                } else if (t.getMachines().size()==1){
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
            List<JobAssignment> jaList =base.getListJobAssignment().stream().filter(x->x.getSolution()==solution).toList();
            List<TaskAssignment> taList = base.getListTaskAssignment().stream().
                    filter(x->x.getJobAssignment().getSolution()==solution).
                    toList();

            calculateWaitBeforeAfter(assignHash);
            calculateSetupAndIdleTimes(taList);
            updateJA(solution);
            updateSolution(solution,jaList,taList);
            return true;
        } else {
            info("No solution, status "+status);
            return false;
        }

    }

    private SequenceType modifiedSequenceType(SolverRun run,SequenceType orig){
        if (run.getAddNoWait()) {
            return NoWait;
        } else if (run.getAddBlocking()){
            return Blocking;
        }
        return orig;
    }
    private IntVar latenessVar(CpModel model,IntVar end,int due,String name){
        IntVar res = model.newIntVar(0, base.getHorizon(), "late" + name);
        model.addMaxEquality(res, new LinearExpr[]{
                LinearExpr.newBuilder().
                        addTerm(end, 1).
                        add(-due).
                        build(),
                LinearExpr.constant(0)});
        return res;

    }
    private IntVar earlinessVar(CpModel model,IntVar end,int due,String name){
        IntVar res = model.newIntVar(0, base.getHorizon(), "early" + name);
        model.addMaxEquality(res, new LinearExpr[]{
                LinearExpr.newBuilder().
                        addTerm(end, -1).
                        add(due).
                        build(),
                LinearExpr.constant(0)});
        return res;

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
            ja.setStartDate(toDateTime(base,ja.getStart()));
            ja.setEndDate(toDateTime(base,ja.getEnd()));
            ja.setDuration(ja.getEnd()-ja.getStart());
            ja.setEarly(Math.max(0,ja.getJob().getOrder().getDue()-ja.getEnd()));
            ja.setLate(Math.max(0,ja.getEnd()-ja.getJob().getOrder().getDue()));
        }
    }

    private int limit(CumulativeResource m){
        return base.getListCumulativeProfile().stream().mapToInt(CumulativeProfile::getCapacity).max().orElse(0);
    }

    private int createDummies(CpModel model,CumulativeConstraint cumul,CumulativeResource r,int limit){
        int res = 0;
        List<CumulativeProfile> profiles = base.getListCumulativeProfile().stream().
                filter(x->x.getCumulativeResource()==r).
                sorted(Comparator.comparing(CumulativeProfile::getFrom)).
                toList();
        Integer prevStart = 0;
        Integer prevCapacity = 0;
        for(CumulativeProfile cp:profiles){
            if (prevCapacity<limit && prevStart<cp.getFrom()){
                //create dummy demand from prevStart to cp.getFrom with demand limit-prevCapacity
                info("Dummy "+r.getName()+" "+res+" from "+prevStart+" to "+cp.getFrom()+" demand "+(limit-prevCapacity));
                IntervalVar dTask = model.newFixedSizeIntervalVar(model.newConstant(prevStart),cp.getFrom()-prevStart,"Dummy"+r.getName()+"_"+res);
                cumul.addDemand(dTask,limit-prevCapacity);
                res++;

            }
            prevStart= cp.getFrom();
            prevCapacity = cp.getCapacity();
        }
        if (prevCapacity < limit && prevStart < base.getHorizon()){
            // create dummy demand from prevStart to horizon with demand limit-prevCapacity
            info("Dummy "+r.getName()+" "+res+" from "+prevStart+" to "+base.getHorizon()+" demand "+(limit-prevCapacity));
            IntervalVar dTask = model.newFixedSizeIntervalVar(model.newConstant(prevStart),base.getHorizon()-prevStart,"Dummy"+r.getName()+"_"+res);
            cumul.addDemand(dTask,limit-prevCapacity);
            res++;
        }
        return res;

    }

    private ProcessSequence findProcessSequence(ProcessStep before,ProcessStep after){
        return base.getListProcessSequence().stream().
                filter(x->x.getBefore()==before && x.getAfter()==after).
                findAny().orElse(null);
    }

}

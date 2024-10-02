package org.insightcentre.tbischeduling.implementedsolver;

import ilog.concert.*;
import ilog.cp.IloCP;
import org.insightcentre.tbischeduling.datamodel.*;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static org.insightcentre.tbischeduling.datamodel.SolverStatus.*;
import static org.insightcentre.tbischeduling.logging.LogShortcut.*;

public class CPOModel extends AbstractModel{
    static int solNr = 1;
    public CPOModel(Scenario base, SolverRun run){
        super(base,run);
    }
    static class IntervalVarList extends ArrayList<IloIntervalVar> {
        public IloIntervalVar[] toArray() {
            return this.toArray(new IloIntervalVar[0]);
        }
    }
    static class IntVarList extends ArrayList<IloIntVar> {
        public IloIntVar[] toArray() {
            return this.toArray(new IloIntVar[0]);
        }
    }
    static class IntExprList extends ArrayList<IloIntExpr> {
        public IloIntExpr[] toArray() {
            return this.toArray(new IloIntExpr[0]);
        }
    }

    public boolean solve(){
        long startTime = System.currentTimeMillis();
        base.resetListIntermediateSolution();

        int horizon = base.getHorizon();
        // tasks
        int nrTasks = base.getListTask().size();
        Task[] tasks = new Task[nrTasks];
        int[] durations = new int[nrTasks];
        int ii=0;
        Hashtable<Task,Integer> taskHash = new Hashtable<>();
        for(Task t:base.getListTask()){
            durations[ii] = t.getDuration();
            tasks[ii] = t;
            taskHash.put(t,ii);
            ii++;
        }
        // jobs
        int nrJobs = base.getListJob().size();
        Job[] jobs = new Job[nrJobs];
        int[] dueDate = new int[nrJobs];
        ii = 0;
        Hashtable<Job,Integer> jobHash = new Hashtable<>();
        for(Job j:base.getListJob()){
            dueDate[ii] = j.getOrder().getDue();
            jobs[ii] = j;
            jobHash.put(j,ii);
            ii++;
        }
        // disjunctive reosurces
        int nrDisjunctiveResources = base.getListDisjunctiveResource().size();
        DisjunctiveResource[] disjRes = new DisjunctiveResource[nrDisjunctiveResources];
        ii =0;
        Hashtable<DisjunctiveResource,Integer> disjHash = new Hashtable<>();
        for(DisjunctiveResource r:base.getListDisjunctiveResource()){
            disjRes[ii] = r;
            disjHash.put(r,ii);
            ii++;
        }

        try {
            IloCP cp = new IloCP();
            /*
            create CPO objects
             */

            // non-optional task vars for each Task
            IloIntervalVar[] x = new IloIntervalVar[nrTasks];
            // non-optional task vars for ech Job
            IloIntervalVar[] y = new IloIntervalVar[nrJobs];
            // lateness variables for the jobs
            IloIntExpr[] lateness = new IloIntExpr[nrJobs];
            IloIntExpr[] earliness = new IloIntExpr[nrJobs];
            // variables for machine use; most fields are null
            // some are set to x variables if there is no machine choice
            IloIntervalVar[][] z = new IloIntervalVar[nrTasks][nrDisjunctiveResources];

            // create task related variables
            for(int i=0;i<nrTasks;i++){
                // assumes machine independent duration
                x[i] = cp.intervalVar(durations[i],tasks[i].getName());
                x[i].setPresent();
                x[i].setStartMin(0);
                x[i].setEndMax(horizon);
            }
            // create job related variables
            for(int j=0;j<nrJobs;j++){
                y[j] = cp.intervalVar(jobs[j].getName());
                y[j].setPresent();
                // enforce release date
                if (run.getEnforceReleaseDate()) {
                    y[j].setStartMin(jobs[j].getOrder().getRelease());
                } else {
                    y[j].setStartMin(0);
                }
                // enforce due date
                if (run.getEnforceDueDate()) {
                    y[j].setEndMax(jobs[j].getOrder().getDue());
                } else {
                    y[j].setEndMax(horizon);
                }
                // constrain lateness and earliness to depend on end date
                lateness[j] = cp.max(0,cp.diff(cp.endOf(y[j]),dueDate[j]));
                earliness[j] = cp.max(0,cp.diff(dueDate[j],cp.endOf(y[j])));
            }
            // create machine task variables if there is more than one choice
            for(int i=0;i<nrTasks;i++){
                List<DisjunctiveResource> list = tasks[i].getMachines();
                if (list.size() == 1){
                    // no machine choice, use x variable on fixed machine
                    int k = disjHash.get(list.get(0));
                    z[i][k] = x[i];
                } else if (list.size() > 1){
                    // create new optional tasks for each possible machine
                    for(DisjunctiveResource r:list){
                        int k = disjHash.get(r);
                        // the duration is not machine dependent
                        z[i][k] = cp.intervalVar(durations[i],"Z"+i+","+k);
                        z[i][k].setOptional();
                    }
                } else {
                    severe("No possible machine for task "+i+" "+tasks[i].getName());
                    assert(false);
                }
            }

            // map jobs to list of their tasks
            Map<Job, List<Task>> mapTask = base.getListTask().stream().collect(groupingBy(Task::getJob));
            // span constraints linking jobs and tasks
            for(Job job:mapTask.keySet()){
                // the job task spans all of its tasks
                IntervalVarList spans = new IntervalVarList();
                for(Task t:mapTask.get(job)){
                    spans.add(x[taskHash.get(t)]);
                }
                cp.add(cp.span(y[jobHash.get(job)],spans.toArray()));
            }
            // create temporal constraints between tasks; only precedences at this point
            for(Task before:tasks){
                int beforeIndex = taskHash.get(before);
                for(Task after:before.getPrecedes()){
                    int afterIndex=taskHash.get(after);
                    cp.add(cp.endBeforeStart(x[beforeIndex], x[afterIndex]));
                }
            }
            // this is more complex code to compute the precedences from the processSequence data
//            for(Job job:mapTask.keySet()){
//                Hashtable<ProcessStep,Task> processStepHash = new Hashtable<>();
//                for(Task t:mapTask.get(job)){
//                    processStepHash.put(t.getProcessStep(),t);
//                }
//                for(ProcessSequence seq:base.getListProcessSequence().stream().filter(xx->xx.getBefore().getProcess()==job.getProcess()).toList()){
//                    int beforeIndex = taskHash.get(processStepHash.get(seq.getBefore()));
//                    int afterIndex = taskHash.get(processStepHash.get(seq.getAfter()));
//                    if (seq.getSequenceType()==SequenceType.EndBeforeStart) {
//                        cp.add(cp.endBeforeStart(x[beforeIndex], x[afterIndex]));
//                    } else {
//                        severe("Bad precedence type "+seq.getSequenceType()+" ignored!");
//                    }
//                }
//            }
            // create disjunctive constraints
            for(int k=0;k<nrDisjunctiveResources;k++){
                DisjunctiveResource r = disjRes[k];
                IntervalVarList disjunctive = new IntervalVarList();
                for(int i=0;i<nrTasks;i++){
                    // most z entries are null as machine is not allowed
                    if (z[i][k] != null){
                        disjunctive.add(z[i][k]);
                    }
                }
                if (run.getEnforceWip()){
                    WiP current = wipForResource(r);
                    if (current != null){
                        IloIntervalVar wip = cp.intervalVar(current.getDuration(),"wip");
                        wip.setPresent();
                        wip.setStartMin(current.getStart());
                        wip.setStartMax(current.getStart());
                        disjunctive.add(wip);
                        info("wip "+r.getName()+" "+current.getEnd());
                    }
                }
                if (run.getEnforceDowntime()){
                    List<Downtime> list = base.getListDowntime().stream().
                            filter(xx->xx.getDisjunctiveResource()==r).
                            sorted(Comparator.comparing(Downtime::getStart)).
                            toList();
                    Integer prevEnd = null;
                    for(Downtime d:list){
                        if (prevEnd != null && prevEnd > d.getStart()){
                            severe("Downtimes for resource "+r.getName()+" overlap "+prevEnd+" "+d.getStart());
                            assert(false);
                        } else {
                            int duration = d.getDuration();
                            IloIntervalVar dd = cp.intervalVar(duration, d.getName());
                            dd.setPresent();
                            dd.setStartMin(d.getStart());
                            dd.setStartMax(d.getStart());
                            disjunctive.add(dd);
                            info(d.getName() + " " + r.getName() + " " + d.getStart() + " " + d.getEnd());
                            prevEnd = d.getEnd();
                        }
                    }
                }
                cp.add(cp.noOverlap(disjunctive.toArray()));
            }
            // create alternative constraints
            for(int i=0;i<nrTasks;i++){
                // only create alternative constraint if there is more than one choice
                if(tasks[i].getMachines().size() > 1){
                    IntervalVarList alternatives = new IntervalVarList();
                    for(int k=0;k<nrDisjunctiveResources;k++){
                        if (z[i][k] != null) {
                            alternatives.add(z[i][k]);
                        }
                    }
                    assert(alternatives.size()==tasks[i].getMachines().size());
                    cp.add(cp.alternative(x[i],alternatives.toArray()));
                }
            }
            // cumulative constraints
            if (run.getEnforceCumulative()){
                for (CumulativeResource r:base.getListCumulativeResource()){
                    Hashtable<ProcessStep,Integer> demandHash = collectDemand(r);
                    IloCumulFunctionExpr expr = cp.cumulFunctionExpr();
                    for(int i=0;i<nrTasks;i++){
                        int demand = tasksNeedsResource(tasks[i],demandHash);
                        if (demand > 0){
                            expr = cp.sum(expr,cp.pulse(x[i],demand));
                        }
                    }
                    List<CumulativeProfile> profiles = base.getListCumulativeProfile().stream().
                            filter(xx->xx.getCumulativeResource()==r).
                            sorted(Comparator.comparing(CumulativeProfile::getFrom)).toList();
                    if (profiles.size() > 0) {
                        CumulativeProfile prev = null;
                        for (CumulativeProfile profile : profiles) {
                            if (prev != null) {
                                info("Always in "+r.getName()+" from "+prev.getFrom()+" "+profile.getFrom()+" cap "+prev.getCapacity());
                                cp.add(cp.alwaysIn(expr, prev.getFrom(), profile.getFrom(), 0, prev.getCapacity()));
                            }
                            prev = profile;
                        }
                        info("Always In "+r.getName()+" from "+prev.getFrom()+" "+base.getHorizon()+" cap "+prev.getCapacity());
                        cp.add(cp.alwaysIn(expr, prev.getFrom(), base.getHorizon(), 0, prev.getCapacity()));
                    }
                }
            }

            /*
            set objective
             */
            IloObjective objective=null;
            switch (run.getObjectiveType()) {
                case Makespan -> {
                    // define makespan
                    IloIntExpr makespan = cp.intVar(0,base.getHorizon());
                    IntExprList ends = new IntExprList();
                    for(int j=0;j<nrJobs;j++){
                        ends.add(cp.endOf(y[j]));
                    }
                    cp.addEq(makespan,cp.max(ends.toArray()));
                    objective = cp.minimize(makespan);
                }
                case Flowtime -> {
                    // define flowtime as sum of completion times
                    IloIntExpr flowtime = cp.intVar(0,base.getHorizon()*nrJobs);
                    IntExprList ends = new IntExprList();
                    for(int j=0;j<nrJobs;j++){
                        ends.add(cp.endOf(y[j]));
                    }
                    cp.addEq(flowtime,cp.sum(ends.toArray()));
                    objective = cp.minimize(flowtime);
                }
                case TotalLateness -> objective = cp.minimize(cp.sum(lateness));
                case TotalEarliness -> objective = cp.minimize(cp.sum(earliness));
                case MaxLateness -> objective = cp.minimize(cp.max(lateness));
                case MaxEarliness -> objective = cp.minimize(cp.max(earliness));
                default -> {
                    severe("ObjectiveType not implemented " + run.getObjectiveType());
                    assert (false);
                }
            }
            cp.add(objective);
//            cp.setParameter(IloCP.IntParam.FailLimit,1000000);
            cp.setParameter(IloCP.IntParam.Workers, run.getNrThreads());
            cp.setParameter(IloCP.IntParam.RandomSeed, run.getSeed());
            cp.setParameter(IloCP.DoubleParam.TimeLimit,run.getTimeout());
            cp.setParameter(IloCP.IntParam.LogVerbosity, IloCP.ParameterValues.Terse);
            if(CPOCallbacks.solveWithCallback(base,cp,run)){
//            if (cp.solve()){
                // extract solution
                int obj = (int) Math.round(cp.getObjValue());
                String status = cp.getStatusString();
                double bound = cp.getObjBound();
                double gap = cp.getObjGap();
                info("Solved Obj "+obj+" status "+status+" bound "+bound+" gap "+gap);
                Solution sol = new Solution(base);
                sol.setName("Sol"+solNr++);
                sol.setObjectiveValue(obj);
                sol.setSolverStatus(toSolverStatus(status));
                run.setSolverStatus(toSolverStatus(status));
                run.setTime(time(startTime));
                sol.setSolverRun(run);
                sol.setBound(bound);
                sol.setGap(gap);

                // extract job assignment
                Hashtable<Job,JobAssignment> jaHash = new Hashtable<>();
                List<JobAssignment> jaList = new ArrayList<>();
                for(int j=0;j<nrJobs;j++){
                    int start = cp.getStart(y[j]);
                    int end = cp.getEnd(y[j]);
                    int duration = end-start;
                    int late = (int) Math.round(cp.getValue(lateness[j]));
                    int due = jobs[j].getOrder().getDue();
                    int early = Math.max(0,due-end);
                    JobAssignment ja = new JobAssignment(base);
                    ja.setName("JA"+j);
                    ja.setSolution(sol);
                    ja.setJob(jobs[j]);
                    ja.setStart(start);
                    ja.setEnd(end);
                    ja.setDuration(duration);
                    ja.setLate(late);
                    ja.setEarly(early);
                    jaHash.put(jobs[j],ja);
                    jaList.add(ja);
                }
                //extract task assignment
                for(int i=0;i<nrTasks;i++){
                    int start = cp.getStart(x[i]);
                    int end = cp.getEnd(x[i]);
                    int duration = end-start;
                    TaskAssignment ta = new TaskAssignment(base);
                    ta.setName("TA"+i);
                    ta.setJobAssignment(jaHash.get(tasks[i].getJob()));
                    ta.setTask(tasks[i]);
                    ta.setStart(start);
                    ta.setEnd(end);
                    ta.setDuration(duration);
                    int cnt =0;
                    for(int k=0;k<nrDisjunctiveResources;k++){
                        if (z[i][k] != null && cp.isPresent(z[i][k])){
                            ta.setDisjunctiveResource(disjRes[k]);
                            cnt++;
                        }
                    }
                    assert(cnt==1);
                }
                sol.setMakespan(jaList.stream().mapToInt(JobAssignment::getEnd).max().orElse(0));
                sol.setFlowtime(jaList.stream().mapToInt(JobAssignment::getEnd).sum());
                sol.setTotalLateness(jaList.stream().mapToInt(JobAssignment::getLate).sum());
                sol.setMaxLateness(jaList.stream().mapToInt(JobAssignment::getLate).max().orElse(0));
                sol.setNrLate((int)jaList.stream().filter(xx->xx.getLate() >0).count());
                sol.setWeightedLateness(jaList.stream().mapToDouble(this::weightedLateness).sum());
                sol.setTotalEarliness(jaList.stream().mapToInt(JobAssignment::getEarly).sum());
                sol.setMaxEarliness(jaList.stream().mapToInt(JobAssignment::getEarly).max().orElse(0));
                sol.setNrEarly((int)jaList.stream().filter(xx->xx.getEarly()>0).count());
                sol.setWeightedEarliness(jaList.stream().mapToDouble(this::weightedEarliness).sum());
                sol.setPercentEarly(100.0*sol.getNrEarly()/nrJobs);
                sol.setPercentLate(100.0*sol.getNrLate()/nrJobs);
                // to capture previously unseen solver status strings
                assert(run.getSolverStatus() != null);
                return true;
            } else {
                String status = cp.getStatusString();
                warning("No solution "+status);
                run.setSolverStatus(toSolverStatus(status));
                run.setTime(time(startTime));
                // to capture previously unseen solver status strings
                assert(run.getSolverStatus() != null);
                return false;
            }

        } catch (IloException e) {
            severe("CPO Error " + e+" "+e.getMessage()+" "+e.getCause() );
            run.setSolverStatus(Error);
            run.setTime(time(startTime));
            return false;
        }
    }

    private WiP wipForResource(DisjunctiveResource r){
        for(WiP wip:base.getListWiP()){
            if (wip.getDisjunctiveResource()==r){
                return wip;
            }
        }
        return null;
    }

    private Hashtable<ProcessStep,Integer> collectDemand(CumulativeResource r){
        Hashtable<ProcessStep,Integer> res = new Hashtable<>();
        for(CumulativeNeed cn:base.getListCumulativeNeed()){
            if (cn.getCumulativeResource() == r){
                res.put(cn.getProcessStep(),cn.getDemand());
            }
        }
        return res;
    }

    private int tasksNeedsResource(Task t,Hashtable<ProcessStep,Integer> demandHash){
        Integer res = demandHash.get(t.getProcessStep());
        if (res == null){
            res = 0;
        }
        return res;
    }



    private double weightedLateness(JobAssignment ja){
        return ja.getLate()*ja.getJob().getOrder().getLatenessWeight();
    }
    private double weightedEarliness(JobAssignment ja){
        return ja.getEarly()*ja.getJob().getOrder().getLatenessWeight();
    }

    private int duration(Task t){
        return t.getDuration();
   }

    /*
    convert CPO status strings into SolverStatus enums; these are solver specific
     */
    private SolverStatus toSolverStatus(String name){
        return switch (name) {
            case "Optimal" -> Optimal;
            case "Feasible" -> SolverStatus.Solution;
            case "Solution" -> SolverStatus.Solution;
            case "Infeasible" -> Infeasible;
            case "Unknown" -> Unknown;
            default -> null;
        };
    }

    private double time(long startTime){
        return (System.currentTimeMillis()-startTime)/1000.0;
    }



}

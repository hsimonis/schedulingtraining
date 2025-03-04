package org.insightcentre.tbischeduling.implementedsolver;

import ilog.concert.*;
import ilog.cp.IloCP;
import org.insightcentre.tbischeduling.datamodel.*;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static org.insightcentre.tbischeduling.datamodel.SequenceType.Blocking;
import static org.insightcentre.tbischeduling.datamodel.SolverStatus.*;
import static org.insightcentre.tbischeduling.logging.LogShortcut.*;
import static org.insightcentre.tbischeduling.utilities.TypeConverters.toDateTime;

public class CPOModel extends AbstractModel{
    public CPOModel(Scenario base, SolverRun run){
        super(base,run);
        info("solver object created");
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
        info("Starting solver");
        long startTime = System.currentTimeMillis();
        base.resetListIntermediateSolution();
        info("Starting solver");

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
        // disjunctive resources
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
            IloIntExpr[] ontime = new IloIntExpr[2*nrJobs];
            // variables for machine use; most fields are null
            // some are set to x variables if there is no machine choice
            IloIntervalVar[][] z = new IloIntervalVar[nrTasks][nrDisjunctiveResources];

            // create task related variables
            for(int i=0;i<nrTasks;i++){
                // assumes machine independent duration
                if(canBeUsedAsBuffer(tasks[i])) {
                    x[i] = cp.intervalVar(durations[i], base.getHorizon());
                } else {
                    x[i] = cp.intervalVar(durations[i], tasks[i].getName());
                }
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
                ontime[2*j] = lateness[j];
                ontime[2*j+1] = earliness[j];
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
                        z[i][k] = cp.intervalVar(durationOnMachine(durations[i],tasks[i],r),"Z"+i+","+k);
                        z[i][k].setOptional();
                    }
                } else {
                    if (base.getHasDisjunctive()) {
                        severe("No possible disjunctive machine for task " + i + " " + tasks[i].getName());
                        assert(false);
                    }
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
                if (job.getNoOverlap() || run.getRelaxSequence()){
                    // create a noOverlap constraint for all tasks belonging to a job
                    // only do this if we need to
                    // normally the temporal constraints handle this
                    info("Added noOverlap for job "+job.getName());
                    cp.add(cp.noOverlap(spans.toArray()));
                }
            }
//            // create temporal constraints between tasks; only precedences at this point
//            if (!run.getRelaxSequence()) {
//                for (Task before : tasks) {
//                    int beforeIndex = taskHash.get(before);
//                    for (Task after : before.getPrecedes()) {
//                        int afterIndex = taskHash.get(after);
//                        cp.add(cp.endBeforeStart(x[beforeIndex], x[afterIndex]));
//                    }
//                }
//            }
            // this is more complex code to compute the precedences from the processSequence data
            for(Job job:mapTask.keySet()){
                Hashtable<ProcessStep,Task> processStepHash = new Hashtable<>();
                for(Task t:mapTask.get(job)){
                    processStepHash.put(t.getProcessStep(),t);
                }
                for(ProcessSequence seq:base.getListProcessSequence().stream().filter(xx->xx.getBefore().getProcess()==job.getProcess()).toList()){
                    int beforeIndex = taskHash.get(processStepHash.get(seq.getBefore()));
                    int afterIndex = taskHash.get(processStepHash.get(seq.getAfter()));
                    if (seq.getSequenceType()==SequenceType.EndBeforeStart) {
                        cp.add(cp.endBeforeStart(x[beforeIndex], x[afterIndex]));
                    } else if (seq.getSequenceType()==SequenceType.StartBeforeStart) {
                        cp.add(cp.startBeforeStart(x[beforeIndex], x[afterIndex]));
                    } else if (seq.getSequenceType()==SequenceType.MaxWait) {
                        // impose endBeforeStart
                        cp.add(cp.endBeforeStart(x[beforeIndex], x[afterIndex]));
                        //??? impose maxWait

                    } else if (seq.getSequenceType()== Blocking) {
                        cp.add(cp.endAtStart(x[beforeIndex], x[afterIndex]));
                    } else if (seq.getSequenceType()==SequenceType.NoWait) {
                        cp.add(cp.endAtStart(x[beforeIndex], x[afterIndex]));
                    } else {
                        severe("Bad precedence type "+seq.getSequenceType()+" ignored!");
                    }
                }
            }
            // remember the sequence of jobs on tasks if we add the sameOrder constraint for flowshop
            IloIntervalSequenceVar seq0=null;
            // create disjunctive constraints
            for(int k=0;k<nrDisjunctiveResources;k++){
                DisjunctiveResource r = disjRes[k];
                List<Task> tasksOnThisMachine =  new ArrayList<>();
                IntervalVarList disjunctive = new IntervalVarList();
                for(int i=0;i<nrTasks;i++){
                    // most z entries are null as machine is not allowed
                    if (z[i][k] != null){
                        disjunctive.add(z[i][k]);
                        tasksOnThisMachine.add(tasks[i]);
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
//                            info(d.getName() + " " + r.getName() + " " + d.getStart() + " " + d.getEnd());
                            prevEnd = d.getEnd();
                        }
                    }
                }
                if (run.getEnforceSetup() && r.getSetup() != null){
                    Setup setup = r.getSetup();
                    info("Creating setup "+r.getSetup()+" for machine "+r);
                    List<SetupType> types = base.getListSetupType().stream().filter(xx->xx.getSetup()==setup).toList();
                    // renumbering the types
                    int typeNr=0;
                    for(SetupType type:types){
                        type.setNr(typeNr++);
                    }

                    int n = disjunctive.size();
                    int nrTypes = types.size();
                    info("Nr Types "+nrTypes+" nr tasks "+n+" properTasks "+tasksOnThisMachine.size());
                    IloTransitionDistance setupMatrix = createSetupMatrix(cp,setup,types);
                    int[] typeValues = new int[n];
                    int i=0;
                    for(Task t:tasksOnThisMachine) {
                        SetupType st = t.getProcessStep().getSetupType();
                        if (st == null || !types.contains(st)){
                            severe("Task "+i+" step "+t.getProcessStep().getName()+" "+st);
                            assert(false);
                        }
                        typeValues[i] = st.getNr();
                        i++;
                    }
                    info("Types "+ Arrays.toString(typeValues));
                    IloIntervalSequenceVar seq = cp.intervalSequenceVar(disjunctive.toArray(), typeValues);

                    cp.add(cp.noOverlap(seq,setupMatrix));
                    if (k==0) {
                        seq0 = seq;
                    } else if (run.getAddSameOrder()){
                        cp.add(cp.sameSequence(seq0, seq));
                    }

                } else {
                    IloIntervalSequenceVar seq = cp.intervalSequenceVar(disjunctive.toArray());
                    cp.add(cp.noOverlap(seq));
                    if (k==0) {
                        seq0 = seq;
                    } else if (run.getAddSameOrder()){
                        cp.add(cp.sameSequence(seq0, seq));
                    }
                    //??? we use the sequence var version of this instead
                    //cp.add(cp.noOverlap(disjunctive.toArray()));
                }
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
                case Projection -> {
                    // this is an experimental objective for the alternative model of the SALBP problem
                    // it defines the number of stations as the real objective, and links this to the makespan via a projection

                    IloIntExpr makespan = cp.intVar(0,base.getHorizon());
                    IloIntExpr nrStations = cp.intVar(0,base.getHorizon()/1001);
                    IloIntExpr time = cp.intVar(0,1000);
                    // Constraint makespan+time = 1001*nrStations with dummy time rest
                    cp.addEq(cp.sum(makespan,time),cp.prod(nrStations,1001));
                    IntExprList ends = new IntExprList();
                    for(int j=0;j<nrJobs;j++){
                        ends.add(cp.endOf(y[j]));
                    }
                    cp.addEq(makespan,cp.max(ends.toArray()));
                    objective = cp.minimize(nrStations);
                }
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
                case OnTime -> {
                    IntExprList total = new IntExprList();
                    total.add(cp.prod(run.getWeightLateness(),cp.sum(lateness)));
                    total.add(cp.prod(run.getWeightEarliness(),cp.sum(earliness)));

                    objective = cp.minimize(cp.sum(total.toArray()));

                }
                case Hybrid -> {
                    IntExprList total = new IntExprList();
                    IloIntExpr makespan = cp.intVar(0,base.getHorizon());
                    IloIntExpr flowtime = cp.intVar(0,base.getHorizon()*nrJobs);
                    IntExprList ends = new IntExprList();
                    for(int j=0;j<nrJobs;j++){
                        ends.add(cp.endOf(y[j]));
                    }
                    cp.addEq(makespan,cp.max(ends.toArray()));
                    cp.addEq(flowtime,cp.sum(ends.toArray()));
                    total.add(cp.prod(run.getWeightMakespan(),makespan));
                    total.add(cp.prod(run.getWeightFlowtime(),flowtime));
                    total.add(cp.prod(run.getWeightLateness(),cp.sum(lateness)));
                    total.add(cp.prod(run.getWeightEarliness(),cp.sum(earliness)));

                    objective = cp.minimize(cp.sum(total.toArray()));

                }
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
                info("Solved Obj "+obj+" status "+status+" bound "+bound+" gapPercent "+100.0*gap);
                Solution sol = new Solution(base);
                sol.setName("Sol"+solNr++);
                sol.setObjectiveValue(obj);
                sol.setSolverStatus(toSolverStatus(status));
                run.setSolverStatus(toSolverStatus(status));
                run.setTime(time(startTime));
                sol.setSolverRun(run);
                sol.setBound(bound);
                sol.setGapPercent(100.0*gap);

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
                    //??? check on correctness of extracted lateness
                    assert(late == Math.max(0,end-due));

                    JobAssignment ja = new JobAssignment(base);
                    ja.setName("JA"+j);
                    ja.setSolution(sol);
                    ja.setJob(jobs[j]);
                    ja.setStart(start);
                    ja.setEnd(end);
                    ja.setStartDate(toDateTime(base,start));
                    ja.setEndDate(toDateTime(base,end));
                    ja.setDuration(duration);
                    ja.setLate(late);
                    ja.setEarly(early);
                    jaHash.put(jobs[j],ja);
                    jaList.add(ja);
                }
                Hashtable<Task,TaskAssignment> assignHash = new Hashtable<>();
                //extract task assignment
                for(int i=0;i<nrTasks;i++){
                    int start = cp.getStart(x[i]);
                    int end = cp.getEnd(x[i]);
                    int duration = end-start;
                    TaskAssignment ta = new TaskAssignment(base);
                    ta.setName("TA"+i);
                    ta.setJobAssignment(jaHash.get(tasks[i].getJob()));
                    ta.setTask(tasks[i]);
                    assignHash.put(tasks[i],ta);
                    ta.setStart(start);
                    ta.setEnd(end);
                    ta.setStartDate(toDateTime(base,start));
                    ta.setEndDate(toDateTime(base,end));
                    ta.setDuration(duration);
                    int cnt =0;
                    for(int k=0;k<nrDisjunctiveResources;k++){
                        if (z[i][k] != null && cp.isPresent(z[i][k])){
                            ta.setDisjunctiveResource(disjRes[k]);
                            cnt++;
                        }
                    }
                    // allow for tasks without disjunctive need
                    assert(cnt<=1);
                }
                Collection<TaskAssignment> taList = assignHash.values();

                calculateWaitBeforeAfter(assignHash);
                calculateSetupAndIdleTimes(taList);

                updateSolution(sol,jaList,taList);

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

private boolean canBeUsedAsBuffer(Task t){
        for(ProcessSequence ps:base.getListProcessSequence()){
            if (ps.getBefore() == t.getProcessStep() && ps.getSequenceType()== Blocking){
                return true;
            }
        }
        return false;
}

private int durationOnMachine(int dur, Task t,DisjunctiveResource r){
        ProcessStep ps = t.getProcessStep();
        for(ResourceNeed rn:base.getListResourceNeed()){
            if (rn.getProcessStep() == ps && rn.getDisjunctiveResource() == r){
                int duration = rn.getDurationFixed()+rn.getDurationPerUnit()*t.getJob().getOrder().getQty();
                if (duration != dur){
                    info("Duration differ "+dur+" "+duration);
                }
                return duration;
            }
        }
        return dur;

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


    private IloTransitionDistance createSetupMatrix(IloCP cp,Setup setup,List<SetupType> types) throws IloException{
        int nrTypes = types.size();
        IloTransitionDistance res = cp.transitionDistance(nrTypes);
        for(int i=0;i<nrTypes;i++){
            for(int j=0;j<nrTypes;j++){
                if (i==j && setup.getSameValue() != null){
                    res.setValue(i,j,setup.getSameValue());
                } else if (setup.getDefaultValue() != null){
                    res.setValue(i,j,setup.getDefaultValue());
                }

            }
        }
        for(SetupMatrix entry:base.getListSetupMatrix().stream().filter(x->x.getFrom().getSetup()==setup).toList()){
            res.setValue(entry.getFrom().getNr(),entry.getTo().getNr(),entry.getValue());
        }
        return res;

    }


}

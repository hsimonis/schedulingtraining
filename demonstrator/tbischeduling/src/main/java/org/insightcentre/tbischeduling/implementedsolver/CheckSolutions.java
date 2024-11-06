package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static org.insightcentre.tbischeduling.datamodel.Severity.*;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class CheckSolutions {
    static int solutionError = 1;
    Scenario base;
    /*
    check one solution, used after running solver
     */
    public CheckSolutions(Scenario base, Solution sol){
        this.base= base;
        base.resetListSolutionError();
        checkSolution(sol);
    }
    /*
    check all solutions, used after reading data files
     */
    public CheckSolutions(Scenario base, List<Solution> sols){
        this.base= base;
        base.resetListSolutionError();
        for(Solution sol:sols){
            checkSolution(sol);
        }
    }

    private void checkSolution(Solution sol){
        info("Checking solution "+sol.getName());
        SolverRun run = sol.getSolverRun();
        List<JobAssignment> jaList = base.getListJobAssignment().stream().
                filter(x->x.getSolution()==sol).
                toList();
        List<TaskAssignment> taList = base.getListTaskAssignment().stream().
                filter(x->x.getJobAssignment().getSolution()==sol).
                toList();
        Hashtable<Job,JobAssignment> jobHash = new Hashtable<>();
        Hashtable<Task,TaskAssignment> taskHash = new Hashtable<>();
        int nrStructuralProblems = 0;
        for(JobAssignment ja:jaList){
            JobAssignment old = jobHash.get(ja.getJob());
            if (old != null){
                nrStructuralProblems++;
                newError(sol,"JobAssignment",ja.getName(),"job",ja.getJob().getName(),
                        "Two assignments for same job "+ja.getName()+" "+old.getName(),Fatal);
            }
            jobHash.put(ja.getJob(),ja);
        }
        for(Job j:base.getListJob()){
            JobAssignment ja = jobHash.get(j);
            if (ja == null){
                nrStructuralProblems++;
                newError(sol,"JobAssignment","n/a","job",j.getName(),
                        "Job is not assigned in solution "+sol.getName(),Fatal);
            } else {
                assert (ja.getJob() == j);
            }
        }
        for(TaskAssignment ta:taList){
            TaskAssignment old = taskHash.get(ta.getTask());
            if (old != null){
                nrStructuralProblems++;
                newError(sol,"TaskAssignment",ta.getName(),"task",ta.getTask().getName(),
                        "Two assignments for same task "+ta.getName()+" "+old.getName(),Fatal);
            }
            taskHash.put(ta.getTask(),ta);
        }
        for(Task t:base.getListTask()){
            TaskAssignment ta = taskHash.get(t);
            if (ta==null){
                nrStructuralProblems++;
                newError(sol,"TaskAssignment","n/a","task",t.getName(),
                        "Task is not assignment in solution "+sol.getName(),Fatal);
            } else {
                assert(ta.getTask()==t);
            }
        }
        if (nrStructuralProblems == 0) {
            // structurally sound, one assignment per task and job
            // we can proceed with the actual tests
            Hashtable<String,ProcessSequence> sequenceHash = new Hashtable<>();
            for(ProcessSequence seq:base.getListProcessSequence()){
                sequenceHash.put(sequenceKey(seq),seq);
            }
            for (Task t : base.getListTask()) {
                TaskAssignment ta = taskHash.get(t);
                for (Task a : t.getPrecedes()) {
                    TaskAssignment after = taskHash.get(a);
                    ProcessSequence seq = sequenceHash.get(sequenceKey(ta,after));
                    switch(seq.getSequenceType()) {
                        case EndBeforeStart:

                            if (!run.getRelaxSequence() && after.getStart() < ta.getEnd()) {
                                newError(sol, "Task", t.getName(), "precedes", a.getName(),
                                        "Precedence EndBeforeStart not respected", Fatal);
                            }
                            break;
                        case StartBeforeStart:
                            if (!run.getRelaxSequence() && after.getStart() < ta.getStart()) {
                                newError(sol, "Task", t.getName(), "precedes", a.getName(),
                                        "Precedence StartBeforeStart not respected", Fatal);
                            }
                            break;
                        default:
                            severe("Bad sequence type "+seq.getSequenceType());
                            assert(false);
                    }

                }
                if (ta.getDisjunctiveResource() != null && !t.getMachines().contains(ta.getDisjunctiveResource())) {
                    newError(sol, "TaskAssignment", ta.getName(), "disjunctiveResource",
                            ta.getDisjunctiveResource().getName(),
                            "Machine not in allowed list " + machineNames(t.getMachines()), Fatal);
                }
                if (ta.getDuration() != ta.getEnd() - ta.getStart()) {
                    newError(sol, "TaskAssignment", ta.getName(), "duration", ta.getDuration(),
                            "Duration is wrong, should be " + (ta.getEnd() - ta.getStart()), Fatal);
                }

            }
            for (Job j : base.getListJob()) {
                JobAssignment ja = jobHash.get(j);
                List<TaskAssignment> tasksOfJob = base.getListTaskAssignment().stream().
                        filter(x->x.getJobAssignment()==ja).
                        sorted(Comparator.comparing(TaskAssignment::getStart)).
                        toList();
                if (ja.getStart() != tasksOfJob.stream().mapToInt(TaskAssignment::getStart).min().orElse(0)){
                    newError(sol,"JobAssignment",ja.getName(),"start",ja.getStart(),"Job start must be minimum of its task starts",Fatal);
                }
                if (ja.getEnd() != tasksOfJob.stream().mapToInt(TaskAssignment::getEnd).max().orElse(0)){
                    newError(sol,"JobAssignment",ja.getName(),"end",ja.getEnd(),"Job end must be maximum of its task ends",Fatal);
                }
                TaskAssignment prev = null;
                for(TaskAssignment t:tasksOfJob){
                    if ((j.getNoOverlap()||run.getRelaxSequence()) && prev != null && prev.getEnd() > t.getStart()){
                        newError(sol,"Job",ja.getName(),"start",t.getStart(),"Tasks of job overlap at this time",Fatal);
                    }
                    prev= t;
                }
                if (ja.getDuration() != ja.getEnd()-ja.getStart()){
                    newError(sol,"JobAssignment",ja.getName(),"duration",ja.getDuration(),
                            "Duration is wrong, should be "+(ja.getEnd()-ja.getStart()),Fatal);
                }
                if (ja.getStart() < j.getOrder().getRelease()) {
                    // release date violations are only fatal if they are enforced
                    newError(sol, "JobAssignment", ja.getName(), "start", ja.getStart(),
                            "Job starts before release " + j.getOrder().getRelease() + ", early " + ja.getEarly(),
                            (sol.getSolverRun().getEnforceReleaseDate() ? Fatal : Minor));
                }

                if (ja.getEnd() > j.getOrder().getDue()) {
                    // due date violation are only fatal if the constraint is enforced
                    newError(sol, "JobAssignment", ja.getName(), "end", ja.getEnd(),
                            "Job ends after due " + j.getOrder().getDue() + ", late " + ja.getLate(),
                            (sol.getSolverRun().getEnforceDueDate() ? Fatal : Minor));
                }
                if (ja.getLate() != Math.max(0, ja.getEnd() - j.getOrder().getDue())) {
                    newError(sol, "JobAssignment", ja.getName(), "late", ja.getLate(),
                            "Job lateness incorrect, should be " + Math.max(0, ja.getEnd() - j.getOrder().getDue()),
                            Fatal);
                }
                if (ja.getEarly() != Math.max(0, j.getOrder().getDue() - ja.getEnd())) {
                    newError(sol, "JobAssignment", ja.getName(), "early", ja.getEarly(),
                            "Job earliness incorrect, should be " + Math.max(0, j.getOrder().getDue() - ja.getEnd()),
                            Fatal);
                }
            }
            Map<DisjunctiveResource, List<TaskAssignment>> map = taList.stream().
                    filter(x->x.getDisjunctiveResource()!= null).
                    collect(groupingBy(TaskAssignment::getDisjunctiveResource));
            for (DisjunctiveResource m : map.keySet()) {
                List<ResourceActivity> list = new ArrayList<>(map.get(m));
                if (sol.getSolverRun().getEnforceDowntime()) {
                    // only include downtimes if they are enforced in the solution
                    list.addAll(base.getListDowntime().stream().filter(x -> x.getDisjunctiveResource() == m).toList());
                }
                if (sol.getSolverRun().getEnforceWip()) {
                    // only include WiP if they are enforced in the solution
                    list.addAll(base.getListWiP().stream().filter(x -> x.getDisjunctiveResource() == m).toList());
                }
                ResourceActivity prev = null;
                for (ResourceActivity ra : list.stream().sorted(Comparator.comparing(ResourceActivity::getStart)).toList()) {
                    if (prev != null && prev.getEnd() > ra.getStart()) {
                        newError(sol, "DisjunctiveResource", m.getName(), prev.getName(), ra.getName(),
                                "Activities overlap", Fatal);
                    }
                    prev = ra;
                }
            }
            for (CumulativeResource r : base.getListCumulativeResource()) {
                Hashtable<ProcessStep, Integer> needsHash = new Hashtable<>();
                for (CumulativeNeed cn : base.getListCumulativeNeed().stream().filter(x -> x.getCumulativeResource() == r).toList()) {
                    needsHash.put(cn.getProcessStep(), cn.getDemand());
                }
                List<CumulativeProfile> profile = base.getListCumulativeProfile().stream().
                        filter(x -> x.getCumulativeResource() == r).
                        sorted(Comparator.comparing(CumulativeProfile::getFrom)).toList();
                List<TaskAssignment> tasks = taList.stream().filter(x -> requiresResource(x, needsHash)).toList();
                if (tasks.size() > 0 && profile.size() == 0) {
                    newError(sol, "CumulativeResource", r.getName(), "n/a", "n/a",
                            "There is no capacity profile for this resource", Fatal);
                }
                //??? missing compute actual profile and check against capacity
                if (sol.getSolverRun().getEnforceCumulative()) {
                    //??? dummy message for missing check
                    newError(sol, "CumulativeResource", r.getName(), "n/a", "n/a",
                            "Resource may exceed capacity", Minor);
                }
            }
            checkKpis(sol, jaList);
        } else {
            severe("Solution has structural problems, not further checks run");
        }

        info("Solution "+sol.getName()+ " checked, "+
                base.getListSolutionError().stream().filter(x->x.getSolution()==sol).filter(x->x.getSeverity()==Minor).count()+" minor errors, "+
                base.getListSolutionError().stream().filter(x->x.getSolution()==sol).filter(x->x.getSeverity()!=Minor).count()+" serious errors");
    }

    private void checkKpis(Solution sol,List<JobAssignment> jaList){
        // accuracy limit for double comparisons
        double accuracy =1e-5;

        int makespan = jaList.stream().mapToInt(JobAssignment::getEnd).max().orElse(0);
        if (sol.getMakespan() != makespan){
            newError(sol,"Solution",sol.getName(),"makespan",sol.getMakespan(),
                    "Makespan given is wrong, should be "+makespan,Fatal);
        }
        int flowtime = jaList.stream().mapToInt(JobAssignment::getEnd).sum();
        if (sol.getFlowtime() != flowtime){
            newError(sol,"Solution",sol.getName(),"flowtime",sol.getFlowtime(),
                    "Flowtime given is wrong, should be "+flowtime,Fatal);
        }
        int totalLateness = jaList.stream().mapToInt(JobAssignment::getLate).sum();
        if (sol.getTotalLateness() != totalLateness){
            newError(sol,"Solution",sol.getName(),"totalLateness",sol.getTotalLateness(),
                    "Total Lateness given is wrong, should be "+totalLateness,Fatal);
        }
        double weightedLateness = jaList.stream().mapToDouble(this::weightedLateness).sum();
        if (Math.abs(sol.getWeightedLateness()-weightedLateness)> accuracy){
            newError(sol,"Solution",sol.getName(),"weightedLateness",sol.getWeightedLateness(),
                    "Weighted Lateness given is wrong, should be "+weightedLateness,Fatal);
        }
        int maxLateness = jaList.stream().mapToInt(JobAssignment::getLate).max().orElse(0);
        if (sol.getMaxLateness() != maxLateness){
            newError(sol,"Solution",sol.getName(),"maxLateness",sol.getMaxLateness(),
                    "Max Lateness given is wrong, should be "+maxLateness,Fatal);
        }
        int nrLate = (int) jaList.stream().filter(x->x.getLate()> 0).count();
        if (sol.getNrLate() != nrLate){
            newError(sol,"Solution",sol.getName(),"nrLate",sol.getNrLate(),
                    "Nr Late value given is wrong, should be "+nrLate,Fatal);
        }
        double percentLate = 100.0*nrLate/jaList.size();
        if (Math.abs(sol.getPercentLate()-percentLate)> accuracy){
            newError(sol,"Solution",sol.getName(),"percentLate",sol.getPercentLate(),
                    "Percent jobs late value given is wrong, should be "+percentLate,Fatal);
        }
    }

    private double weightedLateness(JobAssignment ja){
        return ja.getLate()*ja.getJob().getOrder().getLatenessWeight();
    }

    private boolean requiresResource(TaskAssignment ta,Hashtable<ProcessStep,Integer> hash){
        return hash.get(ta.getTask().getProcessStep()) != null;
    }

    private String machineNames(List<DisjunctiveResource> list){
        return list.stream().map(ApplicationObject::getName).collect(Collectors.joining(","));
    }

    private void newError(Solution sol,String classDesc,String item,String attr,Integer value,
                          String description,Severity severity){
        newError(sol,classDesc,item,attr,String.format("%d",value),description,severity);
    }
    private void newError(Solution sol,String classDesc,String item,String attr,Double value,
                          String description,Severity severity){
        newError(sol,classDesc,item,attr,String.format("%f",value),description,severity);
    }
    private void newError(Solution sol,String classDesc,String item,String attr,String value,
                String description,Severity severity){
        SolutionError se= new SolutionError(base);
        se.setName("SE"+solutionError++);
        se.setSolution(sol);
        se.setClassDesc(classDesc);
        se.setItem(item);
        se.setField(attr);
        se.setValue(value);
        se.setDescription(description);
        se.setSeverity(severity);


    }

    private String sequenceKey(TaskAssignment before,TaskAssignment after){
        return before.getTask().getProcessStep().getName()+"/"+after.getTask().getProcessStep().getName();
    }
    private String sequenceKey(ProcessSequence seq){
        return seq.getBefore().getName()+"/"+seq.getAfter().getName();
    }
}

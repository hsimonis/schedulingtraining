package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.*;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static org.insightcentre.tbischeduling.utilities.TypeConverters.toDateTime;

public abstract class AbstractModel {
    Scenario base;
    SolverRun run;
    static int solNr = 1;


    public AbstractModel(Scenario base, SolverRun run){
        this.base = base;
        this.run = run;
    }

    public abstract boolean solve();

    protected void updateSolution(Solution sol, Collection<JobAssignment> jaList, Collection<TaskAssignment> taList){
        int nrJobs = jaList.size();
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
        sol.setStart(jaList.stream().mapToInt(JobAssignment::getStart).min().orElse(0));
        sol.setEnd(jaList.stream().mapToInt(JobAssignment::getEnd).max().orElse(0));
        sol.setStartDate(toDateTime(base,sol.getStart()));
        sol.setEndDate(toDateTime(base,sol.getEnd()));
        sol.setDuration(sol.getEnd()-sol.getStart());
        sol.setTotalWaitBefore(taList.stream().mapToInt(TaskAssignment::getWaitBefore).sum());
        sol.setMaxWaitBefore(taList.stream().mapToInt(TaskAssignment::getWaitBefore).max().orElse(0));
        sol.setTotalWaitAfter(taList.stream().mapToInt(TaskAssignment::getWaitAfter).sum());
        sol.setMaxWaitAfter(taList.stream().mapToInt(TaskAssignment::getWaitAfter).max().orElse(0));
        sol.setTotalSetupBefore(taList.stream().mapToInt(TaskAssignment::getSetupBefore).sum());
        sol.setMaxSetupBefore(taList.stream().mapToInt(TaskAssignment::getSetupBefore).max().orElse(0));
        sol.setTotalSetupAfter(taList.stream().mapToInt(TaskAssignment::getSetupAfter).sum());
        sol.setMaxSetupAfter(taList.stream().mapToInt(TaskAssignment::getSetupAfter).max().orElse(0));
        sol.setTotalIdleBefore(taList.stream().mapToInt(TaskAssignment::getIdleBefore).sum());
        sol.setMaxIdleBefore(taList.stream().mapToInt(TaskAssignment::getIdleBefore).max().orElse(0));
        sol.setTotalIdleAfter(taList.stream().mapToInt(TaskAssignment::getIdleAfter).sum());
        sol.setMaxIdleAfter(taList.stream().mapToInt(TaskAssignment::getIdleAfter).max().orElse(0));
        new PlacementLayout(base,sol);
    }


    private double weightedLateness(JobAssignment ja){
        return ja.getLate()*ja.getJob().getOrder().getLatenessWeight();
    }
    private double weightedEarliness(JobAssignment ja){
        return ja.getEarly()*ja.getJob().getOrder().getLatenessWeight();
    }

    protected void calculateWaitBeforeAfter(Hashtable<Task,TaskAssignment> assignHash){
        // calculate waitBefore and waitAfter for each task
        for(Task t:base.getListTask()){
            TaskAssignment ta = assignHash.get(t);
            assert(ta != null);
            int waitBefore = t.getFollows().stream().mapToInt(xx->ta.getStart()-assignHash.get(xx).getEnd()).min().orElse(0);
            int waitAfter = t.getPrecedes().stream().mapToInt(xx->assignHash.get(xx).getStart()-ta.getEnd()).min().orElse(0);
            ta.setWaitBefore(waitBefore);
            ta.setWaitAfter(waitAfter);
        }

    }

    protected void calculateSetupAndIdleTimes(Collection<TaskAssignment> taList){
        // calculate setup and idle times for each task
        Map<DisjunctiveResource,List<TaskAssignment>> mapAssignment = taList.stream().
                filter(xx->xx.getDisjunctiveResource()!= null).
                collect(groupingBy(TaskAssignment::getDisjunctiveResource));
        for(DisjunctiveResource r:mapAssignment.keySet()){
            List<TaskAssignment> orderedInTime = mapAssignment.get(r).stream().sorted(Comparator.comparing(TaskAssignment::getStart)).toList();
            TaskAssignment prev = null;
            for(TaskAssignment t:orderedInTime){
                if (prev != null) {
                    int totalGap = t.getStart() - prev.getEnd();
                    int setup = calculateSetupTime(r,prev.getTask(),t.getTask());
                    int idle = totalGap-setup;
                    prev.setIdleAfter(idle);
                    prev.setSetupAfter(setup);
                    t.setIdleBefore(idle);
                    t.setSetupBefore(setup);
                } else {
                    t.setIdleBefore(0);
                    t.setSetupBefore(0);
                }
                prev = t;
            }
            prev.setIdleAfter(0);
            prev.setSetupAfter(0);
        }

    }

    private int calculateSetupTime(DisjunctiveResource r,Task before, Task after){
        if (r.getSetup()==null){
            return 0;
        } else {
            Setup setup = r.getSetup();
            SetupMatrix entry = base.getListSetupMatrix().stream().
                    filter(x->x.getFrom()==before.getProcessStep().getSetupType()).
                    filter(x->x.getTo()==after.getProcessStep().getSetupType()).
                    findAny().orElse(null);
            if (entry != null){
                return entry.getValue();
            } else if (before.getProcessStep().getSetupType() == after.getProcessStep().getSetupType()){
                return setup.getSameValue();
            } else {
                return setup.getDefaultValue();
            }
        }
    }



}

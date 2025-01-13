package org.insightcentre.tbischeduling.minizinc;

import jakarta.annotation.Resource;
import org.insightcentre.tbischeduling.datamodel.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.List;

public class MiniZincProblem {
    Scenario base;
    public MiniZincProblem(Scenario base){
        this.base = base;
    }

    public JSONObject create(){
        JSONObject res = new JSONObject();
        Hashtable<Task,Integer> taskHash = new Hashtable<>();
        res.put("jobs",createJobs());
        res.put("tasks",createTasks(taskHash));
        res.put("machines",createMachines());
        res.put("cumulativeMachines",createCumulativeMachines());
        JSONArray cumulativeNeeds = createCumulativeNeeds(taskHash);
        res.put("cumulativeNeeds",cumulativeNeeds);
        JSONArray sequences = createSequences(taskHash);
        res.put("sequences",sequences);
        JSONArray downtimes = createDowntimes();
        res.put("downtimes",downtimes);
        res.put("nrJobs",base.getListJob().size());
        res.put("nrTasks",base.getListTask().size());
        res.put("nrMachines",base.getListDisjunctiveResource().size());
        res.put("nrCumulativeMachines",base.getListCumulativeResource().size());
        res.put("nrCumulativeNeeds",cumulativeNeeds.length());
        res.put("nrSequences",sequences.length());
        res.put("nrDowntimes",downtimes.length());
        res.put("horizon",base.getHorizon());
        return res;
    }

    private JSONArray createJobs(){
        JSONArray arr = new JSONArray();
        int jNr = 1;
        for(Job j:base.getListJob()){
            JSONObject job = new JSONObject();
            job.put("name",j.getName());
            job.put("nr",jNr++);
            job.put("release",j.getOrder().getRelease());
            job.put("due",j.getOrder().getDue());
            job.put("noOverlap",j.getNoOverlap());
            arr.put(job);
        }
        return arr;
    }

    private JSONArray createTasks(Hashtable<Task,Integer> taskHash){
        JSONArray arr = new JSONArray();
        int tNr = 1;
        for(Task t:base.getListTask()){
            taskHash.put(t,tNr);
            JSONObject task = new JSONObject();
            task.put("name",t.getName());
            task.put("job",t.getJob().getName());
            task.put("nr",tNr++);
            task.put("duration",t.getDuration());
            if (t.getMachines().size()==1){
                task.put("machine",t.getMachines().get(0).getName());
            } else if (t.getMachines().size() == 0) {
                task.put("machine","n/a");
            }
//            task.put("machines",toJSONArray(t.getMachines()));

            arr.put(task);
        }
        return arr;
    }

    private JSONArray toJSONArray(List<DisjunctiveResource> machines){
        JSONArray arr = new JSONArray();
        for(DisjunctiveResource m:machines){
            arr.put(m.getName());
        }
        return arr;
    }

    private JSONArray createMachines(){
        JSONArray arr = new JSONArray();
        int mNr = 1;
        for(DisjunctiveResource m:base.getListDisjunctiveResource()){
            JSONObject machine = new JSONObject();
            machine.put("name",m.getName());
            machine.put("nr",mNr++);
            arr.put(machine);
        }
        return arr;

    }
    private JSONArray createCumulativeMachines(){
        JSONArray arr = new JSONArray();
        int mNr = 1;
        for(CumulativeResource m:base.getListCumulativeResource()){
            JSONObject machine = new JSONObject();
            machine.put("name",m.getName());
            machine.put("nr",mNr++);
            machine.put("limit",limit(m));
            arr.put(machine);
        }
        return arr;
    }

    private int limit(CumulativeResource m){
        return base.getListCumulativeProfile().stream().mapToInt(CumulativeProfile::getCapacity).max().orElse(0);
    }

    private JSONArray createCumulativeNeeds(Hashtable<Task,Integer> taskHash){
        JSONArray arr = new JSONArray();
        for(CumulativeNeed cn:base.getListCumulativeNeed()){
            for(Task t:base.getListTask().stream().filter(x->x.getProcessStep()==cn.getProcessStep()).toList()) {
                JSONObject need = new JSONObject();
                need.put("cumulativeMachine", cn.getCumulativeResource().getName());
                need.put("taskNr", taskHash.get(t));
                need.put("demand", cn.getDemand());
                arr.put(need);
            }
        }
        return arr;
    }

    private JSONArray createSequences(Hashtable<Task,Integer>taskHash){
        JSONArray arr = new JSONArray();
        for(Task t:base.getListTask()){
            for(Task after:t.getPrecedes()){
                JSONObject sequence = new JSONObject();
                sequence.put("before",taskHash.get(t));
                sequence.put("after",taskHash.get(after));
                ProcessSequence ps = findProcessSequence(t.getProcessStep(),after.getProcessStep());
                assert(ps != null);
                sequence.put("offset",ps.getOffset());
                sequence.put("sequenceType",ps.getSequenceType().toString());
                arr.put(sequence);
            }
        }
        return arr;
    }

    private JSONArray createDowntimes(){
        JSONArray arr = new JSONArray();
        int dNr = 1;
        for(Downtime d:base.getListDowntime()){
            JSONObject downtime = new JSONObject();
            downtime.put("name",d.getName());
            downtime.put("nr",dNr++);
            downtime.put("machine",d.getDisjunctiveResource().getName());
            downtime.put("start",d.getStart());
            downtime.put("duration",d.getDuration());
            arr.put(downtime);
        }
        return arr;

    }

    private ProcessSequence findProcessSequence(ProcessStep before,ProcessStep after){
        return base.getListProcessSequence().stream().
                filter(x->x.getBefore()==before && x.getAfter()==after).
                findAny().orElse(null);
    }
}

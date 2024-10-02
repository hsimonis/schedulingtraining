package org.insightcentre.tbischeduling.exporter;

import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class WriteData {
    Scenario base;
    JSONObject root;
    public WriteData(Scenario base){
        this.base = base;
        root = createJSON();
     }

    public JSONObject toJSON(){
        return root;
    }
    public String toString(int indent){
        return root.toString(indent);
    }
    public void toFile(File selected,int indent) {
        try {
            PrintWriter out = new PrintWriter(selected);
            out.println(toString(indent));
            out.close();
            base.setDirty(false);
        } catch (IOException e) {
            severe("Cannot write file " + selected.toString() + ", exception " + e.getMessage());
        }
    }

    private JSONObject createJSON(){
        JSONObject root = new JSONObject();
        // this is the current version number of the data, as defined in minimalData
        root.put("version",base.getDataFileVersionNumber());
        root.put("label","TbIScheduling data file");

        root.put("inputError",inputErrors());
        root.put("problem",problems());
        root.put("product",products());
        root.put("process",processes());
        root.put("disjunctiveResource",disjunctiveResources());
        root.put("cumulativeResource",cumulativeResources());
        root.put("processStep",processSteps());
        root.put("processSequence",processSequences());
        root.put("resourceNeed",resourceNeeds());
        root.put("cumulativeNeed",cumulativeNeeds());
        root.put("cumulativeProfile",cumulativeProfiles());

        root.put("order",orders());
        root.put("job",jobs());
        root.put("task",tasks());
        root.put("wip",wips());
        root.put("downtime",downtimes());

        root.put("solverRun",solverRuns());
        root.put("solution",sols());
        root.put("jobAssignment",jobAssignments());
        root.put("taskAssignment",taskAssignments());



        return root;
    }

    /*
    data elements
     */

    private JSONArray inputErrors(){
        JSONArray res = new JSONArray();
        for(InputError e:base.getListInputError()){
            JSONObject obj = new JSONObject();
            obj.put("name",e.getName());
            obj.put("classDesc",e.getClassDesc());
            obj.put("item",e.getItem());
            obj.put("field",e.getField());
            obj.put("value",e.getValue());
            obj.put("description",e.getDescription());
            obj.put("severity",e.getSeverity().toString());
            res.put(obj);
        }
        return res;
    }
    private JSONArray problems(){
        JSONArray res = new JSONArray();
        for(Problem p:base.getListProblem()){
            JSONObject obj = new JSONObject();
            obj.put("name",p.getName());
            obj.put("timePointsAsDate",p.getTimePointsAsDate());
            obj.put("nrProducts",p.getNrProducts());
            obj.put("nrProcesses",p.getNrProcesses());
            obj.put("nrDisjunctiveResources",p.getNrDisjunctiveResources());
            obj.put("nrCumulativeResources",p.getNrCumulativeResources());
            obj.put("nrOrders",p.getNrOrders());
            obj.put("nrJobs",p.getNrJobs());
            obj.put("nrTasks",p.getNrTasks());
            res.put(obj);
        }
        return res;
    }
    private JSONArray products(){
        JSONArray res = new JSONArray();
        for(Product p:base.getListProduct()){
            JSONObject obj = new JSONObject();
            obj.put("name",p.getName());
            obj.put("defaultProcess",p.getDefaultProcess().getName());
            res.put(obj);
        }
        return res;
    }
    private JSONArray processes(){
        JSONArray res = new JSONArray();
        for(Process p:base.getListProcess()){
            JSONObject obj = new JSONObject();
            obj.put("name",p.getName());
            res.put(obj);
        }
        return res;
    }
    private JSONArray disjunctiveResources(){
        JSONArray res = new JSONArray();
        for(DisjunctiveResource r:base.getListDisjunctiveResource()){
            JSONObject obj = new JSONObject();
            obj.put("name",r.getName());
            res.put(obj);
        }
        return res;
    }
    private JSONArray cumulativeResources(){
        JSONArray res = new JSONArray();
        for(CumulativeResource r:base.getListCumulativeResource()){
            JSONObject obj = new JSONObject();
            obj.put("name",r.getName());
            res.put(obj);
        }
        return res;
    }


    private JSONArray processSteps(){
        JSONArray res = new JSONArray();
        for(ProcessStep p:base.getListProcessStep()){
            JSONObject obj = new JSONObject();
            obj.put("name",p.getName());
            obj.put("process",p.getProcess().getName());
            obj.put("durationFixed",p.getDurationFixed());
            obj.put("durationPerUnit",p.getDurationPerUnit());
            res.put(obj);
        }
        return res;
    }

    private JSONArray processSequences(){
        JSONArray res = new JSONArray();
        for(ProcessSequence p:base.getListProcessSequence()){
            JSONObject obj = new JSONObject();
            obj.put("name",p.getName());
            obj.put("before",p.getBefore().getName());
            obj.put("after",p.getAfter().getName());
            obj.put("sequenceType",p.getSequenceType().toString());
            obj.put("offset",p.getOffset());
            res.put(obj);
        }
        return res;
    }

    private JSONArray resourceNeeds(){
        JSONArray res = new JSONArray();
        for(ResourceNeed p:base.getListResourceNeed()){
            JSONObject obj = new JSONObject();
            obj.put("name",p.getName());
            obj.put("processStep",p.getProcessStep().getName());
            obj.put("disjunctiveResource",p.getDisjunctiveResource().getName());
            res.put(obj);
        }
        return res;
    }
    private JSONArray cumulativeNeeds(){
        JSONArray res = new JSONArray();
        for(CumulativeNeed p:base.getListCumulativeNeed()){
            JSONObject obj = new JSONObject();
            obj.put("name",p.getName());
            obj.put("processStep",p.getProcessStep().getName());
            obj.put("cumulativeResource",p.getCumulativeResource().getName());
            obj.put("demand",p.getDemand());
            res.put(obj);
        }
        return res;
    }
    private JSONArray cumulativeProfiles(){
        JSONArray res = new JSONArray();
        for(CumulativeProfile p:base.getListCumulativeProfile()){
            JSONObject obj = new JSONObject();
            obj.put("name",p.getName());
            obj.put("from",p.getFrom());
            obj.put("cumulativeResource",p.getCumulativeResource().getName());
            obj.put("capacity",p.getCapacity());
            res.put(obj);
        }
        return res;
    }

    /*
    schedule elements
     */
    private JSONArray orders(){
        JSONArray res = new JSONArray();
        for(Order p:base.getListOrder()){
            JSONObject obj = new JSONObject();
            obj.put("name",p.getName());
            obj.put("product",p.getProduct().getName());
            obj.put("process",p.getProcess().getName());
            obj.put("qty",p.getQty());
            obj.put("due",p.getDue());
            obj.put("dueDate",p.getDueDate().toString());
            obj.put("release",p.getRelease());
            obj.put("releaseDate",p.getReleaseDate().toString());
            obj.put("latenessWeight",p.getLatenessWeight());
            obj.put("earlinessWeight",p.getEarlinessWeight());
            res.put(obj);
        }
        return res;
    }
    private JSONArray jobs(){
        JSONArray res = new JSONArray();
        for(Job p:base.getListJob()){
            JSONObject obj = new JSONObject();
            obj.put("name",p.getName());
            obj.put("process",p.getProcess().getName());
            obj.put("order",p.getOrder().getName());
            res.put(obj);
        }
        return res;
    }
    private JSONArray tasks(){
        JSONArray res = new JSONArray();
        for(Task p:base.getListTask()){
            JSONObject obj = new JSONObject();
            obj.put("name",p.getName());
            obj.put("job",p.getJob().getName());
            obj.put("processStep",p.getProcessStep().getName());
            obj.put("duration",p.getDuration());
            res.put(obj);
        }
        return res;
    }

    private JSONArray wips(){
        JSONArray res = new JSONArray();
        for(WiP w:base.getListWiP()){
            JSONObject obj = new JSONObject();
            obj.put("name",w.getName());
            obj.put("disjunctiveResource",w.getDisjunctiveResource().getName());
            obj.put("duration",w.getDuration());
            obj.put("start",w.getStart());
            obj.put("startDate",w.getStartDate());
            obj.put("end",w.getEnd());
            obj.put("endDate",w.getEndDate());
            res.put(obj);
        }
        return res;
    }

    private JSONArray downtimes(){
        JSONArray res = new JSONArray();
        for(Downtime d:base.getListDowntime()){
            JSONObject obj = new JSONObject();
            obj.put("name",d.getName());
            obj.put("disjunctiveResource",d.getDisjunctiveResource().getName());
            obj.put("duration",d.getDuration());
            obj.put("start",d.getStart());
            obj.put("end",d.getEnd());
            obj.put("startDate",d.getStartDate().toString());
            obj.put("endDate",d.getEndDate().toString());
            res.put(obj);
        }
        return res;
    }


    /*
    solution elements
     */

    private JSONArray solverRuns(){
        JSONArray res = new JSONArray();
        for(SolverRun run:base.getListSolverRun()){
            JSONObject obj = new JSONObject();
            obj.put("name",run.getName());
            obj.put("label",run.getLabel());
            obj.put("description",run.getDescription());
            obj.put("modelType",run.getModelType().toString());
            obj.put("solverBackend",run.getSolverBackend().toString());
            obj.put("objectiveType",run.getObjectiveType().toString());
            obj.put("enforceReleaseDate",run.getEnforceReleaseDate());
            obj.put("enforceDueDate",run.getEnforceDueDate());
            obj.put("timeout",run.getTimeout());
            obj.put("nrThreads",run.getNrThreads());
            obj.put("seed",run.getSeed());
            obj.put("removeSolution",run.getRemoveSolution());
            obj.put("solverStatus",run.getSolverStatus().toString());
            obj.put("time",run.getTime());
            res.put(obj);
        }
        return res;
    }

    private JSONArray sols(){
        JSONArray res = new JSONArray();
        for(Solution sol:base.getListSolution()){
            JSONObject obj = new JSONObject();
            obj.put("name",sol.getName());
            obj.put("solverRun",sol.getSolverRun().getName());
            obj.put("objectiveValue",sol.getObjectiveValue());
            obj.put("solverStatus",sol.getSolverStatus().toString());
            obj.put("bound",sol.getBound());
            obj.put("gap",sol.getGap());
            obj.put("makespan",sol.getMakespan());
            obj.put("flowtime",sol.getFlowtime());
            obj.put("totalEarliness",sol.getTotalEarliness());
            obj.put("maxEarliness",sol.getMaxEarliness());
            obj.put("nrEarly",sol.getNrEarly());
            obj.put("weightedEarliness",sol.getWeightedEarliness());
            obj.put("totalLateness",sol.getTotalLateness());
            obj.put("maxLateness",sol.getMaxLateness());
            obj.put("nrLate",sol.getNrLate());
            obj.put("weightedLateness",sol.getWeightedLateness());
            obj.put("percentEarly",sol.getPercentEarly());
            obj.put("percentLate",sol.getPercentLate());
            res.put(obj);
        }
        return res;
    }

    private JSONArray jobAssignments(){
        JSONArray res = new JSONArray();
        for(JobAssignment ja:base.getListJobAssignment()){
            JSONObject jObj = new JSONObject();
            jObj.put("name",ja.getName());
            jObj.put("solution",ja.getSolution().getName());
            jObj.put("job",ja.getJob().getName());
            jObj.put("late",ja.getLate());
            jObj.put("early",ja.getEarly());
            jObj.put("start",ja.getStart());
            jObj.put("end",ja.getEnd());
            jObj.put("duration",ja.getDuration());
            jObj.put("startDate",ja.getStartDate());
            jObj.put("endDate",ja.getEndDate());

            res.put(jObj);
        }
        return res;
    }

    private JSONArray taskAssignments(){
        JSONArray res = new JSONArray();
        for(TaskAssignment ta:base.getListTaskAssignment()){
            JSONObject tObj = new JSONObject();
            tObj.put("name",ta.getName());
            tObj.put("jobAssignment",ta.getJobAssignment().getName());
            tObj.put("disjunctiveResource",ta.getDisjunctiveResource().getName());
            tObj.put("task",ta.getTask().getName());
            tObj.put("start",ta.getStart());
            tObj.put("end",ta.getEnd());
            tObj.put("duration",ta.getDuration());
            tObj.put("startDate",ta.getStartDate());
            tObj.put("endDate",ta.getEndDate());

            res.put(tObj);
        }

        return res;

    }

 }

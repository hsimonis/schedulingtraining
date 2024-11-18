package org.insightcentre.tbischeduling.exporter;

import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class WriteData {
    Scenario base;
    JSONObject root;
    public WriteData(Scenario base){
        this.base = base;
        root = createJSON();
     }

    public JSONObject asJSON(Problem x){
        return problem(x);
    }
    public JSONObject asJSON(Product x){
        return product(x);
    }
    public JSONObject asJSON(Process x){
        return process(x);
    }
    public JSONObject asJSON(ProcessStep x){
        return processStep(x);
    }
    public JSONObject asJSON(ProcessSequence x){
        return processSequence(x);
    }
    public JSONObject asJSON(DisjunctiveResource x){
        return disjunctiveResource(x);
    }
    public JSONObject asJSON(ResourceNeed x){
        return resourceNeed(x);
    }
    public JSONObject asJSON(CumulativeResource x){
        return cumulativeResource(x);
    }
    public JSONObject asJSON(CumulativeNeed x){
        return cumulativeNeed(x);
    }
    public JSONObject asJSON(CumulativeProfile x){
        return cumulativeProfile(x);
    }
    public JSONObject asJSON(Order x){
        return order(x);
    }
    public JSONObject asJSON(Job x){
        return job(x);
    }
    public JSONObject asJSON(Task x){
        return task(x);
    }
    public JSONObject asJSON(WiP x){
        return wip(x);
    }
    public JSONObject asJSON(Downtime x){
        return downtime(x);
    }
    public JSONObject asJSON(SolverRun x){
        return solverRun(x);
    }
    public JSONObject asJSON(Solution x){
        return solution(x);
    }
    public JSONObject asJSON(JobAssignment x){
        return jobAssignment(x);
    }
    public JSONObject asJSON(TaskAssignment x){
        return taskAssignment(x);
    }
    public JSONObject asJSON(InputError x){
        return inputError(x);
    }

    public JSONObject toJSON(){
        return root;
    }
    public String toString(int indent){
        return root.toString(indent);
    }
    public void toJSONDoc(File file,int indent){
        toJSONDoc(root,file,indent);
    }
    public void toJSONDoc(JSONObject obj,File file,int indent){
        try {
            info("Writing file "+file.toString());
            PrintWriter out = new PrintWriter(file);
            out.println("@startjson");
            out.println(obj.toString(indent));
            out.println("@endjson");
            out.close();
            base.setDirty(false);
            info("File written");
        } catch (IOException e) {
            severe("Cannot write file " + file.toString() + ", exception " + e.getMessage());
        }
    }
    public void toFile(File selected,int indent) {
        try {
            info("Writing file "+selected.toString());
            PrintWriter out = new PrintWriter(selected);
            out.println(toString(indent));
            out.close();
            base.setDirty(false);
            info("File written");
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
            JSONObject obj = inputError(e);
            res.put(obj);
        }
        return res;
    }

    private JSONObject inputError(InputError e){
        JSONObject obj = new JSONObject();
        obj.put("name",e.getName());
        obj.put("classDesc",e.getClassDesc());
        obj.put("item",e.getItem());
        obj.put("field",e.getField());
        obj.put("value",e.getValue());
        obj.put("description",e.getDescription());
        obj.put("severity",e.getSeverity().toString());
        return obj;
    }
    private JSONArray problems(){
        JSONArray res = new JSONArray();
        for(Problem p:base.getListProblem()){
            JSONObject obj = problem(p);
            res.put(obj);
        }
        return res;
    }

    private JSONObject problem(Problem p){
        JSONObject obj = new JSONObject();
        obj.put("name",p.getName());
        obj.put("label",p.getLabel());
        obj.put("timePointsAsDate",p.getTimePointsAsDate());
        obj.put("nrProducts",p.getNrProducts());
        obj.put("nrProcesses",p.getNrProcesses());
        obj.put("nrDisjunctiveResources",p.getNrDisjunctiveResources());
        obj.put("nrCumulativeResources",p.getNrCumulativeResources());
        obj.put("nrOrders",p.getNrOrders());
        obj.put("nrJobs",p.getNrJobs());
        obj.put("nrTasks",p.getNrTasks());
        return obj;
    }
    private JSONArray products(){
        JSONArray res = new JSONArray();
        for(Product p:base.getListProduct()){
            JSONObject obj = product(p);
            res.put(obj);
        }
        return res;
    }

    private JSONObject product(Product p){
        JSONObject obj = new JSONObject();
        obj.put("name",p.getName());
        obj.put("defaultProcess",p.getDefaultProcess().getName());
        return obj;
    }
    private JSONArray processes(){
        JSONArray res = new JSONArray();
        for(Process p:base.getListProcess()){
            JSONObject obj = process(p);
            res.put(obj);
        }
        return res;
    }

    private JSONObject process(Process p){
        JSONObject obj = new JSONObject();
        obj.put("name",p.getName());
        obj.put("noOverlap",p.getNoOverlap());
        return obj;
    }
    private JSONArray disjunctiveResources(){
        JSONArray res = new JSONArray();
        for(DisjunctiveResource r:base.getListDisjunctiveResource()){
            JSONObject obj = disjunctiveResource(r);
            res.put(obj);
        }
        return res;
    }

    private JSONObject disjunctiveResource(DisjunctiveResource r){
        JSONObject obj = new JSONObject();
        obj.put("name",r.getName());
        return obj;
    }
    private JSONArray cumulativeResources(){
        JSONArray res = new JSONArray();
        for(CumulativeResource r:base.getListCumulativeResource()){
            JSONObject obj = cumulativeResource(r);
            res.put(obj);
        }
        return res;
    }

    private JSONObject cumulativeResource(CumulativeResource r){
        JSONObject obj = new JSONObject();
        obj.put("name",r.getName());
        return obj;
    }


    private JSONArray processSteps(){
        JSONArray res = new JSONArray();
        for(ProcessStep p:base.getListProcessStep()){
            JSONObject obj = processStep(p);
            res.put(obj);
        }
        return res;
    }

    private JSONObject processStep(ProcessStep p){
        JSONObject obj = new JSONObject();
        obj.put("name",p.getName());
        obj.put("process",p.getProcess().getName());
        obj.put("durationFixed",p.getDurationFixed());
        obj.put("durationPerUnit",p.getDurationPerUnit());
        return obj;
    }

    private JSONArray processSequences(){
        JSONArray res = new JSONArray();
        for(ProcessSequence p:base.getListProcessSequence()){
            JSONObject obj = processSequence(p);
             res.put(obj);
        }
        return res;
    }

    private JSONObject processSequence(ProcessSequence p){
        JSONObject obj = new JSONObject();
        obj.put("name",p.getName());
        obj.put("before",p.getBefore().getName());
        obj.put("after",p.getAfter().getName());
        obj.put("sequenceType",p.getSequenceType().toString());
        obj.put("offset",p.getOffset());
        return obj;
    }

    private JSONArray resourceNeeds(){
        JSONArray res = new JSONArray();
        for(ResourceNeed p:base.getListResourceNeed()){
            JSONObject obj = resourceNeed(p);
            res.put(obj);
        }
        return res;
    }

    private JSONObject resourceNeed(ResourceNeed p){
        JSONObject obj = new JSONObject();
        obj.put("name",p.getName());
        obj.put("processStep",p.getProcessStep().getName());
        obj.put("disjunctiveResource",p.getDisjunctiveResource().getName());
        return obj;
    }
    private JSONArray cumulativeNeeds(){
        JSONArray res = new JSONArray();
        for(CumulativeNeed p:base.getListCumulativeNeed()){
            JSONObject obj = cumulativeNeed(p);
            res.put(obj);
        }
        return res;
    }

    private JSONObject cumulativeNeed(CumulativeNeed p){
        JSONObject obj = new JSONObject();
        obj.put("name",p.getName());
        obj.put("processStep",p.getProcessStep().getName());
        obj.put("cumulativeResource",p.getCumulativeResource().getName());
        obj.put("demand",p.getDemand());
        return obj;
    }
    private JSONArray cumulativeProfiles(){
        JSONArray res = new JSONArray();
        for(CumulativeProfile p:base.getListCumulativeProfile()){
            JSONObject obj = cumulativeProfile(p);
           res.put(obj);
        }
        return res;
    }

    private JSONObject cumulativeProfile(CumulativeProfile p){
        JSONObject obj = new JSONObject();
        obj.put("name",p.getName());
        obj.put("from",p.getFrom());
        obj.put("fromDate",p.getFromDate().toString());
        obj.put("cumulativeResource",p.getCumulativeResource().getName());
        obj.put("capacity",p.getCapacity());
        return obj;
    }

    /*
    schedule elements
     */
    private JSONArray orders(){
        JSONArray res = new JSONArray();
        for(Order p:base.getListOrder()){
            JSONObject obj = order(p);
             res.put(obj);
        }
        return res;
    }

    private JSONObject order(Order p){
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
        return obj;
    }
    private JSONArray jobs(){
        JSONArray res = new JSONArray();
        for(Job p:base.getListJob()){
            JSONObject obj = job(p);
            res.put(obj);
        }
        return res;
    }

    private JSONObject job(Job p){
        JSONObject obj = new JSONObject();
        obj.put("name",p.getName());
        obj.put("process",p.getProcess().getName());
        obj.put("order",p.getOrder().getName());
        obj.put("noOverlap",p.getNoOverlap());
        return obj;
    }
    private JSONArray tasks(){
        JSONArray res = new JSONArray();
        for(Task p:base.getListTask()){
            JSONObject obj = task(p);
            res.put(obj);
        }
        return res;
    }

    private JSONObject task(Task p){
        JSONObject obj = new JSONObject();
        obj.put("name",p.getName());
        obj.put("job",p.getJob().getName());
        obj.put("processStep",p.getProcessStep().getName());
        obj.put("duration",p.getDuration());
        return obj;
    }

    private JSONArray wips(){
        JSONArray res = new JSONArray();
        for(WiP w:base.getListWiP()){
            JSONObject obj = wip(w);
            res.put(obj);
        }
        return res;
    }

    private JSONObject wip(WiP w){
        JSONObject obj = new JSONObject();
        obj.put("name",w.getName());
        obj.put("disjunctiveResource",w.getDisjunctiveResource().getName());
        obj.put("duration",w.getDuration());
        obj.put("start",w.getStart());
        obj.put("startDate",w.getStartDate().toString());
        obj.put("end",w.getEnd());
        obj.put("endDate",w.getEndDate().toString());
        return obj;

    }

    private JSONArray downtimes(){
        JSONArray res = new JSONArray();
        for(Downtime d:base.getListDowntime()){
            JSONObject obj = downtime(d);
            res.put(obj);
        }
        return res;
    }

    private JSONObject downtime(Downtime d){
        JSONObject obj = new JSONObject();
        obj.put("name",d.getName());
        obj.put("disjunctiveResource",d.getDisjunctiveResource().getName());
        obj.put("duration",d.getDuration());
        obj.put("start",d.getStart());
        obj.put("end",d.getEnd());
        obj.put("startDate",d.getStartDate().toString());
        obj.put("endDate",d.getEndDate().toString());
        return obj;
    }


    /*
    solution elements
     */

    private JSONArray solverRuns(){
        JSONArray res = new JSONArray();
        for(SolverRun run:base.getListSolverRun()){
            JSONObject obj = solverRun(run);
            res.put(obj);
        }
        return res;
    }

    private JSONObject solverRun(SolverRun run){
        JSONObject obj = new JSONObject();
        obj.put("name",run.getName());
        obj.put("label",run.getLabel());
        obj.put("description",run.getDescription());
        obj.put("modelType",run.getModelType().toString());
        obj.put("solverBackend",run.getSolverBackend().toString());
        obj.put("objectiveType",run.getObjectiveType().toString());
        obj.put("enforceReleaseDate",run.getEnforceReleaseDate());
        obj.put("enforceDueDate",run.getEnforceDueDate());
        obj.put("enforceCumulative",run.getEnforceCumulative());
        obj.put("enforceWip",run.getEnforceWip());
        obj.put("enforceDowntime",run.getEnforceDowntime());
        obj.put("enforceSetup",run.getEnforceSetup());
        obj.put("enforceTransportTime",run.getEnforceTransportTime());
        obj.put("relaxSequence",run.getRelaxSequence());
        obj.put("addSameOrder",run.getAddSameOrder());
        obj.put("weightMakespan",run.getWeightMakespan());
        obj.put("weightFlowtime",run.getWeightFlowtime());
        obj.put("weightLateness",run.getWeightLateness());
        obj.put("weightEarliness",run.getWeightEarliness());
        obj.put("timeout",run.getTimeout());
        obj.put("nrThreads",run.getNrThreads());
        obj.put("seed",run.getSeed());
        obj.put("removeSolution",run.getRemoveSolution());
        obj.put("produceReport",run.getProduceReport());
        obj.put("producePDF",run.getProducePDF());
        obj.put("solverStatus",run.getSolverStatus().toString());
        obj.put("time",run.getTime());
        return obj;
    }

    private JSONArray sols(){
        JSONArray res = new JSONArray();
        for(Solution sol:base.getListSolution()){
            JSONObject obj = solution(sol);

            res.put(obj);
        }
        return res;
    }

    private JSONObject solution(Solution sol){
        JSONObject obj = new JSONObject();
        obj.put("name",sol.getName());
        obj.put("solverRun",sol.getSolverRun().getName());
        obj.put("objectiveValue",sol.getObjectiveValue());
        obj.put("solverStatus",sol.getSolverStatus().toString());
        obj.put("bound",sol.getBound());
        obj.put("gapPercent",sol.getGapPercent());
        obj.put("makespan",sol.getMakespan());
        obj.put("flowtime",sol.getFlowtime());
        obj.put("totalLateness",sol.getTotalLateness());
        obj.put("maxLateness",sol.getMaxLateness());
        obj.put("nrLate",sol.getNrLate());
        obj.put("weightedLateness",sol.getWeightedLateness());
        obj.put("totalEarliness",sol.getTotalEarliness());
        obj.put("maxEarliness",sol.getMaxEarliness());
        obj.put("nrEarly",sol.getNrEarly());
        obj.put("weightedEarliness",sol.getWeightedEarliness());
        obj.put("percentEarly",sol.getPercentEarly());
        obj.put("percentLate",sol.getPercentLate());
        obj.put("duration",sol.getDuration());
        obj.put("start",sol.getStart());
        obj.put("end",sol.getEnd());
        obj.put("startDate",sol.getStartDate().toString());
        obj.put("endDate",sol.getEndDate().toString());
        obj.put("totalWaitBefore",sol.getTotalWaitBefore());
        obj.put("totalWaitAfter",sol.getTotalWaitAfter());
        obj.put("maxWaitBefore",sol.getMaxWaitBefore());
        obj.put("maxWaitAfter",sol.getMaxWaitAfter());
        return obj;

    }

    private JSONArray jobAssignments(){
        JSONArray res = new JSONArray();
        for(JobAssignment ja:base.getListJobAssignment()){
            JSONObject jObj = jobAssignment(ja);

            res.put(jObj);
        }
        return res;
    }

    private JSONObject jobAssignment(JobAssignment ja){
        JSONObject jObj = new JSONObject();
        jObj.put("name",ja.getName());
        jObj.put("solution",ja.getSolution().getName());
        jObj.put("job",ja.getJob().getName());
        jObj.put("late",ja.getLate());
        jObj.put("early",ja.getEarly());
        jObj.put("start",ja.getStart());
        jObj.put("end",ja.getEnd());
        jObj.put("duration",ja.getDuration());
        jObj.put("startDate",ja.getStartDate().toString());
        jObj.put("endDate",ja.getEndDate().toString());
        return jObj;
    }

    private JSONArray taskAssignments(){
        JSONArray res = new JSONArray();
        for(TaskAssignment ta:base.getListTaskAssignment()){
            JSONObject tObj = taskAssignment(ta);
            res.put(tObj);
        }
        return res;
    }

    private JSONObject taskAssignment(TaskAssignment ta){
        JSONObject tObj = new JSONObject();
        tObj.put("name",ta.getName());
        if (ta.getDisjunctiveResource()!=null) {
            tObj.put("disjunctiveResource", ta.getDisjunctiveResource().getName());
        }
        tObj.put("duration",ta.getDuration());
        tObj.put("start",ta.getStart());
        tObj.put("end",ta.getEnd());
        tObj.put("startDate",ta.getStartDate().toString());
        tObj.put("endDate",ta.getEndDate().toString());
        tObj.put("task",ta.getTask().getName());
        tObj.put("jobAssignment",ta.getJobAssignment().getName());
        tObj.put("waitBefore",ta.getWaitBefore());
        tObj.put("waitAfter",ta.getWaitAfter());
        return tObj;

    }

 }

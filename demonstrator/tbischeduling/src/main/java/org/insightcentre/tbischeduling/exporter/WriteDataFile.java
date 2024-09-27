package org.insightcentre.tbischeduling.exporter;

import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class WriteDataFile {
    Scenario base;
    public WriteDataFile(Scenario base, File selected){
        this.base = base;
        try{
            PrintWriter out = new PrintWriter(selected);
            JSONObject root = createJSON();
            out.println(root.toString(2));
            out.close();
            base.setDirty(false);
        } catch(IOException e){
            severe("Cannot write file "+selected.toString()+", exception "+e.getMessage());
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

        root.put("order",orders());
        root.put("job",jobs());
        root.put("task",tasks());



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
            obj.put("process",p.getProcess().getName());
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

    /*
    schedule elements
     */
    private JSONArray orders(){
        JSONArray res = new JSONArray();
        for(Order p:base.getListOrder()){
            JSONObject obj = new JSONObject();
            obj.put("name",p.getName());
            obj.put("product",p.getProduct().getName());
            obj.put("qty",p.getQty());
            obj.put("due",p.getDue());
//            obj.put("dueDate",p.getDueDate().toString());
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
            res.put(obj);
        }
        return res;
    }


    /*
    solution elements
     */

    private JSONArray sols(){
        JSONArray res = new JSONArray();
        for(Solution sol:base.getListSolution()){
            JSONObject obj = new JSONObject();
            obj.put("name",sol.getName());
            obj.put("objectiveValue",sol.getObjectiveValue());
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
            tObj.put("task",ta.getTask().getName());
//            tObj.put("resource",ta.getResource().getName());
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

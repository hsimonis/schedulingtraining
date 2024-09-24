package org.insightcentre.tbischeduling.importer;

import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;

import static org.insightcentre.tbischeduling.importer.Reset.resetAll;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class ReadDataFile {
    Scenario base;
    public ReadDataFile(Scenario base, File selected){
        this.base = base;
        try{
            resetAll(base);
            String contents = new String(Files.readAllBytes(selected.toPath()));
            JSONObject root = new JSONObject(contents);

            // read the different fields of data, create a hashtable from name to Object
            Hashtable<String, Problem> problemHash = readProblems(root);
            Hashtable<String, DisjunctiveResource> disjunctiveResourceHash = readDisjunctiveResources(root);
            Hashtable<String, CumulativeResource> cumulativeResourceHash = readCumulativeResources(root);
            Hashtable<String, Process> processHash = readProcesses(root);
            Hashtable<String, Product> productHash = readProducts(root,processHash);
            Hashtable<String, ProcessStep> processStepHash = readProcessSteps(root,processHash);
            Hashtable<String, ProcessSequence> processSequenceHash = readProcessSequences(root,processStepHash);
            Hashtable<String, ResourceNeed> resourceNeedHash = readResourceNeeds(root,processStepHash,disjunctiveResourceHash);

            Hashtable<String, Order> orderHash = readOrders(root,productHash);
            Hashtable<String, Job> jobHash = readJobs(root,orderHash,processHash);
            Hashtable<String, Task> taskHash = readTasks(root,jobHash,processStepHash);

            Hashtable<String, Solution> solutionHash = readSolutions(root);
            Hashtable<String, JobAssignment> jobAssignmentHash = readJobAssignments(root,jobHash,solutionHash);
            Hashtable<String, TaskAssignment> taskAssignmentHash = readTaskAssignments(root,jobAssignmentHash,taskHash);


        } catch(IOException e){
            severe("Cannot read file "+selected+", exception "+e.getMessage());
        }
    }

    private Hashtable<String,Problem> readProblems(JSONObject root){
        Hashtable<String,Problem> res = new Hashtable<>();
        if (root.has("problem")){
            JSONArray arr = root.getJSONArray("problem");
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                Boolean flag = item.getBoolean("timePointsAsDate");
                Problem p  = new Problem(base);
                p.setName(name);
                res.put(name,p);

            }
        }
        return res;
    }
    private Hashtable<String,DisjunctiveResource> readDisjunctiveResources(JSONObject root){
        Hashtable<String,DisjunctiveResource> res = new Hashtable<>();
        if (root.has("disjunctiveResource")){
            JSONArray arr = root.getJSONArray("disjunctiveResource");
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                DisjunctiveResource p  = new DisjunctiveResource(base);
                p.setName(name);
                res.put(name,p);

            }
        }
        return res;
    }
    private Hashtable<String,CumulativeResource> readCumulativeResources(JSONObject root){
        Hashtable<String,CumulativeResource> res = new Hashtable<>();
        if (root.has("cumulativeResource")){
            JSONArray arr = root.getJSONArray("cumulativeResource");
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                CumulativeResource p  = new CumulativeResource(base);
                p.setName(name);
                res.put(name,p);

            }
        }
        return res;
    }
    private Hashtable<String,Process> readProcesses(JSONObject root){
        Hashtable<String,Process> res = new Hashtable<>();
        if (root.has("process")){
            JSONArray arr = root.getJSONArray("process");
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                Process p  = new Process(base);
                p.setName(name);
                res.put(name,p);

            }
        }
        return res;
    }
    private Hashtable<String,Product> readProducts(JSONObject root,Hashtable<String,Process> processHash){
        Hashtable<String,Product> res = new Hashtable<>();
        if (root.has("product")){
            JSONArray arr = root.getJSONArray("product");
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                String pName = item.getString("process");
                Process process = processHash.get(pName);
                if (process == null){
                    severe("No such process "+pName+" for product "+name);
                }
                Product p  = new Product(base);
                p.setName(name);
                p.setProcess(process);
                res.put(name,p);

            }
        }
        return res;
    }

    private Hashtable<String,ProcessStep> readProcessSteps(JSONObject root,Hashtable<String,Process> processHash){
        Hashtable<String,ProcessStep> res = new Hashtable<>();
        if (root.has("processStep")){
            JSONArray arr = root.getJSONArray("processStep");
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                String pName = item.getString("process");
                int durationFixed = item.getInt("durationFixed");
                int durationPerUnit = item.getInt("durationPerUnit");
                Process process = processHash.get(pName);
                if (process == null){
                    severe("No such process "+pName+" for processStep "+name);
                }
                ProcessStep ps  = new ProcessStep(base);
                ps.setName(name);
                ps.setProcess(process);
                ps.setDurationFixed(durationFixed);
                ps.setDurationPerUnit(durationPerUnit);
                res.put(name,ps);

            }
        }
        return res;
    }
    private Hashtable<String,ProcessSequence> readProcessSequences(JSONObject root,Hashtable<String,ProcessStep> processStepHash){
        Hashtable<String,ProcessSequence> res = new Hashtable<>();
        if (root.has("processSequence")){
            JSONArray arr = root.getJSONArray("processSequence");
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                String beforeName = item.getString("before");
                String afterName = item.getString("after");
                String sequenceType = item.getString("sequenceType");
                int offset = item.getInt("offset");
                ProcessStep before = processStepHash.get(beforeName);
                ProcessStep after = processStepHash.get(afterName);
                SequenceType type = findSequenceType(sequenceType);
                if (before == null){
                    severe("No such process step"+beforeName+" for processSequence "+name);
                }
                if (after == null){
                    severe("No such process step"+afterName+" for processSequence "+name);
                }
                if (type == null){
                    severe("No such sequence type"+sequenceType+" for processSequence "+name);
                }
                ProcessSequence ps  = new ProcessSequence(base);
                ps.setName(name);
                ps.setBefore(before);
                ps.setAfter(after);
                ps.setSequenceType(type);
                ps.setOffset(offset);
                res.put(name,ps);

            }
        }
        return res;
    }

    private Hashtable<String,ResourceNeed> readResourceNeeds(JSONObject root,
                                                             Hashtable<String,ProcessStep> processStepHash,
                                                             Hashtable<String,DisjunctiveResource> disjunctiveResourceHash){
        Hashtable<String,ResourceNeed> res = new Hashtable<>();
        if (root.has("resourceNeed")){
            JSONArray arr = root.getJSONArray("resourceNeed");
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                String psName = item.getString("processStep");
                String rName = item.getString("disjunctiveResource");
                ProcessStep ps = processStepHash.get(psName);
                DisjunctiveResource r = disjunctiveResourceHash.get(rName);
                if (ps == null){
                    severe("No such process step"+psName+" for resourceNeed "+name);
                }
                if (r == null){
                    severe("No such disjunctiveResource"+rName+" for processSequence "+name);
                }
                ResourceNeed rn  = new ResourceNeed(base);
                rn.setName(name);
                rn.setProcessStep(ps);
                rn.setDisjunctiveResource(r);
                res.put(name,rn);

            }
        }
        return res;
    }

    private Hashtable<String,Order> readOrders(JSONObject root,Hashtable<String,Product> productHash){
        Hashtable<String,Order> res = new Hashtable<>();
        if (root.has("order")){
            JSONArray arr = root.getJSONArray("order");
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                String pName = item.getString("product");
                int qty = item.getInt("qty");
                int due = item.getInt("due");
                Product product = productHash.get(pName);
                if (product == null){
                    severe("No such product "+pName+" for order "+name);
                }
                Order ord  = new Order(base);
                ord.setName(name);
                ord.setProduct(product);
                ord.setQty(qty);
                ord.setDue(due);
                res.put(name,ord);

            }
        } else {
            severe("No orders");
        }
        return res;
    }

    private Hashtable<String,Job> readJobs(JSONObject root,Hashtable<String,Order> orderHash,Hashtable<String,Process> processHash){
        Hashtable<String,Job> res = new Hashtable<>();
        if (root.has("job")){
            JSONArray arr = root.getJSONArray("job");
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                String oName = item.getString("order");
                String pName = item.getString("process");
                Order order = orderHash.get(oName);
                Process process = processHash.get(pName);
                if (order == null){
                    severe("No such order "+oName+" for job "+name);
                }
                if (process == null){
                    severe("No such process "+pName+" for job "+name);
                }
                Job j  = new Job(base);
                j.setName(name);
                j.setOrder(order);
                j.setProcess(process);
                res.put(name,j);

            }
        } else {
            severe("No jobs");
        }
        return res;
    }

    private Hashtable<String,Task> readTasks(JSONObject root,Hashtable<String,Job> jobHash,Hashtable<String,ProcessStep> processStepHash){
        Hashtable<String,Task> res = new Hashtable<>();
        if (root.has("task")){
            JSONArray arr = root.getJSONArray("task");
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                String jName = item.getString("job");
                String pName = item.getString("processStep");
                Job j = jobHash.get(jName);
                ProcessStep processStep = processStepHash.get(pName);
                if (j == null){
                    severe("No such order "+jName+" for task "+name);
                }
                if (processStep == null){
                    severe("No such processStep "+pName+" for task "+name);
                }
                Task t = new Task(base);
                t.setName(name);
                t.setJob(j);
                t.setProcessStep(processStep);
                res.put(name,t);

            }
        } else {
            severe("No tasks");
        }
        return res;
    }

    private Hashtable<String,Solution> readSolutions(JSONObject root){
        Hashtable<String,Solution> res = new Hashtable<>();
        if (root.has("solution") && root.getJSONArray("solution").length() >0){
            JSONArray arr = root.getJSONArray("solution");
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                int objectiveValue = item.getInt("objectiveValue");
                Solution s  = new Solution(base);
                s.setName(name);
                s.setObjectiveValue(objectiveValue);
                res.put(name,s);

            }
        } else {
            info("No solutions");
        }
        return res;
    }

    private Hashtable<String,JobAssignment> readJobAssignments(JSONObject root,Hashtable<String,Job> jobHash,Hashtable<String,Solution> solutionHash){
        Hashtable<String,JobAssignment> res = new Hashtable<>();
        if (root.has("jobAssignment") && root.getJSONArray("jobAssignment").length()>0){
            JSONArray arr = root.getJSONArray("jobAssignment");
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                String jName = item.getString("job");
                String sName = item.getString("solution");
                int start = item.getInt("start");
                int end = item.getInt("end");
                int duration = item.getInt("duration");
                Job j = jobHash.get(jName);
                Solution s = solutionHash.get(sName);
                if (j == null){
                    severe("No such job "+jName+" for jobAssignment "+name);
                }
                if (s == null){
                    severe("No such solution "+sName+" for jobAssignment "+name);
                }
                JobAssignment ja  = new JobAssignment(base);
                ja.setName(name);
                ja.setJob(j);
                ja.setSolution(s);
                ja.setStart(start);
                ja.setEnd(end);
                ja.setDuration(duration);
                res.put(name,ja);

            }
        } else {
            info("No jobAssignments");
        }
        return res;
    }

    private Hashtable<String,TaskAssignment> readTaskAssignments(JSONObject root,
                                                                 Hashtable<String,JobAssignment> jobAssignmentHash,
                                                                 Hashtable<String,Task> taskHash){
        Hashtable<String,TaskAssignment> res = new Hashtable<>();
        if (root.has("taskAssignment") && root.getJSONArray("taskAssignment").length()>0){
            JSONArray arr = root.getJSONArray("taskAssignment");
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                String name = item.getString("name");
                String jName = item.getString("jobAssignment");
                String tName = item.getString("task");
                int start = item.getInt("start");
                int end = item.getInt("end");
                int duration = item.getInt("duration");
                JobAssignment ja = jobAssignmentHash.get(jName);
                Task t = taskHash.get(tName);
                if (ja == null){
                    severe("No such jobAssignment "+jName+" for taskAssignment "+name);
                }
                if (t == null){
                    severe("No such task "+tName+" for taskAssignment "+name);
                }
                TaskAssignment ta  = new TaskAssignment(base);
                ta.setName(name);
                ta.setJobAssignment(ja);
                ta.setTask(t);
                ta.setStart(start);
                ta.setEnd(end);
                ta.setDuration(duration);
                res.put(name,ta);

            }
        } else {
            info("No taskAssignments");
        }
        return res;
    }


    private SequenceType findSequenceType(String name){
        if ("EndBeforeStart".equals(name)) {
            return SequenceType.EndBeforeStart;
        }
        return null;
    }

}

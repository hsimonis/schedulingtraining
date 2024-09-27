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

import static org.insightcentre.tbischeduling.datamodel.ModelType.*;
import static org.insightcentre.tbischeduling.datamodel.ObjectiveType.*;
import static org.insightcentre.tbischeduling.datamodel.Severity.*;
import static org.insightcentre.tbischeduling.datamodel.SolverBackend.*;
import static org.insightcentre.tbischeduling.datamodel.SolverStatus.*;
import static org.insightcentre.tbischeduling.importer.CreateData.summarizeProblem;
import static org.insightcentre.tbischeduling.importer.Reset.resetAll;
import static org.insightcentre.tbischeduling.logging.LogShortcut.*;

public class ReadDataFile {
    Scenario base;
    public ReadDataFile(Scenario base, File selected){
        this.base = base;
        try{
            resetAll(base);
            String contents = new String(Files.readAllBytes(selected.toPath()));
            JSONObject root = new JSONObject(contents);

            if (!root.has("version")){
                inputError("root","root","version","n/a","File does not have version field",Fatal);
            }
            double version = root.getDouble("version");
            // compare to version number defined in minimalData()
            if (version < base.getDataFileVersionNumber()){
                inputError("root","root","version",String.format("%f",version),"File version outdated",Fatal);
            }

            // read the different fields of data, create a hashtable from name to Object
            Hashtable<String, InputError> inputErrorHash = readInputErrors(root);
            Hashtable<String, Problem> problemHash = readProblems(root);
            Hashtable<String, DisjunctiveResource> disjunctiveResourceHash = readDisjunctiveResources(root);
            Hashtable<String, CumulativeResource> cumulativeResourceHash = readCumulativeResources(root);
            Hashtable<String, Process> processHash = readProcesses(root);
            Hashtable<String, Product> productHash = readProducts(root,processHash);
            Hashtable<String, ProcessStep> processStepHash = readProcessSteps(root,processHash);
            Hashtable<String, ProcessSequence> processSequenceHash = readProcessSequences(root,processStepHash);
            Hashtable<String, ResourceNeed> resourceNeedHash = readResourceNeeds(root,processStepHash,disjunctiveResourceHash);
            Hashtable<String, CumulativeNeed> cumulativeNeedHash = readCumulativeNeeds(root,processStepHash,cumulativeResourceHash);
            Hashtable<String, CumulativeProfile> cumulativeProfileHash = readCumulativeProfiles(root,cumulativeResourceHash);

            Hashtable<String, Order> orderHash = readOrders(root,productHash);
            Hashtable<String, Job> jobHash = readJobs(root,orderHash,processHash);
            Hashtable<String, Task> taskHash = readTasks(root,jobHash,processStepHash);

            Hashtable<String, SolverRun> solverRunHash = readSolverRuns(root);
            Hashtable<String, Solution> solutionHash = readSolutions(root,solverRunHash);
            Hashtable<String, JobAssignment> jobAssignmentHash = readJobAssignments(root,jobHash,solutionHash);
            Hashtable<String, TaskAssignment> taskAssignmentHash = readTaskAssignments(root,jobAssignmentHash,taskHash);

            summarizeProblem(base);


        } catch(IOException e){
            severe("Cannot read file "+selected+", exception "+e.getMessage());
        }
    }

    private Hashtable<String,InputError> readInputErrors(JSONObject root){
        Hashtable<String,InputError> res = new Hashtable<>();
        String key = "inputError";
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","classDesc","item","field","value","description","severity"});
                String name = item.getString("name");
                String classDesc = item.getString("classDesc");
                String iName= item.getString("item");
                String field = item.getString("field");
                String value = item.getString("value");
                String description = item.getString("description");
                String severity = item.getString("severity");
                InputError e  = new InputError(base);
                e.setName(name);
                e.setClassDesc(classDesc);
                e.setItem(iName);
                e.setField(field);
                e.setValue(value);
                e.setDescription(description);
                e.setSeverity(toSeverity(severity));
                res.put(name,e);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }
    private Hashtable<String,Problem> readProblems(JSONObject root){
        String key = "problem";
        Hashtable<String,Problem> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","timePointsAsDate","nrProducts","nrProcesses",
                        "nrDisjunctiveResources","nrCumulativeResources","nrOrders","nrJobs","nrTasks"});
                String name = item.getString("name");
                Boolean timePointsAsDate = item.getBoolean("timePointsAsDate");
                int nrProducts = item.getInt("nrProducts");
                int nrProcesses = item.getInt("nrProcesses");
                int nrDisjunctiveResources = item.getInt("nrDisjunctiveResources");
                int nrCumulativeResources = item.getInt("nrCumulativeResources");
                int nrOrders = item.getInt("nrOrders");
                int nrJobs = item.getInt("nrJobs");
                int nrTasks = item.getInt("nrTasks");
                Problem p  = new Problem(base);
                p.setName(name);
                p.setTimePointsAsDate(timePointsAsDate);
                p.setNrProducts(nrProducts);
                p.setNrProcesses(nrProcesses);
                p.setNrDisjunctiveResources(nrDisjunctiveResources);
                p.setNrCumulativeResources(nrCumulativeResources);
                p.setNrOrders(nrOrders);
                p.setNrJobs(nrJobs);
                p.setNrTasks(nrTasks);
                res.put(name,p);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }
    private Hashtable<String,DisjunctiveResource> readDisjunctiveResources(JSONObject root){
        String key = "disjunctiveResource";
        Hashtable<String,DisjunctiveResource> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name"});
                String name = item.getString("name");
                DisjunctiveResource p  = new DisjunctiveResource(base);
                p.setName(name);
                res.put(name,p);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }
    private Hashtable<String,CumulativeResource> readCumulativeResources(JSONObject root){
        String key = "cumulativeResource";
        Hashtable<String,CumulativeResource> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name"});
                String name = item.getString("name");
                CumulativeResource p  = new CumulativeResource(base);
                p.setName(name);
                res.put(name,p);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }
    private Hashtable<String,Process> readProcesses(JSONObject root){
        String key = "process";
        Hashtable<String,Process> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name"});
                String name = item.getString("name");
                Process p  = new Process(base);
                p.setName(name);
                res.put(name,p);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }
    private Hashtable<String,Product> readProducts(JSONObject root,Hashtable<String,Process> processHash){
        String key = "product";
        Hashtable<String,Product> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","process"});
                String name = item.getString("name");
                String pName = item.getString("process");
                Process process = processHash.get(pName);
                if (process == null){
                    inputError(key,name,"process",pName,"The required object does not exist",Fatal);
                }
                Product p  = new Product(base);
                p.setName(name);
                p.setProcess(process);
                res.put(name,p);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }

    private Hashtable<String,ProcessStep> readProcessSteps(JSONObject root,Hashtable<String,Process> processHash){
        String key = "processStep";
        Hashtable<String,ProcessStep> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","process","durationFixed","durationPerUnit"});
                String name = item.getString("name");
                String pName = item.getString("process");
                int durationFixed = item.getInt("durationFixed");
                int durationPerUnit = item.getInt("durationPerUnit");
                Process process = processHash.get(pName);
                if (process == null){
                    inputError(key,name,"process",pName,"The required object does not exist",Fatal);
                }
                ProcessStep ps  = new ProcessStep(base);
                ps.setName(name);
                ps.setProcess(process);
                ps.setDurationFixed(durationFixed);
                ps.setDurationPerUnit(durationPerUnit);
                res.put(name,ps);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }
    private Hashtable<String,ProcessSequence> readProcessSequences(JSONObject root,Hashtable<String,ProcessStep> processStepHash){
        String key = "processSequence";
        Hashtable<String,ProcessSequence> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","before","after","sequenceType","offset"});
                String name = item.getString("name");
                String beforeName = item.getString("before");
                String afterName = item.getString("after");
                String sequenceType = item.getString("sequenceType");
                int offset = item.getInt("offset");
                ProcessStep before = processStepHash.get(beforeName);
                ProcessStep after = processStepHash.get(afterName);
                SequenceType type = toSequenceType(sequenceType);
                if (before == null){
                    inputError(key,name,"before",beforeName,"The required object does not exist",Fatal);
                }
                if (after == null){
                    inputError(key,name,"after",afterName,"The required object does not exist",Fatal);
                }
                if (type == null){
                    inputError(key,name,"sequenceType",sequenceType,"This is not a valid SequenceType",Fatal);
                }
                ProcessSequence ps  = new ProcessSequence(base);
                ps.setName(name);
                ps.setBefore(before);
                ps.setAfter(after);
                ps.setSequenceType(type);
                ps.setOffset(offset);
                res.put(name,ps);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }

    private Hashtable<String,ResourceNeed> readResourceNeeds(JSONObject root,
                                                             Hashtable<String,ProcessStep> processStepHash,
                                                             Hashtable<String,DisjunctiveResource> disjunctiveResourceHash){
        String key = "resourceNeed";
        Hashtable<String,ResourceNeed> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","processStep","disjunctiveResource"});
                String name = item.getString("name");
                String psName = item.getString("processStep");
                String rName = item.getString("disjunctiveResource");
                ProcessStep ps = processStepHash.get(psName);
                DisjunctiveResource r = disjunctiveResourceHash.get(rName);
                if (ps == null){
                    inputError(key,name,"processStep",psName,"The required object does not exist",Fatal);
                }
                if (r == null){
                    inputError(key,name,"disjunctiveResource",rName,"The required object does not exist",Fatal);
                }
                ResourceNeed rn  = new ResourceNeed(base);
                rn.setName(name);
                rn.setProcessStep(ps);
                rn.setDisjunctiveResource(r);
                res.put(name,rn);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }
    private Hashtable<String,CumulativeNeed> readCumulativeNeeds(JSONObject root,
                                                                 Hashtable<String,ProcessStep> processStepHash,
                                                                 Hashtable<String,CumulativeResource> cumulativeResourceHash){
        String key = "cumulativeNeed";
        Hashtable<String,CumulativeNeed> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","processStep","cumulativeResource","demand"});
                String name = item.getString("name");
                String psName = item.getString("processStep");
                String rName = item.getString("cumulativeResource");
                int demand = item.getInt("demand");
                ProcessStep ps = processStepHash.get(psName);
                CumulativeResource r = cumulativeResourceHash.get(rName);
                if (ps == null){
                    inputError(key,name,"processStep",psName,"The required object does not exist",Fatal);
                }
                if (r == null){
                    inputError(key,name,"cumulativeResource",rName,"The required object does not exist",Fatal);
                }
                CumulativeNeed cn  = new CumulativeNeed(base);
                cn.setName(name);
                cn.setProcessStep(ps);
                cn.setCumulativeResource(r);
                cn.setDemand(demand);
                res.put(name,cn);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }
    private Hashtable<String,CumulativeProfile> readCumulativeProfiles(JSONObject root,
                                                                 Hashtable<String,CumulativeResource> cumulativeResourceHash){
        String key = "cumulativeProfile";
        Hashtable<String,CumulativeProfile> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","from","cumulativeResource","capacity"});
                String name = item.getString("name");
                int from = item.getInt("from");
                String rName = item.getString("cumulativeResource");
                int capacity = item.getInt("capacity");
                CumulativeResource r = cumulativeResourceHash.get(rName);
                if (r == null){
                    inputError(key,name,"cumulativeResource",rName,"The required object does not exist",Fatal);
                }
                CumulativeProfile cn  = new CumulativeProfile(base);
                cn.setName(name);
                cn.setFrom(from);
                cn.setCumulativeResource(r);
                cn.setCapacity(capacity);
                res.put(name,cn);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }

    private Hashtable<String,Order> readOrders(JSONObject root,Hashtable<String,Product> productHash){
        String key = "order";
        Hashtable<String,Order> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","product","qty","due"});
                String name = item.getString("name");
                String pName = item.getString("product");
                int qty = item.getInt("qty");
                int due = item.getInt("due");
                Product product = productHash.get(pName);
                if (product == null){
                    inputError(key,name,"product",pName,"The required object does not exist",Fatal);
                }
                Order ord  = new Order(base);
                ord.setName(name);
                ord.setProduct(product);
                ord.setQty(qty);
                ord.setDue(due);
                res.put(name,ord);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }

    private Hashtable<String,Job> readJobs(JSONObject root,Hashtable<String,Order> orderHash,Hashtable<String,Process> processHash){
        String key = "job";
        Hashtable<String,Job> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","order","process"});
                String name = item.getString("name");
                String oName = item.getString("order");
                String pName = item.getString("process");
                Order order = orderHash.get(oName);
                Process process = processHash.get(pName);
                if (order == null){
                    inputError(key,name,"order",oName,"The required object does not exist",Fatal);
                }
                if (process == null){
                    inputError(key,name,"process",pName,"The required object does not exist",Fatal);
                }
                Job j  = new Job(base);
                j.setName(name);
                j.setOrder(order);
                j.setProcess(process);
                res.put(name,j);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }

    private Hashtable<String,Task> readTasks(JSONObject root,Hashtable<String,Job> jobHash,Hashtable<String,ProcessStep> processStepHash){
        String key = "task";
        Hashtable<String,Task> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","job","processStep"});
                String name = item.getString("name");
                String jName = item.getString("job");
                String pName = item.getString("processStep");
                Job j = jobHash.get(jName);
                ProcessStep processStep = processStepHash.get(pName);
                if (j == null){
                    inputError(key,name,"job",jName,"The required object does not exist",Fatal);
                }
                if (processStep == null){
                    inputError(key,name,"processStep",pName,"The required object does not exist",Fatal);
                }
                Task t = new Task(base);
                t.setName(name);
                t.setJob(j);
                t.setProcessStep(processStep);
                res.put(name,t);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }

    private Hashtable<String,SolverRun> readSolverRuns(JSONObject root){
        String key="solverRun";
        Hashtable<String,SolverRun> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","label","description","modelType","solverBackend",
                        "objectiveType","enforceReleaseDate","enforceDueDate","timeout","nrThreads","seed",
                        "removeSolution","solverStatus","time"});
                String name = item.getString("name");
                String label = item.getString("label");
                String description = item.getString("description");
                String modelType = item.getString("modelType");
                String solverBackend = item.getString("solverBackend");
                String objectiveType = item.getString("objectiveType");
                boolean enforceReleaseDate = item.getBoolean("enforceReleaseDate");
                boolean enforceDueDate = item.getBoolean("enforceDueDate");
                int timeout = item.getInt("timeout");
                int nrThreads = item.getInt("nrThreads");
                int seed = item.getInt("seed");
                boolean removeSolution = item.getBoolean("removeSolution");
                String solverStatus = item.getString("solverStatus");
                double time = item.getDouble("time");
                SolverRun s  = new SolverRun(base);
                s.setName(name);
                s.setLabel(label);
                s.setDescription(description);
                s.setModelType(toModelType(modelType));
                s.setSolverBackend(toSolverBackend(solverBackend));
                s.setObjectiveType(toObjectiveType(objectiveType));
                s.setEnforceReleaseDate(enforceReleaseDate);
                s.setEnforceDueDate(enforceDueDate);
                s.setTimeout(timeout);
                s.setNrThreads(nrThreads);
                s.setSeed(seed);
                s.setRemoveSolution(removeSolution);
                s.setSolverStatus(toSolverStatus(solverStatus));
                s.setTime(time);
                res.put(name,s);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }

    private Hashtable<String,Solution> readSolutions(JSONObject root,Hashtable<String,SolverRun> solverRunHash){
        String key="solution";
        Hashtable<String,Solution> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","solverRun","objectiveValue","solverStatus","bound","gap",
                        "makespan","flowtime",
                        "totalLateness","maxLateness","weightedLateness",
                        "totalEarliness","maxEarliness","weightedEarliness"});
                String name = item.getString("name");
                String solverRun = item.getString("solverRun");
                int objectiveValue = item.getInt("objectiveValue");
                String solverStatus = item.getString("solverStatus");
                double bound = item.getDouble("bound");
                double gap = item.getDouble("gap");
                int makespan= item.getInt("makespan");
                int flowtime= item.getInt("flowtime");
                int totalLateness= item.getInt("totalLateness");
                int maxLateness= item.getInt("maxLateness");
                double weightedLateness= item.getDouble("weightedLateness");
                int totalEarliness = item.getInt("totalEarliness");
                int maxEarliness = item.getInt("maxEarliness");
                double weightedEarliness= item.getDouble("weightedEarliness");
                SolverRun sr = solverRunHash.get(solverRun);
                if (sr ==null){
                    inputError(key,name,"solverRun",solverRun,"The required object does not exist",Fatal);

                }
                Solution s  = new Solution(base);
                s.setName(name);
                s.setSolverRun(sr);
                s.setObjectiveValue(objectiveValue);
                s.setSolverStatus(toSolverStatus(solverStatus));
                s.setBound(bound);
                s.setGap(gap);
                s.setMakespan(makespan);
                s.setFlowtime(flowtime);
                s.setTotalLateness(totalLateness);
                s.setMaxLateness(maxLateness);
                s.setWeightedLateness(weightedLateness);
                s.setTotalEarliness(totalEarliness);
                s.setMaxEarliness(maxEarliness);
                s.setWeightedEarliness(weightedEarliness);
                res.put(name,s);

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }

    private Hashtable<String,JobAssignment> readJobAssignments(JSONObject root,Hashtable<String,Job> jobHash,Hashtable<String,Solution> solutionHash){
        String key = "jobAssignment";
        Hashtable<String,JobAssignment> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","job","solution","start","end","duration"});
                String name = item.getString("name");
                String jName = item.getString("job");
                String sName = item.getString("solution");
                int start = item.getInt("start");
                int end = item.getInt("end");
                int duration = item.getInt("duration");
                Job j = jobHash.get(jName);
                Solution s = solutionHash.get(sName);
                if (j == null){
                    inputError(key,name,"job",jName,"The required object does not exist",Fatal);
                }
                if (s == null){
                    inputError(key,name,"solution",sName,"The required object does not exist",Fatal);
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
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }

    private Hashtable<String,TaskAssignment> readTaskAssignments(JSONObject root,
                                                                 Hashtable<String,JobAssignment> jobAssignmentHash,
                                                                 Hashtable<String,Task> taskHash){
        String key = "taskAssignment";
        Hashtable<String,TaskAssignment> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                requireFields(key,i,item,new String[]{"name","jobAssignment","task","start","end","duration"});
                String name = item.getString("name");
                String jName = item.getString("jobAssignment");
                String tName = item.getString("task");
                int start = item.getInt("start");
                int end = item.getInt("end");
                int duration = item.getInt("duration");
                JobAssignment ja = jobAssignmentHash.get(jName);
                Task t = taskHash.get(tName);
                if (ja == null){
                    inputError(key,name,"jobAssignment",jName,"The required object does not exist",Fatal);
                }
                if (t == null){
                    inputError(key,name,"task",tName,"The required object does not exist",Fatal);
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
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }


    private SequenceType toSequenceType(String name){
        if ("EndBeforeStart".equals(name)) {
            return SequenceType.EndBeforeStart;
        }
        return null;
    }

    private Severity toSeverity(String name){
        return switch (name) {
            case "Fatal" -> Fatal;
            case "Critical" -> Critical;
            case "Major" -> Major;
            case "Minor" -> Minor;
            default -> Minor;
        };
    }
    private ModelType toModelType(String name){
        return switch (name) {
            case "CPO" -> CPO;
            case "MiniZincDiffn" -> MiniZincDiffn;
            case "MiniZincTask" -> MiniZincTask;
            default -> null;
        };
    }
    private SolverBackend toSolverBackend(String name){
        return switch (name) {
            case "Chuffed" -> Chuffed;
            case "Gecode" -> Gecode;
            case "CPSat" -> CPSat;
            case "Cplex" -> Cplex;
            default -> null;
        };
    }
    private SolverStatus toSolverStatus(String name){
        return switch (name) {
            case "Optimal" -> Optimal;
            case "Solution" -> Solution;
            case "Infeasible" -> Infeasible;
            case "Unknown" -> Unknown;
            case "Error" -> Error;
            default -> null;
        };
    }
    private ObjectiveType toObjectiveType(String name){
        return switch (name) {
            case "Makespan" -> Makespan;
            case "Flowtime" -> Flowtime;
            case "TotalEarliness" -> TotalEarliness;
            case "MaxEarliness" -> MaxEarliness;
            case "WeightedEarliness" -> WeightedEarliness;
            case "TotalLateness" -> TotalLateness;
            case "MaxLateness" -> MaxLateness;
            case "WeightedLateness" -> WeightedLateness;
            case "Hybrid" -> Hybrid;
            default -> null;
        };
    }

    int errNr=1;
    private InputError inputError(String classDesc,String item,String field,String value,String description,Severity severity){
        InputError res = new InputError(base);
        res.setName("Err"+errNr++);
        res.setClassDesc(classDesc);
        res.setItem(item);
        res.setField(field);
        res.setValue(value);
        res.setDescription(description);
        res.setSeverity(severity);
        if (res.getSeverity()==Fatal || res.getSeverity()==Critical){
            severe("Class "+classDesc+" item "+item+" field "+field+" value "+value+": "+description);
        } else if (res.getSeverity()==Major){
            warning("Class "+classDesc+" item "+item+" field "+field+" value "+value+": "+description);
        } else {
            info("Class "+classDesc+" item "+item+" field "+field+" value "+value+": "+description);

        }
        return res;
    }

    private void requireFields(String classDesc,int i,JSONObject obj,String[] keys){
        for(String key:keys){
            if (!obj.has(key)){
                inputError(classDesc,"entry nr "+i,key,"n/a","Class requires field "+key,Fatal);
            }
        }
    }

}

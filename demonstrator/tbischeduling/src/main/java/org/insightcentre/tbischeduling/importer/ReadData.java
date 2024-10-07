package org.insightcentre.tbischeduling.importer;

import framework.types.DateTime;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.implementedsolver.CheckSolutions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Hashtable;

import static org.insightcentre.tbischeduling.datamodel.ModelType.*;
import static org.insightcentre.tbischeduling.datamodel.ObjectiveType.*;
import static org.insightcentre.tbischeduling.datamodel.Severity.*;
import static org.insightcentre.tbischeduling.datamodel.SolverBackend.*;
import static org.insightcentre.tbischeduling.datamodel.SolverStatus.*;
import static org.insightcentre.tbischeduling.importer.CreateData.summarizeProblem;
import static org.insightcentre.tbischeduling.importer.Reset.resetAll;
import static org.insightcentre.tbischeduling.logging.LogShortcut.*;
import static org.insightcentre.tbischeduling.utilities.TypeConverters.*;

public class ReadData {
    JSONObject root;
    Scenario base;

    // DateTime format produced by DateTime.toString() when writing them
    String dateTimeFormat = "d/M/yyyy HH:mm";
    public ReadData(Scenario base, File selected) {
        this.base = base;
        try {
            resetAll(base);
            String contents = new String(Files.readAllBytes(selected.toPath()));
            root = new JSONObject(contents);
        } catch(IOException e){
            severe("Cannot read file "+selected+", exception "+e.getMessage());
        }
        read(root);
    }
    public ReadData(Scenario base, String text) {
        this.base = base;
        this.root = new JSONObject(text);
        read(root);
    }
    public ReadData(Scenario base, JSONObject root) {
        this.base = base;
        this.root = root;
        read(root);
    }


    private void read(JSONObject root){
        resetAll(base);

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

        Hashtable<String, Order> orderHash = readOrders(root,productHash,processHash);
        Hashtable<String, Job> jobHash = readJobs(root,orderHash,processHash);
        Hashtable<String, Task> taskHash = readTasks(root,jobHash,processStepHash);
        Hashtable<String, WiP> wipHash = readWiPs(root,disjunctiveResourceHash);
        Hashtable<String, Downtime> downtimeHash = readDowntimes(root,disjunctiveResourceHash);

        Hashtable<String, SolverRun> solverRunHash = readSolverRuns(root);
        Hashtable<String, Solution> solutionHash = readSolutions(root,solverRunHash);
        Hashtable<String, JobAssignment> jobAssignmentHash = readJobAssignments(root,jobHash,solutionHash);
        Hashtable<String, TaskAssignment> taskAssignmentHash = readTaskAssignments(root,jobAssignmentHash,
                taskHash,disjunctiveResourceHash);

        summarizeProblem(base);
        new CheckSolutions(base,base.getListSolution());
        info("File read, "+base.getListInputError().size()+" input error(s), "+base.getListSolutionError().size()+" solution errors");


    }

    private Hashtable<String,InputError> readInputErrors(JSONObject root){
        Hashtable<String,InputError> res = new Hashtable<>();
        String key = "inputError";
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                if (requireFields(key,i,item,new String[]{"name","classDesc","item","field","value",
                        "description","severity"})) {
                    String name = item.getString("name");
                    String classDesc = item.getString("classDesc");
                    String iName = item.getString("item");
                    String field = item.getString("field");
                    String value = item.getString("value");
                    String description = item.getString("description");
                    String severity = item.getString("severity");
                    InputError e = new InputError(base);
                    e.setName(name);
                    e.setClassDesc(classDesc);
                    e.setItem(iName);
                    e.setField(field);
                    e.setValue(value);
                    e.setDescription(description);
                    e.setSeverity(toSeverity(severity));
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, e);
                }

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
                if (requireFields(key,i,item,new String[]{"label","timePointsAsDate","nrProducts","nrProcesses",
                        "nrDisjunctiveResources","nrCumulativeResources","nrOrders","nrJobs","nrTasks"})) {
                    String label = item.getString("label");
                    Boolean timePointsAsDate = item.getBoolean("timePointsAsDate");
                    int nrProducts = item.getInt("nrProducts");
                    int nrProcesses = item.getInt("nrProcesses");
                    int nrDisjunctiveResources = item.getInt("nrDisjunctiveResources");
                    int nrCumulativeResources = item.getInt("nrCumulativeResources");
                    int nrOrders = item.getInt("nrOrders");
                    int nrJobs = item.getInt("nrJobs");
                    int nrTasks = item.getInt("nrTasks");
                    Problem p = new Problem(base);
                    String name = "ReadFromFile";
                    p.setName(name);
                    p.setLabel(label);
                    p.setTimePointsAsDate(timePointsAsDate);
                    p.setNrProducts(nrProducts);
                    p.setNrProcesses(nrProcesses);
                    p.setNrDisjunctiveResources(nrDisjunctiveResources);
                    p.setNrCumulativeResources(nrCumulativeResources);
                    p.setNrOrders(nrOrders);
                    p.setNrJobs(nrJobs);
                    p.setNrTasks(nrTasks);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, p);
                }

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
                if (requireFields(key,i,item,new String[]{"name"})) {
                    String name = item.getString("name");
                    DisjunctiveResource p = new DisjunctiveResource(base);
                    p.setName(name);
                    p.setShortName(name);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, p);
                }

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
                if (requireFields(key,i,item,new String[]{"name"})) {
                    String name = item.getString("name");
                    CumulativeResource p = new CumulativeResource(base);
                    p.setName(name);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, p);
                }

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
                if (requireFields(key,i,item,new String[]{"name"})) {
                    String name = item.getString("name");
                    Process p = new Process(base);
                    p.setName(name);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, p);
                }

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
                if (requireFields(key,i,item,new String[]{"name","defaultProcess"})) {
                    String name = item.getString("name");
                    String pName = item.getString("defaultProcess");
                    Process process = processHash.get(pName);
                    if (process == null) {
                        inputError(key, name, "defaultProcess", pName, "The required object does not exist", Fatal);
                    }
                    Product p = new Product(base);
                    p.setName(name);
                    p.setDefaultProcess(process);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, p);
                }

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
                if (requireFields(key,i,item,new String[]{"name","process","durationFixed","durationPerUnit"})) {
                    String name = item.getString("name");
                    String pName = item.getString("process");
                    int durationFixed = item.getInt("durationFixed");
                    int durationPerUnit = item.getInt("durationPerUnit");
                    Process process = processHash.get(pName);
                    if (process == null) {
                        inputError(key, name, "process", pName, "The required object does not exist", Fatal);
                    }
                    if (durationFixed < 0){
                        inputError(key,name,"durationFixed",String.format("%d",durationFixed),"Value cannot be negative",Fatal);
                    }
                    if (durationPerUnit < 0){
                        inputError(key,name,"durationPerUnit",String.format("%d",durationPerUnit),"Value cannot be negative",Fatal);
                    }
                    ProcessStep ps = new ProcessStep(base);
                    ps.setName(name);
                    ps.setProcess(process);
                    ps.setDurationFixed(durationFixed);
                    ps.setDurationPerUnit(durationPerUnit);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, ps);
                }

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
                if (requireFields(key,i,item,new String[]{"name","before","after","sequenceType","offset"})) {
                    String name = item.getString("name");
                    String beforeName = item.getString("before");
                    String afterName = item.getString("after");
                    String sequenceType = item.getString("sequenceType");
                    int offset = item.getInt("offset");
                    ProcessStep before = processStepHash.get(beforeName);
                    ProcessStep after = processStepHash.get(afterName);
                    SequenceType type = toSequenceType(sequenceType);
                    if (before == null) {
                        inputError(key, name, "before", beforeName, "The required object does not exist", Fatal);
                    }
                    if (after == null) {
                        inputError(key, name, "after", afterName, "The required object does not exist", Fatal);
                    }
                    if (type == null) {
                        inputError(key, name, "sequenceType", sequenceType, "This is not a valid SequenceType", Fatal);
                    }
                    ProcessSequence ps = new ProcessSequence(base);
                    ps.setName(name);
                    ps.setBefore(before);
                    ps.setAfter(after);
                    ps.setSequenceType(type);
                    ps.setOffset(offset);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, ps);
                }

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
                if (requireFields(key,i,item,new String[]{"name","processStep","disjunctiveResource"})) {
                    String name = item.getString("name");
                    String psName = item.getString("processStep");
                    String rName = item.getString("disjunctiveResource");
                    ProcessStep ps = processStepHash.get(psName);
                    DisjunctiveResource r = disjunctiveResourceHash.get(rName);
                    if (ps == null) {
                        inputError(key, name, "processStep", psName, "The required object does not exist", Fatal);
                    }
                    if (r == null) {
                        inputError(key, name, "disjunctiveResource", rName, "The required object does not exist", Fatal);
                    }
                    ResourceNeed rn = new ResourceNeed(base);
                    rn.setName(name);
                    rn.setProcessStep(ps);
                    rn.setDisjunctiveResource(r);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, rn);
                }

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
                if (requireFields(key,i,item,new String[]{"name","processStep","cumulativeResource","demand"})) {
                    String name = item.getString("name");
                    String psName = item.getString("processStep");
                    String rName = item.getString("cumulativeResource");
                    int demand = item.getInt("demand");
                    ProcessStep ps = processStepHash.get(psName);
                    CumulativeResource r = cumulativeResourceHash.get(rName);
                    if (ps == null) {
                        inputError(key, name, "processStep", psName, "The required object does not exist", Fatal);
                    }
                    if (r == null) {
                        inputError(key, name, "cumulativeResource", rName, "The required object does not exist", Fatal);
                    }
                    if (demand <= 0 ){
                        inputError(key,name,"demand",String.format("%d",demand),"Value must be positive",Fatal);
                    }
                    CumulativeNeed cn = new CumulativeNeed(base);
                    cn.setName(name);
                    cn.setProcessStep(ps);
                    cn.setCumulativeResource(r);
                    cn.setDemand(demand);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, cn);
                }

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
                if(requireFields(key,i,item,new String[]{"name","from","fromDate","cumulativeResource","capacity"})) {
                    String name = item.getString("name");
                    int from = item.getInt("from");
                    DateTime fromDate = readDateTime(item.getString("fromDate"),from);
                    String rName = item.getString("cumulativeResource");
                    int capacity = item.getInt("capacity");
                    CumulativeResource r = cumulativeResourceHash.get(rName);
                    if (r == null) {
                        inputError(key, name, "cumulativeResource", rName, "The required object does not exist", Fatal);
                    }
                    if (capacity < 0 ){
                        inputError(key,name,"capacity",String.format("%d",capacity),"Value cannot be negative",Fatal);
                    }
                    CumulativeProfile cn = new CumulativeProfile(base);
                    cn.setName(name);
                    cn.setFrom(from);
                    cn.setFromDate(fromDate);
                    cn.setCumulativeResource(r);
                    cn.setCapacity(capacity);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, cn);
                }

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }

    private DateTime readDateTime(String string,int value){
        try{
            return DateTime.parseDateTime(string,dateTimeFormat);
        } catch(Exception e){
            severe("Cannot parse DateTime from "+string);
            return toDateTime(base,value);
        }
    }

    private Hashtable<String,Order> readOrders(JSONObject root,Hashtable<String,Product> productHash,
                                               Hashtable<String,Process> processHash){
        String key = "order";
        Hashtable<String,Order> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                if (requireFields(key,i,item,new String[]{"name","product","qty","due","release","dueDate","releaseDate",
                        "latenessWeight","earlinessWeight"})) {
                    String name = item.getString("name");
                    String pName = item.getString("product");
                    String qName = item.getString("process");
                    int qty = item.getInt("qty");
                    int due = item.getInt("due");
                    int release = item.getInt("release");
                    DateTime dueDate = readDateTime(item.getString("dueDate"),due);
                    DateTime releaseDate = readDateTime(item.getString("releaseDate"),release);
                    double latenessWeight = item.getDouble("latenessWeight");
                    double earlinessWeight = item.getDouble("latenessWeight");
                    Product product = productHash.get(pName);
                    Process process = processHash.get(qName);
                    if (product == null) {
                        inputError(key, name, "product", pName, "The required object does not exist", Fatal);
                    }
                    if (process == null) {
                        inputError(key, name, "process", qName, "The required object does not exist", Fatal);
                    }
                    if (qty <= 0 ){
                        inputError(key,name,"qty",String.format("%d",qty),"Value must be positive",Fatal);
                    }
                    if (release > due){
                        inputError(key,name,"release",String.format("%d",release),"Release date must before due date",Fatal);
                    }
                    if (latenessWeight < 0.0){
                        inputError(key,name,"latenessWeight",String.format("%f",latenessWeight),"Value cannot be negative",Fatal);
                    }
                    if (earlinessWeight < 0.0){
                        inputError(key,name,"earlinessWeight",String.format("%f",earlinessWeight),"Value cannot be negative",Fatal);
                    }
                    Order ord = new Order(base);
                    ord.setName(name);
                    ord.setProduct(product);
                    ord.setProcess(process);
                    ord.setQty(qty);
                    ord.setDue(due);
                    ord.setDueDate(dueDate);
                    ord.setReleaseDate(releaseDate);
                    ord.setRelease(release);
                    ord.setLatenessWeight(latenessWeight);
                    ord.setEarlinessWeight(earlinessWeight);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, ord);
                }

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }

    private Hashtable<String,Job> readJobs(JSONObject root,Hashtable<String,Order> orderHash,Hashtable<String,Process> processHash){
        String key = "job";
        int jobNr = 0;
        Hashtable<String,Job> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                if (requireFields(key,i,item,new String[]{"name","order","process"})) {
                    String name = item.getString("name");
                    String oName = item.getString("order");
                    String pName = item.getString("process");
                    Order order = orderHash.get(oName);
                    Process process = processHash.get(pName);
                    if (order == null) {
                        inputError(key, name, "order", oName, "The required object does not exist", Fatal);
                    }
                    if (process == null) {
                        inputError(key, name, "process", pName, "The required object does not exist", Fatal);
                    }
                    if (order != null && process != order.getProcess()){
                        inputError(key,name,"process",pName,"Process is different from order process",Fatal);
                    }
                    Job j = new Job(base);
                    j.setName(name);
                    j.setNr(jobNr++);
                    j.setOrder(order);
                    j.setProcess(process);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, j);
                }

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }

    private Hashtable<String,Task> readTasks(JSONObject root,Hashtable<String,Job> jobHash,Hashtable<String,ProcessStep> processStepHash){
        String key = "task";
        int taskNr = 0;
        Hashtable<String,Task> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                if (requireFields(key,i,item,new String[]{"name","job","processStep","duration"})) {
                    String name = item.getString("name");
                    String jName = item.getString("job");
                    String pName = item.getString("processStep");
                    int duration = item.getInt("duration");
                    Job j = jobHash.get(jName);
                    ProcessStep processStep = processStepHash.get(pName);
                    if (j == null) {
                        inputError(key, name, "job", jName, "The required object does not exist", Fatal);
                    }
                    if (processStep == null) {
                        inputError(key, name, "processStep", pName, "The required object does not exist", Fatal);
                    }
                    Task t = new Task(base);
                    t.setName(name);
                    t.setShortName(name);
                    t.setNr(taskNr++);
                    t.setJob(j);
                    t.setProcessStep(processStep);
                    t.setDuration(duration);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, t);
                }

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }

    private Hashtable<String,WiP> readWiPs(JSONObject root,
                                           Hashtable<String,DisjunctiveResource> disjunctiveResourceHash){
        String key = "wip";
        Hashtable<String,WiP> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                if (requireFields(key,i,item,new String[]{"name","disjunctiveResource","start","end","duration","startDate","endDate"})) {
                    String name = item.getString("name");
                    String rName = item.getString("disjunctiveResource");
                    int duration = item.getInt("duration");
                    int start = item.getInt("start");
                    int end = item.getInt("end");
                    DateTime startDate = readDateTime(item.getString("startDate"),start);
                    DateTime endDate = readDateTime(item.getString("endDate"),end);
                    DisjunctiveResource r = disjunctiveResourceHash.get(rName);
                    if (r == null) {
                        inputError(key, name, "disjunctiveResource", rName, "The required object does not exist", Fatal);
                    }
                    WiP w = new WiP(base);
                    w.setName(name);
                    w.setDisjunctiveResource(r);
                    w.setStart(start);
                    w.setEnd(end);
                    w.setDuration(duration);
                    w.setStartDate(startDate);
                    w.setEndDate(endDate);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, w);
                }

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }
    private Hashtable<String,Downtime> readDowntimes(JSONObject root,
                                           Hashtable<String,DisjunctiveResource> disjunctiveResourceHash){
        String key = "downtime";
        Hashtable<String,Downtime> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                if (requireFields(key,i,item,new String[]{"name","disjunctiveResource","start","end","duration","startDate","endDate"})) {
                    String name = item.getString("name");
                    String rName = item.getString("disjunctiveResource");
                    int start = item.getInt("start");
                    int end = item.getInt("end");
                    int duration = item.getInt("duration");
                    DateTime startDate = readDateTime(item.getString("startDate"),start);
                    DateTime endDate = readDateTime(item.getString("endDate"),end);
                    DisjunctiveResource r = disjunctiveResourceHash.get(rName);
                    if (r == null) {
                        inputError(key, name, "disjunctiveResource", rName, "The required object does not exist", Fatal);
                    }
                    Downtime d = new Downtime(base);
                    d.setName(name);
                    d.setDisjunctiveResource(r);
                    d.setStart(start);
                    d.setEnd(end);
                    d.setDuration(duration);
                    d.setStartDate(startDate);
                    d.setEndDate(endDate);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, d);
                }

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
                if (requireFields(key,i,item,new String[]{"name","label","description","modelType","solverBackend",
                        "objectiveType","enforceReleaseDate","enforceDueDate","timeout","nrThreads","seed",
                        "removeSolution","solverStatus","time"})) {
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
                    SolverRun s = new SolverRun(base);
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
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, s);
                }

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
                if (requireFields(key,i,item,new String[]{"name","solverRun","objectiveValue","solverStatus","bound","gap",
                        "makespan","flowtime",
                        "totalLateness","maxLateness","weightedLateness",
                        "totalEarliness","maxEarliness","weightedEarliness","start","end","duration","startDate","endDate"})) {
                    String name = item.getString("name");
                    String solverRun = item.getString("solverRun");
                    int objectiveValue = item.getInt("objectiveValue");
                    String solverStatus = item.getString("solverStatus");
                    double bound = item.getDouble("bound");
                    double gap = item.getDouble("gap");
                    int makespan = item.getInt("makespan");
                    int flowtime = item.getInt("flowtime");
                    int totalLateness = item.getInt("totalLateness");
                    int maxLateness = item.getInt("maxLateness");
                    int nrLate = item.getInt("nrLate");
                    double weightedLateness = item.getDouble("weightedLateness");
                    int totalEarliness = item.getInt("totalEarliness");
                    int maxEarliness = item.getInt("maxEarliness");
                    int nrEarly = item.getInt("nrEarly");
                    double weightedEarliness = item.getDouble("weightedEarliness");
                    double percentEarly = item.getDouble("percentEarly");
                    double percentLate = item.getDouble("percentLate");
                    int start = item.getInt("start");
                    int end = item.getInt("end");
                    int duration = item.getInt("duration");
                    DateTime startDate = readDateTime(item.getString("startDate"),start);
                    DateTime endDate = readDateTime(item.getString("endDate"),end);

                    SolverRun sr = solverRunHash.get(solverRun);
                    if (sr == null) {
                        inputError(key, name, "solverRun", solverRun, "The required object does not exist", Fatal);

                    }
                    Solution s = new Solution(base);
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
                    s.setNrLate(nrLate);
                    s.setWeightedLateness(weightedLateness);
                    s.setTotalEarliness(totalEarliness);
                    s.setMaxEarliness(maxEarliness);
                    s.setNrEarly(nrEarly);
                    s.setWeightedEarliness(weightedEarliness);
                    s.setPercentEarly(percentEarly);
                    s.setPercentLate(percentLate);
                    s.setStart(start);
                    s.setEnd(end);
                    s.setDuration(duration);
                    s.setStartDate(startDate);
                    s.setEndDate(endDate);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, s);
                }

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
                if (requireFields(key,i,item,new String[]{"name","job","solution","start","end","duration","late","early","startDate","endDate"})) {
                    String name = item.getString("name");
                    String sName = item.getString("solution");
                    String jName = item.getString("job");
                    int start = item.getInt("start");
                    int end = item.getInt("end");
                    int duration = item.getInt("duration");
                    DateTime startDate = readDateTime(item.getString("startDate"),start);
                    DateTime endDate = readDateTime(item.getString("endDate"),end);
                    int late = item.getInt("late");
                    int early = item.getInt("early");
                    Job j = jobHash.get(jName);
                    Solution s = solutionHash.get(sName);
                    if (j == null) {
                        inputError(key, name, "job", jName, "The required object does not exist", Fatal);
                    }
                    if (s == null) {
                        inputError(key, name, "solution", sName, "The required object does not exist", Fatal);
                    }
                    JobAssignment ja = new JobAssignment(base);
                    ja.setName(name);
                    ja.setJob(j);
                    ja.setSolution(s);
                    ja.setStart(start);
                    ja.setEnd(end);
                    ja.setDuration(duration);
                    ja.setStartDate(startDate);
                    ja.setEndDate(endDate);
                    ja.setEarly(early);
                    ja.setLate(late);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, ja);
                }

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
    }

    private Hashtable<String,TaskAssignment> readTaskAssignments(JSONObject root,
                                                                 Hashtable<String,JobAssignment> jobAssignmentHash,
                                                                 Hashtable<String,Task> taskHash,
                                                                 Hashtable<String,DisjunctiveResource> disjHash){
        String key = "taskAssignment";
        Hashtable<String,TaskAssignment> res = new Hashtable<>();
        if (root.has(key)){
            JSONArray arr = root.getJSONArray(key);
            for(int i=0;i<arr.length();i++){
                JSONObject item = arr.getJSONObject(i);
                if (requireFields(key,i,item,new String[]{"name","jobAssignment","task","disjunctiveResource",
                        "start","end","duration","startDate","endDate"})) {
                    String name = item.getString("name");
                    String jName = item.getString("jobAssignment");
                    String tName = item.getString("task");
                    String rName = item.getString("disjunctiveResource");
                    int start = item.getInt("start");
                    int end = item.getInt("end");
                    DateTime startDate = readDateTime(item.getString("startDate"),start);
                    DateTime endDate = readDateTime(item.getString("endDate"),end);
                    int duration = item.getInt("duration");
                    JobAssignment ja = jobAssignmentHash.get(jName);
                    Task t = taskHash.get(tName);
                    DisjunctiveResource r = disjHash.get(rName);
                    if (ja == null) {
                        inputError(key, name, "jobAssignment", jName, "The required object does not exist", Fatal);
                    }
                    if (t == null) {
                        inputError(key, name, "task", tName, "The required object does not exist", Fatal);
                    }
                    if (r == null) {
                        inputError(key, name, "disjunctiveResource", rName, "The required object does not exist", Fatal);

                    }
                    TaskAssignment ta = new TaskAssignment(base);
                    ta.setName(name);
                    ta.setJobAssignment(ja);
                    ta.setTask(t);
                    ta.setDisjunctiveResource(r);
                    ta.setStart(start);
                    ta.setEnd(end);
                    ta.setStartDate(startDate);
                    ta.setEndDate(endDate);
                    ta.setDuration(duration);
                    if (res.get(name) != null) {
                        inputError(key, name, "name", name, "Duplicate name", Fatal);
                    }
                    res.put(name, ta);
                }

            }
        } else {
            inputError("root","root",key,"n/a","File does not have "+key+" data",Fatal);
        }
        return res;
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

    /*
    returns true if all fields are present
     */
    private boolean requireFields(String classDesc,int i,JSONObject obj,String[] keys){
        boolean res = true;
        for(String key:keys){
            if (!obj.has(key)){
                inputError(classDesc,"entry nr "+i,key,"n/a","Class requires field "+key,Fatal);
                res = false;
            }
        }
        return res;
    }

}

package org.insightcentre.tbischeduling.datamodel;

import framework.AbstractXMLLoader;
import framework.ApplicationDatasetInterface;
import framework.ApplicationObjectInterface;
import framework.gui.StatusException;
import framework.types.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.*;

public class XMLLoader extends AbstractXMLLoader {
    Scenario base = null;

    public XMLLoader() {
    }
    public ApplicationDatasetInterface loadXML(File filename) {
        try {
            SAXParserFactory configFactory = SAXParserFactory.newInstance();
            SAXParser configParser = configFactory.newSAXParser();
            ConfigHandler configHandler = new ConfigHandler();
            Phase2Handler phase2Handler = new Phase2Handler();
            configParser.parse(filename, configHandler);
            configParser.parse(filename, phase2Handler);
            base = configHandler.getScenario();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        base.setIdNr(base.lastIdNr()+1);
        return base;
    }

public DifferenceType getDifferenceType(String attributeName,
                               Attributes attributes) {
        String e = attributes.getValue(attributeName);
        if (e == null) {
            System.out.println("DifferenceType"+": "+attributeName);
            return null;
        } else {
            return DifferenceType.valueOf(e);
        }
    }
public WarningType getWarningType(String attributeName,
                               Attributes attributes) {
        String e = attributes.getValue(attributeName);
        if (e == null) {
            System.out.println("WarningType"+": "+attributeName);
            return null;
        } else {
            return WarningType.valueOf(e);
        }
    }
public SequenceType getSequenceType(String attributeName,
                               Attributes attributes) {
        String e = attributes.getValue(attributeName);
        if (e == null) {
            System.out.println("SequenceType"+": "+attributeName);
            return null;
        } else {
            return SequenceType.valueOf(e);
        }
    }
    public ApplicationDataset getApplicationDataset(String attributeName,
                               Attributes attributes) {
        return (ApplicationDataset) find(getId(attributeName,attributes));
    }

    public List<ApplicationDataset> getApplicationDatasetCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<ApplicationDataset> res = new ArrayList<ApplicationDataset>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((ApplicationDataset) find(id));
            }
        }
        return res;
    }

    public ApplicationDifference getApplicationDifference(String attributeName,
                               Attributes attributes) {
        return (ApplicationDifference) find(getId(attributeName,attributes));
    }

    public List<ApplicationDifference> getApplicationDifferenceCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<ApplicationDifference> res = new ArrayList<ApplicationDifference>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((ApplicationDifference) find(id));
            }
        }
        return res;
    }

    public ApplicationObject getApplicationObject(String attributeName,
                               Attributes attributes) {
        return (ApplicationObject) find(getId(attributeName,attributes));
    }

    public List<ApplicationObject> getApplicationObjectCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<ApplicationObject> res = new ArrayList<ApplicationObject>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((ApplicationObject) find(id));
            }
        }
        return res;
    }

    public ApplicationWarning getApplicationWarning(String attributeName,
                               Attributes attributes) {
        return (ApplicationWarning) find(getId(attributeName,attributes));
    }

    public List<ApplicationWarning> getApplicationWarningCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<ApplicationWarning> res = new ArrayList<ApplicationWarning>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((ApplicationWarning) find(id));
            }
        }
        return res;
    }

    public CumulativeResource getCumulativeResource(String attributeName,
                               Attributes attributes) {
        return (CumulativeResource) find(getId(attributeName,attributes));
    }

    public List<CumulativeResource> getCumulativeResourceCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<CumulativeResource> res = new ArrayList<CumulativeResource>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((CumulativeResource) find(id));
            }
        }
        return res;
    }

    public DisjunctiveResource getDisjunctiveResource(String attributeName,
                               Attributes attributes) {
        return (DisjunctiveResource) find(getId(attributeName,attributes));
    }

    public List<DisjunctiveResource> getDisjunctiveResourceCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<DisjunctiveResource> res = new ArrayList<DisjunctiveResource>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((DisjunctiveResource) find(id));
            }
        }
        return res;
    }

    public Job getJob(String attributeName,
                               Attributes attributes) {
        return (Job) find(getId(attributeName,attributes));
    }

    public List<Job> getJobCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<Job> res = new ArrayList<Job>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((Job) find(id));
            }
        }
        return res;
    }

    public JobAssignment getJobAssignment(String attributeName,
                               Attributes attributes) {
        return (JobAssignment) find(getId(attributeName,attributes));
    }

    public List<JobAssignment> getJobAssignmentCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<JobAssignment> res = new ArrayList<JobAssignment>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((JobAssignment) find(id));
            }
        }
        return res;
    }

    public Order getOrder(String attributeName,
                               Attributes attributes) {
        return (Order) find(getId(attributeName,attributes));
    }

    public List<Order> getOrderCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<Order> res = new ArrayList<Order>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((Order) find(id));
            }
        }
        return res;
    }

    public Problem getProblem(String attributeName,
                               Attributes attributes) {
        return (Problem) find(getId(attributeName,attributes));
    }

    public List<Problem> getProblemCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<Problem> res = new ArrayList<Problem>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((Problem) find(id));
            }
        }
        return res;
    }

    public Process getProcess(String attributeName,
                               Attributes attributes) {
        return (Process) find(getId(attributeName,attributes));
    }

    public List<Process> getProcessCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<Process> res = new ArrayList<Process>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((Process) find(id));
            }
        }
        return res;
    }

    public ProcessSequence getProcessSequence(String attributeName,
                               Attributes attributes) {
        return (ProcessSequence) find(getId(attributeName,attributes));
    }

    public List<ProcessSequence> getProcessSequenceCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<ProcessSequence> res = new ArrayList<ProcessSequence>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((ProcessSequence) find(id));
            }
        }
        return res;
    }

    public ProcessStep getProcessStep(String attributeName,
                               Attributes attributes) {
        return (ProcessStep) find(getId(attributeName,attributes));
    }

    public List<ProcessStep> getProcessStepCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<ProcessStep> res = new ArrayList<ProcessStep>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((ProcessStep) find(id));
            }
        }
        return res;
    }

    public Product getProduct(String attributeName,
                               Attributes attributes) {
        return (Product) find(getId(attributeName,attributes));
    }

    public List<Product> getProductCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<Product> res = new ArrayList<Product>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((Product) find(id));
            }
        }
        return res;
    }

    public ResourceNeed getResourceNeed(String attributeName,
                               Attributes attributes) {
        return (ResourceNeed) find(getId(attributeName,attributes));
    }

    public List<ResourceNeed> getResourceNeedCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<ResourceNeed> res = new ArrayList<ResourceNeed>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((ResourceNeed) find(id));
            }
        }
        return res;
    }

    public Scenario getScenario(String attributeName,
                               Attributes attributes) {
        return (Scenario) find(getId(attributeName,attributes));
    }

    public List<Scenario> getScenarioCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<Scenario> res = new ArrayList<Scenario>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((Scenario) find(id));
            }
        }
        return res;
    }

    public Solution getSolution(String attributeName,
                               Attributes attributes) {
        return (Solution) find(getId(attributeName,attributes));
    }

    public List<Solution> getSolutionCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<Solution> res = new ArrayList<Solution>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((Solution) find(id));
            }
        }
        return res;
    }

    public Task getTask(String attributeName,
                               Attributes attributes) {
        return (Task) find(getId(attributeName,attributes));
    }

    public List<Task> getTaskCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<Task> res = new ArrayList<Task>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((Task) find(id));
            }
        }
        return res;
    }

    public TaskAssignment getTaskAssignment(String attributeName,
                               Attributes attributes) {
        return (TaskAssignment) find(getId(attributeName,attributes));
    }

    public List<TaskAssignment> getTaskAssignmentCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<TaskAssignment> res = new ArrayList<TaskAssignment>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((TaskAssignment) find(id));
            }
        }
        return res;
    }

    private class ConfigHandler extends DefaultHandler {
        private int numNodes = 0;
        public Scenario getScenario() {
            return base;
        }

        public void startDocument() {
            System.out.println("Reading XML Document, Pass 1");
        }

        public void endDocument() {
            System.out.println("Pass 1 complete");
        }

        public void processingInstruction(String target, String data) {
            System.out.println("AssignableProcess target" + target + " data " + data);
        }
        public void startElement(String uri,
                                 String localname, String qname,
                                 Attributes attributes) {
            if (qname.equals("body")) {
            } else if (qname.equals("scenario")) {
                base = new Scenario(false,
                        getId("id", attributes),
                        getString("name", attributes, "dummy"),
                        getBoolean("valid",attributes,false)
                              );
            } else if (qname.equals("applicationDifference")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new ApplicationDifference(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getString("item",attributes,""),
                        null
                        ));
            } else if (qname.equals("applicationWarning")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new ApplicationWarning(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getString("attrString",attributes,""),
                        getString("classString",attributes,""),
                        getString("item",attributes,""),
                        getString("limit",attributes,""),
                        null
                        ));
            } else if (qname.equals("cumulativeResource")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new CumulativeResource(base,
                        id,
                        getString("name", attributes, "dummy")
                        ));
            } else if (qname.equals("disjunctiveResource")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new DisjunctiveResource(base,
                        id,
                        getString("name", attributes, "dummy")
                        ));
            } else if (qname.equals("job")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new Job(base,
                        id,
                        getString("name", attributes, "dummy"),
                        null,
                        null
                        ));
            } else if (qname.equals("jobAssignment")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new JobAssignment(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getInteger("duration",attributes,0),
                        getInteger("end",attributes,0),
                        getDateTime("endDate",attributes,"2011-01-01"),
                        null,
                        null,
                        getInteger("start",attributes,0),
                        getDateTime("startDate",attributes,"2011-01-01")
                        ));
            } else if (qname.equals("order")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new Order(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getInteger("due",attributes,0),
                        getInteger("dueDate",attributes,0),
                        null,
                        getInteger("qty",attributes,0)
                        ));
            } else if (qname.equals("problem")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new Problem(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getBoolean("timePointsAsDate",attributes,false)
                        ));
            } else if (qname.equals("process")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new Process(base,
                        id,
                        getString("name", attributes, "dummy")
                        ));
            } else if (qname.equals("processSequence")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new ProcessSequence(base,
                        id,
                        getString("name", attributes, "dummy"),
                        null,
                        null,
                        getInteger("offset",attributes,0),
                        null
                        ));
            } else if (qname.equals("processStep")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new ProcessStep(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getInteger("durationFixed",attributes,0),
                        getInteger("durationPerUnit",attributes,0),
                        null
                        ));
            } else if (qname.equals("product")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new Product(base,
                        id,
                        getString("name", attributes, "dummy"),
                        null
                        ));
            } else if (qname.equals("resourceNeed")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new ResourceNeed(base,
                        id,
                        getString("name", attributes, "dummy"),
                        null,
                        null
                        ));
            } else if (qname.equals("solution")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new Solution(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getInteger("objectiveValue",attributes,0)
                        ));
            } else if (qname.equals("task")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new Task(base,
                        id,
                        getString("name", attributes, "dummy"),
                        null,
                        null
                        ));
            } else if (qname.equals("taskAssignment")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new TaskAssignment(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getInteger("duration",attributes,0),
                        getInteger("end",attributes,0),
                        getDateTime("endDate",attributes,"2011-01-01"),
                        null,
                        getInteger("resource",attributes,0),
                        getInteger("start",attributes,0),
                        getDateTime("startDate",attributes,"2011-01-01"),
                        null
                        ));
            } else {
                System.out.println("Element Structure " + qname);
                numNodes++;
            }
        }
    }
    private class Phase2Handler extends DefaultHandler {
        private int numNodes = 0;
        public Scenario getScenario() {
            return base;
        }

        public void startDocument() {
            System.out.println("Reading XML Document, Pass 2");
        }

        public void endDocument() {
            System.out.println("Pass 2 complete");
        }

        public void processingInstruction(String target, String data) {
            System.out.println("AssignableProcess target" + target + " data " + data);
        }
        public void startElement(String uri,
                                 String localname, String qname,
                                 Attributes attributes) {
            if (qname.equals("body")) {
            } else if (qname.equals("scenario")) {
                assert (base != null);
                int id = getId("id", attributes);
                Scenario item = (Scenario) base;
            } else if (qname.equals("applicationDifference")) {
                assert (base != null);
                int id = getId("id", attributes);
                ApplicationDifference item = (ApplicationDifference) find(id);
                 item.setType(getDifferenceType("type",attributes));
            } else if (qname.equals("applicationWarning")) {
                assert (base != null);
                int id = getId("id", attributes);
                ApplicationWarning item = (ApplicationWarning) find(id);
                 item.setType(getWarningType("type",attributes));
            } else if (qname.equals("cumulativeResource")) {
                assert (base != null);
                int id = getId("id", attributes);
                CumulativeResource item = (CumulativeResource) find(id);
            } else if (qname.equals("disjunctiveResource")) {
                assert (base != null);
                int id = getId("id", attributes);
                DisjunctiveResource item = (DisjunctiveResource) find(id);
            } else if (qname.equals("job")) {
                assert (base != null);
                int id = getId("id", attributes);
                Job item = (Job) find(id);
                 item.setOrder(getOrder("order",attributes));
                 item.setProcess(getProcess("process",attributes));
            } else if (qname.equals("jobAssignment")) {
                assert (base != null);
                int id = getId("id", attributes);
                JobAssignment item = (JobAssignment) find(id);
                 item.setJob(getJob("job",attributes));
                 item.setSolution(getSolution("solution",attributes));
            } else if (qname.equals("order")) {
                assert (base != null);
                int id = getId("id", attributes);
                Order item = (Order) find(id);
                 item.setProduct(getProduct("product",attributes));
            } else if (qname.equals("problem")) {
                assert (base != null);
                int id = getId("id", attributes);
                Problem item = (Problem) find(id);
            } else if (qname.equals("process")) {
                assert (base != null);
                int id = getId("id", attributes);
                Process item = (Process) find(id);
            } else if (qname.equals("processSequence")) {
                assert (base != null);
                int id = getId("id", attributes);
                ProcessSequence item = (ProcessSequence) find(id);
                 item.setAfter(getProcessStep("after",attributes));
                 item.setBefore(getProcessStep("before",attributes));
                 item.setSequenceType(getSequenceType("sequenceType",attributes));
            } else if (qname.equals("processStep")) {
                assert (base != null);
                int id = getId("id", attributes);
                ProcessStep item = (ProcessStep) find(id);
                 item.setProcess(getProcess("process",attributes));
            } else if (qname.equals("product")) {
                assert (base != null);
                int id = getId("id", attributes);
                Product item = (Product) find(id);
                 item.setProcess(getProcess("process",attributes));
            } else if (qname.equals("resourceNeed")) {
                assert (base != null);
                int id = getId("id", attributes);
                ResourceNeed item = (ResourceNeed) find(id);
                 item.setDisjunctiveResource(getDisjunctiveResource("disjunctiveResource",attributes));
                 item.setProcessStep(getProcessStep("processStep",attributes));
            } else if (qname.equals("solution")) {
                assert (base != null);
                int id = getId("id", attributes);
                Solution item = (Solution) find(id);
            } else if (qname.equals("task")) {
                assert (base != null);
                int id = getId("id", attributes);
                Task item = (Task) find(id);
                 item.setJob(getJob("job",attributes));
                 item.setProcessStep(getProcessStep("processStep",attributes));
            } else if (qname.equals("taskAssignment")) {
                assert (base != null);
                int id = getId("id", attributes);
                TaskAssignment item = (TaskAssignment) find(id);
                 item.setJobAssignment(getJobAssignment("jobAssignment",attributes));
                 item.setTask(getTask("task",attributes));
            } else {
                System.out.println("Element Structure " + qname);
                numNodes++;
            }
        }
    }


}

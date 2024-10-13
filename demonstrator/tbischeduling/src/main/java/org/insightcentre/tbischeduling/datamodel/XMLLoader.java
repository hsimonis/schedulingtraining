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
public Severity getSeverity(String attributeName,
                               Attributes attributes) {
        String e = attributes.getValue(attributeName);
        if (e == null) {
            System.out.println("Severity"+": "+attributeName);
            return null;
        } else {
            return Severity.valueOf(e);
        }
    }
public ModelType getModelType(String attributeName,
                               Attributes attributes) {
        String e = attributes.getValue(attributeName);
        if (e == null) {
            System.out.println("ModelType"+": "+attributeName);
            return null;
        } else {
            return ModelType.valueOf(e);
        }
    }
public SolverBackend getSolverBackend(String attributeName,
                               Attributes attributes) {
        String e = attributes.getValue(attributeName);
        if (e == null) {
            System.out.println("SolverBackend"+": "+attributeName);
            return null;
        } else {
            return SolverBackend.valueOf(e);
        }
    }
public SolverStatus getSolverStatus(String attributeName,
                               Attributes attributes) {
        String e = attributes.getValue(attributeName);
        if (e == null) {
            System.out.println("SolverStatus"+": "+attributeName);
            return null;
        } else {
            return SolverStatus.valueOf(e);
        }
    }
public ObjectiveType getObjectiveType(String attributeName,
                               Attributes attributes) {
        String e = attributes.getValue(attributeName);
        if (e == null) {
            System.out.println("ObjectiveType"+": "+attributeName);
            return null;
        } else {
            return ObjectiveType.valueOf(e);
        }
    }
public ResourceModel getResourceModel(String attributeName,
                               Attributes attributes) {
        String e = attributes.getValue(attributeName);
        if (e == null) {
            System.out.println("ResourceModel"+": "+attributeName);
            return null;
        } else {
            return ResourceModel.valueOf(e);
        }
    }
public DurationModel getDurationModel(String attributeName,
                               Attributes attributes) {
        String e = attributes.getValue(attributeName);
        if (e == null) {
            System.out.println("DurationModel"+": "+attributeName);
            return null;
        } else {
            return DurationModel.valueOf(e);
        }
    }
    public AbstractDataGeneratorProperty getAbstractDataGeneratorProperty(String attributeName,
                               Attributes attributes) {
        return (AbstractDataGeneratorProperty) find(getId(attributeName,attributes));
    }

    public List<AbstractDataGeneratorProperty> getAbstractDataGeneratorPropertyCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<AbstractDataGeneratorProperty> res = new ArrayList<AbstractDataGeneratorProperty>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((AbstractDataGeneratorProperty) find(id));
            }
        }
        return res;
    }

    public AbstractSolverProperty getAbstractSolverProperty(String attributeName,
                               Attributes attributes) {
        return (AbstractSolverProperty) find(getId(attributeName,attributes));
    }

    public List<AbstractSolverProperty> getAbstractSolverPropertyCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<AbstractSolverProperty> res = new ArrayList<AbstractSolverProperty>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((AbstractSolverProperty) find(id));
            }
        }
        return res;
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

    public CumulativeNeed getCumulativeNeed(String attributeName,
                               Attributes attributes) {
        return (CumulativeNeed) find(getId(attributeName,attributes));
    }

    public List<CumulativeNeed> getCumulativeNeedCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<CumulativeNeed> res = new ArrayList<CumulativeNeed>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((CumulativeNeed) find(id));
            }
        }
        return res;
    }

    public CumulativeProfile getCumulativeProfile(String attributeName,
                               Attributes attributes) {
        return (CumulativeProfile) find(getId(attributeName,attributes));
    }

    public List<CumulativeProfile> getCumulativeProfileCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<CumulativeProfile> res = new ArrayList<CumulativeProfile>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((CumulativeProfile) find(id));
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

    public DataGeneratorProperty getDataGeneratorProperty(String attributeName,
                               Attributes attributes) {
        return (DataGeneratorProperty) find(getId(attributeName,attributes));
    }

    public List<DataGeneratorProperty> getDataGeneratorPropertyCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<DataGeneratorProperty> res = new ArrayList<DataGeneratorProperty>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((DataGeneratorProperty) find(id));
            }
        }
        return res;
    }

    public DataGeneratorRun getDataGeneratorRun(String attributeName,
                               Attributes attributes) {
        return (DataGeneratorRun) find(getId(attributeName,attributes));
    }

    public List<DataGeneratorRun> getDataGeneratorRunCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<DataGeneratorRun> res = new ArrayList<DataGeneratorRun>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((DataGeneratorRun) find(id));
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

    public Downtime getDowntime(String attributeName,
                               Attributes attributes) {
        return (Downtime) find(getId(attributeName,attributes));
    }

    public List<Downtime> getDowntimeCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<Downtime> res = new ArrayList<Downtime>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((Downtime) find(id));
            }
        }
        return res;
    }

    public InputError getInputError(String attributeName,
                               Attributes attributes) {
        return (InputError) find(getId(attributeName,attributes));
    }

    public List<InputError> getInputErrorCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<InputError> res = new ArrayList<InputError>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((InputError) find(id));
            }
        }
        return res;
    }

    public IntermediateSolution getIntermediateSolution(String attributeName,
                               Attributes attributes) {
        return (IntermediateSolution) find(getId(attributeName,attributes));
    }

    public List<IntermediateSolution> getIntermediateSolutionCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<IntermediateSolution> res = new ArrayList<IntermediateSolution>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((IntermediateSolution) find(id));
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

    public ResourceActivity getResourceActivity(String attributeName,
                               Attributes attributes) {
        return (ResourceActivity) find(getId(attributeName,attributes));
    }

    public List<ResourceActivity> getResourceActivityCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<ResourceActivity> res = new ArrayList<ResourceActivity>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((ResourceActivity) find(id));
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

    public ResourceUtilization getResourceUtilization(String attributeName,
                               Attributes attributes) {
        return (ResourceUtilization) find(getId(attributeName,attributes));
    }

    public List<ResourceUtilization> getResourceUtilizationCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<ResourceUtilization> res = new ArrayList<ResourceUtilization>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((ResourceUtilization) find(id));
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

    public SolutionError getSolutionError(String attributeName,
                               Attributes attributes) {
        return (SolutionError) find(getId(attributeName,attributes));
    }

    public List<SolutionError> getSolutionErrorCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<SolutionError> res = new ArrayList<SolutionError>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((SolutionError) find(id));
            }
        }
        return res;
    }

    public SolverProperty getSolverProperty(String attributeName,
                               Attributes attributes) {
        return (SolverProperty) find(getId(attributeName,attributes));
    }

    public List<SolverProperty> getSolverPropertyCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<SolverProperty> res = new ArrayList<SolverProperty>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((SolverProperty) find(id));
            }
        }
        return res;
    }

    public SolverRun getSolverRun(String attributeName,
                               Attributes attributes) {
        return (SolverRun) find(getId(attributeName,attributes));
    }

    public List<SolverRun> getSolverRunCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<SolverRun> res = new ArrayList<SolverRun>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((SolverRun) find(id));
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

    public WiP getWiP(String attributeName,
                               Attributes attributes) {
        return (WiP) find(getId(attributeName,attributes));
    }

    public List<WiP> getWiPCollectionFromIds(String attributeName,
                                     Attributes attributes) {
        String e = attributes.getValue(attributeName);
        String[] words = e.split(" ");
        List<WiP> res = new ArrayList<WiP>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                int id = Integer.parseInt(words[i].substring(3));
                res.add((WiP) find(id));
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
                        getBoolean("valid",attributes,false),
                        getString("dataFile",attributes,""),
                        getDouble("dataFileVersionNumber",attributes,0.0),
                        null,
                        getDouble("ganttLineHeight",attributes,0.0),
                        getInteger("ganttLinesPerPage",attributes,0),
                        getInteger("ganttWidth",attributes,0),
                        getInteger("horizon",attributes,0),
                        null,
                        getDateTime("startDateTime",attributes,"2011-01-01"),
                        getInteger("timeResolution",attributes,0)
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
            } else if (qname.equals("cumulativeNeed")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new CumulativeNeed(base,
                        id,
                        getString("name", attributes, "dummy"),
                        null,
                        getInteger("demand",attributes,0),
                        null
                        ));
            } else if (qname.equals("cumulativeProfile")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new CumulativeProfile(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getInteger("capacity",attributes,0),
                        null,
                        getInteger("from",attributes,0),
                        getDateTime("fromDate",attributes,"2011-01-01")
                        ));
            } else if (qname.equals("cumulativeResource")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new CumulativeResource(base,
                        id,
                        getString("name", attributes, "dummy")
                        ));
            } else if (qname.equals("dataGeneratorProperty")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new DataGeneratorProperty(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getDouble("downtimeProbability",attributes,0.0),
                        getInteger("durationFixedFactor",attributes,0),
                        null,
                        getInteger("earliestDue",attributes,0),
                        getInteger("horizonDays",attributes,0),
                        getString("label",attributes,""),
                        getInteger("maxCumulCapacity",attributes,0),
                        getInteger("maxCumulDemand",attributes,0),
                        getInteger("maxDowntime",attributes,0),
                        getInteger("maxDuration",attributes,0),
                        getInteger("maxQty",attributes,0),
                        getInteger("maxStages",attributes,0),
                        getInteger("maxWip",attributes,0),
                        getInteger("minCumulCapacity",attributes,0),
                        getInteger("minCumulDemand",attributes,0),
                        getInteger("minDowntime",attributes,0),
                        getInteger("minDuration",attributes,0),
                        getInteger("minQty",attributes,0),
                        getInteger("minStages",attributes,0),
                        getInteger("minWip",attributes,0),
                        getInteger("nrCumulativeResources",attributes,0),
                        getInteger("nrDisjunctiveResources",attributes,0),
                        getInteger("nrOrders",attributes,0),
                        getInteger("nrProducts",attributes,0),
                        getInteger("profilePieces",attributes,0),
                        null,
                        getDouble("resourceProbability",attributes,0.0),
                        getInteger("seed",attributes,0),
                        getDateTime("startDateTime",attributes,"2011-01-01"),
                        getInteger("timeResolution",attributes,0),
                        getDouble("wipProbability",attributes,0.0)
                        ));
            } else if (qname.equals("dataGeneratorRun")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new DataGeneratorRun(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getDouble("downtimeProbability",attributes,0.0),
                        getInteger("durationFixedFactor",attributes,0),
                        null,
                        getInteger("earliestDue",attributes,0),
                        getInteger("horizonDays",attributes,0),
                        getString("label",attributes,""),
                        getInteger("maxCumulCapacity",attributes,0),
                        getInteger("maxCumulDemand",attributes,0),
                        getInteger("maxDowntime",attributes,0),
                        getInteger("maxDuration",attributes,0),
                        getInteger("maxQty",attributes,0),
                        getInteger("maxStages",attributes,0),
                        getInteger("maxWip",attributes,0),
                        getInteger("minCumulCapacity",attributes,0),
                        getInteger("minCumulDemand",attributes,0),
                        getInteger("minDowntime",attributes,0),
                        getInteger("minDuration",attributes,0),
                        getInteger("minQty",attributes,0),
                        getInteger("minStages",attributes,0),
                        getInteger("minWip",attributes,0),
                        getInteger("nrCumulativeResources",attributes,0),
                        getInteger("nrDisjunctiveResources",attributes,0),
                        getInteger("nrOrders",attributes,0),
                        getInteger("nrProducts",attributes,0),
                        getInteger("profilePieces",attributes,0),
                        null,
                        getDouble("resourceProbability",attributes,0.0),
                        getInteger("seed",attributes,0),
                        getDateTime("startDateTime",attributes,"2011-01-01"),
                        getInteger("timeResolution",attributes,0),
                        getDouble("wipProbability",attributes,0.0)
                        ));
            } else if (qname.equals("disjunctiveResource")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new DisjunctiveResource(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getString("shortName",attributes,"")
                        ));
            } else if (qname.equals("downtime")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new Downtime(base,
                        id,
                        getString("name", attributes, "dummy"),
                        null,
                        getInteger("duration",attributes,0),
                        getInteger("end",attributes,0),
                        getDateTime("endDate",attributes,"2011-01-01"),
                        getInteger("start",attributes,0),
                        getDateTime("startDate",attributes,"2011-01-01")
                        ));
            } else if (qname.equals("inputError")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new InputError(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getString("classDesc",attributes,""),
                        getString("description",attributes,""),
                        getString("field",attributes,""),
                        getString("item",attributes,""),
                        null,
                        getString("value",attributes,"")
                        ));
            } else if (qname.equals("intermediateSolution")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new IntermediateSolution(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getDouble("bound",attributes,0.0),
                        getDouble("cost",attributes,0.0),
                        getDouble("gapPercent",attributes,0.0),
                        getInteger("nr",attributes,0),
                        null,
                        getDouble("time",attributes,0.0)
                        ));
            } else if (qname.equals("job")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new Job(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getInteger("nr",attributes,0),
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
                        getInteger("early",attributes,0),
                        getInteger("end",attributes,0),
                        getDateTime("endDate",attributes,"2011-01-01"),
                        null,
                        getInteger("late",attributes,0),
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
                        getDateTime("dueDate",attributes,"2011-01-01"),
                        getDouble("earlinessWeight",attributes,0.0),
                        getDouble("latenessWeight",attributes,0.0),
                        getInteger("minDuration",attributes,0),
                        getInteger("nr",attributes,0),
                        null,
                        null,
                        getInteger("qty",attributes,0),
                        getInteger("release",attributes,0),
                        getDateTime("releaseDate",attributes,"2011-01-01")
                        ));
            } else if (qname.equals("problem")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new Problem(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getString("label",attributes,""),
                        getInteger("nrCumulativeResources",attributes,0),
                        getInteger("nrDisjunctiveResources",attributes,0),
                        getInteger("nrJobs",attributes,0),
                        getInteger("nrOrders",attributes,0),
                        getInteger("nrProcesses",attributes,0),
                        getInteger("nrProducts",attributes,0),
                        getInteger("nrTasks",attributes,0),
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
                        null,
                        getInteger("stage",attributes,0)
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
                        null,
                        getInteger("value",attributes,0)
                        ));
            } else if (qname.equals("resourceUtilization")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new ResourceUtilization(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getInteger("active",attributes,0),
                        null,
                        getInteger("earliest",attributes,0),
                        getInteger("latest",attributes,0),
                        null,
                        getInteger("use",attributes,0),
                        getDouble("utilization",attributes,0.0)
                        ));
            } else if (qname.equals("solution")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new Solution(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getDouble("bound",attributes,0.0),
                        getInteger("duration",attributes,0),
                        getInteger("end",attributes,0),
                        getDateTime("endDate",attributes,"2011-01-01"),
                        getInteger("flowtime",attributes,0),
                        getDouble("gap",attributes,0.0),
                        getInteger("makespan",attributes,0),
                        getInteger("maxEarliness",attributes,0),
                        getInteger("maxLateness",attributes,0),
                        getInteger("maxWaitAfter",attributes,0),
                        getInteger("maxWaitBefore",attributes,0),
                        getInteger("nrEarly",attributes,0),
                        getInteger("nrLate",attributes,0),
                        getInteger("objectiveValue",attributes,0),
                        getDouble("percentEarly",attributes,0.0),
                        getDouble("percentLate",attributes,0.0),
                        null,
                        null,
                        getInteger("start",attributes,0),
                        getDateTime("startDate",attributes,"2011-01-01"),
                        getInteger("totalEarliness",attributes,0),
                        getInteger("totalLateness",attributes,0),
                        getInteger("totalWaitAfter",attributes,0),
                        getInteger("totalWaitBefore",attributes,0),
                        getDouble("weightedEarliness",attributes,0.0),
                        getDouble("weightedLateness",attributes,0.0)
                        ));
            } else if (qname.equals("solutionError")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new SolutionError(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getString("classDesc",attributes,""),
                        getString("description",attributes,""),
                        getString("field",attributes,""),
                        getString("item",attributes,""),
                        null,
                        null,
                        getString("value",attributes,"")
                        ));
            } else if (qname.equals("solverProperty")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new SolverProperty(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getString("description",attributes,""),
                        getBoolean("enforceCumulative",attributes,false),
                        getBoolean("enforceDowntime",attributes,false),
                        getBoolean("enforceDueDate",attributes,false),
                        getBoolean("enforceReleaseDate",attributes,false),
                        getBoolean("enforceWip",attributes,false),
                        getString("label",attributes,""),
                        null,
                        getInteger("nrThreads",attributes,0),
                        null,
                        getBoolean("producePDF",attributes,false),
                        getBoolean("produceReport",attributes,false),
                        getBoolean("removeSolution",attributes,false),
                        getInteger("seed",attributes,0),
                        null,
                        getDateTime("startDateTime",attributes,"2011-01-01"),
                        getInteger("timeout",attributes,0),
                        getInteger("weightEarliness",attributes,0),
                        getInteger("weightFlowtime",attributes,0),
                        getInteger("weightLateness",attributes,0),
                        getInteger("weightMakespan",attributes,0)
                        ));
            } else if (qname.equals("solverRun")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new SolverRun(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getString("description",attributes,""),
                        getBoolean("enforceCumulative",attributes,false),
                        getBoolean("enforceDowntime",attributes,false),
                        getBoolean("enforceDueDate",attributes,false),
                        getBoolean("enforceReleaseDate",attributes,false),
                        getBoolean("enforceWip",attributes,false),
                        getString("label",attributes,""),
                        null,
                        getInteger("nrThreads",attributes,0),
                        null,
                        getBoolean("producePDF",attributes,false),
                        getBoolean("produceReport",attributes,false),
                        getBoolean("removeSolution",attributes,false),
                        getInteger("seed",attributes,0),
                        null,
                        getDateTime("startDateTime",attributes,"2011-01-01"),
                        getInteger("timeout",attributes,0),
                        getInteger("weightEarliness",attributes,0),
                        getInteger("weightFlowtime",attributes,0),
                        getInteger("weightLateness",attributes,0),
                        getInteger("weightMakespan",attributes,0),
                        null,
                        getDouble("time",attributes,0.0)
                        ));
            } else if (qname.equals("task")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new Task(base,
                        id,
                        getString("name", attributes, "dummy"),
                        getInteger("duration",attributes,0),
                        null,
                        null,
                        null,
                        getInteger("nr",attributes,0),
                        null,
                        null,
                        getString("shortName",attributes,""),
                        getInteger("stage",attributes,0)
                        ));
            } else if (qname.equals("taskAssignment")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new TaskAssignment(base,
                        id,
                        getString("name", attributes, "dummy"),
                        null,
                        getInteger("duration",attributes,0),
                        getInteger("end",attributes,0),
                        getDateTime("endDate",attributes,"2011-01-01"),
                        getInteger("start",attributes,0),
                        getDateTime("startDate",attributes,"2011-01-01"),
                        null,
                        null,
                        getInteger("waitAfter",attributes,0),
                        getInteger("waitBefore",attributes,0)
                        ));
            } else if (qname.equals("wiP")) {
                assert (base != null);
                int id = getId("id", attributes);
                store(id, new WiP(base,
                        id,
                        getString("name", attributes, "dummy"),
                        null,
                        getInteger("duration",attributes,0),
                        getInteger("end",attributes,0),
                        getDateTime("endDate",attributes,"2011-01-01"),
                        getInteger("start",attributes,0),
                        getDateTime("startDate",attributes,"2011-01-01")
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
                 item.setDataGeneratorProperty(getDataGeneratorProperty("dataGeneratorProperty",attributes));
                 item.setSolverProperty(getSolverProperty("solverProperty",attributes));
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
            } else if (qname.equals("cumulativeNeed")) {
                assert (base != null);
                int id = getId("id", attributes);
                CumulativeNeed item = (CumulativeNeed) find(id);
                 item.setCumulativeResource(getCumulativeResource("cumulativeResource",attributes));
                 item.setProcessStep(getProcessStep("processStep",attributes));
            } else if (qname.equals("cumulativeProfile")) {
                assert (base != null);
                int id = getId("id", attributes);
                CumulativeProfile item = (CumulativeProfile) find(id);
                 item.setCumulativeResource(getCumulativeResource("cumulativeResource",attributes));
            } else if (qname.equals("cumulativeResource")) {
                assert (base != null);
                int id = getId("id", attributes);
                CumulativeResource item = (CumulativeResource) find(id);
            } else if (qname.equals("dataGeneratorProperty")) {
                assert (base != null);
                int id = getId("id", attributes);
                DataGeneratorProperty item = (DataGeneratorProperty) find(id);
                 item.setDurationModel(getDurationModel("durationModel",attributes));
                 item.setResourceModel(getResourceModel("resourceModel",attributes));
            } else if (qname.equals("dataGeneratorRun")) {
                assert (base != null);
                int id = getId("id", attributes);
                DataGeneratorRun item = (DataGeneratorRun) find(id);
                 item.setDurationModel(getDurationModel("durationModel",attributes));
                 item.setResourceModel(getResourceModel("resourceModel",attributes));
            } else if (qname.equals("disjunctiveResource")) {
                assert (base != null);
                int id = getId("id", attributes);
                DisjunctiveResource item = (DisjunctiveResource) find(id);
            } else if (qname.equals("downtime")) {
                assert (base != null);
                int id = getId("id", attributes);
                Downtime item = (Downtime) find(id);
                 item.setDisjunctiveResource(getDisjunctiveResource("disjunctiveResource",attributes));
            } else if (qname.equals("inputError")) {
                assert (base != null);
                int id = getId("id", attributes);
                InputError item = (InputError) find(id);
                 item.setSeverity(getSeverity("severity",attributes));
            } else if (qname.equals("intermediateSolution")) {
                assert (base != null);
                int id = getId("id", attributes);
                IntermediateSolution item = (IntermediateSolution) find(id);
                 item.setSolverRun(getSolverRun("solverRun",attributes));
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
                 item.setProcess(getProcess("process",attributes));
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
                 item.setDefaultProcess(getProcess("defaultProcess",attributes));
            } else if (qname.equals("resourceNeed")) {
                assert (base != null);
                int id = getId("id", attributes);
                ResourceNeed item = (ResourceNeed) find(id);
                 item.setDisjunctiveResource(getDisjunctiveResource("disjunctiveResource",attributes));
                 item.setProcessStep(getProcessStep("processStep",attributes));
            } else if (qname.equals("resourceUtilization")) {
                assert (base != null);
                int id = getId("id", attributes);
                ResourceUtilization item = (ResourceUtilization) find(id);
                 item.setDisjunctiveResource(getDisjunctiveResource("disjunctiveResource",attributes));
                 item.setSolution(getSolution("solution",attributes));
            } else if (qname.equals("solution")) {
                assert (base != null);
                int id = getId("id", attributes);
                Solution item = (Solution) find(id);
                 item.setSolverRun(getSolverRun("solverRun",attributes));
                 item.setSolverStatus(getSolverStatus("solverStatus",attributes));
            } else if (qname.equals("solutionError")) {
                assert (base != null);
                int id = getId("id", attributes);
                SolutionError item = (SolutionError) find(id);
                 item.setSeverity(getSeverity("severity",attributes));
                 item.setSolution(getSolution("solution",attributes));
            } else if (qname.equals("solverProperty")) {
                assert (base != null);
                int id = getId("id", attributes);
                SolverProperty item = (SolverProperty) find(id);
                 item.setModelType(getModelType("modelType",attributes));
                 item.setObjectiveType(getObjectiveType("objectiveType",attributes));
                 item.setSolverBackend(getSolverBackend("solverBackend",attributes));
            } else if (qname.equals("solverRun")) {
                assert (base != null);
                int id = getId("id", attributes);
                SolverRun item = (SolverRun) find(id);
                 item.setModelType(getModelType("modelType",attributes));
                 item.setObjectiveType(getObjectiveType("objectiveType",attributes));
                 item.setSolverBackend(getSolverBackend("solverBackend",attributes));
                 item.setSolverStatus(getSolverStatus("solverStatus",attributes));
            } else if (qname.equals("task")) {
                assert (base != null);
                int id = getId("id", attributes);
                Task item = (Task) find(id);
                 item.setFollows(getTaskCollectionFromIds("follows",attributes));
                 item.setJob(getJob("job",attributes));
                 item.setMachines(getDisjunctiveResourceCollectionFromIds("machines",attributes));
                 item.setPrecedes(getTaskCollectionFromIds("precedes",attributes));
                 item.setProcessStep(getProcessStep("processStep",attributes));
            } else if (qname.equals("taskAssignment")) {
                assert (base != null);
                int id = getId("id", attributes);
                TaskAssignment item = (TaskAssignment) find(id);
                 item.setDisjunctiveResource(getDisjunctiveResource("disjunctiveResource",attributes));
                 item.setJobAssignment(getJobAssignment("jobAssignment",attributes));
                 item.setTask(getTask("task",attributes));
            } else if (qname.equals("wiP")) {
                assert (base != null);
                int id = getId("id", attributes);
                WiP item = (WiP) find(id);
                 item.setDisjunctiveResource(getDisjunctiveResource("disjunctiveResource",attributes));
            } else {
                System.out.println("Element Structure " + qname);
                numNodes++;
            }
        }
    }


}

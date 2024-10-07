// licence details to be added
package org.insightcentre.tbischeduling.datamodel;
import org.insightcentre.tbischeduling.datamodel.ApplicationDataset;
import org.insightcentre.tbischeduling.datamodel.ApplicationObject;
import org.insightcentre.tbischeduling.datamodel.ApplicationDifference;
import org.insightcentre.tbischeduling.datamodel.ApplicationWarning;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.AbstractSolverProperty;
import org.insightcentre.tbischeduling.datamodel.SolverProperty;
import org.insightcentre.tbischeduling.datamodel.SolverRun;
import org.insightcentre.tbischeduling.datamodel.AbstractDataGeneratorProperty;
import org.insightcentre.tbischeduling.datamodel.DataGeneratorProperty;
import org.insightcentre.tbischeduling.datamodel.DataGeneratorRun;
import org.insightcentre.tbischeduling.datamodel.InputError;
import org.insightcentre.tbischeduling.datamodel.Problem;
import org.insightcentre.tbischeduling.datamodel.Product;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.datamodel.ProcessStep;
import org.insightcentre.tbischeduling.datamodel.ProcessSequence;
import org.insightcentre.tbischeduling.datamodel.ResourceNeed;
import org.insightcentre.tbischeduling.datamodel.CumulativeNeed;
import org.insightcentre.tbischeduling.datamodel.CumulativeProfile;
import org.insightcentre.tbischeduling.datamodel.DisjunctiveResource;
import org.insightcentre.tbischeduling.datamodel.CumulativeResource;
import org.insightcentre.tbischeduling.datamodel.ResourceActivity;
import org.insightcentre.tbischeduling.datamodel.Order;
import org.insightcentre.tbischeduling.datamodel.Job;
import org.insightcentre.tbischeduling.datamodel.Task;
import org.insightcentre.tbischeduling.datamodel.WiP;
import org.insightcentre.tbischeduling.datamodel.Downtime;
import org.insightcentre.tbischeduling.datamodel.Solution;
import org.insightcentre.tbischeduling.datamodel.JobAssignment;
import org.insightcentre.tbischeduling.datamodel.TaskAssignment;
import org.insightcentre.tbischeduling.datamodel.ResourceUtilization;
import org.insightcentre.tbischeduling.datamodel.IntermediateSolution;
import org.insightcentre.tbischeduling.datamodel.SolutionError;
import org.insightcentre.tbischeduling.datamodel.DifferenceType;
import org.insightcentre.tbischeduling.datamodel.WarningType;
import org.insightcentre.tbischeduling.datamodel.SequenceType;
import org.insightcentre.tbischeduling.datamodel.Severity;
import org.insightcentre.tbischeduling.datamodel.ModelType;
import org.insightcentre.tbischeduling.datamodel.SolverBackend;
import org.insightcentre.tbischeduling.datamodel.SolverStatus;
import org.insightcentre.tbischeduling.datamodel.ObjectiveType;
import org.insightcentre.tbischeduling.datamodel.ResourceModel;
import org.insightcentre.tbischeduling.datamodel.DurationModel;
import org.insightcentre.tbischeduling.datamodel.XMLLoader;
import java.util.*;
import java.io.*;
import framework.types.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import framework.ApplicationObjectInterface;
import framework.ApplicationDatasetInterface;
import framework.AppearInCollection;

/**
 * 
 * @author generated
*/

public abstract class AbstractSolverProperty extends ApplicationObject{
/**
 *  
 *
*/

    public String description;

/**
 *  
 *
*/

    public Boolean enforceCumulative;

    private transient BooleanProperty enforceCumulativeWrapper;

/**
 *  
 *
*/

    public Boolean enforceDowntime;

    private transient BooleanProperty enforceDowntimeWrapper;

/**
 *  
 *
*/

    public Boolean enforceDueDate;

    private transient BooleanProperty enforceDueDateWrapper;

/**
 *  
 *
*/

    public Boolean enforceReleaseDate;

    private transient BooleanProperty enforceReleaseDateWrapper;

/**
 *  
 *
*/

    public Boolean enforceWip;

    private transient BooleanProperty enforceWipWrapper;

/**
 *  
 *
*/

    public String label;

/**
 *  
 *
*/

    public ModelType modelType;

/**
 *  
 *
*/

    public Integer nrThreads;

/**
 *  
 *
*/

    public ObjectiveType objectiveType;

/**
 *  
 *
*/

    public Boolean producePDF;

    private transient BooleanProperty producePDFWrapper;

/**
 *  
 *
*/

    public Boolean produceReport;

    private transient BooleanProperty produceReportWrapper;

/**
 *  
 *
*/

    public Boolean removeSolution;

    private transient BooleanProperty removeSolutionWrapper;

/**
 *  
 *
*/

    public Integer seed;

/**
 *  
 *
*/

    public SolverBackend solverBackend;

/**
 *  
 *
*/

    public DateTime startDateTime;

/**
 *  
 *
*/

    public Integer timeout;

/**
 *  
 *
*/

    public Integer weightEarliness;

/**
 *  
 *
*/

    public Integer weightFlowtime;

/**
 *  
 *
*/

    public Integer weightLateness;

/**
 *  
 *
*/

    public Integer weightMakespan;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public AbstractSolverProperty(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public AbstractSolverProperty(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setDescription("");
        setEnforceCumulative(true);
        setEnforceDowntime(true);
        setEnforceDueDate(false);
        setEnforceReleaseDate(true);
        setEnforceWip(true);
        setLabel("");
        setModelType(ModelType.CPO);
        setNrThreads(2);
        setObjectiveType(ObjectiveType.Makespan);
        setProducePDF(true);
        setProduceReport(true);
        setRemoveSolution(false);
        setSeed(42);
        setSolverBackend(SolverBackend.None);
        setStartDateTime(new DateTime(new Date()));
        setTimeout(30);
        setWeightEarliness(1);
        setWeightFlowtime(1);
        setWeightLateness(1);
        setWeightMakespan(1);
        applicationDataset.addAbstractSolverProperty(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public AbstractSolverProperty(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            String description,
            Boolean enforceCumulative,
            Boolean enforceDowntime,
            Boolean enforceDueDate,
            Boolean enforceReleaseDate,
            Boolean enforceWip,
            String label,
            ModelType modelType,
            Integer nrThreads,
            ObjectiveType objectiveType,
            Boolean producePDF,
            Boolean produceReport,
            Boolean removeSolution,
            Integer seed,
            SolverBackend solverBackend,
            DateTime startDateTime,
            Integer timeout,
            Integer weightEarliness,
            Integer weightFlowtime,
            Integer weightLateness,
            Integer weightMakespan){
        super(applicationDataset,
            id,
            name);
        setDescription(description);
        setEnforceCumulative(enforceCumulative);
        setEnforceDowntime(enforceDowntime);
        setEnforceDueDate(enforceDueDate);
        setEnforceReleaseDate(enforceReleaseDate);
        setEnforceWip(enforceWip);
        setLabel(label);
        setModelType(modelType);
        setNrThreads(nrThreads);
        setObjectiveType(objectiveType);
        setProducePDF(producePDF);
        setProduceReport(produceReport);
        setRemoveSolution(removeSolution);
        setSeed(seed);
        setSolverBackend(solverBackend);
        setStartDateTime(startDateTime);
        setTimeout(timeout);
        setWeightEarliness(weightEarliness);
        setWeightFlowtime(weightFlowtime);
        setWeightLateness(weightLateness);
        setWeightMakespan(weightMakespan);
        applicationDataset.addAbstractSolverProperty(this);
    }

    public AbstractSolverProperty(AbstractSolverProperty other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.description,
            other.enforceCumulative,
            other.enforceDowntime,
            other.enforceDueDate,
            other.enforceReleaseDate,
            other.enforceWip,
            other.label,
            other.modelType,
            other.nrThreads,
            other.objectiveType,
            other.producePDF,
            other.produceReport,
            other.removeSolution,
            other.seed,
            other.solverBackend,
            other.startDateTime,
            other.timeout,
            other.weightEarliness,
            other.weightFlowtime,
            other.weightLateness,
            other.weightMakespan);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeAbstractSolverProperty(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute description
 *
 * @return String
*/

    public String getDescription(){
        return this.description;
    }

/**
 *  get attribute enforceCumulative
 *
 * @return Boolean
*/

    public Boolean getEnforceCumulative(){
        return this.enforceCumulative;
    }

    public BooleanProperty enforceCumulativeWrapperProperty() {
        if (enforceCumulativeWrapper == null) {
            enforceCumulativeWrapper = new SimpleBooleanProperty();
        }
        enforceCumulativeWrapper.set(enforceCumulative);
        return enforceCumulativeWrapper;
    }

/**
 *  get attribute enforceDowntime
 *
 * @return Boolean
*/

    public Boolean getEnforceDowntime(){
        return this.enforceDowntime;
    }

    public BooleanProperty enforceDowntimeWrapperProperty() {
        if (enforceDowntimeWrapper == null) {
            enforceDowntimeWrapper = new SimpleBooleanProperty();
        }
        enforceDowntimeWrapper.set(enforceDowntime);
        return enforceDowntimeWrapper;
    }

/**
 *  get attribute enforceDueDate
 *
 * @return Boolean
*/

    public Boolean getEnforceDueDate(){
        return this.enforceDueDate;
    }

    public BooleanProperty enforceDueDateWrapperProperty() {
        if (enforceDueDateWrapper == null) {
            enforceDueDateWrapper = new SimpleBooleanProperty();
        }
        enforceDueDateWrapper.set(enforceDueDate);
        return enforceDueDateWrapper;
    }

/**
 *  get attribute enforceReleaseDate
 *
 * @return Boolean
*/

    public Boolean getEnforceReleaseDate(){
        return this.enforceReleaseDate;
    }

    public BooleanProperty enforceReleaseDateWrapperProperty() {
        if (enforceReleaseDateWrapper == null) {
            enforceReleaseDateWrapper = new SimpleBooleanProperty();
        }
        enforceReleaseDateWrapper.set(enforceReleaseDate);
        return enforceReleaseDateWrapper;
    }

/**
 *  get attribute enforceWip
 *
 * @return Boolean
*/

    public Boolean getEnforceWip(){
        return this.enforceWip;
    }

    public BooleanProperty enforceWipWrapperProperty() {
        if (enforceWipWrapper == null) {
            enforceWipWrapper = new SimpleBooleanProperty();
        }
        enforceWipWrapper.set(enforceWip);
        return enforceWipWrapper;
    }

/**
 *  get attribute label
 *
 * @return String
*/

    public String getLabel(){
        return this.label;
    }

/**
 *  get attribute modelType
 *
 * @return ModelType
*/

    public ModelType getModelType(){
        return this.modelType;
    }

/**
 *  get attribute nrThreads
 *
 * @return Integer
*/

    public Integer getNrThreads(){
        return this.nrThreads;
    }

/**
 *  get attribute objectiveType
 *
 * @return ObjectiveType
*/

    public ObjectiveType getObjectiveType(){
        return this.objectiveType;
    }

/**
 *  get attribute producePDF
 *
 * @return Boolean
*/

    public Boolean getProducePDF(){
        return this.producePDF;
    }

    public BooleanProperty producePDFWrapperProperty() {
        if (producePDFWrapper == null) {
            producePDFWrapper = new SimpleBooleanProperty();
        }
        producePDFWrapper.set(producePDF);
        return producePDFWrapper;
    }

/**
 *  get attribute produceReport
 *
 * @return Boolean
*/

    public Boolean getProduceReport(){
        return this.produceReport;
    }

    public BooleanProperty produceReportWrapperProperty() {
        if (produceReportWrapper == null) {
            produceReportWrapper = new SimpleBooleanProperty();
        }
        produceReportWrapper.set(produceReport);
        return produceReportWrapper;
    }

/**
 *  get attribute removeSolution
 *
 * @return Boolean
*/

    public Boolean getRemoveSolution(){
        return this.removeSolution;
    }

    public BooleanProperty removeSolutionWrapperProperty() {
        if (removeSolutionWrapper == null) {
            removeSolutionWrapper = new SimpleBooleanProperty();
        }
        removeSolutionWrapper.set(removeSolution);
        return removeSolutionWrapper;
    }

/**
 *  get attribute seed
 *
 * @return Integer
*/

    public Integer getSeed(){
        return this.seed;
    }

/**
 *  get attribute solverBackend
 *
 * @return SolverBackend
*/

    public SolverBackend getSolverBackend(){
        return this.solverBackend;
    }

/**
 *  get attribute startDateTime
 *
 * @return DateTime
*/

    public DateTime getStartDateTime(){
        return this.startDateTime;
    }

/**
 *  get attribute timeout
 *
 * @return Integer
*/

    public Integer getTimeout(){
        return this.timeout;
    }

/**
 *  get attribute weightEarliness
 *
 * @return Integer
*/

    public Integer getWeightEarliness(){
        return this.weightEarliness;
    }

/**
 *  get attribute weightFlowtime
 *
 * @return Integer
*/

    public Integer getWeightFlowtime(){
        return this.weightFlowtime;
    }

/**
 *  get attribute weightLateness
 *
 * @return Integer
*/

    public Integer getWeightLateness(){
        return this.weightLateness;
    }

/**
 *  get attribute weightMakespan
 *
 * @return Integer
*/

    public Integer getWeightMakespan(){
        return this.weightMakespan;
    }

/**
 *  set attribute description, mark dataset as dirty, mark dataset as not valid
@param description String
 *
*/

    public void setDescription(String description){
        this.description = description;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute enforceCumulative, mark dataset as dirty, mark dataset as not valid
@param enforceCumulative Boolean
 *
*/

    public void setEnforceCumulative(Boolean enforceCumulative){
        this.enforceCumulative = enforceCumulative;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute enforceDowntime, mark dataset as dirty, mark dataset as not valid
@param enforceDowntime Boolean
 *
*/

    public void setEnforceDowntime(Boolean enforceDowntime){
        this.enforceDowntime = enforceDowntime;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute enforceDueDate, mark dataset as dirty, mark dataset as not valid
@param enforceDueDate Boolean
 *
*/

    public void setEnforceDueDate(Boolean enforceDueDate){
        this.enforceDueDate = enforceDueDate;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute enforceReleaseDate, mark dataset as dirty, mark dataset as not valid
@param enforceReleaseDate Boolean
 *
*/

    public void setEnforceReleaseDate(Boolean enforceReleaseDate){
        this.enforceReleaseDate = enforceReleaseDate;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute enforceWip, mark dataset as dirty, mark dataset as not valid
@param enforceWip Boolean
 *
*/

    public void setEnforceWip(Boolean enforceWip){
        this.enforceWip = enforceWip;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute label, mark dataset as dirty, mark dataset as not valid
@param label String
 *
*/

    public void setLabel(String label){
        this.label = label;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute modelType, mark dataset as dirty, mark dataset as not valid
@param modelType ModelType
 *
*/

    public void setModelType(ModelType modelType){
        this.modelType = modelType;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute nrThreads, mark dataset as dirty, mark dataset as not valid
@param nrThreads Integer
 *
*/

    public void setNrThreads(Integer nrThreads){
        this.nrThreads = nrThreads;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute objectiveType, mark dataset as dirty, mark dataset as not valid
@param objectiveType ObjectiveType
 *
*/

    public void setObjectiveType(ObjectiveType objectiveType){
        this.objectiveType = objectiveType;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute producePDF, mark dataset as dirty, mark dataset as not valid
@param producePDF Boolean
 *
*/

    public void setProducePDF(Boolean producePDF){
        this.producePDF = producePDF;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute produceReport, mark dataset as dirty, mark dataset as not valid
@param produceReport Boolean
 *
*/

    public void setProduceReport(Boolean produceReport){
        this.produceReport = produceReport;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute removeSolution, mark dataset as dirty, mark dataset as not valid
@param removeSolution Boolean
 *
*/

    public void setRemoveSolution(Boolean removeSolution){
        this.removeSolution = removeSolution;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute seed, mark dataset as dirty, mark dataset as not valid
@param seed Integer
 *
*/

    public void setSeed(Integer seed){
        this.seed = seed;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute solverBackend, mark dataset as dirty, mark dataset as not valid
@param solverBackend SolverBackend
 *
*/

    public void setSolverBackend(SolverBackend solverBackend){
        this.solverBackend = solverBackend;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute startDateTime, mark dataset as dirty, mark dataset as not valid
@param startDateTime DateTime
 *
*/

    public void setStartDateTime(DateTime startDateTime){
        this.startDateTime = startDateTime;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute timeout, mark dataset as dirty, mark dataset as not valid
@param timeout Integer
 *
*/

    public void setTimeout(Integer timeout){
        this.timeout = timeout;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute weightEarliness, mark dataset as dirty, mark dataset as not valid
@param weightEarliness Integer
 *
*/

    public void setWeightEarliness(Integer weightEarliness){
        this.weightEarliness = weightEarliness;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute weightFlowtime, mark dataset as dirty, mark dataset as not valid
@param weightFlowtime Integer
 *
*/

    public void setWeightFlowtime(Integer weightFlowtime){
        this.weightFlowtime = weightFlowtime;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute weightLateness, mark dataset as dirty, mark dataset as not valid
@param weightLateness Integer
 *
*/

    public void setWeightLateness(Integer weightLateness){
        this.weightLateness = weightLateness;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute weightMakespan, mark dataset as dirty, mark dataset as not valid
@param weightMakespan Integer
 *
*/

    public void setWeightMakespan(Integer weightMakespan){
        this.weightMakespan = weightMakespan;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nrThreads, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNrThreads(){
        this.nrThreads++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute seed, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incSeed(){
        this.seed++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute timeout, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incTimeout(){
        this.timeout++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute weightEarliness, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incWeightEarliness(){
        this.weightEarliness++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute weightFlowtime, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incWeightFlowtime(){
        this.weightFlowtime++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute weightLateness, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incWeightLateness(){
        this.weightLateness++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute weightMakespan, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incWeightMakespan(){
        this.weightMakespan++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  override generic toString() method, show all attributes in human readable form
 * @return String details of the format are not clearly defined at the moment
*/

    @Override
    public String toString(){
        return toColumnString();
    }

/**
 *  alternative to the toString() method, experimental at this point
 *  This should be easier to read than toString(), but contain more information than toColumnString()
 * @return String human readable
*/

    public String prettyString(){
        return ""+ " " +getId()+ " " +getName()+ " " +getDescription()+ " " +getEnforceCumulative()+ " " +getEnforceDowntime()+ " " +getEnforceDueDate()+ " " +getEnforceReleaseDate()+ " " +getEnforceWip()+ " " +getLabel()+ " " +getModelType()+ " " +getNrThreads()+ " " +getObjectiveType()+ " " +getProducePDF()+ " " +getProduceReport()+ " " +getRemoveSolution()+ " " +getSeed()+ " " +getSolverBackend()+ " " +getStartDateTime()+ " " +getTimeout()+ " " +getWeightEarliness()+ " " +getWeightFlowtime()+ " " +getWeightLateness()+ " " +getWeightMakespan();
    }

/**
 *  alternative to the toString() method, used in the table views
 *  this only shows enough fields to identify the object
 *  Normally this is the name attribute, but this can be changed by the display_key fields
 * @return String normally name or other fields defned in display_key
*/

    public String toColumnString(){
        return getName();
    }

/**
 * show object as one element in XML format
 * side effect of writing to file
 * @param out PrintWriter
*/

     public void toXML(PrintWriter out){
         out.println("<abstractSolverProperty "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " description=\""+toXMLDescription()+"\""+
            " enforceCumulative=\""+toXMLEnforceCumulative()+"\""+
            " enforceDowntime=\""+toXMLEnforceDowntime()+"\""+
            " enforceDueDate=\""+toXMLEnforceDueDate()+"\""+
            " enforceReleaseDate=\""+toXMLEnforceReleaseDate()+"\""+
            " enforceWip=\""+toXMLEnforceWip()+"\""+
            " label=\""+toXMLLabel()+"\""+
            " modelType=\""+toXMLModelType()+"\""+
            " nrThreads=\""+toXMLNrThreads()+"\""+
            " objectiveType=\""+toXMLObjectiveType()+"\""+
            " producePDF=\""+toXMLProducePDF()+"\""+
            " produceReport=\""+toXMLProduceReport()+"\""+
            " removeSolution=\""+toXMLRemoveSolution()+"\""+
            " seed=\""+toXMLSeed()+"\""+
            " solverBackend=\""+toXMLSolverBackend()+"\""+
            " startDateTime=\""+toXMLStartDateTime()+"\""+
            " timeout=\""+toXMLTimeout()+"\""+
            " weightEarliness=\""+toXMLWeightEarliness()+"\""+
            " weightFlowtime=\""+toXMLWeightFlowtime()+"\""+
            " weightLateness=\""+toXMLWeightLateness()+"\""+
            " weightMakespan=\""+toXMLWeightMakespan()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDescription(){
        return this.safeXML(getDescription());
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLEnforceCumulative(){
        return this.getEnforceCumulative().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLEnforceDowntime(){
        return this.getEnforceDowntime().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLEnforceDueDate(){
        return this.getEnforceDueDate().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLEnforceReleaseDate(){
        return this.getEnforceReleaseDate().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLEnforceWip(){
        return this.getEnforceWip().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLLabel(){
        return this.safeXML(getLabel());
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLModelType(){
        return this.getModelType().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNrThreads(){
        return this.getNrThreads().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLObjectiveType(){
        return this.getObjectiveType().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLProducePDF(){
        return this.getProducePDF().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLProduceReport(){
        return this.getProduceReport().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLRemoveSolution(){
        return this.getRemoveSolution().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLSeed(){
        return this.getSeed().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLSolverBackend(){
        return this.getSolverBackend().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLStartDateTime(){
        return this.getStartDateTime().toXML();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLTimeout(){
        return this.getTimeout().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLWeightEarliness(){
        return this.getWeightEarliness().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLWeightFlowtime(){
        return this.getWeightFlowtime().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLWeightLateness(){
        return this.getWeightLateness().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLWeightMakespan(){
        return this.getWeightMakespan().toString();
    }

/**
 * find the same object in another dataset
 * @param a AbstractSolverProperty item we are looking for
 * @param bList List<AbstractSolverProperty> list of items in which we are searching
 * @return AbstractSolverProperty entry of list b which is applicationSame() to a
*/

    public static AbstractSolverProperty find(AbstractSolverProperty a, List<AbstractSolverProperty> bList){
        for(AbstractSolverProperty b : bList){
            if (b.applicationSame(a)){
                return b;
            }
        }
        return null;
    }

/**
 * find an object from its name; returns null if no such item exists
 * it is not defined which object is returned if multiple have the same name
 * @param base  dataset in which we are searching
 * @param name AbstractSolverProperty name of the object we are looking for
 * @return AbstractSolverProperty entry of the dataset with the given name; otherwise null
*/

    public static AbstractSolverProperty findByName(ApplicationDataset base, String name){
        for(AbstractSolverProperty a:base.getListAbstractSolverProperty()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        return null;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return AbstractSolverProperty first entry in the dataset of this type; null if that does not exists
*/

    public static AbstractSolverProperty findFirst(ApplicationDataset base){
        if (base.getListAbstractSolverProperty().isEmpty()) {
            return null;
        }
        return base.getListAbstractSolverProperty().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return AbstractSolverProperty some entry in the dataset of this type; null if that does not exists
*/

    public static AbstractSolverProperty findAny(ApplicationDataset base){
        int size=base.getListAbstractSolverProperty().size();
        if (size > 0) {
             return base.getListAbstractSolverProperty().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return AbstractSolverProperty last entry in the dataset of this type; null if that does not exists
*/

    public static AbstractSolverProperty findLast(ApplicationDataset base){
        int size=base.getListAbstractSolverProperty().size();
        if (size > 0) {
             return base.getListAbstractSolverProperty().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b AbstractSolverProperty compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(AbstractSolverProperty b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b AbstractSolverProperty compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(AbstractSolverProperty b){
      if(!this.getDescription().equals(b.getDescription())){
         System.out.println("Description");
        }
      if(!this.getEnforceCumulative().equals(b.getEnforceCumulative())){
         System.out.println("EnforceCumulative");
        }
      if(!this.getEnforceDowntime().equals(b.getEnforceDowntime())){
         System.out.println("EnforceDowntime");
        }
      if(!this.getEnforceDueDate().equals(b.getEnforceDueDate())){
         System.out.println("EnforceDueDate");
        }
      if(!this.getEnforceReleaseDate().equals(b.getEnforceReleaseDate())){
         System.out.println("EnforceReleaseDate");
        }
      if(!this.getEnforceWip().equals(b.getEnforceWip())){
         System.out.println("EnforceWip");
        }
      if(!this.getLabel().equals(b.getLabel())){
         System.out.println("Label");
        }
      if(!this.getModelType().equals(b.getModelType())){
         System.out.println("ModelType");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getNrThreads().equals(b.getNrThreads())){
         System.out.println("NrThreads");
        }
      if(!this.getObjectiveType().equals(b.getObjectiveType())){
         System.out.println("ObjectiveType");
        }
      if(!this.getProducePDF().equals(b.getProducePDF())){
         System.out.println("ProducePDF");
        }
      if(!this.getProduceReport().equals(b.getProduceReport())){
         System.out.println("ProduceReport");
        }
      if(!this.getRemoveSolution().equals(b.getRemoveSolution())){
         System.out.println("RemoveSolution");
        }
      if(!this.getSeed().equals(b.getSeed())){
         System.out.println("Seed");
        }
      if(!this.getSolverBackend().equals(b.getSolverBackend())){
         System.out.println("SolverBackend");
        }
      if(!this.getStartDateTime().applicationEqual(b.getStartDateTime())){
         System.out.println("StartDateTime");
        }
      if(!this.getTimeout().equals(b.getTimeout())){
         System.out.println("Timeout");
        }
      if(!this.getWeightEarliness().equals(b.getWeightEarliness())){
         System.out.println("WeightEarliness");
        }
      if(!this.getWeightFlowtime().equals(b.getWeightFlowtime())){
         System.out.println("WeightFlowtime");
        }
      if(!this.getWeightLateness().equals(b.getWeightLateness())){
         System.out.println("WeightLateness");
        }
      if(!this.getWeightMakespan().equals(b.getWeightMakespan())){
         System.out.println("WeightMakespan");
        }
        return  this.getDescription().equals(b.getDescription()) &&
          this.getEnforceCumulative().equals(b.getEnforceCumulative()) &&
          this.getEnforceDowntime().equals(b.getEnforceDowntime()) &&
          this.getEnforceDueDate().equals(b.getEnforceDueDate()) &&
          this.getEnforceReleaseDate().equals(b.getEnforceReleaseDate()) &&
          this.getEnforceWip().equals(b.getEnforceWip()) &&
          this.getLabel().equals(b.getLabel()) &&
          this.getModelType().equals(b.getModelType()) &&
          this.getName().equals(b.getName()) &&
          this.getNrThreads().equals(b.getNrThreads()) &&
          this.getObjectiveType().equals(b.getObjectiveType()) &&
          this.getProducePDF().equals(b.getProducePDF()) &&
          this.getProduceReport().equals(b.getProduceReport()) &&
          this.getRemoveSolution().equals(b.getRemoveSolution()) &&
          this.getSeed().equals(b.getSeed()) &&
          this.getSolverBackend().equals(b.getSolverBackend()) &&
          this.getStartDateTime().applicationEqual(b.getStartDateTime()) &&
          this.getTimeout().equals(b.getTimeout()) &&
          this.getWeightEarliness().equals(b.getWeightEarliness()) &&
          this.getWeightFlowtime().equals(b.getWeightFlowtime()) &&
          this.getWeightLateness().equals(b.getWeightLateness()) &&
          this.getWeightMakespan().equals(b.getWeightMakespan());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","AbstractSolverProperty",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
    }

/**
 *  This method states if the class depends on the solver.
 *
*/

    public static Boolean isSolverDependent(){
        return false;
    }

   public List<ApplicationObjectInterface> getFeasibleValues(ApplicationDatasetInterface base,String attrName){
      return null;
   }

}

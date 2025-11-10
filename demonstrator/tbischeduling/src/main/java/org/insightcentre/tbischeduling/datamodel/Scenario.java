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
import org.insightcentre.tbischeduling.datamodel.AbstractGanttProperty;
import org.insightcentre.tbischeduling.datamodel.GanttProperty;
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
import org.insightcentre.tbischeduling.datamodel.Setup;
import org.insightcentre.tbischeduling.datamodel.SetupType;
import org.insightcentre.tbischeduling.datamodel.SetupMatrix;
import org.insightcentre.tbischeduling.datamodel.Transport;
import org.insightcentre.tbischeduling.datamodel.TransportMatrix;
import org.insightcentre.tbischeduling.datamodel.SolutionSummary;
import org.insightcentre.tbischeduling.datamodel.LowerBound;
import org.insightcentre.tbischeduling.datamodel.JobLowerBound;
import org.insightcentre.tbischeduling.datamodel.DisjunctiveLowerBound;
import org.insightcentre.tbischeduling.datamodel.CumulativeLowerBound;
import org.insightcentre.tbischeduling.datamodel.MachineGroupLowerBound;
import org.insightcentre.tbischeduling.datamodel.PlacedRectangle;
import org.insightcentre.tbischeduling.datamodel.DifferenceType;
import org.insightcentre.tbischeduling.datamodel.WarningType;
import org.insightcentre.tbischeduling.datamodel.SequenceType;
import org.insightcentre.tbischeduling.datamodel.Severity;
import org.insightcentre.tbischeduling.datamodel.ModelType;
import org.insightcentre.tbischeduling.datamodel.SolverBackend;
import org.insightcentre.tbischeduling.datamodel.SolverStatus;
import org.insightcentre.tbischeduling.datamodel.SolutionStatus;
import org.insightcentre.tbischeduling.datamodel.ObjectiveType;
import org.insightcentre.tbischeduling.datamodel.ResourceModel;
import org.insightcentre.tbischeduling.datamodel.DurationModel;
import org.insightcentre.tbischeduling.datamodel.ColorBy;
import org.insightcentre.tbischeduling.datamodel.TaskLabel;
import org.insightcentre.tbischeduling.datamodel.JobOrder;
import org.insightcentre.tbischeduling.datamodel.ResourceChoice;
import org.insightcentre.tbischeduling.datamodel.LineChoice;
import org.insightcentre.tbischeduling.datamodel.DatesDisplay;
import org.insightcentre.tbischeduling.datamodel.ResourceZoom;
import org.insightcentre.tbischeduling.datamodel.TimingDisplay;
import org.insightcentre.tbischeduling.datamodel.DurationDisplay;
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
 * This class describes the dataset for the application. Typically there is a single instance of this class in the application at all times.
 * @author generated
*/

public  class Scenario extends ApplicationDataset{
/**
 *  
 *
*/

    public String dataFile;

/**
 *  
 *
*/

    public Double dataFileVersionNumber;

/**
 *  
 *
*/

    public DataGeneratorProperty dataGeneratorProperty;

/**
 *  
 *
*/

    public Double ganttLineHeight;

/**
 *  
 *
*/

    public Integer ganttLinesPerPage;

/**
 *  
 *
*/

    public GanttProperty ganttProperty;

/**
 *  
 *
*/

    public Integer ganttWidth;

/**
 *  
 *
*/

    public Boolean hasCumulative;

    private transient BooleanProperty hasCumulativeWrapper;

/**
 *  
 *
*/

    public Boolean hasDisjunctive;

    private transient BooleanProperty hasDisjunctiveWrapper;

/**
 *  
 *
*/

    public Boolean hasDowntime;

    private transient BooleanProperty hasDowntimeWrapper;

/**
 *  
 *
*/

    public Boolean hasDueDate;

    private transient BooleanProperty hasDueDateWrapper;

/**
 *  
 *
*/

    public Boolean hasReleaseDate;

    private transient BooleanProperty hasReleaseDateWrapper;

/**
 *  
 *
*/

    public Boolean hasSetupTime;

    private transient BooleanProperty hasSetupTimeWrapper;

/**
 *  
 *
*/

    public Boolean hasTransportTime;

    private transient BooleanProperty hasTransportTimeWrapper;

/**
 *  
 *
*/

    public Boolean hasWiP;

    private transient BooleanProperty hasWiPWrapper;

/**
 *  
 *
*/

    public String homeDir;

/**
 *  
 *
*/

    public Integer horizon;

/**
 *  
 *
*/

    public SolverProperty solverProperty;

/**
 *  
 *
*/

    public DateTime startDateTime;

/**
 *  
 *
*/

    public Integer timeResolution;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public Scenario(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public Scenario(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setDataFile("");
        setDataFileVersionNumber(0.0);
        setDataGeneratorProperty(null);
        setGanttLineHeight(0.0);
        setGanttLinesPerPage(0);
        setGanttProperty(null);
        setGanttWidth(0);
        setHasCumulative(true);
        setHasDisjunctive(true);
        setHasDowntime(true);
        setHasDueDate(true);
        setHasReleaseDate(true);
        setHasSetupTime(true);
        setHasTransportTime(true);
        setHasWiP(true);
        setHomeDir("");
        setHorizon(0);
        setSolverProperty(null);
        setStartDateTime(new DateTime());
        setTimeResolution(0);
        addScenario(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public Scenario(Boolean dirty,
            Integer id,
            String name,
            Boolean valid,
            String dataFile,
            Double dataFileVersionNumber,
            DataGeneratorProperty dataGeneratorProperty,
            Double ganttLineHeight,
            Integer ganttLinesPerPage,
            GanttProperty ganttProperty,
            Integer ganttWidth,
            Boolean hasCumulative,
            Boolean hasDisjunctive,
            Boolean hasDowntime,
            Boolean hasDueDate,
            Boolean hasReleaseDate,
            Boolean hasSetupTime,
            Boolean hasTransportTime,
            Boolean hasWiP,
            String homeDir,
            Integer horizon,
            SolverProperty solverProperty,
            DateTime startDateTime,
            Integer timeResolution){
        super(dirty,
            id,
            name,
            valid);
        setDataFile(dataFile);
        setDataFileVersionNumber(dataFileVersionNumber);
        setDataGeneratorProperty(dataGeneratorProperty);
        setGanttLineHeight(ganttLineHeight);
        setGanttLinesPerPage(ganttLinesPerPage);
        setGanttProperty(ganttProperty);
        setGanttWidth(ganttWidth);
        setHasCumulative(hasCumulative);
        setHasDisjunctive(hasDisjunctive);
        setHasDowntime(hasDowntime);
        setHasDueDate(hasDueDate);
        setHasReleaseDate(hasReleaseDate);
        setHasSetupTime(hasSetupTime);
        setHasTransportTime(hasTransportTime);
        setHasWiP(hasWiP);
        setHomeDir(homeDir);
        setHorizon(horizon);
        setSolverProperty(solverProperty);
        setStartDateTime(startDateTime);
        setTimeResolution(timeResolution);
        addScenario(this);
    }

    public Scenario(Scenario other){
        this(other.dirty,
            other.id,
            other.name,
            other.valid,
            other.dataFile,
            other.dataFileVersionNumber,
            other.dataGeneratorProperty,
            other.ganttLineHeight,
            other.ganttLinesPerPage,
            other.ganttProperty,
            other.ganttWidth,
            other.hasCumulative,
            other.hasDisjunctive,
            other.hasDowntime,
            other.hasDueDate,
            other.hasReleaseDate,
            other.hasSetupTime,
            other.hasTransportTime,
            other.hasWiP,
            other.homeDir,
            other.horizon,
            other.solverProperty,
            other.startDateTime,
            other.timeResolution);
    }

    public Boolean remove(){
        // ignored, you can not remove a dataset like this
        return true;
    }

/**
 *  get attribute dataFile
 *
 * @return String
*/

    public String getDataFile(){
        return this.dataFile;
    }

/**
 *  get attribute dataFileVersionNumber
 *
 * @return Double
*/

    public Double getDataFileVersionNumber(){
        return this.dataFileVersionNumber;
    }

/**
 *  get attribute dataGeneratorProperty
 *
 * @return DataGeneratorProperty
*/

    public DataGeneratorProperty getDataGeneratorProperty(){
        return this.dataGeneratorProperty;
    }

/**
 *  get attribute ganttLineHeight
 *
 * @return Double
*/

    public Double getGanttLineHeight(){
        return this.ganttLineHeight;
    }

/**
 *  get attribute ganttLinesPerPage
 *
 * @return Integer
*/

    public Integer getGanttLinesPerPage(){
        return this.ganttLinesPerPage;
    }

/**
 *  get attribute ganttProperty
 *
 * @return GanttProperty
*/

    public GanttProperty getGanttProperty(){
        return this.ganttProperty;
    }

/**
 *  get attribute ganttWidth
 *
 * @return Integer
*/

    public Integer getGanttWidth(){
        return this.ganttWidth;
    }

/**
 *  get attribute hasCumulative
 *
 * @return Boolean
*/

    public Boolean getHasCumulative(){
        return this.hasCumulative;
    }

    public BooleanProperty hasCumulativeWrapperProperty() {
        if (hasCumulativeWrapper == null) {
            hasCumulativeWrapper = new SimpleBooleanProperty();
        }
        hasCumulativeWrapper.set(hasCumulative);
        return hasCumulativeWrapper;
    }

/**
 *  get attribute hasDisjunctive
 *
 * @return Boolean
*/

    public Boolean getHasDisjunctive(){
        return this.hasDisjunctive;
    }

    public BooleanProperty hasDisjunctiveWrapperProperty() {
        if (hasDisjunctiveWrapper == null) {
            hasDisjunctiveWrapper = new SimpleBooleanProperty();
        }
        hasDisjunctiveWrapper.set(hasDisjunctive);
        return hasDisjunctiveWrapper;
    }

/**
 *  get attribute hasDowntime
 *
 * @return Boolean
*/

    public Boolean getHasDowntime(){
        return this.hasDowntime;
    }

    public BooleanProperty hasDowntimeWrapperProperty() {
        if (hasDowntimeWrapper == null) {
            hasDowntimeWrapper = new SimpleBooleanProperty();
        }
        hasDowntimeWrapper.set(hasDowntime);
        return hasDowntimeWrapper;
    }

/**
 *  get attribute hasDueDate
 *
 * @return Boolean
*/

    public Boolean getHasDueDate(){
        return this.hasDueDate;
    }

    public BooleanProperty hasDueDateWrapperProperty() {
        if (hasDueDateWrapper == null) {
            hasDueDateWrapper = new SimpleBooleanProperty();
        }
        hasDueDateWrapper.set(hasDueDate);
        return hasDueDateWrapper;
    }

/**
 *  get attribute hasReleaseDate
 *
 * @return Boolean
*/

    public Boolean getHasReleaseDate(){
        return this.hasReleaseDate;
    }

    public BooleanProperty hasReleaseDateWrapperProperty() {
        if (hasReleaseDateWrapper == null) {
            hasReleaseDateWrapper = new SimpleBooleanProperty();
        }
        hasReleaseDateWrapper.set(hasReleaseDate);
        return hasReleaseDateWrapper;
    }

/**
 *  get attribute hasSetupTime
 *
 * @return Boolean
*/

    public Boolean getHasSetupTime(){
        return this.hasSetupTime;
    }

    public BooleanProperty hasSetupTimeWrapperProperty() {
        if (hasSetupTimeWrapper == null) {
            hasSetupTimeWrapper = new SimpleBooleanProperty();
        }
        hasSetupTimeWrapper.set(hasSetupTime);
        return hasSetupTimeWrapper;
    }

/**
 *  get attribute hasTransportTime
 *
 * @return Boolean
*/

    public Boolean getHasTransportTime(){
        return this.hasTransportTime;
    }

    public BooleanProperty hasTransportTimeWrapperProperty() {
        if (hasTransportTimeWrapper == null) {
            hasTransportTimeWrapper = new SimpleBooleanProperty();
        }
        hasTransportTimeWrapper.set(hasTransportTime);
        return hasTransportTimeWrapper;
    }

/**
 *  get attribute hasWiP
 *
 * @return Boolean
*/

    public Boolean getHasWiP(){
        return this.hasWiP;
    }

    public BooleanProperty hasWiPWrapperProperty() {
        if (hasWiPWrapper == null) {
            hasWiPWrapper = new SimpleBooleanProperty();
        }
        hasWiPWrapper.set(hasWiP);
        return hasWiPWrapper;
    }

/**
 *  get attribute homeDir
 *
 * @return String
*/

    public String getHomeDir(){
        return this.homeDir;
    }

/**
 *  get attribute horizon
 *
 * @return Integer
*/

    public Integer getHorizon(){
        return this.horizon;
    }

/**
 *  get attribute solverProperty
 *
 * @return SolverProperty
*/

    public SolverProperty getSolverProperty(){
        return this.solverProperty;
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
 *  get attribute timeResolution
 *
 * @return Integer
*/

    public Integer getTimeResolution(){
        return this.timeResolution;
    }

/**
 *  set attribute dataFile, mark dataset as dirty, mark dataset as not valid
@param dataFile String
 *
*/

    public void setDataFile(String dataFile){
        this.dataFile = dataFile;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute dataFileVersionNumber, mark dataset as dirty, mark dataset as not valid
@param dataFileVersionNumber Double
 *
*/

    public void setDataFileVersionNumber(Double dataFileVersionNumber){
        this.dataFileVersionNumber = dataFileVersionNumber;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute dataGeneratorProperty, mark dataset as dirty, mark dataset as not valid
@param dataGeneratorProperty DataGeneratorProperty
 *
*/

    public void setDataGeneratorProperty(DataGeneratorProperty dataGeneratorProperty){
        this.dataGeneratorProperty = dataGeneratorProperty;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute ganttLineHeight, mark dataset as dirty, mark dataset as not valid
@param ganttLineHeight Double
 *
*/

    public void setGanttLineHeight(Double ganttLineHeight){
        this.ganttLineHeight = ganttLineHeight;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute ganttLinesPerPage, mark dataset as dirty, mark dataset as not valid
@param ganttLinesPerPage Integer
 *
*/

    public void setGanttLinesPerPage(Integer ganttLinesPerPage){
        this.ganttLinesPerPage = ganttLinesPerPage;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute ganttProperty, mark dataset as dirty, mark dataset as not valid
@param ganttProperty GanttProperty
 *
*/

    public void setGanttProperty(GanttProperty ganttProperty){
        this.ganttProperty = ganttProperty;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute ganttWidth, mark dataset as dirty, mark dataset as not valid
@param ganttWidth Integer
 *
*/

    public void setGanttWidth(Integer ganttWidth){
        this.ganttWidth = ganttWidth;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute hasCumulative, mark dataset as dirty, mark dataset as not valid
@param hasCumulative Boolean
 *
*/

    public void setHasCumulative(Boolean hasCumulative){
        this.hasCumulative = hasCumulative;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute hasDisjunctive, mark dataset as dirty, mark dataset as not valid
@param hasDisjunctive Boolean
 *
*/

    public void setHasDisjunctive(Boolean hasDisjunctive){
        this.hasDisjunctive = hasDisjunctive;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute hasDowntime, mark dataset as dirty, mark dataset as not valid
@param hasDowntime Boolean
 *
*/

    public void setHasDowntime(Boolean hasDowntime){
        this.hasDowntime = hasDowntime;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute hasDueDate, mark dataset as dirty, mark dataset as not valid
@param hasDueDate Boolean
 *
*/

    public void setHasDueDate(Boolean hasDueDate){
        this.hasDueDate = hasDueDate;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute hasReleaseDate, mark dataset as dirty, mark dataset as not valid
@param hasReleaseDate Boolean
 *
*/

    public void setHasReleaseDate(Boolean hasReleaseDate){
        this.hasReleaseDate = hasReleaseDate;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute hasSetupTime, mark dataset as dirty, mark dataset as not valid
@param hasSetupTime Boolean
 *
*/

    public void setHasSetupTime(Boolean hasSetupTime){
        this.hasSetupTime = hasSetupTime;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute hasTransportTime, mark dataset as dirty, mark dataset as not valid
@param hasTransportTime Boolean
 *
*/

    public void setHasTransportTime(Boolean hasTransportTime){
        this.hasTransportTime = hasTransportTime;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute hasWiP, mark dataset as dirty, mark dataset as not valid
@param hasWiP Boolean
 *
*/

    public void setHasWiP(Boolean hasWiP){
        this.hasWiP = hasWiP;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute homeDir, mark dataset as dirty, mark dataset as not valid
@param homeDir String
 *
*/

    public void setHomeDir(String homeDir){
        this.homeDir = homeDir;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute horizon, mark dataset as dirty, mark dataset as not valid
@param horizon Integer
 *
*/

    public void setHorizon(Integer horizon){
        this.horizon = horizon;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute solverProperty, mark dataset as dirty, mark dataset as not valid
@param solverProperty SolverProperty
 *
*/

    public void setSolverProperty(SolverProperty solverProperty){
        this.solverProperty = solverProperty;
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
 *  set attribute timeResolution, mark dataset as dirty, mark dataset as not valid
@param timeResolution Integer
 *
*/

    public void setTimeResolution(Integer timeResolution){
        this.timeResolution = timeResolution;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute ganttLinesPerPage, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incGanttLinesPerPage(){
        this.ganttLinesPerPage++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute ganttWidth, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incGanttWidth(){
        this.ganttWidth++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute horizon, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incHorizon(){
        this.horizon++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute timeResolution, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incTimeResolution(){
        this.timeResolution++;
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
        return getDirty()+ " " +getId()+ " " +getName()+ " " +getValid()+ " " +getDataFile()+ " " +getDataFileVersionNumber()+ " " +getDataGeneratorProperty().toColumnString()+ " " +getGanttLineHeight()+ " " +getGanttLinesPerPage()+ " " +getGanttProperty().toColumnString()+ " " +getGanttWidth()+ " " +getHasCumulative()+ " " +getHasDisjunctive()+ " " +getHasDowntime()+ " " +getHasDueDate()+ " " +getHasReleaseDate()+ " " +getHasSetupTime()+ " " +getHasTransportTime()+ " " +getHasWiP()+ " " +getHomeDir()+ " " +getHorizon()+ " " +getSolverProperty().toColumnString()+ " " +getStartDateTime()+ " " +getTimeResolution();
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
         out.println("<scenario "+ " dirty=\""+toXMLDirty()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " valid=\""+toXMLValid()+"\""+
            " dataFile=\""+toXMLDataFile()+"\""+
            " dataFileVersionNumber=\""+toXMLDataFileVersionNumber()+"\""+
            " dataGeneratorProperty=\""+toXMLDataGeneratorProperty()+"\""+
            " ganttLineHeight=\""+toXMLGanttLineHeight()+"\""+
            " ganttLinesPerPage=\""+toXMLGanttLinesPerPage()+"\""+
            " ganttProperty=\""+toXMLGanttProperty()+"\""+
            " ganttWidth=\""+toXMLGanttWidth()+"\""+
            " hasCumulative=\""+toXMLHasCumulative()+"\""+
            " hasDisjunctive=\""+toXMLHasDisjunctive()+"\""+
            " hasDowntime=\""+toXMLHasDowntime()+"\""+
            " hasDueDate=\""+toXMLHasDueDate()+"\""+
            " hasReleaseDate=\""+toXMLHasReleaseDate()+"\""+
            " hasSetupTime=\""+toXMLHasSetupTime()+"\""+
            " hasTransportTime=\""+toXMLHasTransportTime()+"\""+
            " hasWiP=\""+toXMLHasWiP()+"\""+
            " homeDir=\""+toXMLHomeDir()+"\""+
            " horizon=\""+toXMLHorizon()+"\""+
            " solverProperty=\""+toXMLSolverProperty()+"\""+
            " startDateTime=\""+toXMLStartDateTime()+"\""+
            " timeResolution=\""+toXMLTimeResolution()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDataFile(){
        return this.safeXML(getDataFile());
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDataFileVersionNumber(){
        return this.getDataFileVersionNumber().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDataGeneratorProperty(){
        return "ID_"+this.getDataGeneratorProperty().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLGanttLineHeight(){
        return this.getGanttLineHeight().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLGanttLinesPerPage(){
        return this.getGanttLinesPerPage().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLGanttProperty(){
        return "ID_"+this.getGanttProperty().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLGanttWidth(){
        return this.getGanttWidth().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLHasCumulative(){
        return this.getHasCumulative().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLHasDisjunctive(){
        return this.getHasDisjunctive().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLHasDowntime(){
        return this.getHasDowntime().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLHasDueDate(){
        return this.getHasDueDate().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLHasReleaseDate(){
        return this.getHasReleaseDate().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLHasSetupTime(){
        return this.getHasSetupTime().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLHasTransportTime(){
        return this.getHasTransportTime().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLHasWiP(){
        return this.getHasWiP().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLHomeDir(){
        return this.safeXML(getHomeDir());
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLHorizon(){
        return this.getHorizon().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLSolverProperty(){
        return "ID_"+this.getSolverProperty().getId().toString();
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

    String toXMLTimeResolution(){
        return this.getTimeResolution().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>Scenario</th>"+"<th>Name</th>"+"<th>Dirty</th>"+"<th>Valid</th>"+"<th>DataFileVersionNumber</th>"+"<th>DataFile</th>"+"<th>HomeDir</th>"+"<th>StartDateTime</th>"+"<th>Horizon</th>"+"<th>TimeResolution</th>"+"<th>GanttWidth</th>"+"<th>GanttLinesPerPage</th>"+"<th>GanttLineHeight</th>"+"<th>SolverProperty</th>"+"<th>DataGeneratorProperty</th>"+"<th>GanttProperty</th>"+"<th>HasReleaseDate</th>"+"<th>HasDueDate</th>"+"<th>HasDisjunctive</th>"+"<th>HasCumulative</th>"+"<th>HasWiP</th>"+"<th>HasDowntime</th>"+"<th>HasSetupTime</th>"+"<th>HasTransportTime</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getDirty()+"</td>"+ " " +"<td>"+getValid()+"</td>"+ " " +"<td>"+getDataFileVersionNumber()+"</td>"+ " " +"<td>"+getDataFile()+"</td>"+ " " +"<td>"+getHomeDir()+"</td>"+ " " +"<td>"+getStartDateTime()+"</td>"+ " " +"<td>"+getHorizon()+"</td>"+ " " +"<td>"+getTimeResolution()+"</td>"+ " " +"<td>"+getGanttWidth()+"</td>"+ " " +"<td>"+getGanttLinesPerPage()+"</td>"+ " " +"<td>"+getGanttLineHeight()+"</td>"+ " " +"<td>"+getSolverProperty().toColumnString()+"</td>"+ " " +"<td>"+getDataGeneratorProperty().toColumnString()+"</td>"+ " " +"<td>"+getGanttProperty().toColumnString()+"</td>"+ " " +"<td>"+getHasReleaseDate()+"</td>"+ " " +"<td>"+getHasDueDate()+"</td>"+ " " +"<td>"+getHasDisjunctive()+"</td>"+ " " +"<td>"+getHasCumulative()+"</td>"+ " " +"<td>"+getHasWiP()+"</td>"+ " " +"<td>"+getHasDowntime()+"</td>"+ " " +"<td>"+getHasSetupTime()+"</td>"+ " " +"<td>"+getHasTransportTime()+"</td>"+"</tr>";
    }

/**
 * compare object to another of the same type, create ApplicationWarnings if they differ
*/

    public void compare(Scenario compare){
        System.out.println("Calling compare super");
        super.compare(compare);
        System.out.println("Comparing Scenario");
        if (!this.applicationEqual(compare)) {
            System.out.println("Scenarios differ:"+this+" "+compare);
            new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Scenario A",this.toString(),DifferenceType.DIFFERA);
            new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Scenario B",compare.toString(),DifferenceType.DIFFERB);
        }
        System.out.println("Done Compare");
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b Scenario compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(Scenario b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b Scenario compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(Scenario b){
      if(!this.getDataFile().equals(b.getDataFile())){
         System.out.println("DataFile");
        }
      if(!this.getDataFileVersionNumber().equals(b.getDataFileVersionNumber())){
         System.out.println("DataFileVersionNumber");
        }
      if(!this.getDataGeneratorProperty().applicationSame(b.getDataGeneratorProperty())){
         System.out.println("DataGeneratorProperty");
        }
      if(!this.getGanttLineHeight().equals(b.getGanttLineHeight())){
         System.out.println("GanttLineHeight");
        }
      if(!this.getGanttLinesPerPage().equals(b.getGanttLinesPerPage())){
         System.out.println("GanttLinesPerPage");
        }
      if(!this.getGanttProperty().applicationSame(b.getGanttProperty())){
         System.out.println("GanttProperty");
        }
      if(!this.getGanttWidth().equals(b.getGanttWidth())){
         System.out.println("GanttWidth");
        }
      if(!this.getHasCumulative().equals(b.getHasCumulative())){
         System.out.println("HasCumulative");
        }
      if(!this.getHasDisjunctive().equals(b.getHasDisjunctive())){
         System.out.println("HasDisjunctive");
        }
      if(!this.getHasDowntime().equals(b.getHasDowntime())){
         System.out.println("HasDowntime");
        }
      if(!this.getHasDueDate().equals(b.getHasDueDate())){
         System.out.println("HasDueDate");
        }
      if(!this.getHasReleaseDate().equals(b.getHasReleaseDate())){
         System.out.println("HasReleaseDate");
        }
      if(!this.getHasSetupTime().equals(b.getHasSetupTime())){
         System.out.println("HasSetupTime");
        }
      if(!this.getHasTransportTime().equals(b.getHasTransportTime())){
         System.out.println("HasTransportTime");
        }
      if(!this.getHasWiP().equals(b.getHasWiP())){
         System.out.println("HasWiP");
        }
      if(!this.getHomeDir().equals(b.getHomeDir())){
         System.out.println("HomeDir");
        }
      if(!this.getHorizon().equals(b.getHorizon())){
         System.out.println("Horizon");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getSolverProperty().applicationSame(b.getSolverProperty())){
         System.out.println("SolverProperty");
        }
      if(!this.getStartDateTime().applicationEqual(b.getStartDateTime())){
         System.out.println("StartDateTime");
        }
      if(!this.getTimeResolution().equals(b.getTimeResolution())){
         System.out.println("TimeResolution");
        }
        return  this.getDataFile().equals(b.getDataFile()) &&
          this.getDataFileVersionNumber().equals(b.getDataFileVersionNumber()) &&
          this.getDataGeneratorProperty().applicationSame(b.getDataGeneratorProperty()) &&
          this.getGanttLineHeight().equals(b.getGanttLineHeight()) &&
          this.getGanttLinesPerPage().equals(b.getGanttLinesPerPage()) &&
          this.getGanttProperty().applicationSame(b.getGanttProperty()) &&
          this.getGanttWidth().equals(b.getGanttWidth()) &&
          this.getHasCumulative().equals(b.getHasCumulative()) &&
          this.getHasDisjunctive().equals(b.getHasDisjunctive()) &&
          this.getHasDowntime().equals(b.getHasDowntime()) &&
          this.getHasDueDate().equals(b.getHasDueDate()) &&
          this.getHasReleaseDate().equals(b.getHasReleaseDate()) &&
          this.getHasSetupTime().equals(b.getHasSetupTime()) &&
          this.getHasTransportTime().equals(b.getHasTransportTime()) &&
          this.getHasWiP().equals(b.getHasWiP()) &&
          this.getHomeDir().equals(b.getHomeDir()) &&
          this.getHorizon().equals(b.getHorizon()) &&
          this.getName().equals(b.getName()) &&
          this.getSolverProperty().applicationSame(b.getSolverProperty()) &&
          this.getStartDateTime().applicationEqual(b.getStartDateTime()) &&
          this.getTimeResolution().equals(b.getTimeResolution());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getName().length() == 0) {
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"name","Scenario",(getName()==null?"null":getName().toString()),"",WarningType.NOTEMPTY);
        }
        if (getDataGeneratorProperty() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"dataGeneratorProperty","Scenario",(getDataGeneratorProperty()==null?"null":getDataGeneratorProperty().toString()),"",WarningType.NOTNULL);
        }
        if (getGanttProperty() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"ganttProperty","Scenario",(getGanttProperty()==null?"null":getGanttProperty().toString()),"",WarningType.NOTNULL);
        }
        if (getSolverProperty() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"solverProperty","Scenario",(getSolverProperty()==null?"null":getSolverProperty().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class Scenario
    }

/**
 *  This method states if the class depends on the solver.
 *
*/

    public static Boolean isSolverDependent(){
        return false;
    }

   public List<ApplicationObjectInterface> getFeasibleValues(ApplicationDatasetInterface base,String attrName){
      if (attrName.equals("dataGeneratorProperty")){
         return (List) ((Scenario)base).getListDataGeneratorProperty();
      }
      if (attrName.equals("ganttProperty")){
         return (List) ((Scenario)base).getListGanttProperty();
      }
      if (attrName.equals("solverProperty")){
         return (List) ((Scenario)base).getListSolverProperty();
      }
      return null;
   }

}

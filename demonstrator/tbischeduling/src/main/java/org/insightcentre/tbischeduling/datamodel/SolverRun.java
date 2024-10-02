// licence details to be added
package org.insightcentre.tbischeduling.datamodel;
import org.insightcentre.tbischeduling.datamodel.ApplicationDataset;
import org.insightcentre.tbischeduling.datamodel.ApplicationObject;
import org.insightcentre.tbischeduling.datamodel.ApplicationDifference;
import org.insightcentre.tbischeduling.datamodel.ApplicationWarning;
import org.insightcentre.tbischeduling.datamodel.Scenario;
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
import org.insightcentre.tbischeduling.datamodel.Order;
import org.insightcentre.tbischeduling.datamodel.Job;
import org.insightcentre.tbischeduling.datamodel.Task;
import org.insightcentre.tbischeduling.datamodel.WiP;
import org.insightcentre.tbischeduling.datamodel.Downtime;
import org.insightcentre.tbischeduling.datamodel.SolverRun;
import org.insightcentre.tbischeduling.datamodel.Solution;
import org.insightcentre.tbischeduling.datamodel.JobAssignment;
import org.insightcentre.tbischeduling.datamodel.TaskAssignment;
import org.insightcentre.tbischeduling.datamodel.ResourceUtilization;
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

public  class SolverRun extends ApplicationObject{
/**
 *  
 *
*/

    public String description;

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

    public SolverStatus solverStatus;

/**
 *  
 *
*/

    public Double time;

/**
 *  
 *
*/

    public Integer timeout;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public SolverRun(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public SolverRun(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setDescription("");
        setEnforceDueDate(true);
        setEnforceReleaseDate(true);
        setLabel("");
        setModelType(null);
        setNrThreads(0);
        setObjectiveType(null);
        setRemoveSolution(true);
        setSeed(0);
        setSolverBackend(null);
        setSolverStatus(null);
        setTime(0.0);
        setTimeout(0);
        applicationDataset.addSolverRun(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public SolverRun(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            String description,
            Boolean enforceDueDate,
            Boolean enforceReleaseDate,
            String label,
            ModelType modelType,
            Integer nrThreads,
            ObjectiveType objectiveType,
            Boolean removeSolution,
            Integer seed,
            SolverBackend solverBackend,
            SolverStatus solverStatus,
            Double time,
            Integer timeout){
        super(applicationDataset,
            id,
            name);
        setDescription(description);
        setEnforceDueDate(enforceDueDate);
        setEnforceReleaseDate(enforceReleaseDate);
        setLabel(label);
        setModelType(modelType);
        setNrThreads(nrThreads);
        setObjectiveType(objectiveType);
        setRemoveSolution(removeSolution);
        setSeed(seed);
        setSolverBackend(solverBackend);
        setSolverStatus(solverStatus);
        setTime(time);
        setTimeout(timeout);
        applicationDataset.addSolverRun(this);
    }

    public SolverRun(SolverRun other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.description,
            other.enforceDueDate,
            other.enforceReleaseDate,
            other.label,
            other.modelType,
            other.nrThreads,
            other.objectiveType,
            other.removeSolution,
            other.seed,
            other.solverBackend,
            other.solverStatus,
            other.time,
            other.timeout);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        getApplicationDataset().cascadeSolutionSolverRun(this);
        return getApplicationDataset().removeSolverRun(this) && getApplicationDataset().removeApplicationObject(this);
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
 *  get attribute solverStatus
 *
 * @return SolverStatus
*/

    public SolverStatus getSolverStatus(){
        return this.solverStatus;
    }

/**
 *  get attribute time
 *
 * @return Double
*/

    public Double getTime(){
        return this.time;
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
 *  set attribute solverStatus, mark dataset as dirty, mark dataset as not valid
@param solverStatus SolverStatus
 *
*/

    public void setSolverStatus(SolverStatus solverStatus){
        this.solverStatus = solverStatus;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute time, mark dataset as dirty, mark dataset as not valid
@param time Double
 *
*/

    public void setTime(Double time){
        this.time = time;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getDescription()+ " " +getEnforceDueDate()+ " " +getEnforceReleaseDate()+ " " +getLabel()+ " " +getModelType()+ " " +getNrThreads()+ " " +getObjectiveType()+ " " +getRemoveSolution()+ " " +getSeed()+ " " +getSolverBackend()+ " " +getSolverStatus()+ " " +getTime()+ " " +getTimeout();
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
         out.println("<solverRun "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " description=\""+toXMLDescription()+"\""+
            " enforceDueDate=\""+toXMLEnforceDueDate()+"\""+
            " enforceReleaseDate=\""+toXMLEnforceReleaseDate()+"\""+
            " label=\""+toXMLLabel()+"\""+
            " modelType=\""+toXMLModelType()+"\""+
            " nrThreads=\""+toXMLNrThreads()+"\""+
            " objectiveType=\""+toXMLObjectiveType()+"\""+
            " removeSolution=\""+toXMLRemoveSolution()+"\""+
            " seed=\""+toXMLSeed()+"\""+
            " solverBackend=\""+toXMLSolverBackend()+"\""+
            " solverStatus=\""+toXMLSolverStatus()+"\""+
            " time=\""+toXMLTime()+"\""+
            " timeout=\""+toXMLTimeout()+"\""+" />");
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

    String toXMLSolverStatus(){
        return this.getSolverStatus().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLTime(){
        return this.getTime().toString();
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
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>SolverRun</th>"+"<th>Name</th>"+"<th>Label</th>"+"<th>Description</th>"+"<th>ModelType</th>"+"<th>SolverBackend</th>"+"<th>ObjectiveType</th>"+"<th>EnforceReleaseDate</th>"+"<th>EnforceDueDate</th>"+"<th>Timeout</th>"+"<th>NrThreads</th>"+"<th>Seed</th>"+"<th>RemoveSolution</th>"+"<th>SolverStatus</th>"+"<th>Time</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getLabel()+"</td>"+ " " +"<td>"+getDescription()+"</td>"+ " " +"<td>"+getModelType()+"</td>"+ " " +"<td>"+getSolverBackend()+"</td>"+ " " +"<td>"+getObjectiveType()+"</td>"+ " " +"<td>"+getEnforceReleaseDate()+"</td>"+ " " +"<td>"+getEnforceDueDate()+"</td>"+ " " +"<td>"+getTimeout()+"</td>"+ " " +"<td>"+getNrThreads()+"</td>"+ " " +"<td>"+getSeed()+"</td>"+ " " +"<td>"+getRemoveSolution()+"</td>"+ " " +"<td>"+getSolverStatus()+"</td>"+ " " +"<td>"+getTime()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a SolverRun item we are looking for
 * @param bList List<SolverRun> list of items in which we are searching
 * @return SolverRun entry of list b which is applicationSame() to a
*/

    public static SolverRun find(SolverRun a, List<SolverRun> bList){
        for(SolverRun b : bList){
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
 * @param name SolverRun name of the object we are looking for
 * @return SolverRun entry of the dataset with the given name; otherwise null
*/

    public static SolverRun findByName(ApplicationDataset base, String name){
        for(SolverRun a:base.getListSolverRun()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        return null;
    }

/**
 * find an object from its name; create new instance if no such item exists
 * it is not defined which object is returned if multiple have the same name
 * @param base  dataset in which we are searching
 * @param name SolverRun name of the object we are looking for
 * @return SolverRun entry of the dataset with the given name
*/

    public static SolverRun findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(SolverRun a:base.getListSolverRun()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        SolverRun res = new SolverRun(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return SolverRun first entry in the dataset of this type; null if that does not exists
*/

    public static SolverRun findFirst(ApplicationDataset base){
        if (base.getListSolverRun().isEmpty()) {
            return null;
        }
        return base.getListSolverRun().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return SolverRun some entry in the dataset of this type; null if that does not exists
*/

    public static SolverRun findAny(ApplicationDataset base){
        int size=base.getListSolverRun().size();
        if (size > 0) {
             return base.getListSolverRun().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return SolverRun last entry in the dataset of this type; null if that does not exists
*/

    public static SolverRun findLast(ApplicationDataset base){
        int size=base.getListSolverRun().size();
        if (size > 0) {
             return base.getListSolverRun().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b SolverRun compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(SolverRun b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b SolverRun compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(SolverRun b){
      if(!this.getDescription().equals(b.getDescription())){
         System.out.println("Description");
        }
      if(!this.getEnforceDueDate().equals(b.getEnforceDueDate())){
         System.out.println("EnforceDueDate");
        }
      if(!this.getEnforceReleaseDate().equals(b.getEnforceReleaseDate())){
         System.out.println("EnforceReleaseDate");
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
      if(!this.getRemoveSolution().equals(b.getRemoveSolution())){
         System.out.println("RemoveSolution");
        }
      if(!this.getSeed().equals(b.getSeed())){
         System.out.println("Seed");
        }
      if(!this.getSolverBackend().equals(b.getSolverBackend())){
         System.out.println("SolverBackend");
        }
      if(!this.getSolverStatus().equals(b.getSolverStatus())){
         System.out.println("SolverStatus");
        }
      if(!this.getTime().equals(b.getTime())){
         System.out.println("Time");
        }
      if(!this.getTimeout().equals(b.getTimeout())){
         System.out.println("Timeout");
        }
        return  this.getDescription().equals(b.getDescription()) &&
          this.getEnforceDueDate().equals(b.getEnforceDueDate()) &&
          this.getEnforceReleaseDate().equals(b.getEnforceReleaseDate()) &&
          this.getLabel().equals(b.getLabel()) &&
          this.getModelType().equals(b.getModelType()) &&
          this.getName().equals(b.getName()) &&
          this.getNrThreads().equals(b.getNrThreads()) &&
          this.getObjectiveType().equals(b.getObjectiveType()) &&
          this.getRemoveSolution().equals(b.getRemoveSolution()) &&
          this.getSeed().equals(b.getSeed()) &&
          this.getSolverBackend().equals(b.getSolverBackend()) &&
          this.getSolverStatus().equals(b.getSolverStatus()) &&
          this.getTime().equals(b.getTime()) &&
          this.getTimeout().equals(b.getTimeout());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","SolverRun",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class SolverRun
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

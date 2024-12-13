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
 * 
 * @author generated
*/

public  class SolutionSummary extends ApplicationObject{
/**
 *  
 *
*/

    public Double bound;

/**
 *  
 *
*/

    public Double gapPercent;

/**
 *  
 *
*/

    public String instance;

/**
 *  
 *
*/

    public Integer instanceNr;

/**
 *  
 *
*/

    public Integer makespan;

/**
 *  
 *
*/

    public Integer nrCumulatives;

/**
 *  
 *
*/

    public Integer nrJobs;

/**
 *  
 *
*/

    public Integer nrMachines;

/**
 *  
 *
*/

    public Integer nrTasks;

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

    public String variant;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public SolutionSummary(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public SolutionSummary(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setBound(0.0);
        setGapPercent(0.0);
        setInstance("");
        setInstanceNr(0);
        setMakespan(0);
        setNrCumulatives(0);
        setNrJobs(0);
        setNrMachines(0);
        setNrTasks(0);
        setSolverStatus(null);
        setTime(0.0);
        setVariant("");
        applicationDataset.addSolutionSummary(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public SolutionSummary(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Double bound,
            Double gapPercent,
            String instance,
            Integer instanceNr,
            Integer makespan,
            Integer nrCumulatives,
            Integer nrJobs,
            Integer nrMachines,
            Integer nrTasks,
            SolverStatus solverStatus,
            Double time,
            String variant){
        super(applicationDataset,
            id,
            name);
        setBound(bound);
        setGapPercent(gapPercent);
        setInstance(instance);
        setInstanceNr(instanceNr);
        setMakespan(makespan);
        setNrCumulatives(nrCumulatives);
        setNrJobs(nrJobs);
        setNrMachines(nrMachines);
        setNrTasks(nrTasks);
        setSolverStatus(solverStatus);
        setTime(time);
        setVariant(variant);
        applicationDataset.addSolutionSummary(this);
    }

    public SolutionSummary(SolutionSummary other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.bound,
            other.gapPercent,
            other.instance,
            other.instanceNr,
            other.makespan,
            other.nrCumulatives,
            other.nrJobs,
            other.nrMachines,
            other.nrTasks,
            other.solverStatus,
            other.time,
            other.variant);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeSolutionSummary(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute bound
 *
 * @return Double
*/

    public Double getBound(){
        return this.bound;
    }

/**
 *  get attribute gapPercent
 *
 * @return Double
*/

    public Double getGapPercent(){
        return this.gapPercent;
    }

/**
 *  get attribute instance
 *
 * @return String
*/

    public String getInstance(){
        return this.instance;
    }

/**
 *  get attribute instanceNr
 *
 * @return Integer
*/

    public Integer getInstanceNr(){
        return this.instanceNr;
    }

/**
 *  get attribute makespan
 *
 * @return Integer
*/

    public Integer getMakespan(){
        return this.makespan;
    }

/**
 *  get attribute nrCumulatives
 *
 * @return Integer
*/

    public Integer getNrCumulatives(){
        return this.nrCumulatives;
    }

/**
 *  get attribute nrJobs
 *
 * @return Integer
*/

    public Integer getNrJobs(){
        return this.nrJobs;
    }

/**
 *  get attribute nrMachines
 *
 * @return Integer
*/

    public Integer getNrMachines(){
        return this.nrMachines;
    }

/**
 *  get attribute nrTasks
 *
 * @return Integer
*/

    public Integer getNrTasks(){
        return this.nrTasks;
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
 *  get attribute variant
 *
 * @return String
*/

    public String getVariant(){
        return this.variant;
    }

/**
 *  set attribute bound, mark dataset as dirty, mark dataset as not valid
@param bound Double
 *
*/

    public void setBound(Double bound){
        this.bound = bound;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute gapPercent, mark dataset as dirty, mark dataset as not valid
@param gapPercent Double
 *
*/

    public void setGapPercent(Double gapPercent){
        this.gapPercent = gapPercent;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute instance, mark dataset as dirty, mark dataset as not valid
@param instance String
 *
*/

    public void setInstance(String instance){
        this.instance = instance;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute instanceNr, mark dataset as dirty, mark dataset as not valid
@param instanceNr Integer
 *
*/

    public void setInstanceNr(Integer instanceNr){
        this.instanceNr = instanceNr;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute makespan, mark dataset as dirty, mark dataset as not valid
@param makespan Integer
 *
*/

    public void setMakespan(Integer makespan){
        this.makespan = makespan;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute nrCumulatives, mark dataset as dirty, mark dataset as not valid
@param nrCumulatives Integer
 *
*/

    public void setNrCumulatives(Integer nrCumulatives){
        this.nrCumulatives = nrCumulatives;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute nrJobs, mark dataset as dirty, mark dataset as not valid
@param nrJobs Integer
 *
*/

    public void setNrJobs(Integer nrJobs){
        this.nrJobs = nrJobs;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute nrMachines, mark dataset as dirty, mark dataset as not valid
@param nrMachines Integer
 *
*/

    public void setNrMachines(Integer nrMachines){
        this.nrMachines = nrMachines;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute nrTasks, mark dataset as dirty, mark dataset as not valid
@param nrTasks Integer
 *
*/

    public void setNrTasks(Integer nrTasks){
        this.nrTasks = nrTasks;
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
 *  set attribute variant, mark dataset as dirty, mark dataset as not valid
@param variant String
 *
*/

    public void setVariant(String variant){
        this.variant = variant;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute instanceNr, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incInstanceNr(){
        this.instanceNr++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute makespan, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMakespan(){
        this.makespan++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nrCumulatives, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNrCumulatives(){
        this.nrCumulatives++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nrJobs, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNrJobs(){
        this.nrJobs++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nrMachines, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNrMachines(){
        this.nrMachines++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nrTasks, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNrTasks(){
        this.nrTasks++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getBound()+ " " +getGapPercent()+ " " +getInstance()+ " " +getInstanceNr()+ " " +getMakespan()+ " " +getNrCumulatives()+ " " +getNrJobs()+ " " +getNrMachines()+ " " +getNrTasks()+ " " +getSolverStatus()+ " " +getTime()+ " " +getVariant();
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
         out.println("<solutionSummary "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " bound=\""+toXMLBound()+"\""+
            " gapPercent=\""+toXMLGapPercent()+"\""+
            " instance=\""+toXMLInstance()+"\""+
            " instanceNr=\""+toXMLInstanceNr()+"\""+
            " makespan=\""+toXMLMakespan()+"\""+
            " nrCumulatives=\""+toXMLNrCumulatives()+"\""+
            " nrJobs=\""+toXMLNrJobs()+"\""+
            " nrMachines=\""+toXMLNrMachines()+"\""+
            " nrTasks=\""+toXMLNrTasks()+"\""+
            " solverStatus=\""+toXMLSolverStatus()+"\""+
            " time=\""+toXMLTime()+"\""+
            " variant=\""+toXMLVariant()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLBound(){
        return this.getBound().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLGapPercent(){
        return this.getGapPercent().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLInstance(){
        return this.safeXML(getInstance());
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLInstanceNr(){
        return this.getInstanceNr().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMakespan(){
        return this.getMakespan().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNrCumulatives(){
        return this.getNrCumulatives().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNrJobs(){
        return this.getNrJobs().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNrMachines(){
        return this.getNrMachines().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNrTasks(){
        return this.getNrTasks().toString();
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

    String toXMLVariant(){
        return this.safeXML(getVariant());
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>SolutionSummary</th>"+"<th>Name</th>"+"<th>Variant</th>"+"<th>Instance</th>"+"<th>InstanceNr</th>"+"<th>NrJobs</th>"+"<th>NrTasks</th>"+"<th>NrMachines</th>"+"<th>NrCumulatives</th>"+"<th>SolverStatus</th>"+"<th>Time</th>"+"<th>Makespan</th>"+"<th>Bound</th>"+"<th>GapPercent</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getVariant()+"</td>"+ " " +"<td>"+getInstance()+"</td>"+ " " +"<td>"+getInstanceNr()+"</td>"+ " " +"<td>"+getNrJobs()+"</td>"+ " " +"<td>"+getNrTasks()+"</td>"+ " " +"<td>"+getNrMachines()+"</td>"+ " " +"<td>"+getNrCumulatives()+"</td>"+ " " +"<td>"+getSolverStatus()+"</td>"+ " " +"<td>"+getTime()+"</td>"+ " " +"<td>"+getMakespan()+"</td>"+ " " +"<td>"+getBound()+"</td>"+ " " +"<td>"+getGapPercent()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a SolutionSummary item we are looking for
 * @param bList List<SolutionSummary> list of items in which we are searching
 * @return SolutionSummary entry of list b which is applicationSame() to a
*/

    public static SolutionSummary find(SolutionSummary a, List<SolutionSummary> bList){
        for(SolutionSummary b : bList){
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
 * @param name SolutionSummary name of the object we are looking for
 * @return SolutionSummary entry of the dataset with the given name; otherwise null
*/

    public static SolutionSummary findByName(ApplicationDataset base, String name){
        for(SolutionSummary a:base.getListSolutionSummary()) {
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
 * @param name SolutionSummary name of the object we are looking for
 * @return SolutionSummary entry of the dataset with the given name
*/

    public static SolutionSummary findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(SolutionSummary a:base.getListSolutionSummary()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        SolutionSummary res = new SolutionSummary(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return SolutionSummary first entry in the dataset of this type; null if that does not exists
*/

    public static SolutionSummary findFirst(ApplicationDataset base){
        if (base.getListSolutionSummary().isEmpty()) {
            return null;
        }
        return base.getListSolutionSummary().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return SolutionSummary some entry in the dataset of this type; null if that does not exists
*/

    public static SolutionSummary findAny(ApplicationDataset base){
        int size=base.getListSolutionSummary().size();
        if (size > 0) {
             return base.getListSolutionSummary().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return SolutionSummary last entry in the dataset of this type; null if that does not exists
*/

    public static SolutionSummary findLast(ApplicationDataset base){
        int size=base.getListSolutionSummary().size();
        if (size > 0) {
             return base.getListSolutionSummary().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b SolutionSummary compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(SolutionSummary b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b SolutionSummary compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(SolutionSummary b){
      if(!this.getBound().equals(b.getBound())){
         System.out.println("Bound");
        }
      if(!this.getGapPercent().equals(b.getGapPercent())){
         System.out.println("GapPercent");
        }
      if(!this.getInstance().equals(b.getInstance())){
         System.out.println("Instance");
        }
      if(!this.getInstanceNr().equals(b.getInstanceNr())){
         System.out.println("InstanceNr");
        }
      if(!this.getMakespan().equals(b.getMakespan())){
         System.out.println("Makespan");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getNrCumulatives().equals(b.getNrCumulatives())){
         System.out.println("NrCumulatives");
        }
      if(!this.getNrJobs().equals(b.getNrJobs())){
         System.out.println("NrJobs");
        }
      if(!this.getNrMachines().equals(b.getNrMachines())){
         System.out.println("NrMachines");
        }
      if(!this.getNrTasks().equals(b.getNrTasks())){
         System.out.println("NrTasks");
        }
      if(!this.getSolverStatus().equals(b.getSolverStatus())){
         System.out.println("SolverStatus");
        }
      if(!this.getTime().equals(b.getTime())){
         System.out.println("Time");
        }
      if(!this.getVariant().equals(b.getVariant())){
         System.out.println("Variant");
        }
        return  this.getBound().equals(b.getBound()) &&
          this.getGapPercent().equals(b.getGapPercent()) &&
          this.getInstance().equals(b.getInstance()) &&
          this.getInstanceNr().equals(b.getInstanceNr()) &&
          this.getMakespan().equals(b.getMakespan()) &&
          this.getName().equals(b.getName()) &&
          this.getNrCumulatives().equals(b.getNrCumulatives()) &&
          this.getNrJobs().equals(b.getNrJobs()) &&
          this.getNrMachines().equals(b.getNrMachines()) &&
          this.getNrTasks().equals(b.getNrTasks()) &&
          this.getSolverStatus().equals(b.getSolverStatus()) &&
          this.getTime().equals(b.getTime()) &&
          this.getVariant().equals(b.getVariant());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","SolutionSummary",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class SolutionSummary
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

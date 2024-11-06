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

public  class TaskAssignment extends ResourceActivity implements SolutionHierarchy{
/**
 *  
 *
*/

    public Integer idleAfter;

/**
 *  
 *
*/

    public Integer idleBefore;

/**
 *  
 *
*/

    public JobAssignment jobAssignment;

/**
 *  
 *
*/

    public Integer setupAfter;

/**
 *  
 *
*/

    public Integer setupBefore;

/**
 *  
 *
*/

    public Task task;

/**
 *  
 *
*/

    public Integer waitAfter;

/**
 *  
 *
*/

    public Integer waitBefore;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public TaskAssignment(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public TaskAssignment(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setIdleAfter(0);
        setIdleBefore(0);
        setJobAssignment(null);
        setSetupAfter(0);
        setSetupBefore(0);
        setTask(null);
        setWaitAfter(0);
        setWaitBefore(0);
        applicationDataset.addTaskAssignment(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public TaskAssignment(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            DisjunctiveResource disjunctiveResource,
            Integer duration,
            Integer end,
            DateTime endDate,
            Integer start,
            DateTime startDate,
            Integer idleAfter,
            Integer idleBefore,
            JobAssignment jobAssignment,
            Integer setupAfter,
            Integer setupBefore,
            Task task,
            Integer waitAfter,
            Integer waitBefore){
        super(applicationDataset,
            id,
            name,
            disjunctiveResource,
            duration,
            end,
            endDate,
            start,
            startDate);
        setIdleAfter(idleAfter);
        setIdleBefore(idleBefore);
        setJobAssignment(jobAssignment);
        setSetupAfter(setupAfter);
        setSetupBefore(setupBefore);
        setTask(task);
        setWaitAfter(waitAfter);
        setWaitBefore(waitBefore);
        applicationDataset.addTaskAssignment(this);
    }

    public TaskAssignment(TaskAssignment other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.disjunctiveResource,
            other.duration,
            other.end,
            other.endDate,
            other.start,
            other.startDate,
            other.idleAfter,
            other.idleBefore,
            other.jobAssignment,
            other.setupAfter,
            other.setupBefore,
            other.task,
            other.waitAfter,
            other.waitBefore);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeTaskAssignment(this) && getApplicationDataset().removeResourceActivity(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute idleAfter
 *
 * @return Integer
*/

    public Integer getIdleAfter(){
        return this.idleAfter;
    }

/**
 *  get attribute idleBefore
 *
 * @return Integer
*/

    public Integer getIdleBefore(){
        return this.idleBefore;
    }

/**
 *  get attribute jobAssignment
 *
 * @return JobAssignment
*/

    public JobAssignment getJobAssignment(){
        return this.jobAssignment;
    }

/**
 *  get attribute setupAfter
 *
 * @return Integer
*/

    public Integer getSetupAfter(){
        return this.setupAfter;
    }

/**
 *  get attribute setupBefore
 *
 * @return Integer
*/

    public Integer getSetupBefore(){
        return this.setupBefore;
    }

/**
 *  get attribute task
 *
 * @return Task
*/

    public Task getTask(){
        return this.task;
    }

/**
 *  get attribute waitAfter
 *
 * @return Integer
*/

    public Integer getWaitAfter(){
        return this.waitAfter;
    }

/**
 *  get attribute waitBefore
 *
 * @return Integer
*/

    public Integer getWaitBefore(){
        return this.waitBefore;
    }

/**
 *  set attribute idleAfter, mark dataset as dirty, mark dataset as not valid
@param idleAfter Integer
 *
*/

    public void setIdleAfter(Integer idleAfter){
        this.idleAfter = idleAfter;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute idleBefore, mark dataset as dirty, mark dataset as not valid
@param idleBefore Integer
 *
*/

    public void setIdleBefore(Integer idleBefore){
        this.idleBefore = idleBefore;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute jobAssignment, mark dataset as dirty, mark dataset as not valid
@param jobAssignment JobAssignment
 *
*/

    public void setJobAssignment(JobAssignment jobAssignment){
        this.jobAssignment = jobAssignment;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute setupAfter, mark dataset as dirty, mark dataset as not valid
@param setupAfter Integer
 *
*/

    public void setSetupAfter(Integer setupAfter){
        this.setupAfter = setupAfter;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute setupBefore, mark dataset as dirty, mark dataset as not valid
@param setupBefore Integer
 *
*/

    public void setSetupBefore(Integer setupBefore){
        this.setupBefore = setupBefore;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute task, mark dataset as dirty, mark dataset as not valid
@param task Task
 *
*/

    public void setTask(Task task){
        this.task = task;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute waitAfter, mark dataset as dirty, mark dataset as not valid
@param waitAfter Integer
 *
*/

    public void setWaitAfter(Integer waitAfter){
        this.waitAfter = waitAfter;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute waitBefore, mark dataset as dirty, mark dataset as not valid
@param waitBefore Integer
 *
*/

    public void setWaitBefore(Integer waitBefore){
        this.waitBefore = waitBefore;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute idleAfter, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incIdleAfter(){
        this.idleAfter++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute idleBefore, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incIdleBefore(){
        this.idleBefore++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute setupAfter, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incSetupAfter(){
        this.setupAfter++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute setupBefore, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incSetupBefore(){
        this.setupBefore++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute waitAfter, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incWaitAfter(){
        this.waitAfter++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute waitBefore, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incWaitBefore(){
        this.waitBefore++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getDisjunctiveResource().toColumnString()+ " " +getDuration()+ " " +getEnd()+ " " +getEndDate()+ " " +getStart()+ " " +getStartDate()+ " " +getIdleAfter()+ " " +getIdleBefore()+ " " +getJobAssignment().toColumnString()+ " " +getSetupAfter()+ " " +getSetupBefore()+ " " +getTask().toColumnString()+ " " +getWaitAfter()+ " " +getWaitBefore();
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
         out.println("<taskAssignment "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " disjunctiveResource=\""+toXMLDisjunctiveResource()+"\""+
            " duration=\""+toXMLDuration()+"\""+
            " end=\""+toXMLEnd()+"\""+
            " endDate=\""+toXMLEndDate()+"\""+
            " start=\""+toXMLStart()+"\""+
            " startDate=\""+toXMLStartDate()+"\""+
            " idleAfter=\""+toXMLIdleAfter()+"\""+
            " idleBefore=\""+toXMLIdleBefore()+"\""+
            " jobAssignment=\""+toXMLJobAssignment()+"\""+
            " setupAfter=\""+toXMLSetupAfter()+"\""+
            " setupBefore=\""+toXMLSetupBefore()+"\""+
            " task=\""+toXMLTask()+"\""+
            " waitAfter=\""+toXMLWaitAfter()+"\""+
            " waitBefore=\""+toXMLWaitBefore()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLIdleAfter(){
        return this.getIdleAfter().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLIdleBefore(){
        return this.getIdleBefore().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLJobAssignment(){
        return "ID_"+this.getJobAssignment().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLSetupAfter(){
        return this.getSetupAfter().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLSetupBefore(){
        return this.getSetupBefore().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLTask(){
        return "ID_"+this.getTask().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLWaitAfter(){
        return this.getWaitAfter().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLWaitBefore(){
        return this.getWaitBefore().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>TaskAssignment</th>"+"<th>Name</th>"+"<th>DisjunctiveResource</th>"+"<th>Duration</th>"+"<th>Start</th>"+"<th>End</th>"+"<th>StartDate</th>"+"<th>EndDate</th>"+"<th>Task</th>"+"<th>JobAssignment</th>"+"<th>WaitBefore</th>"+"<th>WaitAfter</th>"+"<th>IdleBefore</th>"+"<th>IdleAfter</th>"+"<th>SetupBefore</th>"+"<th>SetupAfter</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getDisjunctiveResource().toColumnString()+"</td>"+ " " +"<td>"+getDuration()+"</td>"+ " " +"<td>"+getStart()+"</td>"+ " " +"<td>"+getEnd()+"</td>"+ " " +"<td>"+getStartDate()+"</td>"+ " " +"<td>"+getEndDate()+"</td>"+ " " +"<td>"+getTask().toColumnString()+"</td>"+ " " +"<td>"+getJobAssignment().toColumnString()+"</td>"+ " " +"<td>"+getWaitBefore()+"</td>"+ " " +"<td>"+getWaitAfter()+"</td>"+ " " +"<td>"+getIdleBefore()+"</td>"+ " " +"<td>"+getIdleAfter()+"</td>"+ " " +"<td>"+getSetupBefore()+"</td>"+ " " +"<td>"+getSetupAfter()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a TaskAssignment item we are looking for
 * @param bList List<TaskAssignment> list of items in which we are searching
 * @return TaskAssignment entry of list b which is applicationSame() to a
*/

    public static TaskAssignment find(TaskAssignment a, List<TaskAssignment> bList){
        for(TaskAssignment b : bList){
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
 * @param name TaskAssignment name of the object we are looking for
 * @return TaskAssignment entry of the dataset with the given name; otherwise null
*/

    public static TaskAssignment findByName(ApplicationDataset base, String name){
        for(TaskAssignment a:base.getListTaskAssignment()) {
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
 * @param name TaskAssignment name of the object we are looking for
 * @return TaskAssignment entry of the dataset with the given name
*/

    public static TaskAssignment findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(TaskAssignment a:base.getListTaskAssignment()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        TaskAssignment res = new TaskAssignment(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return TaskAssignment first entry in the dataset of this type; null if that does not exists
*/

    public static TaskAssignment findFirst(ApplicationDataset base){
        if (base.getListTaskAssignment().isEmpty()) {
            return null;
        }
        return base.getListTaskAssignment().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return TaskAssignment some entry in the dataset of this type; null if that does not exists
*/

    public static TaskAssignment findAny(ApplicationDataset base){
        int size=base.getListTaskAssignment().size();
        if (size > 0) {
             return base.getListTaskAssignment().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return TaskAssignment last entry in the dataset of this type; null if that does not exists
*/

    public static TaskAssignment findLast(ApplicationDataset base){
        int size=base.getListTaskAssignment().size();
        if (size > 0) {
             return base.getListTaskAssignment().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b TaskAssignment compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(TaskAssignment b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b TaskAssignment compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(TaskAssignment b){
      if(!this.getDisjunctiveResource().applicationSame(b.getDisjunctiveResource())){
         System.out.println("DisjunctiveResource");
        }
      if(!this.getDuration().equals(b.getDuration())){
         System.out.println("Duration");
        }
      if(!this.getEnd().equals(b.getEnd())){
         System.out.println("End");
        }
      if(!this.getEndDate().applicationEqual(b.getEndDate())){
         System.out.println("EndDate");
        }
      if(!this.getIdleAfter().equals(b.getIdleAfter())){
         System.out.println("IdleAfter");
        }
      if(!this.getIdleBefore().equals(b.getIdleBefore())){
         System.out.println("IdleBefore");
        }
      if(!this.getJobAssignment().applicationSame(b.getJobAssignment())){
         System.out.println("JobAssignment");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getSetupAfter().equals(b.getSetupAfter())){
         System.out.println("SetupAfter");
        }
      if(!this.getSetupBefore().equals(b.getSetupBefore())){
         System.out.println("SetupBefore");
        }
      if(!this.getStart().equals(b.getStart())){
         System.out.println("Start");
        }
      if(!this.getStartDate().applicationEqual(b.getStartDate())){
         System.out.println("StartDate");
        }
      if(!this.getTask().applicationSame(b.getTask())){
         System.out.println("Task");
        }
      if(!this.getWaitAfter().equals(b.getWaitAfter())){
         System.out.println("WaitAfter");
        }
      if(!this.getWaitBefore().equals(b.getWaitBefore())){
         System.out.println("WaitBefore");
        }
        return  this.getDisjunctiveResource().applicationSame(b.getDisjunctiveResource()) &&
          this.getDuration().equals(b.getDuration()) &&
          this.getEnd().equals(b.getEnd()) &&
          this.getEndDate().applicationEqual(b.getEndDate()) &&
          this.getIdleAfter().equals(b.getIdleAfter()) &&
          this.getIdleBefore().equals(b.getIdleBefore()) &&
          this.getJobAssignment().applicationSame(b.getJobAssignment()) &&
          this.getName().equals(b.getName()) &&
          this.getSetupAfter().equals(b.getSetupAfter()) &&
          this.getSetupBefore().equals(b.getSetupBefore()) &&
          this.getStart().equals(b.getStart()) &&
          this.getStartDate().applicationEqual(b.getStartDate()) &&
          this.getTask().applicationSame(b.getTask()) &&
          this.getWaitAfter().equals(b.getWaitAfter()) &&
          this.getWaitBefore().equals(b.getWaitBefore());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","TaskAssignment",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getDisjunctiveResource() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"disjunctiveResource","TaskAssignment",(getDisjunctiveResource()==null?"null":getDisjunctiveResource().toString()),"",WarningType.NOTNULL);
        }
        if (getJobAssignment() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"jobAssignment","TaskAssignment",(getJobAssignment()==null?"null":getJobAssignment().toString()),"",WarningType.NOTNULL);
        }
        if (getTask() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"task","TaskAssignment",(getTask()==null?"null":getTask().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class TaskAssignment
    }

/**
 *  This method states if the class depends on the solver.
 *
*/

    public static Boolean isSolverDependent(){
        return false;
    }

   public List<ApplicationObjectInterface> getFeasibleValues(ApplicationDatasetInterface base,String attrName){
      if (attrName.equals("disjunctiveResource")){
         return (List) ((Scenario)base).getListDisjunctiveResource();
      }
      if (attrName.equals("jobAssignment")){
         return (List) ((Scenario)base).getListJobAssignment();
      }
      if (attrName.equals("task")){
         return (List) ((Scenario)base).getListTask();
      }
      return null;
   }

}

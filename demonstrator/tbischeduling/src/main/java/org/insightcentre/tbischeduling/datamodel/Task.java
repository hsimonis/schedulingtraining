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

public  class Task extends ApplicationObject implements AppearInCollection{
/**
 *  
 *
*/

    public Integer duration;

/**
 *  
 *
*/

    public Job job;

/**
 *  
 *
*/

    public List<DisjunctiveResource> machines;

/**
 *  
 *
*/

    public List<Task> precedes;

/**
 *  
 *
*/

    public ProcessStep processStep;

/**
 *  
 *
*/

    public String shortName;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public Task(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public Task(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setDuration(0);
        setJob(null);
        setMachines(new ArrayList<DisjunctiveResource>());
        setPrecedes(new ArrayList<Task>());
        setProcessStep(null);
        setShortName("");
        applicationDataset.addTask(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public Task(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Integer duration,
            Job job,
            List<DisjunctiveResource> machines,
            List<Task> precedes,
            ProcessStep processStep,
            String shortName){
        super(applicationDataset,
            id,
            name);
        setDuration(duration);
        setJob(job);
        setMachines(machines);
        setPrecedes(precedes);
        setProcessStep(processStep);
        setShortName(shortName);
        applicationDataset.addTask(this);
    }

    public Task(Task other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.duration,
            other.job,
            other.machines,
            other.precedes,
            other.processStep,
            other.shortName);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        getApplicationDataset().cascadeTaskPrecedes(this);
        getApplicationDataset().cascadeTaskAssignmentTask(this);
        return getApplicationDataset().removeTask(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  (varargs) build list of items of type Task
 *
 * @param pList multiple items of type Task
 * @return List<Task>
*/

    static public List<Task> buildList(Task... pList){
        List<Task> l = new ArrayList<Task>();
        l.addAll(Arrays.asList(pList));
        return l;
    }

/**
 *  get attribute duration
 *
 * @return Integer
*/

    public Integer getDuration(){
        return this.duration;
    }

/**
 *  get attribute job
 *
 * @return Job
*/

    public Job getJob(){
        return this.job;
    }

/**
 *  get attribute machines
 *
 * @return List<DisjunctiveResource>
*/

    public List<DisjunctiveResource> getMachines(){
        return this.machines;
    }

/**
 *  get attribute precedes
 *
 * @return List<Task>
*/

    public List<Task> getPrecedes(){
        return this.precedes;
    }

/**
 *  get attribute processStep
 *
 * @return ProcessStep
*/

    public ProcessStep getProcessStep(){
        return this.processStep;
    }

/**
 *  get attribute shortName
 *
 * @return String
*/

    public String getShortName(){
        return this.shortName;
    }

/**
 *  set attribute duration, mark dataset as dirty, mark dataset as not valid
@param duration Integer
 *
*/

    public void setDuration(Integer duration){
        this.duration = duration;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute job, mark dataset as dirty, mark dataset as not valid
@param job Job
 *
*/

    public void setJob(Job job){
        this.job = job;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute machines, mark dataset as dirty, mark dataset as not valid
@param machines List<DisjunctiveResource>
 *
*/

    public void setMachines(List<DisjunctiveResource> machines){
        this.machines = machines;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute precedes, mark dataset as dirty, mark dataset as not valid
@param precedes List<Task>
 *
*/

    public void setPrecedes(List<Task> precedes){
        this.precedes = precedes;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute processStep, mark dataset as dirty, mark dataset as not valid
@param processStep ProcessStep
 *
*/

    public void setProcessStep(ProcessStep processStep){
        this.processStep = processStep;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute shortName, mark dataset as dirty, mark dataset as not valid
@param shortName String
 *
*/

    public void setShortName(String shortName){
        this.shortName = shortName;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute duration, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incDuration(){
        this.duration++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getDuration()+ " " +getJob().toColumnString()+ " " +getMachines()+ " " +getPrecedes()+ " " +getProcessStep().toColumnString()+ " " +getShortName();
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
         out.println("<task "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " duration=\""+toXMLDuration()+"\""+
            " job=\""+toXMLJob()+"\""+
            " machines=\""+toXMLMachines()+"\""+
            " precedes=\""+toXMLPrecedes()+"\""+
            " processStep=\""+toXMLProcessStep()+"\""+
            " shortName=\""+toXMLShortName()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDuration(){
        return this.getDuration().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLJob(){
        return "ID_"+this.getJob().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMachines(){
        String str="";
        for(DisjunctiveResource x:getMachines()){
            str=str+" "+"ID_"+x.getId();
        }
        return str;
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLPrecedes(){
        String str="";
        for(Task x:getPrecedes()){
            str=str+" "+"ID_"+x.getId();
        }
        return str;
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLProcessStep(){
        return "ID_"+this.getProcessStep().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLShortName(){
        return this.safeXML(getShortName());
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>Task</th>"+"<th>Name</th>"+"<th>ShortName</th>"+"<th>Job</th>"+"<th>ProcessStep</th>"+"<th>Duration</th>"+"<th>Machines</th>"+"<th>Precedes</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getShortName()+"</td>"+ " " +"<td>"+getJob().toColumnString()+"</td>"+ " " +"<td>"+getProcessStep().toColumnString()+"</td>"+ " " +"<td>"+getDuration()+"</td>"+ " " +"<td>"+getMachines()+"</td>"+ " " +"<td>"+getPrecedes()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a Task item we are looking for
 * @param bList List<Task> list of items in which we are searching
 * @return Task entry of list b which is applicationSame() to a
*/

    public static Task find(Task a, List<Task> bList){
        for(Task b : bList){
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
 * @param name Task name of the object we are looking for
 * @return Task entry of the dataset with the given name; otherwise null
*/

    public static Task findByName(ApplicationDataset base, String name){
        for(Task a:base.getListTask()) {
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
 * @param name Task name of the object we are looking for
 * @return Task entry of the dataset with the given name
*/

    public static Task findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(Task a:base.getListTask()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        Task res = new Task(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Task first entry in the dataset of this type; null if that does not exists
*/

    public static Task findFirst(ApplicationDataset base){
        if (base.getListTask().isEmpty()) {
            return null;
        }
        return base.getListTask().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Task some entry in the dataset of this type; null if that does not exists
*/

    public static Task findAny(ApplicationDataset base){
        int size=base.getListTask().size();
        if (size > 0) {
             return base.getListTask().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Task last entry in the dataset of this type; null if that does not exists
*/

    public static Task findLast(ApplicationDataset base){
        int size=base.getListTask().size();
        if (size > 0) {
             return base.getListTask().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b Task compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(Task b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b Task compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(Task b){
      if(!this.getDuration().equals(b.getDuration())){
         System.out.println("Duration");
        }
      if(!this.getJob().applicationSame(b.getJob())){
         System.out.println("Job");
        }
      if (true) {         System.out.println("Machines");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if (true) {         System.out.println("Precedes");
        }
      if(!this.getProcessStep().applicationSame(b.getProcessStep())){
         System.out.println("ProcessStep");
        }
      if(!this.getShortName().equals(b.getShortName())){
         System.out.println("ShortName");
        }
        return  this.getDuration().equals(b.getDuration()) &&
          this.getJob().applicationSame(b.getJob()) &&
          true &&
          this.getName().equals(b.getName()) &&
          true &&
          this.getProcessStep().applicationSame(b.getProcessStep()) &&
          this.getShortName().equals(b.getShortName());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","Task",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getJob() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"job","Task",(getJob()==null?"null":getJob().toString()),"",WarningType.NOTNULL);
        }
        if (getMachines() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"machines","Task",(getMachines()==null?"null":getMachines().toString()),"",WarningType.NOTNULL);
        }
        if (getMachines().size() == 0){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"machines","Task",(getMachines()==null?"null":getMachines().toString()),"",WarningType.NOTEMPTY);
        }
        if (getPrecedes() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"precedes","Task",(getPrecedes()==null?"null":getPrecedes().toString()),"",WarningType.NOTNULL);
        }
        if (getProcessStep() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"processStep","Task",(getProcessStep()==null?"null":getProcessStep().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class Task
    }

/**
 *  This method states if the class depends on the solver.
 *
*/

    public static Boolean isSolverDependent(){
        return false;
    }

   public List<ApplicationObjectInterface> getFeasibleValues(ApplicationDatasetInterface base,String attrName){
      if (attrName.equals("job")){
         return (List) ((Scenario)base).getListJob();
      }
      if (attrName.equals("machines")){
         return (List) ((Scenario)base).getListDisjunctiveResource();
      }
      if (attrName.equals("precedes")){
         return (List) ((Scenario)base).getListTask();
      }
      if (attrName.equals("processStep")){
         return (List) ((Scenario)base).getListProcessStep();
      }
      return null;
   }

}

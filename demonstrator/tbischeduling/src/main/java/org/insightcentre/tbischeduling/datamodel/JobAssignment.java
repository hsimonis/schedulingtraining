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

public  class JobAssignment extends ApplicationObject{
/**
 *  
 *
*/

    public Integer duration;

/**
 *  
 *
*/

    public Integer early;

/**
 *  
 *
*/

    public Integer end;

/**
 *  
 *
*/

    public DateTime endDate;

/**
 *  
 *
*/

    public Job job;

/**
 *  
 *
*/

    public Integer late;

/**
 *  
 *
*/

    public Solution solution;

/**
 *  
 *
*/

    public Integer start;

/**
 *  
 *
*/

    public DateTime startDate;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public JobAssignment(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public JobAssignment(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setDuration(0);
        setEarly(0);
        setEnd(0);
        setEndDate(new DateTime());
        setJob(null);
        setLate(0);
        setSolution(null);
        setStart(0);
        setStartDate(new DateTime());
        applicationDataset.addJobAssignment(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public JobAssignment(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Integer duration,
            Integer early,
            Integer end,
            DateTime endDate,
            Job job,
            Integer late,
            Solution solution,
            Integer start,
            DateTime startDate){
        super(applicationDataset,
            id,
            name);
        setDuration(duration);
        setEarly(early);
        setEnd(end);
        setEndDate(endDate);
        setJob(job);
        setLate(late);
        setSolution(solution);
        setStart(start);
        setStartDate(startDate);
        applicationDataset.addJobAssignment(this);
    }

    public JobAssignment(JobAssignment other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.duration,
            other.early,
            other.end,
            other.endDate,
            other.job,
            other.late,
            other.solution,
            other.start,
            other.startDate);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        getApplicationDataset().cascadeTaskAssignmentJobAssignment(this);
        return getApplicationDataset().removeJobAssignment(this) && getApplicationDataset().removeApplicationObject(this);
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
 *  get attribute early
 *
 * @return Integer
*/

    public Integer getEarly(){
        return this.early;
    }

/**
 *  get attribute end
 *
 * @return Integer
*/

    public Integer getEnd(){
        return this.end;
    }

/**
 *  get attribute endDate
 *
 * @return DateTime
*/

    public DateTime getEndDate(){
        return this.endDate;
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
 *  get attribute late
 *
 * @return Integer
*/

    public Integer getLate(){
        return this.late;
    }

/**
 *  get attribute solution
 *
 * @return Solution
*/

    public Solution getSolution(){
        return this.solution;
    }

/**
 *  get attribute start
 *
 * @return Integer
*/

    public Integer getStart(){
        return this.start;
    }

/**
 *  get attribute startDate
 *
 * @return DateTime
*/

    public DateTime getStartDate(){
        return this.startDate;
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
 *  set attribute early, mark dataset as dirty, mark dataset as not valid
@param early Integer
 *
*/

    public void setEarly(Integer early){
        this.early = early;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute end, mark dataset as dirty, mark dataset as not valid
@param end Integer
 *
*/

    public void setEnd(Integer end){
        this.end = end;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute endDate, mark dataset as dirty, mark dataset as not valid
@param endDate DateTime
 *
*/

    public void setEndDate(DateTime endDate){
        this.endDate = endDate;
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
 *  set attribute late, mark dataset as dirty, mark dataset as not valid
@param late Integer
 *
*/

    public void setLate(Integer late){
        this.late = late;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute solution, mark dataset as dirty, mark dataset as not valid
@param solution Solution
 *
*/

    public void setSolution(Solution solution){
        this.solution = solution;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute start, mark dataset as dirty, mark dataset as not valid
@param start Integer
 *
*/

    public void setStart(Integer start){
        this.start = start;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute startDate, mark dataset as dirty, mark dataset as not valid
@param startDate DateTime
 *
*/

    public void setStartDate(DateTime startDate){
        this.startDate = startDate;
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
 *  inc attribute early, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incEarly(){
        this.early++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute end, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incEnd(){
        this.end++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute late, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incLate(){
        this.late++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute start, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incStart(){
        this.start++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getDuration()+ " " +getEarly()+ " " +getEnd()+ " " +getEndDate()+ " " +getJob().toColumnString()+ " " +getLate()+ " " +getSolution().toColumnString()+ " " +getStart()+ " " +getStartDate();
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
         out.println("<jobAssignment "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " duration=\""+toXMLDuration()+"\""+
            " early=\""+toXMLEarly()+"\""+
            " end=\""+toXMLEnd()+"\""+
            " endDate=\""+toXMLEndDate()+"\""+
            " job=\""+toXMLJob()+"\""+
            " late=\""+toXMLLate()+"\""+
            " solution=\""+toXMLSolution()+"\""+
            " start=\""+toXMLStart()+"\""+
            " startDate=\""+toXMLStartDate()+"\""+" />");
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

    String toXMLEarly(){
        return this.getEarly().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLEnd(){
        return this.getEnd().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLEndDate(){
        return this.getEndDate().toXML();
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

    String toXMLLate(){
        return this.getLate().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLSolution(){
        return "ID_"+this.getSolution().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLStart(){
        return this.getStart().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLStartDate(){
        return this.getStartDate().toXML();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>JobAssignment</th>"+"<th>Name</th>"+"<th>Solution</th>"+"<th>Job</th>"+"<th>Late</th>"+"<th>Early</th>"+"<th>Duration</th>"+"<th>Start</th>"+"<th>End</th>"+"<th>StartDate</th>"+"<th>EndDate</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getSolution().toColumnString()+"</td>"+ " " +"<td>"+getJob().toColumnString()+"</td>"+ " " +"<td>"+getLate()+"</td>"+ " " +"<td>"+getEarly()+"</td>"+ " " +"<td>"+getDuration()+"</td>"+ " " +"<td>"+getStart()+"</td>"+ " " +"<td>"+getEnd()+"</td>"+ " " +"<td>"+getStartDate()+"</td>"+ " " +"<td>"+getEndDate()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a JobAssignment item we are looking for
 * @param bList List<JobAssignment> list of items in which we are searching
 * @return JobAssignment entry of list b which is applicationSame() to a
*/

    public static JobAssignment find(JobAssignment a, List<JobAssignment> bList){
        for(JobAssignment b : bList){
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
 * @param name JobAssignment name of the object we are looking for
 * @return JobAssignment entry of the dataset with the given name; otherwise null
*/

    public static JobAssignment findByName(ApplicationDataset base, String name){
        for(JobAssignment a:base.getListJobAssignment()) {
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
 * @param name JobAssignment name of the object we are looking for
 * @return JobAssignment entry of the dataset with the given name
*/

    public static JobAssignment findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(JobAssignment a:base.getListJobAssignment()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        JobAssignment res = new JobAssignment(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return JobAssignment first entry in the dataset of this type; null if that does not exists
*/

    public static JobAssignment findFirst(ApplicationDataset base){
        if (base.getListJobAssignment().isEmpty()) {
            return null;
        }
        return base.getListJobAssignment().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return JobAssignment some entry in the dataset of this type; null if that does not exists
*/

    public static JobAssignment findAny(ApplicationDataset base){
        int size=base.getListJobAssignment().size();
        if (size > 0) {
             return base.getListJobAssignment().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return JobAssignment last entry in the dataset of this type; null if that does not exists
*/

    public static JobAssignment findLast(ApplicationDataset base){
        int size=base.getListJobAssignment().size();
        if (size > 0) {
             return base.getListJobAssignment().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b JobAssignment compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(JobAssignment b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b JobAssignment compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(JobAssignment b){
      if(!this.getDuration().equals(b.getDuration())){
         System.out.println("Duration");
        }
      if(!this.getEarly().equals(b.getEarly())){
         System.out.println("Early");
        }
      if(!this.getEnd().equals(b.getEnd())){
         System.out.println("End");
        }
      if(!this.getEndDate().applicationEqual(b.getEndDate())){
         System.out.println("EndDate");
        }
      if(!this.getJob().applicationSame(b.getJob())){
         System.out.println("Job");
        }
      if(!this.getLate().equals(b.getLate())){
         System.out.println("Late");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getSolution().applicationSame(b.getSolution())){
         System.out.println("Solution");
        }
      if(!this.getStart().equals(b.getStart())){
         System.out.println("Start");
        }
      if(!this.getStartDate().applicationEqual(b.getStartDate())){
         System.out.println("StartDate");
        }
        return  this.getDuration().equals(b.getDuration()) &&
          this.getEarly().equals(b.getEarly()) &&
          this.getEnd().equals(b.getEnd()) &&
          this.getEndDate().applicationEqual(b.getEndDate()) &&
          this.getJob().applicationSame(b.getJob()) &&
          this.getLate().equals(b.getLate()) &&
          this.getName().equals(b.getName()) &&
          this.getSolution().applicationSame(b.getSolution()) &&
          this.getStart().equals(b.getStart()) &&
          this.getStartDate().applicationEqual(b.getStartDate());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","JobAssignment",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getJob() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"job","JobAssignment",(getJob()==null?"null":getJob().toString()),"",WarningType.NOTNULL);
        }
        if (getSolution() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"solution","JobAssignment",(getSolution()==null?"null":getSolution().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class JobAssignment
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
      if (attrName.equals("solution")){
         return (List) ((Scenario)base).getListSolution();
      }
      return null;
   }

}

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

public abstract class ResourceActivity extends ApplicationObject{
/**
 *  
 *
*/

    public DisjunctiveResource disjunctiveResource;

/**
 *  
 *
*/

    public Integer duration;

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

    public ResourceActivity(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public ResourceActivity(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setDisjunctiveResource(null);
        setDuration(0);
        setEnd(0);
        setEndDate(new DateTime());
        setStart(0);
        setStartDate(new DateTime());
        applicationDataset.addResourceActivity(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public ResourceActivity(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            DisjunctiveResource disjunctiveResource,
            Integer duration,
            Integer end,
            DateTime endDate,
            Integer start,
            DateTime startDate){
        super(applicationDataset,
            id,
            name);
        setDisjunctiveResource(disjunctiveResource);
        setDuration(duration);
        setEnd(end);
        setEndDate(endDate);
        setStart(start);
        setStartDate(startDate);
        applicationDataset.addResourceActivity(this);
    }

    public ResourceActivity(ResourceActivity other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.disjunctiveResource,
            other.duration,
            other.end,
            other.endDate,
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
        return getApplicationDataset().removeResourceActivity(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute disjunctiveResource
 *
 * @return DisjunctiveResource
*/

    public DisjunctiveResource getDisjunctiveResource(){
        return this.disjunctiveResource;
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
 *  set attribute disjunctiveResource, mark dataset as dirty, mark dataset as not valid
@param disjunctiveResource DisjunctiveResource
 *
*/

    public void setDisjunctiveResource(DisjunctiveResource disjunctiveResource){
        this.disjunctiveResource = disjunctiveResource;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
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
 *  inc attribute end, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incEnd(){
        this.end++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getDisjunctiveResource().toColumnString()+ " " +getDuration()+ " " +getEnd()+ " " +getEndDate()+ " " +getStart()+ " " +getStartDate();
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
         out.println("<resourceActivity "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " disjunctiveResource=\""+toXMLDisjunctiveResource()+"\""+
            " duration=\""+toXMLDuration()+"\""+
            " end=\""+toXMLEnd()+"\""+
            " endDate=\""+toXMLEndDate()+"\""+
            " start=\""+toXMLStart()+"\""+
            " startDate=\""+toXMLStartDate()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDisjunctiveResource(){
        return "ID_"+this.getDisjunctiveResource().getId().toString();
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
 * find the same object in another dataset
 * @param a ResourceActivity item we are looking for
 * @param bList List<ResourceActivity> list of items in which we are searching
 * @return ResourceActivity entry of list b which is applicationSame() to a
*/

    public static ResourceActivity find(ResourceActivity a, List<ResourceActivity> bList){
        for(ResourceActivity b : bList){
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
 * @param name ResourceActivity name of the object we are looking for
 * @return ResourceActivity entry of the dataset with the given name; otherwise null
*/

    public static ResourceActivity findByName(ApplicationDataset base, String name){
        for(ResourceActivity a:base.getListResourceActivity()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        return null;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ResourceActivity first entry in the dataset of this type; null if that does not exists
*/

    public static ResourceActivity findFirst(ApplicationDataset base){
        if (base.getListResourceActivity().isEmpty()) {
            return null;
        }
        return base.getListResourceActivity().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ResourceActivity some entry in the dataset of this type; null if that does not exists
*/

    public static ResourceActivity findAny(ApplicationDataset base){
        int size=base.getListResourceActivity().size();
        if (size > 0) {
             return base.getListResourceActivity().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ResourceActivity last entry in the dataset of this type; null if that does not exists
*/

    public static ResourceActivity findLast(ApplicationDataset base){
        int size=base.getListResourceActivity().size();
        if (size > 0) {
             return base.getListResourceActivity().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b ResourceActivity compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(ResourceActivity b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b ResourceActivity compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(ResourceActivity b){
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
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getStart().equals(b.getStart())){
         System.out.println("Start");
        }
      if(!this.getStartDate().applicationEqual(b.getStartDate())){
         System.out.println("StartDate");
        }
        return  this.getDisjunctiveResource().applicationSame(b.getDisjunctiveResource()) &&
          this.getDuration().equals(b.getDuration()) &&
          this.getEnd().equals(b.getEnd()) &&
          this.getEndDate().applicationEqual(b.getEndDate()) &&
          this.getName().equals(b.getName()) &&
          this.getStart().equals(b.getStart()) &&
          this.getStartDate().applicationEqual(b.getStartDate());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","ResourceActivity",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getDisjunctiveResource() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"disjunctiveResource","ResourceActivity",(getDisjunctiveResource()==null?"null":getDisjunctiveResource().toString()),"",WarningType.NOTNULL);
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
      if (attrName.equals("disjunctiveResource")){
         return (List) ((Scenario)base).getListDisjunctiveResource();
      }
      return null;
   }

}

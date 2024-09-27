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

public  class Downtime extends ApplicationObject{
/**
 *  
 *
*/

    public DisjunctiveResource disjunctiveResource;

/**
 *  
 *
*/

    public Integer from;

/**
 *  
 *
*/

    public DateTime fromDate;

/**
 *  
 *
*/

    public Integer to;

/**
 *  
 *
*/

    public DateTime toDate;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public Downtime(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public Downtime(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setDisjunctiveResource(null);
        setFrom(0);
        setFromDate(new DateTime());
        setTo(0);
        setToDate(new DateTime());
        applicationDataset.addDowntime(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public Downtime(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            DisjunctiveResource disjunctiveResource,
            Integer from,
            DateTime fromDate,
            Integer to,
            DateTime toDate){
        super(applicationDataset,
            id,
            name);
        setDisjunctiveResource(disjunctiveResource);
        setFrom(from);
        setFromDate(fromDate);
        setTo(to);
        setToDate(toDate);
        applicationDataset.addDowntime(this);
    }

    public Downtime(Downtime other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.disjunctiveResource,
            other.from,
            other.fromDate,
            other.to,
            other.toDate);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeDowntime(this) && getApplicationDataset().removeApplicationObject(this);
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
 *  get attribute from
 *
 * @return Integer
*/

    public Integer getFrom(){
        return this.from;
    }

/**
 *  get attribute fromDate
 *
 * @return DateTime
*/

    public DateTime getFromDate(){
        return this.fromDate;
    }

/**
 *  get attribute to
 *
 * @return Integer
*/

    public Integer getTo(){
        return this.to;
    }

/**
 *  get attribute toDate
 *
 * @return DateTime
*/

    public DateTime getToDate(){
        return this.toDate;
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
 *  set attribute from, mark dataset as dirty, mark dataset as not valid
@param from Integer
 *
*/

    public void setFrom(Integer from){
        this.from = from;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute fromDate, mark dataset as dirty, mark dataset as not valid
@param fromDate DateTime
 *
*/

    public void setFromDate(DateTime fromDate){
        this.fromDate = fromDate;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute to, mark dataset as dirty, mark dataset as not valid
@param to Integer
 *
*/

    public void setTo(Integer to){
        this.to = to;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute toDate, mark dataset as dirty, mark dataset as not valid
@param toDate DateTime
 *
*/

    public void setToDate(DateTime toDate){
        this.toDate = toDate;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute from, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incFrom(){
        this.from++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute to, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incTo(){
        this.to++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getDisjunctiveResource().toColumnString()+ " " +getFrom()+ " " +getFromDate()+ " " +getTo()+ " " +getToDate();
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
         out.println("<downtime "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " disjunctiveResource=\""+toXMLDisjunctiveResource()+"\""+
            " from=\""+toXMLFrom()+"\""+
            " fromDate=\""+toXMLFromDate()+"\""+
            " to=\""+toXMLTo()+"\""+
            " toDate=\""+toXMLToDate()+"\""+" />");
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

    String toXMLFrom(){
        return this.getFrom().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLFromDate(){
        return this.getFromDate().toXML();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLTo(){
        return this.getTo().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLToDate(){
        return this.getToDate().toXML();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>Downtime</th>"+"<th>Name</th>"+"<th>From</th>"+"<th>To</th>"+"<th>FromDate</th>"+"<th>ToDate</th>"+"<th>DisjunctiveResource</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getFrom()+"</td>"+ " " +"<td>"+getTo()+"</td>"+ " " +"<td>"+getFromDate()+"</td>"+ " " +"<td>"+getToDate()+"</td>"+ " " +"<td>"+getDisjunctiveResource().toColumnString()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a Downtime item we are looking for
 * @param bList List<Downtime> list of items in which we are searching
 * @return Downtime entry of list b which is applicationSame() to a
*/

    public static Downtime find(Downtime a, List<Downtime> bList){
        for(Downtime b : bList){
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
 * @param name Downtime name of the object we are looking for
 * @return Downtime entry of the dataset with the given name; otherwise null
*/

    public static Downtime findByName(ApplicationDataset base, String name){
        for(Downtime a:base.getListDowntime()) {
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
 * @param name Downtime name of the object we are looking for
 * @return Downtime entry of the dataset with the given name
*/

    public static Downtime findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(Downtime a:base.getListDowntime()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        Downtime res = new Downtime(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Downtime first entry in the dataset of this type; null if that does not exists
*/

    public static Downtime findFirst(ApplicationDataset base){
        if (base.getListDowntime().isEmpty()) {
            return null;
        }
        return base.getListDowntime().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Downtime some entry in the dataset of this type; null if that does not exists
*/

    public static Downtime findAny(ApplicationDataset base){
        int size=base.getListDowntime().size();
        if (size > 0) {
             return base.getListDowntime().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Downtime last entry in the dataset of this type; null if that does not exists
*/

    public static Downtime findLast(ApplicationDataset base){
        int size=base.getListDowntime().size();
        if (size > 0) {
             return base.getListDowntime().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b Downtime compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(Downtime b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b Downtime compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(Downtime b){
      if(!this.getDisjunctiveResource().applicationSame(b.getDisjunctiveResource())){
         System.out.println("DisjunctiveResource");
        }
      if(!this.getFrom().equals(b.getFrom())){
         System.out.println("From");
        }
      if(!this.getFromDate().applicationEqual(b.getFromDate())){
         System.out.println("FromDate");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getTo().equals(b.getTo())){
         System.out.println("To");
        }
      if(!this.getToDate().applicationEqual(b.getToDate())){
         System.out.println("ToDate");
        }
        return  this.getDisjunctiveResource().applicationSame(b.getDisjunctiveResource()) &&
          this.getFrom().equals(b.getFrom()) &&
          this.getFromDate().applicationEqual(b.getFromDate()) &&
          this.getName().equals(b.getName()) &&
          this.getTo().equals(b.getTo()) &&
          this.getToDate().applicationEqual(b.getToDate());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","Downtime",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getDisjunctiveResource() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"disjunctiveResource","Downtime",(getDisjunctiveResource()==null?"null":getDisjunctiveResource().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class Downtime
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

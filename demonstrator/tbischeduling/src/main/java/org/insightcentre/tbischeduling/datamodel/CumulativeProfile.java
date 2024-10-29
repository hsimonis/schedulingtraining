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

public  class CumulativeProfile extends ApplicationObject{
/**
 *  
 *
*/

    public Integer capacity;

/**
 *  
 *
*/

    public CumulativeResource cumulativeResource;

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
 *  No-arg constructor for use in TableView
 *
*/

    public CumulativeProfile(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public CumulativeProfile(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setCapacity(0);
        setCumulativeResource(null);
        setFrom(0);
        setFromDate(new DateTime());
        applicationDataset.addCumulativeProfile(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public CumulativeProfile(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Integer capacity,
            CumulativeResource cumulativeResource,
            Integer from,
            DateTime fromDate){
        super(applicationDataset,
            id,
            name);
        setCapacity(capacity);
        setCumulativeResource(cumulativeResource);
        setFrom(from);
        setFromDate(fromDate);
        applicationDataset.addCumulativeProfile(this);
    }

    public CumulativeProfile(CumulativeProfile other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.capacity,
            other.cumulativeResource,
            other.from,
            other.fromDate);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeCumulativeProfile(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute capacity
 *
 * @return Integer
*/

    public Integer getCapacity(){
        return this.capacity;
    }

/**
 *  get attribute cumulativeResource
 *
 * @return CumulativeResource
*/

    public CumulativeResource getCumulativeResource(){
        return this.cumulativeResource;
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
 *  set attribute capacity, mark dataset as dirty, mark dataset as not valid
@param capacity Integer
 *
*/

    public void setCapacity(Integer capacity){
        this.capacity = capacity;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute cumulativeResource, mark dataset as dirty, mark dataset as not valid
@param cumulativeResource CumulativeResource
 *
*/

    public void setCumulativeResource(CumulativeResource cumulativeResource){
        this.cumulativeResource = cumulativeResource;
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
 *  inc attribute capacity, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incCapacity(){
        this.capacity++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getCapacity()+ " " +getCumulativeResource().toColumnString()+ " " +getFrom()+ " " +getFromDate();
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
         out.println("<cumulativeProfile "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " capacity=\""+toXMLCapacity()+"\""+
            " cumulativeResource=\""+toXMLCumulativeResource()+"\""+
            " from=\""+toXMLFrom()+"\""+
            " fromDate=\""+toXMLFromDate()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLCapacity(){
        return this.getCapacity().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLCumulativeResource(){
        return "ID_"+this.getCumulativeResource().getId().toString();
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
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>CumulativeProfile</th>"+"<th>Name</th>"+"<th>CumulativeResource</th>"+"<th>From</th>"+"<th>FromDate</th>"+"<th>Capacity</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getCumulativeResource().toColumnString()+"</td>"+ " " +"<td>"+getFrom()+"</td>"+ " " +"<td>"+getFromDate()+"</td>"+ " " +"<td>"+getCapacity()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a CumulativeProfile item we are looking for
 * @param bList List<CumulativeProfile> list of items in which we are searching
 * @return CumulativeProfile entry of list b which is applicationSame() to a
*/

    public static CumulativeProfile find(CumulativeProfile a, List<CumulativeProfile> bList){
        for(CumulativeProfile b : bList){
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
 * @param name CumulativeProfile name of the object we are looking for
 * @return CumulativeProfile entry of the dataset with the given name; otherwise null
*/

    public static CumulativeProfile findByName(ApplicationDataset base, String name){
        for(CumulativeProfile a:base.getListCumulativeProfile()) {
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
 * @param name CumulativeProfile name of the object we are looking for
 * @return CumulativeProfile entry of the dataset with the given name
*/

    public static CumulativeProfile findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(CumulativeProfile a:base.getListCumulativeProfile()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        CumulativeProfile res = new CumulativeProfile(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return CumulativeProfile first entry in the dataset of this type; null if that does not exists
*/

    public static CumulativeProfile findFirst(ApplicationDataset base){
        if (base.getListCumulativeProfile().isEmpty()) {
            return null;
        }
        return base.getListCumulativeProfile().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return CumulativeProfile some entry in the dataset of this type; null if that does not exists
*/

    public static CumulativeProfile findAny(ApplicationDataset base){
        int size=base.getListCumulativeProfile().size();
        if (size > 0) {
             return base.getListCumulativeProfile().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return CumulativeProfile last entry in the dataset of this type; null if that does not exists
*/

    public static CumulativeProfile findLast(ApplicationDataset base){
        int size=base.getListCumulativeProfile().size();
        if (size > 0) {
             return base.getListCumulativeProfile().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b CumulativeProfile compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(CumulativeProfile b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b CumulativeProfile compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(CumulativeProfile b){
      if(!this.getCapacity().equals(b.getCapacity())){
         System.out.println("Capacity");
        }
      if(!this.getCumulativeResource().applicationSame(b.getCumulativeResource())){
         System.out.println("CumulativeResource");
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
        return  this.getCapacity().equals(b.getCapacity()) &&
          this.getCumulativeResource().applicationSame(b.getCumulativeResource()) &&
          this.getFrom().equals(b.getFrom()) &&
          this.getFromDate().applicationEqual(b.getFromDate()) &&
          this.getName().equals(b.getName());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","CumulativeProfile",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getCumulativeResource() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"cumulativeResource","CumulativeProfile",(getCumulativeResource()==null?"null":getCumulativeResource().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class CumulativeProfile
    }

/**
 *  This method states if the class depends on the solver.
 *
*/

    public static Boolean isSolverDependent(){
        return false;
    }

   public List<ApplicationObjectInterface> getFeasibleValues(ApplicationDatasetInterface base,String attrName){
      if (attrName.equals("cumulativeResource")){
         return (List) ((Scenario)base).getListCumulativeResource();
      }
      return null;
   }

}

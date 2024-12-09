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

public  class CumulativeLowerBound extends LowerBound{
/**
 *  
 *
*/

    public CumulativeResource cumulativeResource;

/**
 *  
 *
*/

    public Integer maxCapacity;

/**
 *  
 *
*/

    public Integer totalDemand;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public CumulativeLowerBound(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public CumulativeLowerBound(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setCumulativeResource(null);
        setMaxCapacity(0);
        setTotalDemand(0);
        applicationDataset.addCumulativeLowerBound(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public CumulativeLowerBound(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            String description,
            Integer value,
            CumulativeResource cumulativeResource,
            Integer maxCapacity,
            Integer totalDemand){
        super(applicationDataset,
            id,
            name,
            description,
            value);
        setCumulativeResource(cumulativeResource);
        setMaxCapacity(maxCapacity);
        setTotalDemand(totalDemand);
        applicationDataset.addCumulativeLowerBound(this);
    }

    public CumulativeLowerBound(CumulativeLowerBound other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.description,
            other.value,
            other.cumulativeResource,
            other.maxCapacity,
            other.totalDemand);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeCumulativeLowerBound(this) && getApplicationDataset().removeLowerBound(this) && getApplicationDataset().removeApplicationObject(this);
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
 *  get attribute maxCapacity
 *
 * @return Integer
*/

    public Integer getMaxCapacity(){
        return this.maxCapacity;
    }

/**
 *  get attribute totalDemand
 *
 * @return Integer
*/

    public Integer getTotalDemand(){
        return this.totalDemand;
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
 *  set attribute maxCapacity, mark dataset as dirty, mark dataset as not valid
@param maxCapacity Integer
 *
*/

    public void setMaxCapacity(Integer maxCapacity){
        this.maxCapacity = maxCapacity;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute totalDemand, mark dataset as dirty, mark dataset as not valid
@param totalDemand Integer
 *
*/

    public void setTotalDemand(Integer totalDemand){
        this.totalDemand = totalDemand;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute maxCapacity, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMaxCapacity(){
        this.maxCapacity++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute totalDemand, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incTotalDemand(){
        this.totalDemand++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getDescription()+ " " +getValue()+ " " +getCumulativeResource().toColumnString()+ " " +getMaxCapacity()+ " " +getTotalDemand();
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
         out.println("<cumulativeLowerBound "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " description=\""+toXMLDescription()+"\""+
            " value=\""+toXMLValue()+"\""+
            " cumulativeResource=\""+toXMLCumulativeResource()+"\""+
            " maxCapacity=\""+toXMLMaxCapacity()+"\""+
            " totalDemand=\""+toXMLTotalDemand()+"\""+" />");
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

    String toXMLMaxCapacity(){
        return this.getMaxCapacity().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLTotalDemand(){
        return this.getTotalDemand().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>CumulativeLowerBound</th>"+"<th>Name</th>"+"<th>Value</th>"+"<th>Description</th>"+"<th>CumulativeResource</th>"+"<th>MaxCapacity</th>"+"<th>TotalDemand</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getValue()+"</td>"+ " " +"<td>"+getDescription()+"</td>"+ " " +"<td>"+getCumulativeResource().toColumnString()+"</td>"+ " " +"<td>"+getMaxCapacity()+"</td>"+ " " +"<td>"+getTotalDemand()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a CumulativeLowerBound item we are looking for
 * @param bList List<CumulativeLowerBound> list of items in which we are searching
 * @return CumulativeLowerBound entry of list b which is applicationSame() to a
*/

    public static CumulativeLowerBound find(CumulativeLowerBound a, List<CumulativeLowerBound> bList){
        for(CumulativeLowerBound b : bList){
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
 * @param name CumulativeLowerBound name of the object we are looking for
 * @return CumulativeLowerBound entry of the dataset with the given name; otherwise null
*/

    public static CumulativeLowerBound findByName(ApplicationDataset base, String name){
        for(CumulativeLowerBound a:base.getListCumulativeLowerBound()) {
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
 * @param name CumulativeLowerBound name of the object we are looking for
 * @return CumulativeLowerBound entry of the dataset with the given name
*/

    public static CumulativeLowerBound findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(CumulativeLowerBound a:base.getListCumulativeLowerBound()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        CumulativeLowerBound res = new CumulativeLowerBound(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return CumulativeLowerBound first entry in the dataset of this type; null if that does not exists
*/

    public static CumulativeLowerBound findFirst(ApplicationDataset base){
        if (base.getListCumulativeLowerBound().isEmpty()) {
            return null;
        }
        return base.getListCumulativeLowerBound().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return CumulativeLowerBound some entry in the dataset of this type; null if that does not exists
*/

    public static CumulativeLowerBound findAny(ApplicationDataset base){
        int size=base.getListCumulativeLowerBound().size();
        if (size > 0) {
             return base.getListCumulativeLowerBound().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return CumulativeLowerBound last entry in the dataset of this type; null if that does not exists
*/

    public static CumulativeLowerBound findLast(ApplicationDataset base){
        int size=base.getListCumulativeLowerBound().size();
        if (size > 0) {
             return base.getListCumulativeLowerBound().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b CumulativeLowerBound compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(CumulativeLowerBound b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b CumulativeLowerBound compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(CumulativeLowerBound b){
      if(!this.getCumulativeResource().applicationSame(b.getCumulativeResource())){
         System.out.println("CumulativeResource");
        }
      if(!this.getDescription().equals(b.getDescription())){
         System.out.println("Description");
        }
      if(!this.getMaxCapacity().equals(b.getMaxCapacity())){
         System.out.println("MaxCapacity");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getTotalDemand().equals(b.getTotalDemand())){
         System.out.println("TotalDemand");
        }
      if(!this.getValue().equals(b.getValue())){
         System.out.println("Value");
        }
        return  this.getCumulativeResource().applicationSame(b.getCumulativeResource()) &&
          this.getDescription().equals(b.getDescription()) &&
          this.getMaxCapacity().equals(b.getMaxCapacity()) &&
          this.getName().equals(b.getName()) &&
          this.getTotalDemand().equals(b.getTotalDemand()) &&
          this.getValue().equals(b.getValue());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","CumulativeLowerBound",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getCumulativeResource() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"cumulativeResource","CumulativeLowerBound",(getCumulativeResource()==null?"null":getCumulativeResource().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class CumulativeLowerBound
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

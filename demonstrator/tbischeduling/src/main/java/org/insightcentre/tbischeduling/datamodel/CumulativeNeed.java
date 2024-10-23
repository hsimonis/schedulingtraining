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

public  class CumulativeNeed extends ApplicationObject{
/**
 *  
 *
*/

    public CumulativeResource cumulativeResource;

/**
 *  
 *
*/

    public Integer demand;

/**
 *  
 *
*/

    public ProcessStep processStep;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public CumulativeNeed(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public CumulativeNeed(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setCumulativeResource(null);
        setDemand(0);
        setProcessStep(null);
        applicationDataset.addCumulativeNeed(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public CumulativeNeed(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            CumulativeResource cumulativeResource,
            Integer demand,
            ProcessStep processStep){
        super(applicationDataset,
            id,
            name);
        setCumulativeResource(cumulativeResource);
        setDemand(demand);
        setProcessStep(processStep);
        applicationDataset.addCumulativeNeed(this);
    }

    public CumulativeNeed(CumulativeNeed other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.cumulativeResource,
            other.demand,
            other.processStep);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeCumulativeNeed(this) && getApplicationDataset().removeApplicationObject(this);
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
 *  get attribute demand
 *
 * @return Integer
*/

    public Integer getDemand(){
        return this.demand;
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
 *  set attribute demand, mark dataset as dirty, mark dataset as not valid
@param demand Integer
 *
*/

    public void setDemand(Integer demand){
        this.demand = demand;
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
 *  inc attribute demand, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incDemand(){
        this.demand++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getCumulativeResource().toColumnString()+ " " +getDemand()+ " " +getProcessStep().toColumnString();
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
         out.println("<cumulativeNeed "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " cumulativeResource=\""+toXMLCumulativeResource()+"\""+
            " demand=\""+toXMLDemand()+"\""+
            " processStep=\""+toXMLProcessStep()+"\""+" />");
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

    String toXMLDemand(){
        return this.getDemand().toString();
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
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>CumulativeNeed</th>"+"<th>Name</th>"+"<th>CumulativeResource</th>"+"<th>ProcessStep</th>"+"<th>Demand</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getCumulativeResource().toColumnString()+"</td>"+ " " +"<td>"+getProcessStep().toColumnString()+"</td>"+ " " +"<td>"+getDemand()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a CumulativeNeed item we are looking for
 * @param bList List<CumulativeNeed> list of items in which we are searching
 * @return CumulativeNeed entry of list b which is applicationSame() to a
*/

    public static CumulativeNeed find(CumulativeNeed a, List<CumulativeNeed> bList){
        for(CumulativeNeed b : bList){
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
 * @param name CumulativeNeed name of the object we are looking for
 * @return CumulativeNeed entry of the dataset with the given name; otherwise null
*/

    public static CumulativeNeed findByName(ApplicationDataset base, String name){
        for(CumulativeNeed a:base.getListCumulativeNeed()) {
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
 * @param name CumulativeNeed name of the object we are looking for
 * @return CumulativeNeed entry of the dataset with the given name
*/

    public static CumulativeNeed findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(CumulativeNeed a:base.getListCumulativeNeed()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        CumulativeNeed res = new CumulativeNeed(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return CumulativeNeed first entry in the dataset of this type; null if that does not exists
*/

    public static CumulativeNeed findFirst(ApplicationDataset base){
        if (base.getListCumulativeNeed().isEmpty()) {
            return null;
        }
        return base.getListCumulativeNeed().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return CumulativeNeed some entry in the dataset of this type; null if that does not exists
*/

    public static CumulativeNeed findAny(ApplicationDataset base){
        int size=base.getListCumulativeNeed().size();
        if (size > 0) {
             return base.getListCumulativeNeed().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return CumulativeNeed last entry in the dataset of this type; null if that does not exists
*/

    public static CumulativeNeed findLast(ApplicationDataset base){
        int size=base.getListCumulativeNeed().size();
        if (size > 0) {
             return base.getListCumulativeNeed().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b CumulativeNeed compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(CumulativeNeed b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b CumulativeNeed compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(CumulativeNeed b){
      if(!this.getCumulativeResource().applicationSame(b.getCumulativeResource())){
         System.out.println("CumulativeResource");
        }
      if(!this.getDemand().equals(b.getDemand())){
         System.out.println("Demand");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getProcessStep().applicationSame(b.getProcessStep())){
         System.out.println("ProcessStep");
        }
        return  this.getCumulativeResource().applicationSame(b.getCumulativeResource()) &&
          this.getDemand().equals(b.getDemand()) &&
          this.getName().equals(b.getName()) &&
          this.getProcessStep().applicationSame(b.getProcessStep());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","CumulativeNeed",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getCumulativeResource() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"cumulativeResource","CumulativeNeed",(getCumulativeResource()==null?"null":getCumulativeResource().toString()),"",WarningType.NOTNULL);
        }
        if (getProcessStep() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"processStep","CumulativeNeed",(getProcessStep()==null?"null":getProcessStep().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class CumulativeNeed
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
      if (attrName.equals("processStep")){
         return (List) ((Scenario)base).getListProcessStep();
      }
      return null;
   }

}

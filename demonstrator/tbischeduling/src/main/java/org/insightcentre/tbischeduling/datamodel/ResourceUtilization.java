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

public  class ResourceUtilization extends ApplicationObject{
/**
 *  
 *
*/

    public Integer active;

/**
 *  
 *
*/

    public DisjunctiveResource disjunctiveResource;

/**
 *  
 *
*/

    public Integer earliest;

/**
 *  
 *
*/

    public Integer idle;

/**
 *  
 *
*/

    public Double idlePercent;

/**
 *  
 *
*/

    public Integer latest;

/**
 *  
 *
*/

    public Integer setup;

/**
 *  
 *
*/

    public Double setupPercent;

/**
 *  
 *
*/

    public Solution solution;

/**
 *  
 *
*/

    public Integer use;

/**
 *  
 *
*/

    public Double utilization;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public ResourceUtilization(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public ResourceUtilization(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setActive(0);
        setDisjunctiveResource(null);
        setEarliest(0);
        setIdle(0);
        setIdlePercent(0.0);
        setLatest(0);
        setSetup(0);
        setSetupPercent(0.0);
        setSolution(null);
        setUse(0);
        setUtilization(0.0);
        applicationDataset.addResourceUtilization(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public ResourceUtilization(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Integer active,
            DisjunctiveResource disjunctiveResource,
            Integer earliest,
            Integer idle,
            Double idlePercent,
            Integer latest,
            Integer setup,
            Double setupPercent,
            Solution solution,
            Integer use,
            Double utilization){
        super(applicationDataset,
            id,
            name);
        setActive(active);
        setDisjunctiveResource(disjunctiveResource);
        setEarliest(earliest);
        setIdle(idle);
        setIdlePercent(idlePercent);
        setLatest(latest);
        setSetup(setup);
        setSetupPercent(setupPercent);
        setSolution(solution);
        setUse(use);
        setUtilization(utilization);
        applicationDataset.addResourceUtilization(this);
    }

    public ResourceUtilization(ResourceUtilization other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.active,
            other.disjunctiveResource,
            other.earliest,
            other.idle,
            other.idlePercent,
            other.latest,
            other.setup,
            other.setupPercent,
            other.solution,
            other.use,
            other.utilization);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeResourceUtilization(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute active
 *
 * @return Integer
*/

    public Integer getActive(){
        return this.active;
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
 *  get attribute earliest
 *
 * @return Integer
*/

    public Integer getEarliest(){
        return this.earliest;
    }

/**
 *  get attribute idle
 *
 * @return Integer
*/

    public Integer getIdle(){
        return this.idle;
    }

/**
 *  get attribute idlePercent
 *
 * @return Double
*/

    public Double getIdlePercent(){
        return this.idlePercent;
    }

/**
 *  get attribute latest
 *
 * @return Integer
*/

    public Integer getLatest(){
        return this.latest;
    }

/**
 *  get attribute setup
 *
 * @return Integer
*/

    public Integer getSetup(){
        return this.setup;
    }

/**
 *  get attribute setupPercent
 *
 * @return Double
*/

    public Double getSetupPercent(){
        return this.setupPercent;
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
 *  get attribute use
 *
 * @return Integer
*/

    public Integer getUse(){
        return this.use;
    }

/**
 *  get attribute utilization
 *
 * @return Double
*/

    public Double getUtilization(){
        return this.utilization;
    }

/**
 *  set attribute active, mark dataset as dirty, mark dataset as not valid
@param active Integer
 *
*/

    public void setActive(Integer active){
        this.active = active;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
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
 *  set attribute earliest, mark dataset as dirty, mark dataset as not valid
@param earliest Integer
 *
*/

    public void setEarliest(Integer earliest){
        this.earliest = earliest;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute idle, mark dataset as dirty, mark dataset as not valid
@param idle Integer
 *
*/

    public void setIdle(Integer idle){
        this.idle = idle;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute idlePercent, mark dataset as dirty, mark dataset as not valid
@param idlePercent Double
 *
*/

    public void setIdlePercent(Double idlePercent){
        this.idlePercent = idlePercent;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute latest, mark dataset as dirty, mark dataset as not valid
@param latest Integer
 *
*/

    public void setLatest(Integer latest){
        this.latest = latest;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute setup, mark dataset as dirty, mark dataset as not valid
@param setup Integer
 *
*/

    public void setSetup(Integer setup){
        this.setup = setup;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute setupPercent, mark dataset as dirty, mark dataset as not valid
@param setupPercent Double
 *
*/

    public void setSetupPercent(Double setupPercent){
        this.setupPercent = setupPercent;
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
 *  set attribute use, mark dataset as dirty, mark dataset as not valid
@param use Integer
 *
*/

    public void setUse(Integer use){
        this.use = use;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute utilization, mark dataset as dirty, mark dataset as not valid
@param utilization Double
 *
*/

    public void setUtilization(Double utilization){
        this.utilization = utilization;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute active, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incActive(){
        this.active++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute earliest, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incEarliest(){
        this.earliest++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute idle, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incIdle(){
        this.idle++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute latest, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incLatest(){
        this.latest++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute setup, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incSetup(){
        this.setup++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute use, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incUse(){
        this.use++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getActive()+ " " +getDisjunctiveResource().toColumnString()+ " " +getEarliest()+ " " +getIdle()+ " " +getIdlePercent()+ " " +getLatest()+ " " +getSetup()+ " " +getSetupPercent()+ " " +getSolution().toColumnString()+ " " +getUse()+ " " +getUtilization();
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
         out.println("<resourceUtilization "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " active=\""+toXMLActive()+"\""+
            " disjunctiveResource=\""+toXMLDisjunctiveResource()+"\""+
            " earliest=\""+toXMLEarliest()+"\""+
            " idle=\""+toXMLIdle()+"\""+
            " idlePercent=\""+toXMLIdlePercent()+"\""+
            " latest=\""+toXMLLatest()+"\""+
            " setup=\""+toXMLSetup()+"\""+
            " setupPercent=\""+toXMLSetupPercent()+"\""+
            " solution=\""+toXMLSolution()+"\""+
            " use=\""+toXMLUse()+"\""+
            " utilization=\""+toXMLUtilization()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLActive(){
        return this.getActive().toString();
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

    String toXMLEarliest(){
        return this.getEarliest().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLIdle(){
        return this.getIdle().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLIdlePercent(){
        return this.getIdlePercent().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLLatest(){
        return this.getLatest().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLSetup(){
        return this.getSetup().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLSetupPercent(){
        return this.getSetupPercent().toString();
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

    String toXMLUse(){
        return this.getUse().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLUtilization(){
        return this.getUtilization().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>ResourceUtilization</th>"+"<th>Name</th>"+"<th>DisjunctiveResource</th>"+"<th>Solution</th>"+"<th>Earliest</th>"+"<th>Latest</th>"+"<th>Active</th>"+"<th>Use</th>"+"<th>Setup</th>"+"<th>Idle</th>"+"<th>Utilization</th>"+"<th>SetupPercent</th>"+"<th>IdlePercent</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getDisjunctiveResource().toColumnString()+"</td>"+ " " +"<td>"+getSolution().toColumnString()+"</td>"+ " " +"<td>"+getEarliest()+"</td>"+ " " +"<td>"+getLatest()+"</td>"+ " " +"<td>"+getActive()+"</td>"+ " " +"<td>"+getUse()+"</td>"+ " " +"<td>"+getSetup()+"</td>"+ " " +"<td>"+getIdle()+"</td>"+ " " +"<td>"+getUtilization()+"</td>"+ " " +"<td>"+getSetupPercent()+"</td>"+ " " +"<td>"+getIdlePercent()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a ResourceUtilization item we are looking for
 * @param bList List<ResourceUtilization> list of items in which we are searching
 * @return ResourceUtilization entry of list b which is applicationSame() to a
*/

    public static ResourceUtilization find(ResourceUtilization a, List<ResourceUtilization> bList){
        for(ResourceUtilization b : bList){
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
 * @param name ResourceUtilization name of the object we are looking for
 * @return ResourceUtilization entry of the dataset with the given name; otherwise null
*/

    public static ResourceUtilization findByName(ApplicationDataset base, String name){
        for(ResourceUtilization a:base.getListResourceUtilization()) {
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
 * @param name ResourceUtilization name of the object we are looking for
 * @return ResourceUtilization entry of the dataset with the given name
*/

    public static ResourceUtilization findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(ResourceUtilization a:base.getListResourceUtilization()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        ResourceUtilization res = new ResourceUtilization(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ResourceUtilization first entry in the dataset of this type; null if that does not exists
*/

    public static ResourceUtilization findFirst(ApplicationDataset base){
        if (base.getListResourceUtilization().isEmpty()) {
            return null;
        }
        return base.getListResourceUtilization().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ResourceUtilization some entry in the dataset of this type; null if that does not exists
*/

    public static ResourceUtilization findAny(ApplicationDataset base){
        int size=base.getListResourceUtilization().size();
        if (size > 0) {
             return base.getListResourceUtilization().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ResourceUtilization last entry in the dataset of this type; null if that does not exists
*/

    public static ResourceUtilization findLast(ApplicationDataset base){
        int size=base.getListResourceUtilization().size();
        if (size > 0) {
             return base.getListResourceUtilization().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b ResourceUtilization compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(ResourceUtilization b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b ResourceUtilization compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(ResourceUtilization b){
      if(!this.getActive().equals(b.getActive())){
         System.out.println("Active");
        }
      if(!this.getDisjunctiveResource().applicationSame(b.getDisjunctiveResource())){
         System.out.println("DisjunctiveResource");
        }
      if(!this.getEarliest().equals(b.getEarliest())){
         System.out.println("Earliest");
        }
      if(!this.getIdle().equals(b.getIdle())){
         System.out.println("Idle");
        }
      if(!this.getIdlePercent().equals(b.getIdlePercent())){
         System.out.println("IdlePercent");
        }
      if(!this.getLatest().equals(b.getLatest())){
         System.out.println("Latest");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getSetup().equals(b.getSetup())){
         System.out.println("Setup");
        }
      if(!this.getSetupPercent().equals(b.getSetupPercent())){
         System.out.println("SetupPercent");
        }
      if(!this.getSolution().applicationSame(b.getSolution())){
         System.out.println("Solution");
        }
      if(!this.getUse().equals(b.getUse())){
         System.out.println("Use");
        }
      if(!this.getUtilization().equals(b.getUtilization())){
         System.out.println("Utilization");
        }
        return  this.getActive().equals(b.getActive()) &&
          this.getDisjunctiveResource().applicationSame(b.getDisjunctiveResource()) &&
          this.getEarliest().equals(b.getEarliest()) &&
          this.getIdle().equals(b.getIdle()) &&
          this.getIdlePercent().equals(b.getIdlePercent()) &&
          this.getLatest().equals(b.getLatest()) &&
          this.getName().equals(b.getName()) &&
          this.getSetup().equals(b.getSetup()) &&
          this.getSetupPercent().equals(b.getSetupPercent()) &&
          this.getSolution().applicationSame(b.getSolution()) &&
          this.getUse().equals(b.getUse()) &&
          this.getUtilization().equals(b.getUtilization());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","ResourceUtilization",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getDisjunctiveResource() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"disjunctiveResource","ResourceUtilization",(getDisjunctiveResource()==null?"null":getDisjunctiveResource().toString()),"",WarningType.NOTNULL);
        }
        if (getSolution() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"solution","ResourceUtilization",(getSolution()==null?"null":getSolution().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class ResourceUtilization
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
      if (attrName.equals("solution")){
         return (List) ((Scenario)base).getListSolution();
      }
      return null;
   }

}

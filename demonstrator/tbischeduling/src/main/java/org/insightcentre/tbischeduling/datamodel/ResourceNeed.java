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
import org.insightcentre.tbischeduling.datamodel.ColorBy;
import org.insightcentre.tbischeduling.datamodel.TaskLabel;
import org.insightcentre.tbischeduling.datamodel.JobOrder;
import org.insightcentre.tbischeduling.datamodel.ResourceChoice;
import org.insightcentre.tbischeduling.datamodel.LineChoice;
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

public  class ResourceNeed extends ApplicationObject{
/**
 *  
 *
*/

    public DisjunctiveResource disjunctiveResource;

/**
 *  
 *
*/

    public ProcessStep processStep;

/**
 *  
 *
*/

    public Integer value;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public ResourceNeed(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public ResourceNeed(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setDisjunctiveResource(null);
        setProcessStep(null);
        setValue(1);
        applicationDataset.addResourceNeed(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public ResourceNeed(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            DisjunctiveResource disjunctiveResource,
            ProcessStep processStep,
            Integer value){
        super(applicationDataset,
            id,
            name);
        setDisjunctiveResource(disjunctiveResource);
        setProcessStep(processStep);
        setValue(value);
        applicationDataset.addResourceNeed(this);
    }

    public ResourceNeed(ResourceNeed other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.disjunctiveResource,
            other.processStep,
            other.value);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeResourceNeed(this) && getApplicationDataset().removeApplicationObject(this);
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
 *  get attribute processStep
 *
 * @return ProcessStep
*/

    public ProcessStep getProcessStep(){
        return this.processStep;
    }

/**
 *  get attribute value
 *
 * @return Integer
*/

    public Integer getValue(){
        return this.value;
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
 *  set attribute value, mark dataset as dirty, mark dataset as not valid
@param value Integer
 *
*/

    public void setValue(Integer value){
        this.value = value;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute value, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incValue(){
        this.value++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getDisjunctiveResource().toColumnString()+ " " +getProcessStep().toColumnString()+ " " +getValue();
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
         out.println("<resourceNeed "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " disjunctiveResource=\""+toXMLDisjunctiveResource()+"\""+
            " processStep=\""+toXMLProcessStep()+"\""+
            " value=\""+toXMLValue()+"\""+" />");
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

    String toXMLProcessStep(){
        return "ID_"+this.getProcessStep().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLValue(){
        return this.getValue().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>ResourceNeed</th>"+"<th>Name</th>"+"<th>DisjunctiveResource</th>"+"<th>ProcessStep</th>"+"<th>Value</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getDisjunctiveResource().toColumnString()+"</td>"+ " " +"<td>"+getProcessStep().toColumnString()+"</td>"+ " " +"<td>"+getValue()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a ResourceNeed item we are looking for
 * @param bList List<ResourceNeed> list of items in which we are searching
 * @return ResourceNeed entry of list b which is applicationSame() to a
*/

    public static ResourceNeed find(ResourceNeed a, List<ResourceNeed> bList){
        for(ResourceNeed b : bList){
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
 * @param name ResourceNeed name of the object we are looking for
 * @return ResourceNeed entry of the dataset with the given name; otherwise null
*/

    public static ResourceNeed findByName(ApplicationDataset base, String name){
        for(ResourceNeed a:base.getListResourceNeed()) {
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
 * @param name ResourceNeed name of the object we are looking for
 * @return ResourceNeed entry of the dataset with the given name
*/

    public static ResourceNeed findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(ResourceNeed a:base.getListResourceNeed()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        ResourceNeed res = new ResourceNeed(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ResourceNeed first entry in the dataset of this type; null if that does not exists
*/

    public static ResourceNeed findFirst(ApplicationDataset base){
        if (base.getListResourceNeed().isEmpty()) {
            return null;
        }
        return base.getListResourceNeed().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ResourceNeed some entry in the dataset of this type; null if that does not exists
*/

    public static ResourceNeed findAny(ApplicationDataset base){
        int size=base.getListResourceNeed().size();
        if (size > 0) {
             return base.getListResourceNeed().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ResourceNeed last entry in the dataset of this type; null if that does not exists
*/

    public static ResourceNeed findLast(ApplicationDataset base){
        int size=base.getListResourceNeed().size();
        if (size > 0) {
             return base.getListResourceNeed().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b ResourceNeed compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(ResourceNeed b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b ResourceNeed compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(ResourceNeed b){
      if(!this.getDisjunctiveResource().applicationSame(b.getDisjunctiveResource())){
         System.out.println("DisjunctiveResource");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getProcessStep().applicationSame(b.getProcessStep())){
         System.out.println("ProcessStep");
        }
      if(!this.getValue().equals(b.getValue())){
         System.out.println("Value");
        }
        return  this.getDisjunctiveResource().applicationSame(b.getDisjunctiveResource()) &&
          this.getName().equals(b.getName()) &&
          this.getProcessStep().applicationSame(b.getProcessStep()) &&
          this.getValue().equals(b.getValue());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","ResourceNeed",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getDisjunctiveResource() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"disjunctiveResource","ResourceNeed",(getDisjunctiveResource()==null?"null":getDisjunctiveResource().toString()),"",WarningType.NOTNULL);
        }
        if (getProcessStep() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"processStep","ResourceNeed",(getProcessStep()==null?"null":getProcessStep().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class ResourceNeed
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
      if (attrName.equals("processStep")){
         return (List) ((Scenario)base).getListProcessStep();
      }
      return null;
   }

}

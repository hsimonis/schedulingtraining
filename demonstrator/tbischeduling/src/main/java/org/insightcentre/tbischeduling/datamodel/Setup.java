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

public  class Setup extends ApplicationObject{
/**
 *  
 *
*/

    public Integer defaultValue;

/**
 *  
 *
*/

    public List<DisjunctiveResource> disjunctiveResource;

/**
 *  
 *
*/

    public Integer sameValue;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public Setup(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public Setup(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setDefaultValue(0);
        setDisjunctiveResource(new ArrayList<DisjunctiveResource>());
        setSameValue(0);
        applicationDataset.addSetup(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public Setup(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Integer defaultValue,
            List<DisjunctiveResource> disjunctiveResource,
            Integer sameValue){
        super(applicationDataset,
            id,
            name);
        setDefaultValue(defaultValue);
        setDisjunctiveResource(disjunctiveResource);
        setSameValue(sameValue);
        applicationDataset.addSetup(this);
    }

    public Setup(Setup other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.defaultValue,
            other.disjunctiveResource,
            other.sameValue);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        getApplicationDataset().cascadeDisjunctiveResourceSetup(this);
        getApplicationDataset().cascadeSetupTypeSetup(this);
        return getApplicationDataset().removeSetup(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute defaultValue
 *
 * @return Integer
*/

    public Integer getDefaultValue(){
        return this.defaultValue;
    }

/**
 *  get attribute disjunctiveResource
 *
 * @return List<DisjunctiveResource>
*/

    public List<DisjunctiveResource> getDisjunctiveResource(){
        return this.disjunctiveResource;
    }

/**
 *  get attribute sameValue
 *
 * @return Integer
*/

    public Integer getSameValue(){
        return this.sameValue;
    }

/**
 *  set attribute defaultValue, mark dataset as dirty, mark dataset as not valid
@param defaultValue Integer
 *
*/

    public void setDefaultValue(Integer defaultValue){
        this.defaultValue = defaultValue;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute disjunctiveResource, mark dataset as dirty, mark dataset as not valid
@param disjunctiveResource List<DisjunctiveResource>
 *
*/

    public void setDisjunctiveResource(List<DisjunctiveResource> disjunctiveResource){
        this.disjunctiveResource = disjunctiveResource;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute sameValue, mark dataset as dirty, mark dataset as not valid
@param sameValue Integer
 *
*/

    public void setSameValue(Integer sameValue){
        this.sameValue = sameValue;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute defaultValue, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incDefaultValue(){
        this.defaultValue++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute sameValue, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incSameValue(){
        this.sameValue++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getDefaultValue()+ " " +getDisjunctiveResource()+ " " +getSameValue();
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
         out.println("<setup "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " defaultValue=\""+toXMLDefaultValue()+"\""+
            " disjunctiveResource=\""+toXMLDisjunctiveResource()+"\""+
            " sameValue=\""+toXMLSameValue()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDefaultValue(){
        return this.getDefaultValue().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDisjunctiveResource(){
        String str="";
        for(DisjunctiveResource x:getDisjunctiveResource()){
            str=str+" "+"ID_"+x.getId();
        }
        return str;
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLSameValue(){
        return this.getSameValue().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>Setup</th>"+"<th>Name</th>"+"<th>DefaultValue</th>"+"<th>SameValue</th>"+"<th>DisjunctiveResource</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getDefaultValue()+"</td>"+ " " +"<td>"+getSameValue()+"</td>"+ " " +"<td>"+getDisjunctiveResource()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a Setup item we are looking for
 * @param bList List<Setup> list of items in which we are searching
 * @return Setup entry of list b which is applicationSame() to a
*/

    public static Setup find(Setup a, List<Setup> bList){
        for(Setup b : bList){
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
 * @param name Setup name of the object we are looking for
 * @return Setup entry of the dataset with the given name; otherwise null
*/

    public static Setup findByName(ApplicationDataset base, String name){
        for(Setup a:base.getListSetup()) {
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
 * @param name Setup name of the object we are looking for
 * @return Setup entry of the dataset with the given name
*/

    public static Setup findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(Setup a:base.getListSetup()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        Setup res = new Setup(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Setup first entry in the dataset of this type; null if that does not exists
*/

    public static Setup findFirst(ApplicationDataset base){
        if (base.getListSetup().isEmpty()) {
            return null;
        }
        return base.getListSetup().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Setup some entry in the dataset of this type; null if that does not exists
*/

    public static Setup findAny(ApplicationDataset base){
        int size=base.getListSetup().size();
        if (size > 0) {
             return base.getListSetup().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Setup last entry in the dataset of this type; null if that does not exists
*/

    public static Setup findLast(ApplicationDataset base){
        int size=base.getListSetup().size();
        if (size > 0) {
             return base.getListSetup().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b Setup compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(Setup b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b Setup compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(Setup b){
      if(!this.getDefaultValue().equals(b.getDefaultValue())){
         System.out.println("DefaultValue");
        }
      if (true) {         System.out.println("DisjunctiveResource");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getSameValue().equals(b.getSameValue())){
         System.out.println("SameValue");
        }
        return  this.getDefaultValue().equals(b.getDefaultValue()) &&
          true &&
          this.getName().equals(b.getName()) &&
          this.getSameValue().equals(b.getSameValue());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","Setup",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getDisjunctiveResource() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"disjunctiveResource","Setup",(getDisjunctiveResource()==null?"null":getDisjunctiveResource().toString()),"",WarningType.NOTNULL);
        }
        if (getDisjunctiveResource().size() == 0){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"disjunctiveResource","Setup",(getDisjunctiveResource()==null?"null":getDisjunctiveResource().toString()),"",WarningType.NOTEMPTY);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class Setup
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

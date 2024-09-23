// licence details to be added
package org.insightcentre.tbischeduling.datamodel;
import org.insightcentre.tbischeduling.datamodel.ApplicationDataset;
import org.insightcentre.tbischeduling.datamodel.ApplicationObject;
import org.insightcentre.tbischeduling.datamodel.ApplicationDifference;
import org.insightcentre.tbischeduling.datamodel.ApplicationWarning;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.Problem;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.datamodel.ProcessStep;
import org.insightcentre.tbischeduling.datamodel.ProcessSequence;
import org.insightcentre.tbischeduling.datamodel.DisjunctiveResource;
import org.insightcentre.tbischeduling.datamodel.CumulativeResource;
import org.insightcentre.tbischeduling.datamodel.ResourceNeed;
import org.insightcentre.tbischeduling.datamodel.Product;
import org.insightcentre.tbischeduling.datamodel.Order;
import org.insightcentre.tbischeduling.datamodel.Job;
import org.insightcentre.tbischeduling.datamodel.Task;
import org.insightcentre.tbischeduling.datamodel.Solution;
import org.insightcentre.tbischeduling.datamodel.TaskAssignment;
import org.insightcentre.tbischeduling.datamodel.JobAssignment;
import org.insightcentre.tbischeduling.datamodel.DifferenceType;
import org.insightcentre.tbischeduling.datamodel.WarningType;
import org.insightcentre.tbischeduling.datamodel.SequenceType;
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

public  class Solution extends ApplicationObject{
/**
 *  
 *
*/

    public Integer objectiveValue;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public Solution(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public Solution(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setObjectiveValue(0);
        applicationDataset.addSolution(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public Solution(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Integer objectiveValue){
        super(applicationDataset,
            id,
            name);
        setObjectiveValue(objectiveValue);
        applicationDataset.addSolution(this);
    }

    public Solution(Solution other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.objectiveValue);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        getApplicationDataset().cascadeJobAssignmentSolution(this);
        return getApplicationDataset().removeSolution(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute objectiveValue
 *
 * @return Integer
*/

    public Integer getObjectiveValue(){
        return this.objectiveValue;
    }

/**
 *  set attribute objectiveValue, mark dataset as dirty, mark dataset as not valid
@param objectiveValue Integer
 *
*/

    public void setObjectiveValue(Integer objectiveValue){
        this.objectiveValue = objectiveValue;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute objectiveValue, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incObjectiveValue(){
        this.objectiveValue++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getObjectiveValue();
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
         out.println("<solution "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " objectiveValue=\""+toXMLObjectiveValue()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLObjectiveValue(){
        return this.getObjectiveValue().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>Solution</th>"+"<th>Name</th>"+"<th>ObjectiveValue</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getObjectiveValue()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a Solution item we are looking for
 * @param bList List<Solution> list of items in which we are searching
 * @return Solution entry of list b which is applicationSame() to a
*/

    public static Solution find(Solution a, List<Solution> bList){
        for(Solution b : bList){
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
 * @param name Solution name of the object we are looking for
 * @return Solution entry of the dataset with the given name; otherwise null
*/

    public static Solution findByName(ApplicationDataset base, String name){
        for(Solution a:base.getListSolution()) {
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
 * @param name Solution name of the object we are looking for
 * @return Solution entry of the dataset with the given name
*/

    public static Solution findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(Solution a:base.getListSolution()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        Solution res = new Solution(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Solution first entry in the dataset of this type; null if that does not exists
*/

    public static Solution findFirst(ApplicationDataset base){
        if (base.getListSolution().isEmpty()) {
            return null;
        }
        return base.getListSolution().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Solution some entry in the dataset of this type; null if that does not exists
*/

    public static Solution findAny(ApplicationDataset base){
        int size=base.getListSolution().size();
        if (size > 0) {
             return base.getListSolution().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Solution last entry in the dataset of this type; null if that does not exists
*/

    public static Solution findLast(ApplicationDataset base){
        int size=base.getListSolution().size();
        if (size > 0) {
             return base.getListSolution().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b Solution compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(Solution b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b Solution compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(Solution b){
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getObjectiveValue().equals(b.getObjectiveValue())){
         System.out.println("ObjectiveValue");
        }
        return  this.getName().equals(b.getName()) &&
          this.getObjectiveValue().equals(b.getObjectiveValue());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","Solution",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class Solution
    }

/**
 *  This method states if the class depends on the solver.
 *
*/

    public static Boolean isSolverDependent(){
        return false;
    }

   public List<ApplicationObjectInterface> getFeasibleValues(ApplicationDatasetInterface base,String attrName){
      return null;
   }

}

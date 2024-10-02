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
import org.insightcentre.tbischeduling.datamodel.ResourceActivity;
import org.insightcentre.tbischeduling.datamodel.Order;
import org.insightcentre.tbischeduling.datamodel.Job;
import org.insightcentre.tbischeduling.datamodel.Task;
import org.insightcentre.tbischeduling.datamodel.WiP;
import org.insightcentre.tbischeduling.datamodel.Downtime;
import org.insightcentre.tbischeduling.datamodel.SolverRun;
import org.insightcentre.tbischeduling.datamodel.Solution;
import org.insightcentre.tbischeduling.datamodel.JobAssignment;
import org.insightcentre.tbischeduling.datamodel.TaskAssignment;
import org.insightcentre.tbischeduling.datamodel.ResourceUtilization;
import org.insightcentre.tbischeduling.datamodel.IntermediateSolution;
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

public  class InputError extends ApplicationObject{
/**
 *  
 *
*/

    public String classDesc;

/**
 *  
 *
*/

    public String description;

/**
 *  
 *
*/

    public String field;

/**
 *  
 *
*/

    public String item;

/**
 *  
 *
*/

    public Severity severity;

/**
 *  
 *
*/

    public String value;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public InputError(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public InputError(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setClassDesc("");
        setDescription("");
        setField("");
        setItem("");
        setSeverity(null);
        setValue("");
        applicationDataset.addInputError(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public InputError(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            String classDesc,
            String description,
            String field,
            String item,
            Severity severity,
            String value){
        super(applicationDataset,
            id,
            name);
        setClassDesc(classDesc);
        setDescription(description);
        setField(field);
        setItem(item);
        setSeverity(severity);
        setValue(value);
        applicationDataset.addInputError(this);
    }

    public InputError(InputError other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.classDesc,
            other.description,
            other.field,
            other.item,
            other.severity,
            other.value);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeInputError(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute classDesc
 *
 * @return String
*/

    public String getClassDesc(){
        return this.classDesc;
    }

/**
 *  get attribute description
 *
 * @return String
*/

    public String getDescription(){
        return this.description;
    }

/**
 *  get attribute field
 *
 * @return String
*/

    public String getField(){
        return this.field;
    }

/**
 *  get attribute item
 *
 * @return String
*/

    public String getItem(){
        return this.item;
    }

/**
 *  get attribute severity
 *
 * @return Severity
*/

    public Severity getSeverity(){
        return this.severity;
    }

/**
 *  get attribute value
 *
 * @return String
*/

    public String getValue(){
        return this.value;
    }

/**
 *  set attribute classDesc, mark dataset as dirty, mark dataset as not valid
@param classDesc String
 *
*/

    public void setClassDesc(String classDesc){
        this.classDesc = classDesc;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute description, mark dataset as dirty, mark dataset as not valid
@param description String
 *
*/

    public void setDescription(String description){
        this.description = description;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute field, mark dataset as dirty, mark dataset as not valid
@param field String
 *
*/

    public void setField(String field){
        this.field = field;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute item, mark dataset as dirty, mark dataset as not valid
@param item String
 *
*/

    public void setItem(String item){
        this.item = item;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute severity, mark dataset as dirty, mark dataset as not valid
@param severity Severity
 *
*/

    public void setSeverity(Severity severity){
        this.severity = severity;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute value, mark dataset as dirty, mark dataset as not valid
@param value String
 *
*/

    public void setValue(String value){
        this.value = value;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getClassDesc()+ " " +getDescription()+ " " +getField()+ " " +getItem()+ " " +getSeverity()+ " " +getValue();
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
         out.println("<inputError "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " classDesc=\""+toXMLClassDesc()+"\""+
            " description=\""+toXMLDescription()+"\""+
            " field=\""+toXMLField()+"\""+
            " item=\""+toXMLItem()+"\""+
            " severity=\""+toXMLSeverity()+"\""+
            " value=\""+toXMLValue()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLClassDesc(){
        return this.safeXML(getClassDesc());
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDescription(){
        return this.safeXML(getDescription());
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLField(){
        return this.safeXML(getField());
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLItem(){
        return this.safeXML(getItem());
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLSeverity(){
        return this.getSeverity().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLValue(){
        return this.safeXML(getValue());
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>InputError</th>"+"<th>Name</th>"+"<th>ClassDesc</th>"+"<th>Item</th>"+"<th>Field</th>"+"<th>Value</th>"+"<th>Description</th>"+"<th>Severity</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getClassDesc()+"</td>"+ " " +"<td>"+getItem()+"</td>"+ " " +"<td>"+getField()+"</td>"+ " " +"<td>"+getValue()+"</td>"+ " " +"<td>"+getDescription()+"</td>"+ " " +"<td>"+getSeverity()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a InputError item we are looking for
 * @param bList List<InputError> list of items in which we are searching
 * @return InputError entry of list b which is applicationSame() to a
*/

    public static InputError find(InputError a, List<InputError> bList){
        for(InputError b : bList){
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
 * @param name InputError name of the object we are looking for
 * @return InputError entry of the dataset with the given name; otherwise null
*/

    public static InputError findByName(ApplicationDataset base, String name){
        for(InputError a:base.getListInputError()) {
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
 * @param name InputError name of the object we are looking for
 * @return InputError entry of the dataset with the given name
*/

    public static InputError findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(InputError a:base.getListInputError()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        InputError res = new InputError(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return InputError first entry in the dataset of this type; null if that does not exists
*/

    public static InputError findFirst(ApplicationDataset base){
        if (base.getListInputError().isEmpty()) {
            return null;
        }
        return base.getListInputError().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return InputError some entry in the dataset of this type; null if that does not exists
*/

    public static InputError findAny(ApplicationDataset base){
        int size=base.getListInputError().size();
        if (size > 0) {
             return base.getListInputError().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return InputError last entry in the dataset of this type; null if that does not exists
*/

    public static InputError findLast(ApplicationDataset base){
        int size=base.getListInputError().size();
        if (size > 0) {
             return base.getListInputError().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b InputError compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(InputError b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b InputError compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(InputError b){
      if(!this.getClassDesc().equals(b.getClassDesc())){
         System.out.println("ClassDesc");
        }
      if(!this.getDescription().equals(b.getDescription())){
         System.out.println("Description");
        }
      if(!this.getField().equals(b.getField())){
         System.out.println("Field");
        }
      if(!this.getItem().equals(b.getItem())){
         System.out.println("Item");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getSeverity().equals(b.getSeverity())){
         System.out.println("Severity");
        }
      if(!this.getValue().equals(b.getValue())){
         System.out.println("Value");
        }
        return  this.getClassDesc().equals(b.getClassDesc()) &&
          this.getDescription().equals(b.getDescription()) &&
          this.getField().equals(b.getField()) &&
          this.getItem().equals(b.getItem()) &&
          this.getName().equals(b.getName()) &&
          this.getSeverity().equals(b.getSeverity()) &&
          this.getValue().equals(b.getValue());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","InputError",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class InputError
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

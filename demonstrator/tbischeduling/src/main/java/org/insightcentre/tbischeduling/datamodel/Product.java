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

public  class Product extends ApplicationObject{
/**
 *  
 *
*/

    public Process process;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public Product(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public Product(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setProcess(null);
        applicationDataset.addProduct(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public Product(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Process process){
        super(applicationDataset,
            id,
            name);
        setProcess(process);
        applicationDataset.addProduct(this);
    }

    public Product(Product other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.process);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        getApplicationDataset().cascadeOrderProduct(this);
        return getApplicationDataset().removeProduct(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute process
 *
 * @return Process
*/

    public Process getProcess(){
        return this.process;
    }

/**
 *  set attribute process, mark dataset as dirty, mark dataset as not valid
@param process Process
 *
*/

    public void setProcess(Process process){
        this.process = process;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getProcess().toColumnString();
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
         out.println("<product "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " process=\""+toXMLProcess()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLProcess(){
        return "ID_"+this.getProcess().getId().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>Product</th>"+"<th>Name</th>"+"<th>Process</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getProcess().toColumnString()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a Product item we are looking for
 * @param bList List<Product> list of items in which we are searching
 * @return Product entry of list b which is applicationSame() to a
*/

    public static Product find(Product a, List<Product> bList){
        for(Product b : bList){
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
 * @param name Product name of the object we are looking for
 * @return Product entry of the dataset with the given name; otherwise null
*/

    public static Product findByName(ApplicationDataset base, String name){
        for(Product a:base.getListProduct()) {
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
 * @param name Product name of the object we are looking for
 * @return Product entry of the dataset with the given name
*/

    public static Product findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(Product a:base.getListProduct()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        Product res = new Product(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Product first entry in the dataset of this type; null if that does not exists
*/

    public static Product findFirst(ApplicationDataset base){
        if (base.getListProduct().isEmpty()) {
            return null;
        }
        return base.getListProduct().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Product some entry in the dataset of this type; null if that does not exists
*/

    public static Product findAny(ApplicationDataset base){
        int size=base.getListProduct().size();
        if (size > 0) {
             return base.getListProduct().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Product last entry in the dataset of this type; null if that does not exists
*/

    public static Product findLast(ApplicationDataset base){
        int size=base.getListProduct().size();
        if (size > 0) {
             return base.getListProduct().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b Product compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(Product b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b Product compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(Product b){
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getProcess().applicationSame(b.getProcess())){
         System.out.println("Process");
        }
        return  this.getName().equals(b.getName()) &&
          this.getProcess().applicationSame(b.getProcess());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","Product",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getProcess() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"process","Product",(getProcess()==null?"null":getProcess().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class Product
    }

/**
 *  This method states if the class depends on the solver.
 *
*/

    public static Boolean isSolverDependent(){
        return false;
    }

   public List<ApplicationObjectInterface> getFeasibleValues(ApplicationDatasetInterface base,String attrName){
      if (attrName.equals("process")){
         return (List) ((Scenario)base).getListProcess();
      }
      return null;
   }

}

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

public  class Order extends ApplicationObject{
/**
 *  
 *
*/

    public Integer due;

/**
 *  
 *
*/

    public DateTime dueDate;

/**
 *  
 *
*/

    public Double earlinessWeight;

/**
 *  
 *
*/

    public Double latenessWeight;

/**
 *  
 *
*/

    public Product product;

/**
 *  
 *
*/

    public Integer qty;

/**
 *  
 *
*/

    public Integer release;

/**
 *  
 *
*/

    public DateTime releaseDate;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public Order(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public Order(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setDue(0);
        setDueDate(new DateTime());
        setEarlinessWeight(1.0);
        setLatenessWeight(1.0);
        setProduct(null);
        setQty(0);
        setRelease(0);
        setReleaseDate(new DateTime());
        applicationDataset.addOrder(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public Order(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Integer due,
            DateTime dueDate,
            Double earlinessWeight,
            Double latenessWeight,
            Product product,
            Integer qty,
            Integer release,
            DateTime releaseDate){
        super(applicationDataset,
            id,
            name);
        setDue(due);
        setDueDate(dueDate);
        setEarlinessWeight(earlinessWeight);
        setLatenessWeight(latenessWeight);
        setProduct(product);
        setQty(qty);
        setRelease(release);
        setReleaseDate(releaseDate);
        applicationDataset.addOrder(this);
    }

    public Order(Order other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.due,
            other.dueDate,
            other.earlinessWeight,
            other.latenessWeight,
            other.product,
            other.qty,
            other.release,
            other.releaseDate);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        getApplicationDataset().cascadeJobOrder(this);
        return getApplicationDataset().removeOrder(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute due
 *
 * @return Integer
*/

    public Integer getDue(){
        return this.due;
    }

/**
 *  get attribute dueDate
 *
 * @return DateTime
*/

    public DateTime getDueDate(){
        return this.dueDate;
    }

/**
 *  get attribute earlinessWeight
 *
 * @return Double
*/

    public Double getEarlinessWeight(){
        return this.earlinessWeight;
    }

/**
 *  get attribute latenessWeight
 *
 * @return Double
*/

    public Double getLatenessWeight(){
        return this.latenessWeight;
    }

/**
 *  get attribute product
 *
 * @return Product
*/

    public Product getProduct(){
        return this.product;
    }

/**
 *  get attribute qty
 *
 * @return Integer
*/

    public Integer getQty(){
        return this.qty;
    }

/**
 *  get attribute release
 *
 * @return Integer
*/

    public Integer getRelease(){
        return this.release;
    }

/**
 *  get attribute releaseDate
 *
 * @return DateTime
*/

    public DateTime getReleaseDate(){
        return this.releaseDate;
    }

/**
 *  set attribute due, mark dataset as dirty, mark dataset as not valid
@param due Integer
 *
*/

    public void setDue(Integer due){
        this.due = due;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute dueDate, mark dataset as dirty, mark dataset as not valid
@param dueDate DateTime
 *
*/

    public void setDueDate(DateTime dueDate){
        this.dueDate = dueDate;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute earlinessWeight, mark dataset as dirty, mark dataset as not valid
@param earlinessWeight Double
 *
*/

    public void setEarlinessWeight(Double earlinessWeight){
        this.earlinessWeight = earlinessWeight;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute latenessWeight, mark dataset as dirty, mark dataset as not valid
@param latenessWeight Double
 *
*/

    public void setLatenessWeight(Double latenessWeight){
        this.latenessWeight = latenessWeight;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute product, mark dataset as dirty, mark dataset as not valid
@param product Product
 *
*/

    public void setProduct(Product product){
        this.product = product;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute qty, mark dataset as dirty, mark dataset as not valid
@param qty Integer
 *
*/

    public void setQty(Integer qty){
        this.qty = qty;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute release, mark dataset as dirty, mark dataset as not valid
@param release Integer
 *
*/

    public void setRelease(Integer release){
        this.release = release;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute releaseDate, mark dataset as dirty, mark dataset as not valid
@param releaseDate DateTime
 *
*/

    public void setReleaseDate(DateTime releaseDate){
        this.releaseDate = releaseDate;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute due, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incDue(){
        this.due++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute qty, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incQty(){
        this.qty++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute release, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incRelease(){
        this.release++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getDue()+ " " +getDueDate()+ " " +getEarlinessWeight()+ " " +getLatenessWeight()+ " " +getProduct().toColumnString()+ " " +getQty()+ " " +getRelease()+ " " +getReleaseDate();
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
         out.println("<order "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " due=\""+toXMLDue()+"\""+
            " dueDate=\""+toXMLDueDate()+"\""+
            " earlinessWeight=\""+toXMLEarlinessWeight()+"\""+
            " latenessWeight=\""+toXMLLatenessWeight()+"\""+
            " product=\""+toXMLProduct()+"\""+
            " qty=\""+toXMLQty()+"\""+
            " release=\""+toXMLRelease()+"\""+
            " releaseDate=\""+toXMLReleaseDate()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDue(){
        return this.getDue().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDueDate(){
        return this.getDueDate().toXML();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLEarlinessWeight(){
        return this.getEarlinessWeight().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLLatenessWeight(){
        return this.getLatenessWeight().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLProduct(){
        return "ID_"+this.getProduct().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLQty(){
        return this.getQty().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLRelease(){
        return this.getRelease().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLReleaseDate(){
        return this.getReleaseDate().toXML();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>Order</th>"+"<th>Name</th>"+"<th>Product</th>"+"<th>Qty</th>"+"<th>Due</th>"+"<th>DueDate</th>"+"<th>Release</th>"+"<th>ReleaseDate</th>"+"<th>LatenessWeight</th>"+"<th>EarlinessWeight</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getProduct().toColumnString()+"</td>"+ " " +"<td>"+getQty()+"</td>"+ " " +"<td>"+getDue()+"</td>"+ " " +"<td>"+getDueDate()+"</td>"+ " " +"<td>"+getRelease()+"</td>"+ " " +"<td>"+getReleaseDate()+"</td>"+ " " +"<td>"+getLatenessWeight()+"</td>"+ " " +"<td>"+getEarlinessWeight()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a Order item we are looking for
 * @param bList List<Order> list of items in which we are searching
 * @return Order entry of list b which is applicationSame() to a
*/

    public static Order find(Order a, List<Order> bList){
        for(Order b : bList){
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
 * @param name Order name of the object we are looking for
 * @return Order entry of the dataset with the given name; otherwise null
*/

    public static Order findByName(ApplicationDataset base, String name){
        for(Order a:base.getListOrder()) {
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
 * @param name Order name of the object we are looking for
 * @return Order entry of the dataset with the given name
*/

    public static Order findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(Order a:base.getListOrder()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        Order res = new Order(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Order first entry in the dataset of this type; null if that does not exists
*/

    public static Order findFirst(ApplicationDataset base){
        if (base.getListOrder().isEmpty()) {
            return null;
        }
        return base.getListOrder().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Order some entry in the dataset of this type; null if that does not exists
*/

    public static Order findAny(ApplicationDataset base){
        int size=base.getListOrder().size();
        if (size > 0) {
             return base.getListOrder().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Order last entry in the dataset of this type; null if that does not exists
*/

    public static Order findLast(ApplicationDataset base){
        int size=base.getListOrder().size();
        if (size > 0) {
             return base.getListOrder().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b Order compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(Order b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b Order compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(Order b){
      if(!this.getDue().equals(b.getDue())){
         System.out.println("Due");
        }
      if(!this.getDueDate().applicationEqual(b.getDueDate())){
         System.out.println("DueDate");
        }
      if(!this.getEarlinessWeight().equals(b.getEarlinessWeight())){
         System.out.println("EarlinessWeight");
        }
      if(!this.getLatenessWeight().equals(b.getLatenessWeight())){
         System.out.println("LatenessWeight");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getProduct().applicationSame(b.getProduct())){
         System.out.println("Product");
        }
      if(!this.getQty().equals(b.getQty())){
         System.out.println("Qty");
        }
      if(!this.getRelease().equals(b.getRelease())){
         System.out.println("Release");
        }
      if(!this.getReleaseDate().applicationEqual(b.getReleaseDate())){
         System.out.println("ReleaseDate");
        }
        return  this.getDue().equals(b.getDue()) &&
          this.getDueDate().applicationEqual(b.getDueDate()) &&
          this.getEarlinessWeight().equals(b.getEarlinessWeight()) &&
          this.getLatenessWeight().equals(b.getLatenessWeight()) &&
          this.getName().equals(b.getName()) &&
          this.getProduct().applicationSame(b.getProduct()) &&
          this.getQty().equals(b.getQty()) &&
          this.getRelease().equals(b.getRelease()) &&
          this.getReleaseDate().applicationEqual(b.getReleaseDate());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","Order",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getProduct() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"product","Order",(getProduct()==null?"null":getProduct().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class Order
    }

/**
 *  This method states if the class depends on the solver.
 *
*/

    public static Boolean isSolverDependent(){
        return false;
    }

   public List<ApplicationObjectInterface> getFeasibleValues(ApplicationDatasetInterface base,String attrName){
      if (attrName.equals("product")){
         return (List) ((Scenario)base).getListProduct();
      }
      return null;
   }

}

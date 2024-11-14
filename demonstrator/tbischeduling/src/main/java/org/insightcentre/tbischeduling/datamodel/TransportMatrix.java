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

public  class TransportMatrix extends ApplicationObject{
/**
 *  
 *
*/

    public DisjunctiveResource from;

/**
 *  
 *
*/

    public DisjunctiveResource to;

/**
 *  
 *
*/

    public Integer value;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public TransportMatrix(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public TransportMatrix(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setFrom(null);
        setTo(null);
        setValue(0);
        applicationDataset.addTransportMatrix(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public TransportMatrix(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            DisjunctiveResource from,
            DisjunctiveResource to,
            Integer value){
        super(applicationDataset,
            id,
            name);
        setFrom(from);
        setTo(to);
        setValue(value);
        applicationDataset.addTransportMatrix(this);
    }

    public TransportMatrix(TransportMatrix other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.from,
            other.to,
            other.value);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeTransportMatrix(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute from
 *
 * @return DisjunctiveResource
*/

    public DisjunctiveResource getFrom(){
        return this.from;
    }

/**
 *  get attribute to
 *
 * @return DisjunctiveResource
*/

    public DisjunctiveResource getTo(){
        return this.to;
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
 *  set attribute from, mark dataset as dirty, mark dataset as not valid
@param from DisjunctiveResource
 *
*/

    public void setFrom(DisjunctiveResource from){
        this.from = from;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute to, mark dataset as dirty, mark dataset as not valid
@param to DisjunctiveResource
 *
*/

    public void setTo(DisjunctiveResource to){
        this.to = to;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getFrom().toColumnString()+ " " +getTo().toColumnString()+ " " +getValue();
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
         out.println("<transportMatrix "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " from=\""+toXMLFrom()+"\""+
            " to=\""+toXMLTo()+"\""+
            " value=\""+toXMLValue()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLFrom(){
        return "ID_"+this.getFrom().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLTo(){
        return "ID_"+this.getTo().getId().toString();
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
        return "<tr><th>TransportMatrix</th>"+"<th>Name</th>"+"<th>From</th>"+"<th>To</th>"+"<th>Value</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getFrom().toColumnString()+"</td>"+ " " +"<td>"+getTo().toColumnString()+"</td>"+ " " +"<td>"+getValue()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a TransportMatrix item we are looking for
 * @param bList List<TransportMatrix> list of items in which we are searching
 * @return TransportMatrix entry of list b which is applicationSame() to a
*/

    public static TransportMatrix find(TransportMatrix a, List<TransportMatrix> bList){
        for(TransportMatrix b : bList){
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
 * @param name TransportMatrix name of the object we are looking for
 * @return TransportMatrix entry of the dataset with the given name; otherwise null
*/

    public static TransportMatrix findByName(ApplicationDataset base, String name){
        for(TransportMatrix a:base.getListTransportMatrix()) {
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
 * @param name TransportMatrix name of the object we are looking for
 * @return TransportMatrix entry of the dataset with the given name
*/

    public static TransportMatrix findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(TransportMatrix a:base.getListTransportMatrix()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        TransportMatrix res = new TransportMatrix(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return TransportMatrix first entry in the dataset of this type; null if that does not exists
*/

    public static TransportMatrix findFirst(ApplicationDataset base){
        if (base.getListTransportMatrix().isEmpty()) {
            return null;
        }
        return base.getListTransportMatrix().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return TransportMatrix some entry in the dataset of this type; null if that does not exists
*/

    public static TransportMatrix findAny(ApplicationDataset base){
        int size=base.getListTransportMatrix().size();
        if (size > 0) {
             return base.getListTransportMatrix().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return TransportMatrix last entry in the dataset of this type; null if that does not exists
*/

    public static TransportMatrix findLast(ApplicationDataset base){
        int size=base.getListTransportMatrix().size();
        if (size > 0) {
             return base.getListTransportMatrix().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b TransportMatrix compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(TransportMatrix b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b TransportMatrix compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(TransportMatrix b){
      if(!this.getFrom().applicationSame(b.getFrom())){
         System.out.println("From");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getTo().applicationSame(b.getTo())){
         System.out.println("To");
        }
      if(!this.getValue().equals(b.getValue())){
         System.out.println("Value");
        }
        return  this.getFrom().applicationSame(b.getFrom()) &&
          this.getName().equals(b.getName()) &&
          this.getTo().applicationSame(b.getTo()) &&
          this.getValue().equals(b.getValue());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","TransportMatrix",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getFrom() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"from","TransportMatrix",(getFrom()==null?"null":getFrom().toString()),"",WarningType.NOTNULL);
        }
        if (getTo() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"to","TransportMatrix",(getTo()==null?"null":getTo().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class TransportMatrix
    }

/**
 *  This method states if the class depends on the solver.
 *
*/

    public static Boolean isSolverDependent(){
        return false;
    }

   public List<ApplicationObjectInterface> getFeasibleValues(ApplicationDatasetInterface base,String attrName){
      if (attrName.equals("from")){
         return (List) ((Scenario)base).getListDisjunctiveResource();
      }
      if (attrName.equals("to")){
         return (List) ((Scenario)base).getListDisjunctiveResource();
      }
      return null;
   }

}

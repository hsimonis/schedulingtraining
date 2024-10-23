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

public  class ProcessSequence extends ApplicationObject{
/**
 *  
 *
*/

    public ProcessStep after;

/**
 *  
 *
*/

    public ProcessStep before;

/**
 *  
 *
*/

    public Integer offset;

/**
 *  
 *
*/

    public SequenceType sequenceType;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public ProcessSequence(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public ProcessSequence(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setAfter(null);
        setBefore(null);
        setOffset(0);
        setSequenceType(null);
        applicationDataset.addProcessSequence(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public ProcessSequence(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            ProcessStep after,
            ProcessStep before,
            Integer offset,
            SequenceType sequenceType){
        super(applicationDataset,
            id,
            name);
        setAfter(after);
        setBefore(before);
        setOffset(offset);
        setSequenceType(sequenceType);
        applicationDataset.addProcessSequence(this);
    }

    public ProcessSequence(ProcessSequence other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.after,
            other.before,
            other.offset,
            other.sequenceType);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeProcessSequence(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute after
 *
 * @return ProcessStep
*/

    public ProcessStep getAfter(){
        return this.after;
    }

/**
 *  get attribute before
 *
 * @return ProcessStep
*/

    public ProcessStep getBefore(){
        return this.before;
    }

/**
 *  get attribute offset
 *
 * @return Integer
*/

    public Integer getOffset(){
        return this.offset;
    }

/**
 *  get attribute sequenceType
 *
 * @return SequenceType
*/

    public SequenceType getSequenceType(){
        return this.sequenceType;
    }

/**
 *  set attribute after, mark dataset as dirty, mark dataset as not valid
@param after ProcessStep
 *
*/

    public void setAfter(ProcessStep after){
        this.after = after;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute before, mark dataset as dirty, mark dataset as not valid
@param before ProcessStep
 *
*/

    public void setBefore(ProcessStep before){
        this.before = before;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute offset, mark dataset as dirty, mark dataset as not valid
@param offset Integer
 *
*/

    public void setOffset(Integer offset){
        this.offset = offset;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute sequenceType, mark dataset as dirty, mark dataset as not valid
@param sequenceType SequenceType
 *
*/

    public void setSequenceType(SequenceType sequenceType){
        this.sequenceType = sequenceType;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute offset, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incOffset(){
        this.offset++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getAfter().toColumnString()+ " " +getBefore().toColumnString()+ " " +getOffset()+ " " +getSequenceType();
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
         out.println("<processSequence "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " after=\""+toXMLAfter()+"\""+
            " before=\""+toXMLBefore()+"\""+
            " offset=\""+toXMLOffset()+"\""+
            " sequenceType=\""+toXMLSequenceType()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLAfter(){
        return "ID_"+this.getAfter().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLBefore(){
        return "ID_"+this.getBefore().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLOffset(){
        return this.getOffset().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLSequenceType(){
        return this.getSequenceType().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>ProcessSequence</th>"+"<th>Name</th>"+"<th>Before</th>"+"<th>After</th>"+"<th>SequenceType</th>"+"<th>Offset</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getBefore().toColumnString()+"</td>"+ " " +"<td>"+getAfter().toColumnString()+"</td>"+ " " +"<td>"+getSequenceType()+"</td>"+ " " +"<td>"+getOffset()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a ProcessSequence item we are looking for
 * @param bList List<ProcessSequence> list of items in which we are searching
 * @return ProcessSequence entry of list b which is applicationSame() to a
*/

    public static ProcessSequence find(ProcessSequence a, List<ProcessSequence> bList){
        for(ProcessSequence b : bList){
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
 * @param name ProcessSequence name of the object we are looking for
 * @return ProcessSequence entry of the dataset with the given name; otherwise null
*/

    public static ProcessSequence findByName(ApplicationDataset base, String name){
        for(ProcessSequence a:base.getListProcessSequence()) {
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
 * @param name ProcessSequence name of the object we are looking for
 * @return ProcessSequence entry of the dataset with the given name
*/

    public static ProcessSequence findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(ProcessSequence a:base.getListProcessSequence()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        ProcessSequence res = new ProcessSequence(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ProcessSequence first entry in the dataset of this type; null if that does not exists
*/

    public static ProcessSequence findFirst(ApplicationDataset base){
        if (base.getListProcessSequence().isEmpty()) {
            return null;
        }
        return base.getListProcessSequence().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ProcessSequence some entry in the dataset of this type; null if that does not exists
*/

    public static ProcessSequence findAny(ApplicationDataset base){
        int size=base.getListProcessSequence().size();
        if (size > 0) {
             return base.getListProcessSequence().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ProcessSequence last entry in the dataset of this type; null if that does not exists
*/

    public static ProcessSequence findLast(ApplicationDataset base){
        int size=base.getListProcessSequence().size();
        if (size > 0) {
             return base.getListProcessSequence().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b ProcessSequence compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(ProcessSequence b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b ProcessSequence compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(ProcessSequence b){
      if(!this.getAfter().applicationSame(b.getAfter())){
         System.out.println("After");
        }
      if(!this.getBefore().applicationSame(b.getBefore())){
         System.out.println("Before");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getOffset().equals(b.getOffset())){
         System.out.println("Offset");
        }
      if(!this.getSequenceType().equals(b.getSequenceType())){
         System.out.println("SequenceType");
        }
        return  this.getAfter().applicationSame(b.getAfter()) &&
          this.getBefore().applicationSame(b.getBefore()) &&
          this.getName().equals(b.getName()) &&
          this.getOffset().equals(b.getOffset()) &&
          this.getSequenceType().equals(b.getSequenceType());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","ProcessSequence",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getAfter() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"after","ProcessSequence",(getAfter()==null?"null":getAfter().toString()),"",WarningType.NOTNULL);
        }
        if (getBefore() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"before","ProcessSequence",(getBefore()==null?"null":getBefore().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class ProcessSequence
    }

/**
 *  This method states if the class depends on the solver.
 *
*/

    public static Boolean isSolverDependent(){
        return false;
    }

   public List<ApplicationObjectInterface> getFeasibleValues(ApplicationDatasetInterface base,String attrName){
      if (attrName.equals("after")){
         return (List) ((Scenario)base).getListProcessStep();
      }
      if (attrName.equals("before")){
         return (List) ((Scenario)base).getListProcessStep();
      }
      return null;
   }

}

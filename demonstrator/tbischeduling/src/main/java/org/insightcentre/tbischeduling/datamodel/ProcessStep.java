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

public  class ProcessStep extends ApplicationObject{
/**
 *  
 *
*/

    public Integer durationFixed;

/**
 *  
 *
*/

    public Integer durationPerUnit;

/**
 *  
 *
*/

    public Process process;

/**
 *  
 *
*/

    public Integer stage;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public ProcessStep(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public ProcessStep(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setDurationFixed(0);
        setDurationPerUnit(0);
        setProcess(null);
        setStage(0);
        applicationDataset.addProcessStep(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public ProcessStep(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Integer durationFixed,
            Integer durationPerUnit,
            Process process,
            Integer stage){
        super(applicationDataset,
            id,
            name);
        setDurationFixed(durationFixed);
        setDurationPerUnit(durationPerUnit);
        setProcess(process);
        setStage(stage);
        applicationDataset.addProcessStep(this);
    }

    public ProcessStep(ProcessStep other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.durationFixed,
            other.durationPerUnit,
            other.process,
            other.stage);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        getApplicationDataset().cascadeProcessSequenceBefore(this);
        getApplicationDataset().cascadeProcessSequenceAfter(this);
        getApplicationDataset().cascadeResourceNeedProcessStep(this);
        getApplicationDataset().cascadeCumulativeNeedProcessStep(this);
        getApplicationDataset().cascadeTaskProcessStep(this);
        return getApplicationDataset().removeProcessStep(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute durationFixed
 *
 * @return Integer
*/

    public Integer getDurationFixed(){
        return this.durationFixed;
    }

/**
 *  get attribute durationPerUnit
 *
 * @return Integer
*/

    public Integer getDurationPerUnit(){
        return this.durationPerUnit;
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
 *  get attribute stage
 *
 * @return Integer
*/

    public Integer getStage(){
        return this.stage;
    }

/**
 *  set attribute durationFixed, mark dataset as dirty, mark dataset as not valid
@param durationFixed Integer
 *
*/

    public void setDurationFixed(Integer durationFixed){
        this.durationFixed = durationFixed;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute durationPerUnit, mark dataset as dirty, mark dataset as not valid
@param durationPerUnit Integer
 *
*/

    public void setDurationPerUnit(Integer durationPerUnit){
        this.durationPerUnit = durationPerUnit;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
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
 *  set attribute stage, mark dataset as dirty, mark dataset as not valid
@param stage Integer
 *
*/

    public void setStage(Integer stage){
        this.stage = stage;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute durationFixed, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incDurationFixed(){
        this.durationFixed++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute durationPerUnit, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incDurationPerUnit(){
        this.durationPerUnit++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute stage, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incStage(){
        this.stage++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getDurationFixed()+ " " +getDurationPerUnit()+ " " +getProcess().toColumnString()+ " " +getStage();
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
         out.println("<processStep "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " durationFixed=\""+toXMLDurationFixed()+"\""+
            " durationPerUnit=\""+toXMLDurationPerUnit()+"\""+
            " process=\""+toXMLProcess()+"\""+
            " stage=\""+toXMLStage()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDurationFixed(){
        return this.getDurationFixed().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDurationPerUnit(){
        return this.getDurationPerUnit().toString();
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
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLStage(){
        return this.getStage().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>ProcessStep</th>"+"<th>Name</th>"+"<th>Process</th>"+"<th>Stage</th>"+"<th>DurationFixed</th>"+"<th>DurationPerUnit</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getProcess().toColumnString()+"</td>"+ " " +"<td>"+getStage()+"</td>"+ " " +"<td>"+getDurationFixed()+"</td>"+ " " +"<td>"+getDurationPerUnit()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a ProcessStep item we are looking for
 * @param bList List<ProcessStep> list of items in which we are searching
 * @return ProcessStep entry of list b which is applicationSame() to a
*/

    public static ProcessStep find(ProcessStep a, List<ProcessStep> bList){
        for(ProcessStep b : bList){
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
 * @param name ProcessStep name of the object we are looking for
 * @return ProcessStep entry of the dataset with the given name; otherwise null
*/

    public static ProcessStep findByName(ApplicationDataset base, String name){
        for(ProcessStep a:base.getListProcessStep()) {
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
 * @param name ProcessStep name of the object we are looking for
 * @return ProcessStep entry of the dataset with the given name
*/

    public static ProcessStep findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(ProcessStep a:base.getListProcessStep()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        ProcessStep res = new ProcessStep(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ProcessStep first entry in the dataset of this type; null if that does not exists
*/

    public static ProcessStep findFirst(ApplicationDataset base){
        if (base.getListProcessStep().isEmpty()) {
            return null;
        }
        return base.getListProcessStep().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ProcessStep some entry in the dataset of this type; null if that does not exists
*/

    public static ProcessStep findAny(ApplicationDataset base){
        int size=base.getListProcessStep().size();
        if (size > 0) {
             return base.getListProcessStep().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return ProcessStep last entry in the dataset of this type; null if that does not exists
*/

    public static ProcessStep findLast(ApplicationDataset base){
        int size=base.getListProcessStep().size();
        if (size > 0) {
             return base.getListProcessStep().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b ProcessStep compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(ProcessStep b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b ProcessStep compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(ProcessStep b){
      if(!this.getDurationFixed().equals(b.getDurationFixed())){
         System.out.println("DurationFixed");
        }
      if(!this.getDurationPerUnit().equals(b.getDurationPerUnit())){
         System.out.println("DurationPerUnit");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getProcess().applicationSame(b.getProcess())){
         System.out.println("Process");
        }
      if(!this.getStage().equals(b.getStage())){
         System.out.println("Stage");
        }
        return  this.getDurationFixed().equals(b.getDurationFixed()) &&
          this.getDurationPerUnit().equals(b.getDurationPerUnit()) &&
          this.getName().equals(b.getName()) &&
          this.getProcess().applicationSame(b.getProcess()) &&
          this.getStage().equals(b.getStage());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","ProcessStep",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getProcess() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"process","ProcessStep",(getProcess()==null?"null":getProcess().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class ProcessStep
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

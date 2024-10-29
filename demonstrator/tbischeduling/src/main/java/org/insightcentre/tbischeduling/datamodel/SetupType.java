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

public  class SetupType extends ApplicationObject{
/**
 *  
 *
*/

    public Integer nr;

/**
 *  
 *
*/

    public List<ProcessStep> processStep;

/**
 *  
 *
*/

    public Setup setup;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public SetupType(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public SetupType(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setNr(0);
        setProcessStep(new ArrayList<ProcessStep>());
        setSetup(null);
        applicationDataset.addSetupType(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public SetupType(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Integer nr,
            List<ProcessStep> processStep,
            Setup setup){
        super(applicationDataset,
            id,
            name);
        setNr(nr);
        setProcessStep(processStep);
        setSetup(setup);
        applicationDataset.addSetupType(this);
    }

    public SetupType(SetupType other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.nr,
            other.processStep,
            other.setup);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        getApplicationDataset().cascadeProcessStepSetupType(this);
        getApplicationDataset().cascadeSetupMatrixFrom(this);
        getApplicationDataset().cascadeSetupMatrixTo(this);
        return getApplicationDataset().removeSetupType(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute nr
 *
 * @return Integer
*/

    public Integer getNr(){
        return this.nr;
    }

/**
 *  get attribute processStep
 *
 * @return List<ProcessStep>
*/

    public List<ProcessStep> getProcessStep(){
        return this.processStep;
    }

/**
 *  get attribute setup
 *
 * @return Setup
*/

    public Setup getSetup(){
        return this.setup;
    }

/**
 *  set attribute nr, mark dataset as dirty, mark dataset as not valid
@param nr Integer
 *
*/

    public void setNr(Integer nr){
        this.nr = nr;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute processStep, mark dataset as dirty, mark dataset as not valid
@param processStep List<ProcessStep>
 *
*/

    public void setProcessStep(List<ProcessStep> processStep){
        this.processStep = processStep;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute setup, mark dataset as dirty, mark dataset as not valid
@param setup Setup
 *
*/

    public void setSetup(Setup setup){
        this.setup = setup;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nr, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNr(){
        this.nr++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getNr()+ " " +getProcessStep()+ " " +getSetup().toColumnString();
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
         out.println("<setupType "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " nr=\""+toXMLNr()+"\""+
            " processStep=\""+toXMLProcessStep()+"\""+
            " setup=\""+toXMLSetup()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNr(){
        return this.getNr().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLProcessStep(){
        String str="";
        for(ProcessStep x:getProcessStep()){
            str=str+" "+"ID_"+x.getId();
        }
        return str;
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLSetup(){
        return "ID_"+this.getSetup().getId().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>SetupType</th>"+"<th>Name</th>"+"<th>Setup</th>"+"<th>Nr</th>"+"<th>ProcessStep</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getSetup().toColumnString()+"</td>"+ " " +"<td>"+getNr()+"</td>"+ " " +"<td>"+getProcessStep()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a SetupType item we are looking for
 * @param bList List<SetupType> list of items in which we are searching
 * @return SetupType entry of list b which is applicationSame() to a
*/

    public static SetupType find(SetupType a, List<SetupType> bList){
        for(SetupType b : bList){
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
 * @param name SetupType name of the object we are looking for
 * @return SetupType entry of the dataset with the given name; otherwise null
*/

    public static SetupType findByName(ApplicationDataset base, String name){
        for(SetupType a:base.getListSetupType()) {
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
 * @param name SetupType name of the object we are looking for
 * @return SetupType entry of the dataset with the given name
*/

    public static SetupType findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(SetupType a:base.getListSetupType()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        SetupType res = new SetupType(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return SetupType first entry in the dataset of this type; null if that does not exists
*/

    public static SetupType findFirst(ApplicationDataset base){
        if (base.getListSetupType().isEmpty()) {
            return null;
        }
        return base.getListSetupType().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return SetupType some entry in the dataset of this type; null if that does not exists
*/

    public static SetupType findAny(ApplicationDataset base){
        int size=base.getListSetupType().size();
        if (size > 0) {
             return base.getListSetupType().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return SetupType last entry in the dataset of this type; null if that does not exists
*/

    public static SetupType findLast(ApplicationDataset base){
        int size=base.getListSetupType().size();
        if (size > 0) {
             return base.getListSetupType().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b SetupType compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(SetupType b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b SetupType compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(SetupType b){
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getNr().equals(b.getNr())){
         System.out.println("Nr");
        }
      if (true) {         System.out.println("ProcessStep");
        }
      if(!this.getSetup().applicationSame(b.getSetup())){
         System.out.println("Setup");
        }
        return  this.getName().equals(b.getName()) &&
          this.getNr().equals(b.getNr()) &&
          true &&
          this.getSetup().applicationSame(b.getSetup());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","SetupType",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getProcessStep() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"processStep","SetupType",(getProcessStep()==null?"null":getProcessStep().toString()),"",WarningType.NOTNULL);
        }
        if (getSetup() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"setup","SetupType",(getSetup()==null?"null":getSetup().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class SetupType
    }

/**
 *  This method states if the class depends on the solver.
 *
*/

    public static Boolean isSolverDependent(){
        return false;
    }

   public List<ApplicationObjectInterface> getFeasibleValues(ApplicationDatasetInterface base,String attrName){
      if (attrName.equals("processStep")){
         return (List) ((Scenario)base).getListProcessStep();
      }
      if (attrName.equals("setup")){
         return (List) ((Scenario)base).getListSetup();
      }
      return null;
   }

}

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
import org.insightcentre.tbischeduling.datamodel.LowerBound;
import org.insightcentre.tbischeduling.datamodel.JobLowerBound;
import org.insightcentre.tbischeduling.datamodel.DisjunctiveLowerBound;
import org.insightcentre.tbischeduling.datamodel.CumulativeLowerBound;
import org.insightcentre.tbischeduling.datamodel.MachineGroupLowerBound;
import org.insightcentre.tbischeduling.datamodel.PlacedRectangle;
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

public  class IntermediateSolution extends ApplicationObject{
/**
 *  
 *
*/

    public Double bound;

/**
 *  
 *
*/

    public Double cost;

/**
 *  
 *
*/

    public Double gapPercent;

/**
 *  
 *
*/

    public Integer nr;

/**
 *  
 *
*/

    public SolverRun solverRun;

/**
 *  
 *
*/

    public Double time;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public IntermediateSolution(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public IntermediateSolution(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setBound(0.0);
        setCost(0.0);
        setGapPercent(0.0);
        setNr(0);
        setSolverRun(null);
        setTime(0.0);
        applicationDataset.addIntermediateSolution(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public IntermediateSolution(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Double bound,
            Double cost,
            Double gapPercent,
            Integer nr,
            SolverRun solverRun,
            Double time){
        super(applicationDataset,
            id,
            name);
        setBound(bound);
        setCost(cost);
        setGapPercent(gapPercent);
        setNr(nr);
        setSolverRun(solverRun);
        setTime(time);
        applicationDataset.addIntermediateSolution(this);
    }

    public IntermediateSolution(IntermediateSolution other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.bound,
            other.cost,
            other.gapPercent,
            other.nr,
            other.solverRun,
            other.time);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeIntermediateSolution(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute bound
 *
 * @return Double
*/

    public Double getBound(){
        return this.bound;
    }

/**
 *  get attribute cost
 *
 * @return Double
*/

    public Double getCost(){
        return this.cost;
    }

/**
 *  get attribute gapPercent
 *
 * @return Double
*/

    public Double getGapPercent(){
        return this.gapPercent;
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
 *  get attribute solverRun
 *
 * @return SolverRun
*/

    public SolverRun getSolverRun(){
        return this.solverRun;
    }

/**
 *  get attribute time
 *
 * @return Double
*/

    public Double getTime(){
        return this.time;
    }

/**
 *  set attribute bound, mark dataset as dirty, mark dataset as not valid
@param bound Double
 *
*/

    public void setBound(Double bound){
        this.bound = bound;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute cost, mark dataset as dirty, mark dataset as not valid
@param cost Double
 *
*/

    public void setCost(Double cost){
        this.cost = cost;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute gapPercent, mark dataset as dirty, mark dataset as not valid
@param gapPercent Double
 *
*/

    public void setGapPercent(Double gapPercent){
        this.gapPercent = gapPercent;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
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
 *  set attribute solverRun, mark dataset as dirty, mark dataset as not valid
@param solverRun SolverRun
 *
*/

    public void setSolverRun(SolverRun solverRun){
        this.solverRun = solverRun;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute time, mark dataset as dirty, mark dataset as not valid
@param time Double
 *
*/

    public void setTime(Double time){
        this.time = time;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getBound()+ " " +getCost()+ " " +getGapPercent()+ " " +getNr()+ " " +getSolverRun().toColumnString()+ " " +getTime();
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
         out.println("<intermediateSolution "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " bound=\""+toXMLBound()+"\""+
            " cost=\""+toXMLCost()+"\""+
            " gapPercent=\""+toXMLGapPercent()+"\""+
            " nr=\""+toXMLNr()+"\""+
            " solverRun=\""+toXMLSolverRun()+"\""+
            " time=\""+toXMLTime()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLBound(){
        return this.getBound().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLCost(){
        return this.getCost().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLGapPercent(){
        return this.getGapPercent().toString();
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

    String toXMLSolverRun(){
        return "ID_"+this.getSolverRun().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLTime(){
        return this.getTime().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>IntermediateSolution</th>"+"<th>Name</th>"+"<th>SolverRun</th>"+"<th>Nr</th>"+"<th>Cost</th>"+"<th>Bound</th>"+"<th>Time</th>"+"<th>GapPercent</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getSolverRun().toColumnString()+"</td>"+ " " +"<td>"+getNr()+"</td>"+ " " +"<td>"+getCost()+"</td>"+ " " +"<td>"+getBound()+"</td>"+ " " +"<td>"+getTime()+"</td>"+ " " +"<td>"+getGapPercent()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a IntermediateSolution item we are looking for
 * @param bList List<IntermediateSolution> list of items in which we are searching
 * @return IntermediateSolution entry of list b which is applicationSame() to a
*/

    public static IntermediateSolution find(IntermediateSolution a, List<IntermediateSolution> bList){
        for(IntermediateSolution b : bList){
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
 * @param name IntermediateSolution name of the object we are looking for
 * @return IntermediateSolution entry of the dataset with the given name; otherwise null
*/

    public static IntermediateSolution findByName(ApplicationDataset base, String name){
        for(IntermediateSolution a:base.getListIntermediateSolution()) {
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
 * @param name IntermediateSolution name of the object we are looking for
 * @return IntermediateSolution entry of the dataset with the given name
*/

    public static IntermediateSolution findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(IntermediateSolution a:base.getListIntermediateSolution()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        IntermediateSolution res = new IntermediateSolution(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return IntermediateSolution first entry in the dataset of this type; null if that does not exists
*/

    public static IntermediateSolution findFirst(ApplicationDataset base){
        if (base.getListIntermediateSolution().isEmpty()) {
            return null;
        }
        return base.getListIntermediateSolution().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return IntermediateSolution some entry in the dataset of this type; null if that does not exists
*/

    public static IntermediateSolution findAny(ApplicationDataset base){
        int size=base.getListIntermediateSolution().size();
        if (size > 0) {
             return base.getListIntermediateSolution().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return IntermediateSolution last entry in the dataset of this type; null if that does not exists
*/

    public static IntermediateSolution findLast(ApplicationDataset base){
        int size=base.getListIntermediateSolution().size();
        if (size > 0) {
             return base.getListIntermediateSolution().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b IntermediateSolution compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(IntermediateSolution b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b IntermediateSolution compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(IntermediateSolution b){
      if(!this.getBound().equals(b.getBound())){
         System.out.println("Bound");
        }
      if(!this.getCost().equals(b.getCost())){
         System.out.println("Cost");
        }
      if(!this.getGapPercent().equals(b.getGapPercent())){
         System.out.println("GapPercent");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getNr().equals(b.getNr())){
         System.out.println("Nr");
        }
      if(!this.getSolverRun().applicationSame(b.getSolverRun())){
         System.out.println("SolverRun");
        }
      if(!this.getTime().equals(b.getTime())){
         System.out.println("Time");
        }
        return  this.getBound().equals(b.getBound()) &&
          this.getCost().equals(b.getCost()) &&
          this.getGapPercent().equals(b.getGapPercent()) &&
          this.getName().equals(b.getName()) &&
          this.getNr().equals(b.getNr()) &&
          this.getSolverRun().applicationSame(b.getSolverRun()) &&
          this.getTime().equals(b.getTime());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","IntermediateSolution",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getSolverRun() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"solverRun","IntermediateSolution",(getSolverRun()==null?"null":getSolverRun().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class IntermediateSolution
    }

/**
 *  This method states if the class depends on the solver.
 *
*/

    public static Boolean isSolverDependent(){
        return false;
    }

   public List<ApplicationObjectInterface> getFeasibleValues(ApplicationDatasetInterface base,String attrName){
      if (attrName.equals("solverRun")){
         return (List) ((Scenario)base).getListSolverRun();
      }
      return null;
   }

}

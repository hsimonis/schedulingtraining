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

public abstract class AbstractGanttProperty extends ApplicationObject{
/**
 *  
 *
*/

    public DatesDisplay datesDisplay;

/**
 *  
 *
*/

    public LineChoice showEarly;

/**
 *  
 *
*/

    public LineChoice showIdle;

/**
 *  
 *
*/

    public LineChoice showLate;

/**
 *  
 *
*/

    public LineChoice showRelease;

/**
 *  
 *
*/

    public LineChoice showSetup;

/**
 *  
 *
*/

    public LineChoice showWait;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public AbstractGanttProperty(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public AbstractGanttProperty(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setDatesDisplay(DatesDisplay.External);
        setShowEarly(LineChoice.None);
        setShowIdle(LineChoice.None);
        setShowLate(LineChoice.None);
        setShowRelease(LineChoice.None);
        setShowSetup(LineChoice.None);
        setShowWait(LineChoice.None);
        applicationDataset.addAbstractGanttProperty(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public AbstractGanttProperty(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            DatesDisplay datesDisplay,
            LineChoice showEarly,
            LineChoice showIdle,
            LineChoice showLate,
            LineChoice showRelease,
            LineChoice showSetup,
            LineChoice showWait){
        super(applicationDataset,
            id,
            name);
        setDatesDisplay(datesDisplay);
        setShowEarly(showEarly);
        setShowIdle(showIdle);
        setShowLate(showLate);
        setShowRelease(showRelease);
        setShowSetup(showSetup);
        setShowWait(showWait);
        applicationDataset.addAbstractGanttProperty(this);
    }

    public AbstractGanttProperty(AbstractGanttProperty other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.datesDisplay,
            other.showEarly,
            other.showIdle,
            other.showLate,
            other.showRelease,
            other.showSetup,
            other.showWait);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeAbstractGanttProperty(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute datesDisplay
 *
 * @return DatesDisplay
*/

    public DatesDisplay getDatesDisplay(){
        return this.datesDisplay;
    }

/**
 *  get attribute showEarly
 *
 * @return LineChoice
*/

    public LineChoice getShowEarly(){
        return this.showEarly;
    }

/**
 *  get attribute showIdle
 *
 * @return LineChoice
*/

    public LineChoice getShowIdle(){
        return this.showIdle;
    }

/**
 *  get attribute showLate
 *
 * @return LineChoice
*/

    public LineChoice getShowLate(){
        return this.showLate;
    }

/**
 *  get attribute showRelease
 *
 * @return LineChoice
*/

    public LineChoice getShowRelease(){
        return this.showRelease;
    }

/**
 *  get attribute showSetup
 *
 * @return LineChoice
*/

    public LineChoice getShowSetup(){
        return this.showSetup;
    }

/**
 *  get attribute showWait
 *
 * @return LineChoice
*/

    public LineChoice getShowWait(){
        return this.showWait;
    }

/**
 *  set attribute datesDisplay, mark dataset as dirty, mark dataset as not valid
@param datesDisplay DatesDisplay
 *
*/

    public void setDatesDisplay(DatesDisplay datesDisplay){
        this.datesDisplay = datesDisplay;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute showEarly, mark dataset as dirty, mark dataset as not valid
@param showEarly LineChoice
 *
*/

    public void setShowEarly(LineChoice showEarly){
        this.showEarly = showEarly;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute showIdle, mark dataset as dirty, mark dataset as not valid
@param showIdle LineChoice
 *
*/

    public void setShowIdle(LineChoice showIdle){
        this.showIdle = showIdle;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute showLate, mark dataset as dirty, mark dataset as not valid
@param showLate LineChoice
 *
*/

    public void setShowLate(LineChoice showLate){
        this.showLate = showLate;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute showRelease, mark dataset as dirty, mark dataset as not valid
@param showRelease LineChoice
 *
*/

    public void setShowRelease(LineChoice showRelease){
        this.showRelease = showRelease;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute showSetup, mark dataset as dirty, mark dataset as not valid
@param showSetup LineChoice
 *
*/

    public void setShowSetup(LineChoice showSetup){
        this.showSetup = showSetup;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute showWait, mark dataset as dirty, mark dataset as not valid
@param showWait LineChoice
 *
*/

    public void setShowWait(LineChoice showWait){
        this.showWait = showWait;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getDatesDisplay()+ " " +getShowEarly()+ " " +getShowIdle()+ " " +getShowLate()+ " " +getShowRelease()+ " " +getShowSetup()+ " " +getShowWait();
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
         out.println("<abstractGanttProperty "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " datesDisplay=\""+toXMLDatesDisplay()+"\""+
            " showEarly=\""+toXMLShowEarly()+"\""+
            " showIdle=\""+toXMLShowIdle()+"\""+
            " showLate=\""+toXMLShowLate()+"\""+
            " showRelease=\""+toXMLShowRelease()+"\""+
            " showSetup=\""+toXMLShowSetup()+"\""+
            " showWait=\""+toXMLShowWait()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDatesDisplay(){
        return this.getDatesDisplay().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLShowEarly(){
        return this.getShowEarly().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLShowIdle(){
        return this.getShowIdle().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLShowLate(){
        return this.getShowLate().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLShowRelease(){
        return this.getShowRelease().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLShowSetup(){
        return this.getShowSetup().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLShowWait(){
        return this.getShowWait().toString();
    }

/**
 * find the same object in another dataset
 * @param a AbstractGanttProperty item we are looking for
 * @param bList List<AbstractGanttProperty> list of items in which we are searching
 * @return AbstractGanttProperty entry of list b which is applicationSame() to a
*/

    public static AbstractGanttProperty find(AbstractGanttProperty a, List<AbstractGanttProperty> bList){
        for(AbstractGanttProperty b : bList){
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
 * @param name AbstractGanttProperty name of the object we are looking for
 * @return AbstractGanttProperty entry of the dataset with the given name; otherwise null
*/

    public static AbstractGanttProperty findByName(ApplicationDataset base, String name){
        for(AbstractGanttProperty a:base.getListAbstractGanttProperty()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        return null;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return AbstractGanttProperty first entry in the dataset of this type; null if that does not exists
*/

    public static AbstractGanttProperty findFirst(ApplicationDataset base){
        if (base.getListAbstractGanttProperty().isEmpty()) {
            return null;
        }
        return base.getListAbstractGanttProperty().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return AbstractGanttProperty some entry in the dataset of this type; null if that does not exists
*/

    public static AbstractGanttProperty findAny(ApplicationDataset base){
        int size=base.getListAbstractGanttProperty().size();
        if (size > 0) {
             return base.getListAbstractGanttProperty().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return AbstractGanttProperty last entry in the dataset of this type; null if that does not exists
*/

    public static AbstractGanttProperty findLast(ApplicationDataset base){
        int size=base.getListAbstractGanttProperty().size();
        if (size > 0) {
             return base.getListAbstractGanttProperty().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b AbstractGanttProperty compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(AbstractGanttProperty b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b AbstractGanttProperty compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(AbstractGanttProperty b){
      if(!this.getDatesDisplay().equals(b.getDatesDisplay())){
         System.out.println("DatesDisplay");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getShowEarly().equals(b.getShowEarly())){
         System.out.println("ShowEarly");
        }
      if(!this.getShowIdle().equals(b.getShowIdle())){
         System.out.println("ShowIdle");
        }
      if(!this.getShowLate().equals(b.getShowLate())){
         System.out.println("ShowLate");
        }
      if(!this.getShowRelease().equals(b.getShowRelease())){
         System.out.println("ShowRelease");
        }
      if(!this.getShowSetup().equals(b.getShowSetup())){
         System.out.println("ShowSetup");
        }
      if(!this.getShowWait().equals(b.getShowWait())){
         System.out.println("ShowWait");
        }
        return  this.getDatesDisplay().equals(b.getDatesDisplay()) &&
          this.getName().equals(b.getName()) &&
          this.getShowEarly().equals(b.getShowEarly()) &&
          this.getShowIdle().equals(b.getShowIdle()) &&
          this.getShowLate().equals(b.getShowLate()) &&
          this.getShowRelease().equals(b.getShowRelease()) &&
          this.getShowSetup().equals(b.getShowSetup()) &&
          this.getShowWait().equals(b.getShowWait());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","AbstractGanttProperty",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
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

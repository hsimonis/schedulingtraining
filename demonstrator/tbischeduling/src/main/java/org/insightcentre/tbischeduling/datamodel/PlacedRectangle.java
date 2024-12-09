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

public  class PlacedRectangle extends ApplicationObject{
/**
 *  
 *
*/

    public CumulativeResource cumulativeResource;

/**
 *  
 *
*/

    public Integer h;

/**
 *  
 *
*/

    public TaskAssignment taskAssignment;

/**
 *  
 *
*/

    public Integer w;

/**
 *  
 *
*/

    public Integer x;

/**
 *  
 *
*/

    public Integer y;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public PlacedRectangle(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public PlacedRectangle(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setCumulativeResource(null);
        setH(0);
        setTaskAssignment(null);
        setW(0);
        setX(0);
        setY(0);
        applicationDataset.addPlacedRectangle(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public PlacedRectangle(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            CumulativeResource cumulativeResource,
            Integer h,
            TaskAssignment taskAssignment,
            Integer w,
            Integer x,
            Integer y){
        super(applicationDataset,
            id,
            name);
        setCumulativeResource(cumulativeResource);
        setH(h);
        setTaskAssignment(taskAssignment);
        setW(w);
        setX(x);
        setY(y);
        applicationDataset.addPlacedRectangle(this);
    }

    public PlacedRectangle(PlacedRectangle other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.cumulativeResource,
            other.h,
            other.taskAssignment,
            other.w,
            other.x,
            other.y);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removePlacedRectangle(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute cumulativeResource
 *
 * @return CumulativeResource
*/

    public CumulativeResource getCumulativeResource(){
        return this.cumulativeResource;
    }

/**
 *  get attribute h
 *
 * @return Integer
*/

    public Integer getH(){
        return this.h;
    }

/**
 *  get attribute taskAssignment
 *
 * @return TaskAssignment
*/

    public TaskAssignment getTaskAssignment(){
        return this.taskAssignment;
    }

/**
 *  get attribute w
 *
 * @return Integer
*/

    public Integer getW(){
        return this.w;
    }

/**
 *  get attribute x
 *
 * @return Integer
*/

    public Integer getX(){
        return this.x;
    }

/**
 *  get attribute y
 *
 * @return Integer
*/

    public Integer getY(){
        return this.y;
    }

/**
 *  set attribute cumulativeResource, mark dataset as dirty, mark dataset as not valid
@param cumulativeResource CumulativeResource
 *
*/

    public void setCumulativeResource(CumulativeResource cumulativeResource){
        this.cumulativeResource = cumulativeResource;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute h, mark dataset as dirty, mark dataset as not valid
@param h Integer
 *
*/

    public void setH(Integer h){
        this.h = h;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute taskAssignment, mark dataset as dirty, mark dataset as not valid
@param taskAssignment TaskAssignment
 *
*/

    public void setTaskAssignment(TaskAssignment taskAssignment){
        this.taskAssignment = taskAssignment;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute w, mark dataset as dirty, mark dataset as not valid
@param w Integer
 *
*/

    public void setW(Integer w){
        this.w = w;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute x, mark dataset as dirty, mark dataset as not valid
@param x Integer
 *
*/

    public void setX(Integer x){
        this.x = x;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute y, mark dataset as dirty, mark dataset as not valid
@param y Integer
 *
*/

    public void setY(Integer y){
        this.y = y;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute h, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incH(){
        this.h++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute w, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incW(){
        this.w++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute x, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incX(){
        this.x++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute y, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incY(){
        this.y++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getCumulativeResource().toColumnString()+ " " +getH()+ " " +getTaskAssignment().toColumnString()+ " " +getW()+ " " +getX()+ " " +getY();
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
         out.println("<placedRectangle "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " cumulativeResource=\""+toXMLCumulativeResource()+"\""+
            " h=\""+toXMLH()+"\""+
            " taskAssignment=\""+toXMLTaskAssignment()+"\""+
            " w=\""+toXMLW()+"\""+
            " x=\""+toXMLX()+"\""+
            " y=\""+toXMLY()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLCumulativeResource(){
        return "ID_"+this.getCumulativeResource().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLH(){
        return this.getH().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLTaskAssignment(){
        return "ID_"+this.getTaskAssignment().getId().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLW(){
        return this.getW().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLX(){
        return this.getX().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLY(){
        return this.getY().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>PlacedRectangle</th>"+"<th>Name</th>"+"<th>CumulativeResource</th>"+"<th>TaskAssignment</th>"+"<th>X</th>"+"<th>Y</th>"+"<th>W</th>"+"<th>H</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getCumulativeResource().toColumnString()+"</td>"+ " " +"<td>"+getTaskAssignment().toColumnString()+"</td>"+ " " +"<td>"+getX()+"</td>"+ " " +"<td>"+getY()+"</td>"+ " " +"<td>"+getW()+"</td>"+ " " +"<td>"+getH()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a PlacedRectangle item we are looking for
 * @param bList List<PlacedRectangle> list of items in which we are searching
 * @return PlacedRectangle entry of list b which is applicationSame() to a
*/

    public static PlacedRectangle find(PlacedRectangle a, List<PlacedRectangle> bList){
        for(PlacedRectangle b : bList){
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
 * @param name PlacedRectangle name of the object we are looking for
 * @return PlacedRectangle entry of the dataset with the given name; otherwise null
*/

    public static PlacedRectangle findByName(ApplicationDataset base, String name){
        for(PlacedRectangle a:base.getListPlacedRectangle()) {
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
 * @param name PlacedRectangle name of the object we are looking for
 * @return PlacedRectangle entry of the dataset with the given name
*/

    public static PlacedRectangle findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(PlacedRectangle a:base.getListPlacedRectangle()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        PlacedRectangle res = new PlacedRectangle(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return PlacedRectangle first entry in the dataset of this type; null if that does not exists
*/

    public static PlacedRectangle findFirst(ApplicationDataset base){
        if (base.getListPlacedRectangle().isEmpty()) {
            return null;
        }
        return base.getListPlacedRectangle().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return PlacedRectangle some entry in the dataset of this type; null if that does not exists
*/

    public static PlacedRectangle findAny(ApplicationDataset base){
        int size=base.getListPlacedRectangle().size();
        if (size > 0) {
             return base.getListPlacedRectangle().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return PlacedRectangle last entry in the dataset of this type; null if that does not exists
*/

    public static PlacedRectangle findLast(ApplicationDataset base){
        int size=base.getListPlacedRectangle().size();
        if (size > 0) {
             return base.getListPlacedRectangle().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b PlacedRectangle compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(PlacedRectangle b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b PlacedRectangle compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(PlacedRectangle b){
      if(!this.getCumulativeResource().applicationSame(b.getCumulativeResource())){
         System.out.println("CumulativeResource");
        }
      if(!this.getH().equals(b.getH())){
         System.out.println("H");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getTaskAssignment().applicationSame(b.getTaskAssignment())){
         System.out.println("TaskAssignment");
        }
      if(!this.getW().equals(b.getW())){
         System.out.println("W");
        }
      if(!this.getX().equals(b.getX())){
         System.out.println("X");
        }
      if(!this.getY().equals(b.getY())){
         System.out.println("Y");
        }
        return  this.getCumulativeResource().applicationSame(b.getCumulativeResource()) &&
          this.getH().equals(b.getH()) &&
          this.getName().equals(b.getName()) &&
          this.getTaskAssignment().applicationSame(b.getTaskAssignment()) &&
          this.getW().equals(b.getW()) &&
          this.getX().equals(b.getX()) &&
          this.getY().equals(b.getY());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","PlacedRectangle",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getCumulativeResource() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"cumulativeResource","PlacedRectangle",(getCumulativeResource()==null?"null":getCumulativeResource().toString()),"",WarningType.NOTNULL);
        }
        if (getTaskAssignment() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"taskAssignment","PlacedRectangle",(getTaskAssignment()==null?"null":getTaskAssignment().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class PlacedRectangle
    }

/**
 *  This method states if the class depends on the solver.
 *
*/

    public static Boolean isSolverDependent(){
        return false;
    }

   public List<ApplicationObjectInterface> getFeasibleValues(ApplicationDatasetInterface base,String attrName){
      if (attrName.equals("cumulativeResource")){
         return (List) ((Scenario)base).getListCumulativeResource();
      }
      if (attrName.equals("taskAssignment")){
         return (List) ((Scenario)base).getListTaskAssignment();
      }
      return null;
   }

}

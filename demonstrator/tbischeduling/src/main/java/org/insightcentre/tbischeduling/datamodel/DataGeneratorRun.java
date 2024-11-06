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

public  class DataGeneratorRun extends AbstractDataGeneratorProperty{
/**
 *  No-arg constructor for use in TableView
 *
*/

    public DataGeneratorRun(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public DataGeneratorRun(ApplicationDataset applicationDataset){
        super(applicationDataset);
        applicationDataset.addDataGeneratorRun(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public DataGeneratorRun(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Double downtimeProbability,
            Integer durationFixedFactor,
            DurationModel durationModel,
            Integer earliestDue,
            Integer horizonDays,
            String label,
            Integer maxCumulCapacity,
            Integer maxCumulDemand,
            Integer maxDowntime,
            Integer maxDuration,
            Integer maxQty,
            Integer maxStages,
            Integer maxWip,
            Integer minCumulCapacity,
            Integer minCumulDemand,
            Integer minDowntime,
            Integer minDuration,
            Integer minQty,
            Integer minStages,
            Integer minWip,
            Integer nrCumulativeResources,
            Integer nrDisjunctiveResources,
            Integer nrOrders,
            Integer nrProducts,
            Integer profilePieces,
            ResourceModel resourceModel,
            Double resourceProbability,
            Integer seed,
            DateTime startDateTime,
            Integer timeResolution,
            Double wipProbability){
        super(applicationDataset,
            id,
            name,
            downtimeProbability,
            durationFixedFactor,
            durationModel,
            earliestDue,
            horizonDays,
            label,
            maxCumulCapacity,
            maxCumulDemand,
            maxDowntime,
            maxDuration,
            maxQty,
            maxStages,
            maxWip,
            minCumulCapacity,
            minCumulDemand,
            minDowntime,
            minDuration,
            minQty,
            minStages,
            minWip,
            nrCumulativeResources,
            nrDisjunctiveResources,
            nrOrders,
            nrProducts,
            profilePieces,
            resourceModel,
            resourceProbability,
            seed,
            startDateTime,
            timeResolution,
            wipProbability);
        applicationDataset.addDataGeneratorRun(this);
    }

    public DataGeneratorRun(DataGeneratorRun other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.downtimeProbability,
            other.durationFixedFactor,
            other.durationModel,
            other.earliestDue,
            other.horizonDays,
            other.label,
            other.maxCumulCapacity,
            other.maxCumulDemand,
            other.maxDowntime,
            other.maxDuration,
            other.maxQty,
            other.maxStages,
            other.maxWip,
            other.minCumulCapacity,
            other.minCumulDemand,
            other.minDowntime,
            other.minDuration,
            other.minQty,
            other.minStages,
            other.minWip,
            other.nrCumulativeResources,
            other.nrDisjunctiveResources,
            other.nrOrders,
            other.nrProducts,
            other.profilePieces,
            other.resourceModel,
            other.resourceProbability,
            other.seed,
            other.startDateTime,
            other.timeResolution,
            other.wipProbability);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeDataGeneratorRun(this) && getApplicationDataset().removeAbstractDataGeneratorProperty(this) && getApplicationDataset().removeApplicationObject(this);
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
        return ""+ " " +getId()+ " " +getName()+ " " +getDowntimeProbability()+ " " +getDurationFixedFactor()+ " " +getDurationModel()+ " " +getEarliestDue()+ " " +getHorizonDays()+ " " +getLabel()+ " " +getMaxCumulCapacity()+ " " +getMaxCumulDemand()+ " " +getMaxDowntime()+ " " +getMaxDuration()+ " " +getMaxQty()+ " " +getMaxStages()+ " " +getMaxWip()+ " " +getMinCumulCapacity()+ " " +getMinCumulDemand()+ " " +getMinDowntime()+ " " +getMinDuration()+ " " +getMinQty()+ " " +getMinStages()+ " " +getMinWip()+ " " +getNrCumulativeResources()+ " " +getNrDisjunctiveResources()+ " " +getNrOrders()+ " " +getNrProducts()+ " " +getProfilePieces()+ " " +getResourceModel()+ " " +getResourceProbability()+ " " +getSeed()+ " " +getStartDateTime()+ " " +getTimeResolution()+ " " +getWipProbability();
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
         out.println("<dataGeneratorRun "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " downtimeProbability=\""+toXMLDowntimeProbability()+"\""+
            " durationFixedFactor=\""+toXMLDurationFixedFactor()+"\""+
            " durationModel=\""+toXMLDurationModel()+"\""+
            " earliestDue=\""+toXMLEarliestDue()+"\""+
            " horizonDays=\""+toXMLHorizonDays()+"\""+
            " label=\""+toXMLLabel()+"\""+
            " maxCumulCapacity=\""+toXMLMaxCumulCapacity()+"\""+
            " maxCumulDemand=\""+toXMLMaxCumulDemand()+"\""+
            " maxDowntime=\""+toXMLMaxDowntime()+"\""+
            " maxDuration=\""+toXMLMaxDuration()+"\""+
            " maxQty=\""+toXMLMaxQty()+"\""+
            " maxStages=\""+toXMLMaxStages()+"\""+
            " maxWip=\""+toXMLMaxWip()+"\""+
            " minCumulCapacity=\""+toXMLMinCumulCapacity()+"\""+
            " minCumulDemand=\""+toXMLMinCumulDemand()+"\""+
            " minDowntime=\""+toXMLMinDowntime()+"\""+
            " minDuration=\""+toXMLMinDuration()+"\""+
            " minQty=\""+toXMLMinQty()+"\""+
            " minStages=\""+toXMLMinStages()+"\""+
            " minWip=\""+toXMLMinWip()+"\""+
            " nrCumulativeResources=\""+toXMLNrCumulativeResources()+"\""+
            " nrDisjunctiveResources=\""+toXMLNrDisjunctiveResources()+"\""+
            " nrOrders=\""+toXMLNrOrders()+"\""+
            " nrProducts=\""+toXMLNrProducts()+"\""+
            " profilePieces=\""+toXMLProfilePieces()+"\""+
            " resourceModel=\""+toXMLResourceModel()+"\""+
            " resourceProbability=\""+toXMLResourceProbability()+"\""+
            " seed=\""+toXMLSeed()+"\""+
            " startDateTime=\""+toXMLStartDateTime()+"\""+
            " timeResolution=\""+toXMLTimeResolution()+"\""+
            " wipProbability=\""+toXMLWipProbability()+"\""+" />");
     }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>DataGeneratorRun</th>"+"<th>Name</th>"+"<th>Label</th>"+"<th>StartDateTime</th>"+"<th>ResourceModel</th>"+"<th>NrProducts</th>"+"<th>MinStages</th>"+"<th>MaxStages</th>"+"<th>NrDisjunctiveResources</th>"+"<th>ResourceProbability</th>"+"<th>DurationModel</th>"+"<th>MinDuration</th>"+"<th>MaxDuration</th>"+"<th>DurationFixedFactor</th>"+"<th>NrCumulativeResources</th>"+"<th>MinCumulDemand</th>"+"<th>MaxCumulDemand</th>"+"<th>ProfilePieces</th>"+"<th>MinCumulCapacity</th>"+"<th>MaxCumulCapacity</th>"+"<th>NrOrders</th>"+"<th>MinQty</th>"+"<th>MaxQty</th>"+"<th>WipProbability</th>"+"<th>MinWip</th>"+"<th>MaxWip</th>"+"<th>DowntimeProbability</th>"+"<th>MinDowntime</th>"+"<th>MaxDowntime</th>"+"<th>EarliestDue</th>"+"<th>HorizonDays</th>"+"<th>TimeResolution</th>"+"<th>Seed</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getLabel()+"</td>"+ " " +"<td>"+getStartDateTime()+"</td>"+ " " +"<td>"+getResourceModel()+"</td>"+ " " +"<td>"+getNrProducts()+"</td>"+ " " +"<td>"+getMinStages()+"</td>"+ " " +"<td>"+getMaxStages()+"</td>"+ " " +"<td>"+getNrDisjunctiveResources()+"</td>"+ " " +"<td>"+getResourceProbability()+"</td>"+ " " +"<td>"+getDurationModel()+"</td>"+ " " +"<td>"+getMinDuration()+"</td>"+ " " +"<td>"+getMaxDuration()+"</td>"+ " " +"<td>"+getDurationFixedFactor()+"</td>"+ " " +"<td>"+getNrCumulativeResources()+"</td>"+ " " +"<td>"+getMinCumulDemand()+"</td>"+ " " +"<td>"+getMaxCumulDemand()+"</td>"+ " " +"<td>"+getProfilePieces()+"</td>"+ " " +"<td>"+getMinCumulCapacity()+"</td>"+ " " +"<td>"+getMaxCumulCapacity()+"</td>"+ " " +"<td>"+getNrOrders()+"</td>"+ " " +"<td>"+getMinQty()+"</td>"+ " " +"<td>"+getMaxQty()+"</td>"+ " " +"<td>"+getWipProbability()+"</td>"+ " " +"<td>"+getMinWip()+"</td>"+ " " +"<td>"+getMaxWip()+"</td>"+ " " +"<td>"+getDowntimeProbability()+"</td>"+ " " +"<td>"+getMinDowntime()+"</td>"+ " " +"<td>"+getMaxDowntime()+"</td>"+ " " +"<td>"+getEarliestDue()+"</td>"+ " " +"<td>"+getHorizonDays()+"</td>"+ " " +"<td>"+getTimeResolution()+"</td>"+ " " +"<td>"+getSeed()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a DataGeneratorRun item we are looking for
 * @param bList List<DataGeneratorRun> list of items in which we are searching
 * @return DataGeneratorRun entry of list b which is applicationSame() to a
*/

    public static DataGeneratorRun find(DataGeneratorRun a, List<DataGeneratorRun> bList){
        for(DataGeneratorRun b : bList){
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
 * @param name DataGeneratorRun name of the object we are looking for
 * @return DataGeneratorRun entry of the dataset with the given name; otherwise null
*/

    public static DataGeneratorRun findByName(ApplicationDataset base, String name){
        for(DataGeneratorRun a:base.getListDataGeneratorRun()) {
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
 * @param name DataGeneratorRun name of the object we are looking for
 * @return DataGeneratorRun entry of the dataset with the given name
*/

    public static DataGeneratorRun findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(DataGeneratorRun a:base.getListDataGeneratorRun()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        DataGeneratorRun res = new DataGeneratorRun(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return DataGeneratorRun first entry in the dataset of this type; null if that does not exists
*/

    public static DataGeneratorRun findFirst(ApplicationDataset base){
        if (base.getListDataGeneratorRun().isEmpty()) {
            return null;
        }
        return base.getListDataGeneratorRun().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return DataGeneratorRun some entry in the dataset of this type; null if that does not exists
*/

    public static DataGeneratorRun findAny(ApplicationDataset base){
        int size=base.getListDataGeneratorRun().size();
        if (size > 0) {
             return base.getListDataGeneratorRun().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return DataGeneratorRun last entry in the dataset of this type; null if that does not exists
*/

    public static DataGeneratorRun findLast(ApplicationDataset base){
        int size=base.getListDataGeneratorRun().size();
        if (size > 0) {
             return base.getListDataGeneratorRun().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b DataGeneratorRun compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(DataGeneratorRun b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b DataGeneratorRun compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(DataGeneratorRun b){
      if(!this.getDowntimeProbability().equals(b.getDowntimeProbability())){
         System.out.println("DowntimeProbability");
        }
      if(!this.getDurationFixedFactor().equals(b.getDurationFixedFactor())){
         System.out.println("DurationFixedFactor");
        }
      if(!this.getDurationModel().equals(b.getDurationModel())){
         System.out.println("DurationModel");
        }
      if(!this.getEarliestDue().equals(b.getEarliestDue())){
         System.out.println("EarliestDue");
        }
      if(!this.getHorizonDays().equals(b.getHorizonDays())){
         System.out.println("HorizonDays");
        }
      if(!this.getLabel().equals(b.getLabel())){
         System.out.println("Label");
        }
      if(!this.getMaxCumulCapacity().equals(b.getMaxCumulCapacity())){
         System.out.println("MaxCumulCapacity");
        }
      if(!this.getMaxCumulDemand().equals(b.getMaxCumulDemand())){
         System.out.println("MaxCumulDemand");
        }
      if(!this.getMaxDowntime().equals(b.getMaxDowntime())){
         System.out.println("MaxDowntime");
        }
      if(!this.getMaxDuration().equals(b.getMaxDuration())){
         System.out.println("MaxDuration");
        }
      if(!this.getMaxQty().equals(b.getMaxQty())){
         System.out.println("MaxQty");
        }
      if(!this.getMaxStages().equals(b.getMaxStages())){
         System.out.println("MaxStages");
        }
      if(!this.getMaxWip().equals(b.getMaxWip())){
         System.out.println("MaxWip");
        }
      if(!this.getMinCumulCapacity().equals(b.getMinCumulCapacity())){
         System.out.println("MinCumulCapacity");
        }
      if(!this.getMinCumulDemand().equals(b.getMinCumulDemand())){
         System.out.println("MinCumulDemand");
        }
      if(!this.getMinDowntime().equals(b.getMinDowntime())){
         System.out.println("MinDowntime");
        }
      if(!this.getMinDuration().equals(b.getMinDuration())){
         System.out.println("MinDuration");
        }
      if(!this.getMinQty().equals(b.getMinQty())){
         System.out.println("MinQty");
        }
      if(!this.getMinStages().equals(b.getMinStages())){
         System.out.println("MinStages");
        }
      if(!this.getMinWip().equals(b.getMinWip())){
         System.out.println("MinWip");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getNrCumulativeResources().equals(b.getNrCumulativeResources())){
         System.out.println("NrCumulativeResources");
        }
      if(!this.getNrDisjunctiveResources().equals(b.getNrDisjunctiveResources())){
         System.out.println("NrDisjunctiveResources");
        }
      if(!this.getNrOrders().equals(b.getNrOrders())){
         System.out.println("NrOrders");
        }
      if(!this.getNrProducts().equals(b.getNrProducts())){
         System.out.println("NrProducts");
        }
      if(!this.getProfilePieces().equals(b.getProfilePieces())){
         System.out.println("ProfilePieces");
        }
      if(!this.getResourceModel().equals(b.getResourceModel())){
         System.out.println("ResourceModel");
        }
      if(!this.getResourceProbability().equals(b.getResourceProbability())){
         System.out.println("ResourceProbability");
        }
      if(!this.getSeed().equals(b.getSeed())){
         System.out.println("Seed");
        }
      if(!this.getStartDateTime().applicationEqual(b.getStartDateTime())){
         System.out.println("StartDateTime");
        }
      if(!this.getTimeResolution().equals(b.getTimeResolution())){
         System.out.println("TimeResolution");
        }
      if(!this.getWipProbability().equals(b.getWipProbability())){
         System.out.println("WipProbability");
        }
        return  this.getDowntimeProbability().equals(b.getDowntimeProbability()) &&
          this.getDurationFixedFactor().equals(b.getDurationFixedFactor()) &&
          this.getDurationModel().equals(b.getDurationModel()) &&
          this.getEarliestDue().equals(b.getEarliestDue()) &&
          this.getHorizonDays().equals(b.getHorizonDays()) &&
          this.getLabel().equals(b.getLabel()) &&
          this.getMaxCumulCapacity().equals(b.getMaxCumulCapacity()) &&
          this.getMaxCumulDemand().equals(b.getMaxCumulDemand()) &&
          this.getMaxDowntime().equals(b.getMaxDowntime()) &&
          this.getMaxDuration().equals(b.getMaxDuration()) &&
          this.getMaxQty().equals(b.getMaxQty()) &&
          this.getMaxStages().equals(b.getMaxStages()) &&
          this.getMaxWip().equals(b.getMaxWip()) &&
          this.getMinCumulCapacity().equals(b.getMinCumulCapacity()) &&
          this.getMinCumulDemand().equals(b.getMinCumulDemand()) &&
          this.getMinDowntime().equals(b.getMinDowntime()) &&
          this.getMinDuration().equals(b.getMinDuration()) &&
          this.getMinQty().equals(b.getMinQty()) &&
          this.getMinStages().equals(b.getMinStages()) &&
          this.getMinWip().equals(b.getMinWip()) &&
          this.getName().equals(b.getName()) &&
          this.getNrCumulativeResources().equals(b.getNrCumulativeResources()) &&
          this.getNrDisjunctiveResources().equals(b.getNrDisjunctiveResources()) &&
          this.getNrOrders().equals(b.getNrOrders()) &&
          this.getNrProducts().equals(b.getNrProducts()) &&
          this.getProfilePieces().equals(b.getProfilePieces()) &&
          this.getResourceModel().equals(b.getResourceModel()) &&
          this.getResourceProbability().equals(b.getResourceProbability()) &&
          this.getSeed().equals(b.getSeed()) &&
          this.getStartDateTime().applicationEqual(b.getStartDateTime()) &&
          this.getTimeResolution().equals(b.getTimeResolution()) &&
          this.getWipProbability().equals(b.getWipProbability());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","DataGeneratorRun",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class DataGeneratorRun
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

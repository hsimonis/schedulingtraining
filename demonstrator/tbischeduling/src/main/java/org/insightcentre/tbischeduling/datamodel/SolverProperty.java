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

public  class SolverProperty extends AbstractSolverProperty{
/**
 *  No-arg constructor for use in TableView
 *
*/

    public SolverProperty(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public SolverProperty(ApplicationDataset applicationDataset){
        super(applicationDataset);
        applicationDataset.addSolverProperty(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public SolverProperty(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Boolean addSameOrder,
            String description,
            Boolean enforceCumulative,
            Boolean enforceDowntime,
            Boolean enforceDueDate,
            Boolean enforceReleaseDate,
            Boolean enforceSetup,
            Boolean enforceTransportTime,
            Boolean enforceWip,
            String label,
            ModelType modelType,
            Integer nrThreads,
            ObjectiveType objectiveType,
            Boolean producePDF,
            Boolean produceReport,
            Boolean relaxSequence,
            Boolean removeSolution,
            Integer seed,
            SolverBackend solverBackend,
            DateTime startDateTime,
            Integer timeout,
            Integer weightEarliness,
            Integer weightFlowtime,
            Integer weightLateness,
            Integer weightMakespan){
        super(applicationDataset,
            id,
            name,
            addSameOrder,
            description,
            enforceCumulative,
            enforceDowntime,
            enforceDueDate,
            enforceReleaseDate,
            enforceSetup,
            enforceTransportTime,
            enforceWip,
            label,
            modelType,
            nrThreads,
            objectiveType,
            producePDF,
            produceReport,
            relaxSequence,
            removeSolution,
            seed,
            solverBackend,
            startDateTime,
            timeout,
            weightEarliness,
            weightFlowtime,
            weightLateness,
            weightMakespan);
        applicationDataset.addSolverProperty(this);
    }

    public SolverProperty(SolverProperty other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.addSameOrder,
            other.description,
            other.enforceCumulative,
            other.enforceDowntime,
            other.enforceDueDate,
            other.enforceReleaseDate,
            other.enforceSetup,
            other.enforceTransportTime,
            other.enforceWip,
            other.label,
            other.modelType,
            other.nrThreads,
            other.objectiveType,
            other.producePDF,
            other.produceReport,
            other.relaxSequence,
            other.removeSolution,
            other.seed,
            other.solverBackend,
            other.startDateTime,
            other.timeout,
            other.weightEarliness,
            other.weightFlowtime,
            other.weightLateness,
            other.weightMakespan);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        getApplicationDataset().cascadeScenarioSolverProperty(this);
        return getApplicationDataset().removeSolverProperty(this) && getApplicationDataset().removeAbstractSolverProperty(this) && getApplicationDataset().removeApplicationObject(this);
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
        return ""+ " " +getId()+ " " +getName()+ " " +getAddSameOrder()+ " " +getDescription()+ " " +getEnforceCumulative()+ " " +getEnforceDowntime()+ " " +getEnforceDueDate()+ " " +getEnforceReleaseDate()+ " " +getEnforceSetup()+ " " +getEnforceTransportTime()+ " " +getEnforceWip()+ " " +getLabel()+ " " +getModelType()+ " " +getNrThreads()+ " " +getObjectiveType()+ " " +getProducePDF()+ " " +getProduceReport()+ " " +getRelaxSequence()+ " " +getRemoveSolution()+ " " +getSeed()+ " " +getSolverBackend()+ " " +getStartDateTime()+ " " +getTimeout()+ " " +getWeightEarliness()+ " " +getWeightFlowtime()+ " " +getWeightLateness()+ " " +getWeightMakespan();
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
         out.println("<solverProperty "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " addSameOrder=\""+toXMLAddSameOrder()+"\""+
            " description=\""+toXMLDescription()+"\""+
            " enforceCumulative=\""+toXMLEnforceCumulative()+"\""+
            " enforceDowntime=\""+toXMLEnforceDowntime()+"\""+
            " enforceDueDate=\""+toXMLEnforceDueDate()+"\""+
            " enforceReleaseDate=\""+toXMLEnforceReleaseDate()+"\""+
            " enforceSetup=\""+toXMLEnforceSetup()+"\""+
            " enforceTransportTime=\""+toXMLEnforceTransportTime()+"\""+
            " enforceWip=\""+toXMLEnforceWip()+"\""+
            " label=\""+toXMLLabel()+"\""+
            " modelType=\""+toXMLModelType()+"\""+
            " nrThreads=\""+toXMLNrThreads()+"\""+
            " objectiveType=\""+toXMLObjectiveType()+"\""+
            " producePDF=\""+toXMLProducePDF()+"\""+
            " produceReport=\""+toXMLProduceReport()+"\""+
            " relaxSequence=\""+toXMLRelaxSequence()+"\""+
            " removeSolution=\""+toXMLRemoveSolution()+"\""+
            " seed=\""+toXMLSeed()+"\""+
            " solverBackend=\""+toXMLSolverBackend()+"\""+
            " startDateTime=\""+toXMLStartDateTime()+"\""+
            " timeout=\""+toXMLTimeout()+"\""+
            " weightEarliness=\""+toXMLWeightEarliness()+"\""+
            " weightFlowtime=\""+toXMLWeightFlowtime()+"\""+
            " weightLateness=\""+toXMLWeightLateness()+"\""+
            " weightMakespan=\""+toXMLWeightMakespan()+"\""+" />");
     }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>SolverProperty</th>"+"<th>Name</th>"+"<th>Label</th>"+"<th>Description</th>"+"<th>StartDateTime</th>"+"<th>EnforceReleaseDate</th>"+"<th>EnforceDueDate</th>"+"<th>EnforceCumulative</th>"+"<th>EnforceWip</th>"+"<th>EnforceDowntime</th>"+"<th>EnforceSetup</th>"+"<th>EnforceTransportTime</th>"+"<th>RelaxSequence</th>"+"<th>AddSameOrder</th>"+"<th>ModelType</th>"+"<th>SolverBackend</th>"+"<th>ObjectiveType</th>"+"<th>WeightMakespan</th>"+"<th>WeightFlowtime</th>"+"<th>WeightLateness</th>"+"<th>WeightEarliness</th>"+"<th>Timeout</th>"+"<th>NrThreads</th>"+"<th>Seed</th>"+"<th>RemoveSolution</th>"+"<th>ProduceReport</th>"+"<th>ProducePDF</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getLabel()+"</td>"+ " " +"<td>"+getDescription()+"</td>"+ " " +"<td>"+getStartDateTime()+"</td>"+ " " +"<td>"+getEnforceReleaseDate()+"</td>"+ " " +"<td>"+getEnforceDueDate()+"</td>"+ " " +"<td>"+getEnforceCumulative()+"</td>"+ " " +"<td>"+getEnforceWip()+"</td>"+ " " +"<td>"+getEnforceDowntime()+"</td>"+ " " +"<td>"+getEnforceSetup()+"</td>"+ " " +"<td>"+getEnforceTransportTime()+"</td>"+ " " +"<td>"+getRelaxSequence()+"</td>"+ " " +"<td>"+getAddSameOrder()+"</td>"+ " " +"<td>"+getModelType()+"</td>"+ " " +"<td>"+getSolverBackend()+"</td>"+ " " +"<td>"+getObjectiveType()+"</td>"+ " " +"<td>"+getWeightMakespan()+"</td>"+ " " +"<td>"+getWeightFlowtime()+"</td>"+ " " +"<td>"+getWeightLateness()+"</td>"+ " " +"<td>"+getWeightEarliness()+"</td>"+ " " +"<td>"+getTimeout()+"</td>"+ " " +"<td>"+getNrThreads()+"</td>"+ " " +"<td>"+getSeed()+"</td>"+ " " +"<td>"+getRemoveSolution()+"</td>"+ " " +"<td>"+getProduceReport()+"</td>"+ " " +"<td>"+getProducePDF()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a SolverProperty item we are looking for
 * @param bList List<SolverProperty> list of items in which we are searching
 * @return SolverProperty entry of list b which is applicationSame() to a
*/

    public static SolverProperty find(SolverProperty a, List<SolverProperty> bList){
        for(SolverProperty b : bList){
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
 * @param name SolverProperty name of the object we are looking for
 * @return SolverProperty entry of the dataset with the given name; otherwise null
*/

    public static SolverProperty findByName(ApplicationDataset base, String name){
        for(SolverProperty a:base.getListSolverProperty()) {
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
 * @param name SolverProperty name of the object we are looking for
 * @return SolverProperty entry of the dataset with the given name
*/

    public static SolverProperty findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(SolverProperty a:base.getListSolverProperty()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        SolverProperty res = new SolverProperty(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return SolverProperty first entry in the dataset of this type; null if that does not exists
*/

    public static SolverProperty findFirst(ApplicationDataset base){
        if (base.getListSolverProperty().isEmpty()) {
            return null;
        }
        return base.getListSolverProperty().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return SolverProperty some entry in the dataset of this type; null if that does not exists
*/

    public static SolverProperty findAny(ApplicationDataset base){
        int size=base.getListSolverProperty().size();
        if (size > 0) {
             return base.getListSolverProperty().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return SolverProperty last entry in the dataset of this type; null if that does not exists
*/

    public static SolverProperty findLast(ApplicationDataset base){
        int size=base.getListSolverProperty().size();
        if (size > 0) {
             return base.getListSolverProperty().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b SolverProperty compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(SolverProperty b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b SolverProperty compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(SolverProperty b){
      if(!this.getAddSameOrder().equals(b.getAddSameOrder())){
         System.out.println("AddSameOrder");
        }
      if(!this.getDescription().equals(b.getDescription())){
         System.out.println("Description");
        }
      if(!this.getEnforceCumulative().equals(b.getEnforceCumulative())){
         System.out.println("EnforceCumulative");
        }
      if(!this.getEnforceDowntime().equals(b.getEnforceDowntime())){
         System.out.println("EnforceDowntime");
        }
      if(!this.getEnforceDueDate().equals(b.getEnforceDueDate())){
         System.out.println("EnforceDueDate");
        }
      if(!this.getEnforceReleaseDate().equals(b.getEnforceReleaseDate())){
         System.out.println("EnforceReleaseDate");
        }
      if(!this.getEnforceSetup().equals(b.getEnforceSetup())){
         System.out.println("EnforceSetup");
        }
      if(!this.getEnforceTransportTime().equals(b.getEnforceTransportTime())){
         System.out.println("EnforceTransportTime");
        }
      if(!this.getEnforceWip().equals(b.getEnforceWip())){
         System.out.println("EnforceWip");
        }
      if(!this.getLabel().equals(b.getLabel())){
         System.out.println("Label");
        }
      if(!this.getModelType().equals(b.getModelType())){
         System.out.println("ModelType");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getNrThreads().equals(b.getNrThreads())){
         System.out.println("NrThreads");
        }
      if(!this.getObjectiveType().equals(b.getObjectiveType())){
         System.out.println("ObjectiveType");
        }
      if(!this.getProducePDF().equals(b.getProducePDF())){
         System.out.println("ProducePDF");
        }
      if(!this.getProduceReport().equals(b.getProduceReport())){
         System.out.println("ProduceReport");
        }
      if(!this.getRelaxSequence().equals(b.getRelaxSequence())){
         System.out.println("RelaxSequence");
        }
      if(!this.getRemoveSolution().equals(b.getRemoveSolution())){
         System.out.println("RemoveSolution");
        }
      if(!this.getSeed().equals(b.getSeed())){
         System.out.println("Seed");
        }
      if(!this.getSolverBackend().equals(b.getSolverBackend())){
         System.out.println("SolverBackend");
        }
      if(!this.getStartDateTime().applicationEqual(b.getStartDateTime())){
         System.out.println("StartDateTime");
        }
      if(!this.getTimeout().equals(b.getTimeout())){
         System.out.println("Timeout");
        }
      if(!this.getWeightEarliness().equals(b.getWeightEarliness())){
         System.out.println("WeightEarliness");
        }
      if(!this.getWeightFlowtime().equals(b.getWeightFlowtime())){
         System.out.println("WeightFlowtime");
        }
      if(!this.getWeightLateness().equals(b.getWeightLateness())){
         System.out.println("WeightLateness");
        }
      if(!this.getWeightMakespan().equals(b.getWeightMakespan())){
         System.out.println("WeightMakespan");
        }
        return  this.getAddSameOrder().equals(b.getAddSameOrder()) &&
          this.getDescription().equals(b.getDescription()) &&
          this.getEnforceCumulative().equals(b.getEnforceCumulative()) &&
          this.getEnforceDowntime().equals(b.getEnforceDowntime()) &&
          this.getEnforceDueDate().equals(b.getEnforceDueDate()) &&
          this.getEnforceReleaseDate().equals(b.getEnforceReleaseDate()) &&
          this.getEnforceSetup().equals(b.getEnforceSetup()) &&
          this.getEnforceTransportTime().equals(b.getEnforceTransportTime()) &&
          this.getEnforceWip().equals(b.getEnforceWip()) &&
          this.getLabel().equals(b.getLabel()) &&
          this.getModelType().equals(b.getModelType()) &&
          this.getName().equals(b.getName()) &&
          this.getNrThreads().equals(b.getNrThreads()) &&
          this.getObjectiveType().equals(b.getObjectiveType()) &&
          this.getProducePDF().equals(b.getProducePDF()) &&
          this.getProduceReport().equals(b.getProduceReport()) &&
          this.getRelaxSequence().equals(b.getRelaxSequence()) &&
          this.getRemoveSolution().equals(b.getRemoveSolution()) &&
          this.getSeed().equals(b.getSeed()) &&
          this.getSolverBackend().equals(b.getSolverBackend()) &&
          this.getStartDateTime().applicationEqual(b.getStartDateTime()) &&
          this.getTimeout().equals(b.getTimeout()) &&
          this.getWeightEarliness().equals(b.getWeightEarliness()) &&
          this.getWeightFlowtime().equals(b.getWeightFlowtime()) &&
          this.getWeightLateness().equals(b.getWeightLateness()) &&
          this.getWeightMakespan().equals(b.getWeightMakespan());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","SolverProperty",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class SolverProperty
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

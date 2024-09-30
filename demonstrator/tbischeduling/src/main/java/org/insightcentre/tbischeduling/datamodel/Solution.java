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

public  class Solution extends ApplicationObject{
/**
 *  
 *
*/

    public Double bound;

/**
 *  
 *
*/

    public Integer flowtime;

/**
 *  
 *
*/

    public Double gap;

/**
 *  
 *
*/

    public Integer makespan;

/**
 *  
 *
*/

    public Integer maxEarliness;

/**
 *  
 *
*/

    public Integer maxLateness;

/**
 *  
 *
*/

    public Integer nrEarly;

/**
 *  
 *
*/

    public Integer nrLate;

/**
 *  
 *
*/

    public Integer objectiveValue;

/**
 *  
 *
*/

    public Double percentEarly;

/**
 *  
 *
*/

    public Double percentLate;

/**
 *  
 *
*/

    public SolverRun solverRun;

/**
 *  
 *
*/

    public SolverStatus solverStatus;

/**
 *  
 *
*/

    public Integer totalEarliness;

/**
 *  
 *
*/

    public Integer totalLateness;

/**
 *  
 *
*/

    public Double weightedEarliness;

/**
 *  
 *
*/

    public Double weightedLateness;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public Solution(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public Solution(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setBound(0.0);
        setFlowtime(0);
        setGap(0.0);
        setMakespan(0);
        setMaxEarliness(0);
        setMaxLateness(0);
        setNrEarly(0);
        setNrLate(0);
        setObjectiveValue(0);
        setPercentEarly(0.0);
        setPercentLate(0.0);
        setSolverRun(null);
        setSolverStatus(null);
        setTotalEarliness(0);
        setTotalLateness(0);
        setWeightedEarliness(0.0);
        setWeightedLateness(0.0);
        applicationDataset.addSolution(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public Solution(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Double bound,
            Integer flowtime,
            Double gap,
            Integer makespan,
            Integer maxEarliness,
            Integer maxLateness,
            Integer nrEarly,
            Integer nrLate,
            Integer objectiveValue,
            Double percentEarly,
            Double percentLate,
            SolverRun solverRun,
            SolverStatus solverStatus,
            Integer totalEarliness,
            Integer totalLateness,
            Double weightedEarliness,
            Double weightedLateness){
        super(applicationDataset,
            id,
            name);
        setBound(bound);
        setFlowtime(flowtime);
        setGap(gap);
        setMakespan(makespan);
        setMaxEarliness(maxEarliness);
        setMaxLateness(maxLateness);
        setNrEarly(nrEarly);
        setNrLate(nrLate);
        setObjectiveValue(objectiveValue);
        setPercentEarly(percentEarly);
        setPercentLate(percentLate);
        setSolverRun(solverRun);
        setSolverStatus(solverStatus);
        setTotalEarliness(totalEarliness);
        setTotalLateness(totalLateness);
        setWeightedEarliness(weightedEarliness);
        setWeightedLateness(weightedLateness);
        applicationDataset.addSolution(this);
    }

    public Solution(Solution other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.bound,
            other.flowtime,
            other.gap,
            other.makespan,
            other.maxEarliness,
            other.maxLateness,
            other.nrEarly,
            other.nrLate,
            other.objectiveValue,
            other.percentEarly,
            other.percentLate,
            other.solverRun,
            other.solverStatus,
            other.totalEarliness,
            other.totalLateness,
            other.weightedEarliness,
            other.weightedLateness);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        getApplicationDataset().cascadeJobAssignmentSolution(this);
        return getApplicationDataset().removeSolution(this) && getApplicationDataset().removeApplicationObject(this);
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
 *  get attribute flowtime
 *
 * @return Integer
*/

    public Integer getFlowtime(){
        return this.flowtime;
    }

/**
 *  get attribute gap
 *
 * @return Double
*/

    public Double getGap(){
        return this.gap;
    }

/**
 *  get attribute makespan
 *
 * @return Integer
*/

    public Integer getMakespan(){
        return this.makespan;
    }

/**
 *  get attribute maxEarliness
 *
 * @return Integer
*/

    public Integer getMaxEarliness(){
        return this.maxEarliness;
    }

/**
 *  get attribute maxLateness
 *
 * @return Integer
*/

    public Integer getMaxLateness(){
        return this.maxLateness;
    }

/**
 *  get attribute nrEarly
 *
 * @return Integer
*/

    public Integer getNrEarly(){
        return this.nrEarly;
    }

/**
 *  get attribute nrLate
 *
 * @return Integer
*/

    public Integer getNrLate(){
        return this.nrLate;
    }

/**
 *  get attribute objectiveValue
 *
 * @return Integer
*/

    public Integer getObjectiveValue(){
        return this.objectiveValue;
    }

/**
 *  get attribute percentEarly
 *
 * @return Double
*/

    public Double getPercentEarly(){
        return this.percentEarly;
    }

/**
 *  get attribute percentLate
 *
 * @return Double
*/

    public Double getPercentLate(){
        return this.percentLate;
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
 *  get attribute solverStatus
 *
 * @return SolverStatus
*/

    public SolverStatus getSolverStatus(){
        return this.solverStatus;
    }

/**
 *  get attribute totalEarliness
 *
 * @return Integer
*/

    public Integer getTotalEarliness(){
        return this.totalEarliness;
    }

/**
 *  get attribute totalLateness
 *
 * @return Integer
*/

    public Integer getTotalLateness(){
        return this.totalLateness;
    }

/**
 *  get attribute weightedEarliness
 *
 * @return Double
*/

    public Double getWeightedEarliness(){
        return this.weightedEarliness;
    }

/**
 *  get attribute weightedLateness
 *
 * @return Double
*/

    public Double getWeightedLateness(){
        return this.weightedLateness;
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
 *  set attribute flowtime, mark dataset as dirty, mark dataset as not valid
@param flowtime Integer
 *
*/

    public void setFlowtime(Integer flowtime){
        this.flowtime = flowtime;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute gap, mark dataset as dirty, mark dataset as not valid
@param gap Double
 *
*/

    public void setGap(Double gap){
        this.gap = gap;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute makespan, mark dataset as dirty, mark dataset as not valid
@param makespan Integer
 *
*/

    public void setMakespan(Integer makespan){
        this.makespan = makespan;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute maxEarliness, mark dataset as dirty, mark dataset as not valid
@param maxEarliness Integer
 *
*/

    public void setMaxEarliness(Integer maxEarliness){
        this.maxEarliness = maxEarliness;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute maxLateness, mark dataset as dirty, mark dataset as not valid
@param maxLateness Integer
 *
*/

    public void setMaxLateness(Integer maxLateness){
        this.maxLateness = maxLateness;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute nrEarly, mark dataset as dirty, mark dataset as not valid
@param nrEarly Integer
 *
*/

    public void setNrEarly(Integer nrEarly){
        this.nrEarly = nrEarly;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute nrLate, mark dataset as dirty, mark dataset as not valid
@param nrLate Integer
 *
*/

    public void setNrLate(Integer nrLate){
        this.nrLate = nrLate;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute objectiveValue, mark dataset as dirty, mark dataset as not valid
@param objectiveValue Integer
 *
*/

    public void setObjectiveValue(Integer objectiveValue){
        this.objectiveValue = objectiveValue;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute percentEarly, mark dataset as dirty, mark dataset as not valid
@param percentEarly Double
 *
*/

    public void setPercentEarly(Double percentEarly){
        this.percentEarly = percentEarly;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute percentLate, mark dataset as dirty, mark dataset as not valid
@param percentLate Double
 *
*/

    public void setPercentLate(Double percentLate){
        this.percentLate = percentLate;
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
 *  set attribute solverStatus, mark dataset as dirty, mark dataset as not valid
@param solverStatus SolverStatus
 *
*/

    public void setSolverStatus(SolverStatus solverStatus){
        this.solverStatus = solverStatus;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute totalEarliness, mark dataset as dirty, mark dataset as not valid
@param totalEarliness Integer
 *
*/

    public void setTotalEarliness(Integer totalEarliness){
        this.totalEarliness = totalEarliness;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute totalLateness, mark dataset as dirty, mark dataset as not valid
@param totalLateness Integer
 *
*/

    public void setTotalLateness(Integer totalLateness){
        this.totalLateness = totalLateness;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute weightedEarliness, mark dataset as dirty, mark dataset as not valid
@param weightedEarliness Double
 *
*/

    public void setWeightedEarliness(Double weightedEarliness){
        this.weightedEarliness = weightedEarliness;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute weightedLateness, mark dataset as dirty, mark dataset as not valid
@param weightedLateness Double
 *
*/

    public void setWeightedLateness(Double weightedLateness){
        this.weightedLateness = weightedLateness;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute flowtime, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incFlowtime(){
        this.flowtime++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute makespan, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMakespan(){
        this.makespan++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute maxEarliness, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMaxEarliness(){
        this.maxEarliness++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute maxLateness, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMaxLateness(){
        this.maxLateness++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nrEarly, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNrEarly(){
        this.nrEarly++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nrLate, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNrLate(){
        this.nrLate++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute objectiveValue, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incObjectiveValue(){
        this.objectiveValue++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute totalEarliness, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incTotalEarliness(){
        this.totalEarliness++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute totalLateness, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incTotalLateness(){
        this.totalLateness++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getBound()+ " " +getFlowtime()+ " " +getGap()+ " " +getMakespan()+ " " +getMaxEarliness()+ " " +getMaxLateness()+ " " +getNrEarly()+ " " +getNrLate()+ " " +getObjectiveValue()+ " " +getPercentEarly()+ " " +getPercentLate()+ " " +getSolverRun().toColumnString()+ " " +getSolverStatus()+ " " +getTotalEarliness()+ " " +getTotalLateness()+ " " +getWeightedEarliness()+ " " +getWeightedLateness();
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
         out.println("<solution "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " bound=\""+toXMLBound()+"\""+
            " flowtime=\""+toXMLFlowtime()+"\""+
            " gap=\""+toXMLGap()+"\""+
            " makespan=\""+toXMLMakespan()+"\""+
            " maxEarliness=\""+toXMLMaxEarliness()+"\""+
            " maxLateness=\""+toXMLMaxLateness()+"\""+
            " nrEarly=\""+toXMLNrEarly()+"\""+
            " nrLate=\""+toXMLNrLate()+"\""+
            " objectiveValue=\""+toXMLObjectiveValue()+"\""+
            " percentEarly=\""+toXMLPercentEarly()+"\""+
            " percentLate=\""+toXMLPercentLate()+"\""+
            " solverRun=\""+toXMLSolverRun()+"\""+
            " solverStatus=\""+toXMLSolverStatus()+"\""+
            " totalEarliness=\""+toXMLTotalEarliness()+"\""+
            " totalLateness=\""+toXMLTotalLateness()+"\""+
            " weightedEarliness=\""+toXMLWeightedEarliness()+"\""+
            " weightedLateness=\""+toXMLWeightedLateness()+"\""+" />");
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

    String toXMLFlowtime(){
        return this.getFlowtime().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLGap(){
        return this.getGap().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMakespan(){
        return this.getMakespan().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMaxEarliness(){
        return this.getMaxEarliness().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMaxLateness(){
        return this.getMaxLateness().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNrEarly(){
        return this.getNrEarly().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNrLate(){
        return this.getNrLate().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLObjectiveValue(){
        return this.getObjectiveValue().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLPercentEarly(){
        return this.getPercentEarly().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLPercentLate(){
        return this.getPercentLate().toString();
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

    String toXMLSolverStatus(){
        return this.getSolverStatus().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLTotalEarliness(){
        return this.getTotalEarliness().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLTotalLateness(){
        return this.getTotalLateness().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLWeightedEarliness(){
        return this.getWeightedEarliness().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLWeightedLateness(){
        return this.getWeightedLateness().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>Solution</th>"+"<th>Name</th>"+"<th>SolverRun</th>"+"<th>ObjectiveValue</th>"+"<th>SolverStatus</th>"+"<th>Bound</th>"+"<th>Gap</th>"+"<th>Makespan</th>"+"<th>Flowtime</th>"+"<th>TotalLateness</th>"+"<th>MaxLateness</th>"+"<th>NrLate</th>"+"<th>WeightedLateness</th>"+"<th>TotalEarliness</th>"+"<th>MaxEarliness</th>"+"<th>NrEarly</th>"+"<th>WeightedEarliness</th>"+"<th>PercentEarly</th>"+"<th>PercentLate</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getSolverRun().toColumnString()+"</td>"+ " " +"<td>"+getObjectiveValue()+"</td>"+ " " +"<td>"+getSolverStatus()+"</td>"+ " " +"<td>"+getBound()+"</td>"+ " " +"<td>"+getGap()+"</td>"+ " " +"<td>"+getMakespan()+"</td>"+ " " +"<td>"+getFlowtime()+"</td>"+ " " +"<td>"+getTotalLateness()+"</td>"+ " " +"<td>"+getMaxLateness()+"</td>"+ " " +"<td>"+getNrLate()+"</td>"+ " " +"<td>"+getWeightedLateness()+"</td>"+ " " +"<td>"+getTotalEarliness()+"</td>"+ " " +"<td>"+getMaxEarliness()+"</td>"+ " " +"<td>"+getNrEarly()+"</td>"+ " " +"<td>"+getWeightedEarliness()+"</td>"+ " " +"<td>"+getPercentEarly()+"</td>"+ " " +"<td>"+getPercentLate()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a Solution item we are looking for
 * @param bList List<Solution> list of items in which we are searching
 * @return Solution entry of list b which is applicationSame() to a
*/

    public static Solution find(Solution a, List<Solution> bList){
        for(Solution b : bList){
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
 * @param name Solution name of the object we are looking for
 * @return Solution entry of the dataset with the given name; otherwise null
*/

    public static Solution findByName(ApplicationDataset base, String name){
        for(Solution a:base.getListSolution()) {
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
 * @param name Solution name of the object we are looking for
 * @return Solution entry of the dataset with the given name
*/

    public static Solution findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(Solution a:base.getListSolution()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        Solution res = new Solution(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Solution first entry in the dataset of this type; null if that does not exists
*/

    public static Solution findFirst(ApplicationDataset base){
        if (base.getListSolution().isEmpty()) {
            return null;
        }
        return base.getListSolution().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Solution some entry in the dataset of this type; null if that does not exists
*/

    public static Solution findAny(ApplicationDataset base){
        int size=base.getListSolution().size();
        if (size > 0) {
             return base.getListSolution().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Solution last entry in the dataset of this type; null if that does not exists
*/

    public static Solution findLast(ApplicationDataset base){
        int size=base.getListSolution().size();
        if (size > 0) {
             return base.getListSolution().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b Solution compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(Solution b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b Solution compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(Solution b){
      if(!this.getBound().equals(b.getBound())){
         System.out.println("Bound");
        }
      if(!this.getFlowtime().equals(b.getFlowtime())){
         System.out.println("Flowtime");
        }
      if(!this.getGap().equals(b.getGap())){
         System.out.println("Gap");
        }
      if(!this.getMakespan().equals(b.getMakespan())){
         System.out.println("Makespan");
        }
      if(!this.getMaxEarliness().equals(b.getMaxEarliness())){
         System.out.println("MaxEarliness");
        }
      if(!this.getMaxLateness().equals(b.getMaxLateness())){
         System.out.println("MaxLateness");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getNrEarly().equals(b.getNrEarly())){
         System.out.println("NrEarly");
        }
      if(!this.getNrLate().equals(b.getNrLate())){
         System.out.println("NrLate");
        }
      if(!this.getObjectiveValue().equals(b.getObjectiveValue())){
         System.out.println("ObjectiveValue");
        }
      if(!this.getPercentEarly().equals(b.getPercentEarly())){
         System.out.println("PercentEarly");
        }
      if(!this.getPercentLate().equals(b.getPercentLate())){
         System.out.println("PercentLate");
        }
      if(!this.getSolverRun().applicationSame(b.getSolverRun())){
         System.out.println("SolverRun");
        }
      if(!this.getSolverStatus().equals(b.getSolverStatus())){
         System.out.println("SolverStatus");
        }
      if(!this.getTotalEarliness().equals(b.getTotalEarliness())){
         System.out.println("TotalEarliness");
        }
      if(!this.getTotalLateness().equals(b.getTotalLateness())){
         System.out.println("TotalLateness");
        }
      if(!this.getWeightedEarliness().equals(b.getWeightedEarliness())){
         System.out.println("WeightedEarliness");
        }
      if(!this.getWeightedLateness().equals(b.getWeightedLateness())){
         System.out.println("WeightedLateness");
        }
        return  this.getBound().equals(b.getBound()) &&
          this.getFlowtime().equals(b.getFlowtime()) &&
          this.getGap().equals(b.getGap()) &&
          this.getMakespan().equals(b.getMakespan()) &&
          this.getMaxEarliness().equals(b.getMaxEarliness()) &&
          this.getMaxLateness().equals(b.getMaxLateness()) &&
          this.getName().equals(b.getName()) &&
          this.getNrEarly().equals(b.getNrEarly()) &&
          this.getNrLate().equals(b.getNrLate()) &&
          this.getObjectiveValue().equals(b.getObjectiveValue()) &&
          this.getPercentEarly().equals(b.getPercentEarly()) &&
          this.getPercentLate().equals(b.getPercentLate()) &&
          this.getSolverRun().applicationSame(b.getSolverRun()) &&
          this.getSolverStatus().equals(b.getSolverStatus()) &&
          this.getTotalEarliness().equals(b.getTotalEarliness()) &&
          this.getTotalLateness().equals(b.getTotalLateness()) &&
          this.getWeightedEarliness().equals(b.getWeightedEarliness()) &&
          this.getWeightedLateness().equals(b.getWeightedLateness());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","Solution",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
        if (getSolverRun() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"solverRun","Solution",(getSolverRun()==null?"null":getSolverRun().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class Solution
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

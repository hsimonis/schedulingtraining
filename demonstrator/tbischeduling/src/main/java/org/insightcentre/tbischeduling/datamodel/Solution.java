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
import org.insightcentre.tbischeduling.datamodel.ColorBy;
import org.insightcentre.tbischeduling.datamodel.TaskLabel;
import org.insightcentre.tbischeduling.datamodel.JobOrder;
import org.insightcentre.tbischeduling.datamodel.ResourceChoice;
import org.insightcentre.tbischeduling.datamodel.LineChoice;
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

public  class Solution extends ApplicationObject implements SolutionHierarchy{
/**
 *  
 *
*/

    public Double bound;

/**
 *  
 *
*/

    public Integer duration;

/**
 *  
 *
*/

    public Integer end;

/**
 *  
 *
*/

    public DateTime endDate;

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

    public Integer maxWaitAfter;

/**
 *  
 *
*/

    public Integer maxWaitBefore;

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

    public Integer start;

/**
 *  
 *
*/

    public DateTime startDate;

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

    public Integer totalWaitAfter;

/**
 *  
 *
*/

    public Integer totalWaitBefore;

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
        setDuration(0);
        setEnd(0);
        setEndDate(new DateTime());
        setFlowtime(0);
        setGap(0.0);
        setMakespan(0);
        setMaxEarliness(0);
        setMaxLateness(0);
        setMaxWaitAfter(0);
        setMaxWaitBefore(0);
        setNrEarly(0);
        setNrLate(0);
        setObjectiveValue(0);
        setPercentEarly(0.0);
        setPercentLate(0.0);
        setSolverRun(null);
        setSolverStatus(null);
        setStart(0);
        setStartDate(new DateTime());
        setTotalEarliness(0);
        setTotalLateness(0);
        setTotalWaitAfter(0);
        setTotalWaitBefore(0);
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
            Integer duration,
            Integer end,
            DateTime endDate,
            Integer flowtime,
            Double gap,
            Integer makespan,
            Integer maxEarliness,
            Integer maxLateness,
            Integer maxWaitAfter,
            Integer maxWaitBefore,
            Integer nrEarly,
            Integer nrLate,
            Integer objectiveValue,
            Double percentEarly,
            Double percentLate,
            SolverRun solverRun,
            SolverStatus solverStatus,
            Integer start,
            DateTime startDate,
            Integer totalEarliness,
            Integer totalLateness,
            Integer totalWaitAfter,
            Integer totalWaitBefore,
            Double weightedEarliness,
            Double weightedLateness){
        super(applicationDataset,
            id,
            name);
        setBound(bound);
        setDuration(duration);
        setEnd(end);
        setEndDate(endDate);
        setFlowtime(flowtime);
        setGap(gap);
        setMakespan(makespan);
        setMaxEarliness(maxEarliness);
        setMaxLateness(maxLateness);
        setMaxWaitAfter(maxWaitAfter);
        setMaxWaitBefore(maxWaitBefore);
        setNrEarly(nrEarly);
        setNrLate(nrLate);
        setObjectiveValue(objectiveValue);
        setPercentEarly(percentEarly);
        setPercentLate(percentLate);
        setSolverRun(solverRun);
        setSolverStatus(solverStatus);
        setStart(start);
        setStartDate(startDate);
        setTotalEarliness(totalEarliness);
        setTotalLateness(totalLateness);
        setTotalWaitAfter(totalWaitAfter);
        setTotalWaitBefore(totalWaitBefore);
        setWeightedEarliness(weightedEarliness);
        setWeightedLateness(weightedLateness);
        applicationDataset.addSolution(this);
    }

    public Solution(Solution other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.bound,
            other.duration,
            other.end,
            other.endDate,
            other.flowtime,
            other.gap,
            other.makespan,
            other.maxEarliness,
            other.maxLateness,
            other.maxWaitAfter,
            other.maxWaitBefore,
            other.nrEarly,
            other.nrLate,
            other.objectiveValue,
            other.percentEarly,
            other.percentLate,
            other.solverRun,
            other.solverStatus,
            other.start,
            other.startDate,
            other.totalEarliness,
            other.totalLateness,
            other.totalWaitAfter,
            other.totalWaitBefore,
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
        getApplicationDataset().cascadeResourceUtilizationSolution(this);
        getApplicationDataset().cascadeSolutionErrorSolution(this);
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
 *  get attribute duration
 *
 * @return Integer
*/

    public Integer getDuration(){
        return this.duration;
    }

/**
 *  get attribute end
 *
 * @return Integer
*/

    public Integer getEnd(){
        return this.end;
    }

/**
 *  get attribute endDate
 *
 * @return DateTime
*/

    public DateTime getEndDate(){
        return this.endDate;
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
 *  get attribute maxWaitAfter
 *
 * @return Integer
*/

    public Integer getMaxWaitAfter(){
        return this.maxWaitAfter;
    }

/**
 *  get attribute maxWaitBefore
 *
 * @return Integer
*/

    public Integer getMaxWaitBefore(){
        return this.maxWaitBefore;
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
 *  get attribute start
 *
 * @return Integer
*/

    public Integer getStart(){
        return this.start;
    }

/**
 *  get attribute startDate
 *
 * @return DateTime
*/

    public DateTime getStartDate(){
        return this.startDate;
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
 *  get attribute totalWaitAfter
 *
 * @return Integer
*/

    public Integer getTotalWaitAfter(){
        return this.totalWaitAfter;
    }

/**
 *  get attribute totalWaitBefore
 *
 * @return Integer
*/

    public Integer getTotalWaitBefore(){
        return this.totalWaitBefore;
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
 *  set attribute duration, mark dataset as dirty, mark dataset as not valid
@param duration Integer
 *
*/

    public void setDuration(Integer duration){
        this.duration = duration;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute end, mark dataset as dirty, mark dataset as not valid
@param end Integer
 *
*/

    public void setEnd(Integer end){
        this.end = end;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute endDate, mark dataset as dirty, mark dataset as not valid
@param endDate DateTime
 *
*/

    public void setEndDate(DateTime endDate){
        this.endDate = endDate;
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
 *  set attribute maxWaitAfter, mark dataset as dirty, mark dataset as not valid
@param maxWaitAfter Integer
 *
*/

    public void setMaxWaitAfter(Integer maxWaitAfter){
        this.maxWaitAfter = maxWaitAfter;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute maxWaitBefore, mark dataset as dirty, mark dataset as not valid
@param maxWaitBefore Integer
 *
*/

    public void setMaxWaitBefore(Integer maxWaitBefore){
        this.maxWaitBefore = maxWaitBefore;
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
 *  set attribute start, mark dataset as dirty, mark dataset as not valid
@param start Integer
 *
*/

    public void setStart(Integer start){
        this.start = start;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute startDate, mark dataset as dirty, mark dataset as not valid
@param startDate DateTime
 *
*/

    public void setStartDate(DateTime startDate){
        this.startDate = startDate;
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
 *  set attribute totalWaitAfter, mark dataset as dirty, mark dataset as not valid
@param totalWaitAfter Integer
 *
*/

    public void setTotalWaitAfter(Integer totalWaitAfter){
        this.totalWaitAfter = totalWaitAfter;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute totalWaitBefore, mark dataset as dirty, mark dataset as not valid
@param totalWaitBefore Integer
 *
*/

    public void setTotalWaitBefore(Integer totalWaitBefore){
        this.totalWaitBefore = totalWaitBefore;
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
 *  inc attribute duration, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incDuration(){
        this.duration++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute end, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incEnd(){
        this.end++;
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
 *  inc attribute maxWaitAfter, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMaxWaitAfter(){
        this.maxWaitAfter++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute maxWaitBefore, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMaxWaitBefore(){
        this.maxWaitBefore++;
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
 *  inc attribute start, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incStart(){
        this.start++;
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
 *  inc attribute totalWaitAfter, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incTotalWaitAfter(){
        this.totalWaitAfter++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute totalWaitBefore, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incTotalWaitBefore(){
        this.totalWaitBefore++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getBound()+ " " +getDuration()+ " " +getEnd()+ " " +getEndDate()+ " " +getFlowtime()+ " " +getGap()+ " " +getMakespan()+ " " +getMaxEarliness()+ " " +getMaxLateness()+ " " +getMaxWaitAfter()+ " " +getMaxWaitBefore()+ " " +getNrEarly()+ " " +getNrLate()+ " " +getObjectiveValue()+ " " +getPercentEarly()+ " " +getPercentLate()+ " " +getSolverRun().toColumnString()+ " " +getSolverStatus()+ " " +getStart()+ " " +getStartDate()+ " " +getTotalEarliness()+ " " +getTotalLateness()+ " " +getTotalWaitAfter()+ " " +getTotalWaitBefore()+ " " +getWeightedEarliness()+ " " +getWeightedLateness();
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
            " duration=\""+toXMLDuration()+"\""+
            " end=\""+toXMLEnd()+"\""+
            " endDate=\""+toXMLEndDate()+"\""+
            " flowtime=\""+toXMLFlowtime()+"\""+
            " gap=\""+toXMLGap()+"\""+
            " makespan=\""+toXMLMakespan()+"\""+
            " maxEarliness=\""+toXMLMaxEarliness()+"\""+
            " maxLateness=\""+toXMLMaxLateness()+"\""+
            " maxWaitAfter=\""+toXMLMaxWaitAfter()+"\""+
            " maxWaitBefore=\""+toXMLMaxWaitBefore()+"\""+
            " nrEarly=\""+toXMLNrEarly()+"\""+
            " nrLate=\""+toXMLNrLate()+"\""+
            " objectiveValue=\""+toXMLObjectiveValue()+"\""+
            " percentEarly=\""+toXMLPercentEarly()+"\""+
            " percentLate=\""+toXMLPercentLate()+"\""+
            " solverRun=\""+toXMLSolverRun()+"\""+
            " solverStatus=\""+toXMLSolverStatus()+"\""+
            " start=\""+toXMLStart()+"\""+
            " startDate=\""+toXMLStartDate()+"\""+
            " totalEarliness=\""+toXMLTotalEarliness()+"\""+
            " totalLateness=\""+toXMLTotalLateness()+"\""+
            " totalWaitAfter=\""+toXMLTotalWaitAfter()+"\""+
            " totalWaitBefore=\""+toXMLTotalWaitBefore()+"\""+
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

    String toXMLDuration(){
        return this.getDuration().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLEnd(){
        return this.getEnd().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLEndDate(){
        return this.getEndDate().toXML();
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

    String toXMLMaxWaitAfter(){
        return this.getMaxWaitAfter().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMaxWaitBefore(){
        return this.getMaxWaitBefore().toString();
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

    String toXMLStart(){
        return this.getStart().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLStartDate(){
        return this.getStartDate().toXML();
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

    String toXMLTotalWaitAfter(){
        return this.getTotalWaitAfter().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLTotalWaitBefore(){
        return this.getTotalWaitBefore().toString();
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
        return "<tr><th>Solution</th>"+"<th>Name</th>"+"<th>SolverRun</th>"+"<th>ObjectiveValue</th>"+"<th>SolverStatus</th>"+"<th>Bound</th>"+"<th>Gap</th>"+"<th>Makespan</th>"+"<th>Flowtime</th>"+"<th>TotalLateness</th>"+"<th>MaxLateness</th>"+"<th>NrLate</th>"+"<th>WeightedLateness</th>"+"<th>TotalEarliness</th>"+"<th>MaxEarliness</th>"+"<th>NrEarly</th>"+"<th>WeightedEarliness</th>"+"<th>PercentEarly</th>"+"<th>PercentLate</th>"+"<th>Duration</th>"+"<th>Start</th>"+"<th>End</th>"+"<th>StartDate</th>"+"<th>EndDate</th>"+"<th>TotalWaitBefore</th>"+"<th>TotalWaitAfter</th>"+"<th>MaxWaitBefore</th>"+"<th>MaxWaitAfter</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getSolverRun().toColumnString()+"</td>"+ " " +"<td>"+getObjectiveValue()+"</td>"+ " " +"<td>"+getSolverStatus()+"</td>"+ " " +"<td>"+getBound()+"</td>"+ " " +"<td>"+getGap()+"</td>"+ " " +"<td>"+getMakespan()+"</td>"+ " " +"<td>"+getFlowtime()+"</td>"+ " " +"<td>"+getTotalLateness()+"</td>"+ " " +"<td>"+getMaxLateness()+"</td>"+ " " +"<td>"+getNrLate()+"</td>"+ " " +"<td>"+getWeightedLateness()+"</td>"+ " " +"<td>"+getTotalEarliness()+"</td>"+ " " +"<td>"+getMaxEarliness()+"</td>"+ " " +"<td>"+getNrEarly()+"</td>"+ " " +"<td>"+getWeightedEarliness()+"</td>"+ " " +"<td>"+getPercentEarly()+"</td>"+ " " +"<td>"+getPercentLate()+"</td>"+ " " +"<td>"+getDuration()+"</td>"+ " " +"<td>"+getStart()+"</td>"+ " " +"<td>"+getEnd()+"</td>"+ " " +"<td>"+getStartDate()+"</td>"+ " " +"<td>"+getEndDate()+"</td>"+ " " +"<td>"+getTotalWaitBefore()+"</td>"+ " " +"<td>"+getTotalWaitAfter()+"</td>"+ " " +"<td>"+getMaxWaitBefore()+"</td>"+ " " +"<td>"+getMaxWaitAfter()+"</td>"+"</tr>";
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
      if(!this.getDuration().equals(b.getDuration())){
         System.out.println("Duration");
        }
      if(!this.getEnd().equals(b.getEnd())){
         System.out.println("End");
        }
      if(!this.getEndDate().applicationEqual(b.getEndDate())){
         System.out.println("EndDate");
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
      if(!this.getMaxWaitAfter().equals(b.getMaxWaitAfter())){
         System.out.println("MaxWaitAfter");
        }
      if(!this.getMaxWaitBefore().equals(b.getMaxWaitBefore())){
         System.out.println("MaxWaitBefore");
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
      if(!this.getStart().equals(b.getStart())){
         System.out.println("Start");
        }
      if(!this.getStartDate().applicationEqual(b.getStartDate())){
         System.out.println("StartDate");
        }
      if(!this.getTotalEarliness().equals(b.getTotalEarliness())){
         System.out.println("TotalEarliness");
        }
      if(!this.getTotalLateness().equals(b.getTotalLateness())){
         System.out.println("TotalLateness");
        }
      if(!this.getTotalWaitAfter().equals(b.getTotalWaitAfter())){
         System.out.println("TotalWaitAfter");
        }
      if(!this.getTotalWaitBefore().equals(b.getTotalWaitBefore())){
         System.out.println("TotalWaitBefore");
        }
      if(!this.getWeightedEarliness().equals(b.getWeightedEarliness())){
         System.out.println("WeightedEarliness");
        }
      if(!this.getWeightedLateness().equals(b.getWeightedLateness())){
         System.out.println("WeightedLateness");
        }
        return  this.getBound().equals(b.getBound()) &&
          this.getDuration().equals(b.getDuration()) &&
          this.getEnd().equals(b.getEnd()) &&
          this.getEndDate().applicationEqual(b.getEndDate()) &&
          this.getFlowtime().equals(b.getFlowtime()) &&
          this.getGap().equals(b.getGap()) &&
          this.getMakespan().equals(b.getMakespan()) &&
          this.getMaxEarliness().equals(b.getMaxEarliness()) &&
          this.getMaxLateness().equals(b.getMaxLateness()) &&
          this.getMaxWaitAfter().equals(b.getMaxWaitAfter()) &&
          this.getMaxWaitBefore().equals(b.getMaxWaitBefore()) &&
          this.getName().equals(b.getName()) &&
          this.getNrEarly().equals(b.getNrEarly()) &&
          this.getNrLate().equals(b.getNrLate()) &&
          this.getObjectiveValue().equals(b.getObjectiveValue()) &&
          this.getPercentEarly().equals(b.getPercentEarly()) &&
          this.getPercentLate().equals(b.getPercentLate()) &&
          this.getSolverRun().applicationSame(b.getSolverRun()) &&
          this.getSolverStatus().equals(b.getSolverStatus()) &&
          this.getStart().equals(b.getStart()) &&
          this.getStartDate().applicationEqual(b.getStartDate()) &&
          this.getTotalEarliness().equals(b.getTotalEarliness()) &&
          this.getTotalLateness().equals(b.getTotalLateness()) &&
          this.getTotalWaitAfter().equals(b.getTotalWaitAfter()) &&
          this.getTotalWaitBefore().equals(b.getTotalWaitBefore()) &&
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

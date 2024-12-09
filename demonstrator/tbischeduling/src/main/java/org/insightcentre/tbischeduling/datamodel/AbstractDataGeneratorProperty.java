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

public abstract class AbstractDataGeneratorProperty extends ApplicationObject{
/**
 *  
 *
*/

    public Double downtimeProbability;

/**
 *  
 *
*/

    public Integer durationFixedFactor;

/**
 *  
 *
*/

    public DurationModel durationModel;

/**
 *  
 *
*/

    public Integer earliestDue;

/**
 *  
 *
*/

    public Integer horizonDays;

/**
 *  
 *
*/

    public String label;

/**
 *  
 *
*/

    public Integer maxCumulCapacity;

/**
 *  
 *
*/

    public Integer maxCumulDemand;

/**
 *  
 *
*/

    public Integer maxDowntime;

/**
 *  
 *
*/

    public Integer maxDuration;

/**
 *  
 *
*/

    public Integer maxQty;

/**
 *  
 *
*/

    public Integer maxStages;

/**
 *  
 *
*/

    public Integer maxWip;

/**
 *  
 *
*/

    public Integer minCumulCapacity;

/**
 *  
 *
*/

    public Integer minCumulDemand;

/**
 *  
 *
*/

    public Integer minDowntime;

/**
 *  
 *
*/

    public Integer minDuration;

/**
 *  
 *
*/

    public Integer minQty;

/**
 *  
 *
*/

    public Integer minStages;

/**
 *  
 *
*/

    public Integer minWip;

/**
 *  
 *
*/

    public Integer nrCumulativeResources;

/**
 *  
 *
*/

    public Integer nrDisjunctiveResources;

/**
 *  
 *
*/

    public Integer nrOrders;

/**
 *  
 *
*/

    public Integer nrProducts;

/**
 *  
 *
*/

    public Integer profilePieces;

/**
 *  
 *
*/

    public ResourceModel resourceModel;

/**
 *  
 *
*/

    public Double resourceProbability;

/**
 *  
 *
*/

    public Integer seed;

/**
 *  
 *
*/

    public DateTime startDateTime;

/**
 *  
 *
*/

    public Integer timeResolution;

/**
 *  
 *
*/

    public Double wipProbability;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public AbstractDataGeneratorProperty(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public AbstractDataGeneratorProperty(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setDowntimeProbability(0.5);
        setDurationFixedFactor(1);
        setDurationModel(DurationModel.Random);
        setEarliestDue(20);
        setHorizonDays(20);
        setLabel("");
        setMaxCumulCapacity(10);
        setMaxCumulDemand(1);
        setMaxDowntime(70);
        setMaxDuration(20);
        setMaxQty(10);
        setMaxStages(4);
        setMaxWip(50);
        setMinCumulCapacity(5);
        setMinCumulDemand(1);
        setMinDowntime(50);
        setMinDuration(5);
        setMinQty(1);
        setMinStages(4);
        setMinWip(20);
        setNrCumulativeResources(1);
        setNrDisjunctiveResources(8);
        setNrOrders(50);
        setNrProducts(10);
        setProfilePieces(3);
        setResourceModel(ResourceModel.HybridFlowShop);
        setResourceProbability(0.3);
        setSeed(42);
        setStartDateTime(new DateTime(new Date()));
        setTimeResolution(5);
        setWipProbability(1.0);
        applicationDataset.addAbstractDataGeneratorProperty(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public AbstractDataGeneratorProperty(ApplicationDataset applicationDataset,
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
            name);
        setDowntimeProbability(downtimeProbability);
        setDurationFixedFactor(durationFixedFactor);
        setDurationModel(durationModel);
        setEarliestDue(earliestDue);
        setHorizonDays(horizonDays);
        setLabel(label);
        setMaxCumulCapacity(maxCumulCapacity);
        setMaxCumulDemand(maxCumulDemand);
        setMaxDowntime(maxDowntime);
        setMaxDuration(maxDuration);
        setMaxQty(maxQty);
        setMaxStages(maxStages);
        setMaxWip(maxWip);
        setMinCumulCapacity(minCumulCapacity);
        setMinCumulDemand(minCumulDemand);
        setMinDowntime(minDowntime);
        setMinDuration(minDuration);
        setMinQty(minQty);
        setMinStages(minStages);
        setMinWip(minWip);
        setNrCumulativeResources(nrCumulativeResources);
        setNrDisjunctiveResources(nrDisjunctiveResources);
        setNrOrders(nrOrders);
        setNrProducts(nrProducts);
        setProfilePieces(profilePieces);
        setResourceModel(resourceModel);
        setResourceProbability(resourceProbability);
        setSeed(seed);
        setStartDateTime(startDateTime);
        setTimeResolution(timeResolution);
        setWipProbability(wipProbability);
        applicationDataset.addAbstractDataGeneratorProperty(this);
    }

    public AbstractDataGeneratorProperty(AbstractDataGeneratorProperty other){
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
        return getApplicationDataset().removeAbstractDataGeneratorProperty(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute downtimeProbability
 *
 * @return Double
*/

    public Double getDowntimeProbability(){
        return this.downtimeProbability;
    }

/**
 *  get attribute durationFixedFactor
 *
 * @return Integer
*/

    public Integer getDurationFixedFactor(){
        return this.durationFixedFactor;
    }

/**
 *  get attribute durationModel
 *
 * @return DurationModel
*/

    public DurationModel getDurationModel(){
        return this.durationModel;
    }

/**
 *  get attribute earliestDue
 *
 * @return Integer
*/

    public Integer getEarliestDue(){
        return this.earliestDue;
    }

/**
 *  get attribute horizonDays
 *
 * @return Integer
*/

    public Integer getHorizonDays(){
        return this.horizonDays;
    }

/**
 *  get attribute label
 *
 * @return String
*/

    public String getLabel(){
        return this.label;
    }

/**
 *  get attribute maxCumulCapacity
 *
 * @return Integer
*/

    public Integer getMaxCumulCapacity(){
        return this.maxCumulCapacity;
    }

/**
 *  get attribute maxCumulDemand
 *
 * @return Integer
*/

    public Integer getMaxCumulDemand(){
        return this.maxCumulDemand;
    }

/**
 *  get attribute maxDowntime
 *
 * @return Integer
*/

    public Integer getMaxDowntime(){
        return this.maxDowntime;
    }

/**
 *  get attribute maxDuration
 *
 * @return Integer
*/

    public Integer getMaxDuration(){
        return this.maxDuration;
    }

/**
 *  get attribute maxQty
 *
 * @return Integer
*/

    public Integer getMaxQty(){
        return this.maxQty;
    }

/**
 *  get attribute maxStages
 *
 * @return Integer
*/

    public Integer getMaxStages(){
        return this.maxStages;
    }

/**
 *  get attribute maxWip
 *
 * @return Integer
*/

    public Integer getMaxWip(){
        return this.maxWip;
    }

/**
 *  get attribute minCumulCapacity
 *
 * @return Integer
*/

    public Integer getMinCumulCapacity(){
        return this.minCumulCapacity;
    }

/**
 *  get attribute minCumulDemand
 *
 * @return Integer
*/

    public Integer getMinCumulDemand(){
        return this.minCumulDemand;
    }

/**
 *  get attribute minDowntime
 *
 * @return Integer
*/

    public Integer getMinDowntime(){
        return this.minDowntime;
    }

/**
 *  get attribute minDuration
 *
 * @return Integer
*/

    public Integer getMinDuration(){
        return this.minDuration;
    }

/**
 *  get attribute minQty
 *
 * @return Integer
*/

    public Integer getMinQty(){
        return this.minQty;
    }

/**
 *  get attribute minStages
 *
 * @return Integer
*/

    public Integer getMinStages(){
        return this.minStages;
    }

/**
 *  get attribute minWip
 *
 * @return Integer
*/

    public Integer getMinWip(){
        return this.minWip;
    }

/**
 *  get attribute nrCumulativeResources
 *
 * @return Integer
*/

    public Integer getNrCumulativeResources(){
        return this.nrCumulativeResources;
    }

/**
 *  get attribute nrDisjunctiveResources
 *
 * @return Integer
*/

    public Integer getNrDisjunctiveResources(){
        return this.nrDisjunctiveResources;
    }

/**
 *  get attribute nrOrders
 *
 * @return Integer
*/

    public Integer getNrOrders(){
        return this.nrOrders;
    }

/**
 *  get attribute nrProducts
 *
 * @return Integer
*/

    public Integer getNrProducts(){
        return this.nrProducts;
    }

/**
 *  get attribute profilePieces
 *
 * @return Integer
*/

    public Integer getProfilePieces(){
        return this.profilePieces;
    }

/**
 *  get attribute resourceModel
 *
 * @return ResourceModel
*/

    public ResourceModel getResourceModel(){
        return this.resourceModel;
    }

/**
 *  get attribute resourceProbability
 *
 * @return Double
*/

    public Double getResourceProbability(){
        return this.resourceProbability;
    }

/**
 *  get attribute seed
 *
 * @return Integer
*/

    public Integer getSeed(){
        return this.seed;
    }

/**
 *  get attribute startDateTime
 *
 * @return DateTime
*/

    public DateTime getStartDateTime(){
        return this.startDateTime;
    }

/**
 *  get attribute timeResolution
 *
 * @return Integer
*/

    public Integer getTimeResolution(){
        return this.timeResolution;
    }

/**
 *  get attribute wipProbability
 *
 * @return Double
*/

    public Double getWipProbability(){
        return this.wipProbability;
    }

/**
 *  set attribute downtimeProbability, mark dataset as dirty, mark dataset as not valid
@param downtimeProbability Double
 *
*/

    public void setDowntimeProbability(Double downtimeProbability){
        this.downtimeProbability = downtimeProbability;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute durationFixedFactor, mark dataset as dirty, mark dataset as not valid
@param durationFixedFactor Integer
 *
*/

    public void setDurationFixedFactor(Integer durationFixedFactor){
        this.durationFixedFactor = durationFixedFactor;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute durationModel, mark dataset as dirty, mark dataset as not valid
@param durationModel DurationModel
 *
*/

    public void setDurationModel(DurationModel durationModel){
        this.durationModel = durationModel;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute earliestDue, mark dataset as dirty, mark dataset as not valid
@param earliestDue Integer
 *
*/

    public void setEarliestDue(Integer earliestDue){
        this.earliestDue = earliestDue;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute horizonDays, mark dataset as dirty, mark dataset as not valid
@param horizonDays Integer
 *
*/

    public void setHorizonDays(Integer horizonDays){
        this.horizonDays = horizonDays;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute label, mark dataset as dirty, mark dataset as not valid
@param label String
 *
*/

    public void setLabel(String label){
        this.label = label;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute maxCumulCapacity, mark dataset as dirty, mark dataset as not valid
@param maxCumulCapacity Integer
 *
*/

    public void setMaxCumulCapacity(Integer maxCumulCapacity){
        this.maxCumulCapacity = maxCumulCapacity;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute maxCumulDemand, mark dataset as dirty, mark dataset as not valid
@param maxCumulDemand Integer
 *
*/

    public void setMaxCumulDemand(Integer maxCumulDemand){
        this.maxCumulDemand = maxCumulDemand;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute maxDowntime, mark dataset as dirty, mark dataset as not valid
@param maxDowntime Integer
 *
*/

    public void setMaxDowntime(Integer maxDowntime){
        this.maxDowntime = maxDowntime;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute maxDuration, mark dataset as dirty, mark dataset as not valid
@param maxDuration Integer
 *
*/

    public void setMaxDuration(Integer maxDuration){
        this.maxDuration = maxDuration;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute maxQty, mark dataset as dirty, mark dataset as not valid
@param maxQty Integer
 *
*/

    public void setMaxQty(Integer maxQty){
        this.maxQty = maxQty;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute maxStages, mark dataset as dirty, mark dataset as not valid
@param maxStages Integer
 *
*/

    public void setMaxStages(Integer maxStages){
        this.maxStages = maxStages;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute maxWip, mark dataset as dirty, mark dataset as not valid
@param maxWip Integer
 *
*/

    public void setMaxWip(Integer maxWip){
        this.maxWip = maxWip;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute minCumulCapacity, mark dataset as dirty, mark dataset as not valid
@param minCumulCapacity Integer
 *
*/

    public void setMinCumulCapacity(Integer minCumulCapacity){
        this.minCumulCapacity = minCumulCapacity;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute minCumulDemand, mark dataset as dirty, mark dataset as not valid
@param minCumulDemand Integer
 *
*/

    public void setMinCumulDemand(Integer minCumulDemand){
        this.minCumulDemand = minCumulDemand;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute minDowntime, mark dataset as dirty, mark dataset as not valid
@param minDowntime Integer
 *
*/

    public void setMinDowntime(Integer minDowntime){
        this.minDowntime = minDowntime;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute minDuration, mark dataset as dirty, mark dataset as not valid
@param minDuration Integer
 *
*/

    public void setMinDuration(Integer minDuration){
        this.minDuration = minDuration;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute minQty, mark dataset as dirty, mark dataset as not valid
@param minQty Integer
 *
*/

    public void setMinQty(Integer minQty){
        this.minQty = minQty;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute minStages, mark dataset as dirty, mark dataset as not valid
@param minStages Integer
 *
*/

    public void setMinStages(Integer minStages){
        this.minStages = minStages;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute minWip, mark dataset as dirty, mark dataset as not valid
@param minWip Integer
 *
*/

    public void setMinWip(Integer minWip){
        this.minWip = minWip;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute nrCumulativeResources, mark dataset as dirty, mark dataset as not valid
@param nrCumulativeResources Integer
 *
*/

    public void setNrCumulativeResources(Integer nrCumulativeResources){
        this.nrCumulativeResources = nrCumulativeResources;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute nrDisjunctiveResources, mark dataset as dirty, mark dataset as not valid
@param nrDisjunctiveResources Integer
 *
*/

    public void setNrDisjunctiveResources(Integer nrDisjunctiveResources){
        this.nrDisjunctiveResources = nrDisjunctiveResources;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute nrOrders, mark dataset as dirty, mark dataset as not valid
@param nrOrders Integer
 *
*/

    public void setNrOrders(Integer nrOrders){
        this.nrOrders = nrOrders;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute nrProducts, mark dataset as dirty, mark dataset as not valid
@param nrProducts Integer
 *
*/

    public void setNrProducts(Integer nrProducts){
        this.nrProducts = nrProducts;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute profilePieces, mark dataset as dirty, mark dataset as not valid
@param profilePieces Integer
 *
*/

    public void setProfilePieces(Integer profilePieces){
        this.profilePieces = profilePieces;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute resourceModel, mark dataset as dirty, mark dataset as not valid
@param resourceModel ResourceModel
 *
*/

    public void setResourceModel(ResourceModel resourceModel){
        this.resourceModel = resourceModel;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute resourceProbability, mark dataset as dirty, mark dataset as not valid
@param resourceProbability Double
 *
*/

    public void setResourceProbability(Double resourceProbability){
        this.resourceProbability = resourceProbability;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute seed, mark dataset as dirty, mark dataset as not valid
@param seed Integer
 *
*/

    public void setSeed(Integer seed){
        this.seed = seed;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute startDateTime, mark dataset as dirty, mark dataset as not valid
@param startDateTime DateTime
 *
*/

    public void setStartDateTime(DateTime startDateTime){
        this.startDateTime = startDateTime;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute timeResolution, mark dataset as dirty, mark dataset as not valid
@param timeResolution Integer
 *
*/

    public void setTimeResolution(Integer timeResolution){
        this.timeResolution = timeResolution;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute wipProbability, mark dataset as dirty, mark dataset as not valid
@param wipProbability Double
 *
*/

    public void setWipProbability(Double wipProbability){
        this.wipProbability = wipProbability;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute durationFixedFactor, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incDurationFixedFactor(){
        this.durationFixedFactor++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute earliestDue, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incEarliestDue(){
        this.earliestDue++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute horizonDays, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incHorizonDays(){
        this.horizonDays++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute maxCumulCapacity, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMaxCumulCapacity(){
        this.maxCumulCapacity++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute maxCumulDemand, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMaxCumulDemand(){
        this.maxCumulDemand++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute maxDowntime, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMaxDowntime(){
        this.maxDowntime++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute maxDuration, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMaxDuration(){
        this.maxDuration++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute maxQty, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMaxQty(){
        this.maxQty++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute maxStages, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMaxStages(){
        this.maxStages++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute maxWip, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMaxWip(){
        this.maxWip++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute minCumulCapacity, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMinCumulCapacity(){
        this.minCumulCapacity++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute minCumulDemand, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMinCumulDemand(){
        this.minCumulDemand++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute minDowntime, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMinDowntime(){
        this.minDowntime++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute minDuration, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMinDuration(){
        this.minDuration++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute minQty, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMinQty(){
        this.minQty++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute minStages, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMinStages(){
        this.minStages++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute minWip, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incMinWip(){
        this.minWip++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nrCumulativeResources, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNrCumulativeResources(){
        this.nrCumulativeResources++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nrDisjunctiveResources, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNrDisjunctiveResources(){
        this.nrDisjunctiveResources++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nrOrders, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNrOrders(){
        this.nrOrders++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nrProducts, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNrProducts(){
        this.nrProducts++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute profilePieces, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incProfilePieces(){
        this.profilePieces++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute seed, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incSeed(){
        this.seed++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute timeResolution, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incTimeResolution(){
        this.timeResolution++;
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
         out.println("<abstractDataGeneratorProperty "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
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
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDowntimeProbability(){
        return this.getDowntimeProbability().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDurationFixedFactor(){
        return this.getDurationFixedFactor().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDurationModel(){
        return this.getDurationModel().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLEarliestDue(){
        return this.getEarliestDue().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLHorizonDays(){
        return this.getHorizonDays().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLLabel(){
        return this.safeXML(getLabel());
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMaxCumulCapacity(){
        return this.getMaxCumulCapacity().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMaxCumulDemand(){
        return this.getMaxCumulDemand().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMaxDowntime(){
        return this.getMaxDowntime().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMaxDuration(){
        return this.getMaxDuration().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMaxQty(){
        return this.getMaxQty().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMaxStages(){
        return this.getMaxStages().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMaxWip(){
        return this.getMaxWip().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMinCumulCapacity(){
        return this.getMinCumulCapacity().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMinCumulDemand(){
        return this.getMinCumulDemand().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMinDowntime(){
        return this.getMinDowntime().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMinDuration(){
        return this.getMinDuration().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMinQty(){
        return this.getMinQty().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMinStages(){
        return this.getMinStages().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLMinWip(){
        return this.getMinWip().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNrCumulativeResources(){
        return this.getNrCumulativeResources().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNrDisjunctiveResources(){
        return this.getNrDisjunctiveResources().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNrOrders(){
        return this.getNrOrders().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNrProducts(){
        return this.getNrProducts().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLProfilePieces(){
        return this.getProfilePieces().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLResourceModel(){
        return this.getResourceModel().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLResourceProbability(){
        return this.getResourceProbability().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLSeed(){
        return this.getSeed().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLStartDateTime(){
        return this.getStartDateTime().toXML();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLTimeResolution(){
        return this.getTimeResolution().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLWipProbability(){
        return this.getWipProbability().toString();
    }

/**
 * find the same object in another dataset
 * @param a AbstractDataGeneratorProperty item we are looking for
 * @param bList List<AbstractDataGeneratorProperty> list of items in which we are searching
 * @return AbstractDataGeneratorProperty entry of list b which is applicationSame() to a
*/

    public static AbstractDataGeneratorProperty find(AbstractDataGeneratorProperty a, List<AbstractDataGeneratorProperty> bList){
        for(AbstractDataGeneratorProperty b : bList){
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
 * @param name AbstractDataGeneratorProperty name of the object we are looking for
 * @return AbstractDataGeneratorProperty entry of the dataset with the given name; otherwise null
*/

    public static AbstractDataGeneratorProperty findByName(ApplicationDataset base, String name){
        for(AbstractDataGeneratorProperty a:base.getListAbstractDataGeneratorProperty()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        return null;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return AbstractDataGeneratorProperty first entry in the dataset of this type; null if that does not exists
*/

    public static AbstractDataGeneratorProperty findFirst(ApplicationDataset base){
        if (base.getListAbstractDataGeneratorProperty().isEmpty()) {
            return null;
        }
        return base.getListAbstractDataGeneratorProperty().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return AbstractDataGeneratorProperty some entry in the dataset of this type; null if that does not exists
*/

    public static AbstractDataGeneratorProperty findAny(ApplicationDataset base){
        int size=base.getListAbstractDataGeneratorProperty().size();
        if (size > 0) {
             return base.getListAbstractDataGeneratorProperty().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return AbstractDataGeneratorProperty last entry in the dataset of this type; null if that does not exists
*/

    public static AbstractDataGeneratorProperty findLast(ApplicationDataset base){
        int size=base.getListAbstractDataGeneratorProperty().size();
        if (size > 0) {
             return base.getListAbstractDataGeneratorProperty().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b AbstractDataGeneratorProperty compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(AbstractDataGeneratorProperty b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b AbstractDataGeneratorProperty compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(AbstractDataGeneratorProperty b){
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
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","AbstractDataGeneratorProperty",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
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

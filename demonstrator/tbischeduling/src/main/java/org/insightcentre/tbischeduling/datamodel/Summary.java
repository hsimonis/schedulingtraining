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
import org.insightcentre.tbischeduling.datamodel.Summary;
import org.insightcentre.tbischeduling.datamodel.DifferenceType;
import org.insightcentre.tbischeduling.datamodel.WarningType;
import org.insightcentre.tbischeduling.datamodel.SequenceType;
import org.insightcentre.tbischeduling.datamodel.Severity;
import org.insightcentre.tbischeduling.datamodel.ModelType;
import org.insightcentre.tbischeduling.datamodel.SolverBackend;
import org.insightcentre.tbischeduling.datamodel.SolverStatus;
import org.insightcentre.tbischeduling.datamodel.SolutionStatus;
import org.insightcentre.tbischeduling.datamodel.NoWaitType;
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

public  class Summary extends ApplicationObject{
/**
 *  
 *
*/

    public Integer boundFS;

/**
 *  
 *
*/

    public Integer boundNW;

/**
 *  
 *
*/

    public Integer boundPNW;

/**
 *  
 *
*/

    public Integer capacity;

/**
 *  
 *
*/

    public Integer cpFS;

/**
 *  
 *
*/

    public Integer cpNW;

/**
 *  
 *
*/

    public Integer cpPNW;

/**
 *  
 *
*/

    public Integer fsCPOCost;

/**
 *  
 *
*/

    public Integer fsCPSatCost;

/**
 *  
 *
*/

    public Double gapFS;

/**
 *  
 *
*/

    public Double gapNW;

/**
 *  
 *
*/

    public Double gapPNW;

/**
 *  
 *
*/

    public Integer localFS;

/**
 *  
 *
*/

    public Integer localNW;

/**
 *  
 *
*/

    public Integer localPNW;

/**
 *  
 *
*/

    public Integer nr;

/**
 *  
 *
*/

    public Integer nrJobs;

/**
 *  
 *
*/

    public Integer nrStages;

/**
 *  
 *
*/

    public Integer nwCPOCost;

/**
 *  
 *
*/

    public Integer nwCPSatCost;

/**
 *  
 *
*/

    public Integer pnwCPOCost;

/**
 *  
 *
*/

    public Integer pnwCPSatCost;

/**
 *  No-arg constructor for use in TableView
 *
*/

    public Summary(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public Summary(ApplicationDataset applicationDataset){
        super(applicationDataset);
        setBoundFS(0);
        setBoundNW(0);
        setBoundPNW(0);
        setCapacity(0);
        setCpFS(0);
        setCpNW(0);
        setCpPNW(0);
        setFsCPOCost(0);
        setFsCPSatCost(0);
        setGapFS(0.0);
        setGapNW(0.0);
        setGapPNW(0.0);
        setLocalFS(0);
        setLocalNW(0);
        setLocalPNW(0);
        setNr(0);
        setNrJobs(0);
        setNrStages(0);
        setNwCPOCost(0);
        setNwCPSatCost(0);
        setPnwCPOCost(0);
        setPnwCPSatCost(0);
        applicationDataset.addSummary(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public Summary(ApplicationDataset applicationDataset,
            Integer id,
            String name,
            Integer boundFS,
            Integer boundNW,
            Integer boundPNW,
            Integer capacity,
            Integer cpFS,
            Integer cpNW,
            Integer cpPNW,
            Integer fsCPOCost,
            Integer fsCPSatCost,
            Double gapFS,
            Double gapNW,
            Double gapPNW,
            Integer localFS,
            Integer localNW,
            Integer localPNW,
            Integer nr,
            Integer nrJobs,
            Integer nrStages,
            Integer nwCPOCost,
            Integer nwCPSatCost,
            Integer pnwCPOCost,
            Integer pnwCPSatCost){
        super(applicationDataset,
            id,
            name);
        setBoundFS(boundFS);
        setBoundNW(boundNW);
        setBoundPNW(boundPNW);
        setCapacity(capacity);
        setCpFS(cpFS);
        setCpNW(cpNW);
        setCpPNW(cpPNW);
        setFsCPOCost(fsCPOCost);
        setFsCPSatCost(fsCPSatCost);
        setGapFS(gapFS);
        setGapNW(gapNW);
        setGapPNW(gapPNW);
        setLocalFS(localFS);
        setLocalNW(localNW);
        setLocalPNW(localPNW);
        setNr(nr);
        setNrJobs(nrJobs);
        setNrStages(nrStages);
        setNwCPOCost(nwCPOCost);
        setNwCPSatCost(nwCPSatCost);
        setPnwCPOCost(pnwCPOCost);
        setPnwCPSatCost(pnwCPSatCost);
        applicationDataset.addSummary(this);
    }

    public Summary(Summary other){
        this(other.applicationDataset,
            other.id,
            other.name,
            other.boundFS,
            other.boundNW,
            other.boundPNW,
            other.capacity,
            other.cpFS,
            other.cpNW,
            other.cpPNW,
            other.fsCPOCost,
            other.fsCPSatCost,
            other.gapFS,
            other.gapNW,
            other.gapPNW,
            other.localFS,
            other.localNW,
            other.localPNW,
            other.nr,
            other.nrJobs,
            other.nrStages,
            other.nwCPOCost,
            other.nwCPSatCost,
            other.pnwCPOCost,
            other.pnwCPSatCost);
    }

/**
 *  remove this object from dataset, this may remove
 *  other objects of other classes, if they rely on this.
 *  Will remove item from list of this type, but also all parent types
 * @return Boolean true if item was removed without problems
*/

    public Boolean remove(){
        return getApplicationDataset().removeSummary(this) && getApplicationDataset().removeApplicationObject(this);
    }

/**
 *  get attribute boundFS
 *
 * @return Integer
*/

    public Integer getBoundFS(){
        return this.boundFS;
    }

/**
 *  get attribute boundNW
 *
 * @return Integer
*/

    public Integer getBoundNW(){
        return this.boundNW;
    }

/**
 *  get attribute boundPNW
 *
 * @return Integer
*/

    public Integer getBoundPNW(){
        return this.boundPNW;
    }

/**
 *  get attribute capacity
 *
 * @return Integer
*/

    public Integer getCapacity(){
        return this.capacity;
    }

/**
 *  get attribute cpFS
 *
 * @return Integer
*/

    public Integer getCpFS(){
        return this.cpFS;
    }

/**
 *  get attribute cpNW
 *
 * @return Integer
*/

    public Integer getCpNW(){
        return this.cpNW;
    }

/**
 *  get attribute cpPNW
 *
 * @return Integer
*/

    public Integer getCpPNW(){
        return this.cpPNW;
    }

/**
 *  get attribute fsCPOCost
 *
 * @return Integer
*/

    public Integer getFsCPOCost(){
        return this.fsCPOCost;
    }

/**
 *  get attribute fsCPSatCost
 *
 * @return Integer
*/

    public Integer getFsCPSatCost(){
        return this.fsCPSatCost;
    }

/**
 *  get attribute gapFS
 *
 * @return Double
*/

    public Double getGapFS(){
        return this.gapFS;
    }

/**
 *  get attribute gapNW
 *
 * @return Double
*/

    public Double getGapNW(){
        return this.gapNW;
    }

/**
 *  get attribute gapPNW
 *
 * @return Double
*/

    public Double getGapPNW(){
        return this.gapPNW;
    }

/**
 *  get attribute localFS
 *
 * @return Integer
*/

    public Integer getLocalFS(){
        return this.localFS;
    }

/**
 *  get attribute localNW
 *
 * @return Integer
*/

    public Integer getLocalNW(){
        return this.localNW;
    }

/**
 *  get attribute localPNW
 *
 * @return Integer
*/

    public Integer getLocalPNW(){
        return this.localPNW;
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
 *  get attribute nrJobs
 *
 * @return Integer
*/

    public Integer getNrJobs(){
        return this.nrJobs;
    }

/**
 *  get attribute nrStages
 *
 * @return Integer
*/

    public Integer getNrStages(){
        return this.nrStages;
    }

/**
 *  get attribute nwCPOCost
 *
 * @return Integer
*/

    public Integer getNwCPOCost(){
        return this.nwCPOCost;
    }

/**
 *  get attribute nwCPSatCost
 *
 * @return Integer
*/

    public Integer getNwCPSatCost(){
        return this.nwCPSatCost;
    }

/**
 *  get attribute pnwCPOCost
 *
 * @return Integer
*/

    public Integer getPnwCPOCost(){
        return this.pnwCPOCost;
    }

/**
 *  get attribute pnwCPSatCost
 *
 * @return Integer
*/

    public Integer getPnwCPSatCost(){
        return this.pnwCPSatCost;
    }

/**
 *  set attribute boundFS, mark dataset as dirty, mark dataset as not valid
@param boundFS Integer
 *
*/

    public void setBoundFS(Integer boundFS){
        this.boundFS = boundFS;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute boundNW, mark dataset as dirty, mark dataset as not valid
@param boundNW Integer
 *
*/

    public void setBoundNW(Integer boundNW){
        this.boundNW = boundNW;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute boundPNW, mark dataset as dirty, mark dataset as not valid
@param boundPNW Integer
 *
*/

    public void setBoundPNW(Integer boundPNW){
        this.boundPNW = boundPNW;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute capacity, mark dataset as dirty, mark dataset as not valid
@param capacity Integer
 *
*/

    public void setCapacity(Integer capacity){
        this.capacity = capacity;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute cpFS, mark dataset as dirty, mark dataset as not valid
@param cpFS Integer
 *
*/

    public void setCpFS(Integer cpFS){
        this.cpFS = cpFS;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute cpNW, mark dataset as dirty, mark dataset as not valid
@param cpNW Integer
 *
*/

    public void setCpNW(Integer cpNW){
        this.cpNW = cpNW;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute cpPNW, mark dataset as dirty, mark dataset as not valid
@param cpPNW Integer
 *
*/

    public void setCpPNW(Integer cpPNW){
        this.cpPNW = cpPNW;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute fsCPOCost, mark dataset as dirty, mark dataset as not valid
@param fsCPOCost Integer
 *
*/

    public void setFsCPOCost(Integer fsCPOCost){
        this.fsCPOCost = fsCPOCost;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute fsCPSatCost, mark dataset as dirty, mark dataset as not valid
@param fsCPSatCost Integer
 *
*/

    public void setFsCPSatCost(Integer fsCPSatCost){
        this.fsCPSatCost = fsCPSatCost;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute gapFS, mark dataset as dirty, mark dataset as not valid
@param gapFS Double
 *
*/

    public void setGapFS(Double gapFS){
        this.gapFS = gapFS;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute gapNW, mark dataset as dirty, mark dataset as not valid
@param gapNW Double
 *
*/

    public void setGapNW(Double gapNW){
        this.gapNW = gapNW;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute gapPNW, mark dataset as dirty, mark dataset as not valid
@param gapPNW Double
 *
*/

    public void setGapPNW(Double gapPNW){
        this.gapPNW = gapPNW;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute localFS, mark dataset as dirty, mark dataset as not valid
@param localFS Integer
 *
*/

    public void setLocalFS(Integer localFS){
        this.localFS = localFS;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute localNW, mark dataset as dirty, mark dataset as not valid
@param localNW Integer
 *
*/

    public void setLocalNW(Integer localNW){
        this.localNW = localNW;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute localPNW, mark dataset as dirty, mark dataset as not valid
@param localPNW Integer
 *
*/

    public void setLocalPNW(Integer localPNW){
        this.localPNW = localPNW;
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
 *  set attribute nrJobs, mark dataset as dirty, mark dataset as not valid
@param nrJobs Integer
 *
*/

    public void setNrJobs(Integer nrJobs){
        this.nrJobs = nrJobs;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute nrStages, mark dataset as dirty, mark dataset as not valid
@param nrStages Integer
 *
*/

    public void setNrStages(Integer nrStages){
        this.nrStages = nrStages;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute nwCPOCost, mark dataset as dirty, mark dataset as not valid
@param nwCPOCost Integer
 *
*/

    public void setNwCPOCost(Integer nwCPOCost){
        this.nwCPOCost = nwCPOCost;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute nwCPSatCost, mark dataset as dirty, mark dataset as not valid
@param nwCPSatCost Integer
 *
*/

    public void setNwCPSatCost(Integer nwCPSatCost){
        this.nwCPSatCost = nwCPSatCost;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute pnwCPOCost, mark dataset as dirty, mark dataset as not valid
@param pnwCPOCost Integer
 *
*/

    public void setPnwCPOCost(Integer pnwCPOCost){
        this.pnwCPOCost = pnwCPOCost;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute pnwCPSatCost, mark dataset as dirty, mark dataset as not valid
@param pnwCPSatCost Integer
 *
*/

    public void setPnwCPSatCost(Integer pnwCPSatCost){
        this.pnwCPSatCost = pnwCPSatCost;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute boundFS, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incBoundFS(){
        this.boundFS++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute boundNW, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incBoundNW(){
        this.boundNW++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute boundPNW, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incBoundPNW(){
        this.boundPNW++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute capacity, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incCapacity(){
        this.capacity++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute cpFS, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incCpFS(){
        this.cpFS++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute cpNW, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incCpNW(){
        this.cpNW++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute cpPNW, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incCpPNW(){
        this.cpPNW++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute fsCPOCost, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incFsCPOCost(){
        this.fsCPOCost++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute fsCPSatCost, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incFsCPSatCost(){
        this.fsCPSatCost++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute localFS, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incLocalFS(){
        this.localFS++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute localNW, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incLocalNW(){
        this.localNW++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute localPNW, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incLocalPNW(){
        this.localPNW++;
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
 *  inc attribute nrJobs, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNrJobs(){
        this.nrJobs++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nrStages, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNrStages(){
        this.nrStages++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nwCPOCost, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNwCPOCost(){
        this.nwCPOCost++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute nwCPSatCost, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incNwCPSatCost(){
        this.nwCPSatCost++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute pnwCPOCost, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incPnwCPOCost(){
        this.pnwCPOCost++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  inc attribute pnwCPSatCost, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incPnwCPSatCost(){
        this.pnwCPSatCost++;
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
        return ""+ " " +getId()+ " " +getName()+ " " +getBoundFS()+ " " +getBoundNW()+ " " +getBoundPNW()+ " " +getCapacity()+ " " +getCpFS()+ " " +getCpNW()+ " " +getCpPNW()+ " " +getFsCPOCost()+ " " +getFsCPSatCost()+ " " +getGapFS()+ " " +getGapNW()+ " " +getGapPNW()+ " " +getLocalFS()+ " " +getLocalNW()+ " " +getLocalPNW()+ " " +getNr()+ " " +getNrJobs()+ " " +getNrStages()+ " " +getNwCPOCost()+ " " +getNwCPSatCost()+ " " +getPnwCPOCost()+ " " +getPnwCPSatCost();
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
         out.println("<summary "+ " applicationDataset=\""+toXMLApplicationDataset()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " boundFS=\""+toXMLBoundFS()+"\""+
            " boundNW=\""+toXMLBoundNW()+"\""+
            " boundPNW=\""+toXMLBoundPNW()+"\""+
            " capacity=\""+toXMLCapacity()+"\""+
            " cpFS=\""+toXMLCpFS()+"\""+
            " cpNW=\""+toXMLCpNW()+"\""+
            " cpPNW=\""+toXMLCpPNW()+"\""+
            " fsCPOCost=\""+toXMLFsCPOCost()+"\""+
            " fsCPSatCost=\""+toXMLFsCPSatCost()+"\""+
            " gapFS=\""+toXMLGapFS()+"\""+
            " gapNW=\""+toXMLGapNW()+"\""+
            " gapPNW=\""+toXMLGapPNW()+"\""+
            " localFS=\""+toXMLLocalFS()+"\""+
            " localNW=\""+toXMLLocalNW()+"\""+
            " localPNW=\""+toXMLLocalPNW()+"\""+
            " nr=\""+toXMLNr()+"\""+
            " nrJobs=\""+toXMLNrJobs()+"\""+
            " nrStages=\""+toXMLNrStages()+"\""+
            " nwCPOCost=\""+toXMLNwCPOCost()+"\""+
            " nwCPSatCost=\""+toXMLNwCPSatCost()+"\""+
            " pnwCPOCost=\""+toXMLPnwCPOCost()+"\""+
            " pnwCPSatCost=\""+toXMLPnwCPSatCost()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLBoundFS(){
        return this.getBoundFS().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLBoundNW(){
        return this.getBoundNW().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLBoundPNW(){
        return this.getBoundPNW().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLCapacity(){
        return this.getCapacity().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLCpFS(){
        return this.getCpFS().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLCpNW(){
        return this.getCpNW().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLCpPNW(){
        return this.getCpPNW().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLFsCPOCost(){
        return this.getFsCPOCost().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLFsCPSatCost(){
        return this.getFsCPSatCost().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLGapFS(){
        return this.getGapFS().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLGapNW(){
        return this.getGapNW().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLGapPNW(){
        return this.getGapPNW().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLLocalFS(){
        return this.getLocalFS().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLLocalNW(){
        return this.getLocalNW().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLLocalPNW(){
        return this.getLocalPNW().toString();
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

    String toXMLNrJobs(){
        return this.getNrJobs().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNrStages(){
        return this.getNrStages().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNwCPOCost(){
        return this.getNwCPOCost().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLNwCPSatCost(){
        return this.getNwCPSatCost().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLPnwCPOCost(){
        return this.getPnwCPOCost().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLPnwCPSatCost(){
        return this.getPnwCPSatCost().toString();
    }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>Summary</th>"+"<th>Name</th>"+"<th>Nr</th>"+"<th>NrJobs</th>"+"<th>NrStages</th>"+"<th>Capacity</th>"+"<th>LocalFS</th>"+"<th>LocalPNW</th>"+"<th>LocalNW</th>"+"<th>CpFS</th>"+"<th>CpPNW</th>"+"<th>CpNW</th>"+"<th>BoundFS</th>"+"<th>BoundPNW</th>"+"<th>BoundNW</th>"+"<th>GapFS</th>"+"<th>GapPNW</th>"+"<th>GapNW</th>"+"<th>FsCPOCost</th>"+"<th>FsCPSatCost</th>"+"<th>PnwCPOCost</th>"+"<th>PnwCPSatCost</th>"+"<th>NwCPOCost</th>"+"<th>NwCPSatCost</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getNr()+"</td>"+ " " +"<td>"+getNrJobs()+"</td>"+ " " +"<td>"+getNrStages()+"</td>"+ " " +"<td>"+getCapacity()+"</td>"+ " " +"<td>"+getLocalFS()+"</td>"+ " " +"<td>"+getLocalPNW()+"</td>"+ " " +"<td>"+getLocalNW()+"</td>"+ " " +"<td>"+getCpFS()+"</td>"+ " " +"<td>"+getCpPNW()+"</td>"+ " " +"<td>"+getCpNW()+"</td>"+ " " +"<td>"+getBoundFS()+"</td>"+ " " +"<td>"+getBoundPNW()+"</td>"+ " " +"<td>"+getBoundNW()+"</td>"+ " " +"<td>"+getGapFS()+"</td>"+ " " +"<td>"+getGapPNW()+"</td>"+ " " +"<td>"+getGapNW()+"</td>"+ " " +"<td>"+getFsCPOCost()+"</td>"+ " " +"<td>"+getFsCPSatCost()+"</td>"+ " " +"<td>"+getPnwCPOCost()+"</td>"+ " " +"<td>"+getPnwCPSatCost()+"</td>"+ " " +"<td>"+getNwCPOCost()+"</td>"+ " " +"<td>"+getNwCPSatCost()+"</td>"+"</tr>";
    }

/**
 * find the same object in another dataset
 * @param a Summary item we are looking for
 * @param bList List<Summary> list of items in which we are searching
 * @return Summary entry of list b which is applicationSame() to a
*/

    public static Summary find(Summary a, List<Summary> bList){
        for(Summary b : bList){
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
 * @param name Summary name of the object we are looking for
 * @return Summary entry of the dataset with the given name; otherwise null
*/

    public static Summary findByName(ApplicationDataset base, String name){
        for(Summary a:base.getListSummary()) {
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
 * @param name Summary name of the object we are looking for
 * @return Summary entry of the dataset with the given name
*/

    public static Summary findOrCreate(ApplicationDataset base, String name){
        if (name.equals("null")){ return null;}
        for(Summary a:base.getListSummary()) {
            if (a.getName().equals(name)){
                return a;
            }
        }
        Summary res = new Summary(base);
        res.setName(name);
        return res;
    }

/**
 * find the first entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Summary first entry in the dataset of this type; null if that does not exists
*/

    public static Summary findFirst(ApplicationDataset base){
        if (base.getListSummary().isEmpty()) {
            return null;
        }
        return base.getListSummary().get(0);
    }

/**
 * find some entry entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Summary some entry in the dataset of this type; null if that does not exists
*/

    public static Summary findAny(ApplicationDataset base){
        int size=base.getListSummary().size();
        if (size > 0) {
             return base.getListSummary().get(new Random().nextInt(size));
        }
        return null;
    }

/**
 * find the last entry in the dataset of that type
 * @param base dataset in which we are searching
 * @return Summary last entry in the dataset of this type; null if that does not exists
*/

    public static Summary findLast(ApplicationDataset base){
        int size=base.getListSummary().size();
        if (size > 0) {
             return base.getListSummary().get(size-1);
        }
        return null;
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b Summary compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(Summary b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b Summary compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(Summary b){
      if(!this.getBoundFS().equals(b.getBoundFS())){
         System.out.println("BoundFS");
        }
      if(!this.getBoundNW().equals(b.getBoundNW())){
         System.out.println("BoundNW");
        }
      if(!this.getBoundPNW().equals(b.getBoundPNW())){
         System.out.println("BoundPNW");
        }
      if(!this.getCapacity().equals(b.getCapacity())){
         System.out.println("Capacity");
        }
      if(!this.getCpFS().equals(b.getCpFS())){
         System.out.println("CpFS");
        }
      if(!this.getCpNW().equals(b.getCpNW())){
         System.out.println("CpNW");
        }
      if(!this.getCpPNW().equals(b.getCpPNW())){
         System.out.println("CpPNW");
        }
      if(!this.getFsCPOCost().equals(b.getFsCPOCost())){
         System.out.println("FsCPOCost");
        }
      if(!this.getFsCPSatCost().equals(b.getFsCPSatCost())){
         System.out.println("FsCPSatCost");
        }
      if(!this.getGapFS().equals(b.getGapFS())){
         System.out.println("GapFS");
        }
      if(!this.getGapNW().equals(b.getGapNW())){
         System.out.println("GapNW");
        }
      if(!this.getGapPNW().equals(b.getGapPNW())){
         System.out.println("GapPNW");
        }
      if(!this.getLocalFS().equals(b.getLocalFS())){
         System.out.println("LocalFS");
        }
      if(!this.getLocalNW().equals(b.getLocalNW())){
         System.out.println("LocalNW");
        }
      if(!this.getLocalPNW().equals(b.getLocalPNW())){
         System.out.println("LocalPNW");
        }
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
      if(!this.getNr().equals(b.getNr())){
         System.out.println("Nr");
        }
      if(!this.getNrJobs().equals(b.getNrJobs())){
         System.out.println("NrJobs");
        }
      if(!this.getNrStages().equals(b.getNrStages())){
         System.out.println("NrStages");
        }
      if(!this.getNwCPOCost().equals(b.getNwCPOCost())){
         System.out.println("NwCPOCost");
        }
      if(!this.getNwCPSatCost().equals(b.getNwCPSatCost())){
         System.out.println("NwCPSatCost");
        }
      if(!this.getPnwCPOCost().equals(b.getPnwCPOCost())){
         System.out.println("PnwCPOCost");
        }
      if(!this.getPnwCPSatCost().equals(b.getPnwCPSatCost())){
         System.out.println("PnwCPSatCost");
        }
        return  this.getBoundFS().equals(b.getBoundFS()) &&
          this.getBoundNW().equals(b.getBoundNW()) &&
          this.getBoundPNW().equals(b.getBoundPNW()) &&
          this.getCapacity().equals(b.getCapacity()) &&
          this.getCpFS().equals(b.getCpFS()) &&
          this.getCpNW().equals(b.getCpNW()) &&
          this.getCpPNW().equals(b.getCpPNW()) &&
          this.getFsCPOCost().equals(b.getFsCPOCost()) &&
          this.getFsCPSatCost().equals(b.getFsCPSatCost()) &&
          this.getGapFS().equals(b.getGapFS()) &&
          this.getGapNW().equals(b.getGapNW()) &&
          this.getGapPNW().equals(b.getGapPNW()) &&
          this.getLocalFS().equals(b.getLocalFS()) &&
          this.getLocalNW().equals(b.getLocalNW()) &&
          this.getLocalPNW().equals(b.getLocalPNW()) &&
          this.getName().equals(b.getName()) &&
          this.getNr().equals(b.getNr()) &&
          this.getNrJobs().equals(b.getNrJobs()) &&
          this.getNrStages().equals(b.getNrStages()) &&
          this.getNwCPOCost().equals(b.getNwCPOCost()) &&
          this.getNwCPSatCost().equals(b.getNwCPSatCost()) &&
          this.getPnwCPOCost().equals(b.getPnwCPOCost()) &&
          this.getPnwCPSatCost().equals(b.getPnwCPSatCost());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getApplicationDataset() == null){
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"applicationDataset","Summary",(getApplicationDataset()==null?"null":getApplicationDataset().toString()),"",WarningType.NOTNULL);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class Summary
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

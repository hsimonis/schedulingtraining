package org.insightcentre.tbischeduling.generatedsolver;

import org.insightcentre.tbischeduling.datamodel.*;
import framework.solver.AbstractSolver;
import framework.types.DateOnly;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;
import static org.insightcentre.tbischeduling.logging.LogShortcut.warning;

public class GenerateDataSolver extends DefaultSolver{
// solver internal variables
    protected long startSystem = 0;
// solver parameters
    protected String label="test";
    protected String startDate="1-3-2023";
    protected String startTime="08:00";
    protected String resourceModel="HybridFlowShop";
    protected int nrProducts=5;
    protected int minStages=4;
    protected int maxStages=4;
    protected int nrDisjunctiveResources=8;
    protected double resourceProbability=0.3;
    protected String durationModel="Random";
    protected int minDuration=5;
    protected int maxDuration=20;
    protected int durationFixedFactor=0;
    protected int nrCumulativeResources=1;
    protected int minCumulDemand=1;
    protected int maxCumulDemand=5;
    protected int profilePieces=3;
    protected int minCumulCapacity=5;
    protected int maxCumulCapacity=10;
    protected int nrOrders=20;
    protected int minQty=1;
    protected int maxQty=10;
    protected double wipProbability=1.0;
    protected int minWip=20;
    protected int maxWip=50;
    protected double downtimeProbability=0.0;
    protected int minDowntime=50;
    protected int maxDowntime=70;
    protected int earliestDue=20;
    protected int horizonDays=20;
    protected int timeResolution=5;
    protected int seed=42;

    public GenerateDataSolver(Scenario base){
        super(base,new String[] {});
    }
    public GenerateDataSolver(Scenario base,String label,String startDate,String startTime,String resourceModel,int nrProducts,int minStages,int maxStages,int nrDisjunctiveResources,double resourceProbability,String durationModel,int minDuration,int maxDuration,int durationFixedFactor,int nrCumulativeResources,int minCumulDemand,int maxCumulDemand,int profilePieces,int minCumulCapacity,int maxCumulCapacity,int nrOrders,int minQty,int maxQty,double wipProbability,int minWip,int maxWip,double downtimeProbability,int minDowntime,int maxDowntime,int earliestDue,int horizonDays,int timeResolution,int seed){
        super(base,new String[] {});
        this.label=label;
        this.startDate=startDate;
        this.startTime=startTime;
        this.resourceModel=resourceModel;
        this.nrProducts=nrProducts;
        this.minStages=minStages;
        this.maxStages=maxStages;
        this.nrDisjunctiveResources=nrDisjunctiveResources;
        this.resourceProbability=resourceProbability;
        this.durationModel=durationModel;
        this.minDuration=minDuration;
        this.maxDuration=maxDuration;
        this.durationFixedFactor=durationFixedFactor;
        this.nrCumulativeResources=nrCumulativeResources;
        this.minCumulDemand=minCumulDemand;
        this.maxCumulDemand=maxCumulDemand;
        this.profilePieces=profilePieces;
        this.minCumulCapacity=minCumulCapacity;
        this.maxCumulCapacity=maxCumulCapacity;
        this.nrOrders=nrOrders;
        this.minQty=minQty;
        this.maxQty=maxQty;
        this.wipProbability=wipProbability;
        this.minWip=minWip;
        this.maxWip=maxWip;
        this.downtimeProbability=downtimeProbability;
        this.minDowntime=minDowntime;
        this.maxDowntime=maxDowntime;
        this.earliestDue=earliestDue;
        this.horizonDays=horizonDays;
        this.timeResolution=timeResolution;
        this.seed=seed;
    }

public String getLabel(){
 return label;
}

public String getStartDate(){
 return startDate;
}

public String getStartTime(){
 return startTime;
}

public String getResourceModel(){
 return resourceModel;
}

public int getNrProducts(){
 return nrProducts;
}

public int getMinStages(){
 return minStages;
}

public int getMaxStages(){
 return maxStages;
}

public int getNrDisjunctiveResources(){
 return nrDisjunctiveResources;
}

public double getResourceProbability(){
 return resourceProbability;
}

public String getDurationModel(){
 return durationModel;
}

public int getMinDuration(){
 return minDuration;
}

public int getMaxDuration(){
 return maxDuration;
}

public int getDurationFixedFactor(){
 return durationFixedFactor;
}

public int getNrCumulativeResources(){
 return nrCumulativeResources;
}

public int getMinCumulDemand(){
 return minCumulDemand;
}

public int getMaxCumulDemand(){
 return maxCumulDemand;
}

public int getProfilePieces(){
 return profilePieces;
}

public int getMinCumulCapacity(){
 return minCumulCapacity;
}

public int getMaxCumulCapacity(){
 return maxCumulCapacity;
}

public int getNrOrders(){
 return nrOrders;
}

public int getMinQty(){
 return minQty;
}

public int getMaxQty(){
 return maxQty;
}

public double getWipProbability(){
 return wipProbability;
}

public int getMinWip(){
 return minWip;
}

public int getMaxWip(){
 return maxWip;
}

public double getDowntimeProbability(){
 return downtimeProbability;
}

public int getMinDowntime(){
 return minDowntime;
}

public int getMaxDowntime(){
 return maxDowntime;
}

public int getEarliestDue(){
 return earliestDue;
}

public int getHorizonDays(){
 return horizonDays;
}

public int getTimeResolution(){
 return timeResolution;
}

public int getSeed(){
 return seed;
}

public GenerateDataSolver setLabel(String v){
 label = v;
 return this;
}

public GenerateDataSolver setStartDate(String v){
 startDate = v;
 return this;
}

public GenerateDataSolver setStartTime(String v){
 startTime = v;
 return this;
}

public GenerateDataSolver setResourceModel(String v){
 resourceModel = v;
 return this;
}

public GenerateDataSolver setNrProducts(int v){
 nrProducts = v;
 return this;
}

public GenerateDataSolver setMinStages(int v){
 minStages = v;
 return this;
}

public GenerateDataSolver setMaxStages(int v){
 maxStages = v;
 return this;
}

public GenerateDataSolver setNrDisjunctiveResources(int v){
 nrDisjunctiveResources = v;
 return this;
}

public GenerateDataSolver setResourceProbability(double v){
 resourceProbability = v;
 return this;
}

public GenerateDataSolver setDurationModel(String v){
 durationModel = v;
 return this;
}

public GenerateDataSolver setMinDuration(int v){
 minDuration = v;
 return this;
}

public GenerateDataSolver setMaxDuration(int v){
 maxDuration = v;
 return this;
}

public GenerateDataSolver setDurationFixedFactor(int v){
 durationFixedFactor = v;
 return this;
}

public GenerateDataSolver setNrCumulativeResources(int v){
 nrCumulativeResources = v;
 return this;
}

public GenerateDataSolver setMinCumulDemand(int v){
 minCumulDemand = v;
 return this;
}

public GenerateDataSolver setMaxCumulDemand(int v){
 maxCumulDemand = v;
 return this;
}

public GenerateDataSolver setProfilePieces(int v){
 profilePieces = v;
 return this;
}

public GenerateDataSolver setMinCumulCapacity(int v){
 minCumulCapacity = v;
 return this;
}

public GenerateDataSolver setMaxCumulCapacity(int v){
 maxCumulCapacity = v;
 return this;
}

public GenerateDataSolver setNrOrders(int v){
 nrOrders = v;
 return this;
}

public GenerateDataSolver setMinQty(int v){
 minQty = v;
 return this;
}

public GenerateDataSolver setMaxQty(int v){
 maxQty = v;
 return this;
}

public GenerateDataSolver setWipProbability(double v){
 wipProbability = v;
 return this;
}

public GenerateDataSolver setMinWip(int v){
 minWip = v;
 return this;
}

public GenerateDataSolver setMaxWip(int v){
 maxWip = v;
 return this;
}

public GenerateDataSolver setDowntimeProbability(double v){
 downtimeProbability = v;
 return this;
}

public GenerateDataSolver setMinDowntime(int v){
 minDowntime = v;
 return this;
}

public GenerateDataSolver setMaxDowntime(int v){
 maxDowntime = v;
 return this;
}

public GenerateDataSolver setEarliestDue(int v){
 earliestDue = v;
 return this;
}

public GenerateDataSolver setHorizonDays(int v){
 horizonDays = v;
 return this;
}

public GenerateDataSolver setTimeResolution(int v){
 timeResolution = v;
 return this;
}

public GenerateDataSolver setSeed(int v){
 seed = v;
 return this;
}

public void stop() {
}
public boolean solve() {
    boolean isfeas = true;
    startSystem = System.currentTimeMillis();
// reset objects
// create indices
    long endSystem = System.currentTimeMillis();
    info("Run Time: "+(endSystem - startSystem) / 1000.0);
// remove indices
    return isfeas;
}


}

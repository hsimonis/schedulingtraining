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
    protected String name="test";
    protected int nrProducts=5;
    protected int nrStages=3;
    protected int nrDisjunctiveResources=6;
    protected double resourceProbability=0.3;
    protected int nrCumulativeResources=1;
    protected int minCumulDemand=1;
    protected int maxCumulDemand=5;
    protected int cumulCapacity=10;
    protected int nrOrders=20;
    protected int minQty=1;
    protected int maxQty=10;
    protected int earliestDue=20;
    protected int horizonDays=10;
    protected int timeResolution=5;
    protected int seed=42;

    public GenerateDataSolver(Scenario base){
        super(base,new String[] {});
    }
    public GenerateDataSolver(Scenario base,String name,int nrProducts,int nrStages,int nrDisjunctiveResources,double resourceProbability,int nrCumulativeResources,int minCumulDemand,int maxCumulDemand,int cumulCapacity,int nrOrders,int minQty,int maxQty,int earliestDue,int horizonDays,int timeResolution,int seed){
        super(base,new String[] {});
        this.name=name;
        this.nrProducts=nrProducts;
        this.nrStages=nrStages;
        this.nrDisjunctiveResources=nrDisjunctiveResources;
        this.resourceProbability=resourceProbability;
        this.nrCumulativeResources=nrCumulativeResources;
        this.minCumulDemand=minCumulDemand;
        this.maxCumulDemand=maxCumulDemand;
        this.cumulCapacity=cumulCapacity;
        this.nrOrders=nrOrders;
        this.minQty=minQty;
        this.maxQty=maxQty;
        this.earliestDue=earliestDue;
        this.horizonDays=horizonDays;
        this.timeResolution=timeResolution;
        this.seed=seed;
    }

public String getName(){
 return name;
}

public int getNrProducts(){
 return nrProducts;
}

public int getNrStages(){
 return nrStages;
}

public int getNrDisjunctiveResources(){
 return nrDisjunctiveResources;
}

public double getResourceProbability(){
 return resourceProbability;
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

public int getCumulCapacity(){
 return cumulCapacity;
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

public GenerateDataSolver setName(String v){
 name = v;
 return this;
}

public GenerateDataSolver setNrProducts(int v){
 nrProducts = v;
 return this;
}

public GenerateDataSolver setNrStages(int v){
 nrStages = v;
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

public GenerateDataSolver setCumulCapacity(int v){
 cumulCapacity = v;
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

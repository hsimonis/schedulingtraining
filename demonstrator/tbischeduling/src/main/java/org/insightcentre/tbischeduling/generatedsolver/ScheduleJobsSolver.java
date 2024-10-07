package org.insightcentre.tbischeduling.generatedsolver;

import org.insightcentre.tbischeduling.datamodel.*;
import framework.solver.AbstractSolver;
import framework.types.DateOnly;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;
import static org.insightcentre.tbischeduling.logging.LogShortcut.warning;

public class ScheduleJobsSolver extends DefaultSolver{
// solver internal variables
    protected long startSystem = 0;
// solver parameters
    protected String label="test";
    protected String description="";
    protected String startDate="1-3-2023";
    protected String startTime="08:00";
    protected boolean enforceReleaseDate=true;
    protected boolean enforceDueDate=false;
    protected boolean enforceCumulative=true;
    protected boolean enforceWip=true;
    protected boolean enforceDowntime=true;
    protected String modelType="CPO";
    protected String solverBackend="None";
    protected String objectiveType="Makespan";
    protected int weightMakespan=1;
    protected int weightFlowtime=1;
    protected int weightLateness=1;
    protected int weightEarliness=1;
    protected int timeout=60;
    protected int nrThreads=2;
    protected int seed=1;
    protected boolean removeSolution=false;
    protected boolean produceReport=true;
    protected boolean producePDF=true;

    public ScheduleJobsSolver(Scenario base){
        super(base,new String[] {});
    }
    public ScheduleJobsSolver(Scenario base,String label,String description,String startDate,String startTime,boolean enforceReleaseDate,boolean enforceDueDate,boolean enforceCumulative,boolean enforceWip,boolean enforceDowntime,String modelType,String solverBackend,String objectiveType,int weightMakespan,int weightFlowtime,int weightLateness,int weightEarliness,int timeout,int nrThreads,int seed,boolean removeSolution,boolean produceReport,boolean producePDF){
        super(base,new String[] {});
        this.label=label;
        this.description=description;
        this.startDate=startDate;
        this.startTime=startTime;
        this.enforceReleaseDate=enforceReleaseDate;
        this.enforceDueDate=enforceDueDate;
        this.enforceCumulative=enforceCumulative;
        this.enforceWip=enforceWip;
        this.enforceDowntime=enforceDowntime;
        this.modelType=modelType;
        this.solverBackend=solverBackend;
        this.objectiveType=objectiveType;
        this.weightMakespan=weightMakespan;
        this.weightFlowtime=weightFlowtime;
        this.weightLateness=weightLateness;
        this.weightEarliness=weightEarliness;
        this.timeout=timeout;
        this.nrThreads=nrThreads;
        this.seed=seed;
        this.removeSolution=removeSolution;
        this.produceReport=produceReport;
        this.producePDF=producePDF;
    }

public String getLabel(){
 return label;
}

public String getDescription(){
 return description;
}

public String getStartDate(){
 return startDate;
}

public String getStartTime(){
 return startTime;
}

public boolean getEnforceReleaseDate(){
 return enforceReleaseDate;
}

public boolean getEnforceDueDate(){
 return enforceDueDate;
}

public boolean getEnforceCumulative(){
 return enforceCumulative;
}

public boolean getEnforceWip(){
 return enforceWip;
}

public boolean getEnforceDowntime(){
 return enforceDowntime;
}

public String getModelType(){
 return modelType;
}

public String getSolverBackend(){
 return solverBackend;
}

public String getObjectiveType(){
 return objectiveType;
}

public int getWeightMakespan(){
 return weightMakespan;
}

public int getWeightFlowtime(){
 return weightFlowtime;
}

public int getWeightLateness(){
 return weightLateness;
}

public int getWeightEarliness(){
 return weightEarliness;
}

public int getTimeout(){
 return timeout;
}

public int getNrThreads(){
 return nrThreads;
}

public int getSeed(){
 return seed;
}

public boolean getRemoveSolution(){
 return removeSolution;
}

public boolean getProduceReport(){
 return produceReport;
}

public boolean getProducePDF(){
 return producePDF;
}

public ScheduleJobsSolver setLabel(String v){
 label = v;
 return this;
}

public ScheduleJobsSolver setDescription(String v){
 description = v;
 return this;
}

public ScheduleJobsSolver setStartDate(String v){
 startDate = v;
 return this;
}

public ScheduleJobsSolver setStartTime(String v){
 startTime = v;
 return this;
}

public ScheduleJobsSolver setEnforceReleaseDate(boolean v){
 enforceReleaseDate = v;
 return this;
}

public ScheduleJobsSolver setEnforceDueDate(boolean v){
 enforceDueDate = v;
 return this;
}

public ScheduleJobsSolver setEnforceCumulative(boolean v){
 enforceCumulative = v;
 return this;
}

public ScheduleJobsSolver setEnforceWip(boolean v){
 enforceWip = v;
 return this;
}

public ScheduleJobsSolver setEnforceDowntime(boolean v){
 enforceDowntime = v;
 return this;
}

public ScheduleJobsSolver setModelType(String v){
 modelType = v;
 return this;
}

public ScheduleJobsSolver setSolverBackend(String v){
 solverBackend = v;
 return this;
}

public ScheduleJobsSolver setObjectiveType(String v){
 objectiveType = v;
 return this;
}

public ScheduleJobsSolver setWeightMakespan(int v){
 weightMakespan = v;
 return this;
}

public ScheduleJobsSolver setWeightFlowtime(int v){
 weightFlowtime = v;
 return this;
}

public ScheduleJobsSolver setWeightLateness(int v){
 weightLateness = v;
 return this;
}

public ScheduleJobsSolver setWeightEarliness(int v){
 weightEarliness = v;
 return this;
}

public ScheduleJobsSolver setTimeout(int v){
 timeout = v;
 return this;
}

public ScheduleJobsSolver setNrThreads(int v){
 nrThreads = v;
 return this;
}

public ScheduleJobsSolver setSeed(int v){
 seed = v;
 return this;
}

public ScheduleJobsSolver setRemoveSolution(boolean v){
 removeSolution = v;
 return this;
}

public ScheduleJobsSolver setProduceReport(boolean v){
 produceReport = v;
 return this;
}

public ScheduleJobsSolver setProducePDF(boolean v){
 producePDF = v;
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

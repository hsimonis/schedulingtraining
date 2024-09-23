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
    protected String solverType="CPO";
    protected int timeout=300;
    protected int nrThreads=25;
    protected int timeResolution=1;
    protected int seed=1;

    public ScheduleJobsSolver(Scenario base){
        super(base,new String[] {});
    }
    public ScheduleJobsSolver(Scenario base,String label,String solverType,int timeout,int nrThreads,int timeResolution,int seed){
        super(base,new String[] {});
        this.label=label;
        this.solverType=solverType;
        this.timeout=timeout;
        this.nrThreads=nrThreads;
        this.timeResolution=timeResolution;
        this.seed=seed;
    }

public String getLabel(){
 return label;
}

public String getSolverType(){
 return solverType;
}

public int getTimeout(){
 return timeout;
}

public int getNrThreads(){
 return nrThreads;
}

public int getTimeResolution(){
 return timeResolution;
}

public int getSeed(){
 return seed;
}

public ScheduleJobsSolver setLabel(String v){
 label = v;
 return this;
}

public ScheduleJobsSolver setSolverType(String v){
 solverType = v;
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

public ScheduleJobsSolver setTimeResolution(int v){
 timeResolution = v;
 return this;
}

public ScheduleJobsSolver setSeed(int v){
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

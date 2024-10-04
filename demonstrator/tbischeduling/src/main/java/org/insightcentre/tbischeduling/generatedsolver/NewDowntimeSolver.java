package org.insightcentre.tbischeduling.generatedsolver;

import org.insightcentre.tbischeduling.datamodel.*;
import framework.solver.AbstractSolver;
import framework.types.DateOnly;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;
import static org.insightcentre.tbischeduling.logging.LogShortcut.warning;

public class NewDowntimeSolver extends DefaultSolver{
// solver internal variables
    protected long startSystem = 0;
// solver parameters
    protected String disjunctiveResource="DR1";
    protected int start=50;
    protected int end=100;

    public NewDowntimeSolver(Scenario base){
        super(base,new String[] {});
    }
    public NewDowntimeSolver(Scenario base,String disjunctiveResource,int start,int end){
        super(base,new String[] {});
        this.disjunctiveResource=disjunctiveResource;
        this.start=start;
        this.end=end;
    }

public String getDisjunctiveResource(){
 return disjunctiveResource;
}

public int getStart(){
 return start;
}

public int getEnd(){
 return end;
}

public NewDowntimeSolver setDisjunctiveResource(String v){
 disjunctiveResource = v;
 return this;
}

public NewDowntimeSolver setStart(int v){
 start = v;
 return this;
}

public NewDowntimeSolver setEnd(int v){
 end = v;
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

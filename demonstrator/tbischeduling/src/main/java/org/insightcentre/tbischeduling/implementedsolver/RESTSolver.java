package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverRun;

public class RESTSolver extends AbstractModel{
    public RESTSolver(Scenario base, SolverRun run){
        super(base,run);
    }

    public boolean solve(){
        return true;
    }
}

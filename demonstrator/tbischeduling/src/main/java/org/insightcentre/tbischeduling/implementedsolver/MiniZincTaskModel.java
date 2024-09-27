package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverRun;

public class MiniZincTaskModel extends AbstractModel{
    public MiniZincTaskModel(Scenario base, SolverRun run){
        super(base,run);
    }

    public boolean solve(){
        return true;
    }
}

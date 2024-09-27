package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverRun;

public class MiniZincDiffnModel extends AbstractModel{
    public MiniZincDiffnModel(Scenario base, SolverRun run){
        super(base,run);
    }

    public boolean solve(){
        return true;
    }
}

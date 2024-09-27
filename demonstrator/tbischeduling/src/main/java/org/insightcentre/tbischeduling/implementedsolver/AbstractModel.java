package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverRun;

public abstract class AbstractModel {
    Scenario base;
    SolverRun run;

    public AbstractModel(Scenario base, SolverRun run){
        this.base = base;
        this.run = run;
    }

    public abstract boolean solve();
}

package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.generatedsolver.GenerateDataSolver;
import org.insightcentre.tbischeduling.importer.CreateData;

import static org.insightcentre.tbischeduling.JfxApp.horizon;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;

public class GenerateDataSolverImpl extends GenerateDataSolver {
    public GenerateDataSolverImpl(Scenario base){
        super(base);
    }

    public boolean solve(){
        info("Generating dataset based on parameters");
        new CreateData(base,getName(),getSeed(),getNrProducts(),getNrStages(),
                getNrDisjunctiveResources(),getNrCumulativeResources(),getResourceProbability(),
                getMinCumulDemand(),getMaxCumulDemand(),getCumulCapacity(),
                getNrOrders(),
                getEarliestDue(),horizon(getHorizonDays(),getTimeResolution()),getMinQty(),getMaxQty());
        return true;
    }
}

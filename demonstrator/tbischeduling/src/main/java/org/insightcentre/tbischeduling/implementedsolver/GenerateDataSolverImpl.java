package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.ResourceModel;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.generatedsolver.GenerateDataSolver;
import org.insightcentre.tbischeduling.importer.CreateData;

import static org.insightcentre.tbischeduling.JfxApp.horizon;
import static org.insightcentre.tbischeduling.datamodel.ResourceModel.*;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class GenerateDataSolverImpl extends GenerateDataSolver {
    public GenerateDataSolverImpl(Scenario base){
        super(base);
    }

    public boolean solve(){
        info("Generating dataset based on parameters");
        new CreateData(base,getName(),toResourceModel(getResourceModel()),getSeed(),getNrProducts(),getNrStages(),
                getNrDisjunctiveResources(),getNrCumulativeResources(),getResourceProbability(),
                getMinCumulDemand(),getMaxCumulDemand(),getCumulCapacity(),
                getNrOrders(),
                getEarliestDue(),horizon(getHorizonDays(),getTimeResolution()),getMinQty(),getMaxQty());
        return true;
    }

    private ResourceModel toResourceModel(String name){
        switch(name){
            case "FlowShop": return FlowShop;
            case "JobShop": return JobShop;
            case "HybridFlowShop": return HybridFlowShop;
            case "HybridJobShop": return HybridJobShop;
            case "Random": return Random;
            case "All": return All;
            default:
                severe("Unknown resource model name "+name);
                assert(false);
                return null;
        }
    }
}

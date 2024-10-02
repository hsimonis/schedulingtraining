package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.DurationModel;
import org.insightcentre.tbischeduling.datamodel.ResourceModel;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.generatedsolver.GenerateDataSolver;
import org.insightcentre.tbischeduling.importer.CreateData;

import static org.insightcentre.tbischeduling.JfxApp.horizon;
import static org.insightcentre.tbischeduling.datamodel.DurationModel.*;
import static org.insightcentre.tbischeduling.datamodel.DurationModel.Random;
import static org.insightcentre.tbischeduling.datamodel.ResourceModel.*;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class GenerateDataSolverImpl extends GenerateDataSolver {
    public GenerateDataSolverImpl(Scenario base){
        super(base);
    }

    public boolean solve(){
        info("Generating dataset based on parameters");
        new CreateData(base,getName(),toResourceModel(getResourceModel()),getSeed(),getNrProducts(),
                getMinStages(),getMaxStages(),
                getNrDisjunctiveResources(),getNrCumulativeResources(),getResourceProbability(),
                toDurationModel(getDurationModel()),getMinDuration(),getMaxDuration(),getDurationFixedFactor(),
                getMinCumulDemand(),getMaxCumulDemand(),getProfilePieces(),getMinCumulCapacity(),getMaxCumulCapacity(),
                getNrOrders(),
                getEarliestDue(),horizon(getHorizonDays(),getTimeResolution()),getMinQty(),getMaxQty(),
                getWipProbability(),getMinWip(),getMaxWip(),
                getDowntimeProbability(),getMinDowntime(),getMaxDowntime());
        return true;
    }

    private ResourceModel toResourceModel(String name){
        switch(name){
            case "FlowShop": return FlowShop;
            case "JobShop": return JobShop;
            case "HybridFlowShop": return HybridFlowShop;
            case "HybridJobShop": return HybridJobShop;
            case "Random": return ResourceModel.Random;
            case "All": return All;
            default:
                severe("Unknown resource model name "+name);
                assert(false);
                return null;
        }
    }

    private DurationModel toDurationModel(String name){
        switch(name){
            case "Random": return Random;
            case "RandomByStage": return RandomByStage;
            case "Uniform": return Uniform;
            case "UniformByStage": return UniformByStage;
            case "Unitary": return Unitary;
            default:
                severe("Unknown duration model name "+name);
                assert(false);
                return null;
        }
    }
}

package org.insightcentre.tbischeduling.utilities;

import framework.types.DateTime;
import org.insightcentre.tbischeduling.datamodel.*;

import static org.insightcentre.tbischeduling.datamodel.DurationModel.*;
import static org.insightcentre.tbischeduling.datamodel.ModelType.*;
import static org.insightcentre.tbischeduling.datamodel.ModelType.REST;
import static org.insightcentre.tbischeduling.datamodel.ObjectiveType.*;
import static org.insightcentre.tbischeduling.datamodel.ResourceModel.*;
import static org.insightcentre.tbischeduling.datamodel.Severity.*;
import static org.insightcentre.tbischeduling.datamodel.Severity.Minor;
import static org.insightcentre.tbischeduling.datamodel.SolverBackend.*;
import static org.insightcentre.tbischeduling.datamodel.SolverBackend.Cplex;
import static org.insightcentre.tbischeduling.datamodel.SolverStatus.*;
import static org.insightcentre.tbischeduling.datamodel.SolverStatus.Error;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class TypeConverters {
    public static  SequenceType toSequenceType(String name){
        if ("EndBeforeStart".equals(name)) {
            return SequenceType.EndBeforeStart;
        }
        return null;
    }

    public static  Severity toSeverity(String name){
        return switch (name) {
            case "Fatal" -> Fatal;
            case "Critical" -> Critical;
            case "Major" -> Major;
            case "Minor" -> Minor;
            default -> Minor;
        };
    }
    public static  ModelType toModelType(String name){
        return switch (name) {
            case "CPO" -> CPO;
            case "MiniZincDiffn" -> MiniZincDiffn;
            case "MiniZincTask" -> MiniZincTask;
            case "REST" -> REST;
            default -> null;
        };
    }
    public static  SolverBackend toSolverBackend(String name){
        return switch (name) {
            case "None" -> None;
            case "Chuffed" -> Chuffed;
            case "Gecode" -> Gecode;
            case "CPSat" -> CPSat;
            case "Cplex" -> Cplex;
            default -> null;
        };
    }
    public static  SolverStatus toSolverStatus(String name){
        return switch (name) {
            case "ToRun" -> ToRun;
            case "Optimal" -> Optimal;
            case "Solution" -> Solution;
            case "Infeasible" -> Infeasible;
            case "Unknown" -> Unknown;
            case "Error" -> Error;
            default -> null;
        };
    }
    public static ObjectiveType toObjectiveType(String name){
        return switch (name) {
            case "Makespan" -> Makespan;
            case "Flowtime" -> Flowtime;
            case "TotalEarliness" -> TotalEarliness;
            case "MaxEarliness" -> MaxEarliness;
            case "WeightedEarliness" -> WeightedEarliness;
            case "TotalLateness" -> TotalLateness;
            case "MaxLateness" -> MaxLateness;
            case "WeightedLateness" -> WeightedLateness;
            case "Hybrid" -> Hybrid;
            default -> null;
        };
    }
    public static ResourceModel toResourceModel(String name){
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

    public static DurationModel toDurationModel(String name){
        switch(name){
            case "Random": return DurationModel.Random;
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

    public static DateTime toDateTime(Scenario base, int d){
        return base.getStartDateTime().addMinutes(d*base.getTimeResolution());

    }


}

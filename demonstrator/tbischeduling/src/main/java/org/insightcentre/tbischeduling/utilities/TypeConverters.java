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
            case "OnTime" -> OnTime;
            case "Hybrid" -> Hybrid;
            default -> null;
        };
    }
    public static ResourceModel toResourceModel(String name){
        switch(name){
            case "FlowShop": return FlowShop;
            case "JobShop": return JobShop;
            case "OpenShop": return OpenShop;
            case "HybridFlowShop": return HybridFlowShop;
            case "HybridJobShop": return HybridJobShop;
            case "HybridOpenShop": return HybridOpenShop;
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

    public static ColorBy toColorBy(String name){
        switch(name){
            case "Mixed": return ColorBy.Mixed;
            case "Job": return ColorBy.Job;
            case "Machine": return ColorBy.Machine;
            case "Product": return ColorBy.Product;
            case "Process": return ColorBy.Process;
            case "Stage": return ColorBy.Stage;
            case "None": return ColorBy.None;
            default:
                severe("Unknown ColorBy name "+name);
                assert(false);
                return null;
        }
    }
    public static TaskLabel toTaskLabel(String name){
        switch(name){
            case "Task": return TaskLabel.Task;
            case "Start": return TaskLabel.Start;
            case "End": return TaskLabel.End;
            case "Duration": return TaskLabel.Duration;
            case "Wait": return TaskLabel.Wait;
            case "Job": return TaskLabel.Job;
            case "Machine": return TaskLabel.Machine;
            case "Product": return TaskLabel.Product;
            case "Stage": return TaskLabel.Stage;
            case "None": return TaskLabel.None;
            default:
                severe("Unknown TaskLabel name "+name);
                assert(false);
                return null;
        }
    }
    public static JobOrder toJobOrder(String name){
        switch(name){
            case "Input": return JobOrder.Input;
            case "Nr": return JobOrder.Nr;
            case "Start": return JobOrder.Start;
            case "End": return JobOrder.End;
            case "Release": return JobOrder.Release;
            case "Due": return JobOrder.Due;
            case "OnTime": return JobOrder.OnTime;
            case "Product": return JobOrder.Product;
            default:
                severe("Unknown JobOrder name "+name);
                assert(false);
                return null;
        }
    }

    public static ResourceChoice toResourceChoice(String name){
        switch(name){
            case "All":return ResourceChoice.All;
            case "None": return ResourceChoice.None;
            case "Selected":return ResourceChoice.Selected;
            default:
                severe("Unknown Resource Choice "+name);
                assert(false);
                return null;
        }
    }
    public static LineChoice toLineChoice(String name){
        switch(name){
            case "None":return LineChoice.None;
            case "Number":return LineChoice.Number;
            case "Line":return LineChoice.Line;
            case "All":return LineChoice.All;
            default:
                severe("Unknown Line Choice "+name);
                assert(false);
                return null;
        }
    }
    public static ResourceZoom toResourceZoom(String name){
        switch(name){
            case "Wide":return ResourceZoom.Wide;
            case "Normal":return ResourceZoom.Normal;
            case "Dense":return ResourceZoom.Dense;
            case "FitAll":return ResourceZoom.FitAll;
            default:
                severe("Unknown Resource Zoom "+name);
                assert(false);
                return null;
        }
    }

    public static DatesDisplay toDatesDisplay(String name){
        switch(name){
            case "Internal":return DatesDisplay.Internal;
            case "External": return DatesDisplay.External;
            default:
                severe("Unknown DatesDisplay Choice "+name);
                assert(false);
                return null;
        }
    }


}

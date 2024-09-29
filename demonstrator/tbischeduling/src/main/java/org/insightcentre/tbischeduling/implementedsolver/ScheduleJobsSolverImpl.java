package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.generatedsolver.ScheduleJobsSolver;
import org.insightcentre.tbischeduling.importer.Reset;

import static org.insightcentre.tbischeduling.datamodel.ModelType.*;
import static org.insightcentre.tbischeduling.datamodel.ObjectiveType.*;
import static org.insightcentre.tbischeduling.datamodel.SolverBackend.*;
import static org.insightcentre.tbischeduling.datamodel.SolverStatus.ToRun;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class ScheduleJobsSolverImpl extends ScheduleJobsSolver {
    static int runNr=1;

    public ScheduleJobsSolverImpl(Scenario base){
        super(base);
    }

    public boolean solve(){
        info("Solving");
        if (getRemoveSolution()){
            Reset.resetSolution(base);
        }
        SolverRun run = createSolverRun(getLabel(),getDescription(),toModelType(getModelType()),
                toSolverBackend(getSolverBackend()),toObjectiveType(getObjectiveType()),
                getEnforceReleaseDate(),getEnforceDueDate(),getTimeout(),getNrThreads(),getSeed(),
                getRemoveSolution());
        switch(toModelType(getModelType())){
            case CPO:
                return new CPOModel(base,run).solve();
            case MiniZincDiffn:
                return new MiniZincDiffnModel(base,run).solve();
            case MiniZincTask:
                return new MiniZincTaskModel(base,run).solve();
            case REST:
                return new RESTSolver(base,run).solve();
            default:
                severe("Unknown model type "+getModelType());
                assert(false);
        }
        base.setDirty(true);
        return true;
    }

    private ModelType toModelType(String name){
        return switch (name) {
            case "CPO" -> CPO;
            case "MiniZincDiffn" -> MiniZincDiffn;
            case "MiniZincTask" -> MiniZincTask;
            default -> null;
        };
    }

    private SolverBackend toSolverBackend(String name){
        return switch(name){
            case "Chuffed" -> Chuffed;
            case "Gecode" -> Gecode;
            case "CPSat" -> CPSat;
            case "Cplex" -> Cplex;
            default -> null;
        };
    }

    private ObjectiveType toObjectiveType(String name){
        return switch(name){
            case "Makespan" -> Makespan;
            case "Flowtime" -> Flowtime;
            case "TotalLateness" -> TotalLateness;
            case "MaxLateness" -> MaxLateness;
            case "WeightedLateness" -> WeightedLateness;
            case "TotalEarliness" -> TotalEarliness;
            case "MaxEarliness" -> MaxEarliness;
            case "WeightedEarliness" -> WeightedEarliness;
            case "Hybrid" -> Hybrid;
            default -> null;
        };
    }

    private SolverRun createSolverRun(String label,String description,ModelType modelType,SolverBackend solverBackend,
                                      ObjectiveType objectiveType,boolean enforceReleaseDate,boolean enforceDueDate,
                                      int timeout,int nrThreads,int seed,boolean removeSolution){
        SolverRun res = new SolverRun(base);
        res.setName("Run"+runNr++);
        res.setLabel(label);
        res.setDescription(description);
        res.setModelType(modelType);
        res.setSolverBackend(solverBackend);
        res.setObjectiveType(objectiveType);
        res.setEnforceReleaseDate(enforceReleaseDate);
        res.setEnforceDueDate(enforceDueDate);
        res.setTimeout(timeout);
        res.setNrThreads(nrThreads);
        res.setSeed(seed);
        res.setRemoveSolution(removeSolution);
        res.setSolverStatus(ToRun);
        return res;
    }
}

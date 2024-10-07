package org.insightcentre.tbischeduling.implementedsolver;

import framework.types.DateOnly;
import framework.types.DateTime;
import framework.types.TimeOnly;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.generatedsolver.ScheduleJobsSolver;
import org.insightcentre.tbischeduling.importer.Reset;
import org.insightcentre.tbischeduling.reports.RunReport;
import org.insightcentre.tbischeduling.reports.SchedulingReport;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static org.insightcentre.tbischeduling.datamodel.ModelType.*;
import static org.insightcentre.tbischeduling.datamodel.ObjectiveType.*;
import static org.insightcentre.tbischeduling.datamodel.SolverBackend.*;
import static org.insightcentre.tbischeduling.datamodel.SolverStatus.ToRun;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;
import static org.insightcentre.tbischeduling.utilities.TypeConverters.*;

public class ScheduleJobsSolverImpl extends ScheduleJobsSolver {
    static int runNr=1;

    public ScheduleJobsSolverImpl(Scenario base){

        super(base,base.getSolverProperty().getLabel(),
                base.getSolverProperty().getDescription(),
                base.getSolverProperty().getStartDateTime().asDateOnly().toString(),
                base.getSolverProperty().getStartDateTime().asTimeOnly().toString(),
                base.getSolverProperty().getEnforceReleaseDate(),
                base.getSolverProperty().getEnforceDueDate(),
                base.getSolverProperty().getEnforceCumulative(),
                base.getSolverProperty().getEnforceWip(),
                base.getSolverProperty().getEnforceDowntime(),
                base.getSolverProperty().getModelType().toString(),
                base.getSolverProperty().getSolverBackend().toString(),
                base.getSolverProperty().getObjectiveType().toString(),
                base.getSolverProperty().getWeightMakespan(),
                base.getSolverProperty().getWeightFlowtime(),
                base.getSolverProperty().getWeightLateness(),
                base.getSolverProperty().getWeightEarliness(),
                base.getSolverProperty().getTimeout(),
                base.getSolverProperty().getNrThreads(),
                base.getSolverProperty().getSeed(),
                base.getSolverProperty().getRemoveSolution(),
                base.getSolverProperty().getProduceReport(),
                base.getSolverProperty().getProducePDF()
                );
    }

    private void saveProperties(){
        SolverProperty p = base.getSolverProperty();
        p.setLabel(getLabel());
        p.setDescription(getDescription());
        DateOnly dateOnly = DateOnly.parseDateOnly(getStartDate(),"dd-MM-yyyy");
        TimeOnly timeOnly = TimeOnly.parseTimeOnlyWithoutException(getStartTime());
        DateTime dateTime = dateOnly.asDateTime().setTime(timeOnly);
        p.setStartDateTime(dateTime);
        p.setEnforceReleaseDate(getEnforceReleaseDate());
        p.setEnforceDueDate(getEnforceDueDate());
        p.setEnforceCumulative(getEnforceCumulative());
        p.setEnforceWip(getEnforceWip());
        p.setEnforceDowntime(getEnforceDowntime());
        p.setModelType(toModelType(getModelType()));
        p.setSolverBackend(toSolverBackend(getSolverBackend()));
        p.setObjectiveType(toObjectiveType(getObjectiveType()));
        p.setWeightMakespan(getWeightMakespan());
        p.setWeightFlowtime(getWeightFlowtime());
        p.setWeightLateness(getWeightLateness());
        p.setWeightEarliness(getWeightEarliness());
        p.setTimeout(getTimeout());
        p.setNrThreads(getNrThreads());
        p.setSeed(getSeed());
        p.setRemoveSolution(getRemoveSolution());
        p.setProduceReport(getProduceReport());
        p.setProducePDF(getProducePDF());
    }

    public boolean solve(){
        boolean res = false;
        info("Solving");
        if (getRemoveSolution()){
            Reset.resetSolution(base);
        }
        saveProperties();
        SolverRun run = createSolverRun(getLabel(),getDescription(),toModelType(getModelType()),
                toSolverBackend(getSolverBackend()),toObjectiveType(getObjectiveType()),
                getEnforceReleaseDate(),getEnforceDueDate(),getEnforceCumulative(),getEnforceWip(),getEnforceDowntime(),
                getWeightMakespan(),getWeightFlowtime(),getWeightEarliness(),getWeightLateness(),
                getTimeout(),getNrThreads(),getSeed(),
                getRemoveSolution(),getProduceReport(),getProducePDF());
        switch(toModelType(getModelType())){
            case CPO:
                res = new CPOModel(base,run).solve();
                break;
            case MiniZincDiffn:
                res = new MiniZincDiffnModel(base,run).solve();
                break;
            case MiniZincTask:
                res = new MiniZincTaskModel(base,run).solve();
                break;
            case REST:
                res = new RESTSolver(base,run).solve();
                break;
            default:
                severe("Unknown model type "+getModelType());
                assert(false);
        }
        if (res) {
            Solution sol = Solution.findLast(base);
            kpiCalc(sol);
            new CheckSolutions(base,sol);

            if (run.getProduceReport() || run.getProducePDF()) {
                new SchedulingReport(base, "reports/").produce("schedulingreport", "Scheduling Report", "L. O'Toole and H. Simonis");
            }
            if (run.getProducePDF()) {
                // run latex to produce report
                new RunReport("lualatex", "reports/", "schedulingreport").runProgram();
            }
        }

        base.setDirty(true);
        return res;
    }


    private SolverRun createSolverRun(String label,String description,ModelType modelType,SolverBackend solverBackend,
                                      ObjectiveType objectiveType,boolean enforceReleaseDate,boolean enforceDueDate,
                                      boolean enforceCumulative,boolean enforceWip,boolean enforceDowntime,
                                      int weightMakespan,int weightFlowtime,int weightEarliness,int weightLateness,
                                      int timeout,int nrThreads,int seed,boolean removeSolution,
                                      boolean produceReport,boolean producePDF){
        SolverRun res = new SolverRun(base);
        res.setName("Run"+runNr++);
        res.setLabel(label);
        res.setDescription(description);
        res.setModelType(modelType);
        res.setSolverBackend(solverBackend);
        res.setObjectiveType(objectiveType);
        res.setEnforceReleaseDate(enforceReleaseDate);
        res.setEnforceDueDate(enforceDueDate);
        res.setEnforceCumulative(enforceCumulative);
        res.setEnforceWip(enforceWip);
        res.setEnforceDowntime(enforceDowntime);

        res.setWeightMakespan(weightMakespan);
        res.setWeightFlowtime(weightFlowtime);
        res.setWeightEarliness(weightEarliness);
        res.setWeightLateness(weightLateness);

        res.setTimeout(timeout);
        res.setNrThreads(nrThreads);
        res.setSeed(seed);
        res.setRemoveSolution(removeSolution);
        res.setProduceReport(produceReport);
        res.setProducePDF(producePDF);
        res.setSolverStatus(ToRun);
        return res;
    }

    private void kpiCalc(Solution sol){
        List<TaskAssignment> list = base.getListTaskAssignment().stream().filter(x->x.getJobAssignment().getSolution()==sol).toList();
        Map<DisjunctiveResource,List<TaskAssignment>> map = list.stream().collect(groupingBy(TaskAssignment::getDisjunctiveResource));
        // create entries in a given order
        for(DisjunctiveResource r:map.keySet().stream().sorted(Comparator.comparing(DisjunctiveResource::getName)).toList()){
            List<TaskAssignment> current = map.get(r);
            int earliest = current.stream().mapToInt(TaskAssignment::getStart).min().orElse(0);
            int latest = current.stream().mapToInt(TaskAssignment::getEnd).max().orElse(0);
            int active = latest-earliest;
            int use = current.stream().mapToInt(TaskAssignment::getDuration).sum();
            double utilization = 100.0*use/active;
            ResourceUtilization ru = new ResourceUtilization(base);
            ru.setName(r.getName());
            ru.setDisjunctiveResource(r);
            ru.setSolution(sol);
            ru.setEarliest(earliest);
            ru.setLatest(latest);
            ru.setActive(active);
            ru.setUse(use);
            ru.setUtilization(utilization);
        }
    }
}

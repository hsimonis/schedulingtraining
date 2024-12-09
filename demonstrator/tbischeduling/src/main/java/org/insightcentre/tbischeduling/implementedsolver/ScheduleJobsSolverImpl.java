package org.insightcentre.tbischeduling.implementedsolver;

import framework.types.DateOnly;
import framework.types.DateTime;
import framework.types.TimeOnly;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.generatedsolver.ScheduleJobsSolver;
import org.insightcentre.tbischeduling.importer.Reset;
import org.insightcentre.tbischeduling.reports.RunReport;
import org.insightcentre.tbischeduling.reports.SchedulingReport;

import java.util.*;

import static framework.reports.AbstractCommon.safe;
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
                base.getSolverProperty().getEnforceSetup(),
                base.getSolverProperty().getEnforceTransportTime(),
                base.getSolverProperty().getRelaxSequence(),
                base.getSolverProperty().getAddSameOrder(),
                base.getSolverProperty().getAddNoWait(),
                base.getSolverProperty().getAddBlocking(),
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
        p.setEnforceSetup(getEnforceSetup());
        p.setEnforceTransportTime(getEnforceTransportTime());
        p.setRelaxSequence(getRelaxSequence());
        p.setAddSameOrder(getAddSameOrder());
        p.setAddNoWait(getAddNoWait());
        p.setAddBlocking(getAddBlocking());
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
                getEnforceReleaseDate(),getEnforceDueDate(),getEnforceCumulative(),getEnforceWip(),
                getEnforceDowntime(),getEnforceSetup(),getEnforceTransportTime(),getRelaxSequence(),
                getAddSameOrder(),getAddNoWait(),getAddBlocking(),
                getWeightMakespan(),getWeightFlowtime(),getWeightEarliness(),getWeightLateness(),
                getTimeout(),getNrThreads(),getSeed(),
                getRemoveSolution(),getProduceReport(),getProducePDF());
        switch(toModelType(getModelType())){
            case CPO:
                res = new CPOModel(base,run).solve();
                break;
            case CPSat:
                res = new CPSatModel(base,run).solve();
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
            case Batch:
                res = new BatchSolver(base,run).solve();
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
                new SchedulingReport(base, "reports/").produce("schedulingreport", "Scheduling Report for "+safe(base.getDataFile()), "L. O'Toole and H. Simonis");
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
                                      boolean enforceCumulative,boolean enforceWip,boolean enforceDowntime,boolean enforceSetup,
                                      boolean enforceTransportTime,boolean relaxSequence,
                                      boolean addSameOrder,boolean addNoWait,boolean addBlocking,
                                      int weightMakespan,int weightFlowtime,int weightEarliness,int weightLateness,
                                      int timeout,int nrThreads,int seed,boolean removeSolution,
                                      boolean produceReport,boolean producePDF){
        String name = "Run"+runNr++;
        double time = 0.0;
        DateTime startDateTime = new DateTime(new Date());
        SolverRun res = new SolverRun(base,runNr,name,addBlocking,addNoWait,addSameOrder,description,enforceCumulative,enforceDowntime,enforceDueDate,
                enforceReleaseDate,enforceSetup,enforceTransportTime,enforceWip,
                label,modelType,nrThreads,objectiveType,producePDF,produceReport,relaxSequence,removeSolution,seed,solverBackend,
                startDateTime,timeout,weightEarliness,weightFlowtime,weightLateness,weightMakespan,ToRun,time);
        return res;
    }

    private void kpiCalc(Solution sol){
        List<TaskAssignment> list = base.getListTaskAssignment().stream().
                filter(x->x.getJobAssignment().getSolution()==sol).
                toList();
        Map<DisjunctiveResource,List<TaskAssignment>> map = list.stream().
                filter(x->x.getDisjunctiveResource()!=null).
                collect(groupingBy(TaskAssignment::getDisjunctiveResource));
        // create entries in a given order
        List<ResourceUtilization> ruList = new ArrayList<>();
        for(DisjunctiveResource r:map.keySet().stream().sorted(Comparator.comparing(DisjunctiveResource::getName)).toList()){
            List<TaskAssignment> current = map.get(r);
            int earliest = current.stream().mapToInt(TaskAssignment::getStart).min().orElse(0);
            int latest = current.stream().mapToInt(TaskAssignment::getEnd).max().orElse(0);
            int active = latest-earliest;
            int use = current.stream().mapToInt(TaskAssignment::getDuration).sum();
            int setup = current.stream().mapToInt(TaskAssignment::getSetupBefore).sum();
            int idle = current.stream().mapToInt(TaskAssignment::getIdleBefore).sum();
            double utilization = 100.0*use/active;
            double setupPercent = 100.0*setup/active;
            double idlePercent = 100.0*idle/active;
            ResourceUtilization ru = new ResourceUtilization(base);
            ru.setName(r.getName());
            ru.setDisjunctiveResource(r);
            ru.setSolution(sol);
            ru.setEarliest(earliest);
            ru.setLatest(latest);
            ru.setActive(active);
            ru.setUse(use);
            ru.setSetup(setup);
            ru.setIdle(idle);
            ru.setUtilization(utilization);
            ru.setSetupPercent(setupPercent);
            ru.setIdlePercent(idlePercent);
            ruList.add(ru);
        }
        sol.setTotalActiveTime(ruList.stream().mapToInt(ResourceUtilization::getActive).sum());
        sol.setTotalProductionTime(ruList.stream().mapToInt(ResourceUtilization::getUse).sum());
        sol.setActiveUtilization(100.0*sol.getTotalProductionTime()/sol.getTotalActiveTime());
        sol.setSetupPercent(100.0*sol.getTotalSetupBefore()/sol.getTotalActiveTime());
        sol.setIdlePercent(100.0*sol.getTotalIdleBefore()/sol.getTotalActiveTime());
    }
}

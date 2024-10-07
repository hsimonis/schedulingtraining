package org.insightcentre.tbischeduling.reports;

import framework.reports.visualization.gantt.GanttDraw;
import framework.reports.visualization.gantt.GanttResourceFunctions;
import framework.reports.visualization.gantt.GanttTaskFunctions;
import framework.reports.visualization.plot.barplot.BarPlot;
import framework.reports.visualization.plot.lineplot.LinePlot;
import framework.reports.visualization.plot.lineplot.LinePlotFunctions;
import framework.reports.visualization.plot.profileplot.AddProfile;
import framework.reports.visualization.plot.profileplot.ProfilePlot;
import framework.reports.visualization.plot.scatterplot.ScatterPlot;
import framework.reports.visualization.plot.scatterplot.ScatterPlotFunctions;
import framework.reports.visualization.tabular.TableStyle;
import framework.reports.visualization.tabular.table.*;
import org.apache.commons.collections4.ListUtils;
import org.insightcentre.tbischeduling.datamodel.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import static framework.reports.visualization.plot.PlotStyle.FIGURE;
import static framework.reports.visualization.tabular.table.LimitOp.GT;
import static framework.reports.visualization.tabular.table.RankingDirection.GreaterIsBetter;
import static framework.reports.visualization.tabular.table.RankingDirection.SmallerIsBetter;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;

public class SchedulingReport extends AbstractReport{
    Hashtable<Job,String> jobColorHash;
    Hashtable<DisjunctiveResource,String> resourceColorHash;
    public SchedulingReport(Scenario base, String reportDir){
        super(base,reportDir);
    }

    public void content(){
        stagecolorDefs(tex);

        section("Introduction");
        problemTable();


//        section("Orders");
//        orderTable();

        if (base.getListSolution().size() > 0) {
            section("Solution");

            // highlight fields if value is not "true"
            DefaultValuePainter<Solution> enforcePainter = new DefaultValuePainter<>(true);
            enforcePainter.setColor("white","red!30");

            relaxationTable(enforcePainter);

            solutionTable();

            Solution sol = Solution.findLast(base);
            assert(sol!=null);
            intermediateSolutions(sol.getSolverRun());

            List<DisjunctiveResource> machines = base.getListDisjunctiveResource();
            List<Job> jobs = base.getListJobAssignment().stream().
                    filter(x->x.getSolution()==sol).
                    sorted(Comparator.comparing(JobAssignment::getStart)).
                    map(JobAssignment::getJob).
                    toList();
            List<TaskAssignment> tasks = base.getListTaskAssignment().stream().
                    filter(x->x.getJobAssignment().getSolution()==sol).
                    toList();
            int minStage = tasks.stream().mapToInt(x -> x.getTask().getStage()).min().orElse(0);
            int maxStage = tasks.stream().mapToInt(x -> x.getTask().getStage()).max().orElse(0);
            int end = sol.getMakespan();

            initColors();

            List<ResourceActivity> activities = new ArrayList<>(tasks);
            // only show wip if constraints are enforced
            if (sol.getSolverRun().getEnforceWip()) {
                activities.addAll(new ArrayList<ResourceActivity>(base.getListWiP()));
            }
            // only show downtimes if constraints are enforced
            // only show downtimes that are in makespan
            if (sol.getSolverRun().getEnforceDowntime()) {
                activities.addAll(new ArrayList<ResourceActivity>(base.getListDowntime().stream().
                        filter(x->x.getStart()<end).
                        toList()));
            }
            machineGantt(sol,machines,activities,minStage,maxStage);
            jobGantt(sol,jobs,tasks,minStage,maxStage);

            utilizationChart(sol);
            for(CumulativeResource cr:base.getListCumulativeResource()) {
                cumulProfile(sol,cr);
            }
            latenessPlot(sol);


        } else {
            section("No Solutions");
        }
    }

    private void problemTable(){
        new TableDraw<>("Problem",base.getListProblem()).
                addStringColumn("Name",this::nameOf).
                addStringColumn("Label",x->safe(x.getLabel())).
                addBooleanColumn(st("Timepoints","as","Date"), Problem::getTimePointsAsDate).
                addIntegerColumn(st("Nr","Products"),Problem::getNrProducts).
                addIntegerColumn(st("Nr","Process"),Problem::getNrProcesses).
                addIntegerColumn(st("Nr","Disjunctive","Resources"),Problem::getNrDisjunctiveResources).
                addIntegerColumn(st("Nr","Cumulative","Resources"),Problem::getNrCumulativeResources).
                addIntegerColumn(st("Nr","Orders"),Problem::getNrOrders).
                addIntegerColumn(st("Nr","Jobs"),Problem::getNrJobs).
                addIntegerColumn(st("Nr","Tasks"),Problem::getNrTasks).
                generate().latex(tex);

    }

    private void orderTable(){
        new TableDraw<>("Orders (Total "+base.getListOrder().size()+")",base.getListOrder()).
                addStringColumn("Name",this::nameOf).
                addStringColumn("Product",x->nameOf(x.getProduct())).
                addStringColumn("Process",x->nameOf(x.getProcess())).
                addIntegerColumn("Qty", Order::getQty,"%,d").
                addIntegerColumn("Release", Order::getRelease,"%,d").
                addIntegerColumn("Due", Order::getDue,"%,d").
                addDoubleColumn("Earliness Weight",Order::getEarlinessWeight,"%5.2f").
                addDoubleColumn("Lateness Weight",Order::getLatenessWeight,"%5.2f").
//                addDateTimeColumn(st("Due","Date"),Order::getDueDate).
        tableStyle(TableStyle.LONGTABLE).
                generate().latex(tex);

    }

    private void relaxationTable(CellPainter<Solution> enforcePainter) {
        new TableDraw<>("Relaxation of Constraints", base.getListSolution()).
                addStringColumn("Name", this::nameOf).
                addStringColumn("Label", x -> safe(x.getSolverRun().getLabel())).
                addBooleanColumn(st("Enforce", "Release", "Date"), x -> x.getSolverRun().getEnforceReleaseDate(), enforcePainter).
                addBooleanColumn(st("Enforce", "Due", "Date"), x -> x.getSolverRun().getEnforceDueDate(), enforcePainter).
                addBooleanColumn(st("Enforce", "Cumulative"), x -> x.getSolverRun().getEnforceCumulative(), enforcePainter).
                addBooleanColumn(st("Enforce", "WiP"), x -> x.getSolverRun().getEnforceWip(), enforcePainter).
                addBooleanColumn(st("Enforce", "Downtime"), x -> x.getSolverRun().getEnforceDowntime(), enforcePainter).
                addIntegerColumn(st("Nr", "Threads"), x -> x.getSolverRun().getNrThreads()).
                addIntegerColumn(st("Timeout", "(s)"), x -> x.getSolverRun().getTimeout()).
                generate().latex(tex);
    }

    private void solutionTable(){
        new TableDraw<>("Solutions (Total " + base.getListSolution().size() + ")", base.getListSolution()).
                addStringColumn("Name", this::nameOf).
                addStringColumn(st("Solver","Status"),x->x.getSolverStatus().toString()).
                addStringColumn(st("Objective","Type"),x->x.getSolverRun().getObjectiveType().toString()).
                addIntegerColumn(st("Objective", "Value"), Solution::getObjectiveValue,"%,d",new KPIRanking<>(SmallerIsBetter)).
                addDoubleColumn("Bound",Solution::getBound,"%5.2f",new KPIRanking<>(GreaterIsBetter)).
                addDoubleColumn("Gap",Solution::getGap,"%5.2f",new KPIRanking<>(SmallerIsBetter)).
                addIntegerColumn("Makespan", Solution::getMakespan,"%,d",new KPIRanking<>(SmallerIsBetter)).
                addIntegerColumn("Flowtime", Solution::getFlowtime,"%,d",new KPIRanking<>(SmallerIsBetter)).
                addIntegerColumn(st("Total", "Lateness"), Solution::getTotalLateness,"%,d",new KPIRanking<>(SmallerIsBetter)).
                addIntegerColumn(st("Max", "Lateness"), Solution::getMaxLateness,"%,d",new KPIRanking<>(SmallerIsBetter)).
                addIntegerColumn(st("Nr", "Late"), Solution::getNrLate,"%,d",new KPIRankingWithLimit<>(SmallerIsBetter,GT,0)).
                addIntegerColumn(st("Total", "Earliness"), Solution::getTotalEarliness,"%,d",new KPIRanking<>(SmallerIsBetter)).
                addIntegerColumn(st("Max", "Earliness"), Solution::getMaxEarliness,"%,d",new KPIRanking<>(SmallerIsBetter)).
                addIntegerColumn(st("Nr", "Early"), Solution::getNrEarly,"%,d",new KPIRankingWithLimit<>(SmallerIsBetter,GT,0)).
                addStringColumn(st("Model","Type"),x->x.getSolverRun().getModelType().toString()).
                addDoubleColumn(st("Time","(s)"),x->x.getSolverRun().getTime(),"%5.2f").
                generate().latex(tex);

    }


    private void machineGantt(Solution sol,List<DisjunctiveResource> all,
                              List<ResourceActivity> tasks,int minStage,int maxStage) {
        List<List<DisjunctiveResource>> split = ListUtils.partition(all,base.getGanttLinesPerPage());
        int parts=split.size();
        int part=1;
        for(List<DisjunctiveResource> machines:split) {
            int height = (int)Math.round(machines.size()*base.getGanttLineHeight());
            List<ResourceActivity> toDraw = tasks.stream().filter(x->machines.contains(x.getDisjunctiveResource())).toList();

            GanttDraw<DisjunctiveResource, ResourceActivity> gd = new GanttDraw<>(machines,
                    new GanttResourceFunctions<>(this::nameOf, this::resourceStyle),
                    toDraw,
                    new GanttTaskFunctions<>(x -> xcoor(x.getStart()), x -> xcoor(x.getEnd()),
                            ResourceActivity::getDisjunctiveResource,
                            x -> "", this::taskStyle));
            gd.addMarker(sol.getMakespan(), "draw=makespancolor,thick",
                            "Cmax: " + sol.getMakespan(),
                            "above left,font=\\scriptsize").
                    width(base.getGanttWidth()).height(height).
                    title("Machine Gantt for Solution " + sol.getName()+ (parts>1?" (Part " + part + " of " + parts + ")":"")).
                    label("resourceGantt"+part);
            if (containsWip(toDraw)) {
                gd.addLegendEntry("WiP", "draw=black,fill=wipcolor!40,font=\\scriptsize");
            }
            if (containsDowntime(toDraw)) {
                gd.addLegendEntry("Down", "draw=black,fill=downcolor!40,font=\\scriptsize");
            }
            for (int i = minStage; i <= maxStage; i++) {
                gd.addLegendEntry("Stage" + i, "draw=black,fill=stagecolor" + i + "!10,font=\\scriptsize");
            }
            gd.addLegendEntry("Late", "draw=latecolor,font=\\scriptsize");

            gd.generate().latex(tex);
            part++;
        }
    }

    private boolean containsWip(List<ResourceActivity> list){
        return list.stream().anyMatch(x -> x instanceof WiP);
    }
    private boolean containsDowntime(List<ResourceActivity> list){
        return list.stream().anyMatch(x -> x instanceof Downtime);
    }

    private void jobGantt(Solution sol,List<Job> all,List<TaskAssignment> tasks,int minStage,int maxStage){
        List<List<Job>> split = ListUtils.partition(all,base.getGanttLinesPerPage());
        int parts=split.size();
        int part=1;
        for(List<Job> jobs:split) {
            int height = (int)Math.round(jobs.size()*base.getGanttLineHeight());
            List<TaskAssignment> toDraw = tasks.stream().filter(x->jobs.contains(x.getJobAssignment().getJob())).toList();
            GanttDraw<Job, TaskAssignment> gd = new GanttDraw<>(jobs,
                    new GanttResourceFunctions<>(this::nameOf, this::resourceStyle),
                    toDraw, new GanttTaskFunctions<>(x -> xcoor(x.getStart()), x -> xcoor(x.getEnd()),
                    this::job,
                    x -> "", this::taskStyle));
            gd.addMarker(sol.getMakespan(), "draw=makespancolor,thick",
                            "Cmax: " + sol.getMakespan(),
                            "above left,font=\\scriptsize").
                    width(base.getGanttWidth()).height(height).
                        title("Job Gantt for Solution " + sol.getName() + (parts>1?" (Part " + part + " of " + parts + ")":"")).
                    label("jobGantt"+part);
            for (int i = minStage; i <= maxStage; i++) {
                gd.addLegendEntry("Stage" + i, "draw=black,fill=stagecolor" + i + "!10,font=\\scriptsize");
            }
            gd.addLegendEntry("Late", "draw=latecolor,font=\\scriptsize");
            gd.generate().latex(tex);
            part++;
        }

    }


    private Job job(TaskAssignment ta){
        return ta.getJobAssignment().getJob();
    }

    private void initColors(){
        jobColorHash = new Hashtable<>();
        resourceColorHash = new Hashtable<>();
        for(Job j:base.getListJob()){
            String color = "jobcolor!10";
            jobColorHash.put(j,color);
        }
        for(DisjunctiveResource r:base.getListDisjunctiveResource()){
            String color = "resourcecolor!10";
            resourceColorHash.put(r,color);
        }
    }

    private int xcoor(int x){
        return x;
    }

    private String resourceStyle(DisjunctiveResource r){
        return "draw=black,fill=resourcecolor!10,font=\\scriptsize";
    }
    private String resourceStyle(Job j){
        return "draw=black,fill=jobcolor!10,font=\\scriptsize";
    }

    private String taskStyle(ResourceActivity ta){
        if (ta instanceof WiP){
            return "draw=black,fill=wipcolor!40";

        } else if (ta instanceof Downtime){
            return "draw=black,fill=downcolor!40";

        } else {
            TaskAssignment task = (TaskAssignment) ta;
            return "draw=" + lateColor(task) + ",fill=" + stageColor(task.getTask().getStage());
        }
    }

    private String lateColor(TaskAssignment ta){
        if (ta.getJobAssignment().getLate() > 0 && ta.getEnd() > ta.getJobAssignment().getJob().getOrder().getDue()){
            return "latecolor";
        }
        return "black";
    }

    private String jobColor(Job j){
        return jobColorHash.get(j);
    }

    private String stageColor(int i){
        return "stagecolor"+i+"!10";
    }

    private void stagecolorDefs(PrintWriter tex) {
        tex.printf("\\definecolor{wipcolor}{RGB}{%d,%d,%d}\n", 128,128,128);
        tex.printf("\\definecolor{downcolor}{RGB}{%d,%d,%d}\n", 255,0,0);
        tex.printf("\\definecolor{latecolor}{RGB}{%d,%d,%d}\n", 255,0,0);
        tex.printf("\\definecolor{makespancolor}{RGB}{%d,%d,%d}\n", 255,0,0);
        tex.printf("\\definecolor{resourcecolor}{RGB}{%d,%d,%d}\n", 0,0,255);
        tex.printf("\\definecolor{jobcolor}{RGB}{%d,%d,%d}\n", 0,255,0);
        tex.printf("\\definecolor{stagecolor%d}{RGB}{%d,%d,%d}\n", 0, 240, 163, 255);
        tex.printf("\\definecolor{stagecolor%d}{RGB}{%d,%d,%d}\n", 1, 0, 117, 220);
        tex.printf("\\definecolor{stagecolor%d}{RGB}{%d,%d,%d}\n", 2, 153, 63, 0);
        tex.printf("\\definecolor{stagecolor%d}{RGB}{%d,%d,%d}\n", 3, 76, 0, 92);
        tex.printf("\\definecolor{stagecolor%d}{RGB}{%d,%d,%d}\n", 4, 25, 25, 25);
        tex.printf("\\definecolor{stagecolor%d}{RGB}{%d,%d,%d}\n", 5, 0, 92, 49);
        tex.printf("\\definecolor{stagecolor%d}{RGB}{%d,%d,%d}\n", 6, 43, 206, 72);
        tex.printf("\\definecolor{stagecolor%d}{RGB}{%d,%d,%d}\n", 7, 255, 204, 153);
    }

    private void utilizationChart(Solution sol){
        List<ResourceUtilization> list = base.getListResourceUtilization().stream().filter(x->x.getSolution()==sol).toList();
        new BarPlot<>(list, ResourceUtilization::getDisjunctiveResource, ResourceUtilization::getUtilization).
                includeZero(true).
                plotStyle(FIGURE).
                width(12).height(10).
                caption("Resource Utilization for Solution "+sol.getName()).
                xlabel("Resource").ylabel("Utilization").
                generate().latex(tex);
    }

    private void cumulProfile(Solution sol,CumulativeResource cr){
        List<TaskAssignment> ta = base.getListTaskAssignment().stream().filter(x->x.getJobAssignment().getSolution()==sol).toList();
        int end = ta.stream().mapToInt(TaskAssignment::getEnd).max().orElse(0);
        List<Level> levels = createCapacityLevels(cr,end);

        ProfilePlot<Level> pp =new ProfilePlot<>(0,end);
        pp.addProfile(new AddProfile<>(levels,"Capacity",Level::getStart,Level::getEnd,Level::getValue));
        pp.addProfile(new AddProfile<>(toLevels(ta,cr), "Demand",Level::getStart, Level::getEnd, Level::getValue));
        pp.plotStyle(FIGURE).
                legendPos("outer north east").
                width(21).height(12).
                caption("Cumulative Resource Use "+cr.getName()).
                xlabel("Time").ylabel("Resource Use").
                generate().latex(tex);
        info("Max "+pp.getYmax());
    }

    public List<Level> toLevels(List<TaskAssignment> list,CumulativeResource cr){
        List<Level> res = new ArrayList<>();
        for(TaskAssignment ta:list){
            res.add(new Level(ta.getStart(),ta.getEnd(),cumulUse(ta,cr)));
        }
        return res;
    }

    public List<Level> createCapacityLevels(CumulativeResource r,int end){
        List<Level> res = new ArrayList<>();
        List<CumulativeProfile> profiles = base.getListCumulativeProfile().stream().
                filter(x->x.getCumulativeResource()==r).
                sorted(Comparator.comparing(CumulativeProfile::getFrom)).toList();
        if (profiles.size() > 0) {
            Level prev = null;
            for (CumulativeProfile cp : profiles) {
                Level l = new Level(cp.getFrom(), 0, cp.getCapacity());
                res.add(l);
                if (prev != null) {
                    prev.setEnd(cp.getFrom());
                }
                prev = l;
            }
            prev.setEnd(end);
        }
        return res;
    }

    private int cumulUse(TaskAssignment ta,CumulativeResource cr){
        for(CumulativeNeed cn:base.getListCumulativeNeed()){
            if (cn.getProcessStep() == ta.getTask().getProcessStep() && cn.getCumulativeResource() == cr){
                return cn.getDemand();
            }
        }
        return 0;
    }

    private void intermediateSolutions(SolverRun run){
        List<IntermediateSolution> steps = base.getListIntermediateSolution().stream().
                filter(x->x.getSolverRun()==run).
                sorted(Comparator.comparing(IntermediateSolution::getTime)).
                toList();
        new LinePlot<>(steps,new LinePlotFunctions<>(IntermediateSolution::getTime, IntermediateSolution::getCost)).
                plotStyle(FIGURE).
                width(23).height(12).
                caption("Cost of Intermediate Solutions").
                xlabel("Time (s)").ylabel("Cost").
                generate().latex(tex);
    }

    private void latenessPlot(Solution sol){
        List<JobAssignment> list = base.getListJobAssignment().stream().filter(x->x.getSolution()==sol).toList();
        int maxDelay = list.stream().mapToInt(JobAssignment::getLate).max().orElse(0);
        int maxEarly = list.stream().mapToInt(JobAssignment::getEarly).max().orElse(0);
        new ScatterPlot<>(list,
                new ScatterPlotFunctions<>(JobAssignment::getEnd, this::earlyLate, this::earlyLate)).
                addLevel("Max Delay: "+String.format("%,d",maxDelay),"draw=red",maxDelay).
                addLevel("Max Early: "+String.format("%,d",maxEarly),"draw=blue",-maxEarly).
                addLevel("On-time","draw=black",0).
                addMarker(""+String.format("%,d",sol.getMakespan()),"draw=red",sol.getMakespan()).
                plotStyle(FIGURE).
                width(23).height(13).
                caption("Earliness/Lateness Over Time").
                xlabel("Time").
                ylabel("Earliness/Lateness").generate().latex(tex);
    }

    private int earlyLate(JobAssignment ja){
        if (ja.getLate() >0){
            return ja.getLate();
        } else {
            return -ja.getEarly();
        }
    }


}

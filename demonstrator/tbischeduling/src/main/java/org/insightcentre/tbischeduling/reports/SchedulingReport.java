package org.insightcentre.tbischeduling.reports;

import framework.reports.visualization.gantt.GanttDraw;
import framework.reports.visualization.gantt.GanttResourceFunctions;
import framework.reports.visualization.gantt.GanttTaskData;
import framework.reports.visualization.gantt.GanttTaskFunctions;
import framework.reports.visualization.plot.barplot.BarPlot;
import framework.reports.visualization.plot.barplot.BarPlotOrdering;
import framework.reports.visualization.plot.distributionplot.DistributionPlot;
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
import static org.insightcentre.tbischeduling.logging.LogShortcut.warning;

public class SchedulingReport extends AbstractReport{
    Hashtable<Job,String> jobColorHash;
    Hashtable<DisjunctiveResource,String> resourceColorHash;

    Color releaseColor = new Color("green!10","green","Release","gantt");

    Color lateColor = new Color("red!20","red","Late Job","gantt");
    Color earlyColor = new Color("blue!20","blue","Early Job","gantt");
    Color waitBeforeColor = new Color("cyan!20","cyan","Wait Before","gantt");
    Color waitAfterColor = new Color("brown!20","brown","Wait After","gantt");
    boolean drawWaitBefore = true;
    boolean drawWaitAfter = false;
    boolean drawLate = true;
    boolean drawEarly = true;
    boolean drawRelease = true;
    boolean showWaitLabel = true;
    boolean showLateLabel = true;
    boolean showEarlyLabel = true;
    boolean showReleaseLabel = true;
    int showWaitMinLength = 25;
    int makespan;
    int earliestStart;
    int latestEnd;

    public SchedulingReport(Scenario base, String reportDir){
        super(base,reportDir);
    }

    public void content(){
        stagecolorDefs(tex);

        section("Introduction");
        problemTable();


        section("Orders");
//        orderTable();
        new DistributionPlot<>(base.getListOrder(), Order::getProduct).
                includeZero(true).
                width(20).height(12).
                title("Number of Orders Per Product").
                xlabel("Product").ylabel("Nr Orders").
                generate().latex(tex);
        new DistributionPlot<>(base.getListOrder(), Order::getProduct,Order::getQty).
                includeZero(true).
                width(20).height(12).
                title("Qty Ordered Per Product").
                xlabel("Product").ylabel("Qty Ordered").
                generate().latex(tex);
        new DistributionPlot<>(base.getListOrder(), Order::getProduct,Order::getMinDuration).
                includeZero(true).
                width(20).height(12).
                title("Min Manufacturing Time Per Product").
                xlabel("Product").ylabel("Required Time").
                generate().latex(tex);

        new DistributionPlot<>(base.getListTask(),Task::getStage).
                includeZero(true).
                width(20).height(12).
                title("Tasks Required By Stage").
                xlabel("Stage").ylabel("Nr Tasks").
                generate().latex(tex);
        new DistributionPlot<>(base.getListTask(),Task::getStage,Task::getDuration).
                includeZero(true).
                width(20).height(12).
                title("Manufacturing Time Required By Stage").
                xlabel("Stage").ylabel("Required Duration").
                generate().latex(tex);

        new BarPlot<>(base.getListCumulativeResource(),CumulativeResource::getName,this::cumulativeDemand).
                includeZero(true).
                width(10).height(12).
                title("Cumulative Demand (Resource Time) per Cumulative Resource").
                xlabel("Resource").ylabel("Demand").
                generate().latex(tex);
        //??? the following is too simple, a better diagram is based on BarPlot and show below
//        new BarPlot<>(base.getListDisjunctiveResource(),DisjunctiveResource::getName,this::disjunctiveDemand).
//                includeZero(true).
//                width(10).height(12).
//                title("Potential Disjunctive Demand (Resource Time) per Disjunctive Resource").
//                xlabel("Resource").ylabel("Demand").
//                generate().latex(tex);
        BarPlot<DisjunctiveResource,String> bp = new BarPlot<>();
        //??? must add each plot individually, cannot chain addPlot calls
        bp.addPlot("WiP",base.getListDisjunctiveResource(),DisjunctiveResource::getName,this::wipDemand);
        bp.addPlot("Downtime",base.getListDisjunctiveResource(),DisjunctiveResource::getName,this::downtimeDemand);
        bp.addPlot("Required",base.getListDisjunctiveResource(),DisjunctiveResource::getName,this::requiredDisjunctiveDemand);
        bp.addPlot("Potential",base.getListDisjunctiveResource(),DisjunctiveResource::getName,this::potentialDisjunctiveDemand);
        bp.includeZero(true).
                // needs fixed ordering to that plots align properly
                ordering(BarPlotOrdering.LABEL).
                // stack the bars on top of each other, otherwise there is not enough room
                stacked().
                // legend outside to not obstruct plot
                legendPos("outer north east").
                width(20).height(12).
                title("Potential Disjunctive Demand (Resource Time) per Disjunctive Resource").
                xlabel("Resource").ylabel("Demand").
                generate().latex(tex);

        if (base.getListSolution().size() > 0) {
            clearpage();
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


    private int wipDemand(DisjunctiveResource r){
        return base.getListWiP().stream().filter(x->x.getDisjunctiveResource()==r).mapToInt(WiP::getDuration).sum();
    }
    private int downtimeDemand(DisjunctiveResource r){
        return base.getListDowntime().stream().filter(x->x.getDisjunctiveResource()==r).mapToInt(Downtime::getDuration).sum();
    }

    private int disjunctiveDemand(DisjunctiveResource r){
        return base.getListTask().stream().mapToInt(x->disjunctiveDemand(x,r)).sum();
    }
    private int potentialDisjunctiveDemand(DisjunctiveResource r){
        return base.getListTask().stream().mapToInt(x->potentialDemand(x,r)).sum();
    }
    private int requiredDisjunctiveDemand(DisjunctiveResource r){
        return base.getListTask().stream().mapToInt(x->requiredDemand(x,r)).sum();
    }
    private int disjunctiveDemand(Task t,DisjunctiveResource r){
        if (t.getMachines().contains(r)){
            return t.getDuration();
        } else {
            return 0;
        }
    }
    private int requiredDemand(Task t,DisjunctiveResource r){
        if (t.getMachines().size()==1 && t.getMachines().contains(r)){
            return t.getDuration();
        } else {
            return 0;
        }
    }
    private int potentialDemand(Task t,DisjunctiveResource r){
        if (t.getMachines().size() > 1 && t.getMachines().contains(r)){
            return t.getDuration();
        } else {
            return 0;
        }
    }

    private int cumulativeDemand(CumulativeResource r){
        Hashtable<ProcessStep,Integer> hash = new Hashtable<>();
        for(CumulativeNeed cn:base.getListCumulativeNeed().stream().filter(x->x.getCumulativeResource()==r).toList()){
            hash.put(cn.getProcessStep(),cn.getDemand());
        }
        return base.getListTask().stream().mapToInt(x->demandTime(x,hash)).sum();
    }

    private int demandTime(Task t,Hashtable<ProcessStep,Integer> hash){
        Integer need = hash.get(t.getProcessStep());
        if (need ==null || need ==0){
            return 0;
        }
        return t.getDuration()*need;
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
                addStringColumn(st("Objective","Type"),x->x.getSolverRun().getObjectiveType().toString()).
                addIntegerColumn(st("Weight", "Makespan"), x -> x.getSolverRun().getWeightMakespan()).
                addIntegerColumn(st("Weight", "Flowtime"), x -> x.getSolverRun().getWeightFlowtime()).
                addIntegerColumn(st("Weight", "Lateness"), x -> x.getSolverRun().getWeightLateness()).
                addIntegerColumn(st("Weight", "Earliness"), x -> x.getSolverRun().getWeightEarliness()).
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
                addDoubleColumn(st("Gap","Percent"),Solution::getGapPercent,"%5.2f",new KPIRanking<>(SmallerIsBetter)).
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
        makespan = sol.getMakespan();
        for(List<Job> jobs:split) {
            int height = (int)Math.round(jobs.size()*base.getGanttLineHeight());
            List<TaskAssignment> toDraw = tasks.stream().filter(x->jobs.contains(x.getJobAssignment().getJob())).toList();
            earliestStart = toDraw.stream().mapToInt(TaskAssignment::getStart).min().orElse(0);
            latestEnd = toDraw.stream().mapToInt(TaskAssignment::getEnd).max().orElse(0);
            GanttDraw<Job, TaskAssignment> gd = new GanttDraw<>(jobs,
                    new GanttResourceFunctions<>(this::nameOf, this::resourceStyle),
                    toDraw, new GanttTaskFunctions<>(x -> xcoor(x.getStart()),
                    x -> xcoor(x.getEnd()),
                    this::job,
                    x -> "",
                    this::taskStyle,
                    this::ganttTaskDraw));
            gd.addMarker(sol.getMakespan(), "draw=makespancolor,thick",
                            "Cmax: " + sol.getMakespan(),
                            "above left,font=\\scriptsize").
                    width(base.getGanttWidth()).height(height).
                        title("Job Gantt for Solution " + sol.getName() + (parts>1?" (Part " + part + " of " + parts + ")":"")).
                    label("jobGantt"+part);
            for (int i = minStage; i <= maxStage; i++) {
                gd.addLegendEntry("Stage" + i, "draw=black,fill=stagecolor" + i + "!10,font=\\scriptsize");
            }
            gd.addLegendEntry(lateColor.getDescription(), "draw="+lateColor.getLabel()+",font=\\tiny");
            gd.addLegendEntry(earlyColor.getDescription(), "draw="+earlyColor.getLabel()+",font=\\tiny");
            gd.addLegendEntry(releaseColor.getDescription(), "draw="+releaseColor.getLabel()+",font=\\tiny");
            gd.addLegendEntry("Wait", "draw="+waitBeforeColor.getLabel()+",font=\\tiny");
            gd.generate().latex(tex);
            part++;
        }

    }

    private String ganttTaskDraw(GanttDraw gd, GanttTaskData item){
        if (item.getItem() instanceof TaskAssignment) {
            TaskAssignment ta = (TaskAssignment) item.getItem();
            int waitBefore = (ta.getWaitBefore() == null ? 0 : ta.getWaitBefore());
            int waitAfter = (ta.getWaitAfter() == null ? 0 : ta.getWaitAfter());
            double mid = gd.ycoor(item.getY() + gd.getTaskHeight() / 2);
            double top = gd.ycoor(item.getY() + gd.getTaskHeight());
            double bottom = gd.ycoor(item.getY());
            double left = Math.max(0, gd.xcoor(gd.start(item) - waitBefore));
            double fullLeft = gd.xcoor(gd.start(item) - waitBefore);
            double start = gd.xcoor(gd.start(item));
            double end = gd.xcoor(gd.end(item));
            double endWait = gd.xcoor(gd.end(item)+ waitAfter);
            double fullDue = gd.xcoor(ta.getJobAssignment().getJob().getOrder().getDue());
            double due = gd.xcoor(Math.max(earliestStart,Math.min(makespan+10,ta.getJobAssignment().getJob().getOrder().getDue())));
            double release = gd.xcoor(Math.max(earliestStart,ta.getJobAssignment().getJob().getOrder().getRelease()));
            double fullRelease = gd.xcoor(ta.getJobAssignment().getJob().getOrder().getRelease());
            int releaseWait = gd.start(item)-ta.getJobAssignment().getJob().getOrder().getRelease();
            String wbc = waitBeforeColor.getLabel();
            String wac = waitAfterColor.getLabel();
            String lac = lateColor.getLabel();
            String eac = earlyColor.getLabel();
            String rec = releaseColor.getLabel();
            return rectangle((String)item.getF().getStyle().apply(item.getItem()),
                             start, bottom, end, top,
                             (String)item.getF().getLabel().apply(item.getItem())) +
                    // draw waitBefore line
                    (drawWaitBefore && waitBefore > 0 ? line(wbc, left, mid, start, mid,waitLabel(waitBefore)) +
                                                        (fullLeft >= 0 ?
                                                              line(wbc,left, bottom, left, top) : "") +
                                                        line(wbc, start, bottom, start, top)
                            : "") +
                    // draw waitAfter line
                    (drawWaitAfter && waitAfter > 0 ? line(wac, end, top, endWait, top) +
                            line(wac, end, bottom, end, top) +
                            line(wac, endWait, bottom, endWait, top)
                            : "")+
                    // draw late line between due date and end of last task
                    (drawLate && isLate(ta)?
                            thickLine(lac,due,mid,end,mid)+
                                    // thick line at end of task plus text for lateness
                                    thickLine(lac,end,bottom,end,top)+text(end,mid,lateLabel(ta))+
                                    // marker at due date only if due date is visible
                                    (fullDue >= due?thickLine(lac,fullDue,bottom,fullDue,top):"")
                            :"")+
                    // draw early line based on end of last task and due date
                    (drawEarly && isEarly(ta)?
                            thickLine(eac,end,mid,due,mid,earlyLabel(ta))+
                                    thickLine(eac,end,bottom,end,top)+
                                    (fullDue<= due?thickLine(eac,fullDue,bottom,fullDue,top):"")+
                                    text(due,mid,dueLabel(ta))
                            :"")+
                    // draw release line from relase date or start of gantt to start of first task
                    (drawRelease && isReleased(ta) ?
                            thickLine(rec,release,mid,start,mid,releaseLabel(releaseWait))+
                                    (fullRelease >= release ?thickLine(rec,fullRelease,bottom,fullRelease,top):"")+
                                    thickLine(rec,start,bottom,start,top)
                            : "");
        } else {
            warning("bad item class in task draw "+item.getItem().getClass());
            return "";
        }
    }

    // draw the lateness if this is the last task && and the job is late
    private boolean isReleased(TaskAssignment ta){
        return ta.getTask().getFollows().size() == 0;
    }
    private boolean isLate(TaskAssignment ta){
        return ta.getJobAssignment().getLate() > 0 && ta.getTask().getPrecedes().size() == 0;
    }
    private boolean isEarly(TaskAssignment ta){
        return ta.getJobAssignment().getEarly() > 0 && ta.getTask().getPrecedes().size() == 0;
    }
    private String lateLabel(TaskAssignment ta){
        return "\\scriptsize "+String.format("%,d",ta.getJobAssignment().getLate());
    }
    private String earlyLabel(TaskAssignment ta){
        return "\\tiny "+String.format("%,d",ta.getJobAssignment().getEarly());
    }

    private String waitLabel(int wait){
        return (showWaitLabel && wait > showWaitMinLength? "\\tiny "+String.format("%,d",wait):"");

    }

    private String releaseLabel(int releaseWait){
        return (showReleaseLabel && releaseWait > showWaitMinLength ? "\\tiny "+String.format("%,d",releaseWait):"");
    }

    private String dueLabel(TaskAssignment ta){
        return "\\tiny "+ta.getJobAssignment().getJob().getOrder().getDue();
    }

    private String line(String color,double x1,double y1,double x2, double y2,String label){
        return String.format("\\draw[%s,thin] (%f,%f) -- node[above,black] {%s} (%f,%f);\n",color,x1,y1,label,x2,y2);
    }
    private String line(String color,double x1,double y1,double x2, double y2){
        return String.format("\\draw[%s,thin] (%f,%f) -- (%f,%f);\n",color,x1,y1,x2,y2);
    }
    private String thickLine(String color,double x1,double y1,double x2, double y2,String label){
        return String.format("\\draw[%s,thick] (%f,%f) -- node[above,black] {%s} (%f,%f);\n",color,x1,y1,label,x2,y2);
    }
    private String thickLine(String color,double x1,double y1,double x2, double y2){
        return String.format("\\draw[%s,thick] (%f,%f) -- (%f,%f);\n",color,x1,y1,x2,y2);
    }
    private String rectangle(String color,double x1,double y1,double x2, double y2,String label){
        return String.format("\\draw[%s] (%f,%f) rectangle node {%s} (%f,%f);\n",color,x1,y1,label,x2,y2);
    }

    private String text(double x,double y,String text){
        return String.format("\\node[right,black] at (%f,%f) {%s};\n",x,y,text);
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
        ScatterPlot<JobAssignment> sp = new ScatterPlot<>(list,
                new ScatterPlotFunctions<>(JobAssignment::getEnd, this::earlyLate, this::earlyLate));
        sp.addLevel("Max Delay: "+String.format("%,d",maxDelay),"draw=red",maxDelay).
                addLevel("Max Early: "+String.format("%,d",maxEarly),"draw=blue",-maxEarly);
        if (maxDelay > 0) {
            // only draw on-time line if max delay > 0 to avoid overlap
            sp.addLevel("On-time", "draw=black", 0);
        }
        sp.addMarker(""+String.format("%,d",sol.getMakespan()),"draw=red",sol.getMakespan()).
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

package org.insightcentre.tbischeduling.reports;

import framework.reports.visualization.gantt.GanttDraw;
import framework.reports.visualization.gantt.GanttResourceFunctions;
import framework.reports.visualization.gantt.GanttTaskFunctions;
import framework.reports.visualization.plot.barplot.BarPlot;
import framework.reports.visualization.plot.profileplot.AddProfile;
import framework.reports.visualization.plot.profileplot.ProfilePlot;
import framework.reports.visualization.tabular.TableStyle;
import framework.reports.visualization.tabular.table.TableDraw;
import org.insightcentre.tbischeduling.datamodel.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

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

        new TableDraw<>("Problem",base.getListProblem()).
                addStringColumn("Name",this::nameOf).
                addBooleanColumn(st("Timepoints","as","Date"), Problem::getTimePointsAsDate).
                addIntegerColumn(st("Nr","Products"),Problem::getNrProducts).
                addIntegerColumn(st("Nr","Process"),Problem::getNrProcesses).
                addIntegerColumn(st("Nr","Disjunctive","Resources"),Problem::getNrDisjunctiveResources).
                addIntegerColumn(st("Nr","Cumulative","Resources"),Problem::getNrCumulativeResources).
                addIntegerColumn(st("Nr","Orders"),Problem::getNrOrders).
                addIntegerColumn(st("Nr","Jobs"),Problem::getNrJobs).
                addIntegerColumn(st("Nr","Tasks"),Problem::getNrTasks).
                generate().latex(tex);
        section("Orders");

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

        if (base.getListSolution().size() > 0) {
            section("Solution");

            new TableDraw<>("Solutions (Total " + base.getListSolution().size() + ")", base.getListSolution()).
                    addStringColumn("Name", this::nameOf).
                    addStringColumn(st("Solver","Status"),x->x.getSolverStatus().toString()).
                    addIntegerColumn(st("Objective", "Value"), Solution::getObjectiveValue,"%,d").
                    addDoubleColumn("Bound",Solution::getBound,"%5.2f").
                    addDoubleColumn("Gap",Solution::getGap,"%5.2f").
                    addIntegerColumn("Makespan", Solution::getMakespan,"%,d").
                    addIntegerColumn("Flowtime", Solution::getFlowtime,"%,d").
                    addIntegerColumn(st("Total", "Lateness"), Solution::getTotalLateness,"%,d").
                    addIntegerColumn(st("Max", "Lateness"), Solution::getMaxLateness,"%,d").
                    addIntegerColumn(st("Nr", "Late"), Solution::getNrLate,"%,d").
                    addIntegerColumn(st("Total", "Earliness"), Solution::getTotalEarliness,"%,d").
                    addIntegerColumn(st("Max", "Earliness"), Solution::getMaxEarliness,"%,d").
                    addIntegerColumn(st("Nr", "Early"), Solution::getNrEarly,"%,d").
                    addStringColumn(st("Model","Type"),x->x.getSolverRun().getModelType().toString()).
                    addStringColumn(st("Objective","Type"),x->x.getSolverRun().getObjectiveType().toString()).
                    addDoubleColumn("Time",x->x.getSolverRun().getTime(),"%5.2f").
                    generate().latex(tex);

            Solution sol = Solution.findLast(base);
            assert(sol!=null);
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

            initColors();

            machineGantt(sol,machines,tasks,minStage,maxStage);
            jobGantt(sol,jobs,tasks,minStage,maxStage);

            utilizationChart(sol);
            for(CumulativeResource cr:base.getListCumulativeResource()) {
                cumulProfile(sol,cr);
            }


        } else {
            section("No Solutions");
        }
    }

    private void machineGantt(Solution sol,List<DisjunctiveResource> machines,
                              List<TaskAssignment> tasks,int minStage,int maxStage) {

        GanttDraw<DisjunctiveResource, TaskAssignment> gd = new GanttDraw<>(machines,
                new GanttResourceFunctions<>(this::nameOf, this::resourceStyle),
                tasks,
                new GanttTaskFunctions<>(x -> xcoor(x.getStart()), x -> xcoor(x.getEnd()),
                        TaskAssignment::getDisjunctiveResource,
                        x -> "", this::taskStyle));
        gd.addMarker(sol.getMakespan(), "draw=red,thick",
                        "Cmax: " + sol.getMakespan(),
                        "above left,font=\\scriptsize").
                width(23).height(7).
                title("Machine Gantt for Solution " + sol.getName()).
                label("resourceGantt");
        for (int i = minStage; i <= maxStage; i++) {
            gd.addLegendEntry("Stage" + i, "draw=black,fill=stagecolor" + i + "!10,font=\\scriptsize");
        }
        gd.addLegendEntry("Late","draw=red,font=\\scriptsize");

        gd.generate().latex(tex);
    }

    private void jobGantt(Solution sol,List<Job> jobs,List<TaskAssignment> tasks,int minStage,int maxStage){
        GanttDraw<Job,TaskAssignment> gd = new GanttDraw<>(jobs,
                new GanttResourceFunctions<>(this::nameOf, this::resourceStyle),
                tasks,new GanttTaskFunctions<>(x->xcoor(x.getStart()),x->xcoor(x.getEnd()),
                this::job,
                x->"", this::taskStyle));
        gd.addMarker(sol.getMakespan(),"draw=red,thick",
                        "Cmax: "+sol.getMakespan(),
                        "above left,font=\\scriptsize").
                width(23).height(13).
                title("Job Gantt for Solution "+sol.getName()).
                label("jobGantt");
        for (int i = minStage; i <= maxStage; i++) {
            gd.addLegendEntry("Stage" + i, "draw=black,fill=stagecolor" + i + "!10,font=\\scriptsize");
        }
        gd.addLegendEntry("Late","draw=red,font=\\scriptsize");
        gd.generate().latex(tex);

    }


    private Job job(TaskAssignment ta){
        return ta.getJobAssignment().getJob();
    }

    private void initColors(){
        jobColorHash = new Hashtable<>();
        resourceColorHash = new Hashtable<>();
        for(Job j:base.getListJob()){
            String color = "green!10";
            jobColorHash.put(j,color);
        }
        for(DisjunctiveResource r:base.getListDisjunctiveResource()){
            String color = "blue!10";
            resourceColorHash.put(r,color);
        }
    }

    private int xcoor(int x){
        return x;
    }

    private String resourceStyle(DisjunctiveResource r){
        return "draw=black,fill=blue!10,font=\\scriptsize";
    }
    private String resourceStyle(Job j){
        return "draw=black,fill=green!10,font=\\scriptsize";
    }

    private String taskStyle(TaskAssignment ta){
        return "draw="+lateColor(ta)+",fill="+stageColor(ta.getTask().getStage());
    }

    private String lateColor(TaskAssignment ta){
        if (ta.getJobAssignment().getLate() > 0){
            return "red";
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
                width(12).height(10).
                title("Resource Utilization for Solution "+sol.getName()).
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
        pp.legendPos("outer north east").width(21).height(12).
                title("Cumulative Resource Use "+cr.getName()).
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


}

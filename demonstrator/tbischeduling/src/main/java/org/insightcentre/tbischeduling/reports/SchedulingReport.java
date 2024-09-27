package org.insightcentre.tbischeduling.reports;

import framework.reports.visualization.tabular.TableStyle;
import framework.reports.visualization.tabular.table.TableDraw;
import org.insightcentre.tbischeduling.datamodel.Order;
import org.insightcentre.tbischeduling.datamodel.Problem;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.Solution;

public class SchedulingReport extends AbstractReport{
    public SchedulingReport(Scenario base, String reportDir){
        super(base,reportDir);
    }

    public void content(){
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
                addStringColumn("Process",x->nameOf(x.getProduct().getProcess())).
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
                    addIntegerColumn(st("Total", "Earliness"), Solution::getTotalEarliness,"%,d").
                    addIntegerColumn(st("Max", "Earliness"), Solution::getMaxEarliness,"%,d").
                    addStringColumn(st("Model","Type"),x->x.getSolverRun().getModelType().toString()).
                    addStringColumn(st("Objective","Type"),x->x.getSolverRun().getObjectiveType().toString()).
                    addDoubleColumn("Time",x->x.getSolverRun().getTime(),"%5.2f").
                    generate().latex(tex);
        } else {
            section("No Solutions");
        }
    }

}

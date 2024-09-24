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
//                addIntegerColumn(st("Nr","Products"),Problem::getNrProduct).
//                addIntegerColumn(st("Nr","Process"),Problem::getNrProcess).
//                addIntegerColumn(st("Nr","Disjunctive","Resources"),Problem::getNrDisjunctiveResources).
//                addIntegerColumn(st("Nr","Cumulative","Resources"),Problem::getNrCumulativeResources).
//                addIntegerColumn(st("Nr","Process"),Problem::getNrProcess).
//                addIntegerColumn(st("Nr","Orders"),Problem::getNrOrders).
//                addIntegerColumn(st("Nr","Jobs"),Problem::getNrJobs).
//                addIntegerColumn(st("Nr","Tasks"),Problem::getNrTasks).
                generate().latex(tex);
        section("Orders");

        new TableDraw<>("Orders (Total "+base.getListOrder().size()+")",base.getListOrder()).
                addStringColumn("Name",this::nameOf).
                addStringColumn("Product",x->nameOf(x.getProduct())).
                addStringColumn("Process",x->nameOf(x.getProduct().getProcess())).
                addIntegerColumn("Qty", Order::getQty,"%,d").
                addIntegerColumn("Due", Order::getDue,"%,d").
//                addDateTimeColumn(st("Due","Date"),Order::getDueDate).
                tableStyle(TableStyle.LONGTABLE).
                generate().latex(tex);

        if (base.getListSolution().size() > 0) {
            section("Solution");

            new TableDraw<>("Solutions (Total " + base.getListSolution().size() + ")", base.getListSolution()).
                    addStringColumn("Name", this::nameOf).
                    addIntegerColumn(st("Objective", "Value"), Solution::getObjectiveValue,"%,d").
                    generate().latex(tex);
        } else {
            section("No Solutions");
        }
    }

}

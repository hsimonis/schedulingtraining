package org.insightcentre.tbischeduling.reports;

import framework.reports.visualization.plot.lineplot.LinePlot;
import framework.reports.visualization.plot.lineplot.LinePlotFunctions;
import framework.reports.visualization.plot.scatterplot.ScatterPlot;
import framework.reports.visualization.plot.scatterplot.ScatterPlotFunctions;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.Summary;

public class ExportReport extends AbstractReport{
    public ExportReport(Scenario base, String reportDir){
        super(base,reportDir);
    }

    public void content(){
        section("Introduction");
        paragraph("This report compares the summary results for the 480 instances of the cumulative flow-shop benchmarks by different criteria. We compare the local search and CP results");

        clearpage();
        section("Direct CP/Local Comparison for FS, PNW, NW");

        LinePlot<Summary> localLpFS = new LinePlot<>();
        markers(localLpFS);
//        localLpFS.addRegion("","lightgray",0,120).

        localLpFS.addPlot("CP FS",base.getListSummary(),new LinePlotFunctions<>(Summary::getNr, Summary::getCpFS));
        localLpFS.addPlot("Local FS",base.getListSummary(),new LinePlotFunctions<>(Summary::getNr, Summary::getLocalFS));
        localLpFS.title("Local/CP FS").
                xlabel("Instance").
                ylabel("Cost").
                width(22).
                height(12).
                generate().
                latex(tex);

        LinePlot<Summary> localLpPWN = new LinePlot<>();
        markers(localLpPWN);
        localLpPWN.addPlot("CP PNW",base.getListSummary(),new LinePlotFunctions<>(Summary::getNr, Summary::getCpPNW));
        localLpPWN.addPlot("Local PNW",base.getListSummary(),new LinePlotFunctions<>(Summary::getNr, Summary::getLocalPNW));
        localLpPWN.title("Local/CP PWN").
                xlabel("Instance").
                ylabel("Cost").
                width(22).
                height(12).
                generate().
                latex(tex);

        LinePlot<Summary> localLpNW = new LinePlot<>();
        markers(localLpNW);
        localLpNW.addPlot("CP NW",base.getListSummary(),new LinePlotFunctions<>(Summary::getNr, Summary::getCpNW));
        localLpNW.addPlot("Local NW",base.getListSummary(),new LinePlotFunctions<>(Summary::getNr, Summary::getLocalNW));
        localLpNW.title("Local/CP NW").
                xlabel("Instance").
                ylabel("Cost").
                width(22).
                height(12).
                generate().
                latex(tex);

        section("Local/CP Diagonal Comparison by 2D Scatter Plot for FS, PNW, NW");

        new ScatterPlot<>(base.getListSummary(), Summary::getLocalFS,Summary::getCpFS,Summary::getGapFS).
//                addLine(0,0,4500,4500).
                title("Local/CP Comparison FS Cost (Colored by Gap Percent)").
                xlabel("Local").
                ylabel("CP").
                width(22).
                height(12).
                generate().
                latex(tex);

        new ScatterPlot<>(base.getListSummary(), Summary::getLocalPNW,Summary::getCpPNW,Summary::getGapPNW).
                title("Local/CP Comparison PNW Cost (Colored by Gap Percent)").
                xlabel("Local").
                ylabel("CP").
                width(22).
                height(12).
                generate().
                latex(tex);

        new ScatterPlot<>(base.getListSummary(), Summary::getLocalNW,Summary::getCpNW,Summary::getGapNW).
                title("Local/CP Comparison NW Cost (Colored by Gap Percent)").
                xlabel("Local").
                ylabel("CP").
                width(22).
                height(12).
                generate().
                latex(tex);

        section("Comparison Local/CP by Difference Local-CP for FS, PNW, NW");

        ScatterPlot<Summary> sp1 = new ScatterPlot<>();
        groupMarkers(sp1);
        sp1.addPlot("",base.getListSummary(), new ScatterPlotFunctions<>(this::group,this::diffFS,Summary::getGapFS));

        sp1.title("FS Grouped Diff Local-CP (Colored by CP Gap Percent)").
                xlabel("Group").
                ylabel("Diff").
                width(22).
                height(12).
                generate().
                latex(tex);

        ScatterPlot<Summary> sp2 = new ScatterPlot<>();
        groupMarkers(sp2);
        sp2.addPlot("",base.getListSummary(), new ScatterPlotFunctions<>(this::group,this::diffPNW,Summary::getGapPNW));
        sp2.title("PWN Grouped Diff Local-CP (Colored by CP Gap Percent)").
                xlabel("Group").
                ylabel("Diff").
                width(22).
                height(12).
                generate().
                latex(tex);

        ScatterPlot<Summary> sp3 = new ScatterPlot<>();
        groupMarkers(sp3);
        sp3.addPlot("",base.getListSummary(), new ScatterPlotFunctions<>(this::group,this::diffNW,Summary::getGapNW));
        sp3.title("NW Grouped Diff Local-CP (Colored by CP Gap Percent)").
                xlabel("Group").
                ylabel("Diff").
                width(22).
                height(12).
                generate().
                latex(tex);


        section("Cost CP for Problem Variants");

        LinePlot<Summary> lp = new LinePlot<>();
        markers(lp);
        lp.addPlot("CP FS",base.getListSummary(),new LinePlotFunctions<>(Summary::getNr, Summary::getCpFS));
        lp.addPlot("CP PNW",base.getListSummary(),new LinePlotFunctions<>(Summary::getNr, Summary::getCpPNW));
        lp.addPlot("CP NW",base.getListSummary(),new LinePlotFunctions<>(Summary::getNr, Summary::getCpNW));
        lp.title("Cost CP").
                xlabel("Instance").
                ylabel("Cost").
                width(22).
                height(12).
                generate().
                latex(tex);

        section("Gap CP for Problem Variants");

        LinePlot<Summary> gapLp = new LinePlot<>();
        markers(gapLp);
        gapLp.addPlot("CP FS",base.getListSummary(),new LinePlotFunctions<>(Summary::getNr, Summary::getGapFS));
        gapLp.addPlot("CP PNW",base.getListSummary(),new LinePlotFunctions<>(Summary::getNr, Summary::getGapPNW));
        gapLp.addPlot("CP NW",base.getListSummary(),new LinePlotFunctions<>(Summary::getNr, Summary::getGapNW));
        gapLp.title("Gap CP").
                xlabel("Instance").
                ylabel("Optimality Gap Percent").
                width(22).
                height(12).
                generate().
                latex(tex);

        section("Grouped Results for FS, PNW, NW");

        new ScatterPlot<>(base.getListSummary(), this::group,Summary::getCpFS,Summary::getGapFS).
                title("FS Grouped Cost (Colored by Gap Percent)").
                xlabel("Group").
                ylabel("Cost").
                width(22).
                height(12).
                generate().
                latex(tex);

        new ScatterPlot<>(base.getListSummary(), this::group,Summary::getCpPNW,Summary::getGapPNW).
                title("PNW Grouped Cost (Colored by Gap Percent)").
                xlabel("Group").
                ylabel("Cost").
                width(22).
                height(12).
                generate().
                latex(tex);

        new ScatterPlot<>(base.getListSummary(), this::group,Summary::getCpNW,Summary::getGapNW).
                title("NW Grouped Cost (Colored by Gap Percent)").
                xlabel("Group").
                ylabel("Cost").
                width(22).
                height(12).
                generate().
                latex(tex);

        section("Diff CPO-CPSat");

        ScatterPlot<Summary> sp4 = new ScatterPlot<>();
        diffMarkers(sp4);
        sp4.addPlot("",base.getListSummary(), new ScatterPlotFunctions<>(this::group,this::diffFSCPO,Summary::getGapFS)).
             title("FS Grouped Diff CPO-CPSat (Colored by CP Gap Percent)").
                xlabel("Group").
                ylabel("Diff").
                width(22).
                height(12).
                generate().
                latex(tex);

        ScatterPlot<Summary> sp5 = new ScatterPlot<>();
        diffMarkers(sp5);
        sp5.addPlot("",base.getListSummary(), new ScatterPlotFunctions<>(this::group,this::diffPNWCPO,Summary::getGapPNW)).
                addLevel("","lightgray",0).
                title("PWN Grouped Diff CPO-CPSat (Colored by CP Gap Percent)").
                xlabel("Group").
                ylabel("Diff").
                width(22).
                height(12).
                generate().
                latex(tex);

        ScatterPlot<Summary> sp6 = new ScatterPlot<>();
        diffMarkers(sp6);
        sp6.addPlot("",base.getListSummary(), new ScatterPlotFunctions<>(this::group,this::diffNWCPO,Summary::getGapNW)).
                addLevel("","lightgray",0).
                title("NW Grouped Diff CPO-CPSat (Colored by CP Gap Percent)").
                xlabel("Group").
                ylabel("Diff").
                width(22).
                height(12).
                generate().
                latex(tex);



    }

    private int group(Summary s){
        return s.getNr()/10;
    }

    private int diffFS(Summary s){
        return s.getLocalFS()-s.getCpFS();
    }

    private int diffPNW(Summary s){
        return s.getLocalPNW()-s.getCpPNW();
    }

    private int diffNW(Summary s){
        return s.getLocalNW()-s.getCpNW();
    }

    private int diffFSCPO(Summary s){
        return -s.getFsCPOCost()+s.getFsCPSatCost();
    }

    private int diffPNWCPO(Summary s){
        return -s.getPnwCPOCost()+s.getPnwCPSatCost();
    }

    private int diffNWCPO(Summary s){
        return -s.getNwCPOCost()+s.getNwCPSatCost();
    }

    private void markers(LinePlot<Summary> lp){
        lp.noMarkers().
                addMarker("20 Jobs","lightgray,pos=0.8",0).
                addMarker("30 Jobs","lightgray,pos=0.8",120).
                addMarker("50 Jobs","lightgray,pos=0.8",240).
                addMarker("100 Jobs","lightgray,pos=0.8",360).
                addMarker("3 Stages","lightgray,pos=0.75",0).
                addMarker("5","dashed,lightgray,pos=0.75",40).
                addMarker("10","dashed,lightgray,pos=0.75",80).
                addMarker("3","lightgray,pos=0.75",120).
                addMarker("5","dashed,lightgray,pos=0.75",160).
                addMarker("10","dashed,lightgray,pos=0.75",200).
                addMarker("3","lightgray,pos=0.75",240).
                addMarker("5","dashed,lightgray,pos=0.75",280).
                addMarker("10","dashed,lightgray,pos=0.75",320).
                addMarker("3","lightgray,pos=0.75",360).
                addMarker("5","dashed,lightgray,pos=0.75",400).
                addMarker("10","dashed,lightgray,pos=0.75",440).
                legendPos("north west");
    }

    private void groupMarkers(ScatterPlot<Summary> sp){
        sp.addLevel("CP Better","lightgray,pos=0.95",0).
                addMarker("20 Jobs","lightgray,pos=0.9",0).
                addMarker("3 stages","dotted,lightgray,pos=0.85",0).
                addMarker("5","dashed,lightgray,pos=0.85",4).
                addMarker("10","dashed,lightgray,pos=0.85",8).
                addMarker("3","dotted,lightgray,pos=0.85",12).
                addMarker("5","dashed,lightgray,pos=0.85",16).
                addMarker("10","dashed,lightgray,pos=0.85",20).
                addMarker("3","dotted,lightgray,pos=0.85",24).
                addMarker("5","dashed,lightgray,pos=0.85",28).
                addMarker("10","dashed,lightgray,pos=0.85",32).
                addMarker("3","dotted,lightgray,pos=0.85",36).
                addMarker("5","dashed,lightgray,pos=0.85",40).
                addMarker("10","dashed,lightgray,pos=0.85",44).
                addMarker("30 Jobs","lightgray,pos=0.9",12).
                addMarker("50 Jobs","lightgray,pos=0.9",24).
                addMarker("100 Jobs","lightgray,pos=0.9",36).
                addLabel("Capa","lightgray",-3,-10).
                addLabel("10","lightgray",0,-10).
                addLabel("20","lightgray",1,-10).
                addLabel("30","lightgray",2,-10).
                addLabel("50","lightgray",3,-10).
                addLabel("Local Better","lightgray",48,-10);
    }

    private void diffMarkers(ScatterPlot<Summary> sp){
        sp.addLevel("CPO Better","lightgray,pos=0.93",0).
                addMarker("20 Jobs","lightgray,pos=0.9",0).
                addMarker("3 stages","dotted,lightgray,pos=0.85",0).
                addMarker("5","dashed,lightgray,pos=0.85",4).
                addMarker("10","dashed,lightgray,pos=0.85",8).
                addMarker("3","dotted,lightgray,pos=0.85",12).
                addMarker("5","dashed,lightgray,pos=0.85",16).
                addMarker("10","dashed,lightgray,pos=0.85",20).
                addMarker("3","dotted,lightgray,pos=0.85",24).
                addMarker("5","dashed,lightgray,pos=0.85",28).
                addMarker("10","dashed,lightgray,pos=0.85",32).
                addMarker("3","dotted,lightgray,pos=0.85",36).
                addMarker("5","dashed,lightgray,pos=0.85",40).
                addMarker("10","dashed,lightgray,pos=0.85",44).
                addMarker("10","dotted,lightgray,pos=0.8",0).
                addMarker("20","dotted,lightgray,pos=0.75",1).
                addMarker("30","dotted,lightgray,pos=0.8",2).
                addMarker("50","dotted,lightgray,pos=0.75",3).
                addMarker("30 Jobs","lightgray,pos=0.9",12).
                addMarker("50 Jobs","lightgray,pos=0.9",24).
                addMarker("100 Jobs","lightgray,pos=0.9",36).
//                addLabel("Capa","lightgray",-3,-10).
        addLabel("CPSat Better","lightgray",48,-10);

    }


}

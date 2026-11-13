package org.insightcentre.tbischeduling;

import framework.reports.visualization.plot.lineplot.LinePlot;
import framework.reports.visualization.plot.lineplot.LinePlotFunctions;
import org.insightcentre.tbischeduling.datamodel.IntermediateSolution;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.reports.AbstractReport;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.insightcentre.tbischeduling.importer.Reset.resetAll;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class IntermediateResults extends AbstractReport {
    Scenario base;
//    static int instance = 421;
//    static String params = "100_5_30_rep_1";
    static int instance = 438;
    static String params = "100_5_50_rep_8";

    public static void main(String[] args) {
        Scenario base = new Scenario();


        readIntermediate(base, "cfssomewait/", "resultsprec/", instance,params );
        new IntermediateResults(base,"reports/").produce("intermediate"+instance,"Intermediate Results for Instance "+instance,"H. Simonis");
    }

    public IntermediateResults(Scenario base,String reportDir){
        super(base,reportDir);
        this.base = base;
    }

    public void content(){
        section("Intermediate");
        LinePlot<IntermediateSolution> lp = new LinePlot<>();
        lp.addPlot("Cost",base.getListIntermediateSolution(),new LinePlotFunctions<>(IntermediateSolution::getTime,IntermediateSolution::getCost));
        lp.addPlot("Bound",base.getListIntermediateSolution(),new LinePlotFunctions<>(IntermediateSolution::getTime,IntermediateSolution::getBound));

        lp.title("Intermediate Solutions for Instance "+instance).
                xlabel("Time (s)").
                ylabel("Cost").
                width(23).height(12).
                generate().latex(tex);
    }


    private static void readIntermediate(Scenario base, String problemDir, String resultDir, int instance, String params) {
        String resultFile = problemDir + resultDir + instance + "_" + params + ".json";
        JSONObject root;
        try {
            resetAll(base);
            info("Reading "+resultFile);
            String contents = new String(Files.readAllBytes(new File(resultFile).toPath()));
            root = new JSONObject(contents);
            JSONArray intermediate = root.getJSONArray("intermediate");
            int size = intermediate.length();
            for(int i=0;i<size;i++){
                JSONObject entry = intermediate.getJSONObject(i);
                IntermediateSolution is = new IntermediateSolution(base);
                is.setTime(entry.getDouble("time"));
                is.setCost(entry.getDouble("cost"));
                is.setBound(entry.getDouble("bound"));
                is.setGapPercent(entry.getDouble("gapPercent"));
                is.setNr(entry.getInt("nr"));
                is.setName("IS"+is.getNr());
            }
            info("Intermediate Solutions read "+size);

        } catch (IOException e) {
            severe("Cannot read file " + resultFile);
        }
    }

}

package org.insightcentre.tbischeduling;

import framework.reports.visualization.plot.lineplot.LinePlot;
import framework.reports.visualization.plot.lineplot.LinePlotFunctions;
import org.insightcentre.tbischeduling.datamodel.IntermediateSolution;
import org.insightcentre.tbischeduling.datamodel.Problem;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverRun;
import org.insightcentre.tbischeduling.importer.ImportExport;
import org.insightcentre.tbischeduling.importer.Reset;
import org.insightcentre.tbischeduling.reports.AbstractReport;
import org.insightcentre.tbischeduling.reports.ExportReport;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import static framework.reports.visualization.plot.AxisType.LogX;
import static org.insightcentre.tbischeduling.importer.Reset.resetAll;
import static org.insightcentre.tbischeduling.logging.LogShortcut.*;

public class IntermediateResults extends AbstractReport {
    Scenario base;
//    static int instance = 421;
//    static String params = "100_5_30_rep_1";
    static int instance = 480;
    static String params = "100_10_10_rep_1";
    static String variant = "nowait";
    static SolverRun cpo;
    static SolverRun cpSat;
    static SolverRun ls;

    public static void main(String[] args) {
        Scenario base = new Scenario();
//        new ImportExport(base,"reports/","export.csv");
//        new ExportReport(base,"reports/").produce("exportReport","Export Report","H. Simonis");
        compareSolvers(base);
//        compareVariants(base);

     }

    private static void compareSolvers(Scenario base){
        for(int i = 441;i<=480;i++) {
            instance = i;
            for (String v : new String[]{"prec", "nowait", "somewait"}) {
                variant = v;
                Reset.resetAll(base);
                cpo = new SolverRun(base);
                cpo.setName("CPO");
                cpSat = new SolverRun(base);
                cpSat.setName("CPSat");
                ls = new SolverRun(base);
                ls.setName("LS");
//            assert(params.equals(parameters(instance)));
                params = parameters(instance);
                readIntermediate(base, "cfssomewait/", "results" + variant + "/", instance, params, cpo);
                readIntermediate(base, "cfssomewait/", "results" + variant + "CPSat/", instance, params, cpSat);
                readLSResult(base, "cfssomewait/", variant + "LS/", "instance_" + instance + ".txt", ls);
                new IntermediateResults(base, "reports/").produce("intermediate" + instance + variant,
                        "Intermediate Results for Instance " + instance + " Variant " + variant,
                        "H. Simonis");
            }
        }
    }
    private static void compareVariants(Scenario base){
        for(String solver:new String[]{"","CPSat"}) {
            SolverRun solve;
            if(solver.isEmpty()){
                solve = cpo;
            }else {
                solve = cpSat;
            }
            Reset.resetAll(base);
            cpo = new SolverRun(base);
            cpo.setName("CPO");
            cpSat = new SolverRun(base);
            cpSat.setName("CPSat");
            readIntermediate(base, "cfssomewait/", "results" + "prec" + solver+"/", instance, params, solve);
            readIntermediate(base, "cfssomewait/", "results" + "somewait" + solver+"/", instance, params, solve);
            readIntermediate(base, "cfssomewait/", "results" + "nowait" + solver+"/", instance, params, solve);
            new IntermediateResults(base, "reports/").produce("variants" + instance + solver, "Intermediate Results for Variants of Instance " + instance +" solver "+solver, "H. Simonis");
        }
    }

    public IntermediateResults(Scenario base,String reportDir){
        super(base,reportDir);
        this.base = base;
    }

    public void content(){
        section("Intermediate");
        LinePlot<IntermediateSolution> lp = new LinePlot<>();
        lp.axisType(LogX);
        lp.addPlot("Cost CPO",base.getListIntermediateSolution().stream().
                filter(x->x.getSolverRun()==cpo).
                toList(),
                new LinePlotFunctions<>(IntermediateSolution::getTime,IntermediateSolution::getCost));
        lp.addPlot("Cost CPSat",base.getListIntermediateSolution().stream().
                        filter(x->x.getSolverRun()==cpSat).
                        toList(),
                new LinePlotFunctions<>(IntermediateSolution::getTime,IntermediateSolution::getCost));
        lp.addPlot("Cost LS",base.getListIntermediateSolution().stream().
                        filter(x->x.getSolverRun()==ls).
                        toList(),
                new LinePlotFunctions<>(IntermediateSolution::getTime,IntermediateSolution::getCost));
        lp.addPlot("Bound CPO",base.getListIntermediateSolution().stream().
                filter(x->x.getSolverRun()==cpo).
                toList(),
                new LinePlotFunctions<>(IntermediateSolution::getTime,IntermediateSolution::getBound));
        lp.addPlot("Bound CPSat",base.getListIntermediateSolution().stream().
                filter(x->x.getSolverRun()==cpSat).
                toList(),
                new LinePlotFunctions<>(IntermediateSolution::getTime,IntermediateSolution::getBound));
        lp.title("Intermediate Solutions for Instance "+instance+" "+variant).
                xlabel("Time (s)").
                ylabel("Cost").
                width(23).height(12).
                generate().latex(tex);
    }


    private static void readIntermediate(Scenario base, String problemDir, String resultDir, int instance, String params,SolverRun run) {
        String resultFile = problemDir + resultDir + instance + "_" + params + ".json";
        JSONObject root;
        try {
//            resetAll(base);
            info("Reading "+resultFile);
            String contents = new String(Files.readAllBytes(new File(resultFile).toPath()));
            root = new JSONObject(contents);
            if (root.has("intermediate")) {
                JSONArray intermediate = root.getJSONArray("intermediate");
                int size = intermediate.length();
                for (int i = 0; i < size; i++) {
                    JSONObject entry = intermediate.getJSONObject(i);
                    IntermediateSolution is = new IntermediateSolution(base);
                    is.setSolverRun(run);
                    is.setTime(entry.getDouble("time"));
                    is.setCost(entry.getDouble("cost"));
                    is.setBound(entry.getDouble("bound"));
                    is.setGapPercent(entry.getDouble("gapPercent"));
                    is.setNr(entry.getInt("nr"));
                    is.setName("IS" + is.getNr());
                }
                info("Intermediate Solutions read " + size);
            } else {
                warning("no intermediate "+instance+" run "+run);
            }

        } catch (IOException e) {
            severe("Cannot read file " + resultFile);
        }
    }

    private static void readLSResult(Scenario base,String problemDir,String resultDir,String fileName,SolverRun run){
        assert(problemDir.endsWith("/"));
        assert(resultDir.endsWith("/"));
        String fullName = problemDir+resultDir+fileName;
        try{
            Scanner scanner = new Scanner(new File(fullName));

            String ignore = scanner.next();
            int instance = scanner.nextInt();
            ignore = scanner.next();
            int nrJobs = scanner.nextInt();
            ignore = scanner.next();
            int nrStages = scanner.nextInt();
            ignore = scanner.next();
            int cpuParam = scanner.nextInt();
            ignore = scanner.next();
            int obj = scanner.nextInt();

            info("problem "+instance+" jobs "+nrJobs+" stages"+ nrStages+" cpu "+cpuParam+" obj "+obj);
            ignore = scanner.next();
            ignore = scanner.next();
            for(int i=0;i<nrJobs;i++) {
                int seq = scanner.nextInt();
            }
            for(int i=0;i<nrJobs;i++){
                int job = scanner.nextInt();
                for(int j=0;j<nrStages;j++){
                    int start = scanner.nextInt();
                }
            }
            int nr = 0;
            while(scanner.hasNext()) {
                double cost = scanner.nextDouble();
                double time = scanner.nextDouble();
//                info("intermed "+cost+" "+time);
                IntermediateSolution is = new IntermediateSolution(base);
                is.setSolverRun(run);
                is.setName("LS"+nr);
                is.setNr(nr++);
                is.setCost(cost);
                is.setTime(time);
            }


            scanner.close();

        } catch(IOException e){
            severe("Cannot read file "+fullName+", exception "+e.getMessage());
        }

    }

    private static String parameters(int n1){
        int n = n1-1;
        int nr = n %10 +1;
        int[] jobs = new int[]{20,30,50,100};
        int[] stages = new int[]{3,5,10};
        int[] cap= new int[]{10,20,30,50};
        int nrJobs = jobs[n/120];
        int nrStages = stages[(n/40)%3];
        int capa = cap[(n/10)%4];


        return nrJobs+"_"+nrStages+"_"+capa+"_rep_"+nr;
    }

}

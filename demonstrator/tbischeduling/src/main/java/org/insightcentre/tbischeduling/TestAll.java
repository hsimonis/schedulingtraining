package org.insightcentre.tbischeduling;

import au.com.bytecode.opencsv.CSVReader;
import framework.types.DateTime;
import framework.types.IrishCalendar;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.exporter.WriteData;
import org.insightcentre.tbischeduling.implementedsolver.CPOModel;
import org.insightcentre.tbischeduling.implementedsolver.CPSatModel;
import org.insightcentre.tbischeduling.implementedsolver.MiniZincDiffnModel;
import org.insightcentre.tbischeduling.importer.*;
import org.insightcentre.tbischeduling.utilities.Group;
import org.insightcentre.tbischeduling.utilities.GroupType;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static org.insightcentre.tbischeduling.datamodel.ModelType.*;
import static org.insightcentre.tbischeduling.datamodel.SolverBackend.Chuffed;
import static org.insightcentre.tbischeduling.datamodel.SolverBackend.Cplex;
import static org.insightcentre.tbischeduling.datamodel.SolverStatus.Optimal;
import static org.insightcentre.tbischeduling.datamodel.SolverStatus.ToRun;
import static org.insightcentre.tbischeduling.logging.LogShortcut.*;
import static org.insightcentre.tbischeduling.utilities.TypeConverters.toSolverStatus;

/*
this class serves two purposes
a) run main on the command line with two arguments, an input and output file
b) run as a subroutine in other application by calling new Batch().query(inputJSON), return the result as a JSON object
 */
public class TestAll {


    static {
        IrishCalendar.initIrishCalendar();
        IrishCalendar.buildCalendar();
    }


    public static void main(String[] args) {
        Scenario base = new Scenario();
        boolean overWrite = false;
////        testAll("imports/RCPSP/SingleMode/j30/","results/",CPO,4,600,overWrite);
////        testAll("imports/RCPSP/SingleMode/j30/","resultsCPSat/",CPSat,8,600,overWrite);
//        base.resetListSolutionSummary();
//        analyzeAll(base,"imports/RCPSP/SingleMode/j30/results/","RCPSP J30 (CPO)","rcpspj30CPO","CPO");
//        analyzeAll(base,"imports/RCPSP/SingleMode/j30/resultsCPSat/","RCPSP J30 (CPSat)","rcpspj30CPSat","CPSat");
////        compareSummaries(base,"comparercpspj30",false,"CPO","CPSat",
////                "Comparison of CPO and CPSat for Results of RCPSP J30",GroupType.RCPSP);
//
////        testAll("imports/RCPSP/SingleMode/j60/","results/",CPO,4,600,overWrite);
////        testAll("imports/RCPSP/SingleMode/j60/","resultsCPSat/",CPSat,8,600,overWrite);
////        base.resetListSolutionSummary();
//        analyzeAll(base,"imports/RCPSP/SingleMode/j60/results/","RCPSP J60 (CPO)","rcpspj60CPO","CPO");
//        analyzeAll(base,"imports/RCPSP/SingleMode/j60/resultsCPSat/","RCPSP J60 (CPSat)","rcpspj60CPSat","CPSat");
////        compareSummaries(base,"comparercpspj60",false,"CPO","CPSat",
////                "Comparison of CPO and CPSat for Results of RCPSP",GroupType.RCPSP);
//
////        testAll("imports/RCPSP/SingleMode/j90/","results/",CPO,4,30,overWrite);
////        testAll("imports/RCPSP/SingleMode/j90/","resultsCPSat/",CPSat,8,30,overWrite);
// //       base.resetListSolutionSummary();
//        analyzeAll(base,"imports/RCPSP/SingleMode/j90/results/","RCPSP J90 (CPO)","rcpspj90CPO","CPO");
//        analyzeAll(base,"imports/RCPSP/SingleMode/j90/resultsCPSat/","RCPSP J90 (CPSat)","rcpspj90CPSat","CPSat");
////        compareSummaries(base,"comparercpspj90",false,"CPO","CPSat",
////                "Comparison of CPO and CPSat for Results of RCPSP",GroupType.RCPSP);
//
//        testAll("imports/RCPSP/SingleMode/j120/","results/",CPO,4,60,overWrite);
//        testAll("imports/RCPSP/SingleMode/j120/","resultsCPSat/",CPSat,8,60,overWrite);
//        //       base.resetListSolutionSummary();
//        analyzeAll(base,"imports/RCPSP/SingleMode/j120/results/","RCPSP J120 (CPO)","rcpspj120CPO","CPO");
//        analyzeAll(base,"imports/RCPSP/SingleMode/j120/resultsCPSat/","RCPSP J120 (CPSat)","rcpspj120CPSat","CPSat");
//        compareSummaries(base,"comparercpspj120",false,"CPO","CPSat",
//                "Comparison of CPO and CPSat for Results of RCPSP",GroupType.RCPSP);

//        testAll("imports/Taillard/OSS/","results/",CPO,4,600,overWrite);
//        testAll("imports/Taillard/OSS/","resultsCPSat/",CPSat,8,30,overWrite);
//        base.resetListSolutionSummary();
//        analyzeAll(base,"imports/Taillard/OSS/results/","Taillard OpenShop (CPO)","oss","CPO");
//        analyzeAll(base,"imports/Taillard/OSS/resultsCPSat/","Taillard OpenShop (CPSat)","ossCPSat","CPSat");
//        compareSummaries(base,"compareoss",false,"CPO","CPSat",
//                "Comparison of CPO and CPSat for Result Groups of Taillard OpenShop Problems",GroupType.Taillard);
//        testAll("imports/Taillard/JSS/","resultsCPSat/");
//        base.resetListSolutionSummary();
//        analyzeAll(base,"imports/Taillard/JSS/results/","Taillard JobShop (CPO)","jss","CPO");
//        analyzeAll(base,"imports/Taillard/JSS/resultsCPSat/","Taillard JobShop (CPSat)","jssCPSat","CPSat");
//        compareSummaries(base,"comparejss",false,"CPO","CPSat",
//                "Comparison of CPO and CPSat for Result Groups of Taillard JobShop Problems",GroupType.Taillard);
//
////        testAll("imports/Taillard/FSS/","resultsCPSat/");
//        base.resetListSolutionSummary();
//        analyzeAll(base,"imports/Taillard/FSS/results/","Taillard Flowshop (CPO)","fss","CPSat");
//        analyzeAll(base,"imports/Taillard/FSS/resultsCPSat/","Taillard Flowshop (CPSat)","fssCPSat","CPO");
//        compareSummaries(base,"comparefss",false,"CPO","CPSat",
//                "Comparison of CPO and CPSat for Result Groups of Taillard FlowShop Problems",GroupType.Taillard);
//        base.resetListSolutionSummary();
//        analyzeAll(base,"imports/Taillard/FSS/results/","Taillard Flowshop (CPO)","fss","FSS");
//        analyzeAll(base,"imports/Taillard/FSS/permutationresults/","Taillard Permutation Flowshop (CPO)","pfss","PFSS");
//        compareSummaries(base,"comparepfss",true,"FSS","PFSS",
//                "Comparison of CPO for Result Groups of Permutation and Unrestricted FlowShop Problems",GroupType.Taillard);
//        testSALBP("salbp/","results/",CPO,null,4,30,overWrite);
//        testSALBP("salbp/","resultsCPSat/",CPSat,null,8,30,overWrite);
//        testSALBP("salbp/","resultsChuffed/",MiniZincDiffn,Chuffed,1,120,overWrite);
//        testSALBP("salbp/","resultsCplex/",MiniZincDiffn,Cplex,8,120,overWrite);
//        base.resetListSolutionSummary();
//        analyzeAll(base,"salbp/results/","SALBP-1 Problems (CPO)","salbp","CPO");
//        analyzeAll(base,"salbp/resultsCPSat/","SALBP-1 Problems (CPSat)","salbpCPSat","CPSat");
//        compareSummaries(base,"comparesalbp",false,"CPO","CPSat",
//                "Comparison of CPO and CPSat for Result Groups of SALBP-1 Problems",GroupType.Salbp);
        testSALBPAlternative("salbp/","alternative/",CPO,4,120,overWrite);
//        testSALBPAlternative("salbp/","alternativeCPSat/",CPSat,8,30,overWrite);
//        base.resetListSolutionSummary();
//        analyzeAll(base,"salbp/alternative/","SALBP-1 Problems Alternative (CPO)","alternativesalbp","CPO",true);
//        analyzeAll(base,"salbp/alternativeCPSat/","SALBP-1 Problems Alternative (CPSat)","alternativesalbpCPSat","CPSat",true);
//        tabularResults(base);
//        compareSummaries(base,"comparesalbpalternative",false,"CPO","CPSat",
//                "Comparison of CPO and CPSat for Result Groups of SALBP-1 Problems Alternative ",GroupType.Salbp);
////        testTestScheduling("testscheduling/","resultsCPSat/");
//        base.resetListSolutionSummary();
//        analyzeAll(base,"testscheduling/results/","Test Scheduling Problems (CPO)","tsched","CPO");
//        analyzeAll(base,"testscheduling/resultsCPSat/","Test Scheduling Problems (CPSat)","tschedCPSat","CPSat");
//        compareSummaries(base,"compareTest",false,"CPO","CPSat",
//                "Comparison of CPO and CPSat for Result Groups of Test Scheduling Problems",GroupType.TestScheduling);
//        testTransport("transport/","results/");
//        base.resetListSolutionSummary();
//        analyzeAll(base,"transport/results/","Factory Design (CPO)","trans","CPO");
//        analyzeAll(base,"transport/resultsCPSat/","Factory Design (CPSat)","transCPSat","CPSat");
//        compareSummaries(base,"compareTrans",false,"CPO","CPSat",
//                "Comparison of CPO and CPSat for Result Groups of Factory Design Problems",GroupType.Transport);

        base.resetListSolutionSummary();
        baseResults(base,"salbp/bounds1000.csv");
        baseResults(base,"salbp/bounds100.csv");
        baseResults(base,"salbp/bounds50.csv");
        baseResults(base,"salbp/bounds20.csv");
        analyzeAll(base,"salbp/results/","SALBP-1 Problems (CPO)","salbp","CPO");
        analyzeAll(base,"salbp/resultsCPSat/","SALBP-1 Problems (CPSat)","salbpCPSat","CPSat");
        analyzeAll(base,"salbp/resultsCplex/","SALBP-1 Problems (Cplex)","salbpCplex","Cplex");
        analyzeAll(base,"salbp/resultsChuffed/","SALBP-1 Problems (Chuffed)","salbpChuffed","Chuffed");
        analyzeAll(base,"salbp/alternative/","SALBP-1 Problems Alternative (CPO)","alternativesalbp","CPOAlternative",true);
        analyzeAll(base,"salbp/alternativeCPSat/","SALBP-1 Problems Alternative (CPSat)","alternativesalbpCPSat","CPSatAlternative",true);
        tabularResults(base,"reports/salbpresultcompare.tex");

    }

    /*
    test a directory of files in the correct JSON format
    read each file, create a SolverRun to run it, run the test and write the result in the results subdirectory under the same name
    important: check the SolverRun for the correct test settings
     */
    private static void testAll(String importDir,String resultDir,ModelType solver,int nrThreads,int timeout,boolean overWrite){
        assert(importDir.endsWith("/"));
        List<String> list =  listFilesUsingJavaIO(importDir,".json");

        for(String fileName:list) {
            String outputFile = importDir + resultDir + fileName;
            if (overWrite || (!new File(outputFile).exists() /*&& fileName.startsWith("tai50_5")*/)) {
                info("trying file " + fileName);

                Scenario base = new Scenario();
                base.setDataFileVersionNumber(8.0);
                base.setDataFile("");
                base.setHorizon(50000);
                base.setTimeResolution(5);
                base.setStartDateTime(new DateTime(2024, 10, 1, 0, 0));

                // define the format version of the datafiles
                new ReadData(base, new File(importDir + fileName));
                SolverRun test = new SolverRun(base);
                test.setName(fileName);
                test.setSolverStatus(ToRun);
                test.setEnforceReleaseDate(true);
                test.setEnforceDueDate(false);
                test.setEnforceCumulative(true);
                test.setEnforceWip(false);
                test.setEnforceDowntime(false);
                test.setEnforceSetup(false);
                test.setEnforceTransportTime(false);
                test.setRelaxSequence(false);
                //??? we did run some experiments on flow shop to turn this on
                //??? save the results in a different directory
                test.setAddSameOrder(false);
                test.setTimeout(timeout);
                test.setModelType(solver);
//                test.setSolverBackend(SolverBackend.Chuffed);
                test.setObjectiveType(ObjectiveType.Makespan);
                test.setNrThreads(nrThreads);

//            info("Nr SolverRun " + base.getListSolverRun().size());
                for (SolverRun run : base.getListSolverRun().stream().filter(x -> x.getSolverStatus() == ToRun).toList()) {
                    info("Running " + run.getName());
                    switch (solver) {
                        case CPO -> new CPOModel(base, run).solve();
                        case CPSat -> new CPSatModel(base, run).solve();
                        case MiniZincDiffn -> new MiniZincDiffnModel(base, run).solve();
                        default -> {
                            severe("solver not supported " + solver);
                            assert (false);
                        }
                    }
                }
                new WriteData(base).toFile(new File(outputFile), 2);
            }
        }

    }
    /*
    test a directory of SALBP instances, read each instance, run it and create a JSON result file
    there is no JSON file of the created input data
    this needs enforceCumulative true and enforceDueDate false
     */
    private static void testSALBP(String importDir,String resultDir,ModelType solver,SolverBackend backEnd,int nrThreads,int timeout,boolean overWrite){
        assert(importDir.endsWith("/"));
        List<String> list =  listFilesUsingJavaIO(importDir,".alb");

        for(String fileName:list) {
            String outputFile = importDir+resultDir+ fileName.replaceAll(".alb",".json");
            if (overWrite || (!new File(outputFile).exists() /*&& fileName.startsWith("instance_n=50_")*/)) {
                info("trying file " + fileName);

                Scenario base = new Scenario();
                base.setDataFileVersionNumber(8.0);
                base.setDataFile("");
                base.setHorizon(50000);
                base.setTimeResolution(5);
                base.setStartDateTime(new DateTime(2024, 10, 1, 0, 0));

                // define the format version of the datafiles
                new ReadSALBPFile(base, new File(importDir + fileName));
                SolverRun test = new SolverRun(base);
                test.setName(fileName);
                test.setSolverStatus(ToRun);
                test.setEnforceReleaseDate(false);
                test.setEnforceDueDate(false);
                test.setEnforceCumulative(true);
                test.setEnforceWip(false);
                test.setEnforceDowntime(false);
                test.setEnforceSetup(false);
                test.setEnforceTransportTime(false);
                test.setRelaxSequence(false);
                test.setAddSameOrder(false);
                test.setTimeout(timeout);
                test.setModelType(solver);
                test.setSolverBackend(backEnd);
                test.setObjectiveType(ObjectiveType.Makespan);
                test.setNrThreads(nrThreads);

//            info("Nr SolverRun " + base.getListSolverRun().size());
                for (SolverRun run : base.getListSolverRun().stream().filter(x -> x.getSolverStatus() == ToRun).toList()) {
                    info("Running " + run.getName());
                    switch (solver) {
                        case CPO -> new CPOModel(base, run).solve();
                        case CPSat -> new CPSatModel(base, run).solve();
                        case MiniZincDiffn -> new MiniZincDiffnModel(base, run).solve();
                        default -> {
                            severe("solver not supported " + solver);
                            assert (false);
                        }
                    }

                }
                new WriteData(base).toFile(new File(outputFile), 2);
            }

        }

    }
    private static void testSALBPAlternative(String importDir,String resultDir,ModelType solver,int nrThreads,int timeout,boolean overWrite){
        assert(importDir.endsWith("/"));
        List<String> list =  listFilesUsingJavaIO(importDir,".alb");

        for(String fileName:list) {
            String outputFile = importDir+resultDir+ fileName.replaceAll(".alb",".json");
            if (overWrite || (!new File(outputFile).exists() /*&& fileName.startsWith("instance_n=20_")*/)) {
                info("trying file " + fileName);

                Scenario base = new Scenario();
                base.setDataFileVersionNumber(8.0);
                base.setDataFile("");
                base.setHorizon(50000);
                base.setTimeResolution(5);
                base.setStartDateTime(new DateTime(2024, 10, 1, 0, 0));

                // define the format version of the datafiles
                new ReadSALBPAlternativeFile(base, new File(importDir + fileName));
                SolverRun test = new SolverRun(base);
                test.setName(fileName);
                test.setSolverStatus(ToRun);
                test.setEnforceReleaseDate(false);
                test.setEnforceDueDate(false);
                test.setEnforceCumulative(true);
                test.setEnforceWip(true);
                test.setEnforceDowntime(true);
                test.setEnforceSetup(false);
                test.setEnforceTransportTime(false);
                test.setRelaxSequence(false);
                test.setAddSameOrder(false);
                test.setTimeout(timeout);
                test.setModelType(solver);
                test.setObjectiveType(ObjectiveType.Makespan);
                test.setNrThreads(nrThreads);

//            info("Nr SolverRun " + base.getListSolverRun().size());
                for (SolverRun run : base.getListSolverRun().stream().filter(x -> x.getSolverStatus() == ToRun).toList()) {
                    info("Running " + run.getName());
                    switch (solver) {
                        case CPO -> new CPOModel(base, run).solve();
                        case CPSat -> new CPSatModel(base, run).solve();
                        case MiniZincDiffn -> new MiniZincDiffnModel(base, run).solve();
                        default -> {
                            severe("solver not supported " + solver);
                            assert (false);
                        }
                    }

                }
                new WriteData(base).toFile(new File(outputFile), 2);
            }

        }

    }
    /*
    read a directory of TestScheduling instances in its own JSON format, create a SolverRun, and write the results in the results subdirectory
    this needs enforceCumulative true and enforceDueDate false to work
     */
    private static void testTestScheduling(String importDir,String resultDir){
        assert(importDir.endsWith("/"));
        assert(resultDir.endsWith("/"));
        List<String> list =  listFilesUsingJavaIO(importDir,".json");

        for(String fileName:list) {
            info("trying file " + fileName);
            String outputFile = importDir+resultDir + fileName;
            if (!new File(outputFile).exists() && fileName.startsWith("t500m")) {

                Scenario base = new Scenario();
                base.setDataFileVersionNumber(8.0);
                base.setDataFile("");
                base.setHorizon(100000);
                base.setTimeResolution(5);
                base.setStartDateTime(new DateTime(2024, 10, 1, 0, 0));

                // define the format version of the datafiles
                new ReadTestSchedulingFile(base, new File(importDir + fileName));
                SolverRun test = new SolverRun(base);
                test.setName(fileName);
                test.setSolverStatus(ToRun);
                test.setEnforceReleaseDate(false);
                test.setEnforceDueDate(false);
                test.setEnforceCumulative(true);
                test.setEnforceWip(false);
                test.setEnforceDowntime(false);
                test.setEnforceSetup(false);
                test.setEnforceTransportTime(false);
                test.setRelaxSequence(false);
                test.setAddSameOrder(false);
                test.setTimeout(30);
                test.setModelType(CPSat);
                test.setObjectiveType(ObjectiveType.Makespan);
                test.setNrThreads(4);

//            info("Nr SolverRun " + base.getListSolverRun().size());
                for (SolverRun run : base.getListSolverRun().stream().filter(x -> x.getSolverStatus() == ToRun).toList()) {
                    info("Running " + run.getName());
                    new CPSatModel(base, run).solve();
                }
                new WriteData(base).toFile(new File(outputFile), 2);
            }

        }

    }
    private static void testTransport(String importDir,String resultDir){
        assert(importDir.endsWith("/"));
        assert(resultDir.endsWith("/"));
        List<String> list =  listFilesUsingJavaIO(importDir,".txt");

        for(String fileName:list) {
            info("trying file " + fileName);
            String outputFile = importDir+resultDir+ fileName.replaceAll(".txt",".json");
            if (!new File(outputFile).exists() /*&& fileName.startsWith("instance20_")*/) {

                Scenario base = new Scenario();
                base.setDataFileVersionNumber(8.0);
                base.setDataFile("");
                base.setHorizon(100000);
                base.setTimeResolution(5);
                base.setStartDateTime(new DateTime(2024, 10, 1, 0, 0));

                // define the format version of the datafiles
                new ReadJJFlatFile(base, new File(importDir + fileName));
                SolverRun test = new SolverRun(base);
                test.setName(fileName);
                test.setSolverStatus(ToRun);
                test.setEnforceReleaseDate(false);
                test.setEnforceDueDate(false);
                test.setEnforceCumulative(true);
                test.setEnforceWip(false);
                test.setEnforceDowntime(false);
                test.setEnforceSetup(false);
                test.setEnforceTransportTime(false);
                test.setRelaxSequence(false);
                test.setAddSameOrder(false);
                test.setTimeout(300);
                test.setModelType(CPO);
//                test.setModelType(CPSat);
                test.setObjectiveType(ObjectiveType.Makespan);
                test.setNrThreads(4);

//            info("Nr SolverRun " + base.getListSolverRun().size());
                for (SolverRun run : base.getListSolverRun().stream().filter(x -> x.getSolverStatus() == ToRun).toList()) {
                    info("Running " + run.getName());
                    new CPOModel(base, run).solve();
//                    new CPSatModel(base, run).solve();
                }
                new WriteData(base).toFile(new File(outputFile), 2);
            }

        }

    }


    private static void analyzeAll(Scenario base,String resultDir,String title,String suffix,String variant) {
        analyzeAll (base, resultDir, title, suffix, variant,false);
    }
    private static void analyzeAll(Scenario base,String resultDir,String title,String suffix,String variant,boolean adjust){
        assert(resultDir.endsWith("/"));
        List<String> list =  listFilesUsingJavaIO(resultDir,".json");
        String reportFile = "reports/results"+suffix+".tex";
        try{
            PrintWriter out = new PrintWriter(reportFile);
            out.printf("\\begin{longtable}{lrrlrrrr}\n");
            out.printf("\\caption{Results for %s (%d Instances)}\\\\\\toprule\n",title,list.size());
            out.printf("Name & \\shortstack{Nr\\\\Jobs} & \\shortstack{Nr\\\\Machines} & Status & Time & Makespan & Bound & \\shortstack{Gap\\\\Percent}\\\\ \\midrule\n");
            out.printf("\\endhead\n");
            out.printf("\\bottomrule\n");
            out.printf("\\endfoot\n");


            for(String fileName:list) {
//            info("analyzing file " + fileName);
                try {
                    JSONObject root = new JSONObject(new String(Files.readAllBytes(Paths.get(resultDir + fileName))));
                    JSONObject sol = root.getJSONArray("solution").getJSONObject(0);
                    JSONObject problem = root.getJSONArray("problem").getJSONObject(0);
                    JSONObject solverRun = root.getJSONArray("solverRun").getJSONObject(0);
                    String name = solverRun.getString("name");
                    int nrJobs = problem.getInt("nrJobs");
                    int nrMachines = problem.getInt("nrDisjunctiveResources");
                    int nrCumulatives = problem.getInt("nrCumulativeResources");
                    int nrTasks = problem.getInt("nrTasks");
                    String status = sol.getString("solverStatus");
                    double time = solverRun.getDouble("time");
                    double bound = sol.getDouble("bound");
                    double gapPercent=0.0;
                    if (sol.has("gapPercent")) {
                        gapPercent = sol.getDouble("gapPercent");
                    } else if (sol.has("gap")){
                        gapPercent = 100.0*sol.getDouble("gap");
                    } else {
                        warning("Solution has not gap information");
                    }
                    int makespan = sol.getInt("makespan");
                    SolutionSummary s = new SolutionSummary(base);
                    s.setInstance(name);
                    s.setSolverStatus(toSolverStatus(status));
                    s.setNrJobs(nrJobs);
                    s.setNrMachines(nrMachines);
                    s.setNrCumulatives(nrCumulatives);
                    s.setNrTasks(nrTasks);
                    s.setTime(time);
                    s.setMakespan(makespan);
                    s.setBound(bound);
                    s.setGapPercent(gapPercent);
                    s.setVariant(variant);
                    if (adjust) {
                        adjustMakespan(s);
                    }


//                    info(name + " jobs " + nrJobs + " machines " + nrMachines + " status " + status + " time " + time + " makespan " + makespan + " bound " + bound + " gapPercent " + gapPercent);
                    out.printf("%s & %d & %d & %s & %5.2f & %d & %5.2f & %5.2f\\\\\n",
                            name.replaceAll("_"," "),nrJobs,nrMachines,status,time,s.getMakespan(),s.getBound(),s.getGapPercent());

                } catch (IOException e) {
                    severe("Cannot read file " + fileName + ", exception " + e.getMessage());
                }catch (Exception e) {
                    severe(resultDir+" Instance "+fileName+" Exception " + e.getMessage());
//                    assert(false);
                }
            }
            out.printf("\\end{longtable}\n\n");
            out.close();

        } catch(IOException e){
            severe("Cannot write file "+reportFile+", exception "+e.getMessage());
        }
    }

    private static void adjustMakespan(SolutionSummary s){
        s.setMakespan((int)adjustValue(s.getMakespan()));
        s.setBound(adjustValue(s.getBound()));
        s.setGapPercent(100.0*(s.getMakespan()-s.getBound())/s.getMakespan());

    }

    private static double adjustValue(double value){
        return Math.ceil(value/(1000.0+1));
    }

    public static List<String> listFilesUsingJavaIO(String dir,String ext) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .filter(x->x.endsWith(ext))
                .sorted() //to get a standard order
//                .limit(2)
                .collect(Collectors.toList());
    }

    private static void compareSummaries(Scenario base, String key, boolean relax, String variant1, String variant2, String label, GroupType grouper){
        String fileName = "reports/"+key+".tex";
        try {
            PrintWriter out = new PrintWriter(fileName);
            out.printf("\\begin{table}[htbp]\n");
            out.printf("\\caption{\\label{tab:%s}%s}\n",key,label);
            out.printf("{\\scriptsize\n");
            out.printf("\\begin{tabular}{lr*{10}{r}} \\toprule\n");
            out.printf("& &\\multicolumn{4}{c}{All Instances} & \\multicolumn{2}{c}{Optimal Only} & \\multicolumn{4}{c}{Non Optimal Only}\\\\ \n");
            out.printf("& &\\multicolumn{4}{c}{Optimal (\\%% of All Instances)} & \\multicolumn{2}{c}{Time (\\%% of VB)} & \\multicolumn{2}{c}{Cost (\\%% of VB)} & \\multicolumn{2}{c}{Bound (\\%% of VB)}\\\\ \n");
            out.printf("Group & Nr & Both & %s & %s & None & %s & %s & %s & %s & %s & %s\\\\ \\midrule\n",
                    variant1,variant2,variant1,variant2,variant1,variant2,variant1,variant2);
            List<Group> groups = base.getListSolutionSummary().stream().
                    map(x->grouper(x,grouper)).
                    distinct().
                    sorted(Comparator.comparing(Group::getKey1).thenComparing(Group::getKey2).thenComparing(Group::getKey3)).
                    toList();
            for(Group group:groups) {

                String variantOne = null;
                String variantTwo = null;
                double timeOne = 0.0;
                double timeTwo = 0.0;
                double timeVb = 0.0;
                int bothOptimal = 0;
                int oneOnlyOptimal = 0;
                int twoOnlyOptimal = 0;
                int oneSpan = 0;
                int twoSpan = 0;
                int vbSpan = 0;
                int spanCnt = 0;
                double oneBound = 0.0;
                double twoBound = 0.0;
                double vbBound = 0.0;
                int minDiff = 0;
                int maxDiff = 0;
                int onlyOneCnt = 0;
                int twoCnt = 0;
                int notOptimal = 0;
                int sameCostOne = 0;
                int sameCostTwo = 0;

                Map<String, List<SolutionSummary>> map = base.getListSolutionSummary().stream().
                        filter(x->grouper(x,grouper)==group).
                        collect(groupingBy(SolutionSummary::getInstance));
                for (String instance : map.keySet()) {
                    List<SolutionSummary> sols = map.get(instance).stream().
                            sorted(Comparator.comparing(SolutionSummary::getVariant)).
                            toList();
                    if (sols.size() == 1) {
//                info("Only 1 "+instance);
                        onlyOneCnt++;
                    } else if (sols.size() == 2) {
                        twoCnt++;
                        SolutionSummary one = sols.get(0);
                        SolutionSummary two = sols.get(1);
                        if (variantOne != null) {
                            assert (variantOne.equals(one.getVariant()));
                        } else {
                            variantOne = one.getVariant();
                        }
                        if (variantTwo != null) {
                            assert (variantTwo.equals(two.getVariant()));
                        } else {
                            variantTwo = two.getVariant();
                        }
                        assert(variant1.equals(variantOne));
                        assert(variant2.equals(variantTwo));
                        if (one.getSolverStatus() == two.getSolverStatus()) {
                            if (one.getSolverStatus() == Optimal) {
                                if (one.getMakespan() != (int)two.getMakespan()) {
                                    info("Equal " + one.getSolverStatus() + " " + one.getMakespan() + " " + two.getMakespan());
                                }
                                assert (relax || one.getMakespan() == (int) two.getMakespan());
                                timeOne += one.getTime();
                                timeTwo += two.getTime();
                                timeVb += Math.min(one.getTime(),two.getTime());
                                bothOptimal++;

                            } else {
                                notOptimal++;
//                        info("Equal " + one.getSolverStatus() + " " + one.getMakespan() + " " + two.getMakespan());
                                oneSpan += one.getMakespan();
                                twoSpan += two.getMakespan();
                                vbSpan += Math.min(one.getMakespan(), two.getMakespan());
                                oneBound += one.getBound();
                                twoBound += two.getBound();
                                vbBound += Math.max(one.getBound(),two.getBound());
                                minDiff = Math.min(minDiff, one.getMakespan() - two.getMakespan());
                                maxDiff = Math.max(maxDiff, one.getMakespan() - two.getMakespan());
                                spanCnt++;
                            }

                        } else {
//                    info("Different status "+one.getSolverStatus()+" "+two.getSolverStatus());
                            oneSpan += one.getMakespan();
                            twoSpan += two.getMakespan();
                            vbSpan += Math.min(one.getMakespan(), two.getMakespan());
                            oneBound += one.getBound();
                            twoBound += two.getBound();
                            vbBound += Math.max(one.getBound(),two.getBound());
                            spanCnt++;
                            if (one.getSolverStatus() == Optimal) {
                                oneOnlyOptimal++;
                                if (one.getMakespan() == (int) two.getMakespan()) {
                                    sameCostOne++;
                                }
                            } else if (two.getSolverStatus() == Optimal) {
                                twoOnlyOptimal++;
                                if (one.getMakespan() == (int) two.getMakespan()) {
                                    sameCostTwo++;
                                }
                            }
                        }

                    } else {
                        warning("Too many solutions " + instance);
                        assert (false);
                    }

                }
                info("Only one entry " + onlyOneCnt + " two entries " + twoCnt);
                info("Time for " + bothOptimal + " " + percent(bothOptimal, twoCnt) + " optimal cases " + variantOne + " " + timeOne + " " + variantTwo + " " + timeTwo);
                info("One only Optimal " + variantOne + " " + oneOnlyOptimal + " " + percent(oneOnlyOptimal, twoCnt) + " same Cost " + sameCostOne);
                info("Two only Optimal " + variantTwo + " " + twoOnlyOptimal + " " + percent(twoOnlyOptimal, twoCnt) + " same Cost " + sameCostTwo);
                info("Not optimal " + notOptimal + " " + percent(notOptimal, twoCnt));
                info("Sum Makespan " + spanCnt + " one " + variantOne + " " + oneSpan + " " + variantTwo + " " + twoSpan);
                info("Sum Bound " + spanCnt + " one " + variantOne + " " + oneBound + " " + variantTwo + " " + twoBound);
                info("Two Better than one by " + minDiff + " one better than two by " + maxDiff);
                out.printf("%s & %d & %s & %s & %s & %s & %s & %s & %s & %s & %s & %s \\\\ \n",
                        group.getName(),twoCnt,percent(bothOptimal, twoCnt), percent(oneOnlyOptimal, twoCnt),percent(twoOnlyOptimal, twoCnt),percent(notOptimal, twoCnt),
                        percent(timeOne,timeVb),percent(timeTwo,timeVb),
                        percent(oneSpan,vbSpan),percent(twoSpan,vbSpan),
                        percent(oneBound,vbBound),percent(twoBound,vbBound));
            }
            out.printf("\\bottomrule\n");
            out.printf("\\end{tabular}\n");
            out.printf("}\n");
            out.printf("\\end{table}\n\n");
            out.close();
        } catch(IOException e){
            severe("Cannot write file "+fileName+", exception "+e.getMessage());
        }
    }

    static Hashtable<String,Group> groupHash = new Hashtable<>();
    private static Group grouper(SolutionSummary s,GroupType type){
        switch(type){
            case RCPSP: return lookupGroup(s.getNrTasks()+"",s.getNrTasks(),0,0);
            case Taillard: return lookupGroup(s.getNrJobs()+"/"+s.getNrMachines(),s.getNrJobs(),s.getNrMachines(),0);
            case Salbp: return lookupGroup(s.getNrTasks()+"",s.getNrTasks(),0,0);
            case TestScheduling: return lookupGroup(s.getNrJobs()+"/"+s.getNrMachines()+"/"+s.getNrCumulatives(),s.getNrJobs(),s.getNrMachines(),s.getNrCumulatives());
            case Transport: return lookupGroup(s.getNrJobs()+"",s.getNrJobs(),0,0);
            default:
                severe("Bad Group type: "+type);
                assert(false);
                return null;
        }
    }

    private static Group lookupGroup(String name,int key1,int key2,int key3){
        Group res = groupHash.get(name);
        if (res == null){
            res = new Group(name,key1,key2,key3);
            groupHash.put(name,res);
        }
        return res;
    }
    private static String percent(double a,double b){
        if (b==0.0){
            return "n/a";
        }
        return String.format("%5.2f",100.0*a/b);
    }

    private static void tabularResults(Scenario base,String fileName){
        List<String> problems = base.getListSolutionSummary().stream().
                map(SolutionSummary::getInstance).
                distinct().
                sorted().
                toList();
        Hashtable<String,SolutionSummary> hash = new Hashtable<>();
        for(SolutionSummary s:base.getListSolutionSummary()){
            assert(hash.get(key(s))==null);
            hash.put(key(s),s);
        }
        try {
            PrintWriter out = new PrintWriter(fileName);
            out.printf("\\begin{longtable}{lrrrrrrrr}\n");
            out.printf("\\caption{Result Comparisoon for SALBP}\\\\\\toprule\n");
            out.printf("& \\multicolumn{2}{c}{SALOME} & \\multicolumn{4}{c}{Direct} & \\multicolumn{2}{c}{Alternative}\\\\");
            out.printf("Instance & LB & UB & CPO & CPSat & Cplex & Chuffed & CPO & CPSat \\\\\\midrule\n");
            out.printf("\\endhead\n");
            out.printf("\\bottomrule\n");
            out.printf("\\endfoot\n");

            for (String problem : problems) {
                SolutionSummary salome = hash.get(key(problem,"base"));
                Integer ub = solution(hash, problem, "base");
                Integer a = solution(hash, problem, "CPO");
                Integer b = solution(hash, problem, "CPSat");
                Integer b1 = solution(hash, problem, "Cplex");
                Integer b2 = solution(hash, problem, "Chuffed");
                Integer c = solution(hash, problem, "CPOAlternative") ;
                Integer d = solution(hash, problem, "CPSatAlternative");
                SolverStatus ubStatus = status(hash, problem, "base");
                SolverStatus aStatus = status(hash, problem, "CPO");
                SolverStatus bStatus = status(hash, problem, "CPSat");
                SolverStatus b1Status = status(hash, problem, "Cplex");
                SolverStatus b2Status = status(hash, problem, "Chuffed");
                SolverStatus cStatus = status(hash, problem, "CPOAlternative") ;
                SolverStatus dStatus = status(hash, problem, "CPSatAlternative");
                List<Integer> sols = new ArrayList<>();
                if (ub != null) sols.add(ub);
                if (a != null) sols.add(a);
                if (b != null) sols.add(b);
                if (b1 != null) sols.add(b1);
                if (b2 != null) sols.add(b2);
                if (c != null) sols.add(c);
                if (d != null) sols.add(d);
                int min = sols.stream().mapToInt(x->x).min().orElse(0);
                int exAequo = (int) sols.stream().filter(x->x==min).count();

                out.printf("%s",problem.replaceAll("instance_n=","").replaceAll(".alb","").replaceAll("_"," "));
                if (salome != null) {
                    out.printf("& %d ",(int)Math.round(salome.getBound()));
                } else {
                    out.printf("& n/a");
                }
                printSol(out,ub,ubStatus,min,exAequo);
                printSol(out,a,aStatus,min,exAequo);
                printSol(out,b,bStatus,min,exAequo);
                printSol(out,b1,b1Status,min,exAequo);
                printSol(out,b2,b2Status,min,exAequo);
                printSol(out,c,cStatus,min,exAequo);
                printSol(out,d,dStatus,min,exAequo);
                out.printf("\\\\\n");
            }
            out.printf("\\end{longtable}\n\n");
            out.close();
        } catch(IOException e){
            severe("Cannot write file "+fileName+", exception "+e.getMessage());
        }
    }

    private static void printSol(PrintWriter out,Integer v,SolverStatus status,int min,int exAequo){
        if (v == null){
            out.printf("& \\cellcolor{red!20} n/a ");
        } else if (v == min){
            out.printf("& \\cellcolor{%s} %s ",intensity(exAequo),statusValue(v,status));
        } else {
            out.printf("& %s ",statusValue(v,status));
        }
    }

    private static String statusValue(int v,SolverStatus status){
        if (status==Optimal){
            return String.format("\\textbf{%d}",v);
        }
        return String.format("%d",v);
    }

    private static String intensity(int exAequo){
        if (exAequo == 1){
            return "blue!40";
        } else if (exAequo == 2) {
            return "blue!20";
        } else if (exAequo == 3) {
            return "blue!10";
        } else {
            return "blue!5";

        }
    }

    private static Integer solution(Hashtable<String,SolutionSummary> hash, String instance,String variant){
        SolutionSummary s = hash.get(key(instance,variant));
        if (s == null) {
            return null;
        }
        return s.getMakespan();
    }
    private static SolverStatus status(Hashtable<String,SolutionSummary> hash, String instance,String variant){
        SolutionSummary s = hash.get(key(instance,variant));
        if (s == null) {
            return null;
        }
        return s.getSolverStatus();
    }

    private static String key(String instance,String variant){
        return instance+"/"+variant;
    }

    private static String key(SolutionSummary s){
        return key(s.getInstance(),s.getVariant());
    }

    private static void baseResults(Scenario base,String fullFile){
        try{
            info("Reading file " + fullFile);
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(fullFile),"UTF8"),',');
            String[] firstLine = reader.readNext();
            String[] nextLine;
            int line = 1;
            int created = 0;
            while ((nextLine = reader.readNext()) != null) {
                int col= 0;
//                System.out.println(nextLine[0]);
                if (!nextLine[0].equals("")) {

                    String instance = nextLine[col++].trim()+".alb";
                    int lb = readInteger(nextLine[col++].trim());
                    int ub = readInteger(nextLine[col++].trim());

                    SolutionSummary ss = new SolutionSummary(base);
                    ss.setInstance(instance);
                    ss.setBound((double)lb);
                    ss.setMakespan(ub);
                    ss.setVariant("base");
                    ss.setSolverStatus(lb == ub?Optimal:SolverStatus.Solution);


                    created++;


                }
                line++;
            }
            info("BaseResult File read, "+line+" lines, "+created+ " created items");

        } catch(IOException e){
            severe("Cannot read file "+fullFile+", exception "+e.getMessage());

        }
    }

    public static int readInteger(String entry){
        if (entry.equals("")||entry.equals("null")){
            return 0;
        }
        return Integer.parseInt(entry);
    }




}

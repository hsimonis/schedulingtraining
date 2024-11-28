package org.insightcentre.tbischeduling;

import framework.types.DateTime;
import framework.types.IrishCalendar;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.exporter.WriteData;
import org.insightcentre.tbischeduling.implementedsolver.CPOModel;
import org.insightcentre.tbischeduling.implementedsolver.CPSatModel;
import org.insightcentre.tbischeduling.implementedsolver.MiniZincDiffnModel;
import org.insightcentre.tbischeduling.importer.ReadData;
import org.insightcentre.tbischeduling.importer.ReadJJFlatFile;
import org.insightcentre.tbischeduling.importer.ReadSALBPFile;
import org.insightcentre.tbischeduling.importer.ReadTestSchedulingFile;
import org.insightcentre.tbischeduling.utilities.Group;
import org.insightcentre.tbischeduling.utilities.GroupType;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static org.insightcentre.tbischeduling.datamodel.ModelType.*;
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
        testAll("imports/Taillard/OSS/","results/",CPO,4,600,overWrite);
        testAll("imports/Taillard/OSS/","resultsCPSat/",CPSat,8,30,overWrite);
        base.resetListSolutionSummary();
        analyzeAll(base,"imports/Taillard/OSS/results/","Taillard OpenShop (CPO)","oss","CPO");
        analyzeAll(base,"imports/Taillard/OSS/resultsCPSat/","Taillard OpenShop (CPSat)","ossCPSat","CPSat");
        compareSummaries(base,"compareoss",false,"CPO","CPSat",
                "Comparison of CPO and CPSat for Result Groups of Taillard OpenShop Problems",GroupType.Taillard);
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
//        testSALBP("salbp/","resultsCPSat/",CPSat,8,30,overWrite);
//        base.resetListSolutionSummary();
//        analyzeAll(base,"salbp/results/","SALBP-1 Problems (CPO)","salbp","CPO");
//        analyzeAll(base,"salbp/resultsCPSat/","SALBP-1 Problems (CPSat)","salbpCPSat","CPSat");
//        compareSummaries(base,"comparesalbp",false,"CPO","CPSat",
//                "Comparison of CPO and CPSat for Result Groups of SALBP-1 Problems",GroupType.Salbp);
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
            info("trying file " + fileName);
            String outputFile = importDir + resultDir + fileName;
            if (overWrite || (!new File(outputFile).exists() /*&& fileName.startsWith("tai50_5")*/)) {

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
    private static void testSALBP(String importDir,String resultDir,ModelType solver,int nrThreads,int timeout,boolean overWrite){
        assert(importDir.endsWith("/"));
        List<String> list =  listFilesUsingJavaIO(importDir,".alb");

        for(String fileName:list) {
            info("trying file " + fileName);
            String outputFile = importDir+resultDir+ fileName.replaceAll(".alb",".json");
            if (overWrite || (!new File(outputFile).exists() /* && fileName.startsWith("instance_n=20_")*/)) {

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


    private static void analyzeAll(Scenario base,String resultDir,String title,String suffix,String variant){
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


//                    info(name + " jobs " + nrJobs + " machines " + nrMachines + " status " + status + " time " + time + " makespan " + makespan + " bound " + bound + " gapPercent " + gapPercent);
                    out.printf("%s & %d & %d & %s & %5.2f & %d & %5.2f & %5.2f\\\\\n",
                            name.replaceAll("_"," "),nrJobs,nrMachines,status,time,makespan,bound,gapPercent);

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
//                        info("Equal " + one.getSolverStatus() + " " + one.getMakespan() + " " + two.getMakespan());
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

}

package org.insightcentre.tbischeduling;

import framework.types.DateTime;
import framework.types.IrishCalendar;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.exporter.WriteData;
import org.insightcentre.tbischeduling.implementedsolver.CPOModel;
import org.insightcentre.tbischeduling.implementedsolver.CPSatModel;
import org.insightcentre.tbischeduling.implementedsolver.MiniZincDiffnModel;
import org.insightcentre.tbischeduling.importer.ReadData;
import org.insightcentre.tbischeduling.importer.ReadSALBPFile;
import org.insightcentre.tbischeduling.importer.ReadTestSchedulingFile;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
//        testAll("imports/Taillard/OSS/","resultsCPSat/");
//        analyzeAll("imports/Taillard/OSS/results/","Taillard OpenShop (CPOptimizer)","oss");
//        analyzeAll("imports/Taillard/OSS/resultsCPSat/","Taillard OpenShop (CPSat)","ossCPSat");
//        testAll("imports/Taillard/JSS/","resultsCPSat/");
//        analyzeAll("imports/Taillard/JSS/results/","Taillard JobShop","jss");
//        analyzeAll("imports/Taillard/JSS/resultsCPSat/","Taillard JobShop (CPSat)","jssCPSat");
//        testAll("imports/Taillard/FSS/","resultsCPSat/");
//        analyzeAll("imports/Taillard/FSS/resultsCPSat/","Taillard Flowshop (CPSat)","fssCPSat");
//        analyzeAll("imports/Taillard/FSS/results/","Taillard Flowshop","fss");
//        testSALBP("salbp/","resultsCPSat/");
        analyzeAll(base,"salbp/resultsCPSat/","SALBP-1 Problems (CPSat)","salbpCPSat");
        analyzeAll(base,"salbp/results/","SALBP-1 Problems","salbp");
        compareSummaries(base);
//        testTestScheduling("testscheduling/");
//        analyzeAll("testscheduling/results/","Test Scheduling Problems","tsched");
    }

    /*
    test a directory of files in the correct JSON format
    read each file, create a SolverRun to run it, run the test and write the result in the results subdirectory under the same name
    important: check the SolverRun for the correct test settings
     */
    private static void testAll(String importDir,String resultDir){
        assert(importDir.endsWith("/"));
        List<String> list =  listFilesUsingJavaIO(importDir,".json");

        for(String fileName:list) {
            info("trying file " + fileName);
            String outputFile = importDir + resultDir + fileName;
            if (!new File(outputFile).exists() /*&& fileName.startsWith("tai50_5")*/) {

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
                test.setTimeout(600);
//                test.setModelType(CPO);
                test.setModelType(CPSat);
//                test.setModelType(MiniZincDiffn);
//                test.setSolverBackend(SolverBackend.Chuffed);
                test.setSolverBackend(SolverBackend.CPSat);
                test.setObjectiveType(ObjectiveType.Makespan);
                test.setNrThreads(8);

//            info("Nr SolverRun " + base.getListSolverRun().size());
                for (SolverRun run : base.getListSolverRun().stream().filter(x -> x.getSolverStatus() == ToRun).toList()) {
                    info("Running " + run.getName());
//                    new CPOModel(base, run).solve();
                    new CPSatModel(base, run).solve();
//                    new MiniZincDiffnModel(base, run).solve();
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
    private static void testSALBP(String importDir,String resultDir){
        assert(importDir.endsWith("/"));
        List<String> list =  listFilesUsingJavaIO(importDir,".alb");

        for(String fileName:list) {
            info("trying file " + fileName);
            String outputFile = importDir+resultDir+ fileName.replaceAll(".alb",".json");
            if (!new File(outputFile).exists()  && fileName.startsWith("instance_n=100_")) {

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
                test.setTimeout(30);
                test.setModelType(CPO);
                test.setModelType(CPSat);
                test.setObjectiveType(ObjectiveType.Makespan);
                test.setNrThreads(4);

//            info("Nr SolverRun " + base.getListSolverRun().size());
                for (SolverRun run : base.getListSolverRun().stream().filter(x -> x.getSolverStatus() == ToRun).toList()) {
                    info("Running " + run.getName());
//                    new CPOModel(base, run).solve();
                    new CPSatModel(base, run).solve();
                }
                new WriteData(base).toFile(new File(outputFile), 2);
            }

        }

    }
    /*
    read a directory of TestScheduling instances in its own JSON format, create a SolverRun, and write the results in the results subdirectory
    this needs enforceCumulative true and enforceDueDate false to work
     */
    private static void testTestScheduling(String importDir){
        assert(importDir.endsWith("/"));
        List<String> list =  listFilesUsingJavaIO(importDir,".json");

        for(String fileName:list) {
                info("trying file " + fileName);
                String outputFile = importDir+"results/" + fileName;
                if (!new File(outputFile).exists()) {

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
                    test.setModelType(CPO);
                    test.setObjectiveType(ObjectiveType.Makespan);
                    test.setNrThreads(4);

//            info("Nr SolverRun " + base.getListSolverRun().size());
                    for (SolverRun run : base.getListSolverRun().stream().filter(x -> x.getSolverStatus() == ToRun).toList()) {
                        info("Running " + run.getName());
                        new CPOModel(base, run).solve();
                    }
                    new WriteData(base).toFile(new File(outputFile), 2);
                }

        }

    }


    private static void analyzeAll(Scenario base,String resultDir,String title,String suffix){
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
                    double gap = sol.getDouble("gap");
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
                    s.setGapPercent(100.0*gap);
                    s.setVariant(suffix);


//                    info(name + " jobs " + nrJobs + " machines " + nrMachines + " status " + status + " time " + time + " makespan " + makespan + " bound " + bound + " gap " + gap*100.0);
                    out.printf("%s & %d & %d & %s & %5.2f & %d & %5.2f & %5.2f\\\\\n",
                            name.replaceAll("_"," "),nrJobs,nrMachines,status,time,makespan,bound,gap*100.0);

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

    private static void compareSummaries(Scenario base){
        String variantOne=null;
        String variantTwo=null;
        double timeOne = 0.0;
        double timeTwo = 0.0;
        int bothOptimal = 0;
        int oneOnlyOptimal = 0;
        int twoOnlyOptimal = 0;
        int oneSpan = 0;
        int twoSpan = 0;
        int spanCnt = 0;
        double oneBound=0.0;
        double twoBound = 0.0;
        int minDiff = 0;
        int maxDiff =0;
        int onlyOneCnt = 0;
        int twoCnt = 0;
        int notOptimal = 0;
        int sameCostOne = 0;
        int sameCostTwo = 0;
        Map<String,List<SolutionSummary>> map = base.getListSolutionSummary().stream().collect(groupingBy(SolutionSummary::getInstance));
        for(String instance:map.keySet()){
            List<SolutionSummary> sols = map.get(instance).stream().sorted(Comparator.comparing(SolutionSummary::getVariant)).toList();
            if (sols.size()==1){
//                info("Only 1 "+instance);
                onlyOneCnt++;
            } else if (sols.size() ==2) {
                twoCnt++;
                SolutionSummary one = sols.get(0);
                SolutionSummary two = sols.get(1);
                if (variantOne != null){
                    assert(variantOne.equals(one.getVariant()));
                } else {
                    variantOne = one.getVariant();
                }
                if (variantTwo != null){
                    assert(variantTwo.equals(two.getVariant()));
                } else {
                    variantTwo = two.getVariant();
                }
                if (one.getSolverStatus() == two.getSolverStatus()){
                    if (one.getSolverStatus() ==Optimal){
//                        info("Equal " + one.getSolverStatus() + " " + one.getMakespan() + " " + two.getMakespan());
                        assert(one.getMakespan() == 0+two.getMakespan());
                        timeOne += one.getTime();
                        timeTwo += two.getTime();
                        bothOptimal++;

                    } else {
                        notOptimal++;
//                        info("Equal " + one.getSolverStatus() + " " + one.getMakespan() + " " + two.getMakespan());
                        oneSpan += one.getMakespan();
                        twoSpan += two.getMakespan();
                        oneBound += one.getBound();
                        twoBound += two.getBound();
                        minDiff =Math.min(minDiff,one.getMakespan()-two.getMakespan());
                        maxDiff =Math.max(maxDiff,one.getMakespan()-two.getMakespan());
                        spanCnt++;
                    }

                } else {
//                    info("Different status "+one.getSolverStatus()+" "+two.getSolverStatus());
                    oneSpan += one.getMakespan();
                    twoSpan += two.getMakespan();
                    oneBound += one.getBound();
                    twoBound += two.getBound();
                    spanCnt++;
                    if (one.getSolverStatus() == Optimal){
                        oneOnlyOptimal++;
                        if (one.getMakespan()==0+two.getMakespan()){
                            sameCostOne++;
                        }
                    } else if (two.getSolverStatus() == Optimal){
                        twoOnlyOptimal++;
                        if (one.getMakespan()==0+two.getMakespan()){
                            sameCostTwo++;
                        }
                    }
                }

            } else {
                warning("Too many solutions "+instance);
                assert(false);
            }
        }
        info("Only one entry "+onlyOneCnt+" two entries "+twoCnt);
        info("Time for "+bothOptimal+" "+percent(bothOptimal,twoCnt)+" optimal cases "+variantOne+" "+timeOne+" "+variantTwo+" "+timeTwo);
        info("One only Optimal "+variantOne+" "+oneOnlyOptimal +" "+percent(oneOnlyOptimal,twoCnt)+" same Cost "+sameCostOne);
        info("Two only Optimal "+variantTwo+" "+twoOnlyOptimal+" "+percent(twoOnlyOptimal,twoCnt)+" same Cost "+sameCostTwo);
        info("Not optimal "+notOptimal+" "+percent(notOptimal,twoCnt));
        info("Sum Makespan "+spanCnt+" one "+variantOne +" "+oneSpan+" "+variantTwo+" "+twoSpan);
        info("Sum Bound "+spanCnt+" one "+variantOne +" "+oneBound+" "+variantTwo+" "+twoBound);
        info("Two Better than one by "+minDiff+" one better than two by "+maxDiff);
    }

    private static String percent(double a,double b){
        return String.format("%5.2f%%",100.0*a/b);
    }

}

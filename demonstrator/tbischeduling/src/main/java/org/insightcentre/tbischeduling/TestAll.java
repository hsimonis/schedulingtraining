package org.insightcentre.tbischeduling;

import framework.types.DateTime;
import framework.types.IrishCalendar;
import org.insightcentre.tbischeduling.datamodel.ObjectiveType;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverRun;
import org.insightcentre.tbischeduling.exporter.WriteData;
import org.insightcentre.tbischeduling.implementedsolver.CPOModel;
import org.insightcentre.tbischeduling.importer.ReadData;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.insightcentre.tbischeduling.datamodel.ModelType.CPO;
import static org.insightcentre.tbischeduling.datamodel.SolverStatus.ToRun;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

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
//        testAll("imports/Taillard/OSS/");
//        analyzeAll("imports/Taillard/OSS/","Taillard OpenShop","oss");
//        testAll("imports/Taillard/JSS/");
//        analyzeAll("imports/Taillard/JSS/","Taillard JobShop","jss");
        testAll("imports/Taillard/FSS/");
//        analyzeAll("imports/Taillard/FSS/","Taillard Flowshop","fss");
    }

    private static void testAll(String importDir){
        assert(importDir.endsWith("/"));
        List<String> list =  listFilesUsingJavaIO(importDir);

        for(String fileName:list) {
            info("trying file " + fileName);
            String outputFile = "results/" + fileName;

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
            test.setEnforceReleaseDate(false);
            test.setEnforceDueDate(false);
            test.setEnforceCumulative(false);
            test.setEnforceWip(false);
            test.setEnforceDowntime(false);
            test.setEnforceSetup(false);
            test.setEnforceTransportTime(false);
            test.setRelaxSequence(false);
            test.setTimeout(600);
            test.setModelType(CPO);
            test.setObjectiveType(ObjectiveType.Makespan);
            test.setNrThreads(2);

//            info("Nr SolverRun " + base.getListSolverRun().size());
            for (SolverRun run : base.getListSolverRun().stream().filter(x -> x.getSolverStatus() == ToRun).toList()) {
                info("Running " + run.getName());
                new CPOModel(base, run).solve();
            }
            new WriteData(base).toFile(new File(importDir + outputFile), 2);
        }

    }

    public static List<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .filter(x->x.endsWith(".json"))
//                .limit(2)
                .collect(Collectors.toList());
    }

    private static void analyzeAll(String importDir,String title,String suffix){
        assert(importDir.endsWith("/"));
        List<String> list =  listFilesUsingJavaIO(importDir);
        try{
            PrintWriter out = new PrintWriter("reports/results"+suffix+".tex");
            out.printf("\\begin{longtable}{lrrlrrrr}\n");
            out.printf("\\caption{Results for %s (%d Instances)}\\\\\\toprule\n",title,list.size());
            out.printf("Name & \\shortstack{Nr\\\\Jobs} & \\shortstack{Nr\\\\Machines} & Status & Time & Makespan & Bound & \\shortstack{Gap\\\\Percent}\\\\ \\midrule\n");
            out.printf("\\endhead\n");
            out.printf("\\bottomrule\n");
            out.printf("\\endfoot\n");


            for(String fileName:list) {
//            info("analyzing file " + fileName);
                String outputFile = "results/" + fileName;
                try {
                    JSONObject root = new JSONObject(new String(Files.readAllBytes(Paths.get(importDir + outputFile))));
                    JSONObject sol = root.getJSONArray("solution").getJSONObject(0);
                    JSONObject problem = root.getJSONArray("problem").getJSONObject(0);
                    JSONObject solverRun = root.getJSONArray("solverRun").getJSONObject(0);
                    String name = solverRun.getString("name");
                    int nrJobs = problem.getInt("nrJobs");
                    int nrMachines = problem.getInt("nrDisjunctiveResources");
                    String status = sol.getString("solverStatus");
                    double time = solverRun.getDouble("time");
                    double bound = sol.getDouble("bound");
                    double gap = sol.getDouble("gap");
                    int makespan = sol.getInt("makespan");
                    info(name + " " + nrJobs + " " + nrMachines + " " + status + " " + time + " " + makespan + " " + bound + " " + gap);
                    out.printf("%s & %d & %d & %s & %5.2f & %d & %5.2f & %5.2f\\\\\n",
                            name.replaceAll("_"," "),nrJobs,nrMachines,status,time,makespan,bound,gap*100.0);

                } catch (IOException e) {
                    severe("Cannot read file " + outputFile + ", exception " + e.getMessage());
                }catch (Exception e) {
                    severe(importDir+" Instance "+fileName+" Exception " + e.getMessage());
                }
            }
            out.printf("\\end{longtable}\n\n");
            out.close();

        }catch(IOException e){
            severe("Cannot write file "+"results.tex"+", exception "+e.getMessage());
        }
    }


}

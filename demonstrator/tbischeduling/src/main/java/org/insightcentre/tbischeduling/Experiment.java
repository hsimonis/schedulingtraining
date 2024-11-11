package org.insightcentre.tbischeduling;

import framework.types.DateTime;
import framework.types.IrishCalendar;
import org.insightcentre.tbischeduling.datamodel.ObjectiveType;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverRun;
import org.insightcentre.tbischeduling.exporter.WriteData;
import org.insightcentre.tbischeduling.implementedsolver.CPOModel;
import org.insightcentre.tbischeduling.importer.ReadData;
import org.insightcentre.tbischeduling.importer.ReadSALBPFile;
import org.insightcentre.tbischeduling.importer.ReadTestSchedulingFile;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
public class Experiment {


    static {
        IrishCalendar.initIrishCalendar();
        IrishCalendar.buildCalendar();
    }


    public static void main(String[] args) {
        experiment("imports/taillard/jss/","tai100_20_0.json","timeout",20);
//        analyzeAll("imports/taillard/jss/experiments/","Different Seed","seed");
    }

    /*
    test a directory of files in the correct JSON format
    read each file, create a SolverRun to run it, run the test and write the result in the results subdirectory under the same name
    important: check the SolverRun for the correct test settings
     */
    private static void experiment(String importDir,String fileName,String type,int cnt){
        assert(importDir.endsWith("/"));

        info("trying file " + fileName);
        for(int i=0;i<cnt;i++) {
            String outputFile = importDir + "experiments/" + type +i+".json";

            Scenario base = new Scenario();
            base.setDataFileVersionNumber(8.0);
            base.setDataFile("");
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
            test.setModelType(CPO);
            test.setObjectiveType(ObjectiveType.Makespan);
            switch (type) {
                case "same" -> {
                    base.setHorizon(50000);
                    test.setSeed(1);
                    test.setNrThreads(1);
                    test.setTimeout(600);
                }
                case "seed" -> {
                    base.setHorizon(50000);
                    test.setSeed(i);
                    test.setNrThreads(1);
                    test.setTimeout(600);
                }
                case "threads" -> {
                    base.setHorizon(50000);
                    test.setSeed(1);
                    test.setNrThreads(1 + i % 8);
                    test.setTimeout(600);
                }
                case "horizon" -> {
                    base.setHorizon(5464 + i * 100);
                    test.setSeed(1);
                    test.setNrThreads(1);
                    test.setTimeout(600);
                }
                case "timeout" -> {
                    base.setHorizon(50000);
                    test.setSeed(1);
                    test.setNrThreads(1);
                    test.setTimeout(i * 100);
                }
                default -> {
                    severe("bad type " + type);
                    assert (false);
                }
            }

//            info("Nr SolverRun " + base.getListSolverRun().size());
            for (SolverRun run : base.getListSolverRun().stream().filter(x -> x.getSolverStatus() == ToRun).toList()) {
                info("Running " + run.getName());
                new CPOModel(base, run).solve();
            }
            new WriteData(base).toFile(new File(outputFile), 2);

        }


    }

    private static void analyzeAll(String resultDir,String title,String suffix){
        assert(resultDir.endsWith("/"));
        List<String> list =  listFilesUsingJavaIO(resultDir,".json");
        String reportFile = "reports/experiments"+suffix+".tex";
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
                    String name = fileName;
//                    String name = solverRun.getString("name");
                    int nrJobs = problem.getInt("nrJobs");
                    int nrMachines = problem.getInt("nrDisjunctiveResources");
                    String status = sol.getString("solverStatus");
                    double time = solverRun.getDouble("time");
                    double bound = sol.getDouble("bound");
                    double gap = sol.getDouble("gap");
                    int makespan = sol.getInt("makespan");
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

}

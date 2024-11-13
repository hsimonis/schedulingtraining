package org.insightcentre.tbischeduling.minizinc;

import org.insightcentre.tbischeduling.datamodel.SolutionStatus;
import org.insightcentre.tbischeduling.datamodel.SolverBackend;
import org.insightcentre.tbischeduling.datamodel.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Hashtable;

import static org.insightcentre.tbischeduling.datamodel.SolutionStatus.*;
import static org.insightcentre.tbischeduling.datamodel.SolverBackend.*;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;
import static org.insightcentre.tbischeduling.minizinc.RunMiniZinc.asString;

public class RunMiniZincGeneral {
    String costTag = "obj";
    String startTag = "start";
    String tasksTag = "tasks";

    SolverBackend solver;
    String directory;
    String program;
    String dataFile;
    String resFile;
    int timeout;
    int threads;

    public RunMiniZincGeneral(String directory, SolverBackend solver, String program, String dataFile, String resFile, int timeout, int threads) {
        this.directory = directory;
        this.solver = solver;
        this.program = program;
        this.dataFile = dataFile;
        this.resFile = resFile;
        this.timeout = timeout;
        this.threads = threads;
    }

    public SolMultiple solve() {
        // get rid of any previously existing result file with that name
        deleteExistingResultFile(directory, resFile);

        // run Minizinc
        runMiniZinc(directory, program, dataFile, resFile);

        // parse the result file for information
        try {
            BufferedReader br = new BufferedReader(new FileReader(directory + resFile));
            String line = br.readLine();
            if (line==null || line.equals("")){
                return null;
            }
            info(line);
            // expect at least one message
            JSONObject json = new JSONObject(line);
            SolMultiple res = interpret(json);
            while (line != null) {
                line = br.readLine();
                if (line != null) {
                    info(line);
                    if (line.startsWith("{")) {
                        json = new JSONObject(line);
                        // combine the elements of the solution extracted from multiple messages
                        SolMultiple added = interpret(json);
                        if (res == null) {
                            res = added;

                        } else {
                            res.merge(added);
                        }
                    }
                }
            }
            br.close();
            return res;
        } catch (IOException e) {
            severe("Cannot read result file " + directory + resFile);
            return null;
        }
    }

    /*
    convert the JSON result back into something the program can handle
     */
    private SolMultiple interpret(JSONObject json) {
        String type = json.getString("type");
        if (type.equals("solution")) {
            JSONObject output = json.getJSONObject("output");
            String defaultOutput = output.getString("default");
            JSONObject sol = new JSONObject(defaultOutput);
            int cost = sol.getInt(costTag); //depends on user program
            int[] start = toVector(sol.getJSONArray(startTag)); //depends on user program
            String[] tasks = toTasks(sol.getJSONArray(tasksTag));
            String[] machines = toMachines(sol.getJSONArray(tasksTag));
            String[] jobs = toJobs(sol.getJSONArray(tasksTag));
           double time = json.getDouble("time");
            SolMultiple res = new SolMultiple(cost, start,tasks,machines,jobs,time/1000.0);
            return res;
        } else if (type.equals("status")) {
            String status = json.getString("status");
//                info("Type: "+type+" "+status);
            int time = json.getInt("time");
            return new SolMultiple(convertStatus(status), time);
        } else if (type.equals("statistics")) {
            JSONObject stats = json.getJSONObject("statistics");
            int objective = stats.getInt("objective");
            int nodes = 0;
            if (stats.has("nodes")) {
                nodes = stats.getInt("nodes");
            }
            double solveTime = stats.getDouble("solveTime");
            return new SolMultiple(new Stats(objective, nodes, solveTime));
        } else if (type.equals("comment")) {
            if (json.has("comment")) {
                info("Comment " + json.getString("comment"));
            }
            return null;
        } else {
            info("Unknown Type: " + type);
            assert (false);
            return null;
        }
    }



    private SolutionStatus convertStatus(String text){
        switch (text){
            case "UNSATISFIABLE": return Unsatisfiable;
            case "UNKNOWN": return Unknown;
            case "OPTIMAL_SOLUTION": return Optimal;
            default:
                severe("Bad status "+text);
                assert(false);
                return null;
        }
    }

    private int[][] toArray(JSONArray arr){
        int rows = arr.length();
        int cols = arr.getJSONArray(0).length();
        int[][] res = new int[rows][cols];
        for(int i=0;i<rows;i++){
            JSONArray line = arr.getJSONArray(i);
            for(int j=0;j<cols;j++){
                int v = line.getInt(j);
                res[i][j] = v;
            }
        }
        return res;
    }

    private String[] toTasks(JSONArray arr){

        int size = arr.length();
        String[] res = new String[size];
        for(int i=0;i<size;i++){
            JSONObject item = arr.getJSONObject(i);
            String name = item.getString("name");
            res[i] = name;
        }
        return res;
    }
    private String[] toMachines(JSONArray arr){

        int size = arr.length();
        String[] res = new String[size];
        for(int i=0;i<size;i++){
            JSONObject item = arr.getJSONObject(i);
            String name = item.getString("machine");
            res[i] = name;
        }
        return res;
    }
    private String[] toJobs(JSONArray arr){

        int size = arr.length();
        String[] res = new String[size];
        for(int i=0;i<size;i++){
            JSONObject item = arr.getJSONObject(i);
            String name = item.getString("job");
            res[i] = name;
        }
        return res;
    }
    private int[] toVector(JSONArray arr){
        int entries = arr.length();
        int[] res = new int[entries];
        for(int i=0;i<entries;i++){
                int v = arr.getInt(i);
                res[i] = v;
        }
        return res;
    }

    private void runMiniZinc(String directory, String program,String dataFile, String resultFile) {
        String cmd = String.format("minizinc -p %d --solver %s --time-limit %d000 --no-intermediate --json-stream --output-time -s " +
                "-o %s %s %s",threads,asString(solver), timeout,resultFile,program,dataFile);
        try {
            ProcessBuilder pb;
            pb = new ProcessBuilder("minizinc",
                    "-p",String.format("%d",threads),
                    "--solver", asString(solver),
                    "--time-limit", String.format("%d000", timeout),
 //                   "--no-intermediate",
                    "--json-stream",
                    "--output-time",
                    "-s",
                    "-o", resultFile,
                    program,dataFile);

            info("command-line: "+cmd);
            pb.directory(new File(directory));
//            info("directory: " + directory);
            pb.redirectErrorStream(true);
            File log = new File(directory+"log.txt");
            pb.redirectErrorStream(true);
            pb.redirectOutput(ProcessBuilder.Redirect.to(log));
            info("Start program: " + program);
            Process p = pb.start();
//           info("started");
            p.waitFor();
            info("command ran");
        } catch(Exception e) {
            severe("Problem with executing command, " + e.getMessage());
        }
    }


    private void deleteExistingResultFile(String directory,String resFile){
        String fullName = directory+resFile;
        try {
            Files.deleteIfExists(
                    Paths.get(fullName));
        }
        catch (NoSuchFileException e) {
            severe("No such file/directory exists");
        }
        catch (DirectoryNotEmptyException e) {
            severe("Directory is not empty.");
        }
        catch (IOException e) {
            severe("Invalid permissions.");
        }
    }

    // utility to convert string into solverType
    public static SolverBackend asSolverType(String text){
        switch(text){
            case "Cplex": return Cplex;
            case "Chuffed": return Chuffed;
            case "Gecode": return Gecode;
            case "CPSat": return CPSat;
            default:
                severe("Unknown Solver Type "+text);
                assert(false);
                return null;

        }
    }



}

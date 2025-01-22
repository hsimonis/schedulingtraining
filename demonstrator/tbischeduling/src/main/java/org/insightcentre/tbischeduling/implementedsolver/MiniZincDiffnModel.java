package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.minizinc.MiniZincProblem;
import org.insightcentre.tbischeduling.minizinc.RunMiniZincGeneral;
import org.insightcentre.tbischeduling.minizinc.SolMultiple;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static org.insightcentre.tbischeduling.datamodel.SolverStatus.*;
import static org.insightcentre.tbischeduling.logging.LogShortcut.*;

public class MiniZincDiffnModel extends AbstractModel{
    int jaNr=1;
    public MiniZincDiffnModel(Scenario base, SolverRun run){
        super(base,run);
    }

    public boolean solve(){
        String minizincDir = "minizinc/";
        String dataFile = "data.json";
        String resultFile = "res.json";
        String program="problem.mzn";

        JSONObject problem = new MiniZincProblem(base).create();
        info("write problem file "+minizincDir+dataFile);
        writeFile(problem,minizincDir+dataFile);
        info("running solver");
        SolMultiple sol = new RunMiniZincGeneral(minizincDir, run.getSolverBackend(),
                program,
                dataFile,
                resultFile, run.getTimeout(),run.getNrThreads()).solve();
        if (sol != null) {
            info("Status" + sol.getSolutionStatus());
//        info("tasks "+sol.getStart().length+" Cost "+sol.getCost());
//        info("names "+ Arrays.toString(sol.getTasks()));
            SolverStatus status = toSolverStatus(sol.getSolutionStatus());
            run.setSolverStatus(status);
            run.setTime(sol.getTime());
            if (status == Optimal || status == Solution) {
                Solution solution = new Solution(base);
                solution.setName("minizinc");
                solution.setObjectiveValue(sol.getCost());
                // we should get a bound from MiniZinc
                solution.setBound(0.0);
                solution.setGapPercent(100.0);
                solution.setSolverRun(run);
                solution.setSolverStatus(status);
                Hashtable<Job, JobAssignment> jobHash = new Hashtable<>();
                for (int i = 0; i < sol.getStart().length; i++) {
                    int start = sol.getStart()[i];
                    String name = sol.getTasks()[i];
                    Task t = Task.findByName(base, name);
                    DisjunctiveResource m = DisjunctiveResource.findByName(base, sol.getMachines()[i]);
                    Job j = Job.findByName(base, sol.getJobs()[i]);
                    assert (t != null);
                    TaskAssignment ta = new TaskAssignment(base);
                    ta.setName("TA" + i);
                    ta.setTask(t);
                    ta.setStart(start);
                    ta.setEnd(start + t.getDuration());
                    ta.setDuration(t.getDuration());
                    ta.setDisjunctiveResource(m);
                    ta.setJobAssignment(findJobAssignment(j, solution, jobHash));
//            ta.setJobAssignment(ja);
                }
                updateJA(solution);

                return true;
            } else {
                info("Solver failed");
                return false;
            }
        } else {
            warning("No answer returned");
            return false;
        }


    }

    private void updateJA(Solution solution){
        Map<JobAssignment, List<TaskAssignment>> map = base.getListTaskAssignment().stream().
                filter(x->x.getJobAssignment().getSolution()==solution).
                collect(groupingBy(TaskAssignment::getJobAssignment));
        for(JobAssignment ja:map.keySet()){
            List<TaskAssignment> tasks = map.get(ja);
            ja.setStart(tasks.stream().mapToInt(TaskAssignment::getStart).min().orElse(0));
            ja.setEnd(tasks.stream().mapToInt(TaskAssignment::getEnd).max().orElse(0));
            ja.setDuration(ja.getEnd()-ja.getStart());
            ja.setLate(Math.max(0,ja.getEnd()-ja.getJob().getOrder().getDue()));
            ja.setEarly(Math.max(0,ja.getJob().getOrder().getDue()-ja.getEnd()));
        }
        List<JobAssignment> jList =base.getListJobAssignment().stream().filter(x->x.getSolution()==solution).toList();
        solution.setStart(jList.stream().mapToInt(JobAssignment::getStart).min().orElse(0));
        solution.setEnd(jList.stream().mapToInt(JobAssignment::getEnd).max().orElse(0));
        solution.setMakespan(jList.stream().mapToInt(JobAssignment::getEnd).max().orElse(0));
        solution.setFlowtime(jList.stream().mapToInt(JobAssignment::getEnd).sum());
        solution.setDuration(solution.getEnd()-solution.getStart());
        solution.setTotalLateness(jList.stream().mapToInt(JobAssignment::getLate).sum());
        solution.setWeightedLateness(jList.stream().mapToDouble(x->x.getLate()*x.getJob().getOrder().getLatenessWeight()).sum());
        solution.setMaxLateness(jList.stream().mapToInt(JobAssignment::getLate).max().orElse(0));
        solution.setNrLate((int)jList.stream().filter(x->x.getLate() > 0).count());
        solution.setPercentLate(100.0*solution.getNrLate()/jList.size());

    }

    private JobAssignment findJobAssignment(Job j, Solution s, Hashtable<Job,JobAssignment> jobHash){
        JobAssignment res = jobHash.get(j);
        if (res == null){
            res = new JobAssignment(base);
            res.setName("JA"+jaNr++);
            res.setJob(j);
            res.setSolution(s);
            jobHash.put(j,res);
        }
        return res;
    }

    private void writeFile(JSONObject root,String fileName){
        try{
            PrintWriter out = new PrintWriter(fileName);
            out.printf("%s\n",root.toString(2));
            out.close();
        } catch(IOException e){
            severe("Cannot write file "+fileName+", exception "+e.getMessage());
            assert(false);
        }
    }

    private SolverStatus toSolverStatus(SolutionStatus status){
        switch(status){
            case Optimal: return Optimal;
            case Solution: return SolverStatus.Solution;
            case Unknown: return Unknown;
            case Unsatisfiable: return Infeasible;
            default:
                severe("Bad solutionStatus "+status);
                assert(false);
                return null;
        }
    }
}

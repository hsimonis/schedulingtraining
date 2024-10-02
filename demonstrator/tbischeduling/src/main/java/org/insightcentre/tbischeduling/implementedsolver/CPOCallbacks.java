package org.insightcentre.tbischeduling.implementedsolver;

import ilog.concert.IloException;
import ilog.cp.IloCP;
import org.insightcentre.tbischeduling.datamodel.IntermediateSolution;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverRun;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class CPOCallbacks implements IloCP.Callback {
    Scenario base;
    SolverRun run;
    int nr;
    private double lb;
    private double ub;
    private double gap;
    public CPOCallbacks(Scenario base,SolverRun run) {
        this.base = base;
        this.run = run;
        init();
    }
    public void init() {
        nr = 1;
        lb = Double.NEGATIVE_INFINITY;
        ub = Double.POSITIVE_INFINITY;
        gap = Double.POSITIVE_INFINITY;
    }
    public void invoke(IloCP cp, Reason reason)  {
        try {
            // Initialize the local fields at the beginning of search,
            // and print the banner
            if (reason == Reason.StartSolve) {
                init();
                info("Start solver");
            }
            else if (reason == Reason.EndSolve) {
                // Finish the line at the end of search
                info("Finish search");
            }
            else {
                boolean soln = (reason == Reason.Solution);
                boolean bnd = (reason == Reason.ObjBound);
                if (soln || bnd) {
                    if (soln) {
                        // Update upper bound and gap (solution found)
                        ub = cp.getObjValue();
                        if (lb > Double.NEGATIVE_INFINITY)
                            gap = cp.getObjGap();
                    } else if (bnd) {
                        // Update lower bound and gap (new bound)
                        lb = cp.getObjBound();
                        if (ub < Double.POSITIVE_INFINITY)
                            gap = cp.getObjGap();
                    }

                    if (gap != Double.POSITIVE_INFINITY) {
                        // Both a bound and a solution.
//                        info("Solution lb " + lb + " ub " + ub + " gap " + gap + " time " + cp.getInfo(IloCP.DoubleInfo.SolveTime) + " reason " + reason.toString());
                        IntermediateSolution sol = new IntermediateSolution(base);
                        sol.setName("IntSol"+nr);
                        sol.setNr(nr++);
                        sol.setSolverRun(run);
                        sol.setBound(lb);
                        sol.setCost(ub);
                        sol.setGapPercent(100.0 * gap);
                        sol.setTime(cp.getInfo(IloCP.DoubleInfo.SolveTime));

                    }
                }
            }
        }
        catch (IloException ex) {
            severe("FATAL: Exception encountered during callback " + ex);
            System.exit(-1);
        }
    }


    static boolean solveWithCallback(Scenario base, IloCP cp, SolverRun run) throws IloException {
        // Add callback, solve, then remove it.
        CPOCallbacks cb = new CPOCallbacks(base,run);
        cp.addCallback(cb);
        boolean res = cp.solve();
        cp.removeCallback(cb);
        return res;
    }

}

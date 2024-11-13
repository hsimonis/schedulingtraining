package org.insightcentre.tbischeduling.minizinc;

public class Stats {
    int objective;
    int nodes;
    double solveTime;

    public Stats(int objective,int nodes,double solveTime){
        this.objective = objective;
        this.nodes = nodes;
        this.solveTime = solveTime;

    }

    public double getSolveTime(){
        return solveTime;
    }
}

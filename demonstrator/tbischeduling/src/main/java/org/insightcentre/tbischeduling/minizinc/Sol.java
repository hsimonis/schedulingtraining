package org.insightcentre.tbischeduling.minizinc;

import org.insightcentre.tbischeduling.datamodel.SolutionStatus;

import static org.insightcentre.tbischeduling.datamodel.SolutionStatus.Solution;

public abstract class Sol {
    int cost;
    double time;
    boolean valid;
    SolutionStatus status;
    Stats stats;
    public Sol(int cost,double time){
        this.status = Solution;
        this.cost = cost;
        this.time = time;
        this.valid = true;
    }

    public Sol(SolutionStatus status,double time){
        this.status = status;
        this.time = time;
        this.valid = false;
    }

    public Sol(Stats stats){
        this.stats = stats;
        this.valid = false;
    }

    public int getCost(){
        return cost;
    }

    public double getTime(){ return time;}

    public boolean isValid(){
        return valid;
    }

    public SolutionStatus getSolutionStatus(){
        return status;
    }

    public double getSolveTime(){
        if (stats != null){
            return stats.getSolveTime();
        }
        return Double.NaN;
    }

}

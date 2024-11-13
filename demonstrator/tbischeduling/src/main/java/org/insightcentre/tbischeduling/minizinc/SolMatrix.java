package org.insightcentre.tbischeduling.minizinc;

import org.insightcentre.tbischeduling.datamodel.SolutionStatus;

public class SolMatrix extends Sol{
    int[][] matrix;

    public SolMatrix(int cost,int[][] matrix,int time){
        super(cost,time);
        this.matrix = matrix;
    }

    public SolMatrix(SolutionStatus status,int time){
        super(status,time);
    }

    public SolMatrix(Stats stats) {super(stats);}

    public int[][] getMatrix(){
        return matrix;
    }

    public SolMatrix merge(SolMatrix added){
        if (added != null) {
            valid = valid || added.isValid();
            if (added.stats != null) {
                stats = added.stats;
            }
            if (added.status != null) {
                status = added.status;
            }
            if (added.getMatrix() != null) {
                assert(matrix == null);
                matrix = added.getMatrix();
                cost = added.getCost();
                time = added.getTime();
            }
        }
        return this;
    }
}

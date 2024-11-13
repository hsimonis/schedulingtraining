package org.insightcentre.tbischeduling.minizinc;

import org.insightcentre.tbischeduling.datamodel.SolutionStatus;

public class SolVector extends Sol{
    int[] vector;
    int unassigned;

    public SolVector(int cost, int[] vector, int unassigned,int time){
        super(cost,time);
        this.vector = vector;
        this.unassigned = unassigned;
    }

    public SolVector(SolutionStatus status, int time){
        super(status,time);
    }

    public SolVector(Stats stats) {super(stats);}

    public int[] getVector(){
        return vector;
    }

    public int getUnassigned(){return unassigned;}

    public SolVector merge(SolVector added){
        if (added != null) {
            valid = valid || added.isValid();
            if (added.stats != null) {
                stats = added.stats;
            }
            if (added.status != null) {
                status = added.status;
            }
            if (added.getVector() != null) {
                assert(vector == null);
                vector = added.getVector();
                unassigned = added.getUnassigned();
                cost = added.getCost();
                time = added.getTime();
            }
        }
        return this;
    }
}

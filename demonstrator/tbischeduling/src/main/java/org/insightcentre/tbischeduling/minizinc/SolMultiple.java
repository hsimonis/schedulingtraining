package org.insightcentre.tbischeduling.minizinc;

import org.insightcentre.tbischeduling.datamodel.SolutionStatus;
import org.insightcentre.tbischeduling.datamodel.Task;

public class SolMultiple extends Sol{
    int[] start;
    String[] tasks;
    String[] machines;
    String[] jobs;

    public SolMultiple(int cost, int[] start, String[] tasks, String[] machines, String[] jobs, double time){
        super(cost,time);
        this.start = start;
        this.tasks = tasks;
        this.machines = machines;
        this.jobs = jobs;
    }

    public SolMultiple(SolutionStatus status, int time){
        super(status,time);
    }

    public SolMultiple(Stats stats) {super(stats);}


    public int[] getStart(){
        return start;
    }

    public String[] getTasks(){return tasks;}
    public String[] getMachines(){return machines;}
    public String[] getJobs(){return jobs;}
    public SolMultiple merge(SolMultiple added){
        if (added != null) {
            valid = valid || added.isValid();
            if (added.stats != null) {
                stats = added.stats;
            }
            if (added.status != null) {
                status = added.status;
            }
            if (added.getStart() != null) {
                assert(start == null);
                start = added.getStart();
                tasks = added.getTasks();
                machines = added.getMachines();
                jobs = added.getJobs();
                cost = added.getCost();
                time = added.getTime();
            }
        }
        return this;
    }
}

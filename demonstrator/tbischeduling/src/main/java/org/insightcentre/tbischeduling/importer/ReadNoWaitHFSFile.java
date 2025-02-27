package org.insightcentre.tbischeduling.importer;

import framework.types.DateTime;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.implementedsolver.CheckSolutions;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

import static org.insightcentre.tbischeduling.importer.CreateData.summarizeProblem;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class ReadNoWaitHFSFile {
    public ReadNoWaitHFSFile(Scenario base, File file){
        Reset.resetData(base);
        DateTime startDate = new DateTime(2024,10,1,0,0);
        base.setStartDateTime(startDate);
        base.setTimeResolution(60);
        base.setHorizon(2000);
        base.setDataFile(file.getName());
        base.setHasReleaseDate(false);
        base.setHasCumulative(false);
        base.setHasDueDate(false);
        base.setHasWiP(false);
        base.setHasDowntime(false);
        base.setHasSetupTime(false);
        base.setHasTransportTime(false);

        try {
            Scanner scanner = new Scanner(file);
            Problem prob = new Problem(base);
            prob.setName(file.getName());
            int nrOrders = scanner.nextInt();
            for(int i=0;i<nrOrders;i++){
           }
            int nrStages = scanner.nextInt();
            int[] machinesPerStage = new int[nrStages];
            for(int i=0;i<nrStages;i++){
                machinesPerStage[i] = scanner.nextInt();
            }
            Hashtable<String,DisjunctiveResource> mHash = new Hashtable<>();
            for(int i=0;i<nrStages;i++){
                for(int j=0;j<machinesPerStage[i];j++){
                    double rate = scanner.nextDouble();
                    DisjunctiveResource mach = new DisjunctiveResource(base);
                    mach.setName("M"+i+"_"+j);
                    mach.setShortName(mach.getName());
                    mHash.put(key(i,j),mach);
                }
            }
            for(int i=0;i<nrOrders;i++){
                Order ord = new Order(base);
                ord.setName("Order"+i);
                ord.setNr(i);
                ord.setQty(1);
                Product p = new Product(base);
                p.setName("P"+i);
                Process proc = new Process(base);
                proc.setName("Proc"+i);
                Job job = new Job(base);
                job.setName("J"+i);
                job.setNr(i);
                job.setProcess(proc);
                job.setOrder(ord);

                ord.setProduct(p);
                ord.setProcess(proc);
                ord.setQty(1);
                p.setDefaultProcess(proc);
                ProcessStep prev = null;
                for(int j=0;j<nrStages;j++){
                    ProcessStep ps = new ProcessStep(base);
                    ps.setName("PS"+i+"_"+j);
                    ps.setShortName(ps.getName());
                    ps.setProcess(proc);
                    ps.setStage(j);
                    Task task = new Task(base);
                    task.setName("T"+i+"_"+j);
                    task.setShortName(task.getName());
                    task.setJob(job);
                    task.setProcessStep(ps);
                    task.setStage(j);
                    if (prev != null){
                        ProcessSequence pseq = new ProcessSequence(base);
                        pseq.setName("Pseq"+i+"_"+j);
                        pseq.setBefore(prev);
                        pseq.setAfter(ps);
                        pseq.setSequenceType(SequenceType.NoWait);
                    }
                    prev = ps;
                    for(int m=0;m<machinesPerStage[j];m++){
                        int jobId = scanner.nextInt();
                        int stageId = scanner.nextInt();
                        int machineId = scanner.nextInt();
                        int dur = scanner.nextInt();
                        assert(i+1 == jobId);
                        assert(j+1 == stageId);
                        assert(m+1 == machineId);
                        assert(dur > 0);
                        ResourceNeed rn = new ResourceNeed(base);
                        rn.setName("RN"+i+"_"+j+"_"+m);
                        rn.setProcessStep(ps);
                        rn.setDisjunctiveResource(mHash.get(key(j,m)));
                        rn.setDurationFixed(dur);

                    }
                }
            }
            scanner.close();
            summarizeProblem(base);
            new CheckSolutions(base,base.getListSolution());
            info("File read, "+base.getListInputError().size()+" input error(s), "+base.getListSolutionError().size()+" solution errors");

        } catch(IOException e){
            severe("Cannot read file "+file+", exception "+e.getMessage());
        }


    }

    private String key(int i,int j){
        return i+"/"+j;
    }
}

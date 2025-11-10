package org.insightcentre.tbischeduling.importer;

import framework.types.DateTime;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.implementedsolver.CheckSolutions;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

import static java.lang.Math.min;
import static org.insightcentre.tbischeduling.importer.CreateData.summarizeProblem;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class ReadDFSNoWaitFile {
    public ReadDFSNoWaitFile(Scenario base, File file){
        Reset.resetData(base);
        DateTime startDate = new DateTime(2024,10,1,0,0);
        base.setStartDateTime(startDate);
        base.setTimeResolution(60);
        base.setHorizon(20000);
        base.setDataFile(file.getName());
        base.setHasReleaseDate(false);
        base.setHasCumulative(true);
        base.setHasDisjunctive(false);
        base.setHasDueDate(false);
        base.setHasWiP(false);
        base.setHasDowntime(false);
        base.setHasSetupTime(false);
        base.setHasTransportTime(false);

        try {
            Scanner scanner = new Scanner(file);
            Problem prob = new Problem(base);
            prob.setName(file.getName());
            String n = scanner.next();
            int nrOrders = scanner.nextInt();
            String m = scanner.next();
            int nrStages = scanner.nextInt();
            String cap = scanner.next();
            int capacity = scanner.nextInt();
            info("n "+nrOrders+" stages "+nrStages+" capacity "+capacity);
            String processing = scanner.next();

            CumulativeResource[] cumulativeResources = new CumulativeResource[nrStages];
            for(int i=0;i<nrStages;i++){
                CumulativeResource c = new CumulativeResource(base);
                c.setName("C"+i);
                CumulativeProfile cf = new CumulativeProfile(base);
                cf.setName("CF"+i);
                cf.setCumulativeResource(c);
                cf.setFrom(0);
                cf.setCapacity(capacity);
                cumulativeResources[i] = c;
            }
            ProcessStep[][] processSteps = new ProcessStep[nrOrders][nrStages];

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
                    int duration = scanner.nextInt();
                    ProcessStep ps = new ProcessStep(base);
                    ps.setName("PS"+i+"_"+j);
                    ps.setShortName(ps.getName());
                    ps.setProcess(proc);
                    ps.setStage(j);
                    ps.setDurationFixed(duration);
                    ps.setDurationPerUnit(0);
                    processSteps[i][j] = ps;
                    Task task = new Task(base);
                    task.setName("T"+i+"_"+j);
                    task.setShortName(task.getName());
                    task.setJob(job);
                    task.setProcessStep(ps);
                    task.setStage(j);
                    task.setDuration(duration);
                    if (prev != null){
                        ProcessSequence pseq = new ProcessSequence(base);
                        pseq.setName("Pseq"+i+"_"+j);
                        pseq.setBefore(prev);
                        pseq.setAfter(ps);
                        pseq.setSequenceType(SequenceType.NoWait);
                    }
                    prev = ps;
                }
            }
            String size = scanner.next();
            for(int i=0;i<nrOrders;i++){
                for(int j=0;j<nrStages;j++){
                    int demand = scanner.nextInt();
                    CumulativeNeed cn = new CumulativeNeed(base);
                    cn.setName("CN"+i+"_"+j);
                    cn.setDemand(demand);
                    cn.setProcessStep(processSteps[i][j]);
                    cn.setCumulativeResource(cumulativeResources[j]);
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
}

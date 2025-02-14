package org.insightcentre.tbischeduling.importer;

import framework.types.DateTime;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.implementedsolver.CheckSolutions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import static org.insightcentre.tbischeduling.importer.CreateData.summarizeProblem;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class ReadFJSPFile {
    public ReadFJSPFile(Scenario base, File file){
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
            int nrMachines = scanner.nextInt();
            DisjunctiveResource[] machineTable = new DisjunctiveResource[nrMachines];
            for(int m=0;m<nrMachines;m++){
                DisjunctiveResource d = new DisjunctiveResource(base);
                d.setName("M"+(m+1));
                d.setShortName("M"+(m+1));
                machineTable[m] = d;
            }
            //??? what does this value mean
            double ignore = scanner.nextDouble();

            for(int i=0;i<nrOrders;i++){
                Order ord = new Order(base);
                ord.setName("O"+i);
                ord.setNr(i);
                ord.setQty(1);
                Product p = new Product(base);
                p.setName("P"+i);
                Process proc = new Process(base);
                proc.setName("Proc"+i);
                Job job = new Job(base);
                job.setName("J"+i);
                job.setNr(i);
                job.setOrder(ord);
                job.setProcess(proc);

                ord.setProduct(p);
                ord.setProcess(proc);
                p.setDefaultProcess(proc);
                int nrSteps = scanner.nextInt();
                ProcessStep before = null;
                for(int j=0;j<nrSteps;j++){
                    ProcessStep ps = new ProcessStep(base);
                    ps.setName(proc.getName()+"_"+j);
                    ps.setShortName(ps.getName());
                    ps.setStage(j);
                    ps.setProcess(proc);
                    Task t = new Task(base);
                    t.setName("T"+i+"_"+j);
                    t.setShortName(t.getName());
                    t.setJob(job);
                    t.setDuration(1);
                    t.setProcessStep(ps);
                    int alternatives = scanner.nextInt();
                    for(int k=0;k<alternatives;k++){
                        int machine = scanner.nextInt();
                        int duration = scanner.nextInt();
                        ResourceNeed rs = new ResourceNeed(base);
                        rs.setName("RN"+i+"_"+j+"_"+k);
                        rs.setProcessStep(ps);
                        rs.setDisjunctiveResource(machineTable[machine-1]);
                        rs.setDurationFixed(duration);
                        rs.setDurationPerUnit(0);

                    }

                    if (before != null){
                        ProcessSequence seq = new ProcessSequence(base);
                        seq.setName("Seq "+i+j);
                        seq.setBefore(before);
                        seq.setAfter(ps);
                        seq.setSequenceType(SequenceType.EndBeforeStart);
                    }
                    before = ps;

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

package org.insightcentre.tbischeduling.importer;

import framework.types.DateTime;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;
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

public class ReadJJFlatFile {
    public ReadJJFlatFile(Scenario base, String fileName){
        Reset.resetData(base);
        DateTime startDate = new DateTime(2024,10,1,0,0);
        base.setStartDateTime(startDate);
        base.setTimeResolution(60);
        base.setHorizon(2000);
        base.setDataFile(fileName);
        base.setHasReleaseDate(false);
        base.setHasCumulative(false);
        base.setHasDueDate(false);
        base.setHasWiP(false);
        base.setHasDowntime(false);
        base.setHasSetupTime(false);
        base.setHasTransportTime(true);

        try {
            Scanner scanner = new Scanner(new File(fileName));
            int nrProcesses = scanner.nextInt();
            int nrStages = scanner.nextInt();
            int[] nrOrders = new int[nrProcesses];
            for(int i=0;i<nrProcesses;i++){
                nrOrders[i] = scanner.nextInt();
            }
            Hashtable<Integer,List<DisjunctiveResource>> hash = new Hashtable<>();
            List<DisjunctiveResource> allMachines = new ArrayList<>();
            for(int i=0;i<nrStages;i++){
                List<DisjunctiveResource> list = new ArrayList<>();
                int nrMachinesPerStage = scanner.nextInt();
                for(int j=0;j<nrMachinesPerStage;j++){
                    DisjunctiveResource r = new DisjunctiveResource(base);
                    r.setName("M"+i+"_"+j);
                    r.setShortName(r.getName());
                    list.add(r);
                    allMachines.add(r);
                }
                hash.put(i,list);
            }
            DisjunctiveResource[] machineArray = allMachines.toArray(new DisjunctiveResource[0]);

            int seqNr = 0;
            int rnNr = 0;
            Process[] processes = new Process[nrProcesses];
            Product[] products = new Product[nrProcesses];
            for(int i=0;i<nrProcesses;i++){
                Product product = new Product(base);
                product.setName("P"+i);
                products[i] = product;
                Process process = new Process(base);
                process.setName("Proc"+i);
                processes[i] = process;
                product.setDefaultProcess(process);
                ProcessStep prev = null;
                for(int j=0;j<nrStages;j++){
                    int duration = scanner.nextInt();
                    if (duration > 0){
                        ProcessStep ps = new ProcessStep(base);
                        ps.setName("PS"+i+"_"+j);
                        ps.setProcess(process);
                        ps.setStage(j);
                        ps.setDurationFixed(0);
                        ps.setDurationPerUnit(duration);
                        for(DisjunctiveResource r:hash.get(j)){
                            ResourceNeed rn = new ResourceNeed(base);
                            rn.setName("RN"+rnNr++);
                            rn.setProcessStep(ps);
                            rn.setDisjunctiveResource(r);
                        }
                        if (prev!= null){
                            ProcessSequence seq = new ProcessSequence(base);
                            seq.setName("Seq"+seqNr++);
                            seq.setBefore(prev);
                            seq.setAfter(ps);
                            seq.setOffset(0);
                            seq.setSequenceType(SequenceType.EndBeforeStart);

                        }
                        prev = ps;

                    }
                }
            }
            int ordNr = 0;
            int taskNr = 0;
            for(int i=0;i<nrProcesses;i++){
                for(int j=0;j<nrOrders[i];j++){
                    Order ord = new Order(base);
                    ord.setName("O"+i+"_"+j);
                    ord.setNr(ordNr);
                    Process p = processes[i];
                    ord.setProcess(p);
                    ord.setProduct(products[i]);
                    int qty = 1;
                    ord.setQty(qty);
                    ord.setRelease(0);
                    ord.setDue(0);
                    ord.setReleaseDate(startDate);
                    ord.setDueDate(startDate);
                    ord.setEarlinessWeight(1.0);
                    ord.setLatenessWeight(1.0);
                    Job job = new Job(base);
                    job.setName("J"+ordNr);
                    job.setOrder(ord);
                    job.setProcess(p);
                    job.setNoOverlap(false);
                    job.setNr(ordNr);
                    for(ProcessStep ps:base.getListProcessStep().stream().filter(x->x.getProcess()==p).toList()){
                        Task t = new Task(base);
                        t.setName("T"+taskNr++);
                        t.setShortName(t.getName());
                        t.setJob(job);
                        t.setProcessStep(ps);
                        t.setStage(ps.getStage());
                        t.setDuration(ps.getDurationFixed()+qty*ps.getDurationPerUnit());
                    }

                    ordNr++;




                }
            }
            Problem prob = new Problem(base);
            prob.setName("Prob");
            prob.setTimePointsAsDate(false);
            prob.setLabel("Generated from J&J flatfile description");
            prob.setNrCumulativeResources(0);
            int nrMachines = base.getListDisjunctiveResource().size();
            prob.setNrDisjunctiveResources(nrMachines);
            prob.setNrProcesses(nrProcesses);
            prob.setNrProducts(nrProcesses);
            prob.setNrOrders(base.getListOrder().size());

            Transport transport = new Transport(base);
            transport.setName("Transport");
            transport.setDefaultValue(-1);
            for(int i=0;i<nrMachines;i++){
                for(int j=0;j<nrMachines;j++){
                    int v = scanner.nextInt();
                    if (v >= 0){
                        TransportMatrix tm = new TransportMatrix(base);
                        tm.setName("TM"+i+"_"+j);
                        tm.setFrom(machineArray[i]);
                        tm.setTo(machineArray[j]);
                        tm.setValue(v);
                    }
                }
            }
            scanner.close();
            summarizeProblem(base);
            new CheckSolutions(base,base.getListSolution());
            info("File read, "+base.getListInputError().size()+" input error(s), "+base.getListSolutionError().size()+" solution errors");

        } catch(IOException e){
            severe("Cannot read file "+fileName+", exception "+e.getMessage());
        }


    }
}

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

public class ReadSALBPFile {
    public ReadSALBPFile(Scenario base, File file){
        Reset.resetData(base);
        DateTime startDate = new DateTime(2024,10,1,0,0);
        base.setStartDateTime(startDate);
        base.setTimeResolution(60);
        base.setDataFile(file.getName());
        base.setHasReleaseDate(false);
        base.setHasDisjunctive(false);
        base.setHasCumulative(true);
        base.setHasDueDate(false);
        base.setHasWiP(false);
        base.setHasDowntime(false);
        base.setHasSetupTime(false);
        base.setHasTransportTime(false);

        try {
            Scanner reader = new Scanner(file);
            skip(readField(reader));
            int nrTasks = reader.nextInt();
            Problem problem = new Problem(base);
            problem.setName("Problem");
            problem.setNrProcesses(1);
            problem.setNrProducts(1);
            problem.setNrOrders(1);
            problem.setNrCumulativeResources(1);
            problem.setNrDisjunctiveResources(0);
            problem.setNrJobs(1);
            problem.setNrTasks(nrTasks);
            Process process = new Process(base);
            process.setName("Process");
            Product product = new Product(base);
            product.setName("Product");
            product.setDefaultProcess(process);
            base.setHorizon(nrTasks);

            Order order = new Order(base);
            order.setName("Order");
            order.setNr(0);
            order.setQty(1);
            order.setProcess(process);
            order.setProduct(product);
            order.setDue(0);
            order.setRelease(0);
            Job job = new Job(base);
            job.setName("Job");
            job.setOrder(order);
            job.setProcess(process);
            job.setNr(1);
            job.setNoOverlap(false);

            CumulativeResource cumul = new CumulativeResource(base);
            cumul.setName("C");
            Hashtable<Integer,ProcessStep> taskHash = new Hashtable<>();
            Hashtable<Integer,Integer> demandHash = new Hashtable<>();
            problem.setNrTasks(nrTasks);
            skip(readField(reader));
            int cycleTime = reader.nextInt();
            CumulativeProfile cp = new CumulativeProfile(base);
            cp.setName("Profile");
            cp.setCumulativeResource(cumul);
            cp.setFrom(0);
            cp.setCapacity(cycleTime);

            skip(readField(reader));
            skip(readField(reader));
            skip(readField(reader));
            for(int i=0;i<nrTasks;i++){
                int nr = reader.nextInt();
                int demand = reader.nextInt();
                ProcessStep ps = new ProcessStep(base);
                ps.setName("TP"+nr);
                ps.setShortName(ps.getName());
                ps.setDurationPerUnit(0);
                ps.setDurationFixed(1);
                ps.setProcess(process);
                ps.setStage(0);
                taskHash.put(nr,ps);
                demandHash.put(nr,demand);
                CumulativeNeed cn = new CumulativeNeed(base);
                cn.setName("CN"+nr);
                cn.setCumulativeResource(cumul);
                cn.setProcessStep(ps);
                cn.setDemand(demand);
                Task t = new Task(base);
                t.setName("T"+nr);
                t.setShortName(t.getName());
                t.setNr(nr);
                t.setJob(job);
                t.setProcessStep(ps);
                t.setDuration(1);
                t.setStage(0);
            }
            skip(readField(reader));
            String pair=reader.nextLine();
            int i=0;
            while(!pair.equals("")){
                String[] split = pair.split(",");
                assert(split.length == 2);
                int from = Integer.parseInt(split[0]);
                int to = Integer.parseInt(split[1]);
//                info("from "+from+" to "+to);
                ProcessStep before = taskHash.get(from);
                ProcessStep after = taskHash.get(to);
                ProcessSequence p = new ProcessSequence(base);
                p.setName("Seq"+ ++i);
                p.setBefore(before);
                p.setAfter(after);
                // a little trick to strengthen the model
                // if the demands of two linked tasks exceed the cycleTime, they must be in different stations
                int pairDemand = demandHash.get(from)+demandHash.get(to);
                p.setSequenceType(pairDemand <= cycleTime?SequenceType.StartBeforeStart:SequenceType.EndBeforeStart);

                p.setOffset(0);

                pair=reader.nextLine();
            }
            skip(readField(reader));
            reader.close();
            summarizeProblem(base);

        } catch (IOException e){
            severe("Cannot read file: "+file+", exception "+e.getMessage());
        }

    }

    private String readField(Scanner reader){
        String res = reader.nextLine();
        while(res.equals("")){
            res = reader.nextLine();
        }
        return res;
    }

    /*
    the information read here will not be used; print only for debugging
     */
    private void skip(String t){
//        info("Skipped "+t);
    }

}

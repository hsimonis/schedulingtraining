package org.insightcentre.tbischeduling.importer;

import framework.types.DateTime;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.insightcentre.tbischeduling.importer.Reset.resetAll;

public class CreateData {
    Random random;
    Scenario base;

    public CreateData(Scenario base,int seed,int nrProducts,int nrStages,int nrDisjunctiveResources,int nrCumulativeResources,
                      double resourceProbability,int nrOrders,int earliestDue,int horizon,int minQty,int maxQty) {
        this.base = base;
        random = new Random(seed);
        resetAll(base);
        createBaseData(nrProducts,nrStages,nrDisjunctiveResources,nrCumulativeResources,resourceProbability);
        createSchedule(nrOrders,earliestDue,horizon,minQty,maxQty);
    }

    private void createBaseData( int nrProducts, int nrStages, int nrDisjunctiveResources, int nrCumulativeResources,
                                 double resourceProbability){

    Problem problem = new Problem(base);
        problem.setName("P1");
        problem.setTimePointsAsDate(true);
        for(int i=0;i<nrDisjunctiveResources;i++){
            DisjunctiveResource r = new DisjunctiveResource(base);
            r.setName("DR"+i);
        }
        for(int i=0;i<nrCumulativeResources;i++){
            CumulativeResource r = new CumulativeResource(base);
            r.setName("CR"+i);
        }
        for(int i=0;i<nrProducts;i++){
            Product p = new Product(base);
            p.setName("Prod"+i);
            Process pp = new Process(base);
            pp.setName("Process "+i);
            p.setProcess(pp);
            ProcessStep before = null;
            for(int j=0;j<nrStages;j++){
                ProcessStep ps = new ProcessStep(base);
                ps.setProcess(pp);
                ps.setName("PS"+i+"/"+j);
                ps.setDurationFixed(10);
                ps.setDurationPerUnit(1);
                if (before!=null) {
                    ProcessSequence seq = new ProcessSequence(base);
                    seq.setName("Seq " + i + "/" + j);
                    seq.setBefore(before);
                    seq.setAfter(ps);
                    seq.setSequenceType(SequenceType.EndBeforeStart);
                    seq.setOffset(0);
                }
                before = ps;
                for(DisjunctiveResource r:resources(ps,resourceProbability)){
                    ResourceNeed rn = new ResourceNeed(base);
                    rn.setName("RN"+i+"/"+j+"/"+r.getName());
                    rn.setProcessStep(ps);
                    rn.setDisjunctiveResource(r);
                }
            }
        }
    }

    private List<DisjunctiveResource> resources(ProcessStep ps,double resourceProbability){
        List<DisjunctiveResource> res = new ArrayList<>();
        for(DisjunctiveResource r:base.getListDisjunctiveResource()){
            if (random.nextDouble()<resourceProbability){
                res.add(r);
            }
        }
        return res;
    }

    private void createSchedule(int nrOrders,int earliestDue,int horizon,int minQty,int maxQty){
        for(int i=0;i<nrOrders;i++){
            Order ord = new Order(base);
            ord.setName("Order"+i);
            ord.setProduct(pickProduct());
            ord.setQty(pickQty(minQty,maxQty));
            ord.setDue(due(earliestDue,horizon));
//            ord.setDueDate(toDateTime(ord.getDue()));
            Job j = new Job(base);
            j.setName("J"+i);
            j.setOrder(ord);
            j.setProcess(ord.getProduct().getProcess());
            for(ProcessStep ps:processSteps(j.getProcess())){
                Task t = new Task(base);
                t.setName("T"+i+ps.getName());
                t.setJob(j);
                t.setProcessStep(ps);
            }

        }
    }

    private List<ProcessStep> processSteps(Process p){
        return base.getListProcessStep().stream().filter(x->x.getProcess()==p).toList();
    }

    private Product pickProduct(){
        return Product.findAny(base);
    }

    private int pickQty(int minQty,int maxQty){
        return minQty+random.nextInt(maxQty-minQty);
    }

    private int due(int earliestDue,int horizon){
        return Math.min(horizon,earliestDue+random.nextInt(horizon));
    }

    private DateTime toDateTime(int period){
//        return base.getHorizonStart().addMinutes(period);
        return null;
    }
}

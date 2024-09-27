package org.insightcentre.tbischeduling.importer;

import framework.types.DateTime;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.insightcentre.tbischeduling.importer.Reset.resetAll;

public class CreateData {
    Random random;
    Scenario base;

    public CreateData(Scenario base,String name,int seed,int nrProducts,int nrStages,int nrDisjunctiveResources,int nrCumulativeResources,
                      double resourceProbability,int minCumulDemand,int maxCumulDemand,int cumulCapacity,
                      int nrOrders,int earliestDue,int horizon,int minQty,int maxQty) {
        this.base = base;
        random = new Random(seed);
        resetAll(base);
        base.setHorizon(horizon);
        createBaseData(name,nrProducts,nrStages,nrDisjunctiveResources,nrCumulativeResources,
                resourceProbability,minCumulDemand,maxCumulDemand,cumulCapacity);
        createSchedule(nrOrders,earliestDue,horizon,minQty,maxQty);

        base.setDirty(false);
        summarizeProblem(base);
    }

    private void createBaseData( String name,int nrProducts, int nrStages, int nrDisjunctiveResources, int nrCumulativeResources,
                                 double resourceProbability,int minCumulDemand,int maxCumulDemand,int cumulCapacity){

    Problem problem = new Problem(base);
        problem.setName(name);
        problem.setTimePointsAsDate(true);
        for(int i=0;i<nrDisjunctiveResources;i++){
            DisjunctiveResource r = new DisjunctiveResource(base);
            r.setName("DR"+i);
            r.setShortName("DR"+i);
        }
        for(int i=0;i<nrCumulativeResources;i++){
            CumulativeResource r = new CumulativeResource(base);
            r.setName("CR"+i);
            CumulativeProfile cp = new CumulativeProfile(base);
            cp.setName("CP"+i);
            cp.setCumulativeResource(r);
            cp.setFrom(0);
            cp.setCapacity(cumulCapacity);
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
                for(CumulativeResource cr:base.getListCumulativeResource()){
                    int demand = minCumulDemand+(maxCumulDemand-minCumulDemand > 0 ?random.nextInt(maxCumulDemand-minCumulDemand):0);
                    if (demand > 0) {
                        CumulativeNeed cn = new CumulativeNeed(base);
                        cn.setName("CN"+i+"/"+j+"/"+cr.getName());
                        cn.setProcessStep(ps);
                        cn.setCumulativeResource(cr);
                        cn.setDemand(demand);
                    }
                }
            }
        }
    }

    /*
    select a list of machines for a task; make sure that there is at least one
    //??? resulting probability is too high
     */
    private List<DisjunctiveResource> resources(ProcessStep ps,double resourceProbability){
        List<DisjunctiveResource> res = new ArrayList<>();
        int fallback = random.nextInt(base.getListDisjunctiveResource().size());
        int i=0;
        for(DisjunctiveResource r:base.getListDisjunctiveResource()){
            if (i++==fallback||random.nextDouble()<resourceProbability){
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
                t.setDuration(duration(t));
            }

        }
    }

    private int duration(Task t){
        return t.getProcessStep().getDurationFixed()+t.getJob().getOrder().getQty()*t.getProcessStep().getDurationPerUnit();
    }

    private List<ProcessStep> processSteps(Process p){
        return base.getListProcessStep().stream().filter(x->x.getProcess()==p).toList();
    }

    private Product pickProduct(){
        // must use the random with the specified seed
        int k = random.nextInt(base.getListProduct().size());
        return base.getListProduct().get(k);
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

    public static void summarizeProblem(Scenario base){
        assert(base.getListProblem().size()==1);
        Problem prob = Problem.findFirst(base);
        assert(prob!=null);
        prob.setNrProducts(base.getListProduct().size());
        prob.setNrProcesses(base.getListProcess().size());
        prob.setNrDisjunctiveResources(base.getListDisjunctiveResource().size());
        prob.setNrCumulativeResources(base.getListCumulativeResource().size());
        prob.setNrOrders(base.getListOrder().size());
        prob.setNrJobs(base.getListJob().size());
        prob.setNrTasks(base.getListTask().size());

        for(Task t:base.getListTask()){
            t.setMachines(disjunctiveResources(base,t));
        }
    }

    private static List<DisjunctiveResource> disjunctiveResources(Scenario base,Task t){
        return base.getListResourceNeed().stream().
                filter(x->x.getProcessStep()==t.getProcessStep()).
                map(ResourceNeed::getDisjunctiveResource).
                distinct().
                sorted().
                toList();
    }
}

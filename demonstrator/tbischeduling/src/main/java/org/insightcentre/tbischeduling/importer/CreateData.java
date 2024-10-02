package org.insightcentre.tbischeduling.importer;

import framework.types.DateTime;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.insightcentre.tbischeduling.importer.Reset.resetAll;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class CreateData {
    Random random;
    Scenario base;

    public CreateData(Scenario base,String name,ResourceModel resourceModel,int seed,int nrProducts,
                      int minStages,int maxStages,
                      int nrDisjunctiveResources,int nrCumulativeResources,
                      double resourceProbability,
                      DurationModel durationModel,int minDuration,int maxDuration,int durationFixedFactor,
                      int minCumulDemand,int maxCumulDemand,int cumulCapacity,
                      int nrOrders,int earliestDue,int horizon,int minQty,int maxQty,
                      double wipProbability,int minWip,int maxWip,
                      double downtimeProbability, int minDowntime, int maxDowntime) {
        this.base = base;
        random = new Random(seed);
        resetAll(base);
        base.setHorizon(horizon);
        createBaseData(name,resourceModel,nrProducts,minStages,maxStages,nrDisjunctiveResources,nrCumulativeResources,
                resourceProbability,
                durationModel,minDuration,maxDuration,durationFixedFactor,
                minCumulDemand,maxCumulDemand,cumulCapacity);
        createSchedule(nrOrders,earliestDue,horizon,minQty,maxQty,
                wipProbability,minWip,maxWip,
                downtimeProbability,minDowntime,maxDowntime);

        base.setDirty(false);
        summarizeProblem(base);
    }

    private void createBaseData( String name,ResourceModel resourceModel,int nrProducts, int minStages, int maxStages,
                                 int nrDisjunctiveResources, int nrCumulativeResources,
                                 double resourceProbability,
                                 DurationModel durationModel,int minDuration,int maxDuration,int durationFixedFactor,
                                 int minCumulDemand,int maxCumulDemand,int cumulCapacity){

    Problem problem = new Problem(base);
        problem.setName(name);
        problem.setTimePointsAsDate(true);
        DisjunctiveResource[] resources = new DisjunctiveResource[nrDisjunctiveResources];
        for(int i=0;i<nrDisjunctiveResources;i++){
            DisjunctiveResource r = new DisjunctiveResource(base);
            r.setName("DR"+i);
            r.setShortName("DR"+i);
            resources[i] = r;
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
            p.setDefaultProcess(pp);
            ProcessStep before = null;
            int[] permutation = permuteArray(maxStages);
            int[] stageDurationDefault = stageDurationDefaults(durationModel,maxStages,minDuration,maxDuration);
//            info("permutation "+ Arrays.toString(permutation));
            int nrStages = randomStages(minStages,maxStages);
            for(int j=0;j<nrStages;j++){
                ProcessStep ps = new ProcessStep(base);
                ps.setProcess(pp);
                ps.setName("PS"+i+"/"+j);
                ps.setStage(j);
                switch(durationModel){
                    case Random:
                        ps.setDurationFixed(durationFixedFactor*randomDuration(minDuration,maxDuration));
                        ps.setDurationPerUnit(randomDuration(minDuration,maxDuration));
                        break;
                    case RandomByStage:
                        ps.setDurationFixed(durationFixedFactor*randomDuration(minDuration,stageDurationDefault[j]));
                        ps.setDurationPerUnit(randomDuration(minDuration,stageDurationDefault[j]));
                        break;
                    case Uniform:
                        ps.setDurationFixed(durationFixedFactor*stageDurationDefault[j]);
                        ps.setDurationPerUnit(stageDurationDefault[j]);
                        break;
                    case UniformByStage:
                        ps.setDurationFixed(durationFixedFactor*stageDurationDefault[0]);
                        ps.setDurationPerUnit(stageDurationDefault[0]);
                        break;
                    case Unitary:
                        ps.setDurationFixed(1);
                        ps.setDurationPerUnit(0);
                        break;
                    default:
                        severe("durationModel value not handled "+durationModel);
                        assert(false);
                        ps.setDurationFixed(1);
                        ps.setDurationPerUnit(0);
                }
                if (before!=null) {
                    ProcessSequence seq = new ProcessSequence(base);
                    seq.setName("Seq " + i + "/" + j);
                    seq.setBefore(before);
                    seq.setAfter(ps);
                    seq.setSequenceType(SequenceType.EndBeforeStart);
                    seq.setOffset(0);
                }
                before = ps;
                switch(resourceModel) {
                    case HybridFlowShop: {
                        int nrMachinesPerStage = nrDisjunctiveResources / nrStages;
                        assert (nrDisjunctiveResources >= nrStages * nrMachinesPerStage);
                        for (int k = 0; k < nrMachinesPerStage; k++) {
                            ResourceNeed rnf = new ResourceNeed(base);
                            rnf.setName("RN" + i + "/" + j + "/" + k);
                            rnf.setProcessStep(ps);
                            rnf.setDisjunctiveResource(resources[j * nrMachinesPerStage + k]);
                        }
                        break;
                    }
                    case HybridJobShop: {
                        int nrMachinesPerStage = nrDisjunctiveResources / nrStages;
                        assert (nrDisjunctiveResources >= nrStages * nrMachinesPerStage);
                        for (int k = 0; k < nrMachinesPerStage; k++) {
                            ResourceNeed rnf = new ResourceNeed(base);
                            int l = permutation[j];
                            rnf.setName("RN" + i + "/" + l + "/" + k);
                            rnf.setProcessStep(ps);
                            rnf.setDisjunctiveResource(resources[l * nrMachinesPerStage + k]);
                        }
                        break;
                    }
                    case FlowShop: {
                        assert (nrDisjunctiveResources >= nrStages);
                        ResourceNeed rnf = new ResourceNeed(base);
                        rnf.setName("RN" + i + "/" + j);
                        rnf.setProcessStep(ps);
                        rnf.setDisjunctiveResource(resources[j]);
                        break;
                    }
                    case JobShop: {
                        assert (nrDisjunctiveResources >= nrStages);
                        ResourceNeed rnf = new ResourceNeed(base);
                        int k = permutation[j];
                        rnf.setName("RN" + i + "/" + k);
                        rnf.setProcessStep(ps);
                        rnf.setDisjunctiveResource(resources[k]);
                        break;
                    }
                    case Random:
                        for (DisjunctiveResource r : resources(ps, resourceProbability)) {
                            ResourceNeed rn = new ResourceNeed(base);
                            rn.setName("RN" + i + "/" + j + "/" + r.getName());
                            rn.setProcessStep(ps);
                            rn.setDisjunctiveResource(r);
                        }
                        break;
                    case All:
                        for (DisjunctiveResource r : resources) {
                            ResourceNeed rn = new ResourceNeed(base);
                            rn.setName("RN" + i + "/" + j + "/" + r.getName());
                            rn.setProcessStep(ps);
                            rn.setDisjunctiveResource(r);
                        }
                        break;
                    default:
                        severe("Unhandled resource model "+resourceModel.toString());
                        assert(false);
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

    private void createSchedule(int nrOrders,int earliestDue,int horizon,int minQty,int maxQty,
                                double wipProbability,int minWip,int maxWip,
                                double downtimeProbability,int minDowntime,int maxDowntime){
        for(int i=0;i<nrOrders;i++){
            Order ord = new Order(base);
            ord.setName("Order"+i);
            ord.setProduct(pickProduct());
            ord.setProcess(ord.getProduct().getDefaultProcess());
            ord.setQty(pickQty(minQty,maxQty));
            ord.setDue(due(earliestDue,horizon));
//            ord.setDueDate(toDateTime(ord.getDue()));
            Job j = new Job(base);
            j.setName("J"+i);
            j.setOrder(ord);
            j.setProcess(ord.getProcess());
            for(ProcessStep ps:processSteps(j.getProcess())){
                Task t = new Task(base);
                t.setName("T"+i+ps.getName());
                t.setShortName("T"+i+ps.getName());
                t.setJob(j);
                t.setProcessStep(ps);
                t.setDuration(duration(t));
                t.setStage(ps.getStage());
            }

        }
        for(DisjunctiveResource r:base.getListDisjunctiveResource()){
            if (random.nextDouble() < wipProbability){
                int until = minWip+(maxWip-minWip> 0 ?random.nextInt(maxWip-minWip):0);
                WiP wip = new WiP(base);
                wip.setName("W"+r.getName());
                wip.setDisjunctiveResource(r);
                wip.setUntil(until);

            }
            if (random.nextDouble() < downtimeProbability){
                int from = random.nextInt(base.getHorizon());
                int duration = minDowntime+(maxDowntime-minDowntime > 0? random.nextInt(maxDowntime-minDowntime):0);
                int to = Math.min(from+duration,base.getHorizon());
                Downtime d = new Downtime(base);
                d.setName("D"+r.getName());
                d.setDisjunctiveResource(r);
                d.setFrom(from);
                d.setTo(to);
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
            t.setPrecedes(precedences(base,t));
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

    public static List<Task> precedences(Scenario base,Task t){
        List<ProcessStep> seqList = base.getListProcessSequence().stream().
                filter(x->x.getBefore()==t.getProcessStep()).
                map(ProcessSequence::getAfter).toList();
        return base.getListTask().stream().
                filter(x->x.getJob()==t.getJob()).
                filter(x->seqList.contains(x.getProcessStep())).
                toList();
    }

    public int[] permuteArray(int size){
        int[] array = new int[size];
        for(int i=0;i<size;i++){
            array[i] = i;
        }
        for(int i=0;i<size;i++) {
            int j = i + random.nextInt(size-i);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }

    public int[] stageDurationDefaults(DurationModel model,int size,int minDuration,int maxDuration){
        assert(maxDuration >= minDuration);
        assert(minDuration >= 0);
        int[] array=new int[size];
        for(int i=0;i<size;i++){
            array[i] = randomDuration(minDuration,maxDuration);
        }
        return array;
    }

    public int randomDuration(int minDuration,int maxDuration){
        assert(minDuration <= maxDuration);
        if (minDuration == maxDuration){
            return minDuration;
        } else {
            return minDuration+random.nextInt(maxDuration - minDuration);
        }
    }

    public int randomStages(int minStages,int maxStages) {
        assert (minStages <= maxStages);
        assert (minStages > 0);
        if (minStages == maxStages) {
            return minStages;
        } else {
            return minStages + random.nextInt(maxStages - minStages);
        }
    }

}

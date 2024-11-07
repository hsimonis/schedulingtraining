package org.insightcentre.tbischeduling.importer;

import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.implementedsolver.CheckSolutions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.insightcentre.tbischeduling.importer.CreateData.summarizeProblem;
import static org.insightcentre.tbischeduling.importer.Reset.resetAll;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class ReadTestSchedulingFile {
    Scenario base;
    public ReadTestSchedulingFile(Scenario base, File selected){
        this.base = base;
        try {
            resetAll(base);
            String contents = new String(Files.readAllBytes(selected.toPath()));
            JSONObject root = new JSONObject(contents);
            load(root);
            summarizeProblem(base);
            new CheckSolutions(base,base.getListSolution());
            info("File read, "+base.getListInputError().size()+" input error(s), "+base.getListSolutionError().size()+" solution errors");
        } catch(IOException e){
            severe("Cannot read file "+selected+", exception "+e.getMessage());
        }
    }

    private void load(JSONObject root){
        String name= root.getString("name");
        JSONArray machines = root.getJSONArray("boards");
        JSONArray resources = root.getJSONArray("resources");
        JSONArray tests= root.getJSONArray("test");
        Problem prob = new Problem(base);
        prob.setName(name);
        prob.setLabel("Generated from Input data");
        for(int i =0;i<machines.length();i++){
            String dname = machines.getString(i);
            DisjunctiveResource r = new DisjunctiveResource(base);
            r.setName(dname);
            r.setShortName(dname);
        }
        List<DisjunctiveResource> mList = base.getListDisjunctiveResource();
        for(int i =0;i<resources.length();i++){
            JSONObject res = resources.getJSONObject(i);
            String cname = res.getString("name");
            int qty = res.getInt("qty");
            CumulativeResource r = new CumulativeResource(base);
            r.setName(cname);
            CumulativeProfile cp = new CumulativeProfile(base);
            cp.setName("CP"+cname);
            cp.setCumulativeResource(r);;
            cp.setFrom(0);
            cp.setCapacity(qty);
        }
        int rnNr = 0;
        int cnNr = 0;
        for(int i =0;i<tests.length();i++) {
            JSONObject test = tests.getJSONObject(i);
            String tname = test.getString("name");
            int dur = test.getInt("dur");
            String m = test.getString("m");
            String r = test.getString("r");
           Process p = new Process(base);
            p.setName(tname);
            Product prod = new Product(base);
            prod.setName(tname);
            prod.setDefaultProcess(p);
            ProcessStep ps = new ProcessStep(base);
            ps.setName(tname);
            ps.setShortName(tname);
            ps.setProcess(p);
            ps.setDurationFixed(dur);
            Order ord = new Order(base);
            ord.setName(tname);
            ord.setNr(i);
            ord.setQty(1);
            ord.setProduct(prod);
            ord.setProcess(p);
            ord.setRelease(0);
            ord.setDue(0);
            Job job = new Job(base);
            job.setName(tname);
            job.setNr(i);
            job.setOrder(ord);
            job.setProcess(p);
            Task t = new Task(base);
            t.setName(tname);
            t.setShortName(tname);
            t.setJob(job);
            t.setDuration(dur);
            t.setProcessStep(ps);
            List<DisjunctiveResource> mm;
            if (m.equals("[]")) {
                mm = mList;
            } else {
                mm = extractMachines(m);
            }
//            info(tname+" mm "+mm.size()+" "+m);
            t.setMachines(mm);
            for(DisjunctiveResource dr:mm){
                ResourceNeed rn = new ResourceNeed(base);
                rn.setName("RN"+rnNr++);
                rn.setProcessStep(ps);
                rn.setDisjunctiveResource((dr));
            }
            List<CumulativeResource> cc = extractCumulative(r);
            for(CumulativeResource c:cc){
                CumulativeNeed cn = new CumulativeNeed(base);
                cn.setName("CN"+cnNr++);
                cn.setCumulativeResource(c);
                cn.setProcessStep(ps);
                cn.setDemand(1);
            }

        }

    }

    private List<DisjunctiveResource> extractMachines(String m){
        String[] split = m.replace("[","").replace("]","").split(", ");
//        info("Split"+ Arrays.toString(split));
        List<DisjunctiveResource> res = new ArrayList<>();
        if (split.length==0 || (split.length==1 && split[0].equals(""))){
            return res;
        } else {
            for (String s : split) {
                DisjunctiveResource dr = DisjunctiveResource.findByName(base, s);
                if (dr == null) {
                    severe("Bad dr "+s);
                    assert(false);
                } else {
                    res.add(dr);
                }
            }
            return res;
        }
    }
    private List<CumulativeResource> extractCumulative(String m){
        String[] split = m.replace("[","").replace("]","").split(", ");
//        info("Split"+ Arrays.toString(split));
        List<CumulativeResource> res = new ArrayList<>();
        if (split.length==0 || (split.length==1 && split[0].equals(""))){
            return res;
        } else {
            for (String s : split) {
                CumulativeResource cr = CumulativeResource.findByName(base, s);
                if (cr == null) {
                    severe("bad cr "+s);
                    assert(false);
                } else {
                    res.add(cr);
                }
            }
            return res;
        }
    }

}

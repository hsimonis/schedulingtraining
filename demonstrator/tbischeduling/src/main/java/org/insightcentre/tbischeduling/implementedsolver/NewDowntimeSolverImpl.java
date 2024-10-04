package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.generatedsolver.NewDowntimeSolver;
import org.insightcentre.tbischeduling.generatedsolver.NewOrderSolver;
import org.insightcentre.tbischeduling.importer.CreateData;

public class NewDowntimeSolverImpl extends NewDowntimeSolver {
    public NewDowntimeSolverImpl(Scenario base){
        super(base);
    }

    public boolean solve(){
        DisjunctiveResource r = DisjunctiveResource.findByName(base,getDisjunctiveResource());
        if (r==null){
            DisjunctiveResource.findFirst(base);
        }
        assert(r != null);
        int i = (int) base.getListDowntime().stream().filter(x->x.getDisjunctiveResource()==r).count()+1;
        Downtime d = new Downtime(base);
        d.setName("DT"+r.getName()+"-"+i);
        d.setDisjunctiveResource(r);
        d.setStart(getStart());
        d.setEnd(getEnd());
        d.setDuration(getEnd()-getStart());
        return true;
    }
}

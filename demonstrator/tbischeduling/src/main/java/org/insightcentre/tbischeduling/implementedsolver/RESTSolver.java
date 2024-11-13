package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.Batch;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverRun;
import org.insightcentre.tbischeduling.exporter.WriteData;
import org.insightcentre.tbischeduling.importer.ReadData;
import org.insightcentre.tbischeduling.utilities.RestClient;
import org.json.JSONObject;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;

public class RESTSolver extends AbstractModel{
    public RESTSolver(Scenario base, SolverRun run){
        super(base,run);
    }

    public boolean solve(){
        info("Creating problem as String");
        String problem = new WriteData(base).toString();
        info("Problem created, calling Server");
        String result = new RestClient().solve(problem);
        info("Result returned, loading data");
        new ReadData(base,result);
        info("REST Solver finished");

        return true;
    }
}

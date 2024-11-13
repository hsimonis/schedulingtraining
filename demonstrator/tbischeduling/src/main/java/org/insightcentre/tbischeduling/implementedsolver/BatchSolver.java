package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.Batch;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverRun;
import org.insightcentre.tbischeduling.exporter.WriteData;
import org.insightcentre.tbischeduling.importer.ReadData;
import org.json.JSONObject;

import static org.insightcentre.tbischeduling.datamodel.SolverStatus.ToRun;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;

public class BatchSolver extends AbstractModel{
    public BatchSolver(Scenario base, SolverRun run){
        super(base,run);
    }

    public boolean solve(){
        info("Creating problem");
        JSONObject problem = new WriteData(base).toJSON();
        info("Problem created, calling Batch");
        JSONObject result = new Batch().query(problem);
        info("Result returned, loading data");
        new ReadData(base,result);
        info("Batch Solver finished");

        return true;
    }
}

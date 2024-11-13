package org.insightcentre.tbischeduling;

import framework.types.DateTime;
import framework.types.IrishCalendar;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverRun;
import org.insightcentre.tbischeduling.exporter.WriteData;
import org.insightcentre.tbischeduling.implementedsolver.CPOModel;
import org.insightcentre.tbischeduling.importer.ReadData;
import org.json.JSONObject;

import java.io.File;

import static org.insightcentre.tbischeduling.datamodel.SolverStatus.ToRun;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;

/*
this class serves two purposes
a) run main on the command line with two arguments, an input and output file
b) run as a subroutine in other application by calling new Batch().query(inputJSON), return the result as a JSON object
 */
public class Batch {


    static {
        IrishCalendar.initIrishCalendar();
        IrishCalendar.buildCalendar();
    }

    public Batch(){}

    public JSONObject query(JSONObject queryInput){
        Scenario base = new Scenario();
        base.setDataFileVersionNumber(8.0);
        base.setDataFile("");
        base.setHorizon(100000);
        base.setTimeResolution(5);
        base.setStartDateTime(new DateTime(2024,10,1,0,0));


        info("Starting");
        // define the format version of the datafiles
        new ReadData(base, queryInput);
        info("Nr SolverRun "+base.getListSolverRun().size());
        for (SolverRun run : base.getListSolverRun().stream().filter(x -> x.getSolverStatus() == ToRun).toList()){
            info("Running "+run.getName());
            new CPOModel(base, run).solve();
        }
        info("runs scheduled");
        return new WriteData(base).toJSON();

    }

    public static void main(String[] args) {
        Scenario base = new Scenario();
        base.setDataFileVersionNumber(8.0);
        base.setDataFile("");
        base.setHorizon(20000);
        base.setTimeResolution(5);
        base.setStartDateTime(new DateTime(2024,10,1,0,0));

        assert (args.length == 2);
        String inputFile = args[0];
        String outputFile = args[1];
        info("Input "+inputFile);
        // define the format version of the datafiles
        new ReadData(base, new File("imports/" + inputFile));
        info("Nr SolverRun "+base.getListSolverRun().size());
        for (SolverRun run : base.getListSolverRun().stream().filter(x -> x.getSolverStatus() == ToRun).toList()){
            info("Running "+run.getName());
            new CPOModel(base, run).solve();
        }
        new WriteData(base).toFile(new File("exports/"+outputFile),2);
    }

}

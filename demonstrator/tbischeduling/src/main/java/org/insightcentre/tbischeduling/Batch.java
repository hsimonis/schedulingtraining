package org.insightcentre.tbischeduling;

import framework.types.IrishCalendar;
import org.insightcentre.tbischeduling.datamodel.Problem;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverRun;
import org.insightcentre.tbischeduling.exporter.WriteDataFile;
import org.insightcentre.tbischeduling.implementedsolver.CPOModel;
import org.insightcentre.tbischeduling.importer.ReadDataFile;

import java.io.File;

import static org.insightcentre.tbischeduling.datamodel.SolverStatus.ToRun;

public class Batch {


    public static void main(String[] args) {
        IrishCalendar.initIrishCalendar();
        IrishCalendar.buildCalendar();
        Scenario base = new Scenario();

        assert (args.length == 2);
        String inputFile = args[0];
        String outputFile = args[0];
        // define the format version of the datafiles
        base.setDataFileVersionNumber(3.0);
        base.setDataFile("");
        base.setHorizon(2000);
        new ReadDataFile(base, new File("imports/" + inputFile));
        for (SolverRun run : base.getListSolverRun().stream().filter(x -> x.getSolverStatus() == ToRun).toList()){
            new CPOModel(base, run);
        }
        new WriteDataFile(base,new File("exports/"+outputFile));
    }

}

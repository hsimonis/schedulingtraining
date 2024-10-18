package org.insightcentre.tbischeduling;

import framework.types.IrishCalendar;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverRun;
import org.insightcentre.tbischeduling.exporter.WriteData;
import org.insightcentre.tbischeduling.implementedsolver.CPOModel;
import org.insightcentre.tbischeduling.importer.ReadData;

import java.io.File;

import static org.insightcentre.tbischeduling.datamodel.SolverStatus.ToRun;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;

public class Batch {


    static {
        IrishCalendar.initIrishCalendar();
        IrishCalendar.buildCalendar();
    }

    public static void main(String[] args) {
        System.out.println("hello world");
        Scenario base = new Scenario();
        System.out.println("a");
        base.setDataFileVersionNumber(8.0);
        base.setDataFile("");
        base.setHorizon(2000);

//        IrishCalendar.initIrishCalendar();
//        IrishCalendar.buildCalendar();

        info("Starting");
        assert (args.length == 2);
        String inputFile = args[0];
        String outputFile = args[0];
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

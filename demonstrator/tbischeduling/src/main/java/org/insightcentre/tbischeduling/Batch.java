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
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;

public class Batch {


    public static void main(String[] args) {
        System.out.println("hello world");
//        Scenario base = new Scenario();
//        base.setDataFileVersionNumber(3.0);
//        base.setDataFile("");
//        base.setHorizon(2000);
//
//        IrishCalendar.initIrishCalendar();
//        IrishCalendar.buildCalendar();
//
//        info("Starting");
//        assert (args.length == 2);
//        String inputFile = args[0];
//        String outputFile = args[0];
//        info("Input "+inputFile);
//        // define the format version of the datafiles
//        new ReadDataFile(base, new File("imports/" + inputFile));
//        info("Nr SolverRun "+base.getListSolverRun().size());
//        for (SolverRun run : base.getListSolverRun().stream().filter(x -> x.getSolverStatus() == ToRun).toList()){
//            info("Running "+run.getName());
//            new CPOModel(base, run);
//        }
//        new WriteDataFile(base,new File("exports/"+outputFile));
    }

}

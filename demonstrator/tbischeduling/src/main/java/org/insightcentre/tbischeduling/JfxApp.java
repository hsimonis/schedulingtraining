package org.insightcentre.tbischeduling;

/*
Generated once, should be extended by user
*/

import javafx.stage.FileChooser;
import org.insightcentre.tbischeduling.datamodel.*;
import framework.ApplicationDatasetInterface;
import framework.ApplicationObjectInterface;
import framework.types.IrishCalendar;
import org.insightcentre.tbischeduling.exporter.WriteData;
import org.insightcentre.tbischeduling.generatedsolver.GenerateDataDialogBox;
import org.insightcentre.tbischeduling.generatedsolver.ScheduleJobsDialogBox;
import org.insightcentre.tbischeduling.implementedsolver.GenerateDataSolverImpl;
import org.insightcentre.tbischeduling.implementedsolver.ScheduleJobsSolverImpl;
import org.insightcentre.tbischeduling.importer.CreateData;
import org.insightcentre.tbischeduling.importer.ReadData;
import org.insightcentre.tbischeduling.reports.SchedulingReport;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.insightcentre.tbischeduling.datamodel.ResourceModel.FlowShop;
import static org.insightcentre.tbischeduling.logging.LogShortcut.*;

public class JfxApp extends GeneratedJfxApp {

// callbacks to add for user interaction that is not generated
// use stubs in GeneratedJFxApp as basis
// callback for ctrl+selection in list
        public void showObject(ApplicationObjectInterface obj){
                super.showObject(obj);
        }

// callback called once at startup to create initial data in application
        @Override
        public ApplicationDatasetInterface minimalDataset() {
                Scenario base = new Scenario();
                IrishCalendar.buildCalendar();
                // define the format version of the datafiles
                base.setDataFileVersionNumber(5.0);
                base.setDataFile("");
                base.setHorizon(2000);
                info("Creating default data");
                new CreateData(base,"P1",FlowShop,42,5,3,6,1,
                        0.3,1,5,10,
                        20,20,horizon(10,5),1,10);
                base.setDirty(false);
                return base;
        }

        public static int horizon(int days,int timeResolution){
                return days*1440/timeResolution;
        }

// main entry point for interactive application
        public static void main(String[] args) {
                launch(args);
        }

        @Override
        public void LoadDataFileAction(Scenario base) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load Datafile");
                fileChooser.setInitialDirectory(new File("imports/"));
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("JSON Files", "*.json"));
                fileChooser.setInitialFileName("demo.json");
                // allow to enter new file
                File selected = fileChooser.showOpenDialog(primaryStage);
                if (selected != null){
                        try {
                                info("Opening File: " + selected.getCanonicalPath()+" name "+selected.getName());
                                base.setDataFile(selected.getName());
                                new ReadData(base,selected);
                        } catch(IOException e){
                                severe("IOException "+e.getMessage());
                        }
                } else {
                        warning("File null");
                }
                // re-adjust the user interface to reflect the modified data
                reset();
                // if any errors were found, show them in the GUI
                if (base.getListInputError().size() > 0){
                        showView("InputError");
                }
        }

        @Override
        public void SaveDataFileAction(Scenario base) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Datafile");
                fileChooser.setInitialDirectory(new File("imports/"));
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("JSON Files", "*.json"));
                fileChooser.setInitialFileName("result.json");
                // allow to enter new file
                File selected = fileChooser.showSaveDialog(primaryStage);
                if (selected != null){
                        try {
                                info("Saving File: " + selected.getCanonicalPath());
                                new WriteData(base).toFile(selected,2);
                        } catch(IOException e){
                                severe("IOException "+e.getMessage());
                        }
                } else {
                        warning("File null");
                }
                // re-adjust the user interface to reflect the modified data
                reset();
        }

        @Override
        public void GenerateReportAction(Scenario base) {
                new SchedulingReport(base,"reports/").produce("schedulingreport","Scheduling Report","L. O'Toole and H. Simonis");
                reset();

        }

        @Override
        public void generateDataSolverRun(Scenario base) {
                Optional<Boolean> result = new GenerateDataDialogBox(this,base,new GenerateDataSolverImpl(base)).showAndWait();
                reset();
        }

        @Override
        public void scheduleJobsSolverRun(Scenario base) {
                Optional<Boolean> result = new ScheduleJobsDialogBox(this,base,new ScheduleJobsSolverImpl(base)).showAndWait();
                reset();
                showView("Solution");
        }


}

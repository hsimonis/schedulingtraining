package org.insightcentre.tbischeduling;

/*
Generated once, should be extended by user
*/

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.insightcentre.tbischeduling.datamodel.*;
import framework.ApplicationDatasetInterface;
import framework.ApplicationObjectInterface;
import framework.types.IrishCalendar;
import org.insightcentre.tbischeduling.exporter.WriteDataFile;
import org.insightcentre.tbischeduling.importer.CreateData;
import org.insightcentre.tbischeduling.importer.ReadDataFile;
import org.insightcentre.tbischeduling.reports.SchedulingReport;

import java.io.File;
import java.io.IOException;

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
                new CreateData(base,42,5,3,6,1,
                        0.3,
                        20,20,horizon(10,5),1,10);
                base.setDirty(false);
                return base;
        }

        private int horizon(int days,int timeResolution){
                return days*1440/timeResolution;
        }

// main entry point for interactive application
        public static void main(String[] args) {
                launch(args);
        }

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
//                                base.setDataFile(selected.getName());
                                new ReadDataFile(base,selected);
                        } catch(IOException e){
                                severe("IOException "+e.getMessage());
                        }
                } else {
                        warning("File null");
                }
                // re-adjust the user interface to reflect the modified data
                reset();
//                if (base.getListInputErrors().size() > 0){
//                        showView("RawIssue");
//                }
        }

        public void SaveSpreadsheetAction(Scenario base) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Result");
                fileChooser.setInitialDirectory(new File("exports/"));
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("JSON Files", "*.json"));
                fileChooser.setInitialFileName("result.json");
                // allow to enter new file
                File selected = fileChooser.showSaveDialog(primaryStage);
                if (selected != null){
                        try {
                                info("Saving File: " + selected.getCanonicalPath());
                                new WriteDataFile(base,selected);
                        } catch(IOException e){
                                severe("IOException "+e.getMessage());
                        }
                } else {
                        warning("File null");
                }
                // re-adjust the user interface to reflect the modified data
                reset();
        }

        public void GenerateReportAction(Scenario base) {
                new SchedulingReport(base,"reports/").produce("schedulingreport","Scheduling Report","L. O'Toole and H. Simonis");

        }

}

package org.insightcentre.tbischeduling;

/*
Generated once, should be extended by user
*/

import framework.types.DateTime;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.insightcentre.tbischeduling.datamodel.*;
import framework.ApplicationDatasetInterface;
import framework.ApplicationObjectInterface;
import framework.types.IrishCalendar;
import org.insightcentre.tbischeduling.exporter.CreateJSONDoc;
import org.insightcentre.tbischeduling.exporter.WriteData;
import org.insightcentre.tbischeduling.generatedsolver.*;
import org.insightcentre.tbischeduling.implementedsolver.GenerateDataSolverImpl;
import org.insightcentre.tbischeduling.implementedsolver.NewDowntimeSolverImpl;
import org.insightcentre.tbischeduling.implementedsolver.NewOrderSolverImpl;
import org.insightcentre.tbischeduling.implementedsolver.ScheduleJobsSolverImpl;
import org.insightcentre.tbischeduling.importer.CreateData;
import org.insightcentre.tbischeduling.importer.ReadData;
import org.insightcentre.tbischeduling.reports.SchedulingReport;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;

import static org.insightcentre.tbischeduling.datamodel.ResourceModel.*;
import static org.insightcentre.tbischeduling.datamodel.Severity.Minor;
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
                //??? initialize for use in controllers
                basebase = base;
                // define the format version of the datafiles
                base.setDataFileVersionNumber(8.0);
                base.setDataFile("");
                base.setStartDateTime(new DateTime(2024,10,1,0,0));
                base.setHorizon(20000);
                base.setTimeResolution(5);
                base.setGanttWidth(23);
                base.setGanttLinesPerPage(25);
                base.setGanttLineHeight(0.5);
                base.setSolverProperty(createSolverProperties(base));
                DataGeneratorProperty q = createDataGeneratorProperties(base);
                base.setDataGeneratorProperty(q);
                info("Create JSON doc");
                new CreateJSONDoc(base,"site/jsonDoc/");
                info("Creating default data");
                new CreateData(base,q.getLabel(),q.getStartDateTime(),q.getResourceModel(),q.getNrProducts(),
                        q.getMinStages(),q.getMaxStages(),q.getNrDisjunctiveResources(),
                        q.getResourceProbability(),q.getDurationModel(),q.getMinDuration(),q.getMaxDuration(),
                        q.getDurationFixedFactor(),
                        q.getNrCumulativeResources(),q.getMinCumulDemand(),q.getMaxCumulDemand(),q.getProfilePieces(),
                        q.getMinCumulCapacity(),q.getMaxCumulCapacity(),
                        q.getNrOrders(),
                        q.getMinQty(),q.getMaxQty(),
                        q.getWipProbability(),q.getMinWip(),q.getMaxWip(),
                        q.getDowntimeProbability(),q.getMinDowntime(),q.getMaxDowntime(),
                        q.getEarliestDue(),100,q.getTimeResolution(),q.getSeed());
                base.setHorizon(50000);
                base.setDirty(false);
//                setTitle(applicationTitle+" (Generated)");
                return base;
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
                                setStatus("Reading file "+selected.getName());
                                info("Opening File: " + selected.getCanonicalPath()+" name "+selected.getName());
                                base.setDataFile(selected.getName());
                                new ReadData(base,selected);
//                                setTitle(applicationTitle+" ("+selected.getName()+")");
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
                        setStatus("File read with "+base.getListInputError().size()+" input data errors");
                        showView("InputError");
                } else if (base.getListSolutionError().size() > 0){
                        setStatus("File read with "+base.getListSolutionError().size()+" solution errors");
                        showView("SolutionError");
                } else {
                        setStatus("File read");
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
                                setStatus("Saving file");
                                info("Saving File: " + selected.getCanonicalPath());
                                new WriteData(base).toFile(selected,2);
                                setStatus("File saved");
//                                setTitle(applicationTitle+" ("+selected.getName()+")");
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
                new SchedulingReport(base,"reports/").produce("schedulingreport",
                        "Scheduling Report","L. O'Toole and H. Simonis");
                setStatus("Scheduling report generated");
                reset();

        }

        @Override
        public void generateDataSolverRun(Scenario base) {
                Optional<Boolean> result = new GenerateDataDialogBoxImpl(this,base,new GenerateDataSolverImpl(base)).showAndWait();
                setStatus("Data Generated");
//                setTitle(applicationTitle+" (Generated data)");
                reset();
        }

        @Override
        public void scheduleJobsSolverRun(Scenario base) {
                Optional<Boolean> result = new ScheduleJobsDialogBoxImpl(this,base,new ScheduleJobsSolverImpl(base)).showAndWait();
                reset();
                if (base.getListSolutionError().stream().anyMatch(x -> x.getSeverity() != Minor)) {
                        setStatus("Solver finished with errors");
                        showView("SolutionError");
                } else {
                        setStatus("Solver finished");
//                        showView("Solution");
                        showView("custom/GanttBorderViewer");

                }
        }
        public void newOrderSolverRun(Scenario base) {
                Optional<Boolean> result = new NewOrderDialogBox(this,base,new NewOrderSolver(base)).showAndWait();
                reset();
        }

//        @Override
//        public void newOrderSolverRun(Scenario base) {
//                Optional<Boolean> result = new NewOrderDialogBoxImpl(this,base,new NewOrderSolverImpl(base)).showAndWait();
//                setStatus("New order created");
//                reset();
//        }
        @Override
        public void newDowntimeSolverRun(Scenario base) {
                Optional<Boolean> result = new NewDowntimeDialogBoxImpl(this,base,new NewDowntimeSolverImpl(base)).showAndWait();
                setStatus("New downtime created");
                reset();
        }

        @Override
        public void showSolutionReport(Scenario base) {
                try {
                        showUrl("Solution Report",new File("reports/html/schedulingreport.html").getAbsoluteFile().toURI().toURL());
//                        showUrlInBrowser(new File("reports/html/schedulingreport.html").getAbsoluteFile().toURI().toURL());
                } catch(Exception e){
                        severe("Cannot show report "+e.getMessage());
                }
        }

        @Override
        public void ganttViewer(Scenario base) {
                showView("custom/GanttViewer");
        }
        @Override
        public void ganttBorderViewer(Scenario base) {
                showView("custom/GanttBorderViewer");
        }

        //??? these should check a property file to read persistent property values
        public SolverProperty createSolverProperties(Scenario base){
                SolverProperty res = new SolverProperty(base);
                res.setName("Default Properties");
                res.setStartDateTime(base.getStartDateTime());
                info("Solver startDate "+res.getStartDateTime()+" produceReport "+res.getProduceReport());
                return res;
        }
        public DataGeneratorProperty createDataGeneratorProperties(Scenario base){
                DataGeneratorProperty res = new DataGeneratorProperty(base);
                res.setName("Default Properties");
                res.setStartDateTime(base.getStartDateTime());
                info("DateGenerator startDate "+res.getStartDateTime());
                return res;
        }

}

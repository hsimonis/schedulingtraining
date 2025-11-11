package org.insightcentre.tbischeduling;

/*
Generated once, should be extended by user
*/

import framework.types.DateTime;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.insightcentre.tbischeduling.datamodel.*;
import framework.ApplicationDatasetInterface;
import framework.ApplicationObjectInterface;
import framework.types.IrishCalendar;
import org.insightcentre.tbischeduling.exporter.CreateJSONDoc;
import org.insightcentre.tbischeduling.exporter.WriteData;
import org.insightcentre.tbischeduling.generatedsolver.*;
import org.insightcentre.tbischeduling.implementedsolver.*;
import org.insightcentre.tbischeduling.importer.*;
import org.insightcentre.tbischeduling.reports.SchedulingReport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

import static framework.reports.AbstractCommon.safe;
import static java.lang.System.getProperty;
import static org.insightcentre.tbischeduling.datamodel.Severity.Minor;
import static org.insightcentre.tbischeduling.logging.LogShortcut.*;

public class JfxApp extends GeneratedJfxApp {

    String workingDir;

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
                base.setHorizon(5000);
                base.setTimeResolution(5);
                base.setGanttWidth(23);
                base.setGanttLinesPerPage(25);
                base.setGanttLineHeight(0.5);
                base.setSolverProperty(createSolverProperties(base));
                DataGeneratorProperty q = createDataGeneratorProperties(base);
                base.setDataGeneratorProperty(q);
                base.setGanttProperty(createGanttProperties(base));
                base.setHasDisjunctive(true);
                workingDir = getProperty("user.dir");
                if (!workingDir.endsWith("/")){
                    workingDir = workingDir + "/";
                }
                if (!new File(workingDir).exists()){
                    severe("WorkingDir "+workingDir+" does not exist");
                } else{
                    info("WorkingDir "+workingDir);
                }
                base.setHomeDir(workingDir);
//                info("Create JSON doc");
//            requiresDirectory(workingDir+"site/");
//                new CreateJSONDoc(base,workingDir+"site/jsondoc/");
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
                        q.getEarliestDue(),20,q.getTimeResolution(),q.getSeed());
                base.setHorizon(100000);
                base.setDirty(false);
                setTitle(applicationTitle+" (Generated)");
                info("user.dir "+getProperty("user.dir")+" user home "+getProperty("user.home")+" cwd "+
                        Paths.get("").toAbsolutePath().toString());
//                new ReadData(base,new File("imports/RCPSP/SingleMode/j30/j301_1.json"));
//                new ReadData(base,new File("imports/RCPSP/SingleMode/j30/j3013_2.json"));
//                new ReadData(base,new File("imports/RCPSP/SingleMode/j120/j12013_2.json"));
//                new ReadData(base,new File("imports/RCPSP/SingleMode/j120/j12011_1.json"));
////                new ReadData(base,new File("imports/Taillard/JSS/tai15_15_0.json"));
////                new ReadData(base,new File("imports/Taillard/JSS/tai100_20_0.json"));
////                new ReadData(base,new File("imports/Taillard/FSS/tai20_5_0.json"));
////                new ReadData(base,new File("imports/Taillard/OSS/tai20_20_0.json"));
//                new ReadSALBPAlternativeFile(base,new File("salbp/instance_n=20_171.alb"));
//                new ReadSALBPFile(base,new File("salbp/instance_n=20_171.alb"));
//                new ReadSALBPAlternativeFile(base,new File("salbp/instance_n=1000_511.alb"));
//                new ReadSALBPFile(base,new File("salbp/instance_n=1000_511.alb"));
//                new ReadTestSchedulingFile(base,new File("testscheduling/t20m10r3-1.pl.json"));
//                new ReadTestSchedulingFile(base,new File("testscheduling/t500m20r10-1.pl.json"));
//                new ReadJJFlatFile(base,"transport/instance400_1.txt");
//                new ReadNoWaitHFSFile(base,new File("nowaithfs/benchmark_instances 1111/instance_1_J10_S2.txt"));
//                SolverRun run = new SolverRun(base);
//                run.setName("cpsat");
//                run.setModelType(ModelType.CPSat);
//                run.setTimeout(30);
//                run.setNrThreads(8);
//                run.setSolverStatus(SolverStatus.ToRun);
//                new CPSatModel(base,run).solve();
                setTitle(applicationTitle+" ("+base.getDataFile()+")");
//                new ProcessDiagram(base, Objects.requireNonNull(org.insightcentre.tbischeduling.datamodel.Process.findFirst(base)));
//                for(ProcessSequence ps:base.getListProcessSequence()){
//                        if (ps.getAfter().getStage()<=3){
//                                ps.setSequenceType(Blocking);
//                        }
//                }
//            requiresDirectory(workingDir+"reports/");
//                new SchedulingReport(base, "reports/").produce("placementreport", "Scheduling Report for "+safe(base.getDataFile()), "L. O'Toole and H. Simonis");
                info("file "+base.getDataFile());
                return base;
        }


// main entry point for interactive application
        public static void main(String[] args) {
                launch(args);
        }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image(JfxApp.class.getResourceAsStream("/entire.png")));
        super.start(primaryStage);
    }


    @Override
        public void LoadDataFileAction(Scenario base) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load Datafile");
                File importDir = new File(workingDir+"imports/");
                if (!importDir.exists()){
                    severe("ImportDir "+ importDir.getAbsolutePath()+" does not exist");
                    importDir = new File(workingDir);
                } else {
                    info("ImportDir "+importDir.getAbsolutePath());
                }
                fileChooser.setInitialDirectory(importDir);
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
                                setTitle(applicationTitle+" ("+selected.getName()+")");
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
            File importDir = new File(workingDir+"imports/");
            if (!importDir.exists()){
                severe("ImportDir "+ importDir.getAbsolutePath()+" does not exist");
                importDir = new File(workingDir);
            } else {
                info("ImportDir "+importDir.getAbsolutePath());
            }

            fileChooser.setInitialDirectory(importDir);
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
                                setTitle(applicationTitle+" ("+selected.getName()+")");
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
                new SchedulingReport(base,workingDir+"reports/").produce("schedulingreport",
                        "Scheduling Report","L. O'Toole and H. Simonis");
                setStatus("Scheduling report generated");
                reset();

        }

        @Override
        public void LoadJJFileAction(Scenario base) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load Datafile");
                fileChooser.setInitialDirectory(new File(workingDir+"transport/"));
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("Text Files", "*.txt"));
                fileChooser.setInitialFileName("instance20_1.txt");
                // allow to enter new file
                File selected = fileChooser.showOpenDialog(primaryStage);
                if (selected != null){
                        try {
                                setStatus("Reading file "+selected.getName());
                                info("Opening File: " + selected.getCanonicalPath()+" name "+selected.getName());
                                base.setDataFile(selected.getName());
                                new ReadJJFlatFile(base,selected);
                                setTitle(applicationTitle+" ("+selected.getName()+")");
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
        public void LoadSALBPFileAction(Scenario base) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load Datafile");
                fileChooser.setInitialDirectory(new File(workingDir+"salbp/"));
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("ALB Files", "*.alb"));
                fileChooser.setInitialFileName("instance+n=20_1.alb");
                // allow to enter new file
                File selected = fileChooser.showOpenDialog(primaryStage);
                if (selected != null){
                        try {
                                setStatus("Reading file "+selected.getName());
                                info("Opening File: " + selected.getCanonicalPath()+" name "+selected.getName());
                                base.setDataFile(selected.getName());
                                new ReadSALBPFile(base,selected);
                                setTitle(applicationTitle+" ("+selected.getName()+")");
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
                        showView("custom/DiagramViewer");
                        setStatus("File read");
                }
        }
        @Override
        public void LoadSALBPAlternativeFileAction(Scenario base) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load Datafile");
                fileChooser.setInitialDirectory(new File(workingDir+"salbp/"));
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("ALB Files", "*.alb"));
                fileChooser.setInitialFileName("instance+n=20_1.alb");
                // allow to enter new file
                File selected = fileChooser.showOpenDialog(primaryStage);
                if (selected != null){
                        try {
                                setStatus("Reading file "+selected.getName());
                                info("Opening File: " + selected.getCanonicalPath()+" name "+selected.getName());
                                base.setDataFile(selected.getName());
                                new ReadSALBPAlternativeFile(base,selected);
                                setTitle(applicationTitle+" ("+selected.getName()+")");
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
                        showView("custom/DiagramViewer");
                        setStatus("File read");
                }
        }

        @Override
        public void LoadTestFileAction(Scenario base) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load Test Scheduling Datafile");
                fileChooser.setInitialDirectory(new File(workingDir+"testscheduling/"));
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("JSON Files", "*.json"));
                fileChooser.setInitialFileName("t20m10r3-1.pl.json");
                // allow to enter new file
                File selected = fileChooser.showOpenDialog(primaryStage);
                if (selected != null){
                        try {
                                setStatus("Reading file "+selected.getName());
                                info("Opening File: " + selected.getCanonicalPath()+" name "+selected.getName());
                                base.setDataFile(selected.getName());
                                new ReadTestSchedulingFile(base,selected);
                                setTitle(applicationTitle+" ("+selected.getName()+")");
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
                        showView("custom/DiagramViewer");
                        setStatus("File read");
                }
        }

        @Override
        public void LoadFJSPFileAction(Scenario base) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load FJSP Datafile");
                fileChooser.setInitialDirectory(new File(workingDir+"imports/fjsp/"));
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("FJS Files", "*.fjs"));
//                fileChooser.setInitialFileName("t20m10r3-1.pl.json");
                // allow to enter new file
                File selected = fileChooser.showOpenDialog(primaryStage);
                if (selected != null){
                        try {
                                setStatus("Reading file "+selected.getName());
                                info("Opening File: " + selected.getCanonicalPath()+" name "+selected.getName());
                                base.setDataFile(selected.getName());
                                new ReadFJSPFile(base,selected);
                                setTitle(applicationTitle+" ("+selected.getName()+")");
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
                        showView("custom/DiagramViewer");
                        setStatus("File read");
                }
        }

        @Override
        public void LoadNoWaitHFSFileAction(Scenario base) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load NoWaitHFS Datafile");
                fileChooser.setInitialDirectory(new File(workingDir+"nowaithfs/"));
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("HFS Files", "*.txt"));
//                fileChooser.setInitialFileName("t20m10r3-1.pl.json");
                // allow to enter new file
                File selected = fileChooser.showOpenDialog(primaryStage);
                if (selected != null){
                        try {
                                setStatus("Reading file "+selected.getName());
                                info("Opening File: " + selected.getCanonicalPath()+" name "+selected.getName());
                                base.setDataFile(selected.getName());
                                new ReadNoWaitHFSFile(base,selected);
                                setTitle(applicationTitle+" ("+selected.getName()+")");
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
                        showView("custom/DiagramViewer");
                        setStatus("File read");
                }
        }

        @Override
    public void LoadCFSNoWaitFileAction(Scenario base) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load CFSNoWait Datafile");
        fileChooser.setInitialDirectory(new File(workingDir+"cfsnowait/"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CFS Files", "*.txt"));
//                fileChooser.setInitialFileName("t20m10r3-1.pl.json");
        // allow to enter new file
        File selected = fileChooser.showOpenDialog(primaryStage);
        if (selected != null){
            try {
                setStatus("Reading file "+selected.getName());
                info("Opening File: " + selected.getCanonicalPath()+" name "+selected.getName());
                base.setDataFile(selected.getName());
                new ReadDFSNoWaitFile(base,selected);
                setTitle(applicationTitle+" ("+selected.getName()+")");
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
            showView("custom/DiagramViewer");
            setStatus("File read");
        }
    }




    @Override
        public void generateDataSolverRun(Scenario base) {
            GenerateDataDialogBoxImpl dialog = new GenerateDataDialogBoxImpl(this,base,
                    new GenerateDataSolverImpl(base));
            dialog.setShowLineChart(false);
                Optional<Boolean> result = dialog.showAndWait();
                setStatus("Data Generated");
                setTitle(applicationTitle+" (Generated data)");
                reset();
        }

        @Override
        public void scheduleJobsSolverRun(Scenario base) {
                Optional<Boolean> result = new ScheduleJobsDialogBoxImpl(this,base,
                        new ScheduleJobsSolverImpl(base)).showAndWait();
                reset();
                if (base.getListSolutionError().stream().anyMatch(x -> x.getSeverity() != Minor)) {
                        setStatus("Solver finished with errors");
                        showView("SolutionError");
                } else {
                        setStatus("Solver finished");
                        if (base.getHasDisjunctive()) {
                                showView("custom/GanttBorderViewer");
                        } else if (base.getHasCumulative()){
                                showView("custom/CumulativeResourceViewer");
                        } else {
                                showView("Solution");
                        }

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

//        @Override
//        public void showSolutionReport(Scenario base) {
//                try {
//                        showUrl("Solution Report",new File(workingDir+"reports/html/schedulingreport.html").getAbsoluteFile().toURI().toURL());
////                        showUrlInBrowser(new File(workingDir+"reports/html/schedulingreport.html").getAbsoluteFile().toURI().toURL());
//                } catch(Exception e){
//                        severe("Cannot show report "+e.getMessage());
//                }
//        }

        @Override
        public void diagramViewer(Scenario base) {
                showView("custom/DiagramViewer");
        }


        @Override
        public void ganttBorderViewer(Scenario base) {
                showView("custom/GanttBorderViewer");
        }

        @Override
        public void pertViewer(Scenario base) {
                showView("custom/PertViewer");
        }

        @Override
        public void disjunctiveResourceViewer(Scenario base) {
                showView("custom/DisjunctiveResourceViewer");
        }

        @Override
        public void cumulativeResourceViewer(Scenario base) {
                showView("custom/CumulativeResourceViewer");
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
                info("DataGenerator startDate "+res.getStartDateTime());
                return res;
        }

        public GanttProperty createGanttProperties(Scenario base){
                GanttProperty res = new GanttProperty(base);
                res.setName("Default Gantt Properties");
                return res;

        }

    public static void requiresDirectory(String name){
        File dir = new File(name);
        if (!dir.exists()){
            boolean res = dir.mkdir();
            if (!res){
                severe("Cannot create directory "+name);
            } else {
                info("Directory "+name+" created");
            }
        } else {
            info("Directory "+name+" exists");
        }
    }


}

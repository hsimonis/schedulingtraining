package org.insightcentre.tbischeduling.generatedsolver;

import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.JfxApp;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import framework.gui.JfxSolverDialogBox;
import framework.solver.AbstractSolver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import framework.types.DateOnly;
import framework.types.IrishCalendar;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;

public class ScheduleJobsDialogBox extends GeneralDialogBox{
   private TextField labelItem = new TextField();
   private TextField descriptionItem = new TextField();
   private TextField startDateItem = new TextField();
   private TextField startTimeItem = new TextField();
   private CheckBox enforceReleaseDateItem = new CheckBox();
   private CheckBox enforceDueDateItem = new CheckBox();
   private CheckBox enforceCumulativeItem = new CheckBox();
   private CheckBox enforceWipItem = new CheckBox();
   private CheckBox enforceDowntimeItem = new CheckBox();
   private CheckBox enforceSetupItem = new CheckBox();
   private TextField modelTypeItem = new TextField();
   private TextField solverBackendItem = new TextField();
   private TextField objectiveTypeItem = new TextField();
   private IntegerTextField weightMakespanItem = new IntegerTextField();
   private IntegerTextField weightFlowtimeItem = new IntegerTextField();
   private IntegerTextField weightLatenessItem = new IntegerTextField();
   private IntegerTextField weightEarlinessItem = new IntegerTextField();
   private IntegerTextField timeoutItem = new IntegerTextField();
   private IntegerTextField nrThreadsItem = new IntegerTextField();
   private IntegerTextField seedItem = new IntegerTextField();
   private CheckBox removeSolutionItem = new CheckBox();
   private CheckBox produceReportItem = new CheckBox();
   private CheckBox producePDFItem = new CheckBox();

    public ScheduleJobsDialogBox(GeneratedJfxApp app, Scenario base,AbstractSolver solver){
        super(app, base, solver);
        GridPane pane = new GridPane();
        pane.setVgap(10.0);
        pane.setHgap(10.0);
        int row = 0;
        pane.add(new Label("Label:"), 0, row);
        pane.add(labelItem, 1, row++);
        labelItem.setText(((ScheduleJobsSolver)solver).getLabel());
        pane.add(new Label("Description:"), 0, row);
        pane.add(descriptionItem, 1, row++);
        descriptionItem.setText(((ScheduleJobsSolver)solver).getDescription());
        pane.add(new Label("StartDate:"), 0, row);
        pane.add(startDateItem, 1, row++);
        startDateItem.setText(((ScheduleJobsSolver)solver).getStartDate());
        pane.add(new Label("StartTime:"), 0, row);
        pane.add(startTimeItem, 1, row++);
        startTimeItem.setText(((ScheduleJobsSolver)solver).getStartTime());
        pane.add(new Label("Enforce ReleaseDate:"), 0, row);
        pane.add(enforceReleaseDateItem, 1, row++);
        enforceReleaseDateItem.setSelected(((ScheduleJobsSolver)solver).getEnforceReleaseDate());
        pane.add(new Label("Enforce DueDate:"), 0, row);
        pane.add(enforceDueDateItem, 1, row++);
        enforceDueDateItem.setSelected(((ScheduleJobsSolver)solver).getEnforceDueDate());
        pane.add(new Label("Enforce Cumulative:"), 0, row);
        pane.add(enforceCumulativeItem, 1, row++);
        enforceCumulativeItem.setSelected(((ScheduleJobsSolver)solver).getEnforceCumulative());
        pane.add(new Label("Enforce WiP:"), 0, row);
        pane.add(enforceWipItem, 1, row++);
        enforceWipItem.setSelected(((ScheduleJobsSolver)solver).getEnforceWip());
        pane.add(new Label("Enforce Downtime:"), 0, row);
        pane.add(enforceDowntimeItem, 1, row++);
        enforceDowntimeItem.setSelected(((ScheduleJobsSolver)solver).getEnforceDowntime());
        pane.add(new Label("Enforce Setup Times:"), 0, row);
        pane.add(enforceSetupItem, 1, row++);
        enforceSetupItem.setSelected(((ScheduleJobsSolver)solver).getEnforceSetup());
        pane.add(new Label("Model Type:"), 0, row);
        pane.add(modelTypeItem, 1, row++);
        modelTypeItem.setText(((ScheduleJobsSolver)solver).getModelType());
        pane.add(new Label("Solver Backend:"), 0, row);
        pane.add(solverBackendItem, 1, row++);
        solverBackendItem.setText(((ScheduleJobsSolver)solver).getSolverBackend());
        pane.add(new Label("Objective Type:"), 0, row);
        pane.add(objectiveTypeItem, 1, row++);
        objectiveTypeItem.setText(((ScheduleJobsSolver)solver).getObjectiveType());
        pane.add(new Label("Weight Makespan:"), 0, row);
        pane.add(weightMakespanItem, 1, row++);
        weightMakespanItem.setText(String.format("%d",((ScheduleJobsSolver)solver).getWeightMakespan()));
        pane.add(new Label("Weight Flowtime:"), 0, row);
        pane.add(weightFlowtimeItem, 1, row++);
        weightFlowtimeItem.setText(String.format("%d",((ScheduleJobsSolver)solver).getWeightFlowtime()));
        pane.add(new Label("Weight Lateness:"), 0, row);
        pane.add(weightLatenessItem, 1, row++);
        weightLatenessItem.setText(String.format("%d",((ScheduleJobsSolver)solver).getWeightLateness()));
        pane.add(new Label("Weight Earliness:"), 0, row);
        pane.add(weightEarlinessItem, 1, row++);
        weightEarlinessItem.setText(String.format("%d",((ScheduleJobsSolver)solver).getWeightEarliness()));
        pane.add(new Label("Timeout (s):"), 0, row);
        pane.add(timeoutItem, 1, row++);
        timeoutItem.setText(String.format("%d",((ScheduleJobsSolver)solver).getTimeout()));
        pane.add(new Label("Nr Threads:"), 0, row);
        pane.add(nrThreadsItem, 1, row++);
        nrThreadsItem.setText(String.format("%d",((ScheduleJobsSolver)solver).getNrThreads()));
        pane.add(new Label("Random Seed:"), 0, row);
        pane.add(seedItem, 1, row++);
        seedItem.setText(String.format("%d",((ScheduleJobsSolver)solver).getSeed()));
        pane.add(new Label("Remove Previous Solution:"), 0, row);
        pane.add(removeSolutionItem, 1, row++);
        removeSolutionItem.setSelected(((ScheduleJobsSolver)solver).getRemoveSolution());
        pane.add(new Label("Produce LaTeX Report:"), 0, row);
        pane.add(produceReportItem, 1, row++);
        produceReportItem.setSelected(((ScheduleJobsSolver)solver).getProduceReport());
        pane.add(new Label("Produce PDF Report:"), 0, row);
        pane.add(producePDFItem, 1, row++);
        producePDFItem.setSelected(((ScheduleJobsSolver)solver).getProducePDF());
        getDialogPane().setContent(pane);
        setTitle("ScheduleJobs Solver Parameters");
    }
@Override
public void handle(InputEvent event) {
    if (event.getEventType() == MouseEvent.MOUSE_RELEASED ||
      (event.getEventType() == KeyEvent.KEY_RELEASED &&
      ((KeyEvent) event).getCode() == KeyCode.ENTER)) {
        info("Get ScheduleJobs parameters");
        String labelValue = labelItem.getText();
        String descriptionValue = descriptionItem.getText();
        String startDateValue = startDateItem.getText();
        String startTimeValue = startTimeItem.getText();
        boolean enforceReleaseDateValue = enforceReleaseDateItem.isSelected();
        boolean enforceDueDateValue = enforceDueDateItem.isSelected();
        boolean enforceCumulativeValue = enforceCumulativeItem.isSelected();
        boolean enforceWipValue = enforceWipItem.isSelected();
        boolean enforceDowntimeValue = enforceDowntimeItem.isSelected();
        boolean enforceSetupValue = enforceSetupItem.isSelected();
        String modelTypeValue = modelTypeItem.getText();
        String solverBackendValue = solverBackendItem.getText();
        String objectiveTypeValue = objectiveTypeItem.getText();
        int weightMakespanValue = Integer.parseInt(weightMakespanItem.getText());
        int weightFlowtimeValue = Integer.parseInt(weightFlowtimeItem.getText());
        int weightLatenessValue = Integer.parseInt(weightLatenessItem.getText());
        int weightEarlinessValue = Integer.parseInt(weightEarlinessItem.getText());
        int timeoutValue = Integer.parseInt(timeoutItem.getText());
        int nrThreadsValue = Integer.parseInt(nrThreadsItem.getText());
        int seedValue = Integer.parseInt(seedItem.getText());
        boolean removeSolutionValue = removeSolutionItem.isSelected();
        boolean produceReportValue = produceReportItem.isSelected();
        boolean producePDFValue = producePDFItem.isSelected();
        ((ScheduleJobsSolver)getSolver())
            .setLabel(labelValue)
            .setDescription(descriptionValue)
            .setStartDate(startDateValue)
            .setStartTime(startTimeValue)
            .setEnforceReleaseDate(enforceReleaseDateValue)
            .setEnforceDueDate(enforceDueDateValue)
            .setEnforceCumulative(enforceCumulativeValue)
            .setEnforceWip(enforceWipValue)
            .setEnforceDowntime(enforceDowntimeValue)
            .setEnforceSetup(enforceSetupValue)
            .setModelType(modelTypeValue)
            .setSolverBackend(solverBackendValue)
            .setObjectiveType(objectiveTypeValue)
            .setWeightMakespan(weightMakespanValue)
            .setWeightFlowtime(weightFlowtimeValue)
            .setWeightLateness(weightLatenessValue)
            .setWeightEarliness(weightEarlinessValue)
            .setTimeout(timeoutValue)
            .setNrThreads(nrThreadsValue)
            .setSeed(seedValue)
            .setRemoveSolution(removeSolutionValue)
            .setProduceReport(produceReportValue)
            .setProducePDF(producePDFValue)
            ;
        super.handle(event);
    }
}
}

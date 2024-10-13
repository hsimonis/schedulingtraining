package org.insightcentre.tbischeduling.generatedsolver;

import framework.solver.AbstractSolver;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.converter.LocalDateStringConverter;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.ModelType;
import org.insightcentre.tbischeduling.datamodel.ObjectiveType;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverBackend;

import java.time.format.DateTimeFormatter;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;

public class ScheduleJobsDialogBoxImpl extends GeneralDialogBox {
    private final TextField labelItem = new TextField();
    private final TextArea descriptionItem = new TextArea();
    private final DatePicker startDateItem = new DatePicker();
    private final TextField startTimeItem = new TextField();
    private final CheckBox enforceReleaseDateItem = new CheckBox();
    private final CheckBox enforceDueDateItem = new CheckBox();
    private final CheckBox enforceCumulativeItem = new CheckBox();
    private final CheckBox enforceWipItem = new CheckBox();
    private final CheckBox enforceDowntimeItem = new CheckBox();
    private final ChoiceBox modelTypeItem = new ChoiceBox();
    private final ChoiceBox solverBackendItem = new ChoiceBox();
    private final ChoiceBox objectiveTypeItem = new ChoiceBox();
    private final IntegerTextField weightMakespanItem = new IntegerTextField();
    private final IntegerTextField weightFlowtimeItem = new IntegerTextField();
    private final IntegerTextField weightLatenessItem = new IntegerTextField();
    private final IntegerTextField weightEarlinessItem = new IntegerTextField();
    private final IntegerTextField timeoutItem = new IntegerTextField();
    private final IntegerTextField nrThreadsItem = new IntegerTextField();
    private final IntegerTextField seedItem = new IntegerTextField();
    private final CheckBox removeSolutionItem = new CheckBox();
    private final CheckBox produceReportItem = new CheckBox();
    private final CheckBox producePDFItem = new CheckBox();

    private final String pattern = "d-M-yyyy";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    LocalDateStringConverter dateConverter = new LocalDateStringConverter(formatter,formatter);

    public ScheduleJobsDialogBoxImpl(GeneratedJfxApp app, Scenario base, AbstractSolver solver){
        super(app, base, solver);
        GridPane pane = new GridPane();
        pane.setVgap(10.0);
        pane.setHgap(10.0);
        int row = 0;
        pane.add(new Label("Label:"), 0, row);
        pane.add(labelItem, 1, row++);
        labelItem.setText(((ScheduleJobsSolver)solver).getLabel());
        pane.add(new Label("Description:"), 0, row);
        descriptionItem.setPrefRowCount(4);
        descriptionItem.setPrefColumnCount(10);
        descriptionItem.setWrapText(true);
        pane.add(descriptionItem, 1, row++);
        descriptionItem.setText(((ScheduleJobsSolver)solver).getDescription());
        // make descriptionItem span 3 columns
        GridPane.setColumnSpan( descriptionItem, 3 );

        Separator sep1 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep1,0,row++,2,1);

        pane.add(new Label("StartDate:"), 0, row);
        pane.add(startDateItem, 1, row++);
        startDateItem.setValue(dateConverter.fromString(((ScheduleJobsSolver)solver).getStartDate()));
        pane.add(new Label("Start Time:"), 0, row);
        pane.add(startTimeItem, 1, row++);
        startTimeItem.setText(((ScheduleJobsSolver)solver).getStartTime());

        Separator sep2 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep2,0,row++,2,1);

        pane.add(new Label("Enforce Release Date:"), 0, row);
        pane.add(enforceReleaseDateItem, 1, row++);
        enforceReleaseDateItem.setSelected(((ScheduleJobsSolver)solver).getEnforceReleaseDate());

        pane.add(new Label("Enforce Due Date:"), 0, row);
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

        Separator sep3 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep3,0,row++,2,1);

        pane.add(new Label("Model Type:"), 0, row);
        pane.add(modelTypeItem, 1, row++);
        modelTypeItem.getItems().addAll(ModelType.getNames());
        modelTypeItem.setValue(((ScheduleJobsSolver)solver).getModelType());
        modelTypeItem.setOnAction( (evt) -> {

            String model = modelTypeItem.getValue().toString();
//            info("Update "+obj);

            if (model.equals("CPO")){
                solverBackendItem.setValue("None");
            } else if (model.equals("REST")){
                solverBackendItem.setValue("None");
            } else {
                solverBackendItem.setValue("CPSat");
            }
        });

        pane.add(new Label("Solver Backend:"), 0, row);
        pane.add(solverBackendItem, 1, row++);
        solverBackendItem.getItems().addAll(SolverBackend.getNames());
        solverBackendItem.setValue(((ScheduleJobsSolver)solver).getSolverBackend());

        Separator sep4 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep4,0,row++,2,1);

        pane.add(new Label("Objective Type:"), 0, row);
        pane.add(objectiveTypeItem, 1, row++);
        objectiveTypeItem.getItems().addAll(ObjectiveType.getNames());
        objectiveTypeItem.setValue(((ScheduleJobsSolver)solver).getObjectiveType());
        objectiveTypeItem.setOnAction( (evt) -> {

            String obj = objectiveTypeItem.getValue().toString();
//            info("Update "+obj);

            if (obj.equals("Hybrid")){
                weightMakespanItem.setDisable(false);
                weightFlowtimeItem.setDisable(false);
                weightEarlinessItem.setDisable(false);
                weightLatenessItem.setDisable(false);
            } else if (obj.equals("OnTime")){
                weightMakespanItem.setDisable(true);
                weightFlowtimeItem.setDisable(true);
                weightEarlinessItem.setDisable(false);
                weightLatenessItem.setDisable(false);
            } else {
                weightMakespanItem.setDisable(true);
                weightFlowtimeItem.setDisable(true);
                weightEarlinessItem.setDisable(true);
                weightLatenessItem.setDisable(true);
            }
        });

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
        //??? by default make them disabled, this might not match the default value of the objective
        weightMakespanItem.setDisable(true);
        weightFlowtimeItem.setDisable(true);
        weightEarlinessItem.setDisable(true);
        weightLatenessItem.setDisable(true);

        Separator sep5 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep5,0,row++,2,1);

        pane.add(new Label("Timeout (s):"), 0, row);
        pane.add(timeoutItem, 1, row++);
        timeoutItem.setText(String.format("%d",((ScheduleJobsSolver)solver).getTimeout()));
        pane.add(new Label("Nr Threads:"), 0, row);
        pane.add(nrThreadsItem, 1, row++);
        nrThreadsItem.setText(String.format("%d",((ScheduleJobsSolver)solver).getNrThreads()));
        pane.add(new Label("Random Seed:"), 0, row);
        pane.add(seedItem, 1, row++);
        seedItem.setText(String.format("%d",((ScheduleJobsSolver)solver).getSeed()));
        pane.add(new Label("Remove Previous Solutions:"), 0, row);
        pane.add(removeSolutionItem, 1, row++);
        removeSolutionItem.setSelected(((ScheduleJobsSolver)solver).getRemoveSolution());

        pane.add(new Label("Produce Report LaTeX:"), 0, row);
        pane.add(produceReportItem, 1, row);
        produceReportItem.setSelected(((ScheduleJobsSolver)solver).getProduceReport());
        pane.add(new Label("PDF:"), 2, row);
        pane.add(producePDFItem, 3, row++);
        producePDFItem.setSelected(((ScheduleJobsSolver)solver).getProducePDF());

//        pane.setGridLinesVisible(true); //??? debug only
        getDialogPane().setContent(pane);
        setTitle("Schedule Solver Parameters");
    }
    @Override
    public void handle(InputEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_RELEASED ||
                (event.getEventType() == KeyEvent.KEY_RELEASED &&
                        ((KeyEvent) event).getCode() == KeyCode.ENTER)) {
            info("Get ScheduleJobs parameters");
            String labelValue = labelItem.getText();
            String descriptionValue = descriptionItem.getText();
            String startDateValue = dateConverter.toString(startDateItem.getValue());
            String startTimeValue = startTimeItem.getText();
            boolean enforceReleaseDateValue = enforceReleaseDateItem.isSelected();
            boolean enforceDueDateValue = enforceDueDateItem.isSelected();
            boolean enforceCumulativeValue = enforceCumulativeItem.isSelected();
            boolean enforceWipValue = enforceWipItem.isSelected();
            boolean enforceDowntimeValue = enforceDowntimeItem.isSelected();

            String modelTypeValue = modelTypeItem.getValue().toString();
            String solverBackendValue = solverBackendItem.getValue().toString();
            String objectiveTypeValue = objectiveTypeItem.getValue().toString();
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
        } else {
//            info("event "+event.getEventType());
        }
    }
}

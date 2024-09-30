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
import org.insightcentre.tbischeduling.datamodel.*;

import java.time.format.DateTimeFormatter;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;

public class GenerateDataDialogBoxImpl extends GeneralDialogBox {
    private final TextField nameItem = new TextField();
    private final ChoiceBox resourceModelItem = new ChoiceBox();
    private final IntegerTextField nrProductsItem = new IntegerTextField();
    private final IntegerTextField minStagesItem = new IntegerTextField();
    private final IntegerTextField maxStagesItem = new IntegerTextField();
    private final IntegerTextField nrDisjunctiveResourcesItem = new IntegerTextField();
    private final DoubleTextField resourceProbabilityItem = new DoubleTextField();
    private final ChoiceBox durationModelItem = new ChoiceBox();
    private final IntegerTextField minDurationItem = new IntegerTextField();
    private final IntegerTextField maxDurationItem = new IntegerTextField();
    private final IntegerTextField durationFixedFactorItem = new IntegerTextField();
    private final IntegerTextField nrCumulativeResourcesItem = new IntegerTextField();
    private final IntegerTextField minCumulDemandItem = new IntegerTextField();
    private final IntegerTextField maxCumulDemandItem = new IntegerTextField();
    private final IntegerTextField cumulCapacityItem = new IntegerTextField();
    private final IntegerTextField nrOrdersItem = new IntegerTextField();
    private final IntegerTextField minQtyItem = new IntegerTextField();
    private final IntegerTextField maxQtyItem = new IntegerTextField();
    private final IntegerTextField earliestDueItem = new IntegerTextField();
    private final IntegerTextField horizonDaysItem = new IntegerTextField();
    private final IntegerTextField timeResolutionItem = new IntegerTextField();
    private final IntegerTextField seedItem = new IntegerTextField();

    private final String pattern = "d-M-yyyy";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    LocalDateStringConverter dateConverter = new LocalDateStringConverter(formatter,formatter);

    public GenerateDataDialogBoxImpl(GeneratedJfxApp app, Scenario base, AbstractSolver solver){
        super(app, base, solver);
        GridPane pane = new GridPane();
        pane.setVgap(10.0);
        pane.setHgap(10.0);
        int row = 0;
        pane.add(new Label("Name:"), 0, row);
        pane.add(nameItem, 1, row++);
        nameItem.setText(((GenerateDataSolver)solver).getName());

        Separator sep1 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep1,0,row++,2,1);


        pane.add(new Label("Resource Model:"), 0, row);
        pane.add(resourceModelItem, 1, row++);
        resourceModelItem.getItems().addAll(ResourceModel.getNames());
        resourceModelItem.setValue(((GenerateDataSolver)solver).getResourceModel());

        pane.add(new Label("Nr Products:"), 0, row);
        pane.add(nrProductsItem, 1, row++);
        nrProductsItem.setText(String.format("%d",((GenerateDataSolver)solver).getNrProducts()));

        pane.add(new Label("Min Stages:"), 0, row);
        pane.add(minStagesItem, 1, row++);
        minStagesItem.setText(String.format("%d",((GenerateDataSolver)solver).getMinStages()));

        pane.add(new Label("Max Stages:"), 0, row);
        pane.add(maxStagesItem, 1, row++);
        maxStagesItem.setText(String.format("%d",((GenerateDataSolver)solver).getMaxStages()));

        Separator sep2 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep2,0,row++,2,1);


        pane.add(new Label("Nr Disjunctive Resources:"), 0, row);
        pane.add(nrDisjunctiveResourcesItem, 1, row++);
        nrDisjunctiveResourcesItem.setText(String.format("%d",((GenerateDataSolver)solver).getNrDisjunctiveResources()));

        pane.add(new Label("Resource Probability:"), 0, row);
        pane.add(resourceProbabilityItem, 1, row++);
        resourceProbabilityItem.setText(String.format("%f",((GenerateDataSolver)solver).getResourceProbability()));

        Separator sep3 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep3,0,row++,2,1);


        pane.add(new Label("Duration Model:"), 0, row);
        pane.add(durationModelItem, 1, row++);
        durationModelItem.getItems().addAll(DurationModel.getNames());
        durationModelItem.setValue(((GenerateDataSolver)solver).getDurationModel());

        pane.add(new Label("Min Duration:"), 0, row);
        pane.add(minDurationItem, 1, row++);
        minDurationItem.setText(String.format("%d",((GenerateDataSolver)solver).getMinDuration()));

        pane.add(new Label("Max Duration:"), 0, row);
        pane.add(maxDurationItem, 1, row++);
        maxDurationItem.setText(String.format("%d",((GenerateDataSolver)solver).getMaxDuration()));

        pane.add(new Label("Duration Fixed Factor:"), 0, row);
        pane.add(durationFixedFactorItem, 1, row++);
        durationFixedFactorItem.setText(String.format("%d",((GenerateDataSolver)solver).getDurationFixedFactor()));

        Separator sep4 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep4,0,row++,2,1);


        pane.add(new Label("Nr Cumulative Resources:"), 0, row);
        pane.add(nrCumulativeResourcesItem, 1, row++);
        nrCumulativeResourcesItem.setText(String.format("%d",((GenerateDataSolver)solver).getNrCumulativeResources()));

        pane.add(new Label("Min Cumul Demand:"), 0, row);
        pane.add(minCumulDemandItem, 1, row++);
        minCumulDemandItem.setText(String.format("%d",((GenerateDataSolver)solver).getMinCumulDemand()));

        pane.add(new Label("Max Cumul Demand:"), 0, row);
        pane.add(maxCumulDemandItem, 1, row++);
        maxCumulDemandItem.setText(String.format("%d",((GenerateDataSolver)solver).getMaxCumulDemand()));

        pane.add(new Label("Cumul Capacity:"), 0, row);
        pane.add(cumulCapacityItem, 1, row++);
        cumulCapacityItem.setText(String.format("%d",((GenerateDataSolver)solver).getCumulCapacity()));

        Separator sep5 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep5,0,row++,2,1);


        pane.add(new Label("Nr Orders:"), 0, row);
        pane.add(nrOrdersItem, 1, row++);
        nrOrdersItem.setText(String.format("%d",((GenerateDataSolver)solver).getNrOrders()));

        pane.add(new Label("Min Qty:"), 0, row);
        pane.add(minQtyItem, 1, row++);
        minQtyItem.setText(String.format("%d",((GenerateDataSolver)solver).getMinQty()));

        pane.add(new Label("Max Qty:"), 0, row);
        pane.add(maxQtyItem, 1, row++);
        maxQtyItem.setText(String.format("%d",((GenerateDataSolver)solver).getMaxQty()));

        Separator sep6 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep6,0,row++,2,1);


        pane.add(new Label("Earliest Due:"), 0, row);
        pane.add(earliestDueItem, 1, row++);
        earliestDueItem.setText(String.format("%d",((GenerateDataSolver)solver).getEarliestDue()));

        pane.add(new Label("Horizon Days:"), 0, row);
        pane.add(horizonDaysItem, 1, row++);
        horizonDaysItem.setText(String.format("%d",((GenerateDataSolver)solver).getHorizonDays()));

        pane.add(new Label("Time Resolution:"), 0, row);
        pane.add(timeResolutionItem, 1, row++);
        timeResolutionItem.setText(String.format("%d",((GenerateDataSolver)solver).getTimeResolution()));

        pane.add(new Label("Random Seed:"), 0, row);
        pane.add(seedItem, 1, row++);
        seedItem.setText(String.format("%d",((GenerateDataSolver)solver).getSeed()));

        getDialogPane().setContent(pane);
        setTitle("Schedule Solver Parameters");
    }
    @Override
    public void handle(InputEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_RELEASED ||
                (event.getEventType() == KeyEvent.KEY_RELEASED &&
                        ((KeyEvent) event).getCode() == KeyCode.ENTER)) {
            info("Get ScheduleJobs parameters");
            String nameValue = nameItem.getText();
            String resourceModelValue = resourceModelItem.getValue().toString();
            int nrProductsValue = Integer.parseInt(nrProductsItem.getText());
            int minStagesValue = Integer.parseInt(minStagesItem.getText());
            int maxStagesValue = Integer.parseInt(maxStagesItem.getText());
            int nrDisjunctiveResourcesValue = Integer.parseInt(nrDisjunctiveResourcesItem.getText());
            double resourceProbabilityValue = Double.parseDouble(resourceProbabilityItem.getText());

            String durationModelValue = durationModelItem.getValue().toString();
            int minDurationValue = Integer.parseInt(minDurationItem.getText());
            int maxDurationValue = Integer.parseInt(maxDurationItem.getText());
            int durationFixedFactorValue = Integer.parseInt(durationFixedFactorItem.getText());

            int nrCumulativeResourcesValue = Integer.parseInt(nrCumulativeResourcesItem.getText());
            int minCumulDemandValue = Integer.parseInt(minCumulDemandItem.getText());
            int maxCumulDemandValue = Integer.parseInt(maxCumulDemandItem.getText());
            int cumulCapacityValue = Integer.parseInt(cumulCapacityItem.getText());

            int nrOrdersValue = Integer.parseInt(nrOrdersItem.getText());
            int minQtyValue = Integer.parseInt(minQtyItem.getText());
            int maxQtyValue = Integer.parseInt(maxQtyItem.getText());

            int earliestDueValue = Integer.parseInt(earliestDueItem.getText());
            int horizonDaysValue = Integer.parseInt(horizonDaysItem.getText());
            int timeResolutionValue = Integer.parseInt(timeResolutionItem.getText());
            int seedValue = Integer.parseInt(seedItem.getText());

            ((GenerateDataSolver)getSolver())
                    .setName(nameValue)
                    .setResourceModel(resourceModelValue)
                    .setNrProducts(nrProductsValue)
                    .setMinStages(minStagesValue)
                    .setMaxStages(maxStagesValue)
                    .setNrDisjunctiveResources(nrDisjunctiveResourcesValue)
                    .setResourceProbability(resourceProbabilityValue)
                    .setDurationModel(durationModelValue)
                    .setMinDuration(minDurationValue)
                    .setMaxDuration(maxDurationValue)
                    .setDurationFixedFactor(durationFixedFactorValue)
                    .setNrCumulativeResources(nrCumulativeResourcesValue)
                    .setMinCumulDemand(minCumulDemandValue)
                    .setMaxCumulDemand(maxCumulDemandValue)
                    .setCumulCapacity(cumulCapacityValue)
                    .setNrOrders(nrOrdersValue)
                    .setMinQty(minQtyValue)
                    .setMaxQty(maxQtyValue)
                    .setEarliestDue(earliestDueValue)
                    .setHorizonDays(horizonDaysValue)
                    .setTimeResolution(timeResolutionValue)
                    .setSeed(seedValue)
            ;
            super.handle(event);
        } else {
//            info("event "+event.getEventType());
        }
    }
}

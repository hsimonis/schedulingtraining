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

public class GenerateDataDialogBox extends GeneralDialogBox{
   private TextField nameItem = new TextField();
   private TextField resourceModelItem = new TextField();
   private IntegerTextField nrProductsItem = new IntegerTextField();
   private IntegerTextField minStagesItem = new IntegerTextField();
   private IntegerTextField maxStagesItem = new IntegerTextField();
   private IntegerTextField nrDisjunctiveResourcesItem = new IntegerTextField();
   private DoubleTextField resourceProbabilityItem = new DoubleTextField();
   private TextField durationModelItem = new TextField();
   private IntegerTextField minDurationItem = new IntegerTextField();
   private IntegerTextField maxDurationItem = new IntegerTextField();
   private IntegerTextField durationFixedFactorItem = new IntegerTextField();
   private IntegerTextField nrCumulativeResourcesItem = new IntegerTextField();
   private IntegerTextField minCumulDemandItem = new IntegerTextField();
   private IntegerTextField maxCumulDemandItem = new IntegerTextField();
   private IntegerTextField cumulCapacityItem = new IntegerTextField();
   private IntegerTextField nrOrdersItem = new IntegerTextField();
   private IntegerTextField minQtyItem = new IntegerTextField();
   private IntegerTextField maxQtyItem = new IntegerTextField();
   private IntegerTextField earliestDueItem = new IntegerTextField();
   private IntegerTextField horizonDaysItem = new IntegerTextField();
   private IntegerTextField timeResolutionItem = new IntegerTextField();
   private IntegerTextField seedItem = new IntegerTextField();

    public GenerateDataDialogBox(GeneratedJfxApp app, Scenario base,AbstractSolver solver){
        super(app, base, solver);
        GridPane pane = new GridPane();
        pane.setVgap(10.0);
        pane.setHgap(10.0);
        int row = 0;
        pane.add(new Label("name:"), 0, row);
        pane.add(nameItem, 1, row++);
        nameItem.setText(((GenerateDataSolver)solver).getName());
        pane.add(new Label("Resource Model:"), 0, row);
        pane.add(resourceModelItem, 1, row++);
        resourceModelItem.setText(((GenerateDataSolver)solver).getResourceModel());
        pane.add(new Label("nr Products:"), 0, row);
        pane.add(nrProductsItem, 1, row++);
        nrProductsItem.setText(String.format("%d",((GenerateDataSolver)solver).getNrProducts()));
        pane.add(new Label("Min Nr Stages:"), 0, row);
        pane.add(minStagesItem, 1, row++);
        minStagesItem.setText(String.format("%d",((GenerateDataSolver)solver).getMinStages()));
        pane.add(new Label("Max Nr Stages:"), 0, row);
        pane.add(maxStagesItem, 1, row++);
        maxStagesItem.setText(String.format("%d",((GenerateDataSolver)solver).getMaxStages()));
        pane.add(new Label("Nr Disjunctive Resources:"), 0, row);
        pane.add(nrDisjunctiveResourcesItem, 1, row++);
        nrDisjunctiveResourcesItem.setText(String.format("%d",((GenerateDataSolver)solver).getNrDisjunctiveResources()));
        pane.add(new Label("Resource Probability:"), 0, row);
        pane.add(resourceProbabilityItem, 1, row++);
        resourceProbabilityItem.setText(String.format("%f",((GenerateDataSolver)solver).getResourceProbability()));
        pane.add(new Label("Duration Model:"), 0, row);
        pane.add(durationModelItem, 1, row++);
        durationModelItem.setText(((GenerateDataSolver)solver).getDurationModel());
        pane.add(new Label("Min Duration:"), 0, row);
        pane.add(minDurationItem, 1, row++);
        minDurationItem.setText(String.format("%d",((GenerateDataSolver)solver).getMinDuration()));
        pane.add(new Label("Max Duration:"), 0, row);
        pane.add(maxDurationItem, 1, row++);
        maxDurationItem.setText(String.format("%d",((GenerateDataSolver)solver).getMaxDuration()));
        pane.add(new Label("Duration Fixed Factor:"), 0, row);
        pane.add(durationFixedFactorItem, 1, row++);
        durationFixedFactorItem.setText(String.format("%d",((GenerateDataSolver)solver).getDurationFixedFactor()));
        pane.add(new Label("Nr Cumulative Resources:"), 0, row);
        pane.add(nrCumulativeResourcesItem, 1, row++);
        nrCumulativeResourcesItem.setText(String.format("%d",((GenerateDataSolver)solver).getNrCumulativeResources()));
        pane.add(new Label("Min Cumulative Demand:"), 0, row);
        pane.add(minCumulDemandItem, 1, row++);
        minCumulDemandItem.setText(String.format("%d",((GenerateDataSolver)solver).getMinCumulDemand()));
        pane.add(new Label("Max Cumulative Demand:"), 0, row);
        pane.add(maxCumulDemandItem, 1, row++);
        maxCumulDemandItem.setText(String.format("%d",((GenerateDataSolver)solver).getMaxCumulDemand()));
        pane.add(new Label("Cumulative Capacity:"), 0, row);
        pane.add(cumulCapacityItem, 1, row++);
        cumulCapacityItem.setText(String.format("%d",((GenerateDataSolver)solver).getCumulCapacity()));
        pane.add(new Label("nr Orders:"), 0, row);
        pane.add(nrOrdersItem, 1, row++);
        nrOrdersItem.setText(String.format("%d",((GenerateDataSolver)solver).getNrOrders()));
        pane.add(new Label("Min Qty:"), 0, row);
        pane.add(minQtyItem, 1, row++);
        minQtyItem.setText(String.format("%d",((GenerateDataSolver)solver).getMinQty()));
        pane.add(new Label("Max Qty:"), 0, row);
        pane.add(maxQtyItem, 1, row++);
        maxQtyItem.setText(String.format("%d",((GenerateDataSolver)solver).getMaxQty()));
        pane.add(new Label("Earliest DueDate (int):"), 0, row);
        pane.add(earliestDueItem, 1, row++);
        earliestDueItem.setText(String.format("%d",((GenerateDataSolver)solver).getEarliestDue()));
        pane.add(new Label("Horizon (days):"), 0, row);
        pane.add(horizonDaysItem, 1, row++);
        horizonDaysItem.setText(String.format("%d",((GenerateDataSolver)solver).getHorizonDays()));
        pane.add(new Label("Time Resolution (min):"), 0, row);
        pane.add(timeResolutionItem, 1, row++);
        timeResolutionItem.setText(String.format("%d",((GenerateDataSolver)solver).getTimeResolution()));
        pane.add(new Label("Random Seed:"), 0, row);
        pane.add(seedItem, 1, row++);
        seedItem.setText(String.format("%d",((GenerateDataSolver)solver).getSeed()));
        getDialogPane().setContent(pane);
        setTitle("GenerateData Solver Parameters");
    }
@Override
public void handle(InputEvent event) {
    if (event.getEventType() == MouseEvent.MOUSE_RELEASED ||
      (event.getEventType() == KeyEvent.KEY_RELEASED &&
      ((KeyEvent) event).getCode() == KeyCode.ENTER)) {
        info("Get GenerateData parameters");
        String nameValue = nameItem.getText();
        String resourceModelValue = resourceModelItem.getText();
        int nrProductsValue = Integer.parseInt(nrProductsItem.getText());
        int minStagesValue = Integer.parseInt(minStagesItem.getText());
        int maxStagesValue = Integer.parseInt(maxStagesItem.getText());
        int nrDisjunctiveResourcesValue = Integer.parseInt(nrDisjunctiveResourcesItem.getText());
        double resourceProbabilityValue = Double.parseDouble(resourceProbabilityItem.getText());
        String durationModelValue = durationModelItem.getText();
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
    }
}
}

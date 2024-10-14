package org.insightcentre.tbischeduling.generatedsolver;

import com.dlsc.gemsfx.TimePicker;
import framework.solver.AbstractSolver;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import org.controlsfx.control.RangeSlider;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;

public class GenerateDataDialogBoxImpl extends GeneralDialogBox {
    private final TextField labelItem = new TextField();

    private final DatePicker startDateItem = new DatePicker();
    private final TimePicker startTimeItem = new TimePicker();

    private final ChoiceBox resourceModelItem = new ChoiceBox();
    private final IntegerTextField nrProductsItem = new IntegerTextField();

    private final RangeSlider stagesItem = new RangeSlider(1,8,4,5);
    private final Slider nrDisjunctiveResourcesItem = new Slider(1,20,8);
    private final Slider resourceProbabilityItem = new Slider(0.0,1.0,0.3);
    private final ChoiceBox durationModelItem = new ChoiceBox();
    private final RangeSlider durationItem = new RangeSlider(1,50,1,10);
    private final Slider durationFixedFactorItem = new Slider(0,5,1);
    private final Slider nrCumulativeResourcesItem = new Slider(1,5,1);
    private final RangeSlider cumulDemandItem = new RangeSlider(1,10,1,1);
    private final Slider profilePiecesItem = new Slider(1,5,3);
    private final RangeSlider cumulCapacityItem = new RangeSlider(1,30,5,10);
    private final IntegerTextField nrOrdersItem = new IntegerTextField();
    private final RangeSlider qtyItem = new RangeSlider(1,30,1,10);
    private final Slider wipProbabilityItem = new Slider(0.0,1.0,0.3);
    private final RangeSlider wipItem = new RangeSlider(1,100,20,50);
    private final Slider downtimeProbabilityItem = new Slider(0.0,1.0,0.3);
    private final RangeSlider downtimeItem = new RangeSlider(1,100,50,70);
    private final IntegerTextField earliestDueItem = new IntegerTextField();
    private final IntegerTextField horizonDaysItem = new IntegerTextField();
    private final IntegerTextField timeResolutionItem = new IntegerTextField();
    private final IntegerTextField seedItem = new IntegerTextField();

    private final String datePattern = "d-M-yyyy";
    private final String timePattern = "HH:mm";
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timePattern);
    LocalDateStringConverter dateConverter = new LocalDateStringConverter(dateFormatter,dateFormatter);
    LocalTimeStringConverter timeConverter = new LocalTimeStringConverter(timeFormatter,timeFormatter);

    double sliderWidth=400.0;
    double vGap=10.0;
    double hGap=10.0;

    public GenerateDataDialogBoxImpl(GeneratedJfxApp app, Scenario base, AbstractSolver solver){
        super(app, base, solver);
        GridPane pane = new GridPane();
        pane.setVgap(vGap);
        pane.setHgap(hGap);
        int row = 0;
        pane.add(new Label("Label:"), 0, row);
        pane.add(labelItem, 1, row++);
        labelItem.setText(((GenerateDataSolver)solver).getLabel());
        GridPane.setColumnSpan( labelItem, 3 );

        pane.add(new Label("StartDate:"), 0, row);
        pane.add(startDateItem, 1, row);
        startDateItem.setValue(dateConverter.fromString(((GenerateDataSolver)solver).getStartDate()));
        startTimeItem.setClockType(TimePicker.ClockType.TWENTY_FOUR_HOUR_CLOCK);
        pane.add(new Label("Start Time:"), 2, row);
        startTimeItem.setTime(LocalTime.of(0,0));
        pane.add(startTimeItem, 3, row++);
//        startTimeItem.setValue(LocalTime.from(timeConverter.fromString(((GenerateDataSolver)solver).getStartTime())));


        Separator sep1 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep1,0,row++,4,1);


        pane.add(new Label("Resource Model:"), 0, row);
        pane.add(resourceModelItem, 1, row++);
        resourceModelItem.getItems().addAll(ResourceModel.getNames());
        resourceModelItem.setValue(((GenerateDataSolver)solver).getResourceModel());

        pane.add(new Label("Nr Disjunctive Resources:"), 0, row);
        nrDisjunctiveResourcesItem.setMajorTickUnit(2);
        nrDisjunctiveResourcesItem.setMinorTickCount(1);
        nrDisjunctiveResourcesItem.setShowTickMarks(true);
        nrDisjunctiveResourcesItem.setShowTickLabels(true);
        nrDisjunctiveResourcesItem.setPrefWidth(sliderWidth);
        pane.add(nrDisjunctiveResourcesItem, 1, row++);
        nrDisjunctiveResourcesItem.setValue(((GenerateDataSolver)solver).getNrDisjunctiveResources());
        GridPane.setColumnSpan( nrDisjunctiveResourcesItem, 3 );

        pane.add(new Label("Resource Probability:"), 0, row);
        resourceProbabilityItem.setMajorTickUnit(0.1);
        resourceProbabilityItem.setMinorTickCount(1);
        resourceProbabilityItem.setShowTickMarks(true);
        resourceProbabilityItem.setShowTickLabels(true);
//        resourceProbabilityItem.setSnapToTicks(true);
        resourceProbabilityItem.setPrefWidth(sliderWidth);
        pane.add(resourceProbabilityItem, 1, row++);
        resourceProbabilityItem.setValue(((GenerateDataSolver)solver).getResourceProbability());
        GridPane.setColumnSpan( resourceProbabilityItem, 3 );


        Accordion accordion = new Accordion();
        pane.add(accordion,0,row++);
        GridPane.setColumnSpan( accordion, 5 );

        GridPane productGrid = new GridPane();
        productGrid.setVgap(vGap);
        productGrid.setHgap(hGap);
        int pRow =0;
        TitledPane productPane = new TitledPane("Products",productGrid);
        accordion.getPanes().add(productPane);

        productGrid.add(new Label("Nr Products:"), 0, pRow);
        productGrid.add(nrProductsItem, 1, pRow++);
        nrProductsItem.setText(String.format("%d",((GenerateDataSolver)solver).getNrProducts()));

        productGrid.add(new Label("Stages Range:"), 0, pRow);
        stagesItem.setMajorTickUnit(1);
        stagesItem.setMinorTickCount(0);
        stagesItem.setShowTickMarks(true);
        stagesItem.setShowTickLabels(true);
        stagesItem.setSnapToTicks(true);
        productGrid.add(stagesItem,1,pRow++);
        stagesItem.setLowValue(((GenerateDataSolver)solver).getMinStages());
        stagesItem.setHighValue(((GenerateDataSolver)solver).getMaxStages());
        GridPane.setColumnSpan( stagesItem, 3 );


        GridPane durationGrid = new GridPane();
        durationGrid.setVgap(vGap);
        durationGrid.setHgap(hGap);
        int duRow =0;
        TitledPane durationPane = new TitledPane("Duration",durationGrid);
        accordion.getPanes().add(durationPane);

        durationGrid.add(new Label("Duration Model:"), 0, duRow);
        durationGrid.add(durationModelItem, 1, duRow++);
        durationModelItem.getItems().addAll(DurationModel.getNames());
        durationModelItem.setValue(((GenerateDataSolver)solver).getDurationModel());

        durationGrid.add(new Label("Duration Range:"), 0, duRow);
        durationItem.setMajorTickUnit(5);
        durationItem.setMinorTickCount(4);
        durationItem.setShowTickMarks(true);
        durationItem.setShowTickLabels(true);
        durationItem.setSnapToTicks(true);
        durationItem.setPrefWidth(sliderWidth);
        durationGrid.add(durationItem,1,duRow++);
        durationItem.setLowValue(((GenerateDataSolver)solver).getMinDuration());
        durationItem.setHighValue(((GenerateDataSolver)solver).getMaxDuration());
//        GridPane.setColumnSpan( durationItem, 5 );

        durationGrid.add(new Label("Duration Fixed Factor:"), 0, duRow);
        durationFixedFactorItem.setMajorTickUnit(1);
        durationFixedFactorItem.setMinorTickCount(0);
        durationFixedFactorItem.setShowTickMarks(true);
        durationFixedFactorItem.setShowTickLabels(true);
        durationGrid.add(durationFixedFactorItem, 1, duRow++);
        durationFixedFactorItem.setValue(((GenerateDataSolver)solver).getDurationFixedFactor());



        GridPane cumulGrid = new GridPane();
        cumulGrid.setVgap(vGap);
        cumulGrid.setHgap(hGap);
        int cRow =0;
        TitledPane cumulPane = new TitledPane("Cumulative Resource",cumulGrid);
        accordion.getPanes().add(cumulPane);

        cumulGrid.add(new Label("Nr Cumulative Resources:"), 0, cRow);
        nrCumulativeResourcesItem.setMajorTickUnit(1);
        nrCumulativeResourcesItem.setMinorTickCount(0);
        nrCumulativeResourcesItem.setShowTickMarks(true);
        nrCumulativeResourcesItem.setShowTickLabels(true);
        cumulGrid.add(nrCumulativeResourcesItem, 1, cRow++);
        nrCumulativeResourcesItem.setValue(((GenerateDataSolver)solver).getNrCumulativeResources());
//        GridPane.setColumnSpan( nrCumulativeResourcesItem, 3 );


        cumulGrid.add(new Label("Cumul Demand Range:"), 0, cRow);
        cumulDemandItem.setMajorTickUnit(1);
        cumulDemandItem.setMinorTickCount(0);
        cumulDemandItem.setShowTickMarks(true);
        cumulDemandItem.setShowTickLabels(true);
        cumulDemandItem.setSnapToTicks(true);
        cumulGrid.add(cumulDemandItem,1,cRow++);
        cumulDemandItem.setLowValue(((GenerateDataSolver)solver).getMinCumulDemand());
        cumulDemandItem.setHighValue(((GenerateDataSolver)solver).getMaxCumulDemand());
        GridPane.setColumnSpan( cumulDemandItem, 3 );


        cumulGrid.add(new Label("Profile Pieces:"), 0, cRow);
        profilePiecesItem.setMajorTickUnit(1);
        profilePiecesItem.setMinorTickCount(0);
        profilePiecesItem.setShowTickMarks(true);
        profilePiecesItem.setShowTickLabels(true);
        cumulGrid.add(profilePiecesItem, 1, cRow++);
        profilePiecesItem.setValue(((GenerateDataSolver)solver).getProfilePieces());

        cumulGrid.add(new Label("Cumul Capacity Range:"), 0, cRow);
        cumulCapacityItem.setMajorTickUnit(5);
        cumulCapacityItem.setMinorTickCount(4);
        cumulCapacityItem.setShowTickMarks(true);
        cumulCapacityItem.setShowTickLabels(true);
        cumulCapacityItem.setSnapToTicks(true);
        cumulCapacityItem.setPrefWidth(sliderWidth);
        cumulGrid.add(cumulCapacityItem,1,cRow++);
        cumulCapacityItem.setLowValue(((GenerateDataSolver)solver).getMinCumulCapacity());
        cumulCapacityItem.setHighValue(((GenerateDataSolver)solver).getMaxCumulCapacity());
        GridPane.setColumnSpan( cumulCapacityItem, 3 );

        GridPane orderGrid = new GridPane();
        orderGrid.setVgap(vGap);
        orderGrid.setHgap(hGap);
        int ordRow =0;
        TitledPane orderPane = new TitledPane("Orders",orderGrid);
        accordion.getPanes().add(orderPane);

        orderGrid.add(new Label("Nr Orders:"), 0, ordRow);
        orderGrid.add(nrOrdersItem, 1, ordRow++);
        nrOrdersItem.setText(String.format("%d",((GenerateDataSolver)solver).getNrOrders()));

        orderGrid.add(new Label("Qty Range:"), 0, ordRow);
        qtyItem.setMajorTickUnit(5);
        qtyItem.setMinorTickCount(4);
        qtyItem.setShowTickMarks(true);
        qtyItem.setShowTickLabels(true);
        qtyItem.setSnapToTicks(true);
        qtyItem.setPrefWidth(sliderWidth);
        orderGrid.add(qtyItem,1,ordRow++);
        qtyItem.setLowValue(((GenerateDataSolver)solver).getMinQty());
        qtyItem.setHighValue(((GenerateDataSolver)solver).getMaxQty());
        GridPane.setColumnSpan( qtyItem, 3 );

        GridPane wipGrid = new GridPane();
        wipGrid.setVgap(vGap);
        wipGrid.setHgap(hGap);
        int wRow =0;
        TitledPane wipPane = new TitledPane("WiP",wipGrid);
        accordion.getPanes().add(wipPane);

        wipGrid.add(new Label("WiP Probability:"), 0, wRow);
        wipProbabilityItem.setMajorTickUnit(0.1);
        wipProbabilityItem.setMinorTickCount(1);
        wipProbabilityItem.setShowTickMarks(true);
        wipProbabilityItem.setShowTickLabels(true);
//        wipProbabilityItem.setSnapToTicks(true);
        wipGrid.add(wipProbabilityItem, 1, wRow++);
        wipProbabilityItem.setValue(((GenerateDataSolver)solver).getWipProbability());
        GridPane.setColumnSpan( wipProbabilityItem, 3 );

        wipGrid.add(new Label("WiP Range:"), 0, wRow);
        wipItem.setMajorTickUnit(10);
        wipItem.setMinorTickCount(0);
        wipItem.setShowTickMarks(true);
        wipItem.setShowTickLabels(true);
//        wipItem.setSnapToTicks(true);
        wipItem.setPrefWidth(sliderWidth);
        wipGrid.add(wipItem,1,wRow++);
        wipItem.setLowValue(((GenerateDataSolver)solver).getMinWip());
        wipItem.setHighValue(((GenerateDataSolver)solver).getMaxWip());
        GridPane.setColumnSpan( wipItem, 3 );

        GridPane downGrid = new GridPane();
        downGrid.setVgap(vGap);
        downGrid.setHgap(hGap);
        int dRow =0;
        TitledPane downPane = new TitledPane("Downtime",downGrid);
        accordion.getPanes().add(downPane);

        downGrid.add(new Label("Downtime Probability:"), 0, dRow);
        downtimeProbabilityItem.setMajorTickUnit(0.1);
        downtimeProbabilityItem.setMinorTickCount(1);
        downtimeProbabilityItem.setShowTickMarks(true);
        downtimeProbabilityItem.setShowTickLabels(true);
//        downtimeProbabilityItem.setSnapToTicks(true);
        downGrid.add(downtimeProbabilityItem, 1, dRow++);
        downtimeProbabilityItem.setValue(((GenerateDataSolver)solver).getDowntimeProbability());
        GridPane.setColumnSpan( downtimeProbabilityItem, 3 );

        downGrid.add(new Label("Downtime Range:"), 0, dRow);
        downtimeItem.setMajorTickUnit(10);
        downtimeItem.setMinorTickCount(0);
        downtimeItem.setShowTickMarks(true);
        downtimeItem.setShowTickLabels(true);
//        downtimeItem.setSnapToTicks(true);
        downtimeItem.setPrefWidth(sliderWidth);
        downGrid.add(downtimeItem,1,dRow++);
        downtimeItem.setLowValue(((GenerateDataSolver)solver).getMinDowntime());
        downtimeItem.setHighValue(((GenerateDataSolver)solver).getMaxDowntime());
        GridPane.setColumnSpan( downtimeItem, 3 );

        GridPane otherGrid = new GridPane();
        otherGrid.setVgap(vGap);
        otherGrid.setHgap(hGap);
        int oRow =0;
        TitledPane otherPane = new TitledPane("Other Parameters",otherGrid);
        accordion.getPanes().add(otherPane);

        otherGrid.add(new Label("Earliest Due:"), 0, oRow);
        otherGrid.add(earliestDueItem, 1, oRow++);
        earliestDueItem.setText(String.format("%d",((GenerateDataSolver)solver).getEarliestDue()));

        otherGrid.add(new Label("Horizon Days:"), 0, oRow);
        otherGrid.add(horizonDaysItem, 1, oRow++);
        horizonDaysItem.setText(String.format("%d",((GenerateDataSolver)solver).getHorizonDays()));

        otherGrid.add(new Label("Time Resolution:"), 0, oRow);
        otherGrid.add(timeResolutionItem, 1, oRow++);
        timeResolutionItem.setText(String.format("%d",((GenerateDataSolver)solver).getTimeResolution()));

        otherGrid.add(new Label("Random Seed:"), 0, oRow);
        otherGrid.add(seedItem, 1, oRow++);
        seedItem.setText(String.format("%d",((GenerateDataSolver)solver).getSeed()));

        //??? pick the entry with the largest vertical extent to reserve enough space;
        //??? containing dialog box does not resize
        accordion.setExpandedPane(cumulPane);

//        pane.setGridLinesVisible(true); //??? debug only
        getDialogPane().setContent(pane);
        setTitle("Data Generator Parameters");
    }
    @Override
    public void handle(InputEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_RELEASED ||
                (event.getEventType() == KeyEvent.KEY_RELEASED &&
                        ((KeyEvent) event).getCode() == KeyCode.ENTER)) {
            info("Get ScheduleJobs parameters");
            String labelValue = labelItem.getText();
            String startDateValue = dateConverter.toString(startDateItem.getValue());
            info("Start time "+startTimeItem.getTime());
            String startTimeValue = timeConverter.toString(startTimeItem.getTime());
            info("as String "+startDateValue+" "+startTimeValue);
//            String startTimeValue = "13:45";

            String resourceModelValue = resourceModelItem.getValue().toString();
            int nrProductsValue = Integer.parseInt(nrProductsItem.getText());
            int minStagesValue = (int) Math.round(stagesItem.getLowValue());
            int maxStagesValue = (int) Math.round(stagesItem.getHighValue());
            int nrDisjunctiveResourcesValue = (int) Math.round(nrDisjunctiveResourcesItem.getValue());
            double resourceProbabilityValue = resourceProbabilityItem.getValue();

            String durationModelValue = durationModelItem.getValue().toString();
            int minDurationValue = (int) Math.round(durationItem.getLowValue());
            int maxDurationValue = (int) Math.round(durationItem.getHighValue());
            int durationFixedFactorValue = (int) Math.round(durationFixedFactorItem.getValue());

            int nrCumulativeResourcesValue = (int) Math.round(nrCumulativeResourcesItem.getValue());
            int minCumulDemandValue = (int) Math.round(cumulDemandItem.getLowValue());
            int maxCumulDemandValue = (int) Math.round(cumulDemandItem.getHighValue());
            int profilePiecesValue = (int) Math.round(profilePiecesItem.getValue());
            int minCumulCapacityValue = (int) Math.round(cumulCapacityItem.getLowValue());
            int maxCumulCapacityValue = (int) Math.round(cumulCapacityItem.getHighValue());

            int nrOrdersValue = Integer.parseInt(nrOrdersItem.getText());
            int minQtyValue = (int) Math.round(qtyItem.getLowValue());
            int maxQtyValue = (int) Math.round(qtyItem.getHighValue());

            double wipProbabilityValue = wipProbabilityItem.getValue();
            int minWipValue = (int) Math.round(wipItem.getLowValue());
            int maxWipValue = (int) Math.round(wipItem.getHighValue());

            double downtimeProbabilityValue = downtimeProbabilityItem.getValue();
            int minDowntimeValue = (int) Math.round(downtimeItem.getLowValue());
            int maxDowntimeValue = (int) Math.round(downtimeItem.getHighValue());

            int earliestDueValue = Integer.parseInt(earliestDueItem.getText());
            int horizonDaysValue = Integer.parseInt(horizonDaysItem.getText());
            int timeResolutionValue = Integer.parseInt(timeResolutionItem.getText());
            int seedValue = Integer.parseInt(seedItem.getText());

            ((GenerateDataSolver)getSolver())
                    .setLabel(labelValue)
                    .setResourceModel(resourceModelValue)
                    .setStartDate(startDateValue)
                    .setStartTime(startTimeValue)
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
                    .setProfilePieces(profilePiecesValue)
                    .setMinCumulCapacity(minCumulCapacityValue)
                    .setMaxCumulCapacity(maxCumulCapacityValue)
                    .setNrOrders(nrOrdersValue)
                    .setMinQty(minQtyValue)
                    .setMaxQty(maxQtyValue)

                    .setWipProbability(wipProbabilityValue)
                    .setMinWip(minWipValue)
                    .setMaxWip(maxWipValue)
                    .setDowntimeProbability(downtimeProbabilityValue)
                    .setMinDowntime(minDowntimeValue)
                    .setMaxDowntime(maxDowntimeValue)

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

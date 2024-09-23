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
   private TextField solverTypeItem = new TextField();
   private IntegerTextField timeoutItem = new IntegerTextField();
   private IntegerTextField nrThreadsItem = new IntegerTextField();
   private IntegerTextField timeResolutionItem = new IntegerTextField();
   private IntegerTextField seedItem = new IntegerTextField();

    public ScheduleJobsDialogBox(GeneratedJfxApp app, Scenario base,AbstractSolver solver){
        super(app, base, solver);
        GridPane pane = new GridPane();
        pane.setVgap(10.0);
        pane.setHgap(10.0);
        int row = 0;
        pane.add(new Label("Label:"), 0, row);
        pane.add(labelItem, 1, row++);
        labelItem.setText(((ScheduleJobsSolver)solver).getLabel());
        pane.add(new Label("Solver Type:"), 0, row);
        pane.add(solverTypeItem, 1, row++);
        solverTypeItem.setText(((ScheduleJobsSolver)solver).getSolverType());
        pane.add(new Label("Timeout (s):"), 0, row);
        pane.add(timeoutItem, 1, row++);
        timeoutItem.setText(String.format("%d",((ScheduleJobsSolver)solver).getTimeout()));
        pane.add(new Label("Nr Threads:"), 0, row);
        pane.add(nrThreadsItem, 1, row++);
        nrThreadsItem.setText(String.format("%d",((ScheduleJobsSolver)solver).getNrThreads()));
        pane.add(new Label("Time Resolution (min):"), 0, row);
        pane.add(timeResolutionItem, 1, row++);
        timeResolutionItem.setText(String.format("%d",((ScheduleJobsSolver)solver).getTimeResolution()));
        pane.add(new Label("Random Seed:"), 0, row);
        pane.add(seedItem, 1, row++);
        seedItem.setText(String.format("%d",((ScheduleJobsSolver)solver).getSeed()));
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
        String solverTypeValue = solverTypeItem.getText();
        int timeoutValue = Integer.parseInt(timeoutItem.getText());
        int nrThreadsValue = Integer.parseInt(nrThreadsItem.getText());
        int timeResolutionValue = Integer.parseInt(timeResolutionItem.getText());
        int seedValue = Integer.parseInt(seedItem.getText());
        ((ScheduleJobsSolver)getSolver())
            .setLabel(labelValue)
            .setSolverType(solverTypeValue)
            .setTimeout(timeoutValue)
            .setNrThreads(nrThreadsValue)
            .setTimeResolution(timeResolutionValue)
            .setSeed(seedValue)
            ;
        super.handle(event);
    }
}
}

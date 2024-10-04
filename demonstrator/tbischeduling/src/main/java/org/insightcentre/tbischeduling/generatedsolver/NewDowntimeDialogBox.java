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

public class NewDowntimeDialogBox extends GeneralDialogBox{
   private TextField disjunctiveResourceItem = new TextField();
   private IntegerTextField startItem = new IntegerTextField();
   private IntegerTextField endItem = new IntegerTextField();

    public NewDowntimeDialogBox(GeneratedJfxApp app, Scenario base,AbstractSolver solver){
        super(app, base, solver);
        GridPane pane = new GridPane();
        pane.setVgap(10.0);
        pane.setHgap(10.0);
        int row = 0;
        pane.add(new Label("Disjunctive Resource:"), 0, row);
        pane.add(disjunctiveResourceItem, 1, row++);
        disjunctiveResourceItem.setText(((NewDowntimeSolver)solver).getDisjunctiveResource());
        pane.add(new Label("Start:"), 0, row);
        pane.add(startItem, 1, row++);
        startItem.setText(String.format("%d",((NewDowntimeSolver)solver).getStart()));
        pane.add(new Label("End:"), 0, row);
        pane.add(endItem, 1, row++);
        endItem.setText(String.format("%d",((NewDowntimeSolver)solver).getEnd()));
        getDialogPane().setContent(pane);
        setTitle("NewDowntime Solver Parameters");
    }
@Override
public void handle(InputEvent event) {
    if (event.getEventType() == MouseEvent.MOUSE_RELEASED ||
      (event.getEventType() == KeyEvent.KEY_RELEASED &&
      ((KeyEvent) event).getCode() == KeyCode.ENTER)) {
        info("Get NewDowntime parameters");
        String disjunctiveResourceValue = disjunctiveResourceItem.getText();
        int startValue = Integer.parseInt(startItem.getText());
        int endValue = Integer.parseInt(endItem.getText());
        ((NewDowntimeSolver)getSolver())
            .setDisjunctiveResource(disjunctiveResourceValue)
            .setStart(startValue)
            .setEnd(endValue)
            ;
        super.handle(event);
    }
}
}

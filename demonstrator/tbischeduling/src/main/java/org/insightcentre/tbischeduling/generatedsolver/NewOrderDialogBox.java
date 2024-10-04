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

public class NewOrderDialogBox extends GeneralDialogBox{
   private TextField productItem = new TextField();
   private IntegerTextField qtyItem = new IntegerTextField();
   private IntegerTextField releaseItem = new IntegerTextField();
   private IntegerTextField dueItem = new IntegerTextField();
   private DoubleTextField latenessWeightItem = new DoubleTextField();
   private DoubleTextField earlinessWeightItem = new DoubleTextField();

    public NewOrderDialogBox(GeneratedJfxApp app, Scenario base,AbstractSolver solver){
        super(app, base, solver);
        GridPane pane = new GridPane();
        pane.setVgap(10.0);
        pane.setHgap(10.0);
        int row = 0;
        pane.add(new Label("Product:"), 0, row);
        pane.add(productItem, 1, row++);
        productItem.setText(((NewOrderSolver)solver).getProduct());
        pane.add(new Label("Quantity:"), 0, row);
        pane.add(qtyItem, 1, row++);
        qtyItem.setText(String.format("%d",((NewOrderSolver)solver).getQty()));
        pane.add(new Label("Release:"), 0, row);
        pane.add(releaseItem, 1, row++);
        releaseItem.setText(String.format("%d",((NewOrderSolver)solver).getRelease()));
        pane.add(new Label("Due:"), 0, row);
        pane.add(dueItem, 1, row++);
        dueItem.setText(String.format("%d",((NewOrderSolver)solver).getDue()));
        pane.add(new Label("Lateness Weight:"), 0, row);
        pane.add(latenessWeightItem, 1, row++);
        latenessWeightItem.setText(String.format("%f",((NewOrderSolver)solver).getLatenessWeight()));
        pane.add(new Label("Earliness Weight:"), 0, row);
        pane.add(earlinessWeightItem, 1, row++);
        earlinessWeightItem.setText(String.format("%f",((NewOrderSolver)solver).getEarlinessWeight()));
        getDialogPane().setContent(pane);
        setTitle("NewOrder Solver Parameters");
    }
@Override
public void handle(InputEvent event) {
    if (event.getEventType() == MouseEvent.MOUSE_RELEASED ||
      (event.getEventType() == KeyEvent.KEY_RELEASED &&
      ((KeyEvent) event).getCode() == KeyCode.ENTER)) {
        info("Get NewOrder parameters");
        String productValue = productItem.getText();
        int qtyValue = Integer.parseInt(qtyItem.getText());
        int releaseValue = Integer.parseInt(releaseItem.getText());
        int dueValue = Integer.parseInt(dueItem.getText());
        double latenessWeightValue = Double.parseDouble(latenessWeightItem.getText());
        double earlinessWeightValue = Double.parseDouble(earlinessWeightItem.getText());
        ((NewOrderSolver)getSolver())
            .setProduct(productValue)
            .setQty(qtyValue)
            .setRelease(releaseValue)
            .setDue(dueValue)
            .setLatenessWeight(latenessWeightValue)
            .setEarlinessWeight(earlinessWeightValue)
            ;
        super.handle(event);
    }
}
}

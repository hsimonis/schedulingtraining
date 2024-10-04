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

public class NewOrderDialogBoxImpl extends GeneralDialogBox {
    private final ChoiceBox productItem = new ChoiceBox();
    private final IntegerTextField qtyItem = new IntegerTextField();
    private final IntegerTextField releaseItem = new IntegerTextField();
    private final IntegerTextField dueItem = new IntegerTextField();
    private final DoubleTextField earlinessWeightItem = new DoubleTextField();
    private final DoubleTextField latenessWeightItem = new DoubleTextField();

    private final String pattern = "d-M-yyyy";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    LocalDateStringConverter dateConverter = new LocalDateStringConverter(formatter,formatter);

    public NewOrderDialogBoxImpl(GeneratedJfxApp app, Scenario base, AbstractSolver solver){
        super(app, base, solver);
        GridPane pane = new GridPane();
        pane.setVgap(10.0);
        pane.setHgap(10.0);
        int row = 0;

        pane.add(new Label("Product:"), 0, row);
        pane.add(productItem, 1, row++);
        productItem.getItems().addAll(base.getListProduct().stream().map(ApplicationObject::getName).toList());
        productItem.setValue(((NewOrderSolver)solver).getProduct());

        pane.add(new Label("Qty:"), 0, row);
        pane.add(qtyItem, 1, row++);
        qtyItem.setText(String.format("%d",((NewOrderSolver)solver).getQty()));

        Separator sep1 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep1,0,row++,2,1);

        pane.add(new Label("Release:"), 0, row);
        pane.add(releaseItem, 1, row++);
        releaseItem.setText(String.format("%d",((NewOrderSolver)solver).getRelease()));

        pane.add(new Label("Due:"), 0, row);
        pane.add(dueItem, 1, row++);
        dueItem.setText(String.format("%d",((NewOrderSolver)solver).getDue()));

        Separator sep2 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep2,0,row++,2,1);

        pane.add(new Label("Earliness Weight:"), 0, row);
        pane.add(earlinessWeightItem, 1, row++);
        earlinessWeightItem.setText(String.format("%5.2f",((NewOrderSolver)solver).getEarlinessWeight()));

        pane.add(new Label("Lateness Weight:"), 0, row);
        pane.add(latenessWeightItem, 1, row++);
        latenessWeightItem.setText(String.format("%5.2f",((NewOrderSolver)solver).getLatenessWeight()));


        getDialogPane().setContent(pane);
        setTitle("New Order");
    }
    @Override
    public void handle(InputEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_RELEASED ||
                (event.getEventType() == KeyEvent.KEY_RELEASED &&
                        ((KeyEvent) event).getCode() == KeyCode.ENTER)) {
            info("Get NewOrder parameters");

            String productValue = productItem.getValue().toString();
            int qtyValue = Integer.parseInt(qtyItem.getText());
            int releaseValue = Integer.parseInt(releaseItem.getText());
            int dueValue = Integer.parseInt(dueItem.getText());
            double earlinessWeightValue = Double.parseDouble(earlinessWeightItem.getText());
            double latenessWeightValue = Double.parseDouble(latenessWeightItem.getText());

            ((NewOrderSolver)getSolver())
                    .setProduct(productValue)
                    .setQty(qtyValue)
                    .setRelease(releaseValue)
                    .setDue(dueValue)
                    .setEarlinessWeight(earlinessWeightValue)
                    .setLatenessWeight(latenessWeightValue)
            ;
            super.handle(event);
        } else {
//            info("event "+event.getEventType());
        }
    }
}

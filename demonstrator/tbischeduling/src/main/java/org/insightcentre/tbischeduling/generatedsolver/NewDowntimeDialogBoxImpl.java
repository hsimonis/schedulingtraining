package org.insightcentre.tbischeduling.generatedsolver;

import framework.solver.AbstractSolver;
import javafx.geometry.Orientation;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.converter.LocalDateStringConverter;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.ApplicationObject;
import org.insightcentre.tbischeduling.datamodel.DisjunctiveResource;
import org.insightcentre.tbischeduling.datamodel.Scenario;

import java.time.format.DateTimeFormatter;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;

public class NewDowntimeDialogBoxImpl extends GeneralDialogBox {
    private final ChoiceBox disjunctiveResourceItem = new ChoiceBox();
    private final IntegerTextField startItem = new IntegerTextField();
    private final IntegerTextField endItem = new IntegerTextField();

    private final String pattern = "d-M-yyyy";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    LocalDateStringConverter dateConverter = new LocalDateStringConverter(formatter,formatter);

    public NewDowntimeDialogBoxImpl(GeneratedJfxApp app, Scenario base, AbstractSolver solver){
        super(app, base, solver);
        GridPane pane = new GridPane();
        pane.setVgap(10.0);
        pane.setHgap(10.0);
        int row = 0;

        pane.add(new Label("Disjunctive Resource:"), 0, row);
        pane.add(disjunctiveResourceItem, 1, row++);
        disjunctiveResourceItem.getItems().addAll(base.getListDisjunctiveResource().stream().map(ApplicationObject::getName).toList());
//        disjunctiveResourceItem.setValue(((NewDowntimeSolver)solver).getDisjunctiveResource());
        disjunctiveResourceItem.setValue(DisjunctiveResource.findFirst(base).getName());

        Separator sep1 = new Separator(Orientation.HORIZONTAL);
        pane.add(sep1,0,row++,2,1);

        pane.add(new Label("Start:"), 0, row);
        pane.add(startItem, 1, row++);
        startItem.setText(String.format("%d",((NewDowntimeSolver)solver).getStart()));

        pane.add(new Label("End:"), 0, row);
        pane.add(endItem, 1, row++);
        endItem.setText(String.format("%d",((NewDowntimeSolver)solver).getEnd()));



        getDialogPane().setContent(pane);
        setTitle("New Downtime");
    }
    @Override
    public void handle(InputEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_RELEASED ||
                (event.getEventType() == KeyEvent.KEY_RELEASED &&
                        ((KeyEvent) event).getCode() == KeyCode.ENTER)) {
            info("Get NewOrder parameters");

            String disjunctiveResourceValue = disjunctiveResourceItem.getValue().toString();
            int startValue = Integer.parseInt(startItem.getText());
            int endValue = Integer.parseInt(endItem.getText());

            ((NewDowntimeSolver)getSolver())
                    .setDisjunctiveResource(disjunctiveResourceValue)
                    .setStart(startValue)
                    .setEnd(endValue)
            ;
            super.handle(event);
        } else {
//            info("event "+event.getEventType());
        }
    }
}

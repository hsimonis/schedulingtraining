package org.insightcentre.tbischeduling.controller.custom;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import org.insightcentre.tbischeduling.controller.ChartController;
import org.insightcentre.tbischeduling.datamodel.ApplicationObject;
import org.insightcentre.tbischeduling.datamodel.Solution;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class JJAbstractChartController extends ChartController {
    protected void setChoices(ChoiceBox<String> box, ObservableList<String> choices) {
        box.getSelectionModel().clearSelection();
        box.getItems().clear();
        box.getItems().addAll(choices);
        box.getSelectionModel().selectLast();
    }

    protected List<String> solutions() {
        return this.mainApp.getSolutionData().stream().
                map(ApplicationObject::getName).
                toList();
    }

}

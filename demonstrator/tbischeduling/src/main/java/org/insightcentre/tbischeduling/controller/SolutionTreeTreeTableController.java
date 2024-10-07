package org.insightcentre.tbischeduling.controller;

import org.insightcentre.tbischeduling.datamodel.SolutionHierarchy;
import org.insightcentre.tbischeduling.datamodel.Solution;
import org.insightcentre.tbischeduling.datamodel.JobAssignment;
import org.insightcentre.tbischeduling.datamodel.TaskAssignment;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import framework.gui.Table3Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.control.TreeItem;
import framework.gui.AbstractJfxMainWindow;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Generated  **/
public class SolutionTreeTreeTableController extends Table3Controller {

	@FXML
	private TreeTableView<SolutionHierarchy> table;

	@FXML
private TreeTableColumn<SolutionHierarchy,String> name;
	@FXML
private TreeTableColumn<SolutionHierarchy,Integer> start;
	@FXML
private TreeTableColumn<SolutionHierarchy,Integer> end;
	@FXML
private TreeTableColumn<SolutionHierarchy,Integer> duration;
	private GeneratedJfxApp mainApp;
public TreeTableView<SolutionHierarchy> getTable() {
    return table;
}
@Override
public void setMainApp(AbstractJfxMainWindow app) {
    mainApp = (GeneratedJfxApp) app;
    table.setShowRoot(false);
    table.setTableMenuButtonVisible(true);
    table.setRoot(computeRoot(mainApp));
}
private TreeItem<SolutionHierarchy> computeRoot(GeneratedJfxApp mainApp) {
    TreeItem<SolutionHierarchy> item0 = new TreeItem<>();
    for(Solution level1:mainApp.getSolutionData()){
        TreeItem<SolutionHierarchy> item1 = new TreeItem<>(level1);
        item0.getChildren().add(item1);
    for(JobAssignment level2:mainApp.getJobAssignmentData()){
        if (level2.getSolution() == level1) {
        TreeItem<SolutionHierarchy> item2 = new TreeItem<>(level2);
        item1.getChildren().add(item2);
    for(TaskAssignment level3:mainApp.getTaskAssignmentData()){
        if (level3.getJobAssignment() == level2) {
        TreeItem<SolutionHierarchy> item3 = new TreeItem<>(level3);
        item2.getChildren().add(item3);
    }
    }
    }
    }
    }
    item0.setExpanded(true);
    return item0;
}
@FXML
private void initialize() {
    ObservableList<String> choices = FXCollections.observableArrayList();
    choices.add("name");
    name.setCellValueFactory(new TreeItemPropertyValueFactory<SolutionHierarchy,String>("name"));
    name.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
    choices.add("start");
    start.setCellValueFactory(new TreeItemPropertyValueFactory<SolutionHierarchy,Integer>("start"));
    start.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(INTEGER_CONVERTER));
    choices.add("end");
    end.setCellValueFactory(new TreeItemPropertyValueFactory<SolutionHierarchy,Integer>("end"));
    end.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(INTEGER_CONVERTER));
    choices.add("duration");
    duration.setCellValueFactory(new TreeItemPropertyValueFactory<SolutionHierarchy,Integer>("duration"));
    duration.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(INTEGER_CONVERTER));
  }

@Override
public void filter(String attribute, String comparison, String text){
}
}

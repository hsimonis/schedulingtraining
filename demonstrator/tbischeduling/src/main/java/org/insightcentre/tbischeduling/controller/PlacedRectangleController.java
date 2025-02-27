package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.Table3Controller;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.reflect.Field;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.CumulativeResource;
import org.insightcentre.tbischeduling.datamodel.PlacedRectangle;
import org.insightcentre.tbischeduling.datamodel.TaskAssignment;

/**
 * Generated at 10:19:39 on 2025-02-26 */
public class PlacedRectangleController extends Table3Controller {
	@FXML
	private TableView<PlacedRectangle> table;

	@FXML
	private TableColumn<PlacedRectangle, String> name;

	@FXML
	private TableColumn<PlacedRectangle, CumulativeResource> cumulativeResource;

	@FXML
	private TableColumn<PlacedRectangle, TaskAssignment> taskAssignment;

	@FXML
	private TableColumn<PlacedRectangle, Integer> x;

	@FXML
	private TableColumn<PlacedRectangle, Integer> y;

	@FXML
	private TableColumn<PlacedRectangle, Integer> w;

	@FXML
	private TableColumn<PlacedRectangle, Integer> h;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getPlacedRectangleData());
		cumulativeResource.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getCumulativeResourceData()));
		cumulativeResource.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setCumulativeResource(event.getNewValue()); mainApp.reset();});
		taskAssignment.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getTaskAssignmentData()));
		taskAssignment.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setTaskAssignment(event.getNewValue()); mainApp.reset();});
	}

	public TableView<PlacedRectangle> getTable() {
		return table;
	}

	@FXML
	private void initialize() {
		table.setTableMenuButtonVisible(true);
		table.setOnMouseClicked(event -> {if (event.isControlDown()) {mainApp.showObject(table.getFocusModel().getFocusedItem());}});
		ObservableList<String> choices = FXCollections.observableArrayList();
		choices.add("name");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setName(event.getNewValue()); mainApp.reset();});
		choices.add("cumulativeResource");
		cumulativeResource.setCellValueFactory(new PropertyValueFactory<>("cumulativeResource"));
		choices.add("taskAssignment");
		taskAssignment.setCellValueFactory(new PropertyValueFactory<>("taskAssignment"));
		choices.add("x");
		x.setCellValueFactory(new PropertyValueFactory<>("x"));
		x.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		x.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setX(event.getNewValue()); mainApp.reset();});
		choices.add("y");
		y.setCellValueFactory(new PropertyValueFactory<>("y"));
		y.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		y.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setY(event.getNewValue()); mainApp.reset();});
		choices.add("w");
		w.setCellValueFactory(new PropertyValueFactory<>("w"));
		w.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		w.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setW(event.getNewValue()); mainApp.reset();});
		choices.add("h");
		h.setCellValueFactory(new PropertyValueFactory<>("h"));
		h.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		h.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setH(event.getNewValue()); mainApp.reset();});
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getPlacedRectangleData());
		try {
			ObservableList<PlacedRectangle> filteredItems = FXCollections.observableArrayList();
			for (PlacedRectangle item : table.getItems()) {
				String[] fields = attribute.split("\\.");
				Field f = null;
				Object obj = item;
				for (int i = 0; i < fields.length; i++) {
					f = obj.getClass().getField(fields[i]);
					obj = f.get(obj);
				}
				if (obj instanceof Integer && (comparison.equals("less than")||comparison.equals("greater than"))) {
					Integer value = (Integer) obj;
					int comp;
					try {
						comp = Integer.parseInt(text);
					}
					catch (NumberFormatException e) {
						comp = 0;
					}
					if ((comparison.equals("less than") && value < comp) ||(comparison.equals("greater than") && value > comp)) {
						filteredItems.add(item);
					}
				}
				else if (obj instanceof Double && (comparison.equals("less than")||comparison.equals("greater than"))) {
					Double value = (Double) obj;;
					double comp;;
					try {
						comp = Double.parseDouble(text);
					}
					catch (NumberFormatException e) {
						comp = 0.0;
					}
					if ((comparison.equals("less than") && value < comp) ||	(comparison.equals("greater than") && value > comp)) {
						filteredItems.add(item);
					}
				}
				else {
					String value = (obj==null?null:obj.toString());
					if (value != null && ((comparison.equals("equals") && value.equals(text)) ||(comparison.equals("not equals") && !value.equals(text)) ||(comparison.equals("contains") && value.contains(text)) ||(comparison.equals("matches") && value.matches(text)) ||(comparison.equals("starts with") && value.startsWith(text)) ||(comparison.equals("ends with") && value.endsWith(text)) ||(comparison.equals("less than") && value.compareTo(text) < 0) ||(comparison.equals("greater than") && value.compareTo(text) > 0))) {
						filteredItems.add(item);
					}
				}
			}
			table.setItems(filteredItems);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

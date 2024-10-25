package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.DateTimePickerTableCell;
import framework.gui.Table3Controller;
import framework.types.DateTime;
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
import org.insightcentre.tbischeduling.datamodel.DisjunctiveResource;
import org.insightcentre.tbischeduling.datamodel.WiP;

/**
 * Generated at 20:37:39 on 2024-10-24 */
public class WiPController extends Table3Controller {
	@FXML
	private TableView<WiP> table;

	@FXML
	private TableColumn<WiP, String> name;

	@FXML
	private TableColumn<WiP, DisjunctiveResource> disjunctiveResource;

	@FXML
	private TableColumn<WiP, Integer> duration;

	@FXML
	private TableColumn<WiP, Integer> start;

	@FXML
	private TableColumn<WiP, Integer> end;

	@FXML
	private TableColumn<WiP, DateTime> startDate;

	@FXML
	private TableColumn<WiP, DateTime> endDate;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getWiPData());
		disjunctiveResource.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getDisjunctiveResourceData()));
		disjunctiveResource.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDisjunctiveResource(event.getNewValue()); mainApp.reset();});
	}

	public TableView<WiP> getTable() {
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
		choices.add("disjunctiveResource");
		disjunctiveResource.setCellValueFactory(new PropertyValueFactory<>("disjunctiveResource"));
		choices.add("duration");
		duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		duration.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		duration.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDuration(event.getNewValue()); mainApp.reset();});
		choices.add("start");
		start.setCellValueFactory(new PropertyValueFactory<>("start"));
		start.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		start.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setStart(event.getNewValue()); mainApp.reset();});
		choices.add("end");
		end.setCellValueFactory(new PropertyValueFactory<>("end"));
		end.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		end.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setEnd(event.getNewValue()); mainApp.reset();});
		choices.add("startDate");
		startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		startDate.setCellFactory(DateTimePickerTableCell.forTableColumn(DATETIME_CONVERTER));
		startDate.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setStartDate(event.getNewValue()); mainApp.reset();});
		choices.add("endDate");
		endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		endDate.setCellFactory(DateTimePickerTableCell.forTableColumn(DATETIME_CONVERTER));
		endDate.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setEndDate(event.getNewValue()); mainApp.reset();});
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getWiPData());
		try {
			ObservableList<WiP> filteredItems = FXCollections.observableArrayList();
			for (WiP item : table.getItems()) {
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

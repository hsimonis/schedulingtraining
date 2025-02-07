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
import org.insightcentre.tbischeduling.datamodel.CumulativeLowerBound;
import org.insightcentre.tbischeduling.datamodel.CumulativeResource;

/**
 * Generated at 10:43:45 on 2025-02-06 */
public class CumulativeLowerBoundController extends Table3Controller {
	@FXML
	private TableView<CumulativeLowerBound> table;

	@FXML
	private TableColumn<CumulativeLowerBound, String> name;

	@FXML
	private TableColumn<CumulativeLowerBound, Integer> value;

	@FXML
	private TableColumn<CumulativeLowerBound, String> description;

	@FXML
	private TableColumn<CumulativeLowerBound, CumulativeResource> cumulativeResource;

	@FXML
	private TableColumn<CumulativeLowerBound, Integer> maxCapacity;

	@FXML
	private TableColumn<CumulativeLowerBound, Integer> totalDemand;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getCumulativeLowerBoundData());
		cumulativeResource.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getCumulativeResourceData()));
		cumulativeResource.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setCumulativeResource(event.getNewValue()); mainApp.reset();});
	}

	public TableView<CumulativeLowerBound> getTable() {
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
		choices.add("value");
		value.setCellValueFactory(new PropertyValueFactory<>("value"));
		value.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		value.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setValue(event.getNewValue()); mainApp.reset();});
		choices.add("description");
		description.setCellValueFactory(new PropertyValueFactory<>("description"));
		description.setCellFactory(TextFieldTableCell.forTableColumn());
		description.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDescription(event.getNewValue()); mainApp.reset();});
		choices.add("cumulativeResource");
		cumulativeResource.setCellValueFactory(new PropertyValueFactory<>("cumulativeResource"));
		choices.add("maxCapacity");
		maxCapacity.setCellValueFactory(new PropertyValueFactory<>("maxCapacity"));
		maxCapacity.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		maxCapacity.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMaxCapacity(event.getNewValue()); mainApp.reset();});
		choices.add("totalDemand");
		totalDemand.setCellValueFactory(new PropertyValueFactory<>("totalDemand"));
		totalDemand.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		totalDemand.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setTotalDemand(event.getNewValue()); mainApp.reset();});
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getCumulativeLowerBoundData());
		try {
			ObservableList<CumulativeLowerBound> filteredItems = FXCollections.observableArrayList();
			for (CumulativeLowerBound item : table.getItems()) {
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

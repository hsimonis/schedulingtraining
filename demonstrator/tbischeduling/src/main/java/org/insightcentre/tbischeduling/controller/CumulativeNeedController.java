package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.Table3Controller;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.reflect.Field;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.CumulativeNeed;
import org.insightcentre.tbischeduling.datamodel.CumulativeResource;
import org.insightcentre.tbischeduling.datamodel.ProcessStep;

/**
 * Generated at 12:39:47 on 2024-11-13 */
public class CumulativeNeedController extends Table3Controller {
	@FXML
	private TableView<CumulativeNeed> table;

	@FXML
	private TableColumn<CumulativeNeed, String> name;

	@FXML
	private TableColumn<CumulativeNeed, CumulativeResource> cumulativeResource;

	@FXML
	private TableColumn<CumulativeNeed, ProcessStep> processStep;

	@FXML
	private TableColumn<CumulativeNeed, Integer> demand;

	@FXML
	private TableColumn<CumulativeNeed, Integer> durationFixed;

	@FXML
	private TableColumn<CumulativeNeed, Integer> durationPerUnit;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getCumulativeNeedData());
		cumulativeResource.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getCumulativeResourceData()));
		cumulativeResource.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setCumulativeResource(event.getNewValue()); mainApp.reset();});
		processStep.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getProcessStepData()));
		processStep.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setProcessStep(event.getNewValue()); mainApp.reset();});
	}

	public TableView<CumulativeNeed> getTable() {
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
		choices.add("processStep");
		processStep.setCellValueFactory(new PropertyValueFactory<>("processStep"));
		choices.add("demand");
		demand.setCellValueFactory(new PropertyValueFactory<>("demand"));
		demand.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		demand.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDemand(event.getNewValue()); mainApp.reset();});
		choices.add("processStep.durationFixed");
		try {
			durationFixed.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProcessStep().getDurationFixed()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("processStep.durationPerUnit");
		try {
			durationPerUnit.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProcessStep().getDurationPerUnit()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getCumulativeNeedData());
		try {
			ObservableList<CumulativeNeed> filteredItems = FXCollections.observableArrayList();
			for (CumulativeNeed item : table.getItems()) {
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

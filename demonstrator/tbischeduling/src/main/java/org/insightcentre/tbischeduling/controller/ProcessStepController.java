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
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.datamodel.ProcessStep;

/**
 * Generated at 10:29:29 on 2024-09-28 */
public class ProcessStepController extends Table3Controller {
	@FXML
	private TableView<ProcessStep> table;

	@FXML
	private TableColumn<ProcessStep, String> name;

	@FXML
	private TableColumn<ProcessStep, Process> process;

	@FXML
	private TableColumn<ProcessStep, Integer> durationFixed;

	@FXML
	private TableColumn<ProcessStep, Integer> durationPerUnit;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getProcessStepData());
		process.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getProcessData()));
		process.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setProcess(event.getNewValue()); mainApp.reset();});
	}

	public TableView<ProcessStep> getTable() {
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
		choices.add("process");
		process.setCellValueFactory(new PropertyValueFactory<>("process"));
		choices.add("durationFixed");
		durationFixed.setCellValueFactory(new PropertyValueFactory<>("durationFixed"));
		durationFixed.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		durationFixed.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDurationFixed(event.getNewValue()); mainApp.reset();});
		choices.add("durationPerUnit");
		durationPerUnit.setCellValueFactory(new PropertyValueFactory<>("durationPerUnit"));
		durationPerUnit.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		durationPerUnit.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDurationPerUnit(event.getNewValue()); mainApp.reset();});
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getProcessStepData());
		try {
			ObservableList<ProcessStep> filteredItems = FXCollections.observableArrayList();
			for (ProcessStep item : table.getItems()) {
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

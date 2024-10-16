package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.Table3Controller;
import java.lang.Exception;
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
import org.insightcentre.tbischeduling.datamodel.Severity;
import org.insightcentre.tbischeduling.datamodel.Solution;
import org.insightcentre.tbischeduling.datamodel.SolutionError;

/**
 * Generated at 07:10:18 on 2024-10-16 */
public class SolutionErrorController extends Table3Controller {
	@FXML
	private TableView<SolutionError> table;

	@FXML
	private TableColumn<SolutionError, String> name;

	@FXML
	private TableColumn<SolutionError, Solution> solution;

	@FXML
	private TableColumn<SolutionError, String> classDesc;

	@FXML
	private TableColumn<SolutionError, String> item;

	@FXML
	private TableColumn<SolutionError, String> field;

	@FXML
	private TableColumn<SolutionError, String> value;

	@FXML
	private TableColumn<SolutionError, String> description;

	@FXML
	private TableColumn<SolutionError, Severity> severity;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getSolutionErrorData());
		solution.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getSolutionData()));
		solution.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSolution(event.getNewValue()); mainApp.reset();});
		ObservableList<Severity> severityValues = FXCollections.observableArrayList(Severity.values());
		severity.setCellFactory(ComboBoxTableCell.forTableColumn(severityValues));
		severity.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSeverity(event.getNewValue()); mainApp.reset();});
	}

	public TableView<SolutionError> getTable() {
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
		choices.add("solution");
		solution.setCellValueFactory(new PropertyValueFactory<>("solution"));
		choices.add("classDesc");
		classDesc.setCellValueFactory(new PropertyValueFactory<>("classDesc"));
		classDesc.setCellFactory(TextFieldTableCell.forTableColumn());
		classDesc.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setClassDesc(event.getNewValue()); mainApp.reset();});
		choices.add("item");
		item.setCellValueFactory(new PropertyValueFactory<>("item"));
		item.setCellFactory(TextFieldTableCell.forTableColumn());
		item.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setItem(event.getNewValue()); mainApp.reset();});
		choices.add("field");
		field.setCellValueFactory(new PropertyValueFactory<>("field"));
		field.setCellFactory(TextFieldTableCell.forTableColumn());
		field.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setField(event.getNewValue()); mainApp.reset();});
		choices.add("value");
		value.setCellValueFactory(new PropertyValueFactory<>("value"));
		value.setCellFactory(TextFieldTableCell.forTableColumn());
		value.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setValue(event.getNewValue()); mainApp.reset();});
		choices.add("description");
		description.setCellValueFactory(new PropertyValueFactory<>("description"));
		description.setCellFactory(TextFieldTableCell.forTableColumn());
		description.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDescription(event.getNewValue()); mainApp.reset();});
		choices.add("severity");
		severity.setCellValueFactory(new PropertyValueFactory<>("severity"));
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getSolutionErrorData());
		try {
			ObservableList<SolutionError> filteredItems = FXCollections.observableArrayList();
			for (SolutionError item : table.getItems()) {
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

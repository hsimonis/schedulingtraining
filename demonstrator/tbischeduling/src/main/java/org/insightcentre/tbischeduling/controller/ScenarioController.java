package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.DateTimePickerTableCell;
import framework.gui.Table3Controller;
import framework.types.DateTime;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.reflect.Field;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.DataGeneratorProperty;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.SolverProperty;

/**
 * Generated at 05:23:33 on 2024-10-13 */
public class ScenarioController extends Table3Controller {
	@FXML
	private TableView<Scenario> table;

	@FXML
	private TableColumn<Scenario, String> name;

	@FXML
	private TableColumn<Scenario, Boolean> dirty;

	@FXML
	private TableColumn<Scenario, Boolean> valid;

	@FXML
	private TableColumn<Scenario, Double> dataFileVersionNumber;

	@FXML
	private TableColumn<Scenario, String> dataFile;

	@FXML
	private TableColumn<Scenario, DateTime> startDateTime;

	@FXML
	private TableColumn<Scenario, Integer> horizon;

	@FXML
	private TableColumn<Scenario, Integer> timeResolution;

	@FXML
	private TableColumn<Scenario, Integer> ganttWidth;

	@FXML
	private TableColumn<Scenario, Integer> ganttLinesPerPage;

	@FXML
	private TableColumn<Scenario, Double> ganttLineHeight;

	@FXML
	private TableColumn<Scenario, SolverProperty> solverProperty;

	@FXML
	private TableColumn<Scenario, DataGeneratorProperty> dataGeneratorProperty;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getScenarioData());
		solverProperty.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getSolverPropertyData()));
		solverProperty.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSolverProperty(event.getNewValue()); mainApp.reset();});
		dataGeneratorProperty.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getDataGeneratorPropertyData()));
		dataGeneratorProperty.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDataGeneratorProperty(event.getNewValue()); mainApp.reset();});
	}

	public TableView<Scenario> getTable() {
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
		choices.add("dirty");
		dirty.setCellValueFactory(new DirtyCallback());
		dirty.setCellFactory(CheckBoxTableCell.forTableColumn(dirty));
		choices.add("valid");
		valid.setCellValueFactory(new ValidCallback());
		valid.setCellFactory(CheckBoxTableCell.forTableColumn(valid));
		choices.add("dataFileVersionNumber");
		dataFileVersionNumber.setCellValueFactory(new PropertyValueFactory<>("dataFileVersionNumber"));
		dataFileVersionNumber.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		dataFileVersionNumber.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDataFileVersionNumber(event.getNewValue()); mainApp.reset();});
		choices.add("dataFile");
		dataFile.setCellValueFactory(new PropertyValueFactory<>("dataFile"));
		dataFile.setCellFactory(TextFieldTableCell.forTableColumn());
		dataFile.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDataFile(event.getNewValue()); mainApp.reset();});
		choices.add("startDateTime");
		startDateTime.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
		startDateTime.setCellFactory(DateTimePickerTableCell.forTableColumn(DATETIME_CONVERTER));
		startDateTime.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setStartDateTime(event.getNewValue()); mainApp.reset();});
		choices.add("horizon");
		horizon.setCellValueFactory(new PropertyValueFactory<>("horizon"));
		horizon.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		horizon.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setHorizon(event.getNewValue()); mainApp.reset();});
		choices.add("timeResolution");
		timeResolution.setCellValueFactory(new PropertyValueFactory<>("timeResolution"));
		timeResolution.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		timeResolution.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setTimeResolution(event.getNewValue()); mainApp.reset();});
		choices.add("ganttWidth");
		ganttWidth.setCellValueFactory(new PropertyValueFactory<>("ganttWidth"));
		ganttWidth.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		ganttWidth.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setGanttWidth(event.getNewValue()); mainApp.reset();});
		choices.add("ganttLinesPerPage");
		ganttLinesPerPage.setCellValueFactory(new PropertyValueFactory<>("ganttLinesPerPage"));
		ganttLinesPerPage.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		ganttLinesPerPage.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setGanttLinesPerPage(event.getNewValue()); mainApp.reset();});
		choices.add("ganttLineHeight");
		ganttLineHeight.setCellValueFactory(new PropertyValueFactory<>("ganttLineHeight"));
		ganttLineHeight.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("")));
		ganttLineHeight.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setGanttLineHeight(event.getNewValue()); mainApp.reset();});
		choices.add("solverProperty");
		solverProperty.setCellValueFactory(new PropertyValueFactory<>("solverProperty"));
		choices.add("dataGeneratorProperty");
		dataGeneratorProperty.setCellValueFactory(new PropertyValueFactory<>("dataGeneratorProperty"));
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getScenarioData());
		try {
			ObservableList<Scenario> filteredItems = FXCollections.observableArrayList();
			for (Scenario item : table.getItems()) {
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

	class DirtyCallback implements Callback<TableColumn.CellDataFeatures<Scenario, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Scenario, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().dirtyWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setDirty(newValue);
				}
			});
			return prop;
		}
	}

	class ValidCallback implements Callback<TableColumn.CellDataFeatures<Scenario, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Scenario, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().validWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setValid(newValue);
				}
			});
			return prop;
		}
	}
}

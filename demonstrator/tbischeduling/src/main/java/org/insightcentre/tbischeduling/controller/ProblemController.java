package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.Table3Controller;
import java.lang.Boolean;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.Problem;

/**
 * Generated at 05:23:33 on 2024-10-13 */
public class ProblemController extends Table3Controller {
	@FXML
	private TableView<Problem> table;

	@FXML
	private TableColumn<Problem, String> name;

	@FXML
	private TableColumn<Problem, String> label;

	@FXML
	private TableColumn<Problem, Boolean> timePointsAsDate;

	@FXML
	private TableColumn<Problem, Integer> nrProducts;

	@FXML
	private TableColumn<Problem, Integer> nrProcesses;

	@FXML
	private TableColumn<Problem, Integer> nrDisjunctiveResources;

	@FXML
	private TableColumn<Problem, Integer> nrCumulativeResources;

	@FXML
	private TableColumn<Problem, Integer> nrOrders;

	@FXML
	private TableColumn<Problem, Integer> nrJobs;

	@FXML
	private TableColumn<Problem, Integer> nrTasks;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getProblemData());
	}

	public TableView<Problem> getTable() {
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
		choices.add("label");
		label.setCellValueFactory(new PropertyValueFactory<>("label"));
		label.setCellFactory(TextFieldTableCell.forTableColumn());
		label.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setLabel(event.getNewValue()); mainApp.reset();});
		choices.add("timePointsAsDate");
		timePointsAsDate.setCellValueFactory(new TimePointsAsDateCallback());
		timePointsAsDate.setCellFactory(CheckBoxTableCell.forTableColumn(timePointsAsDate));
		choices.add("nrProducts");
		nrProducts.setCellValueFactory(new PropertyValueFactory<>("nrProducts"));
		nrProducts.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrProducts.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrProducts(event.getNewValue()); mainApp.reset();});
		choices.add("nrProcesses");
		nrProcesses.setCellValueFactory(new PropertyValueFactory<>("nrProcesses"));
		nrProcesses.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrProcesses.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrProcesses(event.getNewValue()); mainApp.reset();});
		choices.add("nrDisjunctiveResources");
		nrDisjunctiveResources.setCellValueFactory(new PropertyValueFactory<>("nrDisjunctiveResources"));
		nrDisjunctiveResources.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrDisjunctiveResources.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrDisjunctiveResources(event.getNewValue()); mainApp.reset();});
		choices.add("nrCumulativeResources");
		nrCumulativeResources.setCellValueFactory(new PropertyValueFactory<>("nrCumulativeResources"));
		nrCumulativeResources.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrCumulativeResources.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrCumulativeResources(event.getNewValue()); mainApp.reset();});
		choices.add("nrOrders");
		nrOrders.setCellValueFactory(new PropertyValueFactory<>("nrOrders"));
		nrOrders.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrOrders.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrOrders(event.getNewValue()); mainApp.reset();});
		choices.add("nrJobs");
		nrJobs.setCellValueFactory(new PropertyValueFactory<>("nrJobs"));
		nrJobs.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrJobs.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrJobs(event.getNewValue()); mainApp.reset();});
		choices.add("nrTasks");
		nrTasks.setCellValueFactory(new PropertyValueFactory<>("nrTasks"));
		nrTasks.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrTasks.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrTasks(event.getNewValue()); mainApp.reset();});
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getProblemData());
		try {
			ObservableList<Problem> filteredItems = FXCollections.observableArrayList();
			for (Problem item : table.getItems()) {
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

	class TimePointsAsDateCallback implements Callback<TableColumn.CellDataFeatures<Problem, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Problem, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().timePointsAsDateWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setTimePointsAsDate(newValue);
				}
			});
			return prop;
		}
	}
}

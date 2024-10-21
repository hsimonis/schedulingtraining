package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.Table3Controller;
import java.lang.Double;
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
import org.insightcentre.tbischeduling.datamodel.ResourceUtilization;
import org.insightcentre.tbischeduling.datamodel.Solution;

/**
 * Generated at 10:13:47 on 2024-10-19 */
public class ResourceUtilizationController extends Table3Controller {
	@FXML
	private TableView<ResourceUtilization> table;

	@FXML
	private TableColumn<ResourceUtilization, String> name;

	@FXML
	private TableColumn<ResourceUtilization, DisjunctiveResource> disjunctiveResource;

	@FXML
	private TableColumn<ResourceUtilization, Solution> solution;

	@FXML
	private TableColumn<ResourceUtilization, Integer> earliest;

	@FXML
	private TableColumn<ResourceUtilization, Integer> latest;

	@FXML
	private TableColumn<ResourceUtilization, Integer> active;

	@FXML
	private TableColumn<ResourceUtilization, Integer> use;

	@FXML
	private TableColumn<ResourceUtilization, Double> utilization;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getResourceUtilizationData());
		disjunctiveResource.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getDisjunctiveResourceData()));
		disjunctiveResource.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDisjunctiveResource(event.getNewValue()); mainApp.reset();});
		solution.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getSolutionData()));
		solution.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSolution(event.getNewValue()); mainApp.reset();});
	}

	public TableView<ResourceUtilization> getTable() {
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
		choices.add("solution");
		solution.setCellValueFactory(new PropertyValueFactory<>("solution"));
		choices.add("earliest");
		earliest.setCellValueFactory(new PropertyValueFactory<>("earliest"));
		earliest.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		earliest.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setEarliest(event.getNewValue()); mainApp.reset();});
		choices.add("latest");
		latest.setCellValueFactory(new PropertyValueFactory<>("latest"));
		latest.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		latest.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setLatest(event.getNewValue()); mainApp.reset();});
		choices.add("active");
		active.setCellValueFactory(new PropertyValueFactory<>("active"));
		active.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		active.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setActive(event.getNewValue()); mainApp.reset();});
		choices.add("use");
		use.setCellValueFactory(new PropertyValueFactory<>("use"));
		use.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		use.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setUse(event.getNewValue()); mainApp.reset();});
		choices.add("utilization");
		utilization.setCellValueFactory(new PropertyValueFactory<>("utilization"));
		utilization.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		utilization.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setUtilization(event.getNewValue()); mainApp.reset();});
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getResourceUtilizationData());
		try {
			ObservableList<ResourceUtilization> filteredItems = FXCollections.observableArrayList();
			for (ResourceUtilization item : table.getItems()) {
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

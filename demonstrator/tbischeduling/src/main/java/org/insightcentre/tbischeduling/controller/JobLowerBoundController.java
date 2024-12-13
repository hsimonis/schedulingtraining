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
import org.insightcentre.tbischeduling.datamodel.Job;
import org.insightcentre.tbischeduling.datamodel.JobLowerBound;

/**
 * Generated at 13:12:28 on 2024-12-12 */
public class JobLowerBoundController extends Table3Controller {
	@FXML
	private TableView<JobLowerBound> table;

	@FXML
	private TableColumn<JobLowerBound, String> name;

	@FXML
	private TableColumn<JobLowerBound, Integer> value;

	@FXML
	private TableColumn<JobLowerBound, String> description;

	@FXML
	private TableColumn<JobLowerBound, Job> job;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getJobLowerBoundData());
		job.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getJobData()));
		job.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setJob(event.getNewValue()); mainApp.reset();});
	}

	public TableView<JobLowerBound> getTable() {
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
		choices.add("job");
		job.setCellValueFactory(new PropertyValueFactory<>("job"));
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getJobLowerBoundData());
		try {
			ObservableList<JobLowerBound> filteredItems = FXCollections.observableArrayList();
			for (JobLowerBound item : table.getItems()) {
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

package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.DateTimePickerTableCell;
import framework.gui.Table3Controller;
import framework.types.DateTime;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.reflect.Field;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import org.insightcentre.tbischeduling.datamodel.JobAssignment;
import org.insightcentre.tbischeduling.datamodel.Order;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.datamodel.Product;
import org.insightcentre.tbischeduling.datamodel.Solution;

/**
 * Generated at 11:56:31 on 2025-02-12 */
public class JobAssignmentController extends Table3Controller {
	@FXML
	private TableView<JobAssignment> table;

	@FXML
	private TableColumn<JobAssignment, String> name;

	@FXML
	private TableColumn<JobAssignment, Solution> solution;

	@FXML
	private TableColumn<JobAssignment, Job> job;

	@FXML
	private TableColumn<JobAssignment, Integer> late;

	@FXML
	private TableColumn<JobAssignment, Integer> early;

	@FXML
	private TableColumn<JobAssignment, Integer> duration;

	@FXML
	private TableColumn<JobAssignment, Integer> start;

	@FXML
	private TableColumn<JobAssignment, Integer> end;

	@FXML
	private TableColumn<JobAssignment, DateTime> startDate;

	@FXML
	private TableColumn<JobAssignment, DateTime> endDate;

	@FXML
	private TableColumn<JobAssignment, Order> order;

	@FXML
	private TableColumn<JobAssignment, Product> product;

	@FXML
	private TableColumn<JobAssignment, Process> process;

	@FXML
	private TableColumn<JobAssignment, Integer> release;

	@FXML
	private TableColumn<JobAssignment, Integer> due;

	@FXML
	private TableColumn<JobAssignment, DateTime> releaseDate;

	@FXML
	private TableColumn<JobAssignment, DateTime> dueDate;

	@FXML
	private TableColumn<JobAssignment, Integer> qty;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getJobAssignmentData());
		solution.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getSolutionData()));
		solution.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSolution(event.getNewValue()); mainApp.reset();});
		job.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getJobData()));
		job.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setJob(event.getNewValue()); mainApp.reset();});
	}

	public TableView<JobAssignment> getTable() {
		return table;
	}

	@FXML
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		choices.add("job");
		job.setCellValueFactory(new PropertyValueFactory<>("job"));
		choices.add("late");
		late.setCellValueFactory(new PropertyValueFactory<>("late"));
		late.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		late.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setLate(event.getNewValue()); mainApp.reset();});
		choices.add("early");
		early.setCellValueFactory(new PropertyValueFactory<>("early"));
		early.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		early.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setEarly(event.getNewValue()); mainApp.reset();});
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
		choices.add("job.order");
		try {
			order.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getJob().getOrder()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("job.order.product");
		try {
			product.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getJob().getOrder().getProduct()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("job.process");
		try {
			process.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getJob().getProcess()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("job.order.release");
		try {
			release.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getJob().getOrder().getRelease()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("job.order.due");
		try {
			due.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getJob().getOrder().getDue()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("job.order.releaseDate");
		try {
			releaseDate.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getJob().getOrder().getReleaseDate()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("job.order.dueDate");
		try {
			dueDate.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getJob().getOrder().getDueDate()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("job.order.qty");
		try {
			qty.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getJob().getOrder().getQty()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getJobAssignmentData());
		try {
			ObservableList<JobAssignment> filteredItems = FXCollections.observableArrayList();
			for (JobAssignment item : table.getItems()) {
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

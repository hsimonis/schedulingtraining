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
import org.insightcentre.tbischeduling.datamodel.DisjunctiveResource;
import org.insightcentre.tbischeduling.datamodel.JobAssignment;
import org.insightcentre.tbischeduling.datamodel.Order;
import org.insightcentre.tbischeduling.datamodel.ProcessStep;
import org.insightcentre.tbischeduling.datamodel.Product;
import org.insightcentre.tbischeduling.datamodel.Solution;
import org.insightcentre.tbischeduling.datamodel.Task;
import org.insightcentre.tbischeduling.datamodel.TaskAssignment;

/**
 * Generated code
 */
public class TaskAssignmentController extends Table3Controller {
	@FXML
	private TableView<TaskAssignment> table;

	@FXML
	private TableColumn<TaskAssignment, String> name;

	@FXML
	private TableColumn<TaskAssignment, DisjunctiveResource> disjunctiveResource;

	@FXML
	private TableColumn<TaskAssignment, Integer> duration;

	@FXML
	private TableColumn<TaskAssignment, Integer> start;

	@FXML
	private TableColumn<TaskAssignment, Integer> end;

	@FXML
	private TableColumn<TaskAssignment, DateTime> startDate;

	@FXML
	private TableColumn<TaskAssignment, DateTime> endDate;

	@FXML
	private TableColumn<TaskAssignment, Task> task;

	@FXML
	private TableColumn<TaskAssignment, JobAssignment> jobAssignment;

	@FXML
	private TableColumn<TaskAssignment, Integer> waitBefore;

	@FXML
	private TableColumn<TaskAssignment, Integer> waitAfter;

	@FXML
	private TableColumn<TaskAssignment, Integer> idleBefore;

	@FXML
	private TableColumn<TaskAssignment, Integer> idleAfter;

	@FXML
	private TableColumn<TaskAssignment, Integer> setupBefore;

	@FXML
	private TableColumn<TaskAssignment, Integer> setupAfter;

	@FXML
	private TableColumn<TaskAssignment, Solution> solution;

	@FXML
	private TableColumn<TaskAssignment, Order> order;

	@FXML
	private TableColumn<TaskAssignment, Integer> release;

	@FXML
	private TableColumn<TaskAssignment, Integer> due;

	@FXML
	private TableColumn<TaskAssignment, DateTime> releaseDate;

	@FXML
	private TableColumn<TaskAssignment, DateTime> dueDate;

	@FXML
	private TableColumn<TaskAssignment, Product> product;

	@FXML
	private TableColumn<TaskAssignment, Integer> qty;

	@FXML
	private TableColumn<TaskAssignment, ProcessStep> processStep;

	@FXML
	private TableColumn<TaskAssignment, Integer> durationFixed;

	@FXML
	private TableColumn<TaskAssignment, Integer> durationPerUnit;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getTaskAssignmentData());
		disjunctiveResource.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getDisjunctiveResourceData()));
		disjunctiveResource.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDisjunctiveResource(event.getNewValue()); mainApp.reset();});
		task.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getTaskData()));
		task.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setTask(event.getNewValue()); mainApp.reset();});
		jobAssignment.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getJobAssignmentData()));
		jobAssignment.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setJobAssignment(event.getNewValue()); mainApp.reset();});
	}

	public TableView<TaskAssignment> getTable() {
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
		choices.add("task");
		task.setCellValueFactory(new PropertyValueFactory<>("task"));
		choices.add("jobAssignment");
		jobAssignment.setCellValueFactory(new PropertyValueFactory<>("jobAssignment"));
		choices.add("waitBefore");
		waitBefore.setCellValueFactory(new PropertyValueFactory<>("waitBefore"));
		waitBefore.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		waitBefore.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setWaitBefore(event.getNewValue()); mainApp.reset();});
		choices.add("waitAfter");
		waitAfter.setCellValueFactory(new PropertyValueFactory<>("waitAfter"));
		waitAfter.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		waitAfter.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setWaitAfter(event.getNewValue()); mainApp.reset();});
		choices.add("idleBefore");
		idleBefore.setCellValueFactory(new PropertyValueFactory<>("idleBefore"));
		idleBefore.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		idleBefore.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setIdleBefore(event.getNewValue()); mainApp.reset();});
		choices.add("idleAfter");
		idleAfter.setCellValueFactory(new PropertyValueFactory<>("idleAfter"));
		idleAfter.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		idleAfter.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setIdleAfter(event.getNewValue()); mainApp.reset();});
		choices.add("setupBefore");
		setupBefore.setCellValueFactory(new PropertyValueFactory<>("setupBefore"));
		setupBefore.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		setupBefore.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSetupBefore(event.getNewValue()); mainApp.reset();});
		choices.add("setupAfter");
		setupAfter.setCellValueFactory(new PropertyValueFactory<>("setupAfter"));
		setupAfter.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		setupAfter.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSetupAfter(event.getNewValue()); mainApp.reset();});
		choices.add("jobAssignment.solution");
		try {
			solution.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getJobAssignment().getSolution()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("task.job.order");
		try {
			order.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTask().getJob().getOrder()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("task.job.order.release");
		try {
			release.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTask().getJob().getOrder().getRelease()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("task.job.order.due");
		try {
			due.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTask().getJob().getOrder().getDue()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("task.job.order.releaseDate");
		try {
			releaseDate.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTask().getJob().getOrder().getReleaseDate()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("task.job.order.dueDate");
		try {
			dueDate.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTask().getJob().getOrder().getDueDate()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("task.job.order.product");
		try {
			product.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTask().getJob().getOrder().getProduct()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("task.job.order.qty");
		try {
			qty.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTask().getJob().getOrder().getQty()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("task.processStep");
		try {
			processStep.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTask().getProcessStep()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("task.processStep.durationFixed");
		try {
			durationFixed.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTask().getProcessStep().getDurationFixed()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("task.processStep.durationPerUnit");
		try {
			durationPerUnit.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTask().getProcessStep().getDurationPerUnit()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getTaskAssignmentData());
		try {
			ObservableList<TaskAssignment> filteredItems = FXCollections.observableArrayList();
			for (TaskAssignment item : table.getItems()) {
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

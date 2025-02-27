package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.Table3Controller;
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
import javafx.beans.property.SimpleStringProperty;
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
import org.insightcentre.tbischeduling.datamodel.Order;
import org.insightcentre.tbischeduling.datamodel.ProcessStep;
import org.insightcentre.tbischeduling.datamodel.Product;
import org.insightcentre.tbischeduling.datamodel.Task;

/**
 * Generated at 10:19:39 on 2025-02-26 */
public class TaskController extends Table3Controller {
	@FXML
	private TableView<Task> table;

	@FXML
	private TableColumn<Task, String> name;

	@FXML
	private TableColumn<Task, String> shortName;

	@FXML
	private TableColumn<Task, Job> job;

	@FXML
	private TableColumn<Task, ProcessStep> processStep;

	@FXML
	private TableColumn<Task, Integer> duration;

	@FXML
	private TableColumn<Task, Integer> stage;

	@FXML
	private TableColumn<Task, Integer> nr;

	@FXML
	private TableColumn<Task, String> machines;

	@FXML
	private TableColumn<Task, String> precedes;

	@FXML
	private TableColumn<Task, String> follows;

	@FXML
	private TableColumn<Task, Order> order;

	@FXML
	private TableColumn<Task, Product> product;

	@FXML
	private TableColumn<Task, Integer> qty;

	@FXML
	private TableColumn<Task, Integer> durationFixed;

	@FXML
	private TableColumn<Task, Integer> durationPerUnit;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getTaskData());
		job.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getJobData()));
		job.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setJob(event.getNewValue()); mainApp.reset();});
		processStep.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getProcessStepData()));
		processStep.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setProcessStep(event.getNewValue()); mainApp.reset();});
	}

	public TableView<Task> getTable() {
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
		choices.add("shortName");
		shortName.setCellValueFactory(new PropertyValueFactory<>("shortName"));
		shortName.setCellFactory(TextFieldTableCell.forTableColumn());
		shortName.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setShortName(event.getNewValue()); mainApp.reset();});
		choices.add("job");
		job.setCellValueFactory(new PropertyValueFactory<>("job"));
		choices.add("processStep");
		processStep.setCellValueFactory(new PropertyValueFactory<>("processStep"));
		choices.add("duration");
		duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		duration.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		duration.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDuration(event.getNewValue()); mainApp.reset();});
		choices.add("stage");
		stage.setCellValueFactory(new PropertyValueFactory<>("stage"));
		stage.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		stage.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setStage(event.getNewValue()); mainApp.reset();});
		choices.add("nr");
		nr.setCellValueFactory(new PropertyValueFactory<>("nr"));
		nr.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nr.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNr(event.getNewValue()); mainApp.reset();});
		choices.add("machines");
		machines.setCellValueFactory(cellData -> new SimpleStringProperty(convert(cellData.getValue().getMachines())));
		choices.add("precedes");
		precedes.setCellValueFactory(cellData -> new SimpleStringProperty(convert(cellData.getValue().getPrecedes())));
		choices.add("follows");
		follows.setCellValueFactory(cellData -> new SimpleStringProperty(convert(cellData.getValue().getFollows())));
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
		choices.add("job.order.qty");
		try {
			qty.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getJob().getOrder().getQty()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
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
		table.setItems(mainApp.getTaskData());
		try {
			ObservableList<Task> filteredItems = FXCollections.observableArrayList();
			for (Task item : table.getItems()) {
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

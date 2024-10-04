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
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.datamodel.Product;

/**
 * Generated at 22:48:03 on 2024-10-03 */
public class JobController extends Table3Controller {
	@FXML
	private TableView<Job> table;

	@FXML
	private TableColumn<Job, String> name;

	@FXML
	private TableColumn<Job, Order> order;

	@FXML
	private TableColumn<Job, Process> process;

	@FXML
	private TableColumn<Job, Product> product;

	@FXML
	private TableColumn<Job, Integer> release;

	@FXML
	private TableColumn<Job, Integer> due;

	@FXML
	private TableColumn<Job, Integer> qty;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getJobData());
		order.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getOrderData()));
		order.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setOrder(event.getNewValue()); mainApp.reset();});
		process.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getProcessData()));
		process.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setProcess(event.getNewValue()); mainApp.reset();});
	}

	public TableView<Job> getTable() {
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
		choices.add("order");
		order.setCellValueFactory(new PropertyValueFactory<>("order"));
		choices.add("process");
		process.setCellValueFactory(new PropertyValueFactory<>("process"));
		choices.add("order.product");
		try {
			product.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getOrder().getProduct()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("order.release");
		try {
			release.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrder().getRelease()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("order.due");
		try {
			due.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrder().getDue()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("order.qty");
		try {
			qty.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrder().getQty()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getJobData());
		try {
			ObservableList<Job> filteredItems = FXCollections.observableArrayList();
			for (Job item : table.getItems()) {
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

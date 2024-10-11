package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.DateTimePickerTableCell;
import framework.gui.Table3Controller;
import framework.types.DateTime;
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
import org.insightcentre.tbischeduling.datamodel.Order;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.datamodel.Product;

/**
 * Generated at 12:12:42 on 2024-10-10 */
public class OrderController extends Table3Controller {
	@FXML
	private TableView<Order> table;

	@FXML
	private TableColumn<Order, String> name;

	@FXML
	private TableColumn<Order, Integer> nr;

	@FXML
	private TableColumn<Order, Product> product;

	@FXML
	private TableColumn<Order, Process> process;

	@FXML
	private TableColumn<Order, Integer> qty;

	@FXML
	private TableColumn<Order, Integer> due;

	@FXML
	private TableColumn<Order, DateTime> dueDate;

	@FXML
	private TableColumn<Order, Integer> release;

	@FXML
	private TableColumn<Order, DateTime> releaseDate;

	@FXML
	private TableColumn<Order, Double> latenessWeight;

	@FXML
	private TableColumn<Order, Double> earlinessWeight;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getOrderData());
		product.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getProductData()));
		product.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setProduct(event.getNewValue()); mainApp.reset();});
		process.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getProcessData()));
		process.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setProcess(event.getNewValue()); mainApp.reset();});
	}

	public TableView<Order> getTable() {
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
		choices.add("nr");
		nr.setCellValueFactory(new PropertyValueFactory<>("nr"));
		nr.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nr.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNr(event.getNewValue()); mainApp.reset();});
		choices.add("product");
		product.setCellValueFactory(new PropertyValueFactory<>("product"));
		choices.add("process");
		process.setCellValueFactory(new PropertyValueFactory<>("process"));
		choices.add("qty");
		qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
		qty.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		qty.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setQty(event.getNewValue()); mainApp.reset();});
		choices.add("due");
		due.setCellValueFactory(new PropertyValueFactory<>("due"));
		due.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		due.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDue(event.getNewValue()); mainApp.reset();});
		choices.add("dueDate");
		dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		dueDate.setCellFactory(DateTimePickerTableCell.forTableColumn(DATETIME_CONVERTER));
		dueDate.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDueDate(event.getNewValue()); mainApp.reset();});
		choices.add("release");
		release.setCellValueFactory(new PropertyValueFactory<>("release"));
		release.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		release.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setRelease(event.getNewValue()); mainApp.reset();});
		choices.add("releaseDate");
		releaseDate.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
		releaseDate.setCellFactory(DateTimePickerTableCell.forTableColumn(DATETIME_CONVERTER));
		releaseDate.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setReleaseDate(event.getNewValue()); mainApp.reset();});
		choices.add("latenessWeight");
		latenessWeight.setCellValueFactory(new PropertyValueFactory<>("latenessWeight"));
		latenessWeight.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("")));
		latenessWeight.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setLatenessWeight(event.getNewValue()); mainApp.reset();});
		choices.add("earlinessWeight");
		earlinessWeight.setCellValueFactory(new PropertyValueFactory<>("earlinessWeight"));
		earlinessWeight.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("")));
		earlinessWeight.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setEarlinessWeight(event.getNewValue()); mainApp.reset();});
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getOrderData());
		try {
			ObservableList<Order> filteredItems = FXCollections.observableArrayList();
			for (Order item : table.getItems()) {
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

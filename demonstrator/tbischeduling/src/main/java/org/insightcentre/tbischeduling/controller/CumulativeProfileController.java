package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.DateTimePickerTableCell;
import framework.gui.Table3Controller;
import framework.types.DateTime;
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
import org.insightcentre.tbischeduling.datamodel.CumulativeProfile;
import org.insightcentre.tbischeduling.datamodel.CumulativeResource;

/**
 * Generated at 19:11:16 on 2024-10-17 */
public class CumulativeProfileController extends Table3Controller {
	@FXML
	private TableView<CumulativeProfile> table;

	@FXML
	private TableColumn<CumulativeProfile, String> name;

	@FXML
	private TableColumn<CumulativeProfile, CumulativeResource> cumulativeResource;

	@FXML
	private TableColumn<CumulativeProfile, Integer> from;

	@FXML
	private TableColumn<CumulativeProfile, DateTime> fromDate;

	@FXML
	private TableColumn<CumulativeProfile, Integer> capacity;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getCumulativeProfileData());
		cumulativeResource.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getCumulativeResourceData()));
		cumulativeResource.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setCumulativeResource(event.getNewValue()); mainApp.reset();});
	}

	public TableView<CumulativeProfile> getTable() {
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
		choices.add("cumulativeResource");
		cumulativeResource.setCellValueFactory(new PropertyValueFactory<>("cumulativeResource"));
		choices.add("from");
		from.setCellValueFactory(new PropertyValueFactory<>("from"));
		from.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		from.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setFrom(event.getNewValue()); mainApp.reset();});
		choices.add("fromDate");
		fromDate.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
		fromDate.setCellFactory(DateTimePickerTableCell.forTableColumn(DATETIME_CONVERTER));
		fromDate.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setFromDate(event.getNewValue()); mainApp.reset();});
		choices.add("capacity");
		capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
		capacity.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		capacity.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setCapacity(event.getNewValue()); mainApp.reset();});
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getCumulativeProfileData());
		try {
			ObservableList<CumulativeProfile> filteredItems = FXCollections.observableArrayList();
			for (CumulativeProfile item : table.getItems()) {
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

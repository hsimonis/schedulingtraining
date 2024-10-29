package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.Table3Controller;
import java.lang.Exception;
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
import org.insightcentre.tbischeduling.datamodel.AbstractGanttProperty;
import org.insightcentre.tbischeduling.datamodel.DatesDisplay;
import org.insightcentre.tbischeduling.datamodel.LineChoice;

/**
 * Generated at 11:45:38 on 2024-10-28 */
public class AbstractGanttPropertyController extends Table3Controller {
	@FXML
	private TableView<AbstractGanttProperty> table;

	@FXML
	private TableColumn<AbstractGanttProperty, String> name;

	@FXML
	private TableColumn<AbstractGanttProperty, LineChoice> showLate;

	@FXML
	private TableColumn<AbstractGanttProperty, LineChoice> showEarly;

	@FXML
	private TableColumn<AbstractGanttProperty, LineChoice> showRelease;

	@FXML
	private TableColumn<AbstractGanttProperty, LineChoice> showWait;

	@FXML
	private TableColumn<AbstractGanttProperty, LineChoice> showSetup;

	@FXML
	private TableColumn<AbstractGanttProperty, LineChoice> showIdle;

	@FXML
	private TableColumn<AbstractGanttProperty, DatesDisplay> datesDisplay;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getAbstractGanttPropertyData());
		ObservableList<LineChoice> showLateValues = FXCollections.observableArrayList(LineChoice.values());
		showLate.setCellFactory(ComboBoxTableCell.forTableColumn(showLateValues));
		showLate.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setShowLate(event.getNewValue()); mainApp.reset();});
		ObservableList<LineChoice> showEarlyValues = FXCollections.observableArrayList(LineChoice.values());
		showEarly.setCellFactory(ComboBoxTableCell.forTableColumn(showEarlyValues));
		showEarly.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setShowEarly(event.getNewValue()); mainApp.reset();});
		ObservableList<LineChoice> showReleaseValues = FXCollections.observableArrayList(LineChoice.values());
		showRelease.setCellFactory(ComboBoxTableCell.forTableColumn(showReleaseValues));
		showRelease.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setShowRelease(event.getNewValue()); mainApp.reset();});
		ObservableList<LineChoice> showWaitValues = FXCollections.observableArrayList(LineChoice.values());
		showWait.setCellFactory(ComboBoxTableCell.forTableColumn(showWaitValues));
		showWait.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setShowWait(event.getNewValue()); mainApp.reset();});
		ObservableList<LineChoice> showSetupValues = FXCollections.observableArrayList(LineChoice.values());
		showSetup.setCellFactory(ComboBoxTableCell.forTableColumn(showSetupValues));
		showSetup.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setShowSetup(event.getNewValue()); mainApp.reset();});
		ObservableList<LineChoice> showIdleValues = FXCollections.observableArrayList(LineChoice.values());
		showIdle.setCellFactory(ComboBoxTableCell.forTableColumn(showIdleValues));
		showIdle.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setShowIdle(event.getNewValue()); mainApp.reset();});
		ObservableList<DatesDisplay> datesDisplayValues = FXCollections.observableArrayList(DatesDisplay.values());
		datesDisplay.setCellFactory(ComboBoxTableCell.forTableColumn(datesDisplayValues));
		datesDisplay.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDatesDisplay(event.getNewValue()); mainApp.reset();});
	}

	public TableView<AbstractGanttProperty> getTable() {
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
		choices.add("showLate");
		showLate.setCellValueFactory(new PropertyValueFactory<>("showLate"));
		choices.add("showEarly");
		showEarly.setCellValueFactory(new PropertyValueFactory<>("showEarly"));
		choices.add("showRelease");
		showRelease.setCellValueFactory(new PropertyValueFactory<>("showRelease"));
		choices.add("showWait");
		showWait.setCellValueFactory(new PropertyValueFactory<>("showWait"));
		choices.add("showSetup");
		showSetup.setCellValueFactory(new PropertyValueFactory<>("showSetup"));
		choices.add("showIdle");
		showIdle.setCellValueFactory(new PropertyValueFactory<>("showIdle"));
		choices.add("datesDisplay");
		datesDisplay.setCellValueFactory(new PropertyValueFactory<>("datesDisplay"));
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getAbstractGanttPropertyData());
		try {
			ObservableList<AbstractGanttProperty> filteredItems = FXCollections.observableArrayList();
			for (AbstractGanttProperty item : table.getItems()) {
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

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
import org.insightcentre.tbischeduling.datamodel.ProcessSequence;
import org.insightcentre.tbischeduling.datamodel.ProcessStep;
import org.insightcentre.tbischeduling.datamodel.SequenceType;

/**
 * Generated at 10:19:39 on 2025-02-26 */
public class ProcessSequenceController extends Table3Controller {
	@FXML
	private TableView<ProcessSequence> table;

	@FXML
	private TableColumn<ProcessSequence, String> name;

	@FXML
	private TableColumn<ProcessSequence, ProcessStep> before;

	@FXML
	private TableColumn<ProcessSequence, ProcessStep> after;

	@FXML
	private TableColumn<ProcessSequence, SequenceType> sequenceType;

	@FXML
	private TableColumn<ProcessSequence, Integer> offset;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getProcessSequenceData());
		before.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getProcessStepData()));
		before.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setBefore(event.getNewValue()); mainApp.reset();});
		after.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getProcessStepData()));
		after.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setAfter(event.getNewValue()); mainApp.reset();});
		ObservableList<SequenceType> sequenceTypeValues = FXCollections.observableArrayList(SequenceType.values());
		sequenceType.setCellFactory(ComboBoxTableCell.forTableColumn(sequenceTypeValues));
		sequenceType.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSequenceType(event.getNewValue()); mainApp.reset();});
	}

	public TableView<ProcessSequence> getTable() {
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
		choices.add("before");
		before.setCellValueFactory(new PropertyValueFactory<>("before"));
		choices.add("after");
		after.setCellValueFactory(new PropertyValueFactory<>("after"));
		choices.add("sequenceType");
		sequenceType.setCellValueFactory(new PropertyValueFactory<>("sequenceType"));
		choices.add("offset");
		offset.setCellValueFactory(new PropertyValueFactory<>("offset"));
		offset.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		offset.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setOffset(event.getNewValue()); mainApp.reset();});
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getProcessSequenceData());
		try {
			ObservableList<ProcessSequence> filteredItems = FXCollections.observableArrayList();
			for (ProcessSequence item : table.getItems()) {
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

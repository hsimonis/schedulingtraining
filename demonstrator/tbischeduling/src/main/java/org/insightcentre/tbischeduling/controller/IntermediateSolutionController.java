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
import org.insightcentre.tbischeduling.datamodel.IntermediateSolution;
import org.insightcentre.tbischeduling.datamodel.SolverRun;

/**
 * Generated at 07:10:18 on 2024-10-16 */
public class IntermediateSolutionController extends Table3Controller {
	@FXML
	private TableView<IntermediateSolution> table;

	@FXML
	private TableColumn<IntermediateSolution, String> name;

	@FXML
	private TableColumn<IntermediateSolution, SolverRun> solverRun;

	@FXML
	private TableColumn<IntermediateSolution, Integer> nr;

	@FXML
	private TableColumn<IntermediateSolution, Double> cost;

	@FXML
	private TableColumn<IntermediateSolution, Double> bound;

	@FXML
	private TableColumn<IntermediateSolution, Double> time;

	@FXML
	private TableColumn<IntermediateSolution, Double> gapPercent;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getIntermediateSolutionData());
		solverRun.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getSolverRunData()));
		solverRun.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSolverRun(event.getNewValue()); mainApp.reset();});
	}

	public TableView<IntermediateSolution> getTable() {
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
		choices.add("solverRun");
		solverRun.setCellValueFactory(new PropertyValueFactory<>("solverRun"));
		choices.add("nr");
		nr.setCellValueFactory(new PropertyValueFactory<>("nr"));
		nr.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nr.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNr(event.getNewValue()); mainApp.reset();});
		choices.add("cost");
		cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
		cost.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		cost.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setCost(event.getNewValue()); mainApp.reset();});
		choices.add("bound");
		bound.setCellValueFactory(new PropertyValueFactory<>("bound"));
		bound.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		bound.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setBound(event.getNewValue()); mainApp.reset();});
		choices.add("time");
		time.setCellValueFactory(new PropertyValueFactory<>("time"));
		time.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		time.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setTime(event.getNewValue()); mainApp.reset();});
		choices.add("gapPercent");
		gapPercent.setCellValueFactory(new PropertyValueFactory<>("gapPercent"));
		gapPercent.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		gapPercent.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setGapPercent(event.getNewValue()); mainApp.reset();});
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getIntermediateSolutionData());
		try {
			ObservableList<IntermediateSolution> filteredItems = FXCollections.observableArrayList();
			for (IntermediateSolution item : table.getItems()) {
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

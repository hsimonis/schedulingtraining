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
import org.insightcentre.tbischeduling.datamodel.SolutionSummary;
import org.insightcentre.tbischeduling.datamodel.SolverStatus;

/**
 * Generated at 13:12:28 on 2024-12-12 */
public class SolutionSummaryController extends Table3Controller {
	@FXML
	private TableView<SolutionSummary> table;

	@FXML
	private TableColumn<SolutionSummary, String> name;

	@FXML
	private TableColumn<SolutionSummary, String> variant;

	@FXML
	private TableColumn<SolutionSummary, String> instance;

	@FXML
	private TableColumn<SolutionSummary, Integer> instanceNr;

	@FXML
	private TableColumn<SolutionSummary, Integer> nrJobs;

	@FXML
	private TableColumn<SolutionSummary, Integer> nrTasks;

	@FXML
	private TableColumn<SolutionSummary, Integer> nrMachines;

	@FXML
	private TableColumn<SolutionSummary, Integer> nrCumulatives;

	@FXML
	private TableColumn<SolutionSummary, SolverStatus> solverStatus;

	@FXML
	private TableColumn<SolutionSummary, Double> time;

	@FXML
	private TableColumn<SolutionSummary, Integer> makespan;

	@FXML
	private TableColumn<SolutionSummary, Double> bound;

	@FXML
	private TableColumn<SolutionSummary, Double> gapPercent;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getSolutionSummaryData());
		ObservableList<SolverStatus> solverStatusValues = FXCollections.observableArrayList(SolverStatus.values());
		solverStatus.setCellFactory(ComboBoxTableCell.forTableColumn(solverStatusValues));
		solverStatus.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSolverStatus(event.getNewValue()); mainApp.reset();});
	}

	public TableView<SolutionSummary> getTable() {
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
		choices.add("variant");
		variant.setCellValueFactory(new PropertyValueFactory<>("variant"));
		variant.setCellFactory(TextFieldTableCell.forTableColumn());
		variant.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setVariant(event.getNewValue()); mainApp.reset();});
		choices.add("instance");
		instance.setCellValueFactory(new PropertyValueFactory<>("instance"));
		instance.setCellFactory(TextFieldTableCell.forTableColumn());
		instance.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setInstance(event.getNewValue()); mainApp.reset();});
		choices.add("instanceNr");
		instanceNr.setCellValueFactory(new PropertyValueFactory<>("instanceNr"));
		instanceNr.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		instanceNr.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setInstanceNr(event.getNewValue()); mainApp.reset();});
		choices.add("nrJobs");
		nrJobs.setCellValueFactory(new PropertyValueFactory<>("nrJobs"));
		nrJobs.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrJobs.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrJobs(event.getNewValue()); mainApp.reset();});
		choices.add("nrTasks");
		nrTasks.setCellValueFactory(new PropertyValueFactory<>("nrTasks"));
		nrTasks.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrTasks.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrTasks(event.getNewValue()); mainApp.reset();});
		choices.add("nrMachines");
		nrMachines.setCellValueFactory(new PropertyValueFactory<>("nrMachines"));
		nrMachines.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrMachines.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrMachines(event.getNewValue()); mainApp.reset();});
		choices.add("nrCumulatives");
		nrCumulatives.setCellValueFactory(new PropertyValueFactory<>("nrCumulatives"));
		nrCumulatives.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrCumulatives.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrCumulatives(event.getNewValue()); mainApp.reset();});
		choices.add("solverStatus");
		solverStatus.setCellValueFactory(new PropertyValueFactory<>("solverStatus"));
		choices.add("time");
		time.setCellValueFactory(new PropertyValueFactory<>("time"));
		time.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		time.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setTime(event.getNewValue()); mainApp.reset();});
		choices.add("makespan");
		makespan.setCellValueFactory(new PropertyValueFactory<>("makespan"));
		makespan.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		makespan.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMakespan(event.getNewValue()); mainApp.reset();});
		choices.add("bound");
		bound.setCellValueFactory(new PropertyValueFactory<>("bound"));
		bound.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		bound.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setBound(event.getNewValue()); mainApp.reset();});
		choices.add("gapPercent");
		gapPercent.setCellValueFactory(new PropertyValueFactory<>("gapPercent"));
		gapPercent.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		gapPercent.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setGapPercent(event.getNewValue()); mainApp.reset();});
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getSolutionSummaryData());
		try {
			ObservableList<SolutionSummary> filteredItems = FXCollections.observableArrayList();
			for (SolutionSummary item : table.getItems()) {
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

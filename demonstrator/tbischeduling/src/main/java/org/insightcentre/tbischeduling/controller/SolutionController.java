package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.DateTimePickerTableCell;
import framework.gui.Table3Controller;
import framework.types.DateTime;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.reflect.Field;
import javafx.beans.property.SimpleDoubleProperty;
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
import org.insightcentre.tbischeduling.datamodel.ModelType;
import org.insightcentre.tbischeduling.datamodel.ObjectiveType;
import org.insightcentre.tbischeduling.datamodel.Solution;
import org.insightcentre.tbischeduling.datamodel.SolverBackend;
import org.insightcentre.tbischeduling.datamodel.SolverRun;
import org.insightcentre.tbischeduling.datamodel.SolverStatus;

/**
 * Generated at 08:44:24 on 2024-10-11 */
public class SolutionController extends Table3Controller {
	@FXML
	private TableView<Solution> table;

	@FXML
	private TableColumn<Solution, String> name;

	@FXML
	private TableColumn<Solution, SolverRun> solverRun;

	@FXML
	private TableColumn<Solution, Integer> objectiveValue;

	@FXML
	private TableColumn<Solution, SolverStatus> solverStatus;

	@FXML
	private TableColumn<Solution, Double> bound;

	@FXML
	private TableColumn<Solution, Double> gap;

	@FXML
	private TableColumn<Solution, Integer> makespan;

	@FXML
	private TableColumn<Solution, Integer> flowtime;

	@FXML
	private TableColumn<Solution, Integer> totalLateness;

	@FXML
	private TableColumn<Solution, Integer> maxLateness;

	@FXML
	private TableColumn<Solution, Integer> nrLate;

	@FXML
	private TableColumn<Solution, Double> weightedLateness;

	@FXML
	private TableColumn<Solution, Integer> totalEarliness;

	@FXML
	private TableColumn<Solution, Integer> maxEarliness;

	@FXML
	private TableColumn<Solution, Integer> nrEarly;

	@FXML
	private TableColumn<Solution, Double> weightedEarliness;

	@FXML
	private TableColumn<Solution, Double> percentEarly;

	@FXML
	private TableColumn<Solution, Double> percentLate;

	@FXML
	private TableColumn<Solution, Integer> duration;

	@FXML
	private TableColumn<Solution, Integer> start;

	@FXML
	private TableColumn<Solution, Integer> end;

	@FXML
	private TableColumn<Solution, DateTime> startDate;

	@FXML
	private TableColumn<Solution, DateTime> endDate;

	@FXML
	private TableColumn<Solution, Integer> totalWaitBefore;

	@FXML
	private TableColumn<Solution, Integer> totalWaitAfter;

	@FXML
	private TableColumn<Solution, Integer> maxWaitBefore;

	@FXML
	private TableColumn<Solution, Integer> maxWaitAfter;

	@FXML
	private TableColumn<Solution, ModelType> modelType;

	@FXML
	private TableColumn<Solution, SolverBackend> solverBackend;

	@FXML
	private TableColumn<Solution, ObjectiveType> objectiveType;

	@FXML
	private TableColumn<Solution, Boolean> enforceReleaseDate;

	@FXML
	private TableColumn<Solution, Boolean> enforceDueDate;

	@FXML
	private TableColumn<Solution, Boolean> enforceCumulative;

	@FXML
	private TableColumn<Solution, Boolean> enforceWip;

	@FXML
	private TableColumn<Solution, Boolean> enforceDowntime;

	@FXML
	private TableColumn<Solution, Integer> weightMakespan;

	@FXML
	private TableColumn<Solution, Integer> weightFlowtime;

	@FXML
	private TableColumn<Solution, Integer> weightEarliness;

	@FXML
	private TableColumn<Solution, Integer> weightLateness;

	@FXML
	private TableColumn<Solution, Integer> timeout;

	@FXML
	private TableColumn<Solution, Integer> nrThreads;

	@FXML
	private TableColumn<Solution, Integer> seed;

	@FXML
	private TableColumn<Solution, Boolean> removeSolution;

	@FXML
	private TableColumn<Solution, Double> time;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getSolutionData());
		solverRun.setCellFactory(ComboBoxTableCell.forTableColumn(mainApp.getSolverRunData()));
		solverRun.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSolverRun(event.getNewValue()); mainApp.reset();});
		ObservableList<SolverStatus> solverStatusValues = FXCollections.observableArrayList(SolverStatus.values());
		solverStatus.setCellFactory(ComboBoxTableCell.forTableColumn(solverStatusValues));
		solverStatus.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSolverStatus(event.getNewValue()); mainApp.reset();});
	}

	public TableView<Solution> getTable() {
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
		choices.add("solverRun");
		solverRun.setCellValueFactory(new PropertyValueFactory<>("solverRun"));
		choices.add("objectiveValue");
		objectiveValue.setCellValueFactory(new PropertyValueFactory<>("objectiveValue"));
		objectiveValue.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		objectiveValue.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setObjectiveValue(event.getNewValue()); mainApp.reset();});
		choices.add("solverStatus");
		solverStatus.setCellValueFactory(new PropertyValueFactory<>("solverStatus"));
		choices.add("bound");
		bound.setCellValueFactory(new PropertyValueFactory<>("bound"));
		bound.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		bound.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setBound(event.getNewValue()); mainApp.reset();});
		choices.add("gap");
		gap.setCellValueFactory(new PropertyValueFactory<>("gap"));
		gap.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		gap.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setGap(event.getNewValue()); mainApp.reset();});
		choices.add("makespan");
		makespan.setCellValueFactory(new PropertyValueFactory<>("makespan"));
		makespan.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		makespan.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMakespan(event.getNewValue()); mainApp.reset();});
		choices.add("flowtime");
		flowtime.setCellValueFactory(new PropertyValueFactory<>("flowtime"));
		flowtime.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		flowtime.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setFlowtime(event.getNewValue()); mainApp.reset();});
		choices.add("totalLateness");
		totalLateness.setCellValueFactory(new PropertyValueFactory<>("totalLateness"));
		totalLateness.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		totalLateness.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setTotalLateness(event.getNewValue()); mainApp.reset();});
		choices.add("maxLateness");
		maxLateness.setCellValueFactory(new PropertyValueFactory<>("maxLateness"));
		maxLateness.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		maxLateness.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMaxLateness(event.getNewValue()); mainApp.reset();});
		choices.add("nrLate");
		nrLate.setCellValueFactory(new PropertyValueFactory<>("nrLate"));
		nrLate.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrLate.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrLate(event.getNewValue()); mainApp.reset();});
		choices.add("weightedLateness");
		weightedLateness.setCellValueFactory(new PropertyValueFactory<>("weightedLateness"));
		weightedLateness.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		weightedLateness.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setWeightedLateness(event.getNewValue()); mainApp.reset();});
		choices.add("totalEarliness");
		totalEarliness.setCellValueFactory(new PropertyValueFactory<>("totalEarliness"));
		totalEarliness.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		totalEarliness.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setTotalEarliness(event.getNewValue()); mainApp.reset();});
		choices.add("maxEarliness");
		maxEarliness.setCellValueFactory(new PropertyValueFactory<>("maxEarliness"));
		maxEarliness.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		maxEarliness.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMaxEarliness(event.getNewValue()); mainApp.reset();});
		choices.add("nrEarly");
		nrEarly.setCellValueFactory(new PropertyValueFactory<>("nrEarly"));
		nrEarly.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrEarly.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrEarly(event.getNewValue()); mainApp.reset();});
		choices.add("weightedEarliness");
		weightedEarliness.setCellValueFactory(new PropertyValueFactory<>("weightedEarliness"));
		weightedEarliness.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		weightedEarliness.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setWeightedEarliness(event.getNewValue()); mainApp.reset();});
		choices.add("percentEarly");
		percentEarly.setCellValueFactory(new PropertyValueFactory<>("percentEarly"));
		percentEarly.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		percentEarly.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setPercentEarly(event.getNewValue()); mainApp.reset();});
		choices.add("percentLate");
		percentLate.setCellValueFactory(new PropertyValueFactory<>("percentLate"));
		percentLate.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		percentLate.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setPercentLate(event.getNewValue()); mainApp.reset();});
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
		choices.add("totalWaitBefore");
		totalWaitBefore.setCellValueFactory(new PropertyValueFactory<>("totalWaitBefore"));
		totalWaitBefore.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		totalWaitBefore.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setTotalWaitBefore(event.getNewValue()); mainApp.reset();});
		choices.add("totalWaitAfter");
		totalWaitAfter.setCellValueFactory(new PropertyValueFactory<>("totalWaitAfter"));
		totalWaitAfter.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		totalWaitAfter.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setTotalWaitAfter(event.getNewValue()); mainApp.reset();});
		choices.add("maxWaitBefore");
		maxWaitBefore.setCellValueFactory(new PropertyValueFactory<>("maxWaitBefore"));
		maxWaitBefore.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		maxWaitBefore.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMaxWaitBefore(event.getNewValue()); mainApp.reset();});
		choices.add("maxWaitAfter");
		maxWaitAfter.setCellValueFactory(new PropertyValueFactory<>("maxWaitAfter"));
		maxWaitAfter.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		maxWaitAfter.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMaxWaitAfter(event.getNewValue()); mainApp.reset();});
		choices.add("solverRun.modelType");
		try {
			modelType.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSolverRun().getModelType()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.solverBackend");
		try {
			solverBackend.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSolverRun().getSolverBackend()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.objectiveType");
		try {
			objectiveType.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSolverRun().getObjectiveType()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.enforceReleaseDate");
		try {
			enforceReleaseDate.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSolverRun().getEnforceReleaseDate()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.enforceDueDate");
		try {
			enforceDueDate.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSolverRun().getEnforceDueDate()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.enforceCumulative");
		try {
			enforceCumulative.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSolverRun().getEnforceCumulative()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.enforceWip");
		try {
			enforceWip.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSolverRun().getEnforceWip()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.enforceDowntime");
		try {
			enforceDowntime.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSolverRun().getEnforceDowntime()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.weightMakespan");
		try {
			weightMakespan.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSolverRun().getWeightMakespan()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.weightFlowtime");
		try {
			weightFlowtime.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSolverRun().getWeightFlowtime()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.weightEarliness");
		try {
			weightEarliness.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSolverRun().getWeightEarliness()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.weightLateness");
		try {
			weightLateness.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSolverRun().getWeightLateness()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.timeout");
		try {
			timeout.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSolverRun().getTimeout()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.nrThreads");
		try {
			nrThreads.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSolverRun().getNrThreads()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.seed");
		try {
			seed.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSolverRun().getSeed()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.removeSolution");
		try {
			removeSolution.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSolverRun().getRemoveSolution()));
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		choices.add("solverRun.time");
		try {
			time.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getSolverRun().getTime()).asObject());
		}
		catch (NullPointerException e) {
			System.err.println(e);
		}
		time.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getSolutionData());
		try {
			ObservableList<Solution> filteredItems = FXCollections.observableArrayList();
			for (Solution item : table.getItems()) {
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

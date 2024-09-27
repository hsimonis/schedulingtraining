package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.Table3Controller;
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
 * Generated at 07:28:35 on 2024-09-27 */
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
	private TableColumn<Solution, Double> weightedLateness;

	@FXML
	private TableColumn<Solution, Integer> totalEarliness;

	@FXML
	private TableColumn<Solution, Integer> maxEarliness;

	@FXML
	private TableColumn<Solution, Double> weightedEarliness;

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
		choices.add("weightedEarliness");
		weightedEarliness.setCellValueFactory(new PropertyValueFactory<>("weightedEarliness"));
		weightedEarliness.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		weightedEarliness.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setWeightedEarliness(event.getNewValue()); mainApp.reset();});
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

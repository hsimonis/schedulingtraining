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
import org.insightcentre.tbischeduling.datamodel.DataGeneratorRun;
import org.insightcentre.tbischeduling.datamodel.DurationModel;
import org.insightcentre.tbischeduling.datamodel.ResourceModel;

/**
 * Generated at 19:11:16 on 2024-10-17 */
public class DataGeneratorRunController extends Table3Controller {
	@FXML
	private TableView<DataGeneratorRun> table;

	@FXML
	private TableColumn<DataGeneratorRun, String> name;

	@FXML
	private TableColumn<DataGeneratorRun, String> label;

	@FXML
	private TableColumn<DataGeneratorRun, DateTime> startDateTime;

	@FXML
	private TableColumn<DataGeneratorRun, ResourceModel> resourceModel;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> nrProducts;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> minStages;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> maxStages;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> nrDisjunctiveResources;

	@FXML
	private TableColumn<DataGeneratorRun, Double> resourceProbability;

	@FXML
	private TableColumn<DataGeneratorRun, DurationModel> durationModel;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> minDuration;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> maxDuration;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> durationFixedFactor;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> nrCumulativeResources;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> minCumulDemand;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> maxCumulDemand;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> profilePieces;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> minCumulCapacity;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> maxCumulCapacity;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> nrOrders;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> minQty;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> maxQty;

	@FXML
	private TableColumn<DataGeneratorRun, Double> wipProbability;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> minWip;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> maxWip;

	@FXML
	private TableColumn<DataGeneratorRun, Double> downtimeProbability;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> minDowntime;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> maxDowntime;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> earliestDue;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> horizonDays;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> timeResolution;

	@FXML
	private TableColumn<DataGeneratorRun, Integer> seed;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getDataGeneratorRunData());
		ObservableList<ResourceModel> resourceModelValues = FXCollections.observableArrayList(ResourceModel.values());
		resourceModel.setCellFactory(ComboBoxTableCell.forTableColumn(resourceModelValues));
		resourceModel.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setResourceModel(event.getNewValue()); mainApp.reset();});
		ObservableList<DurationModel> durationModelValues = FXCollections.observableArrayList(DurationModel.values());
		durationModel.setCellFactory(ComboBoxTableCell.forTableColumn(durationModelValues));
		durationModel.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDurationModel(event.getNewValue()); mainApp.reset();});
	}

	public TableView<DataGeneratorRun> getTable() {
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
		choices.add("label");
		label.setCellValueFactory(new PropertyValueFactory<>("label"));
		label.setCellFactory(TextFieldTableCell.forTableColumn());
		label.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setLabel(event.getNewValue()); mainApp.reset();});
		choices.add("startDateTime");
		startDateTime.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
		startDateTime.setCellFactory(DateTimePickerTableCell.forTableColumn(DATETIME_CONVERTER));
		startDateTime.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setStartDateTime(event.getNewValue()); mainApp.reset();});
		choices.add("resourceModel");
		resourceModel.setCellValueFactory(new PropertyValueFactory<>("resourceModel"));
		choices.add("nrProducts");
		nrProducts.setCellValueFactory(new PropertyValueFactory<>("nrProducts"));
		nrProducts.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrProducts.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrProducts(event.getNewValue()); mainApp.reset();});
		choices.add("minStages");
		minStages.setCellValueFactory(new PropertyValueFactory<>("minStages"));
		minStages.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		minStages.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMinStages(event.getNewValue()); mainApp.reset();});
		choices.add("maxStages");
		maxStages.setCellValueFactory(new PropertyValueFactory<>("maxStages"));
		maxStages.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		maxStages.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMaxStages(event.getNewValue()); mainApp.reset();});
		choices.add("nrDisjunctiveResources");
		nrDisjunctiveResources.setCellValueFactory(new PropertyValueFactory<>("nrDisjunctiveResources"));
		nrDisjunctiveResources.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrDisjunctiveResources.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrDisjunctiveResources(event.getNewValue()); mainApp.reset();});
		choices.add("resourceProbability");
		resourceProbability.setCellValueFactory(new PropertyValueFactory<>("resourceProbability"));
		resourceProbability.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("")));
		resourceProbability.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setResourceProbability(event.getNewValue()); mainApp.reset();});
		choices.add("durationModel");
		durationModel.setCellValueFactory(new PropertyValueFactory<>("durationModel"));
		choices.add("minDuration");
		minDuration.setCellValueFactory(new PropertyValueFactory<>("minDuration"));
		minDuration.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		minDuration.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMinDuration(event.getNewValue()); mainApp.reset();});
		choices.add("maxDuration");
		maxDuration.setCellValueFactory(new PropertyValueFactory<>("maxDuration"));
		maxDuration.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		maxDuration.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMaxDuration(event.getNewValue()); mainApp.reset();});
		choices.add("durationFixedFactor");
		durationFixedFactor.setCellValueFactory(new PropertyValueFactory<>("durationFixedFactor"));
		durationFixedFactor.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		durationFixedFactor.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDurationFixedFactor(event.getNewValue()); mainApp.reset();});
		choices.add("nrCumulativeResources");
		nrCumulativeResources.setCellValueFactory(new PropertyValueFactory<>("nrCumulativeResources"));
		nrCumulativeResources.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrCumulativeResources.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrCumulativeResources(event.getNewValue()); mainApp.reset();});
		choices.add("minCumulDemand");
		minCumulDemand.setCellValueFactory(new PropertyValueFactory<>("minCumulDemand"));
		minCumulDemand.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		minCumulDemand.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMinCumulDemand(event.getNewValue()); mainApp.reset();});
		choices.add("maxCumulDemand");
		maxCumulDemand.setCellValueFactory(new PropertyValueFactory<>("maxCumulDemand"));
		maxCumulDemand.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		maxCumulDemand.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMaxCumulDemand(event.getNewValue()); mainApp.reset();});
		choices.add("profilePieces");
		profilePieces.setCellValueFactory(new PropertyValueFactory<>("profilePieces"));
		profilePieces.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		profilePieces.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setProfilePieces(event.getNewValue()); mainApp.reset();});
		choices.add("minCumulCapacity");
		minCumulCapacity.setCellValueFactory(new PropertyValueFactory<>("minCumulCapacity"));
		minCumulCapacity.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		minCumulCapacity.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMinCumulCapacity(event.getNewValue()); mainApp.reset();});
		choices.add("maxCumulCapacity");
		maxCumulCapacity.setCellValueFactory(new PropertyValueFactory<>("maxCumulCapacity"));
		maxCumulCapacity.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		maxCumulCapacity.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMaxCumulCapacity(event.getNewValue()); mainApp.reset();});
		choices.add("nrOrders");
		nrOrders.setCellValueFactory(new PropertyValueFactory<>("nrOrders"));
		nrOrders.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrOrders.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrOrders(event.getNewValue()); mainApp.reset();});
		choices.add("minQty");
		minQty.setCellValueFactory(new PropertyValueFactory<>("minQty"));
		minQty.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		minQty.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMinQty(event.getNewValue()); mainApp.reset();});
		choices.add("maxQty");
		maxQty.setCellValueFactory(new PropertyValueFactory<>("maxQty"));
		maxQty.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		maxQty.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMaxQty(event.getNewValue()); mainApp.reset();});
		choices.add("wipProbability");
		wipProbability.setCellValueFactory(new PropertyValueFactory<>("wipProbability"));
		wipProbability.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("")));
		wipProbability.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setWipProbability(event.getNewValue()); mainApp.reset();});
		choices.add("minWip");
		minWip.setCellValueFactory(new PropertyValueFactory<>("minWip"));
		minWip.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		minWip.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMinWip(event.getNewValue()); mainApp.reset();});
		choices.add("maxWip");
		maxWip.setCellValueFactory(new PropertyValueFactory<>("maxWip"));
		maxWip.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		maxWip.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMaxWip(event.getNewValue()); mainApp.reset();});
		choices.add("downtimeProbability");
		downtimeProbability.setCellValueFactory(new PropertyValueFactory<>("downtimeProbability"));
		downtimeProbability.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("")));
		downtimeProbability.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDowntimeProbability(event.getNewValue()); mainApp.reset();});
		choices.add("minDowntime");
		minDowntime.setCellValueFactory(new PropertyValueFactory<>("minDowntime"));
		minDowntime.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		minDowntime.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMinDowntime(event.getNewValue()); mainApp.reset();});
		choices.add("maxDowntime");
		maxDowntime.setCellValueFactory(new PropertyValueFactory<>("maxDowntime"));
		maxDowntime.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		maxDowntime.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setMaxDowntime(event.getNewValue()); mainApp.reset();});
		choices.add("earliestDue");
		earliestDue.setCellValueFactory(new PropertyValueFactory<>("earliestDue"));
		earliestDue.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		earliestDue.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setEarliestDue(event.getNewValue()); mainApp.reset();});
		choices.add("horizonDays");
		horizonDays.setCellValueFactory(new PropertyValueFactory<>("horizonDays"));
		horizonDays.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		horizonDays.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setHorizonDays(event.getNewValue()); mainApp.reset();});
		choices.add("timeResolution");
		timeResolution.setCellValueFactory(new PropertyValueFactory<>("timeResolution"));
		timeResolution.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		timeResolution.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setTimeResolution(event.getNewValue()); mainApp.reset();});
		choices.add("seed");
		seed.setCellValueFactory(new PropertyValueFactory<>("seed"));
		seed.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		seed.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSeed(event.getNewValue()); mainApp.reset();});
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getDataGeneratorRunData());
		try {
			ObservableList<DataGeneratorRun> filteredItems = FXCollections.observableArrayList();
			for (DataGeneratorRun item : table.getItems()) {
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

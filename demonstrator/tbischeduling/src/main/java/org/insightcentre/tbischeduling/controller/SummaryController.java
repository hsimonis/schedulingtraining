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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.Summary;

/**
 * Generated code
 */
public class SummaryController extends Table3Controller {
	@FXML
	private TableView<Summary> table;

	@FXML
	private TableColumn<Summary, String> name;

	@FXML
	private TableColumn<Summary, Integer> nr;

	@FXML
	private TableColumn<Summary, Integer> nrJobs;

	@FXML
	private TableColumn<Summary, Integer> nrStages;

	@FXML
	private TableColumn<Summary, Integer> capacity;

	@FXML
	private TableColumn<Summary, Integer> localFS;

	@FXML
	private TableColumn<Summary, Integer> localPNW;

	@FXML
	private TableColumn<Summary, Integer> localNW;

	@FXML
	private TableColumn<Summary, Integer> cpFS;

	@FXML
	private TableColumn<Summary, Integer> cpPNW;

	@FXML
	private TableColumn<Summary, Integer> cpNW;

	@FXML
	private TableColumn<Summary, Integer> boundFS;

	@FXML
	private TableColumn<Summary, Integer> boundPNW;

	@FXML
	private TableColumn<Summary, Integer> boundNW;

	@FXML
	private TableColumn<Summary, Double> gapFS;

	@FXML
	private TableColumn<Summary, Double> gapPNW;

	@FXML
	private TableColumn<Summary, Double> gapNW;

	@FXML
	private TableColumn<Summary, Integer> fsCPOCost;

	@FXML
	private TableColumn<Summary, Integer> fsCPSatCost;

	@FXML
	private TableColumn<Summary, Integer> pnwCPOCost;

	@FXML
	private TableColumn<Summary, Integer> pnwCPSatCost;

	@FXML
	private TableColumn<Summary, Integer> nwCPOCost;

	@FXML
	private TableColumn<Summary, Integer> nwCPSatCost;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setEditable(true);
		table.setItems(mainApp.getSummaryData());
	}

	public TableView<Summary> getTable() {
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
		choices.add("nrJobs");
		nrJobs.setCellValueFactory(new PropertyValueFactory<>("nrJobs"));
		nrJobs.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrJobs.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrJobs(event.getNewValue()); mainApp.reset();});
		choices.add("nrStages");
		nrStages.setCellValueFactory(new PropertyValueFactory<>("nrStages"));
		nrStages.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrStages.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrStages(event.getNewValue()); mainApp.reset();});
		choices.add("capacity");
		capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
		capacity.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		capacity.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setCapacity(event.getNewValue()); mainApp.reset();});
		choices.add("localFS");
		localFS.setCellValueFactory(new PropertyValueFactory<>("localFS"));
		localFS.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		localFS.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setLocalFS(event.getNewValue()); mainApp.reset();});
		choices.add("localPNW");
		localPNW.setCellValueFactory(new PropertyValueFactory<>("localPNW"));
		localPNW.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		localPNW.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setLocalPNW(event.getNewValue()); mainApp.reset();});
		choices.add("localNW");
		localNW.setCellValueFactory(new PropertyValueFactory<>("localNW"));
		localNW.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		localNW.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setLocalNW(event.getNewValue()); mainApp.reset();});
		choices.add("cpFS");
		cpFS.setCellValueFactory(new PropertyValueFactory<>("cpFS"));
		cpFS.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		cpFS.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setCpFS(event.getNewValue()); mainApp.reset();});
		choices.add("cpPNW");
		cpPNW.setCellValueFactory(new PropertyValueFactory<>("cpPNW"));
		cpPNW.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		cpPNW.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setCpPNW(event.getNewValue()); mainApp.reset();});
		choices.add("cpNW");
		cpNW.setCellValueFactory(new PropertyValueFactory<>("cpNW"));
		cpNW.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		cpNW.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setCpNW(event.getNewValue()); mainApp.reset();});
		choices.add("boundFS");
		boundFS.setCellValueFactory(new PropertyValueFactory<>("boundFS"));
		boundFS.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		boundFS.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setBoundFS(event.getNewValue()); mainApp.reset();});
		choices.add("boundPNW");
		boundPNW.setCellValueFactory(new PropertyValueFactory<>("boundPNW"));
		boundPNW.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		boundPNW.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setBoundPNW(event.getNewValue()); mainApp.reset();});
		choices.add("boundNW");
		boundNW.setCellValueFactory(new PropertyValueFactory<>("boundNW"));
		boundNW.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		boundNW.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setBoundNW(event.getNewValue()); mainApp.reset();});
		choices.add("gapFS");
		gapFS.setCellValueFactory(new PropertyValueFactory<>("gapFS"));
		gapFS.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		gapFS.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setGapFS(event.getNewValue()); mainApp.reset();});
		choices.add("gapPNW");
		gapPNW.setCellValueFactory(new PropertyValueFactory<>("gapPNW"));
		gapPNW.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		gapPNW.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setGapPNW(event.getNewValue()); mainApp.reset();});
		choices.add("gapNW");
		gapNW.setCellValueFactory(new PropertyValueFactory<>("gapNW"));
		gapNW.setCellFactory(TextFieldTableCell.forTableColumn(getDoubleConverter("#,##0.00")));
		gapNW.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setGapNW(event.getNewValue()); mainApp.reset();});
		choices.add("fsCPOCost");
		fsCPOCost.setCellValueFactory(new PropertyValueFactory<>("fsCPOCost"));
		fsCPOCost.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		fsCPOCost.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setFsCPOCost(event.getNewValue()); mainApp.reset();});
		choices.add("fsCPSatCost");
		fsCPSatCost.setCellValueFactory(new PropertyValueFactory<>("fsCPSatCost"));
		fsCPSatCost.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		fsCPSatCost.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setFsCPSatCost(event.getNewValue()); mainApp.reset();});
		choices.add("pnwCPOCost");
		pnwCPOCost.setCellValueFactory(new PropertyValueFactory<>("pnwCPOCost"));
		pnwCPOCost.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		pnwCPOCost.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setPnwCPOCost(event.getNewValue()); mainApp.reset();});
		choices.add("pnwCPSatCost");
		pnwCPSatCost.setCellValueFactory(new PropertyValueFactory<>("pnwCPSatCost"));
		pnwCPSatCost.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		pnwCPSatCost.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setPnwCPSatCost(event.getNewValue()); mainApp.reset();});
		choices.add("nwCPOCost");
		nwCPOCost.setCellValueFactory(new PropertyValueFactory<>("nwCPOCost"));
		nwCPOCost.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nwCPOCost.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNwCPOCost(event.getNewValue()); mainApp.reset();});
		choices.add("nwCPSatCost");
		nwCPSatCost.setCellValueFactory(new PropertyValueFactory<>("nwCPSatCost"));
		nwCPSatCost.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nwCPSatCost.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNwCPSatCost(event.getNewValue()); mainApp.reset();});
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getSummaryData());
		try {
			ObservableList<Summary> filteredItems = FXCollections.observableArrayList();
			for (Summary item : table.getItems()) {
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

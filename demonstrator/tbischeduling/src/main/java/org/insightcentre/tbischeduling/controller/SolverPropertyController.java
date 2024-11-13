package org.insightcentre.tbischeduling.controller;

import framework.gui.AbstractJfxMainWindow;
import framework.gui.DateTimePickerTableCell;
import framework.gui.Table3Controller;
import framework.types.DateTime;
import java.lang.Boolean;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.reflect.Field;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.ModelType;
import org.insightcentre.tbischeduling.datamodel.ObjectiveType;
import org.insightcentre.tbischeduling.datamodel.SolverBackend;
import org.insightcentre.tbischeduling.datamodel.SolverProperty;

/**
 * Generated at 20:35:12 on 2024-11-12 */
public class SolverPropertyController extends Table3Controller {
	@FXML
	private TableView<SolverProperty> table;

	@FXML
	private TableColumn<SolverProperty, String> name;

	@FXML
	private TableColumn<SolverProperty, String> label;

	@FXML
	private TableColumn<SolverProperty, String> description;

	@FXML
	private TableColumn<SolverProperty, DateTime> startDateTime;

	@FXML
	private TableColumn<SolverProperty, Boolean> enforceReleaseDate;

	@FXML
	private TableColumn<SolverProperty, Boolean> enforceDueDate;

	@FXML
	private TableColumn<SolverProperty, Boolean> enforceCumulative;

	@FXML
	private TableColumn<SolverProperty, Boolean> enforceWip;

	@FXML
	private TableColumn<SolverProperty, Boolean> enforceDowntime;

	@FXML
	private TableColumn<SolverProperty, Boolean> enforceSetup;

	@FXML
	private TableColumn<SolverProperty, Boolean> enforceTransportTime;

	@FXML
	private TableColumn<SolverProperty, Boolean> relaxSequence;

	@FXML
	private TableColumn<SolverProperty, Boolean> addSameOrder;

	@FXML
	private TableColumn<SolverProperty, ModelType> modelType;

	@FXML
	private TableColumn<SolverProperty, SolverBackend> solverBackend;

	@FXML
	private TableColumn<SolverProperty, ObjectiveType> objectiveType;

	@FXML
	private TableColumn<SolverProperty, Integer> weightMakespan;

	@FXML
	private TableColumn<SolverProperty, Integer> weightFlowtime;

	@FXML
	private TableColumn<SolverProperty, Integer> weightLateness;

	@FXML
	private TableColumn<SolverProperty, Integer> weightEarliness;

	@FXML
	private TableColumn<SolverProperty, Integer> timeout;

	@FXML
	private TableColumn<SolverProperty, Integer> nrThreads;

	@FXML
	private TableColumn<SolverProperty, Integer> seed;

	@FXML
	private TableColumn<SolverProperty, Boolean> removeSolution;

	@FXML
	private TableColumn<SolverProperty, Boolean> produceReport;

	@FXML
	private TableColumn<SolverProperty, Boolean> producePDF;

	private GeneratedJfxApp mainApp;

	@Override
	public void setMainApp(AbstractJfxMainWindow app) {
		mainApp = (GeneratedJfxApp) app;
		table.setItems(mainApp.getSolverPropertyData());
		ObservableList<ModelType> modelTypeValues = FXCollections.observableArrayList(ModelType.values());
		modelType.setCellFactory(ComboBoxTableCell.forTableColumn(modelTypeValues));
		modelType.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setModelType(event.getNewValue()); mainApp.reset();});
		ObservableList<SolverBackend> solverBackendValues = FXCollections.observableArrayList(SolverBackend.values());
		solverBackend.setCellFactory(ComboBoxTableCell.forTableColumn(solverBackendValues));
		solverBackend.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSolverBackend(event.getNewValue()); mainApp.reset();});
		ObservableList<ObjectiveType> objectiveTypeValues = FXCollections.observableArrayList(ObjectiveType.values());
		objectiveType.setCellFactory(ComboBoxTableCell.forTableColumn(objectiveTypeValues));
		objectiveType.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setObjectiveType(event.getNewValue()); mainApp.reset();});
	}

	public TableView<SolverProperty> getTable() {
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
		choices.add("description");
		description.setCellValueFactory(new PropertyValueFactory<>("description"));
		description.setCellFactory(TextFieldTableCell.forTableColumn());
		description.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setDescription(event.getNewValue()); mainApp.reset();});
		choices.add("startDateTime");
		startDateTime.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
		startDateTime.setCellFactory(DateTimePickerTableCell.forTableColumn(DATETIME_CONVERTER));
		startDateTime.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setStartDateTime(event.getNewValue()); mainApp.reset();});
		choices.add("enforceReleaseDate");
		enforceReleaseDate.setCellValueFactory(new EnforceReleaseDateCallback());
		enforceReleaseDate.setCellFactory(CheckBoxTableCell.forTableColumn(enforceReleaseDate));
		choices.add("enforceDueDate");
		enforceDueDate.setCellValueFactory(new EnforceDueDateCallback());
		enforceDueDate.setCellFactory(CheckBoxTableCell.forTableColumn(enforceDueDate));
		choices.add("enforceCumulative");
		enforceCumulative.setCellValueFactory(new EnforceCumulativeCallback());
		enforceCumulative.setCellFactory(CheckBoxTableCell.forTableColumn(enforceCumulative));
		choices.add("enforceWip");
		enforceWip.setCellValueFactory(new EnforceWipCallback());
		enforceWip.setCellFactory(CheckBoxTableCell.forTableColumn(enforceWip));
		choices.add("enforceDowntime");
		enforceDowntime.setCellValueFactory(new EnforceDowntimeCallback());
		enforceDowntime.setCellFactory(CheckBoxTableCell.forTableColumn(enforceDowntime));
		choices.add("enforceSetup");
		enforceSetup.setCellValueFactory(new EnforceSetupCallback());
		enforceSetup.setCellFactory(CheckBoxTableCell.forTableColumn(enforceSetup));
		choices.add("enforceTransportTime");
		enforceTransportTime.setCellValueFactory(new EnforceTransportTimeCallback());
		enforceTransportTime.setCellFactory(CheckBoxTableCell.forTableColumn(enforceTransportTime));
		choices.add("relaxSequence");
		relaxSequence.setCellValueFactory(new RelaxSequenceCallback());
		relaxSequence.setCellFactory(CheckBoxTableCell.forTableColumn(relaxSequence));
		choices.add("addSameOrder");
		addSameOrder.setCellValueFactory(new AddSameOrderCallback());
		addSameOrder.setCellFactory(CheckBoxTableCell.forTableColumn(addSameOrder));
		choices.add("modelType");
		modelType.setCellValueFactory(new PropertyValueFactory<>("modelType"));
		choices.add("solverBackend");
		solverBackend.setCellValueFactory(new PropertyValueFactory<>("solverBackend"));
		choices.add("objectiveType");
		objectiveType.setCellValueFactory(new PropertyValueFactory<>("objectiveType"));
		choices.add("weightMakespan");
		weightMakespan.setCellValueFactory(new PropertyValueFactory<>("weightMakespan"));
		weightMakespan.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		weightMakespan.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setWeightMakespan(event.getNewValue()); mainApp.reset();});
		choices.add("weightFlowtime");
		weightFlowtime.setCellValueFactory(new PropertyValueFactory<>("weightFlowtime"));
		weightFlowtime.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		weightFlowtime.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setWeightFlowtime(event.getNewValue()); mainApp.reset();});
		choices.add("weightLateness");
		weightLateness.setCellValueFactory(new PropertyValueFactory<>("weightLateness"));
		weightLateness.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		weightLateness.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setWeightLateness(event.getNewValue()); mainApp.reset();});
		choices.add("weightEarliness");
		weightEarliness.setCellValueFactory(new PropertyValueFactory<>("weightEarliness"));
		weightEarliness.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		weightEarliness.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setWeightEarliness(event.getNewValue()); mainApp.reset();});
		choices.add("timeout");
		timeout.setCellValueFactory(new PropertyValueFactory<>("timeout"));
		timeout.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		timeout.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setTimeout(event.getNewValue()); mainApp.reset();});
		choices.add("nrThreads");
		nrThreads.setCellValueFactory(new PropertyValueFactory<>("nrThreads"));
		nrThreads.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		nrThreads.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setNrThreads(event.getNewValue()); mainApp.reset();});
		choices.add("seed");
		seed.setCellValueFactory(new PropertyValueFactory<>("seed"));
		seed.setCellFactory(TextFieldTableCell.forTableColumn(INTEGER_CONVERTER));
		seed.setOnEditCommit(event -> {table.getSelectionModel().getSelectedItem().setSeed(event.getNewValue()); mainApp.reset();});
		choices.add("removeSolution");
		removeSolution.setCellValueFactory(new RemoveSolutionCallback());
		removeSolution.setCellFactory(CheckBoxTableCell.forTableColumn(removeSolution));
		choices.add("produceReport");
		produceReport.setCellValueFactory(new ProduceReportCallback());
		produceReport.setCellFactory(CheckBoxTableCell.forTableColumn(produceReport));
		choices.add("producePDF");
		producePDF.setCellValueFactory(new ProducePDFCallback());
		producePDF.setCellFactory(CheckBoxTableCell.forTableColumn(producePDF));
		initialize(choices);
	}

	@Override
	public void filter(String attribute, String comparison, String text) {
		table.setItems(mainApp.getSolverPropertyData());
		try {
			ObservableList<SolverProperty> filteredItems = FXCollections.observableArrayList();
			for (SolverProperty item : table.getItems()) {
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

	class EnforceReleaseDateCallback implements Callback<TableColumn.CellDataFeatures<SolverProperty, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SolverProperty, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().enforceReleaseDateWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setEnforceReleaseDate(newValue);
				}
			});
			return prop;
		}
	}

	class EnforceDueDateCallback implements Callback<TableColumn.CellDataFeatures<SolverProperty, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SolverProperty, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().enforceDueDateWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setEnforceDueDate(newValue);
				}
			});
			return prop;
		}
	}

	class EnforceCumulativeCallback implements Callback<TableColumn.CellDataFeatures<SolverProperty, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SolverProperty, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().enforceCumulativeWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setEnforceCumulative(newValue);
				}
			});
			return prop;
		}
	}

	class EnforceWipCallback implements Callback<TableColumn.CellDataFeatures<SolverProperty, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SolverProperty, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().enforceWipWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setEnforceWip(newValue);
				}
			});
			return prop;
		}
	}

	class EnforceDowntimeCallback implements Callback<TableColumn.CellDataFeatures<SolverProperty, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SolverProperty, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().enforceDowntimeWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setEnforceDowntime(newValue);
				}
			});
			return prop;
		}
	}

	class EnforceSetupCallback implements Callback<TableColumn.CellDataFeatures<SolverProperty, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SolverProperty, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().enforceSetupWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setEnforceSetup(newValue);
				}
			});
			return prop;
		}
	}

	class EnforceTransportTimeCallback implements Callback<TableColumn.CellDataFeatures<SolverProperty, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SolverProperty, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().enforceTransportTimeWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setEnforceTransportTime(newValue);
				}
			});
			return prop;
		}
	}

	class RelaxSequenceCallback implements Callback<TableColumn.CellDataFeatures<SolverProperty, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SolverProperty, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().relaxSequenceWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setRelaxSequence(newValue);
				}
			});
			return prop;
		}
	}

	class AddSameOrderCallback implements Callback<TableColumn.CellDataFeatures<SolverProperty, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SolverProperty, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().addSameOrderWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setAddSameOrder(newValue);
				}
			});
			return prop;
		}
	}

	class RemoveSolutionCallback implements Callback<TableColumn.CellDataFeatures<SolverProperty, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SolverProperty, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().removeSolutionWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setRemoveSolution(newValue);
				}
			});
			return prop;
		}
	}

	class ProduceReportCallback implements Callback<TableColumn.CellDataFeatures<SolverProperty, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SolverProperty, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().produceReportWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setProduceReport(newValue);
				}
			});
			return prop;
		}
	}

	class ProducePDFCallback implements Callback<TableColumn.CellDataFeatures<SolverProperty, Boolean>, ObservableValue<Boolean>> {
		@Override
		public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SolverProperty, Boolean> cellData) {
			Property<Boolean> prop = cellData.getValue().producePDFWrapperProperty();
			prop.addListener(new ChangeListener<Boolean>() {
				@Override
				@SuppressWarnings("rawtypes")
				public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
					cellData.getValue().setProducePDF(newValue);
				}
			});
			return prop;
		}
	}
}

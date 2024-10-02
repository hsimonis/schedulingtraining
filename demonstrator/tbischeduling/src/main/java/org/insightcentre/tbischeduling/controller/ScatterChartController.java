package org.insightcentre.tbischeduling.controller;

import java.lang.Exception;
import java.lang.Number;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

/**
 * Generated at 17:10:44 on 2024-10-01 */
public class ScatterChartController extends ChartXYFilterController {
	@FXML
	private ScatterChart<Number, Number> chart;

	@FXML
	@SuppressWarnings("unchecked")
	private void initialize() {
		ObservableList<String> attributeNames = FXCollections.observableArrayList();
		ObservableList<String> filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("dirty");
		filterNames.add("valid");
		attributeNames.add("dataFileVersionNumber");
		filterNames.add("dataFileVersionNumber");
		filterNames.add("dataFile");
		attributeNames.add("horizon");
		filterNames.add("horizon");
		attributeNames.add("timeResolution");
		filterNames.add("timeResolution");
		choicesMap.put("Scenario", attributeNames);
		filterMap.put("Scenario", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("type");
		filterNames.add("item");
		filterNames.add("classString");
		filterNames.add("name");
		filterNames.add("attrString");
		filterNames.add("item");
		filterNames.add("type");
		filterNames.add("limit");
		filterNames.add("name");
		filterNames.add("classDesc");
		filterNames.add("item");
		filterNames.add("field");
		filterNames.add("value");
		filterNames.add("description");
		filterNames.add("severity");
		filterNames.add("name");
		filterNames.add("timePointsAsDate");
		attributeNames.add("nrProducts");
		filterNames.add("nrProducts");
		attributeNames.add("nrProcesses");
		filterNames.add("nrProcesses");
		attributeNames.add("nrDisjunctiveResources");
		filterNames.add("nrDisjunctiveResources");
		attributeNames.add("nrCumulativeResources");
		filterNames.add("nrCumulativeResources");
		attributeNames.add("nrOrders");
		filterNames.add("nrOrders");
		attributeNames.add("nrJobs");
		filterNames.add("nrJobs");
		attributeNames.add("nrTasks");
		filterNames.add("nrTasks");
		choicesMap.put("Problem", attributeNames);
		filterMap.put("Problem", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("defaultProcess");
		filterNames.add("name");
		filterNames.add("name");
		filterNames.add("process");
		attributeNames.add("stage");
		filterNames.add("stage");
		attributeNames.add("durationFixed");
		filterNames.add("durationFixed");
		attributeNames.add("durationPerUnit");
		filterNames.add("durationPerUnit");
		choicesMap.put("ProcessStep", attributeNames);
		filterMap.put("ProcessStep", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("before");
		filterNames.add("after");
		filterNames.add("sequenceType");
		attributeNames.add("offset");
		filterNames.add("offset");
		choicesMap.put("ProcessSequence", attributeNames);
		filterMap.put("ProcessSequence", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("disjunctiveResource");
		filterNames.add("processStep");
		filterNames.add("name");
		filterNames.add("cumulativeResource");
		filterNames.add("processStep");
		attributeNames.add("demand");
		filterNames.add("demand");
		choicesMap.put("CumulativeNeed", attributeNames);
		filterMap.put("CumulativeNeed", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("cumulativeResource");
		attributeNames.add("from");
		filterNames.add("from");
		attributeNames.add("capacity");
		filterNames.add("capacity");
		choicesMap.put("CumulativeProfile", attributeNames);
		filterMap.put("CumulativeProfile", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("shortName");
		filterNames.add("name");
		filterNames.add("name");
		filterNames.add("product");
		filterNames.add("process");
		attributeNames.add("qty");
		filterNames.add("qty");
		attributeNames.add("due");
		filterNames.add("due");
		filterNames.add("dueDate");
		attributeNames.add("release");
		filterNames.add("release");
		filterNames.add("releaseDate");
		attributeNames.add("latenessWeight");
		filterNames.add("latenessWeight");
		attributeNames.add("earlinessWeight");
		filterNames.add("earlinessWeight");
		choicesMap.put("Order", attributeNames);
		filterMap.put("Order", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("order");
		filterNames.add("process");
		filterNames.add("order.product");
		attributeNames.add("order.release");
		filterNames.add("order.release");
		attributeNames.add("order.due");
		filterNames.add("order.due");
		attributeNames.add("order.qty");
		filterNames.add("order.qty");
		choicesMap.put("Job", attributeNames);
		filterMap.put("Job", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("shortName");
		filterNames.add("job");
		filterNames.add("processStep");
		attributeNames.add("duration");
		filterNames.add("duration");
		attributeNames.add("stage");
		filterNames.add("stage");
		filterNames.add("machines");
		filterNames.add("precedes");
		filterNames.add("job.order");
		filterNames.add("job.order.product");
		attributeNames.add("job.order.qty");
		filterNames.add("job.order.qty");
		attributeNames.add("processStep.durationFixed");
		filterNames.add("processStep.durationFixed");
		attributeNames.add("processStep.durationPerUnit");
		filterNames.add("processStep.durationPerUnit");
		choicesMap.put("Task", attributeNames);
		filterMap.put("Task", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		attributeNames.add("until");
		filterNames.add("until");
		filterNames.add("untilDate");
		filterNames.add("disjunctiveResource");
		choicesMap.put("WiP", attributeNames);
		filterMap.put("WiP", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		attributeNames.add("from");
		filterNames.add("from");
		attributeNames.add("to");
		filterNames.add("to");
		filterNames.add("fromDate");
		filterNames.add("toDate");
		filterNames.add("disjunctiveResource");
		choicesMap.put("Downtime", attributeNames);
		filterMap.put("Downtime", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("label");
		filterNames.add("description");
		filterNames.add("modelType");
		filterNames.add("solverBackend");
		filterNames.add("objectiveType");
		filterNames.add("enforceReleaseDate");
		filterNames.add("enforceDueDate");
		attributeNames.add("timeout");
		filterNames.add("timeout");
		attributeNames.add("nrThreads");
		filterNames.add("nrThreads");
		attributeNames.add("seed");
		filterNames.add("seed");
		filterNames.add("removeSolution");
		filterNames.add("solverStatus");
		attributeNames.add("time");
		filterNames.add("time");
		choicesMap.put("SolverRun", attributeNames);
		filterMap.put("SolverRun", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("solverRun");
		attributeNames.add("objectiveValue");
		filterNames.add("objectiveValue");
		filterNames.add("solverStatus");
		attributeNames.add("bound");
		filterNames.add("bound");
		attributeNames.add("gap");
		filterNames.add("gap");
		attributeNames.add("makespan");
		filterNames.add("makespan");
		attributeNames.add("flowtime");
		filterNames.add("flowtime");
		attributeNames.add("totalLateness");
		filterNames.add("totalLateness");
		attributeNames.add("maxLateness");
		filterNames.add("maxLateness");
		attributeNames.add("nrLate");
		filterNames.add("nrLate");
		attributeNames.add("weightedLateness");
		filterNames.add("weightedLateness");
		attributeNames.add("totalEarliness");
		filterNames.add("totalEarliness");
		attributeNames.add("maxEarliness");
		filterNames.add("maxEarliness");
		attributeNames.add("nrEarly");
		filterNames.add("nrEarly");
		attributeNames.add("weightedEarliness");
		filterNames.add("weightedEarliness");
		attributeNames.add("percentEarly");
		filterNames.add("percentEarly");
		attributeNames.add("percentLate");
		filterNames.add("percentLate");
		filterNames.add("solverRun.modelType");
		filterNames.add("solverRun.solverBackend");
		filterNames.add("solverRun.objectiveType");
		filterNames.add("solverRun.enforceReleaseDate");
		filterNames.add("solverRun.enforceDueDate");
		attributeNames.add("solverRun.timeout");
		filterNames.add("solverRun.timeout");
		attributeNames.add("solverRun.nrThreads");
		filterNames.add("solverRun.nrThreads");
		attributeNames.add("solverRun.seed");
		filterNames.add("solverRun.seed");
		filterNames.add("solverRun.removeSolution");
		attributeNames.add("solverRun.time");
		filterNames.add("solverRun.time");
		choicesMap.put("Solution", attributeNames);
		filterMap.put("Solution", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("solution");
		filterNames.add("job");
		attributeNames.add("late");
		filterNames.add("late");
		attributeNames.add("early");
		filterNames.add("early");
		attributeNames.add("duration");
		filterNames.add("duration");
		attributeNames.add("start");
		filterNames.add("start");
		attributeNames.add("end");
		filterNames.add("end");
		filterNames.add("startDate");
		filterNames.add("endDate");
		filterNames.add("job.order");
		filterNames.add("job.order.product");
		filterNames.add("job.process");
		attributeNames.add("job.order.release");
		filterNames.add("job.order.release");
		attributeNames.add("job.order.due");
		filterNames.add("job.order.due");
		attributeNames.add("job.order.qty");
		filterNames.add("job.order.qty");
		choicesMap.put("JobAssignment", attributeNames);
		filterMap.put("JobAssignment", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("task");
		filterNames.add("jobAssignment");
		filterNames.add("disjunctiveResource");
		attributeNames.add("duration");
		filterNames.add("duration");
		attributeNames.add("start");
		filterNames.add("start");
		attributeNames.add("end");
		filterNames.add("end");
		filterNames.add("startDate");
		filterNames.add("endDate");
		filterNames.add("jobAssignment.solution");
		filterNames.add("task.job.order");
		attributeNames.add("task.job.order.release");
		filterNames.add("task.job.order.release");
		attributeNames.add("task.job.order.due");
		filterNames.add("task.job.order.due");
		filterNames.add("task.job.order.product");
		attributeNames.add("task.job.order.qty");
		filterNames.add("task.job.order.qty");
		filterNames.add("task.processStep");
		attributeNames.add("task.processStep.durationFixed");
		filterNames.add("task.processStep.durationFixed");
		attributeNames.add("task.processStep.durationPerUnit");
		filterNames.add("task.processStep.durationPerUnit");
		choicesMap.put("TaskAssignment", attributeNames);
		filterMap.put("TaskAssignment", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("disjunctiveResource");
		filterNames.add("solution");
		attributeNames.add("earliest");
		filterNames.add("earliest");
		attributeNames.add("latest");
		filterNames.add("latest");
		attributeNames.add("active");
		filterNames.add("active");
		attributeNames.add("use");
		filterNames.add("use");
		attributeNames.add("utilization");
		filterNames.add("utilization");
		choicesMap.put("ResourceUtilization", attributeNames);
		filterMap.put("ResourceUtilization", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		ObservableList<String> classes = FXCollections.observableArrayList();
		classes.addAll(choicesMap.keySet());
		classChoiceBox.getItems().addAll(classes);
		ObservableList<String> compOps = FXCollections.observableArrayList();
		compOps.addAll(filterComparisons);
		filterComparisonBox.getItems().addAll(compOps);
		chart.setLegendVisible(false);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void drawChart() {
		String className = classChoiceBox.getSelectionModel().getSelectedItem();
		String attributeXName = attributeChoiceBox.getSelectionModel().getSelectedItem();
		String attributeYName = attributeYChoiceBox.getSelectionModel().getSelectedItem();
		String attributeFilterName = attributeFilterBox.getSelectionModel().getSelectedItem();
		String filterComparisonName = filterComparisonBox.getSelectionModel().getSelectedItem();
		String filterTextName = filterTextField.getText();
		String attributeGroupName = attributeGroupBox.getSelectionModel().getSelectedItem();
		if (className == null || attributeXName == null|| attributeYName == null) {
			return;
		}
		try {
			ObservableList objectList = null;
			if (className.equals("Scenario")) {
				objectList = mainApp.getScenarioData();
			}
			else if (className.equals("ApplicationDifference")) {
				objectList = mainApp.getApplicationDifferenceData();
			}
			else if (className.equals("ApplicationWarning")) {
				objectList = mainApp.getApplicationWarningData();
			}
			else if (className.equals("InputError")) {
				objectList = mainApp.getInputErrorData();
			}
			else if (className.equals("Problem")) {
				objectList = mainApp.getProblemData();
			}
			else if (className.equals("Product")) {
				objectList = mainApp.getProductData();
			}
			else if (className.equals("Process")) {
				objectList = mainApp.getProcessData();
			}
			else if (className.equals("ProcessStep")) {
				objectList = mainApp.getProcessStepData();
			}
			else if (className.equals("ProcessSequence")) {
				objectList = mainApp.getProcessSequenceData();
			}
			else if (className.equals("ResourceNeed")) {
				objectList = mainApp.getResourceNeedData();
			}
			else if (className.equals("CumulativeNeed")) {
				objectList = mainApp.getCumulativeNeedData();
			}
			else if (className.equals("CumulativeProfile")) {
				objectList = mainApp.getCumulativeProfileData();
			}
			else if (className.equals("DisjunctiveResource")) {
				objectList = mainApp.getDisjunctiveResourceData();
			}
			else if (className.equals("CumulativeResource")) {
				objectList = mainApp.getCumulativeResourceData();
			}
			else if (className.equals("Order")) {
				objectList = mainApp.getOrderData();
			}
			else if (className.equals("Job")) {
				objectList = mainApp.getJobData();
			}
			else if (className.equals("Task")) {
				objectList = mainApp.getTaskData();
			}
			else if (className.equals("WiP")) {
				objectList = mainApp.getWiPData();
			}
			else if (className.equals("Downtime")) {
				objectList = mainApp.getDowntimeData();
			}
			else if (className.equals("SolverRun")) {
				objectList = mainApp.getSolverRunData();
			}
			else if (className.equals("Solution")) {
				objectList = mainApp.getSolutionData();
			}
			else if (className.equals("JobAssignment")) {
				objectList = mainApp.getJobAssignmentData();
			}
			else if (className.equals("TaskAssignment")) {
				objectList = mainApp.getTaskAssignmentData();
			}
			else if (className.equals("ResourceUtilization")) {
				objectList = mainApp.getResourceUtilizationData();
			}
			if (objectList != null) {
				XYChart.Series series = new XYChart.Series();
				for (Object obj : objectList) {
					Object objX = deref(obj,attributeXName);
					Object objY = deref(obj,attributeYName);
					Object objFilter = obj;
					if(attributeFilterName != null && !attributeFilterName.equals(filterNone)) {
						objFilter=deref(obj,attributeFilterName);
					}
					if (keepFiltered(objFilter,attributeFilterName,filterComparisonName,filterTextName) && Number.class.isAssignableFrom(objX.getClass()) && Number.class.isAssignableFrom(objY.getClass())) {
						series.getData().add(new XYChart.Data(objX,objY));
					}
				}
				chart.getData().clear();
				chart.getData().add(series);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

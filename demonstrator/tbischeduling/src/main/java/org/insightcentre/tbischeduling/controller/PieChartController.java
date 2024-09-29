package org.insightcentre.tbischeduling.controller;

import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

/**
 * Generated at 10:29:29 on 2024-09-28 */
public class PieChartController extends ChartController {
	public static final Double MIN_SLICE_PERCENTAGE = 1.0d;

	@FXML
	private PieChart chart;

	@FXML
	@SuppressWarnings("unchecked")
	private void initialize() {
		ObservableList<String> attributeNames = null;
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("dirty");
		attributeNames.add("valid");
		attributeNames.add("dataFileVersionNumber");
		attributeNames.add("dataFile");
		attributeNames.add("horizon");
		attributeNames.add("timeResolution");
		choicesMap.put("Scenario", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("type");
		attributeNames.add("item");
		choicesMap.put("ApplicationDifference", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("classString");
		attributeNames.add("name");
		attributeNames.add("attrString");
		attributeNames.add("item");
		attributeNames.add("type");
		attributeNames.add("limit");
		choicesMap.put("ApplicationWarning", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("classDesc");
		attributeNames.add("item");
		attributeNames.add("field");
		attributeNames.add("value");
		attributeNames.add("description");
		attributeNames.add("severity");
		choicesMap.put("InputError", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("timePointsAsDate");
		attributeNames.add("nrProducts");
		attributeNames.add("nrProcesses");
		attributeNames.add("nrDisjunctiveResources");
		attributeNames.add("nrCumulativeResources");
		attributeNames.add("nrOrders");
		attributeNames.add("nrJobs");
		attributeNames.add("nrTasks");
		choicesMap.put("Problem", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("defaultProcess");
		choicesMap.put("Product", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		choicesMap.put("Process", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("process");
		attributeNames.add("durationFixed");
		attributeNames.add("durationPerUnit");
		choicesMap.put("ProcessStep", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("before");
		attributeNames.add("after");
		attributeNames.add("sequenceType");
		attributeNames.add("offset");
		choicesMap.put("ProcessSequence", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("disjunctiveResource");
		attributeNames.add("processStep");
		choicesMap.put("ResourceNeed", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("cumulativeResource");
		attributeNames.add("processStep");
		attributeNames.add("demand");
		choicesMap.put("CumulativeNeed", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("cumulativeResource");
		attributeNames.add("from");
		attributeNames.add("capacity");
		choicesMap.put("CumulativeProfile", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("shortName");
		choicesMap.put("DisjunctiveResource", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		choicesMap.put("CumulativeResource", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("product");
		attributeNames.add("process");
		attributeNames.add("qty");
		attributeNames.add("due");
		attributeNames.add("dueDate");
		attributeNames.add("release");
		attributeNames.add("releaseDate");
		attributeNames.add("latenessWeight");
		attributeNames.add("earlinessWeight");
		choicesMap.put("Order", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("order");
		attributeNames.add("process");
		attributeNames.add("order.product");
		attributeNames.add("order.release");
		attributeNames.add("order.due");
		attributeNames.add("order.qty");
		choicesMap.put("Job", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("shortName");
		attributeNames.add("job");
		attributeNames.add("processStep");
		attributeNames.add("duration");
		attributeNames.add("machines");
		attributeNames.add("precedes");
		attributeNames.add("job.order");
		attributeNames.add("job.order.product");
		attributeNames.add("job.order.qty");
		attributeNames.add("processStep.durationFixed");
		attributeNames.add("processStep.durationPerUnit");
		choicesMap.put("Task", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("until");
		attributeNames.add("untilDate");
		attributeNames.add("disjunctiveResource");
		choicesMap.put("WiP", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("from");
		attributeNames.add("to");
		attributeNames.add("fromDate");
		attributeNames.add("toDate");
		attributeNames.add("disjunctiveResource");
		choicesMap.put("Downtime", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("label");
		attributeNames.add("description");
		attributeNames.add("modelType");
		attributeNames.add("solverBackend");
		attributeNames.add("objectiveType");
		attributeNames.add("enforceReleaseDate");
		attributeNames.add("enforceDueDate");
		attributeNames.add("timeout");
		attributeNames.add("nrThreads");
		attributeNames.add("seed");
		attributeNames.add("removeSolution");
		attributeNames.add("solverStatus");
		attributeNames.add("time");
		choicesMap.put("SolverRun", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("solverRun");
		attributeNames.add("objectiveValue");
		attributeNames.add("solverStatus");
		attributeNames.add("bound");
		attributeNames.add("gap");
		attributeNames.add("makespan");
		attributeNames.add("flowtime");
		attributeNames.add("totalLateness");
		attributeNames.add("maxLateness");
		attributeNames.add("nrLate");
		attributeNames.add("weightedLateness");
		attributeNames.add("totalEarliness");
		attributeNames.add("maxEarliness");
		attributeNames.add("nrEarly");
		attributeNames.add("weightedEarliness");
		attributeNames.add("percentEarly");
		attributeNames.add("percentLate");
		attributeNames.add("solverRun.modelType");
		attributeNames.add("solverRun.solverBackend");
		attributeNames.add("solverRun.objectiveType");
		attributeNames.add("solverRun.enforceReleaseDate");
		attributeNames.add("solverRun.enforceDueDate");
		attributeNames.add("solverRun.timeout");
		attributeNames.add("solverRun.nrThreads");
		attributeNames.add("solverRun.seed");
		attributeNames.add("solverRun.removeSolution");
		attributeNames.add("solverRun.time");
		choicesMap.put("Solution", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("solution");
		attributeNames.add("job");
		attributeNames.add("late");
		attributeNames.add("early");
		attributeNames.add("duration");
		attributeNames.add("start");
		attributeNames.add("end");
		attributeNames.add("startDate");
		attributeNames.add("endDate");
		attributeNames.add("job.order");
		attributeNames.add("job.order.product");
		attributeNames.add("job.process");
		attributeNames.add("job.order.release");
		attributeNames.add("job.order.due");
		attributeNames.add("job.order.qty");
		choicesMap.put("JobAssignment", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("task");
		attributeNames.add("jobAssignment");
		attributeNames.add("disjunctiveResource");
		attributeNames.add("duration");
		attributeNames.add("start");
		attributeNames.add("end");
		attributeNames.add("startDate");
		attributeNames.add("endDate");
		attributeNames.add("jobAssignment.solution");
		attributeNames.add("task.job.order");
		attributeNames.add("task.job.order.release");
		attributeNames.add("task.job.order.due");
		attributeNames.add("task.job.order.product");
		attributeNames.add("task.job.order.qty");
		attributeNames.add("task.processStep");
		attributeNames.add("task.processStep.durationFixed");
		attributeNames.add("task.processStep.durationPerUnit");
		choicesMap.put("TaskAssignment", attributeNames);
		ObservableList<String> classes = FXCollections.observableArrayList();
		classes.addAll(choicesMap.keySet());
		classChoiceBox.getItems().addAll(classes);
		chart.setLegendSide(Side.LEFT);
	}

	@SuppressWarnings("rawtypes")
	public void drawChart() {
		String className = classChoiceBox.getSelectionModel().getSelectedItem();
		String attributeName = attributeChoiceBox.getSelectionModel().getSelectedItem();
		if (className == null || attributeName == null) {
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
			if (objectList != null) {
				Map<String, Integer> countMap = new HashMap<String, Integer>();
				for (Object obj : objectList) {
					obj = deref(obj,attributeName);
					String attributeValue = obj == null ? "" : obj.toString();
					if (countMap.containsKey(attributeValue)) {
						countMap.put(attributeValue, countMap.get(attributeValue) + 1);
					}
					else {
						countMap.put(attributeValue, 1);
					}
				}
				ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
				Integer others = 0;
				for (String name: countMap.keySet()) {
					Integer count = countMap.get(name);
					Double percentage = ((double) count / objectList.size()) * 100;
					if (percentage < MIN_SLICE_PERCENTAGE) {
						others += count;
					}
					else {
						String label = String.format("%s (%.1f%%)", name, percentage);
						data.add(new PieChart.Data(label, count));
					}
				}
				if (others > 0) {
					Double percentage = ((double) others / objectList.size()) * 100;
					String label = String.format("Others (%.1f%%)", percentage);
					data.add(new PieChart.Data(label, others));
				}
				chart.setData(data);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

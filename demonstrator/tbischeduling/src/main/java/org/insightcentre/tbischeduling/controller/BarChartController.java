package org.insightcentre.tbischeduling.controller;

import framework.ApplicationObjectInterface;
import java.lang.Exception;
import java.lang.Number;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * Generated at 12:43:48 on 2024-10-13 */
public class BarChartController extends ChartController {
	@FXML
	private BarChart<String, Number> chart;

	@FXML
	@SuppressWarnings("unchecked")
	private void initialize() {
		ObservableList<String> attributeNames = FXCollections.observableArrayList();
		attributeNames.add("dataFileVersionNumber");
		attributeNames.add("horizon");
		attributeNames.add("timeResolution");
		attributeNames.add("ganttWidth");
		attributeNames.add("ganttLinesPerPage");
		attributeNames.add("ganttLineHeight");
		choicesMap.put("Scenario", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("weightMakespan");
		attributeNames.add("weightFlowtime");
		attributeNames.add("weightLateness");
		attributeNames.add("weightEarliness");
		attributeNames.add("timeout");
		attributeNames.add("nrThreads");
		attributeNames.add("seed");
		choicesMap.put("AbstractSolverProperty", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("weightMakespan");
		attributeNames.add("weightFlowtime");
		attributeNames.add("weightLateness");
		attributeNames.add("weightEarliness");
		attributeNames.add("timeout");
		attributeNames.add("nrThreads");
		attributeNames.add("seed");
		choicesMap.put("SolverProperty", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("weightMakespan");
		attributeNames.add("weightFlowtime");
		attributeNames.add("weightLateness");
		attributeNames.add("weightEarliness");
		attributeNames.add("timeout");
		attributeNames.add("nrThreads");
		attributeNames.add("seed");
		attributeNames.add("time");
		choicesMap.put("SolverRun", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("nrProducts");
		attributeNames.add("minStages");
		attributeNames.add("maxStages");
		attributeNames.add("nrDisjunctiveResources");
		attributeNames.add("resourceProbability");
		attributeNames.add("minDuration");
		attributeNames.add("maxDuration");
		attributeNames.add("durationFixedFactor");
		attributeNames.add("nrCumulativeResources");
		attributeNames.add("minCumulDemand");
		attributeNames.add("maxCumulDemand");
		attributeNames.add("profilePieces");
		attributeNames.add("minCumulCapacity");
		attributeNames.add("maxCumulCapacity");
		attributeNames.add("nrOrders");
		attributeNames.add("minQty");
		attributeNames.add("maxQty");
		attributeNames.add("wipProbability");
		attributeNames.add("minWip");
		attributeNames.add("maxWip");
		attributeNames.add("downtimeProbability");
		attributeNames.add("minDowntime");
		attributeNames.add("maxDowntime");
		attributeNames.add("earliestDue");
		attributeNames.add("horizonDays");
		attributeNames.add("timeResolution");
		attributeNames.add("seed");
		choicesMap.put("AbstractDataGeneratorProperty", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("nrProducts");
		attributeNames.add("minStages");
		attributeNames.add("maxStages");
		attributeNames.add("nrDisjunctiveResources");
		attributeNames.add("resourceProbability");
		attributeNames.add("minDuration");
		attributeNames.add("maxDuration");
		attributeNames.add("durationFixedFactor");
		attributeNames.add("nrCumulativeResources");
		attributeNames.add("minCumulDemand");
		attributeNames.add("maxCumulDemand");
		attributeNames.add("profilePieces");
		attributeNames.add("minCumulCapacity");
		attributeNames.add("maxCumulCapacity");
		attributeNames.add("nrOrders");
		attributeNames.add("minQty");
		attributeNames.add("maxQty");
		attributeNames.add("wipProbability");
		attributeNames.add("minWip");
		attributeNames.add("maxWip");
		attributeNames.add("downtimeProbability");
		attributeNames.add("minDowntime");
		attributeNames.add("maxDowntime");
		attributeNames.add("earliestDue");
		attributeNames.add("horizonDays");
		attributeNames.add("timeResolution");
		attributeNames.add("seed");
		choicesMap.put("DataGeneratorProperty", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("nrProducts");
		attributeNames.add("minStages");
		attributeNames.add("maxStages");
		attributeNames.add("nrDisjunctiveResources");
		attributeNames.add("resourceProbability");
		attributeNames.add("minDuration");
		attributeNames.add("maxDuration");
		attributeNames.add("durationFixedFactor");
		attributeNames.add("nrCumulativeResources");
		attributeNames.add("minCumulDemand");
		attributeNames.add("maxCumulDemand");
		attributeNames.add("profilePieces");
		attributeNames.add("minCumulCapacity");
		attributeNames.add("maxCumulCapacity");
		attributeNames.add("nrOrders");
		attributeNames.add("minQty");
		attributeNames.add("maxQty");
		attributeNames.add("wipProbability");
		attributeNames.add("minWip");
		attributeNames.add("maxWip");
		attributeNames.add("downtimeProbability");
		attributeNames.add("minDowntime");
		attributeNames.add("maxDowntime");
		attributeNames.add("earliestDue");
		attributeNames.add("horizonDays");
		attributeNames.add("timeResolution");
		attributeNames.add("seed");
		choicesMap.put("DataGeneratorRun", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("nrProducts");
		attributeNames.add("nrProcesses");
		attributeNames.add("nrDisjunctiveResources");
		attributeNames.add("nrCumulativeResources");
		attributeNames.add("nrOrders");
		attributeNames.add("nrJobs");
		attributeNames.add("nrTasks");
		choicesMap.put("Problem", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("stage");
		attributeNames.add("durationFixed");
		attributeNames.add("durationPerUnit");
		choicesMap.put("ProcessStep", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("offset");
		choicesMap.put("ProcessSequence", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("value");
		choicesMap.put("ResourceNeed", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("demand");
		choicesMap.put("CumulativeNeed", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("from");
		attributeNames.add("capacity");
		choicesMap.put("CumulativeProfile", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("duration");
		attributeNames.add("start");
		attributeNames.add("end");
		choicesMap.put("ResourceActivity", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("nr");
		attributeNames.add("qty");
		attributeNames.add("due");
		attributeNames.add("release");
		attributeNames.add("latenessWeight");
		attributeNames.add("earlinessWeight");
		attributeNames.add("minDuration");
		choicesMap.put("Order", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("nr");
		attributeNames.add("order.release");
		attributeNames.add("order.due");
		attributeNames.add("order.qty");
		choicesMap.put("Job", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("duration");
		attributeNames.add("stage");
		attributeNames.add("nr");
		attributeNames.add("job.order.qty");
		attributeNames.add("processStep.durationFixed");
		attributeNames.add("processStep.durationPerUnit");
		choicesMap.put("Task", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("duration");
		attributeNames.add("start");
		attributeNames.add("end");
		choicesMap.put("WiP", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("duration");
		attributeNames.add("start");
		attributeNames.add("end");
		choicesMap.put("Downtime", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("objectiveValue");
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
		attributeNames.add("duration");
		attributeNames.add("start");
		attributeNames.add("end");
		attributeNames.add("totalWaitBefore");
		attributeNames.add("totalWaitAfter");
		attributeNames.add("maxWaitBefore");
		attributeNames.add("maxWaitAfter");
		attributeNames.add("solverRun.weightMakespan");
		attributeNames.add("solverRun.weightFlowtime");
		attributeNames.add("solverRun.weightEarliness");
		attributeNames.add("solverRun.weightLateness");
		attributeNames.add("solverRun.timeout");
		attributeNames.add("solverRun.nrThreads");
		attributeNames.add("solverRun.seed");
		attributeNames.add("solverRun.time");
		choicesMap.put("Solution", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("late");
		attributeNames.add("early");
		attributeNames.add("duration");
		attributeNames.add("start");
		attributeNames.add("end");
		attributeNames.add("job.order.release");
		attributeNames.add("job.order.due");
		attributeNames.add("job.order.qty");
		choicesMap.put("JobAssignment", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("duration");
		attributeNames.add("start");
		attributeNames.add("end");
		attributeNames.add("waitBefore");
		attributeNames.add("waitAfter");
		attributeNames.add("task.job.order.release");
		attributeNames.add("task.job.order.due");
		attributeNames.add("task.job.order.qty");
		attributeNames.add("task.processStep.durationFixed");
		attributeNames.add("task.processStep.durationPerUnit");
		choicesMap.put("TaskAssignment", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("earliest");
		attributeNames.add("latest");
		attributeNames.add("active");
		attributeNames.add("use");
		attributeNames.add("utilization");
		choicesMap.put("ResourceUtilization", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("nr");
		attributeNames.add("cost");
		attributeNames.add("bound");
		attributeNames.add("time");
		attributeNames.add("gapPercent");
		choicesMap.put("IntermediateSolution", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		ObservableList<String> classes = FXCollections.observableArrayList();
		classes.addAll(choicesMap.keySet());
		classChoiceBox.getItems().addAll(classes);
		chart.setLegendVisible(false);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
			else if (className.equals("AbstractSolverProperty")) {
				objectList = mainApp.getAbstractSolverPropertyData();
			}
			else if (className.equals("SolverProperty")) {
				objectList = mainApp.getSolverPropertyData();
			}
			else if (className.equals("SolverRun")) {
				objectList = mainApp.getSolverRunData();
			}
			else if (className.equals("AbstractDataGeneratorProperty")) {
				objectList = mainApp.getAbstractDataGeneratorPropertyData();
			}
			else if (className.equals("DataGeneratorProperty")) {
				objectList = mainApp.getDataGeneratorPropertyData();
			}
			else if (className.equals("DataGeneratorRun")) {
				objectList = mainApp.getDataGeneratorRunData();
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
			else if (className.equals("ResourceActivity")) {
				objectList = mainApp.getResourceActivityData();
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
			else if (className.equals("IntermediateSolution")) {
				objectList = mainApp.getIntermediateSolutionData();
			}
			else if (className.equals("SolutionError")) {
				objectList = mainApp.getSolutionErrorData();
			}
			if (objectList != null) {
				XYChart.Series series = new XYChart.Series();
				for (Object obj : objectList) {
					String name = ((ApplicationObjectInterface) obj).getName();
					obj = deref(obj,attributeName);
					if (Number.class.isAssignableFrom(obj.getClass())) {
						series.getData().add(new XYChart.Data(name, obj));
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

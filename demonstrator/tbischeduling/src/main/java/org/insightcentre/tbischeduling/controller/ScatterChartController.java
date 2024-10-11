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
 * Generated at 12:12:43 on 2024-10-10 */
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
		filterNames.add("startDateTime");
		attributeNames.add("horizon");
		filterNames.add("horizon");
		attributeNames.add("timeResolution");
		filterNames.add("timeResolution");
		attributeNames.add("ganttWidth");
		filterNames.add("ganttWidth");
		attributeNames.add("ganttLinesPerPage");
		filterNames.add("ganttLinesPerPage");
		attributeNames.add("ganttLineHeight");
		filterNames.add("ganttLineHeight");
		filterNames.add("solverProperty");
		filterNames.add("dataGeneratorProperty");
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
		filterNames.add("label");
		filterNames.add("description");
		filterNames.add("startDateTime");
		filterNames.add("enforceReleaseDate");
		filterNames.add("enforceDueDate");
		filterNames.add("enforceCumulative");
		filterNames.add("enforceWip");
		filterNames.add("enforceDowntime");
		filterNames.add("modelType");
		filterNames.add("solverBackend");
		filterNames.add("objectiveType");
		attributeNames.add("weightMakespan");
		filterNames.add("weightMakespan");
		attributeNames.add("weightFlowtime");
		filterNames.add("weightFlowtime");
		attributeNames.add("weightLateness");
		filterNames.add("weightLateness");
		attributeNames.add("weightEarliness");
		filterNames.add("weightEarliness");
		attributeNames.add("timeout");
		filterNames.add("timeout");
		attributeNames.add("nrThreads");
		filterNames.add("nrThreads");
		attributeNames.add("seed");
		filterNames.add("seed");
		filterNames.add("removeSolution");
		filterNames.add("produceReport");
		filterNames.add("producePDF");
		choicesMap.put("AbstractSolverProperty", attributeNames);
		filterMap.put("AbstractSolverProperty", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("label");
		filterNames.add("description");
		filterNames.add("startDateTime");
		filterNames.add("enforceReleaseDate");
		filterNames.add("enforceDueDate");
		filterNames.add("enforceCumulative");
		filterNames.add("enforceWip");
		filterNames.add("enforceDowntime");
		filterNames.add("modelType");
		filterNames.add("solverBackend");
		filterNames.add("objectiveType");
		attributeNames.add("weightMakespan");
		filterNames.add("weightMakespan");
		attributeNames.add("weightFlowtime");
		filterNames.add("weightFlowtime");
		attributeNames.add("weightLateness");
		filterNames.add("weightLateness");
		attributeNames.add("weightEarliness");
		filterNames.add("weightEarliness");
		attributeNames.add("timeout");
		filterNames.add("timeout");
		attributeNames.add("nrThreads");
		filterNames.add("nrThreads");
		attributeNames.add("seed");
		filterNames.add("seed");
		filterNames.add("removeSolution");
		filterNames.add("produceReport");
		filterNames.add("producePDF");
		choicesMap.put("SolverProperty", attributeNames);
		filterMap.put("SolverProperty", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("label");
		filterNames.add("description");
		filterNames.add("startDateTime");
		filterNames.add("enforceReleaseDate");
		filterNames.add("enforceDueDate");
		filterNames.add("enforceCumulative");
		filterNames.add("enforceWip");
		filterNames.add("enforceDowntime");
		filterNames.add("modelType");
		filterNames.add("solverBackend");
		filterNames.add("objectiveType");
		attributeNames.add("weightMakespan");
		filterNames.add("weightMakespan");
		attributeNames.add("weightFlowtime");
		filterNames.add("weightFlowtime");
		attributeNames.add("weightLateness");
		filterNames.add("weightLateness");
		attributeNames.add("weightEarliness");
		filterNames.add("weightEarliness");
		attributeNames.add("timeout");
		filterNames.add("timeout");
		attributeNames.add("nrThreads");
		filterNames.add("nrThreads");
		attributeNames.add("seed");
		filterNames.add("seed");
		filterNames.add("removeSolution");
		filterNames.add("produceReport");
		filterNames.add("producePDF");
		filterNames.add("solverStatus");
		attributeNames.add("time");
		filterNames.add("time");
		choicesMap.put("SolverRun", attributeNames);
		filterMap.put("SolverRun", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("label");
		filterNames.add("startDateTime");
		filterNames.add("resourceModel");
		attributeNames.add("nrProducts");
		filterNames.add("nrProducts");
		attributeNames.add("minStages");
		filterNames.add("minStages");
		attributeNames.add("maxStages");
		filterNames.add("maxStages");
		attributeNames.add("nrDisjunctiveResources");
		filterNames.add("nrDisjunctiveResources");
		attributeNames.add("resourceProbability");
		filterNames.add("resourceProbability");
		filterNames.add("durationModel");
		attributeNames.add("minDuration");
		filterNames.add("minDuration");
		attributeNames.add("maxDuration");
		filterNames.add("maxDuration");
		attributeNames.add("durationFixedFactor");
		filterNames.add("durationFixedFactor");
		attributeNames.add("nrCumulativeResources");
		filterNames.add("nrCumulativeResources");
		attributeNames.add("minCumulDemand");
		filterNames.add("minCumulDemand");
		attributeNames.add("maxCumulDemand");
		filterNames.add("maxCumulDemand");
		attributeNames.add("profilePieces");
		filterNames.add("profilePieces");
		attributeNames.add("minCumulCapacity");
		filterNames.add("minCumulCapacity");
		attributeNames.add("maxCumulCapacity");
		filterNames.add("maxCumulCapacity");
		attributeNames.add("nrOrders");
		filterNames.add("nrOrders");
		attributeNames.add("minQty");
		filterNames.add("minQty");
		attributeNames.add("maxQty");
		filterNames.add("maxQty");
		attributeNames.add("wipProbability");
		filterNames.add("wipProbability");
		attributeNames.add("minWip");
		filterNames.add("minWip");
		attributeNames.add("maxWip");
		filterNames.add("maxWip");
		attributeNames.add("downtimeProbability");
		filterNames.add("downtimeProbability");
		attributeNames.add("minDowntime");
		filterNames.add("minDowntime");
		attributeNames.add("maxDowntime");
		filterNames.add("maxDowntime");
		attributeNames.add("earliestDue");
		filterNames.add("earliestDue");
		attributeNames.add("horizonDays");
		filterNames.add("horizonDays");
		attributeNames.add("timeResolution");
		filterNames.add("timeResolution");
		attributeNames.add("seed");
		filterNames.add("seed");
		choicesMap.put("AbstractDataGeneratorProperty", attributeNames);
		filterMap.put("AbstractDataGeneratorProperty", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("label");
		filterNames.add("startDateTime");
		filterNames.add("resourceModel");
		attributeNames.add("nrProducts");
		filterNames.add("nrProducts");
		attributeNames.add("minStages");
		filterNames.add("minStages");
		attributeNames.add("maxStages");
		filterNames.add("maxStages");
		attributeNames.add("nrDisjunctiveResources");
		filterNames.add("nrDisjunctiveResources");
		attributeNames.add("resourceProbability");
		filterNames.add("resourceProbability");
		filterNames.add("durationModel");
		attributeNames.add("minDuration");
		filterNames.add("minDuration");
		attributeNames.add("maxDuration");
		filterNames.add("maxDuration");
		attributeNames.add("durationFixedFactor");
		filterNames.add("durationFixedFactor");
		attributeNames.add("nrCumulativeResources");
		filterNames.add("nrCumulativeResources");
		attributeNames.add("minCumulDemand");
		filterNames.add("minCumulDemand");
		attributeNames.add("maxCumulDemand");
		filterNames.add("maxCumulDemand");
		attributeNames.add("profilePieces");
		filterNames.add("profilePieces");
		attributeNames.add("minCumulCapacity");
		filterNames.add("minCumulCapacity");
		attributeNames.add("maxCumulCapacity");
		filterNames.add("maxCumulCapacity");
		attributeNames.add("nrOrders");
		filterNames.add("nrOrders");
		attributeNames.add("minQty");
		filterNames.add("minQty");
		attributeNames.add("maxQty");
		filterNames.add("maxQty");
		attributeNames.add("wipProbability");
		filterNames.add("wipProbability");
		attributeNames.add("minWip");
		filterNames.add("minWip");
		attributeNames.add("maxWip");
		filterNames.add("maxWip");
		attributeNames.add("downtimeProbability");
		filterNames.add("downtimeProbability");
		attributeNames.add("minDowntime");
		filterNames.add("minDowntime");
		attributeNames.add("maxDowntime");
		filterNames.add("maxDowntime");
		attributeNames.add("earliestDue");
		filterNames.add("earliestDue");
		attributeNames.add("horizonDays");
		filterNames.add("horizonDays");
		attributeNames.add("timeResolution");
		filterNames.add("timeResolution");
		attributeNames.add("seed");
		filterNames.add("seed");
		choicesMap.put("DataGeneratorProperty", attributeNames);
		filterMap.put("DataGeneratorProperty", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("label");
		filterNames.add("startDateTime");
		filterNames.add("resourceModel");
		attributeNames.add("nrProducts");
		filterNames.add("nrProducts");
		attributeNames.add("minStages");
		filterNames.add("minStages");
		attributeNames.add("maxStages");
		filterNames.add("maxStages");
		attributeNames.add("nrDisjunctiveResources");
		filterNames.add("nrDisjunctiveResources");
		attributeNames.add("resourceProbability");
		filterNames.add("resourceProbability");
		filterNames.add("durationModel");
		attributeNames.add("minDuration");
		filterNames.add("minDuration");
		attributeNames.add("maxDuration");
		filterNames.add("maxDuration");
		attributeNames.add("durationFixedFactor");
		filterNames.add("durationFixedFactor");
		attributeNames.add("nrCumulativeResources");
		filterNames.add("nrCumulativeResources");
		attributeNames.add("minCumulDemand");
		filterNames.add("minCumulDemand");
		attributeNames.add("maxCumulDemand");
		filterNames.add("maxCumulDemand");
		attributeNames.add("profilePieces");
		filterNames.add("profilePieces");
		attributeNames.add("minCumulCapacity");
		filterNames.add("minCumulCapacity");
		attributeNames.add("maxCumulCapacity");
		filterNames.add("maxCumulCapacity");
		attributeNames.add("nrOrders");
		filterNames.add("nrOrders");
		attributeNames.add("minQty");
		filterNames.add("minQty");
		attributeNames.add("maxQty");
		filterNames.add("maxQty");
		attributeNames.add("wipProbability");
		filterNames.add("wipProbability");
		attributeNames.add("minWip");
		filterNames.add("minWip");
		attributeNames.add("maxWip");
		filterNames.add("maxWip");
		attributeNames.add("downtimeProbability");
		filterNames.add("downtimeProbability");
		attributeNames.add("minDowntime");
		filterNames.add("minDowntime");
		attributeNames.add("maxDowntime");
		filterNames.add("maxDowntime");
		attributeNames.add("earliestDue");
		filterNames.add("earliestDue");
		attributeNames.add("horizonDays");
		filterNames.add("horizonDays");
		attributeNames.add("timeResolution");
		filterNames.add("timeResolution");
		attributeNames.add("seed");
		filterNames.add("seed");
		choicesMap.put("DataGeneratorRun", attributeNames);
		filterMap.put("DataGeneratorRun", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("classDesc");
		filterNames.add("item");
		filterNames.add("field");
		filterNames.add("value");
		filterNames.add("description");
		filterNames.add("severity");
		filterNames.add("name");
		filterNames.add("label");
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
		attributeNames.add("value");
		filterNames.add("value");
		choicesMap.put("ResourceNeed", attributeNames);
		filterMap.put("ResourceNeed", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
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
		filterNames.add("fromDate");
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
		filterNames.add("disjunctiveResource");
		attributeNames.add("duration");
		filterNames.add("duration");
		attributeNames.add("start");
		filterNames.add("start");
		attributeNames.add("end");
		filterNames.add("end");
		filterNames.add("startDate");
		filterNames.add("endDate");
		choicesMap.put("ResourceActivity", attributeNames);
		filterMap.put("ResourceActivity", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		attributeNames.add("nr");
		filterNames.add("nr");
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
		attributeNames.add("nr");
		filterNames.add("nr");
		filterNames.add("order.product");
		attributeNames.add("order.release");
		filterNames.add("order.release");
		attributeNames.add("order.due");
		filterNames.add("order.due");
		filterNames.add("order.releaseDate");
		filterNames.add("order.dueDate");
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
		attributeNames.add("nr");
		filterNames.add("nr");
		filterNames.add("machines");
		filterNames.add("precedes");
		filterNames.add("follows");
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
		filterNames.add("disjunctiveResource");
		attributeNames.add("duration");
		filterNames.add("duration");
		attributeNames.add("start");
		filterNames.add("start");
		attributeNames.add("end");
		filterNames.add("end");
		filterNames.add("startDate");
		filterNames.add("endDate");
		choicesMap.put("WiP", attributeNames);
		filterMap.put("WiP", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("disjunctiveResource");
		attributeNames.add("duration");
		filterNames.add("duration");
		attributeNames.add("start");
		filterNames.add("start");
		attributeNames.add("end");
		filterNames.add("end");
		filterNames.add("startDate");
		filterNames.add("endDate");
		choicesMap.put("Downtime", attributeNames);
		filterMap.put("Downtime", filterNames);
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
		attributeNames.add("duration");
		filterNames.add("duration");
		attributeNames.add("start");
		filterNames.add("start");
		attributeNames.add("end");
		filterNames.add("end");
		filterNames.add("startDate");
		filterNames.add("endDate");
		filterNames.add("solverRun.modelType");
		filterNames.add("solverRun.solverBackend");
		filterNames.add("solverRun.objectiveType");
		filterNames.add("solverRun.enforceReleaseDate");
		filterNames.add("solverRun.enforceDueDate");
		filterNames.add("solverRun.enforceCumulative");
		filterNames.add("solverRun.enforceWip");
		filterNames.add("solverRun.enforceDowntime");
		attributeNames.add("solverRun.weightMakespan");
		filterNames.add("solverRun.weightMakespan");
		attributeNames.add("solverRun.weightFlowtime");
		filterNames.add("solverRun.weightFlowtime");
		attributeNames.add("solverRun.weightEarliness");
		filterNames.add("solverRun.weightEarliness");
		attributeNames.add("solverRun.weightLateness");
		filterNames.add("solverRun.weightLateness");
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
		filterNames.add("job.order.releaseDate");
		filterNames.add("job.order.dueDate");
		attributeNames.add("job.order.qty");
		filterNames.add("job.order.qty");
		choicesMap.put("JobAssignment", attributeNames);
		filterMap.put("JobAssignment", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("disjunctiveResource");
		attributeNames.add("duration");
		filterNames.add("duration");
		attributeNames.add("start");
		filterNames.add("start");
		attributeNames.add("end");
		filterNames.add("end");
		filterNames.add("startDate");
		filterNames.add("endDate");
		filterNames.add("task");
		filterNames.add("jobAssignment");
		filterNames.add("jobAssignment.solution");
		filterNames.add("task.job.order");
		attributeNames.add("task.job.order.release");
		filterNames.add("task.job.order.release");
		attributeNames.add("task.job.order.due");
		filterNames.add("task.job.order.due");
		filterNames.add("task.job.order.releaseDate");
		filterNames.add("task.job.order.dueDate");
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
		filterNames.add("name");
		filterNames.add("solverRun");
		attributeNames.add("nr");
		filterNames.add("nr");
		attributeNames.add("cost");
		filterNames.add("cost");
		attributeNames.add("bound");
		filterNames.add("bound");
		attributeNames.add("time");
		filterNames.add("time");
		attributeNames.add("gapPercent");
		filterNames.add("gapPercent");
		choicesMap.put("IntermediateSolution", attributeNames);
		filterMap.put("IntermediateSolution", filterNames);
		attributeNames = FXCollections.observableArrayList();
		filterNames = FXCollections.observableArrayList();
		filterNames.add(filterNone);
		filterNames.add("name");
		filterNames.add("solution");
		filterNames.add("classDesc");
		filterNames.add("item");
		filterNames.add("field");
		filterNames.add("value");
		filterNames.add("description");
		filterNames.add("severity");
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

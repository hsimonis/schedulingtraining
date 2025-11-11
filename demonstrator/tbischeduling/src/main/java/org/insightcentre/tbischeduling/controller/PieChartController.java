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
 * Generated code
 */
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
		attributeNames.add("homeDir");
		attributeNames.add("startDateTime");
		attributeNames.add("horizon");
		attributeNames.add("timeResolution");
		attributeNames.add("ganttWidth");
		attributeNames.add("ganttLinesPerPage");
		attributeNames.add("ganttLineHeight");
		attributeNames.add("solverProperty");
		attributeNames.add("dataGeneratorProperty");
		attributeNames.add("ganttProperty");
		attributeNames.add("hasReleaseDate");
		attributeNames.add("hasDueDate");
		attributeNames.add("hasDisjunctive");
		attributeNames.add("hasCumulative");
		attributeNames.add("hasWiP");
		attributeNames.add("hasDowntime");
		attributeNames.add("hasSetupTime");
		attributeNames.add("hasTransportTime");
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
		attributeNames.add("label");
		attributeNames.add("description");
		attributeNames.add("startDateTime");
		attributeNames.add("enforceReleaseDate");
		attributeNames.add("enforceDueDate");
		attributeNames.add("enforceCumulative");
		attributeNames.add("enforceWip");
		attributeNames.add("enforceDowntime");
		attributeNames.add("enforceSetup");
		attributeNames.add("enforceTransportTime");
		attributeNames.add("relaxSequence");
		attributeNames.add("addSameOrder");
		attributeNames.add("addNoWait");
		attributeNames.add("addBlocking");
		attributeNames.add("modelType");
		attributeNames.add("solverBackend");
		attributeNames.add("objectiveType");
		attributeNames.add("weightMakespan");
		attributeNames.add("weightFlowtime");
		attributeNames.add("weightLateness");
		attributeNames.add("weightEarliness");
		attributeNames.add("timeout");
		attributeNames.add("nrThreads");
		attributeNames.add("seed");
		attributeNames.add("removeSolution");
		attributeNames.add("produceReport");
		attributeNames.add("producePDF");
		choicesMap.put("AbstractSolverProperty", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("label");
		attributeNames.add("description");
		attributeNames.add("startDateTime");
		attributeNames.add("enforceReleaseDate");
		attributeNames.add("enforceDueDate");
		attributeNames.add("enforceCumulative");
		attributeNames.add("enforceWip");
		attributeNames.add("enforceDowntime");
		attributeNames.add("enforceSetup");
		attributeNames.add("enforceTransportTime");
		attributeNames.add("relaxSequence");
		attributeNames.add("addSameOrder");
		attributeNames.add("addNoWait");
		attributeNames.add("addBlocking");
		attributeNames.add("modelType");
		attributeNames.add("solverBackend");
		attributeNames.add("objectiveType");
		attributeNames.add("weightMakespan");
		attributeNames.add("weightFlowtime");
		attributeNames.add("weightLateness");
		attributeNames.add("weightEarliness");
		attributeNames.add("timeout");
		attributeNames.add("nrThreads");
		attributeNames.add("seed");
		attributeNames.add("removeSolution");
		attributeNames.add("produceReport");
		attributeNames.add("producePDF");
		choicesMap.put("SolverProperty", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("label");
		attributeNames.add("description");
		attributeNames.add("startDateTime");
		attributeNames.add("enforceReleaseDate");
		attributeNames.add("enforceDueDate");
		attributeNames.add("enforceCumulative");
		attributeNames.add("enforceWip");
		attributeNames.add("enforceDowntime");
		attributeNames.add("enforceSetup");
		attributeNames.add("enforceTransportTime");
		attributeNames.add("relaxSequence");
		attributeNames.add("addSameOrder");
		attributeNames.add("addNoWait");
		attributeNames.add("addBlocking");
		attributeNames.add("modelType");
		attributeNames.add("solverBackend");
		attributeNames.add("objectiveType");
		attributeNames.add("weightMakespan");
		attributeNames.add("weightFlowtime");
		attributeNames.add("weightLateness");
		attributeNames.add("weightEarliness");
		attributeNames.add("timeout");
		attributeNames.add("nrThreads");
		attributeNames.add("seed");
		attributeNames.add("removeSolution");
		attributeNames.add("produceReport");
		attributeNames.add("producePDF");
		attributeNames.add("solverStatus");
		attributeNames.add("time");
		choicesMap.put("SolverRun", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("label");
		attributeNames.add("startDateTime");
		attributeNames.add("resourceModel");
		attributeNames.add("nrProducts");
		attributeNames.add("minStages");
		attributeNames.add("maxStages");
		attributeNames.add("nrDisjunctiveResources");
		attributeNames.add("resourceProbability");
		attributeNames.add("durationModel");
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
		attributeNames.add("name");
		attributeNames.add("label");
		attributeNames.add("startDateTime");
		attributeNames.add("resourceModel");
		attributeNames.add("nrProducts");
		attributeNames.add("minStages");
		attributeNames.add("maxStages");
		attributeNames.add("nrDisjunctiveResources");
		attributeNames.add("resourceProbability");
		attributeNames.add("durationModel");
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
		attributeNames.add("name");
		attributeNames.add("label");
		attributeNames.add("startDateTime");
		attributeNames.add("resourceModel");
		attributeNames.add("nrProducts");
		attributeNames.add("minStages");
		attributeNames.add("maxStages");
		attributeNames.add("nrDisjunctiveResources");
		attributeNames.add("resourceProbability");
		attributeNames.add("durationModel");
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
		attributeNames.add("name");
		attributeNames.add("showLate");
		attributeNames.add("showEarly");
		attributeNames.add("showRelease");
		attributeNames.add("showWait");
		attributeNames.add("showSetup");
		attributeNames.add("showIdle");
		attributeNames.add("datesDisplay");
		choicesMap.put("AbstractGanttProperty", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("showLate");
		attributeNames.add("showEarly");
		attributeNames.add("showRelease");
		attributeNames.add("showWait");
		attributeNames.add("showSetup");
		attributeNames.add("showIdle");
		attributeNames.add("datesDisplay");
		choicesMap.put("GanttProperty", attributeNames);
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
		attributeNames.add("label");
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
		attributeNames.add("noOverlap");
		choicesMap.put("Process", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("shortName");
		attributeNames.add("process");
		attributeNames.add("stage");
		attributeNames.add("durationFixed");
		attributeNames.add("durationPerUnit");
		attributeNames.add("setupType");
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
		attributeNames.add("durationFixed");
		attributeNames.add("durationPerUnit");
		attributeNames.add("preference");
		attributeNames.add("value");
		choicesMap.put("ResourceNeed", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("cumulativeResource");
		attributeNames.add("processStep");
		attributeNames.add("demand");
		attributeNames.add("processStep.durationFixed");
		attributeNames.add("processStep.durationPerUnit");
		choicesMap.put("CumulativeNeed", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("cumulativeResource");
		attributeNames.add("from");
		attributeNames.add("fromDate");
		attributeNames.add("capacity");
		choicesMap.put("CumulativeProfile", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("setup");
		attributeNames.add("shortName");
		choicesMap.put("DisjunctiveResource", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		choicesMap.put("CumulativeResource", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("disjunctiveResource");
		attributeNames.add("duration");
		attributeNames.add("start");
		attributeNames.add("end");
		attributeNames.add("startDate");
		attributeNames.add("endDate");
		choicesMap.put("ResourceActivity", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("nr");
		attributeNames.add("product");
		attributeNames.add("process");
		attributeNames.add("qty");
		attributeNames.add("due");
		attributeNames.add("dueDate");
		attributeNames.add("release");
		attributeNames.add("releaseDate");
		attributeNames.add("latenessWeight");
		attributeNames.add("earlinessWeight");
		attributeNames.add("minDuration");
		choicesMap.put("Order", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("order");
		attributeNames.add("process");
		attributeNames.add("nr");
		attributeNames.add("noOverlap");
		attributeNames.add("order.product");
		attributeNames.add("order.release");
		attributeNames.add("order.due");
		attributeNames.add("order.releaseDate");
		attributeNames.add("order.dueDate");
		attributeNames.add("order.qty");
		choicesMap.put("Job", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("shortName");
		attributeNames.add("job");
		attributeNames.add("processStep");
		attributeNames.add("duration");
		attributeNames.add("stage");
		attributeNames.add("nr");
		attributeNames.add("machines");
		attributeNames.add("precedes");
		attributeNames.add("follows");
		attributeNames.add("job.order");
		attributeNames.add("job.order.product");
		attributeNames.add("job.order.qty");
		attributeNames.add("processStep.durationFixed");
		attributeNames.add("processStep.durationPerUnit");
		choicesMap.put("Task", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("disjunctiveResource");
		attributeNames.add("duration");
		attributeNames.add("start");
		attributeNames.add("end");
		attributeNames.add("startDate");
		attributeNames.add("endDate");
		choicesMap.put("WiP", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("disjunctiveResource");
		attributeNames.add("duration");
		attributeNames.add("start");
		attributeNames.add("end");
		attributeNames.add("startDate");
		attributeNames.add("endDate");
		choicesMap.put("Downtime", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("solverRun");
		attributeNames.add("objectiveValue");
		attributeNames.add("solverStatus");
		attributeNames.add("bound");
		attributeNames.add("gapPercent");
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
		attributeNames.add("startDate");
		attributeNames.add("endDate");
		attributeNames.add("totalWaitBefore");
		attributeNames.add("totalWaitAfter");
		attributeNames.add("maxWaitBefore");
		attributeNames.add("maxWaitAfter");
		attributeNames.add("totalIdleBefore");
		attributeNames.add("totalIdleAfter");
		attributeNames.add("maxIdleBefore");
		attributeNames.add("maxIdleAfter");
		attributeNames.add("totalSetupBefore");
		attributeNames.add("totalSetupAfter");
		attributeNames.add("maxSetupBefore");
		attributeNames.add("maxSetupAfter");
		attributeNames.add("totalActiveTime");
		attributeNames.add("totalProductionTime");
		attributeNames.add("activeUtilization");
		attributeNames.add("setupPercent");
		attributeNames.add("idlePercent");
		attributeNames.add("solverRun.modelType");
		attributeNames.add("solverRun.solverBackend");
		attributeNames.add("solverRun.objectiveType");
		attributeNames.add("solverRun.enforceReleaseDate");
		attributeNames.add("solverRun.enforceDueDate");
		attributeNames.add("solverRun.enforceCumulative");
		attributeNames.add("solverRun.enforceWip");
		attributeNames.add("solverRun.enforceDowntime");
		attributeNames.add("solverRun.enforceSetup");
		attributeNames.add("solverRun.enforceTransportTime");
		attributeNames.add("solverRun.relaxSequence");
		attributeNames.add("solverRun.weightMakespan");
		attributeNames.add("solverRun.weightFlowtime");
		attributeNames.add("solverRun.weightEarliness");
		attributeNames.add("solverRun.weightLateness");
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
		attributeNames.add("job.order.releaseDate");
		attributeNames.add("job.order.dueDate");
		attributeNames.add("job.order.qty");
		choicesMap.put("JobAssignment", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("disjunctiveResource");
		attributeNames.add("duration");
		attributeNames.add("start");
		attributeNames.add("end");
		attributeNames.add("startDate");
		attributeNames.add("endDate");
		attributeNames.add("task");
		attributeNames.add("jobAssignment");
		attributeNames.add("waitBefore");
		attributeNames.add("waitAfter");
		attributeNames.add("idleBefore");
		attributeNames.add("idleAfter");
		attributeNames.add("setupBefore");
		attributeNames.add("setupAfter");
		attributeNames.add("jobAssignment.solution");
		attributeNames.add("task.job.order");
		attributeNames.add("task.job.order.release");
		attributeNames.add("task.job.order.due");
		attributeNames.add("task.job.order.releaseDate");
		attributeNames.add("task.job.order.dueDate");
		attributeNames.add("task.job.order.product");
		attributeNames.add("task.job.order.qty");
		attributeNames.add("task.processStep");
		attributeNames.add("task.processStep.durationFixed");
		attributeNames.add("task.processStep.durationPerUnit");
		choicesMap.put("TaskAssignment", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("disjunctiveResource");
		attributeNames.add("solution");
		attributeNames.add("earliest");
		attributeNames.add("latest");
		attributeNames.add("active");
		attributeNames.add("use");
		attributeNames.add("setup");
		attributeNames.add("idle");
		attributeNames.add("utilization");
		attributeNames.add("setupPercent");
		attributeNames.add("idlePercent");
		choicesMap.put("ResourceUtilization", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("solverRun");
		attributeNames.add("nr");
		attributeNames.add("cost");
		attributeNames.add("bound");
		attributeNames.add("time");
		attributeNames.add("gapPercent");
		choicesMap.put("IntermediateSolution", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("solution");
		attributeNames.add("classDesc");
		attributeNames.add("item");
		attributeNames.add("field");
		attributeNames.add("value");
		attributeNames.add("description");
		attributeNames.add("severity");
		choicesMap.put("SolutionError", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("defaultValue");
		attributeNames.add("sameValue");
		attributeNames.add("disjunctiveResource");
		choicesMap.put("Setup", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("setup");
		attributeNames.add("nr");
		attributeNames.add("processStep");
		choicesMap.put("SetupType", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("from");
		attributeNames.add("to");
		attributeNames.add("value");
		choicesMap.put("SetupMatrix", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("defaultValue");
		choicesMap.put("Transport", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("from");
		attributeNames.add("to");
		attributeNames.add("value");
		choicesMap.put("TransportMatrix", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("variant");
		attributeNames.add("instance");
		attributeNames.add("parameter");
		attributeNames.add("instanceNr");
		attributeNames.add("nrJobs");
		attributeNames.add("nrTasks");
		attributeNames.add("nrMachines");
		attributeNames.add("nrCumulatives");
		attributeNames.add("solverStatus");
		attributeNames.add("time");
		attributeNames.add("makespan");
		attributeNames.add("bound");
		attributeNames.add("gapPercent");
		choicesMap.put("SolutionSummary", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("value");
		attributeNames.add("description");
		choicesMap.put("LowerBound", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("value");
		attributeNames.add("description");
		attributeNames.add("job");
		choicesMap.put("JobLowerBound", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("value");
		attributeNames.add("description");
		attributeNames.add("disjunctiveResource");
		choicesMap.put("DisjunctiveLowerBound", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("value");
		attributeNames.add("description");
		attributeNames.add("cumulativeResource");
		attributeNames.add("maxCapacity");
		attributeNames.add("totalDemand");
		choicesMap.put("CumulativeLowerBound", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("value");
		attributeNames.add("description");
		choicesMap.put("MachineGroupLowerBound", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("cumulativeResource");
		attributeNames.add("taskAssignment");
		attributeNames.add("x");
		attributeNames.add("y");
		attributeNames.add("w");
		attributeNames.add("h");
		choicesMap.put("PlacedRectangle", attributeNames);
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
			else if (className.equals("AbstractGanttProperty")) {
				objectList = mainApp.getAbstractGanttPropertyData();
			}
			else if (className.equals("GanttProperty")) {
				objectList = mainApp.getGanttPropertyData();
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
			else if (className.equals("Setup")) {
				objectList = mainApp.getSetupData();
			}
			else if (className.equals("SetupType")) {
				objectList = mainApp.getSetupTypeData();
			}
			else if (className.equals("SetupMatrix")) {
				objectList = mainApp.getSetupMatrixData();
			}
			else if (className.equals("Transport")) {
				objectList = mainApp.getTransportData();
			}
			else if (className.equals("TransportMatrix")) {
				objectList = mainApp.getTransportMatrixData();
			}
			else if (className.equals("SolutionSummary")) {
				objectList = mainApp.getSolutionSummaryData();
			}
			else if (className.equals("LowerBound")) {
				objectList = mainApp.getLowerBoundData();
			}
			else if (className.equals("JobLowerBound")) {
				objectList = mainApp.getJobLowerBoundData();
			}
			else if (className.equals("DisjunctiveLowerBound")) {
				objectList = mainApp.getDisjunctiveLowerBoundData();
			}
			else if (className.equals("CumulativeLowerBound")) {
				objectList = mainApp.getCumulativeLowerBoundData();
			}
			else if (className.equals("MachineGroupLowerBound")) {
				objectList = mainApp.getMachineGroupLowerBoundData();
			}
			else if (className.equals("PlacedRectangle")) {
				objectList = mainApp.getPlacedRectangleData();
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

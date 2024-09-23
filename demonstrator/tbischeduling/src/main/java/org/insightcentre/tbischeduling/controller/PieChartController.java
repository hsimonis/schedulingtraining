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
 * Generated at 14:32:41 on 2024-09-23 */
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
		attributeNames.add("timePointsAsDate");
		choicesMap.put("Problem", attributeNames);
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
		choicesMap.put("DisjunctiveResource", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		choicesMap.put("CumulativeResource", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("processStep");
		attributeNames.add("disjunctiveResource");
		choicesMap.put("ResourceNeed", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("process");
		choicesMap.put("Product", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("product");
		attributeNames.add("qty");
		attributeNames.add("due");
		attributeNames.add("dueDate");
		choicesMap.put("Order", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("order");
		attributeNames.add("process");
		choicesMap.put("Job", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("job");
		attributeNames.add("processStep");
		choicesMap.put("Task", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("objectiveValue");
		choicesMap.put("Solution", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("jobAssignment");
		attributeNames.add("task");
		attributeNames.add("resource");
		attributeNames.add("start");
		attributeNames.add("end");
		attributeNames.add("duration");
		attributeNames.add("startDate");
		attributeNames.add("endDate");
		choicesMap.put("TaskAssignment", attributeNames);
		attributeNames = FXCollections.observableArrayList();
		attributeNames.add("name");
		attributeNames.add("solution");
		attributeNames.add("job");
		attributeNames.add("start");
		attributeNames.add("end");
		attributeNames.add("duration");
		attributeNames.add("startDate");
		attributeNames.add("endDate");
		choicesMap.put("JobAssignment", attributeNames);
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
			else if (className.equals("Problem")) {
				objectList = mainApp.getProblemData();
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
			else if (className.equals("DisjunctiveResource")) {
				objectList = mainApp.getDisjunctiveResourceData();
			}
			else if (className.equals("CumulativeResource")) {
				objectList = mainApp.getCumulativeResourceData();
			}
			else if (className.equals("ResourceNeed")) {
				objectList = mainApp.getResourceNeedData();
			}
			else if (className.equals("Product")) {
				objectList = mainApp.getProductData();
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
			else if (className.equals("Solution")) {
				objectList = mainApp.getSolutionData();
			}
			else if (className.equals("TaskAssignment")) {
				objectList = mainApp.getTaskAssignmentData();
			}
			else if (className.equals("JobAssignment")) {
				objectList = mainApp.getJobAssignmentData();
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

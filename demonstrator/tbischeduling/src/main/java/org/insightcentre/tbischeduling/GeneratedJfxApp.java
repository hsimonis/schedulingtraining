package org.insightcentre.tbischeduling;

import framework.ApplicationDatasetInterface;
import framework.gui.AbstractJfxMainWindow;
import framework.gui.BaseController;
import framework.gui.StatusException;
import java.io.File;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.insightcentre.tbischeduling.controller.RootController;
import org.insightcentre.tbischeduling.datamodel.ApplicationDifference;
import org.insightcentre.tbischeduling.datamodel.ApplicationWarning;
import org.insightcentre.tbischeduling.datamodel.CumulativeResource;
import org.insightcentre.tbischeduling.datamodel.DisjunctiveResource;
import org.insightcentre.tbischeduling.datamodel.Job;
import org.insightcentre.tbischeduling.datamodel.JobAssignment;
import org.insightcentre.tbischeduling.datamodel.Order;
import org.insightcentre.tbischeduling.datamodel.Problem;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.datamodel.ProcessSequence;
import org.insightcentre.tbischeduling.datamodel.ProcessStep;
import org.insightcentre.tbischeduling.datamodel.Product;
import org.insightcentre.tbischeduling.datamodel.ResourceNeed;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.Solution;
import org.insightcentre.tbischeduling.datamodel.Task;
import org.insightcentre.tbischeduling.datamodel.TaskAssignment;
import org.insightcentre.tbischeduling.datamodel.XMLLoader;
import org.insightcentre.tbischeduling.generatedsolver.ScheduleJobsDialogBox;
import org.insightcentre.tbischeduling.generatedsolver.ScheduleJobsSolver;

/**
 * Generated at 14:32:41 on 2024-09-23 */
public class GeneratedJfxApp extends AbstractJfxMainWindow {
	static {
		FREEMARKER_CFG.setClassForTemplateLoading(GeneratedJfxApp.class, "C:/Users/hsimonis/Documents/GitHub/schedulingtraining/demonstrator/tbischeduling/site/web");
	}

	public Scenario basebase;

	protected List<BaseController> controllers = new ArrayList<>();

	private RootController controller;

	private ObservableList<Scenario> scenarioData = FXCollections.observableArrayList();

	private ObservableList<ApplicationDifference> applicationDifferenceData = FXCollections.observableArrayList();

	private ObservableList<ApplicationWarning> applicationWarningData = FXCollections.observableArrayList();

	private ObservableList<Problem> problemData = FXCollections.observableArrayList();

	private ObservableList<Process> processData = FXCollections.observableArrayList();

	private ObservableList<ProcessStep> processStepData = FXCollections.observableArrayList();

	private ObservableList<ProcessSequence> processSequenceData = FXCollections.observableArrayList();

	private ObservableList<DisjunctiveResource> disjunctiveResourceData = FXCollections.observableArrayList();

	private ObservableList<CumulativeResource> cumulativeResourceData = FXCollections.observableArrayList();

	private ObservableList<ResourceNeed> resourceNeedData = FXCollections.observableArrayList();

	private ObservableList<Product> productData = FXCollections.observableArrayList();

	private ObservableList<Order> orderData = FXCollections.observableArrayList();

	private ObservableList<Job> jobData = FXCollections.observableArrayList();

	private ObservableList<Task> taskData = FXCollections.observableArrayList();

	private ObservableList<Solution> solutionData = FXCollections.observableArrayList();

	private ObservableList<TaskAssignment> taskAssignmentData = FXCollections.observableArrayList();

	private ObservableList<JobAssignment> jobAssignmentData = FXCollections.observableArrayList();

	public GeneratedJfxApp() {
		super("tbischeduling", "ENTIRE EDIH Test Before Invest - Scheduling - University College Cork", "*.data", "C:/Users/hsimonis/Documents/GitHub/schedulingtraining/demonstrator/tbischeduling");
		fs = minimalDataset();
		reset();
		tableViews.put("Scenario", "Scenario");
		tableViews.put("Scenario Differences", "ApplicationDifference");
		tableViews.put("Warnings", "ApplicationWarning");
		tableViews.put("Problem", "Problem");
		tableViews.put("Product", "Product");
		tableViews.put("Process", "Process");
		tableViews.put("ProcessStep", "ProcessStep");
		tableViews.put("ProcessSequence", "ProcessSequence");
		tableViews.put("ResourceNeed", "ResourceNeed");
		tableViews.put("DisjunctiveResource", "DisjunctiveResource");
		tableViews.put("CumulativeResource", "CumulativeResource");
		tableViews.put("Solution", "Solution");
		tableViews.put("JobAssignment", "JobAssignment");
		tableViews.put("TaskAssignment", "TaskAssignment");
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		super.start(primaryStage);
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
			BorderPane rootLayout = (BorderPane) loader.load();
			controller = loader.getController();
			controller.setMainApp(this);
			Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
			rootLayout.setPrefWidth(bounds.getWidth() * 0.7);
			rootLayout.setPrefHeight(bounds.getHeight() * 0.7);
			rootLayout.setCenter(tabPane);
			Scene scene = new Scene(rootLayout);
			URL url = getClass().getResource("/framework/gui/css/theme.css");
			if (url != null) {
				scene.getStylesheets().addAll(url.toExternalForm());
			}
			primaryStage.setScene(scene);
			primaryStage.setTitle(applicationTitle);
			primaryStage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showView(String name) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(String.format("view/%s.fxml", name)));
			Tab tab = (Tab) loader.load();
			showTab(tab);
			BaseController controller = (BaseController) loader.getController();
			controller.setMainApp(this);
			controllers.add(controller);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setStatus(String text) {
		controller.setStatus(text);
	}

	@Override
	public ApplicationDatasetInterface minimalDataset() {
		Scenario base = new Scenario();
		base.setDirty(false);
		return base;
	}

	@Override
	public ApplicationDatasetInterface loadXML(File file) throws StatusException {
		return (Scenario) new XMLLoader().loadXML(file);
	}

	@Override
	public void reset() {
		Scenario base = (Scenario) fs;
		scenarioData.clear();
		scenarioData.addAll(base.getListScenario());
		applicationDifferenceData.clear();
		applicationDifferenceData.addAll(base.getListApplicationDifference());
		applicationWarningData.clear();
		applicationWarningData.addAll(base.getListApplicationWarning());
		problemData.clear();
		problemData.addAll(base.getListProblem());
		processData.clear();
		processData.addAll(base.getListProcess());
		processStepData.clear();
		processStepData.addAll(base.getListProcessStep());
		processSequenceData.clear();
		processSequenceData.addAll(base.getListProcessSequence());
		disjunctiveResourceData.clear();
		disjunctiveResourceData.addAll(base.getListDisjunctiveResource());
		cumulativeResourceData.clear();
		cumulativeResourceData.addAll(base.getListCumulativeResource());
		resourceNeedData.clear();
		resourceNeedData.addAll(base.getListResourceNeed());
		productData.clear();
		productData.addAll(base.getListProduct());
		orderData.clear();
		orderData.addAll(base.getListOrder());
		jobData.clear();
		jobData.addAll(base.getListJob());
		taskData.clear();
		taskData.addAll(base.getListTask());
		solutionData.clear();
		solutionData.addAll(base.getListSolution());
		taskAssignmentData.clear();
		taskAssignmentData.addAll(base.getListTaskAssignment());
		jobAssignmentData.clear();
		jobAssignmentData.addAll(base.getListJobAssignment());
		for (BaseController controller : controllers) {
			controller.setMainApp(this);
		}
	}

	public void LoadDataFileAction(Scenario base) {
		alert(Alert.AlertType.WARNING, "Action LoadDataFileAction is not yet implemented!");
	}

	public void SaveSpreadsheetAction(Scenario base) {
		alert(Alert.AlertType.WARNING, "Action SaveSpreadsheetAction is not yet implemented!");
	}

	public void GenerateReportAction(Scenario base) {
		alert(Alert.AlertType.WARNING, "Action GenerateReportAction is not yet implemented!");
	}

	public void scheduleJobsSolverRun(Scenario base) {
		Optional<Boolean> result = new ScheduleJobsDialogBox(this,base,new ScheduleJobsSolver(base)).showAndWait();
		reset();
	}

	@Override
	public void handle(ActionEvent event) throws StatusException {
		setStatus(null);
		MenuItem mi = ((MenuItem) event.getSource());
		String id = mi.getId();
		if (id.equals("LoadDataFileAction")) {
			LoadDataFileAction((Scenario) fs);
		}
		else if (id.equals("SaveSpreadsheetAction")) {
			SaveSpreadsheetAction((Scenario) fs);
		}
		else if (id.equals("GenerateReportAction")) {
			GenerateReportAction((Scenario) fs);
		}
		else if (id.equals("scheduleJobsSolverRun")) {
			scheduleJobsSolverRun((Scenario) fs);
		}
		else {
			super.handle(event);
		}
	}

	public ObservableList<Scenario> getScenarioData() {
		return scenarioData;
	}

	public ObservableList<ApplicationDifference> getApplicationDifferenceData() {
		return applicationDifferenceData;
	}

	public ObservableList<ApplicationWarning> getApplicationWarningData() {
		return applicationWarningData;
	}

	public ObservableList<Problem> getProblemData() {
		return problemData;
	}

	public ObservableList<Process> getProcessData() {
		return processData;
	}

	public ObservableList<ProcessStep> getProcessStepData() {
		return processStepData;
	}

	public ObservableList<ProcessSequence> getProcessSequenceData() {
		return processSequenceData;
	}

	public ObservableList<DisjunctiveResource> getDisjunctiveResourceData() {
		return disjunctiveResourceData;
	}

	public ObservableList<CumulativeResource> getCumulativeResourceData() {
		return cumulativeResourceData;
	}

	public ObservableList<ResourceNeed> getResourceNeedData() {
		return resourceNeedData;
	}

	public ObservableList<Product> getProductData() {
		return productData;
	}

	public ObservableList<Order> getOrderData() {
		return orderData;
	}

	public ObservableList<Job> getJobData() {
		return jobData;
	}

	public ObservableList<Task> getTaskData() {
		return taskData;
	}

	public ObservableList<Solution> getSolutionData() {
		return solutionData;
	}

	public ObservableList<TaskAssignment> getTaskAssignmentData() {
		return taskAssignmentData;
	}

	public ObservableList<JobAssignment> getJobAssignmentData() {
		return jobAssignmentData;
	}
}

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
import org.insightcentre.tbischeduling.controller.CumulativeNeedsMatrixDraw;
import org.insightcentre.tbischeduling.controller.DisjunctiveNeedsMatrixDraw;
import org.insightcentre.tbischeduling.controller.RootController;
import org.insightcentre.tbischeduling.datamodel.AbstractDataGeneratorProperty;
import org.insightcentre.tbischeduling.datamodel.AbstractSolverProperty;
import org.insightcentre.tbischeduling.datamodel.ApplicationDifference;
import org.insightcentre.tbischeduling.datamodel.ApplicationWarning;
import org.insightcentre.tbischeduling.datamodel.CumulativeNeed;
import org.insightcentre.tbischeduling.datamodel.CumulativeProfile;
import org.insightcentre.tbischeduling.datamodel.CumulativeResource;
import org.insightcentre.tbischeduling.datamodel.DataGeneratorProperty;
import org.insightcentre.tbischeduling.datamodel.DataGeneratorRun;
import org.insightcentre.tbischeduling.datamodel.DisjunctiveResource;
import org.insightcentre.tbischeduling.datamodel.Downtime;
import org.insightcentre.tbischeduling.datamodel.InputError;
import org.insightcentre.tbischeduling.datamodel.IntermediateSolution;
import org.insightcentre.tbischeduling.datamodel.Job;
import org.insightcentre.tbischeduling.datamodel.JobAssignment;
import org.insightcentre.tbischeduling.datamodel.Order;
import org.insightcentre.tbischeduling.datamodel.Problem;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.datamodel.ProcessSequence;
import org.insightcentre.tbischeduling.datamodel.ProcessStep;
import org.insightcentre.tbischeduling.datamodel.Product;
import org.insightcentre.tbischeduling.datamodel.ResourceActivity;
import org.insightcentre.tbischeduling.datamodel.ResourceNeed;
import org.insightcentre.tbischeduling.datamodel.ResourceUtilization;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.Solution;
import org.insightcentre.tbischeduling.datamodel.SolutionError;
import org.insightcentre.tbischeduling.datamodel.SolverProperty;
import org.insightcentre.tbischeduling.datamodel.SolverRun;
import org.insightcentre.tbischeduling.datamodel.Task;
import org.insightcentre.tbischeduling.datamodel.TaskAssignment;
import org.insightcentre.tbischeduling.datamodel.WiP;
import org.insightcentre.tbischeduling.datamodel.XMLLoader;
import org.insightcentre.tbischeduling.generatedsolver.GenerateDataDialogBox;
import org.insightcentre.tbischeduling.generatedsolver.GenerateDataSolver;
import org.insightcentre.tbischeduling.generatedsolver.NewDowntimeDialogBox;
import org.insightcentre.tbischeduling.generatedsolver.NewDowntimeSolver;
import org.insightcentre.tbischeduling.generatedsolver.NewOrderDialogBox;
import org.insightcentre.tbischeduling.generatedsolver.NewOrderSolver;
import org.insightcentre.tbischeduling.generatedsolver.ScheduleJobsDialogBox;
import org.insightcentre.tbischeduling.generatedsolver.ScheduleJobsSolver;

/**
 * Generated at 05:23:32 on 2024-10-13 */
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

	private ObservableList<AbstractSolverProperty> abstractSolverPropertyData = FXCollections.observableArrayList();

	private ObservableList<SolverProperty> solverPropertyData = FXCollections.observableArrayList();

	private ObservableList<SolverRun> solverRunData = FXCollections.observableArrayList();

	private ObservableList<AbstractDataGeneratorProperty> abstractDataGeneratorPropertyData = FXCollections.observableArrayList();

	private ObservableList<DataGeneratorProperty> dataGeneratorPropertyData = FXCollections.observableArrayList();

	private ObservableList<DataGeneratorRun> dataGeneratorRunData = FXCollections.observableArrayList();

	private ObservableList<InputError> inputErrorData = FXCollections.observableArrayList();

	private ObservableList<Problem> problemData = FXCollections.observableArrayList();

	private ObservableList<Product> productData = FXCollections.observableArrayList();

	private ObservableList<Process> processData = FXCollections.observableArrayList();

	private ObservableList<ProcessStep> processStepData = FXCollections.observableArrayList();

	private ObservableList<ProcessSequence> processSequenceData = FXCollections.observableArrayList();

	private ObservableList<ResourceNeed> resourceNeedData = FXCollections.observableArrayList();

	private ObservableList<CumulativeNeed> cumulativeNeedData = FXCollections.observableArrayList();

	private ObservableList<CumulativeProfile> cumulativeProfileData = FXCollections.observableArrayList();

	private ObservableList<DisjunctiveResource> disjunctiveResourceData = FXCollections.observableArrayList();

	private ObservableList<CumulativeResource> cumulativeResourceData = FXCollections.observableArrayList();

	private ObservableList<ResourceActivity> resourceActivityData = FXCollections.observableArrayList();

	private ObservableList<Order> orderData = FXCollections.observableArrayList();

	private ObservableList<Job> jobData = FXCollections.observableArrayList();

	private ObservableList<Task> taskData = FXCollections.observableArrayList();

	private ObservableList<WiP> wiPData = FXCollections.observableArrayList();

	private ObservableList<Downtime> downtimeData = FXCollections.observableArrayList();

	private ObservableList<Solution> solutionData = FXCollections.observableArrayList();

	private ObservableList<JobAssignment> jobAssignmentData = FXCollections.observableArrayList();

	private ObservableList<TaskAssignment> taskAssignmentData = FXCollections.observableArrayList();

	private ObservableList<ResourceUtilization> resourceUtilizationData = FXCollections.observableArrayList();

	private ObservableList<IntermediateSolution> intermediateSolutionData = FXCollections.observableArrayList();

	private ObservableList<SolutionError> solutionErrorData = FXCollections.observableArrayList();

	public GeneratedJfxApp() {
		super("tbischeduling", "ENTIRE EDIH Test Before Invest - Scheduling - University College Cork", "*.data", "C:/Users/hsimonis/Documents/GitHub/schedulingtraining/demonstrator/tbischeduling");
		fs = minimalDataset();
		reset();
		tableViews.put("Scenario", "Scenario");
		tableViews.put("Scenario Differences", "ApplicationDifference");
		tableViews.put("Warnings", "ApplicationWarning");
		tableViews.put("InputError", "InputError");
		tableViews.put("Problem", "Problem");
		tableViews.put("Product", "Product");
		tableViews.put("Process", "Process");
		tableViews.put("ProcessStep", "ProcessStep");
		tableViews.put("ProcessSequence", "ProcessSequence");
		tableViews.put("DisjunctiveResource", "DisjunctiveResource");
		tableViews.put("CumulativeResource", "CumulativeResource");
		tableViews.put("ResourceNeed", "ResourceNeed");
		tableViews.put("CumulativeNeed", "CumulativeNeed");
		tableViews.put("CumulativeProfile", "CumulativeProfile");
		tableViews.put("Order", "Order");
		tableViews.put("Job", "Job");
		tableViews.put("Task", "Task");
		tableViews.put("WiP", "WiP");
		tableViews.put("Downtime", "Downtime");
		tableViews.put("SolutionError", "SolutionError");
		tableViews.put("SolverRun", "SolverRun");
		tableViews.put("Solution", "Solution");
		tableViews.put("JobAssignment", "JobAssignment");
		tableViews.put("TaskAssignment", "TaskAssignment");
		tableViews.put("ResourceUtilization", "ResourceUtilization");
		tableViews.put("IntermediateSolution", "IntermediateSolution");
		tableViews.put("DataGeneratorProperty", "DataGeneratorProperty");
		tableViews.put("SolverProperty", "SolverProperty");
		tableViews.put("AbstractSolverProperty", "AbstractSolverProperty");
		tableViews.put("AbstractDataGeneratorProperty", "AbstractDataGeneratorProperty");
		tableViews.put("DataGeneratorRun", "DataGeneratorRun");
		tableViews.put("ResourceActivity", "ResourceActivity");
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

	public void setTitle(String text) {
		if (primaryStage != null) primaryStage.setTitle(text);
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
		abstractSolverPropertyData.clear();
		abstractSolverPropertyData.addAll(base.getListAbstractSolverProperty());
		solverPropertyData.clear();
		solverPropertyData.addAll(base.getListSolverProperty());
		solverRunData.clear();
		solverRunData.addAll(base.getListSolverRun());
		abstractDataGeneratorPropertyData.clear();
		abstractDataGeneratorPropertyData.addAll(base.getListAbstractDataGeneratorProperty());
		dataGeneratorPropertyData.clear();
		dataGeneratorPropertyData.addAll(base.getListDataGeneratorProperty());
		dataGeneratorRunData.clear();
		dataGeneratorRunData.addAll(base.getListDataGeneratorRun());
		inputErrorData.clear();
		inputErrorData.addAll(base.getListInputError());
		problemData.clear();
		problemData.addAll(base.getListProblem());
		productData.clear();
		productData.addAll(base.getListProduct());
		processData.clear();
		processData.addAll(base.getListProcess());
		processStepData.clear();
		processStepData.addAll(base.getListProcessStep());
		processSequenceData.clear();
		processSequenceData.addAll(base.getListProcessSequence());
		resourceNeedData.clear();
		resourceNeedData.addAll(base.getListResourceNeed());
		cumulativeNeedData.clear();
		cumulativeNeedData.addAll(base.getListCumulativeNeed());
		cumulativeProfileData.clear();
		cumulativeProfileData.addAll(base.getListCumulativeProfile());
		disjunctiveResourceData.clear();
		disjunctiveResourceData.addAll(base.getListDisjunctiveResource());
		cumulativeResourceData.clear();
		cumulativeResourceData.addAll(base.getListCumulativeResource());
		resourceActivityData.clear();
		resourceActivityData.addAll(base.getListResourceActivity());
		orderData.clear();
		orderData.addAll(base.getListOrder());
		jobData.clear();
		jobData.addAll(base.getListJob());
		taskData.clear();
		taskData.addAll(base.getListTask());
		wiPData.clear();
		wiPData.addAll(base.getListWiP());
		downtimeData.clear();
		downtimeData.addAll(base.getListDowntime());
		solutionData.clear();
		solutionData.addAll(base.getListSolution());
		jobAssignmentData.clear();
		jobAssignmentData.addAll(base.getListJobAssignment());
		taskAssignmentData.clear();
		taskAssignmentData.addAll(base.getListTaskAssignment());
		resourceUtilizationData.clear();
		resourceUtilizationData.addAll(base.getListResourceUtilization());
		intermediateSolutionData.clear();
		intermediateSolutionData.addAll(base.getListIntermediateSolution());
		solutionErrorData.clear();
		solutionErrorData.addAll(base.getListSolutionError());
		for (BaseController controller : controllers) {
			controller.setMainApp(this);
		}
	}

	public void LoadDataFileAction(Scenario base) {
		alert(Alert.AlertType.WARNING, "Action LoadDataFileAction is not yet implemented!");
	}

	public void SaveDataFileAction(Scenario base) {
		alert(Alert.AlertType.WARNING, "Action SaveDataFileAction is not yet implemented!");
	}

	public void GenerateReportAction(Scenario base) {
		alert(Alert.AlertType.WARNING, "Action GenerateReportAction is not yet implemented!");
	}

	public void showSolutionReport(Scenario base) {
		alert(Alert.AlertType.WARNING, "Action showSolutionReport is not yet implemented!");
	}

	public void resourceUtilization0BarChart(Scenario base) {
		showView("ResourceUtilizationBarChart0BarChartLayout");
	}

	public void intermediateSolutionTimeCostLineChart(Scenario base) {
		showView("IntermediateSolutionTimeCostLineChartLayout");
	}

	public void solutionTreeTreeTable(Scenario base) {
		showView("SolutionTreeTreeTableLayout");
	}

	public void newOrderSolverRun(Scenario base) {
		Optional<Boolean> result = new NewOrderDialogBox(this,base,new NewOrderSolver(base)).showAndWait();
		reset();
	}

	public void newDowntimeSolverRun(Scenario base) {
		Optional<Boolean> result = new NewDowntimeDialogBox(this,base,new NewDowntimeSolver(base)).showAndWait();
		reset();
	}

	public void generateDataSolverRun(Scenario base) {
		Optional<Boolean> result = new GenerateDataDialogBox(this,base,new GenerateDataSolver(base)).showAndWait();
		reset();
	}

	public void scheduleJobsSolverRun(Scenario base) {
		Optional<Boolean> result = new ScheduleJobsDialogBox(this,base,new ScheduleJobsSolver(base)).showAndWait();
		reset();
	}

	public void disjunctiveNeedsMatrixMatrixViewer(Scenario base) {
		new DisjunctiveNeedsMatrixDraw(base,"maps/tree.html","Disjunctive Resource Needs Matrix");
		showContent("Disjunctive Resource Needs Matrix",DisjunctiveNeedsMatrixDraw.createContent(base,"Disjunctive Resource Needs Matrix"));
	}

	public void cumulativeNeedsMatrixMatrixViewer(Scenario base) {
		new CumulativeNeedsMatrixDraw(base,"maps/tree.html","Cumulative Resource Needs Matrix");
		showContent("Cumulative Resource Needs Matrix",CumulativeNeedsMatrixDraw.createContent(base,"Cumulative Resource Needs Matrix"));
	}

	@Override
	public void handle(ActionEvent event) throws StatusException {
		setStatus(null);
		MenuItem mi = ((MenuItem) event.getSource());
		String id = mi.getId();
		if (id.equals("LoadDataFileAction")) {
			LoadDataFileAction((Scenario) fs);
		}
		else if (id.equals("SaveDataFileAction")) {
			SaveDataFileAction((Scenario) fs);
		}
		else if (id.equals("GenerateReportAction")) {
			GenerateReportAction((Scenario) fs);
		}
		else if (id.equals("newOrderSolverRun")) {
			newOrderSolverRun((Scenario) fs);
		}
		else if (id.equals("newDowntimeSolverRun")) {
			newDowntimeSolverRun((Scenario) fs);
		}
		else if (id.equals("generateDataSolverRun")) {
			generateDataSolverRun((Scenario) fs);
		}
		else if (id.equals("scheduleJobsSolverRun")) {
			scheduleJobsSolverRun((Scenario) fs);
		}
		else if (id.equals("disjunctiveNeedsMatrixMatrixViewer")) {
			disjunctiveNeedsMatrixMatrixViewer((Scenario) fs);
		}
		else if (id.equals("cumulativeNeedsMatrixMatrixViewer")) {
			cumulativeNeedsMatrixMatrixViewer((Scenario) fs);
		}
		else if (id.equals("showSolutionReport")) {
			showSolutionReport((Scenario) fs);
		}
		else if (id.equals("solutionTreeTreeTable")) {
			solutionTreeTreeTable((Scenario) fs);
		}
		else if (id.equals("resourceUtilization0BarChart")) {
			resourceUtilization0BarChart((Scenario) fs);
		}
		else if (id.equals("intermediateSolutionTimeCostLineChart")) {
			intermediateSolutionTimeCostLineChart((Scenario) fs);
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

	public ObservableList<AbstractSolverProperty> getAbstractSolverPropertyData() {
		return abstractSolverPropertyData;
	}

	public ObservableList<SolverProperty> getSolverPropertyData() {
		return solverPropertyData;
	}

	public ObservableList<SolverRun> getSolverRunData() {
		return solverRunData;
	}

	public ObservableList<AbstractDataGeneratorProperty> getAbstractDataGeneratorPropertyData() {
		return abstractDataGeneratorPropertyData;
	}

	public ObservableList<DataGeneratorProperty> getDataGeneratorPropertyData() {
		return dataGeneratorPropertyData;
	}

	public ObservableList<DataGeneratorRun> getDataGeneratorRunData() {
		return dataGeneratorRunData;
	}

	public ObservableList<InputError> getInputErrorData() {
		return inputErrorData;
	}

	public ObservableList<Problem> getProblemData() {
		return problemData;
	}

	public ObservableList<Product> getProductData() {
		return productData;
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

	public ObservableList<ResourceNeed> getResourceNeedData() {
		return resourceNeedData;
	}

	public ObservableList<CumulativeNeed> getCumulativeNeedData() {
		return cumulativeNeedData;
	}

	public ObservableList<CumulativeProfile> getCumulativeProfileData() {
		return cumulativeProfileData;
	}

	public ObservableList<DisjunctiveResource> getDisjunctiveResourceData() {
		return disjunctiveResourceData;
	}

	public ObservableList<CumulativeResource> getCumulativeResourceData() {
		return cumulativeResourceData;
	}

	public ObservableList<ResourceActivity> getResourceActivityData() {
		return resourceActivityData;
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

	public ObservableList<WiP> getWiPData() {
		return wiPData;
	}

	public ObservableList<Downtime> getDowntimeData() {
		return downtimeData;
	}

	public ObservableList<Solution> getSolutionData() {
		return solutionData;
	}

	public ObservableList<JobAssignment> getJobAssignmentData() {
		return jobAssignmentData;
	}

	public ObservableList<TaskAssignment> getTaskAssignmentData() {
		return taskAssignmentData;
	}

	public ObservableList<ResourceUtilization> getResourceUtilizationData() {
		return resourceUtilizationData;
	}

	public ObservableList<IntermediateSolution> getIntermediateSolutionData() {
		return intermediateSolutionData;
	}

	public ObservableList<SolutionError> getSolutionErrorData() {
		return solutionErrorData;
	}
}

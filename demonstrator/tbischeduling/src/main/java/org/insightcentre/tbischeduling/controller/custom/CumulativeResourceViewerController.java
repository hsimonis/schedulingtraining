package org.insightcentre.tbischeduling.controller.custom;

import framework.gui.AbstractJfxMainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.reports.LevelEvent;
import org.insightcentre.tbischeduling.reports.ProcessDiagram;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.insightcentre.tbischeduling.datamodel.TimingDisplay.StartDurationEnd;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.utilities.TypeConverters.toTimingDisplay;


public class CumulativeResourceViewerController extends JJAbstractChartController {

	Scenario base;

	Solution selectedSolution;
	CumulativeResource selectedResource;

	@FXML
	protected LineChart<Number, Number> chart;

	@FXML
	protected NumberAxis xaxis;
	@FXML
	protected NumberAxis yaxis;
	@FXML
	protected BorderPane container;

	@FXML
	protected TreeView<String> treeView;
	@FXML
	protected TreeItem<String> treeRoot;

	@FXML
	protected ChoiceBox<String> solutionChoiceBox;


	@FXML
	public void onSolutionSelect() {
		info("solution select callback");
		updateParameters();
		drawChart();
	}


	@Override
	public void setMainApp(AbstractJfxMainWindow mainApp) {
		info("setMainApp");
		this.mainApp = (GeneratedJfxApp) mainApp;
		base = this.mainApp.basebase;

		ObservableList<String> solutions = FXCollections.observableArrayList();
		solutions.addAll(solutions());
		setChoices(solutionChoiceBox, solutions);


		List<TreeItem<String>> resourceTreeItems = FXCollections.observableArrayList();
		for (String p : cumulativeResources()) {
			// create tree items for each order
			resourceTreeItems.add(new TreeItem<>(p));
		}
		treeRoot.getChildren().removeAll(treeRoot.getChildren());
		treeRoot.getChildren().addAll(resourceTreeItems);
		if (resourceTreeItems.size() > 0) {
			treeView.getSelectionModel().select(1);
		} else {
			chart.setTitle("Cumulative Level");
			chart.getData().clear();
		}
		drawChart();

	}

	protected List<String> cumulativeResources() {
		return this.mainApp.getCumulativeResourceData().stream().
				map(ApplicationObject::getName).
				toList();
	}


	@FXML
	private void initialize() {
		// initialize listeners for GUI objects
		treeView.getSelectionModel()
				.selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> updateTreeSelection(newValue.getValue()));
		// mainApp may not be set, avoid data manipulation here

	}


	// callback from selecting treeItem through listener
	private void updateTreeSelection(String value) {
		info("selected in Treeview " + value);
		updateParameters();
		drawChart();

	}

	// this is used inside the content provider to get the latest values when redrawing
	public void updateParameters() {
		TreeItem<String> selected = treeView.getSelectionModel().getSelectedItem();
		selectedSolution = findSolution(base, solutionChoiceBox.getSelectionModel().getSelectedItem());


		if (selectedSolution != null) {
			if (selected != null && selected.getValue().equals("Resources")) {
				info("All selected");
			} else {
				selectedResource = itemToCumulativeResource(selected);
				// update target zoom to represent the correct value
				if (selectedResource != null) {
					info("draw " + selectedResource);
				}
			}
		}
	}

	private Solution findSolution(Scenario base, String text) {
		if (text == null || text.equals("")) {
			return Solution.findLast(base);
		}
		return Solution.findByName(base, text);

	}

	private CumulativeResource itemToCumulativeResource(TreeItem<String> selected) {
		if (selected == null) {
			return null;
		} else {
			String v = selected.getValue();
			info("tree selected " + v);
			return lookupCumulativeResource(base, v);
		}
	}

	public CumulativeResource lookupCumulativeResource(Scenario base, String v) {
		if (base == null || v == null || v.equals("")) {
			return null;
		}
		return CumulativeResource.findByName(base, v);
	}


	public void drawChart() {
		info("draw cumulative chart");

		if (mainApp != null) {
			// we are now switching back to framework objects, not JavaFX Strings
			base = mainApp.basebase;
			assert (base != null);
			if (selectedResource != null && selectedSolution != null) {
				// we do have a resource and a solution
				chart.setTitle("Cumulative Level for " + selectedResource.getName()+", Solution "+selectedSolution.getName()+", Makespan "+String.format("%,d",selectedSolution.getMakespan()));
				chart.getData().clear();
				chart.getData().add(capacityProfile(selectedResource, selectedSolution));
				chart.getData().add(demandProfile(selectedResource, selectedSolution));
			}

			info("drawn");


		}

	}

	private XYChart.Series<Number, Number> capacityProfile(CumulativeResource r,Solution sol) {
		XYChart.Series<Number, Number> res = new XYChart.Series<>();
		res.setName("Capacity");
		res.getData().add(new XYChart.Data<>(0, 0));
		int previousTime = 0;
		double previousCapacity = 0.0;
		for (CumulativeProfile cp : base.getListCumulativeProfile().stream().
				filter(x -> x.getCumulativeResource() == r).
				sorted(Comparator.comparing(CumulativeProfile::getFrom)).
				toList()) {
			info("cp "+cp.getFrom()+" "+cp.getCapacity());
			res.getData().add(new XYChart.Data<>(previousTime, previousCapacity));
			if (cp.getFrom()<=sol.getMakespan()) {
				res.getData().add(new XYChart.Data<>(cp.getFrom(), previousCapacity));
				res.getData().add(new XYChart.Data<>(cp.getFrom(), cp.getCapacity()));
			} else {
				res.getData().add(new XYChart.Data<>(sol.getMakespan(), previousCapacity));
				break;

			}
			previousTime = cp.getFrom();
			previousCapacity = cp.getCapacity();
		}
		res.getData().add(new XYChart.Data<>(sol!= null?sol.getMakespan():base.getHorizon(), previousCapacity));

		return res;
	}


	private XYChart.Series<Number, Number> demandProfile(CumulativeResource r, Solution sol) {

		XYChart.Series<Number, Number> res = new XYChart.Series<>();
		res.setName("Demand");
		if (sol != null) {
			res.getData().add(new XYChart.Data<>(0, 0));
			List<LevelEvent> list = new ArrayList<>();
			for(TaskAssignment ta:base.getListTaskAssignment().stream().
					filter(x->x.getJobAssignment().getSolution() ==sol).
					filter(x->usesResource(x,r) > 0).
					toList()){
				double demand = usesResource(ta,r);
				list.add(new LevelEvent(ta.getStart(),demand));
				list.add(new LevelEvent(ta.getEnd(),-demand));
			}

			double previousTime = 0;
			double previousDemand = 0.0;
			for(LevelEvent e:list.stream().
					sorted(Comparator.comparing(LevelEvent::getX).thenComparing(LevelEvent::getV)).
					toList()){
				if (previousTime < e.getX()) {
					res.getData().add(new XYChart.Data<>(previousTime, previousDemand));
					res.getData().add(new XYChart.Data<>(e.getX(), previousDemand));
				}
				previousDemand += e.getV();
				previousTime = e.getX();

			}
			res.getData().add(new XYChart.Data<>(previousTime, previousDemand));
			res.getData().add(new XYChart.Data<>(sol.getMakespan(), previousDemand));
		}
		return res;
	}

	private int usesResource(TaskAssignment ta,CumulativeResource r){
		for(CumulativeNeed cn:base.getListCumulativeNeed()){
			if (cn.getProcessStep()==ta.getTask().getProcessStep() && cn.getCumulativeResource() == r){
				return cn.getDemand();
			}
		}
		return 0;
	}
}


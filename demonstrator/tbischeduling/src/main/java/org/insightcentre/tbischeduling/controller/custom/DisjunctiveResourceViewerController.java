package org.insightcentre.tbischeduling.controller.custom;

import framework.gui.AbstractJfxMainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.reports.LevelEvent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;


public class DisjunctiveResourceViewerController extends JJAbstractChartController {

	Scenario base;

	Solution selectedSolution;
	DisjunctiveResource selectedResource;

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
		for (String p : disjunctiveResources()) {
			// create tree items for each order
			resourceTreeItems.add(new TreeItem<>(p));
		}
		treeRoot.getChildren().removeAll(treeRoot.getChildren());
		treeRoot.getChildren().addAll(resourceTreeItems);
		if (resourceTreeItems.size() > 0) {
			treeView.getSelectionModel().select(1);
		} else {
			chart.setTitle("Disjunctive Level");
			chart.getData().clear();
		}

		drawChart();

	}

	protected List<String> disjunctiveResources() {
		return this.mainApp.getDisjunctiveResourceData().stream().
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
				selectedResource = itemToDisjunctiveResource(selected);
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

	private DisjunctiveResource itemToDisjunctiveResource(TreeItem<String> selected) {
		if (selected == null) {
			return null;
		} else {
			String v = selected.getValue();
			info("tree selected " + v);
			return lookupDisjunctiveResource(base, v);
		}
	}

	public DisjunctiveResource lookupDisjunctiveResource(Scenario base, String v) {
		if (base == null || v == null || v.equals("")) {
			return null;
		}
		return DisjunctiveResource.findByName(base, v);
	}


	public void drawChart() {
		info("draw disjunctive chart");

		if (mainApp != null) {
			// we are now switching back to framework objects, not JavaFX Strings
			base = mainApp.basebase;
			assert (base != null);
			if (selectedResource != null && selectedSolution != null) {
				chart.setTitle("Disjunctive Level for " + selectedResource.getName()+", Makespan "+String.format("%,d",selectedSolution.getMakespan()));
				chart.getData().clear();
				chart.getData().add(capacityProfile(selectedResource, selectedSolution));
				chart.getData().add(demandProfile(selectedResource, selectedSolution));
			}

			info("drawn");


		}

	}

	private XYChart.Series<Number, Number> capacityProfile(DisjunctiveResource r,Solution sol) {
		XYChart.Series<Number, Number> res = new XYChart.Series<>();
		res.setName("Capacity");
		res.getData().add(new XYChart.Data<>(0, 1));
		int previousTime = 0;
		double previousCapacity = 1;
		res.getData().add(new XYChart.Data<>(sol!= null?sol.getMakespan():base.getHorizon(), previousCapacity));

		return res;
	}


	private XYChart.Series<Number, Number> demandProfile(DisjunctiveResource r, Solution sol) {

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

	private int usesResource(TaskAssignment ta,DisjunctiveResource r){
		return ta.getDisjunctiveResource()==r ? 1:0;
	}
}


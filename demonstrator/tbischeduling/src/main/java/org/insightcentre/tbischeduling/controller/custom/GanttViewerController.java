package org.insightcentre.tbischeduling.controller.custom;

import framework.gui.AbstractJfxMainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.ResourceActivity;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.Solution;
import org.insightcentre.tbischeduling.datamodel.TaskAssignment;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;


public class GanttViewerController extends JJAbstractChartController {
	private GanttContent contentProvider=null;
	private GanttContent contentProvider2=null;

	@FXML
	private SplitPane splitter;
	@FXML
	private ScrollPane scroller;
	@FXML
	private Canvas canvas2;
	@FXML
	private Canvas canvas;

	@FXML
	protected ChoiceBox<String> solutionChoiceBox;

	@FXML
	protected CheckBox showMachines;
	@FXML
	protected CheckBox showJobs;
	@FXML
	protected CheckBox showEarly;
	@FXML
	protected CheckBox showLate;
	@FXML
	protected CheckBox showWait;
	@FXML
	protected CheckBox showRelease;
	@FXML
	protected CheckBox highlightCritical;

	@FXML
	protected ChoiceBox<String> colorByChoiceBox;

	@FXML
	protected ChoiceBox<String> taskLabelChoiceBox;

	@FXML
	protected ChoiceBox<String> jobOrderChoiceBox;

	@FXML
	protected Slider alpha;

	@Override
	public void setMainApp(AbstractJfxMainWindow mainApp) {
		info("setMainApp");
		this.mainApp = (GeneratedJfxApp) mainApp;

		ObservableList<String> solutions = FXCollections.observableArrayList();
		solutions.addAll(solutions());
		setChoices(solutionChoiceBox,solutions);

		contentProvider = new GanttContent(canvas,true);
		contentProvider.xOffset = 50.0;

		contentProvider2 = new GanttContent(canvas2,false);
		contentProvider2.xOffset = 50.0;

		drawChart();


	}

	@FXML
	public void onShowSelect() {
		drawChart();
	}

	@FXML
	public void onSolutionSelect() {
		drawChart();
	}


	@FXML
	public void onColorBySelect() {
		drawChart();
	}
	@FXML
	public void onTaskLabelSelect() {
		drawChart();
	}
	@FXML
	public void onJobOrderSelect() {
		drawChart();
	}

	@FXML
	private void initialize() {
		// mainApp may not be set, avoid data manipulation here
		info("initialize Gantt");

		canvas.setOnMouseClicked(event -> {
			ganttSelect(event.getX(), event.getY());
		});
		canvas2.setOnMouseClicked(event -> {
			ganttSelectBottom(event.getX(), event.getY());
		});
		alpha.valueProperty().addListener((observable, oldValue, newValue) -> {
			drawChart();
		});
		drawChart();
	}

	public void ganttSelect(double x,double y){
		info("clicked "+x+" "+y);
		ResourceActivity selected =contentProvider.insideRegion(x,y);
		if (selected == contentProvider.getSelected()){
			contentProvider.setSelected(null);
			contentProvider2.setSelected(null);
			drawChart();
		} else {
			contentProvider.setSelected(selected);
			contentProvider2.setSelected(selected);
			if (selected != null) {
				info("inside " + selected.getName());
				drawChart();
			}
		}
	}
	public void ganttSelectBottom(double x,double y){
		info("clicked "+x+" "+y);
		ResourceActivity selected =contentProvider2.insideRegion(x,y);
		if (selected == contentProvider.getSelected()){
			contentProvider.setSelected(null);
			contentProvider2.setSelected(null);
			drawChart();
		} else {
			contentProvider.setSelected(selected);
			contentProvider2.setSelected(selected);
			if (selected != null) {
				info("inside " + selected.getName());
				drawChart();
			}
		}
	}

	public void drawChart() {
		info("draw gantt");
		String sol = solutionChoiceBox.getSelectionModel().getSelectedItem();

		if (mainApp != null && contentProvider != null && sol != null) {
			// we are now switching back to framework objects, not JavaFX Strings
			Scenario base = mainApp.basebase;
			assert(base != null);
			Solution solution = Solution.findByName(base,sol);
			assert(solution!=null);

			contentProvider.setSizeTop(base,solution);
			contentProvider2.setSizeBottom(base,solution);

			String colorBy = colorByChoiceBox.getSelectionModel().getSelectedItem();
			String taskLabel = taskLabelChoiceBox.getSelectionModel().getSelectedItem();
			String jobOrder = jobOrderChoiceBox.getSelectionModel().getSelectedItem();
			double alphaValue = alpha.getValue();

			contentProvider.draw(base, solution,showMachines.isSelected(),
					showJobs.isSelected(), showEarly.isSelected(),
					showLate.isSelected(), showWait.isSelected(),
					showRelease.isSelected(),highlightCritical.isSelected(),
					colorBy,taskLabel,jobOrder,alphaValue);
			contentProvider2.draw(base, solution,showMachines.isSelected(),
					showJobs.isSelected(), showEarly.isSelected(),
					showLate.isSelected(), showWait.isSelected(),
					showRelease.isSelected(),highlightCritical.isSelected(),
					colorBy,taskLabel,jobOrder,alphaValue);
			System.out.println("drawn");
		}

	}


}

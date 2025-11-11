package org.insightcentre.tbischeduling.controller.custom;

import com.dlsc.unitfx.IntegerInputField;
import framework.gui.AbstractJfxMainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.custom.ResizableCanvas;

import static org.insightcentre.tbischeduling.datamodel.ColorBy.Mixed;
import static org.insightcentre.tbischeduling.datamodel.JobOrder.Start;
import static org.insightcentre.tbischeduling.datamodel.ResourceChoice.All;
import static org.insightcentre.tbischeduling.datamodel.ResourceZoom.Normal;
import static org.insightcentre.tbischeduling.datamodel.TaskLabel.None;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.utilities.TypeConverters.toLineChoice;


public class GanttBorderViewerController extends JJAbstractChartController {
	GanttBorderContent contentProvider;
	@FXML
	private ResizableCanvas leftCanvas;
	@FXML
	private ResizableCanvas topCanvas;
	@FXML
	private ResizableCanvas canvas;
	@FXML
	protected ScrollBar rightBar;
	@FXML
	protected ScrollBar bottomBar;

	@FXML
	protected ChoiceBox<String> resourceZoomChoiceBox;
	@FXML
	protected ChoiceBox<String> solutionChoiceBox;

	@FXML
//	protected CheckBox showMachines;
	protected ChoiceBox<String> showMachinesBox;

	@FXML
//	protected CheckBox showJobs;
	protected ChoiceBox<String> showJobsBox;

	@FXML
	protected CheckBox highlightCritical;

	@FXML
	protected ChoiceBox<String> colorByChoiceBox;

	@FXML
	protected ChoiceBox<String> taskLabelChoiceBox;

	@FXML
	protected ChoiceBox<String> jobOrderChoiceBox;

	@FXML
	protected ChoiceBox<String> datesDisplayChoiceBox;

	@FXML
	protected Slider alpha;

	@FXML
	protected Slider zoom;

	/* test fields for details tab */
	@FXML
	protected TextField name;
	@FXML
	protected TextField job;
	@FXML
	protected TextField release;
	@FXML
	protected TextField due;
	@FXML
	protected TextField late;
	@FXML
	protected TextField early;
	@FXML
	protected TextField order;
	@FXML
	protected TextField product;
	@FXML
	protected TextField processStep;
	@FXML
	protected TextField start;
	@FXML
	protected TextField end;
	@FXML
	protected TextField duration;
	@FXML
	protected TextField wait;
	@FXML
	protected TextField setup;
	@FXML
	protected TextField idle;
	@FXML
	protected TextField machine;
	@FXML
	protected TextField qty;


	/* Colors for color tab */
	@FXML
	protected ColorPicker machineColor;
	@FXML
	protected ColorPicker jobColor;
	@FXML
	protected ColorPicker wipColor;
	@FXML
	protected ColorPicker downtimeColor;
	@FXML
	protected ColorPicker selectedColor;
	@FXML
	protected ColorPicker sameJobColor;
	@FXML
	protected ColorPicker sameMachineColor;
	@FXML
	protected ColorPicker alternativeColor;
	@FXML
	protected ColorPicker makespanColor;
	@FXML
	protected ColorPicker horizonColor;
	@FXML
	protected ColorPicker lateColor;
	@FXML
	protected ColorPicker earlyColor;
	@FXML
	protected ColorPicker releaseColor;
	@FXML
	protected ColorPicker waitColor;
	@FXML
	protected ColorPicker textColor;
	@FXML
	protected ColorPicker drawColor;
	@FXML
	protected ColorPicker gridColor;
	@FXML
	protected ColorPicker noneColor;
	@FXML
	protected ColorPicker defaultColor;
	@FXML
	protected ColorPicker setupColor;
	@FXML
	protected ColorPicker idleColor;

	/*   fields for layout tab */
	@FXML
	protected IntegerInputField resourceWidth;

	@FXML
	protected IntegerInputField itemHeight;

	@FXML
	protected IntegerInputField itemGap;
	@FXML
	protected IntegerInputField resourceLabelOffset;
	@FXML
	protected IntegerInputField taskLabelOffset;
	@FXML
	protected IntegerInputField taskLabelIndent;
	@FXML
	protected IntegerInputField titleHeight;
	@FXML
	protected IntegerInputField titleLabelOffset;
	@FXML
	protected ChoiceBox<String> lateLineChoiceBox;
	@FXML
	protected ChoiceBox<String> earlyLineChoiceBox;
	@FXML
	protected ChoiceBox<String> releaseLineChoiceBox;
	@FXML
	protected ChoiceBox<String> waitLineChoiceBox;
	@FXML
	protected ChoiceBox<String> setupLineChoiceBox;
	@FXML
	protected ChoiceBox<String> idleLineChoiceBox;




	@Override
	public void setMainApp(AbstractJfxMainWindow mainApp) {
//		info("setMainApp");
		this.mainApp = (GeneratedJfxApp) mainApp;
		Scenario base = this.mainApp.basebase;

		ObservableList<String> solutions = FXCollections.observableArrayList();
		solutions.addAll(solutions());
		setChoices(solutionChoiceBox,solutions);

		// set the choice boxes to the feasible values based on the enum types
		ObservableList<String> resourceZoomChoices = FXCollections.observableArrayList(ResourceZoom.getNames());
		setChoices(resourceZoomChoiceBox,resourceZoomChoices);
		resourceZoomChoiceBox.getSelectionModel().select(Normal.toString());
		ObservableList<String> colorByChoices = FXCollections.observableArrayList(ColorBy.getNames());
		setChoices(colorByChoiceBox,colorByChoices);
		colorByChoiceBox.getSelectionModel().select(Mixed.toString());
		ObservableList<String> taskLabelChoices = FXCollections.observableArrayList(TaskLabel.getNames());
		setChoices(taskLabelChoiceBox,taskLabelChoices);
		taskLabelChoiceBox.getSelectionModel().select(None.toString());
		ObservableList<String> jobOrderChoices = FXCollections.observableArrayList(JobOrder.getNames());
		setChoices(jobOrderChoiceBox,jobOrderChoices);
		jobOrderChoiceBox.getSelectionModel().select(Start.toString());
		ObservableList<String> showMachinesChoices = FXCollections.observableArrayList(ResourceChoice.getNames());
		setChoices(showMachinesBox,showMachinesChoices);
		showMachinesBox.getSelectionModel().select(All.toString());
		ObservableList<String> showJobsChoices = FXCollections.observableArrayList(ResourceChoice.getNames());
		setChoices(showJobsBox,showJobsChoices);
		showJobsBox.getSelectionModel().select(All.toString());
		ObservableList<String> datesDisplayChoices = FXCollections.observableArrayList(DatesDisplay.getNames());
		setChoices(datesDisplayChoiceBox,datesDisplayChoices);
//		info("Initialize datesDisplay "+base.getGanttProperty().getDatesDisplay());
		datesDisplayChoiceBox.getSelectionModel().select(base.getGanttProperty().getDatesDisplay().toString());

		contentProvider = new GanttBorderContent(this,canvas,topCanvas,leftCanvas,rightBar,bottomBar,base.getGanttProperty());
		contentProvider.initialize();
		contentProvider.setSolution(solutionChoiceBox.getSelectionModel().getSelectedItem());
		contentProvider.setShowMachinesBox(showMachinesBox.getSelectionModel().getSelectedItem());
		contentProvider.setShowJobsBox(showJobsBox.getSelectionModel().getSelectedItem());
		setDialogColors(contentProvider);
		setDialogLayout(contentProvider);


		drawChart();


	}



	@FXML
	private void initialize() {
		// mainApp may not be set, avoid data manipulation here
		zoom.valueProperty().addListener((observable, oldValue, newValue) -> drawChart());
		alpha.valueProperty().addListener((observable, oldValue, newValue) -> drawChart());

	}

	// this is used inside the content provider to get the latest values when redrawing
	public void updateParameters(){
		if (contentProvider != null){
//			info("updateParameters sol "+solutionChoiceBox.getSelectionModel().getSelectedItem()+
//					" m "+showMachinesBox.getSelectionModel().getSelectedItem()+
//					" j "+showJobsBox.getSelectionModel().getSelectedItem()+
//					" crit "+highlightCritical.isSelected()+
//					" a "+alpha.getValue()+
//					" z "+zoom.getValue()+
//					" Color "+colorByChoiceBox.getSelectionModel().getSelectedItem()+
//					" Label "+taskLabelChoiceBox.getSelectionModel().getSelectedItem()+
//					" Order "+jobOrderChoiceBox.getSelectionModel().getSelectedItem()+
//					" Dates "+datesDisplayChoiceBox.getSelectionModel().getSelectedItem()+
//					" rz "+resourceZoomChoiceBox.getSelectionModel().getSelectedItem());
			contentProvider.setResourceZoom(resourceZoomChoiceBox.getSelectionModel().getSelectedItem());
			contentProvider.setSolution(solutionChoiceBox.getSelectionModel().getSelectedItem());
			contentProvider.setShowMachinesBox(showMachinesBox.getSelectionModel().getSelectedItem());
			contentProvider.setShowJobsBox(showJobsBox.getSelectionModel().getSelectedItem());
			contentProvider.setHighlightCritical(highlightCritical.isSelected());
			contentProvider.setAlpha(alpha.getValue());
			contentProvider.setZoom(zoom.getValue());
			contentProvider.setColorBy(colorByChoiceBox.getSelectionModel().getSelectedItem());
			contentProvider.setTaskLabel(taskLabelChoiceBox.getSelectionModel().getSelectedItem());
			contentProvider.setJobOrder(jobOrderChoiceBox.getSelectionModel().getSelectedItem());
			contentProvider.setDatesDisplay(datesDisplayChoiceBox.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	public void onColorPicked() {
//		info("Color picked");
		updateColors(contentProvider);
		drawChart();
	}

	@FXML
	public void onValueEntered(){
//		info("Value entered");
		updateLayout(contentProvider);
		drawChart();
	}



	public void setDialogLayout(GanttBorderContent c){
		resourceWidth.setValue(c.resourceWidth);
		itemHeight.setValue(c.itemHeight);
		itemGap.setValue(c.itemGap);
		resourceLabelOffset.setValue(c.resourceLabelOffset);
		taskLabelOffset.setValue(c.taskLabelOffset);
		taskLabelIndent.setValue(c.taskLabelIndent);
		titleHeight.setValue(c.titleHeight);
		titleLabelOffset.setValue(c.titleLabelOffset);
		GanttProperty gp=c.getGanttProperty();
		lateLineChoiceBox.getSelectionModel().select(gp.getShowLate().toString());
		earlyLineChoiceBox.getSelectionModel().select(gp.getShowEarly().toString());
		releaseLineChoiceBox.getSelectionModel().select(gp.getShowRelease().toString());
		waitLineChoiceBox.getSelectionModel().select(gp.getShowWait().toString());
		setupLineChoiceBox.getSelectionModel().select(gp.getShowSetup().toString());
//		info("Setting idle "+gp.getShowIdle().toString());
		idleLineChoiceBox.getSelectionModel().select(gp.getShowIdle().toString());
	}

	private void updateLayout(GanttBorderContent c){
		c.resourceWidth = resourceWidth.getValue();
		c.itemHeight = itemHeight.getValue();
		c.itemGap = itemGap.getValue();
		c.resourceLabelOffset = resourceLabelOffset.getValue();
		c.taskLabelOffset = taskLabelOffset.getValue();
		c.taskLabelIndent = taskLabelIndent.getValue();
		c.titleHeight = titleHeight.getValue();
		c.titleLabelOffset = titleLabelOffset.getValue();
		GanttProperty gp=c.getGanttProperty();
		gp.setShowLate(toLineChoice(lateLineChoiceBox.getSelectionModel().getSelectedItem()));
		gp.setShowEarly(toLineChoice(earlyLineChoiceBox.getSelectionModel().getSelectedItem()));
		gp.setShowRelease(toLineChoice(releaseLineChoiceBox.getSelectionModel().getSelectedItem()));
		gp.setShowWait(toLineChoice(waitLineChoiceBox.getSelectionModel().getSelectedItem()));
		gp.setShowSetup(toLineChoice(setupLineChoiceBox.getSelectionModel().getSelectedItem()));
		gp.setShowIdle(toLineChoice(idleLineChoiceBox.getSelectionModel().getSelectedItem()));
//		info("IdleUpdate "+idleLineChoiceBox.getSelectionModel().getSelectedItem());
		// have to reposition the windows as well
		c.initialize();


	}

	public void setDialogColors(GanttBorderContent c){
		machineColor.setValue(c.machineColor);
		jobColor.setValue(c.jobColor);
		wipColor.setValue(c.wipColor);
		downtimeColor.setValue(c.downtimeColor);
		selectedColor.setValue(c.selectedColor);
		sameJobColor.setValue(c.sameJobColor);
		sameMachineColor.setValue(c.sameMachineColor);
		alternativeColor.setValue(c.alternativeColor);
		makespanColor.setValue(c.makespanColor);
		horizonColor.setValue(c.horizonColor);
		lateColor.setValue(c.lateColor);
		earlyColor.setValue(c.earlyColor);
		releaseColor.setValue(c.releaseColor);
		waitColor.setValue(c.waitColor);
		textColor.setValue(c.textColor);
		drawColor.setValue(c.drawColor);
		gridColor.setValue(c.gridColor);
		noneColor.setValue(c.noneColor);
		defaultColor.setValue(c.defaultColor);
		setupColor.setValue(c.setupColor);
		idleColor.setValue(c.idleColor);

	}

	private void updateColors(GanttBorderContent c){
		c.machineColor=machineColor.getValue();
		c.jobColor = jobColor.getValue();
		c.wipColor = wipColor.getValue();
		c.downtimeColor = downtimeColor.getValue();
		c.selectedColor = selectedColor.getValue();
		c.sameJobColor = sameJobColor.getValue();
		c.sameMachineColor = sameMachineColor.getValue();
		c.alternativeColor = alternativeColor.getValue();
		c.makespanColor = makespanColor.getValue();
		c.horizonColor = horizonColor.getValue();
		c.lateColor = lateColor.getValue();
		c.earlyColor = earlyColor.getValue();
		c.releaseColor = releaseColor.getValue();
		c.waitColor = waitColor.getValue();
		c.textColor = textColor.getValue();
		c.drawColor = drawColor.getValue();
		c.gridColor = gridColor.getValue();
		c.noneColor = noneColor.getValue();
		c.defaultColor = defaultColor.getValue();
		c.setupColor = setupColor.getValue();
		c.idleColor = idleColor.getValue();
	}
	@FXML
	public void onShowSelect() {
//		info("Show select choice callback");
		drawChart();
	}
	@FXML
	public void onResourceZoomSelect() {
//		info("ResourceZoom callback");
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
	public void onDatesDisplaySelect() {
		drawChart();
	}

	public void updateDetails(ResourceActivity ra){
		GanttBorderContent cp = contentProvider;
		name.setText(ra.getName());
		start.setText(cp.internalExternalDate(ra.getStart()));
		end.setText(cp.internalExternalDate(ra.getEnd()));
		duration.setText(cp.internalExternalPeriod(ra.getDuration()));
        if (ra.getDisjunctiveResource()!= null) {
            machine.setText(ra.getDisjunctiveResource().getName());
        }
		if (ra instanceof TaskAssignment t) {
			wait.setText(cp.internalExternalPeriod(t.getWaitBefore()));
			job.setText(t.getJobAssignment().getJob().getName());
			release.setText(cp.internalExternalDate(t.getJobAssignment().getJob().getOrder().getRelease()));
			due.setText(cp.internalExternalDate(t.getJobAssignment().getJob().getOrder().getDue()));
			early.setText(cp.internalExternalPeriod(t.getJobAssignment().getEarly()));
			late.setText(cp.internalExternalPeriod(t.getJobAssignment().getLate()));
			order.setText(t.getTask().getJob().getOrder().getName());
			product.setText(t.getTask().getJob().getOrder().getProduct().getName());
			qty.setText(String.format("%,d",t.getTask().getJob().getOrder().getQty()));
			processStep.setText(t.getTask().getProcessStep().getName());
			setup.setText(cp.internalExternalPeriod(t.getSetupBefore()));
			idle.setText(cp.internalExternalPeriod(t.getIdleBefore()));
		} else {
			wait.setText("");
			job.setText("");
			release.setText("");
			due.setText("");
			early.setText("");
			late.setText("");
			order.setText("");
			product.setText("");
			qty.setText("");
			processStep.setText("");
			setup.setText("");
			idle.setText("");

		}
	}
	public void resetDetails(){
		name.setText("");
		job.setText("");
		release.setText("");
		due.setText("");
		early.setText("");
		late.setText("");
		order.setText("");
		product.setText("");
		processStep.setText("");
		start.setText("");
		end.setText("");
		duration.setText("");
		wait.setText("");
		setup.setText("");
		idle.setText("");
		machine.setText("");
		qty.setText("");
	}


	public void drawChart() {
//		info("draw gantt");

		if (mainApp != null && contentProvider != null ) {
			// we are now switching back to framework objects, not JavaFX Strings
			Scenario base = mainApp.basebase;
			assert(base != null);
//			info("Scenario ");

			contentProvider.setScenario(base);

			contentProvider.drawChart();

		}

	}


}

package org.insightcentre.tbischeduling.controller.custom;

import framework.gui.AbstractJfxMainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.reports.ProcessDiagram;

import java.util.List;

import static org.insightcentre.tbischeduling.datamodel.ColorBy.Mixed;
import static org.insightcentre.tbischeduling.datamodel.TimingDisplay.StartDurationEnd;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.utilities.TypeConverters.toTimingDisplay;


public class PertViewerController extends JJAbstractChartController {

	Scenario base;

	Order selectedOrder;
	Solution selectedSolution;
	String timingDisplayString;
	Image image;


	@FXML
	protected BorderPane container;
	@FXML
	protected ImageView pertImage;
	@FXML
	protected TreeView<String> treeView;
	@FXML
	protected TreeItem<String> treeRoot;

	@FXML
	protected ChoiceBox<String> solutionChoiceBox;
	@FXML
	protected ChoiceBox<String> showTimingBox;

	@FXML
	protected CheckBox showOrder;
	@FXML
	protected CheckBox showProduct;
	@FXML
	protected CheckBox showProcess;
	@FXML
	protected CheckBox showProcessStep;
	@FXML
	protected CheckBox showMachines;
	@FXML
	protected CheckBox showCumulative;
	@FXML
	protected CheckBox showLinks;


	@FXML
	public void onSolutionSelect() {
		info("solution select callback");
		updateParameters();
		drawChart();
	}

	@FXML
	public void onShowSelect() {
		info("Show select callback");
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
		setChoices(solutionChoiceBox,solutions);

		ObservableList<String> timingChoices = FXCollections.observableArrayList(TimingDisplay.getNames());
		setChoices(showTimingBox,timingChoices);
		showTimingBox.getSelectionModel().select(StartDurationEnd.toString());

		List<TreeItem<String>> processTreeItems = FXCollections.observableArrayList();
		for(String p:orders()){
			// create tree items for each order
			processTreeItems.add(new TreeItem<>(p));
		}
		treeRoot.getChildren().removeAll(treeRoot.getChildren());
		treeRoot.getChildren().addAll(processTreeItems);
		treeView.getSelectionModel().select(1);

		drawChart();

	}

	protected List<String> orders() {
		return this.mainApp.getOrderData().stream().
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
	private void updateTreeSelection(String value){
		info("selected in Treeview "+value);
		updateParameters();
		drawChart();

	}

	// this is used inside the content provider to get the latest values when redrawing
	public void updateParameters(){
		TreeItem<String> selected = treeView.getSelectionModel().getSelectedItem();
		selectedSolution = findSolution(base,solutionChoiceBox.getSelectionModel().getSelectedItem());
		timingDisplayString = showTimingBox.getSelectionModel().getSelectedItem();

		if (selectedSolution != null && timingDisplayString != null) {
			if (selected != null && selected.getValue().equals("Orders")) {
				info("All selected");
				image = new ProcessDiagram(base, selectedSolution, toTimingDisplay(timingDisplayString),
						showOrder.isSelected(), showProduct.isSelected(),
						showProcess.isSelected(),showProcessStep.isSelected(),
						showMachines.isSelected(), showCumulative.isSelected(),
						showLinks.isSelected()).getImage();
			} else {
				selectedOrder = itemToOrder(selected);
				// update target zoom to represent the correct value
				if (selectedOrder != null) {
					image = new ProcessDiagram(base, selectedOrder, selectedSolution, toTimingDisplay(timingDisplayString),
							showOrder.isSelected(), showProduct.isSelected(),
							showProcess.isSelected(),showProcessStep.isSelected(),
							showMachines.isSelected(), showCumulative.isSelected(),
							showLinks.isSelected()).getImage();
				}
			}
		}
	}

	private Solution findSolution(Scenario base, String text){
		if (text == null || text.equals("")){
			return Solution.findLast(base);
		}
		return Solution.findByName(base,text);

	}

	private Order itemToOrder(TreeItem<String> selected){
		if (selected == null){
			return null;
		} else {
			String v = selected.getValue();
			info("tree selected "+v);
			return lookupOrder(base,v);
		}
	}
	public  Order lookupOrder(Scenario base, String v){
		if (base == null || v == null || v.equals("")){
			return null;
		}
		return Order.findByName(base,v);
	}





	public void drawChart() {
		info("draw level diagram");

		if (mainApp != null ) {
			// we are now switching back to framework objects, not JavaFX Strings
			base = mainApp.basebase;
			assert(base != null);

			pertImage.setImage(image);

			info("drawn");



		}

	}


}

package org.insightcentre.tbischeduling.controller.custom;

import framework.gui.AbstractJfxMainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.insightcentre.tbischeduling.GeneratedJfxApp;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.reports.ProcessDiagram;

import java.util.List;

import static org.insightcentre.tbischeduling.datamodel.DurationDisplay.FixedPerUnit;
import static org.insightcentre.tbischeduling.datamodel.TimingDisplay.StartDurationEnd;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.utilities.TypeConverters.toDurationDisplay;


public class DiagramViewerController extends JJAbstractChartController {

	Scenario base;

	Process selectedProcess;
	Image image;


	@FXML
	protected BorderPane container;
	@FXML
	protected ImageView diagramImage;
	@FXML
	protected TreeView<String> treeView;
	@FXML
	protected TreeItem<String> treeRoot;

	@FXML
	protected ChoiceBox<String> showDurationBox;

	@FXML
	protected CheckBox showProcess;
	@FXML
	protected CheckBox showStage;
	@FXML
	protected CheckBox showMachines;
	@FXML
	protected CheckBox showCumulative;


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

		ObservableList<String> durationChoices = FXCollections.observableArrayList(DurationDisplay.getNames());
		setChoices(showDurationBox,durationChoices);
		showDurationBox.getSelectionModel().select(FixedPerUnit.toString());


		List<TreeItem<String>> processTreeItems = FXCollections.observableArrayList();
		for(String p:processes()){
			// create tree items for each product
			processTreeItems.add(new TreeItem<>(p));
		}
		treeRoot.getChildren().removeAll(treeRoot.getChildren());
		treeRoot.getChildren().addAll(processTreeItems);
		if (processTreeItems.size() > 0) {
			treeView.getSelectionModel().select(1);
		}

		drawChart();

	}

	protected List<String> processes() {
		return this.mainApp.getProcessData().stream().
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
		String durationDisplayString = showDurationBox.getSelectionModel().getSelectedItem();

		if (durationDisplayString != null) {
			if (selected != null && selected.getValue().equals("Processes")) {
				info("All selected");
				image = new ProcessDiagram(base, toDurationDisplay(durationDisplayString),
						showProcess.isSelected(), showStage.isSelected(),
						showMachines.isSelected(), showCumulative.isSelected()).getImage();
			} else {
				selectedProcess = itemToProcess(selected);
				// update target zoom to represent the correct value
				if (selectedProcess != null) {
					image = new ProcessDiagram(base, selectedProcess, toDurationDisplay(durationDisplayString),
							showProcess.isSelected(), showStage.isSelected(),
							showMachines.isSelected(), showCumulative.isSelected()).getImage();
				}
			}
		}
	}

	private Process itemToProcess(TreeItem<String> selected){
		if (selected == null){
			return null;
		} else {
			String v = selected.getValue();
			info("tree selected "+v);
			return lookupProcess(base,v);
		}
	}
	public  Process lookupProcess(Scenario base, String v){
		if (base == null || v == null || v.equals("")){
			return null;
		}
		return Process.findByName(base,v);
	}





	public void drawChart() {
		info("draw level diagram");

		if (mainApp != null ) {
			// we are now switching back to framework objects, not JavaFX Strings
			base = mainApp.basebase;
			assert(base != null);

			diagramImage.setImage(image);

			info("drawn");



		}

	}


}

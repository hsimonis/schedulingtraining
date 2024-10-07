package org.insightcentre.tbischeduling.controller;

import org.insightcentre.tbischeduling.datamodel.IntermediateSolution;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Tooltip;

import java.util.*;

/**
 * Generated  **/
public class IntermediateSolutionTimeCostLineChartController extends ChartController {

	@FXML
	private LineChart<Number,Number> chart;

	@FXML
	private NumberAxis xaxis;

	@FXML
	private NumberAxis yaxis;

	@FXML
	private void initialize() {
		chart.setLegendVisible(false);
		chart.setTitle("Intermediate Solution Cost over Time");
		xaxis.setLabel("Time");
		yaxis.setLabel("Cost");
	}

	public void drawChart() {
		XYChart.Series<Number,Number> series = new XYChart.Series<>();
        List<String> tips = new ArrayList<>();
        mainApp.getIntermediateSolutionData().stream().
        filter(x ->!Double.isNaN(x.getTime()) && !Double.isNaN(x.getCost())).
        sorted(Comparator.comparing(IntermediateSolution::getTime)).
        forEach(x -> {
            double valueX = x.getTime();
            double valueY = x.getCost();
            String tip = x.getName();
            tips.add(tip);
            series.getData().add(new XYChart.Data<Number,Number>(valueX,valueY));
        });
		chart.getData().clear();
		chart.getData().add(series);
        int i=0;
        for (XYChart.Data<Number,Number> d:series.getData()){
            Tooltip tip = new Tooltip(); tip.setText(tips.get(i++));
            Tooltip.install(d.getNode(),tip);
        }
	}
}

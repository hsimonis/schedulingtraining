package org.insightcentre.tbischeduling.controller;

import org.insightcentre.tbischeduling.datamodel.ResourceUtilization;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Tooltip;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Generated  **/
public class ResourceUtilizationBarChart0BarChartController extends ChartController {

	@FXML
	private BarChart<Number,String> chart;

	@FXML
	private NumberAxis xaxis;

	@FXML
	private CategoryAxis yaxis;

	@FXML
	private void initialize() {
		chart.setTitle("Disjunctive Resource Utilization");
		chart.setBarGap(3);
		chart.setCategoryGap(20);
	}

	public void drawChart() {
		XYChart.Series<Number,String> series0 = new XYChart.Series<>();
        series0.setName("ResourceUtilization");
        mainApp.getResourceUtilizationData().stream().
        sorted(Comparator.comparing(ResourceUtilization::getName)).
        forEach(x -> {
            String name = x.getName();
            double value0 = x.getUtilization();
                series0.getData().add(new XYChart.Data<>(value0,name));
        });
		chart.getData().clear();
		chart.getData().add(series0);
        for (XYChart.Data d:series0.getData()){
            Tooltip tip = new Tooltip();tip.setText("ResourceUtilization: "+d.getXValue()+" "+d.getYValue());
            Tooltip.install(d.getNode(),tip);
        }
	}
}

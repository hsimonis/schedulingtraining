package org.insightcentre.tbischeduling.controller;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;

import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.controller.MatrixTab;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.ComboBoxTableCell;

import javafx.scene.control.cell.TextFieldTableCell;

public class CumulativeNeedsMatrixTab extends MatrixTab {

	public CumulativeNeedsMatrixTab(Scenario base) {
		super("Cumulative Resource Needs Matrix", base);
	}

	@Override
	protected void initHash() {
		for (CumulativeNeed item : base.getListCumulativeNeed()) {
			hash.put(item.getProcessStep().getId() + ":" + item.getCumulativeResource().getId(), item);
		}
	}

	@Override
	protected TableColumn<ObservableList<ObjectProperty<ApplicationObject>>, String> createColumn(
			String name, int index) {
		final TableColumn<ObservableList<ObjectProperty<ApplicationObject>>, String> col =
				super.createColumn(name, index);
		col.setCellFactory(TextFieldTableCell.forTableColumn());
		col.setCellValueFactory(features -> {
			ApplicationObject obj = features.getValue().get(index).getValue();
			String label = makeLabel(obj, CumulativeNeed.class, "getDemand");
			return new SimpleStringProperty(label);
		});
		col.setOnEditCommit(event -> {
			CumulativeNeed bw = (CumulativeNeed) table.getSelectionModel().getSelectedItem().get(index).getValue();
			bw.setDemand(Integer.valueOf(event.getNewValue()));
		});
		return col;
	}

	@Override
	protected List<ApplicationObject> findColumns(Scenario base) {
		return base.getListCumulativeResource().stream().sorted(Comparator.comparing(CumulativeResource::getName)).collect(toList());
	}

	@Override
	protected List<ApplicationObject> findRows(Scenario base) {
		return base.getListProcessStep().stream().sorted(Comparator.comparing(ProcessStep::getName)).collect(toList());
	}
}

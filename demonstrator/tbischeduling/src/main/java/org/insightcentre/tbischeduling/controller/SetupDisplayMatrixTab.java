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

public class SetupDisplayMatrixTab extends MatrixTab {

	public SetupDisplayMatrixTab(Scenario base) {
		super("Setup Display Matrix", base);
	}

	@Override
	protected void initHash() {
		for (SetupMatrix item : base.getListSetupMatrix()) {
			hash.put(item.getFrom().getId() + ":" + item.getTo().getId(), item);
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
			String label = makeLabel(obj, SetupMatrix.class, "getValue");
			return new SimpleStringProperty(label);
		});
		col.setOnEditCommit(event -> {
			SetupMatrix bw = (SetupMatrix) table.getSelectionModel().getSelectedItem().get(index).getValue();
			bw.setValue(Integer.valueOf(event.getNewValue()));
		});
		return col;
	}

	@Override
	protected List<ApplicationObject> findColumns(Scenario base) {
		return base.getListSetupType().stream().sorted(Comparator.comparing(SetupType::getName)).collect(toList());
	}

	@Override
	protected List<ApplicationObject> findRows(Scenario base) {
		return base.getListSetupType().stream().sorted(Comparator.comparing(SetupType::getName)).collect(toList());
	}
}

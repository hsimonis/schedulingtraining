package org.insightcentre.tbischeduling.controller;

import framework.gui.StatusException;
import java.lang.String;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.insightcentre.tbischeduling.GeneratedJfxApp;

/**
 * Generated at 11:30:11 on 2024-10-23 */
public class RootController {
	private GeneratedJfxApp mainApp;

	@FXML
	private Label statusBar;

	public void setMainApp(GeneratedJfxApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handle(ActionEvent e) throws StatusException {
		mainApp.handle(e);
	}

	public void setStatus(String text) {
		statusBar.setText(text);
	}
}

package org.insightcentre.tbischeduling.controller.custom;

import javafx.fxml.FXML;

public class RowCounter {
    private int currentRowIndex = 0;

    /**
     * Return current rowIndex.
     * @return
     */
    public int getCr() {
        return currentRowIndex;
    }

    /**
     * Return current rowIndex and increase it.
     * @return
     */
    @FXML public int getNr() {
        return currentRowIndex++;
    }
}
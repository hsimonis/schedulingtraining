package org.insightcentre.tbischeduling.datamodel.custom;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import org.insightcentre.tbischeduling.controller.custom.GanttBorderContent;
import org.insightcentre.tbischeduling.controller.custom.GanttBorderViewerController;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;

public class ResizableCanvas extends Canvas {

    GanttBorderContent contentProvider;
    double minWidth = 0;
    double maxWidth = 1000;
    double minHeight = 0;
    double maxHeight = 1000;
    String type = "";

    int xoffset = 0;

    public ResizableCanvas() {
        info("construct resizable");
    }

    public void setXOffset(int v) {
        xoffset = v;
    }

    public void setProvider(GanttBorderContent provider) {
        this.contentProvider = provider;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMin(double w, double h) {
        minWidth = w;
        minHeight = h;
    }

    public void setMax(double w, double h) {
        maxWidth = w;
        maxHeight = h;
    }

    @Override
    public double minHeight(double x) {
        return minHeight;
    }

    @Override
    public double maxHeight(double x) {
        return maxHeight;
    }

    @Override
    public double prefHeight(double x) {
        return minHeight(x);
    }

    @Override
    public double prefWidth(double x) {
        return minWidth(x);
    }

    @Override
    public double minWidth(double x) {
        return minWidth;
    }

    @Override
    public double maxWidth(double x) {
        return maxWidth;
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);
        draw();
    }

    public void draw() {
        double width = getWidth();
        double height = getHeight();
        contentProvider.setCurrentWidth(width);
        contentProvider.setCurrentHeight(height);
        info("draw resizable " + width + " " + height + " " + type);
        switch (type) {
            case "Top":
                contentProvider.drawTimeline();
                break;
            case "Left":
                contentProvider.drawResources();
                break;
            case "Center":
                contentProvider.drawCenter();
                break;
        }
    }

}



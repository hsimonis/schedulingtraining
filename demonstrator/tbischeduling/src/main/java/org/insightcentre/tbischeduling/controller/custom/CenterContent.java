package org.insightcentre.tbischeduling.controller.custom;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import org.insightcentre.tbischeduling.datamodel.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class CenterContent extends CanvasContent{

    public CenterContent(Canvas canvas){
        super(canvas);

    }

    @Override
    public void setSize(Scenario base){
        info("setSize");
    }


    public void draw(Scenario base) {
        info("gantt content ");

        double width = canvas.getWidth();
        double height = canvas.getHeight();
        info("Canvas " + width + " " + height);
        // clear background to white
        context.setFill(Color.PALEGOLDENROD);
        context.setStroke(Color.WHITE);
        context.fillRect(0, 0, width, height);
        context.setStroke(Color.BLACK);
    }





}

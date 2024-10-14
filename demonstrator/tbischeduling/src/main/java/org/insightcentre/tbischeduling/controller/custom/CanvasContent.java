package org.insightcentre.tbischeduling.controller.custom;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.insightcentre.tbischeduling.datamodel.Scenario;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public abstract class CanvasContent {
    Canvas canvas=null;
    GraphicsContext context = null;
    double xOffset = 20.0;
    double yOffset = 20.0;
    double xScale = 20.0;
    double yScale = 20.0;
//    double yScale = 16.0;
    Color[] colors = new Color[26];

    public CanvasContent(Canvas c){
        info("Canvas content");
        setCanvas(c);
     }

    public void setCanvas(Canvas c){
        if (c != null && this.canvas == null) {
            this.canvas = c;
            context = c.getGraphicsContext2D();
            initColors();
        } else if (this.canvas == null){
            severe("No canvas");
        }
    }

    // this is used to determine canvas size based on application requirements
    public abstract void setSize(Scenario base);

    // this actually changes the size
    public void requestSize(double width,double height){
        // limits imposed by internal restriction on canvas size
        canvas.setWidth(Math.min(2000.0,width));
        canvas.setHeight(Math.min(2000.0,height));

    }

    protected double xcoor(double x) {
        return xOffset + x * xScale;
    }

    protected double ycoor(double y) {
        return yOffset + y * yScale;
    }

    protected double xlength(double x) {
        return x * xScale;
    }

    protected double ylength(double y) {
        return y * yScale;
    }

    protected void text(double x, double y, String text) {
        context.strokeText(text, xcoor(x), ycoor(y));
    }



    Color[] initColors() {
        colors[0] = Color.rgb(240, 163, 255);
        colors[1]=Color.rgb(0, 117, 220);
        colors[2]=Color.rgb(153, 63, 0);
        colors[3]=Color.rgb(76, 0, 92);
        colors[4]=Color.rgb(25, 25, 25);
        colors[5]=Color.rgb(0, 92, 49);
        colors[6]=Color.rgb(43, 206, 72);
        colors[7]=Color.rgb(255, 204, 153);
        colors[8]=Color.rgb(128, 128, 128);
        colors[9]=Color.rgb(148, 255, 181);
        colors[10]=Color.rgb(143, 124, 0);
        colors[11]=Color.rgb(157, 204, 0);
        colors[12]=Color.rgb(194, 0, 136);
        colors[13]=Color.rgb(0, 51, 128);
        colors[14]=Color.rgb(255, 164, 5);
        colors[15]=Color.rgb(255, 168, 187);
        colors[16]=Color.rgb(66, 102, 0);
        colors[17]=Color.rgb(255, 0, 16);
        colors[18]=Color.rgb(94, 241, 242);
        colors[19]=Color.rgb(0, 153, 143);
        colors[20]=Color.rgb(224, 255, 102);
        colors[21]=Color.rgb(116, 10, 255);
        colors[22]=Color.rgb(153, 0, 0);
        colors[23]=Color.rgb(255, 255, 128);
        colors[24]=Color.rgb(255, 255, 0);
        colors[25]=Color.rgb(255, 80, 5);
        return colors;

    }


}

package org.insightcentre.tbischeduling.controller.custom;

import framework.types.TimeOnly;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.datamodel.custom.ResizableCanvas;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import static org.insightcentre.tbischeduling.datamodel.ColorBy.Mixed;
import static org.insightcentre.tbischeduling.datamodel.DatesDisplay.External;
import static org.insightcentre.tbischeduling.datamodel.DatesDisplay.Internal;
import static org.insightcentre.tbischeduling.datamodel.ResourceChoice.All;
import static org.insightcentre.tbischeduling.datamodel.ResourceZoom.Normal;
import static org.insightcentre.tbischeduling.datamodel.TaskLabel.None;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;
import static org.insightcentre.tbischeduling.utilities.TypeConverters.*;

public class GanttBorderContent {

    Scenario base;
    GanttBorderViewerController controller;
    Solution solution;
    ResizableCanvas canvas;
    ResizableCanvas topCanvas;
    ResizableCanvas leftCanvas;
    protected ScrollBar rightBar;
    protected ScrollBar bottomBar;

    double virtHeight = 10000;
    double virtWidth = 50000;
    double startX = 0;
    double startY = 0;
    double currentHeight = 800;
    double currentWidth = 2000;

    int resourceWidth=75;
    int itemHeight = 20;
    int itemGap = 5;
    int resourceLabelOffset= itemHeight -5;
    int taskLabelOffset= itemHeight -5;
    int taskLabelIndent = 2;

    double midLine = 1.0*itemHeight/2;
    double lowLine = midLine-5;
    double highLine = midLine+5;
    double nudge = 2;

    int timeUnit = 100;
    int titleHeight = 50;
    int titleLabelOffset=15;
    double maxWidth = 4000;
    double maxHeight = 3000;
    int indent = 10;

    Color[] colors = new Color[26];
    protected List<GanttRegion> regions;
    ResourceActivity selected=null;


    Hashtable<DisjunctiveResource,Integer> machineNr = new Hashtable<>();
    Hashtable<Job,Integer> jobNr = new Hashtable<>();
    Hashtable<Product,Integer> productNr = new Hashtable<>();
    Hashtable<Process,Integer> processNr = new Hashtable<>();

    ResourceChoice showMachinesBox= All;
    ResourceChoice showJobsBox= All;
    boolean highlightCritical=false;

    ResourceZoom resourceZoom = Normal;

    LineChoice showEarly=LineChoice.Number;
    LineChoice showLate=LineChoice.All;
    LineChoice showRelease=LineChoice.None;
    LineChoice showWait=LineChoice.Line;

    int xoffset;

    ColorBy colorBy;
    TaskLabel taskLabel;
    JobOrder jobOrder;

    DatesDisplay datesDisplay;

    double alpha=0.3;
    double zoom=1.0;

    Color downtimeColor = Color.INDIANRED;
    Color selectedColor = Color.RED;
    Color wipColor = Color.GRAY;
    Color machineColor = Color.POWDERBLUE;
    Color jobColor = Color.LAWNGREEN;
    Color sameJobColor = Color.YELLOW;
    Color sameMachineColor = Color.LIMEGREEN;
    Color gridColor = Color.LIGHTGRAY;

    Color alternativeColor = Color.RED;

    Color textColor = Color.BLACK;
    Color drawColor = Color.BLACK;
    Color noneColor = Color.WHITE;
    Color defaultColor = Color.WHITE;

    Color makespanColor = Color.TOMATO;
    Color horizonColor = Color.MEDIUMSEAGREEN;

    Color lateColor = Color.RED;
    Color earlyColor = Color.BLUE;
    Color releaseColor = Color.DARKGREEN;
    Color waitColor = Color.SANDYBROWN;

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setXOffset(int v) {
        xoffset = v;
    }

    public int getXOffset(){
        return xoffset;
    }

    public void setScenario(Scenario base){
        info("Scenario set");
        this.base = base;
    }

    public void setShowMachinesBox(String v){
        if (v != null) {
            showMachinesBox = toResourceChoice(v);
        }

    }
    public void setShowJobsBox(String v){
        if (v != null) {
            showJobsBox = toResourceChoice(v);
        }
    }

    public void setHighlightCritical(boolean v){
        highlightCritical = v;
    }

    public void setSolution(String sol){
        if (base != null && sol != null) {
            solution = Solution.findByName(base,sol);
        } else {
            solution = null;
        }
    }

    public void setColorBy(String v){
        if (v != null) {
            colorBy = toColorBy(v);
        }
    }
    public void setTaskLabel(String v){
        if (v != null) {
            taskLabel = toTaskLabel(v);
        }
    }
    public void setJobOrder(String v){
        if (v != null) {
            jobOrder = toJobOrder(v);
        }
    }
    public void setDatesDisplay(String v){
        if (v != null) {
            datesDisplay = toDatesDisplay(v);
        }
    }
    public void setResourceZoom(String v){
        if (v != null) {
            resourceZoom = toResourceZoom(v);
        }
    }

    public void setAlpha(double v){
        alpha = v;
    }
    public void setZoom(double v){
        zoom = v;
    }

    public GanttBorderContent(GanttBorderViewerController controller,ResizableCanvas canvas, ResizableCanvas topCanvas, ResizableCanvas leftCanvas, ScrollBar rightBar, ScrollBar bottomBar) {
        this.controller =controller;
        this.canvas = canvas;
        this.leftCanvas = leftCanvas;
        this.topCanvas = topCanvas;
        this.rightBar = rightBar;
        this.bottomBar = bottomBar;

    }

    public void initialize() {
        // mainApp may not be set, avoid data manipulation here
        info("initialize Gantt");

//        virtWidth = Math.max(virtWidth,base.getHorizon());

        canvas.setType("Center");
        canvas.setMin(0, 0);
        canvas.setMax(maxWidth, maxHeight);
        canvas.setProvider(this);

        leftCanvas.setType("Left");
        leftCanvas.setMin(resourceWidth, 0);
        leftCanvas.setMax(resourceWidth, maxHeight);
        leftCanvas.setProvider(this);

        topCanvas.setType("Top");
        topCanvas.setMin(0, titleHeight);
        topCanvas.setMax(maxWidth, titleHeight);
        topCanvas.setProvider(this);
        topCanvas.setXOffset(resourceWidth);

        rightBar.setMin(0);
        rightBar.setMax(virtHeight);
        rightBar.setValue(0);
        rightBar.setVisibleAmount(currentHeight);
        rightBar.setUnitIncrement(itemHeight +itemGap);
        rightBar.setBlockIncrement((itemHeight +itemGap)*10);

        rightBar.valueProperty().
                addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldVal, Number newVal) {
                        info("changed right " + oldVal + " to " + newVal);
                        startY = (double) newVal;
                        canvas.draw();
                        leftCanvas.draw();
                        topCanvas.draw();
                    }
                });

        bottomBar.setMin(0);
        bottomBar.setMax(virtWidth);
        bottomBar.setValue(0);
        bottomBar.setVisibleAmount(currentWidth);
        bottomBar.setUnitIncrement(timeUnit);
        bottomBar.setBlockIncrement(timeUnit*5);
        bottomBar.valueProperty().
                addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldVal, Number newVal) {
                        info("changed bottom " + oldVal + " to " + newVal);
                        startX = (double) newVal;
                        canvas.draw();
                        leftCanvas.draw();
                        topCanvas.draw();
                    }
                });


        canvas.setOnMouseClicked(event -> {
            ganttSelect(event.getX(), event.getY(), "Center");
        });
        leftCanvas.setOnMouseClicked(event -> {
            ganttSelect(event.getX(), event.getY(), "Left");
        });
        topCanvas.setOnMouseClicked(event -> {
            ganttSelect(event.getX(), event.getY(), "Top");
        });

        initColors();

        regions = new ArrayList<>();

        // must reflect changes to layout
        xoffset = resourceWidth;

        drawChart();
    }

    public void setCurrentHeight(double v) {
        currentHeight = v;
        rightBar.setMax(virtHeight - v);
        rightBar.setVisibleAmount(v);
//        info("setCurrentHeight " + v + " max " + (virtHeight - v));
    }

    public void setCurrentWidth(double v) {
        double old = bottomBar.getValue();
        currentWidth = v;
        double max = virtWidth - v;
//		bottomBar.setMax(max);
        bottomBar.setVisibleAmount(v);
        double value = Math.min(old, max);
        bottomBar.setValue(value);
//        info("setCurrentWidth " + v + " max " + max + " value " + value);
    }


    public void ganttSelect(double x, double y, String type) {
        info("clicked " + x + " " + y + " on " + type);
        ResourceActivity selected =insideRegion(x,y);
        if (selected == getSelected()){
            setSelected(null);
            controller.resetDetails();
            drawChart();
        } else {
            setSelected(selected);
           if (selected != null) {
                info("inside " + selected.getName());
               controller.updateDetails(selected);
                drawChart();
            }
        }
    }

    public ResourceActivity insideRegion(double x,double y){
        for(GanttRegion region:regions){
            if (region.isInside(x,y)){
                return region.task;
            }
        }
        return null;
    }

    public void setSelected(ResourceActivity task){
        if (selected == task) {
            selected = null;
        } else {
            selected = task;
        }
    }

    // might be null
    public ResourceActivity getSelected(){return selected;}



    public void drawChart() {
        drawTimeline();
        drawResources();
        drawCenter();
    }


    public void drawTimeline() {
        controller.updateParameters();
        if (base != null) {
            double width = topCanvas.getWidth();
            double height = topCanvas.getHeight();

            GraphicsContext gc = topCanvas.getGraphicsContext2D();
            mark(drawColor, gc, width, height);
            String imagePath = "file:images/entire.png";
            Image image = new Image(imagePath);
            // Draw the Image
            gc.drawImage(image, 0, 0, resourceWidth - 5, titleHeight);
            timeline(gc, width, height, getXOffset());
            if (solution != null) {
                showMarker(gc, width, height, "Makespan", solution.getMakespan(), makespanColor);
                showMarker(gc, width, height, "Horizon", base.getHorizon(), horizonColor);
            }
        }
    }


    public void drawResources() {
        controller.updateParameters();
        if (base != null) {
            double width = leftCanvas.getWidth();
            double height = leftCanvas.getHeight();
            considerResourceZoom(height);

            GraphicsContext gc = leftCanvas.getGraphicsContext2D();
            mark(drawColor, gc, width, height);
            if (base != null) {
//            info("with scenario ");
                machinesJobs(gc, width, height);
            } else {
//            info("without scenario");
                resources(gc, width, height);
            }
        }

    }

    public void drawCenter() {
        controller.updateParameters();
        if (base != null) {
            info("Draw center sol " + solution + " M " + showMachinesBox + " J " + showJobsBox);
            double width = canvas.getWidth();
            double height = canvas.getHeight();
            // reset regions as all tasks will have new coordinates
            regions = new ArrayList<>();
            considerResourceZoom(height);

            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setLineWidth(1.0);
            mark(drawColor, gc, width, height);

            centerTimeline(gc, width, height);
            if (base != null) {
                tasks(gc, width, height);
            }
        }

    }

    private void considerResourceZoom(double height){
        switch (resourceZoom){
            case Wide:
                itemHeight = 30;
                itemGap = 8;
                resourceLabelOffset= itemHeight -10;
                taskLabelOffset= itemHeight -10;
                midLine = 1.0*itemHeight/2;
                lowLine = midLine-7;
                highLine = midLine+7;
                break;
            case Dense:
                itemHeight = 15;
                itemGap = 2;
                resourceLabelOffset= itemHeight -3;
                taskLabelOffset= itemHeight -3;
                midLine = 1.0*itemHeight/2;
                lowLine = midLine-3;
                highLine = midLine+3;
                break;
            case FitAll:
                int nrResources = nrTotalResources();
                // each item uses 5 spaces, four for the item itself, and one for the gap
                double space = height/(nrResources*5);
                itemHeight = (int) Math.min(30.0,Math.max(5.0,Math.round(4*space)));
                info("fitAll resources"+nrResources+" space "+space+" itemHeight "+itemHeight);
                itemGap = (int) Math.round(Math.max(2,Math.min(8,space)));
                resourceLabelOffset= itemHeight -3;
                taskLabelOffset= itemHeight -3;
                midLine = 1.0*itemHeight/2;
                lowLine = midLine-1.0*itemHeight/4;
                highLine = midLine+1.0*itemHeight/4;
                break;
            case Normal:
            default:
                itemHeight = 20;
                itemGap = 5;
                resourceLabelOffset= itemHeight -5;
                taskLabelOffset= itemHeight -5;
                midLine = 1.0*itemHeight/2;
                lowLine = midLine-5;
                highLine = midLine+5;
        }
    }

    private int nrTotalResources() {
        return nrMachines() + nrJobs();
    }

    // if any machines are shown, we also need a spacer
    private int nrMachines(){
        return switch (showMachinesBox) {
            case All -> base.getListDisjunctiveResource().size() + 1;
            case Selected -> 2;
            case None -> 0;
        };
    }
    private int nrJobs(){
        return switch (showJobsBox) {
            case All -> base.getListJob().size();
            case Selected -> 1;
            case None -> 0;
        };
    }

    public void mark(Color c,GraphicsContext gc,double width,double height) {
        gc.clearRect(0,0,width,height);

//        gc.setStroke(c);
//        gc.strokeLine(0,0,width,height);
//        gc.strokeLine(0,height,width,0);
    }

    public void timeline(GraphicsContext gc,double width,double height,double xoffset){
        int increment = zoomIncrement(width,zoom);
        info("increment "+increment);
        int startX = (int) Math.round(getStartX());
        for(int i=0;i<virtWidth;i+=increment){
            double x = xoffset+xcoor(i,startX);
            gc.setStroke(textColor);
            if (datesDisplay == External && startOfDay(i)) {
                gc.setTextAlign(TextAlignment.LEFT);
                gc.strokeText(internalExternalddMM(i),x,titleLabelOffset);
            }
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText(internalExternalHHmm(i),x,2*titleLabelOffset);
            gc.setStroke(gridColor);
            gc.strokeLine(x,2*titleLabelOffset,x,height);
        }

    }
    public void centerTimeline(GraphicsContext gc,double width,double height){
        int increment = zoomIncrement(width,zoom);
        info("center increment "+increment);
        int startX = (int) Math.round(getStartX());
        for(int i=0;i<virtWidth;i+=increment){
            double x = xcoor(i,startX);
            gc.setStroke(gridColor);
            gc.strokeLine(x,0,x,height);
        }
        if (solution != null) {
            showCenterMarker(gc, width, height, "Makespan", solution.getMakespan(), makespanColor);
            showCenterMarker(gc, width, height, "Horizon", base.getHorizon(), horizonColor);
        }

    }

    private void showMarker(GraphicsContext gc,double width,double height,String label,int value,Color color){
        int startX = (int) Math.round(getStartX());
        gc.setTextAlign(TextAlignment.RIGHT);
        double x = xoffset+xcoor(value,startX);
        gc.setStroke(textColor);
        gc.strokeText(String.format("%s: %s",label,internalExternalDate(value)),x,height-itemGap);
        gc.setStroke(color);
        gc.strokeLine(x,indent,x,height);

    }
    private void showCenterMarker(GraphicsContext gc,double width,double height,String label,int value,Color color){
        int startX = (int) Math.round(getStartX());
        double x = xcoor(value,startX);
        gc.setStroke(color);
        gc.strokeLine(x,0,x,height);

    }

    public int zoomIncrement(double width,double zoom) {
        if (datesDisplay == Internal) {
            // how many internal time units will be shown in window width
            double valueRange = width / zoom;
            info("timeline " + width + " z " + zoom + " r " + valueRange);
            if (valueRange > 10000) {
                return 500;
            } else if (valueRange > 5000) {
                return 250;
            } else if (valueRange > 2000) {
                return 100;
            } else if (valueRange > 1000) {
                return 50;
            } else if (valueRange > 500) {
                return 25;
            } else if (valueRange > 200) {
                return 10;
            } else if (valueRange > 100) {
                return 5;
            } else {
                return 5;
            }
        } else {
            final int minPerDay = 1440;
            double valueRange = base.getTimeResolution()*width / zoom;
            if (valueRange >= 30*minPerDay){
                // 2 days
                return 2*minPerDay/base.getTimeResolution();
            } else if (valueRange >= 20*minPerDay){
                // day
                return minPerDay/base.getTimeResolution();
            } else if (valueRange >= 10*minPerDay){
                // 12 hour
                return minPerDay/(2*base.getTimeResolution());
            } else if (valueRange >= 5*minPerDay){
                // 6 hour
                return minPerDay/(4*base.getTimeResolution());
            } else if (valueRange >= 2*minPerDay){
                // 2 hours
                return minPerDay/(12*base.getTimeResolution());
            } else if (valueRange >= minPerDay){
                // 1 hour
                return 60/base.getTimeResolution();
            } else if (valueRange >= 1.0*minPerDay/2){
                // 30 min
                return 30/base.getTimeResolution();
            } else if (valueRange >= 1.0*minPerDay/4){
                // 15 min
                return 15/base.getTimeResolution();
            } else {
                return 60 / base.getTimeResolution();
            }

        }
    }

    public void resources(GraphicsContext gc,double width,double height){
        int startY = (int) Math.round(getStartY());
        gc.setTextAlign(TextAlignment.LEFT);
        for(int i = itemHeight -(startY % itemHeight); i<height; i+= itemHeight){
            gc.setStroke(textColor);
            gc.strokeText(String.format("M%d",(startY+i)/ itemHeight),0,i);
            gc.setStroke(gridColor);
            gc.strokeLine(indent,i,width,i);
        }

    }
    public void machinesJobs(GraphicsContext gc,double width,double height){
        productProcessNumbering();
        List<DisjunctiveResource> machines = visibleMachines();
        List<Job> jobs = visibleJobs();
        Hashtable<DisjunctiveResource,Integer> machineHash = initMachines(machines,0);
        Hashtable<Job,Integer> jobHash = initJobs(jobs,(machines.size()> 0?machines.size()+1:0));
        numbering(machines,jobs);
        int startY = (int) Math.round(getStartY());
        gc.setTextAlign(TextAlignment.LEFT);
        if (showMachinesBox != ResourceChoice.None) {
            for (DisjunctiveResource m : machines) {
                int y = ycoor(machineHash.get(m), startY);
                drawResource(gc, y, m.getName(),colorCategory(m,colorBy),alpha(m),utilization(m),textColor);
            }
        }
        if (showJobsBox != ResourceChoice.None) {
            for(Job j:jobs){
                int y=ycoor(jobHash.get(j),startY);
                drawResource(gc,y,j.getName(),colorCategory(j,colorBy),alpha(j),late(j),lateColor);
            }
        }
    }

    private List<DisjunctiveResource> visibleMachines(){
        List<DisjunctiveResource> res = new ArrayList<>();
        if (showMachinesBox == ResourceChoice.Selected){
            if (selected != null) {
                res.add(selected.getDisjunctiveResource());
            }
        } else if (showMachinesBox == ResourceChoice.All) {
            res.addAll(base.getListDisjunctiveResource());
        }
        return res;
    }

    private List<Job> visibleJobs(){
        List<Job> res = new ArrayList<>();
        if (showJobsBox == ResourceChoice.Selected) {
            if (selected != null && selected instanceof TaskAssignment task) {
                res.add(task.getJobAssignment().getJob());
            }
        } else if (showJobsBox == ResourceChoice.All) {
            res.addAll(jobOrdering(base,jobOrder,solution));
        }
        return res;

    }

    private void drawResource(GraphicsContext gc,double y,String name,Color color,double alpha,String extra,Color extraColor){
        gc.setFill(color);
        gc.setGlobalAlpha(alpha);
        gc.fillRect(0,y,resourceWidth, itemHeight);
        gc.setGlobalAlpha(1.0);
        gc.setStroke(drawColor);
        gc.strokeRect(0,y,resourceWidth, itemHeight);
        gc.setStroke(textColor);
        gc.strokeText(name, indent, y+resourceLabelOffset);
        if (extra != null) {
            gc.setStroke(extraColor);
            gc.setTextAlign(TextAlignment.RIGHT);
            gc.strokeText(extra, resourceWidth-nudge, y + resourceLabelOffset);
            gc.setTextAlign(TextAlignment.LEFT);
        }
    }

    //??? really tedious way to extract lateness value
    private String late(Job j){
        if (showLate == LineChoice.None){
            return null;
        }
        return base.getListJobAssignment().stream().
                filter(x->x.getJob()==j).
                filter(x->x.getSolution()==solution).
                filter(x->x.getLate() > 0).
                map(x->internalExternalPeriodShort(x.getLate())).
                findAny().orElse(null);
    }

    private String utilization(DisjunctiveResource m){
        return base.getListResourceUtilization().stream().
                filter(x->x.getDisjunctiveResource()==m).
                filter(x->x.getSolution()==solution).
                map(x->String.format("%3.0f%%",x.getUtilization())).
                findAny().orElse(null);

    }


    public void tasks(GraphicsContext gc,double width,double height){
        int startX = (int) Math.round(getStartX());
        int startY = (int) Math.round(getStartY());
        productProcessNumbering();
        List<DisjunctiveResource> machines = visibleMachines();
        List<Job> jobs = visibleJobs();
        Hashtable<DisjunctiveResource,Integer> machineHash = initMachines(machines,0);
        Hashtable<Job,Integer> jobHash = initJobs(jobs,(machines.size() > 0?machines.size()+1:0));
        numbering(machines,jobs);
        // draw background lines for orientation
        if (showMachinesBox != ResourceChoice.None) {
            for(DisjunctiveResource m:machines){
                int y1 = ycoor(machineHash.get(m),startY);
                gc.setStroke(gridColor);
                gc.strokeLine(0,y1,width,y1);
            }
        }
        if (showJobsBox != ResourceChoice.None) {
            for(Job j:jobs){
                int y1 = ycoor(jobHash.get(j),startY);
                gc.setStroke(gridColor);
                gc.strokeLine(0,y1,width,y1);
            }
        }
        gc.setTextAlign(TextAlignment.LEFT);
        if (showMachinesBox != ResourceChoice.None){
            for (ResourceActivity ra : resourceActivities(solution,machines)) {
                int y1 = ycoor(machineHash.get(ra.getDisjunctiveResource()), startY);
                double x1 = xcoor(ra.getStart(), startX);
                double taHeight = itemHeight;
                double taWidth = xcoor(ra.getEnd(), startX) - x1;
                drawTask(gc,x1,y1,taWidth,taHeight,label(ra,taskLabel),colorResourceActivity(ra),alpha(ra),ra);
                if (ra == selected && ra instanceof TaskAssignment task) {
                    highlightAlternativeMachines(gc, task, x1, y1, taWidth, taHeight, machineHash,startY);
                }
            }

        }

        if (showJobsBox != ResourceChoice.None) {
            for (TaskAssignment ta : taskAssignmentsJob(solution,jobs)) {
                int y1 = ycoor(jobHash.get(ta.getJobAssignment().getJob()), startY);
                double x1 = xcoor(ta.getStart(), startX);
                double x2 = xcoor(ta.getEnd(), startX);
                double taHeight = itemHeight;
                double taWidth = xcoor(ta.getEnd(), startX) - x1;
                drawTask(gc,x1,y1,taWidth,taHeight,label(ta,taskLabel),colorJobTask(ta),alpha(ta),ta);
                if (showWait != LineChoice.None) {
                    wait(gc,showWait,x1, y1, ta.getWaitBefore());
                }
                if (showEarly != LineChoice.None && ta.getTask().getPrecedes().size() == 0) {
                    early(gc,showEarly,x2, y1, ta.getJobAssignment().getEarly());
                }
                if (showLate != LineChoice.None && ta.getTask().getPrecedes().size() == 0) {
                    late(gc,showLate,x2, y1, ta.getJobAssignment().getLate());
                }
                if (showRelease != LineChoice.None && ta.getTask().getFollows().size() == 0) {
                    release(gc,showRelease,x1, y1, ta.getStart()-ta.getJobAssignment().getJob().getOrder().getRelease());
                }

            }
        }
    }



    private void drawTask(GraphicsContext gc,double x1,double y1,double width,double height,String label,Color color,double alpha,ResourceActivity ra){
        gc.setFill(color);
        gc.setGlobalAlpha(alpha);
        regions.add(new GanttRegion(x1,y1,width,height,ra));
        gc.fillRect(x1, y1, width, height);
        gc.setGlobalAlpha(1.0);
        gc.setStroke(drawColor);
        gc.strokeRect(x1, y1, width, height);
        if (taskLabel != None) {
            gc.setTextAlign(TextAlignment.LEFT);
            gc.setStroke(textColor);
            gc.strokeText(label, x1 + taskLabelIndent, y1 + taskLabelOffset);
        }
     }

    private void highlightAlternativeMachines(GraphicsContext gc,TaskAssignment ta,
                                              double x,double y,double width,double height,
                                              Hashtable<DisjunctiveResource,Integer> machineHash,int startY){
        if(showMachinesBox == All) {
            // we can only show the alternative machines if all machines are displayed
            // otherwise we are missing the entries in the hashtable to find the y position
            List<DisjunctiveResource> alternatives = ta.getTask().getMachines();
            for (DisjunctiveResource m : alternatives) {
                if (m != ta.getDisjunctiveResource()) {
                    double ya = ycoor(machineHash.get(m), startY);
                    gc.setGlobalAlpha(1.0);
                    gc.setLineWidth(4);
                    gc.setStroke(alternativeColor);
                    gc.strokeRect(x, ya, width, height);
                    gc.setLineWidth(1);
                }
            }
        }
    }

    private Color colorResourceActivity(ResourceActivity ra){
        if (ra instanceof TaskAssignment task){
            return colorCategory(task,colorBy,ColorBy.Job);
        } else if (ra instanceof WiP){
            if (selected == ra) {
                return selectedColor;
            } else {
                return wipColor;
            }
        } else if (ra instanceof Downtime){
            if (selected == ra) {
                return selectedColor;
            } else {
                return downtimeColor;
            }
        }
        return defaultColor;
    }
    private Color colorJobTask(ResourceActivity ra){
        if (ra instanceof TaskAssignment task){
            return colorCategory(task,colorBy,ColorBy.Machine);
        } else if (ra instanceof WiP){
            return wipColor;
        } else if (ra instanceof Downtime){
            return downtimeColor;
        }
        return defaultColor;
    }


    private Color colorCategory(TaskAssignment task, ColorBy colorBy, ColorBy mixed){
        if (task == selected) {
            return selectedColor;
        } else if (sameJob(task,selected)){
            return sameJobColor;
        } else if (sameMachine(task,selected)){
            return sameMachineColor;
        } else {
            ColorBy type = colorBy;
            if (colorBy == Mixed) {
                type = mixed;
            }
            switch (type) {
                case Job:
                    return colors[jobNr.get(task.getJobAssignment().getJob()) % colors.length];
                case Machine:
                    return colors[machineNr.get(task.getDisjunctiveResource()) % colors.length];
                case Stage:
                    return colors[task.getTask().getStage() % colors.length];
                case Product:
                    return colors[productNr.get(task.getJobAssignment().getJob().getOrder().getProduct()) % colors.length];
                case Process:
                    return colors[processNr.get(task.getJobAssignment().getJob().getOrder().getProcess()) % colors.length];
                case None:
                    return noneColor;
                default:
                    severe("Bad color by: " + colorBy);
                    return defaultColor;

            }
        }

    }

    private boolean sameJob(TaskAssignment task, ResourceActivity selected){
        if (selected == null || selected instanceof WiP || selected instanceof Downtime){
            return false;
        }
        return task.getJobAssignment() == ((TaskAssignment)selected).getJobAssignment();
    }

    private boolean sameJob(Job j, ResourceActivity selected){
        if (selected == null || selected instanceof WiP || selected instanceof Downtime){
            return false;
        }
        return j == ((TaskAssignment)selected).getJobAssignment().getJob();
    }


    private boolean sameMachine(TaskAssignment task,ResourceActivity selected){
        return selected != null && task.getDisjunctiveResource() == selected.getDisjunctiveResource();
    }
    private boolean sameMachine(DisjunctiveResource m,ResourceActivity selected){
        return selected != null && m == selected.getDisjunctiveResource();
    }

    private Color colorCategory(Job job, ColorBy colorBy){
        if (sameJob(job,selected)){
            return sameJobColor;
        }
        return switch (colorBy) {
            case Job, Mixed -> colors[jobNr.get(job) % colors.length];
            case Product -> colors[productNr.get(job.getOrder().getProduct()) % colors.length];
            default -> jobColor;
        };
    }
    private Color colorCategory(DisjunctiveResource m, ColorBy colorBy){
        if (sameMachine(m,selected)){
            return sameMachineColor;
        }
        return switch (colorBy) {
            case Mixed, Machine -> colors[machineNr.get(m) % colors.length];
            default -> machineColor;
        };
    }

    private double alpha(ResourceActivity t){
        if (t instanceof TaskAssignment task){
            if (task == selected) return 1.0;
            if (sameJob(task,selected)) return 1.0;
            if (sameMachine(task,selected)) return 1.0;
            return alpha;
        } else if (t instanceof WiP){
            return 1.0;
        } else if (t instanceof Downtime){
            return 1.0;
        }
        return 1.0;
    }
    private double alpha(DisjunctiveResource r){
        if (sameMachine(r,selected)) return 1.0;
        return alpha;
    }
    private double alpha(Job j){
        if (sameJob(j,selected)) return 1.0;
        return alpha;
    }

    private Hashtable<Job,Integer> initJobs(List<Job> jobs,int initial){
        Hashtable<Job,Integer> res = new Hashtable<>();
        int k=initial;
        for(Job j:jobs){
            res.put(j,k++*(itemHeight +itemGap)+itemGap);
        }
        return res;
    }
    private Hashtable<DisjunctiveResource,Integer> initMachines(List<DisjunctiveResource> machines,int initial){
        Hashtable<DisjunctiveResource,Integer> res = new Hashtable<>();
        int k=initial;
        for(DisjunctiveResource r:machines){
            res.put(r,k++*(itemHeight +itemGap)+itemGap);
        }
        return res;
    }

    private void numbering(List<DisjunctiveResource> machines,List<Job> jobs) {
        int i = 0;
        for (DisjunctiveResource r : machines) {
            machineNr.put(r, i++);
        }
        i = 0;
        for (Job j : jobs) {
            jobNr.put(j, i++);
        }
    }

    private void productProcessNumbering(){
        int i = 0;
        for (Product p : base.getListProduct()) {
            productNr.put(p, i++);
        }
        i = 0;
        for (Process p : base.getListProcess()) {
            processNr.put(p, i++);
        }
    }


    private String label(ResourceActivity ra,TaskLabel taskLabel){
        if (ra instanceof TaskAssignment task) {
            switch (taskLabel) {
                case Job:
                    return task.getJobAssignment().getJob().getName();
                case Stage:
                    return task.getTask().getStage().toString();
                case Machine:
                    return task.getDisjunctiveResource().getName();
                case Product:
                    return task.getJobAssignment().getJob().getOrder().getProduct().getName();
                case Task:
                    return task.getName();
                case Start:
                    return internalExternalHHmm(task.getStart());
                case End:
                    return internalExternalHHmm(task.getEnd());
                case Wait:
                    return internalExternalPeriod(task.getWaitBefore());
                case Duration:
                    return internalExternalPeriod(task.getTask().getDuration());
                case None:
                    // unreachable, filtered out before
                    return "";
                default:
                    severe("bad taskLabel: " + taskLabel);
                    return "";
            }
        } else {
            return switch (taskLabel) {
                case Machine -> ra.getDisjunctiveResource().getName();
                case Start -> internalExternalHHmm(ra.getStart());
                case End -> internalExternalHHmm(ra.getEnd());
                case Duration -> internalExternalPeriod(ra.getDuration());
                case None ->
                    // unreachable, filtered out before
                        "";
                default -> "";
            };
        }
    }

    private List<Job> jobOrdering(Scenario base,JobOrder jobOrder,Solution sol){

        switch (jobOrder) {
            case Input:
                return  base.getListJob();
            case Nr:
                return base.getListJob().stream().
                        sorted(Comparator.comparing(Job::getNr)).
                        collect(Collectors.toList());
            case Start:
                return base.getListJobAssignment().stream().
                        filter(x->x.getSolution() == sol).
                        sorted(Comparator.comparing(JobAssignment::getStart)).
                        map(JobAssignment::getJob).
                        collect(Collectors.toList());
            case Release:
                return base.getListJob().stream().
                        sorted(Comparator.comparing(x->x.getOrder().getRelease())).
                        collect(Collectors.toList());
            case Due:
                return base.getListJob().stream().
                        sorted(Comparator.comparing(x->x.getOrder().getDue())).
                        collect(Collectors.toList());
            case End:
                return base.getListJobAssignment().stream().
                        filter(x->x.getSolution() == sol).
                        sorted(Comparator.comparing(JobAssignment::getEnd)).
                        map(JobAssignment::getJob).
                        collect(Collectors.toList());
            case OnTime:
                return base.getListJobAssignment().stream().
                        filter(x->x.getSolution() == sol).
                        sorted(Comparator.comparing(JobAssignment::getLate).reversed().thenComparing(JobAssignment::getEarly)).
                        map(JobAssignment::getJob).
                        collect(Collectors.toList());
            case Product:
                return  base.getListJob().stream().
                        sorted(Comparator.comparing(this::jobProductNr).thenComparing(Job::getNr)).
                        collect(Collectors.toList());
            default:
                severe("Bad job order: "+jobOrder);
                assert(false);
                return base.getListJob();
        }

    }
    private int jobProductNr(Job j){
        return productNr.get(j.getOrder().getProduct());
    }

    private List<ResourceActivity> resourceActivities(Solution solution,List<DisjunctiveResource> machines){
        List<ResourceActivity> res = new ArrayList<>();
        res.addAll(taskAssignments(solution,machines));
        res.addAll(base.getListWiP().stream().filter(x->machines.contains(x.getDisjunctiveResource())).toList());
        res.addAll(base.getListDowntime().stream().filter(x->machines.contains(x.getDisjunctiveResource())).toList());
        return res;
    }

    private List<TaskAssignment> taskAssignments(Solution solution,List<DisjunctiveResource> machines){
        return base.getListTaskAssignment().stream().
                filter(x->x.getJobAssignment().getSolution()==solution).
                filter(x->machines.contains(x.getDisjunctiveResource())).
                toList();
    }
    private List<TaskAssignment> taskAssignmentsJob(Solution solution,List<Job> jobs){
        return base.getListTaskAssignment().stream().
                filter(x->x.getJobAssignment().getSolution()==solution).
                filter(x->jobs.contains(x.getJobAssignment().getJob())).
                toList();
    }


    private double xcoor(int x,int startX) {
        return x*zoom-startX;
    }

    private double xLength(int w){
        return w*zoom;
    }
    private int ycoor(int y,int startY){
        return y-startY;
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

    private void wait(GraphicsContext gc,LineChoice what,double x, double y,int wait){
        if (wait != 0) {
            gc.setStroke(waitColor);
            gc.setTextAlign(TextAlignment.CENTER);
            if (what==LineChoice.All || what==LineChoice.Number){
                gc.strokeText(internalExternalPeriod(wait),x-xLength(wait)/2,y+midLine-nudge);
            }
            if (what==LineChoice.All || what==LineChoice.Line) {
                lines(gc, x, y, -xLength(wait));
            }
        }
    }

    private void late(GraphicsContext gc,LineChoice what,double x, double y,int late){
        if (late != 0) {
            gc.setStroke(lateColor);
            gc.setTextAlign(TextAlignment.LEFT);
            if (what==LineChoice.All || what==LineChoice.Number) {
                gc.strokeText(internalExternalPeriod(late), x+nudge, y + highLine);
            }
            if (what==LineChoice.All || what==LineChoice.Line) {
                lines(gc, x, y, -xLength(late));
            }
        }
    }
    private void early(GraphicsContext gc,LineChoice what,double x, double y,int early){
        if (early != 0) {
            gc.setStroke(earlyColor);
            gc.setTextAlign(TextAlignment.LEFT);
            if (what==LineChoice.Number) {
                gc.strokeText(internalExternalPeriod(early), x+nudge, y + highLine);
            }
            if (what==LineChoice.All ) {
                gc.strokeText(internalExternalPeriod(early), x+nudge, y + midLine-nudge);
            }
            if (what==LineChoice.All || what==LineChoice.Line) {
                lines(gc, x, y, xLength(early));
            }
        }
    }
    private void release(GraphicsContext gc,LineChoice what,double x, double y,int release){
        if (release != 0) {
            gc.setStroke(releaseColor);
            gc.setTextAlign(TextAlignment.RIGHT);
            if (what==LineChoice.Number) {
                gc.strokeText(internalExternalPeriod(release), x-nudge, y + highLine);
            }
            if (what==LineChoice.All ) {
                gc.strokeText(internalExternalPeriod(release), x-nudge, y + midLine-nudge);
            }
            if (what==LineChoice.All || what==LineChoice.Line) {
                lines(gc, x, y, -xLength(release));
            }
        }
    }

    private void lines(GraphicsContext gc,double x,double y, double width){
        gc.strokeLine(x, y + midLine,x + width, y + midLine);
        gc.strokeLine(x, y + lowLine, x, y + highLine);
        gc.strokeLine(x + width, y + lowLine, x + width, y + highLine);

    }

    public String internalExternalDate(int date){
        if (datesDisplay == Internal){
            return String .format("%d",date);
        } else {
            return base.getStartDateTime().addMinutes(date*base.getTimeResolution()).toString();
        }
    }
    public String internalExternalHHmm(int date){
        if (datesDisplay == Internal){
            return String .format("%d",date);
        } else {
            return base.getStartDateTime().addMinutes(date*base.getTimeResolution()).timeOnly().toString();
        }
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE dd/MM");
    public String internalExternalddMM(int date){
        if (datesDisplay == Internal){
            return String .format("%d",date);
        } else {
            return base.getStartDateTime().addMinutes(date*base.getTimeResolution()).asLocalDate().format(formatter);
        }
    }

    public String internalExternalPeriod(int period){
        if (datesDisplay == Internal){
            return String.format("%d",period);
        } else {
            int inMinutes  = period*base.getTimeResolution();
            int min = inMinutes % 60;
            int h = inMinutes/60;
            int hours = h % 24;
            int days = h/24;
            if (days == 0 && hours == 0){
                return String.format("%dm",min);
            } else if (days ==0){
                return String.format("%dh%dm",hours,min);
            } else {
                //??? should we use %d or %02d for hours and minutes
                if (min==0){
                    //??? special case for zero minutes
                    return String.format("%dd%dh", days, hours);

                } else {
                    return String.format("%dd%dh%dm", days, hours, min);
                }
            }
        }
    }

    // either xxm,xxh,xxd
    public String internalExternalPeriodShort(int period){
        if (datesDisplay == Internal){
            return String.format("%d",period);
        } else {
            int inMinutes  = period*base.getTimeResolution();
            int min = inMinutes % 60;
            int h = inMinutes/60;
            int hours = h % 24;
            int days = h/24;
            if (days == 0 && hours == 0){
                return String.format("%dm",min);
            } else if (days ==0){
                return String.format("%dh",hours);
            } else {
                return String.format("%dd", days);
            }
        }
    }

    private boolean startOfDay(int i){
        TimeOnly time = base.getStartDateTime().addMinutes(i*base.getTimeResolution()).timeOnly();
        return time.get24Hour() == 0 && time.getMinute() == 0;
    }


}

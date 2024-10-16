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

public class GanttContent extends CanvasContent{
    protected List<GanttRegion> regions;
    ResourceActivity selected=null;
    Hashtable<DisjunctiveResource,Integer> machineNr = new Hashtable<>();
    Hashtable<Job,Integer> jobNr = new Hashtable<>();
    Hashtable<Product,Integer> productNr = new Hashtable<>();

    boolean variant;

    public GanttContent(Canvas canvas,boolean variant){
        super(canvas);
        this.variant = variant;

    }

    @Override
    public void setSize(Scenario base){
    }

    public void setSizeTop(Scenario base,Solution sol){
        // magic limit of 2000px to avoid internal limit of Canvas
        double width = Math.min(2000.0,xcoor(sol.getMakespan()));
        double height = ycoor(base.getListDisjunctiveResource().size());
        requestSize(width,height);
        xScale = (width-xOffset)/sol.getMakespan();
        info("setSizeTop "+width+" "+height+" "+xScale+" "+xOffset);
    }
    public void setSizeBottom(Scenario base,Solution sol){
        // magic limit of 2000px to avoid internal limit of Canvas
        double width = Math.min(2000.0,xcoor(sol.getMakespan()));
        double height = ycoor(base.getListJob().size()+2);
        requestSize(width,height);
        xScale = (width-xOffset)/sol.getMakespan();
        info("setSizeBottom "+width+" "+height+" "+xScale+" "+xOffset);
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


    public void draw(Scenario base, Solution sol,boolean showMachines,boolean showJobs,
                     boolean showEarly,boolean showLate,boolean showWait,boolean showRelease,
                     boolean highlightCritical,
                     String colorBy,String taskLabel,String jobOrdering,double alpha){
        info("gantt content "+showMachines+" "+showJobs+" "+showEarly+" "+showLate+" "+showWait+" "+showRelease+" "+highlightCritical+
                " Color by "+colorBy+
                " Task Label "+taskLabel+
                " Job Ordering "+jobOrdering+
                " Sol "+sol.getName());
        assert(sol != null);
        regions = new ArrayList<>();

        double width = canvas.getWidth();
        double height = canvas.getHeight();
        info("Canvas " + width + " " + height);
        // clear background to white
        context.setFill(Color.WHITE);
        context.setStroke(Color.WHITE);
        context.fillRect(0,0,width,height);
        context.setStroke(Color.BLACK);



        List<TaskAssignment> tasks = base.getListTaskAssignment().stream().
                filter(x -> x.getJobAssignment().getSolution() == sol).toList();
        List<DisjunctiveResource> machines = base.getListDisjunctiveResource().stream().
                sorted(Comparator.comparing(DisjunctiveResource::getName)).toList();
        List<Job> jobs = jobOrdering(base,jobOrdering,sol);

        int i=0;
        for(DisjunctiveResource r:machines){
            machineNr.put(r,i++);
        }
        i = 0;
        for(Job j:jobs){
            jobNr.put(j,i++);
        }
        i =0;
        for(Product p:base.getListProduct()){
            productNr.put(p,i++);
        }

        if(variant) {
            int yOffset = 0;

                 for (TaskAssignment task : tasks) {
                    task(colorCategory(task, colorBy, "Job"),
                            task.getStart(), machineNr.get(task.getDisjunctiveResource()), task.getTask().getDuration(), 0.8,
                            task, taskLabel, alpha, highlightCritical);
                }
                for (DisjunctiveResource m : machines) {
                    ylabel(machineNr.get(m), m.getName(), selected != null && selected.getDisjunctiveResource() == m);
                }

        } else {

//            xaxis(0, sol.getMakespan(), 100, 50, yOffset - 1);

            int yOffset = 0;

            for (Job j : jobs) {
                    ylabel(yOffset + jobNr.get(j), j.getName(), sameJob(j,selected));
//                if (showJobTypes){
//                    stage(colors[jobNr.get(j) % 26],0,yOffset+jobOrder[j.getNr()], sol.getMakespan(),1,j.getName(),0.2);
//                }
                }
                for (TaskAssignment task : tasks) {
                    int y = yOffset + jobNr.get(task.getJobAssignment().getJob());
                    task(colorCategory(task, colorBy, "Machine"),
                            task.getStart(), y, task.getTask().getDuration(), 0.8,
                            task, taskLabel, alpha, highlightCritical);
//                if (showTransport){
//                    travel( task.getStart(), y, -task.getTravel());
//                }
                    if (showWait) {
                        wait(task.getStart(), y, -task.getWaitBefore());
                    }
                    if (showEarly && task.getTask().getPrecedes().size() == 0) {
                        early(task.getEnd(), y, task.getJobAssignment().getEarly());
                    }
                    if (showLate && task.getTask().getPrecedes().size() == 0) {
                        late(task.getEnd(), y, -task.getJobAssignment().getLate());
                    }
                    if (showRelease && task.getTask().getFollows().size() == 0) {
                        release(task.getStart(), y, task.getJobAssignment().getJob().getOrder().getRelease() - task.getStart());
                    }
                }


        }

     }

     private List<Job> jobOrdering(Scenario base,String jobOrdering,Solution sol){

         switch (jobOrdering) {
             case "Input":
                 return  base.getListJob();
             case "Nr":
                 return base.getListJob().stream().
                         sorted(Comparator.comparing(Job::getNr)).
                         collect(Collectors.toList());
             case "Start":
                 return base.getListJobAssignment().stream().
                         filter(x->x.getSolution() == sol).
                         sorted(Comparator.comparing(JobAssignment::getStart)).
                         map(JobAssignment::getJob).
                         collect(Collectors.toList());
             case "Release":
                 return base.getListJob().stream().
                         sorted(Comparator.comparing(x->x.getOrder().getRelease())).
                         collect(Collectors.toList());
             case "Due":
                 return base.getListJob().stream().
                         sorted(Comparator.comparing(x->x.getOrder().getDue())).
                         collect(Collectors.toList());
             case "End":
                 return base.getListJobAssignment().stream().
                         filter(x->x.getSolution() == sol).
                         sorted(Comparator.comparing(JobAssignment::getEnd)).
                         map(JobAssignment::getJob).
                         collect(Collectors.toList());
             case "Product":
                 return  base.getListJob().stream().
                         sorted(Comparator.comparing(this::jobProductNr).thenComparing(Job::getNr)).
                         collect(Collectors.toList());
             default:
                 severe("Bad job ordering: "+jobOrdering);
                 return base.getListJob();
         }

     }

     private int jobProductNr(Job j){
        return productNr.get(j.getOrder().getProduct());
     }

    private Color colorCategory(TaskAssignment task, String colorBy, String mixed){
        String type = colorBy;
        if (colorBy.equals("Mixed")){
            type = mixed;
        }
//        return Color.WHITE;
        switch (type){
            case "Job":
                return colors[jobNr.get(task.getJobAssignment().getJob()) % 26];
            case "Machine":
                return colors[machineNr.get(task.getDisjunctiveResource()) % 26];
            case "Stage":
                return  colors[task.getTask().getStage() % 26];
            case "Product":
                return colors[productNr.get(task.getJobAssignment().getJob().getOrder().getProduct()) % 26];
            case "None":
                return Color.WHITE;
            default:
                severe("Bad color by: "+colorBy);
                return Color.WHITE;

        }

    }


    private void xaxis(int min,int max,int major,int minor,int y){
        context.setStroke(Color.BLACK);
        context.setTextAlign(TextAlignment.CENTER);
        context.strokeLine(xcoor(min),ycoor(y),xcoor(max),ycoor(y));
        for(int i=min;i<=max;i+=major){
            context.strokeLine(xcoor(i),ycoor(y+0.4),xcoor(i),ycoor(y-0.4));
            text(i,y+0.8,String.format("%d",i));
        }
        for(int i=min;i<=max;i+=minor){
            context.strokeLine(xcoor(i),ycoor(y+0.2),xcoor(i),ycoor(y-0.2));
        }
        context.setStroke(Color.RED);
        context.setTextAlign(TextAlignment.RIGHT);
        text(max,y-0.2,String.format("Cmax: %d",max));
        context.setTextAlign(TextAlignment.LEFT);
    }


    private void stage(Color color,int x,int y, int width,double height,String title,double opacity) {
        context.setFill(color);
        context.setGlobalAlpha(opacity);
        context.fillRect(xcoor(x), ycoor(y), xlength(width), ylength(height-0.1));
        context.setGlobalAlpha(1.0);
    }

    private void task(Color color, int x, int y, int width, double height,TaskAssignment task,
                      String taskLabel,double alpha,boolean highlightCritical) {
        context.setFill(selectionColor(task,color));
        context.setGlobalAlpha(fullAlpha(highlightCritical,task,alpha));
        context.fillRect(xcoor(x), ycoor(y), xlength(width), ylength(height));
        context.setGlobalAlpha(1.0);
        regions.add(new GanttRegion(xcoor(x), ycoor(y), xlength(width), ylength(height),task));
        context.setStroke(Color.BLACK);
//        if (highlightCritical && task.getCritical()){
//            context.setStroke(Color.RED);
//        } else {
//            context.setStroke(Color.BLACK);
//        }
        context.strokeRect(xcoor(x), ycoor(y), xlength(width), ylength(height));
        if (!taskLabel.equals("None")){
            text(x+0.2, y+0.6,label(task,taskLabel));
        }
    }

    private double fullAlpha(boolean highlightCritical,TaskAssignment task,double alpha){
//        if (highlightCritical && task.getIsCritical()) return 1.0;
        if (task == selected) return 1.0;
        if (sameJob(task,selected)) return 1.0;
        if (sameMachine(task,selected)) return 1.0;
        return alpha;
    }
    private Color selectionColor(TaskAssignment task,Color color){
        if (task == selected) {
            return Color.RED;
        } else if (sameJob(task,selected)){
            return Color.YELLOW;
        } else if (sameMachine(task,selected)){
            return Color.GREEN;
        } else {
            return color;
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

    private String label(TaskAssignment task,String taskLabel){
        switch(taskLabel){
            case "Job":
                return task.getJobAssignment().getJob().getName();
            case "Stage":
                return task.getTask().getStage().toString();
            case "Machine":
                return task.getDisjunctiveResource().getName();
            case "Product":
                return task.getJobAssignment().getJob().getOrder().getProduct().getName();
            case "Task":
                return task.getName();
            case "Start":
                return task.getStart().toString();
            case "End":
                return task.getEnd().toString();
            case "Wait":
                return task.getWaitBefore().toString();
            case "Duration":
                return task.getTask().getDuration().toString();
            case "None":
                // unreachable, filtered out before
                return "";
            default:
                severe("bad taskLabel: "+taskLabel);
                return "";
        }
    }

     private void wait(int x, int y,int width){
        if (width != 0) {
            context.setStroke(Color.CYAN);
            context.strokeLine(xcoor(x), ycoor(y + 0.4), xcoor(x + width), ycoor(y + 0.4));
            context.strokeLine(xcoor(x), ycoor(y + 0.2), xcoor(x), ycoor(y + 0.6));
            context.strokeLine(xcoor(x + width), ycoor(y + 0.2), xcoor(x + width), ycoor(y + 0.6));
        }
    }
    private void late(int x, int y,int width){
        if (width != 0) {
            context.setStroke(Color.RED);
            context.strokeLine(xcoor(x), ycoor(y + 0.4), xcoor(x + width), ycoor(y + 0.4));
            context.strokeLine(xcoor(x), ycoor(y + 0.2), xcoor(x), ycoor(y + 0.6));
            context.strokeLine(xcoor(x + width), ycoor(y + 0.2), xcoor(x + width), ycoor(y + 0.6));
        }
    }
    private void early(int x, int y,int width){
        if (width != 0) {
            context.setStroke(Color.BLUE);
            context.strokeLine(xcoor(x), ycoor(y + 0.4), xcoor(x + width), ycoor(y + 0.4));
            context.strokeLine(xcoor(x), ycoor(y + 0.2), xcoor(x), ycoor(y + 0.6));
            context.strokeLine(xcoor(x + width), ycoor(y + 0.2), xcoor(x + width), ycoor(y + 0.6));
        }
    }
    private void release(int x, int y,int width){
        if (width != 0) {
            context.setStroke(Color.GREEN);
            context.strokeLine(xcoor(x), ycoor(y + 0.4), xcoor(x + width), ycoor(y + 0.4));
            context.strokeLine(xcoor(x), ycoor(y + 0.2), xcoor(x), ycoor(y + 0.6));
            context.strokeLine(xcoor(x + width), ycoor(y + 0.2), xcoor(x + width), ycoor(y + 0.6));
        }
    }


    private void ylabel(int y, String label,boolean selected) {
        context.setStroke(selected?Color.RED:Color.BLACK);
        context.setTextAlign(TextAlignment.RIGHT);
        text(-0.5, y + 0.6, label);
        context.setTextAlign(TextAlignment.LEFT);

    }


}

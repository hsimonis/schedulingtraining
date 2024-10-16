package org.insightcentre.tbischeduling.controller.custom;

import org.insightcentre.tbischeduling.datamodel.ResourceActivity;
import org.insightcentre.tbischeduling.datamodel.TaskAssignment;

public class GanttRegion {
    double x;
    double y;
    double w;
    double h;
    ResourceActivity task;

    public GanttRegion(double x, double y, double w, double h, ResourceActivity task){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.task = task;
    }

    public boolean isInside(double x1,double y1){
        return x1>= x && y1 >= y && x1 <= x+w && y1 <= y+h;
    }
}

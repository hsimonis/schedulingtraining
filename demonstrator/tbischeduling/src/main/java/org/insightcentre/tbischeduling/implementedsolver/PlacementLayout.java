package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;

public class PlacementLayout {
    public PlacementLayout(Scenario base, Solution sol){
        List<TaskAssignment> tasks = base.getListTaskAssignment().stream().
                filter(x->x.getJobAssignment().getSolution()==sol).
//                sorted(Comparator.comparing(this::decreasingSurface)).
//                sorted(Comparator.comparing(TaskAssignment::getStart).thenComparing(this::decreasingEnd)).
                toList();
        for(CumulativeResource c:base.getListCumulativeResource()){
            Hashtable<ProcessStep,Integer> hash = new Hashtable<>();
            for(CumulativeNeed cn:base.getListCumulativeNeed().stream().
                    filter(x->x.getCumulativeResource()==c).
                    toList()){
                hash.put(cn.getProcessStep(),cn.getDemand());
            }
            List<PlacedRectangle> list = new ArrayList<>();
            for(TaskAssignment ta:tasks){
                Integer h = hash.get(ta.getTask().getProcessStep());
                if (h != null && h > 0){
                    PlacedRectangle rect = new PlacedRectangle(base);
                    rect.setName(ta.getTask().getName());
                    rect.setCumulativeResource(c);
                    rect.setTaskAssignment(ta);
                    rect.setX(ta.getStart());
                    rect.setY(0);
                    rect.setW(ta.getDuration());
                    rect.setH(h);
                    list.add(rect);
                }
            }
            info("Cumulative resource "+c+" rects "+list.size());
            layout(list);
        }
    }

    private int decreasingSurface(PlacedRectangle r){
        return -r.getW()*r.getH();
    }
    private int decreasingEnd(PlacedRectangle r){
        return -(r.getX()+r.getW());
    }

    private void layout(List<PlacedRectangle> list){
        List<PlacedRectangle> res = new ArrayList<>();
        int i=0;
//        for(PlacedRectangle rect:list.stream().sorted(Comparator.comparing(this::decreasingSurface)).toList()){
        for(PlacedRectangle rect:list.stream().sorted(Comparator.comparing(PlacedRectangle::getX).thenComparing(this::decreasingEnd)).toList()){
                int y = findPlace(rect,res);
                rect.setName("R"+i++);
                rect.setY(y);
                res.add(rect);
        }
    }

    private int findPlace(PlacedRectangle rect,List<PlacedRectangle> res){
        int y = 0;
        List<PlacedRectangle> overlapping = res.stream().
                filter(x->x.getX()+x.getW()>rect.getX() && x.getX() < rect.getX()+rect.getW()).
                sorted(Comparator.comparing(PlacedRectangle::getY)).
                toList();
        for(PlacedRectangle a:overlapping){
            if (a.getY()< y+rect.getH()){
                y = Math.max(y,a.getY()+a.getH());
            }
        }
        return y;
    }
}

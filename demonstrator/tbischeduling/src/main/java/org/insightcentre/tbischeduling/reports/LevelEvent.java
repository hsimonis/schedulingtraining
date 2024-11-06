package org.insightcentre.tbischeduling.reports;

public class LevelEvent {
    int x;
    double v;

    public LevelEvent(int x,double v){
        this.x = x;
        this.v = v;
    }

    public int getX(){
        return x;
    }
    public double getV(){
        return v;
    }
}

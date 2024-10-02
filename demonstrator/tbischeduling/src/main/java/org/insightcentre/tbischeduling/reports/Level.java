package org.insightcentre.tbischeduling.reports;

public class Level {
    int start;
    int end;
    int value;
    public Level(int start,int end,int value){
        this.start = start;
        this.end =end;
        this.value = value;
    }

    public void setEnd(int end){
        this.end = end;
    }

    public int getStart(){
        return start;
    }
    public int getEnd(){
        return end;
    }
    public int getValue(){
        return value;
    }
}

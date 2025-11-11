package org.insightcentre.tbischeduling.utilities;

public class Group {
    String name;
    int key1;
    int key2;
    int key3;
    int key4;

    public Group(String name,int key1){
        this.name = name;
        this.key1 = key1;
        this.key2=0;
        this.key3=0;
        this.key4=0;
    }
    public Group(String name,int key1,int key2){
        this.name = name;
        this.key1 = key1;
        this.key2=key2;
        this.key3=0;
        this.key4=0;
    }
    public Group(String name,int key1,int key2,int key3){
        this.name = name;
        this.key1 = key1;
        this.key2=key2;
        this.key3=key3;
        this.key4=0;
    }
    public Group(String name,int key1,int key2,int key3,int key4){
        this.name = name;
        this.key1 = key1;
        this.key2=key2;
        this.key3=key3;
        this.key4=key4;
    }

    public boolean equals(Group b){
        return name.equals(b.getName());
    }


    public String getName(){return name;}
    public int getKey1(){return key1;}
    public int getKey2(){return key2;}
    public int getKey3(){return key3;}
    public int getKey4(){return key4;}
}

package org.insightcentre.tbischeduling.reports;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Color {
    String hue;
    String label;
    String description;
    String section;

    private static List<Color> colors = new ArrayList<>();
    public Color(String hue,String label){
        this.hue = hue;
        this.label = label;
        this.description="";
        this.section="any";
        colors.add(this);
    }
    public Color(String hue,String label,String description,String section){
        this.hue = hue;
        this.label = label;
        this.description = description;
        this.section = section;
        colors.add(this);
    }

    @Override
    public String toString(){
        return hue;
    }

    public String getHue() {
        return hue;
    }
    public String getLabel() {
        return label;
    }
    public String getDescription() {
        return description;
    }

    public String getSection(){
        return section;
    }


    public static List<Color> getColors(String section){
        if (section==null) {
            return new ArrayList<>(colors);
        } else {
            return colors.stream().filter(x->x.section.equals(section)).collect(Collectors.toUnmodifiableList());
        }
    }
}

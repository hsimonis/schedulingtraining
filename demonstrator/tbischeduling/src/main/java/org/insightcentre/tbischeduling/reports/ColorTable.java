package org.insightcentre.tbischeduling.reports;

import framework.reports.visualization.tabular.table.TableDraw;

import java.io.PrintWriter;
import java.util.List;

import static framework.reports.AbstractCommon.safe;

public class ColorTable {
    String section;

    public ColorTable(){
        this.section=null;
    }
    public ColorTable(String section){
        this.section = section;
    }

    public void draw(PrintWriter tex){
        List<Color> colors = Color.getColors(section);
        String caption = (section==null?"Overall Color Scheme":"Color Scheme for Section "+safe(section));
        new TableDraw<>(caption, colors).
                addStringColumn("Color", this::drawColor).
                addStringColumn("Section", Color::getSection).
                generate().latex(tex);
    }

    private String drawColor(Color x){
        return "\\rowcolor{"+x.getHue()+"}"+x.getDescription();
    }
}

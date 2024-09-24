package org.insightcentre.tbischeduling.reports;

import framework.reports.AbstractBaseReport;
import org.insightcentre.tbischeduling.datamodel.Scenario;

public abstract class AbstractReport extends AbstractBaseReport {
    Scenario base;

    public AbstractReport(Scenario base, String root) {
        super(root);
        this.base = base;
    }

    protected String st(String a,String b){
        return String.format("\\shortstack{%s\\\\%s}",a,b);
    }
    protected String st(String a,String b,String c){
        return String.format("\\shortstack{%s\\\\%s\\\\%s}",a,b,c);
    }
    protected String st(String a,String b,String c,String d){
        return String.format("\\shortstack{%s\\\\%s\\\\%s\\\\%s}",a,b,c,d);
    }


    public static String time(long start){
        return String.format("%5.2f s",(System.currentTimeMillis()-start)/1000.0);
    }

    public static double time(long from,long to){
        return (to-from)/1000.0;
    }

}

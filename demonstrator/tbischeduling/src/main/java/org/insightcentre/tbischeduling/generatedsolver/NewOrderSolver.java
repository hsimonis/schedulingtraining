package org.insightcentre.tbischeduling.generatedsolver;

import org.insightcentre.tbischeduling.datamodel.*;
import framework.solver.AbstractSolver;
import framework.types.DateOnly;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;
import static org.insightcentre.tbischeduling.logging.LogShortcut.warning;

public class NewOrderSolver extends DefaultSolver{
// solver internal variables
    protected long startSystem = 0;
// solver parameters
    protected String product="Prod1";
    protected int qty=5;
    protected int release=0;
    protected int due=200;
    protected double latenessWeight=1.0;
    protected double earlinessWeight=1.0;

    public NewOrderSolver(Scenario base){
        super(base,new String[] {});
    }
    public NewOrderSolver(Scenario base,String product,int qty,int release,int due,double latenessWeight,double earlinessWeight){
        super(base,new String[] {});
        this.product=product;
        this.qty=qty;
        this.release=release;
        this.due=due;
        this.latenessWeight=latenessWeight;
        this.earlinessWeight=earlinessWeight;
    }

public String getProduct(){
 return product;
}

public int getQty(){
 return qty;
}

public int getRelease(){
 return release;
}

public int getDue(){
 return due;
}

public double getLatenessWeight(){
 return latenessWeight;
}

public double getEarlinessWeight(){
 return earlinessWeight;
}

public NewOrderSolver setProduct(String v){
 product = v;
 return this;
}

public NewOrderSolver setQty(int v){
 qty = v;
 return this;
}

public NewOrderSolver setRelease(int v){
 release = v;
 return this;
}

public NewOrderSolver setDue(int v){
 due = v;
 return this;
}

public NewOrderSolver setLatenessWeight(double v){
 latenessWeight = v;
 return this;
}

public NewOrderSolver setEarlinessWeight(double v){
 earlinessWeight = v;
 return this;
}

public void stop() {
}
public boolean solve() {
    boolean isfeas = true;
    startSystem = System.currentTimeMillis();
// reset objects
// create indices
    long endSystem = System.currentTimeMillis();
    info("Run Time: "+(endSystem - startSystem) / 1000.0);
// remove indices
    return isfeas;
}


}

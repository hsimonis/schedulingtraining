package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.Order;
import org.insightcentre.tbischeduling.datamodel.Product;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.generatedsolver.NewOrderSolver;
import org.insightcentre.tbischeduling.importer.CreateData;

public class NewOrderSolverImpl extends NewOrderSolver {
    public NewOrderSolverImpl(Scenario base){
        super(base);
    }

    public boolean solve(){
        int i = base.getListOrder().stream().mapToInt(Order::getNr).max().orElse(0)+1;
        Order ord = new Order(base);
        ord.setName("Order"+i);
        ord.setNr(i);
        Product product = Product.findByName(base,getProduct());
        if (product==null){
            Product.findFirst(base);
        }
        assert(product != null);
        ord.setProduct(product);
        ord.setProcess(product.getDefaultProcess()); // derived, not user modifiable at the moment
        ord.setQty(getQty());
        ord.setRelease(getRelease());
        ord.setDue(getDue());
        ord.setEarlinessWeight(getEarlinessWeight());
        ord.setLatenessWeight(getLatenessWeight());

        new CreateData(base,ord,i);
        return true;
    }
}

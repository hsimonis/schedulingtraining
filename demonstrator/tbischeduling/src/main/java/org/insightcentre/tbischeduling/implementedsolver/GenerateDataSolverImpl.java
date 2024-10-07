package org.insightcentre.tbischeduling.implementedsolver;

import framework.types.DateOnly;
import framework.types.DateTime;
import framework.types.TimeOnly;
import org.insightcentre.tbischeduling.datamodel.DataGeneratorProperty;
import org.insightcentre.tbischeduling.datamodel.DurationModel;
import org.insightcentre.tbischeduling.datamodel.ResourceModel;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.generatedsolver.GenerateDataSolver;
import org.insightcentre.tbischeduling.importer.CreateData;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;
import static org.insightcentre.tbischeduling.utilities.TypeConverters.toDurationModel;
import static org.insightcentre.tbischeduling.utilities.TypeConverters.toResourceModel;

public class GenerateDataSolverImpl extends GenerateDataSolver {
    public GenerateDataSolverImpl(Scenario base){
        // create the solver object with default values from the DataGeneratorProperty object
        super(base,base.getDataGeneratorProperty().getLabel(),
                base.getDataGeneratorProperty().getStartDateTime().asDateOnly().toString(),
                base.getDataGeneratorProperty().getStartDateTime().asTimeOnly().toString(),
                base.getDataGeneratorProperty().getResourceModel().toString(),
                base.getDataGeneratorProperty().getNrProducts(),
                base.getDataGeneratorProperty().getMinStages(),base.getDataGeneratorProperty().getMaxStages(),
                base.getDataGeneratorProperty().getNrDisjunctiveResources(),base.getDataGeneratorProperty().getResourceProbability(),
                base.getDataGeneratorProperty().getDurationModel().toString(),
                base.getDataGeneratorProperty().getMinDuration(),base.getDataGeneratorProperty().getMaxDuration(),
                base.getDataGeneratorProperty().getDurationFixedFactor(),
                base.getDataGeneratorProperty().getNrCumulativeResources(),
                base.getDataGeneratorProperty().getMinCumulDemand(),base.getDataGeneratorProperty().getMaxCumulDemand(),
                base.getDataGeneratorProperty().getProfilePieces(),
                base.getDataGeneratorProperty().getMinCumulCapacity(),base.getDataGeneratorProperty().getMaxCumulCapacity(),
                base.getDataGeneratorProperty().getNrOrders(),
                base.getDataGeneratorProperty().getMinQty(),base.getDataGeneratorProperty().getMaxQty(),
                base.getDataGeneratorProperty().getWipProbability(),
                base.getDataGeneratorProperty().getMinWip(),base.getDataGeneratorProperty().getMaxWip(),
                base.getDataGeneratorProperty().getDowntimeProbability(),
                base.getDataGeneratorProperty().getMinDowntime(),base.getDataGeneratorProperty().getMaxDowntime(),
                base.getDataGeneratorProperty().getEarliestDue(),
                base.getDataGeneratorProperty().getHorizonDays(),base.getDataGeneratorProperty().getTimeResolution(),
                base.getDataGeneratorProperty().getSeed());
    }

    public boolean solve(){
        info("Generating dataset based on parameters");
        // save the selected parameters back into the DataGeneratorProperty for later reuse
        DataGeneratorProperty q = base.getDataGeneratorProperty();
        q.setLabel(getLabel());
        DateOnly dateOnly = DateOnly.parseDateOnly(getStartDate(),"dd-MM-yyyy");
        TimeOnly timeOnly = TimeOnly.parseTimeOnlyWithoutException(getStartTime());
        DateTime dateTime = dateOnly.asDateTime().setTime(timeOnly);
        q.setStartDateTime(dateTime);
        q.setResourceModel(toResourceModel(getResourceModel()));
        q.setNrProducts(getNrProducts());
        q.setMinStages(getMinStages());q.setMaxStages(getMaxStages());
        q.setNrDisjunctiveResources(getNrDisjunctiveResources());
        q.setResourceProbability(getResourceProbability());
        q.setDurationModel(toDurationModel(getDurationModel()));
        q.setMinDuration(getMinDuration());q.setMaxDuration(getMaxDuration());
        q.setDurationFixedFactor(getDurationFixedFactor());
        q.setNrCumulativeResources(getNrDisjunctiveResources());
        q.setMinCumulDemand(getMinCumulDemand());q.setMaxCumulDemand(getMaxCumulDemand());
        q.setProfilePieces(getProfilePieces());
        q.setMinCumulCapacity(getMinCumulCapacity());q.setMaxCumulCapacity(getMaxCumulCapacity());
        q.setNrOrders(getNrOrders());q.setMinQty(getMinQty());q.setMaxQty(getMaxQty());
        q.setWipProbability(getWipProbability());
        q.setMinWip(getMinWip());q.setMaxWip(getMaxWip());
        q.setDowntimeProbability(getDowntimeProbability());
        q.setMinDowntime(getMinDowntime());q.setMaxDowntime(getMaxDowntime());
        q.setEarliestDue(getEarliestDue());
        q.setHorizonDays(getHorizonDays());q.setTimeResolution(getTimeResolution());
        q.setSeed(getSeed());
        // create the scenario data based on the selected parameters
        new CreateData(base,getLabel(),
                dateTime,
                toResourceModel(getResourceModel()),getNrProducts(),
                getMinStages(),getMaxStages(),
                getNrDisjunctiveResources(),getResourceProbability(),
                toDurationModel(getDurationModel()),getMinDuration(),getMaxDuration(),getDurationFixedFactor(),
                getNrCumulativeResources(),getMinCumulDemand(),getMaxCumulDemand(),
                getProfilePieces(),getMinCumulCapacity(),getMaxCumulCapacity(),
                getNrOrders(),getMinQty(),getMaxQty(),
                getWipProbability(),getMinWip(),getMaxWip(),
                getDowntimeProbability(),getMinDowntime(),getMaxDowntime(),
                getEarliestDue(),getHorizonDays(),getTimeResolution(),getSeed());
        return true;
    }

}

package org.insightcentre.tbischeduling.importer;

import org.insightcentre.tbischeduling.datamodel.Scenario;

/*
utility functions to clear out data at different levels
 */
public class Reset {
    /*
    remove all existing data, restore initial state of data store
     */
    public static void resetAll(Scenario base){
        resetSolution(base);
        resetSchedule(base);
        resetData(base);
    }

    /*
    only remove solution data, required when changing other data to maintain consistency
     */
    public static void resetSolution(Scenario base){
        base.resetListTaskAssignment();
        base.resetListJobAssignment();
        base.resetListSolution();
        base.resetListSolverRun();
        base.resetListResourceUtilization();
        base.resetListIntermediateSolution();
    }


    /*
    remove orders and resulting jobs and tasks; required when modifying base data or order sets
     */
    public static void resetSchedule(Scenario base){
        resetSolution(base);
        base.resetListWiP();
        base.resetListDowntime();
        base.resetListTask();
        base.resetListJob();
        base.resetListOrder();
    }

    /*
    remove data defining the products and resource structure
     */
    public static void resetData(Scenario base){
        resetSchedule(base);
        base.resetListInputError();
        base.resetListSolutionError();
        base.resetListProblem();
        base.resetListResourceNeed();
        base.resetListCumulativeNeed();
        base.resetListCumulativeProfile();
        base.resetListProcessSequence();
        base.resetListProcessStep();
        base.resetListProcess();
        base.resetListProduct();
        base.resetListDisjunctiveResource();
        base.resetListCumulativeResource();
    }
}

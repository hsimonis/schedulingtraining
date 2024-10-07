// licence details to be added
package org.insightcentre.tbischeduling.datamodel;
import org.insightcentre.tbischeduling.datamodel.ApplicationDataset;
import org.insightcentre.tbischeduling.datamodel.ApplicationObject;
import org.insightcentre.tbischeduling.datamodel.ApplicationDifference;
import org.insightcentre.tbischeduling.datamodel.ApplicationWarning;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.AbstractSolverProperty;
import org.insightcentre.tbischeduling.datamodel.SolverProperty;
import org.insightcentre.tbischeduling.datamodel.SolverRun;
import org.insightcentre.tbischeduling.datamodel.AbstractDataGeneratorProperty;
import org.insightcentre.tbischeduling.datamodel.DataGeneratorProperty;
import org.insightcentre.tbischeduling.datamodel.DataGeneratorRun;
import org.insightcentre.tbischeduling.datamodel.InputError;
import org.insightcentre.tbischeduling.datamodel.Problem;
import org.insightcentre.tbischeduling.datamodel.Product;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.datamodel.ProcessStep;
import org.insightcentre.tbischeduling.datamodel.ProcessSequence;
import org.insightcentre.tbischeduling.datamodel.ResourceNeed;
import org.insightcentre.tbischeduling.datamodel.CumulativeNeed;
import org.insightcentre.tbischeduling.datamodel.CumulativeProfile;
import org.insightcentre.tbischeduling.datamodel.DisjunctiveResource;
import org.insightcentre.tbischeduling.datamodel.CumulativeResource;
import org.insightcentre.tbischeduling.datamodel.ResourceActivity;
import org.insightcentre.tbischeduling.datamodel.Order;
import org.insightcentre.tbischeduling.datamodel.Job;
import org.insightcentre.tbischeduling.datamodel.Task;
import org.insightcentre.tbischeduling.datamodel.WiP;
import org.insightcentre.tbischeduling.datamodel.Downtime;
import org.insightcentre.tbischeduling.datamodel.Solution;
import org.insightcentre.tbischeduling.datamodel.JobAssignment;
import org.insightcentre.tbischeduling.datamodel.TaskAssignment;
import org.insightcentre.tbischeduling.datamodel.ResourceUtilization;
import org.insightcentre.tbischeduling.datamodel.IntermediateSolution;
import org.insightcentre.tbischeduling.datamodel.SolutionError;
import org.insightcentre.tbischeduling.datamodel.DifferenceType;
import org.insightcentre.tbischeduling.datamodel.WarningType;
import org.insightcentre.tbischeduling.datamodel.SequenceType;
import org.insightcentre.tbischeduling.datamodel.Severity;
import org.insightcentre.tbischeduling.datamodel.ModelType;
import org.insightcentre.tbischeduling.datamodel.SolverBackend;
import org.insightcentre.tbischeduling.datamodel.SolverStatus;
import org.insightcentre.tbischeduling.datamodel.ObjectiveType;
import org.insightcentre.tbischeduling.datamodel.ResourceModel;
import org.insightcentre.tbischeduling.datamodel.DurationModel;
import org.insightcentre.tbischeduling.datamodel.XMLLoader;
import java.util.*;
import java.io.*;
import framework.types.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import framework.ApplicationObjectInterface;
import framework.ApplicationDatasetInterface;
import framework.AppearInCollection;

/**
 * This is an abstract base class for the dataset description. Each application should derive one class from this to describe a dataset in the application. Most of the time, we will only use one dataset at a time.
 * @author generated
*/

public abstract class ApplicationDataset implements ApplicationDatasetInterface,Comparable<ApplicationDataset>{
/**
 *  Marks the dataset as dirty, there are unsaved modifications.
 *
*/

    public Boolean dirty;

    private transient BooleanProperty dirtyWrapper;

/**
 *  Id number of the dataset, required as this item is not an ApplicationObject.
 *
*/

    public Integer id;

/**
 *  Name of the datase. This is required as the item is not an ApplicationObject.
 *
*/

    public String name;

/**
 *  Marks the dataset as valid, when the solver has been run and no other data has been changed.
 *
*/

    public Boolean valid;

    private transient BooleanProperty validWrapper;

/**
 *  This lists holds all items of class ApplicationDataset and its subclasses
 *
*/

    List<ApplicationDataset> listApplicationDataset = new ArrayList<ApplicationDataset>();

/**
 *  This lists holds all items of class ApplicationObject and its subclasses
 *
*/

    List<ApplicationObject> listApplicationObject = new ArrayList<ApplicationObject>();

/**
 *  This lists holds all items of class ApplicationDifference and its subclasses
 *
*/

    List<ApplicationDifference> listApplicationDifference = new ArrayList<ApplicationDifference>();

/**
 *  This lists holds all items of class ApplicationWarning and its subclasses
 *
*/

    List<ApplicationWarning> listApplicationWarning = new ArrayList<ApplicationWarning>();

/**
 *  This lists holds all items of class Scenario and its subclasses
 *
*/

    List<Scenario> listScenario = new ArrayList<Scenario>();

/**
 *  This lists holds all items of class AbstractSolverProperty and its subclasses
 *
*/

    List<AbstractSolverProperty> listAbstractSolverProperty = new ArrayList<AbstractSolverProperty>();

/**
 *  This lists holds all items of class SolverProperty and its subclasses
 *
*/

    List<SolverProperty> listSolverProperty = new ArrayList<SolverProperty>();

/**
 *  This lists holds all items of class SolverRun and its subclasses
 *
*/

    List<SolverRun> listSolverRun = new ArrayList<SolverRun>();

/**
 *  This lists holds all items of class AbstractDataGeneratorProperty and its subclasses
 *
*/

    List<AbstractDataGeneratorProperty> listAbstractDataGeneratorProperty = new ArrayList<AbstractDataGeneratorProperty>();

/**
 *  This lists holds all items of class DataGeneratorProperty and its subclasses
 *
*/

    List<DataGeneratorProperty> listDataGeneratorProperty = new ArrayList<DataGeneratorProperty>();

/**
 *  This lists holds all items of class DataGeneratorRun and its subclasses
 *
*/

    List<DataGeneratorRun> listDataGeneratorRun = new ArrayList<DataGeneratorRun>();

/**
 *  This lists holds all items of class InputError and its subclasses
 *
*/

    List<InputError> listInputError = new ArrayList<InputError>();

/**
 *  This lists holds all items of class Problem and its subclasses
 *
*/

    List<Problem> listProblem = new ArrayList<Problem>();

/**
 *  This lists holds all items of class Product and its subclasses
 *
*/

    List<Product> listProduct = new ArrayList<Product>();

/**
 *  This lists holds all items of class Process and its subclasses
 *
*/

    List<Process> listProcess = new ArrayList<Process>();

/**
 *  This lists holds all items of class ProcessStep and its subclasses
 *
*/

    List<ProcessStep> listProcessStep = new ArrayList<ProcessStep>();

/**
 *  This lists holds all items of class ProcessSequence and its subclasses
 *
*/

    List<ProcessSequence> listProcessSequence = new ArrayList<ProcessSequence>();

/**
 *  This lists holds all items of class ResourceNeed and its subclasses
 *
*/

    List<ResourceNeed> listResourceNeed = new ArrayList<ResourceNeed>();

/**
 *  This lists holds all items of class CumulativeNeed and its subclasses
 *
*/

    List<CumulativeNeed> listCumulativeNeed = new ArrayList<CumulativeNeed>();

/**
 *  This lists holds all items of class CumulativeProfile and its subclasses
 *
*/

    List<CumulativeProfile> listCumulativeProfile = new ArrayList<CumulativeProfile>();

/**
 *  This lists holds all items of class DisjunctiveResource and its subclasses
 *
*/

    List<DisjunctiveResource> listDisjunctiveResource = new ArrayList<DisjunctiveResource>();

/**
 *  This lists holds all items of class CumulativeResource and its subclasses
 *
*/

    List<CumulativeResource> listCumulativeResource = new ArrayList<CumulativeResource>();

/**
 *  This lists holds all items of class ResourceActivity and its subclasses
 *
*/

    List<ResourceActivity> listResourceActivity = new ArrayList<ResourceActivity>();

/**
 *  This lists holds all items of class Order and its subclasses
 *
*/

    List<Order> listOrder = new ArrayList<Order>();

/**
 *  This lists holds all items of class Job and its subclasses
 *
*/

    List<Job> listJob = new ArrayList<Job>();

/**
 *  This lists holds all items of class Task and its subclasses
 *
*/

    List<Task> listTask = new ArrayList<Task>();

/**
 *  This lists holds all items of class WiP and its subclasses
 *
*/

    List<WiP> listWiP = new ArrayList<WiP>();

/**
 *  This lists holds all items of class Downtime and its subclasses
 *
*/

    List<Downtime> listDowntime = new ArrayList<Downtime>();

/**
 *  This lists holds all items of class Solution and its subclasses
 *
*/

    List<Solution> listSolution = new ArrayList<Solution>();

/**
 *  This lists holds all items of class JobAssignment and its subclasses
 *
*/

    List<JobAssignment> listJobAssignment = new ArrayList<JobAssignment>();

/**
 *  This lists holds all items of class TaskAssignment and its subclasses
 *
*/

    List<TaskAssignment> listTaskAssignment = new ArrayList<TaskAssignment>();

/**
 *  This lists holds all items of class ResourceUtilization and its subclasses
 *
*/

    List<ResourceUtilization> listResourceUtilization = new ArrayList<ResourceUtilization>();

/**
 *  This lists holds all items of class IntermediateSolution and its subclasses
 *
*/

    List<IntermediateSolution> listIntermediateSolution = new ArrayList<IntermediateSolution>();

/**
 *  This lists holds all items of class SolutionError and its subclasses
 *
*/

    List<SolutionError> listSolutionError = new ArrayList<SolutionError>();

/**
 *  This is the static counter from which all id numbers are generated.It is used by all classes, so that ids are unique over all objects.
 *
*/

    private static int idNr=0;

public int compareTo(ApplicationDataset ds2){
    return id.compareTo(ds2.getId());
}

/**
 *  No-arg constructor for use in TableView
 *
*/

    public ApplicationDataset(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public ApplicationDataset(ApplicationDataset applicationDataset){
        setId(ApplicationDataset.getIdNr());
        setName("");
        addApplicationDataset(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public ApplicationDataset(Boolean dirty,
            Integer id,
            String name,
            Boolean valid){
        setDirty(dirty);
        setId(id);
        setName(name);
        setValid(valid);
        addApplicationDataset(this);
    }

    public Boolean remove(){
        // ignored, you can not remove a dataset like this
        return true;
    }

/**
 *  get attribute dirty
 *
 * @return Boolean
*/

    public Boolean getDirty(){
        return this.dirty;
    }

    public BooleanProperty dirtyWrapperProperty() {
        if (dirtyWrapper == null) {
            dirtyWrapper = new SimpleBooleanProperty();
        }
        dirtyWrapper.set(dirty);
        return dirtyWrapper;
    }

/**
 *  get attribute id
 *
 * @return Integer
*/

    public Integer getId(){
        return this.id;
    }

/**
 *  get attribute name
 *
 * @return String
*/

    public String getName(){
        return this.name;
    }

/**
 *  get attribute valid
 *
 * @return Boolean
*/

    public Boolean getValid(){
        return this.valid;
    }

    public BooleanProperty validWrapperProperty() {
        if (validWrapper == null) {
            validWrapper = new SimpleBooleanProperty();
        }
        validWrapper.set(valid);
        return validWrapper;
    }

/**
 *  implement the ApplicationObjectInterface
 *
*/

    public ApplicationDataset getApplicationDataset() {
        return this;
    }

    public List<String> getListOfClassNames(){
        return Arrays.asList("ApplicationDifference",
                             "ApplicationWarning",
                             "CumulativeNeed",
                             "CumulativeProfile",
                             "CumulativeResource",
                             "DataGeneratorProperty",
                             "DataGeneratorRun",
                             "DisjunctiveResource",
                             "Downtime",
                             "InputError",
                             "IntermediateSolution",
                             "Job",
                             "JobAssignment",
                             "Order",
                             "Problem",
                             "Process",
                             "ProcessSequence",
                             "ProcessStep",
                             "Product",
                             "ResourceNeed",
                             "ResourceUtilization",
                             "Scenario",
                             "Solution",
                             "SolutionError",
                             "SolverProperty",
                             "SolverRun",
                             "Task",
                             "TaskAssignment",
                             "WiP");
    }

/**
 *  Iterator for list of class ApplicationDataset
 *
*/

    public Iterator<ApplicationDataset> getIteratorApplicationDataset(){
        return listApplicationDataset.iterator();
    }

/**
 *  Getter for list of class ApplicationDataset
 *
*/

    public List<ApplicationDataset> getListApplicationDataset(){
        return listApplicationDataset;
    }

/**
 *  reset the list of class ApplicationDataset; use with care, does not call cascades
 *
*/

    public void resetListApplicationDataset(){
        listApplicationDataset = new ArrayList<ApplicationDataset>();
        resetListScenario();
    }

/**
 *  Iterator for list of class ApplicationObject
 *
*/

    public Iterator<ApplicationObject> getIteratorApplicationObject(){
        return listApplicationObject.iterator();
    }

/**
 *  Getter for list of class ApplicationObject
 *
*/

    public List<ApplicationObject> getListApplicationObject(){
        return listApplicationObject;
    }

/**
 *  reset the list of class ApplicationObject; use with care, does not call cascades
 *
*/

    public void resetListApplicationObject(){
        listApplicationObject = new ArrayList<ApplicationObject>();
        resetListApplicationWarning();
        resetListApplicationDifference();
        resetListAbstractSolverProperty();
        resetListSolverProperty();
        resetListSolverRun();
        resetListAbstractDataGeneratorProperty();
        resetListDataGeneratorProperty();
        resetListDataGeneratorRun();
        resetListInputError();
        resetListProblem();
        resetListProduct();
        resetListProcess();
        resetListProcessStep();
        resetListProcessSequence();
        resetListResourceNeed();
        resetListCumulativeNeed();
        resetListCumulativeProfile();
        resetListDisjunctiveResource();
        resetListCumulativeResource();
        resetListResourceActivity();
        resetListWiP();
        resetListDowntime();
        resetListTaskAssignment();
        resetListOrder();
        resetListJob();
        resetListTask();
        resetListSolution();
        resetListJobAssignment();
        resetListResourceUtilization();
        resetListIntermediateSolution();
        resetListSolutionError();
    }

/**
 *  Iterator for list of class ApplicationDifference
 *
*/

    public Iterator<ApplicationDifference> getIteratorApplicationDifference(){
        return listApplicationDifference.iterator();
    }

/**
 *  Getter for list of class ApplicationDifference
 *
*/

    public List<ApplicationDifference> getListApplicationDifference(){
        return listApplicationDifference;
    }

/**
 *  reset the list of class ApplicationDifference; use with care, does not call cascades
 *
*/

    public void resetListApplicationDifference(){
        listApplicationDifference = new ArrayList<ApplicationDifference>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof ApplicationDifference)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class ApplicationWarning
 *
*/

    public Iterator<ApplicationWarning> getIteratorApplicationWarning(){
        return listApplicationWarning.iterator();
    }

/**
 *  Getter for list of class ApplicationWarning
 *
*/

    public List<ApplicationWarning> getListApplicationWarning(){
        return listApplicationWarning;
    }

/**
 *  reset the list of class ApplicationWarning; use with care, does not call cascades
 *
*/

    public void resetListApplicationWarning(){
        listApplicationWarning = new ArrayList<ApplicationWarning>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof ApplicationWarning)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class Scenario
 *
*/

    public Iterator<Scenario> getIteratorScenario(){
        return listScenario.iterator();
    }

/**
 *  Getter for list of class Scenario
 *
*/

    public List<Scenario> getListScenario(){
        return listScenario;
    }

/**
 *  reset the list of class Scenario; use with care, does not call cascades
 *
*/

    public void resetListScenario(){
        listScenario = new ArrayList<Scenario>();
        List<ApplicationDataset> newListApplicationDataset = new ArrayList<ApplicationDataset>();
        for(ApplicationDataset a:listApplicationDataset){
            if (!(a instanceof Scenario)){
                newListApplicationDataset.add(a);
            }
        }
       listApplicationDataset = newListApplicationDataset;
    }

/**
 *  Iterator for list of class AbstractSolverProperty
 *
*/

    public Iterator<AbstractSolverProperty> getIteratorAbstractSolverProperty(){
        return listAbstractSolverProperty.iterator();
    }

/**
 *  Getter for list of class AbstractSolverProperty
 *
*/

    public List<AbstractSolverProperty> getListAbstractSolverProperty(){
        return listAbstractSolverProperty;
    }

/**
 *  reset the list of class AbstractSolverProperty; use with care, does not call cascades
 *
*/

    public void resetListAbstractSolverProperty(){
        listAbstractSolverProperty = new ArrayList<AbstractSolverProperty>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof AbstractSolverProperty)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
        resetListSolverProperty();
        resetListSolverRun();
    }

/**
 *  Iterator for list of class SolverProperty
 *
*/

    public Iterator<SolverProperty> getIteratorSolverProperty(){
        return listSolverProperty.iterator();
    }

/**
 *  Getter for list of class SolverProperty
 *
*/

    public List<SolverProperty> getListSolverProperty(){
        return listSolverProperty;
    }

/**
 *  reset the list of class SolverProperty; use with care, does not call cascades
 *
*/

    public void resetListSolverProperty(){
        listSolverProperty = new ArrayList<SolverProperty>();
        List<AbstractSolverProperty> newListAbstractSolverProperty = new ArrayList<AbstractSolverProperty>();
        for(AbstractSolverProperty a:listAbstractSolverProperty){
            if (!(a instanceof SolverProperty)){
                newListAbstractSolverProperty.add(a);
            }
        }
       listAbstractSolverProperty = newListAbstractSolverProperty;
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof SolverProperty)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class SolverRun
 *
*/

    public Iterator<SolverRun> getIteratorSolverRun(){
        return listSolverRun.iterator();
    }

/**
 *  Getter for list of class SolverRun
 *
*/

    public List<SolverRun> getListSolverRun(){
        return listSolverRun;
    }

/**
 *  reset the list of class SolverRun; use with care, does not call cascades
 *
*/

    public void resetListSolverRun(){
        listSolverRun = new ArrayList<SolverRun>();
        List<AbstractSolverProperty> newListAbstractSolverProperty = new ArrayList<AbstractSolverProperty>();
        for(AbstractSolverProperty a:listAbstractSolverProperty){
            if (!(a instanceof SolverRun)){
                newListAbstractSolverProperty.add(a);
            }
        }
       listAbstractSolverProperty = newListAbstractSolverProperty;
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof SolverRun)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class AbstractDataGeneratorProperty
 *
*/

    public Iterator<AbstractDataGeneratorProperty> getIteratorAbstractDataGeneratorProperty(){
        return listAbstractDataGeneratorProperty.iterator();
    }

/**
 *  Getter for list of class AbstractDataGeneratorProperty
 *
*/

    public List<AbstractDataGeneratorProperty> getListAbstractDataGeneratorProperty(){
        return listAbstractDataGeneratorProperty;
    }

/**
 *  reset the list of class AbstractDataGeneratorProperty; use with care, does not call cascades
 *
*/

    public void resetListAbstractDataGeneratorProperty(){
        listAbstractDataGeneratorProperty = new ArrayList<AbstractDataGeneratorProperty>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof AbstractDataGeneratorProperty)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
        resetListDataGeneratorProperty();
        resetListDataGeneratorRun();
    }

/**
 *  Iterator for list of class DataGeneratorProperty
 *
*/

    public Iterator<DataGeneratorProperty> getIteratorDataGeneratorProperty(){
        return listDataGeneratorProperty.iterator();
    }

/**
 *  Getter for list of class DataGeneratorProperty
 *
*/

    public List<DataGeneratorProperty> getListDataGeneratorProperty(){
        return listDataGeneratorProperty;
    }

/**
 *  reset the list of class DataGeneratorProperty; use with care, does not call cascades
 *
*/

    public void resetListDataGeneratorProperty(){
        listDataGeneratorProperty = new ArrayList<DataGeneratorProperty>();
        List<AbstractDataGeneratorProperty> newListAbstractDataGeneratorProperty = new ArrayList<AbstractDataGeneratorProperty>();
        for(AbstractDataGeneratorProperty a:listAbstractDataGeneratorProperty){
            if (!(a instanceof DataGeneratorProperty)){
                newListAbstractDataGeneratorProperty.add(a);
            }
        }
       listAbstractDataGeneratorProperty = newListAbstractDataGeneratorProperty;
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof DataGeneratorProperty)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class DataGeneratorRun
 *
*/

    public Iterator<DataGeneratorRun> getIteratorDataGeneratorRun(){
        return listDataGeneratorRun.iterator();
    }

/**
 *  Getter for list of class DataGeneratorRun
 *
*/

    public List<DataGeneratorRun> getListDataGeneratorRun(){
        return listDataGeneratorRun;
    }

/**
 *  reset the list of class DataGeneratorRun; use with care, does not call cascades
 *
*/

    public void resetListDataGeneratorRun(){
        listDataGeneratorRun = new ArrayList<DataGeneratorRun>();
        List<AbstractDataGeneratorProperty> newListAbstractDataGeneratorProperty = new ArrayList<AbstractDataGeneratorProperty>();
        for(AbstractDataGeneratorProperty a:listAbstractDataGeneratorProperty){
            if (!(a instanceof DataGeneratorRun)){
                newListAbstractDataGeneratorProperty.add(a);
            }
        }
       listAbstractDataGeneratorProperty = newListAbstractDataGeneratorProperty;
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof DataGeneratorRun)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class InputError
 *
*/

    public Iterator<InputError> getIteratorInputError(){
        return listInputError.iterator();
    }

/**
 *  Getter for list of class InputError
 *
*/

    public List<InputError> getListInputError(){
        return listInputError;
    }

/**
 *  reset the list of class InputError; use with care, does not call cascades
 *
*/

    public void resetListInputError(){
        listInputError = new ArrayList<InputError>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof InputError)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class Problem
 *
*/

    public Iterator<Problem> getIteratorProblem(){
        return listProblem.iterator();
    }

/**
 *  Getter for list of class Problem
 *
*/

    public List<Problem> getListProblem(){
        return listProblem;
    }

/**
 *  reset the list of class Problem; use with care, does not call cascades
 *
*/

    public void resetListProblem(){
        listProblem = new ArrayList<Problem>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof Problem)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class Product
 *
*/

    public Iterator<Product> getIteratorProduct(){
        return listProduct.iterator();
    }

/**
 *  Getter for list of class Product
 *
*/

    public List<Product> getListProduct(){
        return listProduct;
    }

/**
 *  reset the list of class Product; use with care, does not call cascades
 *
*/

    public void resetListProduct(){
        listProduct = new ArrayList<Product>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof Product)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class Process
 *
*/

    public Iterator<Process> getIteratorProcess(){
        return listProcess.iterator();
    }

/**
 *  Getter for list of class Process
 *
*/

    public List<Process> getListProcess(){
        return listProcess;
    }

/**
 *  reset the list of class Process; use with care, does not call cascades
 *
*/

    public void resetListProcess(){
        listProcess = new ArrayList<Process>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof Process)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class ProcessStep
 *
*/

    public Iterator<ProcessStep> getIteratorProcessStep(){
        return listProcessStep.iterator();
    }

/**
 *  Getter for list of class ProcessStep
 *
*/

    public List<ProcessStep> getListProcessStep(){
        return listProcessStep;
    }

/**
 *  reset the list of class ProcessStep; use with care, does not call cascades
 *
*/

    public void resetListProcessStep(){
        listProcessStep = new ArrayList<ProcessStep>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof ProcessStep)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class ProcessSequence
 *
*/

    public Iterator<ProcessSequence> getIteratorProcessSequence(){
        return listProcessSequence.iterator();
    }

/**
 *  Getter for list of class ProcessSequence
 *
*/

    public List<ProcessSequence> getListProcessSequence(){
        return listProcessSequence;
    }

/**
 *  reset the list of class ProcessSequence; use with care, does not call cascades
 *
*/

    public void resetListProcessSequence(){
        listProcessSequence = new ArrayList<ProcessSequence>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof ProcessSequence)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class ResourceNeed
 *
*/

    public Iterator<ResourceNeed> getIteratorResourceNeed(){
        return listResourceNeed.iterator();
    }

/**
 *  Getter for list of class ResourceNeed
 *
*/

    public List<ResourceNeed> getListResourceNeed(){
        return listResourceNeed;
    }

/**
 *  reset the list of class ResourceNeed; use with care, does not call cascades
 *
*/

    public void resetListResourceNeed(){
        listResourceNeed = new ArrayList<ResourceNeed>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof ResourceNeed)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class CumulativeNeed
 *
*/

    public Iterator<CumulativeNeed> getIteratorCumulativeNeed(){
        return listCumulativeNeed.iterator();
    }

/**
 *  Getter for list of class CumulativeNeed
 *
*/

    public List<CumulativeNeed> getListCumulativeNeed(){
        return listCumulativeNeed;
    }

/**
 *  reset the list of class CumulativeNeed; use with care, does not call cascades
 *
*/

    public void resetListCumulativeNeed(){
        listCumulativeNeed = new ArrayList<CumulativeNeed>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof CumulativeNeed)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class CumulativeProfile
 *
*/

    public Iterator<CumulativeProfile> getIteratorCumulativeProfile(){
        return listCumulativeProfile.iterator();
    }

/**
 *  Getter for list of class CumulativeProfile
 *
*/

    public List<CumulativeProfile> getListCumulativeProfile(){
        return listCumulativeProfile;
    }

/**
 *  reset the list of class CumulativeProfile; use with care, does not call cascades
 *
*/

    public void resetListCumulativeProfile(){
        listCumulativeProfile = new ArrayList<CumulativeProfile>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof CumulativeProfile)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class DisjunctiveResource
 *
*/

    public Iterator<DisjunctiveResource> getIteratorDisjunctiveResource(){
        return listDisjunctiveResource.iterator();
    }

/**
 *  Getter for list of class DisjunctiveResource
 *
*/

    public List<DisjunctiveResource> getListDisjunctiveResource(){
        return listDisjunctiveResource;
    }

/**
 *  reset the list of class DisjunctiveResource; use with care, does not call cascades
 *
*/

    public void resetListDisjunctiveResource(){
        listDisjunctiveResource = new ArrayList<DisjunctiveResource>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof DisjunctiveResource)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class CumulativeResource
 *
*/

    public Iterator<CumulativeResource> getIteratorCumulativeResource(){
        return listCumulativeResource.iterator();
    }

/**
 *  Getter for list of class CumulativeResource
 *
*/

    public List<CumulativeResource> getListCumulativeResource(){
        return listCumulativeResource;
    }

/**
 *  reset the list of class CumulativeResource; use with care, does not call cascades
 *
*/

    public void resetListCumulativeResource(){
        listCumulativeResource = new ArrayList<CumulativeResource>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof CumulativeResource)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class ResourceActivity
 *
*/

    public Iterator<ResourceActivity> getIteratorResourceActivity(){
        return listResourceActivity.iterator();
    }

/**
 *  Getter for list of class ResourceActivity
 *
*/

    public List<ResourceActivity> getListResourceActivity(){
        return listResourceActivity;
    }

/**
 *  reset the list of class ResourceActivity; use with care, does not call cascades
 *
*/

    public void resetListResourceActivity(){
        listResourceActivity = new ArrayList<ResourceActivity>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof ResourceActivity)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
        resetListWiP();
        resetListDowntime();
        resetListTaskAssignment();
    }

/**
 *  Iterator for list of class Order
 *
*/

    public Iterator<Order> getIteratorOrder(){
        return listOrder.iterator();
    }

/**
 *  Getter for list of class Order
 *
*/

    public List<Order> getListOrder(){
        return listOrder;
    }

/**
 *  reset the list of class Order; use with care, does not call cascades
 *
*/

    public void resetListOrder(){
        listOrder = new ArrayList<Order>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof Order)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class Job
 *
*/

    public Iterator<Job> getIteratorJob(){
        return listJob.iterator();
    }

/**
 *  Getter for list of class Job
 *
*/

    public List<Job> getListJob(){
        return listJob;
    }

/**
 *  reset the list of class Job; use with care, does not call cascades
 *
*/

    public void resetListJob(){
        listJob = new ArrayList<Job>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof Job)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class Task
 *
*/

    public Iterator<Task> getIteratorTask(){
        return listTask.iterator();
    }

/**
 *  Getter for list of class Task
 *
*/

    public List<Task> getListTask(){
        return listTask;
    }

/**
 *  reset the list of class Task; use with care, does not call cascades
 *
*/

    public void resetListTask(){
        listTask = new ArrayList<Task>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof Task)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class WiP
 *
*/

    public Iterator<WiP> getIteratorWiP(){
        return listWiP.iterator();
    }

/**
 *  Getter for list of class WiP
 *
*/

    public List<WiP> getListWiP(){
        return listWiP;
    }

/**
 *  reset the list of class WiP; use with care, does not call cascades
 *
*/

    public void resetListWiP(){
        listWiP = new ArrayList<WiP>();
        List<ResourceActivity> newListResourceActivity = new ArrayList<ResourceActivity>();
        for(ResourceActivity a:listResourceActivity){
            if (!(a instanceof WiP)){
                newListResourceActivity.add(a);
            }
        }
       listResourceActivity = newListResourceActivity;
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof WiP)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class Downtime
 *
*/

    public Iterator<Downtime> getIteratorDowntime(){
        return listDowntime.iterator();
    }

/**
 *  Getter for list of class Downtime
 *
*/

    public List<Downtime> getListDowntime(){
        return listDowntime;
    }

/**
 *  reset the list of class Downtime; use with care, does not call cascades
 *
*/

    public void resetListDowntime(){
        listDowntime = new ArrayList<Downtime>();
        List<ResourceActivity> newListResourceActivity = new ArrayList<ResourceActivity>();
        for(ResourceActivity a:listResourceActivity){
            if (!(a instanceof Downtime)){
                newListResourceActivity.add(a);
            }
        }
       listResourceActivity = newListResourceActivity;
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof Downtime)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class Solution
 *
*/

    public Iterator<Solution> getIteratorSolution(){
        return listSolution.iterator();
    }

/**
 *  Getter for list of class Solution
 *
*/

    public List<Solution> getListSolution(){
        return listSolution;
    }

/**
 *  reset the list of class Solution; use with care, does not call cascades
 *
*/

    public void resetListSolution(){
        listSolution = new ArrayList<Solution>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof Solution)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class JobAssignment
 *
*/

    public Iterator<JobAssignment> getIteratorJobAssignment(){
        return listJobAssignment.iterator();
    }

/**
 *  Getter for list of class JobAssignment
 *
*/

    public List<JobAssignment> getListJobAssignment(){
        return listJobAssignment;
    }

/**
 *  reset the list of class JobAssignment; use with care, does not call cascades
 *
*/

    public void resetListJobAssignment(){
        listJobAssignment = new ArrayList<JobAssignment>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof JobAssignment)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class TaskAssignment
 *
*/

    public Iterator<TaskAssignment> getIteratorTaskAssignment(){
        return listTaskAssignment.iterator();
    }

/**
 *  Getter for list of class TaskAssignment
 *
*/

    public List<TaskAssignment> getListTaskAssignment(){
        return listTaskAssignment;
    }

/**
 *  reset the list of class TaskAssignment; use with care, does not call cascades
 *
*/

    public void resetListTaskAssignment(){
        listTaskAssignment = new ArrayList<TaskAssignment>();
        List<ResourceActivity> newListResourceActivity = new ArrayList<ResourceActivity>();
        for(ResourceActivity a:listResourceActivity){
            if (!(a instanceof TaskAssignment)){
                newListResourceActivity.add(a);
            }
        }
       listResourceActivity = newListResourceActivity;
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof TaskAssignment)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class ResourceUtilization
 *
*/

    public Iterator<ResourceUtilization> getIteratorResourceUtilization(){
        return listResourceUtilization.iterator();
    }

/**
 *  Getter for list of class ResourceUtilization
 *
*/

    public List<ResourceUtilization> getListResourceUtilization(){
        return listResourceUtilization;
    }

/**
 *  reset the list of class ResourceUtilization; use with care, does not call cascades
 *
*/

    public void resetListResourceUtilization(){
        listResourceUtilization = new ArrayList<ResourceUtilization>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof ResourceUtilization)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class IntermediateSolution
 *
*/

    public Iterator<IntermediateSolution> getIteratorIntermediateSolution(){
        return listIntermediateSolution.iterator();
    }

/**
 *  Getter for list of class IntermediateSolution
 *
*/

    public List<IntermediateSolution> getListIntermediateSolution(){
        return listIntermediateSolution;
    }

/**
 *  reset the list of class IntermediateSolution; use with care, does not call cascades
 *
*/

    public void resetListIntermediateSolution(){
        listIntermediateSolution = new ArrayList<IntermediateSolution>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof IntermediateSolution)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Iterator for list of class SolutionError
 *
*/

    public Iterator<SolutionError> getIteratorSolutionError(){
        return listSolutionError.iterator();
    }

/**
 *  Getter for list of class SolutionError
 *
*/

    public List<SolutionError> getListSolutionError(){
        return listSolutionError;
    }

/**
 *  reset the list of class SolutionError; use with care, does not call cascades
 *
*/

    public void resetListSolutionError(){
        listSolutionError = new ArrayList<SolutionError>();
        List<ApplicationObject> newListApplicationObject = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:listApplicationObject){
            if (!(a instanceof SolutionError)){
                newListApplicationObject.add(a);
            }
        }
       listApplicationObject = newListApplicationObject;
    }

/**
 *  Generate a new id number, used in constructor calls
 *
*/

    public static int getIdNr() {
        return idNr++;
    }

    public static void setIdNr(int value) {
        idNr = value;
    }

    public int lastIdNr(){
        int res = 0;
        for(ApplicationObject obj:getListApplicationObject()){
            res = Math.max(res,obj.getId());
        }
        return res;
    }

/**
 *  set attribute dirty, mark dataset as dirty, mark dataset as not valid
@param dirty Boolean
 *
*/

    public void setDirty(Boolean dirty){
        this.dirty = dirty;
    }

/**
 *  set attribute id, mark dataset as dirty, mark dataset as not valid
@param id Integer
 *
*/

    public void setId(Integer id){
        this.id = id;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute name, mark dataset as dirty, mark dataset as not valid
@param name String
 *
*/

    public void setName(String name){
        this.name = name;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  set attribute valid, mark dataset as dirty, mark dataset as not valid
@param valid Boolean
 *
*/

    public void setValid(Boolean valid){
        this.valid = valid;
    }

/**
 *  inc attribute id, mark dataset as dirty, mark dataset as not valid
 *
*/

    public void incId(){
        this.id++;
        getApplicationDataset().setDirty(true);
        getApplicationDataset().setValid(false);
    }

/**
 *  Removing object item of class ApplicationDataset; remove all dependent objects of class ApplicationObject which refer to item through their attribute applicationDataset
 *
*/

    public void cascadeApplicationObjectApplicationDataset(ApplicationDataset item){
        assert item != null;
        List<ApplicationObject> toRemove = new ArrayList<ApplicationObject>();
        for(ApplicationObject a:getListApplicationObject()) {
         if (a.getApplicationDataset() == item) {
            toRemove.add(a);
         }
        }
        for(ApplicationObject b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class SolverProperty; remove all dependent objects of class Scenario which refer to item through their attribute solverProperty
 *
*/

    public void cascadeScenarioSolverProperty(SolverProperty item){
        assert item != null;
        List<Scenario> toRemove = new ArrayList<Scenario>();
        for(Scenario a:getListScenario()) {
         if (a.getSolverProperty() == item) {
            toRemove.add(a);
         }
        }
        for(Scenario b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class DataGeneratorProperty; remove all dependent objects of class Scenario which refer to item through their attribute dataGeneratorProperty
 *
*/

    public void cascadeScenarioDataGeneratorProperty(DataGeneratorProperty item){
        assert item != null;
        List<Scenario> toRemove = new ArrayList<Scenario>();
        for(Scenario a:getListScenario()) {
         if (a.getDataGeneratorProperty() == item) {
            toRemove.add(a);
         }
        }
        for(Scenario b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class Process; remove all dependent objects of class Product which refer to item through their attribute defaultProcess
 *
*/

    public void cascadeProductDefaultProcess(Process item){
        assert item != null;
        List<Product> toRemove = new ArrayList<Product>();
        for(Product a:getListProduct()) {
         if (a.getDefaultProcess() == item) {
            toRemove.add(a);
         }
        }
        for(Product b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class Process; remove all dependent objects of class ProcessStep which refer to item through their attribute process
 *
*/

    public void cascadeProcessStepProcess(Process item){
        assert item != null;
        List<ProcessStep> toRemove = new ArrayList<ProcessStep>();
        for(ProcessStep a:getListProcessStep()) {
         if (a.getProcess() == item) {
            toRemove.add(a);
         }
        }
        for(ProcessStep b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class ProcessStep; remove all dependent objects of class ProcessSequence which refer to item through their attribute before
 *
*/

    public void cascadeProcessSequenceBefore(ProcessStep item){
        assert item != null;
        List<ProcessSequence> toRemove = new ArrayList<ProcessSequence>();
        for(ProcessSequence a:getListProcessSequence()) {
         if (a.getBefore() == item) {
            toRemove.add(a);
         }
        }
        for(ProcessSequence b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class ProcessStep; remove all dependent objects of class ProcessSequence which refer to item through their attribute after
 *
*/

    public void cascadeProcessSequenceAfter(ProcessStep item){
        assert item != null;
        List<ProcessSequence> toRemove = new ArrayList<ProcessSequence>();
        for(ProcessSequence a:getListProcessSequence()) {
         if (a.getAfter() == item) {
            toRemove.add(a);
         }
        }
        for(ProcessSequence b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class DisjunctiveResource; remove all dependent objects of class ResourceNeed which refer to item through their attribute disjunctiveResource
 *
*/

    public void cascadeResourceNeedDisjunctiveResource(DisjunctiveResource item){
        assert item != null;
        List<ResourceNeed> toRemove = new ArrayList<ResourceNeed>();
        for(ResourceNeed a:getListResourceNeed()) {
         if (a.getDisjunctiveResource() == item) {
            toRemove.add(a);
         }
        }
        for(ResourceNeed b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class ProcessStep; remove all dependent objects of class ResourceNeed which refer to item through their attribute processStep
 *
*/

    public void cascadeResourceNeedProcessStep(ProcessStep item){
        assert item != null;
        List<ResourceNeed> toRemove = new ArrayList<ResourceNeed>();
        for(ResourceNeed a:getListResourceNeed()) {
         if (a.getProcessStep() == item) {
            toRemove.add(a);
         }
        }
        for(ResourceNeed b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class CumulativeResource; remove all dependent objects of class CumulativeNeed which refer to item through their attribute cumulativeResource
 *
*/

    public void cascadeCumulativeNeedCumulativeResource(CumulativeResource item){
        assert item != null;
        List<CumulativeNeed> toRemove = new ArrayList<CumulativeNeed>();
        for(CumulativeNeed a:getListCumulativeNeed()) {
         if (a.getCumulativeResource() == item) {
            toRemove.add(a);
         }
        }
        for(CumulativeNeed b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class ProcessStep; remove all dependent objects of class CumulativeNeed which refer to item through their attribute processStep
 *
*/

    public void cascadeCumulativeNeedProcessStep(ProcessStep item){
        assert item != null;
        List<CumulativeNeed> toRemove = new ArrayList<CumulativeNeed>();
        for(CumulativeNeed a:getListCumulativeNeed()) {
         if (a.getProcessStep() == item) {
            toRemove.add(a);
         }
        }
        for(CumulativeNeed b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class CumulativeResource; remove all dependent objects of class CumulativeProfile which refer to item through their attribute cumulativeResource
 *
*/

    public void cascadeCumulativeProfileCumulativeResource(CumulativeResource item){
        assert item != null;
        List<CumulativeProfile> toRemove = new ArrayList<CumulativeProfile>();
        for(CumulativeProfile a:getListCumulativeProfile()) {
         if (a.getCumulativeResource() == item) {
            toRemove.add(a);
         }
        }
        for(CumulativeProfile b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class DisjunctiveResource; remove all dependent objects of class ResourceActivity which refer to item through their attribute disjunctiveResource
 *
*/

    public void cascadeResourceActivityDisjunctiveResource(DisjunctiveResource item){
        assert item != null;
        List<ResourceActivity> toRemove = new ArrayList<ResourceActivity>();
        for(ResourceActivity a:getListResourceActivity()) {
         if (a.getDisjunctiveResource() == item) {
            toRemove.add(a);
         }
        }
        for(ResourceActivity b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class Product; remove all dependent objects of class Order which refer to item through their attribute product
 *
*/

    public void cascadeOrderProduct(Product item){
        assert item != null;
        List<Order> toRemove = new ArrayList<Order>();
        for(Order a:getListOrder()) {
         if (a.getProduct() == item) {
            toRemove.add(a);
         }
        }
        for(Order b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class Process; remove all dependent objects of class Order which refer to item through their attribute process
 *
*/

    public void cascadeOrderProcess(Process item){
        assert item != null;
        List<Order> toRemove = new ArrayList<Order>();
        for(Order a:getListOrder()) {
         if (a.getProcess() == item) {
            toRemove.add(a);
         }
        }
        for(Order b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class Order; remove all dependent objects of class Job which refer to item through their attribute order
 *
*/

    public void cascadeJobOrder(Order item){
        assert item != null;
        List<Job> toRemove = new ArrayList<Job>();
        for(Job a:getListJob()) {
         if (a.getOrder() == item) {
            toRemove.add(a);
         }
        }
        for(Job b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class Process; remove all dependent objects of class Job which refer to item through their attribute process
 *
*/

    public void cascadeJobProcess(Process item){
        assert item != null;
        List<Job> toRemove = new ArrayList<Job>();
        for(Job a:getListJob()) {
         if (a.getProcess() == item) {
            toRemove.add(a);
         }
        }
        for(Job b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class Job; remove all dependent objects of class Task which refer to item through their attribute job
 *
*/

    public void cascadeTaskJob(Job item){
        assert item != null;
        List<Task> toRemove = new ArrayList<Task>();
        for(Task a:getListTask()) {
         if (a.getJob() == item) {
            toRemove.add(a);
         }
        }
        for(Task b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class ProcessStep; remove all dependent objects of class Task which refer to item through their attribute processStep
 *
*/

    public void cascadeTaskProcessStep(ProcessStep item){
        assert item != null;
        List<Task> toRemove = new ArrayList<Task>();
        for(Task a:getListTask()) {
         if (a.getProcessStep() == item) {
            toRemove.add(a);
         }
        }
        for(Task b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class DisjunctiveResource; remove all dependent objects of class Task which refer to item through their attribute machines
 *
*/

    public void cascadeTaskMachines(DisjunctiveResource item){
        assert item != null;
        List<Task> toRemove = new ArrayList<Task>();
        for(Task a:getListTask()) {
         if (a.getMachines().contains(item)) {
            a.getMachines().remove(item);
            if (a.getMachines().isEmpty()) {
               toRemove.add(a);
            }
         }
        }
        for(Task b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class Task; remove all dependent objects of class Task which refer to item through their attribute precedes
 *
*/

    public void cascadeTaskPrecedes(Task item){
        assert item != null;
        List<Task> toRemove = new ArrayList<Task>();
        for(Task a:getListTask()) {
         if (a.getPrecedes().contains(item)) {
            a.getPrecedes().remove(item);
         }
        }
        for(Task b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class Task; remove all dependent objects of class Task which refer to item through their attribute follows
 *
*/

    public void cascadeTaskFollows(Task item){
        assert item != null;
        List<Task> toRemove = new ArrayList<Task>();
        for(Task a:getListTask()) {
         if (a.getFollows().contains(item)) {
            a.getFollows().remove(item);
         }
        }
        for(Task b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class SolverRun; remove all dependent objects of class Solution which refer to item through their attribute solverRun
 *
*/

    public void cascadeSolutionSolverRun(SolverRun item){
        assert item != null;
        List<Solution> toRemove = new ArrayList<Solution>();
        for(Solution a:getListSolution()) {
         if (a.getSolverRun() == item) {
            toRemove.add(a);
         }
        }
        for(Solution b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class Solution; remove all dependent objects of class JobAssignment which refer to item through their attribute solution
 *
*/

    public void cascadeJobAssignmentSolution(Solution item){
        assert item != null;
        List<JobAssignment> toRemove = new ArrayList<JobAssignment>();
        for(JobAssignment a:getListJobAssignment()) {
         if (a.getSolution() == item) {
            toRemove.add(a);
         }
        }
        for(JobAssignment b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class Job; remove all dependent objects of class JobAssignment which refer to item through their attribute job
 *
*/

    public void cascadeJobAssignmentJob(Job item){
        assert item != null;
        List<JobAssignment> toRemove = new ArrayList<JobAssignment>();
        for(JobAssignment a:getListJobAssignment()) {
         if (a.getJob() == item) {
            toRemove.add(a);
         }
        }
        for(JobAssignment b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class Task; remove all dependent objects of class TaskAssignment which refer to item through their attribute task
 *
*/

    public void cascadeTaskAssignmentTask(Task item){
        assert item != null;
        List<TaskAssignment> toRemove = new ArrayList<TaskAssignment>();
        for(TaskAssignment a:getListTaskAssignment()) {
         if (a.getTask() == item) {
            toRemove.add(a);
         }
        }
        for(TaskAssignment b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class JobAssignment; remove all dependent objects of class TaskAssignment which refer to item through their attribute jobAssignment
 *
*/

    public void cascadeTaskAssignmentJobAssignment(JobAssignment item){
        assert item != null;
        List<TaskAssignment> toRemove = new ArrayList<TaskAssignment>();
        for(TaskAssignment a:getListTaskAssignment()) {
         if (a.getJobAssignment() == item) {
            toRemove.add(a);
         }
        }
        for(TaskAssignment b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class DisjunctiveResource; remove all dependent objects of class ResourceUtilization which refer to item through their attribute disjunctiveResource
 *
*/

    public void cascadeResourceUtilizationDisjunctiveResource(DisjunctiveResource item){
        assert item != null;
        List<ResourceUtilization> toRemove = new ArrayList<ResourceUtilization>();
        for(ResourceUtilization a:getListResourceUtilization()) {
         if (a.getDisjunctiveResource() == item) {
            toRemove.add(a);
         }
        }
        for(ResourceUtilization b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class Solution; remove all dependent objects of class ResourceUtilization which refer to item through their attribute solution
 *
*/

    public void cascadeResourceUtilizationSolution(Solution item){
        assert item != null;
        List<ResourceUtilization> toRemove = new ArrayList<ResourceUtilization>();
        for(ResourceUtilization a:getListResourceUtilization()) {
         if (a.getSolution() == item) {
            toRemove.add(a);
         }
        }
        for(ResourceUtilization b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class SolverRun; remove all dependent objects of class IntermediateSolution which refer to item through their attribute solverRun
 *
*/

    public void cascadeIntermediateSolutionSolverRun(SolverRun item){
        assert item != null;
        List<IntermediateSolution> toRemove = new ArrayList<IntermediateSolution>();
        for(IntermediateSolution a:getListIntermediateSolution()) {
         if (a.getSolverRun() == item) {
            toRemove.add(a);
         }
        }
        for(IntermediateSolution b:toRemove) {
            b.remove();
        }
    }

/**
 *  Removing object item of class Solution; remove all dependent objects of class SolutionError which refer to item through their attribute solution
 *
*/

    public void cascadeSolutionErrorSolution(Solution item){
        assert item != null;
        List<SolutionError> toRemove = new ArrayList<SolutionError>();
        for(SolutionError a:getListSolutionError()) {
         if (a.getSolution() == item) {
            toRemove.add(a);
         }
        }
        for(SolutionError b:toRemove) {
            b.remove();
        }
    }

/**
 *  add an item to the list for class ApplicationDataset
 *
*/

    public void addApplicationDataset(ApplicationDataset applicationDataset){
        assert applicationDataset != null;
        this.listApplicationDataset.add(applicationDataset);
    }

/**
 *  remove an item from the list for class ApplicationDataset
 *
*/

    public Boolean removeApplicationDataset(ApplicationDataset applicationDataset){
        assert applicationDataset != null;
        return this.listApplicationDataset.remove(applicationDataset);
    }

/**
 *  add an item to the list for class ApplicationObject
 *
*/

    public void addApplicationObject(ApplicationObject applicationObject){
        assert applicationObject != null;
        this.listApplicationObject.add(applicationObject);
    }

/**
 *  remove an item from the list for class ApplicationObject
 *
*/

    public Boolean removeApplicationObject(ApplicationObject applicationObject){
        assert applicationObject != null;
        return this.listApplicationObject.remove(applicationObject);
    }

/**
 *  add an item to the list for class ApplicationDifference
 *
*/

    public void addApplicationDifference(ApplicationDifference applicationDifference){
        assert applicationDifference != null;
        this.listApplicationDifference.add(applicationDifference);
    }

/**
 *  remove an item from the list for class ApplicationDifference
 *
*/

    public Boolean removeApplicationDifference(ApplicationDifference applicationDifference){
        assert applicationDifference != null;
        return this.listApplicationDifference.remove(applicationDifference);
    }

/**
 *  add an item to the list for class ApplicationWarning
 *
*/

    public void addApplicationWarning(ApplicationWarning applicationWarning){
        assert applicationWarning != null;
        this.listApplicationWarning.add(applicationWarning);
    }

/**
 *  remove an item from the list for class ApplicationWarning
 *
*/

    public Boolean removeApplicationWarning(ApplicationWarning applicationWarning){
        assert applicationWarning != null;
        return this.listApplicationWarning.remove(applicationWarning);
    }

/**
 *  add an item to the list for class Scenario
 *
*/

    public void addScenario(Scenario scenario){
        assert scenario != null;
        this.listScenario.add(scenario);
    }

/**
 *  remove an item from the list for class Scenario
 *
*/

    public Boolean removeScenario(Scenario scenario){
        assert scenario != null;
        return this.listScenario.remove(scenario);
    }

/**
 *  add an item to the list for class AbstractSolverProperty
 *
*/

    public void addAbstractSolverProperty(AbstractSolverProperty abstractSolverProperty){
        assert abstractSolverProperty != null;
        this.listAbstractSolverProperty.add(abstractSolverProperty);
    }

/**
 *  remove an item from the list for class AbstractSolverProperty
 *
*/

    public Boolean removeAbstractSolverProperty(AbstractSolverProperty abstractSolverProperty){
        assert abstractSolverProperty != null;
        return this.listAbstractSolverProperty.remove(abstractSolverProperty);
    }

/**
 *  add an item to the list for class SolverProperty
 *
*/

    public void addSolverProperty(SolverProperty solverProperty){
        assert solverProperty != null;
        this.listSolverProperty.add(solverProperty);
    }

/**
 *  remove an item from the list for class SolverProperty
 *
*/

    public Boolean removeSolverProperty(SolverProperty solverProperty){
        assert solverProperty != null;
        return this.listSolverProperty.remove(solverProperty);
    }

/**
 *  add an item to the list for class SolverRun
 *
*/

    public void addSolverRun(SolverRun solverRun){
        assert solverRun != null;
        this.listSolverRun.add(solverRun);
    }

/**
 *  remove an item from the list for class SolverRun
 *
*/

    public Boolean removeSolverRun(SolverRun solverRun){
        assert solverRun != null;
        return this.listSolverRun.remove(solverRun);
    }

/**
 *  add an item to the list for class AbstractDataGeneratorProperty
 *
*/

    public void addAbstractDataGeneratorProperty(AbstractDataGeneratorProperty abstractDataGeneratorProperty){
        assert abstractDataGeneratorProperty != null;
        this.listAbstractDataGeneratorProperty.add(abstractDataGeneratorProperty);
    }

/**
 *  remove an item from the list for class AbstractDataGeneratorProperty
 *
*/

    public Boolean removeAbstractDataGeneratorProperty(AbstractDataGeneratorProperty abstractDataGeneratorProperty){
        assert abstractDataGeneratorProperty != null;
        return this.listAbstractDataGeneratorProperty.remove(abstractDataGeneratorProperty);
    }

/**
 *  add an item to the list for class DataGeneratorProperty
 *
*/

    public void addDataGeneratorProperty(DataGeneratorProperty dataGeneratorProperty){
        assert dataGeneratorProperty != null;
        this.listDataGeneratorProperty.add(dataGeneratorProperty);
    }

/**
 *  remove an item from the list for class DataGeneratorProperty
 *
*/

    public Boolean removeDataGeneratorProperty(DataGeneratorProperty dataGeneratorProperty){
        assert dataGeneratorProperty != null;
        return this.listDataGeneratorProperty.remove(dataGeneratorProperty);
    }

/**
 *  add an item to the list for class DataGeneratorRun
 *
*/

    public void addDataGeneratorRun(DataGeneratorRun dataGeneratorRun){
        assert dataGeneratorRun != null;
        this.listDataGeneratorRun.add(dataGeneratorRun);
    }

/**
 *  remove an item from the list for class DataGeneratorRun
 *
*/

    public Boolean removeDataGeneratorRun(DataGeneratorRun dataGeneratorRun){
        assert dataGeneratorRun != null;
        return this.listDataGeneratorRun.remove(dataGeneratorRun);
    }

/**
 *  add an item to the list for class InputError
 *
*/

    public void addInputError(InputError inputError){
        assert inputError != null;
        this.listInputError.add(inputError);
    }

/**
 *  remove an item from the list for class InputError
 *
*/

    public Boolean removeInputError(InputError inputError){
        assert inputError != null;
        return this.listInputError.remove(inputError);
    }

/**
 *  add an item to the list for class Problem
 *
*/

    public void addProblem(Problem problem){
        assert problem != null;
        this.listProblem.add(problem);
    }

/**
 *  remove an item from the list for class Problem
 *
*/

    public Boolean removeProblem(Problem problem){
        assert problem != null;
        return this.listProblem.remove(problem);
    }

/**
 *  add an item to the list for class Product
 *
*/

    public void addProduct(Product product){
        assert product != null;
        this.listProduct.add(product);
    }

/**
 *  remove an item from the list for class Product
 *
*/

    public Boolean removeProduct(Product product){
        assert product != null;
        return this.listProduct.remove(product);
    }

/**
 *  add an item to the list for class Process
 *
*/

    public void addProcess(Process process){
        assert process != null;
        this.listProcess.add(process);
    }

/**
 *  remove an item from the list for class Process
 *
*/

    public Boolean removeProcess(Process process){
        assert process != null;
        return this.listProcess.remove(process);
    }

/**
 *  add an item to the list for class ProcessStep
 *
*/

    public void addProcessStep(ProcessStep processStep){
        assert processStep != null;
        this.listProcessStep.add(processStep);
    }

/**
 *  remove an item from the list for class ProcessStep
 *
*/

    public Boolean removeProcessStep(ProcessStep processStep){
        assert processStep != null;
        return this.listProcessStep.remove(processStep);
    }

/**
 *  add an item to the list for class ProcessSequence
 *
*/

    public void addProcessSequence(ProcessSequence processSequence){
        assert processSequence != null;
        this.listProcessSequence.add(processSequence);
    }

/**
 *  remove an item from the list for class ProcessSequence
 *
*/

    public Boolean removeProcessSequence(ProcessSequence processSequence){
        assert processSequence != null;
        return this.listProcessSequence.remove(processSequence);
    }

/**
 *  add an item to the list for class ResourceNeed
 *
*/

    public void addResourceNeed(ResourceNeed resourceNeed){
        assert resourceNeed != null;
        this.listResourceNeed.add(resourceNeed);
    }

/**
 *  remove an item from the list for class ResourceNeed
 *
*/

    public Boolean removeResourceNeed(ResourceNeed resourceNeed){
        assert resourceNeed != null;
        return this.listResourceNeed.remove(resourceNeed);
    }

/**
 *  add an item to the list for class CumulativeNeed
 *
*/

    public void addCumulativeNeed(CumulativeNeed cumulativeNeed){
        assert cumulativeNeed != null;
        this.listCumulativeNeed.add(cumulativeNeed);
    }

/**
 *  remove an item from the list for class CumulativeNeed
 *
*/

    public Boolean removeCumulativeNeed(CumulativeNeed cumulativeNeed){
        assert cumulativeNeed != null;
        return this.listCumulativeNeed.remove(cumulativeNeed);
    }

/**
 *  add an item to the list for class CumulativeProfile
 *
*/

    public void addCumulativeProfile(CumulativeProfile cumulativeProfile){
        assert cumulativeProfile != null;
        this.listCumulativeProfile.add(cumulativeProfile);
    }

/**
 *  remove an item from the list for class CumulativeProfile
 *
*/

    public Boolean removeCumulativeProfile(CumulativeProfile cumulativeProfile){
        assert cumulativeProfile != null;
        return this.listCumulativeProfile.remove(cumulativeProfile);
    }

/**
 *  add an item to the list for class DisjunctiveResource
 *
*/

    public void addDisjunctiveResource(DisjunctiveResource disjunctiveResource){
        assert disjunctiveResource != null;
        this.listDisjunctiveResource.add(disjunctiveResource);
    }

/**
 *  remove an item from the list for class DisjunctiveResource
 *
*/

    public Boolean removeDisjunctiveResource(DisjunctiveResource disjunctiveResource){
        assert disjunctiveResource != null;
        return this.listDisjunctiveResource.remove(disjunctiveResource);
    }

/**
 *  add an item to the list for class CumulativeResource
 *
*/

    public void addCumulativeResource(CumulativeResource cumulativeResource){
        assert cumulativeResource != null;
        this.listCumulativeResource.add(cumulativeResource);
    }

/**
 *  remove an item from the list for class CumulativeResource
 *
*/

    public Boolean removeCumulativeResource(CumulativeResource cumulativeResource){
        assert cumulativeResource != null;
        return this.listCumulativeResource.remove(cumulativeResource);
    }

/**
 *  add an item to the list for class ResourceActivity
 *
*/

    public void addResourceActivity(ResourceActivity resourceActivity){
        assert resourceActivity != null;
        this.listResourceActivity.add(resourceActivity);
    }

/**
 *  remove an item from the list for class ResourceActivity
 *
*/

    public Boolean removeResourceActivity(ResourceActivity resourceActivity){
        assert resourceActivity != null;
        return this.listResourceActivity.remove(resourceActivity);
    }

/**
 *  add an item to the list for class Order
 *
*/

    public void addOrder(Order order){
        assert order != null;
        this.listOrder.add(order);
    }

/**
 *  remove an item from the list for class Order
 *
*/

    public Boolean removeOrder(Order order){
        assert order != null;
        return this.listOrder.remove(order);
    }

/**
 *  add an item to the list for class Job
 *
*/

    public void addJob(Job job){
        assert job != null;
        this.listJob.add(job);
    }

/**
 *  remove an item from the list for class Job
 *
*/

    public Boolean removeJob(Job job){
        assert job != null;
        return this.listJob.remove(job);
    }

/**
 *  add an item to the list for class Task
 *
*/

    public void addTask(Task task){
        assert task != null;
        this.listTask.add(task);
    }

/**
 *  remove an item from the list for class Task
 *
*/

    public Boolean removeTask(Task task){
        assert task != null;
        return this.listTask.remove(task);
    }

/**
 *  add an item to the list for class WiP
 *
*/

    public void addWiP(WiP wiP){
        assert wiP != null;
        this.listWiP.add(wiP);
    }

/**
 *  remove an item from the list for class WiP
 *
*/

    public Boolean removeWiP(WiP wiP){
        assert wiP != null;
        return this.listWiP.remove(wiP);
    }

/**
 *  add an item to the list for class Downtime
 *
*/

    public void addDowntime(Downtime downtime){
        assert downtime != null;
        this.listDowntime.add(downtime);
    }

/**
 *  remove an item from the list for class Downtime
 *
*/

    public Boolean removeDowntime(Downtime downtime){
        assert downtime != null;
        return this.listDowntime.remove(downtime);
    }

/**
 *  add an item to the list for class Solution
 *
*/

    public void addSolution(Solution solution){
        assert solution != null;
        this.listSolution.add(solution);
    }

/**
 *  remove an item from the list for class Solution
 *
*/

    public Boolean removeSolution(Solution solution){
        assert solution != null;
        return this.listSolution.remove(solution);
    }

/**
 *  add an item to the list for class JobAssignment
 *
*/

    public void addJobAssignment(JobAssignment jobAssignment){
        assert jobAssignment != null;
        this.listJobAssignment.add(jobAssignment);
    }

/**
 *  remove an item from the list for class JobAssignment
 *
*/

    public Boolean removeJobAssignment(JobAssignment jobAssignment){
        assert jobAssignment != null;
        return this.listJobAssignment.remove(jobAssignment);
    }

/**
 *  add an item to the list for class TaskAssignment
 *
*/

    public void addTaskAssignment(TaskAssignment taskAssignment){
        assert taskAssignment != null;
        this.listTaskAssignment.add(taskAssignment);
    }

/**
 *  remove an item from the list for class TaskAssignment
 *
*/

    public Boolean removeTaskAssignment(TaskAssignment taskAssignment){
        assert taskAssignment != null;
        return this.listTaskAssignment.remove(taskAssignment);
    }

/**
 *  add an item to the list for class ResourceUtilization
 *
*/

    public void addResourceUtilization(ResourceUtilization resourceUtilization){
        assert resourceUtilization != null;
        this.listResourceUtilization.add(resourceUtilization);
    }

/**
 *  remove an item from the list for class ResourceUtilization
 *
*/

    public Boolean removeResourceUtilization(ResourceUtilization resourceUtilization){
        assert resourceUtilization != null;
        return this.listResourceUtilization.remove(resourceUtilization);
    }

/**
 *  add an item to the list for class IntermediateSolution
 *
*/

    public void addIntermediateSolution(IntermediateSolution intermediateSolution){
        assert intermediateSolution != null;
        this.listIntermediateSolution.add(intermediateSolution);
    }

/**
 *  remove an item from the list for class IntermediateSolution
 *
*/

    public Boolean removeIntermediateSolution(IntermediateSolution intermediateSolution){
        assert intermediateSolution != null;
        return this.listIntermediateSolution.remove(intermediateSolution);
    }

/**
 *  add an item to the list for class SolutionError
 *
*/

    public void addSolutionError(SolutionError solutionError){
        assert solutionError != null;
        this.listSolutionError.add(solutionError);
    }

/**
 *  remove an item from the list for class SolutionError
 *
*/

    public Boolean removeSolutionError(SolutionError solutionError){
        assert solutionError != null;
        return this.listSolutionError.remove(solutionError);
    }

/**
 *  dump all items on the console for debugging
 *
*/

    public void dump(){
        for(ApplicationDifference x:getListApplicationDifference()){
            System.out.println(x);
        }
        for(ApplicationWarning x:getListApplicationWarning()){
            System.out.println(x);
        }
        for(CumulativeNeed x:getListCumulativeNeed()){
            System.out.println(x);
        }
        for(CumulativeProfile x:getListCumulativeProfile()){
            System.out.println(x);
        }
        for(CumulativeResource x:getListCumulativeResource()){
            System.out.println(x);
        }
        for(DataGeneratorProperty x:getListDataGeneratorProperty()){
            System.out.println(x);
        }
        for(DataGeneratorRun x:getListDataGeneratorRun()){
            System.out.println(x);
        }
        for(DisjunctiveResource x:getListDisjunctiveResource()){
            System.out.println(x);
        }
        for(Downtime x:getListDowntime()){
            System.out.println(x);
        }
        for(InputError x:getListInputError()){
            System.out.println(x);
        }
        for(IntermediateSolution x:getListIntermediateSolution()){
            System.out.println(x);
        }
        for(Job x:getListJob()){
            System.out.println(x);
        }
        for(JobAssignment x:getListJobAssignment()){
            System.out.println(x);
        }
        for(Order x:getListOrder()){
            System.out.println(x);
        }
        for(Problem x:getListProblem()){
            System.out.println(x);
        }
        for(Process x:getListProcess()){
            System.out.println(x);
        }
        for(ProcessSequence x:getListProcessSequence()){
            System.out.println(x);
        }
        for(ProcessStep x:getListProcessStep()){
            System.out.println(x);
        }
        for(Product x:getListProduct()){
            System.out.println(x);
        }
        for(ResourceNeed x:getListResourceNeed()){
            System.out.println(x);
        }
        for(ResourceUtilization x:getListResourceUtilization()){
            System.out.println(x);
        }
        for(Scenario x:getListScenario()){
            System.out.println(x);
        }
        for(Solution x:getListSolution()){
            System.out.println(x);
        }
        for(SolutionError x:getListSolutionError()){
            System.out.println(x);
        }
        for(SolverProperty x:getListSolverProperty()){
            System.out.println(x);
        }
        for(SolverRun x:getListSolverRun()){
            System.out.println(x);
        }
        for(Task x:getListTask()){
            System.out.println(x);
        }
        for(TaskAssignment x:getListTaskAssignment()){
            System.out.println(x);
        }
        for(WiP x:getListWiP()){
            System.out.println(x);
        }
    }

    public String getReport(String settings){
        StringWriter out = new StringWriter();
        out.write("<html><head><title>Report</title></head><body>");
        out.write("</body></html>");
        return out.toString();
    }

    public String safeXML(String s){
        String res=s;
        res = res.replaceAll("&","&amp;");
        res = res.replaceAll("<","&lt;");
        res = res.replaceAll(">","&gt;");
        res = res.replaceAll("\"","&quot;");
        res = res.replaceAll("'","&apos;");
        return res;
    }
/**
 *  dump all items in XML format to a file
 *
*/

    public void dumpAsXML(String fileName){
        PrintWriter out=null;
        try {
    	      File f = new File(fileName);
    	      out = new PrintWriter(new FileWriter(f));
    	  } catch (IOException e) {
    	      System.out.println("Can not create file: "+fileName);
    	  }
        out.println("<?xml version=\"1.0\" ?>");
        out.println("<body xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"test.xsd\">");
        for(Scenario x:getListScenario()){
            if (x.getClass().equals(Scenario.class)) x.toXML(out);
        }
        for(ApplicationDifference x:getListApplicationDifference()){
            if (x.getClass().equals(ApplicationDifference.class)) x.toXML(out);
        }
        for(ApplicationWarning x:getListApplicationWarning()){
            if (x.getClass().equals(ApplicationWarning.class)) x.toXML(out);
        }
        for(CumulativeNeed x:getListCumulativeNeed()){
            if (x.getClass().equals(CumulativeNeed.class)) x.toXML(out);
        }
        for(CumulativeProfile x:getListCumulativeProfile()){
            if (x.getClass().equals(CumulativeProfile.class)) x.toXML(out);
        }
        for(CumulativeResource x:getListCumulativeResource()){
            if (x.getClass().equals(CumulativeResource.class)) x.toXML(out);
        }
        for(DataGeneratorProperty x:getListDataGeneratorProperty()){
            if (x.getClass().equals(DataGeneratorProperty.class)) x.toXML(out);
        }
        for(DataGeneratorRun x:getListDataGeneratorRun()){
            if (x.getClass().equals(DataGeneratorRun.class)) x.toXML(out);
        }
        for(DisjunctiveResource x:getListDisjunctiveResource()){
            if (x.getClass().equals(DisjunctiveResource.class)) x.toXML(out);
        }
        for(Downtime x:getListDowntime()){
            if (x.getClass().equals(Downtime.class)) x.toXML(out);
        }
        for(InputError x:getListInputError()){
            if (x.getClass().equals(InputError.class)) x.toXML(out);
        }
        for(IntermediateSolution x:getListIntermediateSolution()){
            if (x.getClass().equals(IntermediateSolution.class)) x.toXML(out);
        }
        for(Job x:getListJob()){
            if (x.getClass().equals(Job.class)) x.toXML(out);
        }
        for(JobAssignment x:getListJobAssignment()){
            if (x.getClass().equals(JobAssignment.class)) x.toXML(out);
        }
        for(Order x:getListOrder()){
            if (x.getClass().equals(Order.class)) x.toXML(out);
        }
        for(Problem x:getListProblem()){
            if (x.getClass().equals(Problem.class)) x.toXML(out);
        }
        for(Process x:getListProcess()){
            if (x.getClass().equals(Process.class)) x.toXML(out);
        }
        for(ProcessSequence x:getListProcessSequence()){
            if (x.getClass().equals(ProcessSequence.class)) x.toXML(out);
        }
        for(ProcessStep x:getListProcessStep()){
            if (x.getClass().equals(ProcessStep.class)) x.toXML(out);
        }
        for(Product x:getListProduct()){
            if (x.getClass().equals(Product.class)) x.toXML(out);
        }
        for(ResourceNeed x:getListResourceNeed()){
            if (x.getClass().equals(ResourceNeed.class)) x.toXML(out);
        }
        for(ResourceUtilization x:getListResourceUtilization()){
            if (x.getClass().equals(ResourceUtilization.class)) x.toXML(out);
        }
        for(Solution x:getListSolution()){
            if (x.getClass().equals(Solution.class)) x.toXML(out);
        }
        for(SolutionError x:getListSolutionError()){
            if (x.getClass().equals(SolutionError.class)) x.toXML(out);
        }
        for(SolverProperty x:getListSolverProperty()){
            if (x.getClass().equals(SolverProperty.class)) x.toXML(out);
        }
        for(SolverRun x:getListSolverRun()){
            if (x.getClass().equals(SolverRun.class)) x.toXML(out);
        }
        for(Task x:getListTask()){
            if (x.getClass().equals(Task.class)) x.toXML(out);
        }
        for(TaskAssignment x:getListTaskAssignment()){
            if (x.getClass().equals(TaskAssignment.class)) x.toXML(out);
        }
        for(WiP x:getListWiP()){
            if (x.getClass().equals(WiP.class)) x.toXML(out);
        }
        out.println("</body>");
        out.close();
    }

/**
 *  override generic toString() method, show all attributes in human readable form
 * @return String details of the format are not clearly defined at the moment
*/

    @Override
    public String toString(){
        return toColumnString();
    }

/**
 *  alternative to the toString() method, experimental at this point
 *  This should be easier to read than toString(), but contain more information than toColumnString()
 * @return String human readable
*/

    public String prettyString(){
        return getDirty()+ " " +getId()+ " " +getName()+ " " +getValid();
    }

/**
 *  alternative to the toString() method, used in the table views
 *  this only shows enough fields to identify the object
 *  Normally this is the name attribute, but this can be changed by the display_key fields
 * @return String normally name or other fields defned in display_key
*/

    public String toColumnString(){
        return getName();
    }

/**
 * show object as one element in XML format
 * side effect of writing to file
 * @param out PrintWriter
*/

     public void toXML(PrintWriter out){
         out.println("<applicationDataset "+ " dirty=\""+toXMLDirty()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " valid=\""+toXMLValid()+"\""+" />");
     }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLDirty(){
        return this.getDirty().toString();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLId(){
        return "ID_"+this.getId();
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLName(){
        return this.safeXML(getName());
    }

/**
 * helper method for toXML(), prcess one attribute
 * probably useless on its own
 * @return String
*/

    String toXMLValid(){
        return this.getValid().toString();
    }

/**
 * compare object to another of the same type, create ApplicationWarnings if they differ
*/

    public void compare(ApplicationDatasetInterface c){
        ApplicationDataset compare = (ApplicationDataset) c;
        System.out.println("Comparing ApplicationDataset");
        compareApplicationWarning(this.getListApplicationWarning(),compare.getListApplicationWarning());
        compareCumulativeNeed(this.getListCumulativeNeed(),compare.getListCumulativeNeed());
        compareCumulativeProfile(this.getListCumulativeProfile(),compare.getListCumulativeProfile());
        compareCumulativeResource(this.getListCumulativeResource(),compare.getListCumulativeResource());
        compareDataGeneratorProperty(this.getListDataGeneratorProperty(),compare.getListDataGeneratorProperty());
        compareDataGeneratorRun(this.getListDataGeneratorRun(),compare.getListDataGeneratorRun());
        compareDisjunctiveResource(this.getListDisjunctiveResource(),compare.getListDisjunctiveResource());
        compareDowntime(this.getListDowntime(),compare.getListDowntime());
        compareInputError(this.getListInputError(),compare.getListInputError());
        compareIntermediateSolution(this.getListIntermediateSolution(),compare.getListIntermediateSolution());
        compareJob(this.getListJob(),compare.getListJob());
        compareJobAssignment(this.getListJobAssignment(),compare.getListJobAssignment());
        compareOrder(this.getListOrder(),compare.getListOrder());
        compareProblem(this.getListProblem(),compare.getListProblem());
        compareProcess(this.getListProcess(),compare.getListProcess());
        compareProcessSequence(this.getListProcessSequence(),compare.getListProcessSequence());
        compareProcessStep(this.getListProcessStep(),compare.getListProcessStep());
        compareProduct(this.getListProduct(),compare.getListProduct());
        compareResourceNeed(this.getListResourceNeed(),compare.getListResourceNeed());
        compareResourceUtilization(this.getListResourceUtilization(),compare.getListResourceUtilization());
        compareSolution(this.getListSolution(),compare.getListSolution());
        compareSolutionError(this.getListSolutionError(),compare.getListSolutionError());
        compareSolverProperty(this.getListSolverProperty(),compare.getListSolverProperty());
        compareSolverRun(this.getListSolverRun(),compare.getListSolverRun());
        compareTask(this.getListTask(),compare.getListTask());
        compareTaskAssignment(this.getListTaskAssignment(),compare.getListTaskAssignment());
        compareWiP(this.getListWiP(),compare.getListWiP());
        System.out.println("Done Comparing ApplicationDataset");
    }

/**
 * compare two lists of types ApplicationWarning, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareApplicationWarning(List<ApplicationWarning> aList,List<ApplicationWarning> bList){
        System.out.println("Comparing ApplicationWarning");
        for(ApplicationWarning a:aList){
            ApplicationWarning b= ApplicationWarning.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ApplicationWarning A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ApplicationWarning A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ApplicationWarning B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(ApplicationWarning b: bList){
            ApplicationWarning a = ApplicationWarning.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ApplicationWarning B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types CumulativeNeed, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareCumulativeNeed(List<CumulativeNeed> aList,List<CumulativeNeed> bList){
        System.out.println("Comparing CumulativeNeed");
        for(CumulativeNeed a:aList){
            CumulativeNeed b= CumulativeNeed.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"CumulativeNeed A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"CumulativeNeed A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"CumulativeNeed B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(CumulativeNeed b: bList){
            CumulativeNeed a = CumulativeNeed.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"CumulativeNeed B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types CumulativeProfile, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareCumulativeProfile(List<CumulativeProfile> aList,List<CumulativeProfile> bList){
        System.out.println("Comparing CumulativeProfile");
        for(CumulativeProfile a:aList){
            CumulativeProfile b= CumulativeProfile.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"CumulativeProfile A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"CumulativeProfile A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"CumulativeProfile B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(CumulativeProfile b: bList){
            CumulativeProfile a = CumulativeProfile.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"CumulativeProfile B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types CumulativeResource, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareCumulativeResource(List<CumulativeResource> aList,List<CumulativeResource> bList){
        System.out.println("Comparing CumulativeResource");
        for(CumulativeResource a:aList){
            CumulativeResource b= CumulativeResource.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"CumulativeResource A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"CumulativeResource A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"CumulativeResource B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(CumulativeResource b: bList){
            CumulativeResource a = CumulativeResource.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"CumulativeResource B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types DataGeneratorProperty, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareDataGeneratorProperty(List<DataGeneratorProperty> aList,List<DataGeneratorProperty> bList){
        System.out.println("Comparing DataGeneratorProperty");
        for(DataGeneratorProperty a:aList){
            DataGeneratorProperty b= DataGeneratorProperty.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"DataGeneratorProperty A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"DataGeneratorProperty A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"DataGeneratorProperty B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(DataGeneratorProperty b: bList){
            DataGeneratorProperty a = DataGeneratorProperty.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"DataGeneratorProperty B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types DataGeneratorRun, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareDataGeneratorRun(List<DataGeneratorRun> aList,List<DataGeneratorRun> bList){
        System.out.println("Comparing DataGeneratorRun");
        for(DataGeneratorRun a:aList){
            DataGeneratorRun b= DataGeneratorRun.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"DataGeneratorRun A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"DataGeneratorRun A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"DataGeneratorRun B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(DataGeneratorRun b: bList){
            DataGeneratorRun a = DataGeneratorRun.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"DataGeneratorRun B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types DisjunctiveResource, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareDisjunctiveResource(List<DisjunctiveResource> aList,List<DisjunctiveResource> bList){
        System.out.println("Comparing DisjunctiveResource");
        for(DisjunctiveResource a:aList){
            DisjunctiveResource b= DisjunctiveResource.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"DisjunctiveResource A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"DisjunctiveResource A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"DisjunctiveResource B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(DisjunctiveResource b: bList){
            DisjunctiveResource a = DisjunctiveResource.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"DisjunctiveResource B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types Downtime, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareDowntime(List<Downtime> aList,List<Downtime> bList){
        System.out.println("Comparing Downtime");
        for(Downtime a:aList){
            Downtime b= Downtime.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Downtime A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Downtime A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Downtime B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(Downtime b: bList){
            Downtime a = Downtime.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Downtime B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types InputError, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareInputError(List<InputError> aList,List<InputError> bList){
        System.out.println("Comparing InputError");
        for(InputError a:aList){
            InputError b= InputError.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"InputError A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"InputError A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"InputError B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(InputError b: bList){
            InputError a = InputError.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"InputError B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types IntermediateSolution, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareIntermediateSolution(List<IntermediateSolution> aList,List<IntermediateSolution> bList){
        System.out.println("Comparing IntermediateSolution");
        for(IntermediateSolution a:aList){
            IntermediateSolution b= IntermediateSolution.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"IntermediateSolution A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"IntermediateSolution A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"IntermediateSolution B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(IntermediateSolution b: bList){
            IntermediateSolution a = IntermediateSolution.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"IntermediateSolution B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types Job, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareJob(List<Job> aList,List<Job> bList){
        System.out.println("Comparing Job");
        for(Job a:aList){
            Job b= Job.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Job A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Job A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Job B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(Job b: bList){
            Job a = Job.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Job B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types JobAssignment, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareJobAssignment(List<JobAssignment> aList,List<JobAssignment> bList){
        System.out.println("Comparing JobAssignment");
        for(JobAssignment a:aList){
            JobAssignment b= JobAssignment.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"JobAssignment A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"JobAssignment A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"JobAssignment B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(JobAssignment b: bList){
            JobAssignment a = JobAssignment.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"JobAssignment B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types Order, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareOrder(List<Order> aList,List<Order> bList){
        System.out.println("Comparing Order");
        for(Order a:aList){
            Order b= Order.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Order A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Order A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Order B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(Order b: bList){
            Order a = Order.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Order B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types Problem, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareProblem(List<Problem> aList,List<Problem> bList){
        System.out.println("Comparing Problem");
        for(Problem a:aList){
            Problem b= Problem.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Problem A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Problem A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Problem B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(Problem b: bList){
            Problem a = Problem.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Problem B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types Process, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareProcess(List<Process> aList,List<Process> bList){
        System.out.println("Comparing Process");
        for(Process a:aList){
            Process b= Process.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Process A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Process A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Process B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(Process b: bList){
            Process a = Process.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Process B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types ProcessSequence, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareProcessSequence(List<ProcessSequence> aList,List<ProcessSequence> bList){
        System.out.println("Comparing ProcessSequence");
        for(ProcessSequence a:aList){
            ProcessSequence b= ProcessSequence.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ProcessSequence A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ProcessSequence A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ProcessSequence B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(ProcessSequence b: bList){
            ProcessSequence a = ProcessSequence.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ProcessSequence B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types ProcessStep, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareProcessStep(List<ProcessStep> aList,List<ProcessStep> bList){
        System.out.println("Comparing ProcessStep");
        for(ProcessStep a:aList){
            ProcessStep b= ProcessStep.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ProcessStep A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ProcessStep A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ProcessStep B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(ProcessStep b: bList){
            ProcessStep a = ProcessStep.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ProcessStep B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types Product, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareProduct(List<Product> aList,List<Product> bList){
        System.out.println("Comparing Product");
        for(Product a:aList){
            Product b= Product.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Product A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Product A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Product B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(Product b: bList){
            Product a = Product.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Product B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types ResourceNeed, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareResourceNeed(List<ResourceNeed> aList,List<ResourceNeed> bList){
        System.out.println("Comparing ResourceNeed");
        for(ResourceNeed a:aList){
            ResourceNeed b= ResourceNeed.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ResourceNeed A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ResourceNeed A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ResourceNeed B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(ResourceNeed b: bList){
            ResourceNeed a = ResourceNeed.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ResourceNeed B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types ResourceUtilization, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareResourceUtilization(List<ResourceUtilization> aList,List<ResourceUtilization> bList){
        System.out.println("Comparing ResourceUtilization");
        for(ResourceUtilization a:aList){
            ResourceUtilization b= ResourceUtilization.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ResourceUtilization A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ResourceUtilization A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ResourceUtilization B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(ResourceUtilization b: bList){
            ResourceUtilization a = ResourceUtilization.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"ResourceUtilization B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types Solution, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareSolution(List<Solution> aList,List<Solution> bList){
        System.out.println("Comparing Solution");
        for(Solution a:aList){
            Solution b= Solution.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Solution A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Solution A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Solution B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(Solution b: bList){
            Solution a = Solution.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Solution B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types SolutionError, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareSolutionError(List<SolutionError> aList,List<SolutionError> bList){
        System.out.println("Comparing SolutionError");
        for(SolutionError a:aList){
            SolutionError b= SolutionError.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"SolutionError A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"SolutionError A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"SolutionError B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(SolutionError b: bList){
            SolutionError a = SolutionError.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"SolutionError B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types SolverProperty, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareSolverProperty(List<SolverProperty> aList,List<SolverProperty> bList){
        System.out.println("Comparing SolverProperty");
        for(SolverProperty a:aList){
            SolverProperty b= SolverProperty.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"SolverProperty A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"SolverProperty A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"SolverProperty B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(SolverProperty b: bList){
            SolverProperty a = SolverProperty.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"SolverProperty B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types SolverRun, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareSolverRun(List<SolverRun> aList,List<SolverRun> bList){
        System.out.println("Comparing SolverRun");
        for(SolverRun a:aList){
            SolverRun b= SolverRun.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"SolverRun A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"SolverRun A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"SolverRun B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(SolverRun b: bList){
            SolverRun a = SolverRun.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"SolverRun B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types Task, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareTask(List<Task> aList,List<Task> bList){
        System.out.println("Comparing Task");
        for(Task a:aList){
            Task b= Task.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Task A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Task A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Task B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(Task b: bList){
            Task a = Task.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Task B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types TaskAssignment, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareTaskAssignment(List<TaskAssignment> aList,List<TaskAssignment> bList){
        System.out.println("Comparing TaskAssignment");
        for(TaskAssignment a:aList){
            TaskAssignment b= TaskAssignment.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"TaskAssignment A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"TaskAssignment A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"TaskAssignment B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(TaskAssignment b: bList){
            TaskAssignment a = TaskAssignment.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"TaskAssignment B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * compare two lists of types WiP, create AppplicationWarnings for items which are in only one of the lists
 * or for items which are applicationSame(), but not applicationEqual()
*/

    public void compareWiP(List<WiP> aList,List<WiP> bList){
        System.out.println("Comparing WiP");
        for(WiP a:aList){
            WiP b= WiP.find(a,bList);
            if (b == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"WiP A",a.prettyString(),DifferenceType.ONLYA);
            } else if (!a.applicationEqual(b)){
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"WiP A",a.prettyString(),DifferenceType.DIFFERA);
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"WiP B",b.prettyString(),DifferenceType.DIFFERB);
            }
        }
        for(WiP b: bList){
            WiP a = WiP.find(b,aList);
            if (a == null) {
                new ApplicationDifference(this,ApplicationDataset.getIdNr(),"WiP B",b.toString(),DifferenceType.ONLYB);
            }
        }
    }

/**
 * check all objects in dataset for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void checkAll(){
        checkApplicationWarning(this.getListApplicationWarning());
        checkCumulativeNeed(this.getListCumulativeNeed());
        checkCumulativeProfile(this.getListCumulativeProfile());
        checkCumulativeResource(this.getListCumulativeResource());
        checkDataGeneratorProperty(this.getListDataGeneratorProperty());
        checkDataGeneratorRun(this.getListDataGeneratorRun());
        checkDisjunctiveResource(this.getListDisjunctiveResource());
        checkDowntime(this.getListDowntime());
        checkInputError(this.getListInputError());
        checkIntermediateSolution(this.getListIntermediateSolution());
        checkJob(this.getListJob());
        checkJobAssignment(this.getListJobAssignment());
        checkOrder(this.getListOrder());
        checkProblem(this.getListProblem());
        checkProcess(this.getListProcess());
        checkProcessSequence(this.getListProcessSequence());
        checkProcessStep(this.getListProcessStep());
        checkProduct(this.getListProduct());
        checkResourceNeed(this.getListResourceNeed());
        checkResourceUtilization(this.getListResourceUtilization());
        checkScenario(this.getListScenario());
        checkSolution(this.getListSolution());
        checkSolutionError(this.getListSolutionError());
        checkSolverProperty(this.getListSolverProperty());
        checkSolverRun(this.getListSolverRun());
        checkTask(this.getListTask());
        checkTaskAssignment(this.getListTaskAssignment());
        checkWiP(this.getListWiP());
    }

/**
 * helper method for checkAll()
 * @param list List<ApplicationWarning> dataset list of all items of type ApplicationWarning
*/

    public void checkApplicationWarning(List<ApplicationWarning> list){
        for(ApplicationWarning a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<CumulativeNeed> dataset list of all items of type CumulativeNeed
*/

    public void checkCumulativeNeed(List<CumulativeNeed> list){
        for(CumulativeNeed a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<CumulativeProfile> dataset list of all items of type CumulativeProfile
*/

    public void checkCumulativeProfile(List<CumulativeProfile> list){
        for(CumulativeProfile a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<CumulativeResource> dataset list of all items of type CumulativeResource
*/

    public void checkCumulativeResource(List<CumulativeResource> list){
        for(CumulativeResource a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<DataGeneratorProperty> dataset list of all items of type DataGeneratorProperty
*/

    public void checkDataGeneratorProperty(List<DataGeneratorProperty> list){
        for(DataGeneratorProperty a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<DataGeneratorRun> dataset list of all items of type DataGeneratorRun
*/

    public void checkDataGeneratorRun(List<DataGeneratorRun> list){
        for(DataGeneratorRun a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<DisjunctiveResource> dataset list of all items of type DisjunctiveResource
*/

    public void checkDisjunctiveResource(List<DisjunctiveResource> list){
        for(DisjunctiveResource a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<Downtime> dataset list of all items of type Downtime
*/

    public void checkDowntime(List<Downtime> list){
        for(Downtime a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<InputError> dataset list of all items of type InputError
*/

    public void checkInputError(List<InputError> list){
        for(InputError a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<IntermediateSolution> dataset list of all items of type IntermediateSolution
*/

    public void checkIntermediateSolution(List<IntermediateSolution> list){
        for(IntermediateSolution a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<Job> dataset list of all items of type Job
*/

    public void checkJob(List<Job> list){
        for(Job a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<JobAssignment> dataset list of all items of type JobAssignment
*/

    public void checkJobAssignment(List<JobAssignment> list){
        for(JobAssignment a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<Order> dataset list of all items of type Order
*/

    public void checkOrder(List<Order> list){
        for(Order a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<Problem> dataset list of all items of type Problem
*/

    public void checkProblem(List<Problem> list){
        for(Problem a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<Process> dataset list of all items of type Process
*/

    public void checkProcess(List<Process> list){
        for(Process a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<ProcessSequence> dataset list of all items of type ProcessSequence
*/

    public void checkProcessSequence(List<ProcessSequence> list){
        for(ProcessSequence a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<ProcessStep> dataset list of all items of type ProcessStep
*/

    public void checkProcessStep(List<ProcessStep> list){
        for(ProcessStep a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<Product> dataset list of all items of type Product
*/

    public void checkProduct(List<Product> list){
        for(Product a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<ResourceNeed> dataset list of all items of type ResourceNeed
*/

    public void checkResourceNeed(List<ResourceNeed> list){
        for(ResourceNeed a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<ResourceUtilization> dataset list of all items of type ResourceUtilization
*/

    public void checkResourceUtilization(List<ResourceUtilization> list){
        for(ResourceUtilization a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<Scenario> dataset list of all items of type Scenario
*/

    public void checkScenario(List<Scenario> list){
        for(Scenario a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<Solution> dataset list of all items of type Solution
*/

    public void checkSolution(List<Solution> list){
        for(Solution a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<SolutionError> dataset list of all items of type SolutionError
*/

    public void checkSolutionError(List<SolutionError> list){
        for(SolutionError a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<SolverProperty> dataset list of all items of type SolverProperty
*/

    public void checkSolverProperty(List<SolverProperty> list){
        for(SolverProperty a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<SolverRun> dataset list of all items of type SolverRun
*/

    public void checkSolverRun(List<SolverRun> list){
        for(SolverRun a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<Task> dataset list of all items of type Task
*/

    public void checkTask(List<Task> list){
        for(Task a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<TaskAssignment> dataset list of all items of type TaskAssignment
*/

    public void checkTaskAssignment(List<TaskAssignment> list){
        for(TaskAssignment a:list){
            a.check();
        }
    }

/**
 * helper method for checkAll()
 * @param list List<WiP> dataset list of all items of type WiP
*/

    public void checkWiP(List<WiP> list){
        for(WiP a:list){
            a.check();
        }
    }

   public void generateDummies(){
        ApplicationDifference.dummy(this);
        ApplicationWarning.dummy(this);
        CumulativeNeed.dummy(this);
        CumulativeProfile.dummy(this);
        CumulativeResource.dummy(this);
        DataGeneratorProperty.dummy(this);
        DataGeneratorRun.dummy(this);
        DisjunctiveResource.dummy(this);
        Downtime.dummy(this);
        InputError.dummy(this);
        IntermediateSolution.dummy(this);
        Job.dummy(this);
        JobAssignment.dummy(this);
        Order.dummy(this);
        Problem.dummy(this);
        Process.dummy(this);
        ProcessSequence.dummy(this);
        ProcessStep.dummy(this);
        Product.dummy(this);
        ResourceNeed.dummy(this);
        ResourceUtilization.dummy(this);
        Scenario.dummy(this);
        Solution.dummy(this);
        SolutionError.dummy(this);
        SolverProperty.dummy(this);
        SolverRun.dummy(this);
        Task.dummy(this);
        TaskAssignment.dummy(this);
        WiP.dummy(this);
   }

/**
 *  This method states if the class depends on the solver.
 *
*/

    public static Boolean isSolverDependent(){
        return false;
    }

   public List<ApplicationObjectInterface> getFeasibleValues(ApplicationDatasetInterface base,String attrName){
      return null;
   }

}

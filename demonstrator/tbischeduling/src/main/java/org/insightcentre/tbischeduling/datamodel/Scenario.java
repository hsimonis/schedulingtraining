// licence details to be added
package org.insightcentre.tbischeduling.datamodel;
import org.insightcentre.tbischeduling.datamodel.ApplicationDataset;
import org.insightcentre.tbischeduling.datamodel.ApplicationObject;
import org.insightcentre.tbischeduling.datamodel.ApplicationDifference;
import org.insightcentre.tbischeduling.datamodel.ApplicationWarning;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.Problem;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.datamodel.ProcessStep;
import org.insightcentre.tbischeduling.datamodel.ProcessSequence;
import org.insightcentre.tbischeduling.datamodel.DisjunctiveResource;
import org.insightcentre.tbischeduling.datamodel.CumulativeResource;
import org.insightcentre.tbischeduling.datamodel.ResourceNeed;
import org.insightcentre.tbischeduling.datamodel.Product;
import org.insightcentre.tbischeduling.datamodel.Order;
import org.insightcentre.tbischeduling.datamodel.Job;
import org.insightcentre.tbischeduling.datamodel.Task;
import org.insightcentre.tbischeduling.datamodel.Solution;
import org.insightcentre.tbischeduling.datamodel.TaskAssignment;
import org.insightcentre.tbischeduling.datamodel.JobAssignment;
import org.insightcentre.tbischeduling.datamodel.DifferenceType;
import org.insightcentre.tbischeduling.datamodel.WarningType;
import org.insightcentre.tbischeduling.datamodel.SequenceType;
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
 * This class describes the dataset for the application. Typically there is a single instance of this class in the application at all times.
 * @author generated
*/

public  class Scenario extends ApplicationDataset{
/**
 *  No-arg constructor for use in TableView
 *
*/

    public Scenario(){
        super();
    }

/**
 *  Constructor for use in TableView
 *  only one argument: the dataset
 *  other fields are left to null or set to defaults
 *  adds object to the relevant lists in the dataset
 *
*/

    public Scenario(ApplicationDataset applicationDataset){
        super(applicationDataset);
        addScenario(this);
    }

/**
 *  General Constructor with all attributes given
 *  attributes from parent come first, others are sorted alphabetically
 *  adds object to the relevant lists in the dataset
 *
*/

    public Scenario(Boolean dirty,
            Integer id,
            String name,
            Boolean valid){
        super(dirty,
            id,
            name,
            valid);
        addScenario(this);
    }

    public Scenario(Scenario other){
        this(other.dirty,
            other.id,
            other.name,
            other.valid);
    }

    public Boolean remove(){
        // ignored, you can not remove a dataset like this
        return true;
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
         out.println("<scenario "+ " dirty=\""+toXMLDirty()+"\""+
            " id=\""+toXMLId()+"\""+
            " name=\""+toXMLName()+"\""+
            " valid=\""+toXMLValid()+"\""+" />");
     }

/**
 * show object as one row in an HTML table
 * 
 * @return String of form <tr>...</tr>
*/

    public static String toHTMLLabels(){
        return "<tr><th>Scenario</th>"+"<th>Name</th>"+"<th>Dirty</th>"+"<th>Valid</th>"+"</tr>";
    }

    public String toHTML(){
        return "<tr><th>&nbsp;</th>"+"<td>"+getName()+"</td>"+ " " +"<td>"+getDirty()+"</td>"+ " " +"<td>"+getValid()+"</td>"+"</tr>";
    }

/**
 * compare object to another of the same type, create ApplicationWarnings if they differ
*/

    public void compare(Scenario compare){
        System.out.println("Calling compare super");
        super.compare(compare);
        System.out.println("Comparing Scenario");
        if (!this.applicationEqual(compare)) {
            System.out.println("Scenarios differ:"+this+" "+compare);
            new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Scenario A",this.toString(),DifferenceType.DIFFERA);
            new ApplicationDifference(this,ApplicationDataset.getIdNr(),"Scenario B",compare.toString(),DifferenceType.DIFFERB);
        }
        System.out.println("Done Compare");
    }

/**
 * check if two objects (typically in different datasets) refer to the same real-world item
 * often this means that the names match, depending on the display_key
 * @param b Scenario compare this to that object
 * @return Boolean true if the objects match the same criteria
*/

    public Boolean applicationSame(Scenario b){
        return this.getName().equals(b.getName());
    }

/**
 * check if two objects (typically in different datasets) are equal, i.e. have the same field values
 * typically used to check if an item is different in two datasets
 * this is quite different from the equals() method, which checks if the objects are idenitcal
 * @param b Scenario compare this to that object
 * @return Boolean true if the objects match the equal criteria
*/

    public Boolean applicationEqual(Scenario b){
      if(!this.getName().equals(b.getName())){
         System.out.println("Name");
        }
        return  this.getName().equals(b.getName());
    }

/**
 * check an object for internal consistency, based on multiplicity
 * and restrictions; create applicationWarning if inconsistent
*/

    public void check(){
        if (getName().length() == 0) {
         new ApplicationWarning(getApplicationDataset(),ApplicationDataset.getIdNr(),toColumnString(),"name","Scenario",(getName()==null?"null":getName().toString()),"",WarningType.NOTEMPTY);
        }
    }

    static void dummy(ApplicationDataset base){
// no dummy information for class Scenario
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

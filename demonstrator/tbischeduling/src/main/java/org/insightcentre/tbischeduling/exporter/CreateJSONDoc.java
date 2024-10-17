package org.insightcentre.tbischeduling.exporter;

import framework.types.DateTime;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;
import org.insightcentre.tbischeduling.implementedsolver.ScheduleJobsSolverImpl;
import org.insightcentre.tbischeduling.importer.CreateData;
import org.insightcentre.tbischeduling.importer.Reset;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static org.insightcentre.tbischeduling.datamodel.ResourceModel.All;
import static org.insightcentre.tbischeduling.datamodel.Severity.Fatal;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class CreateJSONDoc {
    public CreateJSONDoc(Scenario base, String docDir){
        assert(docDir.endsWith("/"));
        makeDirectoryIfNeeded(docDir);
        // create minimal data set; update when parameters are added to the generator
        new CreateData(base,"JSON Doc",new DateTime(new Date()),All,1,
                2,2,1,
                1.0, DurationModel.Random,10,10,
                1,
                1,1,1,1,
                5,10,
                1,
                5,10,
                1.0,20,50,
                1.0,20,50,
                10,10,5,42);

        // get the data as a JSON object
        JSONObject input = new WriteData(base).toJSON();

        // create the input for a diagram of the whole dataset, details will be too small
        printAsJSONDoc(input,docDir,"sampler.txt");

        // create a dep copy of the JSON Object
        JSONObject basedata = new JSONObject(input.toMap());
        // remove the data for the schedule
        basedata.put("downtime",new JSONArray());
        basedata.put("task",new JSONArray());
        basedata.put("job",new JSONArray());
        basedata.put("order",new JSONArray());
        basedata.put("wip",new JSONArray());
        // create the input for a diagram of the base data only
        printAsJSONDoc(basedata,docDir,"basedata.txt");

        // create a JSON object with only the schedule parts
        // this is not viable, we cannot do this on the object side as we loose all references to the base data
        input.put("product",new JSONArray());
        input.put("process",new JSONArray());
        input.put("disjunctiveResource",new JSONArray());
        input.put("inputError",new JSONArray());
        input.put("cumulativeNeed",new JSONArray());
        input.put("processStep",new JSONArray());
        input.put("resourceNeed",new JSONArray());
//        input.put("downtime",new JSONArray());
        input.put("cumulativeProfile",new JSONArray());
        input.put("problem",new JSONArray());
        input.put("cumulativeResource",new JSONArray());
//        input.put("task",new JSONArray());
        input.put("processSequence",new JSONArray());
//        input.put("job",new JSONArray());
//        input.put("order",new JSONArray());
//        input.put("wip",new JSONArray());
        // create the input data of a diagram of only the schedule
        printAsJSONDoc(input,docDir,"schedule.txt");

        // create the input text for small diagrams for each class
        printAsJSONDoc(new WriteData(base).asJSON(Problem.findFirst(base)),docDir,"problem.txt");
        printAsJSONDoc(new WriteData(base).asJSON(Product.findFirst(base)),docDir,"product.txt");
        printAsJSONDoc(new WriteData(base).asJSON(Process.findFirst(base)),docDir,"process.txt");
        printAsJSONDoc(new WriteData(base).asJSON(ProcessStep.findFirst(base)),docDir,"processStep.txt");
        printAsJSONDoc(new WriteData(base).asJSON(ProcessSequence.findFirst(base)),docDir,"processSequence.txt");
        printAsJSONDoc(new WriteData(base).asJSON(DisjunctiveResource.findFirst(base)),docDir,"disjunctiveResource.txt");
        printAsJSONDoc(new WriteData(base).asJSON(ResourceNeed.findFirst(base)),docDir,"resourceNeed.txt");
        printAsJSONDoc(new WriteData(base).asJSON(CumulativeResource.findFirst(base)),docDir,"cumulativeResource.txt");
        printAsJSONDoc(new WriteData(base).asJSON(CumulativeNeed.findFirst(base)),docDir,"cumulativeNeed.txt");
        printAsJSONDoc(new WriteData(base).asJSON(CumulativeProfile.findFirst(base)),docDir,"cumulativeProfile.txt");

        printAsJSONDoc(new WriteData(base).asJSON(Order.findFirst(base)),docDir,"order.txt");
        printAsJSONDoc(new WriteData(base).asJSON(Job.findFirst(base)),docDir,"job.txt");
        printAsJSONDoc(new WriteData(base).asJSON(Task.findFirst(base)),docDir,"task.txt");
        printAsJSONDoc(new WriteData(base).asJSON(WiP.findFirst(base)),docDir,"wip.txt");
        printAsJSONDoc(new WriteData(base).asJSON(Downtime.findFirst(base)),docDir,"downtime.txt");

        // run the scheduler on the minimal dataset
        //??? remember the solver properties we mess up and restore them to previous values
        boolean produceReport = base.getSolverProperty().getProduceReport();
        boolean producePDF = base.getSolverProperty().getProducePDF();
        new ScheduleJobsSolverImpl(base).setProduceReport(false).setProducePDF(false).solve();
        base.getSolverProperty().setProduceReport(produceReport);
        base.getSolverProperty().setProducePDF(producePDF);

        // get a JSON representation of the solution
        JSONObject solved = new WriteData(base).toJSON();
        // empty the attributes we do not want to show
        solved.put("product",new JSONArray());
        solved.put("process",new JSONArray());
        solved.put("disjunctiveResource",new JSONArray());
        solved.put("inputError",new JSONArray());
        solved.put("cumulativeNeed",new JSONArray());
        solved.put("processStep",new JSONArray());
        solved.put("resourceNeed",new JSONArray());
        solved.put("downtime",new JSONArray());
        solved.put("cumulativeProfile",new JSONArray());
        solved.put("problem",new JSONArray());
        solved.put("cumulativeResource",new JSONArray());
        solved.put("task",new JSONArray());
        solved.put("processSequence",new JSONArray());
        solved.put("job",new JSONArray());
        solved.put("order",new JSONArray());
        solved.put("wip",new JSONArray());

        // print the input data of a diagram detailing all the result fields
        printAsJSONDoc(solved,docDir,"samplerresult.txt");

        // show the details of the result classes
        printAsJSONDoc(new WriteData(base).asJSON(SolverRun.findFirst(base)),docDir,"solverRun.txt");
        printAsJSONDoc(new WriteData(base).asJSON(Solution.findFirst(base)),docDir,"solution.txt");
        printAsJSONDoc(new WriteData(base).asJSON(JobAssignment.findFirst(base)),docDir,"jobAssignment.txt");
        printAsJSONDoc(new WriteData(base).asJSON(TaskAssignment.findFirst(base)),docDir,"taskAssignment.txt");

        // error routines
        // we should not have any error in our minimal generated example, so we create some of our own
        // use arbitrary values for attributes
        InputError err = new InputError(base);
        err.setName("Sample");
        err.setSeverity(Fatal);
        err.setClassDesc("Order");
        err.setItem("order1");
        err.setField("release");
        err.setValue("75");
        err.setDescription("Release date is after due date");

        // use that object to create a diagram for the input error
        printAsJSONDoc(new WriteData(base).asJSON(InputError.findFirst(base)),docDir,"inputError.txt");

        enumTypes("SequenceType",SequenceType.getNames(),docDir,"sequenceType.tex");
        enumTypes("SolverBackend",SolverBackend.getNames(),docDir,"solverBackend.tex");
        enumTypes("SolverStatus",SolverStatus.getNames(),docDir,"solverStatus.tex");
        enumTypes("ModelType",ModelType.getNames(),docDir,"modelType.tex");
        enumTypes("ObjectiveType",ObjectiveType.getNames(),docDir,"objectiveType.tex");
        enumTypes("Severity",Severity.getNames(),docDir,"severity.tex");

        stylesTable(docDir,"colorcoding.tex");

        // get rid of any data used in the documentation generation
        Reset.resetAll(base);



    }
    private void makeDirectoryIfNeeded(String dir) {
        Path path = Paths.get(dir);
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            severe("Cannot create directory " + dir + ", exception " + e.getMessage());
            assert (false);
        }
    }

    private void enumTypes(String label,String[] names,String docDir,String file){
        String fullName = docDir+file;
        try{
            PrintWriter out = new PrintWriter(fullName);
            out.printf("\\begin{table}[htbp]\n");
            out.printf("\\caption{\\label{tab:"+label+"} Possible Values for "+label+"}\n");
            out.printf("\\centering\n");
            out.printf("\\begin{tabular}{c}\\toprule\n");
            out.printf("Value\\\\\\midrule\n");
            for(String name:names){
                out.printf("%s\\\\\n",name);
            }
            out.printf("\\bottomrule\n");
            out.printf("\\end{tabular}\n");
            out.printf("\\end{table}\n\n");
            out.close();
        } catch(IOException e){
            severe("Cannot write file "+fullName+", exception "+e.getMessage());
        }

    }

    private void printAsJSONDoc(JSONObject obj, String docDir, String file){
        try{
            PrintWriter out = new PrintWriter(new File(docDir+file));
            out.println("@startjson");
            styles(out);
            out.printf("%s\n",obj.toString(2));
            out.println("@endjson");
            out.close();
        } catch(IOException e){
            severe("Cannot write file "+file+", exception "+e.getMessage());
        }
    }

    private void stylesTable(String docDir,String file){
        String fullName = docDir+file;
        try{
            PrintWriter out = new PrintWriter(fullName);
            out.printf("\\begin{table}[htbp]\n");
            out.printf("\\caption{\\label{tab:colors} Color Legend}\n");
            out.printf("\\centering\n");
            out.printf("\\begin{tabular}{cp{9cm}}\\toprule\n");
            out.printf("Style & Description\\\\\\midrule\n");
            out.printf("\\cellcolor{Gold} name& The \\texttt{name} field is the primary key of a structure. The names within one class must be unique.\\\\\n");
            out.printf("\\cellcolor{GreenYellow} key& A key is a foreign key reference. It is the name of a structure of another type.\\\\\n");
            out.printf("\\cellcolor{LightSkyBlue} date& A date expresses time in the external representation, e.g. day, month, year, hour, minute.\\\\\n");
            out.printf("\\cellcolor{PowderBlue} elapsed& A time period measured in elapsed seconds. Used for timeout and runtime reporting.\\\\\n");
            out.printf("\\cellcolor{Tomato} enum& An eumerated type can only take values from a restricted vocabulary. Lower/Upper case is significant.\\\\\n");
            out.printf("\\cellcolor{LemonChiffon} internaltime& Many fields use the solver internal time, which is " +
                    "expressed as an integer, based on the distance to a reference start date, divided by the time resolution.\\\\\n");
            out.printf("\\cellcolor{White} optional& Some input data fields are optional, if they are not given, the " +
                    "value from another field is used.\\\\\n");
             out.printf("\\bottomrule\n");
            out.printf("\\end{tabular}\n");
            out.printf("\\end{table}\n\n");
            out.close();
        } catch(IOException e){
            severe("Cannot write file "+fullName+", exception "+e.getMessage());
        }

    }
    private void styles(PrintWriter out){
        out.println("<style>");
        out.println(".name {");
        out.println("BackGroundColor gold");
        out.println("FontColor black");
        out.println("FontStyle italic");
        out.println("}");
        out.println(".key {");
        out.println("BackGroundColor greenyellow");
        out.println("FontColor black");
        out.println("FontStyle italic");
        out.println("}");
        out.println(".date {");
        out.println("BackGroundColor lightskyblue");
        out.println("FontColor black");
        out.println("FontStyle italic");
        out.println("}");
        out.println(".elapsed {");
        out.println("BackGroundColor powderblue");
        out.println("FontColor black");
        out.println("FontStyle italic");
        out.println("}");
        out.println(".enum {");
        out.println("BackGroundColor tomato");
        out.println("FontColor black");
        out.println("FontStyle italic");
        out.println("}");
        out.println(".internaltime {");
        out.println("BackGroundColor lemonchiffon");
        out.println("FontColor black");
        out.println("FontStyle italic");
        out.println("}");
        out.println(".optional {");
        out.println("BackGroundColor white");
        out.println("FontColor black");
        out.println("FontStyle italic");
        out.println("}");

        out.println("</style>");
        // mark the primary key name
        out.println("#highlight \"name\" <<name>>");
        // mark all foreign keys; assumes consistent naming of field names
        out.println("#highlight \"disjunctiveResource\" <<key>>");
        out.println("#highlight \"cumulativeResource\" <<key>>");
        out.println("#highlight \"product\" <<key>>");
        out.println("#highlight \"process\" <<key>>");
        out.println("#highlight \"defaultProcess\" <<key>>");
        out.println("#highlight \"before\" <<key>>");
        out.println("#highlight \"after\" <<key>>");
        out.println("#highlight \"processStep\" <<key>>");
        out.println("#highlight \"solverRun\" <<key>>");
        out.println("#highlight \"solution\" <<key>>");
        out.println("#highlight \"order\" <<key>>");
        out.println("#highlight \"job\" <<key>>");
        out.println("#highlight \"task\" <<key>>");
        out.println("#highlight \"jobAssignment\" <<key>>");
        // mark all fields that use external date/time
        out.println("#highlight \"endDate\" <<date>>");
        out.println("#highlight \"startDate\" <<date>>");
        out.println("#highlight \"fromDate\" <<date>>");
        out.println("#highlight \"dueDate\" <<date>>");
        out.println("#highlight \"releaseDate\" <<date>>");
        out.println("#highlight \"fromDate\" <<date>>");
        out.println("#highlight \"timeout\" <<elapsed>>");
        out.println("#highlight \"time\" <<elapsed>>");
        // make enum types as restricted vocabulary
        out.println("#highlight \"sequenceType\" <<enum>>");
        out.println("#highlight \"solverBackend\" <<enum>>");
        out.println("#highlight \"solverStatus\" <<enum>>");
        out.println("#highlight \"modelType\" <<enum>>");
        out.println("#highlight \"objectiveType\" <<enum>>");
        out.println("#highlight \"severity\" <<enum>>");
        out.println("#highlight \"start\" <<internaltime>>");
        out.println("#highlight \"end\" <<internaltime>>");
        out.println("#highlight \"duration\" <<internaltime>>");
        out.println("#highlight \"due\" <<internaltime>>");
        out.println("#highlight \"release\" <<internaltime>>");
        out.println("#highlight \"due\" <<internaltime>>");
        out.println("#highlight \"early\" <<internaltime>>");
        out.println("#highlight \"late\" <<internaltime>>");
        out.println("#highlight \"from\" <<internaltime>>");
        out.println("#highlight \"durationFixed\" <<internaltime>>");
        out.println("#highlight \"durationPerUnit\" <<internaltime>>");
        out.println("#highlight \"offset\" <<internaltime>>");
        out.println("#highlight \"makespan\" <<internaltime>>");
        out.println("#highlight \"flowtime\" <<internaltime>>");
        out.println("#highlight \"totalEarliness\" <<internaltime>>");
        out.println("#highlight \"maxEarliness\" <<internaltime>>");
        out.println("#highlight \"totalLateness\" <<internaltime>>");
        out.println("#highlight \"maxLateness\" <<internaltime>>");
        out.println("#highlight \"waitBefore\" <<internaltime>>");
        out.println("#highlight \"waitAfter\" <<internaltime>>");
        out.println("#highlight \"maxWaitBefore\" <<internaltime>>");
        out.println("#highlight \"maxWaitAfter\" <<internaltime>>");
        out.println("#highlight \"totalWaitBefore\" <<internaltime>>");
        out.println("#highlight \"totalWaitAfter\" <<internaltime>>");
        out.println("#highlight \"earlinessWeight\" <<optional>>");
        out.println("#highlight \"latenessWeight\" <<optional>>");
        out.println("#highlight \"label\" <<optional>>");

    }
}

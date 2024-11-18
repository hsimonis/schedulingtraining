package org.insightcentre.tbischeduling.reports;

import javafx.scene.image.Image;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import org.insightcentre.tbischeduling.datamodel.*;
import org.insightcentre.tbischeduling.datamodel.Process;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static net.sourceforge.plantuml.FileFormat.PNG;
import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class ProcessDiagram {
    Scenario base;
    Image image;

    /*
    use plantuml to create process and PERT diagrams for a schedule
    the diagrams are created as png and displayed in an ImageViewer in the GUI
    it is possible to show only a sub part of the full chart
     */

    /*
    show all process steps and process sequence
     */
    public ProcessDiagram(Scenario base,DurationDisplay durationDisplay,boolean displayProcess,boolean displayStage,boolean displayMachines,boolean displayCumulative){
        this.base = base;
        StringBuilder sb = prefix("p");
        for(ProcessStep ps:base.getListProcessStep()){
            sb.append(record(ps,durationDisplay,displayProcess,displayStage,displayMachines,displayCumulative));
        }
        for(ProcessSequence ps:base.getListProcessSequence()){
            sb.append(link(ps));
        }
        String source = suffix(sb);

        createImage(source,PNG,"maps/diagram.png");
        image = imageFromFile("maps/diagram.png");
    }

     /*
     show process steps and sequence for a single process
      */
    public ProcessDiagram(Scenario base,Process p,DurationDisplay durationDisplay,boolean displayProcess,boolean displayStage,boolean displayMachines,boolean displayCumulative){
        this.base = base;
        StringBuilder sb = prefix("p");
        for(ProcessStep ps:base.getListProcessStep().stream().
                filter(x->x.getProcess()==p).
                toList()){
            sb.append(record(ps,durationDisplay,displayProcess,displayStage,displayMachines,displayCumulative));
        }
        for(ProcessSequence ps:base.getListProcessSequence().stream().
                filter(x->x.getBefore().getProcess()==p).
                toList()){
            sb.append(link(ps));
        }
        String source=suffix(sb);

        createImage(source,PNG,"maps/diagram.png");
        image = imageFromFile("maps/diagram.png");
     }

 /*
 show the pert chart for a single order in a specific solution
  */
    public ProcessDiagram(Scenario base, Order ord,Solution sol,TimingDisplay timingDisplay,
                          boolean displayOrder,boolean displayProduct,
                          boolean displayProcess,boolean displayProcessStep,
                          boolean displayMachines,boolean displayCumulative,
                          boolean displayLinks){
        this.base = base;
        assert(sol!=null);
        info("Drawing diagram for "+ord);
        StringBuilder sb = prefix("p");
        List<TaskAssignment> list = base.getListTaskAssignment().stream().
                filter(x->x.getJobAssignment().getSolution()==sol).
                filter(x->x.getJobAssignment().getJob().getOrder()==ord).
                toList();
        Hashtable<Task,TaskAssignment> taskHash = new Hashtable<>();
        for(TaskAssignment ta:list){
            sb.append(record(ta,timingDisplay,displayOrder,displayProduct,displayProcess,displayProcessStep,displayMachines,displayCumulative));
            taskHash.put(ta.getTask(),ta);
        }
        for(Task t:list.stream().map(TaskAssignment::getTask).toList()){
            for(Task a:t.getPrecedes()){
                sb.append(link(taskHash.get(t),taskHash.get(a)));
            }
        }

        String source = suffix(sb);

        createImage(source,PNG,"maps/pert.png");
        image = imageFromFile("maps/pert.png");
    }
    /*
    show the PERT chart for a complete schedule
     */
    public ProcessDiagram(Scenario base, Solution sol,TimingDisplay timingDisplay,
                          boolean displayOrder,boolean displayProduct,
                          boolean displayProcess,boolean displayProcessStep,
                          boolean displayMachines,boolean displayCumulative,
                          boolean displayLinks){
        assert(sol != null);
        this.base = base;
        info("Drawing diagram for Solution "+sol);
        StringBuilder sb = prefix("p");
        List<TaskAssignment> list = base.getListTaskAssignment().stream().
                filter(x->x.getJobAssignment().getSolution()==sol).
                toList();
        Hashtable<Task,TaskAssignment> taskHash = new Hashtable<>();
        for(TaskAssignment ta:list){
            sb.append(record(ta,timingDisplay,displayOrder,displayProduct,displayProcess,displayProcessStep,displayMachines,displayCumulative));
            taskHash.put(ta.getTask(),ta);
        }
        for(Task t:list.stream().map(TaskAssignment::getTask).toList()){
            for(Task a:t.getPrecedes()){
                sb.append(link(taskHash.get(t),taskHash.get(a)));
            }
        }

        // create links based on order on machines
        if (displayLinks) {
            for (DisjunctiveResource r : base.getListDisjunctiveResource()) {
                List<TaskAssignment> tasks = base.getListTaskAssignment().stream().
                        filter(x -> x.getJobAssignment().getSolution() == sol).
                        filter(x -> x.getDisjunctiveResource() == r).
                        sorted(Comparator.comparing(TaskAssignment::getStart)).
                        toList();
                TaskAssignment prev = null;
                for (TaskAssignment t : tasks) {
                    if (prev != null) {
                        sb.append(link(prev.getName(), t.getName()));
                    }
                    prev = t;
                }
            }
        }
        String source = suffix(sb);

        createImage(source,PNG,"maps/pert.png");
        image = imageFromFile("maps/pert.png");
    }

    private Image imageFromFile(String fileName){
        Image res = null;
        try {
            FileInputStream inputstream = new FileInputStream(fileName);
            res = new Image(inputstream);
            info("Image created");
        } catch(Exception e){
            severe("Cannot create image, "+e.getMessage());
        }
        return res;

    }
    private StringBuilder prefix(String name){
        StringBuilder sb = new StringBuilder();
        sb.append("@startdot\n");
        sb.append("digraph ");sb.append(name);sb.append(" {\n");
        sb.append(rankdir());
        //??? while this seems to work in principle, it scales the image in surprising ways
//        sb.append("graph [size=\"30,12\",dpi=100]");
        sb.append("node [shape=Mrecord]\n");
        return sb;
    }
    private String suffix(StringBuilder sb){
        sb.append("}\n");
        sb.append("@enddot\n");
        return sb.toString();

    }
    private String record(ProcessStep ps,DurationDisplay durationDisplay,boolean displayProcess,boolean displayStage,boolean displayMachines,boolean displayCumulative){
        StringBuilder sb = new StringBuilder();
        sb.append(clean(ps.getName()));
        // use {{ to get the correct layout of records in LR rankdir
        sb.append(" [label=\"{{ ");sb.append(clean(ps.getName()));
        if (displayProcess){
            sb.append("| P: ");sb.append(clean(ps.getProcess().getName()));
        }
        if (displayStage){
            sb.append("| Stage: ");sb.append(ps.getStage());
        }
        switch (durationDisplay) {
            case Fixed -> {
                sb.append("| ");
                sb.append(String.format("Dur F: %d", ps.getDurationFixed()));
            }
            case PerUnit -> {
                sb.append("| ");
                sb.append(String.format("Dur U: %d", ps.getDurationPerUnit()));
            }
            case FixedPerUnit -> {
                sb.append(String.format("| { Dur F: %d", ps.getDurationFixed()));
                sb.append(String.format("| U: %d}", ps.getDurationPerUnit()));
            }
            case None -> {}
            default -> severe("Base duration display " + durationDisplay);
        }
        if (displayMachines) {
            sb.append(resourceNeeds(ps));
        }
        if (displayCumulative) {
            sb.append(cumulNeeds(ps));
        }
        sb.append("}}\"]\n");
        return sb.toString();


    }
     private String record(TaskAssignment ta, TimingDisplay timingDisplay,
                           boolean displayOrder,boolean displayProduct,
                           boolean displayProcess,boolean displayProcessStep,
                           boolean displayMachines,boolean displayCumulative){
        StringBuilder sb = new StringBuilder();
        sb.append(clean(ta.getName()));
        sb.append(" [label=\"{{");
        sb.append(ta.getTask().getName());
         if (displayOrder) {
             sb.append("| O: ");sb.append(ta.getJobAssignment().getJob().getOrder().getName());
         }
         if (displayProduct) {
             sb.append("| Prod: ");sb.append(ta.getJobAssignment().getJob().getOrder().getProduct().getName());
         }
         if (displayProcess) {
             sb.append("| Proc: ");sb.append(ta.getTask().getProcessStep().getProcess().getName());
         }
         if (displayProcessStep) {
             sb.append("| Step: ");sb.append(ta.getTask().getProcessStep().getName());
         }
         switch (timingDisplay) {
             case Start -> sb.append(String.format("| S: %d ", ta.getStart()));
             case StartDuration -> sb.append(String.format("| {S: %d |D: %d}", ta.getStart(), ta.getDuration()));
             case StartEnd -> sb.append(String.format("| {S: %d |E: %d}", ta.getStart(), ta.getEnd()));
             case StartDurationEnd ->
                     sb.append(String.format("| {S: %d |D: %d |E: %d}", ta.getStart(), ta.getDuration(), ta.getEnd()));
             default -> severe("Bad timing display " + timingDisplay);
         }
        if (displayMachines && ta.getDisjunctiveResource() != null) {
            sb.append(String.format("| M: %s", ta.getDisjunctiveResource().getName()));
        }
        if (displayCumulative) {
            sb.append(cumulNeeds(ta.getTask().getProcessStep()));
        }
        sb.append("}}\"]\n");

        return sb.toString();
    }

    private String link(TaskAssignment before,TaskAssignment after){
        return clean(before.getName())+" -> "+clean(after.getName())+"\n";
    }
    private String link(ProcessSequence ps){
        return clean(ps.getBefore().getName())+
                " -> "+
                clean(ps.getAfter().getName())+
                sequenceTypeColor(ps.getSequenceType())+
                "\n";
    }

    private String sequenceTypeColor(SequenceType type){
        switch(type){
            case StartBeforeStart: return " [color=darkseagreen]";
            case NoWait: return " [color=tomato]";
            case Blocking: return " [color=goldenrod]";
            default:
                return "";
        }
    }

    private String link(String before,String after){
        return clean(before)+
                " -> "+
                clean(after)+
                " [color=blue,penwidth=2]\n";

    }

    private String rankdir(){
//        return "";
//        return "rankdir=\"TB\"";
        return "rankdir=\"LR\"";
    }

    private String cumulNeeds(ProcessStep ps){
        return base.getListCumulativeNeed().stream().
                filter(x->x.getProcessStep()==ps).
                map(x->" | "+ x.getCumulativeResource().getName()+": "+x.getDemand()).
                collect(joining(""));
    }
    private String resourceNeeds(ProcessStep ps){
        String names = base.getListResourceNeed().stream().
                filter(x->x.getProcessStep()==ps).
                map(x->x.getDisjunctiveResource().getName()).
                collect(joining(","));
        if (!names.equals("")){
            return "| M: "+names;
        } else {
            return "";
        }
    }

    private String createImage(String source,FileFormat format,String fileName){
        try {
            SourceStringReader reader = new SourceStringReader(source);
            final ByteArrayOutputStream os = new ByteArrayOutputStream();
            String desc = reader.generateImage(os, new FileFormatOption(format));
            info("Desc "+desc);
            os.close();


            final String svg = os.toString(StandardCharsets.UTF_8);
            OutputStream out = new FileOutputStream(new File(fileName));
            os.writeTo(out);
            out.close();
            return desc;

        } catch(Exception e){
            severe("Cannot write diagram, "+e.getMessage());
            return null;
        }

    }
    private String taskName(ProcessStep ps,Hashtable<ProcessStep,TaskAssignment> hash){
        assert(hash!=null);
        assert(ps != null);
        return clean(hash.get(ps).getName());
    }

    public Image getImage(){
        return image;
    }

    public String clean(String text){
        return text.replaceAll("/","");
    }
}

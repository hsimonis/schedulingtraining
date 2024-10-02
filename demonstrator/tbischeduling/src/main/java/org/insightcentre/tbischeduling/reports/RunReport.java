package org.insightcentre.tbischeduling.reports;

import java.io.File;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class RunReport {
    String program;
    String directory;
    String dataFile;

   public RunReport(String program, String directory, String dataFile){
       this.program = program;
        this.directory=directory;
        this.dataFile = dataFile;
    }

     public void runProgram() {
        String cmd = String.format("%s %s",program,dataFile);
        try {
            ProcessBuilder pb;
            pb = new ProcessBuilder(program,
                    dataFile);

            info("command-line: "+cmd);
            pb.directory(new File(directory));
            info("directory: " + directory);
            pb.redirectErrorStream(true);
            File log = new File(directory+"log.txt");
            pb.redirectErrorStream(true);
            pb.redirectOutput(ProcessBuilder.Redirect.to(log));
            info("Start program: " + program);
            Process p = pb.start();
           info("started");
            p.waitFor();
            info("command done");
        } catch(Exception e) {
            severe("Problem with executing command, " + e.getMessage());
        }
    }


}

package org.insightcentre.tbischeduling.importer;

import au.com.bytecode.opencsv.CSVReader;
import org.insightcentre.tbischeduling.datamodel.Scenario;
import org.insightcentre.tbischeduling.datamodel.Summary;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.insightcentre.tbischeduling.logging.LogShortcut.info;
import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

public class ImportExport extends ImportCSV{
    public ImportExport(Scenario base, String importDir, String fileName){
        assert(importDir.endsWith("/"));
        String fullName = importDir+fileName;
        try{
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(fullName),"UTF8"));
            String[] firstLine = reader.readNext();
            String[] secondLine = reader.readNext();
            String[] nextLine;
            int line = 1;
            int created = 0;
            while ((nextLine = reader.readNext()) != null) {
                int col= 0;
//                System.out.println(nextLine[0]);
                if (!nextLine[0].equals("")) {

                    String name = nextLine[col++];
                    int nrJobs = readInteger(nextLine[col++]);
                    int nrStages = readInteger(nextLine[col++]);
                    int capacity = readInteger(nextLine[col++]);
                    String gap = nextLine[col++];
                    assert(gap.isEmpty());
                    int localFS = readInteger(nextLine[col++]);
                    int localPNW = readInteger(nextLine[col++]);
                    int localNW = readInteger(nextLine[col++]);
                    gap = nextLine[col++];
                    assert(gap.isEmpty());

                    //skip over deduced stuff
                    col = 51;
                    String fsCPOType = nextLine[col++];
                    double fsCPOTime = readDouble(nextLine[col++]);
                    int fsCPOCost = readInteger(nextLine[col++]);
                    int fsCPOBound = readInteger(nextLine[col++]);
                    double fsCPOGap = readDouble(nextLine[col++]);
                    gap = nextLine[col++];
                    assert(gap.isEmpty());

                    String pnwCPOType = nextLine[col++];
                    double pnwCPOTime = readDouble(nextLine[col++]);
                    int pnwCPOCost = readInteger(nextLine[col++]);
                    int pnwCPOBound = readInteger(nextLine[col++]);
                    double pnwCPOGap = readDouble(nextLine[col++]);
                    gap = nextLine[col++];
                    assert(gap.isEmpty());

                    String nwCPOType = nextLine[col++];
                    double nwCPOTime = readDouble(nextLine[col++]);
                    int nwCPOCost = readInteger(nextLine[col++]);
                    int nwCPOBound = readInteger(nextLine[col++]);
                    double nwCPOGap = readDouble(nextLine[col++]);
                    gap = nextLine[col++];
                    assert(gap.isEmpty());

                    String fsCPSatType = nextLine[col++];
                    double fsCPSatTime = readDouble(nextLine[col++]);
                    int fsCPSatCost = readInteger(nextLine[col++]);
                    int fsCPSatBound = readInteger(nextLine[col++]);
                    double fsCPSatGap = readDouble(nextLine[col++]);
                    gap = nextLine[col++];
                    assert(gap.isEmpty());

                    String pnwCPSatType = nextLine[col++];
                    double pnwCPSatTime = readDouble(nextLine[col++]);
                    int pnwCPSatCost = readInteger(nextLine[col++]);
                    int pnwCPSatBound = readInteger(nextLine[col++]);
                    double pnwCPSatGap = readDouble(nextLine[col++]);
                    gap = nextLine[col++];
                    assert(gap.isEmpty());

                    String nwCPSatType = nextLine[col++];
                    double nwCPSatTime = readDouble(nextLine[col++]);
                    int nwCPSatCost = readInteger(nextLine[col++]);
                    int nwCPSatBound = readInteger(nextLine[col++]);
                    double nwCPSatGap = readDouble(nextLine[col++]);

                    int cpFS = Math.min(fsCPOCost,fsCPSatCost);
                    int cpPNW = Math.min(pnwCPOCost,pnwCPSatCost);
                    int cpNW = Math.min(nwCPOCost,nwCPSatCost);

                    int boundFS = Math.max(fsCPOBound,fsCPSatBound);
                    int boundPNW = Math.max(pnwCPOBound,pnwCPSatBound);
                    int boundNW = Math.max(nwCPOBound,nwCPSatBound);

                    double gapFS = 100.0*(cpFS-boundFS)/cpFS;
                    double gapPNW = 100.0*(cpPNW-boundPNW)/cpPNW;
                    double gapNW = 100.0*(cpNW-boundNW)/cpNW;

                    Summary s = new Summary(base);
                    s.setName(name);
                    s.setNr(created);
                    s.setNrJobs(nrJobs);
                    s.setNrStages(nrStages);
                    s.setCapacity(capacity);

                    s.setLocalFS(localFS);
                    s.setLocalPNW(localPNW);
                    s.setLocalNW(localNW);

                    s.setCpFS(cpFS);
                    s.setCpPNW(cpPNW);
                    s.setCpNW(cpNW);

                    s.setBoundFS(boundFS);
                    s.setBoundPNW(boundPNW);
                    s.setBoundNW(boundNW);

                    s.setGapFS(gapFS);
                    s.setGapPNW(gapPNW);
                    s.setGapNW(gapNW);

                    s.setFsCPOCost(fsCPOCost);
                    s.setFsCPSatCost(fsCPSatCost);

                    s.setPnwCPOCost(pnwCPOCost);
                    s.setPnwCPSatCost(pnwCPSatCost);

                    s.setNwCPOCost(nwCPOCost);
                    s.setNwCPSatCost(nwCPSatCost);

                    info(created+" "+localFS+" "+localPNW+" "+localNW+" "+cpFS+" "+cpPNW+" "+cpNW);



                    created++;

                }

                line++;


            }
            info("ImportExport File read, "+line+" lines, "+created+" items created");

        } catch(IOException e){
            severe("Cannot read file "+fullName+", exception "+e.getMessage());
        }
    }
}

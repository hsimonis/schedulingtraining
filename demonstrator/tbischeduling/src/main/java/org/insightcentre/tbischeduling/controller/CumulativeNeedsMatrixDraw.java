package org.insightcentre.tbischeduling.controller;

import org.insightcentre.tbischeduling.datamodel.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import static java.util.stream.Collectors.toList;

import static org.insightcentre.tbischeduling.logging.LogShortcut.severe;

   public class CumulativeNeedsMatrixDraw {
       public CumulativeNeedsMatrixDraw(Scenario base, String fileName, String title) {
           try {
                PrintWriter out = new PrintWriter(new File(fileName));
                out.println(createContent(base,title));
                out.close();
               } catch (IOException e) {
                severe("Cannot write file: " + fileName+" exception "+e.getMessage());
               }
       }

       public static String createContent(Scenario base,String title) {

            StringBuilder sb = new StringBuilder();
            sb.append("<html>\n");
            sb.append("  <head>\n");
            sb.append("    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n");
            sb.append("    <script type=\"text/javascript\">\n");
            sb.append("      google.charts.load('current', {'packages':['table']});\n");
            sb.append("      google.charts.setOnLoadCallback(drawTable);\n");
            sb.append("      function drawTable() {\n");
            sb.append("        var data = new google.visualization.DataTable();\n");
            sb.append("        data.addColumn('string','Row');\n");
            for(ApplicationObject col:findColumns(base)){
            sb.append(String.format("        data.addColumn('number','%s');\n",safe(col)));
            }
            initHash(base);
            sb.append("        data.addRows([\n");
            for(ProcessStep row:findRows(base)){
                sb.append(String.format("['%s'",safe(row)));
                for(CumulativeResource col:findColumns(base)){
                    sb.append(String.format(",%s",findCell(base,row,col)));
                }
                sb.append("],\n");
            }
            sb.append("          ]);\n");
            sb.append("\n");
            sb.append("var table = new google.visualization.Table(document.getElementById('chart_div'));\n");
            sb.append("table.draw(data, {showRowNumber: true});\n");
            sb.append("      }\n");
            sb.append("    </script>\n");
            sb.append("  </head>\n");
            sb.append("  <body>\n");
            sb.append(String.format("    <div id=\"chart_div\" ></div>\n"));
            sb.append("  </body>\n");
            sb.append("</html>\n");
           return sb.toString();
        }
        private static List<CumulativeResource> findColumns(Scenario base){
             return base.getListCumulativeResource().stream().sorted(Comparator.comparing(CumulativeResource::getName)).collect(toList());
        }

        private static List<ProcessStep> findRows(Scenario base){
             return base.getListProcessStep().stream().sorted(Comparator.comparing(ProcessStep::getName)).collect(toList());
        }

        private static Hashtable<String,String> hash = new Hashtable<>();
        private static void initHash(Scenario base){
            for(CumulativeNeed item:base.getListCumulativeNeed()){
               hash.put(item.getProcessStep().getId()+":"+item.getCumulativeResource().getId(),item.getDemand().toString());
            }
        }
        private static String findCell(Scenario base,ProcessStep row,CumulativeResource col){
            String res = hash.get(row.getId()+":"+col.getId());
            if (res != null) {
              return res;
            }
            return "0";
        }


       private static String safe(ApplicationObject x){
           return x.getName().replaceAll("'"," ");
       }

   }

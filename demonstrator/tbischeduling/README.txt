This directory contains the sources and sample data for the Test Before Invest Scheduling application of the ENTIRE EDIH project at UCC. To run the application, execute ./runscheduling.bat from this directory (Windows only).

To build the application, use the command

mvn clean package

to create the application jar "tbischeduling-1.0-SNAPSHOT-jar-with-dependencies.jar" in the target directory.

This requires the installation of

Maven
Java (version 17 or later)
JavaFX (assumes Version 18)
CPOptimizer (version 22.1.0.0)
framework (version 4.10.0)
lualatex (to create reports)
IntelliJ IDEA (Community Edition 2022.1.4) to modify sources

The libraries for framework and cpoptimizer may need to be installed in your local maven copy, see lib directory for details.

To load the sources into IntelliJ, create a new project from existing sources, and pick the pom.xml file in this directory as the location of the project. It should go away, resolve all dependencies, and present the application.

To run the application from inside the IDE, 
select "Edit configurations" in the toolbar
select "+" then "application"
Give the configuration a name, e.g. "tbischedulingFX", select the main entry point "org.insightcentre.tbischeduling.JfxApp" and "Add VM options" with the string
"-ea -Xms64m -Xmx2g --module-path=C:/javafx-sdk-18.0.1/lib --add-modules=javafx.controls,javafx.fxml,javafx.web"

reflecting the location of your JavaFX installation. 
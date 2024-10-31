all: tai20_5_0.solved tai20_5_1.solved

%.solved: %.json
	java -ea -Xms64m -Xmx2g --module-path C:/javafx-sdk-18.0.1/lib --add-modules=javafx.controls,javafx.fxml,javafx.web -cp ../../../target/tbischeduling-1.0-SNAPSHOT-jar-with-dependencies.jar org.insightcentre.tbischeduling.Batch Taillard/FSS/$< Taillard/FSS/$@

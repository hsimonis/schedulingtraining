all: description.pdf

description.pdf: description.tex compact.png classes.png full.png AbstractDataGeneratorProperty.png AbstractGanttProperty.png AbstractSolverProperty.png ApplicationDataset.png ApplicationDifference.png ApplicationObject.png ApplicationWarning.png CumulativeNeed.png CumulativeProfile.png CumulativeResource.png DataGeneratorProperty.png DataGeneratorRun.png DisjunctiveResource.png Downtime.png GanttProperty.png InputError.png IntermediateSolution.png Job.png JobAssignment.png Order.png Problem.png Process.png ProcessSequence.png ProcessStep.png Product.png ResourceActivity.png ResourceNeed.png ResourceUtilization.png Scenario.png Setup.png SetupMatrix.png SetupType.png Solution.png SolutionError.png SolutionSummary.png SolverProperty.png SolverRun.png Task.png TaskAssignment.png Transport.png TransportMatrix.png WiP.png
	pdflatex description
	pdflatex description
	pdflatex description

%.png:%.txt
	java -jar plantuml-1.2024.7.jar $<

clean:
	rm -f *.png *.pdf


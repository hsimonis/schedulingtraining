all: description.pdf

description.pdf: description.tex sampler.png basedata.png schedule.png samplerresult.png problem.png product.png process.png processStep.png processSequence.png disjunctiveResource.png resourceNeed.png cumulativeResource.png cumulativeNeed.png cumulativeProfile.png order.png job.png task.png wip.png downtime.png solverRun.png solution.png jobAssignment.png taskAssignment.png inputError.png
	pdflatex description
	pdflatex description
	pdflatex description

%.png:%.txt
	java -jar plantuml-1.2024.7.jar $<
	
clean:
	rm -f *.png *.pdf
	

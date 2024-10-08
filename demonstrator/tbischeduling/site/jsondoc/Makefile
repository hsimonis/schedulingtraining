all: description.pdf


description.pdf: description.txt sampler.png basedata.png schedule.png samplerresult.png problem.png product.png process.png processStep.png processSequence.png disjunctiveResource.png resourceNeed.png cumulativeResource.png cumulativeNeed.png cumulativeProfile.png order.png job.png task.png wip.png downtime.png solverRun.png solution.png jobAssignment.png taskAssignment.png inputError.png
	pdflatex description
	pdflatex description
	pdflatex description
	
description.txt:

sampler.png: sampler.txt
	java -jar plantuml-1.2024.7.jar sampler.txt
	
basedata.png: basedata.txt
	java -jar plantuml-1.2024.7.jar basedata.txt
	
schedule.png: schedule.txt
	java -jar plantuml-1.2024.7.jar schedule.txt
	
samplerresult.png: samplerresult.txt
	java -jar plantuml-1.2024.7.jar samplerresult.txt
	
problem.png: problem.txt
	java -jar plantuml-1.2024.7.jar problem.txt
	
product.png: product.txt
	java -jar plantuml-1.2024.7.jar product.txt
	
process.png: process.txt
	java -jar plantuml-1.2024.7.jar process.txt
	
processStep.png: processStep.txt
	java -jar plantuml-1.2024.7.jar processStep.txt
	
processSequence.png: processSequence.txt
	java -jar plantuml-1.2024.7.jar processSequence.txt
	
disjunctiveResource.png: disjunctiveResource.txt
	java -jar plantuml-1.2024.7.jar disjunctiveResource.txt
	
resourceNeed.png: resourceNeed.txt
	java -jar plantuml-1.2024.7.jar resourceNeed.txt
	
cumulativeResource.png: cumulativeResource.txt
	java -jar plantuml-1.2024.7.jar cumulativeResource.txt
	
cumulativeNeed.png: cumulativeNeed.txt
	java -jar plantuml-1.2024.7.jar cumulativeNeed.txt
	
cumulativeProfile.png: cumulativeProfile.txt
	java -jar plantuml-1.2024.7.jar cumulativeProfile.txt
	
order.png: order.txt
	java -jar plantuml-1.2024.7.jar order.txt
	
job.png: job.txt
	java -jar plantuml-1.2024.7.jar job.txt
	
task.png: task.txt
	java -jar plantuml-1.2024.7.jar task.txt

wip.png: wip.txt
	java -jar plantuml-1.2024.7.jar wip.txt
	
downtime.png: downtime.txt
	java -jar plantuml-1.2024.7.jar downtime.txt
	
solverRun.png: solverRun.txt
	java -jar plantuml-1.2024.7.jar solverRun.txt
	
solution.png: solution.txt
	java -jar plantuml-1.2024.7.jar solution.txt
	
jobAssignment.png: jobAssignment.txt
	java -jar plantuml-1.2024.7.jar jobAssignment.txt
	
taskAssignment.png: taskAssignment.txt
	java -jar plantuml-1.2024.7.jar taskAssignment.txt
	
inputError.png: inputError.txt
	java -jar plantuml-1.2024.7.jar inputError.txt
	
clean:
	rm -f *.png *.pdf
	
	
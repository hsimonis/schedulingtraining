include "globals.mzn";

int:nrJobs;
int:nrTasks;
int:nrMachines;
int:nrCumulativeMachines;
int:nrCumulativeNeeds;
int:nrSequences;
int:nrDowntimes;
int:horizon;

type Job = record(string:name,int:nr,int:release,int:due,bool:noOverlap);
type Task = record(string:name,int:nr,string:job,string:machine,int:duration);
type Machine = record(string:name,int:nr);
type CumulativeMachine = record(string:name,int:nr,int:limit);
type Sequence = record(int:before,int:after,int:offset,string:sequenceType);
type CumulativeNeed = record(string:cumulativeMachine,int:taskNr,int:demand);
type Downtime = record(string:name,int:nr,string:machine,int:start,int:duration);

% data arrays
array[1..nrJobs] of Job:jobs;
array[1..nrTasks] of Task:tasks;
array[1..nrMachines] of Machine:machines;
array[1..nrCumulativeMachines] of CumulativeMachine:cumulativeMachines;
array[1..nrCumulativeNeeds] of CumulativeNeed:cumulativeNeeds;
array[1..nrSequences] of Sequence:sequences;
array[1..nrDowntimes] of Downtime:downtimes;

% variables
array[1..nrTasks] of var 0..horizon:x;
array[1..nrTasks] of var 0..horizon:ends;
var 0..horizon:objective;

constraint forall(m in 1..nrMachines)
(disjunctive([x[i]|i in 1..nrTasks where tasks[i].machine == machines[m].name]++
			 [downtimes[i].start|i in 1..nrDowntimes where downtimes[i].machine == machines[m].name],
             [tasks[i].duration|i in 1..nrTasks where tasks[i].machine == machines[m].name]++
			 [downtimes[i].duration|i in 1..nrDowntimes where downtimes[i].machine == machines[m].name]));
             
constraint forall(j in 1..nrJobs where jobs[j].noOverlap)
(disjunctive([x[i]|i in 1..nrTasks where tasks[i].job == jobs[j].name],
             [tasks[i].duration|i in 1..nrTasks where tasks[i].job == jobs[j].name]));

constraint forall(i in 1..nrTasks)(ends[i] = x[i]+tasks[i].duration);

constraint forall(s in 1..nrSequences where sequences[s].sequenceType=="EndBeforeStart")
(ends[sequences[s].before]+sequences[s].offset <= x[sequences[s].after]);
constraint forall(s in 1..nrSequences where sequences[s].sequenceType=="StartBeforeStart")
(x[sequences[s].before]+sequences[s].offset <= x[sequences[s].after]);

constraint maximum(objective,ends);

constraint forall(m in 1..nrCumulativeMachines)
(cumulative([x[cumulativeNeeds[k].taskNr]|k in 1..nrCumulativeNeeds where cumulativeNeeds[k].cumulativeMachine == cumulativeMachines[m].name],
             [tasks[cumulativeNeeds[k].taskNr].duration|k in 1..nrCumulativeNeeds where cumulativeNeeds[k].cumulativeMachine == cumulativeMachines[m].name],
             [cumulativeNeeds[k].demand|k in 1..nrCumulativeNeeds where cumulativeNeeds[k].cumulativeMachine == cumulativeMachines[m].name],
             cumulativeMachines[m].limit));
 
% solve
solve minimize(objective);

output [
    json_object([|
        "tasks",showJSON(tasks)|
        "start",showJSON(x) |
        "obj",showJSON(objective) 
   |])
];

include "globals.mzn";
int: nrStages;                        
int: nrJobs;   
int: horizon;                        

array[1..nrJobs,1..nrStages] of int: duration;

array[1..nrJobs,1..nrStages] of var 0..horizon: start;

var 0..horizon:obj;

constraint forall( i in 1..nrJobs, j in 1..nrStages-1)
  (start[i,j+1] >= start[i,j]+duration[i,j]);
    
constraint forall(j in 1..nrStages) 
  (disjunctive([start[i,j] |i in 1..nrJobs],[duration[i,j]| i in 1..nrJobs]));

constraint maximum(obj,[start[i,nrStages]+duration[i,nrStages]|i in 1..nrJobs]);

solve minimize obj;
	



package org.insightcentre.tbischeduling.implementedsolver;

import org.insightcentre.tbischeduling.datamodel.SolverBackend;

public class Options {
    SolverBackend backend;
    int timeout;
    int nrThreads;
    int seed;
    public Options(SolverBackend backend,int timeout,int nrThreads,int seed){
        this.backend = backend;
        this.timeout = timeout;
        this.nrThreads = nrThreads;
        this.seed = seed;
    }

    public SolverBackend getBackend(){
        return backend;
    }

    public int getTimeout(){
        return timeout;
    }
    public int getNrThreads(){
        return nrThreads;
    }
    public int getSeed(){
        return seed;
    }
}

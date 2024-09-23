package org.insightcentre.tbischeduling;

/*
Generated once, should be extended by user
*/

import org.insightcentre.tbischeduling.datamodel.*;
import framework.ApplicationDatasetInterface;
import framework.ApplicationObjectInterface;
import framework.types.IrishCalendar;
public class JfxApp extends GeneratedJfxApp {

// callbacks to add for user interaction that is not generated
// use stubs in GeneratedJFxApp as basis
// callback for ctrl+selection in list
        public void showObject(ApplicationObjectInterface obj){
                super.showObject(obj);
        }

// callback called once at startup to create initial data in application
        @Override
        public ApplicationDatasetInterface minimalDataset() {
                Scenario base = new Scenario();
                IrishCalendar.buildCalendar();
                base.setDirty(false);
                return base;
        }

// main entry point for interactive application
        public static void main(String[] args) {
                launch(args);
        }

}

package autoservice.db;

import autoservice.core.*;

/**
 * This class will be used inside the database to set up backend references 
 * after loading the database from file.
 * @author Dmitriy Naumov
 */
class ServiceAction_BackendAware extends ServiceAction {
    
    public void setBackend (AutoServiceBackend backend) {
        this.backend = backend;
    }
    
    public ServiceAction_BackendAware (AutoServiceBackend backend) {
        super(backend);
    }
}

package autoservice.db;

import autoservice.core.*;

/**
 * This class will be used inside the database to set up backend references 
 * after loading the database from file.
 * @author Dmitriy Naumov
 */
class Car_BackendAware extends Car {
    
    public void setBackend (AutoServiceBackend backend) {
        this.backend = backend;
    }
    
    public void setId (int id) {
        this.id = id;
    }
    
    public Car_BackendAware (AutoServiceBackend backend, int id) {
        super(backend);
        this.id = id;
    }
}

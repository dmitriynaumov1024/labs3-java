package autoservice.db;

import java.util.*;
import autoservice.core.*;

/**
 * Autoservice database
 * @author Dmitriy Naumov
 */
public class AutoServiceDB {
    
    /**
     * Get single instance of database.
     * @return instance of database
     */
    public static AutoServiceDB instance() {
        return _instance;
    }
    
    /**
     * Single instance of the type.
     */
    private static AutoServiceDB _instance = new AutoServiceDB();
    
    /**
     * Default private constructor.
     */
    private AutoServiceDB () { 
        this.cars = new HashMap<>();
        this.owners = new HashMap<>();
        this.workers = new HashMap<>();
    }
    
    private HashMap<Integer, Car> cars; 
    private HashMap<Integer, Person> owners;
    private HashMap<Integer, Worker> workers;
    
}

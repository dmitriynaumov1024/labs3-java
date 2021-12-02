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
        this.cars = new TreeMap<>();
        this.owners = new TreeMap<>();
        this.workers = new TreeMap<>();
        this.licensePlate_carId = new HashMap<>();
        this.phoneNumber_carId = new HashMap<>();
    }
    
    private Map<Integer, Car> cars; 
    private Map<Integer, Person> owners;
    private Map<Integer, Worker> workers;
    private Map<String, Integer> licensePlate_carId;
    private Map<String, Set<Integer>> phoneNumber_carId;
    
    public int addCar (Car value) {
        if (value.licensePlate == null || value.ownerNumber == null)
            throw new RuntimeException("nulls detected");
        int nextId = cars.lastKey + 1;
        cars.put(nextId, value);
        licensePlate_carId.put(value.licensePlate, nextId);
        if(phoneNumber_carId.get(value.ownerNumber) == null) 
            phoneNumber_carId.put(value.ownerNumber, new TreeSet<Integer>());
        phoneNumber_carId.get(value.ownerNumber).add(nextId);
        return nextId;
    }
    
    public int addOwner (Person value) {
        int nextId = owners.lastKey + 1;
        owners.put(nextId, value);
        return nextId;
    }
    
    public int addWorker (Worker value) {
        int nextId = workers.lastKey + 1;
        workers.put(nextId, value);
        return nextId;
    }
    
    public Car getCarById (int id) {
        return cars.get(id);
    }
    
    public Car getCarByLicensePlate (String number) {
        
    }
}

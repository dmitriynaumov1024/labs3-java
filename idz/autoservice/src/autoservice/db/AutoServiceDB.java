package autoservice.db;

import java.util.*;
import java.io.*;
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
        if (_instance == null) 
            _instance = new AutoServiceDB();
        return _instance;
    }
    
    /**
     * Unload the database
     */
    public static void unload() {
        if (_instance != null) 
            _instance.forcedUnload();
        _instance = null;
    }
    
    /**
     * Single instance of the type.
     */
    private static AutoServiceDB _instance;
    
    /**
     * Default private constructor.
     * Constructor is responsible for loading the database into memory and 
     * setting up object links
     */
    private AutoServiceDB () { 
        File dbFile = new File(getDatabasePath());
        if (dbFile.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dbFile))) {
                this.owners = (TreeMap<Integer, Person>)in.readObject();
                this.workers = (TreeMap<Integer, Worker>)in.readObject();
                this.cars = (TreeMap<Integer, Car>)in.readObject();
                this.licensePlate_carId = (HashMap<String, Integer>)in.readObject();
                this.phoneNumber_carId = (HashMap<String, Set<Integer>>)in.readObject();
                this.serviceActions = (TreeMap<Integer, ServiceAction>)in.readObject();
                in.close();
                
                for (ServiceAction act : serviceActions.values()) {
                    if (act == null) continue;
                    act.worker = workers.get(act.workerId);
                    act.car = cars.get(act.carId);
                }
                
            }
            catch (Exception ex) {
                System.out.printf("Exception: %s \n", ex);
            }
        }
        else {
            this.cars = new TreeMap<>();
            this.owners = new TreeMap<>();
            this.workers = new TreeMap<>();
            this.licensePlate_carId = new HashMap<>();
            this.phoneNumber_carId = new HashMap<>();
            this.serviceActions = new TreeMap<>();
        }
    }
    
    /**
     * Unload the database.
     */
    private void forcedUnload () {
        File dbFile = new File(getDatabasePath());
        
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dbFile))) {
            out.writeObject(this.owners);
            out.writeObject(this.workers);
            out.writeObject(this.cars);
            out.writeObject(this.licensePlate_carId);
            out.writeObject(this.phoneNumber_carId);

            for (ServiceAction act : this.serviceActions.values()) {
                if (act == null) continue;
                act.worker = null;
                act.car = null;
            }
            out.writeObject(this.serviceActions);
            out.close();
        }
        catch (Exception ex) {
            System.out.printf("I hate Java \n");
        }
    }
    
    private String getDatabasePath () {
        return "database.dat";
    }
    
    private TreeMap<Integer, Car> cars; 
    private TreeMap<Integer, Person> owners;
    private TreeMap<Integer, Worker> workers;
    private HashMap<String, Integer> licensePlate_carId;
    private HashMap<String, Set<Integer>> phoneNumber_carId;
    private TreeMap<Integer, ServiceAction> serviceActions;
    
    /**
     * Add new car to database
     * @param value
     * @return 
     */
    public int addCar (Car value) {
        if (value.licensePlate == null || value.ownerNumber == null)
            throw new RuntimeException("nulls detected");
        int nextId = cars.isEmpty()? 1 : cars.lastKey() + 1;
        cars.put(nextId, value);
        licensePlate_carId.put(value.licensePlate, nextId);
        if (phoneNumber_carId.get(value.ownerNumber) == null) 
            phoneNumber_carId.put(value.ownerNumber, new TreeSet<>());
        phoneNumber_carId.get(value.ownerNumber).add(nextId);
        return nextId;
    }
    
    /**
     * Add new worker to database
     * @param value
     * @return 
     */
    public int addOwner (Person value) {
        int nextId = owners.isEmpty()? 1 : owners.lastKey() + 1;
        owners.put(nextId, value);
        return nextId;
    }
    
    /**
     * Add new worker to database
     * @param value
     * @return 
     */
    public int addWorker (Worker value) {
        int nextId = workers.isEmpty()? 1 : workers.lastKey() + 1;
        workers.put(nextId, value);
        return nextId;
    }
    
    /**
     * This method is responsible for adding new service action into the database
     * and setting up action's object reference to car and worker.
     * @param act
     * @return 
     */
    public int addServiceAction (ServiceAction act) {
        act.car = cars.get(act.carId);
        act.worker = workers.get(act.workerId);
        
        if (act.car == null) 
            throw new RuntimeException("Service action must be associated with a car");
        
        int nextId = serviceActions.isEmpty()? 1 : serviceActions.lastKey() + 1;
        serviceActions.put(nextId, act);
        return nextId;
    }
    
    /**
     * Get car by id
     * @param id
     * @return 
     */
    public Car getCarById (int id) {
        return cars.get(id);
    }
    
    /**
     * Get car by license plate number.
     * @param number license plate number
     * @return car
     */
    public Car getCarByLicensePlate (String number) {
        Integer id = licensePlate_carId.get(number);
        if (id==null) 
            return null;
        else
            return getCarById(id);
    }
    
    /**
     * Get all cars associated with owner's phone number.
     * @param number owner's phone number
     * @return collection of cars
     */
    public Collection<Car> getCarByOwnerNumber (String number) {
        Set<Integer> ids = phoneNumber_carId.get(number);
        if (ids==null || ids.isEmpty()) {
            return null;
        }
        else {
            List<Car> result = new LinkedList<Car>();
            for (Integer id : ids) {
                result.add(cars.get(id));
            }
            return result;
        }
    }
    
    /**
     * Get service action by id.
     * @param id
     * @return 
     */
    public ServiceAction getServiceActionById (int id) {
        return serviceActions.get(id);
    }
    
    /**
     * Get all service actions by date
     * @param date
     * @return 
     */
    public Collection<ServiceAction> getServiceActionsByDate (Date date) {
        List<ServiceAction> result = new LinkedList<>();
        long neededDay = date.getTime() / 86_400_000;
        for (ServiceAction act : serviceActions.values()) {
            if (act.serviceEndTime.getTime() / 86_400_000 == neededDay) {
                result.add(act);
            }
        }
        return result;
    }
}

package autoservice.db;

import java.util.*;
import java.io.*;
import autoservice.core.*;
import autoservice.util.*;
import java.util.function.Consumer;

/**
 * Autoservice database
 * @author Dmitriy Naumov
 */
public class AutoServiceDB implements AutoServiceBackend {
    
    private File databaseFile;
    
    private TreeMap<Integer, Car> cars; 
    private TreeMap<Integer, Person> owners;
    private TreeMap<Integer, Worker> workers;
    private TreeMap<Integer, CarPart> carParts;
    private TreeMap<Integer, CarAction> carActions;
    private HashMap<String, Integer> licensePlate_carId;
    private HashMap<String, Set<Integer>> phoneNumber_carId;
    private TreeMap<Integer, ServiceAction> serviceActions;
    
    public AutoServiceDB (File file) {
        this.databaseFile = file;
        if (this.load()) {
            System.out.printf("Database was sucessfully loaded from file. \n");
        }
        else {
            System.out.printf("An empty database will be created. \n");
            this.cars = new TreeMap<>();
            this.owners = new TreeMap<>();
            this.workers = new TreeMap<>();
            this.licensePlate_carId = new HashMap<>();
            this.phoneNumber_carId = new HashMap<>();
            this.carParts = new TreeMap<>();
            this.carActions = new TreeMap<>();
            this.serviceActions = new TreeMap<>();
        }
        
    }
    
    public boolean load () {
        if (!databaseFile.exists()) 
            return false;
        
        if (!databaseFile.canRead())
            return false;
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(databaseFile))) {
            this.owners = (TreeMap<Integer, Person>)in.readObject();
            this.workers = (TreeMap<Integer, Worker>)in.readObject();
            this.cars = (TreeMap<Integer, Car>)in.readObject();
            this.licensePlate_carId = (HashMap<String, Integer>)in.readObject();
            this.phoneNumber_carId = (HashMap<String, Set<Integer>>)in.readObject();
            this.carParts = (TreeMap<Integer, CarPart>)in.readObject();
            this.carActions = (TreeMap<Integer, CarAction>)in.readObject();
            this.serviceActions = (TreeMap<Integer, ServiceAction>)in.readObject();
            in.close();
            
            AutoServiceBackend backend = this;
            
            this.cars.entrySet().forEach((Map.Entry<Integer, Car> entry) -> {
                Car_BackendAware car = (Car_BackendAware)entry.getValue();
                car.setBackend(backend);
                car.setId(entry.getKey());
            });
            
            this.serviceActions.entrySet().forEach((Map.Entry<Integer, ServiceAction> entry) -> {
                ((ServiceAction_BackendAware)entry.getValue()).setBackend(backend); 
            });
            
            return true;
        }
        catch (Exception ex) {
            System.out.printf("Exception while loading the database: %s \n", ex);
            return false;
        }
    }
    
    public boolean save () {
        if (databaseFile == null) {
            return false;
        }
        File tmpfile = new File (String.format("database-%d.tmp", new Date().getTime()));
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(tmpfile))){
            out.writeObject(this.owners);
            out.writeObject(this.workers);
            out.writeObject(this.cars);
            out.writeObject(this.licensePlate_carId);
            out.writeObject(this.phoneNumber_carId);
            out.writeObject(this.carParts);
            out.writeObject(this.carActions);
            out.writeObject(this.serviceActions); 
            out.close();
            databaseFile.delete();
            tmpfile.renameTo(databaseFile);
            return true;
        }
        catch (Exception ex) {
            System.out.printf("Exception: %s \n", ex);
            return false;
        }
    }

    public String getFilePath () {
        return this.databaseFile.getAbsolutePath();
    }
    
    public Map<Integer, Car> getCars() {
        return this.cars;
    }
    
    public Map<Integer, Person> getOwners() {
        return this.owners;
    }
    
    public Map<Integer, Worker> getWorkers() {
        return this.workers;
    }
    
    public Map<Integer, CarPart> getCarParts() {
        return this.carParts;
    }
    
    public Map<Integer, CarAction> getCarActions() {
        return this.carActions;
    }
    
    public Map<Integer, ServiceAction> getServiceActions() {
        return this.serviceActions;
    }
    
    public Map<String, Integer> getLicensePlateToCarMapping() {
        return this.licensePlate_carId;
    }
    
    public Map<String, Set<Integer>> getPhoneNumberToCarMapping() {
        return this.phoneNumber_carId;
    }
    
    /**
     * Add new car to database
     * @return key-value pair of Integer and Car
     */
    public KeyValuePair<Integer, Car> newCar () {
        int nextId = cars.isEmpty()? 1 : cars.lastKey() + 1;
        Car car = new Car_BackendAware(this, nextId);
        cars.put(nextId, car);
        return new KeyValuePair<>(nextId, car);
    }
    
    /**
     * Add new owner to database
     * @return key-value pair of Integer and Person
     */
    public KeyValuePair<Integer, Person> newOwner () {
        int nextId = owners.isEmpty()? 1 : owners.lastKey() + 1;
        Person owner = new Person();
        owners.put(nextId, owner);
        return new KeyValuePair<>(nextId, owner);
    }
    
    /**
     * Add new worker to database
     * @return key-value pair of Integer and Worker
     */
    public KeyValuePair<Integer, Worker> newWorker () {
        int nextId = workers.isEmpty()? 1 : workers.lastKey() + 1;
        Worker worker = new Worker();
        workers.put(nextId, worker);
        return new KeyValuePair<>(nextId, worker);
    }
    
    /**
     * Add new car part to database
     * @return key-value pair of Integer and CarPart
     */
    public KeyValuePair<Integer, CarPart> newCarPart () {
        int nextId = carParts.isEmpty()? 1 : carParts.lastKey() + 1;
        CarPart part = new CarPart();
        carParts.put(nextId, part);
        return new KeyValuePair<>(nextId, part);
    }
    
    /**
     * Add new car action to database
     * @return key-value pair of Integer and CarAction
     */
    public KeyValuePair<Integer, CarAction> newCarAction () {
        int nextId = carActions.isEmpty()? 1 : carActions.lastKey() + 1;
        CarAction act = new CarAction();
        carActions.put(nextId, act);
        return new KeyValuePair<>(nextId, act);
    }
    
    /**
     * Add new service action to database
     * @return key-value pair of Integer and ServiceAction
     */
    public KeyValuePair<Integer, ServiceAction> newServiceAction () {
        int nextId = serviceActions.isEmpty()? 1 : serviceActions.lastKey() + 1;
        ServiceAction act = new ServiceAction_BackendAware(this);
        serviceActions.put(nextId, act);
        return new KeyValuePair<>(nextId, act);
    }
    
    /**
     * Get car by id
     * @param id
     * @return 
     */
    public Car getCar (int id) {
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
            return getCar(id);
    }
    
    /**
     * Get all cars associated with owner's phone number.
     * @param number owner's phone number
     * @return collection of cars
     */
    public Collection<Car> getCarByOwnerNumber (String number) {
        Set<Integer> ids = phoneNumber_carId.get(number);
        List<Car> result = new LinkedList<Car>();
        if (ids != null) for (Integer id : ids) {
            result.add(cars.get(id));
        }
        return result;
    }
    
    /**
     * Get service action by id.
     * @param id
     * @return 
     */
    public ServiceAction getServiceAction (int id) {
        return serviceActions.get(id);
    }
    
    /**
     * Get all service actions by date
     * @param date needed date
     * @return 
     */
    public Collection<Map.Entry<Integer, ServiceAction>> getServiceActions (Date date) {
        List<Map.Entry<Integer, ServiceAction>> result = new LinkedList<>();
        long neededDay = date.getTime() / 86_400_000;
        serviceActions.entrySet().stream()
                .filter((kvpair)-> {
                    Date endTime = kvpair.getValue().serviceEndTime;
                    return ((endTime == null) ? false : (endTime.getTime() / 86_400_000 == neededDay));
                })
                .forEach((kvpair)-> result.add(kvpair));
        return result;
    }
    
    /**
     * Get service actions with status
     * @param status needed status
     */
    public Collection<Map.Entry<Integer, ServiceAction>> getServiceActions (ServiceStatus status) {
        List<Map.Entry<Integer, ServiceAction>> result = new LinkedList<>();
        serviceActions.entrySet().stream()
                .filter((kvpair)-> kvpair.getValue().status == status)
                .forEach((kvpair)-> result.add(kvpair));
        return result;
    }
    
    /**
     * Get service actions with status for given worker
     * @param status needed status
     * @param workerId worker id
     */
    public Collection<Map.Entry<Integer, ServiceAction>> getServiceActions (ServiceStatus status, int workerId) {
        List<Map.Entry<Integer, ServiceAction>> result = new LinkedList<>();
        if (!workers.containsKey(workerId)) 
            return result;
        serviceActions.entrySet().stream()
                .filter((kvpair)-> (kvpair.getValue().status == status) && (kvpair.getValue().getWorkerId() == workerId))
                .forEach((kvpair)-> result.add(kvpair));
        return result;
    }
    
    /**
     * Get planned car actions for worker and car
     * @param workerId id of worker
     * @param licensePlate license plate of needed car
     * @return 
     */
    public Collection<Several<CarAction>> getCarActions (int workerId, String licensePlate) {
        List<Several<CarAction>> result = new LinkedList<>();
        if (!workers.containsKey(workerId)) 
            return result;
        Integer carId = licensePlate_carId.get(licensePlate);
        if (carId == null)
            return result;
        serviceActions.entrySet().stream()
                .filter((kvpair)-> {
                    ServiceAction act = kvpair.getValue();
                    return act.status != ServiceStatus.Finished
                        && act.getWorkerId() == workerId 
                        && act.getCarId() == carId;
                })
                .forEach((kvpair)-> result.addAll(kvpair.getValue().getActions()));
        return result;
    }
}

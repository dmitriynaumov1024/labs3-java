package autoservice.core;

import java.util.*;
import autoservice.util.*;

/**
 * Represents a solid autoservice backend.
 * @author Dmitriy Naumov
 */
public interface AutoServiceBackend {
    
    /*
    public boolean load();
    public boolean save();
    */
    
    public Map<Integer, Car> getCars();
    public Map<Integer, Person> getOwners();
    public Map<Integer, Worker> getWorkers();
    public Map<Integer, CarPart> getCarParts();
    public Map<Integer, CarAction> getCarActions();
    public Map<Integer, ServiceAction> getServiceActions();
    
    public Map<String, Integer> getLicensePlateToCarMapping();
    public Map<String, Set<Integer>> getPhoneNumberToCarMapping();
    
    public KeyValuePair<Integer, Car> newCar();
    public KeyValuePair<Integer, Person> newOwner();
    public KeyValuePair<Integer, Worker> newWorker();
    public KeyValuePair<Integer, CarPart> newCarPart();
    public KeyValuePair<Integer, CarAction> newCarAction();
    public KeyValuePair<Integer, ServiceAction> newServiceAction();
}

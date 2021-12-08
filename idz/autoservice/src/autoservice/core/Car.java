package autoservice.core;

import java.util.*;

/**
 * Class that contains info about car.
 * This is backend-aware entity.
 * @author Dmitriy Naumov
 */
public class Car implements java.io.Serializable {
    
    protected transient AutoServiceBackend backend;
    protected transient int id;
    
    public String brand;
    public String model;
    public int manufactureYear;
    protected String licensePlate;
    protected String ownerNumber;
    
    
    public String getLicensePlate() { return this.licensePlate; }
    public String getOwnerNumber() { return this.ownerNumber; }
    
    /**
     * Set license plate number for this car.
     * First, this method removes license plate number from map.
     * Then, puts new license plate number to map. If that key existed before,
     * an exception is thrown.
     * @param value new license plate number
     */
    public void setLicensePlate(String value) {
        if (this.licensePlate != null && this.licensePlate.equals(value)) {
            return;
        }
        if (value == null){
            throw new NullPointerException ("License plate must not be null");
        }
        if (this.licensePlate != null){
            backend.getLicensePlateToCarMapping().remove(this.licensePlate);
        }
        if ((backend.getLicensePlateToCarMapping().putIfAbsent(value, id)) != null){
            throw new IllegalArgumentException (String.format("Backend already contains mapping for license plate '%s'", value));
        }
        this.licensePlate = value;
    }
    
    /**
     * Set owner's phone number for this car and add a mapping to backend.
     * @param value new owner's phone number
     */
    public void setOwnerNumber(String value) {
        if (value.equals(this.ownerNumber)) return;
        
        if (this.ownerNumber != null){
            Set<Integer> s = backend.getPhoneNumberToCarMapping().get(this.ownerNumber);
            if (s != null) s.remove(this.id);
        }
        
        this.ownerNumber = value;
        backend.getPhoneNumberToCarMapping().putIfAbsent(value, new TreeSet<Integer>());
        Set<Integer> s = backend.getPhoneNumberToCarMapping().get(value);
        s.add(this.id);
    }
    
    public Car (AutoServiceBackend backend) {
        this.backend = backend;
    }
    
    @Override public String toString() {
        return String.format (
                "Brand: %s \nModel: %s \nLicense plate: %s\nOwner's number: %s",
                brand, model, licensePlate, ownerNumber
        );
    }
}

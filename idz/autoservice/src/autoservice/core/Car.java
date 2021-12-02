package autoservice.core;

/**
 * Class that contains info about car.
 * @author Dmitriy Naumov
 */
public class Car implements java.io.Serializable {
    public String brand;
    public String model;
    public String licensePlate;
    public String ownerNumber;
    
    @Override public String toString() {
        return String.format (
                "Brand: %s \nModel: %s \nLicense plate: %s\nOwner's number: %s",
                brand, model, licensePlate, ownerNumber
        );
    }
}

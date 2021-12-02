package autoservice.core;

/**
 * Class that contains car part info.
 * @author Dmitriy Naumov
 */
public class CarPart implements java.io.Serializable {
    public String brand;
    public String name;
    public String description;
    public int price;
    
    public CarPart () { }
    
    public CarPart (String brand, String name, String description, int price) {
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}

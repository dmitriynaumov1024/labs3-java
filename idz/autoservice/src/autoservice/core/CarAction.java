package autoservice.core;

/**
 *
 * @author Dmitriy Naumov
 */
public class CarAction implements java.io.Serializable {
    public String description;
    public int price;
    
    public CarAction () { }
    
    public CarAction (String description, int price) {
        this.description = description;
        this.price = price;
    }
}

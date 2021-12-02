package autoserviceclient;

import autoservice.core.*;
import java.io.*;

/**
 * Main class
 * @author Dmitriy Naumov
 */
public class Program {

    public static void main(String[] args) {
        Car car1 = new Car();
        car1.brand = "Tesla";
        car1.model = "Model Y";
        car1.licensePlate = "AP8454EP";
        car1.ownerNumber = "0934420198";
        
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("eee.txt"))){
            os.writeObject(car1);
            os.writeObject(new ServiceAction());
            os.close();
        }
        catch (Exception ex) {
            System.out.printf("Exception : %s \n", ex.getMessage());
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("eee.txt"))){
            Car car2 = (Car)ois.readObject();
            ois.close();
            System.out.println(car1);
            System.out.println(car2);
        }
        catch (Exception ex) {
            System.out.printf("Exception : %s \n", ex.getMessage());
        }
    }
    
}

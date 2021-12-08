package autoserviceclient;

import java.util.Scanner;
import autoservice.core.*;
import autoservice.util.KeyValuePair;

/**
 *
 * @author Dmitriy Naumov
 */
public class ConGet {
    
    private static int getId () {
        System.out.printf("[id]: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
    
    public static KeyValuePair<Integer, Car> getCar () {
        int id = getId();
        return new KeyValuePair<>(id, Global.getDatabase().getCar(id));
    }
    
    public static KeyValuePair<Integer, Person> getOwner () {
        int id = getId();
        return new KeyValuePair<>(id, Global.getDatabase().getOwners().get(id));
    } 
    
    public static KeyValuePair<Integer, Worker> getWorker () {
        int id = getId();
        return new KeyValuePair<>(id, Global.getDatabase().getWorkers().get(id));
    }
    
    public static KeyValuePair<Integer, CarPart> getCarPart () {
        int id = getId();
        return new KeyValuePair<>(id, Global.getDatabase().getCarParts().get(id));
    }
    
    public static KeyValuePair<Integer, CarAction> getCarAction () {
        int id = getId();
        return new KeyValuePair<>(id, Global.getDatabase().getCarActions().get(id));
    }
    
    public static KeyValuePair<Integer, ServiceAction> getServiceAction () {
        int id = getId();
        return new KeyValuePair<>(id, Global.getDatabase().getServiceActions().get(id));
    }
}

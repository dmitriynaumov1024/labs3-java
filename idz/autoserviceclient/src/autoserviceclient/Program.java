package autoserviceclient;

import autoservice.core.*;
import autoservice.db.*;
import autoservice.util.*;

import java.util.*;

/**
 * Main class
 * @author Dmitriy Naumov
 */
public class Program {

    public static void main(String[] args) {
        AutoServiceDB database = AutoServiceDB.instance();
        
        for (Map.Entry<Integer, Car> car : database.getCars()) {
            System.out.printf("id : %d \n", car.getKey());
            System.out.println(car.getValue().toString());
        }
        System.out.println();
        
        for (Map.Entry<Integer, ServiceAction> act : database.getServiceActions(ServiceStatus.Finished)) {
            System.out.printf("id : %d \n", act.getKey());
            System.out.println(act.getValue().toString());
        }
        System.out.println();
        AutoServiceDB.unload();
    }
    
    public void addExampleData () {
        AutoServiceDB database = AutoServiceDB.instance();
        
        Car car1 = new Car();
        car1.brand = "Tesla";
        car1.model = "Model Y";
        car1.licensePlate = "AP8454EP";
        car1.ownerNumber = "0934420198";
        
        int car1_id = database.addCar(car1);
        System.out.println(database.getCarById(car1_id));
        
        Worker worker1 = new Worker();
        worker1.surname = "Jameson";
        worker1.name = "Susan";
        worker1.preferences = new LinkedList<CarTag>();
        worker1.preferences.add(CarTag.PetrolEngine);
        worker1.preferences.add(CarTag.Generic);
        worker1.phoneNumber = "7023239293";
        worker1.hourSalary = 1000;
        
        int worker1_id = database.addWorker(worker1);
        
        System.out.println();
        
        ServiceAction action1 = new ServiceAction();
        action1.carId = car1_id;
        action1.workerId = worker1_id;
        action1.servicedParts = new LinkedList<Several<CarPart>>();
        action1.servicedParts.add (
            new Several<CarPart> (
                1, new CarPart (
                    "Tesla",
                    "Front tire",
                    "Front tire, diameter 19\"",
                    5600
                )
            )
        );
        action1.variant = ServiceVariant.RegularService;
        
        int act1_id = database.addServiceAction(action1);
        
        System.out.println(database.getServiceActionById(act1_id));
        System.out.printf("Price : %d", database.getServiceActionById(act1_id).getPrice());
    }
    
}

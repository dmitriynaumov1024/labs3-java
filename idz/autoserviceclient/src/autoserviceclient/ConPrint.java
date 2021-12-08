package autoserviceclient;

import autoservice.core.*;
import autoservice.util.*;
import java.util.List;

/**
 * Contains a set of methods to print autoservice objects
 * @author Dmitriy Naumov
 */
public class ConPrint {
    
    public static void printCar (Car car) {
        if (car == null) {
            System.out.printf("null \n");
            return;
        }
        System.out.printf("Car         : %s %s \n", car.brand, car.model);
        System.out.printf("Man.year    : %d \n", car.manufactureYear);
        System.out.printf("Lic.plate   : %s \n", car.getLicensePlate());
        System.out.printf("Owner phone : %s \n", car.getOwnerNumber());
    }
    
    public static void printPerson (Person person) {
        if (person == null) {
            System.out.printf("null \n");
            return;
        }
        System.out.printf("Name  : %s %s \n", person.name, person.surname);
        System.out.printf("Phone : %s \n", person.phoneNumber);
    }
    
    public static void printWorker (Worker worker) {
        if (worker == null) {
            System.out.printf("null \n");
            return;
        }
        System.out.printf("Name        : %s %s \n", worker.name, worker.surname);
        System.out.printf("Phone       : %s \n", worker.phoneNumber);
        System.out.printf("Hour salary : %d \n", worker.hourSalary);
    }
    
    public static void printCarPart (CarPart part) {
        if (part == null) {
            System.out.printf("null \n");
            return;
        }
        System.out.printf("Brand         : %s \n", part.brand);
        System.out.printf("Name          : %s \n", part.name);
        System.out.printf("Description   : %s \n", part.description);
        System.out.printf("Price         : %s \n", part.price);
    }
    
    public static void printCarAction (CarAction act) {
        if (act == null) {
            System.out.printf("null \n");
            return;
        }
        System.out.printf("Description   : %s \n", act.description);
        System.out.printf("Price         : %s \n", act.price);
    }
    
    /**
     * Print service action
     * @param action 
     */
    public static void printServiceAction (ServiceAction action) {
        if (action == null) {
            System.out.printf("null \n");
            return;
        }
        System.out.printf("Apply date      : %s \n", DateFormats.yyyy_mm_dd().format(action.applyDate));
        Car car = action.getCar();
        if (car != null) {
            System.out.printf("Car             : %s %s [%d]\n", car.brand, car.model, car.manufactureYear);
            System.out.printf("License plate   : %s \n", car.getLicensePlate());
            System.out.printf("Owner's phone   : %s \n", car.getOwnerNumber());
        }
        
        System.out.printf("Service variant : %s \n", action.variant);
        System.out.printf("Status          : %s \n", action.status);
        Worker worker = action.getWorker();
        if (worker != null) 
            System.out.printf("Assigned worker : %s %s \n", worker.name, worker.surname);
        if (action.serviceStartTime != null) 
            System.out.printf("Start time      : %s \n", DateFormats.yyyy_mm_dd_time().format(action.serviceStartTime));
        if (action.serviceEndTime != null)
            System.out.printf("End time        : %s \n", DateFormats.yyyy_mm_dd_time().format(action.serviceEndTime));
        if (action.status == ServiceStatus.Finished) 
            System.out.printf("Work hours      : %d \n", action.getWorkHours());
        
        List<Several<CarPart>> newParts = action.getNewParts();
        if (newParts == null || newParts.isEmpty()) {
            System.out.printf("No new parts.\n");
        }
        else {
            System.out.printf("New parts: \n");
            for (Several<CarPart> item : newParts) {
                System.out.printf("- %s by %s\n", item.value.name, item.value.brand);
                System.out.printf("  price per 1 : %d \n", item.value.price);
                System.out.printf("  amount      : %d \n", item.count);
            }
        }
        
        List<Several<CarAction>> actions = action.getActions();
        if (actions == null || actions.isEmpty()) {
            System.out.printf("No actions yet.\n");
        }
        else {
            System.out.printf("Actions: \n");
            for (Several<CarAction> item : actions) {
                System.out.printf("- %s \n", item.value.description);
                System.out.printf("  price per 1 : %d \n", item.value.price);
                System.out.printf("  count       : %d \n", item.count);
            }
        }
        
        System.out.printf("%s price  : %d \n", (action.status == ServiceStatus.Finished ? "Total" : "Estimated"), action.getPrice());
    }
    
    
    public static void printServiceActionShort (ServiceAction action) {
        if (action == null) {
            System.out.printf("null \n");
            return;
        }
        System.out.printf("Apply date      : %s \n", DateFormats.yyyy_mm_dd().format(action.applyDate));
        Car car = action.getCar();
        if (car != null) {
            System.out.printf("Car             : %s %s [%d]\n", car.brand, car.model, car.manufactureYear);
            System.out.printf("License plate   : %s \n", car.getLicensePlate());
            System.out.printf("Owner's phone   : %s \n", car.getOwnerNumber());
        }
        
        System.out.printf("Service variant : %s \n", action.variant);
        System.out.printf("Status          : %s \n", action.status);
        Worker worker = action.getWorker();
        if (worker != null) 
            System.out.printf("Worker          : %s %s \n", worker.name, worker.surname);
        System.out.printf("%s price  : %d \n", (action.status == ServiceStatus.Finished ? "Total" : "Estimated"), action.getPrice());
    }
}

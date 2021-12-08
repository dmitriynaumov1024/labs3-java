package autoserviceclient;

import autoservice.core.*;
import autoservice.db.AutoServiceDB;
import autoservice.util.*;

import java.util.*;

/**
 *
 * @author Dmitriy Naumov
 */
public class Operations {
    
    public static Map<String, ClientOperation> getMapping() {
        if (operationMapping == null) {
            operationMapping = new HashMap<>();
            
            operationMapping.put("Car add", addCar);
            operationMapping.put("Owner add", addOwner);
            operationMapping.put("Worker add", addWorker);
            operationMapping.put("CarPart add", addCarPart);
            operationMapping.put("CarAction add", addCarAction);
            operationMapping.put("ServiceAction add", addServiceAction);
            
            operationMapping.put("Car edit", editCar);
            operationMapping.put("Owner edit", editOwner);
            operationMapping.put("Worker edit", editWorker);
            operationMapping.put("CarPart edit", editCarPart);
            operationMapping.put("ServiceAction edit", editServiceAction);
            
            operationMapping.put("Car by id", showCarById);
            operationMapping.put("Car by license plate", showCarByLicensePlate);
            operationMapping.put("Car by phone number", showCarByOwnerNumber);
            operationMapping.put("Owner by id", showOwner);
            operationMapping.put("Worker by id", showWorker);
            operationMapping.put("CarPart by id", showCarPart);
            operationMapping.put("CarAction by id", showCarAction);
            operationMapping.put("CarAction for worker", showCarActionForWorkerAndCar);
            operationMapping.put("ServiceAction by id", showServiceAction);
            operationMapping.put("ServiceAction by worker", showServiceActionByWorker);
            operationMapping.put("ServiceAction today", showServiceActionToday);
            
            operationMapping.put("ServiceAction start", startServiceAction);
            operationMapping.put("ServiceAction finish", finishServiceAction);
            
            operationMapping.put("stats", showStats);
            operationMapping.put("help", help);
            operationMapping.put("save", save);
            operationMapping.put("exit", exit);
        }
        
        return operationMapping;
    }
    
    public static Map<String, ClientOperation> operationMapping;
    
    public static ClientOperation addCar = ()-> {
        KeyValuePair<Integer, Car> newEntry = Global.getDatabase().newCar();
        System.out.printf("New car with id=%d\n", newEntry.key);
        ConEdit.editCar(newEntry.value);
    };
    
    public static ClientOperation addOwner = ()-> {
        KeyValuePair<Integer, Person> newEntry = Global.getDatabase().newOwner();
        System.out.printf("New owner with id=%d\n", newEntry.key);
        ConEdit.editPerson(newEntry.value); 
    };
    
    public static ClientOperation addWorker = ()-> {
        KeyValuePair<Integer, Worker> newEntry = Global.getDatabase().newWorker();
        System.out.printf("New worker with id=%d\n", newEntry.key);
        ConEdit.editWorker(newEntry.value); 
    };
    
    public static ClientOperation addCarPart = ()-> {
        KeyValuePair<Integer, CarPart> newEntry = Global.getDatabase().newCarPart();
        System.out.printf("New car part with id=%d\n", newEntry.key);
        ConEdit.editCarPart(newEntry.value);
    };
    
    public static ClientOperation addCarAction = ()-> {
        KeyValuePair<Integer, CarAction> newEntry = Global.getDatabase().newCarAction();
        System.out.printf("New car action with id=%d\n", newEntry.key);
        ConEdit.editCarAction(newEntry.value);
    };
    
    public static ClientOperation addServiceAction = ()-> {
        KeyValuePair<Integer, ServiceAction> newEntry = Global.getDatabase().newServiceAction();
        System.out.printf("New service action with id=%d\n", newEntry.key);
        ConEdit.editServiceAction(newEntry.value);
    };
    
    public static ClientOperation editCar = ()-> {
        KeyValuePair<Integer, Car> car = ConGet.getCar();
        if (car.value == null) {
            System.out.printf("Database does not contain car with id=%d \n", car.key);
        }
        else {
            System.out.printf("Editing car with id=%d. \nPrevious values: \n", car.key);
            ConPrint.printCar(car.value);
            ConEdit.editCar(car.value);
        }
    };
    
    public static ClientOperation editOwner = ()-> {
        KeyValuePair<Integer, Person> owner = ConGet.getOwner();
        if (owner.value == null) {
            System.out.printf("Database does not contain owner with id=%d\n", owner.key);
        }
        else {
            System.out.printf("Editing owner with id=%d. \nPrevious values: \n", owner.key);
            ConPrint.printPerson(owner.value);
            ConEdit.editPerson(owner.value);
        }
    };
    
    public static ClientOperation editWorker = ()-> {
        KeyValuePair<Integer, Worker> worker = ConGet.getWorker();
        if (worker.value == null) {
            System.out.printf("Database does not contain worker with id=%d\n", worker.key);
        }
        else {
            System.out.printf("Editing worker with id=%d. \nPrevious values: \n", worker.key);
            ConPrint.printWorker(worker.value); 
            ConEdit.editWorker(worker.value);
        }
    };
    
    public static ClientOperation editCarPart = ()-> {
        KeyValuePair<Integer, CarPart> part = ConGet.getCarPart();
        if (part.value == null) {
            System.out.printf("Database does not contain car part with id=%d\n", part.key);
        }
        else {
            System.out.printf("Editing car part with id=%d \nPrevious values: \n", part.value);
            ConPrint.printCarPart(part.value);
            ConEdit.editCarPart(part.value);
        }
    };
    
    public static ClientOperation editCarAction = ()-> {
        KeyValuePair<Integer, CarAction> act = ConGet.getCarAction();
        if (act.value == null) {
            System.out.printf("Database does not contain car action with id=%d\n", act.key);
        }
        else {
            System.out.printf("Editing car action with id=%d \nPrevious values: \n", act.key);
            ConPrint.printCarAction(act.value); 
            ConEdit.editCarAction(act.value);
        }
    };
    
    public static ClientOperation editServiceAction = ()-> {
        KeyValuePair<Integer, ServiceAction> act = ConGet.getServiceAction();
        if (act.value == null) {
            System.out.printf("Database does not contain service action with id=%d\n", act.key);
        }
        else {
            System.out.printf("Editing service action with id=%d \nPrevious values: \n", act.key);
            ConPrint.printServiceAction(act.value); 
            ConEdit.editServiceAction(act.value);
        }
    };
    
    public static ClientOperation showCarById = ()-> {
        KeyValuePair<Integer, Car> car = ConGet.getCar();
        if (car.value == null) {
            System.out.printf("Database does not contain car with id=%d \n", car.key);
        }
        else {
            System.out.printf("Car with id=%d \n", car.key);
            ConPrint.printCar(car.value);
        }
    };
    
    public static ClientOperation showCarByLicensePlate = ()-> {
        System.out.printf("[license plate]: ");
        Scanner sc = new Scanner(System.in);
        String lp = sc.nextLine().trim();
        Car car = Global.getDatabase().getCarByLicensePlate(lp);
        if (car == null) {
            System.out.printf("Database does not contain car with license plate '%s' \n", lp);
        }
        else {
            System.out.printf("Found Car with license plate '%s' \n", lp);
            ConPrint.printCar(car);
        }
    };
    
    public static ClientOperation showCarByOwnerNumber = ()-> {
        System.out.printf("[phone number]: ");
        Scanner sc = new Scanner(System.in);
        String phone = sc.nextLine().trim();
        Collection<Car> cars = Global.getDatabase().getCarByOwnerNumber(phone);
        if (cars == null) {
            System.out.printf("Database has no cars with owner's phone '%s' \n", phone);
        }
        else {
            System.out.printf("Found %d car(s)\n", cars.size());
            for (Car car : cars) {
                System.out.println();
                ConPrint.printCar(car);
            }
        }
    };
    
    public static ClientOperation showWorker = ()-> {
        KeyValuePair<Integer, Worker> worker = ConGet.getWorker();
        if (worker.value == null) {
            System.out.printf("Database does not contain worker with id=%d \n", worker.key);
        }
        else {
            System.out.printf("Worker with id=%d \n", worker.key);
            ConPrint.printWorker(worker.value);
        }
    };
    
    public static ClientOperation showOwner = ()-> {
        KeyValuePair<Integer, Person> owner = ConGet.getOwner();
        if (owner.value == null) {
            System.out.printf("Database does not contain owner with id=%d \n", owner.key);
        }
        else {
            System.out.printf("Owner with id=%d \n", owner.key);
            ConPrint.printPerson(owner.value);
        }
    };
    
    public static ClientOperation showCarPart = ()-> {
        KeyValuePair<Integer, CarPart> part = ConGet.getCarPart();
        if (part.value == null) {
            System.out.printf("Database does not contain car part with id=%d \n", part.key);
        }
        else {
            System.out.printf("Car part with id=%d \n", part.key);
            ConPrint.printCarPart(part.value);
        }
    };
    
    public static ClientOperation showCarAction = ()-> {
        KeyValuePair<Integer, CarAction> act = ConGet.getCarAction();
        if (act.value == null) {
            System.out.printf("Database does not contain car action with id=%d\n", act.key);
        }
        else {
            System.out.printf("Car action with id=%d \n", act.key);
            ConPrint.printCarAction(act.value); 
        }
    };
    
    public static ClientOperation showCarActionForWorkerAndCar = ()-> {
        Scanner sc = new Scanner(System.in);
        System.out.printf("[worker id]: ");
        int id = 0;
        while(true) try { 
            id = Integer.parseInt(sc.nextLine().trim());
            break;
        } 
        catch (Exception ex) {
            System.out.printf("Exception: %s \n", ex.getMessage());
        }
        
        System.out.printf("[license plate]: ");
        String lp = sc.nextLine().trim();
        
        Collection<Several<CarAction>> actions = Global.getDatabase().getCarActions(id, lp);
        if (actions.isEmpty()) {
            System.out.printf("No actions found.\n");
        }
        else {
            System.out.printf("Car actions for car with license plate %s: \n", lp);
            for (Several<CarAction> act : actions) {
                System.out.printf("- Task : %s \n", act.value.description);
                System.out.printf("  %d time(s) \n", act.count);
            }
        }
    };
    
    public static ClientOperation showServiceAction = ()-> {
        KeyValuePair<Integer, ServiceAction> act = ConGet.getServiceAction();
        if (act.value == null) {
            System.out.printf("Database does not contain service action with id=%d\n", act.key);
        }
        else {
            System.out.printf("Service action with id=%d \n", act.key);
            ConPrint.printServiceAction(act.value); 
        }
    };
    
    public static ClientOperation showServiceActionByWorker = ()-> {
        Scanner sc = new Scanner(System.in);
        System.out.printf("[worker id]: ");
        int id = sc.nextInt(); 
        
        Collection<Map.Entry<Integer, ServiceAction>> actions = Global.getDatabase().getServiceActions(ServiceStatus.Planned, id);
        Worker w = Global.getDatabase().getWorkers().get(id);
        if (actions.isEmpty()) {
            System.out.printf("No actions found.\n");
        }
        else {
            System.out.printf("Planned service actions for worker %s %s: \n", w.name, w.surname);
            for (Map.Entry<Integer, ServiceAction> act : actions) {
                System.out.println();
                ConPrint.printServiceAction(act.getValue()); 
            }
        }
    };
    
    public static ClientOperation showServiceActionToday = ()-> {
        
        Collection<Map.Entry<Integer, ServiceAction>> actions = Global.getDatabase().getServiceActions(new Date());
        if (actions.isEmpty()) {
            System.out.printf("No actions were finished today.\n");
        }
        else {
            System.out.printf("Today, %d service actions were finished", actions.size());
            for (Map.Entry<Integer, ServiceAction> act : actions) {
                System.out.println();
                ConPrint.printServiceActionShort(act.getValue());
            }
        }
    };
    
    public static ClientOperation startServiceAction = ()-> {
        KeyValuePair<Integer, ServiceAction> act = ConGet.getServiceAction();
        if (act.value == null) {
            System.out.printf("Database does not contain service action with id=%d\n", act.key);
        }
        else {
            act.value.startService();
            System.out.printf("Service action with id=%d was started just now.\n", act.key);
        }
    };
    
    public static ClientOperation finishServiceAction = ()-> {
        KeyValuePair<Integer, ServiceAction> act = ConGet.getServiceAction();
        if (act.value == null) {
            System.out.printf("Database does not contain service action with id=%d\n", act.key);
        }
        else {
            act.value.finishService();
            System.out.printf("Service action with id=%d was finished just now.\n", act.key);
        }
    };
    
    public static ClientOperation showStats = () -> {
        AutoServiceDB db = Global.getDatabase();
        System.out.printf("Stats: \n");
        System.out.printf("  %d owners \n", db.getOwners().size());
        System.out.printf("  %d workers \n", db.getWorkers().size());
        System.out.printf("  %d cars \n", db.getCars().size());
        System.out.printf("  %d car parts \n", db.getCarParts().size());
        System.out.printf("  %d car actions \n", db.getCarActions().size());
        System.out.printf("  %d service actions \n", db.getServiceActions().size());
    };
    
    public static ClientOperation help = ()-> {
        System.out.printf("Known commands: \n");
        for (String key : getMapping().keySet()) {
            System.out.printf("  %s \n", key);
        }
        System.out.printf("-----\n");
    };
    
    public static ClientOperation save = ()-> {
        if (Global.getDatabase().save())
            System.out.printf("Database saved successfully. \n");
        else 
            System.out.printf("Cannot save the database. \n");
    };
    
    public static ClientOperation exit = ()-> {
        System.exit(0);
    };
    
}

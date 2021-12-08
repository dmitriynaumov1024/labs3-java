package autoserviceclient;

import java.util.*;

import autoservice.core.*;
import autoservice.util.*;

/**
 * Provides methods to edit existing objects.
 * @author Dmitriy Naumov
 */
public class ConEdit {
    
    /**
     * Edit car
     * @param car 
     */
    public static void editCar (Car car) {
        Scanner sc = new Scanner(System.in);
        
        System.out.printf("[brand]: ");
        car.brand = sc.nextLine().trim();
        
        System.out.printf("[model]: ");
        car.model = sc.nextLine().trim();
        
        while (true) try {
            System.out.printf("[manufact.year]: ");
            int year = Integer.parseInt(sc.nextLine().trim());
            if (year > 1900 && year <= Global.currentYear){
                car.manufactureYear = year;
                break;
            }
            else {
                System.out.printf("Year has to be in [1900..%d] range \n", Global.currentYear);
            }
        }
        catch (Exception ex) {
            System.out.printf("Exception: %s \n", ex.getMessage());
        }
        
        
        while (true) try {
            System.out.printf("[lic.plate]: ");
            car.setLicensePlate(sc.nextLine().trim());
            break;
        }
        catch (Exception ex) {
            System.out.printf("Exception: %s \n", ex.getMessage());
        }
        
        while (true) try {
            System.out.printf("[owner's phone]: ");
            car.setOwnerNumber(sc.nextLine().trim());
            break;
        }
        catch (Exception ex) {
            System.out.printf("Exception: %s \n", ex.getMessage());
        }
    }
    
    /**
     * Edit a person.
     * @param person Person object to be edited 
     */
    public static void editPerson (Person person) {
        Scanner sc = new Scanner(System.in);
        
        System.out.printf("[first name]: ");
        person.name = sc.nextLine().trim();
        
        System.out.printf("[surname]: ");
        person.surname = sc.nextLine().trim();
        
        System.out.printf("[phone]: ");
        person.phoneNumber = sc.nextLine().trim();
        
    }
    
    /**
     * Edit worker. 
     * Note: editPerson() is used under the hood.
     * Note: Worker.preferences cannot be edited yet.
     * @param worker Worker object to be edited
     */
    public static void editWorker (Worker worker) {
        editPerson(worker);
        
        Scanner sc = new Scanner(System.in);
        
        System.out.printf("[hour salary]: ");
        while (true) try {
            int s = Integer.parseInt(sc.nextLine().trim());
            worker.hourSalary = s;
            if (s > 0) break;
        }
        catch (Exception ex) {
            System.out.printf("Exception: %s \n", ex.getMessage());
        }
    }
    
    /**
     * Edit car part
     * @param part 
     */
    public static void editCarPart (CarPart part) {
        Scanner sc = new Scanner(System.in);
        
        System.out.printf("[brand]: ");
        part.brand = sc.nextLine().trim();
        
        System.out.printf("[name]: ");
        part.name = sc.nextLine().trim();
        
        System.out.printf("[description]: ");
        part.description = sc.nextLine().trim();
        
        System.out.printf("[price]: ");
        while (true) try {
            int s = Integer.parseInt(sc.nextLine().trim());
            part.price = s;
            if (s > 0) break;
            System.out.printf("Price should be greater than 0");
        }
        catch (Exception ex) {
            System.out.printf("Exception: %s \n", ex.getMessage());
        }
    }
    
    /**
     * Edit car action
     * @param act 
     */
    public static void editCarAction (CarAction act) {
        Scanner sc = new Scanner(System.in);
        
        System.out.printf("[description]: ");
        act.description = sc.nextLine().trim();
        
        System.out.printf("[price]: ");
        while (true) try {
            int s = Integer.parseInt(sc.nextLine().trim());
            act.price = s;
            if (s > 0) break;
            System.out.printf("Price should be greater than 0");
        }
        catch (Exception ex) {
            System.out.printf("Exception: %s \n", ex.getMessage());
        }
    }
    
    /**
     * Edit service action - the most complex thing so far
     * @param action 
     */
    public static void editServiceAction (ServiceAction action) {
        Scanner sc = new Scanner(System.in);
        if (action == null){
            System.out.printf("Action is null. \n");
            return;
        }
        if (action.status == ServiceStatus.Finished) {
            System.out.printf("Action is finished and cannot be modified. \n");
            return;
        }
        boolean loop = true;
        while (loop) {
            System.out.printf("[What to edit? worker / car / servicetype / parts / actions / q]: ");
            String command = sc.nextLine().trim().toLowerCase();
            switch (command) {
                case "worker":
                    while (true) try {
                        System.out.printf("[worker id (~ to discard)]: ");
                        String s = sc.nextLine().trim();
                        if (s.contains("~")) 
                            break;
                        int id = Integer.parseInt(s);
                        action.setWorkerId(id);
                        break;
                    }
                    catch (Exception ex) {
                        System.out.printf("Exception: %s \n", ex.getMessage());
                    }
                    break;
                    
                case "car":
                    while (true) try {
                        System.out.printf("[car id (~ to discard)]: "); 
                        String s = sc.nextLine().trim();
                        if (s.contains("~")) 
                            break;
                        int id = Integer.parseInt(s);
                        action.setCarId(id);
                        break;
                    }
                    catch (Exception ex) {
                        System.out.printf("Exception: %s \n", ex.getMessage());
                    }
                    break;
                    
                case "servicetype":
                    System.out.printf("[service type (1 - diagnostics, 2 - regular service, 3 - after crash)]: "); 
                    String s = sc.nextLine().trim();
                    if (s.length()>0) {
                        switch (s.charAt(0)){
                            case '1':
                                action.variant = ServiceVariant.Diagnostics;
                                break;
                            case '2':
                                action.variant = ServiceVariant.RegularService;
                                break;
                            case '3':
                                action.variant = ServiceVariant.AfterCrashService;
                                break;
                            default: 
                                break;
                        }
                    }
                    break;
                    
                case "parts":
                    while (true) try {
                        System.out.printf("Currently, %d part names \n", action.getPartCount());
                        System.out.printf("[new part id (~ to discard)]: "); 
                        String sid = sc.nextLine().trim();
                        if (sid.contains("~")) 
                            break;
                        System.out.printf("[count (~ to discard)]: "); 
                        String scount = sc.nextLine().trim();
                        if (scount.contains("~")) 
                            break;
                        int id = Integer.parseInt(sid);
                        int count = Integer.parseInt(scount);
                        ConPrint.printCarPart(Global.getDatabase().getCarParts().get(id));
                        System.out.printf("[add this x %d? yes / ~]: ", count); 
                        if (sc.nextLine().trim().toLowerCase().startsWith("yes")) 
                            action.addNewPartId(count, id);
                        break;
                    }
                    catch (Exception ex) {
                        System.out.printf("Exception: %s \n", ex.getMessage());
                    }
                    break;
                    
                case "actions":
                    while (true) try {
                        System.out.printf("Currently, %d action names \n", action.getPartCount());
                        System.out.printf("[action id (~ to discard)]: "); 
                        String sid = sc.nextLine().trim();
                        if (sid.contains("~")) 
                            break;
                        System.out.printf("[count (~ to discard)]: "); 
                        String scount = sc.nextLine().trim();
                        if (scount.contains("~")) 
                            break;
                        int id = Integer.parseInt(sid);
                        int count = Integer.parseInt(scount);
                        ConPrint.printCarAction(Global.getDatabase().getCarActions().get(id));
                        System.out.printf("[add this x %d? yes / ~]: ", count); 
                        if (sc.nextLine().trim().toLowerCase().startsWith("yes")) 
                            action.addActionId(count, id);
                        break;
                    }
                    catch (Exception ex) {
                        System.out.printf("Exception: %s \n", ex.getMessage());
                    }
                    break;
                    
                case "q":
                    loop = false;
                    
                default:
                    break;
            }
        }
    }
}

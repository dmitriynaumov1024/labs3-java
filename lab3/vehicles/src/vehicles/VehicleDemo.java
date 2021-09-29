package vehicles;

import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Dmitriy Naumov
 */
public class VehicleDemo {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Locale.setDefault(Locale.ENGLISH);
        
        Vehicle myCar = new Vehicle(0x1FFFFF, new Date(2005, 10, 01), "Audi", "Dmitriy Naumov", EngineType.Combustion, 540.0);
        
        System.out.printf("Brand of my car      : %s \n", myCar.get_brand());
        System.out.printf("My car's power       : %.1f hp \n", myCar.get_power());
        System.out.printf("My car's engine type : %s \n", myCar.get_engineType().toString());
        System.out.printf("Who's the owner?     : %s \n", myCar.get_ownerName());
        
        System.out.printf("I'll replace my car's engine. \n");
        
        try { myCar.set_engine(EngineType.Unknown, 580.0); }
        catch (Exception ex){ System.out.printf("Something went wrong: %s", ex.getMessage()); return; }
        
        System.out.printf("My car's power       : %.1f hp \n", myCar.get_power());
        System.out.printf("My car's engine type : %s \n", myCar.get_engineType().toString());
        
        System.out.printf("How's my car doing? I'll check if it's safe. \n");
        
        myCar.set_currentSpeed(48.0);
        
        double speed = myCar.get_currentSpeed();
        if(speed==0){
            System.out.printf("My car is staying still with %.1f km/h speed \n", myCar.get_currentSpeed());
        }
        else {
            System.out.printf("Oh god! My car is stolen and is currently going with %.1f km/h speed! \n", myCar.get_currentSpeed());
        }
        
    }
    
}

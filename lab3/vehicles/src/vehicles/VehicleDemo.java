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
    public static void main(String[] args) {
        
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
        
    }
    
}

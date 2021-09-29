package vehicles;

import java.util.Date;

/**
 * Represents a vehicle.
 * @author Dmitriy Naumov
 */
public class Vehicle {
    protected long id;
    protected Date assemblyDate;
    protected String brand;
    protected String ownerName;
    protected EngineType engineType;
    protected double power;
    protected double currentSpeed;
    protected double directionAngle;
    
    public Vehicle(long id, Date assemblyDate, String brand, String ownerName, 
                   EngineType engineType, double power)
    {
        this.id = id;
        this.assemblyDate = assemblyDate;
        this.brand = brand;
        this.ownerName = ownerName;
        this.engineType = engineType;
        this.power = power;
    }
    
    public long get_id(){
        return this.id;
    }
    
    public Date get_assemblyDate(){
        return this.assemblyDate;
    }
    
    public String get_brand(){
        return this.brand;
    }
    
    public String get_ownerName(){
        return this.ownerName;
    }
    
    public void set_ownerName(String value){
        this.ownerName = value;
    }
    
    public EngineType get_engineType(){
        return this.engineType;
    }
    
    public double get_power(){
        return this.power;
    }
    
    public void set_engine(EngineType type, double power) throws Exception {
        if(power > 0) {
            this.engineType = type;
            this.power = power;
        }
        else {
            throw new Exception("power must be greater than 0");
        }
    }
    
    public double get_currentSpeed(){
        return this.currentSpeed;
    }
    
    public void set_currentSpeed(double value) throws Exception {
        if (value >= 0){
            this.currentSpeed = value;
        }
        else {
            throw new Exception("speed must be 0 or greater");
        }
    }
    
    public double get_directionAngle(){
        return this.directionAngle;
    }
    
    public void set_directionAngle(double value) throws Exception {
        if (value == Double.POSITIVE_INFINITY || value == Double.NEGATIVE_INFINITY || value == Double.NaN){
            throw new Exception("");
        }
        else {
            this.directionAngle = Math.IEEEremainder(value, 180.0);
        }
    }
}

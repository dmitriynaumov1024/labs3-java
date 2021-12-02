package autoservice.core;

import java.util.*;
import autoservice.util.*;

/**
 *
 * @author Dmitriy Naumov
 */
public class ServiceAction implements java.io.Serializable {
    public Date applyDate;
    public Car car;
    public Worker worker;
    public ServiceVariant variant;
    public List<Several<CarPart>> servicedParts;
    public List<Several<CarPart>> newParts;
    public Date serviceStartTime;
    public Date serviceEndTime;
    public ServiceStatus status;
    public String notes;
    
    public int workerId;
    public int carId;
    
    public ServiceAction () {
        this.applyDate = new Date();
        this.status = ServiceStatus.Planned;
    }
    
    public int getPrice () {
        int result = 0;
        
        if (newParts != null) for (Several<CarPart> itemStack : newParts) {
            result += itemStack.count * itemStack.value.price;
        }
        if (servicedParts != null) for (Several<CarPart> itemStack : servicedParts) {
            result += itemStack.count * itemStack.value.price / 5;
        }
        return result + ((worker == null)? 0 : getWorkHours() * worker.hourSalary);
    }
    
    public void startService () {
        this.serviceStartTime = new Date();
        this.status = ServiceStatus.InProgress;
    }
    
    public void finishService () {
        this.serviceEndTime = new Date();
        this.status = ServiceStatus.Finished;
    }
    
    public int getWorkHours () {
        if (serviceEndTime == null || serviceStartTime == null) return 0;
        return (int) Math.ceil(serviceEndTime.getTime() - serviceStartTime.getTime() / 3_600_000);
    }
    
    @Override public String toString () {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Apply date      : %s\n", DateFormats.yyyy_mm_dd().format(this.applyDate)));
        sb.append(String.format("Car id          : %s\n", this.carId));
        sb.append(String.format("Worker          : %s %s\n", this.worker.name, this.worker.surname));
        sb.append(String.format("Service variant : %s\n", this.variant));
        
        return sb.toString();
    }
}

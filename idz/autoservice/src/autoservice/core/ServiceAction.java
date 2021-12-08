package autoservice.core;

import java.util.*;
import autoservice.util.*;

/**
 * Represents service action
 * @author Dmitriy Naumov
 */
public class ServiceAction implements java.io.Serializable {
    
    protected transient AutoServiceBackend backend;
    
    public Date applyDate;
    public ServiceVariant variant;
    public Date serviceStartTime;
    public Date serviceEndTime;
    public ServiceStatus status;
    public String notes;
    
    private Integer workerId;
    private Integer carId;
    private List<Several<Integer>> actionIds;
    private List<Several<Integer>> newPartIds;
    
    public ServiceAction (AutoServiceBackend backend) {
        this.applyDate = new Date();
        this.status = ServiceStatus.Planned;
        this.actionIds = new LinkedList<>();
        this.newPartIds = new LinkedList<>();
        this.backend = backend;
    }
    
    public Car getCar () {
        return backend.getCars().get(carId);
    }
    
    public Worker getWorker () {
        return backend.getWorkers().get(workerId);
    }
    
    public int getCarId () {
        return this.carId;
    }
    
    public int getWorkerId () {
        return this.workerId;
    }
    
    public void setCarId (int id) {
        if (backend.getCars().containsKey(id)) 
            this.carId = id;
        else 
            throw new IllegalArgumentException (String.format("Backend has no Car with id %d", id));
    }
    
    public void setWorkerId (int id) {
        if (backend.getWorkers().containsKey(id))
            this.workerId = id;
        else 
            throw new IllegalArgumentException (String.format("Backend has no Worker with id %d", id));
    }
    
    public void addNewPartId (int count, int id) {
        if (count < 1)
            throw new IllegalArgumentException (String.format("Count should be greater than 0, but found %d", count));
        if (!backend.getCarParts().containsKey(id))
            throw new IllegalArgumentException (String.format("Backend has no CarPart with id %d", id));
        this.newPartIds.add(new Several<>(count, id));
    }
    
    public void addActionId (int count, int id) {
        if (count < 1)
            throw new IllegalArgumentException (String.format("Count should be greater than 0, but found %d", count));
        if (!backend.getCarActions().containsKey(id))
            throw new IllegalArgumentException (String.format("Backend has no CarAction with id %d", id));
        this.actionIds.add(new Several<>(count, id));
    }
    
    public List<Several<CarAction>> getActions () {
        List<Several<CarAction>> result = new LinkedList<>();
        for (Several<Integer> a : actionIds) 
            result.add(new Several<>(a.count, backend.getCarActions().get(a.value)));
        return result;
    }
    
    public List<Several<CarPart>> getNewParts () {
        List<Several<CarPart>> result = new LinkedList<>();
        for (Several<Integer> a : newPartIds) 
            result.add(new Several<>(a.count, backend.getCarParts().get(a.value)));
        return result;
    }
    
    public int getPartCount () {
        return this.newPartIds.size();
    }
    
    public int getActionCount () {
        return this.actionIds.size();
    }
    
    public int getPrice () {
        int result = 0;
        
        if (newPartIds != null) for (Several<Integer> item : newPartIds) {
            result += item.count * backend.getCarParts().get(item.value).price;
        }
        if (actionIds != null) for (Several<Integer> action : actionIds) {
            result += action.count * backend.getCarActions().get(action.value).price;
        }
        return result + ((workerId == null)? 0 : getWorkHours() * backend.getWorkers().get(workerId).hourSalary);
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
        return (int) Math.ceil((double)(serviceEndTime.getTime() - serviceStartTime.getTime()) / 3600000.0);
    }
}

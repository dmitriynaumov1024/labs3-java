package autoservice.core;

import java.util.*;

/**
 * Class that contains info about autoservice worker.
 * @author Dmitriy Naumov
 */
public class Worker extends Person {
    public List<CarTag> preferences;
    public int hourSalary;
    
    public Worker () {
        super();
        this.preferences = new LinkedList<>();
    }
}

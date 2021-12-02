package autoservice.core;

import java.util.*;

/**
 * Class that contains info about autoservice worker.
 * @author Dmitriy Naumov
 */
public class Worker {
    public List<CarTag> preferences;
    
    public Worker () {
        super();
        this.preferences = new LinkedList<>();
    }
}

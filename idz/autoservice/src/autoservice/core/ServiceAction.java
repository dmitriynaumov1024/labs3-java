package autoservice.core;

import java.util.Date;

/**
 *
 * @author Dmitriy Naumov
 */
public class ServiceAction implements java.io.Serializable {
    public Date applyDate;
    public Car car;
    public Person owner;
    public Worker worker;
}

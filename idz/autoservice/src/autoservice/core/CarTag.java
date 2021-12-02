package autoservice.core;

/**
 * Contains different car service tags (that is not service variants)
 * @author Dmitriy Naumov
 */
public enum CarTag implements java.io.Serializable {
    Electromobile,
    LNG,
    PetrolEngine,
    DieselEngine,
    Body,
    Paint,
    Tires,
    Electronic,
    Cleaning,
    Generic
}

package integrals;

/**
 * Abstract integral class
 * @author Dmitriy Naumov
 */
public abstract class FunctionIntegral {
    
    /**
     * Integrate some function this integral is based on, on interval from a to b.
     * @param a starting point of interval
     * @param b end point of interval
     * @return result of integration
     */
    public abstract double integrate (double a, double b);
}

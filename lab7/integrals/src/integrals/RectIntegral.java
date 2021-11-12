package integrals;

/**
 * Rectangular integral
 * @author Dmitriy Naumov
 */
public class RectIntegral extends ApproxIntegral {
    
    public RectIntegral (Function func, double step) {
        super (func, step);
    }
    
    /**
     * Partial rectangular integration returns value of function at 
     * interval midpoint, multiplied by integral length.
     */
    @Override protected double partialIntegrate (double a, double b) {
        return this.func.apply ((a + b) / 2) * (b - a);
    }
}

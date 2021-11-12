package integrals;

/**
 * Trapezoid integral
 * @author Dmitriy Naumov
 */
public class TrapezoidIntegral extends ApproxIntegral {
    
    public TrapezoidIntegral (Function func, double step) {
        super (func, step);
    }
    
    /**
     * Partial trapezoid integration returns value that is area of a right 
     * trapezoid with bases equal to function values at interval endpoints and 
     * height equal to length of the interval.
     */
    @Override protected double partialIntegrate (double a, double b) {
        return (this.func.apply(a) + this.func.apply(b)) * (b - a) / 2;
    }
}

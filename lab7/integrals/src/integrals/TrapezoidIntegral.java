package integrals;

/**
 * Trapezoid integral
 * @author Dmitriy Naumov
 * @see <a href="https://en.wikipedia.org/wiki/Trapezoidal_rule">Trapezoidal integration rule on Wikipedia</a>
 */
public class TrapezoidIntegral extends ApproxIntegral {
    
    /**
     * Create new trapezoid integral with given function and given step.
     * @param func function to integrate
     * @param step integration step
     */
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

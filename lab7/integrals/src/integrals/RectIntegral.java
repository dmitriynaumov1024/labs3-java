package integrals;

/**
 * Rectangular integral.
 * @author Dmitriy Naumov
 * @see <a href="https://en.wikipedia.org/wiki/Riemann_sum">Rectangular integration (a.k.a Riemann sum) on Wikipedia</a>
 */
public class RectIntegral extends ApproxIntegral {
    
    /**
     * Create new rectangular integral with given function and given step.
     * @param func function to integrate
     * @param step integration step
     */
    public RectIntegral (Function func, double step) {
        super (func, step);
    }
    
    /**
     * Partial rectangular integration returns value of function at 
     * interval midpoint, multiplied by interval length.
     */
    @Override protected double partialIntegrate (double a, double b) {
        return this.func.apply ((a + b) / 2) * (b - a);
    }
}

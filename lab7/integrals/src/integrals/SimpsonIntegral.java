package integrals;

/**
 * Simpson integral
 * @author Dmitriy Naumov
 * @see <a href="https://en.wikipedia.org/wiki/Simpson%27s_rule">Simpson's rule on Wikipedia</a>
 */
public class SimpsonIntegral extends ApproxIntegral {
    
    /**
     * Create new simpson integral with given function and given step
     * @param func function to integrate
     * @param step integration step
     */
    public SimpsonIntegral (Function func, double step) {
        super (func, step);
    }
    
    /**
     * Partial Simpson integration returns result of integration of cubically 
     * approximated function.
     */
    @Override protected double partialIntegrate (double a, double b) {
        return 
          (
            + this.func.apply(a)
            + this.func.apply(b)
            + 3 * this.func.apply((2*a + b) / 3)
            + 3 * this.func.apply((2*b + a) / 3)
          )
          * 
          (b - a) / 8;
    }
}

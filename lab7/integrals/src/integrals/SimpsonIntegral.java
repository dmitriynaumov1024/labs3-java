package integrals;

/**
 *
 * @author Dmitriy Naumov
 */
public class SimpsonIntegral extends ApproxIntegral {
    
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
            + this.func.apply((2*a + b) / 3)
            + this.func.apply((2*b + a) / 3)
          )
          * 
          (b - a) / 8;
    }
}

package integrals;

/**
 * Approximate integral
 * @author Dmitriy Naumov
 */
public abstract class ApproxIntegral extends FunctionIntegral {
    
    /**
     * Function to integrate
     */
    protected Function func;
    
    private double step;
    
    /**
     * Create new approximate integral from given function and given step.
     * @param func single-parameter function to integrate
     * @param step integration step
     * @see integrals.Function
     */
    public ApproxIntegral (Function func, double step) {
        if (func == null) {
            throw new NullPointerException ("Function passed to constructor was null.");
        }
        if (step <= 0) {
            throw new IllegalArgumentException (
                String.format("Step should be greater than 0, but found %f", step)
            );
        }
        this.func = func;
        this.step = step;
    }
    
    /**
     * @param a starting point of interval
     * @param b end point of interval
     * @return approximate result of integration.
     */
    protected abstract double partialIntegrate (double a, double b);
    
    /**
     * Perform approximate integration of function on given interval with 
     * given step.
     */
    @Override public double integrate (double a, double b) {
        double result = 0;
        double x;
        for (x = a; x < b; x += step) {
            result += this.partialIntegrate(x, x + step);
        }
        result += this.partialIntegrate(x, b);
        return result;
    }
}

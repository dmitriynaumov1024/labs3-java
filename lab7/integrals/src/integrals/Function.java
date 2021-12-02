package integrals;

/**
 * Function of single parameter
 * @author Dmitriy Naumov
 */
public abstract class Function {
    /**
     * Calculate function value at given argument value.
     * @param arg argument value
     * @return value of this function at given argument value
     */
    public abstract double apply (double arg);
}

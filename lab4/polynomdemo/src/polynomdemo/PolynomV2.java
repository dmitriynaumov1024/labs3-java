package polynomdemo;

import java.util.Arrays;

/**
 * @author Dmitriy Naumov
 */
public class PolynomV2 implements IFunction<Double> {
    
    // Coefficients of the polynom
    private double[] coefs;
    
    // Create new Polynom using given coefs
    public PolynomV2(double[] a) throws Exception {
        if(a.length < 1) 
            throw new Exception("Should be 1 or more coefficients.");
        this.coefs = a.clone();
    }
    
    // Create new Polynom of given degree with all coefs equal to z
    public PolynomV2(int degree, double z) throws Exception {
        if(degree < 0) 
            throw new Exception("degree should be 0 or higher.");
        this.coefs = new double[degree+1];
        for(int i=0; i<coefs.length; i++) { coefs[i] = z; }
    }
    
    // Create new, absolutely emtpy and invalid Polynom
    private PolynomV2() { }
    
    // get degree of this Polynom
    public int degree() { return this.coefs.length - 1; }
    
    // get value of function at given argument value
    @Override public double f(Double arg){
        double result = this.coefs[0], argCopy = arg;
        for(int i=1; i<this.coefs.length; i++){
            result += argCopy * this.coefs[i];
            argCopy *= arg;
        }
        return result;
    }
    
    static double[] _derivative(double[] coefs, int order){
        if(coefs.length - order < 1) return new double[]{0};
        double[] result = coefs.clone();
        for(int a = 0; a < order; a++){
            result[0] = 0;
            for(int i = 1; i < (result.length - a); i++){
                result[i-1] = i * result[i];
            }
        }
        return Arrays.copyOfRange(result, 0, coefs.length - order);
    }
    
    // First derivative 
    public PolynomV2 derivative() throws Exception {
        return new PolynomV2(_derivative(this.coefs, 1));
    }
    
    // N-th derivative
    public PolynomV2 derivative(int order) throws Exception {
        if (order < 1) 
            throw new Exception("order should be 1 or higher");
        if (order > this.coefs.length)
            return PolynomV2.constant(0.0);
        
        return new PolynomV2(_derivative(this.coefs, order));
    }
    
    // Add other polynom to this polynom
    public PolynomV2 plus(PolynomV2 other){
        double[] resultCoefs, lesserCoefs;
        if(this.degree() > other.degree()){
            resultCoefs = this.coefs.clone();
            lesserCoefs = other.coefs;
        } 
        else {
            resultCoefs = other.coefs.clone();
            lesserCoefs = other.coefs;
        }
        for(int i=0; i<lesserCoefs.length; i++) {
            resultCoefs[i] += lesserCoefs[i];
        }
        PolynomV2 result = new PolynomV2();
        result.coefs = resultCoefs;
        return result;
    }
    
    // substract other polynom from this polynom
    public PolynomV2 minus(PolynomV2 other){
        return this.plus(other.negate());
    }
    
    // negate this polynom
    public PolynomV2 negate(){
        PolynomV2 result = new PolynomV2();
        result.coefs = new double[this.coefs.length];
        for(int i=0; i<result.coefs.length; i++){
            result.coefs[i] = -(this.coefs[i]);
        }
        return result;
    }

    // divide this polynom by scalar value
    PolynomV2 div(double z){
        if(z == 0) 
            return PolynomV2.constant(Double.POSITIVE_INFINITY);
        else
            return this.prod(1 / z);
    }

    // multiply this polynom by scalar value
    PolynomV2 prod(double z) {
        PolynomV2 result = new PolynomV2();
        result.coefs = this.coefs.clone();
        for (int i = 0; i < this.coefs.length; i++) {
            result.coefs[i] *= z;
        }
        return result;
    }

    // multiply this polynom by another polynom
    PolynomV2 prod(PolynomV2 other) {
        int resultDegree = this.degree() + other.degree();
        PolynomV2 result = new PolynomV2();
        result.coefs = new double[resultDegree + 1];
        for (int i = 0; i < this.coefs.length; i++) {
            for (int j = 0; j < other.coefs.length; j++) {
                result.coefs[i + j] += this.coefs[i] * other.coefs[j];
            }
        }
        return result;
    }
    
    // String representation of this Polynom
    @Override public String toString() {
        int degree = this.coefs.length - 1;
        String result = "";
        for(int i=degree; i>0; i--){
            result += String.format("%+.6f*x^%d ", this.coefs[i], i);
        }
        return result + String.format("%+.6f", coefs[0]);
    }
    
    // Constant
    public static PolynomV2 constant(double a){
        PolynomV2 result = new PolynomV2();
        result.coefs = new double[]{ a };
        return result;
    }
}

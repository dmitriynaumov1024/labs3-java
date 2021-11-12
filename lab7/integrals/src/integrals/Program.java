package integrals;

import java.util.Locale;

/**
 * Main class
 * @author Dmitriy Naumov
 */
public class Program {

    /**
     * Entry point
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Locale.setDefault(Locale.ENGLISH);
        
        Function f1 = new Function(){
            @Override public double apply(double arg) {
                return (6 - arg * arg) / (arg * arg * arg + 3);
            }
        };
        
        double start = 0, end = 33.25, step = 0.1;
        
        FunctionIntegral 
            i1 = new RectIntegral(f1, step),
            i2 = new TrapezoidIntegral(f1, step),
            i3 = new SimpsonIntegral(f1, step);
        
        System.out.printf (
            "Integrated f(x) = (6 - x^2) / (x^3 + 3) from %f to %f with step %f \n",
            start, end, step
        );
        
        System.out.printf ("Results of \n");
        
        System.out.printf ("- Rectangular Integral : %f \n", i1.integrate(start, end));
        System.out.printf ("- Trapezoid Integral   : %f \n", i2.integrate(start, end));
        System.out.printf ("- Simpson Integral     : %f \n", i3.integrate(start, end));
        
    }
    
}

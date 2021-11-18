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
        
        Function[] functions = new Function[] {
            new Function(){
                @Override public double apply(double arg) {
                    return (6 - arg * arg) / (arg * arg * arg + 3);
                }
                @Override public String toString() {
                    return "f(x) = (6 - x^2) / (x^3 + 3)";
                }
            },
            new Function(){
                @Override public double apply(double arg) {
                    return Math.pow(6 + arg, 2) * Math.sqrt(arg + 3);
                }
                @Override public String toString() {
                    return "f(x) = (6 + x)^2 * sqrt(x + 3)";
                }
            },
            new Function(){
                @Override public double apply(double arg) {
                    return Math.sqrt(Math.log(arg + 6)) / arg + 3;
                }
                @Override public String toString() {
                    return "f(x) = sqrt(ln(x + 6)) / x + 3";
                }
            }
        };
        
        double start = 1, end = Math.PI, step = 0.5;
        
        for (Function f : functions) {
            FunctionIntegral 
                i1 = new RectIntegral(f, step),
                i2 = new TrapezoidIntegral(f, step),
                i3 = new SimpsonIntegral(f, step);

            System.out.printf (
                "\nIntegrated %s \nfrom %f to %f with step %f \n",
                f, start, end, step
            );

            System.out.printf ("- Rectangular integration : %f \n", i1.integrate(start, end));
            System.out.printf ("- Trapezoid integration   : %f \n", i2.integrate(start, end));
            System.out.printf ("- Simpson integration     : %f \n", i3.integrate(start, end));
        }
    }
}

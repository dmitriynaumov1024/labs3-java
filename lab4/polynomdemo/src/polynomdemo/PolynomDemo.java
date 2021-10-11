package polynomdemo;

import java.util.Locale;
import java.util.Scanner;

/**
 * Demo of Polynom class.
 * @author Dmitriy Naumov
 */
public class PolynomDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Scanner in = new Scanner(System.in);
        System.out.printf("Enter degree of pynomial p: \n");
        int p_degree = in.nextInt();
        double[] p_coefs = new double[p_degree+1];
        for(int i=0; i<=p_degree; i++){
            System.out.printf("Coefficient for power %d of p: ", i);
            p_coefs[i] = in.nextDouble();
        }
        try { 
            PolynomV2 p = new PolynomV2(p_coefs); 
            System.out.printf("p = %s \n", p.toString());
            for(double x = -10.0; x <= 10.0; x += 1.0)
                System.out.printf("Value of p(%f) = %f \n", x, p.f(x));
            
            PolynomV2 pd = p.derivative(2);
            System.out.printf("p'' = %s \n", pd.toString());
            for(double x = -10.0; x <= 10.0; x += 1.0)
                System.out.printf("Value of p''(%f) = %f \n", x, pd.f(x));
            
            System.out.printf("\np = %s \n", p.toString());
            
            PolynomV2 q = new PolynomV2(new double[]{3.0, 4.0});
            System.out.printf("q = %s \n", q.toString());
            
            PolynomV2 r = p.prod(q);
            System.out.printf("r = p * q = %s \n", r.toString());
        }
        catch(Exception ex) { 
            System.out.printf("Exception: %s \n", ex.getMessage());
            return;
        }
    }
}

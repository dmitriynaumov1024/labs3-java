package iw4;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Main class
 * @author Dmitriy Naumov
 */
public class Program {

    /**
     * Get matrix from standard input.
     * First, a prompt for matrix size is displayed.
     * Size value is scanned from std.input.
     * A square matrix with given size is created.
     * Values of all matrix elements are scanned from std.input.
     * @return matrix initialized with data from standard input. 
     */
    static Matrix getMatrix () {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.printf("[size]: ");
            int size = sc.nextInt();
            Matrix result = new Matrix(size);
            for (int row = 0; row < size; row++) {
                System.out.printf ("[%d]: ", row);
                for (int col = 0; col < size; col++) {
                    result.set(row, col, sc.nextDouble());
                }
            }
            return result;
        }
        catch (InputMismatchException ex) {
            throw new RuntimeException ("Expected to get a number from standard input.");
        }
    }
    
    /**
     * Entry point
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        
        try {
            System.out.printf("Initializing matrix A. \n");
            Matrix a = getMatrix();
            System.out.printf("Initializing matrix B. \n");
            Matrix b = getMatrix();
            Matrix c = (a.plus(b)).squared();

            System.out.printf("\nA: \n%s", a);
            System.out.printf("\nB: \n%s", b);
            System.out.printf("\n(A + B)^2: \n%s", c);
        }
        catch (Exception ex) {
            System.out.printf("Exception: %s \n", ex.getMessage());
        }
    }
    
}

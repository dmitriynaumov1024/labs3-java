package iw5;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Given 3 file names (A, B, C).
 * Content of files:
 * A1, A2, A3... An - doubles
 * B1, B2, B3... Bn
 * C1, C2, C3... Cn
 * Create new file (D) 
 * and fill it with numbers
 * A1, B1, C1, A2, B2, C2, ... An, Bn, Cn
 * @author Dmitriy Naumov
 */
public class Program {
    
    /**
     * Entry point
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int inputCount = args.length - 1;
        Locale.setDefault(Locale.ENGLISH);
        
        if (inputCount < 1){
            System.out.print("Expected at least one input and one output path. \n");
            return;
        }
        
        Scanner[] scanners = new Scanner[inputCount];
        FileWriter outputWriter;
        
        for (int i=0; i<inputCount; i++){
            try {
                scanners[i] = new Scanner(new FileInputStream(args[i]));
            }  
            catch (FileNotFoundException ex) {
                System.out.printf ("File '%s' not found. \n", args[i]);
                return;
            }   
        }
        
        try {
            outputWriter = new FileWriter(new File(args[inputCount]));
        }
        catch (FileNotFoundException ex) {
            System.out.printf ("Can not create '%s'. \n", args[inputCount]);
            return;
        }
        catch (IOException ex) {
            System.out.printf ("Can not access file '%s' \n", args[inputCount]);
            return;
        }
        
        try {
            while (true) {
                for (Scanner scanner : scanners) {
                    // If one of scanners runs out of data, 
                    // flush the output and return
                    if (!scanner.hasNextDouble()){
                        outputWriter.flush();
                        outputWriter.close();
                        return;
                    }
                }
                for (Scanner scanner : scanners) {
                    outputWriter.write(String.format("%f ", scanner.nextDouble()));
                }
                outputWriter.write("\r\n");
            }
        }
        catch (IOException ex) {
            System.out.printf ("I/O Exception: %s \n", ex.getMessage());
            return;
        }
    }
    
}

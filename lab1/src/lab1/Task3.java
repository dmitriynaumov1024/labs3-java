package lab1;

/**
 * A program to length of hypotenuse of a triangle with known length of 
 * cathets passed as command line arguments. Result should be printed on 
 * console.
 * @author Dmitriy Naumov
 */
public class Task3 implements ITask {
    String helpString = "Usage: <A: double> <B: double>";
    
    public void run(String[] args){
        if(args.length < 2){
            System.out.println(helpString);
            return;
        }
        
        try {
            double A = Double.parseDouble(args[0]),
                   B = Double.parseDouble(args[1]);
            System.out.printf("A = %f, B = %f, C = %f", 
                              A, B, Math.sqrt(A*A + B*B));
        } catch(NumberFormatException ex){
            System.out.println(helpString);
        }
    }
}

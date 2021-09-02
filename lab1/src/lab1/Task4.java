package lab1;

/**
 * A program to calculate 2^n using loop.
 * @author Dmitriy Naumov
 */
public class Task4 implements ITask {
    String helpString = "Usage: <number: unsigned integer>";
    public void run(String[] args){
        if(args.length < 1) {
            System.out.println(helpString);
            return;
        } 
        try {
            int number = Integer.parseInt(args[0]);
            System.out.printf("2^%d = %d \n", number, IntOperations.exponent2(number));
        } catch(Exception e){
            System.out.printf("Exception: %s \n", e.getMessage());
        }
    }
}

package lab1;

/**
 * A program to calculate factorial of given number.
 * @author Dmitriy Naumov
 */
public class Task2 implements ITask {
    String helpString = "Usage: <number: integer>";
    
    public void run(String[] args){
        if(args.length == 0){
            System.out.println(helpString);
            return;
        }
        
        try {
            long num = Long.parseLong(args[0]);
            if (num < 0 || num > 20) {
                System.out.println("Number must be in 0...20 range.");
            } else {
                System.out.printf("%d! = %d \n", num, IntOperations.factorial(num));
            }
        } catch (NumberFormatException ex){
            System.out.println(helpString);
        }
    }
}

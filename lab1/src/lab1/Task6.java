package lab1;

/**
 * Write a program to calculate factorial of a number n<10 using loop.
 * @author Dmitriy Naumov
 */
public class Task6 implements ITask {
    String helpString = "Usage: <number: unsigned integer>";
    public void run(String[] args){
        if(args.length == 0){
            System.out.println(helpString);
            return;
        }
        
        try {
            long num = Long.parseLong(args[0]);
            if (num < 0 || num >= 10) {
                System.out.println("Number must be in 0..<10 range.");
            } else {
                System.out.printf("%d! = %d \n", num, IntOperations.factorial(num));
            }
        } catch (NumberFormatException ex){
            System.out.println(helpString);
        }
    }
}

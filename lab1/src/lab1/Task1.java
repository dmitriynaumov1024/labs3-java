package lab1;

/**
 * A task to calculate sum, difference, product and quotient of 2 numbers.
 * Numbers are passed as string arguments.
 * Result will be printed to standard out.
 * @author Dmitriy Naumov
 */
public class Task1 implements ITask {
    private String helpString = "Usage: <num1: integer> <num2: integer>";
    
    public void run(String[] args){
        
        if (args.length < 2){
            System.out.println(helpString);
            return;
        } 
        
        try {
            int num1 = Integer.parseInt(args[0]),
                num2 = Integer.parseInt(args[1]);
            String outputString = 
                String.format("%d + %d = %d \n", num1, num2, num1 + num2)
              + String.format("%d - %d = %d \n", num1, num2, num1 - num2)
              + String.format("%d * %d = %d \n", num1, num2, num1 * num2)
              + ((num2 == 0) ? 
                    "Can't divide by zero." : 
                    String.format("%d / %d = %d \n", num1, num2, num1 / num2));
            System.out.print(outputString);
        } catch(NumberFormatException ex) {
            System.out.println(helpString);
            System.out.println(ex.getMessage());
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}

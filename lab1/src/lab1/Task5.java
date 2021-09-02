package lab1;

/**
 * Modify given program to calculate square root of a number passed as 
 * command line argument.
 * @author Dmitriy Naumov
 */
public class Task5 implements ITask {
    String helpString = "Usage: <number: double>";
    
    double sqrt(double a){
        double b=a;
        int i=0;
        while ((b*b>a)&&(i<200)){
            b=(b+a/b)/2;
            i++;
        }
        return b;
    }
    
    public void run(String[] args){
        if (args.length < 1){
            System.out.println(helpString);
            return;
        }
        
        try {
            double number = Double.parseDouble(args[0]);
            System.out.printf("sqrt(%f) = %f \n", number, sqrt(number));
        } catch (NumberFormatException ex) {
            System.out.println(helpString);
        } catch (Exception ex) {
            System.out.printf("Exception: %s \n", ex.getMessage());
        }
    }
}

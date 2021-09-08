package lab2;

/**
 * Sort an array using 4 different methods. Compare their execution time.
 * @author Dmitriy Naumov
 */
public class Task5 implements ITask {
    final static String helpString = "Sort an array using 4 different methods. \nCompare their execution time. \nUsage: <array length: integer>"; 
    
    public void run(String[] args){
        if(args.length < 1){
            System.out.println(helpString);
            return;
        }
        
        try {
            
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}

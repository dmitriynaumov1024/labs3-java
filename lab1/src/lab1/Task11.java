package lab1;

import java.util.Arrays;
import java.util.Random;

/**
 * Given array C(n), where n is in range 1...20. 
 * Write a method to find the average of array elements.
 * @author Dmitriy Naumov
 */
public class Task11 implements ITask {
    String helpString = "Usage: <size: unsigned int>";
    int sizeUpperLimit = 200,
        valueLimit = 10000;
    
    public void run(String[] args){
        if(args.length == 0){
            System.out.println(helpString);
            return;
        }
        try {
            int size = Integer.min(Integer.parseUnsignedInt(args[0]), sizeUpperLimit);
            Boolean ascendingOrder = true;
            
            int[] array = new int[size];
            Random random = new Random();
            for(int i=0; i<size; i++){
                array[i] = random.nextInt(valueLimit);
            }
            
            System.out.println("Randomized array:");
            System.out.println(Arrays.toString(array));
            
            int arrayAvg = ArrayOperations.average(array);
            
            System.out.printf("Average: %d \n", arrayAvg);
        } 
        catch(NumberFormatException ex){
            System.out.println(helpString);
        }
    }
}

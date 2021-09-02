package lab1;

import java.util.Arrays;
import java.util.Random;

/**
 * Given array D(n), where n is in range 1...30. 
 * Write a method to calculate sum of even and odd numbers separately.
 * @author Dmitriy Naumov
 */
public class Task12 implements ITask {
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
            
            int arraySumEven = ArrayOperations.sumEven(array), 
                arraySumOdd = ArrayOperations.sumOdd(array);
            
            System.out.printf("Sum of evens: %d \n", arraySumEven);
            System.out.printf("Sum of odds:  %d \n", arraySumOdd);
        } 
        catch(NumberFormatException ex){
            System.out.println(helpString);
        }
    }
}

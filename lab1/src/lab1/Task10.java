package lab1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Given array B(n), write a method to sort 
 * the array in ascending or descending order.
 * @author Dmitriy Naumov
 */
public class Task10 implements ITask {
    String helpString = "Usage: <size: unsigned int> [asc|desc]";
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
            if(args.length >= 2){
                if(args[1].toLowerCase().startsWith("asc")){
                    ascendingOrder = true;
                } 
                else if(args[1].toLowerCase().startsWith("desc")){
                    ascendingOrder = false;
                } 
                else {
                    System.out.println(helpString);
                    return;
                }
            }
            
            Integer[] array = new Integer[size];
            Random random = new Random();
            for(int i=0; i<size; i++){
                array[i] = random.nextInt(valueLimit);
            }
            
            System.out.println("Randomized array:");
            System.out.println(Arrays.toString(array));
            
            ArrayOperations.sort(array, ascendingOrder);
            
            System.out.println("Array after sort:");
            System.out.println(Arrays.toString(array));
        } 
        catch(NumberFormatException ex){
            System.out.println(helpString);
        }
    }
}

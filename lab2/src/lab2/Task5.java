package lab2;

import java.util.Arrays;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

/**
 * Sort an array using 4 different methods. Compare their execution time.
 * @author Dmitriy Naumov
 */
public class Task5 implements ITask {
    final static String helpString = 
            "Sort an array using 4 different methods. Compare their execution time. \n" +
            "Usage: <array length: integer> <algorithm ID: integer> \n" +
            "Valid algorithm IDs: 1 (insertion), 2 (merge), 3 (bubble), 4 (shaker)"; 
    
    static int[] randomArray(int size, int valueLowerBound, int valueUpperBound){
        int[] result = new int[size];
        Random random = new Random();
        int valueRange = Math.abs(valueUpperBound - valueLowerBound);
        int padding = Math.min(valueUpperBound, valueLowerBound);
        for(int i=0; i<size; i++){
            result[i] = random.nextInt(valueRange) + padding;
        }
        return result;
    }
    
    public void run(String[] args){
        if(args.length < 2){
            System.out.println(helpString);
            return;
        }
        
        IIntSorter sorter;
        String algorithmName;
        switch(args[1]){
            case "1":
                sorter = new IntInsertionSorter();
                algorithmName = "insertion sort";
                break;
            case "2":
                sorter = new IntMergeSorter();
                algorithmName = "merge sort";
                break;
            case "3":
                sorter = new IntBubbleSorter();
                algorithmName = "bubble sort";
                break;
            case "4":
                sorter = new IntShakerSorter();
                algorithmName = "shaker sort";
                break;
            default:
                System.out.println(helpString);
                return;
        }
        
        try {
            long t1, sortTime;
            
            int size = Integer.parseUnsignedInt(args[0]);
            
            int[] array = randomArray(size, -size, size*2),
                  arrayCopy,
                  arraySorted;
            
            arrayCopy = Arrays.copyOf(array, array.length);
            t1 = System.currentTimeMillis();
            arraySorted = sorter.sort(arrayCopy);
            sortTime = System.currentTimeMillis() - t1;
            
            System.out.println("\n[0] Source array: ");
            System.out.println(Arrays.toString(array));
            System.out.println("\n[1] Sorted array: ");
            System.out.println(Arrays.toString(arraySorted));
            System.out.printf ("\n[1] %d elements sorted with %s in %d ms", size, algorithmName, sortTime);
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}

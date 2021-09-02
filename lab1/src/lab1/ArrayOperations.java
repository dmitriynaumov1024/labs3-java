package lab1;

import java.util.Arrays;
import java.util.Random;
import java.util.Comparator;
/**
 * @author Dmitriy Naumov
 */
public class ArrayOperations {
    public static void swapMinMax(int[] array){
        if (array.length < 2) {
            return;
        }
        
        int temporaryMin = Integer.MAX_VALUE, 
            temporaryMax = Integer.MIN_VALUE, 
            indexMin = 0, 
            indexMax = 0;
        
        for (int i=0; i<array.length; i++){
            if (temporaryMin > array[i]){
                temporaryMin = array[i];
                indexMin = i;
            }
            if (temporaryMax < array[i]){
                temporaryMax = array[i];
                indexMax = i;
            }
        }
        
        array[indexMin] = temporaryMax;
        array[indexMax] = temporaryMin;
    }
    
    public static String arrayToString(int[] array){
        if(array.length == 0){
            return "{ }";
        }
        String result = "{ " + array[0];
        for(int i=1; i<array.length; i++){
            result += String.format(", %d", array[i]);
        }
        return result + " }";
    }
    
    public static int[] getRandomizedArray(int size, int bound){
        int[] array = new int[size];
        Random random = new Random();
        for(int i=0; i<size; i++){
            array[i] = random.nextInt(bound);
        }
        return array;
    }
    
    public static int[] getRandomizedArray(int size){
        return getRandomizedArray(size, Integer.MAX_VALUE);
    }
    
    public static void sort(Integer[] array, Boolean ascendingOrder){
        Arrays.sort(array, (ascendingOrder==true)? 
                Comparator.naturalOrder() : 
                Comparator.reverseOrder());
    }
    
    public static int average(int[] array){
        int sum = 0;
        for(int i=0; i<array.length; i++){
            sum += array[i];
        }
        return sum / array.length;
    }
    
    public static int sumEven(int[] array){
        int sum = 0;
        for(int i=0; i<array.length; i++){
            if(array[i]%2 == 0) sum += array[i];
        }
        return sum;
    }
    
    public static int sumOdd(int[] array){
        int sum = 0;
        for(int i=0; i<array.length; i++){
            if(array[i]%2 == 1) sum += array[i];
        }
        return sum;
    }
}

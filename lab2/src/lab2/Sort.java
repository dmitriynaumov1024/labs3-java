package lab2;

import java.util.Arrays;

/**
 * Contains methods to sort an array.
 * @author Dima
 */
public class Sort {
    
    /**
     * Sorts given array modifying the source.
     * Insertion sort algorithm is used.
     * @param source array to sort
     * @return sorted array
     */
    public static int[] insertionSorted(int[] source){
        int length = source.length;
        int[] result = source;
        for(int i = 1; i < length; i++){
            int v = result[i];
            int j;
            for(j=i; j>0 && result[j-1]>v; j--){
                result[j] = result[j-1];
            }
            result[j] = v;
        }
        return result;
    }
    
    /**
     * Sorts given array modifying the source.
     * Merge sort algorithm is used.
     * @param source array to sort
     * @return sorted array
     */
    public static int[] mergeSorted(int[] source){
        int length = source.length;
        int[] result = source;
        return result;
    }
    
    /**
     * Sorts given array modifying the source.
     * Bubble sort algorithm is used.
     * @param source array to sort
     * @return sorted array
     */
    public static int[] bubbleSorted(int[] source){
        int length = source.length;
        int[] result = source;
        return result;
    }
    
    /**
     * Sorts given array modifying the source.
     * Shaker sort algorithm is used.
     * @param source array to sort
     * @return sorted array
     */
    public static int[] shakerSorted(int[] source){
        int length = source.length;
        int[] result = source;
        return result;
    }
}

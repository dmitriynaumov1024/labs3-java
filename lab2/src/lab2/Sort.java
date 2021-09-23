package lab2;

import java.util.Arrays;

/**
 * Contains methods to sort an array.
 * @author Dmitriy Naumov
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
        _mergeSort(source);
        return source;
    }
    
    static void _mergeSort(int[] array){
        if (array.length < 2){
            return;
        } 
        else if (array.length == 2){
            if (array[0] > array[1]){
                int temporary = array[0];
                array[0] = array[1];
                array[1] = temporary;
            }
        }
        else {
            int midIndex = array.length / 2;
            
            int[] leftHalf = Arrays.copyOfRange(array, 0, midIndex),
                  rightHalf = Arrays.copyOfRange(array, midIndex, array.length);
            
            _mergeSort(leftHalf);
            _mergeSort(rightHalf);
            
            int leftIter = 0,
                rightIter = 0,
                mainIter = 0;
            
            while(leftIter < leftHalf.length && rightIter < rightHalf.length){
                int leftCurrent = leftHalf[leftIter], 
                    rightCurrent = rightHalf[rightIter];
                
                if(leftCurrent < rightCurrent){
                    array[mainIter++] = leftCurrent;
                    ++leftIter;
                }
                else {
                    array[mainIter++] = rightCurrent;
                    ++rightIter;
                }
            }
            
            while(leftIter < leftHalf.length){
                array[mainIter++] = leftHalf[leftIter++];
            }
            
            while(rightIter < rightHalf.length){
                array[mainIter++] = rightHalf[rightIter++];
            }
        }
    }
    
    /**
     * Sorts given array modifying the source.
     * Bubble sort algorithm is used.
     * @param source array to sort
     * @return sorted array
     */
    public static int[] bubbleSorted(int[] source){
        int length = source.length;
        for(int unsortedLength = length - 1; unsortedLength > 1; unsortedLength--){
            boolean swapOccured = false;
            for(int j=0; j<unsortedLength; j++){
                if(source[j] > source[j+1]){
                    int temporary = source[j+1];
                    source[j+1] = source[j];
                    source[j] = temporary;
                    swapOccured = true;
                }
            }
            if(!swapOccured) break;
        }
        return source;
    }
    
    /**
     * Sorts given array modifying the source.
     * Shaker sort algorithm is used.
     * @param source array to sort
     * @return sorted array
     */
    public static int[] shakerSorted(int[] source){
        int length = source.length;
        Boolean swapHappened;
        int start = 1, end = length;
        do {
            swapHappened = false;
            int delta_i = 1;
            // Trick with delta_i and turn-back can shorten the code.
            for(int i=start; i>=start; i += delta_i){
                if(i==end){
                    if(swapHappened){
                        delta_i = -1;
                        continue;
                    } 
                    break;
                } 
                int left = source[i-1], right = source[i];
                if (left > right){
                    source[i] = left;
                    source[i-1] = right;
                    swapHappened = true;
                }
            }
            ++start;
            --end;
        } while(swapHappened && start < end);
        return source;
    }
}

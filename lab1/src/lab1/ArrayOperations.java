package lab1;

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
}

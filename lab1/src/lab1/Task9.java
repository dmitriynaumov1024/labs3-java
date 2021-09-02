package lab1;

/**
 * Given array A(n), where n is in range 1...25. 
 * Write a method to swap min and max element of array.
 * @author Dmitriy Naumov
 */
public class Task9 implements ITask {
    String helpString = "Usage: <size: unsigned int>";
    int sizeLowerBound = 1, 
        sizeUpperBound = 25,
        valueBound = 1000;
    
    public void run(String[] args){
        if(args.length == 0) {
            System.out.println(helpString);
            return;
        }
        
        try {
            int size = Integer.parseUnsignedInt(args[0]);
            if(size > sizeUpperBound){
                size = sizeUpperBound;
                System.out.printf("Size too big, limiting to %d \n", size);
            }
            if(size < sizeLowerBound){
                size = sizeLowerBound;
                System.out.printf("Size too small, limiting to %d \n", size);
            }
            int[] array = ArrayOperations.getRandomizedArray(size, valueBound);
            System.out.println("Randomized array:");
            System.out.println(ArrayOperations.arrayToString(array));
            ArrayOperations.swapMinMax(array);
            System.out.println("Array after swapping min and max element:");
            System.out.println(ArrayOperations.arrayToString(array));
        } catch (Exception ex){
            System.out.printf("Exception: %s \n", ex);
        }
    }
}

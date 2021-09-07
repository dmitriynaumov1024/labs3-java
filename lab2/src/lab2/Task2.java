package lab2;

/**
 * Create an array and populate it with Fibonacci numbers.
 * @author Dima
 */
public class Task2 implements ITask {
    
    final static String helpString = "Usage: <sequence size>";
    
    static long[] fibonacci (int size) throws Exception {
        final int lowerBound = 2, upperBound = 92;
        if (size >= lowerBound && size <= upperBound){
            long[] result = new long[size];
            result[0] = 1;
            result[1] = 1;
            for (int i=2; i<size; i++){
                result[i] = result[i-1] + result[i-2];
            }
            return result;
        } 
        else {
            throw new Exception(String.format("size is out of [%d...%d] range.", lowerBound, upperBound));
        }
    }
    
    public void run (String[] args){
        if (args.length < 1){
            System.out.println(helpString);
            return;
        } 
        try {
            int sequenceSize = Integer.parseInt(args[0]);
            System.out.println("Size: " + sequenceSize);
            long[] fib = fibonacci(sequenceSize);
            System.out.println("Fibonacci sequence:");
            for (long i : fib){
                System.out.println(i);
            }
        }
        catch (Exception ex){
            System.out.printf("Exception: %s \n", ex.getMessage());
        }
    }
}

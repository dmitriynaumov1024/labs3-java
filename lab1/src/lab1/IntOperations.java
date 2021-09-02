package lab1;

/**
 * @author Dmitriy Naumov
 */
public class IntOperations {
    public static int factorial(int number){
        if(number < 2) {
            return 1;
        } else {
            int i = number;
            while (i > 1){
                number *= --i;
            }
            return number;
        }
    }
    
    public static long factorial(long number){
        if(number < 2) {
            return 1;
        } else {
            long i = number;
            while (i > 1){
                number *= --i;
            }
            return number;
        }
    }
}

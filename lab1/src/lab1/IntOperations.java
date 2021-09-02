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
    
    public static long exponent2(int exp) throws Exception {
        if(exp < 0 || exp > 62) {
            throw new Exception("Exponent should be in 0...62 range.");
        } else {
            long result = 1;
            for(int i=1; i<=exp; i++){
                result *= 2;
            }
            return result;
        }
    }
    
    public static long ipow(long base, int exp) throws Exception {
        if(exp < 0 || exp > 62) {
            throw new Exception("Exponent should be in 0...62 range.");
        } else {
            long result = 1;
            for(int i=1; i<=exp; i++){
                result *= base;
            }
            return result;
        }
    }
}

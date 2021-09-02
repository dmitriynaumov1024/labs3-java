package lab1;

/**
 * Write a program to check Ferma theorem a^n + b^n = c^n 
 * for numbers in range 1...100 and 2<n<10.
 * @author Dmitriy Naumov
 */
public class Task8 implements ITask {
    long numberLowerBound = 1,
         numberUpperBound = 100;
    int exponentLowerBound = 2,
        exponentUpperBound = 10;
    
    public void run(String[] args){
        System.out.println("Ferma theorem a^n + b^n = c^n");
        for(int n=exponentLowerBound; n<exponentUpperBound; n++){
            for(long a=numberLowerBound; a<numberUpperBound; a++){
                for(long b=a; b<numberUpperBound; b++){
                    for(long c=b; c<numberUpperBound; c++){
                        try {
                            if(IntOperations.ipow(a, n) + IntOperations.ipow(b, n) == IntOperations.ipow(c, n)){
                                System.out.printf("%d^%d + %d^%d = %d^%d \n", a, n, b, n, c, n);
                            }
                        } catch (Exception ex) {
                            System.out.printf("Exception: %s \n", ex.getMessage());
                        }
                    }
                }
            }
        }
    }
}

package lab2;

import java.util.Arrays;
import java.util.Random;

/**
 * Transpose randomly populated matrix of given size. 
 * @author Dmitriy Naumov
 */
public class Task3 implements ITask {
    final static String helpString = "Transpose randomly populated matrix of given size.\nUsage: <size>";
    public void run(String[] args){
        if(args.length < 1){
            System.out.println(helpString);
            return;
        }
        
        int size = Integer.parseInt(args[0]);
        Random random = new Random();
        int randomLimit = size * 2;
        int[][] matrix = new int[size][size];
        for (int[] row : matrix){
            for (int i=0; i<row.length; i++){
                row[i] = random.nextInt(randomLimit);
            }
        }
        
        int[][] transposedMatrix = Transposer.transpose(matrix);
        
        System.out.println("Matrix:");
        for (int[] row : matrix){
            System.out.println(Arrays.toString(row));
        }
        
        System.out.println("Transposed matrix:");
        for (int[] row : transposedMatrix){
            System.out.println(Arrays.toString(row));
        }
    }
}

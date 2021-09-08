package lab2;

import java.util.Arrays;
import java.util.Random;

/**
 * Transpose randomly populated matrix of given size. 
 * @author Dmitriy Naumov
 */
public class Task3 implements ITask {
    final static String helpString = "Transpose randomly populated matrix of given size.\nUsage: <size>";
    
    static int[][] randomArray2d(int rowCount, int columnCount, int lowerValueBound, int upperValueBound){
        int[][] result = new int[rowCount][columnCount];
        int valueRange = Math.abs(upperValueBound - lowerValueBound);
        int padding = Math.min(lowerValueBound, upperValueBound);
        Random random = new Random();
        for(int[] row : result){
            for(int i=0; i<row.length; i++){
                row[i] = random.nextInt(valueRange) + padding;
            }
        }
        return result;
    }
    
    public void run(String[] args){
        if(args.length < 1){
            System.out.println(helpString);
            return;
        }
        
        int size = Integer.parseInt(args[0]);
        
        int[][] matrix = randomArray2d(size, size, 0, size * 2);
        
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

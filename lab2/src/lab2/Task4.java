package lab2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Calculate matrix product of two square matrices whose elements are double type.
 * @author Dmitriy Naumov
 */
public class Task4 implements ITask {
    
    final static String helpString = "Calculate matrix product of two square matrices \nwhose elements are double type. \nUsage: <size: integer>";
    
    static double[][] randomArray2d(int rowCount, int columnCount, double lowerValueBound, double upperValueBound){
        double[][] result = new double[rowCount][columnCount];
        double valueRange = Math.abs(upperValueBound - lowerValueBound);
        double padding = Math.min(lowerValueBound, upperValueBound);
        
        for(double[] row : result){
            for(int i=0; i<row.length; i++){
                row[i] = Math.random() * valueRange + padding;
            }
        }
        return result;
    }
    
    static double[][] matrixProd(double[][] m1, double[][] m2){
        int resultRowCount = m1.length, 
            resultColumnCount = m2[0].length,
            depth = m2.length;
        double[][] result = new double[resultRowCount][resultColumnCount];
        for(int i=0; i<resultRowCount; i++){
            for(int j=0; j<resultColumnCount; j++){
                for(int k=0; k<depth; k++){
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return result;
    }
    
    static String arrayOfDoubleToString(double[] array, String oneFormat) throws Exception {
        if(oneFormat != null && oneFormat.contains("%") && oneFormat.contains("f")){
            List<String> stringList = new LinkedList<String>();
            for (double number : array){
                stringList.add(String.format(oneFormat, number));
            }
            return String.join(" ", stringList);
        } 
        else {
            throw new Exception(String.format("'%s' is not a valid format string for double type.", oneFormat));
        }
    }
    
    public void run(String[] args){
        if(args.length < 1){
            System.out.println(helpString);
            return;
        }
        
        try {
            int size = Integer.parseUnsignedInt(args[0]);
            
            double[][] matrix1 = randomArray2d(size, size, -size, size * 2);
            double[][] matrix2 = randomArray2d(size, size, -size * 2, size * 3);
            
            double[][] prod_1_2 = matrixProd(matrix1, matrix2);
            double[][] prod_2_1 = matrixProd(matrix2, matrix1);
            
            System.out.println("\nM1:");
            for(double[] row : matrix1){
                System.out.println(arrayOfDoubleToString(row, "%7.2f"));
            }
            
            System.out.println("\nM2:");
            for(double[] row : matrix2){
                System.out.println(arrayOfDoubleToString(row, "%7.2f"));
            }
            
            System.out.println("\nMatrix product M1 * M2:");
            for(double[] row : prod_1_2){
                System.out.println(arrayOfDoubleToString(row, "%7.2f"));
            }
            
            System.out.println("\nMatrix product M2 * M1:");
            for(double[] row : prod_2_1){
                System.out.println(arrayOfDoubleToString(row, "%7.2f"));
            }
            
            System.out.println("\n[NOTE] Numbers are rounded to 2 decimal places.");
            
        } catch (Exception ex){
            System.out.println("Exception: "+ ex.getMessage());
        }
    }
}

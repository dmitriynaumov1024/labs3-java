package lab2;

import java.util.Arrays;

/**
 * A program to calculate scalar and vector product of two
 * 3-dimensional vectors. Vectors should be represented with arrays.
 * @author Dmitriy Naumov
 */
public class Task1 implements ITask {
    String helpString = "Calculate scalar and vector product of two 3-dimensional vectors. \nUsage: <x1> <y1> <z1> <x2> <y2> <z2>";
    
    static double vector3dScalarProd(double[] v1, double[] v2) throws Exception {
        if (v1.length != 3 || v2.length != 3){
            throw new Exception("Arrays are expected to have lengths of 3");
        }
        return v1[0] * v2[0] + v1[1] * v2[1] + v1[2] * v2[2];
    }
    
    static double[] vector3dVectorProd(double[] v1, double[] v2) throws Exception {
        if (v1.length != 3 || v2.length != 3){
            throw new Exception("Arrays are expected to have lengths of 3");
        }
        return new double[]{ 
            v1[1] * v2[2] - v1[2] * v2[1],
            v1[2] * v2[0] - v1[0] * v2[2],
            v1[0] * v2[1] - v1[1] * v2[0]
        };
    }
    
    public void run(String[] args){
        if(args.length < 6){
            System.out.println(helpString);
            return;
        }
        
        double[] vector1 = new double[3], 
                 vector2 = new double[3];
        
        vector1[0] = Double.parseDouble(args[0]);
        vector1[1] = Double.parseDouble(args[1]);
        vector1[2] = Double.parseDouble(args[2]);
        
        vector2[0] = Double.parseDouble(args[3]);
        vector2[1] = Double.parseDouble(args[4]);
        vector2[2] = Double.parseDouble(args[5]);
        
        try {
            double scalarProd12 = vector3dScalarProd(vector1, vector2);
            double scalarProd21 = vector3dScalarProd(vector2, vector1);
            double[] vectorProd12 = vector3dVectorProd(vector1, vector2);
            double[] vectorProd21 = vector3dVectorProd(vector2, vector1);
            
            System.out.println("v1 = " + Arrays.toString(vector1));
            System.out.println("v2 = " + Arrays.toString(vector2));
            System.out.println("[Scalar prod] v1 * v2 = " + scalarProd12);
            System.out.println("[Scalar prod] v2 * v1 = " + scalarProd21);
            System.out.println("[Vector prod] v1 x v2 = " + Arrays.toString(vectorProd12));
            System.out.println("[Vector prod] v2 x v1 = " + Arrays.toString(vectorProd21));
        }
        catch (Exception ex) {
            System.out.printf("Exception: %s \n", ex.getMessage());
        }
    }
}

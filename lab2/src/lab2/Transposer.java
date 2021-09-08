package lab2;

/**
 * Contains method to transpose 2-dimensional array (a.k.a matrix)
 * @author Dmitriy Naumov
 */
public class Transposer {
    public static int[][] transpose (int[][] source){
        int[][] result = new int[source[0].length][source.length];
        for (int i=0; i<source.length; i++){
            for(int j=0; j<source[i].length; j++){
                result[j][i] = source[i][j];
            }
        }
        return result;
    }
}

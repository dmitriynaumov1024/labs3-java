package lab1;

/**
 * @author Dmitriy Naumov
 */
public class Lab1 {

    private static String arrayToString(int[] array){
        if(array.length == 0){
            return "{ }";
        }
        String result = "{ " + array[0];
        for(int i=1; i<array.length; i++){
            result += String.format(", %d", array[i]);
        }
        return result + " }";
    }
    
    public static void main(String[] args) {
        int[] array = new int[]{ 1, 2, 3, 4, 5, -1 };
        System.out.println("Before swap: ");
        System.out.println(arrayToString(array));
        ArrayOperations.swapMinMax(array);
        System.out.println("After swap: ");
        System.out.println(arrayToString(array));
    }
}

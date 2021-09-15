package lab3;

import java.util.Locale;

/**
 * The main class
 * @author Dmitriy Naumov
 */
public class Lab3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        
        Point lowerLeft = new Point();
        Point upperRight = new Point();
        Point middlePoint = new Point();
        lowerLeft.x = 0.0;
        lowerLeft.y = 0.0;
        upperRight.x = 1280.0;
        upperRight.y = 1024.0;
        middlePoint.x = 640.0;
        middlePoint.y = 512.0;
        System.out.printf("lowerLeft   : (%.3f, %.3f) \n", lowerLeft.x, lowerLeft.y);
        System.out.printf("upperRight  : (%.3f, %.3f) \n", upperRight.x, upperRight.y);
        System.out.printf("middlePoint : (%.3f, %.3f) \n", middlePoint.x, middlePoint.y);
    }
}

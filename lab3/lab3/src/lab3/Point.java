package lab3;

/**
 * A class that represents 2d point with x, y coordinates.
 * @author Dmitriy Naumov
 */
public class Point {
    public double x;
    public double y;
    
    public static Point origin = new Point();
    
    public double distance(Point that){
        double dx = that.x - this.x,
               dy = that.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
            
}

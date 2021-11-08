package lab6.trees;

/**
 * Tree exception. Can be thrown when something bad occurs in Tree object.
 * @author Dmitriy Naumov
 */
public class TreeException extends Exception {
    
    public int variant;
    
    public TreeException (int variant, String message) {
        super (message);
        this.variant = variant;
    }
    
    public TreeException (int variant) {
        super ();
        this.variant = variant;
    }
    
    public final static int
        UNKNOWN             = 0,
        NODE_UNREACHABLE    = 1,
        NODE_OCCUPIED       = 2,
        NODE_NOTFOUND       = 3;
}

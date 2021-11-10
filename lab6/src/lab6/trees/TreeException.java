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
    
    @Override public String getMessage () {
        switch (this.variant) {
            case NODE_UNREACHABLE:
                return "Node is unreachable.";
            case NODE_OCCUPIED:
                return "Node is already occupied";
            case NODE_NOTFOUND:
                return "Node can not be found.";
            case NODE_EXISTS:
                return "Node already exists.";
            default:
                return "Tree exception";
        }
    }
    
    public final static int
        UNKNOWN             = 0,
        NODE_UNREACHABLE    = 1,
        NODE_OCCUPIED       = 2,
        NODE_NOTFOUND       = 3,
        NODE_EXISTS         = 4;
}

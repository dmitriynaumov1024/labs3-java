package lab6.trees.generic;

/**
 * A generic tree node.
 * @author Dmitriy Naumov
 */
public class TreeNode<T> extends lab6.trees.TreeNode {
    /**
     * Value of this node.
     */
    public T value;
    
    /**
     * Create new generic TreeNode of given type and initialize it with value.
     * @param value value to write into this node.
     */
    public TreeNode (T value) {
        this.value = value;
    }
}

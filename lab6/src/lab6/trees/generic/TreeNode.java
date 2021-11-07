package lab6.trees.generic;

/**
 * A generic tree node.
 * @author Dmitriy Naumov
 */
public class TreeNode<T> extends lab6.trees.TreeNode {
    public T value;
    
    public TreeNode (T value) {
        this.value = value;
    }
}

package lab6.trees;

/**
 * Node of Tree. Contains object pointers to left and right child. 
 * Does not contain any value.
 * @author Dmitriy Naumov
 */
public class TreeNode {
    public TreeNode childL;
    public TreeNode childR;
 
    public TreeNode () { }

    public TreeNode (TreeNode left, TreeNode right) {
        this.childL = left;
        this.childR = right;
    }
}

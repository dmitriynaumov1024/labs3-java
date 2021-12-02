package lab6.trees;

/**
 * Node of Tree. Contains object pointers to left and right child. 
 * Does not contain any value.
 * @author Dmitriy Naumov
 */
public class TreeNode {
    
    /**
     * Left child
     */
    public TreeNode childL;
    
    /**
     * Right child
     */
    public TreeNode childR;
 
    /**
     * Create new, empty tree node.
     */
    public TreeNode () { }

    /**
     * Create new tree node with given children
     * @param left left child
     * @param right right child
     */
    public TreeNode (TreeNode left, TreeNode right) {
        this.childL = left;
        this.childR = right;
    }
    
    /**
     * Get child by index. 0 is left child, 1 is right child. 
     * With any other index value, this method will return null.
     * @param index index
     * @return left child if index is 0; right child if index is 1; null by default.
     */
    public TreeNode getChild (int index) {
        switch (index) {
            case 0: 
                return this.childL;
            case 1: 
                return this.childR;
            default: 
                return null;
        }
    }
    
    /**
     * Get child by boolean index. 
     * @param index index
     * @return left child if index is false; right child if index is true
     */
    public TreeNode getChild (boolean index) {
        return index ? this.childR : this.childL;
    }
    
    /**
     * Set child at given index. 0 is for left child, 1 is for right child. 
     * If index is neither 0 nor 1, no actions are performed.
     * @param index index
     * @param node node to set
     */
    public void setChild (int index, TreeNode node) {
        if (index == 0) {
            this.childL = node;
        }
        else if (index == 1) {
            this.childR = node;
        }
    }
}

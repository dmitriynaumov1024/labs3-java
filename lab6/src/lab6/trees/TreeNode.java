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
    
    public TreeNode getChild (boolean index) {
        return index ? this.childR : this.childL;
    }
    
    public void setChild (int index, TreeNode node) {
        if (index == 0) {
            this.childL = node;
        }
        else if (index == 1) {
            this.childR = node;
        }
    }
}

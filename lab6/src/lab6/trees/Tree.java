package lab6.trees;

import java.util.Stack;

/**
 * A binary tree without any values.
 * @author Dmitriy Naumov
 */
public class Tree {
    
    private TreeNode root;
    
    /**
     * Create new, empty Tree.
     */
    public Tree () {
        this.root = new TreeNode();
        this.root.childL = root;
    }
    
    /**
     * Make this Tree full (all nodes with 2 children are placed contigously, 
     * left to right, top to bottom).
     */
    public final void restore () {
        
    }
    
    /**
     * Check if this Tree is full/complete.
     * @return whether this Tree is full/complete or not.
     */
    public final boolean checkCompletion () {
        return true;
    }
    
    /**
     * Check if there is a node in this Tree at given depth and 
     * horizontal position.
     * @param depth depth of required node.
     * @param horizontal horizontal position of required node.
     * @return if there is a node at given depth and horizontal position.
     */
    public final boolean hasNodeAt (int depth, int horizontal) {
        try {
            return this.getNode(address(depth, horizontal)) != null;
        }
        finally {
            return false;
        }
    }
    
    /**
     * Check if node can be added at given position. To add node, there must 
     * be path to the node and given position must not be occupied. 
     * @param depth depth of required node.
     * @param horizontal horizontal position of required node.
     * @return if node can be added in given position.
     */
    public final boolean canAddNodeAt (int depth, int horizontal) {
        try {
            return this.getNode(address(depth, horizontal) / 2)
                       .getChild(horizontal % 2) == null;
        }
        finally {
            return false;
        }
    }
    
    /**
     * Create new node at given depth-horizontal address only if the
     * address is accessible by already existing path and the address is not 
     * taken. Otherwise this method will throw TreeException.
     * @param depth depth of required node.
     * @param horizontal horizontal position of required node.
     * @throws TreeException
     */
    public final void addNodeAt (int depth, int horizontal) throws TreeException {
        TreeNode node = this.getNode(address(depth, horizontal) / 2);
        if (node.getChild(horizontal % 2) != null) {
            throw new TreeException (TreeException.NODE_OCCUPIED);
        }
        node.setChild(horizontal % 2, new TreeNode());
    }
    
    /**
     * Remove node at given depth-horizontal address only if there is a node.
     * Otherwise this method will throw TreeException.
     * @param depth depth of required node.
     * @param horizontal horizontal position of required node.
     * @throws TreeException
     */
    public void removeNodeAt (int depth, int horizontal) throws TreeException {
        TreeNode node = this.getNode(address(depth, horizontal) / 2);
        if (node.getChild(horizontal % 2) == null) {
            throw new TreeException (TreeException.NODE_NOTFOUND);
        }
        node.setChild(horizontal % 2, null);
    }
    
    protected TreeNode getNode (int address) throws TreeException {
        TreeNode current = this.root;
        for (Boolean waypoint : getPath(address)) {
            current = current.getChild(waypoint);
            if (current == null){
                throw new TreeException (TreeException.NODE_UNREACHABLE);
            }
        }
        return current;
    }
    
    @Override protected void finalize() throws Throwable {
        if (this.root != null) this.root.childL = null;
        super.finalize();
    }
    
    static Stack<Boolean> getPath (int address) {
        if (address <= 0){
            return new Stack<>();
        }
        Stack<Boolean> result = new Stack<>();
        while (address > 0) {
            result.push((address % 2 == 0) ? Boolean.FALSE : Boolean.TRUE);
            address /= 2;
        }
        return result;
    }
    
    static int address (int depth, int horizontal) {
        return 1 << depth + horizontal;
    }
}

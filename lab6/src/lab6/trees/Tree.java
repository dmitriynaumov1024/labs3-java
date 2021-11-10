package lab6.trees;

import java.util.Stack;

/**
 * A binary tree without any values.
 * @author Dmitriy Naumov
 */
public class Tree {
    
    protected TreeNode root;
    
    /**
     * Create new, empty Tree.
     */
    public Tree () {
        this.root = new TreeNode();
        this.root.childL = root;
    }
    
    /**
     * Make this Tree full/complete (all nodes with 2 children are placed 
     * contigously, left to right, top to bottom).
     */
    public void restore () {
        
    }
    
    /**
     * Check if this Tree is full/complete.
     * In a Full/Complete Tree, last index of non-null node must be lesser than
     * first index of null node.
     * @return whether this Tree is full/complete or not.
     */
    public final boolean checkCompletion () {
        TreeNode current = this.root.getChild(1);
        if (current == null) return true;
        int[] addressHolder = new int[] { Integer.MAX_VALUE, 0 };
        checkCompletionRecursive(current, 1, addressHolder);
        return addressHolder[0] > addressHolder[1];
    }
    
    /**
     * Recursively check for completion.
     * @param node current node
     * @param address actual address of node
     * @param addressHolder holds 2 integers: 
     *                      #0 is for first null address,
     *                      #1 is for last non-null address.
     */
    private static void checkCompletionRecursive (TreeNode node, int address, int[] addressHolder) {
        if (node == null) {
            if (addressHolder[0] > address) addressHolder[0] = address;
            return;
        }
        else {
            if (addressHolder[1] < address) addressHolder[1] = address;
            checkCompletionRecursive(node.getChild(0), address * 2, addressHolder);
            checkCompletionRecursive(node.getChild(1), address * 2 + 1, addressHolder);
        }        
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
        catch (TreeException ex) {
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
            TreeNode node = this.getNode(address(depth, horizontal) / 2);
            return node.getChild(address(depth, horizontal) % 2) == null;
        }
        catch (TreeException ex) {
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
    public void addNodeAt (int depth, int horizontal) throws TreeException {
        TreeNode node = this.getNode(address(depth, horizontal) / 2);
        if (node.getChild(address(depth, horizontal) % 2) != null) {
            throw new TreeException (TreeException.NODE_EXISTS);
        }
        node.setChild(address(depth, horizontal) % 2, new TreeNode());
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
        if (node.getChild(address(depth, horizontal) % 2) == null) {
            throw new TreeException (TreeException.NODE_NOTFOUND);
        }
        node.setChild(address(depth, horizontal) % 2, null);
    }
    
    protected TreeNode getNode (int address) throws TreeException {
        TreeNode current = this.root;
        Stack<Integer> path = getPath(address);
        while (path.size() > 0) {
            Integer waypoint = path.pop();
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
    
    protected static Stack<Integer> getPath (int address) {
        if (address <= 0){
            return new Stack<>();
        }
        Stack<Integer> result = new Stack<>();
        while (address > 0) {
            result.push(address % 2);
            address /= 2;
        }
        return result;
    }
    
    protected static int address (int depth, int horizontal) {
        return (1 << depth) + horizontal;
    }
}

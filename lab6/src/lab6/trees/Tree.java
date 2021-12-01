package lab6.trees;

import java.util.Stack;

/**
 * A binary tree without any values. This tree is made of value-less tree nodes
 * 
 * @see TreeNode.java 
 * @author Dmitriy Naumov
 */
public class Tree {
    
    /**
     * root node of this tree
     */
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
     * @return whether node can be added in given position.
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
     * @throws TreeException if parent node cannot be reached or if node 
     * already exists.
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
     * @throws TreeException if parent node cannot be reached or node does 
     * not exist.
     */
    public void removeNodeAt (int depth, int horizontal) throws TreeException {
        TreeNode node = this.getNode(address(depth, horizontal) / 2);
        if (node.getChild(address(depth, horizontal) % 2) == null) {
            throw new TreeException (TreeException.NODE_NOTFOUND);
        }
        node.setChild(address(depth, horizontal) % 2, null);
    }
    
    /**
     * Get node with given linear address.
     * @param address linear address of node
     * @return node with given address
     * @throws TreeException if node cannot be found.
     */
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
    
    /**
     * Break cycle reference at root node of this tree to ensure that nodes can
     * be finalized without problems. Is necessary when reference-counting 
     * garbage collector is used.
     * @throws Throwable if superclass finalization went wrong 
     */
    @Override protected void finalize() throws Throwable {
        if (this.root != null) this.root.childL = null;
        super.finalize();
    }
    
    /**
     * Get path to node with given linear address. Path is a stack of waypoints 
     * with 0..1 values indicating whether search should go to left or right 
     * child of node. It is basically a binary representation of address with 
     * most significant bit on top of the stack.
     * @param address linear address of node
     * @return a stack of waypoints with 0..1 values indicating whether search 
     * should go to left or right child of node.
     */
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
    
    /**
     * Convert depth-horizontal address to linear address of node. Linear 
     * address is then to be converted to stack of waypoints to reach the node.
     * @param depth depth of node (depth of first level is 0)
     * @param horizontal horizontal position of node (from 0 to 2^depth - 1 inclusively). 
     * Horizontal overflow will transfer to next depth levels.
     * @return linear address of node
     */
    protected static int address (int depth, int horizontal) {
        return (1 << depth) + horizontal;
    }
}

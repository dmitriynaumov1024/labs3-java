package lab6.trees.generic;

import lab6.trees.*;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/**
 * A generic binary tree.
 * @see lab6.trees.Tree
 * @see lab6.trees.generic.TreeNode
 * @param <T> data type.
 * @author Dmitriy Naumov
 */
public class Tree<T> extends lab6.trees.Tree implements Iterable<T> {
    
    /**
     * Create new, empty tree.
     */
    public Tree () {
        this.root = new TreeNode<T>(null);
        this.root.setChild(0, root);
    }
    
    /**
     * Create new tree from given iterable collection of values. Values are 
     * placed row by row. Constructed tree is always complete.
     * @param values values to fill the tree
     */
    public Tree (Iterable<T> values) {
        this();
        Iterator<T> iter = values.iterator();
        if (!iter.hasNext()) return;
        TreeNode<T> node = new TreeNode<>(iter.next());
        this.root.setChild(1, node);
        int depth = 1;
        while (iter.hasNext())
        {
            buildLevelsRecursive (node, depth, iter);
            depth++;
        }
    }
    
    /**
     * Create new tree with the same value repeated.
     * @param val value to use
     * @param count number of repetitions
     */
    public Tree (T val, int count) {
        this(Repeat.<T>times(count).value(val));
    }
    
    /**
     * Create new tree with given number of empty (null-valued) nodes.
     * @param count required number of nodes.
     */
    public Tree (int count) {
        this(Repeat.<T>times(count).value(null));
    }
    
    /**
     * Build a specified level of tree, using specific iterator as item source.
     * This is a part of Tree construction algorithm.
     */
    private void buildLevelsRecursive (
            TreeNode<T> node, 
            int depth, 
            Iterator<T> valueIterator) 
    {
        if (depth == 1) {
            if (node.getChild(0) == null && valueIterator.hasNext()){
                node.setChild(0, new TreeNode<>(valueIterator.next()));
            }
            if (node.getChild(1) == null && valueIterator.hasNext()){
                node.setChild(1, new TreeNode<>(valueIterator.next()));
            }
            return;
        }
        else {
            buildLevelsRecursive ((TreeNode<T>)node.getChild(0), depth - 1, valueIterator);
            buildLevelsRecursive ((TreeNode<T>)node.getChild(1), depth - 1, valueIterator);
            return;
        }
    }
    
    @Override public void restore () {
        this.restore (true);
    }
    
    /**
     * Make this Tree full/complete. Optionally null values can be kept or 
     * discarded.
     * @param keepNulls whether to keep null values
     */
    public void restore (boolean keepNulls) {
        System.out.printf ("[debug]: Tree<T>.restore \n");
        TreeNode<T> node = (TreeNode<T>) this.root.getChild(1);
        List<T> list = new LinkedList<>();
        int depth = -1;
        do {
            depth++;
        } 
        while (collectNodesRecursive(node, depth, list, keepNulls));
        this.root = new Tree(list).root;
    }
    
    /**
     * Collect all node values recursively. 
     * This is a part of restoration algorithm.
     * @param node current node
     * @param depth required depth
     * @param valuesHolder queue where badly placed values are stored.
     * @param keepNulls whether to keep nulls
     */
    private boolean collectNodesRecursive (
            TreeNode<T> node, 
            int depth, 
            List<T> valuesHolder, 
            boolean keepNulls) 
    {
        if (node == null) {
            return false;
        }
        else if (depth == 0) {
            if (keepNulls || node.value != null) {
                valuesHolder.add(node.value);
                
            }
            return true;
        }
        else {
            boolean result0 = collectNodesRecursive (
                    (TreeNode<T>)node.getChild(0), 
                    depth - 1, 
                    valuesHolder, 
                    keepNulls
            );
            
            boolean result1 = collectNodesRecursive (
                    (TreeNode<T>)node.getChild(1), 
                    depth - 1, 
                    valuesHolder, 
                    keepNulls
            );
            
            return result0 || result1;
        }
    }
    
    /**
     * Create new node at given depth-horizontal address only if the
     * address is accessible by already existing path and the address is not 
     * taken. Otherwise this method will throw TreeException.
     * @param depth depth of required node.
     * @param horizontal horizontal position of required node.
     * @throws TreeException if node at given address already exists
     */
    @Override public void addNodeAt (int depth, int horizontal) throws TreeException {
        TreeNode<T> node = (TreeNode<T>)this.getNode(address(depth, horizontal) / 2);
        if (node.getChild(address(depth, horizontal) % 2) != null) {
            throw new TreeException (TreeException.NODE_EXISTS);
        }
        node.setChild(address(depth, horizontal) % 2, new TreeNode<T>(null));
    }
    
    /**
     * Set value at given depth-horizontal address.
     * @param depth depth of node
     * @param horizontal horizontal offset of node
     * @param value new value
     * @throws TreeException if node cannot be reached
     */
    public void setValueAt (int depth, int horizontal, T value) throws TreeException {
        TreeNode<T> node = (TreeNode<T>) this.getNode(address(depth, horizontal));
        node.value = value;
    }
    
    /**
     * Get value at given depth-horizontal address. 
     * @param depth depth of node
     * @param horizontal horizontal offset of node.
     * @return value of node at given depth and horizontal offset
     * @throws TreeException if node cannot be reached
     */
    public T getValueAt (int depth, int horizontal) throws TreeException {
        TreeNode<T> node = (TreeNode<T>) this.getNode(address(depth, horizontal));
        return node.value;
    }
    
    /**
     * Create an iterator for this tree
     * @return iterator for this tree
     */
    @Override public Iterator<T> iterator () {
        return new TreeIterator<T>((TreeNode<T>)this.root.getChild(1));
    }
    
    /**
     * Create level-by-level iterable for this tree.
     * @see lab6.trees.generic.TreeLevelIterable
     * @return level-by-level iterable for this tree
     */
    public TreeLevelIterable<T> levels () {
        return new TreeLevelIterable<>((TreeNode<T>)this.root);    
    }
    
    /** 
     * Merge this Tree with other Tree and return a new Tree.
     * @param other other Tree
     * @return new Tree with values from both trees
     */
    public Tree<T> merge (Tree<T> other) {
        Tree<T> result = new Tree<T>();
        Iterator<T> iterThis = this.iterator();
        Iterator<T> iterOther = other.iterator();
        if (iterThis.hasNext()) {
            TreeNode<T> node = new TreeNode<>(iterThis.next());
            result.root.setChild(1, node);
            int depth = 1;
            while (iterThis.hasNext()) {
                buildLevelsRecursive (node, depth, iterThis);
                depth++;
            }
            depth--;
            while (iterOther.hasNext()) {
                buildLevelsRecursive (node, depth, iterOther);
                depth++;
            }
        }
        return result;
    }
    
    /**
     * String representation of this tree.
     * @return all elements of this tree in one row, separated with whitespace.
     */
    @Override public String toString () {
        StringBuilder sb = new StringBuilder();
        for (T item : this) {
            sb.append(item.toString());
            sb.append(" ");
        }
        return sb.toString();
    }
    
}

package lab6.trees.generic;

import lab6.trees.*;
import java.util.Queue;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A generic binary tree.
 * 
 * @param <T> Type of value.
 * @author Dmitriy Naumov
 */
public class Tree<T> extends lab6.trees.Tree implements Iterable<T> {
    
    public Tree () {
        this.root = new TreeNode<T>(null);
        this.root.setChild(0, root);
    }
    
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
        Queue<T> que = new ConcurrentLinkedQueue<>();
        int[] addressHolder = new int[] { Integer.MAX_VALUE };
        int depth = 0;
        do {
            depth++;
        } 
        while (collectBadNodesRecursive(node, depth, 1 << depth, addressHolder, que, keepNulls));
        
        for (T item : que) {
            System.out.printf ("%s ", item);
        }
        System.out.printf ("\n");
    }
    
    /**
     * Collect all bad node values recursively. 
     * This is a part of restoration algorithm.
     * @param node current node
     * @param depth required depth
     * @param address node address
     * @param addressHolder array where first null value address is stored.
     * @param valuesHolder queue where badly placed values are stored.
     * @param keepNulls whether to keep nulls
     */
    private boolean collectBadNodesRecursive (
            TreeNode<T> node, 
            int depth, 
            int address,
            int[] addressHolder, 
            Queue<T> valuesHolder, 
            boolean keepNulls) 
    {
        if (node == null) {
            if (address < addressHolder[0]) {
                addressHolder[0] = address;
            }
            return false;
        }
        else if (depth == 0) {
            if (keepNulls || node.value != null) {
                if (address > addressHolder[0]){
                    valuesHolder.add(node.value);
                }
            }
            else if (address < addressHolder[0]){
                addressHolder[0] = address;
            }
            return true;
        }
        else {
            boolean result0 = collectBadNodesRecursive (
                    (TreeNode<T>)node.getChild(0), 
                    depth - 1, 
                    address * 2, 
                    addressHolder, 
                    valuesHolder, 
                    keepNulls
            );
            
            boolean result1 = collectBadNodesRecursive (
                    (TreeNode<T>)node.getChild(1), 
                    depth - 1, 
                    address * 2 + 1, 
                    addressHolder, 
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
     * @throws TreeException
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
     * @throws TreeException 
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
     * @throws TreeException 
     */
    public T getValueAt (int depth, int horizontal) throws TreeException {
        TreeNode<T> node = (TreeNode<T>) this.getNode(address(depth, horizontal));
        return node.value;
    }
    
    @Override public Iterator<T> iterator () {
        return new TreeIterator<T>((TreeNode<T>)this.root.getChild(1));
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
            this.root.setChild(1, node);
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
    
    @Override public String toString () {
        StringBuilder sb = new StringBuilder();
        for (T item : this) {
            sb.append(item.toString());
            sb.append(" ");
        }
        return sb.toString();
    }
}

package lab6.trees.generic;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Iterator for generic tree.
 * @author Dmitriy Naumov
 */
public class TreeIterator<T> implements java.util.Iterator<T> {

    private Queue<T> items;
    private TreeNode<T> node;
    private int currentDepth;
    
    public TreeIterator (TreeNode<T> source) {
        this.items = new ConcurrentLinkedQueue<>();
        this.node = source;
        this.currentDepth = -1;
    }
    
    @Override public boolean hasNext() {
        if (this.items.isEmpty()){
            this.currentDepth++;
            return this.collectValuesRecursive(this.node, this.currentDepth, this.items);
        }
        else return true;
    }

    @Override public T next() {
        return this.items.poll();
    }
    
    private boolean collectValuesRecursive (
            TreeNode<T> node, 
            int depth,
            Queue<T> valuesHolder) 
    {
        if (node == null) {
            return false;
        }
        else if (depth == 0) {
            valuesHolder.add(node.value);
            return true;
        }
        else {
            boolean result0 = collectValuesRecursive (
                    (TreeNode<T>)node.getChild(0), 
                    depth - 1, 
                    valuesHolder
            );
            
            boolean result1 = collectValuesRecursive (
                    (TreeNode<T>)node.getChild(1), 
                    depth - 1,
                    valuesHolder
            );
            
            return result0 || result1;
        }
    }
}

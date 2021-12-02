package lab6.trees.generic;

import lab6.queue.generic.Queue;

/**
 * Iterator for generic tree.
 * @author Dmitriy Naumov
 * @see java.util.Iterator
 * @see lab6.trees.generic.Tree
 */
public class TreeIterator<T> implements java.util.Iterator<T> {

    private Queue<T> items;
    private TreeNode<T> node;
    private int currentDepth;
    
    /**
     * Create new iterator from given generic Tree
     * @param source given tree to iterate over it
     */
    public TreeIterator (TreeNode<T> source) {
        this.items = new Queue<>();
        this.node = source;
        this.currentDepth = -1;
    }
    
    /**
     * Check whether this iterator has more elements
     * @return whether this iterator has more elements
     */
    @Override public boolean hasNext() {
        if (this.items.isEmpty()){
            this.currentDepth++;
            return this.collectValuesRecursive(this.node, this.currentDepth, this.items);
        }
        else return true;
    }
    
    /**
     * Get next element of this iterator, if available.
     * @return next element of this iterator
     */
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

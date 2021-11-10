package lab6.trees.generic;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/**
 * Allows iterating level-by-level over generic Tree.
 * @author Dmitriy Naumov
 */
public class TreeLevelIterator<T> implements Iterator<Iterable<T>> {
    
    private List<T> items;
    private TreeNode<T> node;
    private int currentDepth;
    
    public TreeLevelIterator (TreeNode<T> node) {
        this.node = node;
        this.currentDepth = -1;
    }
    
    @Override public boolean hasNext () {
        this.items = new LinkedList<T>();
        this.currentDepth++;
        return this.collectValuesRecursive(this.node, this.currentDepth, this.items);
    }
    
    @Override public Iterable<T> next () {
        List<T> result = this.items;
        this.items = null;
        return result;
    }
    
    private boolean collectValuesRecursive (
            TreeNode<T> node, 
            int depth,
            List<T> valuesHolder) 
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

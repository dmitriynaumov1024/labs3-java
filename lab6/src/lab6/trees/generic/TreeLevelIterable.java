package lab6.trees.generic;

import java.util.Iterator;

/**
 * Allows iterating level-by-level over generic Tree. 
 * @author Dmitriy Naumov
 * @see lab6.trees.generic.Tree
 */
public class TreeLevelIterable<T> implements Iterable<Iterable<T>> {
    
    private TreeNode<T> root;
    
    /**
     * Create new TreeLevelIterable from given generic Tree.
     * @param sourceRoot root of tree
     */
    public TreeLevelIterable (TreeNode<T> sourceRoot) {
        this.root = sourceRoot;
    }
    
    /**
     * Create an iterator for this iterable.
     * @return an iterator for this iterable.
     */
    @Override public Iterator<Iterable<T>> iterator () {
        return new TreeLevelIterator<T>((TreeNode<T>)this.root.getChild(1));
    }
    
    /**
     * String representation of this object.
     * @return elements of this tree, level-by-level, separated with commas
     */
    @Override public String toString () {
        StringBuilder sb = new StringBuilder();
        int depth = 0;
        for (Iterable<T> level : this) {
            sb.append(String.format("%d: [", depth));
            ++depth;
            int index = 0;
            for (T item : level) {
                sb.append(String.format(index++ > 0 ? ", %s" : "%s", item));
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}

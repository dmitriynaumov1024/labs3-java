package lab6.heap.generic;

import lab6.trees.generic.*;

/**
 * A generic heap
 * @author Dmitriy Naumov
 */
public class Heap<T extends Comparable<T>> extends Tree<T> {
    
    private int size;
    
    public Heap () {
        super();
        this.size = 0;
    }
    
    public Heap (T[] source) {
        this();
        for (T item : source) {
            this.add(item);
        }
    }
    
    public Heap (Iterable<T> source) {
        this();
        for (T item : source) {
            this.add(item);
        }
    }
    
    /**
     * In Heap, restoration makes this heap satisfy heap condition: 
     * every child value must be greater than current node value.
     */
    @Override public void restore () {
        try {
            if (this.size > 1) {
                for (int i=this.size / 2; i > 0; i--)
                    restoreRecursive ((TreeNode<T>)this.getNode(i));
            }
        } 
        catch (Exception ex) {
            return;
        }
    }
    
    private void restoreRecursive (TreeNode<T> node) {
        TreeNode<T>
            leftChild  = (TreeNode<T>)node.getChild(0),
            rightChild = (TreeNode<T>)node.getChild(1);
        
        if (leftChild == null && rightChild == null) return;
        
        TreeNode<T> min = node;
        if (leftChild != null && min.value.compareTo(leftChild.value)> 0) {
            min = leftChild;
        }
        if (rightChild != null && min.value.compareTo(rightChild.value)> 0) {
            min = rightChild;
        }
        
        if (min != node) {
            T tmp = node.value;
            node.value = min.value;
            min.value = tmp;
            restoreRecursive (min);
        }
    }
    
    public void add (T val) {
        if (this.size == 0) {
            this.root.setChild(1, new TreeNode<T>(val));
        }
        else {
            try {
                TreeNode<T> node = (TreeNode<T>)this.getNode((this.size + 1) / 2);
                node.setChild((this.size + 1) % 2, new TreeNode<T>(val));
            }
            catch (Exception ex) {
                return;
            }
        }
        this.size++;
    }
    
    public void add (T[] values) {
        for (T item : values) {
            this.add(item);
        }
    }
    
    public void add (Iterable<T> values) {
        for (T item : values) {
            this.add(item);
        }
    }
    
    public void removeAt (int address) {
        try {
            TreeNode<T> 
                    node = (TreeNode<T>)this.getNode(address),
                    lastParent = (TreeNode<T>)this.getNode(this.size / 2),
                    last = (TreeNode<T>)lastParent.getChild(this.size % 2);
            
            if (node != null) {
                node.value = last.value;
                lastParent.setChild(this.size % 2, null);
                this.size--;
            }
            
        }
        catch (Exception ex) {
            return;
        }
    }
    
    public void setAt (int address, T val) throws Exception {
        TreeNode<T> node = (TreeNode<T>)this.getNode(address);
        if (node != null) {
            node.value = val;
        }
    }
    
    public T getValueAt (int address) throws Exception {
        TreeNode<T> node = (TreeNode<T>)this.getNode(address);
        return node.value;
    }
    
    public int size () {
        return this.size;
    }
}

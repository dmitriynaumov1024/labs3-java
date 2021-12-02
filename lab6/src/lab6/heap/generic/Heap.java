package lab6.heap.generic;

import lab6.trees.generic.*;

/**
 * A generic heap. Heap is a variant of Tree where every element conforms to 
 * heap condition: every child value must be greater than parent node value.
 * This heap is built on generic binary tree nodes and inherits functionality
 * of generic binary tree.
 * @param <T> data type
 * @author Dmitriy Naumov
 * @see lab6.trees.generic.Tree
 * @see lab6.trees.generic.TreeNode
 * @see <a href="https://en.wikipedia.org/wiki/Heap_(data_structure)">Heap (data structure) on Wikipedia</a>
 */
public class Heap<T extends Comparable<T>> extends Tree<T> {
    
    private int size;
    
    /**
     * Create new, empty heap. 
     */
    public Heap () {
        super();
        this.size = 0;
    }
    
    /**
     * Create new heap from an array of items. 
     * @param source array of items to use
     */
    public Heap (T[] source) {
        this();
        for (T item : source) {
            this.add(item);
        }
        this.restore();
    }
    
    /**
     * Create new heap from an iterable collection of items.
     * @param source iterable collection of items to use
     */
    public Heap (Iterable<T> source) {
        this();
        for (T item : source) {
            this.add(item);
        }
        this.restore();
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
    
    /**
     * Add new value to the end of this heap.
     * @param val value to add
     * @throws Error if null value was passed as parameter, because null values are not allowed in heap.
     */
    public void add (T val) {
        if (val == null) {
            throw new Error("Null values are not allowed in Heap.");
        }
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
    
    /**
     * Add an array of elements to this heap.
     * @param values array of elements to add to this heap
     */
    public void add (T[] values) {
        for (T item : values) {
            this.add(item);
        }
    }
    
    /**
     * Add an iterable collection of elements to this heap.
     * @param values iterable collection of elements to add to this heap
     */
    public void add (Iterable<T> values) {
        for (T item : values) {
            this.add(item);
        }
    }
    
    /**
     * Remove an element at given address in this heap and fill the gap with 
     * the last element of this heap. If given address can not be reached, no 
     * action is performed.
     * @param address address of element to remove
     */
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
    
    /**
     * Set value at given address.
     * @param address address of node
     * @param val value to write into node
     * @throws Exception if node can't be reached.
     */
    public void setAt (int address, T val) throws Exception {
        if (val == null) {
            throw new Error("Null values are not allowed in Heap.");
        }
        TreeNode<T> node = (TreeNode<T>)this.getNode(address);
        if (node != null) {
            node.value = val;
        }
    }
    
    /**
     * Get value at given address
     * @param address address of node
     * @return value at given address, if address is reachable
     * @throws Exception if node can't be reached.
     */
    public T getValueAt (int address) throws Exception {
        TreeNode<T> node = (TreeNode<T>)this.getNode(address);
        return node.value;
    }
    
    /**
     * Get size of this heap.
     * @return size of this heap.
     */
    public int size () {
        return this.size;
    }
}

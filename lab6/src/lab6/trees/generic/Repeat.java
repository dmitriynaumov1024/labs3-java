package lab6.trees.generic;

import java.util.Iterator;

/**
 * Repeating iterable. Allows to iterate over a sequence of repeated value.
 * @param <T> data type
 * @author Dmitriy Naumov
 */
public class Repeat<T> implements Iterable<T>, Iterator<T> {
    private T value;
    private int count;
    
    private boolean 
        hasValue, 
        hasCount;
    
    private Repeat () { 
        count = 0;
    }
    
    /**
     * Set up value for this repeating iterable.
     * @param val value to use
     * @return the same object with given value.
     */
    public Repeat<T> value (T val) {
        Repeat<T> result = this;
        result.value = val;
        result.hasValue = true;
        return result;
    }
    
    /**
     * Create new iterable and initialize it with given number of repetitions. 
     * To set up value, use <code>value(T)</code> method.
     * @param <T> data type
     * @param count number of repetitions.
     * @return new Repeat with given count, but with no value.
     */
    public static <T> Repeat<T> times (int count) {
        Repeat<T> result = new Repeat();
        result.count = count;
        result.hasCount = true;
        return result;
    }
    
    /**
     * Get an iterator for this iterable.
     * @return an iterator for this iterable
     */
    @Override public Iterator<T> iterator () {
        return this;
    }
    
    /**
     * Check if this iterator has more elements
     * @return true if this iterator has more elements, otherwise false.
     */
    @Override public boolean hasNext () {
        return this.hasValue && this.hasCount && this.count > 0;
        
    }
    
    /**
     * Get next element in this iterator
     * @return next element in this iterator
     */
    @Override public T next () {
        this.count--;
        return this.value;
    }
}

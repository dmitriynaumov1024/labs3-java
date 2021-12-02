package lab6.trees.generic;

import java.util.Iterator;

/**
 * Allows iterating over array. Can be used in <code>for ( : )</code> statements.
 * @param <T> data type of array
 * @author Dmitriy Naumov
 * @see java.lang.Iterable
 * @see java.util.Iterator
 */
public class ArrayIterable<T> implements Iterable<T> {
    
    private T[] data;
    
    /**
     * Create new ArrayIterable from given array.
     * @param source array to iterate
     */
    public ArrayIterable (T[] source) {
        this.data = source;
    }
    
    /**
     * Get an iterator for this iterable
     * @return iterator for this iterable
     */
    @Override public Iterator<T> iterator () {
        return new ArrayIterator<T> (this.data);
    }
    
    /**
     * Allows iterating over array.
     * @param <T> data type of underlying array
     */
    class ArrayIterator<T> implements Iterator<T> {
        
        private T[] data;
        private int index;
        
        /**
         * Create new ArrayIterator from given array.
         * @param data array to iterate
         */
        public ArrayIterator (T[] data) {
            this.data = data;
            this.index = 0;
        }
        
        /**
         * Check whether this iterator has more elements
         * @return whether this iterator has more elements
         */
        @Override public boolean hasNext () {
            return this.index < this.data.length;
        }
        
        /**
         * Get next element in underlying array, if available
         * @return next element in underlying array
         */
        @Override public T next () {
            return this.data[index++];
        }
    }
    
}

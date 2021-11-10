package lab6.trees.generic;

import java.util.Iterator;

/**
 * Allows iterating over array.
 * @author Dmitriy Naumov
 */
public class ArrayIterable<T> implements Iterable<T> {
    
    private T[] data;
    
    public ArrayIterable (T[] source) {
        this.data = source;
    }
    
    @Override public Iterator<T> iterator () {
        return new ArrayIterator<T> (this.data);
    }
    
    class ArrayIterator<T> implements Iterator<T> {
        
        private T[] data;
        private int index;
        
        public ArrayIterator (T[] data) {
            this.data = data;
            this.index = 0;
        }
        
        @Override public boolean hasNext () {
            return this.index < this.data.length;
        }
        
        @Override public T next () {
            return this.data[index++];
        }
    }
    
}

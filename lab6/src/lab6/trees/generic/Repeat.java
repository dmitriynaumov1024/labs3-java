package lab6.trees.generic;

import java.util.Iterator;

/**
 * Repeatable iterable
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
    
    public Repeat<T> value (T val) {
        Repeat<T> result = this;
        result.value = val;
        result.hasValue = true;
        return result;
    }
    
    public static <T> Repeat<T> times (int val) {
        Repeat<T> result = new Repeat();
        result.count = val;
        result.hasCount = true;
        return result;
    }
    
    @Override public Iterator<T> iterator () {
        return this;
    }
    
    @Override public boolean hasNext () {
        return this.hasValue && this.hasCount && this.count > 0;
        
    }
    
    @Override public T next () {
        this.count--;
        return this.value;
    }
}

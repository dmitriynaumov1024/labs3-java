package autoservice.util;

/**
 * A class to represent several identical items.
 * @param <T> item type
 * @author Dmitriy Naumov
 */
public class Several<T> implements java.io.Serializable {
    public T value;
    public int count;
    
    public Several () { }
    
    public Several (int count, T val) {
        this.count = count;
        this.value = val;
    }
}

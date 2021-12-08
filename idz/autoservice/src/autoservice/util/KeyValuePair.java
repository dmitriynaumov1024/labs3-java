package autoservice.util;

/**
 * Generic Pair
 * @author Dmitriy Naumov
 */
public class KeyValuePair <TKey, TValue> {
    public TKey key;
    public TValue value;
    
    public KeyValuePair (TKey key, TValue value) {
        this.key = key;
        this.value = value;
    }
}

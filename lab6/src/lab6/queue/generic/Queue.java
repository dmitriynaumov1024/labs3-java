package lab6.queue.generic;

/**
 * A generic queue which allows to add and take elements from opposite ends, 
 * also known as FIFO
 * @param <T> data type
 * @author Dmitriy Naumov
 * @see <a href="https://en.wikipedia.org/wiki/Queue_(abstract_data_type)">Queue (abstract data type) on Wikipedia</a>
 */
public class Queue<T> {

    private Node<T> front;
    private Node<T> tail;
    private int size;
    
    /**
     * Create new, empty queue.
     */
    public Queue () {
        this.front = null;
        this.tail = null;
    }
    
    /**
     * add value to the end of this queue
     * @param value value to add
     */
    public void add (T value) {
        if (this.size == 0){
            this.front = new Node<T>();
            this.tail = front;
        }
        else {
            this.tail.next = new Node<T>();
            this.tail = this.tail.next;
        }
        this.tail.value = value;
        this.size++;
    }
    
    /**
     * remove value from the head of this queue and return it.
     * @throws Error if queue has no items
     * @return value from the head of this queue
     */
    public T poll () {
        if (this.size == 0) {
            throw new Error("Attempted to get item while Queue size was 0");
        }
        T result = this.front.value;
        this.front = this.front.next;
        this.size--;
        return result;
    }
    
    /**
     * Check if this queue is empty.
     * @return whether this queue is empty, e.g. its size is zero.
     */
    public boolean isEmpty () {
        return this.size == 0;
    }
    
    /**
     * A generic queue node. It contains a value and pointer to next node. 
     * @param <T> data type
     */
    class Node<T> {
        
        /**
         * value of this node
         */
        public T value;
        
        /**
         * pointer to next node
         */
        public Node<T> next;
    }
}

package lab6.queue.generic;

import java.util.Iterator;

/**
 * Queue, but FRICKING NORMAL QUEUE ABLE TO STORE NULLS!!!!!!11!!1!1!!!
 * @author Dmitriy Naumov
 */
public class Queue<T> {

    private Node<T> front;
    private Node<T> tail;
    private int size;
    
    public Queue () {
        this.front = null;
        this.tail = null;
    }
    
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

    public T poll () {
        if (this.size == 0) {
            throw new Error("Attempted to get item while Queue size was 0");
        }
        T result = this.front.value;
        this.front = this.front.next;
        this.size--;
        return result;
    }
    
    public boolean isEmpty () {
        return this.size == 0;
    }
    
    class Node<T> {
        public T value;
        public Node<T> next;
    }
}

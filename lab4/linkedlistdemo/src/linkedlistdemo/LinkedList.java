package linkedlistdemo;

import java.util.Iterator;

/**
 * @author Dmitriy Naumov
 * @param <T> data type
 */
public class LinkedList<T> implements Iterable<T> {
    
    class LinkedListNode<T> {
        public T value;
        public LinkedListNode next;
    }
    
    class LinkedListIterator<T> implements Iterator<T> {
        LinkedListNode<T> currentNode;
        
        public LinkedListIterator(LinkedList list){
            currentNode = list.root;
        }
        
        @Override public boolean hasNext(){ 
            return this.currentNode != null;
        }
        
        @Override public T next(){
            T currentValue = this.currentNode.value;
            this.currentNode = currentNode.next;
            return currentValue;
        }
    }
    
    private LinkedListNode<T> root;
    private LinkedListNode<T> end;
    private int count;
    
    // Constructors
    public LinkedList(){
        this.root = null;
        this.count = 0;
    }
    
    public LinkedList(T[] source){
        this();
        for(T val : source){
            this.append(val);
        }
    }
    
    public LinkedList(Iterable<T> source){
        this();
        this.append(source);
    }
    
    // Get item
    public T get(int index) throws Exception {
        if(index < 0 || index >= this.count){
            throw new Exception("index is out of range.");
        }
        LinkedListNode<T> current = this.root;
        for(int i=0; i<index; i++){
            current = current.next;
        }
        return current.value;
    }
    
    // Set item
    public void set(int index, T val) throws Exception {
        if(index < 0 || index >= this.count){
            throw new Exception("index is out of range.");
        }
        LinkedListNode<T> current = this.getNode(index);
        current.value = val;
    }
    
    // Append
    public void append(T val){
        if(this.count == 0){
            this.root = new LinkedListNode<T>();
            this.end = this.root;
        }
        else {
            this.end.next = new LinkedListNode<T>();
            this.end = this.end.next;
        }
        this.end.value = val;
        this.count++;
    }
    
    public void append(T[] vals){
        for(T val : vals){
            this.append(val);
        }
    }
    
    public void append(Iterable<T> vals){
        for(T val : vals){
            this.append(val);
        }
    }
    
    // Insert
    public void insert(int index, T val) throws Exception {
        if(index < 0 || index > this.count){
            throw new Exception("index is out of range.");
        }
        if(index == this.count){
            this.append(val);
        }
        else {
            LinkedListNode<T> insertionNode = this.getNode(index);
            LinkedListNode<T> insertionNodeNext = insertionNode.next;
            insertionNode.next = new LinkedListNode<T>();
            insertionNode.next.value = insertionNode.value;
            insertionNode.next.next = insertionNodeNext;
            insertionNode.value = val;
            this.count++;
        }
    }
    
    public void insert(int index, T[] vals) throws Exception {
        if(index < 0 || index > this.count){
            throw new Exception("index is out of range.");
        }
        if(index == this.count){
            this.append(vals);
        }
        else if(vals.length > 0){
            LinkedListNode<T> insertionNode = this.getNode(index);
            LinkedListNode<T> prevNode = null;
            LinkedListNode<T> insertionNodeReplacement = new LinkedListNode<>();
            insertionNodeReplacement.next = insertionNode.next;
            insertionNodeReplacement.value = insertionNode.value;
            for(T val : vals){
                insertionNode.value = val;
                insertionNode.next = new LinkedListNode<T>();
                prevNode = insertionNode;
                insertionNode = insertionNode.next;
            }
            if(prevNode!=null) 
                prevNode.next = insertionNodeReplacement;
            this.count += vals.length;
        }
    }
    
    public void insert(int index, Iterable<T> vals) throws Exception {
        if(index < 0 || index > this.count){
            throw new Exception("index is out of range.");
        }
        if(index == this.count){
            this.append(vals);
        } else {
            LinkedListNode<T> insertionNode = this.getNode(index);
            LinkedListNode<T> prevNode = null;
            LinkedListNode<T> insertionNodeReplacement = new LinkedListNode<>();
            insertionNodeReplacement.next = insertionNode.next;
            insertionNodeReplacement.value = insertionNode.value;
            for(T val : vals){
                insertionNode.next = new LinkedListNode<T>();
                insertionNode.value = val;
                prevNode = insertionNode;
                insertionNode = insertionNode.next;
                this.count++;
            }
            if(prevNode!=null) 
                prevNode.next = insertionNodeReplacement;
        }
    }
    
    public int count(){
        return this.count;
    }
    
    private LinkedListNode getNode(int index){
        LinkedListNode<T> current = this.root;
        for(int i=0; i<index; i++){
            current = current.next;
        }
        return current;
    }
    
    @Override public String toString(){
        LinkedList<String> stringRepresentations = new LinkedList<>();
        for(LinkedListNode<T> current = this.root; current != null; current = current.next){
            stringRepresentations.append(current.value.toString());
        }
        return String.format("[%s]", String.join(", ", stringRepresentations));
    }
    
    @Override public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this);
    }
}

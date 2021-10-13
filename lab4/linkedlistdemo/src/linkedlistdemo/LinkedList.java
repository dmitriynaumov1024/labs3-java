package linkedlistdemo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * @author Dmitriy Naumov
 * @param <T> data type
 */
public class LinkedList<T> implements Iterable<T> {
    
    class LinkedListNode<T> {
        public T value;
        public LinkedListNode<T> next;
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
        return this.getNode(index).value;
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
            if(prevNode!=null){
                prevNode.next = insertionNodeReplacement;
            }
            this.count += vals.length;
        }
    }
    
    public void insert(int index, Iterable<T> vals) throws Exception {
        if(index < 0 || index > this.count){
            throw new Exception("index is out of range.");
        }
        if(index == this.count){
            this.append(vals);
        } 
        else {
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
            if(prevNode!=null){
                prevNode.next = insertionNodeReplacement;
            }
        }
    }
    
    // Remove at index
    public void removeAt(int index) throws Exception {
        if (index < 0 || index>=this.count) {
            throw new Exception("index is out of range.");
        }
        LinkedListNode<T> node = this.getNode(index);
        if (node == this.root){
            this.root = this.root.next;
        }
        else if (node == this.end){
            this.end = this.getNode(this.count - 2);
            this.end.next = null;
        }
        else {
            if(node.next == this.end){
                this.end = node;
            }
            node.value = node.next.value;
            node.next = node.next.next;
        }
        this.count--;
    }
    
    // Remove after index
    public void removeAfter(int index) throws Exception {
        if (index < 0 || index>=this.count) {
            throw new Exception("index is out of range.");
        }
        this.end = this.getNode(index);
        this.end.next = null;
        this.count = index + 1;
    }
    
    // Remove before index
    public void removeBefore(int index) throws Exception {
        if (index < 0 || index>=this.count) {
            throw new Exception("index is out of range.");
        }
        this.root = this.getNode(index);
        this.count -= index;
    }
    
    // Remove first occurence of given value.
    // Returns true if an element was found and removed.
    public boolean removeFirstByValue(T val) {
        LinkedListNode<T> node = findNextByValue(val, this.root);
        if(node == null){
            return false;
        }
        else {
            if (node == this.root){
                this.root = this.root.next;
            }
            else if (node == this.end){
                this.end = this.getNode(this.count - 2);
                this.end.next = null;
            }
            else {
                if(node.next == this.end){
                    this.end = node;
                }
                node.value = node.next.value;
                node.next = node.next.next;
            }
            this.count--;
            return true;
        }
    }
    
    // Remove all occurences of given value
    // Returns number of removed elements
    public int removeAllByValue(T val){
        int removalCount = 0;
        for(LinkedListNode<T> node = findNextByValue(val, this.root); 
            node != null; 
            node = findNextByValue(val, node)) 
        {
            this.count--;
            removalCount++;
            if (node == this.root){
                this.root = this.root.next;
            }
            else if (node == this.end){
                this.end = this.getNode(this.count - 1);
                this.end.next = null;
                break;
            }
            else {
                if(node.next == this.end){
                    this.end = node;
                }
                node.value = node.next.value;
                node.next = node.next.next;
            }
        } 
        return removalCount;
    }
    
    // Remove all occurences of given values
    // Returns number of removed elements
    public int removeAllByValue(Iterable<T> values){
        int removalCount = 0;
        HashSet<T> usedValues = new HashSet<T>();
        for(T value : values){
            if(!usedValues.contains(value)){
                removalCount += this.removeAllByValue(value);
                usedValues.add(value);
            }
        }
        return removalCount;
    }
    
    // Remove all occurences of given values
    // Returns number of removed elements
    public int removeAllByValue(T[] values){
        int removalCount = 0;
        HashSet<T> usedValues = new HashSet<T>();
        for(T value : values){
            if(!usedValues.contains(value)){
                removalCount += this.removeAllByValue(value);
                usedValues.add(value);
            }
        }
        return removalCount;
    }
    
    // Number of items in this LinkedList
    public int count(){
        return this.count;
    }
    
    // Return a new list with items from this list which meet given condition
    public LinkedList<T> filter(Predicate<T> valueCondition){
        LinkedList<T> result = new LinkedList<>();
        for(LinkedListNode<T> current = this.root; 
            current != null; 
            current = current.next)
        {
            if (valueCondition.test(current.value)) result.append(current.value);
        }
        return result;
    }
    
    // Return a new list with items from this list filtered by value and index
    public LinkedList<T> filter(BiPredicate<Integer, T> indexValueCondition){
        LinkedList<T> result = new LinkedList<>();
        int index = 0;
        LinkedListNode<T> node = this.root;
        while(node != null && index < this.count){
            if (indexValueCondition.test(index, node.value)){
                result.append(node.value);
            }
            node = node.next;
            ++index;
        }
        return result;
    }
    
    // Return new LinkedList with items in given position range, end index is exclusive
    public LinkedList<T> slice(int startIndex, int endIndex) throws Exception {
        if(endIndex < startIndex){
            throw new Exception("End index must not be lower than start index.");
        }
        if(startIndex < 0){
            startIndex = 0;
        }
        if(endIndex > this.count){
            endIndex = this.count;
        }
        
        LinkedList<T> result = new LinkedList<>();
        
        for(LinkedListNode<T> node = this.getNode(startIndex); 
            node!=null && startIndex < endIndex; 
            node = node.next, startIndex++)
        {
            result.append(node.value);
        }
        return result;
    }
    
    private LinkedListNode<T> getNode(int index){
        LinkedListNode<T> current = this.root;
        for(int i=0; i<index; i++){
            current = current.next;
        }
        return current;
    }
    
    private LinkedListNode<T> findNextByValue(T val, LinkedListNode<T> startNode){
        if(startNode == null){ 
            return null;
        }
        for(LinkedListNode<T> current = startNode; 
            current != null; 
            current = current.next)
        {
            if (current.value.equals(val)){ 
                return current;
            }
        }
        return null;
    }
    
    @Override public String toString(){
        LinkedList<String> stringRepresentations = new LinkedList<>();
        for(LinkedListNode<T> current = this.root; 
            current != null; 
            current = current.next)
        {
            stringRepresentations.append(current.value.toString());
        }
        return String.format("[%s]", String.join(", ", stringRepresentations));
    }
    
    @Override public Iterator<T> iterator() {
        return new LinkedListIterator<>(this);
    }
}

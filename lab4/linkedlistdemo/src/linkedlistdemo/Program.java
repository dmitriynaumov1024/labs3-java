package linkedlistdemo;

/**
 * Linked List Demo.
 * @author Dmitriy Naumov
 */
public class Program {

    public static void main(String[] args) {
        LinkedList<Integer> 
                list = new LinkedList<>(new Integer[]{1, 2, 3, 4}),
                childList = new LinkedList<>(new Integer[]{4, 3, 4});
        
        list.append(4);
        list.append(5);
        list.append(8);
        
        try { 
            list.insert(1, 1); 
            list.insert(0, 9);
            list.insert(1, new Integer[]{-99, -98, -97});
            list.append(childList);
            list.insert(list.count(), 2147);
            list.append(new Integer[]{});
            list.insert(4, new Integer[]{});
        }
        catch (Exception ex){ 
            return; 
        }
        
        System.out.println(list.toString());
        
        list.removeFirstByValue(2147);
        System.out.println(list.toString());
        
        list.append(244);
        list.append(245);
        System.out.println(list.toString());
        
        list.removeAllByValue(244);
        System.out.println(list.toString());
        
        list.append(246);
        System.out.println(list.toString());
        
        list.removeAllByValue(new Integer[]{4, 246});
        System.out.println(list.toString());
        
        list.append(247);
        System.out.println(list.toString());
        System.out.printf("List contains %d items now. \n", list.count());
    }
}

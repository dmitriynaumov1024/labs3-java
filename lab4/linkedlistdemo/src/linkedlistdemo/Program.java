package linkedlistdemo;

import java.util.Arrays;
import java.util.Comparator;

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
            System.out.printf("%s - list \n", list);
            list.insert(1, new Integer[]{-99, -98, -97});
            System.out.printf("%s (inserted {-99, -98, -97} into index 1) \n", list);
            list.append(childList);
            System.out.printf("%s (appended %s) \n", list, childList);
            list.insert(list.count(), 2147);
            System.out.printf("%s (appended 2147) \n", list);
            list.append(new Integer[]{});
            list.insert(4, new Integer[]{});
        }
        catch (Exception ex){ 
            return; 
        }
        
        list.removeFirstByValue(2147);
        System.out.printf("%s (removed first by value 2147) \n", list);
        
        list.append(244);
        list.append(245);
        System.out.printf("%s (appended 244, 245) \n", list);
        
        list.removeAllByValue(244);
        System.out.printf("%s (removed all by value 244) \n", list);
        
        list.append(246);
        System.out.printf("%s (appended 246) \n", list.toString());
        
        int removalCount = list.removeAllByValue(new Integer[]{4, 246, 4444});
        System.out.printf("%s (Removed %d elements). \n", list.toString(), removalCount);
        
        list.append(247);
        System.out.printf("%s (List contains %d items now). \n", list.toString(), list.count());
        
        try {
            list.removeAt(list.count()-1);
            System.out.printf("%s (Removed last item). \n", list.toString());
            list.removeAt(2999);
        }
        catch (Exception ex){
            System.out.printf("Exception: %s \n", ex.getMessage());
        }
        finally {
            System.out.printf("%s (tried to remove at index 2999). \n", list.toString());
        }
        
        list.append(32);
        System.out.printf("%s (appended 32). \n", list.toString());
        
        LinkedList<Integer> newlist = list.filter(x -> (x > 0));
        System.out.printf("%s (Selected items with condition x -> (x > 0)). \n", newlist.toString());
        
        newlist = list.filter(x -> (x < 0));
        System.out.printf("%s (Selected items with condition x -> (x < 0)). \n", newlist.toString());
        
        try {
            newlist = newlist.slice(0, 2);
            System.out.printf("%s (Selected items from newlist with index in range [0..2)). \n", newlist.toString());
            
            newlist = list.slice(8, 24);
            System.out.printf("%s (tried to select items from list with index in range [8..24)). \n", newlist.toString());
        } 
        catch (Exception ex){
            return;
        }
        
        newlist = list.filter((index, value) -> (index % 2 == 0 && value > 1));
        System.out.printf("%s (Selected items from list with even index and value > 1). \n", newlist.toString());
        
        Object[] array = newlist.toArray();
        System.out.printf("%s (last list converted to array). \n", Arrays.toString(array));
        
        newlist.clear();
        System.out.printf("%s (list was cleared) \n", newlist.toString());
        
        newlist.append(new Integer[]{1, 2, 3, 4, 5});
        
        System.out.printf("%s (added 1, 2, 3, 4, 5) \n", newlist.toString());
        System.out.printf("Integrity check: %b \n", newlist.checkIntegrity());
        
        LinkedList<Integer> newlistDescSorted = newlist.sorted(Comparator.<Integer>reverseOrder());
        System.out.printf("%s (sorted descending order) \n", newlistDescSorted.toString());
        
        System.out.printf("%s (list again to ensure everything is all right) \n", newlist.toString());
        
        Integer[] lastExample = { 4, 5, 6, 7, 8, 0, 99, 73, -10, 20, 33, 43, 34, 33, 22, 23 };
        LinkedList<Integer> lastExampleList = new LinkedList<>(lastExample);
        System.out.printf("%s (last example) \n", lastExampleList);
        
        System.out.printf("%s (last example sorted ascending and filtered divisible by 3) \n", 
                          lastExampleList
                              .sorted(Comparator.<Integer>naturalOrder())
                              .filter((item)->(item % 3 == 0))
        );
        
        System.out.printf("%s | Max: %d | Min: %d \n", 
                          lastExampleList, 
                          lastExampleList.max(Comparator.<Integer>naturalOrder()), 
                          lastExampleList.min(Comparator.<Integer>naturalOrder())
        );
    }
}

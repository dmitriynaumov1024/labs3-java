package lab6;

import lab6.trees.generic.*;
import lab6.heap.generic.*;

/**
 * Main class
 * @author Dmitriy Naumov
 */
public class Program {

    /**
     * Entry point.
     * @param args the command line arguments
     * @throws Exception if something goes wrong
     */
    public static void main (String[] args) throws Exception {
        testTree();
        testHeap();
    }
    
    static void testTree () throws Exception {
        System.out.printf ("[ TREE DEMO ]\n");
        
        Tree<Integer> tree = new Tree<>();
        int d = 0, h = 0;
        tree.addNodeAt(d, h);
        tree.setValueAt(d, h, Integer.MIN_VALUE);
        System.out.printf("Value at [%d:%d] = %d \n", d, h, tree.getValueAt(d, h));

        d = 1; h = 0;
        System.out.printf("Can I add node at [%d:%d]? %s \n", d, h, tree.canAddNodeAt(d, h));
        tree.addNodeAt(d, h);
        tree.setValueAt(d, h, Integer.MIN_VALUE + d);
        System.out.printf("Value at [%d:%d] = %d \n", d, h, tree.getValueAt(d, h));

        d = 0; h = 0;
        System.out.printf("Value at [%d:%d] = %d \n", d, h, tree.getValueAt(d, h));


        d = 1; h = 1;
        System.out.printf("Can I add node at [%d:%d]? %s \n", d, h, tree.canAddNodeAt(d, h));
        tree.addNodeAt(d, h);
        tree.setValueAt(d, h, 444);
        System.out.printf("Value at [%d:%d] = %d \n", d, h, tree.getValueAt(d, h));

        System.out.printf("Is this Tree full/complete? %s \n", tree.checkCompletion());

        tree.removeNodeAt(d, h);
        System.out.printf("Removed node [%d:%d] \n", d, h);
        System.out.printf("Is this Tree full/complete? %s \n", tree.checkCompletion());

        d = 2; h = 0;
        tree.addNodeAt(d, h);
        tree.setValueAt(d, h, 456);
        System.out.printf("Added node [%d:%d] \n", d, h);
        System.out.printf("Value at [%d:%d] = %d \n", d, h, tree.getValueAt(d, h));
        System.out.printf("Is this Tree full/complete? %s \n", tree.checkCompletion());

        d = 1; h = 1;
        tree.addNodeAt(d, h);
        tree.setValueAt(d, h, -200);
        System.out.printf("Added node [%d:%d] \n", d, h);
        System.out.printf("Value at [%d:%d] = %d \n", d, h, tree.getValueAt(d, h));
        System.out.printf("Is this Tree full/complete? %s \n", tree.checkCompletion());

        d = 2; h = 3;
        tree.addNodeAt(d, h);
        tree.setValueAt(d, h, 324);
        System.out.printf("Added node [%d:%d] \n", d, h);
        System.out.printf("Value at [%d:%d] = %d \n", d, h, tree.getValueAt(d, h));
        System.out.printf("Is this Tree full/complete? %s \n", tree.checkCompletion());

        d = 3; h = 0;
        tree.addNodeAt(d, h);
        tree.setValueAt(d, h, 326);
        System.out.printf("Added node [%d:%d] \n", d, h);
        System.out.printf("Value at [%d:%d] = %d \n", d, h, tree.getValueAt(d, h));
        System.out.printf("Is this Tree full/complete? %s \n", tree.checkCompletion());

        System.out.printf ("\nTree.toString: \n%s \n", tree);

        System.out.printf ("\nTreeLevelIterable.toString: \n%s \n", tree.levels());

        d = 1; h = 0;
        tree.setValueAt(d, h, null);
        System.out.printf("Value at [%d:%d] = %d \n", d, h, tree.getValueAt(d, h));
        System.out.printf ("\nTreeLevelIterable.toString: \n%s \n", tree.levels());

        System.out.printf ("Restoring tree, keeping nulls... \n");
        tree.restore();
        System.out.printf ("\nTreeLevelIterable.toString: \n%s \n", tree.levels());


        Integer[] arr = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        Tree<Integer> tree1 = new Tree<Integer>(new ArrayIterable(arr));
        System.out.printf ("\ntree1: \n%s \n", tree1.levels());

        Tree<Integer> tree2 = new Tree<Integer>(null, 20);
        System.out.printf ("\ntree2, constructed with size 20: \n%s \n", tree2.levels());

        Tree<Integer> tree3 = tree.merge(tree2);
        System.out.printf ("\ntree3, result of merging tree1 with tree2: \n%s \n", tree3.levels());
    }
    
    static void testHeap () throws Exception {
        System.out.printf ("[ HEAP DEMO ]\n");
        
        Heap<Integer> heap1 = new Heap<Integer>();
        heap1.add(1);
        heap1.add(2);
        heap1.add(3);
        heap1.add(4);
        System.out.printf ("\nheap1: \n%s \n", heap1.levels());
        
        heap1.removeAt(2);
        System.out.printf("Removed value at index 2 \n");
        System.out.printf ("\nheap1: \n%s \n", heap1.levels());
        
        heap1.setAt(1, 99);
        System.out.printf("Value at index 1 = %d\n", heap1.getValueAt(1));
        System.out.printf ("\nheap1: \n%s \n", heap1.levels());
        
        heap1.restore();
        System.out.printf ("heap1 was restored\n");
        System.out.printf ("\nheap1: \n%s \n", heap1.levels());
        
        heap1.add(new Integer[]{ 6, 5, 4, 3, 2, 1, 3, 4, 9 , 80, 97, 21, 12 });
        System.out.printf ("Added many values to heap1\n");
        System.out.printf ("\nheap1: \n%s \n", heap1.levels());
        
        heap1.restore();
        System.out.printf ("heap1 was restored\n");
        System.out.printf ("\nheap1: \n%s \n", heap1.levels());
        
        Integer[] arr = new Integer[] { 5, 6, 7, 8, 1, 2, 3, 4, 5, 14, 2, 0, -1, 99, 100, 0, -100 };
        Heap<Integer> heap2 = new Heap<Integer>(arr);
        
        System.out.printf ("heap2 was made from array ");
        for (Integer item : arr) 
            System.out.printf ("%d ", item);
        System.out.printf ("\nheap2: \n%s \n", heap2.levels());
    }
    
}

package lab6;

import lab6.trees.generic.*;

/**
 * Main class
 * @author Dmitriy Naumov
 */
public class Program {

    /**
     * Entry point.
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Tree<Integer> tree = new Tree<>();
        try {
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
            
            Tree<Integer> tree2 = new Tree<Integer>(10, 20);
            System.out.printf ("\ntree2 of size 20: \n%s \n", tree2.levels());
            
            Tree<Integer> tree3 = tree.merge(tree2);
            System.out.printf ("\ntree3, result of merging tree1 with tree2: \n%s \n", tree3.levels());
        }
        catch (Exception ex) {
            System.out.printf ("Exception: %s \n", ex.toString());
            throw ex;
        }
        
    }
    
}

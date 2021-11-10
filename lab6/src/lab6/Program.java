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
    public static void main(String[] args) {
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
            
            System.out.printf ("%s \n", tree);
            
            
        }
        catch (Exception ex) {
            System.out.printf ("Exception: %s \n", ex.getMessage());
        }
        
    }
    
}

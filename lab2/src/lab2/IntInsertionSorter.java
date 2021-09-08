package lab2;

/**
 * Insertion sorter for arrays of int type.
 * @author Dmitriy Naumov
 */
public class IntInsertionSorter implements IIntSorter {
    public int[] sort(int[] source) { return Sort.insertionSorted(source); }
}

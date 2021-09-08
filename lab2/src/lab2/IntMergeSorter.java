package lab2;

/**
 * Merge sorter for arrays of int type.
 * @author Dmitriy Naumov
 */
public class IntMergeSorter implements IIntSorter {
    public int[] sort(int[] source) { return Sort.mergeSorted(source); }
}

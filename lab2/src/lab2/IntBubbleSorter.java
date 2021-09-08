package lab2;

/**
 * Bubble sorter for arrays of int type.
 * @author Dmitriy Naumov
 */
public class IntBubbleSorter implements IIntSorter {
    public int[] sort(int[] source) { return Sort.bubbleSorted(source); }
}

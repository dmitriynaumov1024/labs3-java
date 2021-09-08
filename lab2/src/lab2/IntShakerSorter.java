package lab2;

/**
 * Shaker sorter for arrays of int type.
 * @author Dmitriy Naumov
 */
public class IntShakerSorter implements IIntSorter {
    public int[] sort(int[] source) { return Sort.shakerSorted(source); }
}

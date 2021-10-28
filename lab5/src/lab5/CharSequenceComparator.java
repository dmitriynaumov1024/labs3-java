package lab5;

import java.util.Comparator;

/**
 * Char sequence comparator. It can be used to compare any objects which 
 * implement CharSequence. This comparator will be efficient only if 
 * given CharSequence allows to get chars in constant time. Otherwise 
 * it will be VERY INEFFICIENT.
 * @author Dmitriy Naumov
 */
public class CharSequenceComparator implements Comparator<CharSequence> {

    private static CharSequenceComparator 
        instance = new CharSequenceComparator();
    
    private CharSequenceComparator(){ }
    
    /**
     * Returns single instance of CharSequenceComparator.
     * @return instance of CharSequenceComparator
     */
    public static CharSequenceComparator getInstance(){ 
        return instance;
    }
    
    @Override public int compare(CharSequence left, CharSequence right) {
        
        // Prepare
        int leftLength = left.length(),
            rightLength = right.length(),
            lengthComparisonResult = leftLength - rightLength;
        
        // Compare contents first
        for(int i=0; i<leftLength && i<rightLength; i++){
            int charComparisonResult = left.charAt(i) - right.charAt(i);
            if (charComparisonResult!=0){
                return charComparisonResult;
            }
        }
        
        // Compare lengths
        if (lengthComparisonResult!=0){
            return lengthComparisonResult;
        }
        
        // Return 0 if everything is equal
        return 0;
    }
}

package lab5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

/**
 * Main class
 * @author Dmitriy Naumov
 */
public class Program {

    /**
     * Application entry point.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        
        if (args.length == 0){
            doMethodDemo();
            doConcatEfficiencyDemo();
            doSortDemo();
            return;
        }
        
        switch(args[0].charAt(0)){
        case '1':
            doMethodDemo();
            return;
        case '2':
            doConcatEfficiencyDemo();
            return;
        case '3':
            doSortDemo();
            return;
        default:
            System.out.printf("Usage: <task number [1..3]>");
            return;
        }
    }
    
    /**
     * StringBuffer/Builder method demo.
     */
    static void doMethodDemo() {
        
        System.out.printf("StringBuffer/Builder method demo \n");
        
        StringBuffer sbuf1 = new StringBuffer("Hello world!");
        System.out.printf("\n[1] sbuf1 = new StringBuffer(\"Hello world!\") \n", sbuf1);
        System.out.printf("sbuf1: %s \n", sbuf1);
        
        StringBuffer sbuf2 = new StringBuffer(sbuf1);
        sbuf2.replace(0, 4, "abcd");
        System.out.printf("\n[2] Contents of sbuf1 are copied to sbuf2. \n");
        System.out.printf("Characters of range [0..4) of sbuf2 are replaced with 'abcd'. \n");
        System.out.printf("sbuf2: %s \n", sbuf2);
        
        sbuf2.replace(0, 2, "QWERTY");
        System.out.printf("\n[3] Characters of range [0..2) of sbuf2 are replaced with 'QWERTY'. \n");
        System.out.printf("sbuf2: %s \n", sbuf2);
        
        sbuf2.setCharAt(0, 'ы');
        System.out.printf("\n[4] Character at index 0 of sbuf2 is set to 'ы'. \n");
        System.out.printf("sbuf2: %s \n", sbuf2);
        
        sbuf2.setLength(5);
        System.out.printf("\n[5] Length of sbuf2 is set to 5. \n");
        System.out.printf("sbuf2: %s \n", sbuf2);
        
        sbuf2.append(sbuf2);
        System.out.printf("\n[6] sbuf2 is appended to sbuf2. \n");
        System.out.printf("sbuf2: %s \n", sbuf2);
        
        sbuf2.reverse();
        System.out.printf("\n[7] sbuf2 was reversed. \n");
        System.out.printf("sbuf2: %s \n", sbuf2);
        
        StringBuilder sbild1 = new StringBuilder("String builder");
        System.out.printf("\n[8] sbild1 = new StringBuilder(\"String builder\") \n");
        System.out.printf("sbild1: %s \n", sbild1);
        
        sbild1.insert(0, " This is ");
        System.out.printf("\n[9] Inserted ' This is' into position 0 of sbild1. \n");
        System.out.printf("sbild1: %s \n", sbild1);
        
        sbild1.insert(0, new Date());
        System.out.printf("\n[10] Literally inserted Date instance into position 0 of sbild1. \n");
        System.out.printf("sbild1: %s \n", sbild1);
        
        StringBuilder sbild2 = new StringBuilder();
        for(int i=0; i<256; i++){
            sbild2.append((char)i);
        }
        System.out.printf("\n[11] sbild2 = new StringBuilder(256); \n");
        System.out.printf("Written all 1-byte chars into sbild2. \n");
        System.out.printf("sbild2: %s \n", sbild2);
        
        try {
            System.out.printf("\n[12] Trying to access uninitialized data in StringBuffer. \n");
            StringBuffer sbuf9 = new StringBuffer(9);
            sbuf9.setCharAt(6, 'X');
            
            System.out.printf("sbuf9: %s \n", sbuf9);
        }
        catch (Exception ex){
            System.out.printf("Exception: %s \n", ex);
        }
        
        try {
            System.out.printf("\n[13] Trying to access uninitialized data in StringBuilder. \n");
            StringBuilder sbld9 = new StringBuilder(9);
            sbld9.setCharAt(6, 'X');
            
            System.out.printf("sbld9: %s \n", sbld9);
        }
        catch (Exception ex){
            System.out.printf("Exception: %s \n", ex);
            System.out.printf("Capacity does not correspond to actual size. Keep this in mind.\n");
        }
    }

    /**
     * Concatenation efficiency demo.
     */
    static void doConcatEfficiencyDemo() {
        
        System.out.printf("\nStringBuffer/Builder concat demo \n");
        Random random = new Random();
        
        System.out.printf("\n[1] Appending contents of one StringBuilder/Buffer to another. \n"); 
        System.out.printf(" Size  |  Builder  |   Buffer \n");
        for (int iteration=0, size=10000; iteration<12; iteration++, size*= 2){
            StringBuilder 
                sb1 = new StringBuilder(size * 2),
                sb2 = new StringBuilder(size+1000);
            
            StringBuffer
                bf1 = new StringBuffer(size * 2),
                bf2 = new StringBuffer(size+1000);
            
            for (int i=0; i<size; i++){
                char c = (char)(random.nextInt(23)+'a');
                sb1.append(c);
                sb2.append(c);
                bf1.append(c);
                bf2.append(c);
            }
            
            long startTime = System.nanoTime();
            sb1.append(sb2);
            long builderTime = (System.nanoTime() - startTime)/ 1_000;
            
            startTime = System.nanoTime();
            bf1.append(bf2);
            long bufferTime = (System.nanoTime() - startTime)/ 1_000;
            
            System.out.printf("%5dK | %6d µs | %6d µs \n", size/1000, builderTime, bufferTime);
        }
        
        System.out.printf("\n[2] Appending chars one-by one to \nStringBuilder/Buffer with no initial capacity. \n"); 
        System.out.printf(" Size  | Builder |  Buffer \n");
        
        for (int iteration=0, size=10000; iteration<10; iteration++, size*= 2){
            StringBuilder sb1 = new StringBuilder();
            long startTime = System.nanoTime();
            
            for (int i=0; i<size; i++){
                sb1.append((char)(random.nextInt(23)+'a'));
            }
            long builderTime = (System.nanoTime() - startTime)/ 1_000_000;
            
            StringBuffer sb2 = new StringBuffer();
            startTime = System.nanoTime();
            
            for (int i=0; i<size; i++){
                sb2.append((char)(random.nextInt(23)+'a'));
            }
            long bufferTime = (System.nanoTime() - startTime)/ 1_000_000;
            
            System.out.printf("%5dK | %4d ms | %4d ms \n", size/1000, builderTime, bufferTime);
        }
        
        System.out.printf("\n[2] Appending chars one-by-one to \nStringBuilder / StringBuilder with capacity / usual String \n");
        System.out.printf(" Size  | Builder  | B.+capacity |     String \n");
        
        for (int iteration=1, size=10000; iteration<8; iteration++, size=10000 * iteration){
            StringBuilder sb1 = new StringBuilder();
            long startTime = System.nanoTime();
            
            for (int i=0; i<size; i++){
                sb1.append((char)(random.nextInt(23)+'a'));
            }
            long builder1Time = (System.nanoTime() - startTime)/ 1_000;
            
            StringBuilder sb2 = new StringBuilder(size + 1000);
            startTime = System.nanoTime();
            
            for (int i=0; i<size; i++){
                sb2.append((char)(random.nextInt(23)+'a'));
            }
            long builder2Time = (System.nanoTime() - startTime)/ 1_000;
            
            String s = new String();
            startTime = System.nanoTime();
            
            for (int i=0; i<size; i++){
                s += (char)(random.nextInt(23)+'a');
            }
            long stringTime = (System.nanoTime() - startTime)/ 1_000;
            
            System.out.printf("%5dK | %5d µs | %8d µs | %8d µs \n", size/1000, builder1Time, builder2Time, stringTime);
        }
    }
    
    static void doSortDemo() {
        
        System.out.printf("\nStringBuffer/Builder sort demo \n");
        
        StringBuilder[] builders = new StringBuilder[8];
        builders[0] = new StringBuilder("miracle");
        builders[1] = new StringBuilder("misbehave");
        builders[2] = new StringBuilder("mirage");
        builders[3] = new StringBuilder("mirth");
        builders[4] = new StringBuilder("mire");
        builders[5] = new StringBuilder("miserable");
        builders[6] = new StringBuilder("misdeed");
        builders[7] = new StringBuilder("mischief");
        
        System.out.printf("\n[1] String builders before sort: \n");
        for (StringBuilder sb : builders){
            System.out.printf("%s \n", sb);
        }
        
        Arrays.sort(builders, CharSequenceComparator.getInstance());
        
        System.out.printf("\n[1] After sort: \n");
        for (StringBuilder sb : builders){
            System.out.printf("%s \n", sb);
        }
        
        StringBuffer[] bufs = new StringBuffer[8];
        bufs[0] = new StringBuffer("Admission");
        bufs[1] = new StringBuffer("address");
        bufs[2] = new StringBuffer("Administration");
        bufs[3] = new StringBuffer("anticipation");
        bufs[4] = new StringBuffer("acquire");
        bufs[5] = new StringBuffer("avanguard");
        bufs[6] = new StringBuffer("aerial");
        bufs[7] = new StringBuffer("altitude");
        
        System.out.printf("\n[2] String buffers before sort: \n");
        for (StringBuffer sb : bufs){
            System.out.printf("%s \n", sb);
        }
        
        Arrays.sort(bufs, CharSequenceComparator.getInstance());
        
        System.out.printf("\n[2] After sort: \n");
        for (StringBuffer sb : bufs){
            System.out.printf("%s \n", sb);
        }
        
        String[] strings = new String[] {
            "holiday",
            "hollow",
            "greedy",
            "greeting",
            "good",
            "Grown",
            "grown",
            "custom",
            "customer",
            "culmination"
        };
        
        System.out.printf("\n[3] Strings before sort: \n");
        for (String s : strings){
            System.out.println(s);
        }
        
        Arrays.sort(strings, CharSequenceComparator.getInstance());
        
        System.out.printf("\n[3] Sorted with CharSequenceComparator: \n");
        for (String s : strings){
            System.out.println(s);
        }
        
        Arrays.sort(strings, Comparator.<String>naturalOrder());
        
        System.out.printf("\n[3] Sorted with default Comparator: \n");
        for (String s : strings){
            System.out.println(s);
        }
    }
}

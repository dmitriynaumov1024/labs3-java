package lab1;

/**
 * Given strings s1, s2, s3, s4, s5. 
 * Write a method to: if s4 == s5, concat s1 with s2, else concat s1 with s3.
 * @author Dmitriy Naumov
 */
public class Task14 implements ITask {
    String helpString = "Usage: <s1> <s2> <s3> <s4> <s5>";
    
    void conditionalConcatAndPrint(String s1, String s2, String s3, String s4, String s5){
        if(s4.equals(s5)){
            System.out.println(s1.concat(s2));
        } else {
            System.out.println(s1.concat(s3));
        }
    }
    
    public void run(String[] args){
        if(args.length != 5){
            System.out.println(helpString);
            return;
        }
        conditionalConcatAndPrint(args[0], args[1], args[2], args[3], args[4]);
    }
}

package lab1;

import java.util.*;

/**
 * @author Dmitriy Naumov
 */
public class Lab1 {
    static String helpString = "Usage: java -jar lab1.jar task<1...14> [args]";
    
    private static String arrayToString(int[] array){
        if(array.length == 0){
            return "{ }";
        }
        String result = "{ " + array[0];
        for(int i=1; i<array.length; i++){
            result += String.format(", %d", array[i]);
        }
        return result + " }";
    }
    
    private static Map<String, ITask> getTasks(){
        Map<String, ITask> tasks = new HashMap<>();
        tasks.put("task1", new Task1());
        return tasks;
    }
    
    public static void main(String[] args) {
        
        Map<String, ITask> tasks = getTasks();
        
        if(args.length < 1 
            || "help".equals(args[0]) 
            || "--help".equals(args[0]) 
            || "?".equals(args[0])){
            System.out.println(helpString);
            return;
        }
        
        if(tasks.containsKey(args[0])){
            String[] taskArgs = args.length > 1 ? 
                    Arrays.copyOfRange(args, 1, args.length) : 
                    new String[]{};
            tasks.get(args[0]).run(taskArgs);
            return;
        } else {
            System.out.printf("'%s' not found. \n", args[0]);
            System.out.println(helpString);
            return;
        }
    }
}

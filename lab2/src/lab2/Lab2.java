package lab2;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Locale;

/**
 * @author Dmitriy Naumov
 */
public class Lab2 {
    static String helpString = "Usage: java -jar lab2.jar task<1...5> [args]";
    
    static Map<String, ITask> getTasks(){
        Map<String, ITask> tasks = new HashMap<>();
        tasks.put("task1", new Task1());
        tasks.put("task2", new Task2());
        tasks.put("task3", new Task3());
        return tasks;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        
        Map<String, ITask> tasks = getTasks();
        
        if(args.length < 1 || args[0].toLowerCase().contains("help")){
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

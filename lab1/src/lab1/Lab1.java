package lab1;

import java.util.*;

/**
 * @author Dmitriy Naumov
 */
public class Lab1 {
    static String helpString = "Usage: java -jar lab1.jar task<1...14> [args]";
    
    private static Map<String, ITask> getTasks(){
        Map<String, ITask> tasks = new HashMap<>();
        tasks.put("task1", new Task1());
        tasks.put("task2", new Task2());
        tasks.put("task3", new Task3());
        tasks.put("task4", new Task4());
        tasks.put("task5", new Task5());
        tasks.put("task6", new Task6());
        tasks.put("task7", new Task7());
        tasks.put("task8", new Task8());
        tasks.put("task9", new Task9());
        tasks.put("task10", new Task10());
        tasks.put("task11", new Task11());
        tasks.put("task12", new Task12());
        tasks.put("task13", new Task13());
        tasks.put("task14", new Task14());
        return tasks;
    }
    
    public static void main(String[] args) {
        
        Locale.setDefault(Locale.ENGLISH);
        
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

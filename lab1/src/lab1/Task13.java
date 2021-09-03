package lab1;
import java.util.Random;

/**
 * Write a program to print a 3x5 table of random numbers of 0..&lt;10 range.
 * @author Dmitriy Naumov
 */
public class Task13 implements ITask {
    String helpString = "Usage: [rows: unsigned int] [columns: unsigned int]";
    
    int rowsLimit = 20,
        columnsLimit = 20;
    
    void printGrid(int rowCount, int columnCount){
        Random random = new Random();
        for(int row=0; row<rowCount; row++){
            for(int column=0; column<columnCount; column++){
                System.out.printf(" %d", random.nextInt(10));
            }
            System.out.println();
        }
    }
    
    public void run(String[] args){
        if(args.length == 0){
            printGrid(3, 5);
            return;
        } 
        int rows = rowsLimit, columns = columnsLimit;
        try {
            if(args.length > 0){
                rows = Integer.min(Integer.parseUnsignedInt(args[0]), rowsLimit);
            }
            if(args.length > 1){
                columns = Integer.min(Integer.parseUnsignedInt(args[1]), columnsLimit);
            }
            printGrid(rows, columns);
        } catch (NumberFormatException ex){
            System.out.println(helpString);
        } catch (Exception ex){
            System.out.printf("Exception: %s \n", ex.getMessage());
        }
    }
}

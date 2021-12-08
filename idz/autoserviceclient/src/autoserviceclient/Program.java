package autoserviceclient;

import java.util.Scanner;

/**
 * Main class
 * @author Dmitriy Naumov
 */
public class Program {

    public static void main(String[] args) {
        System.out.printf("Welcome to AutoService client application v%s!\n", Global.version);
        System.out.printf("Author: %s / %s\n", Global.authorName, Global.authorEmail);
        System.out.printf("Database path: %s\n\n", Global.getDatabase().getFilePath());
        Operations.help.execute();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String command = sc.nextLine().trim();
            ClientOperation op = Operations.getMapping().get(command);
            if (op != null) 
                op.execute();
            else 
                System.out.printf("Command '%s' cannot be recognized.\n", command);
            
            System.out.println();
        }
    }
}

package autoserviceclient;

import autoservice.db.AutoServiceDB;
import java.io.File;
import java.util.Date;

/**
 * A database holder for this application
 * @author DmitriyNaumov
 */
public class Global {
    
    public final static int currentYear = (new Date()).getYear() + 1900;
    
    public final static String version = "1.0";
    public final static String authorName = "Dmitriy Naumov"; 
    public final static String authorEmail = "naumov1024@gmail.com";
    
    public static AutoServiceDB getDatabase () { return _database; }
    
    private static AutoServiceDB _database 
            = new AutoServiceDB (new File("database.dat"));
    
}

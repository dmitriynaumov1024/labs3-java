package autoservice.util;

import java.text.SimpleDateFormat;

/**
 *
 * @author Dmitriy Naumov
 */
public class DateFormats {
    
    public static SimpleDateFormat yyyy_mm_dd () {
        return _yyyy_mm_dd;
    }
    
    public static SimpleDateFormat yyyy_mm_dd_time () {
        return _yyyy_mm_dd_time;
    }
            
    private static SimpleDateFormat _yyyy_mm_dd = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat _yyyy_mm_dd_time = new SimpleDateFormat("yyyy-MM-dd hh:mm");
}

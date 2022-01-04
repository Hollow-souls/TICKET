package javabean;

import java.util.Calendar;

/**
 * @author shkstart
 * @create 2022-01-2022/1/4-12:49
 */
public class oDate {
    public String get_date() {
        String date=null;
        Calendar cal=Calendar.getInstance();
        int y=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH)+1;
        int d=cal.get(Calendar.DATE);
        date=y+"-"+m+"-"+d;
        return date;
    }
}

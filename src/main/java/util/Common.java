package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static Date converstrtodate(String date) throws ParseException {
        DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
        Date parsedDate=(Date) df.parse(date);
        return  parsedDate;
    }
}

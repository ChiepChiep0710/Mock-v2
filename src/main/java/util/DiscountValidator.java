package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DiscountValidator {
    private static DiscountValidator instance;
    private Scanner scanner = new Scanner(System.in);

    public static DiscountValidator getInstance(){
        return instance == null ? new DiscountValidator() : instance;
    }

    public boolean dateValidator(String birthDate){
        if(birthDate.matches("[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9]{4}")){
            var format = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false);
            try {
                Date birthDay = dateFormat.parse(birthDate);
                return true;
            } catch (ParseException e) {
                return false;
            }
        }
        else{
            return false;
        }
    }
}

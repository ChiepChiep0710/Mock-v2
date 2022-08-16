package util.Validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ProductValidator {
    private static ProductValidator instance;
    private Scanner scanner = new Scanner(System.in);

    public static ProductValidator getInstance(){
        return instance == null ? new ProductValidator() : instance;
    }

    public Double validatePrice(){
        Double price = scanner.nextDouble();
        scanner.nextLine();
        if(price < 0.0){
            System.out.print("Price is not correct! Retype: ");
            return validatePrice();
        } else {
            return price;
        }
    }

    public int validateInteger(){
        int value = scanner.nextInt();
        scanner.nextLine();
        if(value < 0){
            System.out.println("Value is not correct! Retype: ");
            return validateInteger();
        } else {
            return value;
        }
    }

    public Date validateDate(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = df.parse(scanner.nextLine());
            return date;
        } catch (ParseException e) {
            System.out.print("You must type dd/MM/yyyy! Retype date: ");
            return validateDate();
        }
    }

    public int validateMonth(){
        int month = scanner.nextInt();
        scanner.nextLine();
        if(month > 0 && month < 13){
            return month;
        }
        System.out.print("Month is not correct! Retype: ");
        return validateMonth();
    }
}

package util;

import dao.CustomerDAO;
import dao.impl.CustomerDAOImpl;
import model.Customer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Validator {
    private static Validator instance;
    private static final Scanner scanner = new Scanner(System.in);

    public static Validator getInstance(){
        return instance == null ? new Validator() : instance;
    }

    public int validateInteger(){
        if (scanner.hasNextInt()){
            int value = scanner.nextInt();
            scanner.nextLine();

            return value;
        }

        scanner.nextLine();
        System.out.println("YOU MUST INPUT INTEGER TYPE");

        return validateInteger();
    }

    public String validateString(){
        return scanner.nextLine();
    }

    public double validateDouble(){
        if (scanner.hasNextDouble()){
            double value = scanner.nextDouble();
            scanner.nextLine();

            return value;
        }

        scanner.nextLine();
        System.out.println("YOU MUST INPUT DOUBLE TYPE");

        return validateDouble();
    }

    public String validatePostalCode(){
        String postalCode = scanner.nextLine();
        if(postalCode.length() == 5){
            return postalCode;
        }
        System.out.print("Retype postal code: ");
        return validatePostalCode();
    }



    public String validateEmail(){
        String email = scanner.nextLine();
        String regex = "^(.+)@(.+)$";

        CustomerDAO customerDAO = new CustomerDAOImpl();
        List<Customer> customers = customerDAO.findAll();
        List<Customer> customerFilter = customers.stream()
                .filter(customer -> customer.getEmail().equals(email))
                .collect(Collectors.toList());

        if(customerFilter.size() == 0 && email.matches(regex)){
            return email;
        } else {
            System.out.print("Retype email: ");
            return validateEmail();
        }
    }

    public String validatePhone(){
        String phone = scanner.nextLine();
        String regex = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

        if(phone.matches(regex)){
            return phone;
        }
        System.out.print("Retype phone number: ");
        return validatePhone();
    }

    public Date validateDate(){
        System.out.print("Input date: ");
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        try {
            Date date = df.parse(scanner.nextLine());
            return date;
        } catch (ParseException e) {
            System.out.print("You must type mm-dd-yyyy! Retype date: ");
            return validateDate();
        }
    }

    public boolean checkDateDiscount(Date date1, Date date2){
        if(date2.compareTo(date1) >= 0){
            return true;
        } else{
            return false;
        }
    }

}

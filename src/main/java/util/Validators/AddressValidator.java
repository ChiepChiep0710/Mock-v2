package util.Validators;

import util.Validator;

import java.util.Scanner;
import java.util.regex.Pattern;

public class AddressValidator {

    private static AddressValidator instance;
    private static final Scanner scanner = new Scanner(System.in);

    public static AddressValidator getInstance(){
        return instance == null ? new AddressValidator() : instance;
    }
    public String validatePostalCode(){
        String postalCode = scanner.nextLine();
        String regex = "^(\\d){2}0{4}$";
        boolean result  = Pattern.compile(regex).matcher(postalCode).find();
        if(result){
            return postalCode;
        }
        System.out.print("Retype postal code: ");
        return validatePostalCode();
    }
}

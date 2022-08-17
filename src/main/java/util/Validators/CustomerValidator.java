package util.Validators;

import dao.CustomerDAO;
import dao.impl.CustomerDAOImpl;
import model.Customer;

<<<<<<< HEAD
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
=======
>>>>>>> d893c3d46c5cfcaf3afa97c6a9ef6401a7b85a9b
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CustomerValidator {
    private static CustomerValidator instance;
    private Scanner scanner = new Scanner(System.in);

    public static CustomerValidator getInstance(){
        return instance == null ? new CustomerValidator() : instance;
    }

    public String validatePostalCode(){
        String postalCode = scanner.nextLine();
        if(postalCode.length() == 5){
            return postalCode;
        }
        System.out.print("Retype postal code: ");
        return validatePostalCode();
    }

//    public Double checkPostalCode(String postalCode){
//        AddressDAO addressDAO = new AddressDAOImpl();
//        List<Address> addresses = addressDAO.findAll();
//        List<Address> addressFilter = addresses.stream()
//                .filter(address -> address.getPostalCode().equals(postalCode))
//                .collect(Collectors.toList());
//        if(addressFilter.size() != 0){
//            return addressFilter.get(0).getDeliveryFee();
//        } else{
//            return 0.0;
//        }
//    }

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

//    public int checkAddress(Address address){
//        AddressDAO addressDAO = new AddressDAOImpl();
//        List<Address> addresses = addressDAO.findAll();
//        List<Address> addressFilter = addresses.stream()
//                .filter(add -> add.getPostalCode().equals(address.getPostalCode()))
//                .collect(Collectors.toList());
//        if(addressFilter.size() != 0){
//            return addressFilter.get(0).getId();
//        } else{
//            return 0;
//        }
//    }

    public boolean checkDateDiscount(Date date1, Date date2){
        if(date2.compareTo(date1) >= 0){
            return true;
        } else{
            return false;
        }
    }
}

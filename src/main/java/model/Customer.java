package model;

import util.Validators.CustomerValidator;

import java.util.Scanner;

public class Customer {
    private int id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private int addressId;
    private Scanner scanner = new Scanner(System.in);

    public Customer() {
    }

    public Customer(int id, String fullName, String email, String phoneNumber, int addressId) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addressId = addressId;
    }

    public Customer(String fullName, String email, String phoneNumber, int addressId) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addressId = addressId;
    }

    public Customer(String fullName, String email, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Customer input(){
        System.out.print("Enter full name: ");
        fullName = scanner.nextLine();
        System.out.print("Enter email: ");
        email = CustomerValidator.getInstance().validateEmail();
        System.out.print("Enter phone number: ");
        phoneNumber = CustomerValidator.getInstance().validatePhone();
//        Address address = new Address();
//        address.input();
//        addressId = Validator.getInstance().checkAddress(address);
//        if(addressId == 0){
//            AddressDAO addressDAO = new AddressDAOImpl();
//            addressId = addressDAO.save(address);
//        }
        return new Customer(fullName, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", addressId=" + addressId +
                '}';
    }

}

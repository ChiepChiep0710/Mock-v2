package controller;

import model.Customer;
import service.CustomerService;
import service.impl.CustomerServiceImpl;

import java.util.Scanner;

public class CustomerController {
    private static Scanner scanner = new Scanner(System.in);
    private static final CustomerService customerService = new CustomerServiceImpl();
    public static void customerController(){
        int menu;
        boolean exit = true;
        while(exit){
            menu = showMenu();
            switch (menu){
                case 1:{
                    customerService.save();
                    break;
                }
                case 2:{
                    System.out.print("Enter customer id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    boolean result = customerService.update(id);
                    System.out.println("Update customer " + (result ? "success" : "fail"));
                    break;
                }
                case 3:{
                    System.out.print("Enter customer id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    boolean result = customerService.delete(id);
                    System.out.println("Delete customer " + (result ? "success" : "fail"));
                    break;
                }
                case 4:{
                    System.out.print("Enter customer id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Customer customer = customerService.searchById(id);
                    System.out.printf("%-10s%-30s%-20s%-20s\n", "ID", "FULL_NAME", "EMAIL", "PHONE_NUMBER");
                    customer.display();
                    break;
                }
                case 5:{
                    System.out.printf("%-10s%-30s%-20s%-20s\n", "ID", "FULL_NAME", "EMAIL", "PHONE_NUMBER");
                    customerService.findAll().forEach(Customer::display);
                    break;
                }
                case 6:{
                    exit = false;
                    break;
                }
                default:{
                    System.out.println("You must type from 1 to 5! Retype: ");
                    break;
                }
            }
        }
    }

    public static int showMenu(){
        System.out.println("==========CUSTOMER MANAGEMENT=============");
        System.out.println("1. Add a new customer");
        System.out.println("2. Update a customer by customer id");
        System.out.println("3. Delete a customer by customer id");
        System.out.println("4. Search customer by customer id");
        System.out.println("5. Show all customer");
        System.out.println("6. Exit");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}

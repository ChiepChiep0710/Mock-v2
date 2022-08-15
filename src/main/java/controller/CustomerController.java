package controller;

import service.CustomerService;
import service.impl.CustomerServiceImpl;

import java.util.Scanner;

public class CustomerController {
    private static Scanner scanner = new Scanner(System.in);
    private static final CustomerService customerService = new CustomerServiceImpl();
    public static void main(String[] args) {
        int menu;
        while(true){
            menu = showMenu();
            switch (menu){
                case 1:{
                    boolean result = customerService.save();
                    System.out.println("Add a new customer " + (result ? "success." : "fail."));
                    break;
                }
                case 2:{
                    System.out.print("Enter customer id:");
                    int id = scanner.nextInt();
                    boolean result = customerService.update(id);
                    System.out.println("Update customer " + (result ? "success." : "fail."));
                    break;
                }
                case 3:{
                    System.out.print("Enter customer id:");
                    int id = scanner.nextInt();
                    boolean result = customerService.delete(id);
                    System.out.println("Delete customer " + (result ? "success." : "fail."));
                    break;
                }
                case 4:{
                    customerService.findAll().forEach(System.out::println);
                    break;
                }
                case 5:{
                    System.exit(0);
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
        System.out.println("===============MENU====================");
        System.out.println("1. Add a new customer");
        System.out.println("2. Update a customer by customer id");
        System.out.println("3. Delete a customer by customer id");
        System.out.println("4. Find all customer");
        System.out.println("5. Exit");
        System.out.print("Enter your choose: ");
        int choose = scanner.nextInt();
        scanner.nextLine();
        return choose;
    }
}

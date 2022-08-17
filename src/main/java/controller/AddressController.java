package controller;

import model.Address;
import service.AddressService;
import service.impl.AddressServiceImpl;

import java.util.List;
import java.util.Scanner;

public class AddressController {
    private static final Scanner scanner = new Scanner(System.in);

    private static final AddressService addressService = new AddressServiceImpl();

    public static void addressController(){
        int menu;
        boolean exit = true;
        while(exit){
            menu = showMenu();
            switch (menu){
                case 1:{
                    boolean resultCreate = addressService.save();
                    System.out.println("Create new address " + (resultCreate ? "success":"fail"));
                    break;
                }
                case 2:{
                    boolean resultUpdate =addressService.update();
                    System.out.println("Update address " + (resultUpdate ? "success":"fail"));
                    break;
                }
                case 3:{
                    boolean resultDelete = addressService.delete();
                    System.out.println("Delete address " + (resultDelete ? "success":"fail"));
                    break;
                }
                case 4:{
                    List<Address> addressList = addressService.findAll();
                    addressList.forEach(System.out::println);
                    break;
                }
                case 5:{
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
        System.out.println("==========ADDRESS MANAGEMENT=============");
        System.out.println("1. Add a new address");
        System.out.println("2. Update a address by address id");
        System.out.println("3. Delete a address by address id");
        System.out.println("4. Show all address");
        System.out.println("5. Exit");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}

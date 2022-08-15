package controller;

import java.util.Scanner;

public class DiscountController {
    private static Scanner scanner = new Scanner(System.in);

    public static void discountController(){
        int menu;
        boolean exit = true;
        while(exit){
            menu = showMenu();
            switch (menu){
                case 1:{

                    break;
                }
                case 2:{

                    break;
                }
                case 3:{

                    break;
                }
                case 4:{

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
        System.out.println("==========DISCOUNT MANAGEMENT=============");
        System.out.println("1. Add a new discount");
        System.out.println("2. Update a discount by discount id");
        System.out.println("3. Delete a discount by discount id");
        System.out.println("4. Show all discount");
        System.out.println("5. Exit");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}

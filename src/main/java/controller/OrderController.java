package controller;

import java.util.Scanner;

public class OrderController {
    private static Scanner scanner = new Scanner(System.in);

    public static void orderController(){
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
        System.out.println("==========ORDER MANAGEMENT=============");
        System.out.println("1. Add a new order");
        System.out.println("2. Update a order by order id");
        System.out.println("3. Delete a order by order id");
        System.out.println("4. Show all order");
        System.out.println("5. Exit");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}

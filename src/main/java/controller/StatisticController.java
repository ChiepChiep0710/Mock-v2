package controller;

import java.util.Scanner;

public class StatisticController {
    private static Scanner scanner = new Scanner(System.in);

    public static void statisticController(){
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
        System.out.println("==============STATISTICS================");
        System.out.println("1. By CustomerId");
        System.out.println("2. Number of products sold in a month");
        System.out.println("3. Total revenue a month");
        System.out.println("4. Top selling products");
        System.out.println("5. Exit");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}

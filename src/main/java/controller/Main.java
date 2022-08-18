package controller;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int mainMenu;
        while (true){
            mainMenu = showMainMenu();
            switch (mainMenu){
                case 1:{
                    //Customer management
                    CustomerController.customerController();
                    break;
                }
                case 2:{
                    //Product management
                    ProductController.productController();
                    break;
                }
                case 3:{
                    //Address management
                    AddressController.addressController();
                    break;
                }
                case 4:{
                    //Discount management
                    DiscountController.discountController();
                    break;
                }
                case 5:{
                    //Order-detail management
                    //OrderDetailController.orderDetailController();
                    break;
                }
                case 6:{
                    //Order management
                    OrderController.orderController();
                    break;
                }
                case 7:{
                    //Statistic
                    StatisticController.statisticController();
                    break;
                }
                case 8:{
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println("You must type from 1 to 8.");
                    break;
                }
            }
        }
    }

    public static int showMainMenu(){
        System.out.println("=========MENU=========");
        System.out.println("1. Customer Management");
        System.out.println("2. Product Management");
        System.out.println("3. Address Management");
        System.out.println("4. Discount Management");
        System.out.println("5. Order-detail Management");
        System.out.println("6. Order Management");
        System.out.println("7. Statistics");
        System.out.println("8. Exit");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}

package controller;

import model.Order;
import model.Product;
import service.OrderService;
import service.ProductService;
import service.impl.OrderServiceImpl;
import service.impl.ProductServiceImpl;
import util.Validators.ProductValidator;

import java.util.List;
import java.util.Scanner;

public class StatisticController {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductService productService = new ProductServiceImpl();
    private static OrderService orderService = new OrderServiceImpl();

    public static void statisticController(){
        int menu;
        boolean exit = true;
        while(exit){
            menu = showMenu();
            switch (menu){
                case 1:{
                    System.out.print("Enter customer id:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    productService.findByCustomerId(id);
                    break;
                }
                case 2:{
                    System.out.print("Enter month: ");
                    int month = ProductValidator.getInstance().validateMonth();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.printf("%-15s%-20s%-20s\n","PRODUCT_ID","NAME","SUM_SOLD");
                    List<Product> products = productService.findByMonth(month, year);
                    for (int i = 0; i < products.size(); i++){
                        System.out.printf("%-15d%-20s%-20d\n",products.get(i).getProductId(),products.get(i).getName(),products.get(i).getSumSold());
                    }
                    break;
                }
                case 3:{
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    List<Order> sumTotal = orderService.calculateTotalByMonth(year);
                    System.out.printf("%-20s%-20s\n", "MONTH", "SUM_REVENUE");
                    for(int i = 0; i < sumTotal.size(); i++){
                        System.out.printf("%-20d%-20.2f\n", sumTotal.get(i).getMonth(), sumTotal.get(i).getSumTotal());
                    }
                    break;
                }
                case 4:{
                    System.out.printf("%-10s%-30s%-30s%-10s%-20s%-10s%-10s%-20s%-20s\n", "ID", "NAME", "DESCRIPTION",
                            "PRICE", "DISCOUNT_PRICE", "STOCK", "SOLD", "CREATE_DATE", "STATUS");
                    productService.showTop().forEach(Product::display);
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
        System.out.println("1. Show product by CustomerId");
        System.out.println("2. Number of products sold in month");
        System.out.println("3. Total revenue of year");
        System.out.println("4. Top 10 selling products");
        System.out.println("5. Exit");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}

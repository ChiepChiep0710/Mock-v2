package controller;

import model.Order;
import service.OrderService;
import service.impl.OrderServiceImpl;

import java.util.List;
import java.util.Scanner;

public class OrderController {
    private static final Scanner scanner = new Scanner(System.in);

    private static final OrderService orderService = new OrderServiceImpl();

    public static void orderController(){
        int menu;
        boolean exit = true;
        while(exit){
            menu = showMenu();
            switch (menu){
                case 1:{
                    boolean resultCreate = orderService.save();
                    System.out.println("Create new order " + (resultCreate ? "success":"fail"));
                    break;
                }
                case 2:{
                    boolean resultUpdate = orderService.update();
                    System.out.println("Update new order " + (resultUpdate ? "success":"fail"));
                    break;
                }
                case 3:{
                    boolean resultDelete = orderService.delete();
                    System.out.println("Delete new order " + (resultDelete ? "success":"fail"));
                    break;
                }
                case 4:{
                    orderService.findAll();
                    break;
                }
                case 5:{
                    orderService.searchById();
                    break;
                }
                case 6:{
                    orderService.findByCustomerId();
                    break;
                }
                case 7:{
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
        System.out.println("5. Search a order by order id");
        System.out.println("6. Show list order of a customer by customer id");
        System.out.println("7. Exit");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}

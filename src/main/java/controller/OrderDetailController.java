package controller;

import dao.OrderDAO;
import dao.impl.OrderDAOImpl;
import model.OrderDetail;
import service.OrderDetailService;
import service.OrderService;
import service.impl.OrderDetailServiceImpl;
import service.impl.OrderServiceImpl;

import java.util.Date;
import java.util.Scanner;

public class OrderDetailController {
    private static Scanner scanner = new Scanner(System.in);
    private static OrderDetailService orderDetailService = new OrderDetailServiceImpl();
    private static OrderDAO orderDAO = new OrderDAOImpl();
    public static void orderDetailController(){
        int menu;
        boolean exit = true;
        while(exit){
            menu = showMenu();
            switch (menu){
                case 1:{
                    System.out.print("Enter order id: ");
                    int id = scanner.nextInt();
                    Date orderDate = orderDAO.searchById(id).getOrderDate();
                    boolean result = orderDetailService.save(id, orderDate);
                    System.out.println("Create order-detail " + (result ? "success" : "fail"));
                    break;
                }
                case 2:{
                    System.out.print("Enter cart id: ");
                    int id = scanner.nextInt();
                    boolean result = orderDetailService.update(id);
                    System.out.println("Update order-detail " + (result ? "success" : "fail"));
                    break;
                }
                case 3:{
                    System.out.print("Enter cart id: ");
                    int id = scanner.nextInt();
                    boolean result = orderDetailService.delete(id);
                    System.out.println("Delete order-detail " + (result ? "success" : "fail"));
                    break;
                }
                case 4:{
                    System.out.print("Enter order id: ");
                    int id = scanner.nextInt();
                    System.out.printf("%-10s%-10s%-20s%-10s%-10s\n", "CART_ID", "QUANTITY", "TOTAL", "ORDER_ID", "PRODUCT_ID");
                    orderDetailService.findByOrderId(id).forEach(OrderDetail::display);
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
        System.out.println("==========ORDER-DETAIL MANAGEMENT=============");
        System.out.println("1. Add a new order-detail");
        System.out.println("2. Update a order-detail by order-detail id");
        System.out.println("3. Delete a order-detail by order-detail id");
        System.out.println("4. Show all order-detail by order id");
        System.out.println("5. Exit");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}

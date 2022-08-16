package controller;

import service.ProductService;
import service.impl.ProductServiceImpl;

import java.util.Scanner;

public class ProductController {
//    public static void main(String[] args) throws SQLException {
//        ProductService productService= new ProductServiceImplement();
//        List<Product> products= productService.findAll();
//        products.forEach(System.out::println);
//
//    }

    private static ProductService productService = new ProductServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void productController(){
        int menu;
        boolean exit = true;
        while(exit){
            menu = showMenu();
            switch (menu){
                case 1:{
                    boolean result = productService.create();
                    System.out.println("Create a new product " + (result ? "success" : "fail"));
                    break;
                }
                case 2:{
                    System.out.print("Enter product id: ");
                    int id = scanner.nextInt();
                    boolean result = productService.update(id);
                    System.out.println("Update product " + (result ? "success" : "fail"));
                    break;
                }
                case 3:{
                    System.out.print("Enter product id: ");
                    int id = scanner.nextInt();
                    boolean result = productService.delete(id);
                    System.out.println("Delete product " + (result ? "success" : "fail"));
                    break;
                }
                case 4:{
                    productService.findAll().forEach(System.out::println);
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
        System.out.println("==========PRODUCT MANAGEMENT=============");
        System.out.println("1. Add a new product");
        System.out.println("2. Update a product by product id");
        System.out.println("3. Delete a product by product id");
        System.out.println("4. Show all product");
        System.out.println("5. Exit");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}

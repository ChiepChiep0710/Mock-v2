package controller;

import model.Discount;
import service.DiscountService;
import service.impl.DiscountServiceImpl;
import util.DiscountValidator;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class DiscountController {
    private static final DiscountService discountService = new DiscountServiceImpl();
    private static DiscountValidator validator = new DiscountValidator();
    private static Scanner s = new Scanner(System.in);

    public static void discountController(){
        int menu;
        boolean exit = true;
        while(exit){
            menu = showMenu();
            switch (menu){
                case 1:{
                    boolean result = discountService.createDiscount();
                    out.println("CREATE DISCOUNT " + (result ? "SUCCESS" : "FAIL"));
                    break;
                }
                case 2:{
                    out.println("Enter the ID to update:");
                    int id = s.nextInt();
                    boolean res = discountService.updateDiscount(id);
                    out.println("UPDATE DISCOUNT " + (res ? "SUCCESS" : "FAIL"));
                    break;
                }
                case 3:{
                    out.println("Enter the ID to delete: ");
                    int id = s.nextInt();
                    boolean res = discountService.deleteDiscount(id);
                    out.println("DELETE DISCOUNT " + (res ? "SUCCESS" : "FAIL"));
                    break;
                }
                case 4:{
                    List<Discount> discounts = discountService.findAllDiscount();
                    out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", "ID", "TITLE", "TYPE", "DISCOUNT", "START DATE", "END DATE");
                    for (int i = 0; i < discounts.size(); i++) {
                        out.printf("%-20s%-20s%-20s%-20.2f%-20s%-20s\n", discounts.get(i).getDiscountId(), discounts.get(i).getTitle(), discounts.get(i).getType(), discounts.get(i).getDiscount(), discounts.get(i).getStartDate(), discounts.get(i).getEndDate());
                    }
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
        int choice = s.nextInt();
        s.nextLine();
        return choice;
    }
}

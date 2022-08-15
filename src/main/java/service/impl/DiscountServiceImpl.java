package service.impl;

import dao.DiscountDAO;
import dao.impl.DiscountDAOImpl;
import model.Discount;
import service.DiscountService;
import util.Validators.DiscountValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class DiscountServiceImpl implements DiscountService {
    private Scanner scanner = new Scanner(System.in);
    private static DiscountValidator validator = new DiscountValidator();
    private DiscountDAO discountDAO = new DiscountDAOImpl();

    @Override
    public boolean createDiscount() {
        System.out.println("CREATE DISCOUNT: ");
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Select type discount(0-Percent, 1-Money): ");
        int type = scanner.nextInt();
        scanner.nextLine();
        Double dis = 0.0;
        if(type == 0){
            System.out.print("Enter discount percent: ");
            dis = scanner.nextDouble();
            scanner.nextLine();
            while (dis < 0.0 || dis > 100.0){
                System.out.print("Discount price not correct! Retype: ");
                dis = scanner.nextDouble();
                scanner.nextLine();
            }
        } else if(type == 1){
            System.out.print("Enter discount money: ");
            dis = scanner.nextDouble();
            scanner.nextLine();
        } else{
            out.println("You must type 0 or 1! The discount default 0.");
        }

        String format = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date startDate = null;
        while (true) {
            System.out.print("Enter Start Date: ");
            String day1 = scanner.nextLine();
            boolean isvalid = validator.dateValidator(day1);
            if (isvalid) {
                try {
                    startDate = dateFormat.parse(day1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                out.println("Invalid Start Date requires re-entry!");
            }
        }
        Date endDate = null;
        while (true) {
            System.out.print("Enter End Date: ");
            String day2 = scanner.nextLine();
            boolean isvalid = validator.dateValidator(day2);
            try {
                if (isvalid && dateFormat.parse(day2).compareTo(startDate) >= 0) {
                    try {
                            endDate = dateFormat.parse(day2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                } else {
                    out.println("Invalid End Date requires re-entry!");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Discount discount = new Discount(title, type, dis, startDate, endDate);
        return discountDAO.createDiscount(discount) > 0;
    }

    @Override
    public List<Discount> findAllDiscount() {
        return discountDAO.findAllDiscount();
    }

    @Override
    public boolean deleteDiscount(int id) {
        List<Discount> discounts = findAllDiscount();
        Discount res = null;
        for (int i = 0; i < discounts.size(); i++) {
            if(discounts.get(i).getDiscountId() == id){
                res = discounts.get(i);
            }
        }
        if(res != null){
            out.println("Found dis have ID: "+ res.getDiscountId());
        }else {
            out.println("No discount found");
            return false;
        }
        return discountDAO.deleteDiscount(id) >0;
    }

    @Override
    public boolean updateDiscount(int id) {
        List<Discount> discounts = findAllDiscount();
        Discount res = null;
        for (int i = 0; i < discounts.size(); i++) {
            if(discounts.get(i).getDiscountId() == id){
                res = discounts.get(i);
            }
        }
        if(res != null){
            out.println("Found dis have ID: "+ res.getDiscountId());
        }else {
            out.println("No discount found");
            return false;
        }
        String isContinue;
        System.out.println("Do you want to change title? (y/n)");
        isContinue = scanner.nextLine();
        if(isContinue.equalsIgnoreCase("y")) {
            out.print("Enter Title: ");
            res.setTitle(scanner.nextLine());
        }

        System.out.println("Do you want to change type? (y/n)");
        isContinue = scanner.nextLine();
        if(isContinue.equalsIgnoreCase("y")) {
            out.print("Select type discount(0-Percent, 1-Money): ");
            res.setType(scanner.nextInt());
            scanner.nextLine();
            if(res.getType() == 0){
                System.out.print("Enter discount percent: ");
                res.setDiscount(scanner.nextDouble());
                scanner.nextLine();
                while (res.getDiscount() < 0.00 || res.getDiscount() > 100.00){
                    System.out.print("Discount price not correct! Retype: ");
                    res.setDiscount(scanner.nextDouble());
                    scanner.nextLine();
                }
            } else if(res.getType() == 1){
                System.out.print("Enter discount money: ");
                res.setDiscount(scanner.nextDouble());
                scanner.nextLine();
            } else{
                out.println("You must type 0 or 1! The discount default 0.");
            }
        }

        System.out.println("Do you want to change start date? (y/n)");
        isContinue = scanner.nextLine();
        if(isContinue.equalsIgnoreCase("y")) {
            String format = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date startDate = null;
            while (true) {
                System.out.print("Enter Start Date: ");
                String day1 = scanner.nextLine();
                boolean isvalid = validator.dateValidator(day1);
                if (isvalid) {
                    try {
                        startDate = dateFormat.parse(day1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                } else {
                    out.println("Invalid Start Date requires re-entry!");
                }
            }
            res.setStartDate(startDate);
        }

        System.out.println("Do you want to change End Date? (y/n)");
        isContinue = scanner.nextLine();
        if(isContinue.equalsIgnoreCase("y")) {
            String format = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date endDate = null;
            while (true) {
                System.out.print("Enter End Date: ");
                String day2 = scanner.nextLine();
                boolean isvalid = validator.dateValidator(day2);
                try {
                    if (isvalid && dateFormat.parse(day2).compareTo(res.getStartDate()) > 0) {
                        try {
                            endDate = dateFormat.parse(day2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else {
                        out.println("Invalid End Date requires re-entry!");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            res.setEndDate(endDate);
        }
        return discountDAO.updateDiscount(res) > 0;
    }
}

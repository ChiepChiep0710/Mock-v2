package service.impl;

import dao.DiscountDAO;
import dao.impl.DiscountDAOImpl;
import model.Discount;
import service.DiscountService;
import util.DiscountValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class DiscountServiceImpl implements DiscountService {
    Scanner input = new Scanner(System.in);
    private static DiscountValidator validator = new DiscountValidator();
    private DiscountDAO discountDAO = new DiscountDAOImpl();

    @Override
    public boolean createDiscount() {
        System.out.println("CREATE DISCOUNT: ");
        System.out.println("Enter Title: ");
        var title = input.nextLine();
        System.out.println("Enter Type: ");
        var type = input.nextLine();
        System.out.println("Enter Discount: ");
        var dis = input.nextFloat();
        input.nextLine();
        var format = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date startDate = null;
        while (true) {
            System.out.println("Enter Start Date: ");
            var day1 = input.nextLine();
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
            System.out.println("Enter End Date: ");
            var day2 = input.nextLine();
            boolean isvalid = validator.dateValidator(day2);
            try {
                if (isvalid && dateFormat.parse(day2).compareTo(startDate) > 0) {
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
        var discount = new Discount(title, type, dis, startDate, endDate);
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
        isContinue = input.nextLine();
        if(isContinue.equalsIgnoreCase("y")) {
            res.setTitle(input.nextLine());
        }

        System.out.println("Do you want to change type? (y/n)");
        isContinue = input.nextLine();
        if(isContinue.equalsIgnoreCase("y")) {
            res.setType(input.nextLine());
        }

        System.out.println("Do you want to change discount? (y/n)");
        isContinue = input.nextLine();
        if(isContinue.equalsIgnoreCase("y")) {
            res.setDiscount(input.nextFloat());
            input.nextLine();
        }

        System.out.println("Do you want to change start date? (y/n)");
        isContinue = input.nextLine();
        if(isContinue.equalsIgnoreCase("y")) {
            var format = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date startDate = null;
            while (true) {
                System.out.println("Enter Start Date: ");
                var day1 = input.nextLine();
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
        isContinue = input.nextLine();
        if(isContinue.equalsIgnoreCase("y")) {
            var format = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date endDate = null;
            while (true) {
                System.out.println("Enter End Date: ");
                var day2 = input.nextLine();
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

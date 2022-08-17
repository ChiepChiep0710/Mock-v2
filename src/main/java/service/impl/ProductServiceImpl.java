package service.impl;

import dao.DiscountDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.impl.DiscountDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderDetailDAOImpl;
import dao.impl.ProductDAOImpl;
import model.Order;
import model.OrderDetail;
import model.Product;
import service.ProductService;
<<<<<<< HEAD
import util.Validators.DiscountValidator;
import util.Validators.ProductValidator;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
=======
import util.Validators.ProductValidator;

>>>>>>> d893c3d46c5cfcaf3afa97c6a9ef6401a7b85a9b
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO = new ProductDAOImpl();
    private DiscountDAO discountDAO = new DiscountDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();
    private Scanner scanner = new Scanner(System.in);
    @Override
    public boolean create() {
        Product product = new Product();
        System.out.print("Enter product name: ");
        product.setName(scanner.nextLine());
        System.out.print("Enter product description: ");
        product.setDescription(scanner.nextLine());
        System.out.print("Enter product price: ");
        product.setPrice(ProductValidator.getInstance().validatePrice());
        System.out.print("Enter product stock: ");
        product.setStock(ProductValidator.getInstance().validateInteger());
        if(product.getStock() > 0){
            product.setStatus(1);
        } else{
            product.setStatus(0);
        }
        System.out.print("Enter product sold: ");
        product.setSold(ProductValidator.getInstance().validateInteger());
        out.print("Enter create date: ");
        product.setCreate_date(ProductValidator.getInstance().validateDate());

        out.println("Does this product have a discount code?(y/n)");
        String choice = scanner.nextLine();
        if(choice.equalsIgnoreCase("y")){
            out.print("Enter discount id: ");
            int discountId = scanner.nextInt();
            scanner.nextLine();
            if(discountDAO.searchById(discountId) != null){
                product.setDiscountId(discountId);
                int id =  productDAO.createWithDiscount(product);
                if(id > 0){
                    return productDAO.calculateDiscountPriceById(id) > 0;
                }
                return false;
            } else{
                out.println("Discount id not exists!");
                product.setDiscount_price(product.getPrice());
                return productDAO.createWithoutDiscount(product) > 0;
            }
        } else {
            product.setDiscount_price(product.getPrice());
            return productDAO.createWithoutDiscount(product) > 0;
        }
    }

    @Override
    public boolean delete(int productId) {
        if(productDAO.searchById(productId) == null){
            out.println("Product not exists!");
            return false;
        }
        return productDAO.delete(productId) > 0;
    }

    @Override
    public boolean update(int productId) {
        if(productDAO.searchById(productId) == null){
            out.println("Product not exists!");
            return false;
        }
        Product product = productDAO.searchById(productId);
        String isChange;
        out.print("Do you want to change product name?(y/n)");
        isChange = scanner.nextLine();
        if(isChange.equalsIgnoreCase("y")){
            System.out.print("Enter product name: ");
            product.setName(scanner.nextLine());
        }

        out.print("Do you want to change product description?(y/n)");
        isChange = scanner.nextLine();
        if(isChange.equalsIgnoreCase("y")){
            System.out.print("Enter product description: ");
            product.setDescription(scanner.nextLine());
        }

        out.print("Do you want to change product price?(y/n)");
        isChange = scanner.nextLine();
        if(isChange.equalsIgnoreCase("y")){
            System.out.print("Enter product price: ");
            product.setPrice(ProductValidator.getInstance().validatePrice());
        }

        out.print("Do you want to change product stock?(y/n)");
        isChange = scanner.nextLine();
        if(isChange.equalsIgnoreCase("y")){
            System.out.print("Enter product stock: ");
            product.setStock(ProductValidator.getInstance().validateInteger());
            if(product.getStock() > 0){
                product.setStatus(1);
            } else{
                product.setStatus(0);
            }
        }

        out.print("Do you want to change product sold?(y/n)");
        isChange = scanner.nextLine();
        if(isChange.equalsIgnoreCase("y")){
            System.out.print("Enter product sold: ");
            product.setSold(ProductValidator.getInstance().validateInteger());
        }

        out.print("Do you want to change product create date?(y/n)");
        isChange = scanner.nextLine();
        if(isChange.equalsIgnoreCase("y")){
            out.print("Enter create date: ");
            product.setCreate_date(ProductValidator.getInstance().validateDate());
        }

        out.print("Do you want to change discount code?(y/n)");
        isChange = scanner.nextLine();
        if(isChange.equalsIgnoreCase("y")){
            out.print("Enter discount id: ");
            int discountId = scanner.nextInt();
            scanner.nextLine();
            if(discountDAO.searchById(discountId) != null){
                product.setDiscountId(discountId);
                productDAO.calculateDiscountPriceById(productId);
            } else{
                out.println("Discount id not exists!");
            }
        }

        return productDAO.update(product) > 0;
    }

    @Override
    public List<Product> findAll() {

        return productDAO.findAll();
    }

    @Override
    public Product searchById(int productId) {
        return productDAO.searchById(productId);
    }

    @Override
    public void findByCustomerId(int customerId) {
        List<Order> orders = orderDAO.findByCustomerId(customerId);
        if(orders == null){
            out.println("Don't have order!");
        }
        out.printf("%-15s%-20s%-10s%-15s%-10s%-20s\n","PRODUCT_ID","NAME","PRICE","DISCOUNT_PRICE","QUANTITY","TOTAL");
        for(int i = 0; i < orders.size(); i++){
            List<OrderDetail> orderDetails = orderDetailDAO.findByOrderId(orders.get(i).getOrderID());
            for(int j = 0; j < orderDetails.size(); j++){
                Product product = productDAO.searchById(orderDetails.get(i).getProductId());
                out.printf("%-15d%-20s%-10.2f%-15.2f%-10d%-20.2f\n", product.getProductId(), product.getName(), product.getPrice(),
                        product.getDiscount_price(), orderDetails.get(i).getQuantity(), orderDetails.get(i).getTotal());
            }
        }
    }

    @Override
    public List<Product> findByMonth(int month) {
        return productDAO.findByMonth(month);
    }
}

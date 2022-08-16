package service.impl;

import dao.DiscountDAO;
import dao.ProductDAO;
import dao.impl.DiscountDAOImpl;
import dao.impl.ProductDAOImpl;
import model.Product;
import service.ProductService;
import util.Validators.DiscountValidator;
import util.Validators.ProductValidator;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO = new ProductDAOImpl();
    private DiscountDAO discountDAO = new DiscountDAOImpl();
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
                return productDAO.createWithoutDiscount(product) > 0;
            }
        } else {
            product.setDiscount_price(0.0);
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
}

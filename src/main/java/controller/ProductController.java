package controller;

import model.Product;
import service.ProductService;
import service.impl.ProductServiceImplement;

import java.sql.SQLException;
import java.util.List;

public class ProductController {
    public static void main(String[] args) throws SQLException {
        ProductService productService= new ProductServiceImplement();
        List<Product> products= productService.findAll();
        products.forEach(System.out::println);

    }
}

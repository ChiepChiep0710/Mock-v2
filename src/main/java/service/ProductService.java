package service;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    boolean  create();

    boolean delete(int ProductId);

    boolean update(int productId);

    List<Product> findAll();

    Product searchById(int productId);

    void findByCustomerId(int customerId);

    List<Product> findByMonth(int month);
}

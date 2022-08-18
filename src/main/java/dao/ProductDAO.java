package dao;

import model.Product;

import java.util.List;

public interface ProductDAO {
    int  createWithoutDiscount(Product product);
    int createWithDiscount(Product product);
    int delete(int ProductId);
    int update(Product product);
    List<Product> findAll();
    int calculateDiscountPriceById(int productId);
    Product searchById(int productId);
    List<Product> findByMonth(int month);

    List<Product> showTop();
}

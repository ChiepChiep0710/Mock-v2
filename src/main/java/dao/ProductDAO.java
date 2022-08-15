package dao;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    int  create(Product product) throws SQLException;
    int delete(int ProductId) throws SQLException;
    int update(Product product) throws SQLException;

    List<Product> findAll();

}

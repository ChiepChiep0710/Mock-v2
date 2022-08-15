package service.impl;

import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import model.Product;
import service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImplement implements ProductService {
    private ProductDAO productDAO = new ProductDAOImpl();
    @Override
    public int create(Product product) throws SQLException {

        return productDAO.create(product);
    }

    @Override
    public int delete(int ProductId) throws SQLException {

        return productDAO.delete(ProductId);
    }

    @Override
    public int update(Product product) throws SQLException {
        return productDAO.update(product);
    }

    @Override
    public List<Product> findAll() {

        return productDAO.findAll();
    }
}

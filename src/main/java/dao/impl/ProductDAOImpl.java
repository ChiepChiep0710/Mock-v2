package dao.impl;

import dao.ProductDAO;
import model.Product;
import util.DBUtil;
import util.SQLCOMMAND.ProductSQLCOMMAND;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public int create(Product product) throws SQLException {
        DBUtil dbconect= DBUtil.getInstance();
        Connection connection= dbconect.getConnection();
        String sql= ProductSQLCOMMAND.Product_INSERT;
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,product.getName());
        preparedStatement.setString(2,product.getDescription());
        preparedStatement.setBigDecimal(3, product.getPrice());
        preparedStatement.setBigDecimal(4,product.getDiscount_price());
        preparedStatement.setInt(5,product.getStock());
        preparedStatement.setInt(6,product.getSold());
        preparedStatement.setDate(7, new Date(product.getCreate_date().getTime()));
        preparedStatement.setInt(8,product.getStatus());


        return preparedStatement.executeUpdate();

    }

    @Override
    public int delete(int ProductId) throws SQLException {
        DBUtil dbconect= DBUtil.getInstance();
        Connection connection= dbconect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLCOMMAND.Product_DELETE);
        preparedStatement.setInt(1,ProductId);
        return preparedStatement.executeUpdate();

    }

    @Override
    public int update(Product product) throws SQLException {
        DBUtil dbconect= DBUtil.getInstance();
        Connection connection= dbconect.getConnection();
        String sql= ProductSQLCOMMAND.Product_UPDATE;
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,product.getName());
        preparedStatement.setString(2,product.getDescription());
        preparedStatement.setBigDecimal(3, product.getPrice());
        preparedStatement.setBigDecimal(4,product.getDiscount_price());
        preparedStatement.setInt(5,product.getStock());
        preparedStatement.setInt(6,product.getSold());
        preparedStatement.setDate(7, new Date(product.getCreate_date().getTime()));
        preparedStatement.setInt(8,product.getStatus());
        preparedStatement.setInt(9,product.getProductId());

        return preparedStatement.executeUpdate();

    }

    @Override
    public List<Product> findAll() {
        try (Connection connection = DBUtil.getInstance().getConnection()) {
            String sql = ProductSQLCOMMAND.Product_findall;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<Product> Products = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("ProductID");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                BigDecimal price= resultSet.getBigDecimal("Price");
                BigDecimal discount_price= resultSet.getBigDecimal("Discount_Price");
                int stock= resultSet.getInt("Stock");
                int sold= resultSet.getInt("Sold");
                java.util.Date create_date= new java.util.Date(resultSet.getDate("Create_date").getTime());
                int status= resultSet.getInt("Status");

                Products.add(new Product(id, name, description, price, discount_price, stock, sold, create_date, status));
            }

            return Products;
        } catch (Exception e) {
            e.printStackTrace();

            return new ArrayList<>();
        }
    }
    }


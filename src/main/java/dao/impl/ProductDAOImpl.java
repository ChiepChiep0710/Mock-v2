package dao.impl;

import dao.ProductDAO;
import model.Product;
import util.DBUtil;
import util.SQLCOMMAND.ProductSQLCOMMAND;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public int createWithoutDiscount(Product product) {
        try(Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLCOMMAND.PRODUCT_INSERT_WITHOUT_DISCOUNT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, product.getName(), product.getDescription(),
                    product.getPrice(), product.getDiscount_price(), product.getStock(), product.getSold(), product.getCreate_date());
            if(preparedStatement != null){
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    int id = resultSet.getInt(1);
                    return id;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int createWithDiscount(Product product) {
        try(Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLCOMMAND.PRODUCT_INSERT_WITH_DISCOUNT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, product.getName(), product.getDescription(),
                    product.getPrice(), product.getStock(), product.getSold(), product.getCreate_date(), product.getDiscountId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                int id = resultSet.getInt(1);
                return id;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int ProductId) {
        try (Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLCOMMAND.PRODUCT_DELETE);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, ProductId);
            if(preparedStatement != null){
                return preparedStatement.executeUpdate();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int update(Product product) {
        try (Connection connection = DBUtil.getInstance().getConnection();){
            if(product.getDiscountId() == 0){
                PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLCOMMAND.PRODUCT_UPDATE_WITHOUT_DISCOUNT);
                preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, product.getName(),
                        product.getDescription(), product.getPrice(), product.getStock(), product.getSold(),
                        product.getCreate_date(), product.getStatus(), product.getProductId());
                if(preparedStatement != null){
                    return preparedStatement.executeUpdate();
                }
            }
            else{
                PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLCOMMAND.PRODUCT_UPDATE_WITH_DISCOUNT);
                preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, product.getName(),
                        product.getDescription(), product.getPrice(), product.getStock(), product.getSold(), product.getCreate_date(),
                        product.getStatus(), product.getDiscountId(), product.getProductId());
                if(preparedStatement != null){
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Product> findAll() {
        try (Connection connection = DBUtil.getInstance().getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLCOMMAND.PRODUCT_SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("PRODUCT_ID");
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");
                Double price= resultSet.getDouble("PRICE");
                Double discount_price= resultSet.getDouble("DISCOUNT_PRICE");
                int stock= resultSet.getInt("STOCK");
                int sold= resultSet.getInt("SOLD");
                Date create_date = resultSet.getDate("CREATE_DATE");
                int status= resultSet.getInt("STATUS");
                int discountId = resultSet.getInt("DISCOUNT_ID");

                products.add(new Product(id, name, description, price, discount_price, stock, sold, create_date, status, discountId));
            }

            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int calculateDiscountPriceById(int productId){
        try (Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLCOMMAND.PRODUCT_CAL_DISCOUNT_PRICE);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, productId);

            if(preparedStatement != null){
                return preparedStatement.executeUpdate();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public Product searchById(int productId) {
        try (Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLCOMMAND.PRODUCT_SEARCH_BY_ID);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, productId);
            if(preparedStatement != null){
                ResultSet resultSet = preparedStatement.executeQuery();
                Product product = null;
                while (resultSet.next()){
                    int id = resultSet.getInt("PRODUCT_ID");
                    String name = resultSet.getString("NAME");
                    String description = resultSet.getString("DESCRIPTION");
                    Double price= resultSet.getDouble("PRICE");
                    Double discount_price= resultSet.getDouble("DISCOUNT_PRICE");
                    int stock= resultSet.getInt("STOCK");
                    int sold= resultSet.getInt("SOLD");
                    Date create_date = resultSet.getDate("CREATE_DATE");
                    int status= resultSet.getInt("STATUS");
                    int discountId = resultSet.getInt("DISCOUNT_ID");
                    product = new Product(id, name, description, price, discount_price, stock, sold, create_date, status, discountId);
                }
                return product;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findByMonth(int month) {
        try (Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(ProductSQLCOMMAND.PRODUCT_BY_MONTH);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, month);
            if(preparedStatement != null){
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Product> products = new ArrayList<>();
                while (resultSet.next()){
                    int id = resultSet.getInt("PRODUCT_ID");
                    String name = resultSet.getString("NAME");
                    int sumSold = resultSet.getInt("SUM_SOLD");

                    products.add(new Product(id, name, sumSold));
                }
                return products;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}


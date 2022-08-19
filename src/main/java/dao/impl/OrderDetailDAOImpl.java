package dao.impl;

import dao.OrderDetailDAO;
import model.OrderDetail;
import util.DBUtil;
import util.SQLCOMMAND.OrderDetailSQLCommand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public int save(OrderDetail orderDetail) {
        try (Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(OrderDetailSQLCommand.ORDER_DETAIL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, orderDetail.getQuantity(),
                    orderDetail.getTotal(), orderDetail.getOrderId(), orderDetail.getProductId());
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
    public OrderDetail searchById(int cartId) {
        try (Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(OrderDetailSQLCommand.ORDER_DETAIL_SEARCH_BY_ID);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, cartId);
            if(preparedStatement != null){
                ResultSet resultSet = preparedStatement.executeQuery();
                OrderDetail orderDetail = null;
                while (resultSet.next()){
                    int id = resultSet.getInt("CART_ID");
                    int quantity = resultSet.getInt("QUANTITY");
                    Double total = resultSet.getDouble("TOTAL");
                    int orderId = resultSet.getInt("ORDER_ID");
                    int productId = resultSet.getInt("PRODUCT_ID");

                    orderDetail = new OrderDetail(id, quantity, total, orderId, productId);
                }
                return orderDetail;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(OrderDetail orderDetail) {
        try (Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(OrderDetailSQLCommand.ORDER_DETAIL_UPDATE);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, orderDetail.getQuantity(),
                    orderDetail.getTotal(),orderDetail.getCartId());
            if(preparedStatement != null){
                return preparedStatement.executeUpdate();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int cartId) {
        try(Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(OrderDetailSQLCommand.ORDER_DETAIL_DELETE);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, cartId);
            if(preparedStatement != null){
                return preparedStatement.executeUpdate();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OrderDetail> findByOrderId(int orderId) {
        try (Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(OrderDetailSQLCommand.ORDER_DETAIL_SELECT);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, orderId);
            if(preparedStatement != null){
                ResultSet resultSet = preparedStatement.executeQuery();
                List<OrderDetail> orderDetails = new ArrayList<>();
                while (resultSet.next()){
                    int id = resultSet.getInt("CART_ID");
                    int quantity = resultSet.getInt("QUANTITY");
                    Double total = resultSet.getDouble("TOTAL");
                    int productId = resultSet.getInt("PRODUCT_ID");

                    orderDetails.add(new OrderDetail(id, quantity, total, orderId, productId));
                }
                return orderDetails;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

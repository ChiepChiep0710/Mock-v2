package dao.impl;

import dao.OrderDAO;
import model.Order;
import util.DBUtil;
import util.SQLCOMMAND.OrderSQLCommand;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public int save(Order order) {
        try(Connection connection = DBUtil.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(OrderSQLCommand.INSERT_ORDER);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement,order.getName(), order.getPhoneNumber(),
                    order.getDetailAddress(), order.getTotal(), order.getOrderDate(), order.getStatus(), order.getCustomerID(), order.getAddressID());
            return preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Order order) {
        try(Connection connection = DBUtil.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(OrderSQLCommand.UPDATE_ORDER);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement,order.getName(), order.getPhoneNumber(),
                    order.getDetailAddress(), order.getTotal(), order.getOrderDate(), order.getStatus(), order.getCustomerID(),
                    order.getAddressID(), order.getOrderID());

            return preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Order order) {
        try(Connection connection = DBUtil.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(OrderSQLCommand.DELETE_ORDER);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, order.getOrderID());

            return preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Order> findAll() {
        try (Connection connection = DBUtil.getInstance().getConnection()) {
            String sql = "SELECT * FROM [ORDER]";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("ORDER_ID");
                String name = resultSet.getString("NAME");
                String phoneNumber = resultSet.getString("PHONE_NUMBER");
                String detailAddress = resultSet.getString("DETAIL_ADDRESS");
                double total = resultSet.getDouble("TOTAL");
                Date orderDate = resultSet.getDate("ORDER_DATE");
                int customerID = resultSet.getInt("CUSTOMER_ID");
                int addressID = resultSet.getInt("ADDRESS_ID");
                int status = resultSet.getInt("STATUS");

                orders.add(new Order(orderId, name, phoneNumber, detailAddress, total, orderDate, status, customerID, addressID));
            }

            return orders;
        } catch (Exception e) {
            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    @Override
    public Order searchById(int orderId) {
        try (Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(OrderSQLCommand.ORDER_SEARCH_BY_ID);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, orderId);
            Order order = null;
            if(preparedStatement != null){
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    String name = resultSet.getString("NAME");
                    String phoneNumber = resultSet.getString("PHONE_NUMBER");
                    String detailAddress = resultSet.getString("DETAIL_ADDRESS");
                    double total = resultSet.getDouble("TOTAL");
                    Date orderDate = resultSet.getDate("ORDER_DATE");
                    int customerID = resultSet.getInt("CUSTOMER_ID");
                    int addressID = resultSet.getInt("ADDRESS_ID");
                    int status = resultSet.getInt("STATUS");

                    order = new Order(orderId, name, phoneNumber, detailAddress, total, orderDate, customerID, addressID, status);
                }
                return order;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int check(String city, String district, String subDistrict){
        try (Connection connection = DBUtil.getInstance().getConnection()) {
            String sql = "SELECT * FROM ADDRESS WHERE CITY = '"+ city +"' " +
                    "AND DISTRICT = '" + district +"' " +
                    "AND SUB_DISTRICT = '" + subDistrict + "'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int addressID = resultSet.getInt("ADDRESS_ID");
                return addressID;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        System.out.println("Address do not exist");
        return 0;
    }

    @Override
    public List<Order> findByCustomerId(int customerId) {
        try (Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(OrderSQLCommand.ORDER_SEARCH_BY_CUSTOMER_ID);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, customerId);
            List<Order> orders = new ArrayList<>();
            if(preparedStatement != null){
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    int id = resultSet.getInt("ORDER_ID");
                    String name = resultSet.getString("NAME");
                    String phoneNumber = resultSet.getString("PHONE_NUMBER");
                    String detailAddress = resultSet.getString("DETAIL_ADDRESS");
                    double total = resultSet.getDouble("TOTAL");
                    Date orderDate = resultSet.getDate("ORDER_DATE");
                    int addressId = resultSet.getInt("ADDRESS_ID");
                    int status = resultSet.getInt("STATUS");

                    orders.add(new Order(id, name, phoneNumber, detailAddress, total, orderDate, customerId, addressId, status));
                }
                return orders;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
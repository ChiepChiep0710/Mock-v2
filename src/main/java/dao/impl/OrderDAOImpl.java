package dao.impl;

import dao.AddressDAO;
import dao.OrderDAO;
import model.Address;
import model.Customer;
import model.Order;
import util.DBUtil;
import util.SQLCOMMAND.OrderSQLCommand;
import util.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private static final List<Order> orders = new ArrayList<>();
    private static final AddressDAO addressDAO = new AddressDAOImpl();

    @Override
    public int create(Order order) {
        try(Connection connection = DBUtil.getInstance().getConnection()){
            System.out.println("-----Enter order information------");
            System.out.println("Enter customer name:");
            String name = Validator.getInstance().validateString();
            int customerID = check(new Customer(),OrderSQLCommand.CHECK_CUSTOMER,name);
            // search customer by id
            String phoneNumber = null;
            System.out.println("Enter address:");
            System.out.println("+ Enter city:");
            String city = Validator.getInstance().validateString();
            System.out.println("+ Enter district:");
            String district = Validator.getInstance().validateString();
            System.out.println("+ Enter subDistrict:");
            String subDistrict = Validator.getInstance().validateString();
            String detailAddress = subDistrict + ", " + district + ", " + city;
            int addressID = check(new Address(), OrderSQLCommand.CHECK_ADDRESS,city,district,subDistrict);
            Address address = addressDAO.searchAddressByID(addressID);
            System.out.println("-----Enter order detail:");
            String choice;
            do{
                //  create order detail
                System.out.println("Do you want continue ?  Y/N");
                choice = Validator.getInstance().validateString();
            }while ("Y".equalsIgnoreCase(choice));
            double total = getTotal(customerID);
            Calendar calendar = Calendar.getInstance();
            java.util.Date orderDate = calendar.getTime();
            int status = 0;
            int result = check(new Order(),OrderSQLCommand.INSERT_ORDER,name,phoneNumber,detailAddress,total,orderDate,
                    status,customerID,addressID);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    private int check(Object object, String SQLCommand, Object ... args){
        try(Connection connection = DBUtil.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQLCommand,Statement.RETURN_GENERATED_KEYS);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement,args);
            if (preparedStatement.executeUpdate() == 0 ){
                if (object instanceof Customer){

                } else if (object instanceof Address) {

                }
                preparedStatement = connection.prepareStatement(SQLCommand,Statement.RETURN_GENERATED_KEYS);
                preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement,args);
            }
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                int id = resultSet.getInt(1);
                return id;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    private double getTotal(int id){
        try (Connection connection = DBUtil.getInstance().getConnection()) {

            String sql = "SELECT TOTAL FROM TOTAL_ORDER WHERE CUSTOMER_ID = " + id;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                double total = resultSet.getDouble(1);
                return total;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

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

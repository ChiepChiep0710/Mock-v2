package dao.impl;

import dao.CustomerDAO;
import model.Customer;
import util.DBUtil;
import util.SQLCOMMAND.CustomerSQLCommand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public int save(Customer customer) {
        try(Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(CustomerSQLCommand.CUSTOMER_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, customer.getFullName(),
                                customer.getEmail(), customer.getPhoneNumber());
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
    public List<Customer> findAll() {
        try(Connection connection = DBUtil.getInstance().getConnection();){
            String sql = "SELECT * FROM CUSTOMER";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Customer> customers = new ArrayList<>();

            while (resultSet.next()){
                int id = resultSet.getInt("CUSTOMER_ID");
                String fullName = resultSet.getString("FULL_NAME");
                String email = resultSet.getString("EMAIL");
                String phoneNumber = resultSet.getString("PHONE_NUMBER");
                int addressId = resultSet.getInt("ADDRESS_ID");
                customers.add(new Customer(id, fullName, email, phoneNumber, addressId));
            }
            return customers;
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public int update(Customer customer) {
        try(Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(CustomerSQLCommand.CUSTOMER_UPDATE);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, customer.getFullName(),
                    customer.getEmail(), customer.getPhoneNumber(), customer.getId());
            if (preparedStatement != null){
                return preparedStatement.executeUpdate();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int customerId) {
        try (Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(CustomerSQLCommand.CUSTOMER_DELETE);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, customerId);

            if(preparedStatement != null){
                return preparedStatement.executeUpdate();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Customer searchById(int customerId) {
        try (Connection connection = DBUtil.getInstance().getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(CustomerSQLCommand.CUSTOMER_SEARCH);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Customer customer = new Customer();
            while (resultSet.next()){
                int id = resultSet.getInt("CUSTOMER_ID");
                String fullName = resultSet.getString("FULL_NAME");
                String email = resultSet.getString("EMAIL");
                String phoneNumber = resultSet.getString("PHONE_NUMBER");
                int addressId = resultSet.getInt("ADDRESS_ID");
                customer =  new Customer(id, fullName, email, phoneNumber, addressId);
            }
            return customer;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

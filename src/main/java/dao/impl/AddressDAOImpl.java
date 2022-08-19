package dao.impl;

import dao.AddressDAO;
import model.Address;
import util.DBUtil;
import util.SQLCOMMAND.AddressSQLCommand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressDAOImpl implements AddressDAO {

    @Override
    public int save(Address address) {
        try(Connection connection = DBUtil.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(AddressSQLCommand.INSERT_ADDRESS);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement,address.getCity(),
                    address.getDistrict(),address.getSub_district(),address.getPostal_code(),address.getDelivery_fee());

            return preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Address address) {
        try(Connection connection = DBUtil.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(AddressSQLCommand.UPDATE_ADDRESS);

            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement,address.getCity(),
                    address.getDistrict(),address.getSub_district(),address.getPostal_code(),address.getDelivery_fee(),address.getId());

            return preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Address address) {
        try(Connection connection = DBUtil.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(AddressSQLCommand.DELETE_ADDRESS);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement,address.getId());
            return preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public List<Address> findAll() {
        try (Connection connection = DBUtil.getInstance().getConnection()) {
            String sql = "SELECT * FROM ADDRESS";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<Address> addresses = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("ADDRESS_ID");
                String city = resultSet.getString("CITY");
                String district = resultSet.getString("DISTRICT");
                String sub_district = resultSet.getString("SUB_DISTRICT");
                String postal_code = resultSet.getString("POSTAL_CODE");
                float delivery_fee = resultSet.getFloat("DELIVERY_FEE");

                addresses.add(new Address(id, city, district, sub_district, postal_code, delivery_fee));
            }

            return addresses;
        } catch (Exception e) {
            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    @Override
    public Address searchAddressByID(int id) {
        try (Connection connection = DBUtil.getInstance().getConnection()) {
            String sql = "SELECT * FROM ADDRESS WHERE ADDRESS_ID = " + id;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                String city = resultSet.getString("CITY");
                String district = resultSet.getString("DISTRICT");
                String sub_district = resultSet.getString("SUB_DISTRICT");
                String postal_code = resultSet.getString("POSTAL_CODE");
                float delivery_fee = resultSet.getFloat("DELIVERY_FEE");

                Address address = new Address(id, city, district, sub_district, postal_code, delivery_fee);
                return address;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

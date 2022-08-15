package dao.impl;

import dao.DiscountDAO;
import model.Discount;
import util.DBUtil;
import util.SQLCOMMAND.DiscountSQLCommand;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountDAOImpl implements DiscountDAO {
    @Override
    public List<Discount> findAllDiscount() {
        try(Connection connection = DBUtil.getInstance().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(DiscountSQLCommand.DISCOUNT_SELECT);

            List<Discount> discounts = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt("DISCOUNT_ID");
                String title = resultSet.getString("TITLE");
                int type = resultSet.getInt("TYPE");
                Double dis = resultSet.getDouble("DISCOUNT");
                Date startDate = resultSet.getDate("START_DATE");
                Date endDate = resultSet.getDate("END_DATE");

                discounts.add(new Discount(id, title, type, dis, startDate, endDate));
            }

            return discounts;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public int createDiscount(Discount discount) {
        try(Connection connection = DBUtil.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DiscountSQLCommand.DISCOUNT_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, discount.getTitle(), discount.getType(), discount.getDiscount(), discount.getStartDate(), discount.getEndDate());

            preparedStatement.executeUpdate();
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

    @Override
    public int deleteDiscount(int id) {
        try (Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(DiscountSQLCommand.DISCOUNT_DELETE);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, id);

            if(preparedStatement != null){
                return preparedStatement.executeUpdate();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateDiscount(Discount discount) {
        try(Connection connection = DBUtil.getInstance().getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(DiscountSQLCommand.DISCOUNT_UPDATE);
            preparedStatement = DBUtil.getInstance().statementBinding(preparedStatement, discount.getTitle(), discount.getType(), discount.getDiscount(), discount.getStartDate(), discount.getEndDate(),  discount.getDiscountId());
            if (preparedStatement != null){
                return preparedStatement.executeUpdate();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}

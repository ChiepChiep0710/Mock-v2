package dao;

import model.Order;

import java.util.List;
import java.util.Map;

public interface OrderDAO {
    int save(Order order);

    int update(Order order);

    int delete(Order order);

    List<Order> findAll();

    Order searchById(int orderId);

    int check(String city, String district, String subDistrict);

    List<Order> findByCustomerId(int customerId);

    int getTotal(int orderID);

    int updateTotal(int orderID, double total);

    Map<Integer,Double> calculateTotalByMonth(int year);
}


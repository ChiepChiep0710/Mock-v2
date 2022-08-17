package dao;

import model.Order;

import java.util.List;

public interface OrderDAO {
    int save(Order order);

    int update(Order order);

    int delete(Order order);

    List<Order> findAll();

    Order searchById(int orderId);

    int check(String city, String district, String subDistrict);

    List<Order> findByCustomerId(int customerId);
}


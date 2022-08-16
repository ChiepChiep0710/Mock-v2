package dao;

import model.Order;

import java.util.List;

public interface OrderDAO {
    int create(Order order);
    int save(Order order);

    int update(Order order);

    int delete(Order order);

    List<Order> findAll();

    Order searchById(int orderId);
}

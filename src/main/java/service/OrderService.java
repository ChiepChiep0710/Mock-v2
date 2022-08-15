package service;

import model.Order;

import java.util.List;

public interface OrderService {

    boolean create(Order order);
    boolean save(Order order);

    boolean update(Order order);

    boolean delete(Order order);

    List<Order> findAll();
}

package dao;

import model.Order;
import model.Product;

import java.util.List;

public interface OrderDAO {
    int save(Order order);

    int update(Order order);

    int delete(Order order);

    List<Order> findAll();

    Order searchById(int orderId);

    int check(String city, String district, String subDistrict);

    List<Order> findByCustomerId(int customerId);

    int updateTotal(int orderID, double total);

    List<Product> findProByOrderId(int orderId);

    List<Order> calculateTotalByMonth(int year);
}


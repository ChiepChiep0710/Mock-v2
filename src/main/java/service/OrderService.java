package service;

import model.Order;

import java.util.List;

public interface OrderService {
    boolean save();

    boolean update();

    boolean delete();

    List<Order> findAll();

    int check(String city, String district, String subDistrict);

    List<Order> calculateTotalByMonth(int year);
}

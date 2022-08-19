package service;

import model.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    boolean save();

    boolean update();

    boolean delete();

    List<Order> findAll();

    int check(String city, String district, String subDistrict);

    Map<Integer,Double> calculateTotalByMonth(int year);
}

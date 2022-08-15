package service;

import model.Discount;

import java.util.List;

public interface DiscountService {
    boolean createDiscount();

    List<Discount> findAllDiscount();

    boolean deleteDiscount(int id);

    boolean updateDiscount(int id);
}

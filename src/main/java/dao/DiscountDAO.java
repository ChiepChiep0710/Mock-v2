package dao;

import model.Discount;

import java.util.List;

public interface DiscountDAO {
    List<Discount> findAllDiscount();

    int createDiscount(Discount discount);

    int deleteDiscount(int id);

    int updateDiscount(Discount discount);
}

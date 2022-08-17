package service;

import model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    boolean save(int orderId);

    boolean update(int cartId);

    boolean delete(int cartId);

    List<OrderDetail> findByOrderId(int orderId);

    OrderDetail searchById(int cartId);
}

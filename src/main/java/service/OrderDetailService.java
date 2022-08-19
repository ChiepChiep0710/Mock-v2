package service;

import model.OrderDetail;

import java.util.Date;
import java.util.List;

public interface OrderDetailService {
    boolean save(int orderId, Date orderDate);

    boolean update(int cartId);

    boolean delete(int cartId);

    List<OrderDetail> findByOrderId(int orderId);

    OrderDetail searchById(int cartId);
}

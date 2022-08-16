package dao;

import model.OrderDetail;

import java.util.List;

public interface OrderDetailDAO {
    int save(OrderDetail orderDetail);
    OrderDetail searchById(int cartId);

    int update(OrderDetail orderDetail);

    int delete(int cartId);

    List<OrderDetail> findByOrderId(int orderId);
}

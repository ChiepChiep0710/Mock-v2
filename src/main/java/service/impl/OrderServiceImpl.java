package service.impl;

import dao.OrderDAO;
import dao.impl.OrderDAOImpl;
import model.Order;
import service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public boolean create(Order order) {
        return false;
    }

    @Override
    public boolean save(Order order) {
        return orderDAO.save(order) > 0;
    }

    @Override
    public boolean update(Order order) {
        return orderDAO.update(order) > 0;
    }

    @Override
    public boolean delete(Order order) {
        return orderDAO.delete(order) > 0;
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }
}

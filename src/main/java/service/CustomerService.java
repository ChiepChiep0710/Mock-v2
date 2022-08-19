package service;

import model.Customer;

import java.util.List;

public interface CustomerService {
    boolean save();
    boolean update(int customerId);
    boolean delete(int customerId);
    Customer searchById(int customerId);
    List<Customer> findAll();
}

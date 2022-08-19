package dao;

import model.Customer;

import java.util.List;

public interface CustomerDAO {
    int save(Customer customer);
    List<Customer> findAll();
    int update(Customer customer);
    int delete(int customerId);
    Customer searchById(int customerId);
}

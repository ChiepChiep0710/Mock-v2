package service.impl;

import dao.CustomerDAO;
import dao.impl.CustomerDAOImpl;
import model.Customer;
import service.CustomerService;
import util.Validators.CustomerValidator;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDAO customerDAO = new CustomerDAOImpl();
//    private AddressDAO addressDAO = new AddressDAOImpl();
    private Scanner scanner = new Scanner(System.in);
    @Override
    public boolean save() {
        Customer customer = new Customer();
        customer.input();
        return customerDAO.save(customer) > 0;
    }

    @Override
    public boolean update(int customerId) {
        List<Customer> customers = findAll();
        List<Customer> customerFilter = customers.stream()
                .filter(customer -> customer.getId() == customerId)
                .collect(Collectors.toList());
        if(customerFilter.size() == 0){
            System.out.println("Customer does not exist!");
            return false;
        }
        Customer customer = customerFilter.get(0);
        String choose;
        System.out.print("Do you want to change full name?(y/n)");
        choose = scanner.nextLine();
        if(choose.equalsIgnoreCase("y")){
            System.out.print("Enter full name: ");
            customer.setFullName(scanner.nextLine());
        }

        System.out.print("Do you want to change email?(y/n)");
        choose = scanner.nextLine();
        if(choose.equalsIgnoreCase("y")){
            System.out.print("Enter email: ");
            customer.setEmail(CustomerValidator.getInstance().validateEmail());
        }

        System.out.print("Do you want to change phone number?(y/n)");
        choose = scanner.nextLine();
        if(choose.equalsIgnoreCase("y")){
            System.out.print("Enter phone number: ");
            customer.setPhoneNumber(CustomerValidator.getInstance().validatePhone());
        }

        return customerDAO.update(customer) > 0;
    }

    @Override
    public boolean delete(int customerId) {
        List<Customer> customers = findAll();
        List<Customer> customerFilter = customers.stream()
                .filter(customer -> customer.getId() == customerId)
                .collect(Collectors.toList());
        if(customerFilter.size() == 0){
            System.out.println("Customer does not exist!");
            return false;
        }

        return customerDAO.delete(customerFilter.get(0).getId()) > 0;
    }

    @Override
    public Customer searchById(int customerId) {
        List<Customer> customers = findAll();
        List<Customer> customerFilter = customers.stream()
                .filter(customer -> customer.getId() == customerId)
                .collect(Collectors.toList());
        if(customerFilter.size() == 0){
            System.out.println("Customer does not exist!");
            return new Customer();
        }

        return customerDAO.searchById(customerId);
    }

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }
}

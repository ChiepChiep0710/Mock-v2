package service.impl;

import dao.CustomerDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import dao.impl.CustomerDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.ProductDAOImpl;
import model.Address;
import model.Customer;
import model.Order;
import model.OrderDetail;
import service.AddressService;
import service.OrderDetailService;
import service.OrderService;
import util.Validator;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private static final OrderDAO orderDAO = new OrderDAOImpl();

    private static final AddressService addressService = new AddressServiceImpl();

    private static final CustomerDAO customerDAO = new CustomerDAOImpl();

    private static final ProductDAO productDAO = new ProductDAOImpl();

    private static final OrderDetailService orderDetailService = new OrderDetailServiceImpl();

    @Override
    public boolean save() {
        System.out.println("-----Enter order information------");
        Order order = orderInput();
        int addressID = order.getAddressID();
        Address address = addressService.searchAddressByID(addressID);
        System.out.println("-----Enter order detail:");
        String choice;
        List<Order> orders = orderDAO.findAll();
        int orderID = orders.get(orders.size()-1).getOrderID()+1;
        do{
            orderDetailService.save(orderID);
            System.out.println("Do you want continue ?  Y/N");
            choice = Validator.getInstance().validateString();
        }while ("Y".equalsIgnoreCase(choice));
        double deliveryFee= address.getDelivery_fee();
        double total = getTotal(orderID)-deliveryFee;
        Calendar calendar = Calendar.getInstance();
        java.util.Date orderDate = calendar.getTime();
        int status = 0;
        order.setTotal(total);
        order.setOrderDate(orderDate);
        order.setStatus(status);
        return orderDAO.save(order) > 0;
    }

    private Order orderInput(){
        System.out.println("Enter customer id:");
        int customerID = Validator.getInstance().validateInteger();
        Customer customer = customerDAO.searchById(customerID);
        String phoneNumber= customer.getPhoneNumber();
        System.out.println("Enter address:");
        System.out.println("+ Enter city:");
        String city = Validator.getInstance().validateString();
        System.out.println("+ Enter district:");
        String district = Validator.getInstance().validateString();
        System.out.println("+ Enter subDistrict:");
        String subDistrict = Validator.getInstance().validateString();
        String detailAddress = subDistrict + ", " + district + ", " + city;
        int addressID = check(city,district,subDistrict);
        if (addressID == 0){
            addressService.save();
            List<Address> addressList = addressService.findAll();
            addressID = addressList.get(addressList.size()-1).getId();
        }
        return new Order(customer.getFullName(),phoneNumber,detailAddress,customerID,addressID);
    }

    @Override
    public int check(String city, String district, String subDistrict) {
        return orderDAO.check(city,district,subDistrict);
    }

    private double getTotal(int orderID) {
        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderID);
        double total = 0;
        for (int i = 0; i < orderDetails.size(); i++) {
            total += orderDetails.get(i).getTotal();
        }
        return total;
    }



    @Override
    public boolean update() {
        System.out.println("-----Update order-----");
        System.out.println("Enter order ID:");
        int orderID = Validator.getInstance().validateInteger();
        List<Order> orders = findAll();
        List<Order> orderList = orders.stream()
                .filter(order -> order.getOrderID() == orderID)
                .collect(Collectors.toList());
        if (orderList.size() == 0){
            System.out.println("Order do not exist!");
            return false;
        }
        Order order = updateChoice(orderList,"customer id","detail order","detail address");
        return orderDAO.update(order) > 0;
    }

    private Order updateChoice(List<Order> orderList,Object ... args){
        Order order = orderList.get(0);
        String choice;
        System.out.println("Do you want to change");
        for (int i = 0; i < args.length; i++) {
            System.out.println("+ " + args[i] + " ? Y/N" );
            choice = Validator.getInstance().validateString();
            if ("Y".equalsIgnoreCase(choice)){
                System.out.print("Enter " + args[i] +" :");
                switch (i){
                    case 0:
                        int customerID = Validator.getInstance().validateInteger();
                        Customer customer = customerDAO.searchById(customerID);
                        String name = customer.getFullName();
                        String phoneNumber = customer.getPhoneNumber();
                        order.setName(name);
                        order.setPhoneNumber(phoneNumber);
                        break;
                    case 1:
                        System.out.println("Enter order detail id:");
                        int cartID = Validator.getInstance().validateInteger();
                        orderDetailService.update(cartID);
                        break;
                    case 2:
                        System.out.println("+ Enter city:");
                        String city = Validator.getInstance().validateString();
                        System.out.println("+ Enter district:");
                        String district = Validator.getInstance().validateString();
                        System.out.println("+ Enter subDistrict:");
                        String subDistrict = Validator.getInstance().validateString();
                        String detailAddress = subDistrict + ", " + district + ", " + city;
                        int addressID = check(city,district,subDistrict);
                        order.setAddressID(addressID);
                        order.setDetailAddress(detailAddress);
                        Address address = addressService.searchAddressByID(addressID);
                        double total = getTotal(order.getOrderID())+address.getDelivery_fee();
                        order.setTotal(total);
                        break;
                }
            }
        }
        return order;
    }

    @Override
    public boolean delete() {
        System.out.println("-----Delete order-----");
        System.out.print("Enter order id:");
        int orderID = Validator.getInstance().validateInteger();
        List<Order> orders = findAll();
        List<Order> orderList = orders.stream()
                .filter(order -> order.getOrderID() == orderID)
                .collect(Collectors.toList());
        if (orderList.size() == 0){
            System.out.println("Order do not exist!");
            return false;
        }
        Order order = orderList.get(0);
        return orderDAO.delete(order) > 0;
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }
}

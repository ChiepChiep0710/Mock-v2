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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import util.Validators.DiscountValidator;

import static java.lang.System.out;

public class OrderServiceImpl implements OrderService {

    private static final OrderDAO orderDAO = new OrderDAOImpl();

    private static final AddressService addressService = new AddressServiceImpl();

    private static final CustomerDAO customerDAO = new CustomerDAOImpl();

    private static final ProductDAO productDAO = new ProductDAOImpl();

    private static final OrderDetailService orderDetailService = new OrderDetailServiceImpl();

    private static final DiscountValidator validator = new DiscountValidator();

    private static final Scanner scanner = new Scanner(System.in);

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
        double deliveryFee= address.getDelivery_fee();
        do{
            orderDetailService.save(orderID,order.getOrderDate());
            System.out.println("Do you want continue ?  Y/N");
            choice = Validator.getInstance().validateString();
        }while ("Y".equalsIgnoreCase(choice));
        double total = getTotal(orderID,deliveryFee);;
        int status = 0;
        order.setTotal(total);
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
        String format = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date orderDate = null;
        while (true) {
            System.out.println("+ Enter order date");
            String day1 = scanner.nextLine();
            boolean isvalid = validator.dateValidator(day1);
            if (isvalid) {
                try {
                    orderDate = dateFormat.parse(day1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                out.println("Invalid order Date requires re-entry!");
            }
        }
        return new Order(customer.getFullName(),phoneNumber,detailAddress, orderDate,customerID,addressID);
    }

    @Override
    public int check(String city, String district, String subDistrict) {
        return orderDAO.check(city,district,subDistrict);
    }

    @Override
    public List<Order> calculateTotalByMonth(int year) {
        return orderDAO.calculateTotalByMonth(year);
    }

    private double getTotal(int orderID, double deliveryFee) {
        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderID);
        double total = 0;
        for (int i = 0; i < orderDetails.size(); i++) {
            total += orderDetails.get(i).getTotal();
        }
        return total+deliveryFee;
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
                System.out.println("Enter " + args[i] +" :");
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
                        List<OrderDetail> orderDetailList = orderDetailService.findByOrderId(order.getOrderID());
                        orderDetailList.forEach(out::println);
                        String choice1 ;
                        do{
                            int choice2;
                            out.println("You want to add or update ? (1:add,2:update)" );
                            choice2 = Validator.getInstance().validateInteger();
                            if (choice2 == 1){
                                orderDetailService.save(order.getOrderID(),order.getOrderDate());
                            }
                            else if (choice2 == 2){
                                System.out.println("Enter order detail id:");
                                int cartID = Validator.getInstance().validateInteger();
                                orderDetailService.update(cartID);
                            }
                            else {
                                out.println("You must type 1 or 2!");
                            }
                            System.out.println("You want to continue ? Y/N");
                            choice1 = Validator.getInstance().validateString();
                        }while ("y".equalsIgnoreCase(choice1));
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
                        if (addressID == 0){
                            addressService.save();
                            List<Address> addressList = addressService.findAll();
                            addressID = addressList.get(addressList.size()-1).getId();
                        }
                        order.setAddressID(addressID);
                        order.setDetailAddress(detailAddress);
                        Address address = addressService.searchAddressByID(addressID);
                        double total = getTotal(order.getOrderID(),address.getDelivery_fee());
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

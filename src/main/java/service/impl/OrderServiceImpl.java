package service.impl;

import dao.CustomerDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import dao.impl.CustomerDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.ProductDAOImpl;
import model.*;
import service.AddressService;
import service.OrderDetailService;
import service.OrderService;
import util.Validator;
import util.Validators.DiscountValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
        boolean result = orderDAO.save(order) > 0;
        if (result == false ) {
            out.println("Create order fail");
            return false;
        }
        List<Order> orders = orderDAO.findAll();
        int orderID  = orders.get(orders.size()-1).getOrderID();
        boolean check = true;
        String choice;
        System.out.println("-----Enter order detail:");
        do{
            check = orderDetailService.save(orderID,order.getOrderDate());
            if (check = false) {
                out.println("Create order detail fail");
            }
            System.out.println("Do you want continue ?  Y/N");
            choice = Validator.getInstance().validateString();
        }while ("Y".equalsIgnoreCase(choice));
        int addressID = order.getAddressID();
        Address address = addressService.searchAddressByID(addressID);
        double deliveryFee= address.getDelivery_fee();
        double total = orderDAO.getTotal(orderID)+deliveryFee;
        order.setTotal(total);
        boolean resultUpdateTotal = orderDAO.updateTotal(orderID,total) > 0;
        if (resultUpdateTotal == false){
            out.println("Update total fail!");
            result = false;
        }
        return result;
    }

    private Order orderInput(){
        System.out.print("Enter customer id: ");
        int customerID = Validator.getInstance().validateInteger();
        Customer customer = customerDAO.searchById(customerID);
        String phoneNumber= customer.getPhoneNumber();
        int addressID = 0;
        String detailAddress = null;
        while (addressID == 0){
            List<String> detailAddressList = detailAddressInput();
            String city = detailAddressList.get(0);
            String district = detailAddressList.get(1);
            String subDistrict = detailAddressList.get(2);
            detailAddress = subDistrict + ", " + district + ", " + city;
            addressID = Integer.parseInt(detailAddressList.get(3));
        }
        Date orderDate = dateInput();
        return new Order(customer.getFullName(),phoneNumber,detailAddress, orderDate,customerID,addressID);
    }

    @Override
    public int check(String city, String district, String subDistrict) {
        return orderDAO.check(city,district,subDistrict);
    }

    @Override
    public Map<Integer,Double> calculateTotalByMonth(int year) {
        return orderDAO.calculateTotalByMonth(year);
    }

//    private double getTotal(int orderID, double deliveryFee) {
//        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderID);
//        double total = 0;
//        for (int i = 0; i < orderDetails.size(); i++) {
//            total += orderDetails.get(i).getTotal();
//        }
//        return total+deliveryFee;
//    }



    @Override
    public boolean update() {
        System.out.println("-----Update order-----");
        System.out.print("Enter order ID: ");
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
                System.out.print("Enter " + args[i] +" : ");
                switch (i){
                    case 0:
                        order = updateChoiceCustomer(order);
                        break;
                    case 1:
                        updateChoiceOrderDetail(order);
                        break;
                    case 2:
                        order = updateChoiceAddress(order);
                        break;
                }
            }
        }
        return order;
    }

    @Override
    public boolean delete() {
        System.out.println("-----Delete order-----");
        System.out.print("Enter order id: ");
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
        List<Order> orderList = orderDAO.findAll();
        out.println("----------List order----------");
        for (Order order : orderList){
            out.println("Order id: " + order.getOrderID());
            out.println("Customer:\t\t\t " + order.getName());
            out.println("Phone number:\t\t " + order.getPhoneNumber());
            out.println("Detail address:\t\t " + order.getDetailAddress());
            out.println("Order date:\t\t\t " + order.getOrderDate());
            out.println("Total:\t\t\t\t " + order.getTotal());
            out.println("");
            List<OrderDetail> orderDetailList = orderDetailService.findByOrderId(order.getOrderID());
            out.printf("%-20s%-20s%-20s%-20s\n","Product","Price","Quantity","Total");
            for (OrderDetail orderDetail : orderDetailList){
                Product product = productDAO.searchById(orderDetail.getProductId());
                out.printf("%-20s%-20.2f%-20d%-20.2f\n",product.getName(),product.getDiscount_price(),orderDetail.getQuantity(),orderDetail.getTotal());
            }
        }
        return orderDAO.findAll();
    }

    public Date dateInput(){
        String format = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date orderDate = null;
        while (true) {
            System.out.print("+ Enter order date: ");
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
        return orderDate;
    }

    public List<String> detailAddressInput(){
        List<String> list = new ArrayList<>();
        System.out.println("Enter address: ");
        System.out.print("+ Enter city: ");
        String city = Validator.getInstance().validateString();
        System.out.print("+ Enter district: ");
        String district = Validator.getInstance().validateString();
        System.out.print("+ Enter subDistrict: ");
        String subDistrict = Validator.getInstance().validateString();
        list.add(city);
        list.add(district);
        list.add(subDistrict);
        int addressID = check(city,district,subDistrict);
        if (addressID == 0){
            out.println("You want to create new address ? Y/N");
            String choice = Validator.getInstance().validateString();
            if ("y".equalsIgnoreCase(choice)){
                addressService.save();
                List<Address> addressList = addressService.findAll();
                addressID = addressList.get(addressList.size()-1).getId();
            }
        }
        list.add(String.valueOf(addressID));
        return list;
    }

    public void updateChoiceOrderDetail(Order order){
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
                System.out.print("Enter order detail id: ");
                int cartID = Validator.getInstance().validateInteger();

                orderDetailService.update(cartID);
            }
            else {
                out.println("You must type 1 or 2!");
            }
            System.out.println("You want to continue ? Y/N");
            choice1 = Validator.getInstance().validateString();
        }while ("y".equalsIgnoreCase(choice1));

    }

    public Order updateChoiceAddress(Order order){
        List<String> detailAddressList = detailAddressInput();
        String city = detailAddressList.get(0);
        String district = detailAddressList.get(1);
        String subDistrict = detailAddressList.get(2);
        String detailAddress = subDistrict + ", " + district + ", " + city;
        int addressID = Integer.parseInt(detailAddressList.get(3));
        order.setAddressID(addressID);
        order.setDetailAddress(detailAddress);
        Address address = addressService.searchAddressByID(addressID);
        double total = orderDAO.getTotal(order.getOrderID())+address.getDelivery_fee();
        order.setTotal(total);
        return order;
    }

    public Order updateChoiceCustomer(Order order){
        int customerID = Validator.getInstance().validateInteger();
        Customer customer = customerDAO.searchById(customerID);
        String name = customer.getFullName();
        String phoneNumber = customer.getPhoneNumber();
        order.setName(name);
        order.setPhoneNumber(phoneNumber);
        return order;
    }
}

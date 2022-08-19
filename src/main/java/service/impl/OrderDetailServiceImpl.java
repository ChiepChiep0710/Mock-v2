package service.impl;

import dao.DiscountDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.impl.DiscountDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderDetailDAOImpl;
import dao.impl.ProductDAOImpl;
import model.Discount;
import model.Order;
import model.OrderDetail;
import model.Product;
import service.OrderDetailService;
import util.Validators.OrderDetailValidator;
import util.Validators.ProductValidator;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();
    private ProductDAO productDAO = new ProductDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private DiscountDAO discountDAO = new DiscountDAOImpl();
    private Scanner scanner = new Scanner(System.in);
    @Override
    public boolean save(int orderId, Date orderDate) {
        System.out.print("Enter product id: ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        Product product = productDAO.searchById(productId);
        if(product == null){
            System.out.println("Product not exists!");
            return false;
        }
        if(product.getStatus() == 0){
            System.out.println("The product is out of stock!");
            return false;
        }
        System.out.print("Enter quantity: ");
        int quantity = ProductValidator.getInstance().validateInteger();
        while(quantity > product.getStock()){
            System.out.println("Quantity more than stock! Retype: ");
            quantity = ProductValidator.getInstance().validateInteger();
        }
        product.setStock(product.getStock() - quantity);
        product.setSold(product.getSold() + quantity);
        productDAO.update(product);

        Double total = 0.0;
        Discount discount = discountDAO.searchById(product.getDiscountId());
        if(discount == null){
            total = product.getPrice() * quantity;
        } else{
            if(OrderDetailValidator.getInstance().checkDateDiscount(orderDate, discount.getStartDate(), discount.getEndDate())){
                total = product.getDiscount_price() *  quantity;
            } else {
                total = product.getPrice() * quantity;
            }
        }
        OrderDetail orderDetail = new OrderDetail(quantity, total, orderId, productId);
        return orderDetailDAO.save(orderDetail) > 0;
    }

    @Override
    public boolean update(int cartId) {
        OrderDetail orderDetail = orderDetailDAO.searchById(cartId);
        if(orderDetail == null){
            System.out.println("Order detail not exists!");
            return false;
        }
        Product product = productDAO.searchById(orderDetail.getProductId());
        String choice;
        System.out.println("Do you want to change quantity?(y/n)");
        choice = scanner.nextLine();
        if(choice.equalsIgnoreCase("y")){
            product.setStock(product.getStock() + orderDetail.getQuantity());
            product.setSold(product.getSold() - orderDetail.getQuantity());
            System.out.print("Enter quantity: ");
            int quantity = ProductValidator.getInstance().validateInteger();
            while(quantity > product.getStock()){
                System.out.println("Quantity more than stock! Retype: ");
                quantity = ProductValidator.getInstance().validateInteger();
            }
            orderDetail.setQuantity(quantity);

            product.setStock(product.getStock() - quantity);
            product.setSold(product.getSold() + quantity);
            productDAO.update(product);

            Double total = 0.0;
            Discount discount = discountDAO.searchById(product.getDiscountId());
            Order order = orderDAO.searchById(orderDetail.getOrderId());
            if(discount == null){
                total = product.getPrice() * quantity;
            } else{
                if(OrderDetailValidator.getInstance().checkDateDiscount(order.getOrderDate(), discount.getStartDate(), discount.getEndDate())){
                    total = product.getDiscount_price() *  quantity;
                } else {
                    total = product.getPrice() * quantity;
                }
            }

            orderDetail.setTotal(total);
        }

        return orderDetailDAO.update(orderDetail) > 0;
    }

    @Override
    public boolean delete(int cartId) {
        OrderDetail orderDetail = orderDetailDAO.searchById(cartId);
        if(orderDetail == null){
            System.out.println("Order detail not exists!");
            return false;
        }

        return orderDetailDAO.delete(cartId) > 0;
    }

    @Override
    public List<OrderDetail> findByOrderId(int orderId) {
        return orderDetailDAO.findByOrderId(orderId);
    }

    @Override
    public OrderDetail searchById(int cartId) {
        return orderDetailDAO.searchById(cartId);
    }
}

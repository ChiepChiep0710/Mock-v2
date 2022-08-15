package model;

import java.util.Date;

public class Order {
    private int orderID;
    private String name;
    private String phoneNumber;
    private String detailAddress;
    private double total;
    private Date orderDate;

    private int status;
    private int customerID;
    private int addressID;

    public Order() {
    }

    public Order(String name, String phoneNumber, String detailAddress, double total, Date orderDate, int status) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.detailAddress = detailAddress;
        this.total = total;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Order(String name, String phoneNumber, String detailAddress, double total, Date orderDate, int status, int customerID, int addressID) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.detailAddress = detailAddress;
        this.total = total;
        this.orderDate = orderDate;
        this.status = status;
        this.customerID = customerID;
        this.addressID = addressID;
    }

    public Order(int orderID, String name, String phoneNumber, String detailAddress, double total, Date orderDate, int status, int customerID, int addressID) {
        this.orderID = orderID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.detailAddress = detailAddress;
        this.total = total;
        this.orderDate = orderDate;
        this.status = status;
        this.customerID = customerID;
        this.addressID = addressID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", total=" + total +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", customerID=" + customerID +
                ", addressID=" + addressID +
                '}';
    }
}

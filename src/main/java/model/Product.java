package model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private int ProductId;
    private String Name;
    private String Description;
    private Double Price;
    private Double Discount_price;
    private int Stock;
    private int Sold;
    private Date Create_date;
    private int Status;
    private int discountId;

    public Product() {
    }
    public Product(int productId, String name, String description, Double price, Double discount_price, int stock, int sold, Date create_date, int status) {
        ProductId = productId;
        Name = name;
        Description = description;
        Price = price;
        Discount_price = discount_price;
        Stock = stock;
        Sold = sold;
        Create_date = create_date;
        Status = status;
    }
    public Product(int productId, String name, String description, Double price, Double discount_price, int stock, int sold, Date create_date, int status, int discountId) {
        ProductId = productId;
        Name = name;
        Description = description;
        Price = price;
        Discount_price = discount_price;
        Stock = stock;
        Sold = sold;
        Create_date = create_date;
        Status = status;
        this.discountId = discountId;
    }

    public Product(String name, String description, Double price, Double discount_price, int stock, int sold, Date create_date, int status) {
        Name = name;
        Description = description;
        Price = price;
        Discount_price = discount_price;
        Stock = stock;
        Sold = sold;
        Create_date = create_date;
        Status = status;
    }

    public Product(String name, String description, Double price, Double discount_price, int stock, int sold, Date create_date, int status, int discountId) {
        Name = name;
        Description = description;
        Price = price;
        Discount_price = discount_price;
        Stock = stock;
        Sold = sold;
        Create_date = create_date;
        Status = status;
        this.discountId = discountId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Double getDiscount_price() {
        return Discount_price;
    }

    public void setDiscount_price(Double discount_price) {
        Discount_price = discount_price;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public int getSold() {
        return Sold;
    }

    public void setSold(int sold) {
        Sold = sold;
    }

    public Date getCreate_date() {
        return Create_date;
    }

    public void setCreate_date(Date create_date) {
        Create_date = create_date;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductId=" + ProductId +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Price=" + Price +
                ", Discount_price=" + Discount_price +
                ", Stock=" + Stock +
                ", Sold=" + Sold +
                ", Create_date=" + Create_date +
                ", Status=" + Status +
                ", discountId=" + discountId +
                '}';
    }
}

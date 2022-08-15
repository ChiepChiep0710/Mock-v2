package model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private int ProductId;
    private String Name;
    private String Description ;
    private BigDecimal Price;
    private BigDecimal Discount_price;
    private int Stock;
    private int Sold;

    public int getSold() {
        return Sold;
    }

    public void setSold(int sold) {
        Sold = sold;
    }

    private Date Create_date;

    public Product(int productId, String name, String description, BigDecimal price, BigDecimal discount_price, int stock, int sold, Date create_date, int status) {
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

    public Product(String name, String description, BigDecimal price, BigDecimal discount_price, int stock, int sold, Date create_date, int status) {
        Name = name;
        Description = description;
        Price = price;
        Discount_price = discount_price;
        Stock = stock;
        Sold = sold;
        Create_date = create_date;
        Status = status;
    }

    private  int Status;

    @Override
    public String toString() {
        return "Product{" +
                "ProductId=" + ProductId +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Price=" + Price +
                ", Discount_price=" + Discount_price +
                ", Stock=" + Stock +
                ", Create_date=" + Create_date +
                ", Status=" + Status +
                '}';
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

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }

    public BigDecimal getDiscount_price() {
        return Discount_price;
    }

    public void setDiscount_price(BigDecimal discount_price) {
        Discount_price = discount_price;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
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

    public Product(int productId, String name, String description, BigDecimal price, BigDecimal discount_price, int stock, Date create_date, int status) {
        ProductId = productId;
        Name = name;
        Description = description;
        Price = price;
        Discount_price = discount_price;
        Stock = stock;
        Create_date = create_date;
        Status = status;
    }
}

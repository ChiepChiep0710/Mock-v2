package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Discount {
    private int discountId;
    private String title;
    private int type;
    private Double discount;
    private Date startDate;
    private Date endDate;
    public Discount(int id) {
        this.discountId = id;
    }

    public Discount() {
    }

    public Discount(String title, int type, Double discount, Date startDate, Date endDate) {
        this.title = title;
        this.type = type;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Discount(int discountId, String title, int type, Double discount, Date startDate, Date endDate) {
        this.discountId = discountId;
        this.title = title;
        this.type = type;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        var format = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return "Discount{" +
                "discountId=" + discountId +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", discount=" + discount +
                ", startDate=" + dateFormat.format(startDate) +
                ", endDate=" + dateFormat.format(endDate) +
                '}';
    }
}

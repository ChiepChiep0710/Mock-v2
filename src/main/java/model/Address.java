package model;

public class Address {
    private int id;
    private String city;
    private String district;
    private String sub_district;
    private String postal_code;
    private double delivery_fee;

    public Address() {
    }

    public Address(String city, String district, String sub_district, String postal_code, double delivery_fee) {
        this.city = city;
        this.district = district;
        this.sub_district = sub_district;
        this.postal_code = postal_code;
        this.delivery_fee = delivery_fee;
    }

    public Address(int id, String city, String district, String sub_district, String postal_code, double delivery_fee) {
        this.id = id;
        this.city = city;
        this.district = district;
        this.sub_district = sub_district;
        this.postal_code = postal_code;
        this.delivery_fee = delivery_fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSub_district() {
        return sub_district;
    }

    public void setSub_district(String sub_district) {
        this.sub_district = sub_district;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public double getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(double delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", sub_district='" + sub_district + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", delivery_fee=" + delivery_fee +
                '}';
    }

    public void display(){
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n",id,city,district,sub_district,postal_code,delivery_fee);
    }
}

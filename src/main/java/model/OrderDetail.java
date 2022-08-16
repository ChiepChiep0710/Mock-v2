package model;

public class OrderDetail {
    private int cartId;
    private int quantity;
    private Double total;
    private int orderId;
    private int productId;

    public OrderDetail() {
    }

    public OrderDetail(int cartId, int quantity, Double total, int orderId, int productId) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.total = total;
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderDetail(int quantity, Double total, int orderId, int productId) {
        this.quantity = quantity;
        this.total = total;
        this.orderId = orderId;
        this.productId = productId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "cartId=" + cartId +
                ", quantity=" + quantity +
                ", total=" + total +
                ", orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }
}

package util.SQLCOMMAND;

public class OrderDetailSQLCommand {
    public static final String ORDER_DETAIL_INSERT = "INSERT INTO ORDER_DETAIL (QUANTITY, TOTAL, ORDER_ID, PRODUCT_ID) VALUES (?, ?, ?, ?)";

    public static final String ORDER_DETAIL_UPDATE = "UPDATE ORDER_DETAIL SET QUANTITY = ?, TOTAL = ? WHERE CART_ID = ?";

    public static final String ORDER_DETAIL_DELETE = "DELETE FROM ORDER_DETAIL WHERE CART_ID = ?";

    public static final String ORDER_DETAIL_SELECT = "SELECT * FROM ORDER_DETAIL WHERE ORDER_ID = ?";

    public static final String ORDER_DETAIL_SEARCH_BY_ID = "SELECT * FROM ORDER_DETAIL WHERE CART_ID = ?";
}

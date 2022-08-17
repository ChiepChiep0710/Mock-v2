package util.SQLCOMMAND;

public class OrderSQLCommand {
    public static final String INSERT_ORDER = "INSERT INTO [ORDER] (NAME, PHONE_NUMBER, DETAIL_ADDRESS, " +
            "TOTAL, ORDER_DATE, STATUS, CUSTOMER_ID, ADDRESS_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_ORDER= "UPDATE [ORDER] SET NAME = ?, PHONE_NUMBER = ?, DETAIL_ADDRESS = ?," +
            " TOTAL = ?, ORDER_DATE = ?, STATUS = ?, CUSTOMER_ID = ?, ADDRESS_ID = ? WHERE ORDER_ID = ?";
    public static final String DELETE_ORDER = "DELETE FROM [ORDER] WHERE ORDER_ID = ?";

    public static final String CHECK_CUSTOMER = "SELECT * FROM CUSTOMER WHERE FULL_NAME = ?";

    public static final String CHECK_ADDRESS = "SELECT * FROM ADDRESS WHERE SUB_DISTRICT = ?";

    public static final String GET_TOTAL = "SELECT * FROM TOTAL_ORDER WHERE CUSTOMER_ID = ?";

    public static final String SEARCH_CUSTOMER_BY_ID = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?";
    public static final String ORDER_SEARCH_BY_ID = "SELECT * FROM [ORDER] WHERE ORDER_ID = ?";
    public static final String ORDER_SEARCH_BY_CUSTOMER_ID = "SELECT * FROM [ORDER] WHERE CUSTOMER_ID = ?";
}

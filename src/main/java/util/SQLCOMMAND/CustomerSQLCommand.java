package util.SQLCOMMAND;

public class CustomerSQLCommand {
    public static final String CUSTOMER_INSERT = "INSERT INTO CUSTOMER(FULL_NAME, EMAIL, PHONE_NUMBER) VALUES(?, ?, ?)";
    public static final String CUSTOMER_UPDATE = "UPDATE CUSTOMER SET FULL_NAME = ?, EMAIL = ?, PHONE_NUMBER = ? WHERE CUSTOMER_ID = ?";
    public static final String CUSTOMER_DELETE = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = ?";
    public static final String CUSTOMER_SEARCH = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?";
}

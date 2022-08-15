package util;

public class DiscountSQLCommand {
    public static final String DISCOUNT_INSERT = "INSERT INTO DISCOUNT(TITLE, TYPE, DISCOUNT, START_DATE, END_DATE) VALUES(?, ?, ?, ?, ?)";
    public static final String DISCOUNT_UPDATE = "UPDATE DISCOUNT SET TITLE = ?, TYPE = ?, DISCOUNT = ?, START_DATE = ?, END_DATE = ? WHERE DISCOUNT_ID = ?";
    public static final String DISCOUNT_SELECT = "SELECT * FROM DISCOUNT";
    public static final String DISCOUNT_DELETE = "DELETE FROM DISCOUNT WHERE DISCOUNT_ID = ?";
}

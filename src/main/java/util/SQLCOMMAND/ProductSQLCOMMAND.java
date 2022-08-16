package util.SQLCOMMAND;

public class ProductSQLCOMMAND {
    public static final String PRODUCT_INSERT_WITHOUT_DISCOUNT = "INSERT INTO PRODUCT(NAME, DESCRIPTION, PRICE, DISCOUNT_PRICE, STOCK, SOLD, CREATE_DATE, STATUS) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String PRODUCT_INSERT_WITH_DISCOUNT = "INSERT INTO PRODUCT(NAME, DESCRIPTION, PRICE, STOCK, SOLD, CREATE_DATE, STATUS, DISCOUNT_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String PRODUCT_UPDATE_WITHOUT_DISCOUNT = "UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?, PRICE = ?, STOCK = ?, SOLD= ?, CREATE_DATE = ?, STATUS = ?  WHERE PRODUCT_ID = ?";
    public static final String PRODUCT_UPDATE_WITH_DISCOUNT = "UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?, PRICE = ?, STOCK = ?, SOLD= ?, CREATE_DATE = ?, STATUS = ?, DISCOUNT_ID = ?  WHERE PRODUCT_ID = ?";
    public static final String PRODUCT_DELETE = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ?";
    public static final String PRODUCT_SELECT = "SELECT * FROM PRODUCT";
    public static final String PRODUCT_SEARCH_BY_ID = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = ?";
    public static final String PRODUCT_CAL_DISCOUNT_PRICE = "UPDATE PRODUCT\n" +
            "SET DISCOUNT_PRICE =\t(SELECT CASE \n" +
            "\t\t\t\t\t\t\t\tWHEN TYPE = 0 THEN p.PRICE* ( 1- d.DISCOUNT/100)\n" +
            "\t\t\t\t\t\t\t\tWHEN TYPE = 1 THEN p.PRICE - d.DISCOUNT\n" +
            "\t\t\t\t\t\t\t\tELSE 0\n" +
            "\t\t\t\t\t\t\tEND\n" +
            "\t\t\t\t\t\tFROM PRODUCT p JOIN DISCOUNT d ON p.DISCOUNT_ID = d.DISCOUNT_ID)\n" +
            "WHERE PRODUCT_ID = ?";
}

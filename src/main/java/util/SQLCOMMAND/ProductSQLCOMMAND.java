package util.SQLCOMMAND;

public class ProductSQLCOMMAND {
    public static final String Product_INSERT = "INSERT INTO Products(Name, Description, Price, Discount_Price, Stock, Sold, Create_date, Status) VALUES(?, ?, ?, ?,?,?,?,?)";
    public static final String Product_UPDATE = "UPDATE CUSTOMER SET Name = ?, Description = ?, Price = ?, Discount_Price = ?, Stock = ?, Sold= ?,  Create_date= ?,Status = ?  WHERE ProductID = ?";
    public static final String Product_DELETE = "DELETE FROM Products WHERE ProductID = ?";
    public static final String Product_findall = "SELECT * FROM Products";
}

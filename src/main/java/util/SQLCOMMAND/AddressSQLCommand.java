package util.SQLCOMMAND;

public class AddressSQLCommand {
    public static final String INSERT_ADDRESS = "INSERT INTO ADDRESS(CITY, DISTRICT, SUB_DISTRICT, " +
            "POSTAL_CODE, DELIVERY_FEE) VALUES(?, ?, ?, ?, ?)";
    public static final String UPDATE_ADDRESS = "UPDATE ADDRESS SET CITY = ?, DISTRICT = ?, SUB_DISTRICT = ?," +
            " POSTAL_CODE = ?, DELIVERY_FEE = ? WHERE ADDRESS_ID = ?";
    public static final String DELETE_ADDRESS = "DELETE FROM ADDRESS WHERE ADDRESS_ID = ?";
}

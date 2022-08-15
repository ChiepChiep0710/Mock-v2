package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

public class DiscountDBUtil {
    private static DiscountDBUtil instance;

    public static DiscountDBUtil getInstance(){
        return instance = instance != null ? instance : new DiscountDBUtil();
    }

    public Connection getConnection(){
        try{
            String url = "jdbc:mysql://localhost:3306/MOCK_PROJECT";
            String username = "root";
            String password = "020120002099";

            return DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public PreparedStatement statementBindingDiscount(PreparedStatement preparedStatement, Object ... args) {
        try {
            for (int i = 0; i < args.length; i++) {
                if(args[i] instanceof  String){
                    preparedStatement.setString(i+1, (String) args[i]);
                }else if(args[i] instanceof  Integer){
                    preparedStatement.setInt(i+1, (Integer) args[i]);
                }else if(args[i] instanceof  Double){
                    preparedStatement.setDouble(i+1, (Double) args[i]);
                }else if(args[i] instanceof Date){
                   preparedStatement.setDate(i+1, new java.sql.Date(((Date) args[i]).getTime()));
                } else {
                    System.out.println("MISSING TYPE: "+args[i].getClass());
                }
            }
        }catch (Exception e){
            e.printStackTrace();

            return null;
        }
        return preparedStatement;
    }


}

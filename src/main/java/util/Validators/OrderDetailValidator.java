package util.Validators;

import java.util.Date;

public class OrderDetailValidator {
    private static OrderDetailValidator instance;

    public static OrderDetailValidator getInstance(){
        return instance == null ? new OrderDetailValidator() : instance;
    }

    public boolean checkDateDiscount(Date dateO, Date dateS, Date dateE){
        if(dateO.compareTo(dateS) >= 0 && dateO.compareTo(dateE) <= 0){
            return true;
        } else{
            return false;
        }
    }
<<<<<<< HEAD
=======

>>>>>>> d893c3d46c5cfcaf3afa97c6a9ef6401a7b85a9b
}

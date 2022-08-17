package util.Validators;

public class OrderValidator {
    private static OrderValidator instance;

    public static OrderValidator getInstance(){
        return instance == null ? new OrderValidator() : instance;
    }


}

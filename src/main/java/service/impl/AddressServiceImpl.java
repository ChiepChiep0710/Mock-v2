package service.impl;

import dao.AddressDAO;
import dao.impl.AddressDAOImpl;
import model.Address;
import service.AddressService;
import util.Validator;
import util.Validators.AddressValidator;

import java.util.List;
import java.util.stream.Collectors;

public class AddressServiceImpl implements AddressService {

    private final AddressDAO addressDAO = new AddressDAOImpl();

    @Override
    public boolean save() {
        System.out.println("----Input address information------");
        System.out.println("Enter city:");
        String city = Validator.getInstance().validateString();
        System.out.println("Enter district:");
        String district = Validator.getInstance().validateString();
        System.out.println("Enter sub district:");
        String subDistrict = Validator.getInstance().validateString();
        System.out.println("Enter postal code:");
        String postalCode = AddressValidator.getInstance().validatePostalCode();
        System.out.println("Enter delivery fee:");
        double deliveryFee = Validator.getInstance().validateDouble();
        Address address = new Address(city, district, subDistrict, postalCode, deliveryFee);
        return addressDAO.save(address) > 0;
    }

    @Override
    public boolean update() {
        System.out.println("-----Update address-----");
        System.out.print("Enter address id:");
        int addressID = Validator.getInstance().validateInteger();
        List<Address> addresses = findAll();
        List<Address> addressList = addresses.stream()
                .filter(address -> address.getId() == addressID)
                .collect(Collectors.toList());
        if (addressList.size() == 0){
            System.out.println("Address do not exist!");
            return false;
        }
        Address address = updateChoice(addressList,"city","district","sub district","postal code","delivery fee");
        return addressDAO.update(address) > 0;
    }

    private Address updateChoice(List<Address> addressList,Object ... args){
        Address address = addressList.get(0);
        String choice;
        System.out.println("Do you want to change");
        for (int i = 0; i < args.length; i++) {
            System.out.println("+ " + args[i] + " ? Y/N" );
            choice = Validator.getInstance().validateString();
            if ("Y".equalsIgnoreCase(choice)){
                System.out.print("Enter " + args[i] +" :");
                switch (i){
                    case 0:
                        address.setCity(Validator.getInstance().validateString());
                        break;
                    case 1:
                        address.setDistrict(Validator.getInstance().validateString());
                        break;
                    case 2:
                        address.setSub_district(Validator.getInstance().validateString());
                        break;
                    case 3:
                        address.setPostal_code(Validator.getInstance().validatePostalCode());
                        break;
                    case 4:
                        address.setDelivery_fee(Validator.getInstance().validateDouble());
                        break;
                }
            }
        }
        return address;
    }

    @Override
    public boolean delete() {
        System.out.println("-----Delete address-----");
        System.out.print("Enter address id:");
        int addressID = Validator.getInstance().validateInteger();
        List<Address> addresses = findAll();
        List<Address> addressList = addresses.stream()
                .filter(address -> address.getId() == addressID)
                .collect(Collectors.toList());
        if (addressList.size() == 0){
            System.out.println("Address do not exist!");
            return false;
        }
        Address address = addressList.get(0);
        return addressDAO.delete(address) > 0;
    }

    @Override
    public List<Address> findAll() {
        return addressDAO.findAll();
    }

    @Override
    public Address searchAddressByID(int addressID) {
        Address address = addressDAO.searchAddressByID(addressID);
        if (address == null){
            System.out.println("Address do not exist!");
            return null;
        }
        return address;
    }

}
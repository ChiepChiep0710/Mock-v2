package service;

import model.Address;

import java.util.List;

public interface AddressService {
    boolean save();

    boolean update();

    boolean delete();

    List<Address> findAll();

    Address searchAddressByID(int addressID);
}

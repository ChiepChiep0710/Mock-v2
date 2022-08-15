package service;

import model.Address;

import java.util.List;

public interface AddressService {
    boolean save(Address address);

    boolean update(Address address);

    boolean delete(Address address);

    List<Address> findAll();

    Address searchAddressByID(int id);
}

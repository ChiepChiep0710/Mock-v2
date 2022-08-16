package service.impl;

import dao.AddressDAO;
import dao.impl.AddressDAOImpl;
import model.Address;
import service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    private AddressDAO addressDAO = new AddressDAOImpl();

    @Override
    public boolean save(Address address) {
        return addressDAO.save(address) > 0;
    }

    @Override
    public boolean update(Address address) {
        return addressDAO.update(address) > 0;
    }

    @Override
    public boolean delete(Address address) {
        return addressDAO.delete(address) > 0;
    }

    @Override
    public List<Address> findAll() {
        return addressDAO.findAll();
    }

    @Override
    public Address searchAddressByID(int id) {
        return null;
    }
}

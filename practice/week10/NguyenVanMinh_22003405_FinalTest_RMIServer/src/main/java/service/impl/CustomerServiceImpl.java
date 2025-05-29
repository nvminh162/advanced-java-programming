package service.impl;

import dao.CustomerDAO;
import dao.GenericDAO;
import model.Customer;
import service.CustomerService;

import java.rmi.RemoteException;

public class CustomerServiceImpl extends GenericServiceImpl<Customer, String> implements CustomerService {
    private CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) throws RemoteException {
        super(customerDAO);
        this.customerDAO = customerDAO;
    }
}

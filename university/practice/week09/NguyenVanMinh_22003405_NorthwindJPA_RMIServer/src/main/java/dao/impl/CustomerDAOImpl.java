package dao.impl;

import dao.CustomerDAO;
import jakarta.persistence.EntityManager;
import model.Customer;

public class CustomerDAOImpl extends GenericDAOImpl<Customer, Integer> implements CustomerDAO {
    public CustomerDAOImpl(Class<Customer> clazz) {
        super(clazz);
    }

    public CustomerDAOImpl(EntityManager em, Class<Customer> clazz) {
        super(em, clazz);
    }

    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAOImpl(Customer.class);
        Customer customer = customerDAO.findById(1);
        System.out.println(customer);
    }
}

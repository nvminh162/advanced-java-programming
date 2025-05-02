package dao.impl;

import dao.CustomerDAO;
import jakarta.persistence.EntityManager;
import model.Customer;

public class CustomerDAOImpl extends GenericDAOImpl<Customer, String> implements CustomerDAO {
    public CustomerDAOImpl(Class<Customer> clazz) {
        super(clazz);
    }

    public CustomerDAOImpl(EntityManager em, Class<Customer> clazz) {
        super(em, clazz);
    }
}

package dao;

import model.Customer;
import java.util.List;

public interface CustomerDAO extends GenericDAO<Customer, String> {
    List<Customer> findByName(String name);
}
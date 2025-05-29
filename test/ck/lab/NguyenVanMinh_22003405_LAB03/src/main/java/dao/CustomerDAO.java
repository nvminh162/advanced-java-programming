package dao;

import model.Customer;

import java.util.Map;

public interface CustomerDAO extends GenericDAO<Customer, Integer> {
    // Statistics on the number of customers by each state.
    Map<String, Long> getNumberCustomerByState();

    //  Count the number of orders for each customer.
    Map<Customer, Long> getNumberOfOrdersForEachCustomer();

    //  Delete all customers who have not yet made a purchase.
    int  deleteCustomersWithoutPurchases();
}

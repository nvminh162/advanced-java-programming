package dao;

import model.Order;

import java.time.LocalDate;

public interface OrderDAO extends GenericDAO<Order, Integer> {
    double getTotalAmount(LocalDate orderDate);
}

package dao;

import model.OrderItem;

public interface OrderItemDAO extends GenericDAO<OrderItem, Integer> {
    double calculateOrderTotalById(int orderNumber);
}

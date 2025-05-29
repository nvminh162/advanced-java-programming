package dao.impl;

import dao.OrderItemDAO;
import jakarta.persistence.EntityManager;
import model.OrderItem;

public class OrderItemDAOImpl extends GenericDAOImpl<OrderItem, OrderItem.OrderItemId> implements OrderItemDAO {

    public OrderItemDAOImpl(Class<OrderItem> clazz) {
        super(clazz);
    }

    public OrderItemDAOImpl(EntityManager em, Class<OrderItem> clazz) {
        super(em, clazz);
    }

    /*public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAOImpl(Product.class);
        OrderDAO orderDAO = new OrderDAOImpl(Order.class);
        OrderItemDAO orderItemDAO = new OrderItemImpl(OrderItem.class);

        Product product = productDAO.findById(4);
        Order order = orderDAO.findById(1);
        OrderItem orderItem = orderItemDAO.findById(new OrderItem.OrderItemId(order, product));
        System.out.println(orderItem);
    }*/
}

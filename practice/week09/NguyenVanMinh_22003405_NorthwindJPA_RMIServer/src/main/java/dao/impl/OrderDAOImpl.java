package dao.impl;

import dao.OrderDAO;
import jakarta.persistence.EntityManager;
import model.Order;

import java.time.LocalDate;

public class OrderDAOImpl extends GenericDAOImpl<Order, Integer> implements OrderDAO {
    public OrderDAOImpl(Class<Order> clazz) {
        super(clazz);
    }

    public OrderDAOImpl(EntityManager em, Class<Order> clazz) {
        super(em, clazz);
    }

    @Override
    public double getTotalAmount(LocalDate orderDate) {
        String jpql = """
                select sum(oi.quantity * (1 - oi.discount) * oi.listPrice)
                from OrderItem oi
                where oi.order.orderDate = :orderDate
                """;
        return em
                .createQuery(jpql, Double.class)
                .setParameter("orderDate", orderDate)
                .getSingleResult();
    }

    /*public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAOImpl(Order.class);
        double total = orderDAO.getTotalAmount(LocalDate.of(2016,1,1));
        System.out.println(total);
    }*/
}

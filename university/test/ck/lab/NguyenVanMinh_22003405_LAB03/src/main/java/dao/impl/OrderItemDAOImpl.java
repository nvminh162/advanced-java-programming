package dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.OrderItem;

public class OrderItemDAOImpl extends GenericDAOImpl<OrderItem, Integer> implements dao.OrderItemDAO {
    public OrderItemDAOImpl(Class<OrderItem> clazz) {
        super(clazz);
    }

    public OrderItemDAOImpl(EntityManager em, Class<OrderItem> clazz) {
        super(em, clazz);
    }

    @Override
    public double calculateOrderTotalById(int orderNumber) {
        String jpql = """
                SELECT SUM((oi.listPrice * oi.quantity) * (1 - oi.discount))
                FROM OrderItem oi
                WHERE oi.order.id = :orderNumber
                """;
        TypedQuery<Double> query = em.createQuery(jpql, Double.class);
        query.setParameter("orderNumber", orderNumber);
        return query.getSingleResult();
    }
}

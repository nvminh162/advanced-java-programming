package dao.impl;

import jakarta.persistence.TypedQuery;
import model.Order;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderDAOImpl extends GenericDAOImpl<Order, Integer> implements dao.OrderDAO {
    public OrderDAOImpl(Class<Order> clazz) {
        super(clazz);
    }

    @Override
    public double totalBillAmountForDay(LocalDate day) {
        String jpql = """
                SELECT SUM(oi.listPrice * oi.quantity * (1 - oi.discount))
                FROM Order o
                LEFT JOIN o.orderItems oi ON oi.order.id = o.id
                WHERE o.orderDate = :day
                """;

        TypedQuery<Double> query = em.createQuery(jpql, Double.class);
        query.setParameter("day", day);
        return query.getSingleResult();
    }

    @Override
    public Map<String, Integer> totalBillsByMonth(Month month) {
        String jpql = "SELECT FUNCTION('YEAR', o.orderDate), COUNT(o) FROM Order o WHERE FUNCTION('MONTH', o.orderDate) = :month GROUP BY FUNCTION('YEAR', o.orderDate)";
        List<Object[]> results = em.createQuery(jpql)
                .setParameter("month", month.getValue())
                .getResultList();
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Object[] row : results) {
            map.put(row[0].toString(), ((Long)row[1]).intValue());
        }
        return map;
    }

    @Override
    public Map<String, Integer> totalBillsByYear(Year year) {
        String jpql = "SELECT FUNCTION('MONTH', o.orderDate), COUNT(o) FROM Order o WHERE FUNCTION('YEAR', o.orderDate) = :year GROUP BY FUNCTION('MONTH', o.orderDate)";
        List<Object[]> results = em.createQuery(jpql)
                .setParameter("year", year.getValue())
                .getResultList();
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Object[] row : results) {
            String month = String.format("%02d", (Integer)row[0]);
            map.put(month, ((Long)row[1]).intValue());
        }
        return map;
    }

}

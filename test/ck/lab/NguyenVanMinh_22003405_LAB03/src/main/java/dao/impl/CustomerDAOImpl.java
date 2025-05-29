package dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Customer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerDAOImpl extends GenericDAOImpl<Customer, Integer> implements dao.CustomerDAO {
    public CustomerDAOImpl(Class<Customer> clazz) {
        super(clazz);
    }

    public CustomerDAOImpl(EntityManager em, Class<Customer> clazz) {
        super(em, clazz);
    }

    @Override
    public Map<String, Long> getNumberCustomerByState() {
        String jpql = """
                SELECT c.address.state, COUNT(DISTINCT c)
                FROM Customer c
                GROUP BY c.address.state
                """;
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query
                .getResultList()
                .stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> (Long) obj[1],
                        (obj1, obj2) -> obj2,
                        LinkedHashMap::new
                ));
    }

    @Override
    public Map<Customer, Long> getNumberOfOrdersForEachCustomer() {
        String jpql = """
                SELECT c, COUNT(DISTINCT o)
                FROM Customer c
                LEFT JOIN Order o ON c.id = o.customer.id
                GROUP BY c
                """;
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query
                .getResultList()
                .stream()
                .collect(Collectors.toMap(
                        obj -> (Customer) obj[0],
                        obj -> (Long) obj[1],
                        (obj1, obj2) -> obj2,
                        LinkedHashMap::new
                ));
    }

    @Override
    public int deleteCustomersWithoutPurchases() {
        String jpql1 = """
                    SELECT c.id
                    FROM Customer c
                    WHERE c.orders IS EMPTY
                """;
        TypedQuery<Integer> query1 = em.createQuery(jpql1, Integer.class); // ✅ sửa Long -> Integer
        List<Integer> customersId = query1.getResultList();

        if (customersId.isEmpty()) return 0;

        String jpql2 = """
                    DELETE FROM Customer c
                    WHERE c.id IN :ids
                """;
        Query query2 = em.createQuery(jpql2);
        query2.setParameter("ids", customersId);
        return query2.executeUpdate();
    }

}

package dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Product;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductDAOImpl extends GenericDAOImpl<Product, Integer> implements dao.ProductDAO {
    public ProductDAOImpl(Class<Product> clazz) {
        super(clazz);
    }

    public ProductDAOImpl(EntityManager em, Class<Product> clazz) {
        super(em, clazz);
    }

    @Override
    public List<Product> getProductsWithHighestPrice() {
        String jpql = """
                SELECT p
                FROM Product p
                WHERE p.listPrice = (
                    SELECT MAX(p2.listPrice)
                    FROM Product p2
                )
                """;
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        return query.getResultList();
    }

    @Override
    public List<Product> productsWithoutSales() {
        String jpql = """
                SELECT p
                FROM Product p
                LEFT JOIN p.orderItems oi
                WHERE oi.product IS NULL
                """;
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        return query.getResultList();
    }

    @Override
    public Map<Product, Long> getTotalQuantityOfEachProductSold() {
        String jpql = """
                SELECT p, SUM(oi.quantity) as totalQuantity
                FROM Product p
                LEFT JOIN p.orderItems oi ON oi.product = p
                GROUP BY p
                ORDER BY totalQuantity DESC
                """;

        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query.getResultList()
                .stream()
                .filter(o -> o[1] != null)
                .collect(Collectors.toMap(
                        obj -> (Product) obj[0],
                        obj -> (Long) obj[1],
                        (obj1, obj2) -> obj2,
                        LinkedHashMap::new
                ));
    }
}

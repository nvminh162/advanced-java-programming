package dao.impl;

import dao.ProductDAO;
import jakarta.persistence.EntityManager;
import model.Product;

import java.util.List;

public class ProductDAOImpl extends GenericDAOImpl<Product, Integer> implements ProductDAO {
    public ProductDAOImpl(Class<Product> clazz) {
        super(clazz);
    }

    public ProductDAOImpl(EntityManager em, Class<Product> clazz) {
        super(em, clazz);
    }

    @Override
    public List<Product> listProductsWithTheHighestPriceSQL() {
        String sql = """
                select * from products p
                where p.list_price = (select max(list_price) from products)
                """;
        return em.createNativeQuery(sql, Product.class).getResultList();
    }

    @Override
    public List<Product> listProductsWithTheHighestPriceJPQL() {
        String jpql = """
                select p from Product p
                where p.listPrice = (select max(p.listPrice) from Product p)
                """;
        return em.createQuery(jpql, Product.class)
                .getResultList();
    }

    @Override
    public List<Product> listProductsNotBeenSold1() {
        String jqpl = """
                select p from Product p
                where SIZE(p.orderItems) = 0
                """;
        return em.createQuery(jqpl, Product.class)
                .getResultList();
    }

    @Override
    public List<Product> listProductsNotBeenSold2() {
        String jpql = """
                select p from Product p
                left join OrderItem  oi on p.id = oi.product.id
                where oi.product.id is null
                """;
        return em.createQuery(jpql, Product.class)
                .getResultList();
    }

    /*public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAOImpl(Product.class);
        productDAO.listProductsWithTheHighestPriceSQL()
                .forEach(System.out::println);
        productDAO.listProductsWithTheHighestPriceJPQL()
                .forEach(System.out::println);
        productDAO.listProductsNotBeenSold1()
                .forEach(System.out::println);
        productDAO.listProductsNotBeenSold2()
                .forEach(System.out::println);
    }*/
}

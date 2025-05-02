package dao.impl;

import dao.CustomerDAO;
import model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.List;

public class CustomerDAOImpl extends GenericDAOImpl<Customer, String> implements CustomerDAO {
    
    @Override
    public List<Customer> findByName(String name) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery(
                "SELECT c FROM Customer c WHERE c.name LIKE :name", 
                Customer.class
            );
            query.setParameter("name", "%" + name + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
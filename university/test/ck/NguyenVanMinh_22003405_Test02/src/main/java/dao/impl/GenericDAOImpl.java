package dao.impl;

import dao.GenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

public class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {
    protected EntityManager em;
    protected Class<T> clazz;

    public GenericDAOImpl(Class<T> clazz) {
        this.em = JPAUtil.getEntityManager();
        this.clazz = clazz;
    }

    public GenericDAOImpl(EntityManager em, Class<T> clazz) {
        this.em = em;
        this.clazz = clazz;
    }

    @Override
    public boolean save(T entity) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(entity);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive()) tr.rollback();
            e.printStackTrace();
        }
        return false;
    }
}
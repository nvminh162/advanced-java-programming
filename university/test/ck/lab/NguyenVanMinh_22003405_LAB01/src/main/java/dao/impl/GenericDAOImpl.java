package dao.impl;

import dao.GenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

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
    public T findById(ID id) {
        return em.find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(T entity) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(entity);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive()) tr.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(ID id) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            T entity = em.find(clazz, id);
            if(entity != null) {
                em.remove(entity);
                tr.commit();
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

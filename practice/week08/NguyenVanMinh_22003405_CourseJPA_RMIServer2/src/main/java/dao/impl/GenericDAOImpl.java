package dao.impl;

import dao.GenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAutil;

import java.util.List;

public class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    protected EntityManager em; //not thread safe, stream - tuan tu
    protected Class<T> clazz;

    public GenericDAOImpl(Class<T> clazz) {
        this.clazz = clazz;
        this.em = JPAutil.getEntityManager();
    }

    public GenericDAOImpl(EntityManager em, Class<T> clazz) {
        this.em = em;
        this.clazz = clazz;
    }

    @Override
    public boolean save(T t) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(t);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(T t) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(t);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(ID id) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            T t = em.find(clazz, id);
            if (t != null) {
                em.remove(t);
                tr.commit();
                return true;
            }
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public T findById(ID id) {
        return em.find(clazz, id);
    }

    @Override
    public List<T> getAll() {
        return em
                .createQuery("from " + clazz.getSimpleName() + " t", clazz)
                .getResultList();
    }
}

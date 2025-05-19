package dao.impl;

import dao.GenericDAO;
import jakarta.persistence.EntityManager;
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
}

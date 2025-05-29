package dao.impl;

import jakarta.persistence.EntityManager;
import model.Store;

public class StoreDAOImpl extends GenericDAOImpl<Store, Integer> implements dao.StoreDAO {
    public StoreDAOImpl(Class<Store> clazz) {
        super(clazz);
    }

    public StoreDAOImpl(EntityManager em, Class<Store> clazz) {
        super(em, clazz);
    }
}

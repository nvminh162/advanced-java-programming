package dao.impl;

import jakarta.persistence.EntityManager;
import model.Brand;

public class BrandDAOImpl extends GenericDAOImpl<Brand, Integer> implements dao.BrandDAO {
    public BrandDAOImpl(Class<Brand> clazz) {
        super(clazz);
    }

    public BrandDAOImpl(EntityManager em, Class<Brand> clazz) {
        super(em, clazz);
    }
}

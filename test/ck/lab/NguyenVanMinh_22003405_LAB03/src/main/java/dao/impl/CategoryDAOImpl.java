package dao.impl;

import jakarta.persistence.EntityManager;
import model.Category;

public class CategoryDAOImpl extends GenericDAOImpl<Category, Integer> implements dao.CategoryDAO {
    public CategoryDAOImpl(Class<Category> clazz) {
        super(clazz);
    }

    public CategoryDAOImpl(EntityManager em, Class<Category> clazz) {
        super(em, clazz);
    }
}

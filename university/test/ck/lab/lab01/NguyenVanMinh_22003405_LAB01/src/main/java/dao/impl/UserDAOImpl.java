package dao.impl;

import dao.UserDAO;
import jakarta.persistence.EntityManager;
import model.User;

public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {
    public UserDAOImpl(Class<User> clazz) {
        super(clazz);
    }

    public UserDAOImpl(EntityManager em, Class<User> clazz) {
        super(em, clazz);
    }
}

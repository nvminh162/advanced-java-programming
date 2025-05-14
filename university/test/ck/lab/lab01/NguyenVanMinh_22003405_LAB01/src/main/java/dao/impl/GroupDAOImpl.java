package dao.impl;

import dao.GroupDAO;
import jakarta.persistence.EntityManager;
import model.Group;

public class GroupDAOImpl extends GenericDAOImpl<Group, Integer> implements GroupDAO {
    public GroupDAOImpl(Class<Group> clazz) {
        super(clazz);
    }

    public GroupDAOImpl(EntityManager em, Class<Group> clazz) {
        super(em, clazz);
    }
}
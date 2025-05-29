package dao.impl;

import jakarta.persistence.EntityManager;
import model.Staff;

public class StaffDAOImpl extends GenericDAOImpl<Staff, Integer> implements dao.StaffDAO {
    public StaffDAOImpl(Class<Staff> clazz) {
        super(clazz);
    }

    public StaffDAOImpl(EntityManager em, Class<Staff> clazz) {
        super(em, clazz);
    }
}

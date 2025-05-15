package dao.impl;

import dao.OfficeAssignmentDAO;
import jakarta.persistence.EntityManager;
import model.OfficeAssignment;

public class OfficeAssignmentDAOImpl extends GenericDAOImpl<OfficeAssignment, Integer> implements OfficeAssignmentDAO {
    public OfficeAssignmentDAOImpl(Class<OfficeAssignment> clazz) {
        super(clazz);
    }

    public OfficeAssignmentDAOImpl(EntityManager em, Class<OfficeAssignment> clazz) {
        super(em, clazz);
    }
}

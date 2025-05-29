package dao.impl;

import dao.OnsiteCourseDAO;
import jakarta.persistence.EntityManager;
import model.OnsiteCourse;

public class OnsiteCourseDAOImpl extends GenericDAOImpl<OnsiteCourse, Integer> implements OnsiteCourseDAO {
    public OnsiteCourseDAOImpl(Class<OnsiteCourse> clazz) {
        super(clazz);
    }

    public OnsiteCourseDAOImpl(EntityManager em, Class<OnsiteCourse> clazz) {
        super(em, clazz);
    }
}

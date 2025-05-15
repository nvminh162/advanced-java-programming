package dao.impl;

import dao.OnlineCourseDAO;
import jakarta.persistence.EntityManager;
import model.OnlineCourse;

public class OnlineCourseImpl extends GenericDAOImpl<OnlineCourse, Integer> implements OnlineCourseDAO {
    public OnlineCourseImpl(Class<OnlineCourse> clazz) {
        super(clazz);
    }

    public OnlineCourseImpl(EntityManager em, Class<OnlineCourse> clazz) {
        super(em, clazz);
    }
}

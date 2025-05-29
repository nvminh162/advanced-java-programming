package dao.impl;

import dao.StudentGradeDAO;
import jakarta.persistence.EntityManager;
import model.StudentGrade;

public class StudentGradeDAOImpl extends GenericDAOImpl<StudentGrade, Integer> implements StudentGradeDAO {
    public StudentGradeDAOImpl(Class<StudentGrade> clazz) {
        super(clazz);
    }

    public StudentGradeDAOImpl(EntityManager em, Class<StudentGrade> clazz) {
        super(em, clazz);
    }
}

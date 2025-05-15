package dao.impl;

import dao.StudentDAO;
import jakarta.persistence.EntityManager;
import model.Student;

import java.util.List;
import java.util.Map;

public class StudentDAOImpl extends GenericDAOImpl<Student, Integer> implements StudentDAO {
    public StudentDAOImpl(Class<Student> clazz) {
        super(clazz);
    }

    public StudentDAOImpl(EntityManager em, Class<Student> clazz) {
        super(em, clazz);
    }

    @Override
    public Map<Student, Double> getAverageScoreOfStudents() {
        return Map.of();
    }

    @Override
    public List<Student> listStudentsStudyingCourseWithHighestScore(String courseName) {
        return List.of();
    }
}

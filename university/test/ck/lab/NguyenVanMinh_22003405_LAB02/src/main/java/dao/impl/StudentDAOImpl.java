package dao.impl;

import dao.StudentDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Department;
import model.Student;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentDAOImpl extends GenericDAOImpl<Student, Integer> implements StudentDAO {
    public StudentDAOImpl(Class<Student> clazz) {
        super(clazz);
    }

    public StudentDAOImpl(EntityManager em, Class<Student> clazz) {
        super(em, clazz);
    }

    @Override
    public Map<Student, Double> getAverageScoreOfStudents() {
        String jpql = """
                SELECT s, AVG(sg.grade)
                FROM Student s
                JOIN s.studentGrades sg
                GROUP BY s
                """;
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query
                .getResultList()
                .stream()
                .filter(o -> o[1] != null)
                .collect(Collectors.toMap(
                        obj -> (Student) obj[0],
                        obj -> (Double) obj[1],
                        (obj1, obj2) -> obj2,
                        LinkedHashMap::new
                ));
    }

    @Override
    public List<Student> listStudentsStudyingCourseWithHighestScore(String departmentName) {
        String jpql = """
                SELECT s
                FROM Student s
                JOIN s.studentGrades sg
                JOIN sg.course c
                JOIN c.department d
                WHERE d.name = :departmentName
                AND sg.grade = (
                    SELECT MAX(sg2.grade)
                    FROM StudentGrade sg2
                    JOIN sg2.course c2
                    JOIN c2.department d2
                    WHERE d2.name = :departmentName
                )
                """;
        TypedQuery<Student> query = em.createQuery(jpql, Student.class);
        query.setParameter("departmentName", departmentName);
        return query.getResultList();
    }
}

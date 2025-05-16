package dao.impl;

import dao.DepartmentDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Department;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentDAOImpl extends GenericDAOImpl<Department, Integer> implements DepartmentDAO {
    public DepartmentDAOImpl(Class<Department> clazz) {
        super(clazz);
    }

    public DepartmentDAOImpl(EntityManager em, Class<Department> clazz) {
        super(em, clazz);
    }

    //Calculate the number of students in each department, the result is decreasing the number of students.
    @Override
    public Map<Department, Long> getNumberOfStudentsByDepartment() {
        String jpql = """
                SELECT d, COUNT(DISTINCT sg.student) as studentCount
                FROM Department d
                JOIN d.courses c
                JOIN c.studentGrade sg
                GROUP BY d
                ORDER BY studentCount DESC
                """;
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query
                .getResultList()
                .stream()
                .collect(Collectors.toMap(
                        obj -> (Department) obj[0],
                        obj -> (Long) obj[1],
                        (obj1, obj2) -> obj2,
                        LinkedHashMap::new
                ));
    }

    @Override
    public List<Department> listDepartmentsWithoutStudents() {
        String jpql = """
                SELECT d
                FROM Department d
                LEFT JOIN d.courses c
                LEFT JOIN c.studentGrade sg
                GROUP BY d
                HAVING COUNT(sg.student) = 0
                """;
        TypedQuery<Department> query = em.createQuery(jpql, Department.class);
        return query.getResultList();
    }
}

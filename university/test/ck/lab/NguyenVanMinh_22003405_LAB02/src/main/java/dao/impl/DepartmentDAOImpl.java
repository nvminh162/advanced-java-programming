package dao.impl;

import dao.DepartmentDAO;
import jakarta.persistence.EntityManager;
import model.Department;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDAOImpl extends GenericDAOImpl<Department, Integer> implements DepartmentDAO {
    public DepartmentDAOImpl(Class<Department> clazz) {
        super(clazz);
    }

    public DepartmentDAOImpl(EntityManager em, Class<Department> clazz) {
        super(em, clazz);
    }

    @Override
    public Map<Department, Long> getNumberOfStudentsByDepartment() {
        String jpql = """
                SELECT d, COUNT(DISTINCT sg.student)
                FROM Department d
                JOIN d.courses c
                JOIN c.studentGrade sg
                GROUP BY d
                ORDER BY COUNT(DISTINCT sg.student) DESC
                """;

        List<Object[]> results = em.createQuery(jpql, Object[].class).getResultList();

        Map<Department, Long> departmentStudentCountMap = new LinkedHashMap<>();
        for (Object[] result : results) {
            Department department = (Department) result[0];
            Long studentCount = (Long) result[1];
            departmentStudentCountMap.put(department, studentCount);
        }

        return departmentStudentCountMap;
    }

    @Override
    public List<Department> listDepartmentsWithoutStudents() {
        return List.of();
    }
}

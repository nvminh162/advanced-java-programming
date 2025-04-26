package dao.impl;

import dao.DepartmentDAO;
import jakarta.persistence.EntityManager;
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

    @Override
    public List<Department> listDepartmentsWithoutStudents() {
        return null;
    }

    @Override
    public Map<Department, Long> getNumberOfStudentsByDepartment() {
        String query = """
                SELECT c.department, count(sg.student) as number
                FROM Course c inner join StudentGrade sg
                ON c.id = sg.course.id
                GROUP BY c.department
                ORDER BY number desc
                """;
        return em.createQuery(query, Object[].class)
                .getResultList()
                .stream() //xu ly tuan tu
                .collect(Collectors.toMap(
                        result -> (Department) result[0],
                        result -> (Long) result[1],
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}

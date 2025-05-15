package dao;

import model.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentDAO extends GenericDAO<Department, Integer> {
    Map<Department, Long> getNumberOfStudentsByDepartment();

    List<Department> listDepartmentsWithoutStudents();
}

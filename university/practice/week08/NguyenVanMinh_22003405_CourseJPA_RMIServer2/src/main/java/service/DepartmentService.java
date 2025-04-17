package service;

import model.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Department> listDepartmentsWithoutStudents();
    Map<Department, Long> getNumberOfStudentsByDepartment ();
}

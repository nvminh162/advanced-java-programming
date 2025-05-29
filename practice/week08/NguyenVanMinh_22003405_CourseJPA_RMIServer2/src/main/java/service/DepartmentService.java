package service;

import model.Department;
import model.Student;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface DepartmentService extends GenericService<Department, Integer> {
    List<Department> listDepartmentsWithoutStudents() throws RemoteException;
    Map<Department, Long> getNumberOfStudentsByDepartment () throws RemoteException;
}

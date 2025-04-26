import service.DepartmentService;
import service.StudentService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class RMIClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();

        StudentService studentService = (StudentService) context.lookup("rmi://paul:3405/studentService");
        DepartmentService departmentService = (DepartmentService) context.lookup("rmi://paul:3405/departmentService");

        departmentService.getNumberOfStudentsByDepartment()
                .entrySet()
                .forEach(entry -> {
                    System.out.println(entry.getKey());
                    System.out.println("Number of students: " + entry.getValue());
                });
        System.out.println("**************************");

        studentService.getAll()
                .forEach(st -> System.out.println(st));
        System.out.println("**************************");

        studentService.getAverageScoreOfStudents()
                .entrySet()
                .forEach(entry -> {
                    System.out.println(entry.getKey());
                    System.out.println("Avg of grades: " + entry.getValue());
                });
        System.out.println("**************************");
    }
}

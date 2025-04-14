package rmi;

import dao.DepartmentDAO;
import dao.StudentDAO;
import dao.impl.DepartmentDAOImpl;
import dao.impl.StudentDAOImpl;
import jakarta.persistence.EntityManager;
import model.Department;
import model.Student;
import service.DepartmentService;
import service.StudentService;
import service.impl.DepartmentServiceImpl;
import service.impl.StudentServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws Exception{

        Context context = new InitialContext();

        LocateRegistry.createRegistry(3405);

        StudentDAO studentDAO = new StudentDAOImpl(Student.class); //studentDAO: Java Object
        DepartmentDAO departmentDAO = new DepartmentDAOImpl(Department.class);

        StudentService studentService = new StudentServiceImpl(studentDAO); //studentService: Java Remote Object
        DepartmentService departmentService = new DepartmentServiceImpl(departmentDAO);

        context.bind("rmi://H51M31:3405/studentService", studentService);
        context.bind("rmi://H51M31:3405/departmentService", departmentService);

        System.out.println("RMI server is running...");

    }
}

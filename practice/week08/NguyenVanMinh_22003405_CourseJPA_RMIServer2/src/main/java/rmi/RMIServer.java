package rmi;

import dao.DepartmentDAO;
import dao.StudentDAO;
import dao.impl.DepartmentDAOImpl;
import dao.impl.StudentDAOImpl;
import model.Department;
import model.Student;
import service.DepartmentService;
import service.StudentService;
import service.impl.DepartmentServiceImpl;
import service.impl.StudentServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();

        LocateRegistry.createRegistry(3405);

        StudentDAO studentDAO = new StudentDAOImpl(Student.class); //studentDAO: Java Object
        DepartmentDAO departmentDAO = new DepartmentDAOImpl(Department.class);

        StudentService studentService = new StudentServiceImpl(studentDAO);
        DepartmentService departmentService = new DepartmentServiceImpl(departmentDAO);

        context.bind("rmi://paul:3405/studentService", studentService); //studentService: Java Remote Object
        context.bind("rmi://paul:3405/departmentService", departmentService);

        System.out.println("RMI Server is running ...");
    }
}
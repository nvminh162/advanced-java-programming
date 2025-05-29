import dao.StudentDAO;
import model.Student;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        StudentDAO studentDAO = (StudentDAO) context.lookup("rmi://paul:3405/studentDAO");
        Student student = studentDAO.findStudentByID(7);
        System.out.println(student);
    }
}

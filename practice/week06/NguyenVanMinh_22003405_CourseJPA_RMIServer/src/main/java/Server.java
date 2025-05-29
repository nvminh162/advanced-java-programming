import dao.StudentDAO;
import dao.impl.StudentDAOImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();

        LocateRegistry.createRegistry(3405);

        StudentDAO studentDAO = new StudentDAOImpl();

        context.bind("rmi://paul:3405/studentDAO", studentDAO);

        System.out.println("Server ready!!!");
    }
}

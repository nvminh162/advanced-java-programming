import dao.StudentDAO;
import dao.impl.StudentDAOImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) throws NamingException, RemoteException {

        Context context = new InitialContext();

        LocateRegistry.createRegistry(5656);

        StudentDAO studentDAO = new StudentDAOImpl();

        context.bind("rmi://H51M15:5656/studentDAO", studentDAO);

        System.out.println("Ready!!!");

    }
}

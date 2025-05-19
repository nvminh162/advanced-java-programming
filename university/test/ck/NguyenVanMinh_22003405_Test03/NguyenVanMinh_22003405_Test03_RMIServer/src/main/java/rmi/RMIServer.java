package rmi;

import dao.BookDAO;
import dao.impl.BookDAOImpl;
import model.Book;
import service.BookService;
import service.impl.BookServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(3405);

        BookDAO bookDAO = new BookDAOImpl(Book.class);
        BookService bookService = new BookServiceImpl(bookDAO);

        context.bind("rmi://paul:3405/bookService", bookService);

        System.out.println("RMI Server Started");
    }
}

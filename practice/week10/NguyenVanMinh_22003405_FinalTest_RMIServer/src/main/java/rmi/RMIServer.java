package rmi;

import dao.CustomerDAO;
import dao.MovieDAO;
import dao.ShowDAO;
import dao.TicketDAO;
import dao.impl.CustomerDAOImpl;
import dao.impl.MovieDAOImpl;
import dao.impl.ShowDAOImpl;
import dao.impl.TicketDAOImpl;
import model.Customer;
import model.Movie;
import model.Show;
import model.Ticket;
import service.CustomerService;
import service.MovieService;
import service.ShowService;
import service.TicketService;
import service.impl.CustomerServiceImpl;
import service.impl.MovieServiceImpl;
import service.impl.ShowServiceImpl;
import service.impl.TicketServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    private final static int PORT = 3405;

    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(PORT);

        CustomerDAO customerDAO = new CustomerDAOImpl(Customer.class);
        MovieDAO movieDAO = new MovieDAOImpl(Movie.class);
        ShowDAO showDAO = new ShowDAOImpl(Show.class);
        TicketDAO ticketDAO = new TicketDAOImpl(Ticket.class);

        CustomerService customerService = new CustomerServiceImpl(customerDAO);
        MovieService movieService = new MovieServiceImpl(movieDAO);
        ShowService showService = new ShowServiceImpl(showDAO);
        TicketService ticketService = new TicketServiceImpl(ticketDAO);

        context.bind("rmi://paul:3405/customerService", customerService);
        context.bind("rmi://paul:3405/movieService", movieService);
        context.bind("rmi://paul:3405/showService", showService);
        context.bind("rmi://paul:3405/ticketService", ticketService);

        System.out.println("RMI Server is running at port " + PORT);
    }
}

package service.impl;

import dao.*;
import dao.impl.*;
import model.Customer;
import model.Movie;
import model.Show;
import model.Ticket;
import service.MovieTicketService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MovieTicketServiceImpl extends UnicastRemoteObject implements MovieTicketService {
    private final CustomerDAO customerDAO;
    private final MovieDAO movieDAO;
    private final ShowDAO showDAO;
    private final TicketDAO ticketDAO;

    public MovieTicketServiceImpl() throws RemoteException {
        super();
        this.customerDAO = new CustomerDAOImpl();
        this.movieDAO = new MovieDAOImpl();
        this.showDAO = new ShowDAOImpl();
        this.ticketDAO = new TicketDAOImpl();
    }

    // Customer operations
    @Override
    public Customer addCustomer(Customer customer) throws RemoteException {
        return customerDAO.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) throws RemoteException {
        return customerDAO.save(customer);
    }

    @Override
    public void deleteCustomer(String customerId) throws RemoteException {
        customerDAO.findById(customerId)
                .ifPresent(customerDAO::delete);
    }

    @Override
    public Customer getCustomerById(String customerId) throws RemoteException {
        return customerDAO.findById(customerId).orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() throws RemoteException {
        return customerDAO.findAll();
    }

    @Override
    public List<Customer> searchCustomersByName(String name) throws RemoteException {
        return customerDAO.findByName(name);
    }

    // Movie operations
    @Override
    public Movie addMovie(Movie movie) throws RemoteException {
        if (!movieDAO.validateMovieId(movie.getId())) {
            throw new RemoteException("Invalid movie ID format. Must start with 'M' followed by at least 3 digits.");
        }
        
        if (movie.getDuration() <= 0) {
            throw new RemoteException("Movie duration must be greater than 0.");
        }
        
        return movieDAO.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) throws RemoteException {
        if (!movieDAO.validateMovieId(movie.getId())) {
            throw new RemoteException("Invalid movie ID format. Must start with 'M' followed by at least 3 digits.");
        }
        
        if (movie.getDuration() <= 0) {
            throw new RemoteException("Movie duration must be greater than 0.");
        }
        
        return movieDAO.save(movie);
    }

    @Override
    public void deleteMovie(String movieId) throws RemoteException {
        movieDAO.findById(movieId)
                .ifPresent(movieDAO::delete);
    }

    @Override
    public Movie getMovieById(String movieId) throws RemoteException {
        return movieDAO.findById(movieId).orElse(null);
    }

    @Override
    public List<Movie> getAllMovies() throws RemoteException {
        return movieDAO.findAll();
    }

    @Override
    public List<Movie> searchMoviesByDirector(String director) throws RemoteException {
        return movieDAO.findByDirectorLike(director);
    }

    // Show operations
    @Override
    public Show addShow(Show show) throws RemoteException {
        return showDAO.save(show);
    }

    @Override
    public Show updateShow(Show show) throws RemoteException {
        Optional<Show> existingShow = showDAO.findById(show.getId());
        if (existingShow.isPresent() && existingShow.get().hasBookings()) {
            throw new RemoteException("Cannot update show that already has bookings");
        }
        return showDAO.save(show);
    }

    @Override
    public void deleteShow(String showId) throws RemoteException {
        showDAO.findById(showId)
                .ifPresent(showDAO::delete);
    }

    @Override
    public Show getShowById(String showId) throws RemoteException {
        return showDAO.findById(showId).orElse(null);
    }

    @Override
    public List<Show> getAllShows() throws RemoteException {
        return showDAO.findAll();
    }

    @Override
    public List<Show> listShowsByCurrentDateAndDirector(String director) throws RemoteException {
        return showDAO.findShowsByCurrentDateAndDirector(director);
    }

    // Ticket operations
    @Override
    public Ticket addTicket(Ticket ticket) throws RemoteException {
        return ticketDAO.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) throws RemoteException {
        return ticketDAO.save(ticket);
    }

    @Override
    public void deleteTicket(String ticketNumber) throws RemoteException {
        ticketDAO.findById(ticketNumber)
                .ifPresent(ticketDAO::delete);
    }

    @Override
    public Ticket getTicketById(String ticketNumber) throws RemoteException {
        return ticketDAO.findById(ticketNumber).orElse(null);
    }

    @Override
    public List<Ticket> getAllTickets() throws RemoteException {
        return ticketDAO.findAll();
    }

    // Business logic methods
    @Override
    public Map<Movie, Double> listTicketSalesByMovieSortedByTitle() throws RemoteException {
        return movieDAO.getTicketSalesByMovieSortedByTitle();
    }
}
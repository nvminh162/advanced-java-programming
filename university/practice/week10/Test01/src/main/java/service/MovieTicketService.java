package service;

import model.Customer;
import model.Movie;
import model.Show;
import model.Ticket;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface MovieTicketService extends Remote {
    // Customer operations
    Customer addCustomer(Customer customer) throws RemoteException;
    Customer updateCustomer(Customer customer) throws RemoteException;
    void deleteCustomer(String customerId) throws RemoteException;
    Customer getCustomerById(String customerId) throws RemoteException;
    List<Customer> getAllCustomers() throws RemoteException;
    List<Customer> searchCustomersByName(String name) throws RemoteException;
    
    // Movie operations
    Movie addMovie(Movie movie) throws RemoteException;
    Movie updateMovie(Movie movie) throws RemoteException;
    void deleteMovie(String movieId) throws RemoteException;
    Movie getMovieById(String movieId) throws RemoteException;
    List<Movie> getAllMovies() throws RemoteException;
    List<Movie> searchMoviesByDirector(String director) throws RemoteException;
    
    // Show operations
    Show addShow(Show show) throws RemoteException;
    Show updateShow(Show show) throws RemoteException;
    void deleteShow(String showId) throws RemoteException;
    Show getShowById(String showId) throws RemoteException;
    List<Show> getAllShows() throws RemoteException;
    List<Show> listShowsByCurrentDateAndDirector(String director) throws RemoteException;
    
    // Ticket operations
    Ticket addTicket(Ticket ticket) throws RemoteException;
    Ticket updateTicket(Ticket ticket) throws RemoteException;
    void deleteTicket(String ticketNumber) throws RemoteException;
    Ticket getTicketById(String ticketNumber) throws RemoteException;
    List<Ticket> getAllTickets() throws RemoteException;
    
    // Business logic methods
    Map<Movie, Double> listTicketSalesByMovieSortedByTitle() throws RemoteException;
}
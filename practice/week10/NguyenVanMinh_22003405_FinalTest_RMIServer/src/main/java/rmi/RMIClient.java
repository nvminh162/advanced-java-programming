package rmi;

import model.Customer;
import model.Movie;
import model.Show;
import service.CustomerService;
import service.MovieService;
import service.ShowService;
import service.TicketService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class RMIClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();

        CustomerService customerService = (CustomerService) context.lookup("rmi://paul:3405/customerService");
        MovieService movieService =  (MovieService) context.lookup("rmi://paul:3405/movieService");
        ShowService showService =  (ShowService) context.lookup("rmi://paul:3405/showService");
        TicketService ticketService =  (TicketService) context.lookup("rmi://paul:3405/ticketService");

        //CRUD
        /*CREATE*/
//        Movie movie1 = new Movie();
//        movie1.setId("M126");
//        movie1.setDirector("Nguyen Van Minh");
//        movie1.setDuration(1);
//        movie1.setGenre("Crime");
//        movie1.setReleaseYear(2025);
//        movie1.setTitle("Java Advance");
//        boolean isSave = movieService.save(movie1);
//        System.out.println("isSave: " + isSave);
//        System.out.println("************************************************");
        /*READ*/
//        Customer customer1 = customerService.findById("C102");
//        System.out.println("customer: " + customer1);
//        System.out.println("************************************************");
        /*UPDATE*/
//        Show show1 = showService.findById("S122");
//        show1.setHallName("Hall 70129999");
//        boolean isUpdate = showService.update(show1);
//        System.out.println("isUpdate: " + isUpdate);
//        System.out.println("************************************************");
        /*DELETE*/
//        boolean isDeleted = movieService.delete("M126");
//        System.out.println(isDeleted);
//        System.out.println("************************************************");
        /*READ ALL*/
        List<Movie> movies = movieService.findAll();
        movies.forEach(System.out::println);
        System.out.println("************************************************");

        //Show
        List<Show> listShowsByCurrentDateAndDirector = showService.listShowsByCurrentDateAndDirector("Christopher Nolan");
        listShowsByCurrentDateAndDirector.forEach(System.out::println);
        System.out.println("************************************************");

        //Movie
        Map<Movie, Double> listTicketSalesByMovieSortedByTitle = movieService.listTicketSalesByMovieSortedByTitle();
        System.out.println("Tựa phim" + " | " + "Doanh số bán vé");
        listTicketSalesByMovieSortedByTitle.forEach((movie, total) -> {
            System.out.println(movie.getTitle() + " | " + total);
        });
        System.out.println("************************************************");
    }
}

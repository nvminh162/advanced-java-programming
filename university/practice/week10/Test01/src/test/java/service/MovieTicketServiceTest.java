package service;

import model.Customer;
import model.Movie;
import model.Show;
import model.Ticket;
import org.junit.jupiter.api.*;
import service.impl.MovieTicketServiceImpl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovieTicketServiceTest {
    
    private static MovieTicketService service;
    
    @BeforeAll
    public static void setUp() throws RemoteException {
        service = new MovieTicketServiceImpl();
        
        // Clean up any existing data and add test data
        setupTestData();
    }
    
    private static void setupTestData() throws RemoteException {
        // Add test customers
        Customer customer1 = new Customer();
        customer1.setId("C001");
        customer1.setName("John Smith");
        customer1.setYearOfBirth(1985);
        customer1.setPhone("0123456789");
        customer1.setAddress("123 Street, City");
        service.addCustomer(customer1);
        
        Customer customer2 = new Customer();
        customer2.setId("C002");
        customer2.setName("Jane Doe");
        customer2.setYearOfBirth(1990);
        customer2.setPhone("0987654321");
        customer2.setAddress("456 Avenue, Town");
        service.addCustomer(customer2);
        
        // Add test movies
        Movie movie1 = new Movie();
        movie1.setId("M123");
        movie1.setTitle("Inception");
        movie1.setGenre("Sci-Fi");
        movie1.setReleaseYear(2010);
        movie1.setDirector("Christopher Nolan");
        movie1.setDuration(148);
        movie1.setActors("Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page");
        service.addMovie(movie1);
        
        Movie movie2 = new Movie();
        movie2.setId("M456");
        movie2.setTitle("The Dark Knight");
        movie2.setGenre("Action");
        movie2.setReleaseYear(2008);
        movie2.setDirector("Christopher Nolan");
        movie2.setDuration(152);
        movie2.setActors("Christian Bale, Heath Ledger, Aaron Eckhart");
        service.addMovie(movie2);
        
        // Add a movie showing today
        Movie movie3 = new Movie();
        movie3.setId("M789");
        movie3.setTitle("Interstellar");
        movie3.setGenre("Sci-Fi");
        movie3.setReleaseYear(2014);
        movie3.setDirector("Christopher Nolan");
        movie3.setDuration(169);
        movie3.setActors("Matthew McConaughey, Anne Hathaway, Jessica Chastain");
        service.addMovie(movie3);
        
        // Add test shows
        Show show1 = new Show();
        show1.setId("S001");
        show1.setShowDateTime(LocalDateTime.now().minusDays(1).withHour(18).withMinute(0));
        show1.setHallName("Hall A");
        show1.setMovie(movie1);
        service.addShow(show1);
        
        Show show2 = new Show();
        show2.setId("S002");
        show2.setShowDateTime(LocalDateTime.now().withHour(20).withMinute(30)); // Today
        show2.setHallName("Hall B");
        show2.setMovie(movie2);
        service.addShow(show2);
        
        Show show3 = new Show();
        show3.setId("S003");
        show3.setShowDateTime(LocalDateTime.now().withHour(15).withMinute(0)); // Today
        show3.setHallName("Hall C");
        show3.setMovie(movie3);
        service.addShow(show3);
        
        // Add test tickets
        Ticket ticket1 = new Ticket();
        ticket1.setTicketNumber("T001");
        ticket1.setSeat("A1");
        ticket1.setType(Ticket.TicketType.VIP);
        ticket1.setPrice(150.0);
        ticket1.setBookingDate(LocalDate.now().minusDays(2));
        ticket1.setCustomer(customer1);
        ticket1.setShow(show1);
        service.addTicket(ticket1);
        
        Ticket ticket2 = new Ticket();
        ticket2.setTicketNumber("T002");
        ticket2.setSeat("B2");
        ticket2.setType(Ticket.TicketType.STANDARD);
        ticket2.setPrice(100.0);
        ticket2.setBookingDate(LocalDate.now().minusDays(1));
        ticket2.setCustomer(customer2);
        ticket2.setShow(show1);
        service.addTicket(ticket2);
        
        Ticket ticket3 = new Ticket();
        ticket3.setTicketNumber("T003");
        ticket3.setSeat("C3");
        ticket3.setType(Ticket.TicketType.VIP);
        ticket3.setPrice(180.0);
        ticket3.setBookingDate(LocalDate.now());
        ticket3.setCustomer(customer1);
        ticket3.setShow(show2);
        service.addTicket(ticket3);
    }
    
    @Test
    @Order(1)
    public void testMovieIdValidation() {
        Movie movie = new Movie();
        movie.setId("ABC123"); // Invalid ID format
        movie.setTitle("Test Movie");
        movie.setGenre("Action");
        movie.setReleaseYear(2023);
        movie.setDirector("Test Director");
        movie.setDuration(120);
        
        assertThrows(RemoteException.class, () -> {
            service.addMovie(movie);
        }, "Should throw RemoteException for invalid movie ID");
    }
    
    @Test
    @Order(2)
    public void testMovieDurationValidation() {
        Movie movie = new Movie();
        movie.setId("M999");
        movie.setTitle("Test Movie");
        movie.setGenre("Action");
        movie.setReleaseYear(2023);
        movie.setDirector("Test Director");
        movie.setDuration(0); // Invalid duration
        
        assertThrows(RemoteException.class, () -> {
            service.addMovie(movie);
        }, "Should throw RemoteException for invalid movie duration");
    }
    
    @Test
    @Order(3)
    public void testAddValidMovie() throws RemoteException {
        Movie movie = new Movie();
        movie.setId("M999");
        movie.setTitle("Test Movie");
        movie.setGenre("Action");
        movie.setReleaseYear(2023);
        movie.setDirector("Test Director");
        movie.setDuration(120);
        
        Movie addedMovie = service.addMovie(movie);
        assertNotNull(addedMovie, "Added movie should not be null");
        assertEquals("M999", addedMovie.getId(), "Movie ID should match");
        assertEquals("Test Movie", addedMovie.getTitle(), "Movie title should match");
    }
    
    @Test
    @Order(4)
    public void testUpdateShowWithBookings() throws RemoteException {
        Show show = service.getShowById("S001");
        assertNotNull(show, "Show should exist");
        
        show.setHallName("Updated Hall");
        
        assertThrows(RemoteException.class, () -> {
            service.updateShow(show);
        }, "Should throw RemoteException when updating show with bookings");
    }
    
    @Test
    @Order(5)
    public void testListShowsByCurrentDateAndDirector() throws RemoteException {
        List<Show> shows = service.listShowsByCurrentDateAndDirector("Nolan");
        
        assertNotNull(shows, "Shows list should not be null");
        assertFalse(shows.isEmpty(), "Shows list should not be empty");
        
        // Verify all shows are on the current date
        LocalDate today = LocalDate.now();
        for (Show show : shows) {
            assertEquals(today, show.getShowDateTime().toLocalDate(), "Show should be on current date");
            assertTrue(show.getMovie().getDirector().contains("Nolan"), "Director should contain 'Nolan'");
        }
    }
    
    @Test
    @Order(6)
    public void testTicketSalesByMovieSortedByTitle() throws RemoteException {
        Map<Movie, Double> salesByMovie = service.listTicketSalesByMovieSortedByTitle();
        
        assertNotNull(salesByMovie, "Sales map should not be null");
        assertFalse(salesByMovie.isEmpty(), "Sales map should not be empty");
        
        // Verify sorting by title
        String previousTitle = null;
        for (Movie movie : salesByMovie.keySet()) {
            if (previousTitle != null) {
                assertTrue(movie.getTitle().compareTo(previousTitle) >= 0, "Movies should be sorted by title");
            }
            previousTitle = movie.getTitle();
        }
        
        // Verify sales values
        double inceptionSales = getSalesForMovie(salesByMovie, "Inception");
        assertEquals(250.0, inceptionSales, 0.01, "Inception sales should be 250.0");
        
        double darkKnightSales = getSalesForMovie(salesByMovie, "The Dark Knight");
        assertEquals(180.0, darkKnightSales, 0.01, "The Dark Knight sales should be 180.0");
    }
    
    private double getSalesForMovie(Map<Movie, Double> salesByMovie, String title) {
        for (Map.Entry<Movie, Double> entry : salesByMovie.entrySet()) {
            if (entry.getKey().getTitle().equals(title)) {
                return entry.getValue();
            }
        }
        return 0.0;
    }
}
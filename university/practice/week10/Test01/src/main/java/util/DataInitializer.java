package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Customer;
import model.Movie;
import model.Show;
import model.Ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility class to initialize the database with sample data
 */
public class DataInitializer {
    private static final Random random = new Random();
    
    public static void initializeData() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            
            // Check if data already exists
            long customerCount = (long) em.createQuery("SELECT COUNT(c) FROM Customer c").getSingleResult();
            
            if (customerCount > 0) {
                System.out.println("Database already contains data. Skipping initialization.");
                tx.commit();
                return;
            }
            
            // Create customers
            List<Customer> customers = createCustomers();
            customers.forEach(em::persist);
            
            // Create movies
            List<Movie> movies = createMovies();
            movies.forEach(em::persist);
            
            // Create shows for each movie
            List<Show> shows = createShows(movies);
            shows.forEach(em::persist);
            
            // Create tickets
            List<Ticket> tickets = createTickets(customers, shows);
            tickets.forEach(em::persist);
            
            tx.commit();
            System.out.println("Sample data initialized successfully!");
            
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    private static List<Customer> createCustomers() {
        List<Customer> customers = new ArrayList<>();
        
        // Customer 1
        Customer customer1 = new Customer();
        customer1.setId("C001");
        customer1.setName("John Smith");
        customer1.setYearOfBirth(1985);
        customer1.setPhone("0123456789");
        customer1.setAddress("123 Main Street, New York");
        customers.add(customer1);
        
        // Customer 2
        Customer customer2 = new Customer();
        customer2.setId("C002");
        customer2.setName("Jane Doe");
        customer2.setYearOfBirth(1990);
        customer2.setPhone("0987654321");
        customer2.setAddress("456 Oak Avenue, Los Angeles");
        customers.add(customer2);
        
        // Customer 3
        Customer customer3 = new Customer();
        customer3.setId("C003");
        customer3.setName("Robert Johnson");
        customer3.setYearOfBirth(1988);
        customer3.setPhone("0555123456");
        customer3.setAddress("789 Pine Road, Chicago");
        customers.add(customer3);
        
        // Customer 4
        Customer customer4 = new Customer();
        customer4.setId("C004");
        customer4.setName("Emily Davis");
        customer4.setYearOfBirth(1995);
        customer4.setPhone("0777888999");
        customer4.setAddress("101 Maple Street, Boston");
        customers.add(customer4);
        
        // Customer 5
        Customer customer5 = new Customer();
        customer5.setId("C005");
        customer5.setName("Michael Brown");
        customer5.setYearOfBirth(1980);
        customer5.setPhone("0444555666");
        customer5.setAddress("202 Cedar Lane, Seattle");
        customers.add(customer5);
        
        return customers;
    }
    
    private static List<Movie> createMovies() {
        List<Movie> movies = new ArrayList<>();
        
        // Movie 1
        Movie movie1 = new Movie();
        movie1.setId("M101");
        movie1.setTitle("Inception");
        movie1.setGenre("Sci-Fi");
        movie1.setReleaseYear(2010);
        movie1.setDirector("Christopher Nolan");
        movie1.setDuration(148);
        movie1.setActors("Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page");
        movies.add(movie1);
        
        // Movie 2
        Movie movie2 = new Movie();
        movie2.setId("M102");
        movie2.setTitle("The Dark Knight");
        movie2.setGenre("Action");
        movie2.setReleaseYear(2008);
        movie2.setDirector("Christopher Nolan");
        movie2.setDuration(152);
        movie2.setActors("Christian Bale, Heath Ledger, Aaron Eckhart");
        movies.add(movie2);
        
        // Movie 3
        Movie movie3 = new Movie();
        movie3.setId("M103");
        movie3.setTitle("The Godfather");
        movie3.setGenre("Crime");
        movie3.setReleaseYear(1972);
        movie3.setDirector("Francis Ford Coppola");
        movie3.setDuration(175);
        movie3.setActors("Marlon Brando, Al Pacino, James Caan");
        movies.add(movie3);
        
        // Movie 4
        Movie movie4 = new Movie();
        movie4.setId("M104");
        movie4.setTitle("Pulp Fiction");
        movie4.setGenre("Crime");
        movie4.setReleaseYear(1994);
        movie4.setDirector("Quentin Tarantino");
        movie4.setDuration(154);
        movie4.setActors("John Travolta, Uma Thurman, Samuel L. Jackson");
        movies.add(movie4);
        
        // Movie 5
        Movie movie5 = new Movie();
        movie5.setId("M105");
        movie5.setTitle("Interstellar");
        movie5.setGenre("Sci-Fi");
        movie5.setReleaseYear(2014);
        movie5.setDirector("Christopher Nolan");
        movie5.setDuration(169);
        movie5.setActors("Matthew McConaughey, Anne Hathaway, Jessica Chastain");
        movies.add(movie5);
        
        return movies;
    }
    
    private static List<Show> createShows(List<Movie> movies) {
        List<Show> shows = new ArrayList<>();
        LocalDate today = LocalDate.now();
        String[] halls = {"Hall A", "Hall B", "Hall C", "VIP Hall", "IMAX Hall"};
        
        int showCounter = 1;
        
        // Create shows for each movie (past, current, and future dates)
        for (Movie movie : movies) {
            // Yesterday's show
            Show yesterdayShow = new Show();
            yesterdayShow.setId("S" + String.format("%03d", showCounter++));
            yesterdayShow.setMovie(movie);
            yesterdayShow.setHallName(halls[random.nextInt(halls.length)]);
            yesterdayShow.setShowDateTime(LocalDateTime.of(today.minusDays(1), LocalTime.of(18, 0)));
            shows.add(yesterdayShow);
            
            // Today's shows (multiple shows for some movies)
            Show todayShow1 = new Show();
            todayShow1.setId("S" + String.format("%03d", showCounter++));
            todayShow1.setMovie(movie);
            todayShow1.setHallName(halls[random.nextInt(halls.length)]);
            todayShow1.setShowDateTime(LocalDateTime.of(today, LocalTime.of(14, 30)));
            shows.add(todayShow1);
            
            if (movie.getDirector().equals("Christopher Nolan")) { // Additional show for Nolan movies
                Show todayShow2 = new Show();
                todayShow2.setId("S" + String.format("%03d", showCounter++));
                todayShow2.setMovie(movie);
                todayShow2.setHallName(halls[random.nextInt(halls.length)]);
                todayShow2.setShowDateTime(LocalDateTime.of(today, LocalTime.of(20, 0)));
                shows.add(todayShow2);
            }
            
            // Tomorrow's show
            Show tomorrowShow = new Show();
            tomorrowShow.setId("S" + String.format("%03d", showCounter++));
            tomorrowShow.setMovie(movie);
            tomorrowShow.setHallName(halls[random.nextInt(halls.length)]);
            tomorrowShow.setShowDateTime(LocalDateTime.of(today.plusDays(1), LocalTime.of(19, 15)));
            shows.add(tomorrowShow);
        }
        
        return shows;
    }
    
    private static List<Ticket> createTickets(List<Customer> customers, List<Show> shows) {
        List<Ticket> tickets = new ArrayList<>();
        int ticketCounter = 1;
        
        // For demonstration, create some tickets for past and today's shows
        for (Show show : shows) {
            LocalDate showDate = show.getShowDateTime().toLocalDate();
            
            // Only create tickets for yesterday's and today's shows
            if (showDate.isEqual(LocalDate.now()) || showDate.isBefore(LocalDate.now())) {
                // Generate between 1 and 3 tickets for each applicable show
                int numTickets = random.nextInt(3) + 1;
                
                for (int i = 0; i < numTickets; i++) {
                    Ticket ticket = new Ticket();
                    ticket.setTicketNumber("T" + String.format("%03d", ticketCounter++));
                    ticket.setSeat(generateRandomSeat());
                    ticket.setType(random.nextBoolean() ? Ticket.TicketType.VIP : Ticket.TicketType.STANDARD);
                    ticket.setPrice(ticket.getType() == Ticket.TicketType.VIP ? 180.0 : 120.0);
                    
                    // Booking date is either on the show date or a few days before
                    ticket.setBookingDate(showDate.minusDays(random.nextInt(5)));
                    
                    // Assign a random customer
                    ticket.setCustomer(customers.get(random.nextInt(customers.size())));
                    
                    // Assign the current show
                    ticket.setShow(show);
                    
                    tickets.add(ticket);
                }
            }
        }
        
        return tickets;
    }
    
    private static String generateRandomSeat() {
        char row = (char) ('A' + random.nextInt(10));
        int number = random.nextInt(20) + 1;
        return row + String.valueOf(number);
    }
    
    public static void main(String[] args) {
        initializeData();
    }
}
import dao.CustomerDAO;
import dao.MovieDAO;
import dao.ShowDAO;
import dao.impl.CustomerDAOImpl;
import dao.impl.MovieDAOImpl;
import dao.impl.ShowDAOImpl;
import model.Customer;
import model.Movie;
import model.Show;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDAO {
    private CustomerDAO customerDAO;
    private ShowDAO showDAO;
    private MovieDAO movieDAO;

    @BeforeAll
    void setup() {
        customerDAO = new CustomerDAOImpl(Customer.class);
        showDAO = new ShowDAOImpl(Show.class);
        movieDAO = new MovieDAOImpl(Movie.class);
    }

    // Test cases for CustomerDAO CRUD operations
    @Test
    @Order(1)
    void testCustomerSave() {
        // Create a new customer
        Customer customer = new Customer();
        customer.setId("22003405");
        customer.setName("Nguyen Van Minh");
        customer.setYearOfBirth(2004);
        customer.setPhone("0353999798");
        customer.setAddress("TP HCM");
        
        boolean result = customerDAO.save(customer);
        
        Assertions.assertTrue(result, "Customer should be saved successfully");
        
        // Verify customer was saved
        Customer savedCustomer = customerDAO.findById("22003405");
        Assertions.assertNotNull(savedCustomer, "Should find the saved customer");
        Assertions.assertEquals("Nguyen Van Minh", savedCustomer.getName());
    }
    
    @Test
    @Order(2)
    void testCustomerFindById() {
        Customer customer = customerDAO.findById("22003405");
        
        Assertions.assertNotNull(customer, "Customer should be found");
        Assertions.assertEquals("22003405", customer.getId());
        Assertions.assertEquals("Nguyen Van Minh", customer.getName());
        Assertions.assertEquals(2004, customer.getYearOfBirth());
    }
    
    @Test
    @Order(3)
    void testCustomerUpdate() {
        // First retrieve the customer
        Customer customer = customerDAO.findById("22003405");
        
        // Update customer information
        customer.setPhone("0353.999.798");
        customer.setAddress("Go Vap, TP HCM");
        
        boolean result = customerDAO.update(customer);
        
        Assertions.assertTrue(result, "Customer should be updated successfully");
        
        // Verify the update
        Customer updatedCustomer = customerDAO.findById("22003405");
        Assertions.assertEquals("0353.999.798", updatedCustomer.getPhone());
        Assertions.assertEquals("Go Vap, TP HCM", updatedCustomer.getAddress());
    }
    
    @Test
    @Order(4)
    void testCustomerFindAll() {
        List<Customer> customers = customerDAO.findAll();
        
        Assertions.assertNotNull(customers, "Customer list should not be null");
        Assertions.assertFalse(customers.isEmpty(), "Customer list should not be empty");
    }
    
    @Test
    @Order(5)
    void testCustomerDelete() {
        boolean result = customerDAO.delete("22003405");
        
        Assertions.assertTrue(result, "Customer should be deleted successfully");
        
        // Verify customer was deleted
        Customer deletedCustomer = customerDAO.findById("22003405");
        Assertions.assertNull(deletedCustomer, "Customer should no longer exist after deletion");
    }
    
    // Test for ShowDAO - listShowsByCurrentDateAndDirector
    @Test
    @Order(6)
    void testListShowsByCurrentDateAndDirector() {
        // The ShowDAOImpl uses a hardcoded date (2024-05-11) for testing
        List<Show> shows = showDAO.listShowsByCurrentDateAndDirector("Christopher Nolan");
        
        Assertions.assertNotNull(shows, "Shows list should not be null");
        
        // Since we're working with test data, the assertion depends on your database
        // We're expecting shows directed by Christopher Nolan on May 11, 2024
        shows.forEach(show -> {
            Assertions.assertNotNull(show.getMovie(), "Show should have a movie");
            Assertions.assertTrue(show.getMovie().getDirector().contains("Christopher Nolan"), 
                "Director should be Christopher Nolan");
            
            LocalDateTime showDate = show.getShowDateTime();
            Assertions.assertNotNull(showDate, "Show date should not be null");
            Assertions.assertEquals(11, showDate.getDayOfMonth(), "Show should be on the 11th");
            Assertions.assertEquals(5, showDate.getMonthValue(), "Show should be in May");
        });
    }
    
    // Test for MovieDAO - listTicketSalesByMovieSortedByTitle
    @Test
    @Order(7)
    void testListTicketSalesByMovieSortedByTitle() {
        Map<Movie, Double> salesByMovie = movieDAO.listTicketSalesByMovieSortedByTitle();
        
        Assertions.assertNotNull(salesByMovie, "Sales map should not be null");
        Assertions.assertFalse(salesByMovie.isEmpty(), "Sales map should not be empty");
        
        // Verify the keys are sorted by movie title
        String previousTitle = null;
        for (Movie movie : salesByMovie.keySet()) {
            if (previousTitle != null) {
                Assertions.assertTrue(movie.getTitle().compareTo(previousTitle) >= 0, 
                    "Movies should be sorted by title");
            }
            previousTitle = movie.getTitle();
            
            // Verify each movie has a valid sales amount
            Double sales = salesByMovie.get(movie);
            Assertions.assertNotNull(sales, "Sales amount should not be null");
            Assertions.assertTrue(sales >= 0, "Sales amount should be non-negative");
        }
    }

    @AfterAll
    void tearDown() {
        customerDAO = null;
        showDAO = null;
        movieDAO = null;
    }
}

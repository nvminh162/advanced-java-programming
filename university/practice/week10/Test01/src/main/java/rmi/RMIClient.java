package rmi;

import model.Customer;
import model.Movie;
import model.Show;
import model.Ticket;
import service.MovieTicketService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RMIClient {
    // Using last 4 digits of MSSV as port number - replace with your actual MSSV
    private static final int PORT = 1234; // Change to your last 4 digits of MSSV
    private static final String SERVICE_NAME = "MovieTicketService";
    
    private static MovieTicketService service;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            // Get the hostname from user or use localhost as default
            System.out.print("Enter server hostname (or press Enter for localhost): ");
            String hostName = scanner.nextLine().trim();
            if (hostName.isEmpty()) {
                hostName = "localhost";
            }
            
            // Connect to the RMI registry
            Registry registry = LocateRegistry.getRegistry(hostName, PORT);
            
            // Look up the service
            service = (MovieTicketService) registry.lookup(SERVICE_NAME);
            
            System.out.println("Connected to MovieTicketService on " + hostName + ":" + PORT);
            
            // Display menu and handle user input
            boolean exit = false;
            while (!exit) {
                displayMainMenu();
                int choice = getIntInput("Enter your choice: ");
                
                switch (choice) {
                    case 1:
                        handleCustomerOperations();
                        break;
                    case 2:
                        handleMovieOperations();
                        break;
                    case 3:
                        handleShowOperations();
                        break;
                    case 4:
                        handleTicketOperations();
                        break;
                    case 5:
                        displayTicketSalesByMovie();
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            
            System.out.println("Thank you for using the Movie Ticket Booking System!");
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
    private static void displayMainMenu() {
        System.out.println("\n===== MOVIE TICKET BOOKING SYSTEM =====");
        System.out.println("1. Customer Operations");
        System.out.println("2. Movie Operations");
        System.out.println("3. Show Operations");
        System.out.println("4. Ticket Operations");
        System.out.println("5. View Ticket Sales by Movie");
        System.out.println("0. Exit");
        System.out.println("======================================");
    }
    
    private static void handleCustomerOperations() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== CUSTOMER OPERATIONS =====");
            System.out.println("1. Add New Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Delete Customer");
            System.out.println("4. Find Customer by ID");
            System.out.println("5. List All Customers");
            System.out.println("6. Search Customers by Name");
            System.out.println("0. Back to Main Menu");
            System.out.println("============================");
            
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1:
                        addCustomer();
                        break;
                    case 2:
                        updateCustomer();
                        break;
                    case 3:
                        deleteCustomer();
                        break;
                    case 4:
                        findCustomerById();
                        break;
                    case 5:
                        listAllCustomers();
                        break;
                    case 6:
                        searchCustomersByName();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    
    private static void handleMovieOperations() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MOVIE OPERATIONS =====");
            System.out.println("1. Add New Movie");
            System.out.println("2. Update Movie");
            System.out.println("3. Delete Movie");
            System.out.println("4. Find Movie by ID");
            System.out.println("5. List All Movies");
            System.out.println("6. Search Movies by Director");
            System.out.println("0. Back to Main Menu");
            System.out.println("=========================");
            
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1:
                        addMovie();
                        break;
                    case 2:
                        updateMovie();
                        break;
                    case 3:
                        deleteMovie();
                        break;
                    case 4:
                        findMovieById();
                        break;
                    case 5:
                        listAllMovies();
                        break;
                    case 6:
                        searchMoviesByDirector();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    
    private static void handleShowOperations() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== SHOW OPERATIONS =====");
            System.out.println("1. Add New Show");
            System.out.println("2. Update Show");
            System.out.println("3. Delete Show");
            System.out.println("4. Find Show by ID");
            System.out.println("5. List All Shows");
            System.out.println("6. List Shows for Today by Director");
            System.out.println("0. Back to Main Menu");
            System.out.println("========================");
            
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1:
                        addShow();
                        break;
                    case 2:
                        updateShow();
                        break;
                    case 3:
                        deleteShow();
                        break;
                    case 4:
                        findShowById();
                        break;
                    case 5:
                        listAllShows();
                        break;
                    case 6:
                        listShowsByCurrentDateAndDirector();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    
    private static void handleTicketOperations() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== TICKET OPERATIONS =====");
            System.out.println("1. Add New Ticket");
            System.out.println("2. Update Ticket");
            System.out.println("3. Delete Ticket");
            System.out.println("4. Find Ticket by Number");
            System.out.println("5. List All Tickets");
            System.out.println("0. Back to Main Menu");
            System.out.println("==========================");
            
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1:
                        addTicket();
                        break;
                    case 2:
                        updateTicket();
                        break;
                    case 3:
                        deleteTicket();
                        break;
                    case 4:
                        findTicketByNumber();
                        break;
                    case 5:
                        listAllTickets();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    
    // Customer operations implementations
    private static void addCustomer() throws Exception {
        System.out.println("\n----- Adding New Customer -----");
        
        String id = getStringInput("Enter customer ID: ");
        String name = getStringInput("Enter customer name: ");
        int yearOfBirth = getIntInput("Enter year of birth: ");
        String phone = getStringInput("Enter phone number: ");
        String address = getStringInput("Enter address: ");
        
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setYearOfBirth(yearOfBirth);
        customer.setPhone(phone);
        customer.setAddress(address);
        
        Customer addedCustomer = service.addCustomer(customer);
        System.out.println("Customer added successfully: " + addedCustomer.getName());
    }
    
    private static void updateCustomer() throws Exception {
        System.out.println("\n----- Updating Customer -----");
        
        String id = getStringInput("Enter customer ID to update: ");
        Customer customer = service.getCustomerById(id);
        
        if (customer == null) {
            System.out.println("Customer not found with ID: " + id);
            return;
        }
        
        System.out.println("Current customer details: ");
        displayCustomer(customer);
        
        String name = getStringInput("Enter new name (or press Enter to keep current): ");
        if (!name.isEmpty()) {
            customer.setName(name);
        }
        
        String yearStr = getStringInput("Enter new year of birth (or press Enter to keep current): ");
        if (!yearStr.isEmpty()) {
            customer.setYearOfBirth(Integer.parseInt(yearStr));
        }
        
        String phone = getStringInput("Enter new phone number (or press Enter to keep current): ");
        if (!phone.isEmpty()) {
            customer.setPhone(phone);
        }
        
        String address = getStringInput("Enter new address (or press Enter to keep current): ");
        if (!address.isEmpty()) {
            customer.setAddress(address);
        }
        
        Customer updatedCustomer = service.updateCustomer(customer);
        System.out.println("Customer updated successfully: " + updatedCustomer.getName());
    }
    
    private static void deleteCustomer() throws Exception {
        System.out.println("\n----- Deleting Customer -----");
        
        String id = getStringInput("Enter customer ID to delete: ");
        Customer customer = service.getCustomerById(id);
        
        if (customer == null) {
            System.out.println("Customer not found with ID: " + id);
            return;
        }
        
        System.out.println("Customer to be deleted: ");
        displayCustomer(customer);
        
        String confirm = getStringInput("Are you sure you want to delete this customer? (y/n): ");
        if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
            service.deleteCustomer(id);
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Customer deletion cancelled.");
        }
    }
    
    private static void findCustomerById() throws Exception {
        System.out.println("\n----- Finding Customer by ID -----");
        
        String id = getStringInput("Enter customer ID: ");
        Customer customer = service.getCustomerById(id);
        
        if (customer != null) {
            System.out.println("Customer found:");
            displayCustomer(customer);
        } else {
            System.out.println("No customer found with ID: " + id);
        }
    }
    
    private static void listAllCustomers() throws Exception {
        System.out.println("\n----- All Customers -----");
        
        List<Customer> customers = service.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.forEach(RMIClient::displayCustomer);
        }
    }
    
    private static void searchCustomersByName() throws Exception {
        System.out.println("\n----- Searching Customers by Name -----");
        
        String name = getStringInput("Enter customer name to search: ");
        List<Customer> customers = service.searchCustomersByName(name);
        
        if (customers.isEmpty()) {
            System.out.println("No customers found with name containing: " + name);
        } else {
            System.out.println("Found " + customers.size() + " customers:");
            customers.forEach(RMIClient::displayCustomer);
        }
    }
    
    // Movie operations implementations
    private static void addMovie() throws Exception {
        System.out.println("\n----- Adding New Movie -----");
        
        String id = getStringInput("Enter movie ID (must start with 'M' followed by at least 3 digits): ");
        String title = getStringInput("Enter movie title: ");
        String genre = getStringInput("Enter movie genre: ");
        int releaseYear = getIntInput("Enter release year: ");
        String director = getStringInput("Enter director: ");
        int duration = getIntInput("Enter duration in minutes (must be greater than 0): ");
        String actors = getStringInput("Enter actors (comma-separated): ");
        Set<String> actorSet = new HashSet<>(Arrays.asList(actors.split(",\\s*")));
        
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setReleaseYear(releaseYear);
        movie.setDirector(director);
        movie.setDuration(duration);
        movie.setActors(actorSet);
        
        Movie addedMovie = service.addMovie(movie);
        System.out.println("Movie added successfully: " + addedMovie.getTitle());
    }
    
    private static void updateMovie() throws Exception {
        System.out.println("\n----- Updating Movie -----");
        
        String id = getStringInput("Enter movie ID to update: ");
        Movie movie = service.getMovieById(id);
        
        if (movie == null) {
            System.out.println("Movie not found with ID: " + id);
            return;
        }
        
        System.out.println("Current movie details: ");
        displayMovie(movie);
        
        String title = getStringInput("Enter new title (or press Enter to keep current): ");
        if (!title.isEmpty()) {
            movie.setTitle(title);
        }
        
        String genre = getStringInput("Enter new genre (or press Enter to keep current): ");
        if (!genre.isEmpty()) {
            movie.setGenre(genre);
        }
        
        String releaseYearStr = getStringInput("Enter new release year (or press Enter to keep current): ");
        if (!releaseYearStr.isEmpty()) {
            movie.setReleaseYear(Integer.parseInt(releaseYearStr));
        }
        
        String director = getStringInput("Enter new director (or press Enter to keep current): ");
        if (!director.isEmpty()) {
            movie.setDirector(director);
        }
        
        String durationStr = getStringInput("Enter new duration in minutes (or press Enter to keep current): ");
        if (!durationStr.isEmpty()) {
            movie.setDuration(Integer.parseInt(durationStr));
        }
        
        String actors = getStringInput("Enter new actors (comma-separated) (or press Enter to keep current): ");
        if (!actors.isEmpty()) {
            Set<String> actorSet = new HashSet<>(Arrays.asList(actors.split(",\\s*")));
            movie.setActors(actorSet);
        }
        
        Movie updatedMovie = service.updateMovie(movie);
        System.out.println("Movie updated successfully: " + updatedMovie.getTitle());
    }
    
    private static void deleteMovie() throws Exception {
        System.out.println("\n----- Deleting Movie -----");
        
        String id = getStringInput("Enter movie ID to delete: ");
        Movie movie = service.getMovieById(id);
        
        if (movie == null) {
            System.out.println("Movie not found with ID: " + id);
            return;
        }
        
        System.out.println("Movie to be deleted: ");
        displayMovie(movie);
        
        String confirm = getStringInput("Are you sure you want to delete this movie? (y/n): ");
        if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
            service.deleteMovie(id);
            System.out.println("Movie deleted successfully.");
        } else {
            System.out.println("Movie deletion cancelled.");
        }
    }
    
    private static void findMovieById() throws Exception {
        System.out.println("\n----- Finding Movie by ID -----");
        
        String id = getStringInput("Enter movie ID: ");
        Movie movie = service.getMovieById(id);
        
        if (movie != null) {
            System.out.println("Movie found:");
            displayMovie(movie);
        } else {
            System.out.println("No movie found with ID: " + id);
        }
    }
    
    private static void listAllMovies() throws Exception {
        System.out.println("\n----- All Movies -----");
        
        List<Movie> movies = service.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("No movies found.");
        } else {
            movies.forEach(RMIClient::displayMovie);
        }
    }
    
    private static void searchMoviesByDirector() throws Exception {
        System.out.println("\n----- Searching Movies by Director -----");
        
        String director = getStringInput("Enter director name to search: ");
        List<Movie> movies = service.searchMoviesByDirector(director);
        
        if (movies.isEmpty()) {
            System.out.println("No movies found directed by: " + director);
        } else {
            System.out.println("Found " + movies.size() + " movies:");
            movies.forEach(RMIClient::displayMovie);
        }
    }
    
    // Show operations implementations
    private static void addShow() throws Exception {
        System.out.println("\n----- Adding New Show -----");
        
        // First, list all movies for reference
        System.out.println("Available movies:");
        listAllMovies();
        
        String id = getStringInput("Enter show ID: ");
        String movieId = getStringInput("Enter movie ID for this show: ");
        
        Movie movie = service.getMovieById(movieId);
        if (movie == null) {
            System.out.println("Movie not found with ID: " + movieId);
            return;
        }
        
        // For simplicity, we'll use a basic format for date/time input
        String dateTimeStr = getStringInput("Enter show date and time (yyyy-MM-dd HH:mm): ");
        java.time.LocalDateTime showDateTime = java.time.LocalDateTime.parse(dateTimeStr.replace(" ", "T"));
        
        String hallName = getStringInput("Enter hall name: ");
        
        Show show = new Show();
        show.setId(id);
        show.setShowDateTime(showDateTime);
        show.setHallName(hallName);
        show.setMovie(movie);
        
        Show addedShow = service.addShow(show);
        System.out.println("Show added successfully for movie: " + movie.getTitle());
    }
    
    private static void updateShow() throws Exception {
        System.out.println("\n----- Updating Show -----");
        
        String id = getStringInput("Enter show ID to update: ");
        Show show = service.getShowById(id);
        
        if (show == null) {
            System.out.println("Show not found with ID: " + id);
            return;
        }
        
        System.out.println("Current show details: ");
        displayShow(show);
        
        String movieIdStr = getStringInput("Enter new movie ID (or press Enter to keep current): ");
        if (!movieIdStr.isEmpty()) {
            Movie movie = service.getMovieById(movieIdStr);
            if (movie == null) {
                System.out.println("Movie not found with ID: " + movieIdStr);
                return;
            }
            show.setMovie(movie);
        }
        
        String dateTimeStr = getStringInput("Enter new show date and time (yyyy-MM-dd HH:mm) (or press Enter to keep current): ");
        if (!dateTimeStr.isEmpty()) {
            show.setShowDateTime(java.time.LocalDateTime.parse(dateTimeStr.replace(" ", "T")));
        }
        
        String hallName = getStringInput("Enter new hall name (or press Enter to keep current): ");
        if (!hallName.isEmpty()) {
            show.setHallName(hallName);
        }
        
        try {
            Show updatedShow = service.updateShow(show);
            System.out.println("Show updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating show: " + e.getMessage());
        }
    }
    
    private static void deleteShow() throws Exception {
        System.out.println("\n----- Deleting Show -----");
        
        String id = getStringInput("Enter show ID to delete: ");
        Show show = service.getShowById(id);
        
        if (show == null) {
            System.out.println("Show not found with ID: " + id);
            return;
        }
        
        System.out.println("Show to be deleted: ");
        displayShow(show);
        
        String confirm = getStringInput("Are you sure you want to delete this show? (y/n): ");
        if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
            service.deleteShow(id);
            System.out.println("Show deleted successfully.");
        } else {
            System.out.println("Show deletion cancelled.");
        }
    }
    
    private static void findShowById() throws Exception {
        System.out.println("\n----- Finding Show by ID -----");
        
        String id = getStringInput("Enter show ID: ");
        Show show = service.getShowById(id);
        
        if (show != null) {
            System.out.println("Show found:");
            displayShow(show);
        } else {
            System.out.println("No show found with ID: " + id);
        }
    }
    
    private static void listAllShows() throws Exception {
        System.out.println("\n----- All Shows -----");
        
        List<Show> shows = service.getAllShows();
        if (shows.isEmpty()) {
            System.out.println("No shows found.");
        } else {
            shows.forEach(RMIClient::displayShow);
        }
    }
    
    private static void listShowsByCurrentDateAndDirector() throws Exception {
        System.out.println("\n----- Shows Today by Director -----");
        
        String director = getStringInput("Enter director name: ");
        List<Show> shows = service.listShowsByCurrentDateAndDirector(director);
        
        if (shows.isEmpty()) {
            System.out.println("No shows found today for director: " + director);
        } else {
            System.out.println("Found " + shows.size() + " shows today:");
            shows.forEach(RMIClient::displayShow);
        }
    }
    
    // Ticket operations implementations
    private static void addTicket() throws Exception {
        System.out.println("\n----- Adding New Ticket -----");
        
        // First, list customers and shows for reference
        System.out.println("Available customers:");
        listAllCustomers();
        
        System.out.println("Available shows:");
        listAllShows();
        
        String ticketNumber = getStringInput("Enter ticket number: ");
        String customerId = getStringInput("Enter customer ID: ");
        String showId = getStringInput("Enter show ID: ");
        
        Customer customer = service.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found with ID: " + customerId);
            return;
        }
        
        Show show = service.getShowById(showId);
        if (show == null) {
            System.out.println("Show not found with ID: " + showId);
            return;
        }
        
        String seat = getStringInput("Enter seat number: ");
        
        System.out.println("Select ticket type:");
        System.out.println("1. VIP");
        System.out.println("2. STANDARD");
        int typeChoice = getIntInput("Enter choice (1-2): ");
        Ticket.TicketType type = (typeChoice == 1) ? Ticket.TicketType.VIP : Ticket.TicketType.STANDARD;
        
        double price = getDoubleInput("Enter ticket price: ");
        
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(ticketNumber);
        ticket.setSeat(seat);
        ticket.setType(type);
        ticket.setPrice(price);
        ticket.setBookingDate(java.time.LocalDate.now());
        ticket.setCustomer(customer);
        ticket.setShow(show);
        
        Ticket addedTicket = service.addTicket(ticket);
        System.out.println("Ticket added successfully with number: " + addedTicket.getTicketNumber());
    }
    
    private static void updateTicket() throws Exception {
        System.out.println("\n----- Updating Ticket -----");
        
        String ticketNumber = getStringInput("Enter ticket number to update: ");
        Ticket ticket = service.getTicketById(ticketNumber);
        
        if (ticket == null) {
            System.out.println("Ticket not found with number: " + ticketNumber);
            return;
        }
        
        System.out.println("Current ticket details: ");
        displayTicket(ticket);
        
        String seat = getStringInput("Enter new seat (or press Enter to keep current): ");
        if (!seat.isEmpty()) {
            ticket.setSeat(seat);
        }
        
        String typeStr = getStringInput("Enter new type (VIP/STANDARD) (or press Enter to keep current): ");
        if (!typeStr.isEmpty()) {
            if (typeStr.equalsIgnoreCase("VIP")) {
                ticket.setType(Ticket.TicketType.VIP);
            } else if (typeStr.equalsIgnoreCase("STANDARD")) {
                ticket.setType(Ticket.TicketType.STANDARD);
            } else {
                System.out.println("Invalid ticket type. Must be VIP or STANDARD.");
                return;
            }
        }
        
        String priceStr = getStringInput("Enter new price (or press Enter to keep current): ");
        if (!priceStr.isEmpty()) {
            ticket.setPrice(Double.parseDouble(priceStr));
        }
        
        Ticket updatedTicket = service.updateTicket(ticket);
        System.out.println("Ticket updated successfully.");
    }
    
    private static void deleteTicket() throws Exception {
        System.out.println("\n----- Deleting Ticket -----");
        
        String ticketNumber = getStringInput("Enter ticket number to delete: ");
        Ticket ticket = service.getTicketById(ticketNumber);
        
        if (ticket == null) {
            System.out.println("Ticket not found with number: " + ticketNumber);
            return;
        }
        
        System.out.println("Ticket to be deleted: ");
        displayTicket(ticket);
        
        String confirm = getStringInput("Are you sure you want to delete this ticket? (y/n): ");
        if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
            service.deleteTicket(ticketNumber);
            System.out.println("Ticket deleted successfully.");
        } else {
            System.out.println("Ticket deletion cancelled.");
        }
    }
    
    private static void findTicketByNumber() throws Exception {
        System.out.println("\n----- Finding Ticket by Number -----");
        
        String ticketNumber = getStringInput("Enter ticket number: ");
        Ticket ticket = service.getTicketById(ticketNumber);
        
        if (ticket != null) {
            System.out.println("Ticket found:");
            displayTicket(ticket);
        } else {
            System.out.println("No ticket found with number: " + ticketNumber);
        }
    }
    
    private static void listAllTickets() throws Exception {
        System.out.println("\n----- All Tickets -----");
        
        List<Ticket> tickets = service.getAllTickets();
        if (tickets.isEmpty()) {
            System.out.println("No tickets found.");
        } else {
            tickets.forEach(RMIClient::displayTicket);
        }
    }
    
    private static void displayTicketSalesByMovie() throws Exception {
        System.out.println("\n----- Ticket Sales by Movie -----");
        
        Map<Movie, Double> salesByMovie = service.listTicketSalesByMovieSortedByTitle();
        
        if (salesByMovie.isEmpty()) {
            System.out.println("No ticket sales data available.");
        } else {
            System.out.println("Movie Title | Director | Total Sales");
            System.out.println("------------------------------------");
            
            double totalSales = 0.0;
            for (Map.Entry<Movie, Double> entry : salesByMovie.entrySet()) {
                Movie movie = entry.getKey();
                Double sales = entry.getValue();
                totalSales += sales;
                
                System.out.printf("%-30s | %-20s | $%.2f%n", 
                    movie.getTitle(), 
                    movie.getDirector(),
                    sales);
            }
            
            System.out.println("------------------------------------");
            System.out.printf("TOTAL SALES: $%.2f%n", totalSales);
        }
    }
    
    // Display methods for entities
    private static void displayCustomer(Customer customer) {
        System.out.println("Customer [ID: " + customer.getId() + 
                         ", Name: " + customer.getName() +
                         ", Year of Birth: " + customer.getYearOfBirth() +
                         ", Phone: " + customer.getPhone() +
                         ", Address: " + customer.getAddress() + "]");
    }
    
    private static void displayMovie(Movie movie) {
        System.out.println("Movie [ID: " + movie.getId() + 
                         ", Title: " + movie.getTitle() +
                         ", Genre: " + movie.getGenre() +
                         ", Year: " + movie.getReleaseYear() +
                         ", Director: " + movie.getDirector() +
                         ", Duration: " + movie.getDuration() + " min]");
    }
    
    private static void displayShow(Show show) {
        Movie movie = show.getMovie();
        String movieTitle = (movie != null) ? movie.getTitle() : "Unknown";
        
        System.out.println("Show [ID: " + show.getId() + 
                         ", Movie: " + movieTitle +
                         ", DateTime: " + show.getShowDateTime() +
                         ", Hall: " + show.getHallName() + "]");
    }
    
    private static void displayTicket(Ticket ticket) {
        Customer customer = ticket.getCustomer();
        Show show = ticket.getShow();
        
        String customerName = (customer != null) ? customer.getName() : "Unknown";
        String showInfo = (show != null) ? 
                        (show.getMovie() != null ? show.getMovie().getTitle() : "Unknown") + " at " + show.getShowDateTime() 
                        : "Unknown";
        
        System.out.println("Ticket [Number: " + ticket.getTicketNumber() + 
                         ", Seat: " + ticket.getSeat() +
                         ", Type: " + ticket.getType() +
                         ", Price: $" + ticket.getPrice() +
                         ", Customer: " + customerName +
                         ", Show: " + showInfo +
                         ", Booked on: " + ticket.getBookingDate() + "]");
    }
    
    // Helper methods for input
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }
    
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}

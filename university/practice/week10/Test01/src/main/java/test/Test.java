package test;

import model.Movie;
import model.Show;
import service.MovieTicketService;
import service.impl.MovieTicketServiceImpl;
import util.DataInitializer;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        try {
            // Initialize sample data
            System.out.println("Initializing sample data...");
            DataInitializer.initializeData();
            
            // Create an instance of the service for testing
            MovieTicketService service = new MovieTicketServiceImpl();
            
            // Test the functionality
            System.out.println("=== Testing Movie Ticket Service ===");
            
            // List shows by director on the current date
            System.out.println("\n1. Shows today by Christopher Nolan:");
            List<Show> shows = service.listShowsByCurrentDateAndDirector("Nolan");
            if (shows.isEmpty()) {
                System.out.println("No shows found.");
            } else {
                for (Show show : shows) {
                    System.out.printf("Show ID: %s, Movie: %s, Hall: %s, Time: %s%n",
                            show.getId(),
                            show.getMovie().getTitle(),
                            show.getHallName(),
                            show.getShowDateTime());
                }
            }
            
            // List ticket sales by movie
            System.out.println("\n2. Ticket Sales by Movie:");
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
            
            // Test movie ID validation
            System.out.println("\n3. Testing Movie ID Validation:");
            try {
                Movie invalidMovie = new Movie();
                invalidMovie.setId("ABC123"); // Invalid ID format
                invalidMovie.setTitle("Invalid Movie");
                invalidMovie.setDirector("Test Director");
                invalidMovie.setDuration(120);
                service.addMovie(invalidMovie);
                System.out.println("ERROR: Should have thrown exception for invalid movie ID");
            } catch (RemoteException e) {
                System.out.println("Successfully caught exception: " + e.getMessage());
            }
            
            // Test show update validation
            System.out.println("\n4. Testing Show Update with Bookings:");
            try {
                // Get a show with tickets (from our sample data)
                Show show = null;
                for (Show s : service.getAllShows()) {
                    if (!s.getTickets().isEmpty()) {
                        show = s;
                        break;
                    }
                }
                
                if (show != null) {
                    System.out.println("Attempting to update show with ID: " + show.getId() + " that has bookings");
                    show.setHallName("New Hall Name");
                    service.updateShow(show);
                    System.out.println("ERROR: Should have thrown exception for updating show with bookings");
                } else {
                    System.out.println("No show with tickets found in sample data. Skipping test.");
                }
            } catch (RemoteException e) {
                System.out.println("Successfully caught exception: " + e.getMessage());
            }
            
        } catch (RemoteException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
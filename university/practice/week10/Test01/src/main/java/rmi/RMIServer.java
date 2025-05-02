package rmi;

import service.MovieTicketService;
import service.impl.MovieTicketServiceImpl;
import util.DataInitializer;
import util.JPAUtil;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.InetAddress;

public class RMIServer {
    // Using last 4 digits of MSSV as port number - replace with your actual MSSV
    private static final int PORT = 1234; // Change to your last 4 digits of MSSV
    private static final String SERVICE_NAME = "MovieTicketService";
    
    public static void main(String[] args) {
        try {
            System.out.println("Initializing database with sample data...");
            DataInitializer.initializeData();
            
            // Create the service instance
            MovieTicketService service = new MovieTicketServiceImpl();
            
            // Create the registry on the specified port
            Registry registry = LocateRegistry.createRegistry(PORT);
            
            // Bind the service to the registry
            registry.rebind(SERVICE_NAME, service);
            
            // Get the hostname
            String hostName = InetAddress.getLocalHost().getHostName();
            
            System.out.println("RMI Server is running on " + hostName + ":" + PORT);
            System.out.println("Service '" + SERVICE_NAME + "' is registered and ready for use");
            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

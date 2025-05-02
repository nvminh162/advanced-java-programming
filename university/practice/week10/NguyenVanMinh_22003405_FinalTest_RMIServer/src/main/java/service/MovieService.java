package service;

import model.Movie;

import java.rmi.RemoteException;
import java.util.Map;

public interface MovieService extends GenericService<Movie, String> {
    Map<Movie, Double> listTicketSalesByMovieSortedByTitle() throws RemoteException;
    
    boolean validateMovie(Movie movie) throws RemoteException;
}

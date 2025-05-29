package service.impl;

import dao.MovieDAO;
import model.Movie;
import service.MovieService;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.regex.Pattern;

public class MovieServiceImpl extends GenericServiceImpl<Movie, String> implements MovieService {
    private MovieDAO movieDAO;
    private static final Pattern MOVIE_ID_PATTERN = Pattern.compile("^M\\d{3,}$");

    public MovieServiceImpl(MovieDAO movieDAO) throws RemoteException {
        super(movieDAO);
        this.movieDAO = movieDAO;
    }

    @Override
    public Map<Movie, Double> listTicketSalesByMovieSortedByTitle() throws RemoteException {
        return movieDAO.listTicketSalesByMovieSortedByTitle();
    }
    
    @Override
    public boolean validateMovie(Movie movie) throws RemoteException {
        if (movie == null) {
            throw new RemoteException("Movie cannot be null");
        }
        
        if (movie.getId() == null || !MOVIE_ID_PATTERN.matcher(movie.getId()).matches()) {
            throw new RemoteException("Movie ID must start with 'M' followed by at least 3 digits");
        }
        
        if (movie.getDuration() <= 0) {
            throw new RemoteException("Movie duration must be greater than 0");
        }
        
        return true;
    }
    
    @Override
    public boolean save(Movie movie) throws RemoteException {
        validateMovie(movie);
        return movieDAO.save(movie);
    }
    
    @Override
    public boolean update(Movie movie) throws RemoteException {
        validateMovie(movie);
        return movieDAO.update(movie);
    }
}

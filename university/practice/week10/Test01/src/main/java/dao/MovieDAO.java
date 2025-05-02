package dao;

import model.Movie;
import java.util.List;
import java.util.Map;

public interface MovieDAO extends GenericDAO<Movie, String> {
    List<Movie> findByDirectorLike(String director);
    boolean validateMovieId(String id);
    Map<Movie, Double> getTicketSalesByMovieSortedByTitle();
}
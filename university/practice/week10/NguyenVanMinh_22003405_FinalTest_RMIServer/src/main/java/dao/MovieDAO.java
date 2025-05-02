package dao;

import model.Movie;

import java.util.Map;

public interface MovieDAO extends GenericDAO<Movie, String> {
    Map<Movie, Double> listTicketSalesByMovieSortedByTitle();
}

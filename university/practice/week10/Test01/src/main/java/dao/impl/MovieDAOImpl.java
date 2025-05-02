package dao.impl;

import dao.MovieDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Movie;
import util.JPAUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MovieDAOImpl extends GenericDAOImpl<Movie, String> implements MovieDAO {

    @Override
    public List<Movie> findByDirectorLike(String director) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Movie> query = em.createQuery(
                "SELECT m FROM Movie m WHERE m.director LIKE :director",
                Movie.class
            );
            query.setParameter("director", "%" + director + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean validateMovieId(String id) {
        // Movie ID must start with 'M' followed by at least 3 digits
        return id != null && Pattern.matches("^M\\d{3,}$", id);
    }

    @Override
    public Map<Movie, Double> getTicketSalesByMovieSortedByTitle() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // This query gets the sum of ticket prices for each movie
            List<Object[]> results = em.createQuery(
                "SELECT m, SUM(t.price) FROM Movie m " +
                "JOIN m.shows s " +
                "JOIN s.tickets t " +
                "GROUP BY m " +
                "ORDER BY m.title",
                Object[].class
            ).getResultList();
            
            // Convert results to a map
            Map<Movie, Double> salesByMovie = new LinkedHashMap<>();
            for (Object[] result : results) {
                Movie movie = (Movie) result[0];
                Double totalSales = (Double) result[1];
                salesByMovie.put(movie, totalSales);
            }
            
            return salesByMovie;
        } finally {
            em.close();
        }
    }
}
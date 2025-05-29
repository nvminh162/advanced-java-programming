package dao.impl;

import dao.MovieDAO;
import jakarta.persistence.EntityManager;
import model.Movie;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieDAOImpl extends GenericDAOImpl<Movie, String> implements MovieDAO {
    public MovieDAOImpl(Class<Movie> clazz) {
        super(clazz);
    }

    public MovieDAOImpl(EntityManager em, Class<Movie> clazz) {
        super(em, clazz);
    }

    @Override
    public Map<Movie, Double> listTicketSalesByMovieSortedByTitle() {
        String jpql = """
                SELECT m, SUM(t.price) as total
                FROM Movie m
                JOIN m.shows s
                JOIN s.tickets t
                GROUP BY m
                ORDER BY m.title
                """;
                
        return em.createQuery(jpql, Object[].class)
                .getResultList()
                .stream()
                .collect(Collectors.toMap(
                        result -> (Movie) result[0],
                        result -> (Double) result[1],
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}

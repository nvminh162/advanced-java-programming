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
        if (id == null) return false;
        // ID phải bắt đầu bằng 'M' và theo sau bởi ít nhất 3 chữ số
        Pattern pattern = Pattern.compile("^M\\d{3,}$");
        return pattern.matcher(id).matches();
    }

    @Override
    public Map<Movie, Double> getTicketSalesByMovieSortedByTitle() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Lấy tất cả các bộ phim đã có vé được mua
            List<Object[]> results = em.createQuery(
                "SELECT m, SUM(t.price) FROM Movie m " +
                "JOIN m.shows s " +
                "JOIN s.tickets t " +
                "GROUP BY m " +
                "ORDER BY m.title ASC",
                Object[].class
            ).getResultList();
            
            // Chuyển kết quả sang Map
            Map<Movie, Double> salesByMovie = new LinkedHashMap<>(); // LinkedHashMap giữ thứ tự chèn
            
            for (Object[] result : results) {
                Movie movie = (Movie) result[0];
                Double sales = (Double) result[1];
                salesByMovie.put(movie, sales);
            }
            
            return salesByMovie;
        } finally {
            em.close();
        }
    }
}
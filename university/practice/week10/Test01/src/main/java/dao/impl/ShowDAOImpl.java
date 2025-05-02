package dao.impl;

import dao.ShowDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Show;
import util.JPAUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ShowDAOImpl extends GenericDAOImpl<Show, String> implements ShowDAO {

    @Override
    public List<Show> findShowsByCurrentDateAndDirector(String director) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // Get current date at start of day and end of day
            LocalDate today = LocalDate.now();
            LocalDateTime startOfDay = today.atStartOfDay();
            LocalDateTime endOfDay = today.atTime(LocalTime.MAX);
            
            // Query for shows on current date with movies directed by the specified director
            TypedQuery<Show> query = em.createQuery(
                "SELECT s FROM Show s JOIN s.movie m " +
                "WHERE s.showDateTime BETWEEN :startOfDay AND :endOfDay " +
                "AND m.director LIKE :director",
                Show.class
            );
            
            query.setParameter("startOfDay", startOfDay);
            query.setParameter("endOfDay", endOfDay);
            query.setParameter("director", "%" + director + "%");
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
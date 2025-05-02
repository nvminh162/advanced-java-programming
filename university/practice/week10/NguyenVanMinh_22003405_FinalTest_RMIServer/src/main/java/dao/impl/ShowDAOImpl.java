package dao.impl;

import dao.ShowDAO;
import jakarta.persistence.EntityManager;
import model.Movie;
import model.Show;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ShowDAOImpl extends GenericDAOImpl<Show, String> implements ShowDAO {
    public ShowDAOImpl(Class<Show> clazz) {
        super(clazz);
    }

    public ShowDAOImpl(EntityManager em, Class<Show> clazz) {
        super(em, clazz);
    }

    @Override
    public List<Show> listShowsByCurrentDateAndDirector(String director) {
        if (director == null || director.trim().isEmpty()) {
            return Collections.emptyList();
        }

        //Test Time in database .:!:.
        LocalDateTime startOfDay = LocalDateTime.of(2024, 05, 11 , 0, 0, 0);
        LocalDateTime endOfDay = LocalDateTime.of(2024, 05, 11 , 23, 59, 59);

        //Current time =))
        /* LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX); */

        String jpql = """
                SELECT s FROM Show s JOIN s.movie m
                WHERE s.showDateTime BETWEEN :startOfDay AND :endOfDay
                AND m.director LIKE :director
                """;
        return em
                .createQuery(jpql, Show.class)
                .setParameter("startOfDay", startOfDay)
                .setParameter("endOfDay", endOfDay)
                .setParameter("director", "%" + director + "%")
                .getResultList();
    }
}

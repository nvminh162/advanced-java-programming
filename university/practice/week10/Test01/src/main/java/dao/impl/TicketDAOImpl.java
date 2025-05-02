package dao.impl;

import dao.TicketDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Ticket;
import util.JPAUtil;

import java.util.List;

public class TicketDAOImpl extends GenericDAOImpl<Ticket, String> implements TicketDAO {

    @Override
    public List<Ticket> findByShowId(String showId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Ticket> query = em.createQuery(
                "SELECT t FROM Ticket t WHERE t.show.id = :showId", 
                Ticket.class
            );
            query.setParameter("showId", showId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
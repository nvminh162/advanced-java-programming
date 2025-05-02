package dao.impl;

import dao.TicketDAO;
import jakarta.persistence.EntityManager;
import model.Ticket;

public class TicketDAOImpl extends GenericDAOImpl<Ticket, String> implements TicketDAO {
    public TicketDAOImpl(Class<Ticket> clazz) {
        super(clazz);
    }

    public TicketDAOImpl(EntityManager em, Class<Ticket> clazz) {
        super(em, clazz);
    }
}

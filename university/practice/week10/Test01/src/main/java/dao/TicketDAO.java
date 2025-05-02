package dao;

import model.Ticket;
import java.util.List;

public interface TicketDAO extends GenericDAO<Ticket, String> {
    List<Ticket> findByShowId(String showId);
}
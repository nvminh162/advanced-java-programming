package service.impl;

import dao.GenericDAO;
import dao.TicketDAO;
import model.Ticket;
import service.TicketService;

import java.rmi.RemoteException;

public class TicketServiceImpl extends GenericServiceImpl<Ticket, String> implements TicketService {
    private TicketDAO ticketDAO;

    public TicketServiceImpl(TicketDAO ticketDAO) throws RemoteException {
        super(ticketDAO);
        this.ticketDAO = ticketDAO;
    }
}

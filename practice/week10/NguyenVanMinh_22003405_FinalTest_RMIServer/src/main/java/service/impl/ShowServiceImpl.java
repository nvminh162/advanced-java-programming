package service.impl;

import dao.GenericDAO;
import dao.ShowDAO;
import model.Show;
import service.ShowService;

import java.rmi.RemoteException;
import java.util.List;

public class ShowServiceImpl extends GenericServiceImpl<Show, String> implements ShowService {
    private ShowDAO showDAO;

    public ShowServiceImpl(ShowDAO showDAO) throws RemoteException {
        super(showDAO);
        this.showDAO = showDAO;
    }

    @Override
    public List<Show> listShowsByCurrentDateAndDirector(String director) throws RemoteException {
        return showDAO.listShowsByCurrentDateAndDirector(director);
    }

    @Override
    public boolean validateShowHasNoTickets(Show show) throws RemoteException {
        if (show == null) {
            throw new RemoteException("Show cannot be null");
        }
        if (show.getTickets() != null) {
            throw new RemoteException("Cannot update show because tickets have already been booked for this show");
        }
        return true;
    }

   @Override
   public boolean update(Show show) throws RemoteException {
        validateShowHasNoTickets(show);
       return showDAO.update(show);
   }
}

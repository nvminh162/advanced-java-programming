package service;

import model.Show;

import java.rmi.RemoteException;
import java.util.List;

public interface ShowService extends GenericService<Show, String> {
    List<Show> listShowsByCurrentDateAndDirector(String director) throws RemoteException;
    
    boolean validateShowHasNoTickets(Show show) throws RemoteException;
}

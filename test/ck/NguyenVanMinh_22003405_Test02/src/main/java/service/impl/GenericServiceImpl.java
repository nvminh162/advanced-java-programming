package service.impl;

import dao.GenericDAO;
import service.GenericService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class GenericServiceImpl<T, ID> extends UnicastRemoteObject implements GenericService<T, ID> {
    protected GenericDAO<T, ID> genericDAO;

    public GenericServiceImpl(GenericDAO<T, ID> genericDAO) throws RemoteException {
        this.genericDAO = genericDAO;
    }
    
    @Override
    public boolean save(T t) throws RemoteException {
        return genericDAO.save(t);
    }
}

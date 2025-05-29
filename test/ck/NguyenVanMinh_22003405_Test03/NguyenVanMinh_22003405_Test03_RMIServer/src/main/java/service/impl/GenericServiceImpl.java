package service.impl;

import dao.GenericDAO;
import service.GenericService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GenericServiceImpl<T, ID> extends UnicastRemoteObject implements GenericService<T, ID> {
    protected GenericDAO<T, ID> genericDAO;

    public GenericServiceImpl(GenericDAO<T, ID> genericDAO) throws RemoteException {
        this.genericDAO = genericDAO;
    }
}

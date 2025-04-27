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
    public T findById(ID id) throws RemoteException {
        return genericDAO.findById(id);
    }

    @Override
    public List<T> findAll() throws RemoteException {
        return genericDAO.findAll();
    }

    @Override
    public boolean save(T t) throws RemoteException {
        return genericDAO.save(t);
    }

    @Override
    public boolean update(T t) throws RemoteException {
        return genericDAO.update(t);
    }

    @Override
    public boolean delete(ID id) throws RemoteException {
        return genericDAO.delete(id);
    }
}

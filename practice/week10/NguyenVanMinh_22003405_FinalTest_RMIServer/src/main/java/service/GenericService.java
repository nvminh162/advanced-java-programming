package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GenericService<T, ID> extends Remote {
    T findById(ID id) throws RemoteException;

    List<T> findAll() throws RemoteException;

    boolean save(T entity) throws RemoteException;

    boolean update(T entity) throws RemoteException;

    boolean delete(ID id) throws RemoteException;
}

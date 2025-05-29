package service;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GenericService<T, ID> extends Remote {
    boolean save(T t) throws RemoteException;
}

package iuh.fit.util;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICal extends Remote {
    int sum(int a, int b) throws RemoteException;
    int sub(int a, int b) throws RemoteException;
}

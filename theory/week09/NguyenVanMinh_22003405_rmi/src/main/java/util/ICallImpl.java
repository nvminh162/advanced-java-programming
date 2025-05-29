package util;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ICallImpl extends UnicastRemoteObject implements ICal {
    public ICallImpl() throws RemoteException {}

    @Override
    public int sum(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public int sub(int a, int b) throws RemoteException {
        return a - b;
    }
}

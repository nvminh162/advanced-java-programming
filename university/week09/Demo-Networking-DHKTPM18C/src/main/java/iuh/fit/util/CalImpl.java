package iuh.fit.util;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalImpl extends UnicastRemoteObject implements ICal {
    public CalImpl() throws Exception {
    }

    @Override
    public int sum(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public int sub(int a, int b) throws RemoteException {
        return a - b;
    }
}

package iuh.fit.client;

import iuh.fit.util.CalImpl;
import iuh.fit.util.ICal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();

        ICal obj = (ICal) context.lookup("rmi://paul:3405/obj");

        int total = obj.sum(10, 20);
        System.out.println("Total: " + total);
    }
}

package iuh.fit.server;

import iuh.fit.util.CalImpl;
import iuh.fit.util.ICal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws Exception {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(3405);

        ICal obj = new CalImpl(); //Java remote object
        context.bind("rmi://paul:3405/obj", obj);

        System.out.println("Server started");
    }
}

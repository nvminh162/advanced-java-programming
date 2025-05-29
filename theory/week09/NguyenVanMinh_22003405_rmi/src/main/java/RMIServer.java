import util.ICal;
import util.ICallImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws Exception {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(3405);

        ICal obj = new ICallImpl();
        context.bind("rmi://paul:3405/obj", obj);

        System.out.println("*** Server ready!!! ***");
    }
}

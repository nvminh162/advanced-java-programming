import util.ICal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RMIClient {
    public static void main(String[] args) throws Exception {
        Context context = new InitialContext();

        ICal obj = (ICal) context.lookup("rmi://paul:3405/obj");

        int sum = obj.sum(10, 20);
        int sub = obj.sub(10, 20);
        System.out.println(sum + " | " + sub);
    }
}

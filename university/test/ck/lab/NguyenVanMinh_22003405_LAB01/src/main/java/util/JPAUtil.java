package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private final static String PERSISTENCE_UNIT_NAME_MSSQL = "mssql_pu";
    private final static String PERSISTENCE_UNIT_NAME_MARIADB = "mariadb_pu";
    private static EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME_MARIADB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        if(emf != null && emf.isOpen()) emf.close();
    }
}
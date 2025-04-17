package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAutil {
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("mariadb_pu");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}

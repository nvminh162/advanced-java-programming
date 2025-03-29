import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Student;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        EntityManager em = Persistence
                .createEntityManagerFactory("mariadb_pu")
                .createEntityManager();

        EntityTransaction tr = em.getTransaction();
        tr.begin();

        Student st = new Student("Nguyen", "Minh", LocalDateTime.of(2004, 2, 16, 21, 59, 59, 59));
        em.persist(st);

        tr.commit();
    }
}

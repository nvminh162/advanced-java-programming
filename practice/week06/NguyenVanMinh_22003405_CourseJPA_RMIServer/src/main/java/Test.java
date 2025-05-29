import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Instructor;
import model.Student;

import java.time.LocalDateTime;

public class Test {
    /*1.*/
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb_pu").createEntityManager();
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Student student = new Student("Minh", "Nguyen", LocalDateTime.of(2004, 2, 1, 0, 0, 0));
        Instructor instructor = new Instructor("Minh", "Nguyen", LocalDateTime.of(2004, 2, 1, 0, 0, 0));
        em.persist(student);
        em.persist(instructor);
        tr.commit();
    }
}

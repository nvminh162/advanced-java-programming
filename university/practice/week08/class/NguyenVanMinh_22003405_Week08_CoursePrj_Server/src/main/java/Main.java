import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Student;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
                .createEntityManager();

//        Student student = new Student();
//        student.setFirstName("Lan");
//        student.setLastName("Le");
//        student.setEnrollmentDate(LocalDateTime.of(2022, 1,1,0,0,0));
//
//        EntityTransaction tr = em.getTransaction();
//        tr.begin();
//        em.persist(student);
//
//        tr.commit();
    }
}

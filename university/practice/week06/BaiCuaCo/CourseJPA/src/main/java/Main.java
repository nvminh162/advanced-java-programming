import dao.StudentDAO;
import dao.impl.StudentDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Student;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws Exception{

        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = studentDAO.findStudentByID(28);
        System.out.println(student);


//        EntityManager em = Persistence.createEntityManagerFactory("mariadb_pu")
//                .createEntityManager();
//
//        EntityTransaction tr = em.getTransaction();
//
//        tr.begin();
//
//        Student student = new Student("Lan", "Le", LocalDateTime.of(2025,1,1,0,0,0));
//
//        em.persist(student);
//
//
//        tr.commit();

    }
}

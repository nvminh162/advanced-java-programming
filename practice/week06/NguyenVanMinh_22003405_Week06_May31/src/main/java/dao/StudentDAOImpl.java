package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.AllArgsConstructor;
import model.Student;

import java.util.List;

@AllArgsConstructor
public class StudentDAOImpl {
    private EntityManager em;

    public StudentDAOImpl() {
        em = Persistence
                .createEntityManagerFactory("mariadb_pu")
                .createEntityManager();
    }

    public boolean addStudent(Student student){
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(student);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStudent(Student student){
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(student);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStudent(int studentID){
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            Student st = em.find(Student.class, studentID);
            em.remove(st);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public Student findStudentByID(int studentID){
        return em.find(Student.class, studentID);
    }

    public List<Student> listStudents() {
        return em.createQuery("from Student st", Student.class).getResultList();
    }
}

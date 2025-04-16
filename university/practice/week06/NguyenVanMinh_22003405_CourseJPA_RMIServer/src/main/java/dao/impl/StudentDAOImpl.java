package dao.impl;

import dao.StudentDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Student;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class StudentDAOImpl extends UnicastRemoteObject implements StudentDAO {
    private EntityManager em;

    public StudentDAOImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("mariadb_pu").createEntityManager();
    }

    @Override
    public boolean addStudent(Student student) throws RemoteException {
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

    @Override
    public boolean updateStudent(Student student) throws RemoteException {
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

    @Override
    public boolean deleteStudent(int studentId) throws RemoteException {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            Student student = em.find(Student.class, studentId);
            em.remove(student);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Student findStudentByID(int studentId) throws RemoteException {
        return em.find(Student.class, studentId);
    }

    @Override
    public List<String> listStudents() throws RemoteException {
        return em.createQuery("from Student st").getResultList();
    }

    public void aMethod(){}
}

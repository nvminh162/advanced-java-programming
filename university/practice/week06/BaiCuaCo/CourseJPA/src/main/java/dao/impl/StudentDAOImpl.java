package dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Student;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;

public class StudentDAOImpl extends UnicastRemoteObject implements dao.StudentDAO  {

    private EntityManager em ;

    public  StudentDAOImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("mariadb_pu")
                .createEntityManager();
    }

    @Override
    public boolean addStudent(Student student) throws RemoteException{
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(student);
            tr.commit();
            return true;
        }catch (Exception ex){
            tr.rollback();
            ex.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean updateStudent(Student student) throws RemoteException{
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(student);
            tr.commit();
            return true;
        }catch (Exception ex){
            tr.rollback();
            ex.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean deleteStudent(int studentID) throws RemoteException{
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            Student st = em.find(Student.class, studentID);
            em.remove(st);
            tr.commit();
            return true;
        }catch (Exception ex){
            tr.rollback();
            ex.printStackTrace();
        }

        return false;
    }
    @Override
    public Student findStudentByID(int studentID) throws RemoteException{
        return em.find(Student.class, studentID);
    }

    @Override
    public List<String> listStudents() throws RemoteException{
        return em.createQuery("from Student st")
                .getResultList();
    }


    public void aMethod(){

    }

}

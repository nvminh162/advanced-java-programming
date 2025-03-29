package dao;

import model.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StudentDAO extends Remote {
    boolean addStudent(Student student) throws RemoteException;

    boolean updateStudent(Student student) throws RemoteException;

    boolean deleteStudent(int studentID) throws RemoteException;

    Student findStudentByID(int studentID) throws RemoteException;

    List<String> listStudents() throws RemoteException;
}

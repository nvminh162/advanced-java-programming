package edu.iuh.fit;

import dao.StudentDAO;
import model.Student;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) throws NamingException, RemoteException {

        Context context = new InitialContext();

        StudentDAO studentDAO = (StudentDAO) context.lookup("rmi://H51M15:5656/studentDAO");

        Student st = studentDAO.findStudentByID(28);

        System.out.println(st);

    }

}

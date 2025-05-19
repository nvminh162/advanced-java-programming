package rmi;

import dao.QuestionDAO;
import dao.impl.QuestionDAOImpl;
import model.Question;
import service.QuestionService;
import service.impl.QuestionServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    private final static int PORT = 3405;

    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(PORT);

        QuestionDAO questionDAO = new QuestionDAOImpl(Question.class);
        QuestionService questionService = new QuestionServiceImpl(questionDAO);

        context.bind("rmi://paul:3405/questionService", questionService);

        System.out.println("RMI Server is running at port " + PORT);
    }
}
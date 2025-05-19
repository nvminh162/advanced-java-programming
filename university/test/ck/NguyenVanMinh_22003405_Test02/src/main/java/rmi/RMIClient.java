package rmi;

import model.Question;
import service.QuestionService;
import service.impl.QuestionServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class RMIClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();

        QuestionService questionService = (QuestionService) context.lookup("rmi://paul:3405/questionService");

        List<Question> listQuestionByLevelAndCategory = questionService.listQuestionByLevelAndCategory("Movie", Question.Level.MEDIUM);
        listQuestionByLevelAndCategory.forEach(System.out::println);
        System.out.println("************************************************");

        Map<Question.Level, Long> countQuestionByLevelInQuiz = questionService.countQuestionByLevelInQuiz("QZ119");
        System.out.println("LEVEL : COUNT");
        countQuestionByLevelInQuiz.forEach((key, value) -> System.out.println(key + " : " + value));
        System.out.println("************************************************");

        Question question = new Question();
        question.setId("999999");
        question.setType(Question.Type.FILL_IN_THE_BLANK);
        question.setLevel(Question.Level.HARD);
        question.setQuestionText("99999999999999999");
        Question addQuestionToCategory = questionService.addQuestionToCategory(question, "C101");
        System.out.println(addQuestionToCategory);
        System.out.println("************************************************");
    }
}
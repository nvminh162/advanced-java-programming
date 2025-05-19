package service;

import model.Question;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface QuestionService extends Remote {
    List<Question> listQuestionByLevelAndCategory(String categoryName, Question.Level level) throws RemoteException;

    Map<Question.Level, Long> countQuestionByLevelInQuiz(String quizId) throws RemoteException;

    Question addQuestionToCategory(Question question, String categoryId) throws RemoteException;
}

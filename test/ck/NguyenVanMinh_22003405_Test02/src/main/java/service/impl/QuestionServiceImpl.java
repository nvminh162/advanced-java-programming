package service.impl;

import dao.GenericDAO;
import dao.QuestionDAO;
import model.Question;
import service.QuestionService;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class QuestionServiceImpl extends GenericServiceImpl<Question, String> implements QuestionService {
    private QuestionDAO questionDAO;

    public QuestionServiceImpl(QuestionDAO questionDAO) throws RemoteException {
        super(questionDAO);
        this.questionDAO = questionDAO;
    }

    @Override
    public List<Question> listQuestionByLevelAndCategory(String categoryName, Question.Level level) throws RemoteException {
        return questionDAO.listQuestionByLevelAndCategory(categoryName, level);
    }

    @Override
    public Map<Question.Level, Long> countQuestionByLevelInQuiz(String quizId) throws RemoteException {
        return questionDAO.countQuestionByLevelInQuiz(quizId);
    }

    @Override
    public Question addQuestionToCategory(Question question, String categoryId) throws RemoteException {
        return questionDAO.addQuestionToCategory(question, categoryId);
    }
}

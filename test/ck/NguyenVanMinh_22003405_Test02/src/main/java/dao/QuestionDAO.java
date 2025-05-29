package dao;

import model.Category;
import model.Question;

import java.util.List;
import java.util.Map;

public interface QuestionDAO extends GenericDAO<Question, String> {
    List<Question> listQuestionByLevelAndCategory(String categoryName, Question.Level level);

    Map<Question.Level, Long> countQuestionByLevelInQuiz(String quizId);

    Question addQuestionToCategory(Question question, String categoryId);
}

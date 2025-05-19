package dao.impl;

import dao.QuestionDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Category;
import model.Question;
import util.JPAUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QuestionDAOImpl extends GenericDAOImpl<Question, String> implements QuestionDAO {
    public QuestionDAOImpl(Class<Question> clazz) {
        super(clazz);
    }

    public QuestionDAOImpl(EntityManager em, Class<Question> clazz) {
        super(em, clazz);
    }

    @Override
    public List<Question> listQuestionByLevelAndCategory(String categoryName, Question.Level level) {
        String jpql = """
                select q
                FROM Question q
                JOIN q.category c
                WHERE q.level = :level
                AND c.name LIKE CONCAT("%", :categoryName, "%")
                """;
        TypedQuery<Question> query = em.createQuery(jpql, Question.class);
        query.setParameter("categoryName", categoryName);
        query.setParameter("level", level);
        return query.getResultList();
    }

    @Override
    public Map<Question.Level, Long> countQuestionByLevelInQuiz(String quizId) {
        String jpql = """
                SELECT qs.level, COUNT(qs.level)
                FROM Question qs
                JOIN qs.quizzes qu
                WHERE qu.id = :quizId
                GROUP BY qs.level
                ORDER BY COUNT(qs.level) DESC
                """;
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setParameter("quizId", quizId);
        return query
                .getResultList()
                .stream()
                .collect(Collectors.toMap(
                        obj -> (Question.Level) obj[0],
                        obj -> (Long) obj[1],
                        (obj1, obj2) -> obj1,
                        LinkedHashMap::new
                ));
    }

    @Override
    public Question addQuestionToCategory(Question question, String categoryId) {
        Category category = em.find(Category.class, categoryId);
        if (category == null) return null;
        Question questionExist = em.find(Question.class, question.getId());
        if (questionExist != null) return null;

        question.setCategory(category);
        super.save(question);
        return question;
    }
}

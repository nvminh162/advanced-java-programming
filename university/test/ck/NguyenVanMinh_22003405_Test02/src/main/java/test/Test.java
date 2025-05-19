package test;

import dao.QuestionDAO;
import dao.impl.QuestionDAOImpl;
import model.Question;
import util.JPAUtil;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
//        JPAUtil.getEntityManager();

        /*QuestionDAO questionDAO = new QuestionDAOImpl(Question.class);
        List<Question> listQuestionByLevelAndCategory = questionDAO.listQuestionByLevelAndCategory("Movie", Question.Level.MEDIUM);
        listQuestionByLevelAndCategory.forEach(System.out::println);*/

        /*Map<Question.Level, Long> countQuestionByLevelInQuiz = new QuestionDAOImpl(Question.class).countQuestionByLevelInQuiz("QZ119");
        System.out.println("LEVEL : COUNT");
        countQuestionByLevelInQuiz.forEach((key, value) -> System.out.println(key + " : " + value));*/

        /*Question question = new Question();
        question.setId("99999");
        question.setType(Question.Type.FILL_IN_THE_BLANK);
        question.setLevel(Question.Level.HARD);
        question.setQuestionText("99999999999999999");
        Question addQuestionToCategory = new QuestionDAOImpl(Question.class).addQuestionToCategory(question, "C101");
        System.out.println(addQuestionToCategory);*/
    }
}

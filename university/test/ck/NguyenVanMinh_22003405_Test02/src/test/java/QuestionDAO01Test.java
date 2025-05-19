import dao.QuestionDAO;
import dao.impl.QuestionDAOImpl;
import model.Category;
import model.Question;
import model.Quiz;
import org.junit.jupiter.api.*;
import util.JPAUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionDAO01Test {
    private QuestionDAO questionDAO;

    @BeforeAll
    void startup() {
        questionDAO = new QuestionDAOImpl(Question.class);
    }

    @AfterAll
    void teardown() {
        questionDAO = null;
    }

    // Có 3 Phương thức
    // Mỗi phương thức / 2 hàm:
    // + Hàm test dữ liệu hợp lý
    // + Hàm test dữ liệu không hợp lý

    @Test
    void listQuestionByLevelAndCategoryValidTest() {
        List<Question> questions = questionDAO.listQuestionByLevelAndCategory("Movie", Question.Level.MEDIUM);
        assertNotNull(questions);
        questions.forEach(q -> assertEquals(Question.Level.MEDIUM, q.getLevel()));
    }

    @Test
    void listQuestionByLevelAndCategoryInvalidTest() {
        List<Question> questions = questionDAO.listQuestionByLevelAndCategory("Nguyen Van Minh", Question.Level.MEDIUM);
        assertEquals(0, questions.size());
    }

    @Test
    void countQuestionByLevelInQuizValidTest() {
        Map<Question.Level, Long> countQuestionByLevelInQuiz = questionDAO.countQuestionByLevelInQuiz("QZ119");
        assertNotNull(countQuestionByLevelInQuiz);
        countQuestionByLevelInQuiz.forEach((level, count) -> {
            assertNotNull(level);
            assertTrue(count >= 0);
        });
    }

    @Test
    void countQuestionByLevelInQuizInvalidTest() {
        Map<Question.Level, Long> countQuestionByLevelInQuiz = questionDAO.countQuestionByLevelInQuiz("Nguyen Van Minh");
        assertEquals(0, countQuestionByLevelInQuiz.size());
    }

    @Test
    void addQuestionToCategoryValidTest() {
        Question question = new Question();
        question.setId("22003405");
        question.setQuestionText("Nguyen Van Minh");

        assertEquals(question, questionDAO.addQuestionToCategory(question, "C101"));

        var em = JPAUtil.getEntityManager();
        Question findQuestion = em.find(Question.class, "22003405");
        assertNotNull(findQuestion);

        //Clear data test
        em.getTransaction().begin();
        em.remove(findQuestion);
        em.getTransaction().commit();
    }

    @Test
    void addQuestionToCategoryInvalidTest() {
        Question question = new Question();
        question.setId("22003405");
        question.setQuestionText("Nguyen Van Minh");

        assertNull(questionDAO.addQuestionToCategory(question, "Nguyen Van Minh"));
    }
}

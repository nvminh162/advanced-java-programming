import dao.BookDAO;
import dao.impl.BookDAOImpl;
import model.Book;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDAOTest {
    private BookDAO bookDAO;

    @BeforeAll
    void startup() {
        bookDAO = new BookDAOImpl(Book.class);
    }

    @AfterAll
    void teardown() {
        bookDAO = null;
    }

    // Mỗi phương thức DAO có input dữ liệu / 2 hàm:
    // + Hàm test dữ liệu hợp lý
    // + Hàm test dữ liệu không hợp lý

    @Test
    void listRatedBooksValidTest() {
        List<Book> listRatedBooks = new BookDAOImpl(Book.class).listRatedBooks("Brian W. Kernighan", 4);
        assertNotNull(listRatedBooks);
        assertFalse(listRatedBooks.isEmpty());
    }

    @Test
    void listRatedBooksInvalidTest() {
        List<Book> listRatedBooks = new BookDAOImpl(Book.class).listRatedBooks("Nguyen Van Minh", 4);
        assertEquals(0, listRatedBooks.size());
        assertTrue(listRatedBooks.isEmpty());
    }

    @Test
    void countBooksByAuthorTest() {
        Map<String, Long> countBooksByAuthor = new BookDAOImpl(Book.class).countBooksByAuthor();
        assertNotNull(countBooksByAuthor);
        assertFalse(countBooksByAuthor.isEmpty());
        countBooksByAuthor.forEach((key, value) -> {
            assertNotNull(key);
            assertTrue(value >= 0);
        });
    }

    @Test
    void updateReviewsValidTest() {
        boolean isUpdateReview = new BookDAOImpl(Book.class).updateReviews("9781617294945", 10, 4, "Nguyen Van Minh h");
        assertTrue(isUpdateReview);
    }

    @Test
    void updateReviewsInvalidTest() {
        boolean isUpdateReview = new BookDAOImpl(Book.class).updateReviews("9781617294945", 10, 4, "");
        assertFalse(isUpdateReview);
    }
}

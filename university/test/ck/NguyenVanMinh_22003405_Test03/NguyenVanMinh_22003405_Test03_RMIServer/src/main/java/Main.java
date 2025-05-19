import dao.impl.BookDAOImpl;
import model.Book;
import util.JPAUtil;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        JPAUtil.getEntityManager();

        List<Book> listRatedBooks = new BookDAOImpl(Book.class).listRatedBooks("Brian W. Kernighan", 4);
        listRatedBooks.forEach(System.out::println);

        Map<String, Long> countBooksByAuthor = new BookDAOImpl(Book.class).countBooksByAuthor();
        countBooksByAuthor.forEach((k, v) -> System.out.println(k + ": " + v));

        boolean isUpdateReview = new BookDAOImpl(Book.class).updateReviews("9781617294945", 5, 4, "1");
        System.out.println(isUpdateReview);
    }
}

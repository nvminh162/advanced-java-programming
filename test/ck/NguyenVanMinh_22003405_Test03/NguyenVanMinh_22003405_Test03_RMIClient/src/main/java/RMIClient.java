import model.Book;
import service.BookService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class RMIClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();

        BookService bookService = (BookService) context.lookup("rmi://paul:3405/bookService");

        List<Book> listRatedBooks = bookService.listRatedBooks("Brian W. Kernighan", 4);
        listRatedBooks.forEach(System.out::println);
        System.out.println("+++++++++++++++++++++++++++++++++++");

        Map<String, Long> countBooksByAuthor = bookService.countBooksByAuthor();
        countBooksByAuthor.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("+++++++++++++++++++++++++++++++++++");

        boolean isUpdateReview = bookService.updateReviews("9781617294945", 5, 4, "1");
        System.out.println(isUpdateReview);
        System.out.println("+++++++++++++++++++++++++++++++++++");
    }
}

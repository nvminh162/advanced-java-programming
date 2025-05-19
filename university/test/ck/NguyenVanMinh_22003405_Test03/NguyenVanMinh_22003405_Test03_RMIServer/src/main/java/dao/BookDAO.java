package dao;

import model.Book;

import java.util.List;
import java.util.Map;

public interface BookDAO extends GenericDAO<Book, String> {
    List<Book> listRatedBooks(String author, int rating);

    Map<String, Long> countBooksByAuthor();

    boolean updateReviews(String isbn, int readerID, int rating, String comment);
}

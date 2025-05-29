package service;

import model.Book;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface BookService extends GenericService<Book, String> {
    List<Book> listRatedBooks(String author, int rating) throws RemoteException;

    Map<String, Long> countBooksByAuthor() throws RemoteException;

    boolean updateReviews(String isbn, int readerID, int rating, String comment) throws RemoteException;
}

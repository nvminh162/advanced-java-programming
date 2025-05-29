package service.impl;

import dao.BookDAO;
import dao.GenericDAO;
import model.Book;
import service.BookService;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class BookServiceImpl extends GenericServiceImpl<Book, String> implements BookService {
    private BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) throws RemoteException {
        super(bookDAO);
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> listRatedBooks(String author, int rating) throws RemoteException {
        return bookDAO.listRatedBooks(author, rating);
    }

    @Override
    public Map<String, Long> countBooksByAuthor() throws RemoteException {
        return bookDAO.countBooksByAuthor();
    }

    @Override
    public boolean updateReviews(String isbn, int readerID, int rating, String comment) throws RemoteException {
        return bookDAO.updateReviews(isbn, readerID, rating, comment);
    }
}

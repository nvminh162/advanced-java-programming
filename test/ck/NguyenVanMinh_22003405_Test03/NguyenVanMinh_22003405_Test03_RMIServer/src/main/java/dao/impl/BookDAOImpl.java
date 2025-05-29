package dao.impl;

import dao.BookDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Book;
import model.Person;
import model.Reviews;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookDAOImpl extends GenericDAOImpl<Book, String> implements BookDAO {
    public BookDAOImpl(Class<Book> clazz) {
        super(clazz);
    }

    public BookDAOImpl(EntityManager em, Class<Book> clazz) {
        super(em, clazz);
    }

    @Override
    public List<Book> listRatedBooks(String author, int rating) {
        String jpql = """
                SELECT b
                FROM Book b
                JOIN b.authors a
                JOIN b.reviews r
                WHERE a = :author AND r.rating >= :rating
                """;
        TypedQuery<Book> query = em.createQuery(jpql, Book.class);
        query.setParameter("author", author);
        query.setParameter("rating", rating);
        return query.getResultList();
    }

    @Override
    public Map<String, Long> countBooksByAuthor() {
        String jpql = """
                SELECT a, COUNT(bt)
                FROM BookTranslation bt
                JOIN bt.authors a
                GROUP BY a
                ORDER BY a
                """;
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query.getResultList()
                .stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> (Long) obj[1],
                        (obj1, obj2) -> obj1,
                        LinkedHashMap::new
                ));
    }

    @Override
    public boolean updateReviews(String isbn, int readerID, int rating, String comment) {
        Book book = em.find(Book.class, isbn);
        Person person = em.find(Person.class, readerID);
        if (book == null || person == null) return false;
        if(rating < 1 || rating > 5) return false;
        if(comment.isBlank()) return false;

        Reviews reviews = new Reviews();
        reviews.setBook(book);
        reviews.setPerson(person);
        reviews.setRating(rating);
        reviews.setComment(comment);

        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.merge(reviews);
        tr.commit();
        return true;
    }
}

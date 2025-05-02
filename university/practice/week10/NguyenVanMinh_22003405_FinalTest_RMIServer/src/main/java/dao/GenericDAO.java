package dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    T findById(ID id);

    List<T> findAll();

    boolean save(T entity);

    boolean update(T entity);

    boolean delete(ID id);
}

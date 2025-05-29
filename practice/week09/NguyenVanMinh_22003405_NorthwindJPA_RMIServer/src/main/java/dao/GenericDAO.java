package dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    T findById(ID id);

    List<T> findAll();

    boolean save(T t);

    boolean update(T t);

    boolean delete(ID id);
}

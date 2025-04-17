package service;

import java.util.List;

public interface GenericService<T, ID> {
    boolean save(T t);
    boolean update(T t);
    T findById(ID id);
    List<T> getAll();
}

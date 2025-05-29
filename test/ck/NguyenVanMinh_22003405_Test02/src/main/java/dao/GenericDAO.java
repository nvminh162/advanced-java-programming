package dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    boolean save(T t);
}

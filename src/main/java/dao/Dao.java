package dao;

import java.util.List;

public interface Dao <T>{

    T create(T obj);
    T update(T obj);
    int mask(T obj);
    T find(T obj);
    List<T> getAll(T obj, int limit);
}

package com.neb.nebotools.dao;

import java.util.List;

public interface Dao <T>{

    T create(T obj);
    T update(T obj);
    int mask(T obj);
    T find(String id);
    List<T> getAll(T obj, int limit);
}

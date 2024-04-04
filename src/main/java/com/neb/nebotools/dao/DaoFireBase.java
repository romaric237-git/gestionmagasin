package com.neb.nebotools.dao;

import java.util.List;

public abstract class DaoFireBase<T> implements Dao<T>{
    @Override
    public T create(T obj) {
        return null;
    }

    @Override
    public T update(T obj) {
        return null;
    }

    @Override
    public int mask(T obj) {
        return 0;
    }

    @Override
    public T find(T obj) {
        return null;
    }

    @Override
    public List<T> getAll(T obj, int limit) {
        return null;
    }
}

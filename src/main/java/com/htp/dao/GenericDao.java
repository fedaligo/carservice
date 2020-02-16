package com.htp.dao;

import java.util.List;

public interface GenericDao<T, K> {

    void create(final T entity);

    List<T> readAll();

    T readById(K id);

    T updateById(T entity);

    void deleteById(K id);

    //T save(T entity);


}


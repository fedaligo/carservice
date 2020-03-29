package com.htp.repository;

import java.util.List;

public interface GenericDao<T, K> {

    void create(final T entity);

    List<T> findAll();

    T findById(K id);

    //T updateById(T entity);
    List<T> update(T entity);

    void deleteById(K id);

    //T save(T entity);


}


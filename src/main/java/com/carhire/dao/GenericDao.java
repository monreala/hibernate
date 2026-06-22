package com.carhire.dao;
import java.util.List;

public interface GenericDao <T,ID>{
    void save(T entity);
    T findById(Class<T> entityClass, ID id);
    List<T> findAll(Class<T> entityClass);
    void update(T entity);
    void delete(T entity);
}

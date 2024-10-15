package com.fihriyasmine.tp3_stars_recycleview.dao;

import java.util.List;

public interface IDao<T>{
    boolean create(T o);
    boolean update(T o);
    boolean delete(T o);
    T findById(int id);
    List<T> findAll();
}
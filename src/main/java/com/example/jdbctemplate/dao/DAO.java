package com.example.jdbctemplate.dao;

import com.example.jdbctemplate.model.Course;

import java.util.List;
import java.util.Optional;

public interface DAO<T>{

    List<T> list();

    void create(T t);

    Optional<T>  get(int id);

    void update(Course course, int id);

    void delete(int id);
}

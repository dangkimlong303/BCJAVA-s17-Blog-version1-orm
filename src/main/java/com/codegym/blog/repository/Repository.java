package com.codegym.blog.repository;

import com.codegym.blog.model.Blog;

import java.util.List;

public interface Repository<T> {

    List<T> findAll();

    Blog findById(Long id);

    void save(T t);

    void remove(Long id);
}

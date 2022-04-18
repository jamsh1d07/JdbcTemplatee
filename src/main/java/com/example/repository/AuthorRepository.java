package com.example.repository;

import com.example.entity.Author;

import java.util.List;

public interface AuthorRepository {

    int save(Author author);
    int update(Author author,Integer id);
    Author findById(Integer id);
    List<Author> findAll();

}

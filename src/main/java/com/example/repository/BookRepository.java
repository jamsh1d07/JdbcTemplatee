package com.example.repository;

import com.example.entity.Book;

import java.util.List;

public interface BookRepository {

    int save(Book book);
    int update(Book book,Integer id);
    Book findById(Integer id);
    int deletById(Integer id);
    List<Book> findAll();
    int deletAll();

}

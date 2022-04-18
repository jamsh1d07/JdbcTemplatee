package com.example.repository;

import com.example.entity.Category;

import java.util.List;

public interface CategoryRepository {

    int save(Category category);

    //    int update(Category category);
    int update(Category category, Integer id);

    Category findById(Integer id);

    int deleteById(Integer id);

    List<Category> findAll();

    int deleteAll();

}
